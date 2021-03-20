package com.example.pricing.entities;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Response
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-19T04:16:30.201Z")




public class Response   {
    @JsonProperty("apiStatus")
    private String apiStatus = null;

    @JsonProperty("apiMessage")
    private String apiMessage = null;

    @JsonProperty("correlationId")
    private UUID correlationId = null;

    @JsonProperty("order")
    private Order order = null;

    @JsonProperty("orderPrice")
    private BigDecimal orderPrice = null;

    @JsonProperty("appliedPromotion")
    private String appliedPromotion = null;

    public Response apiStatus(String apiStatus) {
        this.apiStatus = apiStatus;
        return this;
    }

    public Response apiMessage(String apiMessage) {
        this.apiMessage = apiMessage;
        return this;
    }

    /**
     * Get apiStatus
     * @return apiStatus
     **/
    @ApiModelProperty(value = "")


    public String getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(String apiStatus) {
        this.apiStatus = apiStatus;
    }


    /**
     * Get apiMessage
     * @return apiMessage
     **/
    @ApiModelProperty(value = "")


    public String getApiMessage() {
        return apiMessage;
    }

    public void setApiMessage(String apiMessage) {
        this.apiMessage = apiMessage;
    }

    public Response correlationId(UUID correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    /**
     * Get correlationId
     * @return correlationId
     **/
    @ApiModelProperty(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", value = "")

    @Valid

    public UUID getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(UUID correlationId) {
        this.correlationId = correlationId;
    }

    public Response order(Order order) {
        this.order = order;
        return this;
    }

    /**
     * Get order
     * @return order
     **/
    @ApiModelProperty(value = "")

    @Valid

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Response orderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }

    /**
     * Get orderPrice
     * @return orderPrice
     **/
    @ApiModelProperty(example = "20.0", value = "")

    @Valid

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Response appliedPromotion(String appliedPromotion) {
        this.appliedPromotion = appliedPromotion;
        return this;
    }

    /**
     * Get appliedPromotion
     * @return appliedPromotion
     **/
    @ApiModelProperty(example = "buyThreeGetFourthFree", value = "")


    public String getAppliedPromotion() {
        return appliedPromotion;
    }

    public void setAppliedPromotion(String appliedPromotion) {
        this.appliedPromotion = appliedPromotion;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Response response = (Response) o;
        return Objects.equals(this.apiStatus, response.apiStatus) &&
                Objects.equals(this.apiMessage, response.apiMessage) &&
                Objects.equals(this.correlationId, response.correlationId) &&
                Objects.equals(this.order, response.order) &&
                Objects.equals(this.orderPrice, response.orderPrice) &&
                Objects.equals(this.appliedPromotion, response.appliedPromotion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiStatus, apiMessage, correlationId, order, orderPrice, appliedPromotion);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Response {\n");

        sb.append("    apiStatus: ").append(toIndentedString(apiStatus)).append("\n");
        sb.append("    apiMessage: ").append(toIndentedString(apiMessage)).append("\n");
        sb.append("    correlationId: ").append(toIndentedString(correlationId)).append("\n");
        sb.append("    order: ").append(toIndentedString(order)).append("\n");
        sb.append("    orderPrice: ").append(toIndentedString(orderPrice)).append("\n");
        sb.append("    appliedPromotion: ").append(toIndentedString(appliedPromotion)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}


