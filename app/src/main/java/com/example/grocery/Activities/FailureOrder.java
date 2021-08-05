package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.R;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ToolbarSettings;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;

public class FailureOrder extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_failure_order);

        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);
        RelativeLayout toolbarNotification = (RelativeLayout) findViewById(R.id.toolnotification);

        toolbarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FailureOrder.this, NotificationActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        String order_fail = getString(R.string.order_failed);
        toolbarTitle.setText(order_fail);
        RelativeLayout toolbarCart = (RelativeLayout) findViewById(R.id.cart);
        RelativeLayout toolbarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FailureOrder.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.minimumSeekBar = "0";
                ApplyFilter.brandArrays = null;
                ProductDetails.productid = 0;
                isViewWithCatalog = false;

                SearchActivity.searchMin = "";
                SearchActivity.sortedida = 0;
                SearchActivity.formatedStringSearch = "";
                SearchActivity.imagea = "true";
                startActivity(intent);
            }
        });
        toolbarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FailureOrder.this, CartActivity.class);
                startActivity(intent);
            }
        });
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button continueShoppingText = (Button) findViewById(R.id.continueshopping);

        TextView orderSucessText = (TextView) findViewById(R.id.orderFailedText);
        TextView orderPlacedText = (TextView) findViewById(R.id.orderFailedMsg);

        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ColorStateList stateListDrawable = new ColorStateList(
                    new int[][]
                            {
                                    new int[]{android.R.attr.state_pressed},
                                    new int[]{android.R.attr.state_focused},
                                    new int[]{android.R.attr.state_activated},
                                    new int[]{}
                            },
                    new int[]
                            {
                                    Color.parseColor("#" + Appearance.appSettings.getApp_back_color()),
                                    Color.parseColor("#" + Appearance.appSettings.getApp_back_color()),
                                    Color.parseColor("#" + Appearance.appSettings.getApp_back_color()),
                                    Color.parseColor("#" + Appearance.appSettings.getApp_back_color())
                            }
            );
            continueShoppingText.setBackgroundTintList(stateListDrawable);
            continueShoppingText.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        } else {
            continueShoppingText.setBackground(FailureOrder.this.getResources().getDrawable(R.drawable.buttonshape));
            GradientDrawable bgShape = (GradientDrawable) continueShoppingText.getBackground();
            bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
            continueShoppingText.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

        }

        try {
            String failed = getString(R.string.order_failed);
            getSupportActionBar().setTitle(Html.fromHtml("<font color=" + Color.parseColor("#" + Appearance.appSettings.getApp_text_color()) + ">" + failed + "</font>"));
         String success = getString(R.string.order_failed);
         String msg = getString(R.string.order_failed_msg);
         String shopping = getString(R.string.continue_shopping);

            orderSucessText.setText(success);
            orderPlacedText.setText(msg);
            continueShoppingText.setText(shopping);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        //DrawableCompat.setTint(((ImageView) findViewById(R.id.continueShopingCheck)).getDrawable(), FailureOrder.this.getResources().getColor(R.color.errorColor));

        continueShoppingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FailureOrder.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }
}
