package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 9/29/2017.
 */

public class LimitedModel {

    /**
     * category_id : 2
     * category_name : Transportation
     * category_icon : category-icon/VZA2yEo7130Rs2xX4cNczkJkLmKvW4yMWHRX2FQF.png
     * category_image : category-images/BYa5XrUHFR8bIOv96N2EjQya4wFJh3jz9Nc89LNs.png
     * category_description : Transportaion
     * is_deleted : 1
     * created_at : 2017-09-16 12:43:16
     * updated_at : 2017-09-25 13:11:14
     */

    private int category_id;
    private String category_name;
    private String category_icon;
    private String category_image;
    private String category_description;
    private int is_deleted;
    private String created_at;
    private String updated_at;

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_icon() {
        return category_icon;
    }

    public void setCategory_icon(String category_icon) {
        this.category_icon = category_icon;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
