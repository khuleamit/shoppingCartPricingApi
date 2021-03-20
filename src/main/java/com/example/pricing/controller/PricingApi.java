package com.example.pricing.controller;

import org.springframework.web.bind.annotation.*;

import com.example.pricing.entities.Request;
import com.example.pricing.entities.Response;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-19T04:16:30.201Z")

@Api(value = "pricing", description = "the pricing API")
@RequestMapping(value = "/pricing/v1")
public interface PricingApi {

    @ApiOperation(value = "calculates total cost of an order", nickname = "pricing", notes = "calculates total cost of an order", response = Response.class, responseContainer = "List", tags={ "pricing", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful", response = Response.class, responseContainer = "List"),
            @ApiResponse(code = 201, message = "item created"),
            @ApiResponse(code = 400, message = "invalid input, object invalid"),
            @ApiResponse(code = 409, message = "an existing item already exists") })
    @RequestMapping(value = "/pricingCalculator",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Response> pricing(@ApiParam(value = "Order details and applicable Promotions"  )  @Valid @RequestBody Request request);

}
