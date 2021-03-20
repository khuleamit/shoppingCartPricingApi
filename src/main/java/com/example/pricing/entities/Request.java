package com.example.pricing.entities;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Request
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-19T04:16:30.201Z")



public class Request   {
    @JsonProperty("correlationId")
    private UUID correlationId = null;

    @JsonProperty("order")
    private Order order = null;

    @JsonProperty("promotions")
    private Promotions promotions = null;

    public Request correlationId(UUID correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    /**
     * Get correlationId
     * @return correlationId
     **/
    @ApiModelProperty(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", required = true, value = "")
    @NotNull

    @Valid

    public UUID getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(UUID correlationId) {
        this.correlationId = correlationId;
    }

    public Request order(Order order) {
        this.order = order;
        return this;
    }

    /**
     * Get order
     * @return order
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Request promotions(Promotions promotions) {
        this.promotions = promotions;
        return this;
    }

    /**
     * Get promotions
     * @return promotions
     **/
    @ApiModelProperty(value = "")

    @Valid

    public Promotions getPromotions() {
        return promotions;
    }

    public void setPromotions(Promotions promotions) {
        this.promotions = promotions;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Request request = (Request) o;
        return Objects.equals(this.correlationId, request.correlationId) &&
                Objects.equals(this.order, request.order) &&
                Objects.equals(this.promotions, request.promotions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(correlationId, order, promotions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Request {\n");

        sb.append("    correlationId: ").append(toIndentedString(correlationId)).append("\n");
        sb.append("    order: ").append(toIndentedString(order)).append("\n");
        sb.append("    promotions: ").append(toIndentedString(promotions)).append("\n");
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


