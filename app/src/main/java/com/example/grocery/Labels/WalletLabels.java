package com.example.grocery.Labels;

/**
 * Created by Mohd Afzal on 7/21/2018.
 */

public class WalletLabels {
    /**
     * wallet_label_id : 1
     * language_id : 1
     * transfer : Transfer
     * to : to
     * send : Send
     * sent : Sent
     * successfully : Successfully
     * you_have_successfully_paid : You have successfully paid
     * transaction_id : Transaction ID
     * wallet_transfer : Wallet transfer
     * wallet_transfer_confirmation : Wallet transfer confirmation
     * wallet_transfer_sucessful : Wallet transfer sucessful
     * transaction_list_empty : Transaction list empty
     * wallet_amount : Wallet Amount
     * have_a_promocode_Enter_here : Have a Promocode? Enter here
     * please_enter_amount : Please enter amount
     * enter_amount : Enter amount
     * narration : Narration
     * mobile_number : Mobile Number
     * add : Add
     * contact_number_empty : Contact number can not be empty
     * please_enter_a_valid_number : Please enter a valid number
     * transfer_money : Transfer Money
     * amount : Amount
     * confirm : CONFIRM
     * total_balance : Total Balance
     * add_money : Add Money
     * transaction_history : Transaction History
     * description_optional : Description (Optional)
     * proceed : Proceed
     * confirm_payment : Confirm Payment
     * proceed_to_pay : Proceed to Pay
     * wallet : Wallet
     * continue_to_pay : Continue To Pay
     * cancel_payment : Cancel Payment
     * pay_using : Pay using
     * pay_using_wallet : Pay using Wallet
     * order_amount : Order Amount
     * pay_without_wallet : Pay without wallet
     * place_order_with_wallet : Place order with wallet
     * created_at : 2018-07-24 16:10:14
     * updated_at : 2018-07-24 16:10:14
     */

  /*  private int wallet_label_id;
    private int language_id;
    private String transfer;
    private String to;
    private String send;
    private String sent;
    private String successfully;
    private String you_have_successfully_paid;
    private String transaction_id;
    private String wallet_transfer;
    private String wallet_transfer_confirmation;
    private String wallet_transfer_sucessful;
    private String transaction_list_empty;
    private String wallet_amount;
    private String have_a_promocode_Enter_here;
    private String please_enter_amount;
    private String enter_amount;
    private String narration;
    private String mobile_number;
    private String add;
    private String contact_number_empty;
    private String please_enter_a_valid_number;
    private String transfer_money;
    private String amount;
    private String confirm;
    private String total_balance;
    private String add_money;
    private String transaction_history;
    private String description_optional;
    private String proceed;
    private String confirm_payment;
    private String proceed_to_pay;
    private String wallet;
    private String continue_to_pay;
    private String cancel_payment;
    private String pay_using;
    private String pay_using_wallet;
    private String order_amount;
    private String pay_without_wallet;
    private String place_order_with_wallet;
    private String created_at;
    private String updated_at;

    public int getWallet_label_id() {
        return wallet_label_id;
    }

    public void setWallet_label_id(int wallet_label_id) {
        this.wallet_label_id = Label.validInt(wallet_label_id);
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = Label.validInt(language_id);
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = Label.validString(transfer);
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = Label.validString(to);
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = Label.validString(send);
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = Label.validString(sent);
    }

    public String getSuccessfully() {
        return successfully;
    }

    public void setSuccessfully(String successfully) {
        this.successfully = Label.validString(successfully);
    }

    public String getYou_have_successfully_paid() {
        return you_have_successfully_paid;
    }

    public void setYou_have_successfully_paid(String you_have_successfully_paid) {
        this.you_have_successfully_paid = Label.validString(you_have_successfully_paid);
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = Label.validString(transaction_id);
    }

    public String getWallet_transfer() {
        return wallet_transfer;
    }

    public void setWallet_transfer(String wallet_transfer) {
        this.wallet_transfer = Label.validString(wallet_transfer);
    }

    public String getWallet_transfer_confirmation() {
        return wallet_transfer_confirmation;
    }

    public void setWallet_transfer_confirmation(String wallet_transfer_confirmation) {
        this.wallet_transfer_confirmation = Label.validString(wallet_transfer_confirmation);
    }

    public String getWallet_transfer_sucessful() {
        return wallet_transfer_sucessful;
    }

    public void setWallet_transfer_sucessful(String wallet_transfer_sucessful) {
        this.wallet_transfer_sucessful = Label.validString(wallet_transfer_sucessful);
    }

    public String getTransaction_list_empty() {
        return transaction_list_empty;
    }

    public void setTransaction_list_empty(String transaction_list_empty) {
        this.transaction_list_empty = Label.validString(transaction_list_empty);
    }

    public String getWallet_amount() {
        return wallet_amount;
    }

    public void setWallet_amount(String wallet_amount) {
        this.wallet_amount = Label.validString(wallet_amount);
    }

    public String getHave_a_promocode_Enter_here() {
        return have_a_promocode_Enter_here;
    }

    public void setHave_a_promocode_Enter_here(String have_a_promocode_Enter_here) {
        this.have_a_promocode_Enter_here = Label.validString(have_a_promocode_Enter_here);
    }

    public String getPlease_enter_amount() {
        return please_enter_amount;
    }

    public void setPlease_enter_amount(String please_enter_amount) {
        this.please_enter_amount = Label.validString(please_enter_amount);
    }

    public String getEnter_amount() {
        return enter_amount;
    }

    public void setEnter_amount(String enter_amount) {
        this.enter_amount = Label.validString(enter_amount);
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = Label.validString(narration);
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = Label.validString(mobile_number);
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = Label.validString(add);
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

    public String getTransfer_money() {
        return transfer_money;
    }

    public void setTransfer_money(String transfer_money) {
        this.transfer_money = Label.validString(transfer_money);
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = Label.validString(amount);
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = Label.validString(confirm);
    }

    public String getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(String total_balance) {
        this.total_balance = Label.validString(total_balance);
    }

    public String getAdd_money() {
        return add_money;
    }

    public void setAdd_money(String add_money) {
        this.add_money = Label.validString(add_money);
    }

    public String getTransaction_history() {
        return transaction_history;
    }

    public void setTransaction_history(String transaction_history) {
        this.transaction_history = Label.validString(transaction_history);
    }

    public String getDescription_optional() {
        return description_optional;
    }

    public void setDescription_optional(String description_optional) {
        this.description_optional = Label.validString(description_optional);
    }

    public String getProceed() {
        return proceed;
    }

    public void setProceed(String proceed) {
        this.proceed = Label.validString(proceed);
    }

    public String getConfirm_payment() {
        return confirm_payment;
    }

    public void setConfirm_payment(String confirm_payment) {
        this.confirm_payment = Label.validString(confirm_payment);
    }

    public String getProceed_to_pay() {
        return proceed_to_pay;
    }

    public void setProceed_to_pay(String proceed_to_pay) {
        this.proceed_to_pay = Label.validString(proceed_to_pay);
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = Label.validString(wallet);
    }

    public String getContinue_to_pay() {
        return continue_to_pay;
    }

    public void setContinue_to_pay(String continue_to_pay) {
        this.continue_to_pay = Label.validString(continue_to_pay);
    }

    public String getCancel_payment() {
        return cancel_payment;
    }

    public void setCancel_payment(String cancel_payment) {
        this.cancel_payment = Label.validString(cancel_payment);
    }

    public String getPay_using() {
        return pay_using;
    }

    public void setPay_using(String pay_using) {
        this.pay_using = Label.validString(pay_using);
    }

    public String getPay_using_wallet() {
        return pay_using_wallet;
    }

    public void setPay_using_wallet(String pay_using_wallet) {
        this.pay_using_wallet = Label.validString(pay_using_wallet);
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = Label.validString(order_amount);
    }

    public String getPay_without_wallet() {
        return pay_without_wallet;
    }

    public void setPay_without_wallet(String pay_without_wallet) {
        this.pay_without_wallet = Label.validString(pay_without_wallet);
    }

    public String getPlace_order_with_wallet() {
        return place_order_with_wallet;
    }

    public void setPlace_order_with_wallet(String place_order_with_wallet) {
        this.place_order_with_wallet = Label.validString(place_order_with_wallet);
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
