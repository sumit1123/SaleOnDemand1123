package com.example.grocery.Labels;

import com.google.gson.annotations.SerializedName;

public class CartLabel {


    /**
     * cart_label_id : 1
     * language_id : 1
     * qty : Qty
     * move_to_wishlist : Move to wishlist
     * remove_lower : remove
     * remove_item : Remove item
     * do_you_really_want_remove_this_product : Do you really want remove this product?
     * remove_upper : REMOVE
     * price_details : PRICE DETAILS
     * price : Price
     * items : items
     * delivery : Delivery
     * free : FREE
     * total_amount : Total Amount
     * you_will_save : You will save
     * on_this_order : on this order
     * cart_secure_msg : Safe and Secure Payments.Easy Returns 100% Authentic Products
     * view_price_details : View price details
     * continue : CONTINUE
     * out_of_stock : OUT OF STOCK
     * is_available : is available
     * only : Only
     * do_you_have_promocode : Do you have promocode
     * any_special_comment_for_this_order : Any special comment for this order
     * select : Select
     * update : Update
     * order_description : Order decription
     * view_cart : VIEW CART
     * add_to_cart : ADD TO CART
     * update_cart : UPDATE CART
     * empty_cart_title : Your cart is empty!
     * empty_cart_description : Add item to it now
     * please_enter_quantity : Please enter quantity
     * created_at : 2018-07-23 18:37:50
     * updated_at : 2018-07-23 18:37:50
     */

  /*  private int cart_label_id = 0;
    private int language_id = 0;
    private String qty;
    private String move_to_wishlist;
    private String remove_lower;
    private String remove_item;
    private String do_you_really_want_remove_this_product;
    private String remove_upper;
    private String price_details;
    private String price;
    private String items;
    private String delivery;
    private String free;
    private String total_amount;
    private String you_will_save;
    private String on_this_order;
    private String cart_secure_msg;
    private String view_price_details;
    @SerializedName("continue")
    private String continueX;
    private String out_of_stock;
    private String is_available;
    private String only;
    private String do_you_have_promocode;
    private String any_special_comment_for_this_order;
    private String select;
    private String update;
    private String order_description;
    private String view_cart;
    private String add_to_cart;
    private String update_cart;
    private String empty_cart_title;
    private String empty_cart_description;
    private String please_enter_quantity;
    private String created_at;
    private String updated_at;

    public int getCart_label_id() {
        return cart_label_id;
    }

    public void setCart_label_id(int cart_label_id) {
        this.cart_label_id = Label.validInt(cart_label_id);
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = Label.validInt(language_id);
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = Label.validString(qty);
    }

    public String getMove_to_wishlist() {
        return move_to_wishlist;
    }

    public void setMove_to_wishlist(String move_to_wishlist) {
        this.move_to_wishlist = Label.validString(move_to_wishlist);
    }

    public String getRemove_lower() {
        return remove_lower;
    }

    public void setRemove_lower(String remove_lower) {
        this.remove_lower = Label.validString(remove_lower);
    }

    public String getRemove_item() {
        return remove_item;
    }

    public void setRemove_item(String remove_item) {
        this.remove_item = Label.validString(remove_item);
    }

    public String getDo_you_really_want_remove_this_product() {
        return do_you_really_want_remove_this_product;
    }

    public void setDo_you_really_want_remove_this_product(String do_you_really_want_remove_this_product) {
        this.do_you_really_want_remove_this_product = Label.validString(do_you_really_want_remove_this_product);
    }

    public String getRemove_upper() {
        return remove_upper;
    }

    public void setRemove_upper(String remove_upper) {
        this.remove_upper = Label.validString(remove_upper);
    }

    public String getPrice_details() {
        return price_details;
    }

    public void setPrice_details(String price_details) {
        this.price_details = Label.validString(price_details);
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = Label.validString(price);
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = Label.validString(items);
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = Label.validString(delivery);
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = Label.validString(free);
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = Label.validString(total_amount);
    }

    public String getYou_will_save() {
        return you_will_save;
    }

    public void setYou_will_save(String you_will_save) {
        this.you_will_save = Label.validString(you_will_save);
    }

    public String getOn_this_order() {
        return on_this_order;
    }

    public void setOn_this_order(String on_this_order) {
        this.on_this_order = Label.validString(on_this_order);
    }

    public String getCart_secure_msg() {
        return cart_secure_msg;
    }

    public void setCart_secure_msg(String cart_secure_msg) {
        this.cart_secure_msg = Label.validString(cart_secure_msg);
    }

    public String getView_price_details() {
        return view_price_details;
    }

    public void setView_price_details(String view_price_details) {
        this.view_price_details = Label.validString(view_price_details);
    }

    public String getContinueX() {
        return continueX;
    }

    public void setContinueX(String continueX) {
        this.continueX = Label.validString(getContinueX());
    }

    public String getOut_of_stock() {
        return out_of_stock;
    }

    public void setOut_of_stock(String out_of_stock) {
        this.out_of_stock = Label.validString(out_of_stock);
    }

    public String getIs_available() {
        return is_available;
    }

    public void setIs_available(String is_available) {
        this.is_available = Label.validString(is_available);
    }

    public String getOnly() {
        return only;
    }

    public void setOnly(String only) {
        this.only = Label.validString(only);
    }

    public String getDo_you_have_promocode() {
        return do_you_have_promocode;
    }

    public void setDo_you_have_promocode(String do_you_have_promocode) {
        this.do_you_have_promocode = Label.validString(do_you_have_promocode);
    }

    public String getAny_special_comment_for_this_order() {
        return any_special_comment_for_this_order;
    }

    public void setAny_special_comment_for_this_order(String any_special_comment_for_this_order) {
        this.any_special_comment_for_this_order = Label.validString(any_special_comment_for_this_order);
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = Label.validString(select);
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = Label.validString(update);
    }

    public String getOrder_description() {
        return order_description;
    }

    public void setOrder_description(String order_description) {
        this.order_description = Label.validString(order_description);
    }

    public String getView_cart() {
        return view_cart;
    }

    public void setView_cart(String view_cart) {
        this.view_cart = Label.validString(view_cart);
    }

    public String getAdd_to_cart() {
        return add_to_cart;
    }

    public void setAdd_to_cart(String add_to_cart) {
        this.add_to_cart = Label.validString(add_to_cart);
    }

    public String getUpdate_cart() {
        return update_cart;
    }

    public void setUpdate_cart(String update_cart) {
        this.update_cart = Label.validString(update_cart);
    }

    public String getEmpty_cart_title() {
        return empty_cart_title;
    }

    public void setEmpty_cart_title(String empty_cart_title) {
        this.empty_cart_title = Label.validString(empty_cart_title);
    }

    public String getEmpty_cart_description() {
        return empty_cart_description;
    }

    public void setEmpty_cart_description(String empty_cart_description) {
        this.empty_cart_description = Label.validString(empty_cart_description);
    }

    public String getPlease_enter_quantity() {
        return please_enter_quantity;
    }

    public void setPlease_enter_quantity(String please_enter_quantity) {
        this.please_enter_quantity = Label.validString(please_enter_quantity);
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