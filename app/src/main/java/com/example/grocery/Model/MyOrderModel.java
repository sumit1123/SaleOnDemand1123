package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 9/29/2017.
 */

public class MyOrderModel {
    /**
     * order_id : 4
     * user_id : 3
     * delivery_status_id : 1
     * seller_id : 2
     * city_id : 1
     * state_id : 1
     * country_id : 1
     * pincode_id : 1
     * area_id : 1
     * payment_status_id : 1
     * order_type : 1
     * order_review : null
     * order_rating : 0
     * delivery_address : bdhfjf
     * delivery_pincode :
     * order_description : null
     * source_id : 2
     * created_at : 2018-08-04 20:35:02
     * updated_at : 2018-08-04 20:35:02
     * user : {"id":3,"status_id":1,"business_name":null,"seller_id":null,"address_id":3,"name":"Ketki Dadge","email":"dadgeketki@gmail.com","contact_number":"dadgeketki@gmail.com","optional_contact_number":null,"DOB":null,"gst_number":null,"wallet":0,"otp":null,"is_varified":0,"created_at":"2018-08-04 19:24:18","updated_at":"2018-08-04 19:59:51"}
     * delivery_status : {"delivery_status_id":1,"delivery_status_translation_id":1,"delivery_status_color":"270EFF","is_email":0,"is_cancel":1,"is_active":1,"created_at":"2018-08-04 18:51:45","updated_at":"2018-08-04 18:51:45","default_delivery_status_translation":{"delivery_status_translation_id":1,"delivery_status_id":1,"language_id":1,"delivery_status_name":"Pending","created_at":"2018-08-04 18:51:46","updated_at":"2018-08-04 18:51:46"},"delivery_status_translation":{"delivery_status_translation_id":1,"delivery_status_id":1,"language_id":1,"delivery_status_name":"Pending","created_at":"2018-08-04 18:51:46","updated_at":"2018-08-04 18:51:46"}}
     * order_product_amount : {"order_id":4,"total_my_price":399,"total_gst":0}
     */

    private int order_id;
    private int user_id;
    private int delivery_status_id;
    private int seller_id;
    private int city_id;
    private int state_id;
    private int country_id;
    private int pincode_id;
    private int area_id;
    private int payment_status_id;
    private int order_type;
    private String order_review;
    private double order_rating;
    private String delivery_address;
    private String delivery_pincode;
    private String order_description;
    private int source_id;
    private String created_at;
    private String updated_at;
    private UserBean user;
    private DeliveryStatusBean delivery_status;
    private OrderProductAmountBean order_product_amount;
    private OrderAmountBean order_amount;

    public OrderAmountBean getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(OrderAmountBean order_amount) {
        this.order_amount = order_amount;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDelivery_status_id() {
        return delivery_status_id;
    }

    public void setDelivery_status_id(int delivery_status_id) {
        this.delivery_status_id = delivery_status_id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
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

    public int getPincode_id() {
        return pincode_id;
    }

    public void setPincode_id(int pincode_id) {
        this.pincode_id = pincode_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public int getPayment_status_id() {
        return payment_status_id;
    }

    public void setPayment_status_id(int payment_status_id) {
        this.payment_status_id = payment_status_id;
    }

    public int getOrder_type() {
        return order_type;
    }

    public void setOrder_type(int order_type) {
        this.order_type = order_type;
    }

    public String  getOrder_review() {
        return order_review;
    }

    public void setOrder_review(String order_review) {
        this.order_review = order_review;
    }

    public double getOrder_rating() {
        return order_rating;
    }

    public void setOrder_rating(double order_rating) {
        this.order_rating = order_rating;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getDelivery_pincode() {
        return delivery_pincode;
    }

    public void setDelivery_pincode(String delivery_pincode) {
        this.delivery_pincode = delivery_pincode;
    }

    public String getOrder_description() {
        return order_description;
    }

    public void setOrder_description(String order_description) {
        this.order_description = order_description;
    }

    public int getSource_id() {
        return source_id;
    }

    public void setSource_id(int source_id) {
        this.source_id = source_id;
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

    public DeliveryStatusBean getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(DeliveryStatusBean delivery_status) {
        this.delivery_status = delivery_status;
    }

    public OrderProductAmountBean getOrder_product_amount() {
        return order_product_amount;
    }

    public void setOrder_product_amount(OrderProductAmountBean order_product_amount) {
        this.order_product_amount = order_product_amount;
    }


    public class OrderAmountBean {
        private int order_id;
        private double total_amount;
        private double total_gst;
        private double subtotal_amount;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public double getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(double total_amount) {
            this.total_amount = total_amount;
        }

        public double getTotal_gst() {
            return total_gst;
        }

        public void setTotal_gst(double total_gst) {
            this.total_gst = total_gst;
        }

        public double getSubtotal_amount() {
            return subtotal_amount;
        }

        public void setSubtotal_amount(double subtotal_amount) {
            this.subtotal_amount = subtotal_amount;
        }

    }


    public static class UserBean {
        /**
         * id : 3
         * status_id : 1
         * business_name : null
         * seller_id : null
         * address_id : 3
         * name : Ketki Dadge
         * email : dadgeketki@gmail.com
         * contact_number : dadgeketki@gmail.com
         * optional_contact_number : null
         * DOB : null
         * gst_number : null
         * wallet : 0
         * otp : null
         * is_varified : 0
         * created_at : 2018-08-04 19:24:18
         * updated_at : 2018-08-04 19:59:51
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
        private Object gst_number;
        private int wallet;
        private Object otp;
        private int is_varified;
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
    }

    public static class DeliveryStatusBean {
        /**
         * delivery_status_id : 1
         * delivery_status_translation_id : 1
         * delivery_status_color : 270EFF
         * is_email : 0
         * is_cancel : 1
         * is_active : 1
         * created_at : 2018-08-04 18:51:45
         * updated_at : 2018-08-04 18:51:45
         * default_delivery_status_translation : {"delivery_status_translation_id":1,"delivery_status_id":1,"language_id":1,"delivery_status_name":"Pending","created_at":"2018-08-04 18:51:46","updated_at":"2018-08-04 18:51:46"}
         * delivery_status_translation : {"delivery_status_translation_id":1,"delivery_status_id":1,"language_id":1,"delivery_status_name":"Pending","created_at":"2018-08-04 18:51:46","updated_at":"2018-08-04 18:51:46"}
         */

        private int delivery_status_id;
        private int delivery_status_translation_id;
        private String delivery_status_color;
        private int is_email;
        private int is_cancel;
        private int is_active;
        private String created_at;
        private String updated_at;
        private DefaultDeliveryStatusTranslationBean default_delivery_status_translation;
        private DeliveryStatusTranslationBean delivery_status_translation;

        public int getDelivery_status_id() {
            return delivery_status_id;
        }

        public void setDelivery_status_id(int delivery_status_id) {
            this.delivery_status_id = delivery_status_id;
        }

        public int getDelivery_status_translation_id() {
            return delivery_status_translation_id;
        }

        public void setDelivery_status_translation_id(int delivery_status_translation_id) {
            this.delivery_status_translation_id = delivery_status_translation_id;
        }

        public String getDelivery_status_color() {
            return delivery_status_color;
        }

        public void setDelivery_status_color(String delivery_status_color) {
            this.delivery_status_color = delivery_status_color;
        }

        public int getIs_email() {
            return is_email;
        }

        public void setIs_email(int is_email) {
            this.is_email = is_email;
        }

        public int getIs_cancel() {
            return is_cancel;
        }

        public void setIs_cancel(int is_cancel) {
            this.is_cancel = is_cancel;
        }

        public int getIs_active() {
            return is_active;
        }

        public void setIs_active(int is_active) {
            this.is_active = is_active;
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

        public DefaultDeliveryStatusTranslationBean getDefault_delivery_status_translation() {
            return default_delivery_status_translation;
        }

        public void setDefault_delivery_status_translation(DefaultDeliveryStatusTranslationBean default_delivery_status_translation) {
            this.default_delivery_status_translation = default_delivery_status_translation;
        }

        public DeliveryStatusTranslationBean getDelivery_status_translation() {
            return delivery_status_translation;
        }

        public void setDelivery_status_translation(DeliveryStatusTranslationBean delivery_status_translation) {
            this.delivery_status_translation = delivery_status_translation;
        }

        public static class DefaultDeliveryStatusTranslationBean {
            /**
             * delivery_status_translation_id : 1
             * delivery_status_id : 1
             * language_id : 1
             * delivery_status_name : Pending
             * created_at : 2018-08-04 18:51:46
             * updated_at : 2018-08-04 18:51:46
             */

            private int delivery_status_translation_id;
            private int delivery_status_id;
            private int language_id;
            private String delivery_status_name;
            private String created_at;
            private String updated_at;

            public int getDelivery_status_translation_id() {
                return delivery_status_translation_id;
            }

            public void setDelivery_status_translation_id(int delivery_status_translation_id) {
                this.delivery_status_translation_id = delivery_status_translation_id;
            }

            public int getDelivery_status_id() {
                return delivery_status_id;
            }

            public void setDelivery_status_id(int delivery_status_id) {
                this.delivery_status_id = delivery_status_id;
            }

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public String getDelivery_status_name() {
                return delivery_status_name;
            }

            public void setDelivery_status_name(String delivery_status_name) {
                this.delivery_status_name = delivery_status_name;
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

        public static class DeliveryStatusTranslationBean {
            /**
             * delivery_status_translation_id : 1
             * delivery_status_id : 1
             * language_id : 1
             * delivery_status_name : Pending
             * created_at : 2018-08-04 18:51:46
             * updated_at : 2018-08-04 18:51:46
             */

            private int delivery_status_translation_id;
            private int delivery_status_id;
            private int language_id;
            private String delivery_status_name;
            private String created_at;
            private String updated_at;

            public int getDelivery_status_translation_id() {
                return delivery_status_translation_id;
            }

            public void setDelivery_status_translation_id(int delivery_status_translation_id) {
                this.delivery_status_translation_id = delivery_status_translation_id;
            }

            public int getDelivery_status_id() {
                return delivery_status_id;
            }

            public void setDelivery_status_id(int delivery_status_id) {
                this.delivery_status_id = delivery_status_id;
            }

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public String getDelivery_status_name() {
                return delivery_status_name;
            }

            public void setDelivery_status_name(String delivery_status_name) {
                this.delivery_status_name = delivery_status_name;
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

    public static class OrderProductAmountBean {
        /**
         * order_id : 4
         * total_my_price : 399
         * total_gst : 0
         */

        private int order_id;
        private float total_my_price;
        private float total_gst;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public float getTotal_my_price() {
            return total_my_price;
        }

        public void setTotal_my_price(int total_my_price) {
            this.total_my_price = total_my_price;
        }

        public float getTotal_gst() {
            return total_gst;
        }

        public void setTotal_gst(int total_gst) {
            this.total_gst = total_gst;
        }
    }
}
