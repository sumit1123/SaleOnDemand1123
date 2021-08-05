package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 1/13/2018.
 */

public class StateModel {


    /**
     * state_id : 2
     * country_id : 2
     * is_active : 1
     * state_translation_id : 2
     * created_at : 2018-01-13 16:03:31
     * updated_at : 2018-01-13 16:03:31
     * default_state_translation : {"state_translation_id":2,"state_id":2,"language_id":1,"state_name":"maharshtra","created_at":"2018-01-13 16:03:31","updated_at":"2018-01-13 16:03:31"}
     * state_translation : {"state_translation_id":2,"state_id":2,"language_id":1,"state_name":"maharshtra","created_at":"2018-01-13 16:03:31","updated_at":"2018-01-13 16:03:31"}
     */

    private int state_id;
    private int country_id;
    private int is_active;
    private int state_translation_id;
    private String created_at;
    private String updated_at;
    private DefaultStateTranslationBean default_state_translation;
    private StateTranslationBean state_translation;

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

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public int getState_translation_id() {
        return state_translation_id;
    }

    public void setState_translation_id(int state_translation_id) {
        this.state_translation_id = state_translation_id;
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

    public DefaultStateTranslationBean getDefault_state_translation() {
        return default_state_translation;
    }

    public void setDefault_state_translation(DefaultStateTranslationBean default_state_translation) {
        this.default_state_translation = default_state_translation;
    }

    public StateTranslationBean getState_translation() {
        return state_translation;
    }

    public void setState_translation(StateTranslationBean state_translation) {
        this.state_translation = state_translation;
    }

    public static class DefaultStateTranslationBean {
        /**
         * state_translation_id : 2
         * state_id : 2
         * language_id : 1
         * state_name : maharshtra
         * created_at : 2018-01-13 16:03:31
         * updated_at : 2018-01-13 16:03:31
         */

        private int state_translation_id;
        private int state_id;
        private int language_id;
        private String state_name;
        private String created_at;
        private String updated_at;

        public int getState_translation_id() {
            return state_translation_id;
        }

        public void setState_translation_id(int state_translation_id) {
            this.state_translation_id = state_translation_id;
        }

        public int getState_id() {
            return state_id;
        }

        public void setState_id(int state_id) {
            this.state_id = state_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getState_name() {
            return state_name;
        }

        public void setState_name(String state_name) {
            this.state_name = state_name;
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

    public static class StateTranslationBean {
        /**
         * state_translation_id : 2
         * state_id : 2
         * language_id : 1
         * state_name : maharshtra
         * created_at : 2018-01-13 16:03:31
         * updated_at : 2018-01-13 16:03:31
         */

        private int state_translation_id;
        private int state_id;
        private int language_id;
        private String state_name;
        private String created_at;
        private String updated_at;

        public int getState_translation_id() {
            return state_translation_id;
        }

        public void setState_translation_id(int state_translation_id) {
            this.state_translation_id = state_translation_id;
        }

        public int getState_id() {
            return state_id;
        }

        public void setState_id(int state_id) {
            this.state_id = state_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getState_name() {
            return state_name;
        }

        public void setState_name(String state_name) {
            this.state_name = state_name;
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
