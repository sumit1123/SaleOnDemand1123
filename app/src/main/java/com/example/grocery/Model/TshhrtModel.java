package com.example.grocery.Model;

/**
 * Created by Administrator on 9/14/2017.
 */

public class TshhrtModel {

    /**
     * category_id : 1
     * category_name : catagory 1
     * category_icon : category-icon/2pbgqYF06zW234Xm7T1yGTp7AJ8JR4baA2uWsGDh.png
     * category_image : category-images/dM25wRo4YSeZ56LzdDjcCT94krpINqnp9GsMd6fF.png
     * category_description : Hiiiiiiii
     * created_at : 2017-09-15 15:59:43
     * updated_at : 2017-09-15 15:59:43
     */

    private int category_id;
    private String category_name;
    private String category_icon;
    private String category_image;
    private String category_description;
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
