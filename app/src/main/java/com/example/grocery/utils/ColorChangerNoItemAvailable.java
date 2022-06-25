package com.example.grocery.utils;

import android.content.SharedPreferences;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

import com.example.grocery.Appearances.Appearance;
import com.example.grocery.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Mohd Afzal on 12/11/2017.
 */

public class ColorChangerNoItemAvailable {
    private final AppCompatActivity context;

    public ColorChangerNoItemAvailable(AppCompatActivity categories) {
        this.context = categories;

        Button butto = (Button) context.findViewById(R.id.noitemavailablebutton);

        butto.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        butto.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
    }
}
