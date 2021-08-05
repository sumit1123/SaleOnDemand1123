package com.example.grocery.Model;

/**
 * Created by Administrator on 9/16/2017.
 */

public class OptionalImagesModel {

    /**
     * product_image_id : 1
     * product_image : product-image/wByEHrMpVMzuDX6YKl0MoDB9Jbz3gvdwv9hPDQ7s.png
     * product_id : 1
     * parent_variant_id : 1
     * child_variant_id : 1
     * created_at : null
     * updated_at : null
     */

    private int product_image_id;
    private String product_image;
    private int product_id;
    private int parent_variant_id;
    private int child_variant_id;
    private Object created_at;
    private Object updated_at;

    public int getProduct_image_id() {
        return product_image_id;
    }

    public void setProduct_image_id(int product_image_id) {
        this.product_image_id = product_image_id;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getParent_variant_id() {
        return parent_variant_id;
    }

    public void setParent_variant_id(int parent_variant_id) {
        this.parent_variant_id = parent_variant_id;
    }

    public int getChild_variant_id() {
        return child_variant_id;
    }

    public void setChild_variant_id(int child_variant_id) {
        this.child_variant_id = child_variant_id;
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
