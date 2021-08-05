package com.example.grocery.utils;


import java.util.Locale;

import androidx.core.text.TextUtilsCompat;
import androidx.core.view.ViewCompat;

/**
 * Created by Mohd Afzal on 1/7/2018.
 */

public class GettingCurrentRTLLTR {
    public static boolean isLeftToRight;

    public static boolean islanguageLTR() {
        isLeftToRight = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_LTR;
        return isLeftToRight;
    }
}
