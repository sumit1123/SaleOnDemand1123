package com.example.grocery.Labels;

import android.content.Intent;

/**
 * Created by Mohd Afzal on 7/21/2018.
 */

public class Label {

    /*public static CartLabel cartLabel;
    public static NavigationLabels navigationLabels;
    public static ProductLabel productLabel;
    public static WishlistLabel wishlistLabel;
    public static OrderLabel orderLabel;
    public static GlobalLabel globalLabel;
    public static NotificationLabel notificationLabel;
    public static ComplaintLabel complaintLabel;
    public static WalletLabels walletLabels;
    public static UserLabel userLabel;
    public static GatewayLabel gatewayLabel;
    public static PromocodeLabel promocodeLabel;*/



    public static String validString(String value) {
        if (value.equals(null)) {
            value = "";
        }
        return value;
    }

    public static Integer validInt(Integer value) {
        if (value.equals(null)) {
            value = 0;
        }
        return value;
    }
}

