package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 9/29/2017.
 */

public class PolicyModel {


    /**
     * policy_id : 1
     * created_at : null
     * updated_at : null
     * policy_translation_id : 1
     * default_policy_translation : {"policy_translation_id":1,"policy_id":1,"language_id":1,"policy_title":"asd en","policy_description":"asd en","created_at":null,"updated_at":null,"language":{"language_id":1,"language_code":"en","language_name":"English","is_active":"1","created_at":null,"updated_at":null}}
     * policy_translation : {"policy_translation_id":2,"policy_id":1,"language_id":2,"policy_title":"pol mr","policy_description":"pol mr","created_at":null,"updated_at":null,"language":{"language_id":2,"language_code":"ab","language_name":"Abkhazian","is_active":"1","created_at":null,"updated_at":null}}
     */

    private int policy_id;
    private Object created_at;
    private Object updated_at;
    private int policy_translation_id;
    private DefaultPolicyTranslationBean default_policy_translation;
    private PolicyTranslationBean policy_translation;

    public int getPolicy_id() {
        return policy_id;
    }

    public void setPolicy_id(int policy_id) {
        this.policy_id = policy_id;
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

    public int getPolicy_translation_id() {
        return policy_translation_id;
    }

    public void setPolicy_translation_id(int policy_translation_id) {
        this.policy_translation_id = policy_translation_id;
    }

    public DefaultPolicyTranslationBean getDefault_policy_translation() {
        return default_policy_translation;
    }

    public void setDefault_policy_translation(DefaultPolicyTranslationBean default_policy_translation) {
        this.default_policy_translation = default_policy_translation;
    }

    public PolicyTranslationBean getPolicy_translation() {
        return policy_translation;
    }

    public void setPolicy_translation(PolicyTranslationBean policy_translation) {
        this.policy_translation = policy_translation;
    }

    public static class DefaultPolicyTranslationBean {
        /**
         * policy_translation_id : 1
         * policy_id : 1
         * language_id : 1
         * policy_title : asd en
         * policy_description : asd en
         * created_at : null
         * updated_at : null
         * language : {"language_id":1,"language_code":"en","language_name":"English","is_active":"1","created_at":null,"updated_at":null}
         */

        private int policy_translation_id;
        private int policy_id;
        private int language_id;
        private String policy_title;
        private String policy_description;
        private Object created_at;
        private Object updated_at;
        private LanguageBean language;

        public int getPolicy_translation_id() {
            return policy_translation_id;
        }

        public void setPolicy_translation_id(int policy_translation_id) {
            this.policy_translation_id = policy_translation_id;
        }

        public int getPolicy_id() {
            return policy_id;
        }

        public void setPolicy_id(int policy_id) {
            this.policy_id = policy_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getPolicy_title() {
            return policy_title;
        }

        public void setPolicy_title(String policy_title) {
            this.policy_title = policy_title;
        }

        public String getPolicy_description() {
            return policy_description;
        }

        public void setPolicy_description(String policy_description) {
            this.policy_description = policy_description;
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

    public static class PolicyTranslationBean {
        /**
         * policy_translation_id : 2
         * policy_id : 1
         * language_id : 2
         * policy_title : pol mr
         * policy_description : pol mr
         * created_at : null
         * updated_at : null
         * language : {"language_id":2,"language_code":"ab","language_name":"Abkhazian","is_active":"1","created_at":null,"updated_at":null}
         */

        private int policy_translation_id;
        private int policy_id;
        private int language_id;
        private String policy_title;
        private String policy_description;
        private Object created_at;
        private Object updated_at;
        private LanguageBeanX language;

        public int getPolicy_translation_id() {
            return policy_translation_id;
        }

        public void setPolicy_translation_id(int policy_translation_id) {
            this.policy_translation_id = policy_translation_id;
        }

        public int getPolicy_id() {
            return policy_id;
        }

        public void setPolicy_id(int policy_id) {
            this.policy_id = policy_id;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getPolicy_title() {
            return policy_title;
        }

        public void setPolicy_title(String policy_title) {
            this.policy_title = policy_title;
        }

        public String getPolicy_description() {
            return policy_description;
        }

        public void setPolicy_description(String policy_description) {
            this.policy_description = policy_description;
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

        public LanguageBeanX getLanguage() {
            return language;
        }

        public void setLanguage(LanguageBeanX language) {
            this.language = language;
        }

        public static class LanguageBeanX {
            /**
             * language_id : 2
             * language_code : ab
             * language_name : Abkhazian
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
}
