package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 9/29/2017.
 */

public class CartProductModel {

    /**
     * order_product_id : 1
     * product_id : 1
     * parent_variant_id : 1
     * child_variant_id : 1
     * market_price : 120
     * my_price : 100
     * quantity : 1
     * user_id : 8
     * brand_id : 2
     * order_id : 6
     * gst : 0
     * sgst : 0
     * cgst : 0
     * igst : 0
     * created_at : 2017-12-27 21:39:31
     * updated_at : 2017-12-27 21:39:31
     * product : {"product_id":1,"product_image":"product-image/EmRpRdRzPprp5jujvzWsx7KmROa5q1BwjHMa3EX9.png","brand_id":2,"status_id":1,"gst_id":1,"seller_id":9,"is_product_shipping":0,"track_inventory":0,"allow_customer_stock_out":0,"created_at":"2017-12-27 17:48:49","updated_at":"2017-12-27 17:48:49","product_translation_id":1,"default_product_translation":{"product_translation_id":1,"language_id":1,"product_id":1,"product_name":"Product 1 english","description":"p1 desc engllisg","meta_title":null,"meta_keywords":null,"meta_description":null,"product_url":"","parent_variant":"p1 -color-en","child_variant":"p1-size-en","created_at":null,"updated_at":null,"delivery_message":"delevery msg en"},"product_translation":{"product_translation_id":2,"language_id":2,"product_id":1,"product_name":"p1-mr","description":"des-mr-p1","meta_title":null,"meta_keywords":null,"meta_description":null,"product_url":null,"parent_variant":"color-mr-p1","child_variant":"size-mr-p1","created_at":null,"updated_at":null,"delivery_message":"del-mr"},"brand":{"brand_id":2,"created_at":"2017-12-27 17:48:49","updated_at":"2017-12-27 17:48:49","brand_translation_id":4,"default_brand_translation":{"brand_translation_id":4,"brand_id":2,"language_id":2,"brand_name":"brand2 mr","created_at":null,"updated_at":null},"brand_translation":{"brand_translation_id":4,"brand_id":2,"language_id":2,"brand_name":"brand2 mr","created_at":null,"updated_at":null}}}
     * parent_variant : {"parent_variant_id":1,"created_at":null,"updated_at":null,"parent_variant_translation_id":1,"default_parent_variant_translation":{"parent_variant_translation_id":1,"parent_variant_id":1,"language_id":1,"parent_variant_name":"1 en","created_at":null,"updated_at":null},"parent_variant_translation":{"parent_variant_translation_id":2,"parent_variant_id":1,"language_id":2,"parent_variant_name":"2en","created_at":null,"updated_at":null}}
     * child_variant : {"child_variant_id":1,"created_at":null,"updated_at":null,"child_variant_translation_id":1,"default_child_variant_translation":{"child_variant_translation_id":1,"child_variant_id":1,"language_id":1,"child_variant_name":"child 1 en","created_at":null,"updated_at":null},"child_variant_translation":{"child_variant_translation_id":2,"child_variant_id":1,"language_id":2,"child_variant_name":"child1 mr","created_at":null,"updated_at":null}}
     * user : {"id":8,"status_id":1,"business_name":null,"seller_id":null,"address_id":7,"name":"Mohd Afzal","email":"mohd.afzal16@gmail.com","contact_number":"mohd.afzal16@gmail.com","optional_contact_number":null,"DOB":null,"city_id":1,"state_id":1,"country_id":1,"pincode":null,"gst_number":null,"wallet":0,"token":null,"created_at":"2017-12-27 12:54:47","updated_at":"2017-12-30 10:56:32"}
     */

    private int order_product_id;
    private int product_id;
    private int parent_variant_id;
    private int child_variant_id;
    private double market_price;
    private double my_price;
    private int quantity;
   // private int user_id;

    private int user_id;


    private int brand_id;
    private int order_id;
    private double gst;
    private double sgst;

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }

    public double getSgst() {
        return sgst;
    }

    public void setSgst(double sgst) {
        this.sgst = sgst;
    }

    public double getCgst() {
        return cgst;
    }

    public void setCgst(double cgst) {
        this.cgst = cgst;
    }

    public double getIgst() {
        return igst;
    }

    public void setIgst(double igst) {
        this.igst = igst;
    }

    private double cgst;
    private double igst;
    private String created_at;
    private String updated_at;
    private ProductBean product;
    private ParentVariantBean parent_variant;
    private ChildVariantBean child_variant;
    private UserBean user;

    public int getOrder_product_id() {
        return order_product_id;
    }

    public void setOrder_product_id(int order_product_id) {
        this.order_product_id = order_product_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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

    public double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
    }

    public double getMy_price() {
        return my_price;
    }

    public void setMy_price(double my_price) {
        this.my_price = my_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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

    public ParentVariantBean getParent_variant() {
        return parent_variant;
    }

    public void setParent_variant(ParentVariantBean parent_variant) {
        this.parent_variant = parent_variant;
    }

    public ChildVariantBean getChild_variant() {
        return child_variant;
    }

    public void setChild_variant(ChildVariantBean child_variant) {
        this.child_variant = child_variant;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class ProductBean {
        /**
         * product_id : 1
         * product_image : product-image/EmRpRdRzPprp5jujvzWsx7KmROa5q1BwjHMa3EX9.png
         * brand_id : 2
         * status_id : 1
         * gst_id : 1
         * seller_id : 9
         * is_product_shipping : 0
         * track_inventory : 0
         * allow_customer_stock_out : 0
         * created_at : 2017-12-27 17:48:49
         * updated_at : 2017-12-27 17:48:49
         * product_translation_id : 1
         * default_product_translation : {"product_translation_id":1,"language_id":1,"product_id":1,"product_name":"Product 1 english","description":"p1 desc engllisg","meta_title":null,"meta_keywords":null,"meta_description":null,"product_url":"","parent_variant":"p1 -color-en","child_variant":"p1-size-en","created_at":null,"updated_at":null,"delivery_message":"delevery msg en"}
         * product_translation : {"product_translation_id":2,"language_id":2,"product_id":1,"product_name":"p1-mr","description":"des-mr-p1","meta_title":null,"meta_keywords":null,"meta_description":null,"product_url":null,"parent_variant":"color-mr-p1","child_variant":"size-mr-p1","created_at":null,"updated_at":null,"delivery_message":"del-mr"}
         * brand : {"brand_id":2,"created_at":"2017-12-27 17:48:49","updated_at":"2017-12-27 17:48:49","brand_translation_id":4,"default_brand_translation":{"brand_translation_id":4,"brand_id":2,"language_id":2,"brand_name":"brand2 mr","created_at":null,"updated_at":null},"brand_translation":{"brand_translation_id":4,"brand_id":2,"language_id":2,"brand_name":"brand2 mr","created_at":null,"updated_at":null}}
         */

        private int product_id;
        private String product_image;
        private int brand_id;
        private int status_id;
        private int gst_id;
        private int seller_id;
        private int is_product_shipping;
        private int track_inventory;
        private int allow_customer_stock_out;
        private String created_at;
        private String updated_at;
        private int product_translation_id;
        private DefaultProductTranslationBean default_product_translation;
        private ProductTranslationBean product_translation;
        private BrandBean brand;

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

        public int getProduct_translation_id() {
            return product_translation_id;
        }

        public void setProduct_translation_id(int product_translation_id) {
            this.product_translation_id = product_translation_id;
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

        public BrandBean getBrand() {
            return brand;
        }

        public void setBrand(BrandBean brand) {
            this.brand = brand;
        }

        public static class DefaultProductTranslationBean {
            /**
             * product_translation_id : 1
             * language_id : 1
             * product_id : 1
             * product_name : Product 1 english
             * description : p1 desc engllisg
             * meta_title : null
             * meta_keywords : null
             * meta_description : null
             * product_url :
             * parent_variant : p1 -color-en
             * child_variant : p1-size-en
             * created_at : null
             * updated_at : null
             * delivery_message : delevery msg en
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
            private String parent_variant;
            private String child_variant;
            private Object created_at;
            private Object updated_at;
            private String delivery_message;

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

            public String getParent_variant() {
                return parent_variant;
            }

            public void setParent_variant(String parent_variant) {
                this.parent_variant = parent_variant;
            }

            public String getChild_variant() {
                return child_variant;
            }

            public void setChild_variant(String child_variant) {
                this.child_variant = child_variant;
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

            public String getDelivery_message() {
                return delivery_message;
            }

            public void setDelivery_message(String delivery_message) {
                this.delivery_message = delivery_message;
            }
        }

        public static class ProductTranslationBean {
            /**
             * product_translation_id : 2
             * language_id : 2
             * product_id : 1
             * product_name : p1-mr
             * description : des-mr-p1
             * meta_title : null
             * meta_keywords : null
             * meta_description : null
             * product_url : null
             * parent_variant : color-mr-p1
             * child_variant : size-mr-p1
             * created_at : null
             * updated_at : null
             * delivery_message : del-mr
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
            private String parent_variant;
            private String child_variant;
            private Object created_at;
            private Object updated_at;
            private String delivery_message;

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

            public String getParent_variant() {
                return parent_variant;
            }

            public void setParent_variant(String parent_variant) {
                this.parent_variant = parent_variant;
            }

            public String getChild_variant() {
                return child_variant;
            }

            public void setChild_variant(String child_variant) {
                this.child_variant = child_variant;
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

            public String getDelivery_message() {
                return delivery_message;
            }

            public void setDelivery_message(String delivery_message) {
                this.delivery_message = delivery_message;
            }
        }

        public static class BrandBean {
            /**
             * brand_id : 2
             * created_at : 2017-12-27 17:48:49
             * updated_at : 2017-12-27 17:48:49
             * brand_translation_id : 4
             * default_brand_translation : {"brand_translation_id":4,"brand_id":2,"language_id":2,"brand_name":"brand2 mr","created_at":null,"updated_at":null}
             * brand_translation : {"brand_translation_id":4,"brand_id":2,"language_id":2,"brand_name":"brand2 mr","created_at":null,"updated_at":null}
             */

            private int brand_id;
            private String created_at;
            private String updated_at;
            private int brand_translation_id;
            private DefaultBrandTranslationBean default_brand_translation;
            private BrandTranslationBean brand_translation;

            public int getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(int brand_id) {
                this.brand_id = brand_id;
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

            public int getBrand_translation_id() {
                return brand_translation_id;
            }

            public void setBrand_translation_id(int brand_translation_id) {
                this.brand_translation_id = brand_translation_id;
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

            public static class DefaultBrandTranslationBean {
                /**
                 * brand_translation_id : 4
                 * brand_id : 2
                 * language_id : 2
                 * brand_name : brand2 mr
                 * created_at : null
                 * updated_at : null
                 */

                private int brand_translation_id;
                private int brand_id;
                private int language_id;
                private String brand_name;
                private Object created_at;
                private Object updated_at;

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

            public static class BrandTranslationBean {
                /**
                 * brand_translation_id : 4
                 * brand_id : 2
                 * language_id : 2
                 * brand_name : brand2 mr
                 * created_at : null
                 * updated_at : null
                 */

                private int brand_translation_id;
                private int brand_id;
                private int language_id;
                private String brand_name;
                private Object created_at;
                private Object updated_at;

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

    public static class ParentVariantBean {
        /**
         * parent_variant_id : 1
         * created_at : null
         * updated_at : null
         * parent_variant_translation_id : 1
         * default_parent_variant_translation : {"parent_variant_translation_id":1,"parent_variant_id":1,"language_id":1,"parent_variant_name":"1 en","created_at":null,"updated_at":null}
         * parent_variant_translation : {"parent_variant_translation_id":2,"parent_variant_id":1,"language_id":2,"parent_variant_name":"2en","created_at":null,"updated_at":null}
         */

        private int parent_variant_id;
        private Object created_at;
        private Object updated_at;
        private int parent_variant_translation_id;
        private DefaultParentVariantTranslationBean default_parent_variant_translation;
        private ParentVariantTranslationBean parent_variant_translation;

        public int getParent_variant_id() {
            return parent_variant_id;
        }

        public void setParent_variant_id(int parent_variant_id) {
            this.parent_variant_id = parent_variant_id;
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

        public int getParent_variant_translation_id() {
            return parent_variant_translation_id;
        }

        public void setParent_variant_translation_id(int parent_variant_translation_id) {
            this.parent_variant_translation_id = parent_variant_translation_id;
        }

        public DefaultParentVariantTranslationBean getDefault_parent_variant_translation() {
            return default_parent_variant_translation;
        }

        public void setDefault_parent_variant_translation(DefaultParentVariantTranslationBean default_parent_variant_translation) {
            this.default_parent_variant_translation = default_parent_variant_translation;
        }

        public ParentVariantTranslationBean getParent_variant_translation() {
            return parent_variant_translation;
        }

        public void setParent_variant_translation(ParentVariantTranslationBean parent_variant_translation) {
            this.parent_variant_translation = parent_variant_translation;
        }

        public static class DefaultParentVariantTranslationBean {
            /**
             * parent_variant_translation_id : 1
             * parent_variant_id : 1
             * language_id : 1
             * parent_variant_name : 1 en
             * created_at : null
             * updated_at : null
             */

            private int parent_variant_translation_id;
            private int parent_variant_id;
            private int language_id;
            private String parent_variant_name;
            private Object created_at;
            private Object updated_at;

            public int getParent_variant_translation_id() {
                return parent_variant_translation_id;
            }

            public void setParent_variant_translation_id(int parent_variant_translation_id) {
                this.parent_variant_translation_id = parent_variant_translation_id;
            }

            public int getParent_variant_id() {
                return parent_variant_id;
            }

            public void setParent_variant_id(int parent_variant_id) {
                this.parent_variant_id = parent_variant_id;
            }

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public String getParent_variant_name() {
                return parent_variant_name;
            }

            public void setParent_variant_name(String parent_variant_name) {
                this.parent_variant_name = parent_variant_name;
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

        public static class ParentVariantTranslationBean {
            /**
             * parent_variant_translation_id : 2
             * parent_variant_id : 1
             * language_id : 2
             * parent_variant_name : 2en
             * created_at : null
             * updated_at : null
             */

            private int parent_variant_translation_id;
            private int parent_variant_id;
            private int language_id;
            private String parent_variant_name;
            private Object created_at;
            private Object updated_at;

            public int getParent_variant_translation_id() {
                return parent_variant_translation_id;
            }

            public void setParent_variant_translation_id(int parent_variant_translation_id) {
                this.parent_variant_translation_id = parent_variant_translation_id;
            }

            public int getParent_variant_id() {
                return parent_variant_id;
            }

            public void setParent_variant_id(int parent_variant_id) {
                this.parent_variant_id = parent_variant_id;
            }

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public String getParent_variant_name() {
                return parent_variant_name;
            }

            public void setParent_variant_name(String parent_variant_name) {
                this.parent_variant_name = parent_variant_name;
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

    public static class ChildVariantBean {
        /**
         * child_variant_id : 1
         * created_at : null
         * updated_at : null
         * child_variant_translation_id : 1
         * default_child_variant_translation : {"child_variant_translation_id":1,"child_variant_id":1,"language_id":1,"child_variant_name":"child 1 en","created_at":null,"updated_at":null}
         * child_variant_translation : {"child_variant_translation_id":2,"child_variant_id":1,"language_id":2,"child_variant_name":"child1 mr","created_at":null,"updated_at":null}
         */

        private int child_variant_id;
        private Object created_at;
        private Object updated_at;
        private int child_variant_translation_id;
        private DefaultChildVariantTranslationBean default_child_variant_translation;
        private ChildVariantTranslationBean child_variant_translation;

        public int getChild_variant_id() {
            return child_variant_id;
        }

        public void setChild_variant_id(int child_variant_id) {
            this.child_variant_id = child_variant_id;
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

        public int getChild_variant_translation_id() {
            return child_variant_translation_id;
        }

        public void setChild_variant_translation_id(int child_variant_translation_id) {
            this.child_variant_translation_id = child_variant_translation_id;
        }

        public DefaultChildVariantTranslationBean getDefault_child_variant_translation() {
            return default_child_variant_translation;
        }

        public void setDefault_child_variant_translation(DefaultChildVariantTranslationBean default_child_variant_translation) {
            this.default_child_variant_translation = default_child_variant_translation;
        }

        public ChildVariantTranslationBean getChild_variant_translation() {
            return child_variant_translation;
        }

        public void setChild_variant_translation(ChildVariantTranslationBean child_variant_translation) {
            this.child_variant_translation = child_variant_translation;
        }

        public static class DefaultChildVariantTranslationBean {
            /**
             * child_variant_translation_id : 1
             * child_variant_id : 1
             * language_id : 1
             * child_variant_name : child 1 en
             * created_at : null
             * updated_at : null
             */

            private int child_variant_translation_id;
            private int child_variant_id;
            private int language_id;
            private String child_variant_name;
            private Object created_at;
            private Object updated_at;

            public int getChild_variant_translation_id() {
                return child_variant_translation_id;
            }

            public void setChild_variant_translation_id(int child_variant_translation_id) {
                this.child_variant_translation_id = child_variant_translation_id;
            }

            public int getChild_variant_id() {
                return child_variant_id;
            }

            public void setChild_variant_id(int child_variant_id) {
                this.child_variant_id = child_variant_id;
            }

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public String getChild_variant_name() {
                return child_variant_name;
            }

            public void setChild_variant_name(String child_variant_name) {
                this.child_variant_name = child_variant_name;
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

        public static class ChildVariantTranslationBean {
            /**
             * child_variant_translation_id : 2
             * child_variant_id : 1
             * language_id : 2
             * child_variant_name : child1 mr
             * created_at : null
             * updated_at : null
             */

            private int child_variant_translation_id;
            private int child_variant_id;
            private int language_id;
            private String child_variant_name;
            private Object created_at;
            private Object updated_at;

            public int getChild_variant_translation_id() {
                return child_variant_translation_id;
            }

            public void setChild_variant_translation_id(int child_variant_translation_id) {
                this.child_variant_translation_id = child_variant_translation_id;
            }

            public int getChild_variant_id() {
                return child_variant_id;
            }

            public void setChild_variant_id(int child_variant_id) {
                this.child_variant_id = child_variant_id;
            }

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public String getChild_variant_name() {
                return child_variant_name;
            }

            public void setChild_variant_name(String child_variant_name) {
                this.child_variant_name = child_variant_name;
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

    public static class UserBean {
        /**
         * id : 8
         * status_id : 1
         * business_name : null
         * seller_id : null
         * address_id : 7
         * name : Mohd Afzal
         * email : mohd.afzal16@gmail.com
         * contact_number : mohd.afzal16@gmail.com
         * optional_contact_number : null
         * DOB : null
         * city_id : 1
         * state_id : 1
         * country_id : 1
         * pincode : null
         * gst_number : null
         * wallet : 0
         * token : null
         * created_at : 2017-12-27 12:54:47
         * updated_at : 2017-12-30 10:56:32
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
        private Object pincode;
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

        public Object getPincode() {
            return pincode;
        }

        public void setPincode(Object pincode) {
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
}
