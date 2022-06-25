package com.example.grocery.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatButton;

import static com.example.grocery.CustomViews.FontSetter.FONTNAME;

/**
 * Created by Mohd Afzal on 8/11/2018.
 */

public class CustomButton extends AppCompatButton {

    private String TAG = "CustomButton";


    public CustomButton(Context context) {
        super(context);
        setCustomFont(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context);
    }

    public void setCustomFont(Context ctx) {
        Typeface typeface = null;
        try {
            typeface = Typeface.createFromAsset(ctx.getAssets(), FONTNAME);
        } catch (Exception e) {
            Log.e(TAG, "Unable to load typeface: " + e.getMessage());
        }
        setTypeface(typeface);
    }


}
