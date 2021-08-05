package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 12/15/2017.
 */

public class AddressModel {

    /**
     * address_id : 1
     * user_id : 1
     * address : Dange Chowk, Pune
     * name : Super Admin
     * email : superadmin@kpshopy.com
     * contact_number : 9689698387
     * pincode_id : 1
     * city_id : 1
     * state_id : 1
     * country_id : 1
     * area_id : 1
     * created_at : null
     * updated_at : null
     * country : {"country_id":1,"country_translation_id":1,"is_active":0,"created_at":null,"updated_at":null,"default_country_translation":{"country_translation_id":1,"country_id":1,"language_id":1,"country_name":"Afghanistan","created_at":null,"updated_at":null},"country_translation":{"country_translation_id":1,"country_id":1,"language_id":1,"country_name":"Afghanistan","created_at":null,"updated_at":null}}
     * state : {"state_id":1,"country_id":1,"is_active":1,"state_translation_id":1,"created_at":null,"updated_at":null,"default_state_translation":{"state_translation_id":1,"state_id":1,"language_id":1,"state_name":"","created_at":null,"updated_at":null},"state_translation":{"state_translation_id":1,"state_id":1,"language_id":1,"state_name":"","created_at":null,"updated_at":null}}
     * city : {"city_id":1,"state_id":1,"city_translation_id":"1","is_active":1,"created_at":null,"updated_at":null,"default_city_translation":{"city_translation_id":1,"city_id":1,"language_id":1,"city_name":"","created_at":null,"updated_at":null},"city_translation":{"city_translation_id":1,"city_id":1,"language_id":1,"city_name":"","created_at":null,"updated_at":null}}
     * pincode : {"pincode_id":1,"pincode":"","is_active":1,"created_at":null,"updated_at":null}
     */

    private int address_id;
    private int user_id;
    private String address;
    private String name;
    private String email;
    private String contact_number;
    private int pincode_id;
    private int city_id;
    private int state_id;
    private int country_id;
    private int area_id;
    private Object created_at;
    private Object updated_at;
    private CountryBean country;
    private StateBean state;
    private CityBean city;
    private PincodeBean pincode;

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public int getPincode_id() {
        return pincode_id;
    }

    public void setPincode_id(int pincode_id) {
        this.pincode_id = pincode_id;
    }

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

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
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

    public CountryBean getCountry() {
        return country;
    }

    public void setCountry(CountryBean country) {
        this.country = country;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }

    public PincodeBean getPincode() {
        return pincode;
    }

    public void setPincode(PincodeBean pincode) {
        this.pincode = pincode;
    }

    public static class CountryBean {
        /**
         * country_id : 1
         * country_translation_id : 1
         * is_active : 0
         * created_at : null
         * updated_at : null
         * default_country_translation : {"country_translation_id":1,"country_id":1,"language_id":1,"country_name":"Afghanistan","created_at":null,"updated_at":null}
         * country_translation : {"country_translation_id":1,"country_id":1,"language_id":1,"country_name":"Afghanistan","created_at":null,"updated_at":null}
         */

        private int country_id;
        private int country_translation_id;
        private int is_active;
        private Object created_at;
        private Object updated_at;
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
             * country_translation_id : 1
             * country_id : 1
             * language_id : 1
             * country_name : Afghanistan
             * created_at : null
             * updated_at : null
             */

            private int country_translation_id;
            private int country_id;
            private int language_id;
            private String country_name;
            private Object created_at;
            private Object updated_at;

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

        public static class CountryTranslationBean {
            /**
             * country_translation_id : 1
             * country_id : 1
             * language_id : 1
             * country_name : Afghanistan
             * created_at : null
             * updated_at : null
             */

            private int country_translation_id;
            private int country_id;
            private int language_id;
            private String country_name;
            private Object created_at;
            private Object updated_at;

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

    public static class StateBean {
        /**
         * state_id : 1
         * country_id : 1
         * is_active : 1
         * state_translation_id : 1
         * created_at : null
         * updated_at : null
         * default_state_translation : {"state_translation_id":1,"state_id":1,"language_id":1,"state_name":"","created_at":null,"updated_at":null}
         * state_translation : {"state_translation_id":1,"state_id":1,"language_id":1,"state_name":"","created_at":null,"updated_at":null}
         */

        private int state_id;
        private int country_id;
        private int is_active;
        private int state_translation_id;
        private Object created_at;
        private Object updated_at;
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
             * state_translation_id : 1
             * state_id : 1
             * language_id : 1
             * state_name :
             * created_at : null
             * updated_at : null
             */

            private int state_translation_id;
            private int state_id;
            private int language_id;
            private String state_name;
            private Object created_at;
            private Object updated_at;

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

        public static class StateTranslationBean {
            /**
             * state_translation_id : 1
             * state_id : 1
             * language_id : 1
             * state_name :
             * created_at : null
             * updated_at : null
             */

            private int state_translation_id;
            private int state_id;
            private int language_id;
            private String state_name;
            private Object created_at;
            private Object updated_at;

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

    public static class CityBean {
        /**
         * city_id : 1
         * state_id : 1
         * city_translation_id : 1
         * is_active : 1
         * created_at : null
         * updated_at : null
         * default_city_translation : {"city_translation_id":1,"city_id":1,"language_id":1,"city_name":"","created_at":null,"updated_at":null}
         * city_translation : {"city_translation_id":1,"city_id":1,"language_id":1,"city_name":"","created_at":null,"updated_at":null}
         */

        private int city_id;
        private int state_id;
        private String city_translation_id;
        private int is_active;
        private Object created_at;
        private Object updated_at;
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
             * city_translation_id : 1
             * city_id : 1
             * language_id : 1
             * city_name :
             * created_at : null
             * updated_at : null
             */

            private int city_translation_id;
            private int city_id;
            private int language_id;
            private String city_name;
            private Object created_at;
            private Object updated_at;

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

        public static class CityTranslationBean {
            /**
             * city_translation_id : 1
             * city_id : 1
             * language_id : 1
             * city_name :
             * created_at : null
             * updated_at : null
             */

            private int city_translation_id;
            private int city_id;
            private int language_id;
            private String city_name;
            private Object created_at;
            private Object updated_at;

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

    public static class PincodeBean {
        /**
         * pincode_id : 1
         * pincode :
         * is_active : 1
         * created_at : null
         * updated_at : null
         */

        private int pincode_id;
        private String pincode;
        private int is_active;
        private Object created_at;
        private Object updated_at;

        public int getPincode_id() {
            return pincode_id;
        }

        public void setPincode_id(int pincode_id) {
            this.pincode_id = pincode_id;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public int getIs_active() {
            return is_active;
        }

        public void setIs_active(int is_active) {
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
