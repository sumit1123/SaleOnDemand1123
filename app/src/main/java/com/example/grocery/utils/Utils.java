package com.example.grocery.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.BuildConfig;

public class Utils {
    public static void fnPrintLog(String tag, String message ) {
        if( !BuildConfig.DEBUG ) {
            Log.d( tag, message );
        }
    }

    public static void fnPrintLongToastMessage(Context context, String message ) {
        Toast.makeText( context, message, Toast.LENGTH_LONG ).show();
    }

    public static void fnPrintShortToastMessage(Context context, String message ) {
        Toast.makeText( context, message, Toast.LENGTH_SHORT ).show();
    }

    /* The below method if device is connected to network or not. */

    public static boolean fnCheckIsNetworkAvailable( Context context ) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}