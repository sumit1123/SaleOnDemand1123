package com.example.grocery.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.paymentpackage.AppPreference;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import static com.example.grocery.interfaces.IConstants.URL_ADDTOWALLET;

public class RazorPay extends AppCompatActivity  implements PaymentResultListener {
    private AppPreference mAppPreference;
   // private PayUmoneySdkInitializer.PaymentParam mPaymentParams;
    private JSONObject jsonObject;
    public static String name;
    public static String pin;
    public static Double amount;
    public static String address;
    public static String email,mobile;
    private int paymentType;
    private static String merchantId, merchantKey, saltKey;
    private int cart_count;
    private RelativeLayout bar;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razor_pay);

        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        setToolBar();
      //  startPayment();
         getData();
    }

    private void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setImage(R.drawable.logo);
        final Activity activity = this;

        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        findViewById(R.id.retryImage).setVisibility(View.GONE);
        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("language_id", languageid);
            jsonObject.put("currency", "INR");
            total = RazorPay.amount * 100;
           jsonObject.put("amount", total);
        // Toast.makeText(activity, ""+RazorPay.amount, Toast.LENGTH_SHORT).show();
            checkout.open(activity, jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyTask volleyTask = new VolleyTask(RazorPay.this, IConstants.URL_GETPAYUMONEYDETAILS, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {


            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                try {
                    if (response.getString("status").matches("false")) {
                        if (response.has("auth")) {
                            if (response.getInt("auth") == 0) {
                                new CustomToast(RazorPay.this, response.getString("data"));
                                SharedPreferences preferences = getSharedPreferences("cheapstuffs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.apply();
                                Intent intent = new Intent(RazorPay.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            new CustomToast(RazorPay.this, response.getString("data"));
                            findViewById(R.id.whiteloader).setVisibility(View.GONE);
                        }

                    } else {
                        JSONObject jsonObject1 = response.getJSONObject("data").getJSONObject("data");
                        merchantId = jsonObject1.getString("merchant_id");
                        merchantKey = jsonObject1.getString("merchant_key");
                        saltKey = jsonObject1.getString("salt");
                        System.out.println("maney" + merchantId + merchantKey + saltKey);
                        Configuration configuration = getResources().getConfiguration();
                        configuration.setLocale(new Locale("en"));
                        configuration.setLayoutDirection(new Locale("en"));
                        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
                     //   launchPayUMoneyFlow();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void fnErrorOccurred(String error) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bar = (RelativeLayout) findViewById(R.id.progressBar);
                        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
                        progressBar.setVisibility(View.GONE);
                        findViewById(R.id.retryImage).setVisibility(View.VISIBLE);

                        Button retryButton = (Button) findViewById(R.id.retrybutton);
                        retryButton.setVisibility(View.VISIBLE);
                        retryButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }
                }, 2000);
            }
        });
    }

    protected void getData() {
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        findViewById(R.id.retryImage).setVisibility(View.GONE);
        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyTask volleyTask = new VolleyTask(RazorPay.this, IConstants.URL_GETPAYUMONEYDETAILS, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                try {
                    if (response.getString("status").matches("false")) {
                        if (response.has("auth")) {
                            if (response.getInt("auth") == 0) {
                                new CustomToast(RazorPay.this, response.getString("data"));
                                SharedPreferences preferences = getSharedPreferences("cheapstuffs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.apply();
                                Intent intent = new Intent(RazorPay.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            new CustomToast(RazorPay.this, response.getString("data"));
                            findViewById(R.id.whiteloader).setVisibility(View.GONE);

                        }

                    } else {
                        JSONObject jsonObject1 = response.getJSONObject("data").getJSONObject("data");
                        merchantId = jsonObject1.getString("merchant_id");
                        merchantKey = jsonObject1.getString("merchant_key");
                        saltKey = jsonObject1.getString("salt");
                        System.out.println("maney" + merchantId + merchantKey + saltKey);
                        Configuration configuration = getResources().getConfiguration();
                        configuration.setLocale(new Locale("en"));
                        configuration.setLayoutDirection(new Locale("en"));
                        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
                       startPayment();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void fnErrorOccurred(String error) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bar = (RelativeLayout) findViewById(R.id.progressBar);
                        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
                        progressBar.setVisibility(View.GONE);
                        findViewById(R.id.retryImage).setVisibility(View.VISIBLE);

                        Button retryButton = (Button) findViewById(R.id.retrybutton);
                        retryButton.setVisibility(View.VISIBLE);
                        retryButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getData();
                            }
                        });
                    }
                }, 2000);
            }
        });
    }

    private void setToolBar() {
        TextView cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
        if (Dashboard.cart_count != 0) {
            cartCount.setVisibility(View.VISIBLE);
            cartCount.setText(String.valueOf(Dashboard.cart_count));
        }
        RelativeLayout toolBarNotification = (RelativeLayout) findViewById(R.id.toolnotification);
        toolBarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RazorPay.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout toolBarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolBarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RazorPay.this, SearchActivity.class);
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
                Intent intent = new Intent(RazorPay.this, CartActivity.class);
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

    @Override
    public void onPaymentSuccess(String s) {
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        if (PaymentTypeActivity.payment_for.matches("wallet")) {
            JSONObject jsonObject = new JSONObject();
            SharedPreferences prefs1 = getSharedPreferences("UserId", MODE_PRIVATE);
            String userid = prefs1.getString("user_id", "");
            String pwd = prefs1.getString("pwd", "");
            String languageid = prefs1.getString("language", String.valueOf(1));
            try {
                jsonObject.put("business_id",IConstants.BUSINESS_ID);
                jsonObject.put("narration", "Added.");
                jsonObject.put("transaction_against", "online");
                jsonObject.put("id", userid);
                jsonObject.put("password", pwd);
                jsonObject.put("amount", RazorPay.amount);
                jsonObject.put("language_id", languageid);
                jsonObject.put("currency", "INR");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String url = URL_ADDTOWALLET;
            System.out.println("sdxz" + jsonObject);
            System.out.println("wedxs" + url.trim());
            VolleyTask volleyTask = new VolleyTask(RazorPay.this, url, jsonObject, Request.Method.POST);
            volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                @Override
                public void fnPostTaskCompleted(JSONArray response) {
                    System.out.println("sed" + response);
                }

                @Override
                public void fnPostTaskCompletedJsonObject(JSONObject response) {
                    System.out.println("hhhs" + response.toString());
                    if (!new ResponseHandler().validateResponse(RazorPay.this, response)) {
                        return;
                    }
                    try {
                        String jsonObject1 = response.getJSONObject("data").getString("msg");
                        new CustomToast(RazorPay.this, jsonObject1);
                        Intent intent = new Intent(RazorPay.this, Dashboard.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void fnErrorOccurred(String error) {
                    new CustomToast(RazorPay.this, "No connection Available");
                }
            });


        } else if (PaymentTypeActivity.payment_for.matches("cart_order")) {
             paymentType = 3;
             doPayment();
        }
    }

    private void doPayment() {
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("paymentdetails", MODE_PRIVATE);
        JSONObject jsonObject1 = null;
        try {
            jsonObject1 = new JSONObject(sharedPreferences.getString("json",""));
            jsonObject1.put("business_id", IConstants.BUSINESS_ID);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyTask volleyTask = new VolleyTask(RazorPay.this, IConstants.URL_SUBMITCArt, jsonObject1, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                try {
                    if (response.getString("status").matches("true")) {
                        if (response.has("auth")) {
                            if (response.getInt("auth") == 1) {
                                new CustomToast(RazorPay.this, response.getString("data"));
                                SharedPreferences preferences = getSharedPreferences("cheapstuffs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.apply();
                                Intent intent = new Intent(RazorPay.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            new CustomToast(RazorPay.this, response.getString("data"));
                            findViewById(R.id.whiteloader).setVisibility(View.GONE);
                        }
                    } else {
                        Dashboard.cart_count = 0;
                    }
                } catch (JSONException e) {

                }
            }

            @Override
            public void fnErrorOccurred(String error) {

            }
        });
    }


    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment error", Toast.LENGTH_SHORT).show();
        Log.v("VOLLEY", ""+s);
    }
}
