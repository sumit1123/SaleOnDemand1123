package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 11/8/2017.
 */

public class SizeModel {

    /**
     * size_id : 2
     * size_name : L
     * created_at : null
     * updated_at : null
     */

    private int size_id;
    private String size_name;
    private Object created_at;
    private Object updated_at;

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public String getSize_name() {
        return size_name;
    }

    public void setSize_name(String size_name) {
        this.size_name = size_name;
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
