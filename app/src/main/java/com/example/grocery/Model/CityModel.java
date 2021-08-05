package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 1/13/2018.
 */

public class CityModel {

    /**
     * city_id : 2
     * state_id : 2
     * city_translation_id : 2
     * is_active : 1
     * created_at : 2018-01-13 16:05:10
     * updated_at : 2018-01-13 16:05:10
     * default_city_translation : {"city_translation_id":2,"city_id":2,"language_id":1,"city_name":"Pune","created_at":"2018-01-13 16:05:10","updated_at":"2018-01-13 16:05:10"}
     * city_translation : {"city_translation_id":2,"city_id":2,"language_id":1,"city_name":"Pune","created_at":"2018-01-13 16:05:10","updated_at":"2018-01-13 16:05:10"}
     */

    private int city_id;
    private int state_id;
    private String city_translation_id;
    private int is_active;
    private String created_at;
    private String updated_at;
    private DefaultCityTranslationBean default_city_translation;
    private CityTranslationBean city_translation;

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

    public String getCity_translation_id() {
        return city_translation_id;
    }

    public void setCity_translation_id(String city_translation_id) {
        this.city_translation_id = city_translation_id;
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

    public DefaultCityTranslationBean getDefault_city_translation() {
        return default_city_translation;
    }

    public void setDefault_city_translation(DefaultCityTranslationBean default_city_translation) {
        this.default_city_translation = default_city_translation;
    }

    public CityTranslationBean getCity_translation() {
        return city_translation;
    }

    public void setCity_translation(CityTranslationBean city_translation) {
        this.city_translation = city_translation;
    }

    public static class DefaultCityTranslationBean {
        /**
         * city_translation_id : 2
         * city_id : 2
         * language_id : 1
         * city_name : Pune
         * created_at : 2018-01-13 16:05:10
         * updated_at : 2018-01-13 16:05:10
         */

        private int city_translation_id;
        private int city_id;
        private int language_id;
        private String city_name;
        private String created_at;
        private String updated_at;

        public int getCity_translation_id() {
            return city_translation_id;
        }

        public void setCity_translation_id(int city_translation_id) {
            this.city_translation_id = city_translation_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
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

    public static class CityTranslationBean {
        /**
         * city_translation_id : 2
         * city_id : 2
         * language_id : 1
         * city_name : Pune
         * created_at : 2018-01-13 16:05:10
         * updated_at : 2018-01-13 16:05:10
         */

        private int city_translation_id;
        private int city_id;
        private int language_id;
        private String city_name;
        private String created_at;
        private String updated_at;

        public int getCity_translation_id() {
            return city_translation_id;
        }

        public void setCity_translation_id(int city_translation_id) {
            this.city_translation_id = city_translation_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
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
