package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 9/30/2017.
 */

public class OnlineChatModel {


    /**
     * ticket_id : 1
     * complaint_id : 12
     * complaint_type_id : 1
     * user_id : 3
     * is_solve : 0
     * message : what should i do
     * created_at : 2017-09-29 12:53:48
     * updated_at : 2017-09-29 12:53:48
     * user : {"id":3,"name":"abhi","email":"abhi@jmtit.com","contact_no":8055478784,"created_at":"2017-09-26 08:15:31","updated_at":"2017-09-26 08:15:31"}
     */

    private int ticket_id;
    private int complaint_id;
    private int complaint_type_id;
    private int user_id;
    private int is_solve;
    private String message;
    private String created_at;
    private String updated_at;
    private UserBean user;

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(int complaint_id) {
        this.complaint_id = complaint_id;
    }

    public int getComplaint_type_id() {
        return complaint_type_id;
    }

    public void setComplaint_type_id(int complaint_type_id) {
        this.complaint_type_id = complaint_type_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getIs_solve() {
        return is_solve;
    }

    public void setIs_solve(int is_solve) {
        this.is_solve = is_solve;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 3
         * name : abhi
         * email : abhi@jmtit.com
         * contact_no : 8055478784
         * created_at : 2017-09-26 08:15:31
         * updated_at : 2017-09-26 08:15:31
         */

        private int id;
        private String name;
        private String email;
        private long contact_no;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public long getContact_no() {
            return contact_no;
        }

        public void setContact_no(long contact_no) {
            this.contact_no = contact_no;
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
}
