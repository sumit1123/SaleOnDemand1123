package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 11/30/2017.
 */

public class ChildVariantModel {

    /**
     * child_variant_id : 2
     * created_at : 2017-12-27 22:16:06
     * updated_at : 2017-12-27 22:16:06
     * child_variant_translation_id : 3
     * default_child_variant_translation : {"child_variant_translation_id":3,"child_variant_id":2,"language_id":1,"child_variant_name":"child2 en","created_at":null,"updated_at":null}
     * child_variant_translation : null
     */

    private int child_variant_id;
    private String created_at;
    private String updated_at;
    private int child_variant_translation_id;
    private DefaultChildVariantTranslationBean default_child_variant_translation;
    private Object child_variant_translation;

    public int getChild_variant_id() {
        return child_variant_id;
    }

    public void setChild_variant_id(int child_variant_id) {
        this.child_variant_id = child_variant_id;
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

    public Object getChild_variant_translation() {
        return child_variant_translation;
    }

    public void setChild_variant_translation(Object child_variant_translation) {
        this.child_variant_translation = child_variant_translation;
    }

    public static class DefaultChildVariantTranslationBean {
        /**
         * child_variant_translation_id : 3
         * child_variant_id : 2
         * language_id : 1
         * child_variant_name : child2 en
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
