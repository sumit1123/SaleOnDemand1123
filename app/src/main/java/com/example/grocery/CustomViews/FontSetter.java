package com.example.grocery.CustomViews;

import android.util.Log;

/**
 * Created by Mohd Afzal on 8/11/2018.
 */

public class FontSetter {

    public static String  FONTNAME = "fonts/verdana.ttf";

    public  static void setFont(int font_id){
        switch (font_id){
            case 1:
                FONTNAME = "fonts/verdana.ttf";
                break;
            case 2:
                FONTNAME = "fonts/Gentona Medium.otf";
                break;
            case 3:
                FONTNAME = "fonts/IndieFlower.ttf";
                break;
            case 4:
                FONTNAME = "fonts/Acme-Regular.ttf";
                break;
            case 5:

                FONTNAME = "fonts/ExoRegular.ttf";
                break;
        }
        Log.i("FONT_ID "+font_id, "setFont: "+FONTNAME);
    }
}
