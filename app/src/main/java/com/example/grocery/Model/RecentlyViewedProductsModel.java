package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 11/20/2017.
 */

public class RecentlyViewedProductsModel {

    /**
     * recent_view_product_id : 1
     * user_id : 2
     * product_id : 4
     * created_at : 2017-11-20 10:20:40
     * updated_at : 2017-11-20 10:20:40
     * product : {"product_id":4,"product_name":"Product Name","description":"<ol><li>new&nbsp;<br><\/li><li><h4>yes kewjfd eq<\/h4><em>kjdbjkd qjek<\/em><br><\/li><li>qwdbjqw<strong>k dqwd<\/strong>jkqbdqjk<br><\/li><\/ol>","product_image":"product-image/mXxX3MHPV1KTNLTk2ts5izzeHkRuwYKyOSqtX0Qa.png","is_offer_zone":0,"delivery_message":"","brand_id":3,"category_id":4,"is_parent_active":1,"status_id":1,"created_at":null,"updated_at":null}
     */

    private int recent_view_product_id;
    private int user_id;
    private int product_id;
    private String created_at;
    private String updated_at;
    private ProductBean product;

    public int getRecent_view_product_id() {
        return recent_view_product_id;
    }

    public void setRecent_view_product_id(int recent_view_product_id) {
        this.recent_view_product_id = recent_view_product_id;
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

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public static class ProductBean {
        /**
         * product_id : 4
         * product_name : Product Name
         * description : <ol><li>new&nbsp;<br></li><li><h4>yes kewjfd eq</h4><em>kjdbjkd qjek</em><br></li><li>qwdbjqw<strong>k dqwd</strong>jkqbdqjk<br></li></ol>
         * product_image : product-image/mXxX3MHPV1KTNLTk2ts5izzeHkRuwYKyOSqtX0Qa.png
         * is_offer_zone : 0
         * delivery_message :
         * brand_id : 3
         * category_id : 4
         * is_parent_active : 1
         * status_id : 1
         * created_at : null
         * updated_at : null
         */

        private int product_id;
        private String product_name;
        private String description;
        private String product_image;
        private int is_offer_zone;
        private String delivery_message;
        private int brand_id;
        private int category_id;
        private int is_parent_active;
        private int status_id;
        private Object created_at;
        private Object updated_at;

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getProduct_image() {
            return product_image;
        }

        public void setProduct_image(String product_image) {
            this.product_image = product_image;
        }

        public int getIs_offer_zone() {
            return is_offer_zone;
        }

        public void setIs_offer_zone(int is_offer_zone) {
            this.is_offer_zone = is_offer_zone;
        }

        public String getDelivery_message() {
            return delivery_message;
        }

        public void setDelivery_message(String delivery_message) {
            this.delivery_message = delivery_message;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getIs_parent_active() {
            return is_parent_active;
        }

        public void setIs_parent_active(int is_parent_active) {
            this.is_parent_active = is_parent_active;
        }

        public int getStatus_id() {
            return status_id;
        }

        public void setStatus_id(int status_id) {
            this.status_id = status_id;
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
