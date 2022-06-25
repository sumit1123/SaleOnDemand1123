package com.example.grocery.Model;

/**
 * Created by Administrator on 9/15/2017.
 */

public class AllCategoriesModel {
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * category_id : 1
     * category_image : null
     * parent_category_id : 0
     * is_active : 1
     * is_parent_active : 1
     * is_parent_category : 0
     * created_at : null
     * updated_at : null
     * category_translation_id : 1
     * default_category_translation : {"category_translation_id":1,"category_id":1,"language_id":1,"category_name":"En_category1","created_at":null,"updated_at":null,"language":{"language_id":1,"language_code":"en","language_name":"English","is_active":"1","created_at":null,"updated_at":null}}
     * category_translation : {"category_translation_id":1,"category_id":1,"language_id":1,"category_name":"En_category1","created_at":null,"updated_at":null}
     */

private String categoryName;
    private int category_id;
    private Object category_image;
    private int parent_category_id;
    private int is_active;
    private int is_parent_active;
    private int is_parent_category;
    private Object created_at;
    private Object updated_at;
    private int category_translation_id;
    private DefaultCategoryTranslationBean default_category_translation;
    private CategoryTranslationBean category_translation;

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Object getCategory_image() {
        return category_image;
    }

    public void setCategory_image(Object category_image) {
        this.category_image = category_image;
    }

    public int getParent_category_id() {
        return parent_category_id;
    }

    public void setParent_category_id(int parent_category_id) {
        this.parent_category_id = parent_category_id;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public int getIs_parent_active() {
        return is_parent_active;
    }

    public void setIs_parent_active(int is_parent_active) {
        this.is_parent_active = is_parent_active;
    }

    public int getIs_parent_category() {
        return is_parent_category;
    }

    public void setIs_parent_category(int is_parent_category) {
        this.is_parent_category = is_parent_category;
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

    public int getCategory_translation_id() {
        return category_translation_id;
    }

    public void setCategory_translation_id(int category_translation_id) {
        this.category_translation_id = category_translation_id;
    }

    public DefaultCategoryTranslationBean getDefault_category_translation() {
        return default_category_translation;
    }

    public void setDefault_category_translation(DefaultCategoryTranslationBean default_category_translation) {
        this.default_category_translation = default_category_translation;
    }

    public CategoryTranslationBean getCategory_translation() {
        return category_translation;
    }

    public void setCategory_translation(CategoryTranslationBean category_translation) {
        this.category_translation = category_translation;
    }

    public static class DefaultCategoryTranslationBean {
        /**
         * category_translation_id : 1
         * category_id : 1
         * language_id : 1
         * category_name : En_category1
         * created_at : null
         * updated_at : null
         * language : {"language_id":1,"language_code":"en","language_name":"English","is_active":"1","created_at":null,"updated_at":null}
         */

        private int category_translation_id;
        private int category_id;
        private int language_id;
        private String category_name;
        private Object created_at;
        private Object updated_at;
        private LanguageBean language;

        public int getCategory_translation_id() {
            return category_translation_id;
        }

        public void setCategory_translation_id(int category_translation_id) {
            this.category_translation_id = category_translation_id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
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

        public LanguageBean getLanguage() {
            return language;
        }

        public void setLanguage(LanguageBean language) {
            this.language = language;
        }

        public static class LanguageBean {
            /**
             * language_id : 1
             * language_code : en
             * language_name : English
             * is_active : 1
             * created_at : null
             * updated_at : null
             */

            private int language_id;
            private String language_code;
            private String language_name;
            private String is_active;
            private Object created_at;
            private Object updated_at;

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public String getLanguage_code() {
                return language_code;
            }

            public void setLanguage_code(String language_code) {
                this.language_code = language_code;
            }

            public String getLanguage_name() {
                return language_name;
            }

            public void setLanguage_name(String language_name) {
                this.language_name = language_name;
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
        }
    }

    public static class CategoryTranslationBean {
        /**
         * category_translation_id : 1
         * category_id : 1
         * language_id : 1
         * category_name : En_category1
         * created_at : null
         * updated_at : null
         */

        private int category_translation_id;
        private int category_id;
        private int language_id;
        private String category_name;
        private Object created_at;
        private Object updated_at;

        public int getCategory_translation_id() {
            return category_translation_id;
        }

        public void setCategory_translation_id(int category_translation_id) {
            this.category_translation_id = category_translation_id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
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
