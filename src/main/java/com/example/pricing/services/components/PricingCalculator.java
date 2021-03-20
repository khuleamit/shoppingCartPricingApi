package com.example.pricing.services.components;

import com.example.pricing.entities.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.HashMap;

@Component
@RequestScope
public class PricingCalculator {

    //Item Map <itemId, Item>
    HashMap<String, Item> itemMap = new HashMap<>();

    //Product Map <productId, price>
    HashMap<String, BigDecimal> productMap = new HashMap<>();

    //Evaluate promotions and calculate price of order entry
    public Response getPricing(@Valid Request request) {

        String errorMessage;
        errorMessage = loadMaps(request.getOrder().getItems());
        if (!errorMessage.equals(Constants.EMPTY_STRING)) {
            Response response = new Response();
            response.setApiStatus(Constants.ERROR);
            response.setApiMessage(errorMessage);
            return response;
        }

        String eligiblePromotion = getEligiblePromotion(request);
        return calculatePricing(request, eligiblePromotion);

    }

    //Calculate pricing based in supplied promotion
    private Response calculatePricing(Request request, String eligiblePromotion) {

        Response response = new Response();
        response.setOrder(request.getOrder());
        response.appliedPromotion(eligiblePromotion);
        List<Item> orderItemList = response.getOrder().getItems();

        //get discount eligible productId if any
        String discountEligibleProductId;
        if (eligiblePromotion.equals(Constants.EMPTY_STRING)) {
            discountEligibleProductId = Constants.EMPTY_STRING;
        } else {
            discountEligibleProductId = getDiscountEligibleProductId(request.getPromotions(), eligiblePromotion);
        }

        BigDecimal totalOrderPrice = BigDecimal.ZERO;
        int discountEligibleQuantity = 0;

        //Calculate pricing for each item
        for (Item item: orderItemList) {

            int buyingQuantity = item.getQuantity();
            String buyingProductId = item.getProduct().getId();
            BigDecimal buyingProductPrice = item.getProduct().getPrice();
            BigDecimal itemPrice = buyingProductPrice.multiply(BigDecimal.valueOf(buyingQuantity));

            //Check for eligible discounts
            if(buyingProductId.equals(discountEligibleProductId)) {

                BigDecimal eligibleDiscount = calculateEligibleDiscount(request,
                        buyingQuantity,
                        buyingProductPrice,
                        eligiblePromotion,
                        discountEligibleProductId);
                itemPrice = itemPrice.subtract(eligibleDiscount);
            }

            item.setItemPrice(itemPrice);
            totalOrderPrice = totalOrderPrice.add(itemPrice);
        }

        response.setOrderPrice(totalOrderPrice);
        response.setApiStatus(Constants.SUCCESS);

        if (!discountEligibleProductId.equals(Constants.EMPTY_STRING)) {
            StringBuilder msg = new StringBuilder();
            msg.append("Discount is applied to product ");
            msg.append(discountEligibleProductId );
            response.setApiMessage(msg.toString());
        } else {
            response.setApiMessage("Order did not qualify for any promotions");
        }

        return response;
    }

    //calculate total eligible discount
    private BigDecimal calculateEligibleDiscount(Request request,
                                                 int buyingQuantity,
                                                 BigDecimal buyingProductPrice,
                                                 String eligiblePromotion,
                                                 String discountEligibleProductId) {

        List<Item> orderItemList = request.getOrder().getItems();
        List<String> buyOGO50List = request.getPromotions().getBuyOneGet50OffSecond();
        List<String> buy3G4FREEList = request.getPromotions().getBuyThreeGetOneFree();
        int discountEligibleQuantity = 0;

        if (eligiblePromotion.equals(Constants.PROMOTION_BOGO50)) {

            discountEligibleQuantity = getDiscountEligibleCountBOGO50(orderItemList,
                    discountEligibleProductId, buyingQuantity, buyOGO50List);
            logDiscountDetails(discountEligibleProductId, discountEligibleQuantity);

            return buyingProductPrice
                    .multiply(BigDecimal.valueOf(discountEligibleQuantity))
                    .divide(BigDecimal.valueOf(2), RoundingMode.DOWN);

        } else if (eligiblePromotion.equals(Constants.PROMOTION_B3G1FREE)) {

            discountEligibleQuantity = getDiscountEligibleCountB3G1FREE(orderItemList,
                    discountEligibleProductId, buyingQuantity, buy3G4FREEList);
            logDiscountDetails(discountEligibleProductId, discountEligibleQuantity);

            return buyingProductPrice
                    .multiply(BigDecimal.valueOf(discountEligibleQuantity));

        }

        return BigDecimal.ZERO;

    }

    //get the count of items to be discounted
    private int getDiscountEligibleCountBOGO50(List<Item> itemList,
                                               String discountedProductId,
                                               int buyingQuantity,
                                               List<String> buyOneGet50OffSecond) {

        String pairedProductIdInPromotion = Constants.EMPTY_STRING;
        for (String productId: buyOneGet50OffSecond) {
            if(!productId.equals(discountedProductId)) {
                pairedProductIdInPromotion = productId;
            }
        }

        int discountEligibleCount = 0;
        for (Item item: itemList) {
            if(item.getProduct().getId().equals(pairedProductIdInPromotion)) {
                discountEligibleCount = item.getQuantity();
            }
        }

        return Math.min(buyingQuantity, discountEligibleCount);
    }

    //get the smallest quantity of items to be discounted - B3G1FREE
    private int getDiscountEligibleCountB3G1FREE(List<Item> itemList,
                                                String discountedProductId,
                                                int buyingQuantity,
                                                List<String> buyThreeGetFourthFree) {

        int discountEligibleCountSmallest = 0;

        for (String promotionalProductId: buyThreeGetFourthFree) {
            if(!promotionalProductId.equals(discountedProductId)) {
                for (Item item: itemList) {
                    if(item.getProduct().getId().equals(promotionalProductId)) {
                        int discountEligibleCount = item.getQuantity();
                        if ((discountEligibleCountSmallest == 0 ||
                                discountEligibleCountSmallest > discountEligibleCount) &&
                                discountEligibleCount != 0) {
                            discountEligibleCountSmallest = discountEligibleCount;
                        }
                    }
                }
            }
        }

        return Math.min(buyingQuantity, discountEligibleCountSmallest);

    }

    //Evaluate all promotions and return most suitable one
    private String getEligiblePromotion(@Valid Request request) {

        Promotions promotions = request.getPromotions();
        if(promotions == null) {
            return Constants.EMPTY_STRING;
        }

        List<String> buyOneGet50OffSecond = promotions.getBuyOneGet50OffSecond();
        List<String> buyThreeGetFourthFree = promotions.getBuyThreeGetOneFree();
        BigDecimal buyOGO50Discount = BigDecimal.ZERO;
        BigDecimal buy3G1FREEDiscount = BigDecimal.ZERO;


        if (buyOneGet50OffSecond != null && buyOneGet50OffSecond.size() == 2) {
            String discountEligibleProductId = getDiscountEligibleProductId(request.getPromotions(),
                    Constants.PROMOTION_BOGO50);
            if (!discountEligibleProductId.equals(Constants.EMPTY_STRING)) {
                buyOGO50Discount = productMap.get(discountEligibleProductId).divide(BigDecimal.valueOf(2.0),
                        RoundingMode.DOWN);
            }
        }

        if (buyThreeGetFourthFree != null && buyThreeGetFourthFree.size() > 3) {
            String discountEligibleProductId = getDiscountEligibleProductId(request.getPromotions(),
                    Constants.PROMOTION_B3G1FREE);
            if (!discountEligibleProductId.equals(Constants.EMPTY_STRING)) {
                buy3G1FREEDiscount = productMap.get(discountEligibleProductId);
            }
        }

        if (buyOGO50Discount.compareTo(buy3G1FREEDiscount) > 0) {
            return Constants.PROMOTION_BOGO50;
        } else if(buy3G1FREEDiscount.compareTo(BigDecimal.ZERO) > 0) {
            return Constants.PROMOTION_B3G1FREE;
        } else {
            return Constants.EMPTY_STRING;
        }

    }

    //Fetch itemId eligible for promotion specific discount
    private String getDiscountEligibleProductId(Promotions promotions, String eligiblePromotion) {

        List<String> promotionalProducts;
        if (eligiblePromotion.equals(Constants.PROMOTION_BOGO50)) {
            promotionalProducts = promotions.getBuyOneGet50OffSecond();
        } else {
            promotionalProducts = promotions.getBuyThreeGetOneFree();
        }


        int itemsMatchedWithPromotionalList = 0;
        String cheapestEligibleProductID = Constants.EMPTY_STRING;
        BigDecimal cheapestPrice = BigDecimal.ZERO;

        for (String productId: productMap.keySet()) {

            if(promotionalProducts.contains(productId)) {

                if (itemsMatchedWithPromotionalList == 0) {
                    cheapestPrice = productMap.get(productId);
                    cheapestEligibleProductID = productId;
                } else if(cheapestPrice.compareTo(productMap.get(productId)) > 0) {
                    cheapestPrice = productMap.get(productId);
                    cheapestEligibleProductID = productId;
                }

                itemsMatchedWithPromotionalList += 1;

            }
        }

        if(eligiblePromotion.equals(Constants.PROMOTION_BOGO50) &&
                itemsMatchedWithPromotionalList == 2) {
            return cheapestEligibleProductID;
        }

        if(eligiblePromotion.equals(Constants.PROMOTION_B3G1FREE) &&
                itemsMatchedWithPromotionalList > 2) {
            return cheapestEligibleProductID;
        }

        return Constants.EMPTY_STRING;
    }

    //Load Hash Maps
    private String loadMaps(List<Item> itemList) {

        for (Item item: itemList) {

            //Load Item Map
            if(itemMap.containsKey(item.getId())) {
                return "Order can not have same item in duplicate, itemId = " + item.getId();
            }
            itemMap.put(item.getId(), item);

            //Load Product Map <productId, price>
            Product product = item.getProduct();
            if(productMap.containsKey(product.getId())) {
                return "Order can not have same product in duplicate, productId = " + product.getId();
            }
            productMap.put(product.getId(), product.getPrice());

        }

        return Constants.EMPTY_STRING;

    }

    //Log discount for reference only
    private void logDiscountDetails(String discountEligibleProductId, int discountEligibleQuantity) {
        if (discountEligibleQuantity > 0) {
            StringBuilder msg = new StringBuilder();
            msg.append("Pricing Service : Discount is applied to product ");
            msg.append(discountEligibleProductId );
            msg.append(" for ");
            msg.append(discountEligibleQuantity);
            msg.append(" quantities");
            System.out.println(msg);
        }
    }


}
