package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 9/28/2017.
 */

public class ComplaintModel {

    /**
     * complaint_id : 1
     * user_id : 2
     * complaint_type_id : 2
     * complaint_status_id : 1
     * description : Laptop hanging
     * last_comment : null
     * created_at : 2018-01-08 11:15:12
     * updated_at : 2018-01-08 11:15:12
     * user : {"id":2,"status_id":1,"business_name":null,"seller_id":null,"address_id":1,"name":"Mohd Afzal","email":"mohd.afzal16@gmail.com","contact_number":"8149180155","optional_contact_number":null,"DOB":null,"city_id":1,"state_id":1,"country_id":1,"pincode":"400933","gst_number":null,"wallet":0,"token":null,"created_at":"2018-01-07 12:14:52","updated_at":"2018-01-11 11:25:11"}
     * complaint_type : {"complaint_type_id":2,"complaint_type_translation_id":2,"is_active":1,"created_at":null,"updated_at":null}
     * complaint_status : {"complaint_status_id":1,"complaint_status_translation_id":1,"complaint_status_color":"d64040","is_active":1,"created_at":null,"updated_at":null,"default_complaint_status_translation":{"complaint_status_translation_id":1,"complaint_status_id":1,"language_id":1,"complaint_status_name":"Open","created_at":null,"updated_at":null},"complaint_status_translation":{"complaint_status_translation_id":1,"complaint_status_id":1,"language_id":1,"complaint_status_name":"Open","created_at":null,"updated_at":null}}
     */

    private int complaint_id;
    private int user_id;
    private int complaint_type_id;
    private int complaint_status_id;
    private String description;
    private Object last_comment;
    private String created_at;
    private String updated_at;
    private UserBean user;
    private ComplaintTypeBean complaint_type;
    private ComplaintStatusBean complaint_status;

    public int getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(int complaint_id) {
        this.complaint_id = complaint_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getComplaint_type_id() {
        return complaint_type_id;
    }

    public void setComplaint_type_id(int complaint_type_id) {
        this.complaint_type_id = complaint_type_id;
    }

    public int getComplaint_status_id() {
        return complaint_status_id;
    }

    public void setComplaint_status_id(int complaint_status_id) {
        this.complaint_status_id = complaint_status_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getLast_comment() {
        return last_comment;
    }

    public void setLast_comment(Object last_comment) {
        this.last_comment = last_comment;
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

    public ComplaintTypeBean getComplaint_type() {
        return complaint_type;
    }

    public void setComplaint_type(ComplaintTypeBean complaint_type) {
        this.complaint_type = complaint_type;
    }

    public ComplaintStatusBean getComplaint_status() {
        return complaint_status;
    }

    public void setComplaint_status(ComplaintStatusBean complaint_status) {
        this.complaint_status = complaint_status;
    }

    public static class UserBean {
        /**
         * id : 2
         * status_id : 1
         * business_name : null
         * seller_id : null
         * address_id : 1
         * name : Mohd Afzal
         * email : mohd.afzal16@gmail.com
         * contact_number : 8149180155
         * optional_contact_number : null
         * DOB : null
         * city_id : 1
         * state_id : 1
         * country_id : 1
         * pincode : 400933
         * gst_number : null
         * wallet : 0
         * token : null
         * created_at : 2018-01-07 12:14:52
         * updated_at : 2018-01-11 11:25:11
         */

        private int id;
        private int status_id;
        private Object business_name;
        private Object seller_id;
        private int address_id;
        private String name;
        private String email;
        private String contact_number;
        private Object optional_contact_number;
        private Object DOB;
        private int city_id;
        private int state_id;
        private int country_id;
        private String pincode;
        private Object gst_number;
        private int wallet;
        private Object token;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus_id() {
            return status_id;
        }

        public void setStatus_id(int status_id) {
            this.status_id = status_id;
        }

        public Object getBusiness_name() {
            return business_name;
        }

        public void setBusiness_name(Object business_name) {
            this.business_name = business_name;
        }

        public Object getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(Object seller_id) {
            this.seller_id = seller_id;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
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

        public String getContact_number() {
            return contact_number;
        }

        public void setContact_number(String contact_number) {
            this.contact_number = contact_number;
        }

        public Object getOptional_contact_number() {
            return optional_contact_number;
        }

        public void setOptional_contact_number(Object optional_contact_number) {
            this.optional_contact_number = optional_contact_number;
        }

        public Object getDOB() {
            return DOB;
        }

        public void setDOB(Object DOB) {
            this.DOB = DOB;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getState_id() {
            return state_id;
        }

        public void setState_id(int state_id) {
            this.state_id = state_id;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public Object getGst_number() {
            return gst_number;
        }

        public void setGst_number(Object gst_number) {
            this.gst_number = gst_number;
        }

        public int getWallet() {
            return wallet;
        }

        public void setWallet(int wallet) {
            this.wallet = wallet;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
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

    public static class ComplaintTypeBean {
        /**
         * complaint_type_id : 2
         * complaint_type_translation_id : 2
         * is_active : 1
         * created_at : null
         * updated_at : null
         */

        private int complaint_type_id;
        private int complaint_type_translation_id;
        private int is_active;
        private Object created_at;
        private Object updated_at;

        public int getComplaint_type_id() {
            return complaint_type_id;
        }

        public void setComplaint_type_id(int complaint_type_id) {
            this.complaint_type_id = complaint_type_id;
        }

        public int getComplaint_type_translation_id() {
            return complaint_type_translation_id;
        }

        public void setComplaint_type_translation_id(int complaint_type_translation_id) {
            this.complaint_type_translation_id = complaint_type_translation_id;
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

    public static class ComplaintStatusBean {
        /**
         * complaint_status_id : 1
         * complaint_status_translation_id : 1
         * complaint_status_color : d64040
         * is_active : 1
         * created_at : null
         * updated_at : null
         * default_complaint_status_translation : {"complaint_status_translation_id":1,"complaint_status_id":1,"language_id":1,"complaint_status_name":"Open","created_at":null,"updated_at":null}
         * complaint_status_translation : {"complaint_status_translation_id":1,"complaint_status_id":1,"language_id":1,"complaint_status_name":"Open","created_at":null,"updated_at":null}
         */

        private int complaint_status_id;
        private int complaint_status_translation_id;
        private String complaint_status_color;
        private int is_active;
        private Object created_at;
        private Object updated_at;
        private DefaultComplaintStatusTranslationBean default_complaint_status_translation;
        private ComplaintStatusTranslationBean complaint_status_translation;

        public int getComplaint_status_id() {
            return complaint_status_id;
        }

        public void setComplaint_status_id(int complaint_status_id) {
            this.complaint_status_id = complaint_status_id;
        }

        public int getComplaint_status_translation_id() {
            return complaint_status_translation_id;
        }

        public void setComplaint_status_translation_id(int complaint_status_translation_id) {
            this.complaint_status_translation_id = complaint_status_translation_id;
        }

        public String getComplaint_status_color() {
            return complaint_status_color;
        }

        public void setComplaint_status_color(String complaint_status_color) {
            this.complaint_status_color = complaint_status_color;
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

        public DefaultComplaintStatusTranslationBean getDefault_complaint_status_translation() {
            return default_complaint_status_translation;
        }

        public void setDefault_complaint_status_translation(DefaultComplaintStatusTranslationBean default_complaint_status_translation) {
            this.default_complaint_status_translation = default_complaint_status_translation;
        }

        public ComplaintStatusTranslationBean getComplaint_status_translation() {
            return complaint_status_translation;
        }

        public void setComplaint_status_translation(ComplaintStatusTranslationBean complaint_status_translation) {
            this.complaint_status_translation = complaint_status_translation;
        }

        public static class DefaultComplaintStatusTranslationBean {
            /**
             * complaint_status_translation_id : 1
             * complaint_status_id : 1
             * language_id : 1
             * complaint_status_name : Open
             * created_at : null
             * updated_at : null
             */

            private int complaint_status_translation_id;
            private int complaint_status_id;
            private int language_id;
            private String complaint_status_name;
            private Object created_at;
            private Object updated_at;

            public int getComplaint_status_translation_id() {
                return complaint_status_translation_id;
            }

            public void setComplaint_status_translation_id(int complaint_status_translation_id) {
                this.complaint_status_translation_id = complaint_status_translation_id;
            }

            public int getComplaint_status_id() {
                return complaint_status_id;
            }

            public void setComplaint_status_id(int complaint_status_id) {
                this.complaint_status_id = complaint_status_id;
            }

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public String getComplaint_status_name() {
                return complaint_status_name;
            }

            public void setComplaint_status_name(String complaint_status_name) {
                this.complaint_status_name = complaint_status_name;
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

        public static class ComplaintStatusTranslationBean {
            /**
             * complaint_status_translation_id : 1
             * complaint_status_id : 1
             * language_id : 1
             * complaint_status_name : Open
             * created_at : null
             * updated_at : null
             */

            private int complaint_status_translation_id;
            private int complaint_status_id;
            private int language_id;
            private String complaint_status_name;
            private Object created_at;
            private Object updated_at;

            public int getComplaint_status_translation_id() {
                return complaint_status_translation_id;
            }

            public void setComplaint_status_translation_id(int complaint_status_translation_id) {
                this.complaint_status_translation_id = complaint_status_translation_id;
            }

            public int getComplaint_status_id() {
                return complaint_status_id;
            }

            public void setComplaint_status_id(int complaint_status_id) {
                this.complaint_status_id = complaint_status_id;
            }

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public String getComplaint_status_name() {
                return complaint_status_name;
            }

            public void setComplaint_status_name(String complaint_status_name) {
                this.complaint_status_name = complaint_status_name;
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
    }
}
