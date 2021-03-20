package com.example.pricing.entities;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Promotions
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-19T04:16:30.201Z")




public class Promotions   {
    @JsonProperty("buyOneGet50OffSecond")
    @Valid
    private List<String> buyOneGet50OffSecond = null;

    @JsonProperty("buyThreeGetOneFree")
    @Valid
    private List<String> buyThreeGetOneFree = null;

    public Promotions buyOneGet50OffSecond(List<String> buyOneGet50OffSecond) {
        this.buyOneGet50OffSecond = buyOneGet50OffSecond;
        return this;
    }

    public Promotions addBuyOneGet50OffSecondItem(String buyOneGet50OffSecondItem) {
        if (this.buyOneGet50OffSecond == null) {
            this.buyOneGet50OffSecond = new ArrayList<String>();
        }
        this.buyOneGet50OffSecond.add(buyOneGet50OffSecondItem);
        return this;
    }

    /**
     * Get buyOneGet50OffSecond
     * @return buyOneGet50OffSecond
     **/
    @ApiModelProperty(value = "")


    public List<String> getBuyOneGet50OffSecond() {
        return buyOneGet50OffSecond;
    }

    public void setBuyOneGet50OffSecond(List<String> buyOneGet50OffSecond) {
        this.buyOneGet50OffSecond = buyOneGet50OffSecond;
    }

    public Promotions buyThreeGetOneFree(List<String> buyThreeGetOneFree) {
        this.buyThreeGetOneFree = buyThreeGetOneFree;
        return this;
    }

    public Promotions addBuyThreeGetOneFreeItem(String buyThreeGetOneFreeItem) {
        if (this.buyThreeGetOneFree == null) {
            this.buyThreeGetOneFree = new ArrayList<String>();
        }
        this.buyThreeGetOneFree.add(buyThreeGetOneFreeItem);
        return this;
    }

    /**
     * Get buyThreeGetOneFree
     * @return buyThreeGetOneFree
     **/
    @ApiModelProperty(value = "")


    public List<String> getBuyThreeGetOneFree() {
        return buyThreeGetOneFree;
    }

    public void setBuyThreeGetOneFree(List<String> buyThreeGetOneFree) {
        this.buyThreeGetOneFree = buyThreeGetOneFree;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Promotions promotions = (Promotions) o;
        return Objects.equals(this.buyOneGet50OffSecond, promotions.buyOneGet50OffSecond) &&
                Objects.equals(this.buyThreeGetOneFree, promotions.buyThreeGetOneFree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyOneGet50OffSecond, buyThreeGetOneFree);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Promotions {\n");

        sb.append("    buyOneGet50OffSecond: ").append(toIndentedString(buyOneGet50OffSecond)).append("\n");
        sb.append("    buyThreeGetOneFree: ").append(toIndentedString(buyThreeGetOneFree)).append("\n");
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


