package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.R;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ToolbarSettings;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class WalletTransferSucessful extends AppCompatActivity {

    private String nameNet;
    private String amountNet;
    private String txnIDNet;
    private String dateNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_wallet_transfer_sucessful);
        Bundle extras = getIntent().getExtras();
        nameNet = extras.getString("name", "not found");
        amountNet = extras.getString("amount", "not found");
        txnIDNet = extras.getString("txnID", "not found");
        dateNet = extras.getString("date", "not found");

        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        Button home = (Button) findViewById(R.id.home);
        TextView sent = (TextView) findViewById(R.id.sent);
        TextView txnid = (TextView) findViewById(R.id.txnid);
        TextView name = (TextView) findViewById(R.id.name);
        TextView date = (TextView) findViewById(R.id.date);
        TextView sucessfulText = (TextView) findViewById(R.id.text);
        setdate(date, dateNet);
        name.setText(nameNet);
        TextView sucessful = (TextView) findViewById(R.id.sucessful);
        try {
            String id = getString(R.string.transaction_id);
            String paid = getString(R.string.you_have_successfully_paid);
            String to = getString(R.string.to);
            txnid.setText(id +
                    " : " + txnIDNet);
            sucessfulText.setText(paid + " " + Appearance.appTranslation.getCurrency() + amountNet +
                    " " + to);
                   String my_wallet = getString(R.string.my_wallet);
                   String send = getString(R.string.sent);

         //   home.setText(Label.navigationLabels.getMy_wallet());
          //  sent.setText(Label.walletLabels.getSent());
          //  sucessful.setText(Label.walletLabels.getSuccessfully());
            home.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
            home.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
            sent.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
            sucessful.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));


        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalletTransferSucessful.this, WalletActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        setToolBar();
    }

    private void setToolBar() {

        TextView cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
        TextView notificationCount = (TextView) findViewById(R.id.notification_count_textview);
        if (Dashboard.cart_count !=0){
            cartCount.setVisibility(View.VISIBLE);
            cartCount.setText(String.valueOf(Dashboard.cart_count));
        }
        if(Dashboard.notification_count!=0)
        {
            notificationCount.setVisibility(View.VISIBLE);
            notificationCount.setText(String.valueOf(Dashboard.notification_count));
        }
        RelativeLayout toolBarNotification = (RelativeLayout) findViewById(R.id.toolnotification);
        toolBarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletTransferSucessful.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences appsettings = getSharedPreferences("APPSETTINGS", MODE_PRIVATE);

        if (appsettings.getInt("is_notification", 0) == 0) {
            toolBarNotification.setVisibility(View.GONE);

        }
        RelativeLayout toolBarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolBarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletTransferSucessful.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.brandArrays = null;
                ProductDetails.productid = 0;

                ApplyFilter.minimumSeekBar = "0";
                SearchActivity.searchMin = "";
                SearchActivity.sortedida = 0;
                SearchActivity.formatedStringSearch = "";
                SearchActivity.imagea = "true";
                startActivity(intent);
            }
        });
        RelativeLayout toolBarCart = (RelativeLayout) findViewById(R.id.cart);
        toolBarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletTransferSucessful.this, CartActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);

        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        try {
            String transfer = getString(R.string.transfer_money);
            toolbarTitle.setText(transfer);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void setdate(TextView order, String string) {
        StringTokenizer tk = new StringTokenizer(string);
        String date = tk.nextToken();
        String time = tk.nextToken();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        Date dates = null;
        try {
            dates = format1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("fdcx" + format2.format(dates));
        // holder.date.setText(format2.format(date));


        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm:ss a", Locale.ENGLISH);
        Date dt;
        try {
            dt = sdf.parse(time);
            System.out.println("Time Display: " + sdfs.format(dt));
            order.setText(format2.format(dates) + " " + sdfs.format(dt));// <-- I got result here
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}