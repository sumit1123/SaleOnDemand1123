package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 1/13/2018.
 */

public class PincodeModel {

    /**
     * pincode_id : 1
     * pincode :
     * is_active : 1
     * created_at : null
     * updated_at : null
     */

    private int pincode_id;
    private String pincode;
    private int is_active;
    private Object created_at;
    private Object updated_at;

    public int getPincode_id() {
        return pincode_id;
    }

    public void setPincode_id(int pincode_id) {
        this.pincode_id = pincode_id;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
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
