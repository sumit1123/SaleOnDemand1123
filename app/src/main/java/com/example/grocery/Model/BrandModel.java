package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 9/29/2017.
 */

public class BrandModel {


    /**
     * brand_id : 2
     * created_at : 2017-12-27 17:48:49
     * updated_at : 2017-12-27 17:48:49
     * brand_translation_id : 4
     * default_brand_translation : {"brand_translation_id":4,"brand_id":2,"language_id":2,"brand_name":"brand2 mr","created_at":null,"updated_at":null}
     * brand_translation : {"brand_translation_id":3,"brand_id":2,"language_id":1,"brand_name":"brand2_en","created_at":null,"updated_at":null}
     */

    private int brand_id;
    private String created_at;
    private String updated_at;
    private int brand_translation_id;
    private DefaultBrandTranslationBean default_brand_translation;
    private BrandTranslationBean brand_translation;

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * brand_id : 0
     * brand_name : None Brand
     * is_deleted : 0
     * created_at : 2017-09-28 00:00:00
     * updated_at : 2017-09-28 00:00:00
     */

    private boolean isSelected;

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
         * brand_translation_id : 3
         * brand_id : 2
         * language_id : 1
         * brand_name : brand2_en
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
