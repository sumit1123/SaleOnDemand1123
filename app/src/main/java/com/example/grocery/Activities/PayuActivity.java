package com.example.grocery.Activities;

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
import com.example.grocery.Labels.Label;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.paymentpackage.AppEnvironment;
import com.example.grocery.paymentpackage.AppPreference;
import com.example.grocery.paymentpackage.BaseActivity;
import com.example.grocery.paymentpackage.BaseApplication;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;
//import com.payumoney.core.PayUmoneyConfig;
//import com.payumoney.core.PayUmoneyConstants;
//import com.payumoney.core.PayUmoneySdkInitializer;
//import com.payumoney.core.entity.TransactionResponse;
//import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;
//import com.payumoney.sdkui.ui.utils.ResultModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;

import static com.example.grocery.Activities.Addmoney.wallet_amount;
import static com.example.grocery.Activities.CartActivity.cartIds;
import static com.example.grocery.Activities.PaymentDetailsActivity.selectedAddressId;
import static com.example.grocery.interfaces.IConstants.URL_ADDTOWALLET;

public class PayuActivity extends BaseActivity {
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
        setContentView(R.layout.activity_payu);
        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);

        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        setToolBar();
/*        SharedPreferences sharedPreferences = getSharedPreferences("paymentdetails", MODE_PRIVATE);

        try {
            jsonObject = new JSONObject(sharedPreferences.getString("json", "asx"));
            name = jsonObject.getString("name");
            mobile = jsonObject.getString("mobile");
            amount = jsonObject.getString("amount");
            address = jsonObject.getString("address");
            pin = jsonObject.getString("address");

            System.out.println(name + email + mobile + amount + address + pin);

        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        ((BaseApplication) getApplication()).setAppEnvironment(AppEnvironment.PRODUCTION);
        mAppPreference = new AppPreference();
        // launchPayUMoneyFlow(merchantId, merchantKey, saltKey);
        getData();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_payu;
    }


    public static String hashCal(String str) {
        byte[] hashseq = str.getBytes();
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();
            for (byte aMessageDigest : messageDigest) {
                String hex = Integer.toHexString(0xFF & aMessageDigest);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException ignored) {
        }
        return hexString.toString();
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
                    Intent intent = new Intent(PayuActivity.this, NotificationActivity.class);
                    startActivity(intent);
                }
            });
            RelativeLayout toolBarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
            toolBarSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PayuActivity.this, SearchActivity.class);
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
                    Intent intent = new Intent(PayuActivity.this, CartActivity.class);
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
                String title = getString(R.string.pay_u_money);
                toolbarTitle.setText(title);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        // Result Code is -1 send from Payumoney activity
        Log.d("MainActivity", "request code " + requestCode + " resultcode " + resultCode);
//        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data !=
//                null) {
//            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager
//                    .INTENT_EXTRA_TRANSACTION_RESPONSE);
//            ResultModel resultModel = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT);
//
//            // Check which object is non-null
//           // if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
//                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {
//                    if (PaymentTypeActivity.payment_for.matches("wallet")) {
//                        JSONObject jsonObject = new JSONObject();
//
//                        SharedPreferences prefs1 = getSharedPreferences("UserId", MODE_PRIVATE);
//
//                        String userid = prefs1.getString("user_id", "");
//                        String pwd = prefs1.getString("pwd", "");
//
//                        String languageid = prefs1.getString("language", String.valueOf(1));
//
//                        try {
//                            jsonObject.put("business_id",IConstants.BUSINESS_ID);
//                            jsonObject.put("narration", "Added.");
//                            jsonObject.put("transaction_against", "online");
//                            jsonObject.put("id", userid);
//                            jsonObject.put("password", pwd);
//                            jsonObject.put("amount", PayuActivity.amount);
//                            jsonObject.put("language_id", languageid);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        String url = URL_ADDTOWALLET;
//                        System.out.println("sdxz" + jsonObject);
//                        System.out.println("wedxs" + url.trim());
//                        VolleyTask volleyTask = new VolleyTask(PayuActivity.this, url, jsonObject, Request.Method.POST);
//                        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
//                            @Override
//                            public void fnPostTaskCompleted(JSONArray response) {
//                                System.out.println("sed" + response);
//                            }
//
//                            @Override
//                            public void fnPostTaskCompletedJsonObject(JSONObject response) {
//                                System.out.println("hhhs" + response.toString());
//
//
//                                if (!new ResponseHandler().validateResponse(PayuActivity.this, response)) {
//
//
//                                    return;
//                                }
//                                try {
//                                    //   displayInteger.setText("" + minimumInteger);
//                                    String jsonObject1 = response.getJSONObject("data").getString("msg");
//                                    new CustomToast(PayuActivity.this, jsonObject1);
///*                                    cart_count = response.getJSONObject("data").getJSONObject("data").getInt("cart_count");
//                                    new CartCountUtil(PayuActivity.this);*/
//                                    Intent intent = new Intent(PayuActivity.this, Dashboard.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(intent);
//
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//
//                            @Override
//                            public void fnErrorOccurred(String error) {
//
//                                new CustomToast(PayuActivity.this, "No connection Available");
//
//                            }
//                        });
//
//
//                    } else if (PaymentTypeActivity.payment_for.matches("cart_order")) {
//                        paymentType = 2;
//                        doPayment();
//                    }
//                }else {
//                    Intent intent = new Intent(PayuActivity.this, SuccessOrder.class);
//                    finish();
//                    startActivity(intent);
//
//                }
//
//                // Response from Payumoney
//                String payuResponse = transactionResponse.getPayuResponse();
//
//                // Response from SURl and FURL
//                String merchantResponse = transactionResponse.getTransactionDetails();
//
//              /*  new AlertDialog.Builder(this)
//                        .setCancelable(false)
//                        .setMessage(ffinish"Payu's Data : " + payuResponse + "\n\n\n Merchant's Data: " + merchantResponse)
//                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                dialog.dismiss();
//                            }
//                        }).show();
//*/
//          /*   else if (resultModel != null && resultModel.getError() != null) {
//                Log.d("dgf", "Error response : " + resultModel.getError().getTransactionResponse());
//            } else {
//                Log.d("Rfdc", "Both objects are null!");
//            }*/
//        } else {
//            finish();
//        }
    }

    private void launchPayUMoneyFlow() {

//        PayUmoneyConfig payUmoneyConfig = PayUmoneyConfig.getInstance();
//        PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();

        if (pin==null){
            pin="1";
        }
        String txnId = System.currentTimeMillis() + "";
        String phone = PayuActivity.mobile;
        String productName = mAppPreference.getProductInfo();
        String firstName = mAppPreference.getFirstName();
        String email_payu = email;
        String udf1 = address;
        String udf2 = pin;
        String udf3 = "";
        String udf4 = "";
        String udf5 = "";
        String udf6 = "";
        String udf7 = "";
        String udf8 = "";
        String udf9 = "";
        String udf10 = "";
//        if (PaymentTypeActivity.payment_for.matches("wallet")) {
//            AppEnvironment appEnvironment = ((BaseApplication) getApplication()).getAppEnvironment();
//            builder.setAmount(String.valueOf(amount))
//                    .setTxnId(txnId)
//                    .setPhone(phone)
//                    .setProductName(productName)
//                    .setFirstName(firstName)
//                    .setEmail(email_payu)
//                    .setsUrl(appEnvironment.surl())
//                    .setfUrl(appEnvironment.furl())
//                    .setUdf1(udf1)
//                    .setUdf2(udf2)
//                    .setUdf3(udf3)
//                    .setUdf4(udf4)
//                    .setUdf5(udf5)
//                    .setUdf6(udf6)
//                    .setUdf7(udf7)
//                    .setUdf8(udf8)
//                    .setUdf9(udf9)
//                    .setUdf10(udf10)
//                    .setIsDebug(appEnvironment.debug())
//                    .setKey(merchantKey)
//                    .setMerchantId(merchantId);
//        }
//        if (PaymentTypeActivity.payment_for.matches("cart_order")) {
//            AppEnvironment appEnvironment = ((BaseApplication) getApplication()).getAppEnvironment();
//            builder.setAmount(String.valueOf(amount))
//                    .setTxnId(txnId)
//                    .setPhone(phone)
//                    .setProductName(productName)
//                    .setFirstName(firstName)
//                    .setEmail(email_payu)
//                    .setsUrl(appEnvironment.surl())
//                    .setfUrl(appEnvironment.furl())
//                    .setUdf1(udf1)
//                    .setUdf2(udf2)
//                    .setUdf3(udf3)
//                    .setUdf4(udf4)
//                    .setUdf5(udf5)
//                    .setUdf6(udf6)
//                    .setUdf7(udf7)
//                    .setUdf8(udf8)
//                    .setUdf9(udf9)
//                    .setUdf10(udf10)
//                    .setIsDebug(appEnvironment.debug())
//                    .setKey(merchantKey)
//                    .setMerchantId(merchantId);
//        }
//
//        //  System.out.println("dscx"+merchantKey+merchantId+saltKey+"efds"+appEnvironment.merchant_Key()+appEnvironment.merchant_ID()+appEnvironment.salt());
//
//        JSONObject jsonObject = new JSONObject();
//
//        try {
//
//            mPaymentParams = builder.build();
//
//
//            /*
//            * Hash should always be generated from your server side.
//            * */
//            ;
//            calculateServerSideHashAndInitiatePayment1(mPaymentParams);
//
//        } catch (Exception e) {
//            // some exception occurred
//           // Toast.makeText(this, "Money added", Toast.LENGTH_SHORT).show();
//            new CustomToast(PayuActivity.this, e.getMessage());
//            System.out.println("sdxz" + e.getMessage());
//        }
    }

//    private PayUmoneySdkInitializer.PaymentParam calculateServerSideHashAndInitiatePayment1(final PayUmoneySdkInitializer.PaymentParam paymentParam) {
//
//        StringBuilder stringBuilder = new StringBuilder();
//        HashMap<String, String> params = paymentParam.getParams();
//        stringBuilder.append(params.get(PayUmoneyConstants.KEY) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.TXNID) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.AMOUNT) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.PRODUCT_INFO) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.FIRSTNAME) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.EMAIL) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF1) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF2) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF3) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF4) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF5) + "||||||");
//
//        AppEnvironment appEnvironment = ((BaseApplication) getApplication()).getAppEnvironment();
//        stringBuilder.append(saltKey);
//
//        String hash = hashCal(stringBuilder.toString());
//        System.out.println("wdsxz" + hash);
//        paymentParam.setMerchantHash(hash);
//        if (hash.isEmpty() || hash.equals("")) {
//            //new CustomToast(PayuActivity.this, "Could not generate hash");
//
//        } else {
//            mPaymentParams.setMerchantHash(hash);
//
//            //  finish();
//            PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, PayuActivity.this, R.style.ToolBarStyle, true);
//        }
//        return paymentParam;
//    }


    /* private class GetHashesFromServerTask extends AsyncTask<String, String, String> {
         private ProgressDialog progressDialog;

         @Override
         protected void onPreExecute() {
             super.onPreExecute();
             progressDialog = new ProgressDialog(PayuActivity.this);
             progressDialog.setMessage("Please wait...");
             progressDialog.show();
         }

         @Override
         protected String doInBackground(String... postParams) {

             String merchantHash = "";
             try {
                 //TODO Below url is just for testing purpose, merchant needs to replace this with their server side hash generation url
                 URL url = new URL(BASE_URL + "generateHash");

                 String postParam = postParams[0];

                 byte[] postParamsByte = postParam.getBytes("UTF-8");

                 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                 conn.setRequestMethod("POST");
                 conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                 conn.setRequestProperty("Content-Length", String.valueOf(postParamsByte.length));
                 conn.setDoOutput(true);
                 conn.getOutputStream().write(postParamsByte);

                 InputStream responseInputStream = conn.getInputStream();
                 StringBuffer responseStringBuffer = new StringBuffer();
                 byte[] byteContainer = new byte[1024];
                 for (int i; (i = responseInputStream.read(byteContainer)) != -1; ) {
                     responseStringBuffer.append(new String(byteContainer, 0, i));
                 }
                 String response = responseStringBuffer.toString();
                 System.out.println("drfxcx" + response);
                 merchantHash = response;


             } catch (MalformedURLException e) {
                 e.printStackTrace();
             } catch (ProtocolException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             }
             return merchantHash;
         }

         @Override
         protected void onPostExecute(String merchantHash) {
             super.onPostExecute(merchantHash);

             progressDialog.dismiss();

             if (merchantHash.isEmpty() || merchantHash.equals("")) {
                 new CustomToast(PayuActivity.this, "Could not generate hash");
             } else {
                 mPaymentParams.setMerchantHash(merchantHash);

                 if (AppPreference.selectedTheme != -1) {

                     PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, PayuActivity.this, AppPreference.selectedTheme, mAppPreference.isOverrideResultScreen());
                 } else {
                     PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, PayuActivity.this, R.style.AppTheme_default, mAppPreference.isOverrideResultScreen());
                 }
             }
         }}*/

    private void doPayment() {
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("paymentdetails", MODE_PRIVATE);


        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        JSONObject jsonObject1 = null;

        try {

         //   jsonObject.put("id", userid);
          //  jsonObject.put("password", pwd);
          //  jsonObject.put("delivery_address", address);
          //  jsonObject.put("delivery_pincode", pin);
          //  jsonObject.put("order_type", paymentType);
         //   jsonObject.put("address_id", selectedAddressId);
         //   jsonObject.put("cart_ids", cartIds);
          //  jsonObject.put("language_id", languageid);

           // Toast.makeText(this, ""+userid, Toast.LENGTH_SHORT).show();

            //************************

            jsonObject1 = new JSONObject(sharedPreferences.getString("json",""));
            jsonObject1.put("business_id",IConstants.BUSINESS_ID);
   //         jsonObject.put("id", userid);
     //      jsonObject.put("password", pwd);
     //      jsonObject.put("delivery_address", address_et.getText().toString());
     //      jsonObject.put("order_type", paymentType);
     //      jsonObject.put("cart_ids", cartIds);
      //     jsonObject.put("address_id", selectedAddressId);
 //          jsonObject.put("language_id", languageid);
 //          jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
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
        VolleyTask volleyTask = new VolleyTask(PayuActivity.this, IConstants.URL_SUBMITCArt, jsonObject1, Request.Method.POST);
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
                                new CustomToast(PayuActivity.this, response.getString("data"));
                                SharedPreferences preferences = getSharedPreferences("cheapstuffs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.apply();
                                Intent intent = new Intent(PayuActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            new CustomToast(PayuActivity.this, response.getString("data"));
                            findViewById(R.id.whiteloader).setVisibility(View.GONE);

                        }

                    } else {
                        // new CustomToast(PayuActivity.this, "Product Submitted Sucessfully");

                        Dashboard.cart_count = 0;
                      //  Intent intent = new Intent(PayuActivity.this, SuccessOrder.class);
                      //  finish();
                       // stopService(new Intent(PayuActivity.this, PayUmoneyConfig.class));
                      //  startActivity(intent);

                    }
                } catch (JSONException e) {

                }

            }

            @Override
            public void fnErrorOccurred(String error) {

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
        VolleyTask volleyTask = new VolleyTask(PayuActivity.this, IConstants.URL_GETPAYUMONEYDETAILS, jsonObject, Request.Method.POST);
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
                                new CustomToast(PayuActivity.this, response.getString("data"));
                                SharedPreferences preferences = getSharedPreferences("cheapstuffs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.apply();
                                Intent intent = new Intent(PayuActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            new CustomToast(PayuActivity.this, response.getString("data"));
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
                        launchPayUMoneyFlow();

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
        findViewById(R.id.whiteloader).setVisibility(View.GONE);
        new CartCountUtil(PayuActivity.this);

    }

  /*  @Override
    public void onBackPressed() {
        super.onBackPressed();
    }*/
}
