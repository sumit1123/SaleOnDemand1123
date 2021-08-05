package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 1/20/2018.
 */

public class FeaturedBrandsModel {

    /**
     * brand_id : 2
     * brand_translation_id : 2
     * created_at : 2018-01-20 22:02:10
     * updated_at : 2018-01-20 22:02:10
     * default_brand_translation : {"brand_translation_id":2,"brand_id":2,"language_id":1,"brand_name":"Nike","brand_image":"brand-image/wZZAj3sMmZ8Qh93pIo53zt6gOjfpeOEX7AwknLI4.jpeg","created_at":"2018-01-20 22:02:10","updated_at":"2018-01-20 22:02:10"}
     * brand_translation : {"brand_translation_id":2,"brand_id":2,"language_id":1,"brand_name":"Nike","brand_image":"brand-image/wZZAj3sMmZ8Qh93pIo53zt6gOjfpeOEX7AwknLI4.jpeg","created_at":"2018-01-20 22:02:10","updated_at":"2018-01-20 22:02:10"}
     * product : {"product_id":1,"product_image":"product-image/05wcHJDwJiaH8Ef2w294Ci0Cf6QTrfmvMSMfWQeZ.png","brand_id":2,"parent_variant_id":1,"child_variant_id":1,"status_id":1,"gst_id":1,"seller_id":2,"is_product_shipping":0,"track_inventory":0,"allow_customer_stock_out":0,"product_translation_id":1,"created_at":"2018-01-20 22:04:08","updated_at":"2018-01-20 22:04:08","default_product_translation":{"product_translation_id":1,"language_id":1,"product_id":1,"product_name":"product1","description":"<p>xcfghbjn<\/p>","meta_title":null,"meta_keywords":null,"meta_description":null,"product_url":null,"delivery_message":"FREE DELIVERY in 2 days","created_at":"2018-01-20 22:04:08","updated_at":"2018-01-20 22:04:08"},"product_translation":{"product_translation_id":1,"language_id":1,"product_id":1,"product_name":"product1","description":"<p>xcfghbjn<\/p>","meta_title":null,"meta_keywords":null,"meta_description":null,"product_url":null,"delivery_message":"FREE DELIVERY in 2 days","created_at":"2018-01-20 22:04:08","updated_at":"2018-01-20 22:04:08"}}
     */

    private int brand_id;
    private String brand_translation_id;
    private String created_at;
    private String updated_at;
    private DefaultBrandTranslationBean default_brand_translation;
    private BrandTranslationBean brand_translation;
    private ProductBean product;

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_translation_id() {
        return brand_translation_id;
    }

    public void setBrand_translation_id(String brand_translation_id) {
        this.brand_translation_id = brand_translation_id;
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

    public DefaultBrandTranslationBean getDefault_brand_translation() {
        return default_brand_translation;
    }

    public void setDefault_brand_translation(DefaultBrandTranslationBean default_brand_translation) {
        this.default_brand_translation = default_brand_translation;
    }

    public BrandTranslationBean getBrand_translation() {
        return brand_translation;
    }

    public void setBrand_translation(BrandTranslationBean brand_translation) {
        this.brand_translation = brand_translation;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public static class DefaultBrandTranslationBean {
        /**
         * brand_translation_id : 2
         * brand_id : 2
         * language_id : 1
         * brand_name : Nike
         * brand_image : brand-image/wZZAj3sMmZ8Qh93pIo53zt6gOjfpeOEX7AwknLI4.jpeg
         * created_at : 2018-01-20 22:02:10
         * updated_at : 2018-01-20 22:02:10
         */

        private int brand_translation_id;
        private int brand_id;
        private int language_id;
        private String brand_name;
        private String brand_image;
        private String created_at;
        private String updated_at;

        public int getBrand_translation_id() {
            return brand_translation_id;
        }

        public void setBrand_translation_id(int brand_translation_id) {
            this.brand_translation_id = brand_translation_id;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getBrand_image() {
            return brand_image;
        }

        public void setBrand_image(String brand_image) {
            this.brand_image = brand_image;
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

    public static class BrandTranslationBean {
        /**
         * brand_translation_id : 2
         * brand_id : 2
         * language_id : 1
         * brand_name : Nike
         * brand_image : brand-image/wZZAj3sMmZ8Qh93pIo53zt6gOjfpeOEX7AwknLI4.jpeg
         * created_at : 2018-01-20 22:02:10
         * updated_at : 2018-01-20 22:02:10
         */

        private int brand_translation_id;
        private int brand_id;
        private int language_id;
        private String brand_name;
        private String brand_image;
        private String created_at;
        private String updated_at;

        public int getBrand_translation_id() {
            return brand_translation_id;
        }

        public void setBrand_translation_id(int brand_translation_id) {
            this.brand_translation_id = brand_translation_id;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getBrand_image() {
            return brand_image;
        }

        public void setBrand_image(String brand_image) {
            this.brand_image = brand_image;
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

    public static class ProductBean {
        /**
         * product_id : 1
         * product_image : product-image/05wcHJDwJiaH8Ef2w294Ci0Cf6QTrfmvMSMfWQeZ.png
         * brand_id : 2
         * parent_variant_id : 1
         * child_variant_id : 1
         * status_id : 1
         * gst_id : 1
         * seller_id : 2
         * is_product_shipping : 0
         * track_inventory : 0
         * allow_customer_stock_out : 0
         * product_translation_id : 1
         * created_at : 2018-01-20 22:04:08
         * updated_at : 2018-01-20 22:04:08
         * default_product_translation : {"product_translation_id":1,"language_id":1,"product_id":1,"product_name":"product1","description":"<p>xcfghbjn<\/p>","meta_title":null,"meta_keywords":null,"meta_description":null,"product_url":null,"delivery_message":"FREE DELIVERY in 2 days","created_at":"2018-01-20 22:04:08","updated_at":"2018-01-20 22:04:08"}
         * product_translation : {"product_translation_id":1,"language_id":1,"product_id":1,"product_name":"product1","description":"<p>xcfghbjn<\/p>","meta_title":null,"meta_keywords":null,"meta_description":null,"product_url":null,"delivery_message":"FREE DELIVERY in 2 days","created_at":"2018-01-20 22:04:08","updated_at":"2018-01-20 22:04:08"}
         */

        private int product_id;
        private String product_image;
        private int brand_id;
        private int parent_variant_id;
        private int child_variant_id;
        private int status_id;
        private int gst_id;
        private int seller_id;
        private int is_product_shipping;
        private int track_inventory;
        private int allow_customer_stock_out;
        private int product_translation_id;
        private String created_at;
        private String updated_at;
        private DefaultProductTranslationBean default_product_translation;
        private ProductTranslationBean product_translation;

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getProduct_image() {
            return product_image;
        }

        public void setProduct_image(String product_image) {
            this.product_image = product_image;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public int getParent_variant_id() {
            return parent_variant_id;
        }

        public void setParent_variant_id(int parent_variant_id) {
            this.parent_variant_id = parent_variant_id;
        }

        public int getChild_variant_id() {
            return child_variant_id;
        }

        public void setChild_variant_id(int child_variant_id) {
            this.child_variant_id = child_variant_id;
        }

        public int getStatus_id() {
            return status_id;
        }

        public void setStatus_id(int status_id) {
            this.status_id = status_id;
        }

        public int getGst_id() {
            return gst_id;
        }

        public void setGst_id(int gst_id) {
            this.gst_id = gst_id;
        }

        public int getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(int seller_id) {
            this.seller_id = seller_id;
        }

        public int getIs_product_shipping() {
            return is_product_shipping;
        }

        public void setIs_product_shipping(int is_product_shipping) {
            this.is_product_shipping = is_product_shipping;
        }

        public int getTrack_inventory() {
            return track_inventory;
        }

        public void setTrack_inventory(int track_inventory) {
            this.track_inventory = track_inventory;
        }

        public int getAllow_customer_stock_out() {
            return allow_customer_stock_out;
        }

        public void setAllow_customer_stock_out(int allow_customer_stock_out) {
            this.allow_customer_stock_out = allow_customer_stock_out;
        }

        public int getProduct_translation_id() {
            return product_translation_id;
        }

        public void setProduct_translation_id(int product_translation_id) {
            this.product_translation_id = product_translation_id;
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

        public DefaultProductTranslationBean getDefault_product_translation() {
            return default_product_translation;
        }

        public void setDefault_product_translation(DefaultProductTranslationBean default_product_translation) {
            this.default_product_translation = default_product_translation;
        }

        public ProductTranslationBean getProduct_translation() {
            return product_translation;
        }

        public void setProduct_translation(ProductTranslationBean product_translation) {
            this.product_translation = product_translation;
        }

        public static class DefaultProductTranslationBean {
            /**
             * product_translation_id : 1
             * language_id : 1
             * product_id : 1
             * product_name : product1
             * description : <p>xcfghbjn</p>
             * meta_title : null
             * meta_keywords : null
             * meta_description : null
             * product_url : null
             * delivery_message : FREE DELIVERY in 2 days
             * created_at : 2018-01-20 22:04:08
             * updated_at : 2018-01-20 22:04:08
             */

            private int product_translation_id;
            private int language_id;
            private int product_id;
            private String product_name;
            private String description;
            private Object meta_title;
            private Object meta_keywords;
            private Object meta_description;
            private Object product_url;
            private String delivery_message;
            private String created_at;
            private String updated_at;

            public int getProduct_translation_id() {
                return product_translation_id;
            }

            public void setProduct_translation_id(int product_translation_id) {
                this.product_translation_id = product_translation_id;
            }

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

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

            public Object getMeta_title() {
                return meta_title;
            }

            public void setMeta_title(Object meta_title) {
                this.meta_title = meta_title;
            }

            public Object getMeta_keywords() {
                return meta_keywords;
            }

            public void setMeta_keywords(Object meta_keywords) {
                this.meta_keywords = meta_keywords;
            }

            public Object getMeta_description() {
                return meta_description;
            }

            public void setMeta_description(Object meta_description) {
                this.meta_description = meta_description;
            }

            public Object getProduct_url() {
                return product_url;
            }

            public void setProduct_url(Object product_url) {
                this.product_url = product_url;
            }

            public String getDelivery_message() {
                return delivery_message;
            }

            public void setDelivery_message(String delivery_message) {
                this.delivery_message = delivery_message;
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

        public static class ProductTranslationBean {
            /**
             * product_translation_id : 1
             * language_id : 1
             * product_id : 1
             * product_name : product1
             * description : <p>xcfghbjn</p>
             * meta_title : null
             * meta_keywords : null
             * meta_description : null
             * product_url : null
             * delivery_message : FREE DELIVERY in 2 days
             * created_at : 2018-01-20 22:04:08
             * updated_at : 2018-01-20 22:04:08
             */

            private int product_translation_id;
            private int language_id;
            private int product_id;
            private String product_name;
            private String description;
            private Object meta_title;
            private Object meta_keywords;
            private Object meta_description;
            private Object product_url;
            private String delivery_message;
            private String created_at;
            private String updated_at;

            public int getProduct_translation_id() {
                return product_translation_id;
            }

            public void setProduct_translation_id(int product_translation_id) {
                this.product_translation_id = product_translation_id;
            }

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

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

            public Object getMeta_title() {
                return meta_title;
            }

            public void setMeta_title(Object meta_title) {
                this.meta_title = meta_title;
            }

            public Object getMeta_keywords() {
                return meta_keywords;
            }

            public void setMeta_keywords(Object meta_keywords) {
                this.meta_keywords = meta_keywords;
            }

            public Object getMeta_description() {
                return meta_description;
            }

            public void setMeta_description(Object meta_description) {
                this.meta_description = meta_description;
            }

            public Object getProduct_url() {
                return product_url;
            }

            public void setProduct_url(Object product_url) {
                this.product_url = product_url;
            }

            public String getDelivery_message() {
                return delivery_message;
            }

            public void setDelivery_message(String delivery_message) {
                this.delivery_message = delivery_message;
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
