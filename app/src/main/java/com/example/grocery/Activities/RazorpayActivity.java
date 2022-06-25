package com.example.grocery.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.grocery.R;
import com.example.grocery.paymentpackage.AppPreference;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ToolbarSettings;
import org.json.JSONObject;

public class RazorpayActivity extends AppCompatActivity {
    private AppPreference mAppPreference;
   // private PayUmoneySdkInitializer.PaymentParam mPaymentParams;
    private JSONObject jsonObject;
    public static String name;
    public static String pin;
    public static double amount;
    public static String address;
    public static String email,mobile;
    private int paymentType;
    private static String merchantId, merchantKey, saltKey;
    private int cart_count;
    private RelativeLayout bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razorpay);
        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);

        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        setToolBar();
    }

    private void setToolBar() {
        {
            TextView cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
            if (Dashboard.cart_count != 0) {
                cartCount.setVisibility(View.VISIBLE);
                cartCount.setText(String.valueOf(Dashboard.cart_count));
            }
            RelativeLayout toolBarNotification = (RelativeLayout) findViewById(R.id.toolnotification);
            toolBarNotification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RazorpayActivity.this, NotificationActivity.class);
                    startActivity(intent);
                }
            });
            RelativeLayout toolBarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
            toolBarSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RazorpayActivity.this, SearchActivity.class);
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
                    Intent intent = new Intent(RazorpayActivity.this, CartActivity.class);
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
                String title = getString(R.string.razorpay);
                toolbarTitle.setText(title);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

}