package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 3/8/2018.
 */

public class FcmModel {

    /**
     * image :
     * notification : {"notification_class_id":1,"product":{"is_active":1,"product_image":null,"allow_customer_stock_out":0,"created_at":"2018-03-07 22:21:56","child_variant_id":1,"brand_id":1,"is_product_shipping":0,"updated_at":"2018-03-08 11:20:53","default_product_translation":{"meta_description":null,"delivery_message":"FREE DELIVERY in 2 days","product_url":null,"meta_title":null,"updated_at":"2018-03-07 22:21:56","product_id":1,"description":"<p>retgrwet<\/p>","created_at":"2018-03-07 22:21:56","language_id":1,"product_translation_id":1,"meta_keywords":null,"product_name":"ergergt"},"parent_variant_id":1,"product_id":1,"track_inventory":0,"product_translation_id":1,"seller_id":2,"view_count":1,"gst_id":1},"section_id":1,"notification_image":"notification-images/cuwPbiPuud8spXHC8p63RRM3rdyOoGpag7KFe12B.jpeg","updated_at":"2018-03-07 22:22:57","created_at":"2018-03-07 22:22:57","notification_id":2,"category":{"is_main_category":0,"category_image":null,"is_active":1,"category_id":1,"updated_at":null,"category_translation_id":1,"created_at":null,"default_category_translation":{"category_name":"All Products","category_url":"all_products","category_id":1,"updated_at":null,"category_translation_id":1,"created_at":null,"language_id":1},"is_parent_category":0},"notification_translation_id":2}
     * is_background : false
     * payload : {"score":"5.6","team":"India"}
     * id : 116809
     * title : New Offer for the day1
     * message : <p>erff</p>
     * class_name : ProductDetails
     * timestamp : 2018-03-08 11:46:53
     */

    private String image;
    private NotificationBean notification;
    private boolean is_background;
    private PayloadBean payload;
    private int id;
    private String title;
    private String message;
    private String class_name;
    private String timestamp;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public NotificationBean getNotification() {
        return notification;
    }

    public void setNotification(NotificationBean notification) {
        this.notification = notification;
    }

    public boolean isIs_background() {
        return is_background;
    }

    public void setIs_background(boolean is_background) {
        this.is_background = is_background;
    }

    public PayloadBean getPayload() {
        return payload;
    }

    public void setPayload(PayloadBean payload) {
        this.payload = payload;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static class NotificationBean {
        /**
         * notification_class_id : 1
         * product : {"is_active":1,"product_image":null,"allow_customer_stock_out":0,"created_at":"2018-03-07 22:21:56","child_variant_id":1,"brand_id":1,"is_product_shipping":0,"updated_at":"2018-03-08 11:20:53","default_product_translation":{"meta_description":null,"delivery_message":"FREE DELIVERY in 2 days","product_url":null,"meta_title":null,"updated_at":"2018-03-07 22:21:56","product_id":1,"description":"<p>retgrwet<\/p>","created_at":"2018-03-07 22:21:56","language_id":1,"product_translation_id":1,"meta_keywords":null,"product_name":"ergergt"},"parent_variant_id":1,"product_id":1,"track_inventory":0,"product_translation_id":1,"seller_id":2,"view_count":1,"gst_id":1}
         * section_id : 1
         * notification_image : notification-images/cuwPbiPuud8spXHC8p63RRM3rdyOoGpag7KFe12B.jpeg
         * updated_at : 2018-03-07 22:22:57
         * created_at : 2018-03-07 22:22:57
         * notification_id : 2
         * category : {"is_main_category":0,"category_image":null,"is_active":1,"category_id":1,"updated_at":null,"category_translation_id":1,"created_at":null,"default_category_translation":{"category_name":"All Products","category_url":"all_products","category_id":1,"updated_at":null,"category_translation_id":1,"created_at":null,"language_id":1},"is_parent_category":0}
         * notification_translation_id : 2
         */

        private int notification_class_id;
        private ProductBean product;
        private int section_id;
        private String notification_image;
        private String updated_at;
        private String created_at;
        private int notification_id;
        private CategoryBean category;
        private int notification_translation_id;

        public int getNotification_class_id() {
            return notification_class_id;
        }

        public void setNotification_class_id(int notification_class_id) {
            this.notification_class_id = notification_class_id;
        }

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public int getSection_id() {
            return section_id;
        }

        public void setSection_id(int section_id) {
            this.section_id = section_id;
        }

        public String getNotification_image() {
            return notification_image;
        }

        public void setNotification_image(String notification_image) {
            this.notification_image = notification_image;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getNotification_id() {
            return notification_id;
        }

        public void setNotification_id(int notification_id) {
            this.notification_id = notification_id;
        }

        public CategoryBean getCategory() {
            return category;
        }

        public void setCategory(CategoryBean category) {
            this.category = category;
        }

        public int getNotification_translation_id() {
            return notification_translation_id;
        }

        public void setNotification_translation_id(int notification_translation_id) {
            this.notification_translation_id = notification_translation_id;
        }

        public static class ProductBean {
            /**
             * is_active : 1
             * product_image : null
             * allow_customer_stock_out : 0
             * created_at : 2018-03-07 22:21:56
             * child_variant_id : 1
             * brand_id : 1
             * is_product_shipping : 0
             * updated_at : 2018-03-08 11:20:53
             * default_product_translation : {"meta_description":null,"delivery_message":"FREE DELIVERY in 2 days","product_url":null,"meta_title":null,"updated_at":"2018-03-07 22:21:56","product_id":1,"description":"<p>retgrwet<\/p>","created_at":"2018-03-07 22:21:56","language_id":1,"product_translation_id":1,"meta_keywords":null,"product_name":"ergergt"}
             * parent_variant_id : 1
             * product_id : 1
             * track_inventory : 0
             * product_translation_id : 1
             * seller_id : 2
             * view_count : 1
             * gst_id : 1
             */

            private int status_id;
            private Object product_image;
            private int allow_customer_stock_out;
            private String created_at;
            private int child_variant_id;
            private int brand_id;
            private int is_product_shipping;
            private String updated_at;
            private DefaultProductTranslationBean default_product_translation;
            private int parent_variant_id;
            private int product_id;
            private int track_inventory;
            private int product_translation_id;
            private int seller_id;
            private int view_count;
            private int gst_id;

            public int getStatus_id() {
                return status_id;
            }

            public void setStatus_id(int status_id) {
                this.status_id = status_id;
            }

            public Object getProduct_image() {
                return product_image;
            }

            public void setProduct_image(Object product_image) {
                this.product_image = product_image;
            }

            public int getAllow_customer_stock_out() {
                return allow_customer_stock_out;
            }

            public void setAllow_customer_stock_out(int allow_customer_stock_out) {
                this.allow_customer_stock_out = allow_customer_stock_out;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public int getChild_variant_id() {
                return child_variant_id;
            }

            public void setChild_variant_id(int child_variant_id) {
                this.child_variant_id = child_variant_id;
            }

            public int getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(int brand_id) {
                this.brand_id = brand_id;
            }

            public int getIs_product_shipping() {
                return is_product_shipping;
            }

            public void setIs_product_shipping(int is_product_shipping) {
                this.is_product_shipping = is_product_shipping;
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

            public int getParent_variant_id() {
                return parent_variant_id;
            }

            public void setParent_variant_id(int parent_variant_id) {
                this.parent_variant_id = parent_variant_id;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public int getTrack_inventory() {
                return track_inventory;
            }

            public void setTrack_inventory(int track_inventory) {
                this.track_inventory = track_inventory;
            }

            public int getProduct_translation_id() {
                return product_translation_id;
            }

            public void setProduct_translation_id(int product_translation_id) {
                this.product_translation_id = product_translation_id;
            }

            public int getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }

            public int getView_count() {
                return view_count;
            }

            public void setView_count(int view_count) {
                this.view_count = view_count;
            }

            public int getGst_id() {
                return gst_id;
            }

            public void setGst_id(int gst_id) {
                this.gst_id = gst_id;
            }

            public static class DefaultProductTranslationBean {
                /**
                 * meta_description : null
                 * delivery_message : FREE DELIVERY in 2 days
                 * product_url : null
                 * meta_title : null
                 * updated_at : 2018-03-07 22:21:56
                 * product_id : 1
                 * description : <p>retgrwet</p>
                 * created_at : 2018-03-07 22:21:56
                 * language_id : 1
                 * product_translation_id : 1
                 * meta_keywords : null
                 * product_name : ergergt
                 */

                private Object meta_description;
                private String delivery_message;
                private Object product_url;
                private Object meta_title;
                private String updated_at;
                private int product_id;
                private String description;
                private String created_at;
                private int language_id;
                private int product_translation_id;
                private Object meta_keywords;
                private String product_name;

                public Object getMeta_description() {
                    return meta_description;
                }

                public void setMeta_description(Object meta_description) {
                    this.meta_description = meta_description;
                }

                public String getDelivery_message() {
                    return delivery_message;
                }

                public void setDelivery_message(String delivery_message) {
                    this.delivery_message = delivery_message;
                }

                public Object getProduct_url() {
                    return product_url;
                }

                public void setProduct_url(Object product_url) {
                    this.product_url = product_url;
                }

                public Object getMeta_title() {
                    return meta_title;
                }

                public void setMeta_title(Object meta_title) {
                    this.meta_title = meta_title;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }

                public int getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(int product_id) {
                    this.product_id = product_id;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public int getLanguage_id() {
                    return language_id;
                }

                public void setLanguage_id(int language_id) {
                    this.language_id = language_id;
                }

                public int getProduct_translation_id() {
                    return product_translation_id;
                }

                public void setProduct_translation_id(int product_translation_id) {
                    this.product_translation_id = product_translation_id;
                }

                public Object getMeta_keywords() {
                    return meta_keywords;
                }

                public void setMeta_keywords(Object meta_keywords) {
                    this.meta_keywords = meta_keywords;
                }

                public String getProduct_name() {
                    return product_name;
                }

                public void setProduct_name(String product_name) {
                    this.product_name = product_name;
                }
            }
        }

        public static class CategoryBean {
            /**
             * is_main_category : 0
             * category_image : null
             * is_active : 1
             * category_id : 1
             * updated_at : null
             * category_translation_id : 1
             * created_at : null
             * default_category_translation : {"category_name":"All Products","category_url":"all_products","category_id":1,"updated_at":null,"category_translation_id":1,"created_at":null,"language_id":1}
             * is_parent_category : 0
             */

            private int is_main_category;
            private Object category_image;
            private int is_active;
            private int category_id;
            private Object updated_at;
            private int category_translation_id;
            private Object created_at;
            private DefaultCategoryTranslationBean default_category_translation;
            private int is_parent_category;

            public int getIs_main_category() {
                return is_main_category;
            }

            public void setIs_main_category(int is_main_category) {
                this.is_main_category = is_main_category;
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

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public Object getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(Object updated_at) {
                this.updated_at = updated_at;
            }

            public int getCategory_translation_id() {
                return category_translation_id;
            }

            public void setCategory_translation_id(int category_translation_id) {
                this.category_translation_id = category_translation_id;
            }

            public Object getCreated_at() {
                return created_at;
            }

            public void setCreated_at(Object created_at) {
                this.created_at = created_at;
            }

            public DefaultCategoryTranslationBean getDefault_category_translation() {
                return default_category_translation;
            }

            public void setDefault_category_translation(DefaultCategoryTranslationBean default_category_translation) {
                this.default_category_translation = default_category_translation;
            }

            public int getIs_parent_category() {
                return is_parent_category;
            }

            public void setIs_parent_category(int is_parent_category) {
                this.is_parent_category = is_parent_category;
            }

            public static class DefaultCategoryTranslationBean {
                /**
                 * category_name : All Products
                 * category_url : all_products
                 * category_id : 1
                 * updated_at : null
                 * category_translation_id : 1
                 * created_at : null
                 * language_id : 1
                 */

                private String category_name;
                private String category_url;
                private int category_id;
                private Object updated_at;
                private int category_translation_id;
                private Object created_at;
                private int language_id;

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

                public int getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(int category_id) {
                    this.category_id = category_id;
                }

                public Object getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(Object updated_at) {
                    this.updated_at = updated_at;
                }

                public int getCategory_translation_id() {
                    return category_translation_id;
                }

                public void setCategory_translation_id(int category_translation_id) {
                    this.category_translation_id = category_translation_id;
                }

                public Object getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(Object created_at) {
                    this.created_at = created_at;
                }

                public int getLanguage_id() {
                    return language_id;
                }

                public void setLanguage_id(int language_id) {
                    this.language_id = language_id;
                }
            }
        }
    }

    public static class PayloadBean {
        /**
         * score : 5.6
         * team : India
         */

        private String score;
        private String team;

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getTeam() {
            return team;
        }

        public void setTeam(String team) {
            this.team = team;
        }
    }
}
