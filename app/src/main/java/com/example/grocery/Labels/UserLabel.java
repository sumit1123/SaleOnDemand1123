package com.example.grocery.Labels;

/**
 * Created by Mohd Afzal on 7/24/2018.
 */

public class UserLabel {
    /**
     * user_label_id : 1
     * language_id : 1
     * change_password : Change Password
     * update_profile : Update Profile
     * my_addresses : My Addresses
     * login : Login
     * sign_up_with : Sign up with
     * or : or
     * sign_in : Sign In
     * sign_up : Sign Up
     * forgot_password : Forgot Password?
     * register : Register
     * new_password : New Password
     * confirm_password : Confirm Password
     * submit : SUBMIT
     * verification : Verification
     * otp_verification : OTP Verification
     * otp_msg : We have sent to an OTP via SMS on your mobile number
     * otp : OTP
     * resend_otp : RESEND OTP
     * retry : Retry
     * password_empty : Password can not be empty
     * pincode_empty : Pincode can not be empty
     * review_empty : Review can not be empty
     * contact_number_empty : Contact number can not be empty
     * please_enter_a_valid_number : Please enter a valid number
     * email_empty : Email can not be empty
     * dob_empty : Date of birth can not be empty
     * password_length_should_be_8_characters : Password length should be 8 charcters
     * address_empty : address can not be empty
     * please_enter_GST_number : Please enter GST number
     * description_empty : Description can not be empty
     * name_empty : Name can not be empty
     * created_at : 2018-07-23 18:37:56
     * updated_at : 2018-07-23 18:37:56
     * do_you_want_to_logout : Do you want to logout?
     * continue_as_guest : Continue as Guest
     * guest : Guest
     * vendor : Vendor
     * redeem : Redeem
     */

 /*   private int user_label_id;
    private int language_id;
    private String change_password;
    private String update_profile;
    private String my_addresses;
    private String login;
    private String vendor;
    private String contact_us;
    private String sign_up_with;
    private String or;
    private String sign_in;
    private String sign_up;
    private String forgot_password;
    private String register;
    private String new_password;
    private String confirm_password;
    private String submit;
    private String verification;
    private String otp_verification;
    private String otp_msg;
    private String otp;
    private String resend_otp;
    private String retry;
    private String password_empty;
    private String pincode_empty;
    private String review_empty;
    private String contact_number_empty;
    private String please_enter_a_valid_number;
    private String email_empty;
    private String dob_empty;
    private String password_length_should_be_8_characters;
    private String address_empty;
    private String please_enter_GST_number;
    private String description_empty;
    private String name_empty;
    private String created_at;
    private String updated_at;
    private String do_you_want_to_logout;
    private String continue_as_guest;
    private String guest;
    private String redeem;
    public int getUser_label_id() {
        return user_label_id;
    }

    public void setUser_label_id(int user_label_id) {
        this.user_label_id = Label.validInt(user_label_id);
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = Label.validInt(language_id);
    }

    public String getChange_password() {
        return change_password;
    }

    public void setChange_password(String change_password) {
        this.change_password = Label.validString(change_password);
    }

    public String getVendor() { return vendor; }

    public void setVendor(String vendor) { this.vendor = Label.validString(vendor); }

    public String getContact_us() {
        return contact_us;
    }

    public void setContact_us(String contact_us) {
        this.contact_us = Label.validString(contact_us);
    }

    public String getRedeem() { return redeem; }

    public void setRedeem(String redeem) { this.redeem = Label.validString(redeem); }

    public String getUpdate_profile() {
        return update_profile;
    }

    public void setUpdate_profile(String update_profile) {
        this.update_profile = Label.validString(update_profile);
    }




    public String getMy_addresses() {
        return my_addresses;
    }

    public void setMy_addresses(String my_addresses) {
        this.my_addresses = Label.validString(my_addresses);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = Label.validString(login);
    }

    public String getSign_up_with() {
        return sign_up_with;
    }

    public void setSign_up_with(String sign_up_with) {
        this.sign_up_with = Label.validString(sign_up_with);
    }

    public String getOr() {
        return or;
    }

    public void setOr(String or) {
        this.or = Label.validString(or);
    }

    public String getSign_in() {
        return sign_in;
    }

    public void setSign_in(String sign_in) {
        this.sign_in = Label.validString(sign_in);
    }

    public String getSign_up() {
        return sign_up;
    }

    public void setSign_up(String sign_up) {
        this.sign_up = Label.validString(sign_up);
    }



    public String getForgot_password() {
        return forgot_password;
    }

    public void setForgot_password(String forgot_password) {
        this.forgot_password = Label.validString(forgot_password);
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = Label.validString(register);
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = Label.validString(new_password);
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = Label.validString(confirm_password);
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = Label.validString(submit);
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = Label.validString(verification);
    }

    public String getOtp_verification() {
        return otp_verification;
    }

    public void setOtp_verification(String otp_verification) {
        this.otp_verification = Label.validString(otp_verification);
    }

    public String getOtp_msg() {
        return otp_msg;
    }

    public void setOtp_msg(String otp_msg) {
        this.otp_msg = Label.validString(otp_msg);
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = Label.validString(otp);
    }

    public String getResend_otp() {
        return resend_otp;
    }

    public void setResend_otp(String resend_otp) {
        this.resend_otp = Label.validString(resend_otp);
    }

    public String getRetry() {
        return retry;
    }

    public void setRetry(String retry) {
        this.retry = Label.validString(retry);
    }

    public String getPassword_empty() {
        return password_empty;
    }

    public void setPassword_empty(String password_empty) {
        this.password_empty = Label.validString(password_empty);
    }

    public String getPincode_empty() {
        return pincode_empty;
    }

    public void setPincode_empty(String pincode_empty) {
        this.pincode_empty = Label.validString(pincode_empty);
    }

    public String getReview_empty() {
        return review_empty;
    }

    public void setReview_empty(String review_empty) {
        this.review_empty = Label.validString(review_empty);
    }

    public String getContact_number_empty() {
        return contact_number_empty;
    }

    public void setContact_number_empty(String contact_number_empty) {
        this.contact_number_empty = Label.validString(contact_number_empty);
    }

    public String getPlease_enter_a_valid_number() {
        return please_enter_a_valid_number;
    }

    public void setPlease_enter_a_valid_number(String please_enter_a_valid_number) {
        this.please_enter_a_valid_number = Label.validString(please_enter_a_valid_number);
    }

    public String getEmail_empty() {
        return email_empty;
    }

    public void setEmail_empty(String email_empty) {
        this.email_empty = Label.validString(email_empty);
    }



    public String getDob_empty() {
        return dob_empty;
    }

    public void setDob_empty(String dob_empty) {
        this.dob_empty = Label.validString(dob_empty);
    }

    public String getPassword_length_should_be_8_characters() {
        return password_length_should_be_8_characters;
    }

    public void setPassword_length_should_be_8_characters(String password_length_should_be_8_characters) {
        this.password_length_should_be_8_characters = Label.validString(password_length_should_be_8_characters);
    }

    public String getAddress_empty() {
        return address_empty;
    }

    public void setAddress_empty(String address_empty) {
        this.address_empty = Label.validString(address_empty);
    }

    public String getPlease_enter_GST_number() {
        return please_enter_GST_number;
    }

    public void setPlease_enter_GST_number(String please_enter_GST_number) {
        this.please_enter_GST_number = Label.validString(please_enter_GST_number);
    }

    public String getDescription_empty() {
        return description_empty;
    }

    public void setDescription_empty(String description_empty) {
        this.description_empty = Label.validString(description_empty);
    }

    public String getName_empty() {
        return name_empty;
    }

    public void setName_empty(String name_empty) {
        this.name_empty = Label.validString(name_empty);
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = Label.validString(created_at);
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = Label.validString(updated_at);
    }

    public String getDo_you_want_to_logout() {
        return do_you_want_to_logout;
    }

    public void setDo_you_want_to_logout(String do_you_want_to_logout) {
        this.do_you_want_to_logout = Label.validString(do_you_want_to_logout);
    }

    public String getContinue_as_guest() {
        return continue_as_guest;
    }

    public void setContinue_as_guest(String continue_as_guest) {
        this.continue_as_guest = Label.validString(continue_as_guest);
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = Label.validString(guest);
    }
*/}
