package com.example.pricing.entities;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Item
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-19T04:16:30.201Z")




public class Item   {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("product")
    private Product product = null;

    @JsonProperty("quantity")
    private Integer quantity = null;

    @JsonProperty("itemPrice")
    private BigDecimal itemPrice = null;

    public Item id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     * @return id
     **/
    @ApiModelProperty(example = "ItemId_1", required = true, value = "")
    @NotNull


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item product(Product product) {
        this.product = product;
        return this;
    }

    /**
     * Get product
     * @return product
     **/
    @ApiModelProperty(value = "")

    @Valid

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Item quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * Get quantity
     * @return quantity
     **/
    @ApiModelProperty(example = "2", required = true, value = "")
    @NotNull


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item itemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    /**
     * Get itemPrice
     * @return itemPrice
     **/
    @ApiModelProperty(example = "20.0", value = "")

    @Valid

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(this.id, item.id) &&
                Objects.equals(this.product, item.product) &&
                Objects.equals(this.quantity, item.quantity) &&
                Objects.equals(this.itemPrice, item.itemPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, quantity, itemPrice);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Item {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    product: ").append(toIndentedString(product)).append("\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("    itemPrice: ").append(toIndentedString(itemPrice)).append("\n");
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


