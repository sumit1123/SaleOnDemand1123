package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 11/14/2017.
 */

public class Colormodel {

    /**
     * color_id : 2
     * color_name : Black
     * created_at : null
     * updated_at : null
     */

    private int color_id;
    private String color_name;
    private Object created_at;
    private Object updated_at;

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
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
