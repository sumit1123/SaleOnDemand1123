package com.example.grocery.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 9/16/2017.
 */

public class ProductModel {


    public boolean isinWebservice;
    public int getQuantityvariant;
    public boolean isaddbuttonvisible;
    private int minimumInteger;
    private boolean isinWishlist;
    private boolean wholeLayoutVisibility;
    public boolean imageNotAvailable;
    public boolean isvariantone;

    public boolean isIsinWebservice() {
        return isinWebservice;
    }

    public void setIsinWebservice(boolean isinWebservice) {
        this.isinWebservice = isinWebservice;
    }

    public boolean isIsvariantone() {
        return isvariantone;
    }

    public void setIsvariantone(boolean isvariantone) {
        this.isvariantone = isvariantone;
    }

    public boolean isImageNotAvailable() {
        return imageNotAvailable;
    }

    public void setImageNotAvailable(boolean imageNotAvailable) {
        this.imageNotAvailable = imageNotAvailable;
    }

    public boolean isWholeLayoutVisibility() {
        return wholeLayoutVisibility;
    }

    public void setWholeLayoutVisibility(boolean wholeLayoutVisibility) {
        this.wholeLayoutVisibility = wholeLayoutVisibility;
    }

    public int getQuantityvariant() {

        return minimumInteger;
    }

    public void setQuantityvariant(int minimumInteger) {
        this.minimumInteger = minimumInteger;
    }

    public int getGetQuantityvariant() {
        return getQuantityvariant;
    }

    public void setGetQuantityvariant(int getQuantityvariant) {
        this.getQuantityvariant = getQuantityvariant;
    }

    public boolean isinWishlist() {
        return isinWishlist;
    }

    public void setIsinWishlist(boolean isinWishlist) {
        this.isinWishlist = isinWishlist;
    }


    public boolean isaddbuttonvisible() {
        return isaddbuttonvisible;
    }

    public void setIsaddbuttonvisible(boolean isaddbuttonvisible) {
        this.isaddbuttonvisible = isaddbuttonvisible;
    }

    /**
     * product_id : 1
     * product_image : product-image/HcCBl5t3be0VCmSNpj1jzaNLiMX1elSZktHCpNDd.jpeg
     * brand_id : 2
     * parent_variant_id : 2
     * child_variant_id : 3
     * status_id : 1
     * gst_id : 1
     * seller_id : 2
     * is_product_shipping : 0
     * track_inventory : 0
     * allow_customer_stock_out : 0
     * product_translation_id : 1
     * view_count : 1
     * created_at : 2018-08-10 17:08:59
     * updated_at : 2018-08-10 17:30:10
     * sku : {"sku_id":1,"product_id":1,"parent_variant_id":2,"child_variant_id":2,"my_price":1099,"market_price":1199,"product_weight":0,"weight_unit":1,"quantity":0,"sku_name":null,"barcode":null,"created_at":"2018-08-10 17:09:01","updated_at":"2018-08-10 17:09:01","parent_variant":{"parent_variant_id":2,"parent_variant_translation_id":"2","created_at":"2018-08-10 17:09:00","updated_at":"2018-08-10 17:09:00","default_parent_variant_translation":{"parent_variant_translation_id":2,"parent_variant_id":2,"language_id":1,"parent_variant_name":"XL","created_at":"2018-08-10 17:09:00","updated_at":"2018-08-10 17:09:00"},"parent_variant_translation":{"parent_variant_translation_id":10,"parent_variant_id":2,"language_id":2,"parent_variant_name":"XL hn","created_at":"2018-08-10 17:27:49","updated_at":"2018-08-10 17:27:49"}},"child_variant":{"child_variant_id":2,"child_variant_translation_id":"2","created_at":"2018-08-10 17:09:00","updated_at":"2018-08-10 17:09:00","default_child_variant_translation":{"child_variant_translation_id":2,"child_variant_id":2,"language_id":1,"child_variant_name":"RED","created_at":"2018-08-10 17:09:00","updated_at":"2018-08-10 17:09:00"},"child_variant_translation":{"child_variant_translation_id":6,"child_variant_id":2,"language_id":2,"child_variant_name":"RED hn","created_at":"2018-08-10 17:29:11","updated_at":"2018-08-10 17:29:11"}},"cart":{"cart_id":1,"quantity":1,"user":"2","sku_id":1,"source_id":1,"created_at":"2018-08-10 17:30:13","updated_at":"2018-08-10 17:30:13"}}
     * default_product_translation : {"product_translation_id":1,"language_id":1,"product_id":1,"product_name":"T Shirt - VARIENT","description":"<p>DESC<\/p>","meta_title":"","meta_keywords":"","meta_description":"","product_url":"t-shirt-varient","delivery_message":"FREE DELIVERY in 2 days","created_at":"2018-08-10 17:08:59","updated_at":"2018-08-10 17:14:12"}
     * product_translation : {"product_translation_id":2,"language_id":2,"product_id":1,"product_name":"T-Shirt hn","description":"Description hn","meta_title":"","meta_keywords":"","meta_description":"","product_url":"t-shirt-hn","delivery_message":"FREE DELIVERY in 2 days hn","created_at":"2018-08-10 17:12:58","updated_at":"2018-08-10 17:25:21"}
     * brand : {"brand_id":2,"brand_translation_id":"2","is_featured_brand":0,"created_at":"2018-08-10 17:17:19","updated_at":"2018-08-10 17:17:19","default_brand_translation":{"brand_translation_id":2,"brand_id":2,"language_id":1,"brand_name":"Lee Cooper","brand_image":"brand-image/AbHzEvZ9AN9sTElHaBd03HhWjS8D0ZTqrZf3jFKZ.png","created_at":"2018-08-10 17:17:19","updated_at":"2018-08-10 17:19:33"},"brand_translation":{"brand_translation_id":4,"brand_id":2,"language_id":2,"brand_name":"LEE COOPER HINDI","brand_image":null,"created_at":"2018-08-10 17:21:33","updated_at":"2018-08-10 17:21:33"}}
     * wishlist : {"wishlist_id":1,"product_id":1,"user":"2","source_id":1,"created_at":"2018-08-10 17:30:17","updated_at":"2018-08-10 17:30:17"}
     * review_count": {"product_id": 1,"rating_count": 1,"rating": 5}
     * */

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
    private SkuCountBean sku_count;
    private SkuBean sku;
    private DefaultProductTranslationBean default_product_translation;
    private ProductTranslationBean product_translation;
    private DefaultProductTranslationBean default_product_translation_full;
    private FullProductTranslationBean product_translation_full;
    private BrandBean brand;
    private ReviewCountBean review_count;


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

    public SkuBean getSku() {
        return sku;
    }

    public void setSku(SkuBean sku) {
        this.sku = sku;
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

    public ReviewCountBean getReview_count() {
        return review_count;
    }

    public void setReview_count(ReviewCountBean review_count) {
        this.review_count = review_count;
    }

    public SkuCountBean getSku_count() {
        return sku_count;
    }

    public void setSku_count(SkuCountBean sku_count) {
        this.sku_count = sku_count;
    }

    public static class SkuCountBean {
        /**
         * product_id : 5
         * sku_count : 9
         */

        @SerializedName("product_id")
        private int product_idX;
        private int sku_count;

        public int getProduct_idX() {
            return product_idX;
        }

        public void setProduct_idX(int product_idX) {
            this.product_idX = product_idX;
        }

        public int getSku_count() {
            return sku_count;
        }

        public void setSku_count(int sku_count) {
            this.sku_count = sku_count;
        }
    }

    public static class SkuBean {
        /**
         * sku_id : 1
         * product_id : 1
         * parent_variant_id : 2
         * child_variant_id : 2
         * my_price : 1099
         * market_price : 1199
         * product_weight : 0
         * weight_unit : 1
         * quantity : 0
         * sku_name : null
         * barcode : null
         * created_at : 2018-08-10 17:09:01
         * updated_at : 2018-08-10 17:09:01
         * parent_variant : {"parent_variant_id":2,"parent_variant_translation_id":"2","created_at":"2018-08-10 17:09:00","updated_at":"2018-08-10 17:09:00","default_parent_variant_translation":{"parent_variant_translation_id":2,"parent_variant_id":2,"language_id":1,"parent_variant_name":"XL","created_at":"2018-08-10 17:09:00","updated_at":"2018-08-10 17:09:00"},"parent_variant_translation":{"parent_variant_translation_id":10,"parent_variant_id":2,"language_id":2,"parent_variant_name":"XL hn","created_at":"2018-08-10 17:27:49","updated_at":"2018-08-10 17:27:49"}}
         * child_variant : {"child_variant_id":2,"child_variant_translation_id":"2","created_at":"2018-08-10 17:09:00","updated_at":"2018-08-10 17:09:00","default_child_variant_translation":{"child_variant_translation_id":2,"child_variant_id":2,"language_id":1,"child_variant_name":"RED","created_at":"2018-08-10 17:09:00","updated_at":"2018-08-10 17:09:00"},"child_variant_translation":{"child_variant_translation_id":6,"child_variant_id":2,"language_id":2,"child_variant_name":"RED hn","created_at":"2018-08-10 17:29:11","updated_at":"2018-08-10 17:29:11"}}
         * cart : {"cart_id":1,"quantity":1,"user":"2","sku_id":1,"source_id":1,"created_at":"2018-08-10 17:30:13","updated_at":"2018-08-10 17:30:13"}
         */

        private int sku_id;
        private int product_id;
        private int parent_variant_id;
        private int child_variant_id;
        private double my_price;
        private double market_price;
        private double product_weight;
        private int weight_unit;
        private int quantity;
        private String sku_name;
        private String barcode;
        private String created_at;
        private String updated_at;
        private ParentVariantBean parent_variant;
        private ChildVariantBean child_variant;
        private CartBean cart;
        private WishlistBean wishlist;


        /**
         * sku_count : {"product_id":5,"sku_count":9}
         */

        public int getSku_id() {
            return sku_id;
        }

        public void setSku_id(int sku_id) {
            this.sku_id = sku_id;
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

        public double getMy_price() {
            return my_price;
        }

        public void setMy_price(double my_price) {
            this.my_price = my_price;
        }

        public double getMarket_price() {
            return market_price;
        }

        public void setMarket_price(double market_price) {
            this.market_price = market_price;
        }

        public double getProduct_weight() {
            return product_weight;
        }

        public void setProduct_weight(double product_weight) {
            this.product_weight = product_weight;
        }

        public int getWeight_unit() {
            return weight_unit;
        }

        public void setWeight_unit(int weight_unit) {
            this.weight_unit = weight_unit;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getSku_name() {
            return sku_name;
        }

        public void setSku_name(String sku_name) {
            this.sku_name = sku_name;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
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

        public WishlistBean getWishlist() {
            return wishlist;
        }

        public void setWishlist(WishlistBean wishlist) {
            this.wishlist = wishlist;
        }

        public CartBean getCart() {
            return cart;
        }

        public void setCart(CartBean cart) {
            this.cart = cart;
        }

        public static class ParentVariantBean {
            /**
             * parent_variant_id : 2
             * parent_variant_translation_id : 2
             * created_at : 2018-08-10 17:09:00
             * updated_at : 2018-08-10 17:09:00
             * default_parent_variant_translation : {"parent_variant_translation_id":2,"parent_variant_id":2,"language_id":1,"parent_variant_name":"XL","created_at":"2018-08-10 17:09:00","updated_at":"2018-08-10 17:09:00"}
             * parent_variant_translation : {"parent_variant_translation_id":10,"parent_variant_id":2,"language_id":2,"parent_variant_name":"XL hn","created_at":"2018-08-10 17:27:49","updated_at":"2018-08-10 17:27:49"}
             */

            private int parent_variant_id;
            private String parent_variant_translation_id;
            private String created_at;
            private String updated_at;
            private DefaultParentVariantTranslationBean default_parent_variant_translation;
            private ParentVariantTranslationBean parent_variant_translation;

            public int getParent_variant_id() {
                return parent_variant_id;
            }

            public void setParent_variant_id(int parent_variant_id) {
                this.parent_variant_id = parent_variant_id;
            }

            public String getParent_variant_translation_id() {
                return parent_variant_translation_id;
            }

            public void setParent_variant_translation_id(String parent_variant_translation_id) {
                this.parent_variant_translation_id = parent_variant_translation_id;
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
            /* public ParentVariantTranslationBean getParent_variant_translation() {
                return parent_variant_translation;
            }

            public void setParent_variant_translation(ParentVariantTranslationBean parent_variant_translation) {
                this.parent_variant_translation = parent_variant_translation;
            }*/

            public static class DefaultParentVariantTranslationBean {
                /**
                 * parent_variant_translation_id : 2
                 * parent_variant_id : 2
                 * language_id : 1
                 * parent_variant_name : XL
                 * created_at : 2018-08-10 17:09:00
                 * updated_at : 2018-08-10 17:09:00
                 */

                private int parent_variant_translation_id;
                private int parent_variant_id;
                private int language_id;
                private String parent_variant_name;
                private String created_at;
                private String updated_at;

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

            public static class ParentVariantTranslationBean {
                /**
                 * parent_variant_translation_id : 10
                 * parent_variant_id : 2
                 * language_id : 2
                 * parent_variant_name : XL hn
                 * created_at : 2018-08-10 17:27:49
                 * updated_at : 2018-08-10 17:27:49
                 */

                private int parent_variant_translation_id;
                private int parent_variant_id;
                private int language_id;
                public String parent_variant_name;
                private String created_at;
                private String updated_at;

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

        public static class ChildVariantBean {
            /**
             * child_variant_id : 2
             * child_variant_translation_id : 2
             * created_at : 2018-08-10 17:09:00
             * updated_at : 2018-08-10 17:09:00
             * default_child_variant_translation : {"child_variant_translation_id":2,"child_variant_id":2,"language_id":1,"child_variant_name":"RED","created_at":"2018-08-10 17:09:00","updated_at":"2018-08-10 17:09:00"}
             * child_variant_translation : {"child_variant_translation_id":6,"child_variant_id":2,"language_id":2,"child_variant_name":"RED hn","created_at":"2018-08-10 17:29:11","updated_at":"2018-08-10 17:29:11"}
             */

            private int child_variant_id;
            private String child_variant_translation_id;
            private String created_at;
            private String updated_at;
            private DefaultChildVariantTranslationBean default_child_variant_translation;
            private ChildVariantTranslationBean child_variant_translation;

            public int getChild_variant_id() {
                return child_variant_id;
            }

            public void setChild_variant_id(int child_variant_id) {
                this.child_variant_id = child_variant_id;
            }

            public String getChild_variant_translation_id() {
                return child_variant_translation_id;
            }

            public void setChild_variant_translation_id(String child_variant_translation_id) {
                this.child_variant_translation_id = child_variant_translation_id;
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
                 * child_variant_translation_id : 2
                 * child_variant_id : 2
                 * language_id : 1
                 * child_variant_name : RED
                 * created_at : 2018-08-10 17:09:00
                 * updated_at : 2018-08-10 17:09:00
                 */

                private int child_variant_translation_id;
                private int child_variant_id;
                private int language_id;
                private String child_variant_name;
                private String created_at;
                private String updated_at;

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

            public static class ChildVariantTranslationBean {
                /**
                 * child_variant_translation_id : 6
                 * child_variant_id : 2
                 * language_id : 2
                 * child_variant_name : RED hn
                 * created_at : 2018-08-10 17:29:11
                 * updated_at : 2018-08-10 17:29:11
                 */

                private int child_variant_translation_id;
                private int child_variant_id;
                private int language_id;
                private String child_variant_name;
                private String created_at;
                private String updated_at;

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

        public static class CartBean {
            /**
             * cart_id : 1
             * quantity : 1
             * user : 2
             * sku_id : 1
             * source_id : 1
             * created_at : 2018-08-10 17:30:13
             * updated_at : 2018-08-10 17:30:13
             */

            private int cart_id;
            private int quantity;
            private String user;
            private int sku_id;
            private int source_id;
            private String created_at;
            private String updated_at;

            public int getCart_id() {
                return cart_id;
            }

            public void setCart_id(int cart_id) {
                this.cart_id = cart_id;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public int getSku_id() {
                return sku_id;
            }

            public void setSku_id(int sku_id) {
                this.sku_id = sku_id;
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
        }
    }

    public static class DefaultProductTranslationBean {
        /**
         * product_translation_id : 1
         * language_id : 1
         * product_id : 1
         * product_name : T Shirt - VARIENT
         * description : <p>DESC</p>
         * meta_title :
         * meta_keywords :
         * meta_description :
         * product_url : t-shirt-varient
         * delivery_message : FREE DELIVERY in 2 days
         * created_at : 2018-08-10 17:08:59
         * updated_at : 2018-08-10 17:14:12
         */

        private int product_translation_id;
        private int language_id;
        private int product_id;
        private String product_name;
        private String description;
        private String meta_title;
        private String meta_keywords;
        private String meta_description;
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

        public String getMeta_title() {
            return meta_title;
        }

        public void setMeta_title(String meta_title) {
            this.meta_title = meta_title;
        }

        public String getMeta_keywords() {
            return meta_keywords;
        }

        public void setMeta_keywords(String meta_keywords) {
            this.meta_keywords = meta_keywords;
        }

        public String getMeta_description() {
            return meta_description;
        }

        public void setMeta_description(String meta_description) {
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
         * product_translation_id : 2
         * language_id : 2
         * product_id : 1
         * product_name : T-Shirt hn
         * description : Description hn
         * meta_title :
         * meta_keywords :
         * meta_description :
         * product_url : t-shirt-hn
         * delivery_message : FREE DELIVERY in 2 days hn
         * created_at : 2018-08-10 17:12:58
         * updated_at : 2018-08-10 17:25:21
         */

        private int product_translation_id;
        private int language_id;
        private int product_id;
        private String product_name;
        private String description;
        private String meta_title;
        private String meta_keywords;
        private String meta_description;
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

        public String getMeta_title() {
            return meta_title;
        }

        public void setMeta_title(String meta_title) {
            this.meta_title = meta_title;
        }

        public String getMeta_keywords() {
            return meta_keywords;
        }

        public void setMeta_keywords(String meta_keywords) {
            this.meta_keywords = meta_keywords;
        }

        public String getMeta_description() {
            return meta_description;
        }

        public void setMeta_description(String meta_description) {
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

    public static class FullProductTranslationBean {
        /**
         * product_translation_id : 2
         * language_id : 2
         * product_id : 1
         * product_name : T-Shirt hn
         * description : Description hn
         * meta_title :
         * meta_keywords :
         * meta_description :
         * product_url : t-shirt-hn
         * delivery_message : FREE DELIVERY in 2 days hn
         * created_at : 2018-08-10 17:12:58
         * updated_at : 2018-08-10 17:25:21
         */

        private int product_translation_id;
        private int language_id;
        private int product_id;
        private String product_name;
        private String description;
        private String meta_title;
        private String meta_keywords;
        private String meta_description;
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

        public String getMeta_title() {
            return meta_title;
        }

        public void setMeta_title(String meta_title) {
            this.meta_title = meta_title;
        }

        public String getMeta_keywords() {
            return meta_keywords;
        }

        public void setMeta_keywords(String meta_keywords) {
            this.meta_keywords = meta_keywords;
        }

        public String getMeta_description() {
            return meta_description;
        }

        public void setMeta_description(String meta_description) {
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

    public static class BrandBean {
        /**
         * brand_id : 2
         * brand_translation_id : 2
         * is_featured_brand : 0
         * created_at : 2018-08-10 17:17:19
         * updated_at : 2018-08-10 17:17:19
         * default_brand_translation : {"brand_translation_id":2,"brand_id":2,"language_id":1,"brand_name":"Lee Cooper","brand_image":"brand-image/AbHzEvZ9AN9sTElHaBd03HhWjS8D0ZTqrZf3jFKZ.png","created_at":"2018-08-10 17:17:19","updated_at":"2018-08-10 17:19:33"}
         * brand_translation : {"brand_translation_id":4,"brand_id":2,"language_id":2,"brand_name":"LEE COOPER HINDI","brand_image":null,"created_at":"2018-08-10 17:21:33","updated_at":"2018-08-10 17:21:33"}
         */

        private int brand_id;
        private String brand_translation_id;
        private int is_featured_brand;
        private String created_at;
        private String updated_at;
        private DefaultBrandTranslationBean default_brand_translation;
        private BrandTranslationBean brand_translation;

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

        public int getIs_featured_brand() {
            return is_featured_brand;
        }

        public void setIs_featured_brand(int is_featured_brand) {
            this.is_featured_brand = is_featured_brand;
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

        public static class DefaultBrandTranslationBean {
            /**
             * brand_translation_id : 2
             * brand_id : 2
             * language_id : 1
             * brand_name : Lee Cooper
             * brand_image : brand-image/AbHzEvZ9AN9sTElHaBd03HhWjS8D0ZTqrZf3jFKZ.png
             * created_at : 2018-08-10 17:17:19
             * updated_at : 2018-08-10 17:19:33
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
             * brand_translation_id : 4
             * brand_id : 2
             * language_id : 2
             * brand_name : LEE COOPER HINDI
             * brand_image : null
             * created_at : 2018-08-10 17:21:33
             * updated_at : 2018-08-10 17:21:33
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
    }

    public static class WishlistBean {
        /**
         * wishlist_id : 1
         * product_id : 1
         * user : 2
         * source_id : 1
         * created_at : 2018-08-10 17:30:17
         * updated_at : 2018-08-10 17:30:17
         */

        private int wishlist_id;
        private int product_id;
        private String user;
        private int source_id;
        private String created_at;
        private String updated_at;

        public int getWishlist_id() {
            return wishlist_id;
        }

        public void setWishlist_id(int wishlist_id) {
            this.wishlist_id = wishlist_id;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
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
    }

    public static class ReviewCountBean {

        /**
         * product_id : 1
         * rating_count : 1
         * rating : 5
         */

        private int product_id;
        private int rating_count;
        private double rating;

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public int getRating_count() {
            return rating_count;
        }

        public void setRating_count(int rating_count) {
            this.rating_count = rating_count;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }
    }
}
