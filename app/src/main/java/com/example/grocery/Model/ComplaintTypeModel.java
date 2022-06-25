package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 9/29/2017.
 */

public class ComplaintTypeModel {

    /**
     * complaint_type_id : 2
     * complaint_type : Body Damage
     * is_deleted : 0
     * created_at : 2017-09-28 11:25:14
     * updated_at : 2017-10-10 07:42:29
     */

    private int complaint_type_id;
    private String complaint_type;
    private int is_deleted;
    private String created_at;
    private String updated_at;

    public int getComplaint_type_id() {
        return complaint_type_id;
    }

    public void setComplaint_type_id(int complaint_type_id) {
        this.complaint_type_id = complaint_type_id;
    }

    public String getComplaint_type() {
        return complaint_type;
    }

    public void setComplaint_type(String complaint_type) {
        this.complaint_type = complaint_type;
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
