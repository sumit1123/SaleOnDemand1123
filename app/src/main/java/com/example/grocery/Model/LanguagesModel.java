package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 12/26/2017.
 */

public class LanguagesModel {

    /**
     * language_id : 1
     * language_translation_id : 1
     * alignment : ltr
     * language_code : en
     * is_active : 1
     * created_at : null
     * updated_at : null
     * default_language_translation : {"language_translation_id":1,"language_id":1,"language":1,"language_name":"English","created_at":null,"updated_at":null}
     * language_translation : {"language_translation_id":2,"language_id":1,"language":2,"language_name":"इंग्रजी","created_at":null,"updated_at":null}
     */

    private int language_id;
    private int language_translation_id;
    private String alignment;
    private String language_code;
    private String is_active;
    private Object created_at;
    private Object updated_at;
    private DefaultLanguageTranslationBean default_language_translation;
    private LanguageTranslationBean language_translation;

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public int getLanguage_translation_id() {
        return language_translation_id;
    }

    public void setLanguage_translation_id(int language_translation_id) {
        this.language_translation_id = language_translation_id;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
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

    public DefaultLanguageTranslationBean getDefault_language_translation() {
        return default_language_translation;
    }

    public void setDefault_language_translation(DefaultLanguageTranslationBean default_language_translation) {
        this.default_language_translation = default_language_translation;
    }

    public LanguageTranslationBean getLanguage_translation() {
        return language_translation;
    }

    public void setLanguage_translation(LanguageTranslationBean language_translation) {
        this.language_translation = language_translation;
    }

    public static class DefaultLanguageTranslationBean {
        /**
         * language_translation_id : 1
         * language_id : 1
         * language : 1
         * language_name : English
         * created_at : null
         * updated_at : null
         */

        private int language_translation_id;
        private int language_id;
        private int language;
        private String language_name;
        private Object created_at;
        private Object updated_at;

        public int getLanguage_translation_id() {
            return language_translation_id;
        }

        public void setLanguage_translation_id(int language_translation_id) {
            this.language_translation_id = language_translation_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public int getLanguage() {
            return language;
        }

        public void setLanguage(int language) {
            this.language = language;
        }

        public String getLanguage_name() {
            return language_name;
        }

        public void setLanguage_name(String language_name) {
            this.language_name = language_name;
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

    public static class LanguageTranslationBean {
        /**
         * language_translation_id : 2
         * language_id : 1
         * language : 2
         * language_name : इंग्रजी
         * created_at : null
         * updated_at : null
         */

        private int language_translation_id;
        private int language_id;
        private int language;
        private String language_name;
        private Object created_at;
        private Object updated_at;

        public int getLanguage_translation_id() {
            return language_translation_id;
        }

        public void setLanguage_translation_id(int language_translation_id) {
            this.language_translation_id = language_translation_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public int getLanguage() {
            return language;
        }

        public void setLanguage(int language) {
            this.language = language;
        }

        public String getLanguage_name() {
            return language_name;
        }

        public void setLanguage_name(String language_name) {
            this.language_name = language_name;
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
