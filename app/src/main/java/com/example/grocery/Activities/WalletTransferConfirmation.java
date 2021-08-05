package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import static com.example.grocery.interfaces.IConstants.URL_CONFIRMTRANSFER;
import static com.example.grocery.interfaces.IConstants.URL_POSTTRANSFER;

public class WalletTransferConfirmation extends AppCompatActivity {

    private AppCompatEditText amount;
    private Button confirm;
    private String number, name;
    private TextView amounttotal;
    private AppCompatEditText messege;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_wallet_transfer_confirmation);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        TextView name1 = (TextView) findViewById(R.id.name);
        TextView number1 = (TextView) findViewById(R.id.mobile);
        send = (Button) findViewById(R.id.transferButton);
        amount = (AppCompatEditText) findViewById(R.id.amountTobeTransfered);
        new EditTextColorChanger(this,amount);

        messege = (AppCompatEditText) findViewById(R.id.messege);
        new EditTextColorChanger(this,messege);

        confirm = (Button) findViewById(R.id.confirm);
        amounttotal = (TextView) findViewById(R.id.amounttotal);

        try {
            String amounts = getString(R.string.amount);
            String send_1 = getString(R.string.send);
            String msg = getString(R.string.narration);
            String confirm_1 = getString(R.string.confirm);
             amount.setHint("("+String.valueOf(Appearance.appTranslation.getCurrency()+") "+ amounts));
             send.setText(send_1);
             messege.setHint(msg);
            confirm.setText(confirm_1);


        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        setToolBar();
        Bundle extras = getIntent().getExtras();
        number = extras.getString("contact_number", "8149180155");
        name = extras.getString("name", "JMTIT");
        name1.setText(name);
        number1.setText(number);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amount.getText().toString().matches("")) {
                    try {
                        amount.requestFocus();
                        String amt = getString(R.string.please_enter_amount);
                        amount.setError(amt);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                } else {
                    findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
                    final JSONObject jsonObject = new JSONObject();
                    SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

                    String userid = prefs.getString("user_id", "");
                    String emaila = prefs.getString("email", "");
                    String pwda = prefs.getString("pwd", "");
                    String languageid = prefs.getString("language", String.valueOf(1));

                    try {
                        jsonObject.put("business_id", IConstants.BUSINESS_ID);
                        jsonObject.put("id", userid);
                        jsonObject.put("password", pwda);
                        jsonObject.put("contact_number", number);
                        jsonObject.put("language_id", languageid);
                        jsonObject.put("amount", amount.getText().toString());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    System.out.println("gghg" + jsonObject);

                    VolleyTask volleyTask = new VolleyTask(WalletTransferConfirmation.this, URL_CONFIRMTRANSFER, jsonObject, Request.Method.POST);
                    volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                        @Override
                        public void fnPostTaskCompleted(JSONArray response) {
                            System.out.println("sed" + response);
                        }

                        @Override
                        public void fnPostTaskCompletedJsonObject(JSONObject response) {
                            System.out.println("hhhs" + response.toString());
                            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                            if (!new ResponseHandler().validateResponse(WalletTransferConfirmation.this, response)) {
                                return;
                            }
                            Gson gson = new Gson();
                            send.setVisibility(View.GONE);
                            amount.setEnabled(false);
                            amount.setVisibility(View.GONE);
                            confirm.setVisibility(View.VISIBLE);
                            messege.setVisibility(View.GONE);
                            amounttotal.setVisibility(View.VISIBLE);
                            amounttotal.setText(Appearance.appTranslation.getCurrency() + amount.getText().toString());
                            confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    final JSONObject jsonObject = new JSONObject();
                                    findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);

                                    SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

                                    String userid = prefs.getString("user_id", "");
                                    String emaila = prefs.getString("email", "");
                                    String pwda = prefs.getString("pwd", "");
                                    String languageid = prefs.getString("language", String.valueOf(1));

                                    try {
                                        jsonObject.put("business_id",IConstants.BUSINESS_ID);
                                        jsonObject.put("id", userid);
                                        jsonObject.put("password", pwda);
                                        jsonObject.put("contact_number", number);
                                        jsonObject.put("narration", messege.getText().toString());
                                        jsonObject.put("amount", amount.getText().toString());
                                        jsonObject.put("language_id", languageid);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("gghg" + jsonObject);

                                    VolleyTask volleyTask = new VolleyTask(WalletTransferConfirmation.this, URL_POSTTRANSFER, jsonObject, Request.Method.POST);
                                    volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                                        @Override
                                        public void fnPostTaskCompleted(JSONArray response) {
                                            System.out.println("sed" + response);
                                        }

                                        @Override
                                        public void fnPostTaskCompletedJsonObject(JSONObject response) {
                                            System.out.println("hhhs" + response.toString());

                                            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                                            if (!new ResponseHandler().validateResponse(WalletTransferConfirmation.this, response)) {
                                                return;
                                            }
                                            try {
                                                new CustomToast(WalletTransferConfirmation.this, response.getJSONObject("data").getString("msg"));

                                                Intent intent = new Intent(WalletTransferConfirmation.this, WalletTransferSucessful.class);
                                                String txnID=response.getJSONObject("data").getJSONObject("data").getJSONObject("transaction").getString("wallet_transactions_id");
                                                String date=response.getJSONObject("data").getJSONObject("data").getJSONObject("transaction").getString("updated_at");

                                                intent.putExtra("name",response.getJSONObject("data").getJSONObject("data").getJSONObject("receiver").getString("name"));
                                                intent.putExtra("amount",amount.getText().toString());
                                                intent.putExtra("txnID",txnID);
                                                intent.putExtra("date",date);

                                                startActivity(intent);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }


                                        }


                                        @Override
                                        public void fnErrorOccurred(String error) {
                                            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                                        }
                                    });
                                }
                            });


                        }


                        @Override
                        public void fnErrorOccurred(String error) {
                            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                        }
                    });

                }
            }
        });


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
                Intent intent = new Intent(WalletTransferConfirmation.this, NotificationActivity.class);
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
                Intent intent = new Intent(WalletTransferConfirmation.this, SearchActivity.class);
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
                Intent intent = new Intent(WalletTransferConfirmation.this, CartActivity.class);
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
            String title = getString(R.string.transfer_money);
            toolbarTitle.setText(title);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}
