package com.example.grocery.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.Config;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.VolleyTask;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

import static com.example.grocery.Activities.Addmoney.wallet_amount;

public class PaypalActivity extends AppCompatActivity {


    private String paymentAmount;
    public static final int PAYPAL_REQUEST_CODE = 123;
    private static PayPalConfiguration config = new PayPalConfiguration()

            .environment(PayPalConfiguration.ENVIRONMENT_PRODUCTION)
            .clientId(Config.PAYPAL_CLIENT_ID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        getAmount();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void getAmount() {

        SharedPreferences sharedPreferences = getSharedPreferences("paymentdetails", MODE_PRIVATE);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(sharedPreferences.getString("json", "asx"));
            paymentAmount = jsonObject.getString("amount");
//        name = jsonObject.getString("name");
//        email = jsonObject.getString("email");
//        mobile = jsonObject.getString("mobile");
//        address = jsonObject.getString("address");
//        pin = jsonObject.getString("address");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (PaymentTypeActivity.payment_for.matches("wallet")) {
            paymentAmount = wallet_amount;
        }

        PayPalPayment payment = new PayPalPayment(new BigDecimal(PayuActivity.amount), "USD", "Pay to Prashant Thakare",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
        finish();
    }

    private void doPayment() {

        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("paymentdetails", MODE_PRIVATE);


        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        JSONObject jsonObject1 = null;

        try {
//            jsonObject.put("id", userid);
//            jsonObject.put("password", pwd);
//            jsonObject.put("delivery_address", address);
//            jsonObject.put("delivery_pincode", pin);
//            jsonObject.put("order_type", paymentType);
//            jsonObject.put("address_id", selectedAddressId);
//            jsonObject.put("cart_ids", cartIds);
//            jsonObject.put("language_id", languageid);

            //************************
            jsonObject1 = new JSONObject(sharedPreferences.getString("json", ""));
            jsonObject1.put("business_id",IConstants.BUSINESS_ID);
//            jsonObject.put("id", userid);
//            jsonObject.put("password", pwd);
//            jsonObject.put("delivery_address", address_et.getText().toString());
//            jsonObject.put("order_type", paymentType);
//            jsonObject.put("cart_ids", cartIds);
//            jsonObject.put("address_id", selectedAddressId);
//            jsonObject.put("language_id", languageid);
//            jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
//            jsonObject.put("state_id", valuestate == 0 ? 1 : valuestate);
//            jsonObject.put("city_id", valueCity == 0 ? 1 : valueCity);
//            jsonObject.put("area_id", valueArea == 0 ? 1 : valueArea);
//            jsonObject.put("pincode_id", valuePincode == 0 ? 1 : valuePincode);
//            jsonObject.put("order_amount", amount_et.getText().toString());
//            jsonObject.put("order_description", comment.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("xzsdcxz" + jsonObject1);
        VolleyTask volleyTask = new VolleyTask(PaypalActivity.this, IConstants.URL_SUBMITCArt, jsonObject1, Request.Method.POST);
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
                                new CustomToast(PaypalActivity.this, response.getString("data"));
                                SharedPreferences preferences = getSharedPreferences("cheapstuffs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.apply();
                                Intent intent = new Intent(PaypalActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                startActivity(intent);
                                finish();
                            }
                        } else {
                            new CustomToast(PaypalActivity.this, response.getString("data"));

                            findViewById(R.id.whiteloader).setVisibility(View.GONE);

                        }

                    } else {
                        // new CustomToast(PayuActivity.this, "Product Submitted Sucessfully");
                        Dashboard.cart_count = 0;
                        Intent intent = new Intent(PaypalActivity.this, SuccessOrder.class);
                        finish();
                        stopService(new Intent(PaypalActivity.this, PayPalService.class));
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void fnErrorOccurred(String error) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PAYPAL_REQUEST_CODE) {

            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Log.i("paymentExample", paymentDetails);

                        doPayment();

                        //Starting a new activity for the payment details and also putting the payment details with intent
//                       startActivity(new Intent(this, ConfirmationActivity.class)
//                                .putExtra("PaymentDetails", paymentDetails)
//                                .putExtra("PaymentAmount", paymentAmount));

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }
}
