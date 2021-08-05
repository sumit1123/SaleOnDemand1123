package com.example.grocery.Appearances;

import com.example.grocery.Labels.Label;

/**
 * Created by Mohd Afzal on 8/25/2018.
 */

public class ApplicationAppearances {
    /**
     * appearance_id : 1
     * language_id : 1
     * appearance_translation_id : 1
     * font_id : 1
     * app_back_color : 00044C
     * app_text_color : FFFFFF
     * text_color : 00044C
     * app_logo : logo.png
     * toast_back_color : DDDDDD
     * toast_text_color : 000000
     * is_show_product_count:0
     * app_status_bar_color : 00044C
     * business_location : https://www.google.co.in/maps/place/JMTIT+Technologies/@18.616025,73.7643,15z/data=!4m12!1m6!3m5!1s0x0:0xa5053d0f180c582e!2sJMTIT+Technologies!8m2!3d18.616025!4d73.7643!3m4!1s0x0:0xa5053d0f180c582e!8m2!3d18.616025!4d73.7643
     * contact_number : 9689698387
     * business_email : ketki@jmtit.com
     * show_product_type : 0
     * show_new_arrival_product : 1
     * show_recent_view_product : 1
     * show_featured_brands : 0
     * show_max_view_product : 1
     * show_cart_button : 0
     * show_grid_category : 1
     * is_redeem: 1
     * show_category_type : 1
     * is_website : 1
     * is_inventory : 0
     * is_multivendor : 1
     * is_variant : 1
     * is_wallet : 0
     * is_message : 0
     * is_otp : 1
     * is_slider : 1
     * is_slider_effect : 1
     * is_gst : 0
     * is_pincode : 0
     * is_area : 0
     * is_shipping : 0
     * is_subadmin : 0
     * is_vendor_calls: 1
     * is_multilanguage : 1
     * is_delivery_boy : 0
     * is_seo : 0
     * is_notification : 1
     * is_store_order : 0
     * is_offer_block : 0
     * is_social_authentication : 0
     * is_app : 0
     * is_help_chat : 0
     * default_rating : 4.0
     * msg_sender_id : KPSHOP
     * is_promocode : 0
     * min_order_amount : 0
     * is_refferal: 1
     * google : null
     * facebook : null
     * twitter : null
     * instagram: null
     * linkedin:null
     * youtube: null
     * is_create_order : 1
     * is_allow_guest : 0
     * is_business_directory : 1
     * favicon : favicon/favicon.png
     * created_at : 2018-08-24 17:59:21
     * updated_at : 2018-08-25 10:48:30
     * default_appearance_translation : {"appearance_translation_id":1,"appearance_id":1,"language_id":1,"app_name":"JMTIT","tag_line":"Think in new dimensions","currency":"\u20b9","meta_title":null,"meta_keywords":null,"meta_description":null,"product_url":null,"created_at":"2018-08-24 17:59:23","updated_at":"2018-08-24 18:03:25"}
     * appearance_translation : {"appearance_translation_id":1,"appearance_id":1,"language_id":1,"app_name":"JMTIT","tag_line":"Think in new dimensions","currency":"\u20b9","meta_title":null,"meta_keywords":null,"meta_description":null,"product_url":null,"created_at":"2018-08-24 17:59:23","updated_at":"2018-08-24 18:03:25"}
     */

    private int appearance_id;
    private int language_id;
    private int is_show_product_count;
    private int appearance_translation_id;
    private int font_id;
    private String app_back_color;
    private String app_text_color;
    private String text_color;
    private String app_logo;
    private String toast_back_color;
    private String toast_text_color;
    private String app_status_bar_color;
    private String business_location;
    private String contact_number;
    private String business_email;
    private int show_product_type;
    private int is_vendor_calls;
    private int show_new_arrival_product;
    private int show_recent_view_product;
    private int show_featured_brands;
    private int show_max_view_product;
    private int show_cart_button;
    private int show_grid_category;
    private int show_category_type;
    private int is_website;
    private int is_inventory;
    private int is_multivendor;
    private int is_variant;
    private int is_wallet;
    private int is_message;
    private int is_otp;
    private int is_slider;
    private int is_slider_effect;
    private int is_gst;
    private int is_pincode;
    private int is_area;
    private int is_shipping;
    private int is_subadmin;
    private int is_multilanguage;
    private int is_delivery_boy;
    private int is_seo;
    private int is_notification;
    private int is_store_order;
    private int is_offer_block;
    private int is_social_authentication;
    private int is_app;
    private int is_help_chat;
    private int is_refferal;
    private String default_rating;
    private String msg_sender_id;
    private int is_promocode;
    private int is_business_directory;
    private int min_order_amount;
    private String google;
    private String facebook;
    private String twitter;
    private String instagram;
    private String youtube;
    private String linkedin;
    private int is_create_order;
    private int is_allow_guest;
    private String favicon;
    private String created_at;
    private String updated_at;
    private int is_redeem;
    private DefaultAppearanceTranslationBean default_appearance_translation;
    private AppearanceTranslationBean appearance_translation;

    public int getAppearance_id() {
        return appearance_id;
    }

    public void setAppearance_id(int appearance_id) {
        this.appearance_id = Label.validInt(appearance_id);
    }

    public int getIs_refferal() { return is_refferal; }

    public void setIs_refferal(int is_refferal) { Label.validInt(is_refferal); }

    public String getYoutube() { return youtube; }

    public void setYoutube(String youtube) {  Label.validString(youtube); }

    public String getInstagram() { return instagram; }

    public void setInstagram(String instagram) { Label.validString(instagram); }

    public String getLinkedin() { return linkedin; }

    public void setLinkedin(String linkedin) { Label.validString(linkedin); }

    public int getIs_show_product_count() { return is_show_product_count; }

    public void setIs_show_product_count(int is_show_product_count) { this.is_show_product_count = Label.validInt(is_show_product_count); }

    public int getIs_redeem() { return Label.validInt(is_redeem); }

    public void setIs_redeem(int is_redeem) { this.is_redeem = is_redeem; }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = Label.validInt(language_id);
    }

    public int getAppearance_translation_id() {
        return appearance_translation_id;
    }

    public void setAppearance_translation_id(int appearance_translation_id) {
        this.appearance_translation_id = Label.validInt(appearance_translation_id);
    }

    public int getIs_business_directory() { return is_business_directory; }

    public void setIs_business_directory(int is_business_directory) { this.is_business_directory = Label.validInt(is_business_directory); }

    public int getIs_vendor_calls() { return is_vendor_calls; }

    public void setIs_vendor_calls(int is_vendor_calls) { this.is_vendor_calls =  Label.validInt(is_vendor_calls); }

    public int getFont_id() {
        return font_id;
    }

    public void setFont_id(int font_id) {
        this.font_id = Label.validInt(font_id);
    }

    public String getApp_back_color() {
        return app_back_color;
    }

    public void setApp_back_color(String app_back_color) {
        this.app_back_color = Label.validString(app_back_color);
    }

    public String getApp_text_color() {
        return app_text_color;
    }

    public void setApp_text_color(String app_text_color) {
        this.app_text_color = Label.validString(app_text_color);
    }

    public String getText_color() {
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = Label.validString(text_color);
    }

    public String getApp_logo() {
        return app_logo;
    }

    public void setApp_logo(String app_logo) {
        this.app_logo = Label.validString(app_logo);
    }

    public String getToast_back_color() {
        return toast_back_color;
    }

    public void setToast_back_color(String toast_back_color) {
        this.toast_back_color = Label.validString(toast_back_color);
    }

    public String getToast_text_color() {
        return toast_text_color;
    }

    public void setToast_text_color(String toast_text_color) {
        this.toast_text_color = Label.validString(toast_text_color);
    }

    public String getApp_status_bar_color() {
        return app_status_bar_color;
    }

    public void setApp_status_bar_color(String app_status_bar_color) {
        this.app_status_bar_color = Label.validString(app_status_bar_color);
    }

    public String getBusiness_location() {
        return business_location;
    }

    public void setBusiness_location(String business_location) {
        this.business_location = Label.validString(business_location);
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = Label.validString(contact_number);
    }

    public String getBusiness_email() {
        return business_email;
    }

    public void setBusiness_email(String business_email) {
        this.business_email = Label.validString(business_email);
    }

    public int getShow_product_type() {
        return show_product_type;
    }

    public void setShow_product_type(int show_product_type) {
        this.show_product_type = Label.validInt(show_product_type);
    }

    public int getShow_new_arrival_product() {
        return show_new_arrival_product;
    }

    public void setShow_new_arrival_product(int show_new_arrival_product) {
        this.show_new_arrival_product = Label.validInt(show_new_arrival_product);
    }

    public int getShow_recent_view_product() {
        return show_recent_view_product;
    }

    public void setShow_recent_view_product(int show_recent_view_product) {
        this.show_recent_view_product = Label.validInt(show_recent_view_product);
    }

    public int getShow_featured_brands() {
        return show_featured_brands;
    }

    public void setShow_featured_brands(int show_featured_brands) {
        this.show_featured_brands = Label.validInt(show_featured_brands);
    }

    public int getShow_max_view_product() {
        return show_max_view_product;
    }

    public void setShow_max_view_product(int show_max_view_product) {
        this.show_max_view_product = Label.validInt(show_max_view_product);
    }

    public int getShow_cart_button() {
        return show_cart_button;
    }

    public void setShow_cart_button(int show_cart_button) {
        this.show_cart_button = Label.validInt(show_cart_button);
    }

    public int getShow_grid_category() {
        return show_grid_category;
    }

    public void setShow_grid_category(int show_grid_category) {
        this.show_grid_category = Label.validInt(show_grid_category);
    }

    public int getShow_category_type() {
        return show_category_type;
    }

    public void setShow_category_type(int show_category_type) {
        this.show_category_type = Label.validInt(show_category_type);
    }

    public int getIs_website() {
        return is_website;
    }

    public void setIs_website(int is_website) {
        this.is_website = Label.validInt(is_website);
    }

    public int getIs_inventory() {
        return is_inventory;
    }

    public void setIs_inventory(int is_inventory) {
        this.is_inventory = Label.validInt(is_inventory);
    }

    public int getIs_multivendor() {
        return is_multivendor;
    }

    public void setIs_multivendor(int is_multivendor) {
        this.is_multivendor = Label.validInt(is_multivendor);
    }

    public int getIs_variant() {
        return is_variant;
    }

    public void setIs_variant(int is_variant) {
        this.is_variant = Label.validInt(is_variant);
    }

    public int getIs_wallet() {
        return is_wallet;
    }

    public void setIs_wallet(int is_wallet) {
        this.is_wallet = Label.validInt(is_wallet);
    }

    public int getIs_message() {
        return is_message;
    }

    public void setIs_message(int is_message) {
        this.is_message = Label.validInt(is_message);
    }

    public int getIs_otp() {
        return is_otp;
    }

    public void setIs_otp(int is_otp) {
        this.is_otp = Label.validInt(is_otp);
    }

    public int getIs_slider() {
        return is_slider;
    }

    public void setIs_slider(int is_slider) {
        this.is_slider = Label.validInt(is_slider);
    }

    public int getIs_slider_effect() {
        return is_slider_effect;
    }

    public void setIs_slider_effect(int is_slider_effect) {
        this.is_slider_effect = Label.validInt(is_slider_effect);
    }

    public int getIs_gst() {
        return is_gst;
    }

    public void setIs_gst(int is_gst) {
        this.is_gst = Label.validInt(is_gst);
    }

    public int getIs_pincode() {
        return is_pincode;
    }

    public void setIs_pincode(int is_pincode) {
        this.is_pincode = Label.validInt(is_pincode);
    }

    public int getIs_area() {
        return is_area;
    }

    public void setIs_area(int is_area) {
        this.is_area = Label.validInt(is_area);
    }

    public int getIs_shipping() {
        return is_shipping;
    }

    public void setIs_shipping(int is_shipping) {
        this.is_shipping = Label.validInt(is_shipping);
    }

    public int getIs_subadmin() {
        return is_subadmin;
    }

    public void setIs_subadmin(int is_subadmin) {
        this.is_subadmin = Label.validInt(is_subadmin);
    }

    public int getIs_multilanguage() {
        return is_multilanguage;
    }

    public void setIs_multilanguage(int is_multilanguage) {
        this.is_multilanguage = Label.validInt(is_multilanguage);
    }

    public int getIs_delivery_boy() {
        return is_delivery_boy;
    }

    public void setIs_delivery_boy(int is_delivery_boy) {
        this.is_delivery_boy = Label.validInt(is_delivery_boy);
    }

    public int getIs_seo() {
        return is_seo;
    }

    public void setIs_seo(int is_seo) {
        this.is_seo = Label.validInt(is_seo);
    }

    public int getIs_notification() {
        return is_notification;
    }

    public void setIs_notification(int is_notification) {
        this.is_notification = Label.validInt(is_notification);
    }

    public int getIs_store_order() {
        return is_store_order;
    }

    public void setIs_store_order(int is_store_order) {
        this.is_store_order = Label.validInt(is_store_order);
    }

    public int getIs_offer_block() {
        return is_offer_block;
    }

    public void setIs_offer_block(int is_offer_block) {
        this.is_offer_block = Label.validInt(is_offer_block);
    }

    public int getIs_social_authentication() {
        return is_social_authentication;
    }

    public void setIs_social_authentication(int is_social_authentication) {
        this.is_social_authentication = Label.validInt(is_social_authentication);
    }

    public int getIs_app() {
        return is_app;
    }

    public void setIs_app(int is_app) {
        this.is_app = Label.validInt(is_app);
    }

    public int getIs_help_chat() {
        return is_help_chat;
    }

    public void setIs_help_chat(int is_help_chat) {
        this.is_help_chat = Label.validInt(is_help_chat);
    }

    public String getDefault_rating() {
        return default_rating;
    }

    public void setDefault_rating(String default_rating) {
        this.default_rating = Label.validString(default_rating);
    }

    public String getMsg_sender_id() {
        return msg_sender_id;
    }

    public void setMsg_sender_id(String msg_sender_id) {
        this.msg_sender_id = Label.validString(msg_sender_id);
    }

    public int getIs_promocode() {
        return is_promocode;
    }

    public void setIs_promocode(int is_promocode) {
        this.is_promocode = Label.validInt(is_promocode);
    }

    public int getMin_order_amount() {
        return min_order_amount;
    }

    public void setMin_order_amount(int min_order_amount) {
        this.min_order_amount = Label.validInt(min_order_amount);
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = Label.validString(google);
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = Label.validString(facebook);
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = Label.validString(twitter);
    }

    public int getIs_create_order() {
        return is_create_order;
    }

    public void setIs_create_order(int is_create_order) {
        this.is_create_order = Label.validInt(is_create_order);
    }

    public int getIs_allow_guest() {
        return is_allow_guest;
    }

    public void setIs_allow_guest(int is_allow_guest) {
        this.is_allow_guest = Label.validInt(is_allow_guest);
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = Label.validString(favicon);
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = Label.validString(created_at);
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = Label.validString(updated_at);
    }

    public DefaultAppearanceTranslationBean getDefault_appearance_translation() {
        return default_appearance_translation;
    }

    public void setDefault_appearance_translation(DefaultAppearanceTranslationBean default_appearance_translation) {
        this.default_appearance_translation = default_appearance_translation;
    }

    public AppearanceTranslationBean getAppearance_translation() {
        return appearance_translation;
    }

    public void setAppearance_translation(AppearanceTranslationBean appearance_translation) {
        this.appearance_translation = appearance_translation;
    }

    public static class DefaultAppearanceTranslationBean {
        /**
         * appearance_translation_id : 1
         * appearance_id : 1
         * language_id : 1
         * app_name : JMTIT
         * tag_line : Think in new dimensions
         * currency : ₹
         * meta_title : null
         * meta_keywords : null
         * meta_description : null
         * product_url : null
         * created_at : 2018-08-24 17:59:23
         * updated_at : 2018-08-24 18:03:25
         */

        private int appearance_translation_id;
        private int appearance_id;
        private int language_id;
        private String app_name;
        private String tag_line;
        private String currency;
        private String meta_title;
        private String meta_keywords;
        private String meta_description;
        private String product_url;
        private String created_at;
        private String updated_at;


        public int getAppearance_translation_id() {
            return appearance_translation_id;
        }

        public void setAppearance_translation_id(int appearance_translation_id) {
            this.appearance_translation_id = Label.validInt(appearance_translation_id);
        }

        public int getAppearance_id() {
            return appearance_id;
        }

        public void setAppearance_id(int appearance_id) {
            this.appearance_id = Label.validInt(appearance_id);
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = Label.validInt(appearance_id);
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = Label.validString(app_name);
        }

        public String getTag_line() {
            return tag_line;
        }

        public void setTag_line(String tag_line) {
            this.tag_line = Label.validString(tag_line);
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = Label.validString(currency);
        }

        public String getMeta_title() {
            return meta_title;
        }

        public void setMeta_title(String meta_title) {
            this.meta_title = Label.validString(meta_title);
        }

        public String getMeta_keywords() {
            return meta_keywords;
        }

        public void setMeta_keywords(String meta_keywords) {
            this.meta_keywords = Label.validString(meta_keywords);
        }

        public String getMeta_description() {
            return meta_description;
        }

        public void setMeta_description(String meta_description) {
            this.meta_description = Label.validString(meta_description);
        }

        public String getProduct_url() {
            return product_url;
        }

        public void setProduct_url(String product_url) {
            this.product_url = Label.validString(product_url);
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = Label.validString(created_at);
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = Label.validString(updated_at);
        }
    }

    public static class AppearanceTranslationBean {
        /**
         * appearance_translation_id : 1
         * appearance_id : 1
         * language_id : 1
         * app_name : JMTIT
         * tag_line : Think in new dimensions
         * currency : ₹
         * meta_title : null
         * meta_keywords : null
         * meta_description : null
         * product_url : null
         * created_at : 2018-08-24 17:59:23
         * updated_at : 2018-08-24 18:03:25
         */

        private int appearance_translation_id;
        private int appearance_id;
        private int language_id;
        private String app_name;
        private String tag_line;
        private String currency;
        private String meta_title;
        private String meta_keywords;
        private String meta_description;
        private String product_url;
        private String created_at;
        private String updated_at;

        public AppearanceTranslationBean(DefaultAppearanceTranslationBean defaultAppearanceTranslationBean) {
            this.app_name = defaultAppearanceTranslationBean.app_name;
            this.app_name = defaultAppearanceTranslationBean.app_name;
            this.tag_line = defaultAppearanceTranslationBean.tag_line;
            this.currency = defaultAppearanceTranslationBean.currency;
            this.meta_title = defaultAppearanceTranslationBean.meta_title;
            this.meta_keywords = defaultAppearanceTranslationBean.meta_keywords;
            this.meta_description = defaultAppearanceTranslationBean.meta_description;
            this.product_url = defaultAppearanceTranslationBean.product_url;
            this.created_at = defaultAppearanceTranslationBean.created_at;
            this.updated_at = defaultAppearanceTranslationBean.updated_at;
        }

        public int getAppearance_translation_id() {
            return appearance_translation_id;
        }

        public void setAppearance_translation_id(int appearance_translation_id) {
            this.appearance_translation_id = Label.validInt(appearance_translation_id);
        }

        public int getAppearance_id() {
            return appearance_id;
        }

        public void setAppearance_id(int appearance_id) {
            this.appearance_id = Label.validInt(appearance_id);
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = Label.validInt(language_id);
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = Label.validString(app_name);
        }

        public String getTag_line() {
            return tag_line;
        }

        public void setTag_line(String tag_line) {
            this.tag_line = Label.validString(tag_line);
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = Label.validString(currency);
        }

        public String getMeta_title() {
            return meta_title;
        }

        public void setMeta_title(String meta_title) {
            this.meta_title = Label.validString(meta_title);
        }

        public String getMeta_keywords() {
            return meta_keywords;
        }

        public void setMeta_keywords(String meta_keywords) {
            this.meta_keywords = Label.validString(meta_keywords);
        }

        public String getMeta_description() {
            return meta_description;
        }

        public void setMeta_description(String meta_description) {
            this.meta_description = Label.validString(meta_description);
        }

        public String getProduct_url() {
            return product_url;
        }

        public void setProduct_url(String product_url) {
            this.product_url = Label.validString(product_url);
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = Label.validString(created_at);
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = Label.validString(updated_at);
        }
    }
}
