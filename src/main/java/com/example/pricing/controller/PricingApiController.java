package com.example.pricing.controller;

import com.example.pricing.entities.Request;
import com.example.pricing.entities.Response;
import com.example.pricing.services.PricingService;
import com.example.pricing.services.components.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PricingApiController implements PricingApi {

    @Autowired
    PricingService pricingService;

    @Override
    public ResponseEntity<Response> pricing(@Valid Request request) {

        try {

            Response response = pricingService.getPricing(request);
            return ResponseEntity.ok(response);

        } catch (Exception ex) {

            Response response = new Response();
            response.setApiStatus(Constants.ERROR);
            response.setApiMessage("Exception while processing request : " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }
}
