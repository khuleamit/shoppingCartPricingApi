package com.example.pricing.servies;

import com.example.pricing.entities.*;
import com.example.pricing.services.PricingService;
import com.example.pricing.services.components.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
public class PricingServiceTests<region> {

    @Autowired
    PricingService pricingService;

    @Test
    void getPricingBOGO50Test() {

        //Arrange
        Request request = new Request();
        request.setCorrelationId(UUID.randomUUID());
        request.setPromotions(getPromotions());
        request.setOrder(getOrderBOGO50());

        //Act
        Response response = this.pricingService.getPricing(request);

        //Asset
        Assert.isTrue(response != null,
                "Response can not be null");
        Assert.isTrue(response.getApiStatus() == Constants.SUCCESS,
                "Api status must be SUCCESS");
        Assert.isTrue(response.getOrderPrice().compareTo(BigDecimal.valueOf(23.50)) == 0,
                "Order total must be 23.50");
    }


    @Test
    void getPricingB3G1FREETest() {

        //Arrange
        Request request = new Request();
        request.setCorrelationId(UUID.randomUUID());
        request.setPromotions(getPromotions());
        request.setOrder(getOrderB3G1FREE());

        //Act
        Response response = this.pricingService.getPricing(request);

        //Asset
        Assert.isTrue(response != null,
                "Response can not be null");
        Assert.isTrue(response.getApiStatus() == Constants.SUCCESS,
                "Api status must be SUCCESS");
        Assert.isTrue(response.getOrderPrice().compareTo(BigDecimal.valueOf(17.00)) == 0,
                "Order total must be 17.00");
    }


    //Supportive methods

    private Order getOrderBOGO50() {
        Order order = new Order();
        order.setId("Order_1");

        List<Item> itemList = new ArrayList<Item>();

        itemList.add(getItem("Item_1",1, "Product_A", BigDecimal.valueOf(10.00)));
        itemList.add(getItem("Item_2",2, "Product_B", BigDecimal.valueOf(9.00)));

        order.setItems(itemList);
        return order;
    }

    private Order getOrderB3G1FREE() {
        Order order = new Order();
        order.setId("Order_1");

        List<Item> itemList = new ArrayList<Item>();

        itemList.add(getItem("Item_1",1, "Product_A", BigDecimal.valueOf(10.00)));
        itemList.add(getItem("Item_2",1, "Product_B", BigDecimal.valueOf(5.00)));
        itemList.add(getItem("Item_3",1, "Product_C", BigDecimal.valueOf(4.00)));
        itemList.add(getItem("Item_4",1, "Product_F", BigDecimal.valueOf(2.00)));

        order.setItems(itemList);
        return order;
    }

    private Item getItem(String itemId,
                                   int quantity,
                                   String productId,
                                   BigDecimal productPrice) {
        //set product info
        Product product = new Product();
        product.setId(productId);
        product.setPrice(productPrice);

        //set item info
        Item item = new Item();
        item.setId(itemId);
        item.setQuantity(quantity);
        item.setProduct(product);

        return item;
    }

    private Promotions getPromotions() {
        Promotions promotion = new Promotions();
        promotion.setBuyOneGet50OffSecond(Arrays.asList(
                "Product_A",
                "Product_B"
        ));
        promotion.setBuyThreeGetOneFree(Arrays.asList(
                "Product_A",
                "Product_B",
                "Product_C",
                "Product_D",
                "Product_E"
        ));
        return promotion;
    }
}
