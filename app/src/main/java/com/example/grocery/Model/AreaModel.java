package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 1/15/2018.
 */

public class AreaModel {

    /**
     * area_id : 2
     * area_translation_id : 2
     * pincode_id : 3
     * is_active : 1
     * created_at : 2018-01-15 09:48:45
     * updated_at : 2018-01-15 09:48:45
     * default_area_translation : {"area_translation_id":2,"area_id":2,"language_id":1,"area":"ChinchWad","created_at":"2018-01-15 09:48:45","updated_at":"2018-01-15 09:48:45"}
     * area_translation : {"area_translation_id":2,"area_id":2,"language_id":1,"area":"ChinchWad","created_at":"2018-01-15 09:48:45","updated_at":"2018-01-15 09:48:45"}
     */

    private int area_id;
    private int area_translation_id;
    private int pincode_id;
    private int is_active;
    private String created_at;
    private String updated_at;
    private DefaultAreaTranslationBean default_area_translation;
    private AreaTranslationBean area_translation;

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public int getArea_translation_id() {
        return area_translation_id;
    }

    public void setArea_translation_id(int area_translation_id) {
        this.area_translation_id = area_translation_id;
    }

    public int getPincode_id() {
        return pincode_id;
    }

    public void setPincode_id(int pincode_id) {
        this.pincode_id = pincode_id;
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

    public DefaultAreaTranslationBean getDefault_area_translation() {
        return default_area_translation;
    }

    public void setDefault_area_translation(DefaultAreaTranslationBean default_area_translation) {
        this.default_area_translation = default_area_translation;
    }

    public AreaTranslationBean getArea_translation() {
        return area_translation;
    }

    public void setArea_translation(AreaTranslationBean area_translation) {
        this.area_translation = area_translation;
    }

    public static class DefaultAreaTranslationBean {
        /**
         * area_translation_id : 2
         * area_id : 2
         * language_id : 1
         * area : ChinchWad
         * created_at : 2018-01-15 09:48:45
         * updated_at : 2018-01-15 09:48:45
         */

        private int area_translation_id;
        private int area_id;
        private int language_id;
        private String area;
        private String created_at;
        private String updated_at;

        public int getArea_translation_id() {
            return area_translation_id;
        }

        public void setArea_translation_id(int area_translation_id) {
            this.area_translation_id = area_translation_id;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
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

    public static class AreaTranslationBean {
        /**
         * area_translation_id : 2
         * area_id : 2
         * language_id : 1
         * area : ChinchWad
         * created_at : 2018-01-15 09:48:45
         * updated_at : 2018-01-15 09:48:45
         */

        private int area_translation_id;
        private int area_id;
        private int language_id;
        private String area;
        private String created_at;
        private String updated_at;

        public int getArea_translation_id() {
            return area_translation_id;
        }

        public void setArea_translation_id(int area_translation_id) {
            this.area_translation_id = area_translation_id;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
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
