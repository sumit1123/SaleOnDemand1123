package com.example.grocery.Model;

import java.util.List;

/**
 * Created by Mohd Afzal on 10/25/2017.
 */

public class DisplayViewPagerModel {


    private ProductBean product;
    private int rating;
    private int rating_count;
    private int my_rating;
    private List<ImagesBean> images;
    private List<?> reviews;

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating_count() {
        return rating_count;
    }

    public void setRating_count(int rating_count) {
        this.rating_count = rating_count;
    }

    public int getMy_rating() {
        return my_rating;
    }

    public void setMy_rating(int my_rating) {
        this.my_rating = my_rating;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public List<?> getReviews() {
        return reviews;
    }

    public void setReviews(List<?> reviews) {
        this.reviews = reviews;
    }

    public static class ProductBean {
        /**
         * product_id : 17
         * product_name : Jeans Denim
         * description : tyhfgxb
         * selling_price : 800
         * actual_price : 1500
         * product_image : product-image/pfQzArSmDGqzna15sx9Nd4XJ31JjW0jbmwxTmoCm.jpeg
         * status : 0
         * is_deleted : 0
         * deleted_by : 0
         * is_offer_zone : 0
         * available_quantity : 0
         * user_id : 1
         * brand_id : 9
         * sub_category_id : 4
         * parent_deleted : 0
         * created_at : 2017-10-16 07:17:55
         * updated_at : 2017-10-16 07:17:55
         */

        private int product_id;
        private String product_name;
        private String description;
        private int selling_price;
        private int actual_price;
        private String product_image;
        private int status;
        private int is_deleted;
        private int deleted_by;
        private int is_offer_zone;
        private int available_quantity;
        private int user_id;
        private int brand_id;
        private int sub_category_id;
        private int parent_deleted;
        private String created_at;
        private String updated_at;

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

        public int getSelling_price() {
            return selling_price;
        }

        public void setSelling_price(int selling_price) {
            this.selling_price = selling_price;
        }

        public int getActual_price() {
            return actual_price;
        }

        public void setActual_price(int actual_price) {
            this.actual_price = actual_price;
        }

        public String getProduct_image() {
            return product_image;
        }

        public void setProduct_image(String product_image) {
            this.product_image = product_image;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(int is_deleted) {
            this.is_deleted = is_deleted;
        }

        public int getDeleted_by() {
            return deleted_by;
        }

        public void setDeleted_by(int deleted_by) {
            this.deleted_by = deleted_by;
        }

        public int getIs_offer_zone() {
            return is_offer_zone;
        }

        public void setIs_offer_zone(int is_offer_zone) {
            this.is_offer_zone = is_offer_zone;
        }

        public int getAvailable_quantity() {
            return available_quantity;
        }

        public void setAvailable_quantity(int available_quantity) {
            this.available_quantity = available_quantity;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public int getSub_category_id() {
            return sub_category_id;
        }

        public void setSub_category_id(int sub_category_id) {
            this.sub_category_id = sub_category_id;
        }

        public int getParent_deleted() {
            return parent_deleted;
        }

        public void setParent_deleted(int parent_deleted) {
            this.parent_deleted = parent_deleted;
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
         * product_image_id : 33
         * optional_product_image : optional-product-image/TQpTx5h0lyndX4GDRpD3mku3rrvPLsR5bngJBveN.jpeg
         * product_id : 17
         * created_at : 2017-10-16 07:21:08
         * updated_at : 2017-10-16 07:21:08
         */

        private int product_image_id;
        private String optional_product_image;
        private int product_id;
        private String created_at;
        private String updated_at;

        public int getProduct_image_id() {
            return product_image_id;
        }

        public void setProduct_image_id(int product_image_id) {
            this.product_image_id = product_image_id;
        }

        public String getOptional_product_image() {
            return optional_product_image;
        }

        public void setOptional_product_image(String optional_product_image) {
            this.optional_product_image = optional_product_image;
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
    }
}
