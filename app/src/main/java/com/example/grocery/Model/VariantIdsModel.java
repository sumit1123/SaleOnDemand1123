package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 11/29/2017.
 */

public class VariantIdsModel {

    /**
     * product_variant_option_id : 1
     * product_variant_id : 1
     * product_id : 3
     * variant_id : 2
     * option_id : 2
     * product_variant_option_name : Red
     * created_at : null
     * updated_at : null
     */

    private int product_variant_option_id;
    private int product_variant_id;
    private int product_id;
    private int variant_id;
    private int option_id;
    private String product_variant_option_name;
    private Object created_at;
    private Object updated_at;

    public int getProduct_variant_option_id() {
        return product_variant_option_id;
    }

    public void setProduct_variant_option_id(int product_variant_option_id) {
        this.product_variant_option_id = product_variant_option_id;
    }

    public int getProduct_variant_id() {
        return product_variant_id;
    }

    public void setProduct_variant_id(int product_variant_id) {
        this.product_variant_id = product_variant_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getVariant_id() {
        return variant_id;
    }

    public void setVariant_id(int variant_id) {
        this.variant_id = variant_id;
    }

    public int getOption_id() {
        return option_id;
    }

    public void setOption_id(int option_id) {
        this.option_id = option_id;
    }

    public String getProduct_variant_option_name() {
        return product_variant_option_name;
    }

    public void setProduct_variant_option_name(String product_variant_option_name) {
        this.product_variant_option_name = product_variant_option_name;
    }

    public Object getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Object created_at) {
        this.created_at = created_at;
    }

    public Object getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Object updated_at) {
        this.updated_at = updated_at;
    }
}
