package com.example.grocery.utils;

import android.content.SharedPreferences;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grocery.Appearances.Appearance;
import com.example.grocery.R;
import androidx.core.graphics.drawable.DrawableCompat;


import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Mohd Afzal on 12/9/2017.
 */

public class ToolbarSettings {

    private final AppCompatActivity context;

    public ToolbarSettings(AppCompatActivity context) {
        this.context = context;

        DrawableCompat.setTint(((ImageView) context.findViewById(R.id.searchicon)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        DrawableCompat.setTint(((ImageView) context.findViewById(R.id.backicon)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        DrawableCompat.setTint(((ImageView) context.findViewById(R.id.notificationicon)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
       DrawableCompat.setTint(((ImageView) context.findViewById(R.id.toolcart)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
       //   context.findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#" + sharedSettings.getString("app_color", "#FFFFFF")));

        context.findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));


      ((TextView) context.findViewById(R.id.titlebar)).setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
    }
}
