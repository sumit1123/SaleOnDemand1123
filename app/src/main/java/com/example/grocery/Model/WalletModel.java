package com.example.grocery.Model;

/**
 * Created by Mohd Afzal on 12/9/2017.
 */

public class WalletModel {

    /**
     * wallet_transactions_id : 1
     * user_id : 5
     * transaction_type : 1
     * transaction_against : afzal
     * narration : sodcosdxzl
     * transaction_title : Transferred form bank account
     * amount : 1000
     * old_wallet : 0
     * new_wallet : 1000
     * wallet_status : 1
     * created_at : 2017-12-16 11:13:15
     * updated_at : 2017-12-16 11:13:15
     */

    private long wallet_transactions_id;
    private int user_id;
    private int transaction_type;
    private String transaction_against;
    private String narration;
    private String transaction_title;
    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



    private double old_wallet;
    private double new_wallet;
    private int wallet_status;

    public long getWallet_transactions_id() {
        return wallet_transactions_id;
    }

    public void setWallet_transactions_id(long wallet_transactions_id) {
        this.wallet_transactions_id = wallet_transactions_id;
    }

    public double getOld_wallet() {
        return old_wallet;
    }

    public void setOld_wallet(double old_wallet) {
        this.old_wallet = old_wallet;
    }

    public double getNew_wallet() {
        return new_wallet;
    }

    public void setNew_wallet(double new_wallet) {
        this.new_wallet = new_wallet;
    }

    private String created_at;
    private String updated_at;



    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(int transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_against() {
        return transaction_against;
    }

    public void setTransaction_against(String transaction_against) {
        this.transaction_against = transaction_against;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getTransaction_title() {
        return transaction_title;
    }

    public void setTransaction_title(String transaction_title) {
        this.transaction_title = transaction_title;
    }


    public int getWallet_status() {
        return wallet_status;
    }

    public void setWallet_status(int wallet_status) {
        this.wallet_status = wallet_status;
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
}
