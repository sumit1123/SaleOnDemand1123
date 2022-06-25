package com.example.grocery.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import static com.example.grocery.CustomViews.FontSetter.FONTNAME;

/**
 * Created by Mohd Afzal on 8/11/2018.
 */

public class CustomTextView extends AppCompatTextView {

    private String TAG = "CustomTextView";

    public CustomTextView(Context context) {
        super(context);
        setCustomFont(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context);

    }

    public  void setCustomFont(Context ctx) {
        Typeface typeface = null;
        try {
            typeface = Typeface.createFromAsset(ctx.getAssets(), FONTNAME);
        } catch (Exception e) {
            Log.e(TAG, "Unable to load typeface: " + e.getMessage());
        }
        setTypeface(typeface);
    }
}
