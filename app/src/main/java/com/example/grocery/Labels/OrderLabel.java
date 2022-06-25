package com.example.grocery.Labels;

/**
 * Created by Mohd Afzal on 7/24/2018.
 */

public class OrderLabel {
    /**
     * order_label_id : 1
     * language_id : 1
     * delivery_details : Delivery Details
     * select_delivery_address : Select delivery address
     * new_address : New address
     * name : Name
     * mobile_number : Mobile Number
     * email : Email
     * password : Password
     * address : Address
     * pincode : Pincode
     * gst_number : GST Number
     * DOB : Date of Birth
     * amount : Amount
     * cash_on_delivery : CASH ON DELIVERY
     * pay_now : PAY NOW
     * order_id : Order ID
     * order_detail : Order Details
     * cancel_order : Cancel Order
     * need_help : Need Help?
     * shipping_details : SHIPPING DETAILS
     * item_count : Item Count
     * payment_mode : Payment Mode
     * order_cancel_msg : Do you really want to Cancel this order?
     * confirm : CONFIRM
     * ordered : Ordered
     * gst : GST
     * order_failed : Order Failed
     * order_failed_msg : Order failed due to product or quantity unavailable
     * order_success : Order Success
     * order_success_msg : Your order has been successfully placed
     * empty_order_title : Looks like you do not have any orders
     * empty_order_description : Add your order now
     * place_order : Place Order
     * total_amount_to_be_pay : Total amount to be pay
     * change_delivery_details : Change delivery details
     * yes : Yes
     * no : No
     * confirm_your_order : Please confirm your order
     * select : Select
     * please_enter_new_delivery_details : Please enter new delivery details
     * please_provide_rating : Please provide rating
     * change : Change
     * add_new_address : Add new address
     * select_country : Select country
     * select_state : Select state
     * select_city : Select city
     * select_pincode : Select pincode
     * select_area : Select area
     * change_pincode : Change pincode
     * please_select_country_from_list : Please select country from list
     * please_select_state_from_list : Please select state from list
     * please_select_city_from_list : Please select city from list
     * please_select_pincode_from_list : Please select pincode from list
     * please_select_area_from_list : Please select area from list
     * continue_with : Continue with
     * enter_delivery_pincode : Enter delivery pincode
     * enter_pincode : Enter pincode
     * created_at : 2018-07-24 16:10:18
     * updated_at : 2018-07-24 16:10:18
     */

 /*   private int order_label_id;
    private int language_id;
    private String delivery_details;
    private String select_delivery_address;
    private String new_address;
    private String name;
    private String mobile_number;
    private String email;
    private String password;
    private String address;
    private String pincode;
    private String gst_number;
    private String DOB;
    private String amount;
    private String cash_on_delivery;
    private String pay_now;
    private String order_id;
    private String order_detail;
    private String cancel_order;
    private String return_order;
    private String need_help;
    private String shipping_details;
    private String item_count;
    private String payment_mode;
    private String order_cancel_msg;
    private String confirm;
    private String ordered;
    private String gst;
    private String order_failed;
    private String order_failed_msg;
    private String order_success;
    private String order_success_msg;
    private String empty_order_title;
    private String empty_order_description;
    private String place_order;
    private String total_amount_to_be_pay;
    private String change_delivery_details;
    private String yes;
    private String no;
    private String confirm_your_order;
    private String select;
    private String please_enter_new_delivery_details;
    private String please_provide_rating;
    private String change;
    private String add_new_address;
    private String select_country;
    private String select_state;
    private String select_city;
    private String select_pincode;
    private String select_area;
    private String change_pincode;
    private String please_select_country_from_list;
    private String please_select_state_from_list;
    private String please_select_city_from_list;
    private String please_select_pincode_from_list;
    private String please_select_area_from_list;
    private String continue_with;
    private String enter_delivery_pincode;
    private String enter_pincode;
    private String created_at;
    private String updated_at;

    public int getOrder_label_id() {
        return order_label_id;
    }

    public void setOrder_label_id(int order_label_id) {
        this.order_label_id = Label.validInt(order_label_id);
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = Label.validInt(language_id);
    }

    public String getDelivery_details() {
        return delivery_details;
    }

    public void setDelivery_details(String delivery_details) {
        this.delivery_details = Label.validString(delivery_details);
    }

    public String getSelect_delivery_address() {
        return select_delivery_address;
    }

    public void setSelect_delivery_address(String select_delivery_address) {
        this.select_delivery_address = Label.validString(select_delivery_address);
    }

    public String getNew_address() {
        return new_address;
    }

    public void setNew_address(String new_address) {
        this.new_address = Label.validString(new_address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Label.validString(name);
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = Label.validString(mobile_number);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = Label.validString(email);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Label.validString(password);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = Label.validString(address);
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = Label.validString(pincode);
    }

    public String getGst_number() {
        return gst_number;
    }

    public void setGst_number(String gst_number) {
        this.gst_number = Label.validString(gst_number);
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = Label.validString(DOB);
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = Label.validString(amount);
    }

    public String getCash_on_delivery() {
        return cash_on_delivery;
    }

    public void setCash_on_delivery(String cash_on_delivery) {
        this.cash_on_delivery = Label.validString(cash_on_delivery);
    }

    public String getPay_now() {
        return pay_now;
    }

    public void setPay_now(String pay_now) {
        this.pay_now = Label.validString(pay_now);
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = Label.validString(order_id);
    }

    public String getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(String order_detail) {
        this.order_detail = Label.validString(order_detail);
    }

    public String getCancel_order() {
        return cancel_order;
    }

    public void setCancel_order(String cancel_order) {
        this.cancel_order = Label.validString(cancel_order);
    }

    public String getReturn_order() { return return_order; }

    public void setReturn_order(String return_order) { this.return_order = Label.validString(return_order); }

    public String getNeed_help() {
        return need_help;
    }

    public void setNeed_help(String need_help) {
        this.need_help = Label.validString(need_help);
    }

    public String getShipping_details() {
        return shipping_details;
    }

    public void setShipping_details(String shipping_details) {
        this.shipping_details = Label.validString(shipping_details);
    }

    public String getItem_count() {
        return item_count;
    }

    public void setItem_count(String item_count) {
        this.item_count = Label.validString(item_count);
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = Label.validString(payment_mode);
    }

    public String getOrder_cancel_msg() {
        return order_cancel_msg;
    }

    public void setOrder_cancel_msg(String order_cancel_msg) {
        this.order_cancel_msg = Label.validString(order_cancel_msg);
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = Label.validString(confirm);
    }

    public String getOrdered() {
        return ordered;
    }

    public void setOrdered(String ordered) {
        this.ordered = Label.validString(ordered);
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = Label.validString(gst);
    }

    public String getOrder_failed() {
        return order_failed;
    }

    public void setOrder_failed(String order_failed) {
        this.order_failed = Label.validString(order_failed);
    }

    public String getOrder_failed_msg() {
        return order_failed_msg;
    }

    public void setOrder_failed_msg(String order_failed_msg) {
        this.order_failed_msg = Label.validString(order_failed_msg);
    }

    public String getOrder_success() {
        return order_success;
    }

    public void setOrder_success(String order_success) {
        this.order_success = Label.validString(order_success);
    }

    public String getOrder_success_msg() {
        return order_success_msg;
    }

    public void setOrder_success_msg(String order_success_msg) {
        this.order_success_msg = Label.validString(order_success_msg);
    }

    public String getEmpty_order_title() {
        return empty_order_title;
    }

    public void setEmpty_order_title(String empty_order_title) {
        this.empty_order_title = Label.validString(empty_order_title);
    }

    public String getEmpty_order_description() {
        return empty_order_description;
    }

    public void setEmpty_order_description(String empty_order_description) {
        this.empty_order_description = Label.validString(empty_order_description);
    }

    public String getPlace_order() {
        return place_order;
    }

    public void setPlace_order(String place_order) {
        this.place_order = Label.validString(place_order);
    }

    public String getTotal_amount_to_be_pay() {
        return total_amount_to_be_pay;
    }

    public void setTotal_amount_to_be_pay(String total_amount_to_be_pay) {
        this.total_amount_to_be_pay = Label.validString(total_amount_to_be_pay);
    }

    public String getChange_delivery_details() {
        return change_delivery_details;
    }

    public void setChange_delivery_details(String change_delivery_details) {
        this.change_delivery_details = Label.validString(change_delivery_details);
    }

    public String getYes() {
        return yes;
    }

    public void setYes(String yes) {
        this.yes = Label.validString(yes);
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = Label.validString(no);
    }

    public String getConfirm_your_order() {
        return confirm_your_order;
    }

    public void setConfirm_your_order(String confirm_your_order) {
        this.confirm_your_order = Label.validString(confirm_your_order);
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = Label.validString(select);
    }

    public String getPlease_enter_new_delivery_details() {
        return please_enter_new_delivery_details;
    }

    public void setPlease_enter_new_delivery_details(String please_enter_new_delivery_details) {
        this.please_enter_new_delivery_details = Label.validString(please_enter_new_delivery_details);
    }

    public String getPlease_provide_rating() {
        return please_provide_rating;
    }

    public void setPlease_provide_rating(String please_provide_rating) {
        this.please_provide_rating = Label.validString(please_provide_rating);
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = Label.validString(change);
    }

    public String getAdd_new_address() {
        return add_new_address;
    }

    public void setAdd_new_address(String add_new_address) {
        this.add_new_address = Label.validString(add_new_address);
    }

    public String getSelect_country() {
        return select_country;
    }

    public void setSelect_country(String select_country) {
        this.select_country = Label.validString(select_country);
    }

    public String getSelect_state() {
        return select_state;
    }

    public void setSelect_state(String select_state) {
        this.select_state = Label.validString(select_state);
    }

    public String getSelect_city() {
        return select_city;
    }

    public void setSelect_city(String select_city) {
        this.select_city = Label.validString(select_city);
    }

    public String getSelect_pincode() {
        return select_pincode;
    }

    public void setSelect_pincode(String select_pincode) {
        this.select_pincode = Label.validString(select_pincode);
    }

    public String getSelect_area() {
        return select_area;
    }

    public void setSelect_area(String select_area) {
        this.select_area = Label.validString(select_area);
    }

    public String getChange_pincode() {
        return change_pincode;
    }

    public void setChange_pincode(String change_pincode) {
        this.change_pincode = Label.validString(change_pincode);
    }

    public String getPlease_select_country_from_list() {
        return please_select_country_from_list;
    }

    public void setPlease_select_country_from_list(String please_select_country_from_list) {
        this.please_select_country_from_list = Label.validString(please_select_country_from_list);
    }

    public String getPlease_select_state_from_list() {
        return please_select_state_from_list;
    }

    public void setPlease_select_state_from_list(String please_select_state_from_list) {
        this.please_select_state_from_list = Label.validString(please_select_state_from_list);
    }

    public String getPlease_select_city_from_list() {
        return please_select_city_from_list;
    }

    public void setPlease_select_city_from_list(String please_select_city_from_list) {
        this.please_select_city_from_list = Label.validString(please_select_city_from_list);
    }

    public String getPlease_select_pincode_from_list() {
        return please_select_pincode_from_list;
    }

    public void setPlease_select_pincode_from_list(String please_select_pincode_from_list) {
        this.please_select_pincode_from_list = Label.validString(please_select_pincode_from_list);
    }

    public String getPlease_select_area_from_list() {
        return please_select_area_from_list;
    }

    public void setPlease_select_area_from_list(String please_select_area_from_list) {
        this.please_select_area_from_list = Label.validString(please_select_area_from_list);
    }

    public String getContinue_with() {
        return continue_with;
    }

    public void setContinue_with(String continue_with) {
        this.continue_with = Label.validString(continue_with);
    }

    public String getEnter_delivery_pincode() {
        return enter_delivery_pincode;
    }

    public void setEnter_delivery_pincode(String enter_delivery_pincode) {
        this.enter_delivery_pincode = Label.validString(enter_delivery_pincode);
    }

    public String getEnter_pincode() {
        return enter_pincode;
    }

    public void setEnter_pincode(String enter_pincode) {
        this.enter_pincode = Label.validString(enter_pincode);
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
    }*/
}
