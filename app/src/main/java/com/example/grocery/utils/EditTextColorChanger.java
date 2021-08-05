package com.example.grocery.utils;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import com.example.grocery.Appearances.Appearance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Mohd Afzal on 1/13/2018.
 */

public class EditTextColorChanger {

    public EditTextColorChanger(AppCompatActivity loginActivity, AppCompatEditText number) {

        StateListDrawable bgShape1 = (StateListDrawable)(number.getBackground());
        DrawableContainer.DrawableContainerState dcs = (DrawableContainer.DrawableContainerState)bgShape1.getConstantState();
        Drawable[] drawableItems = dcs.getChildren();
        GradientDrawable gradientDrawableChecked = (GradientDrawable)drawableItems[0]; // item 1
        GradientDrawable gradientDrawableUnChecked = (GradientDrawable)drawableItems[1];
        gradientDrawableChecked.setStroke(3, Color.parseColor("#" + Appearance.appSettings.getText_color()));
        gradientDrawableUnChecked.setStroke(1, Color.BLACK);
    }
}
