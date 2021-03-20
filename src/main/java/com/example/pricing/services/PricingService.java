package com.example.pricing.services;

import com.example.pricing.entities.Request;
import com.example.pricing.entities.Response;
import com.example.pricing.services.components.Constants;
import com.example.pricing.services.components.PricingCalculator;
import com.example.pricing.services.components.PricingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class PricingService {

    @Autowired
    PricingValidator pricingValidator;

    @Autowired
    PricingCalculator pricingCalculator;

    public Response getPricing(@Valid Request request) {

        System.out.println("Pricing Service : " + request.toString());

        String validationError = pricingValidator.validatePricingRequest(request);
        if(!validationError.equals(Constants.EMPTY_STRING)) {
            Response response = new Response();
            response.setApiStatus(Constants.ERROR);
            response.setOrder(request.getOrder());
            response.setApiMessage(validationError);
            return response;
        }

        Response response = pricingCalculator.getPricing(request);
        System.out.println("Pricing Service : " + response.toString());
        return response;
    }

}
