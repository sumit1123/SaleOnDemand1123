package com.example.grocery.utils;

/**
 * Created by Mohd Afzal on 11/27/2017.
 */

public class Config {

    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static final String SHARED_PREF = "ah_firebase";

    public static final String PAYPAL_CLIENT_ID =
    //Rajat Sangrame
            "AZGweRjAYp87hWF61abJZNAIp1XB3meWQQhw6KPKUYHuax6WVs55dPIkgY0XsSG5s_VxMsQOilrRnHY4";
//    "AbjSvufmM-rG55-tNAwmoXx-gre6I_Bqxi4OnRhjtysx757ZYUudJCDf-veRcrtfBd7v9r67Tb6lcspI";
}