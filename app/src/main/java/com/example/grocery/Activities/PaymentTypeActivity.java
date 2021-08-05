package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.stringtemplate.v4.ST;

import java.util.Locale;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.interfaces.IConstants.URL_GETDPAYMENTDETAILS;

public class PaymentTypeActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView paypal, payumoney, ccavenue, paytm, mobikwik,razorpay;
    public static String payment_for;
    private RelativeLayout bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_payment_type);

        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        initui();
        setToolBar();
        getData();

    }

    private void initui() {
        payumoney = (TextView) findViewById(R.id.payumoney);
        paypal = (TextView) findViewById(R.id.paypal);
        ccavenue = (TextView) findViewById(R.id.ccavenue);
        paytm = (TextView) findViewById(R.id.paytm);
        mobikwik = (TextView) findViewById(R.id.mobivik);
        razorpay = (TextView) findViewById(R.id.razorpay);
        payumoney.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        payumoney.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        razorpay.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        razorpay.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        paypal.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        paypal.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        ccavenue.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        ccavenue.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        paytm.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        paytm.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        mobikwik.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        mobikwik.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        payumoney.setOnClickListener(this);
        paypal.setOnClickListener(this);
        ccavenue.setOnClickListener(this);
        paytm.setOnClickListener(this);
        mobikwik.setOnClickListener(this);
        try {
            String razrpay = getString(R.string.razorpay);
            String pay_u = getString(R.string.pay_u_money);
            String paypals = getString(R.string.pay_pal);
           payumoney.setText(pay_u);
           paypal.setText(paypals);
           razorpay.setText(razrpay);
          //  ccavenue.setText(Label.gatewayLabel.getC_c_avenue());
          //  paytm.setText(Label.gatewayLabel.getPaytm());
            //mobikwik.setText(Label.gatewayLabel.getMobi_kwik());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    private void setToolBar() {
        TextView cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
        if (Dashboard.cart_count != 0) {
            cartCount.setVisibility(View.VISIBLE);
            cartCount.setText(String.valueOf(Dashboard.cart_count));
        }
        RelativeLayout toolbarNotification = (RelativeLayout) findViewById(R.id.toolnotification);
        toolbarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentTypeActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        if (Appearance.appSettings.getIs_notification() == 0) {
            toolbarNotification.setVisibility(View.GONE);

        }
        RelativeLayout toolBarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolBarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentTypeActivity.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.brandArrays = null;
                ProductDetails.productid = 0;
                isViewWithCatalog = false;
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
                Intent intent = new Intent(PaymentTypeActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout toolBarBack = (RelativeLayout) findViewById(R.id.toolimage);

        toolBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView toolBarTitle = (TextView) findViewById(R.id.titlebar);
        try {
            String psyment = getString(R.string.payment_options);
            toolBarTitle.setText(psyment);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.payumoney:
                intent = new Intent(PaymentTypeActivity.this, PayuActivity.class);
                finish();
                startActivity(intent);
                break;

            case R.id.paypal:
                intent = new Intent(PaymentTypeActivity.this, PaypalActivity.class);
                finish();
                startActivity(intent);
                break;

            case R.id.razorpay:
                intent = new Intent(PaymentTypeActivity.this, RazorPay.class);
                finish();
                startActivity(intent);
                break;
        }

    }

    protected void getData() {
        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        findViewById(R.id.retryImage).setVisibility(View.GONE);


        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
       // Toast.makeText(this, "userid"+userid, Toast.LENGTH_SHORT).show();
        String email = prefs.getString("email", "");

        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyTask volleyTask = new VolleyTask(this, URL_GETDPAYMENTDETAILS, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());
                if (!new ResponseHandler().validateResponse(PaymentTypeActivity.this, response)) {
                    return;
                }

                try {

                    JSONObject jsonObject2 = response.getJSONObject("data").getJSONObject("data");
                    JSONObject jsonObject1 = jsonObject2.getJSONObject("payment_options");
                    int payumoney = jsonObject1.getInt("pay_u_money");
                    int paypal = jsonObject1.getInt("pay_pal");
                    int ccavenue = jsonObject1.getInt("c_c_avenue");
                    int paytm = jsonObject1.getInt("paytm");
                    int mobikwik = jsonObject1.getInt("mobi_kwik");
                    int razorpay = jsonObject1.getInt("razorpay");
                    CardView payumoneyCard = (CardView) findViewById(R.id.payuvisibility);
                    CardView payPalCard = (CardView) findViewById(R.id.paypalvisibility);
                    CardView ccavenueCard = (CardView) findViewById(R.id.ccavenuevisibility);
                    CardView paytmCard = (CardView) findViewById(R.id.paytmvisibility);
                    CardView mobikwikCard = (CardView) findViewById(R.id.mobikwikvisibility);
                    CardView razorpaycard = (CardView) findViewById(R.id.razorpayvisibility);
                    int optionCount = 0;
                    Class className = null;

                    if (payumoney == 1) {
                        payumoneyCard.setVisibility(View.VISIBLE);
                        optionCount++;
                        className = PayuActivity.class;
                    }
                    if (paypal == 1) {
                        payPalCard.setVisibility(View.VISIBLE);
                        optionCount++;
                        className = PayuActivity.class;


                    }
                    if (ccavenue == 1) {
                        ccavenueCard.setVisibility(View.VISIBLE);
                        optionCount++;
                        className = PayuActivity.class;


                    }
                    if (paytm == 1) {
                        paytmCard.setVisibility(View.VISIBLE);
                        optionCount++;
                        className = PayuActivity.class;


                    }
                    if (mobikwik == 1) {
                        mobikwikCard.setVisibility(View.VISIBLE);
                        optionCount++;
                        className = PayuActivity.class;


                    }
                    if (razorpay == 1) {
                        razorpaycard.setVisibility(View.VISIBLE);
                        optionCount++;
                        className = RazorPay.class;
                    }
                        if (optionCount == 1) {
                        payumoneyCard.setVisibility(View.GONE);
                        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);

                        Intent intent = new Intent(PaymentTypeActivity.this, className);

                        finish();

                        startActivity(intent);

                    } else {
                        findViewById(R.id.whiteloader).setVisibility(View.GONE);
                    }

                    if (optionCount==0){
                        findViewById(R.id.no_options).setVisibility(View.VISIBLE);
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

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(PaymentTypeActivity.this);

    }
}
