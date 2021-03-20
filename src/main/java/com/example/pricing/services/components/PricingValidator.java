package com.example.pricing.services.components;

import com.example.pricing.entities.Item;
import com.example.pricing.entities.Promotions;
import com.example.pricing.entities.Request;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Component
public class PricingValidator {


    public String validatePricingRequest(@Valid Request request) {

        //Order level validations
        if(request.getOrder() == null) {
            return "Order must be supplied";
        }

        if(request.getOrder().getId() == null) {
            return "OrderId must be supplied";
        }

        //Item level validations
        List<Item> orderItems = request.getOrder().getItems();
        if(orderItems == null) {
            return "Order must contain an item";
        }

        for (Item item: orderItems) {
            if(item.getId() == null) {
                return "Order must contain an itemId";
            }

            if(item.getId().trim().equals(Constants.EMPTY_STRING)) {
                return "ItemId can not be empty";
            }

            if(!Optional.ofNullable(item.getQuantity()).isPresent()) {
                return "Item Id " + item.getId() + " must specify quantity";
            }

            if(!Optional.ofNullable(item.getProduct()).isPresent()) {
                return "Item Id " + item.getId() + " must specify product details";
            }

            if(!Optional.ofNullable(item.getProduct().getId()).isPresent()) {
                return "Item Id " + item.getId() + " must specify product id";
            }

            if(!Optional.ofNullable(item.getProduct().getPrice()).isPresent()) {
                return "Item Id " + item.getId() + " must specify product price";
            }
        }

        //Promotion level validation
        Promotions orderPromotions = request.getPromotions();
        if(orderPromotions != null) {

            if(Optional.ofNullable(orderPromotions.getBuyOneGet50OffSecond()).isPresent()) {
                if(orderPromotions.getBuyOneGet50OffSecond().size() != 2) {
                    return "Promotion BuyOneGet50OffSecond must contain exactly 2 items";
                }
            }

            if(Optional.ofNullable(orderPromotions.getBuyThreeGetOneFree()).isPresent()) {
                if(orderPromotions.getBuyThreeGetOneFree().size() < 3) {
                    return "Promotion BuyOneGet50OffSecond must contain at least 3 items";
                }
            }

        }

        return Constants.EMPTY_STRING;
    }

}
