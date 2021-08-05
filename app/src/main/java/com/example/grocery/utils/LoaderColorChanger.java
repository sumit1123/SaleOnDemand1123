package com.example.grocery.utils;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.R;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Mohd Afzal on 12/11/2017.
 */

public class LoaderColorChanger {
    private final AppCompatActivity context;
    private final int next;
    private String app_text_color;
    private String app_back_color;
    private String text_color;
    private String app_status_bar_color;


    public LoaderColorChanger(AppCompatActivity appCompatActivity) {
        this.context = appCompatActivity;

        try {
            app_text_color = Appearance.appSettings.getApp_text_color();
            app_back_color = Appearance.appSettings.getApp_back_color();
            text_color = Appearance.appSettings.getText_color();
            app_status_bar_color = Appearance.appSettings.getApp_status_bar_color();

        } catch (NullPointerException e) {

            SharedPreferences prefs = context.getSharedPreferences("APPSETTINGS", MODE_PRIVATE);

            app_status_bar_color = prefs.getString("app_status_bar_color", "00044C");
            app_back_color = prefs.getString("app_back_color", "00044C");
            text_color = prefs.getString("text_color", "000000");
            app_text_color = prefs.getString("app_text_color", "FFFFFF");

        }

        next = Color.parseColor("#" + text_color);
        Button retryButton = (Button) context.findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar circularImage = (ProgressBar) context.findViewById(R.id.progressbarinner);
        circularImage.setVisibility(View.VISIBLE);
        circularImage.getIndeterminateDrawable().setColorFilter(Color.parseColor("#" + text_color), PorterDuff.Mode.MULTIPLY);

        retryButton.setBackgroundColor(Color.parseColor("#" + app_back_color));
        retryButton.setTextColor(Color.parseColor("#" + app_text_color));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#" + app_status_bar_color));
        }

        retryButton.setBackground(context.getResources().getDrawable(R.drawable.buttonshape));
        GradientDrawable bgShape = (GradientDrawable) retryButton.getBackground();
        bgShape.setColor(Color.parseColor("#" + app_back_color));
        retryButton.setTextColor(Color.parseColor("#" + app_text_color));
        bgShape.setColor(Color.parseColor("#" + app_back_color));
        try {

            //retryButton.setText(Label.userLabel.getRetry());
        } catch (NullPointerException e) {
            retryButton.setText("Retry");
        }
    }
}
