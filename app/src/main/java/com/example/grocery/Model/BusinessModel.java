package com.example.grocery.Model;

/**
 * Created by JMTIT-Server on 02/07/2019.
 */

public class BusinessModel {

    /**
     * seller_detail_id : 1
     * user_id : 3
     * business_id : 1
     * seller_detail_translation_id : 1
     * seller_image : 1/seller-image/6gRdbBfFmnSU7eOcxoT2KOx1KFK5OdnDLkKUAp2I.png
     * seller_url : abcsd
     * role_id : 4
     * email : null
     * contact_number : 9102946117
     * adhar_card : null
     * pan_card : null
     * food_license : null
     * local_id_registration_certificate : null
     * tax_registration_certificate : null
     * cancelled_check_copy : null
     * seller_website : mm.com
     * terms_condition : 1
     * local_id_registration_number : fgfdg
     * view_count : 0
     * location : Pune
     * is_varified : 0
     * created_at : 2019-07-02 11:15:39
     * updated_at : 2019-07-02 11:15:40
     * seller_detail_translation : {"seller_detail_translation_id":1,"language_id":1,"seller_detail_id":1,"business_id":1,"seller_name":"zomato","address":"dsfsdf","created_at":"2019-07-02 11:15:40","updated_at":"2019-07-02 11:15:40"}
     * default_seller_detail_translation : {"seller_detail_translation_id":1,"language_id":1,"seller_detail_id":1,"business_id":1,"seller_name":"zomato","address":"dsfsdf","created_at":"2019-07-02 11:15:40","updated_at":"2019-07-02 11:15:40"}
     * user : {"id":3,"status_id":1,"business_name":"zomato","seller_id":3,"role_id":4,"address_id":3,"name":"Nitish Anand","email":"nitish_anand11@jmtit.com","contact_number":"9102946117","optional_contact_number":null,"adhar_card":null,"pan_card":null,"food_license":null,"gst_certificate":null,"residential_certificate":null,"DOB":null,"gst_number":null,"wallet":0,"otp":null,"otp_time":null,"is_varified":0,"business_id":1,"created_at":"2019-07-02 11:15:39","updated_at":"2019-07-02 11:15:40","status":{"status_id":1,"status_name":"Active","status_class":"success","created_at":"2019-07-01 20:42:46","updated_at":"2019-07-01 20:42:46"},"city":null,"state":null,"country":null,"area":null}
     */

    private int seller_detail_id;
    private int user_id;
    private int business_id;
    private int seller_detail_translation_id;
    private String seller_image;
    private String seller_url;
    private String role_id;
    private Object email;
    private String contact_number;
    private Object adhar_card;
    private Object pan_card;
    private Object food_license;
    private Object local_id_registration_certificate;
    private Object tax_registration_certificate;
    private Object cancelled_check_copy;
    private String seller_website;
    private int terms_condition;
    private String local_id_registration_number;
    private int view_count;
    private String location;
    private int is_varified;
    private String created_at;
    private String updated_at;
    private SellerDetailTranslationBean seller_detail_translation;
    private DefaultSellerDetailTranslationBean default_seller_detail_translation;
    private UserBean user;

    public int getSeller_detail_id() {
        return seller_detail_id;
    }

    public void setSeller_detail_id(int seller_detail_id) {
        this.seller_detail_id = seller_detail_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public int getSeller_detail_translation_id() {
        return seller_detail_translation_id;
    }

    public void setSeller_detail_translation_id(int seller_detail_translation_id) {
        this.seller_detail_translation_id = seller_detail_translation_id;
    }

    public String getSeller_image() {
        return seller_image;
    }

    public void setSeller_image(String seller_image) {
        this.seller_image = seller_image;
    }

    public String getSeller_url() {
        return seller_url;
    }

    public void setSeller_url(String seller_url) {
        this.seller_url = seller_url;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public Object getAdhar_card() {
        return adhar_card;
    }

    public void setAdhar_card(Object adhar_card) {
        this.adhar_card = adhar_card;
    }

    public Object getPan_card() {
        return pan_card;
    }

    public void setPan_card(Object pan_card) {
        this.pan_card = pan_card;
    }

    public Object getFood_license() {
        return food_license;
    }

    public void setFood_license(Object food_license) {
        this.food_license = food_license;
    }

    public Object getLocal_id_registration_certificate() {
        return local_id_registration_certificate;
    }

    public void setLocal_id_registration_certificate(Object local_id_registration_certificate) {
        this.local_id_registration_certificate = local_id_registration_certificate;
    }

    public Object getTax_registration_certificate() {
        return tax_registration_certificate;
    }

    public void setTax_registration_certificate(Object tax_registration_certificate) {
        this.tax_registration_certificate = tax_registration_certificate;
    }

    public Object getCancelled_check_copy() {
        return cancelled_check_copy;
    }

    public void setCancelled_check_copy(Object cancelled_check_copy) {
        this.cancelled_check_copy = cancelled_check_copy;
    }

    public String getSeller_website() {
        return seller_website;
    }

    public void setSeller_website(String seller_website) {
        this.seller_website = seller_website;
    }

    public int getTerms_condition() {
        return terms_condition;
    }

    public void setTerms_condition(int terms_condition) {
        this.terms_condition = terms_condition;
    }

    public String getLocal_id_registration_number() {
        return local_id_registration_number;
    }

    public void setLocal_id_registration_number(String local_id_registration_number) {
        this.local_id_registration_number = local_id_registration_number;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getIs_varified() {
        return is_varified;
    }

    public void setIs_varified(int is_varified) {
        this.is_varified = is_varified;
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

    public SellerDetailTranslationBean getSeller_detail_translation() {
        return seller_detail_translation;
    }

    public void setSeller_detail_translation(SellerDetailTranslationBean seller_detail_translation) {
        this.seller_detail_translation = seller_detail_translation;
    }

    public DefaultSellerDetailTranslationBean getDefault_seller_detail_translation() {
        return default_seller_detail_translation;
    }

    public void setDefault_seller_detail_translation(DefaultSellerDetailTranslationBean default_seller_detail_translation) {
        this.default_seller_detail_translation = default_seller_detail_translation;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class SellerDetailTranslationBean {
        /**
         * seller_detail_translation_id : 1
         * language_id : 1
         * seller_detail_id : 1
         * business_id : 1
         * seller_name : zomato
         * address : dsfsdf
         * created_at : 2019-07-02 11:15:40
         * updated_at : 2019-07-02 11:15:40
         */

        private int seller_detail_translation_id;
        private int language_id;
        private int seller_detail_id;
        private int business_id;
        private String seller_name;
        private String address;
        private String created_at;
        private String updated_at;

        public int getSeller_detail_translation_id() {
            return seller_detail_translation_id;
        }

        public void setSeller_detail_translation_id(int seller_detail_translation_id) {
            this.seller_detail_translation_id = seller_detail_translation_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public int getSeller_detail_id() {
            return seller_detail_id;
        }

        public void setSeller_detail_id(int seller_detail_id) {
            this.seller_detail_id = seller_detail_id;
        }

        public int getBusiness_id() {
            return business_id;
        }

        public void setBusiness_id(int business_id) {
            this.business_id = business_id;
        }

        public String getSeller_name() {
            return seller_name;
        }

        public void setSeller_name(String seller_name) {
            this.seller_name = seller_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

    public static class DefaultSellerDetailTranslationBean {
        /**
         * seller_detail_translation_id : 1
         * language_id : 1
         * seller_detail_id : 1
         * business_id : 1
         * seller_name : zomato
         * address : dsfsdf
         * created_at : 2019-07-02 11:15:40
         * updated_at : 2019-07-02 11:15:40
         */

        private int seller_detail_translation_id;
        private int language_id;
        private int seller_detail_id;
        private int business_id;
        private String seller_name;
        private String address;
        private String created_at;
        private String updated_at;

        public int getSeller_detail_translation_id() {
            return seller_detail_translation_id;
        }

        public void setSeller_detail_translation_id(int seller_detail_translation_id) {
            this.seller_detail_translation_id = seller_detail_translation_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public int getSeller_detail_id() {
            return seller_detail_id;
        }

        public void setSeller_detail_id(int seller_detail_id) {
            this.seller_detail_id = seller_detail_id;
        }

        public int getBusiness_id() {
            return business_id;
        }

        public void setBusiness_id(int business_id) {
            this.business_id = business_id;
        }

        public String getSeller_name() {
            return seller_name;
        }

        public void setSeller_name(String seller_name) {
            this.seller_name = seller_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

    public static class UserBean {
        /**
         * id : 3
         * status_id : 1
         * business_name : zomato
         * seller_id : 3
         * role_id : 4
         * address_id : 3
         * name : Nitish Anand
         * email : nitish_anand11@jmtit.com
         * contact_number : 9102946117
         * optional_contact_number : null
         * adhar_card : null
         * pan_card : null
         * food_license : null
         * gst_certificate : null
         * residential_certificate : null
         * DOB : null
         * gst_number : null
         * wallet : 0
         * otp : null
         * otp_time : null
         * is_varified : 0
         * business_id : 1
         * created_at : 2019-07-02 11:15:39
         * updated_at : 2019-07-02 11:15:40
         * status : {"status_id":1,"status_name":"Active","status_class":"success","created_at":"2019-07-01 20:42:46","updated_at":"2019-07-01 20:42:46"}
         * city : null
         * state : null
         * country : null
         * area : null
         */

        private int id;
        private int status_id;
        private String business_name;
        private int seller_id;
        private int role_id;
        private int address_id;
        private String name;
        private String email;
        private String contact_number;
        private Object optional_contact_number;
        private Object adhar_card;
        private Object pan_card;
        private Object food_license;
        private Object gst_certificate;
        private Object residential_certificate;
        private Object DOB;
        private Object gst_number;
        private int wallet;
        private Object otp;
        private Object otp_time;
        private int is_varified;
        private int business_id;
        private String created_at;
        private String updated_at;
        private StatusBean status;
        private Object city;
        private Object state;
        private Object country;
        private Object area;

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

        public String getBusiness_name() {
            return business_name;
        }

        public void setBusiness_name(String business_name) {
            this.business_name = business_name;
        }

        public int getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(int seller_id) {
            this.seller_id = seller_id;
        }

        public int getRole_id() {
            return role_id;
        }

        public void setRole_id(int role_id) {
            this.role_id = role_id;
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

        public Object getAdhar_card() {
            return adhar_card;
        }

        public void setAdhar_card(Object adhar_card) {
            this.adhar_card = adhar_card;
        }

        public Object getPan_card() {
            return pan_card;
        }

        public void setPan_card(Object pan_card) {
            this.pan_card = pan_card;
        }

        public Object getFood_license() {
            return food_license;
        }

        public void setFood_license(Object food_license) {
            this.food_license = food_license;
        }

        public Object getGst_certificate() {
            return gst_certificate;
        }

        public void setGst_certificate(Object gst_certificate) {
            this.gst_certificate = gst_certificate;
        }

        public Object getResidential_certificate() {
            return residential_certificate;
        }

        public void setResidential_certificate(Object residential_certificate) {
            this.residential_certificate = residential_certificate;
        }

        public Object getDOB() {
            return DOB;
        }

        public void setDOB(Object DOB) {
            this.DOB = DOB;
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

        public Object getOtp() {
            return otp;
        }

        public void setOtp(Object otp) {
            this.otp = otp;
        }

        public Object getOtp_time() {
            return otp_time;
        }

        public void setOtp_time(Object otp_time) {
            this.otp_time = otp_time;
        }

        public int getIs_varified() {
            return is_varified;
        }

        public void setIs_varified(int is_varified) {
            this.is_varified = is_varified;
        }

        public int getBusiness_id() {
            return business_id;
        }

        public void setBusiness_id(int business_id) {
            this.business_id = business_id;
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

        public StatusBean getStatus() {
            return status;
        }

        public void setStatus(StatusBean status) {
            this.status = status;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getCountry() {
            return country;
        }

        public void setCountry(Object country) {
            this.country = country;
        }

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
            this.area = area;
        }

        public static class StatusBean {
            /**
             * status_id : 1
             * status_name : Active
             * status_class : success
             * created_at : 2019-07-01 20:42:46
             * updated_at : 2019-07-01 20:42:46
             */

            private int status_id;
            private String status_name;
            private String status_class;
            private String created_at;
            private String updated_at;

            public int getStatus_id() {
                return status_id;
            }

            public void setStatus_id(int status_id) {
                this.status_id = status_id;
            }

            public String getStatus_name() {
                return status_name;
            }

            public void setStatus_name(String status_name) {
                this.status_name = status_name;
            }

            public String getStatus_class() {
                return status_class;
            }

            public void setStatus_class(String status_class) {
                this.status_class = status_class;
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
}
