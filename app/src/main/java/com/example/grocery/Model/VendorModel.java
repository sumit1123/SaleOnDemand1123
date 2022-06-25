package com.example.grocery.Model;

public class VendorModel
{
    private String name;
    private String contact_number;

    public int getVendor_call_id() {
        return vendor_call_id;
    }

    public void setVendor_call_id(int vendor_call_id) {
        this.vendor_call_id = vendor_call_id;
    }

    private int vendor_call_id;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }


}
