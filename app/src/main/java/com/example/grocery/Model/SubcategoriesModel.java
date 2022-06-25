package com.example.grocery.Model;

/**
 * Created by Administrator on 9/15/2017.
 */

public class SubcategoriesModel {


    /**
     * sub_category_id : 7
     * category_id : 5
     * sub_category_name : fsd
     * sub_category_icon : sub_category_icon/7AUL5glkIMYprY7hidWCmdo8a7qZkmB17StuzlyB.png
     * sub_category_image : sub_category_images/TdADydZIybdyiriWvyjC6zuYCgBDihvm7WIbbR7y.png
     * sub_category_description : evfwr
     * is_deleted : 0
     * parent_deleted : 0
     * created_at : 2017-10-09 16:08:09
     * updated_at : 2017-10-10 07:05:36
     */

    private int sub_category_id;
    private int category_id;
    private String sub_category_name;
    private String sub_category_icon;
    private String sub_category_image;
    private String sub_category_description;
    private int is_deleted;
    private int parent_deleted;
    private String created_at;
    private String updated_at;

    public int getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(int sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getSub_category_name() {
        return sub_category_name;
    }

    public void setSub_category_name(String sub_category_name) {
        this.sub_category_name = sub_category_name;
    }

    public String getSub_category_icon() {
        return sub_category_icon;
    }

    public void setSub_category_icon(String sub_category_icon) {
        this.sub_category_icon = sub_category_icon;
    }

    public String getSub_category_image() {
        return sub_category_image;
    }

    public void setSub_category_image(String sub_category_image) {
        this.sub_category_image = sub_category_image;
    }

    public String getSub_category_description() {
        return sub_category_description;
    }

    public void setSub_category_description(String sub_category_description) {
        this.sub_category_description = sub_category_description;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public int getParent_deleted() {
        return parent_deleted;
    }

    public void setParent_deleted(int parent_deleted) {
        this.parent_deleted = parent_deleted;
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
