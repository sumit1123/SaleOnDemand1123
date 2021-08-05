package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 1/25/2018.
 */

public class OfferBlockModel {


    /**
     * offer_block_id : 1
     * offer_block_translation_id : 1
     * notification_class_id : 1
     * section_id : 3
     * is_active : 1
     * created_at : 2018-06-12 19:36:40
     * updated_at : 2018-06-12 19:36:46
     * default_offer_block_translation : {"offer_block_translation_id":1,"offer_block_id":1,"language_id":1,"offer_block_image":"offerblock/9jcpFRYbzIAV4sR8Q9S2GypNA1xazcE4Y1ITSc5e.jpeg","created_at":"2018-06-12 19:36:40","updated_at":"2018-06-12 19:36:40"}
     * offer_block_translation : {"offer_block_translation_id":1,"offer_block_id":1,"language_id":1,"offer_block_image":"offerblock/9jcpFRYbzIAV4sR8Q9S2GypNA1xazcE4Y1ITSc5e.jpeg","created_at":"2018-06-12 19:36:40","updated_at":"2018-06-12 19:36:40"}
     * product : {"product_id":3,"product_image":"product-image/nsXKWhEqzRwramOowZz9FTddYmUvg7XAIzCnkKqu.jpeg","brand_id":1,"parent_variant_id":1,"child_variant_id":1,"status_id":1,"gst_id":1,"seller_id":2,"is_product_shipping":0,"track_inventory":0,"allow_customer_stock_out":0,"product_translation_id":3,"view_count":5,"created_at":"2018-05-31 11:55:13","updated_at":"2018-06-29 16:58:01","default_product_translation":{"product_translation_id":3,"language_id":1,"product_id":3,"product_name":"John Players Men's Solid Casual Shirt","description":"https://www.youtube.com/watch?v=P-MQgNhlTdc","meta_title":null,"meta_keywords":null,"meta_description":null,"product_url":"demo","delivery_message":"FREE DELIVERY in 2 days","created_at":"2018-05-31 11:55:13","updated_at":"2018-06-17 19:31:48"},"product_translation":{"product_translation_id":3,"language_id":1,"product_id":3,"product_name":"John Players Men's Solid Casual Shirt","description":"https://www.youtube.com/watch?v=P-MQgNhlTdc","meta_title":null,"meta_keywords":null,"meta_description":null,"product_url":"demo","delivery_message":"FREE DELIVERY in 2 days","created_at":"2018-05-31 11:55:13","updated_at":"2018-06-17 19:31:48"}}
     * category : {"category_id":3,"category_translation_id":3,"category_image":null,"is_active":1,"is_parent_category":1,"is_main_category":0,"created_at":"2018-06-04 01:12:10","updated_at":"2018-06-04 22:35:00","default_category_translation":{"category_translation_id":3,"category_id":3,"language_id":1,"category_name":"hgfb krfgr","category_url":"hgfb-krfgr","created_at":"2018-06-04 01:12:10","updated_at":"2018-06-04 01:12:10"},"category_translation":{"category_translation_id":3,"category_id":3,"language_id":1,"category_name":"hgfb krfgr","category_url":"hgfb-krfgr","created_at":"2018-06-04 01:12:10","updated_at":"2018-06-04 01:12:10"}}
     * product_translation : {"product_translation_id":2,"language_id":2,"product_id":1,"product_name":"T-Shirt hn","description":"Description hn","meta_title":"","meta_keywords":"","meta_description":"","product_url":"t-shirt-hn","delivery_message":"FREE DELIVERY in 2 days hn","created_at":"2018-08-10 17:12:58","updated_at":"2018-08-10 17:25:21"}

     */

    private int offer_block_id;
    private int offer_block_translation_id;
    private int notification_class_id;
    private int section_id;
    private int is_active;
    private String created_at;
    private String updated_at;
    private DefaultOfferBlockTranslationBean default_offer_block_translation;
    private OfferBlockTranslationBean offer_block_translation;
    private ProductBean product;
    private CategoryBean category;
    private ProductModel.ProductTranslationBean product_translation;
    private ProductModel.DefaultProductTranslationBean default_product_translation;

    public int getOffer_block_id() {
        return offer_block_id;
    }

    public void setOffer_block_id(int offer_block_id) {
        this.offer_block_id = offer_block_id;
    }

    public int getOffer_block_translation_id() {
        return offer_block_translation_id;
    }

    public void setOffer_block_translation_id(int offer_block_translation_id) {
        this.offer_block_translation_id = offer_block_translation_id;
    }

    public ProductModel.DefaultProductTranslationBean getDefault_product_translation() {
        return default_product_translation;
    }

    public void setDefault_product_translation(ProductModel.DefaultProductTranslationBean default_product_translation) {
        this.default_product_translation = default_product_translation;
    }

    public ProductModel.ProductTranslationBean getProduct_translation() {
        return product_translation;
    }

    public void setProduct_translation(ProductModel.ProductTranslationBean product_translation) {
        this.product_translation = product_translation;
    }

    public int getNotification_class_id() {
        return notification_class_id;
    }

    public void setNotification_class_id(int notification_class_id) {
        this.notification_class_id = notification_class_id;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
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

    public DefaultOfferBlockTranslationBean getDefault_offer_block_translation() {
        return default_offer_block_translation;
    }

    public void setDefault_offer_block_translation(DefaultOfferBlockTranslationBean default_offer_block_translation) {
        this.default_offer_block_translation = default_offer_block_translation;
    }

    public OfferBlockTranslationBean getOffer_block_translation() {
        return offer_block_translation;
    }

    public void setOffer_block_translation(OfferBlockTranslationBean offer_block_translation) {
        this.offer_block_translation = offer_block_translation;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public static class DefaultOfferBlockTranslationBean {
        /**
         * offer_block_translation_id : 1
         * offer_block_id : 1
         * language_id : 1
         * offer_block_image : offerblock/9jcpFRYbzIAV4sR8Q9S2GypNA1xazcE4Y1ITSc5e.jpeg
         * created_at : 2018-06-12 19:36:40
         * updated_at : 2018-06-12 19:36:40
         */

        private int offer_block_translation_id;
        private int offer_block_id;
        private int language_id;
        private String offer_block_image;
        private String created_at;
        private String updated_at;

        public int getOffer_block_translation_id() {
            return offer_block_translation_id;
        }

        public void setOffer_block_translation_id(int offer_block_translation_id) {
            this.offer_block_translation_id = offer_block_translation_id;
        }

        public int getOffer_block_id() {
            return offer_block_id;
        }

        public void setOffer_block_id(int offer_block_id) {
            this.offer_block_id = offer_block_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getOffer_block_image() {
            return offer_block_image;
        }

        public void setOffer_block_image(String offer_block_image) {
            this.offer_block_image = offer_block_image;
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

    public static class OfferBlockTranslationBean {
        /**
         * offer_block_translation_id : 1
         * offer_block_id : 1
         * language_id : 1
         * offer_block_image : offerblock/9jcpFRYbzIAV4sR8Q9S2GypNA1xazcE4Y1ITSc5e.jpeg
         * created_at : 2018-06-12 19:36:40
         * updated_at : 2018-06-12 19:36:40
         */

        private int offer_block_translation_id;
        private int offer_block_id;
        private int language_id;
        private String offer_block_image;
        private String created_at;
        private String updated_at;

        public int getOffer_block_translation_id() {
            return offer_block_translation_id;
        }

        public void setOffer_block_translation_id(int offer_block_translation_id) {
            this.offer_block_translation_id = offer_block_translation_id;
        }

        public int getOffer_block_id() {
            return offer_block_id;
        }

        public void setOffer_block_id(int offer_block_id) {
            this.offer_block_id = offer_block_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getOffer_block_image() {
            return offer_block_image;
        }

        public void setOffer_block_image(String offer_block_image) {
            this.offer_block_image = offer_block_image;
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
         * product_id : 3
         * product_image : product-image/nsXKWhEqzRwramOowZz9FTddYmUvg7XAIzCnkKqu.jpeg
         * brand_id : 1
         * parent_variant_id : 1
         * child_variant_id : 1
         * status_id : 1
         * gst_id : 1
         * seller_id : 2
         * is_product_shipping : 0
         * track_inventory : 0
         * allow_customer_stock_out : 0
         * product_translation_id : 3
         * view_count : 5
         * created_at : 2018-05-31 11:55:13
         * updated_at : 2018-06-29 16:58:01
         * default_product_translation : {"product_translation_id":3,"language_id":1,"product_id":3,"product_name":"John Players Men's Solid Casual Shirt","description":"https://www.youtube.com/watch?v=P-MQgNhlTdc","meta_title":null,"meta_keywords":null,"meta_description":null,"product_url":"demo","delivery_message":"FREE DELIVERY in 2 days","created_at":"2018-05-31 11:55:13","updated_at":"2018-06-17 19:31:48"}
         * product_translation : {"product_translation_id":3,"language_id":1,"product_id":3,"product_name":"John Players Men's Solid Casual Shirt","description":"https://www.youtube.com/watch?v=P-MQgNhlTdc","meta_title":null,"meta_keywords":null,"meta_description":null,"product_url":"demo","delivery_message":"FREE DELIVERY in 2 days","created_at":"2018-05-31 11:55:13","updated_at":"2018-06-17 19:31:48"}
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
        private int view_count;
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

        public int getView_count() {
            return view_count;
        }

        public void setView_count(int view_count) {
            this.view_count = view_count;
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
             * product_translation_id : 3
             * language_id : 1
             * product_id : 3
             * product_name : John Players Men's Solid Casual Shirt
             * description : https://www.youtube.com/watch?v=P-MQgNhlTdc
             * meta_title : null
             * meta_keywords : null
             * meta_description : null
             * product_url : demo
             * delivery_message : FREE DELIVERY in 2 days
             * created_at : 2018-05-31 11:55:13
             * updated_at : 2018-06-17 19:31:48
             */

            private int product_translation_id;
            private int language_id;
            private int product_id;
            private String product_name;
            private String description;
            private Object meta_title;
            private Object meta_keywords;
            private Object meta_description;
            private String product_url;
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

            public String getProduct_url() {
                return product_url;
            }

            public void setProduct_url(String product_url) {
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
             * product_translation_id : 3
             * language_id : 1
             * product_id : 3
             * product_name : John Players Men's Solid Casual Shirt
             * description : https://www.youtube.com/watch?v=P-MQgNhlTdc
             * meta_title : null
             * meta_keywords : null
             * meta_description : null
             * product_url : demo
             * delivery_message : FREE DELIVERY in 2 days
             * created_at : 2018-05-31 11:55:13
             * updated_at : 2018-06-17 19:31:48
             */

            private int product_translation_id;
            private int language_id;
            private int product_id;
            private String product_name;
            private String description;
            private Object meta_title;
            private Object meta_keywords;
            private Object meta_description;
            private String product_url;
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

            public String getProduct_url() {
                return product_url;
            }

            public void setProduct_url(String product_url) {
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

    public static class CategoryBean {
        /**
         * category_id : 3
         * category_translation_id : 3
         * category_image : null
         * is_active : 1
         * is_parent_category : 1
         * is_main_category : 0
         * created_at : 2018-06-04 01:12:10
         * updated_at : 2018-06-04 22:35:00
         * default_category_translation : {"category_translation_id":3,"category_id":3,"language_id":1,"category_name":"hgfb krfgr","category_url":"hgfb-krfgr","created_at":"2018-06-04 01:12:10","updated_at":"2018-06-04 01:12:10"}
         * category_translation : {"category_translation_id":3,"category_id":3,"language_id":1,"category_name":"hgfb krfgr","category_url":"hgfb-krfgr","created_at":"2018-06-04 01:12:10","updated_at":"2018-06-04 01:12:10"}
         */

        private int category_id;
        private int category_translation_id;
        private Object category_image;
        private int is_active;
        private int is_parent_category;
        private int is_main_category;
        private String created_at;
        private String updated_at;
        private DefaultCategoryTranslationBean default_category_translation;
        private CategoryTranslationBean category_translation;

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getCategory_translation_id() {
            return category_translation_id;
        }

        public void setCategory_translation_id(int category_translation_id) {
            this.category_translation_id = category_translation_id;
        }

        public Object getCategory_image() {
            return category_image;
        }

        public void setCategory_image(Object category_image) {
            this.category_image = category_image;
        }

        public int getIs_active() {
            return is_active;
        }

        public void setIs_active(int is_active) {
            this.is_active = is_active;
        }

        public int getIs_parent_category() {
            return is_parent_category;
        }

        public void setIs_parent_category(int is_parent_category) {
            this.is_parent_category = is_parent_category;
        }

        public int getIs_main_category() {
            return is_main_category;
        }

        public void setIs_main_category(int is_main_category) {
            this.is_main_category = is_main_category;
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

        public DefaultCategoryTranslationBean getDefault_category_translation() {
            return default_category_translation;
        }

        public void setDefault_category_translation(DefaultCategoryTranslationBean default_category_translation) {
            this.default_category_translation = default_category_translation;
        }

        public CategoryTranslationBean getCategory_translation() {
            return category_translation;
        }

        public void setCategory_translation(CategoryTranslationBean category_translation) {
            this.category_translation = category_translation;
        }

        public static class DefaultCategoryTranslationBean {
            /**
             * category_translation_id : 3
             * category_id : 3
             * language_id : 1
             * category_name : hgfb krfgr
             * category_url : hgfb-krfgr
             * created_at : 2018-06-04 01:12:10
             * updated_at : 2018-06-04 01:12:10
             */

            private int category_translation_id;
            private int category_id;
            private int language_id;
            private String category_name;
            private String category_url;
            private String created_at;
            private String updated_at;

            public int getCategory_translation_id() {
                return category_translation_id;
            }

            public void setCategory_translation_id(int category_translation_id) {
                this.category_translation_id = category_translation_id;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getCategory_url() {
                return category_url;
            }

            public void setCategory_url(String category_url) {
                this.category_url = category_url;
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

        public static class CategoryTranslationBean {
            /**
             * category_translation_id : 3
             * category_id : 3
             * language_id : 1
             * category_name : hgfb krfgr
             * category_url : hgfb-krfgr
             * created_at : 2018-06-04 01:12:10
             * updated_at : 2018-06-04 01:12:10
             */

            private int category_translation_id;
            private int category_id;
            private int language_id;
            private String category_name;
            private String category_url;
            private String created_at;
            private String updated_at;

            public int getCategory_translation_id() {
                return category_translation_id;
            }

            public void setCategory_translation_id(int category_translation_id) {
                this.category_translation_id = category_translation_id;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getCategory_url() {
                return category_url;
            }

            public void setCategory_url(String category_url) {
                this.category_url = category_url;
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
