package com.example.grocery.Model;

import java.util.List;

/**
 * Created by Mohd Afzal on 9/27/2017.
 */

public class ReviewsModel {

    /**
     * review_id : 1
     * review : Hhggv
     * rating : 3
     * date : 2018-02-06
     * user_id : 3
     * product_id : 1
     * created_at : 2018-02-06 12:29:20
     * updated_at : 2018-02-06 13:26:24
     * user : {"id":3,"status_id":1,"business_name":null,"seller_id":null,"address_id":3,"name":"Mohd Afzal","email":"mohd.afzal16@gmail.com","contact_number":"mohd.afzal16@gmail.com","optional_contact_number":null,"DOB":null,"gst_number":null,"wallet":0,"otp":null,"is_varified":0,"token":null,"created_at":"2018-02-06 12:18:49","updated_at":"2018-02-06 12:18:49"}
     * images : [{"review_image_id":1,"product_review_image":"img_1078261.png","review_id":1,"created_at":"2018-02-06 14:13:48","updated_at":"2018-02-06 14:13:48"},{"review_image_id":2,"product_review_image":"img_1100620.png","review_id":1,"created_at":"2018-02-06 14:16:05","updated_at":"2018-02-06 14:16:05"},{"review_image_id":3,"product_review_image":"img_1102787.png","review_id":1,"created_at":"2018-02-06 14:16:51","updated_at":"2018-02-06 14:16:51"},{"review_image_id":4,"product_review_image":"img_1049032.png","review_id":1,"created_at":"2018-02-06 14:18:11","updated_at":"2018-02-06 14:18:11"},{"review_image_id":5,"product_review_image":"img_1031749.png","review_id":1,"created_at":"2018-02-06 14:18:44","updated_at":"2018-02-06 14:18:44"},{"review_image_id":6,"product_review_image":"img_1038514.png","review_id":1,"created_at":"2018-02-06 14:19:36","updated_at":"2018-02-06 14:19:36"}]
     */

    private int review_id;
    private String review;
    private Double rating;
    private String date;

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    private int user_id;
    private int product_id;
    private String created_at;
    private String updated_at;
    private UserBean user;
    private List<ImagesBean> images;

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class UserBean {
        /**
         * id : 3
         * status_id : 1
         * business_name : null
         * seller_id : null
         * address_id : 3
         * name : Mohd Afzal
         * email : mohd.afzal16@gmail.com
         * contact_number : mohd.afzal16@gmail.com
         * optional_contact_number : null
         * DOB : null
         * gst_number : null
         * wallet : 0
         * otp : null
         * is_varified : 0
         * token : null
         * created_at : 2018-02-06 12:18:49
         * updated_at : 2018-02-06 12:18:49
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

    public static class ImagesBean {
        /**
         * review_image_id : 1
         * product_review_image : img_1078261.png
         * review_id : 1
         * created_at : 2018-02-06 14:13:48
         * updated_at : 2018-02-06 14:13:48
         */

        private int review_image_id;
        private String product_review_image;
        private int review_id;
        private String created_at;
        private String updated_at;

        public int getReview_image_id() {
            return review_image_id;
        }

        public void setReview_image_id(int review_image_id) {
            this.review_image_id = review_image_id;
        }

        public String getProduct_review_image() {
            return product_review_image;
        }

        public void setProduct_review_image(String product_review_image) {
            this.product_review_image = product_review_image;
        }

        public int getReview_id() {
            return review_id;
        }

        public void setReview_id(int review_id) {
            this.review_id = review_id;
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
