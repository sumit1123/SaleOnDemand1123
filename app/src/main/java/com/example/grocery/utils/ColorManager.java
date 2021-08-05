package com.example.grocery.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;


/**
 * Created by Mohd Afzal on 7/12/2018.
 */

public class ColorManager {

    public static String setRatingColor(double rating) {

        String s = "#00bc58";
            if (rating ==1) {
                //RED
                Log.i("InRed",s+rating);
                s = "#ff4c4c";
            }else if(rating >1 && rating<= 3.5) {
                //YELLOW
                s = "#ffaf4f";
            }
        return s;
    }



}
