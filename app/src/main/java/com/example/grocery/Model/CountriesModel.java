package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 1/13/2018.
 */

public class CountriesModel {

    /**
     * country_id : 2
     * country_translation_id : 2
     * is_active : 1
     * created_at : 2018-01-13 20:35:28
     * updated_at : 2018-01-13 20:35:28
     * default_country_translation : {"country_translation_id":2,"country_id":2,"language_id":1,"country_name":"India","created_at":"2018-01-13 20:35:28","updated_at":"2018-01-13 20:35:28"}
     * country_translation : {"country_translation_id":2,"country_id":2,"language_id":1,"country_name":"India","created_at":"2018-01-13 20:35:28","updated_at":"2018-01-13 20:35:28"}
     */

    private int country_id;
    private int country_translation_id;
    private int is_active;
    private String created_at;
    private String updated_at;
    private DefaultCountryTranslationBean default_country_translation;
    private CountryTranslationBean country_translation;

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getCountry_translation_id() {
        return country_translation_id;
    }

    public void setCountry_translation_id(int country_translation_id) {
        this.country_translation_id = country_translation_id;
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

    public DefaultCountryTranslationBean getDefault_country_translation() {
        return default_country_translation;
    }

    public void setDefault_country_translation(DefaultCountryTranslationBean default_country_translation) {
        this.default_country_translation = default_country_translation;
    }

    public CountryTranslationBean getCountry_translation() {
        return country_translation;
    }

    public void setCountry_translation(CountryTranslationBean country_translation) {
        this.country_translation = country_translation;
    }

    public static class DefaultCountryTranslationBean {
        /**
         * country_translation_id : 2
         * country_id : 2
         * language_id : 1
         * country_name : India
         * created_at : 2018-01-13 20:35:28
         * updated_at : 2018-01-13 20:35:28
         */

        private int country_translation_id;
        private int country_id;
        private int language_id;
        private String country_name;
        private String created_at;
        private String updated_at;

        public int getCountry_translation_id() {
            return country_translation_id;
        }

        public void setCountry_translation_id(int country_translation_id) {
            this.country_translation_id = country_translation_id;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
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

    public static class CountryTranslationBean {
        /**
         * country_translation_id : 2
         * country_id : 2
         * language_id : 1
         * country_name : India
         * created_at : 2018-01-13 20:35:28
         * updated_at : 2018-01-13 20:35:28
         */

        private int country_translation_id;
        private int country_id;
        private int language_id;
        private String country_name;
        private String created_at;
        private String updated_at;

        public int getCountry_translation_id() {
            return country_translation_id;
        }

        public void setCountry_translation_id(int country_translation_id) {
            this.country_translation_id = country_translation_id;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
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
