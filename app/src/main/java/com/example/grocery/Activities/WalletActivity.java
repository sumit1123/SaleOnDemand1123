package com.example.grocery.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.WalletAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.WalletModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.example.grocery.Appearances.Appearance.appSettings;

public class WalletActivity extends AppCompatActivity {

    private RecyclerView wallerRecycler;
    private Dialog dialog;
    private AppCompatEditText addQuantityField;
    LinearLayout redeem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_wallet);

        TextView addMoneyToWalletText = (TextView) findViewById(R.id.addMoneyToWalletText);
       TextView sharemoneytext = (TextView) findViewById(R.id.sharemoneytext);
        ImageView img_redeem = (ImageView) findViewById(R.id.img_redeem);

        TextView total_amount_text = (TextView) findViewById(R.id.total_amount_text);
        TextView txnDetails = (TextView) findViewById(R.id.txnDetails);
        LinearLayout redeem = (LinearLayout) findViewById(R.id.transfer_layout);


        addMoneyToWalletText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletActivity.this, Addmoney.class);
                startActivity(intent);
            }
        });
        sharemoneytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletActivity.this, Redeem.class);
                startActivity(intent);
            }
        });

        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);

        setToolBar();

        addMoneyToWalletText.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        sharemoneytext.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));


        try {
          //  addMoneyToWalletText.setText(Label.walletLabels.getAdd_money());
          //  sharemoneytext.setText(Label.walletLabels.getTransfer_money());
          //  total_amount_text.setText(Label.walletLabels.getTotal_balance());
          //  txnDetails.setText(Label.walletLabels.getTransaction_history());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    private void setToolBar() {
        {
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
                    Intent intent = new Intent(WalletActivity.this, NotificationActivity.class);
                    startActivity(intent);
                }
            });
            SharedPreferences appsettings = getSharedPreferences("APPSETTINGS", MODE_PRIVATE);

            if (appSettings.getIs_notification() == 0) {
                toolBarNotification.setVisibility(View.GONE);
            }


            RelativeLayout toolBarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
            toolBarSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(WalletActivity.this, SearchActivity.class);
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
                    Intent intent = new Intent(WalletActivity.this, CartActivity.class);
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
                String cart = getString(R.string.my_wallet);

                toolbarTitle.setText(cart);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    protected void onResume() {
        super.onResume();
        new CartCountUtil(WalletActivity.this);

        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);

        findViewById(R.id.retryImage).setVisibility(View.GONE);

        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);

        final JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        int role_id=prefs.getInt("role_id",3);
        String languageid = prefs.getString("language", String.valueOf(1));
       /* if(role_id>2){
            findViewById(R.id.transfer_layout).setVisibility(View.GONE);
        }*/


        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_GETWALLET, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                findViewById(R.id.whiteloader).setVisibility(View.GONE);
                try {
                    System.out.println("frdxcz" + jsonObject);
                    System.out.println("frdxcz" + response);
                    TextView textView = (TextView) findViewById(R.id.total_amount);
                    textView.setText("" + Appearance.appTranslation.getCurrency() + " " + response.getJSONObject("data").getJSONObject("data").getJSONObject("user").getString("wallet"));
                    JSONArray jsonArray1 = response.getJSONObject("data").getJSONObject("data").getJSONArray("wallet_transaction");
                    if (jsonArray1.length() == 0) {
                        findViewById(R.id.emptytransactionlayout).setVisibility(View.VISIBLE);
                        Button button = (Button) findViewById(R.id.noitemavailablebutton);
                        TextView extra = (TextView) findViewById(R.id.extra);
                        String empty = getString(R.string.transaction_list_empty);

                        extra.setText(empty);
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
                            button.setBackgroundTintList(stateListDrawable);
                            button.setTextColor( Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
                        }
                        else{
                            button.setBackground(WalletActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                            GradientDrawable bgShape = (GradientDrawable) button.getBackground();
                            bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                            button.setTextColor( Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

                        }
                        String add = getString(R.string.add_money);
                        button.setText(add);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(WalletActivity.this, Addmoney.class);
                                startActivity(intent);
                            }
                        });
                    } else {
                        findViewById(R.id.emptytransactionlayout).setVisibility(View.GONE);

                        setData(jsonArray1);
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
                        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
                        progressBar.setVisibility(View.GONE);
                        findViewById(R.id.retryImage).setVisibility(View.VISIBLE);


                        Button imageView = (Button) findViewById(R.id.retrybutton);
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onResume();
                            }
                        });
                    }
                }, 2000);


            }
        });
    }

    private void setData(JSONArray jsonArray1) {
        Gson gson = new Gson();
        List<WalletModel> list = Arrays.asList(gson.fromJson(jsonArray1.toString(), WalletModel[].class));
        wallerRecycler = (RecyclerView) findViewById(R.id.walletRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        wallerRecycler.setLayoutManager(layoutManager);
        WalletAdapter walletAdapter = new WalletAdapter(this, list);
        wallerRecycler.setAdapter(walletAdapter);
    }


}
