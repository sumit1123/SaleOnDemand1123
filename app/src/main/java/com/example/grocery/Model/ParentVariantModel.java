package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 11/29/2017.
 */

public class ParentVariantModel {


    /**
     * parent_variant_id : 2
     * created_at : 2017-12-27 22:16:06
     * updated_at : 2017-12-27 22:16:06
     * parent_variant_translation_id : 2
     * default_parent_variant_translation : {"parent_variant_translation_id":2,"parent_variant_id":1,"language_id":2,"parent_variant_name":"2en","created_at":null,"updated_at":null}
     * parent_variant_translation : {"parent_variant_translation_id":4,"parent_variant_id":2,"language_id":2,"parent_variant_name":"4en","created_at":null,"updated_at":null}
     */

    private int variant_option_id;
    private String created_at;
    private String updated_at;
    private int variant_option_translation_id;
    private DefaultParentVariantTranslationBean default_variant_option_translation;
    private ParentVariantTranslationBean variant_option_translation;

    public int getParent_variant_id() {
        return variant_option_id;
    }

    public void setParent_variant_id(int parent_variant_id) {
        this.variant_option_id = parent_variant_id;
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

    public int getParent_variant_translation_id() {
        return variant_option_translation_id;
    }

    public void setParent_variant_translation_id(int parent_variant_translation_id) {
        this.variant_option_translation_id = parent_variant_translation_id;
    }

    public DefaultParentVariantTranslationBean getDefault_parent_variant_translation() {
        return default_variant_option_translation;
    }

    public void setDefault_parent_variant_translation(DefaultParentVariantTranslationBean default_parent_variant_translation) {
        this.default_variant_option_translation = default_parent_variant_translation;
    }

    public ParentVariantTranslationBean getParent_variant_translation() {
        return variant_option_translation;
    }

    public void setParent_variant_translation(ParentVariantTranslationBean parent_variant_translation) {
        this.variant_option_translation = parent_variant_translation;

    }

    public static class DefaultParentVariantTranslationBean {
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
        private String variant_option_name;
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
            return variant_option_name;
        }

        public void setParent_variant_name(String parent_variant_name) {
            this.variant_option_name = parent_variant_name;
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
         * parent_variant_translation_id : 4
         * parent_variant_id : 2
         * language_id : 2
         * parent_variant_name : 4en
         * created_at : null
         * updated_at : null
         */

        private int parent_variant_translation_id;
        private int parent_variant_id;
        private int language_id;
        private String variant_option_name;
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
            return variant_option_name;
        }

        public void setParent_variant_name(String parent_variant_name) {
            this.variant_option_name = parent_variant_name;
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

        @Override
        public String toString() {
            return "ParentVariantTranslationBean{" +
                    "parent_variant_translation_id=" + parent_variant_translation_id +
                    ", parent_variant_id=" + parent_variant_id +
                    ", language_id=" + language_id +
                    ", parent_variant_name='" + variant_option_name + '\'' +
                    ", created_at=" + created_at +
                    ", updated_at=" + updated_at +
                    '}';
        }
    }
}


