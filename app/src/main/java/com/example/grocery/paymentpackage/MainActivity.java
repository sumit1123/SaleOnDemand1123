package com.example.grocery.paymentpackage;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.example.grocery.Activities.CartActivity;
import com.example.grocery.Activities.Dashboard;
import com.example.grocery.Activities.LoginActivity;
import com.example.grocery.Activities.NotificationActivity;
import com.example.grocery.Activities.SuccessOrder;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.VolleyTask;
import com.google.android.material.textfield.TextInputLayout;
import com.payumoney.core.PayUmoneyConfig;
import com.payumoney.core.PayUmoneyConstants;
import com.payumoney.core.PayUmoneySdkInitializer;
import com.payumoney.core.entity.TransactionResponse;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;
import com.payumoney.sdkui.ui.utils.ResultModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.grocery.Activities.CartActivity.deliviry;
import static com.example.grocery.Activities.CartActivity.sum;
import static com.example.grocery.interfaces.IConstants.BASE_URL;
import static com.example.grocery.interfaces.IConstants.URL_GETUSERPROFILE;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity:";

    private String userMobile, userEmail;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private SharedPreferences userDetailsPreference;
    private EditText email_et, mobile_et, amount_et;
    private TextInputLayout email_til, mobile_til;
    private RadioGroup radioGroup_select_env;
    private TextView logoutBtn;
    private AppPreference mAppPreference;

    private Button payNowButton;
    private PayUmoneySdkInitializer.PaymentParam mPaymentParams;
    private Button cashon;
    private EditText address_et;
    private EditText pin_et;
    private EditText name_et;
    private int paymenttype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAppPreference = new AppPreference();

        settings = getSharedPreferences("settings", MODE_PRIVATE);
        email_et = (EditText) findViewById(R.id.email_et);
        address_et = (EditText) findViewById(R.id.address_et);
        pin_et = (EditText) findViewById(R.id.pin_et);
        name_et = (EditText) findViewById(R.id.name_et);
        TextView textView = (TextView) findViewById(R.id.actionbar_notifcation_textview);
        textView.setText(String.valueOf(Dashboard.cart_count));

        LinearLayout noti = (LinearLayout) findViewById(R.id.toolnotification);

        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                finish();
                startActivity(intent);
            }
        });

        ImageView cart = (ImageView) findViewById(R.id.toolcart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                finish();
                startActivity(intent);
            }
        });
        mobile_et = (EditText) findViewById(R.id.mobile_et);
        amount_et = (EditText) findViewById(R.id.amount_et);
        amount_et.setEnabled(false);

        amount_et.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(7, 2)});
        amount_et.setText(String.valueOf(sum - deliviry));
        email_til = (TextInputLayout) findViewById(R.id.email_til);
        mobile_til = (TextInputLayout) findViewById(R.id.mobile_til);

        TextView tc = (TextView) findViewById(R.id.titlebar);
        tc.setText("Delivery Details");


        payNowButton = (Button) findViewById(R.id.pay_now_button);
        payNowButton.setOnClickListener(this);
        cashon = (Button) findViewById(R.id.cashon);

        initListeners();

        //Set Up SharedPref
        setUpUserDetails();

        ((BaseApplication) getApplication()).setAppEnvironment(AppEnvironment.PRODUCTION);

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


    public static void setErrorInputLayout(EditText editText, String msg, TextInputLayout textInputLayout) {
        textInputLayout.setError(msg);
        editText.requestFocus();
    }

    public static boolean isValidEmail(String strEmail) {
        return strEmail != null && android.util.Patterns.EMAIL_ADDRESS.matcher(strEmail).matches();
    }

    public static boolean isValidPhone(String phone) {
        Pattern pattern = Pattern.compile(AppPreference.PHONE_PATTERN);

        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    private void setUpUserDetails() {
        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);


        Button imageView = (Button) findViewById(R.id.retrybutton);
        imageView.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String email = prefs.getString("email", "");
        String pwd = prefs.getString("pwd", "");
        VolleyTask volleyTask = new VolleyTask(this, URL_GETUSERPROFILE + "?business_id=" + IConstants.BUSINESS_ID + "id=" + userid + "&password=" + pwd, jsonObject, Request.Method.GET);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {

            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());
                try {
                    if (response.getString("status").matches("false")) {
                        if (response.has("auth")) {
                            if (response.getInt("auth") == 0) {
                                new CustomToast(MainActivity.this, response.getString("data"));
                                SharedPreferences preferences = getSharedPreferences("cheapstuffs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.apply();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                startActivity(intent);
                                finish();
                            }
                        } else {

                            new CustomToast(MainActivity.this, response.getString("data"));
                            findViewById(R.id.whiteloader).setVisibility(View.GONE);

                        }

                    } else {
                        JSONObject jsonObject2 = response.getJSONObject("data");
                        String name = jsonObject2.getString("name");
                        String number = jsonObject2.getString("contact_number");
                        String address = jsonObject2.getString("address");
                        String pincode = jsonObject2.getString("pincode");
                        String emailet = jsonObject2.getString("email");
                        address_et.setText(address);
                        name_et.setText(name);
                        mobile_et.setText(number);
                        pin_et.setText(pincode);
                        email_et.setText(emailet);
                        SharedPreferences.Editor editor = getSharedPreferences("usertable", MODE_PRIVATE).edit();
                        editor.putString("name", name);
                        editor.putString("address", address);
                        editor.putString("pincode", pincode);
                        editor.apply();
                        findViewById(R.id.whiteloader).setVisibility(View.GONE);

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
    protected void onResume() {
        super.onResume();
        payNowButton.setEnabled(true);


    }

    /**
     * This function sets the mode to PRODUCTION in Shared Preference
     */

    /**
     * This function sets the mode to SANDBOX in Shared Preference
     */


    /**
     * This function sets the layout for activity
     */
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result Code is -1 send from Payumoney activity
        Log.d("MainActivity", "request code " + requestCode + " resultcode " + resultCode);
        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data !=
                null) {
            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager
                    .INTENT_EXTRA_TRANSACTION_RESPONSE);

            ResultModel resultModel = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT);

            // Check which object is non-null
            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {
                    paymenttype = 2;
                    dopayment();

                } else {
                    new CustomToast(MainActivity.this, "An Error Occured While Completing Your Payment");


                }

                // Response from Payumoney
                String payuResponse = transactionResponse.getPayuResponse();

                // Response from SURl and FURL
                String merchantResponse = transactionResponse.getTransactionDetails();

                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setMessage("Payu's Data : " + payuResponse + "\n\n\n Merchant's Data: " + merchantResponse)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }).show();

            } else if (resultModel != null && resultModel.getError() != null) {
                Log.d(TAG, "Error response : " + resultModel.getError().getTransactionResponse());
            } else {
                Log.d(TAG, "Both objects are null!");
            }
        }
    }

    @Override
    public void onClick(View v) {
        userEmail = email_et.getText().toString().trim();
        userMobile = mobile_et.getText().toString().trim();
        switch (v.getId()) {
            case R.id.pay_now_button:
                payNowButton.setEnabled(false);
                launchPayUMoneyFlow();
                break;
            case R.id.cashon:
                paymenttype = 1;
                dopayment();


        }
    }

    private void dopayment() {
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("delivery_address", address_et.getText().toString());
            jsonObject.put("delivery_pincode", pin_et.getText().toString());
            jsonObject.put("order_type", paymenttype);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyTask volleyTask = new VolleyTask(MainActivity.this, IConstants.URL_SUBMITCArt, jsonObject, Request.Method.POST);
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
                                new CustomToast(MainActivity.this, response.getString("data"));
                                SharedPreferences preferences = getSharedPreferences("cheapstuffs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.apply();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                startActivity(intent);
                                finish();
                            }
                        } else {
                            new CustomToast(MainActivity.this, response.getString("data"));
                            findViewById(R.id.whiteloader).setVisibility(View.GONE);

                        }

                    } else {
                        new CustomToast(MainActivity.this, "Product Submitted Sucessfully");

                        Dashboard.cart_count = 0;
                        Intent intent = new Intent(MainActivity.this, SuccessOrder.class);
                        finish();
                        startActivity(intent);
                    }
                } catch (JSONException e) {


                }

            }

            @Override
            public void fnErrorOccurred(String error) {
            }
        });


    }

    private void initListeners() {
        email_et.addTextChangedListener(new EditTextInputWatcher(email_til));
        mobile_et.addTextChangedListener(new EditTextInputWatcher(mobile_til));
        cashon.setOnClickListener(this);

    }

    /**
     * This fucntion checks if email and mobile number are valid or not.
     *
     * @param email  email id entered in edit text
     * @param mobile mobile number entered in edit text
     * @return boolean value
     */
    public boolean validateDetails(String email, String mobile) {
        email = email.trim();
        mobile = mobile.trim();

        if (TextUtils.isEmpty(mobile)) {
            setErrorInputLayout(mobile_et, getString(R.string.err_phone_empty), mobile_til);
            return false;
        } else if (!isValidPhone(mobile)) {
            setErrorInputLayout(mobile_et, getString(R.string.err_phone_not_valid), mobile_til);
            return false;
        } else if (TextUtils.isEmpty(email)) {
            setErrorInputLayout(email_et, getString(R.string.err_email_empty), email_til);
            return false;
        } else if (!isValidEmail(email)) {
            setErrorInputLayout(email_et, getString(R.string.email_not_valid), email_til);
            return false;
        } else {
            return true;
        }
    }

    /**
     * This function prepares the data for payment and launches payumoney plug n play sdk
     */
    private void launchPayUMoneyFlow() {

        PayUmoneyConfig payUmoneyConfig = PayUmoneyConfig.getInstance();

        //Use this to set your custom text on result screen button


        PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();

        double amount = 0;
        try {
            amount = Double.parseDouble(amount_et.getText().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        String txnId = System.currentTimeMillis() + "";
        String phone = mobile_til.getEditText().getText().toString().trim();
        String productName = mAppPreference.getProductInfo();
        String firstName = mAppPreference.getFirstName();
        String email = email_til.getEditText().getText().toString().trim();
        String udf1 = address_et.getText().toString();
        String udf2 = pin_et.getText().toString();
        String udf3 = "";
        String udf4 = "";
        String udf5 = "";
        String udf6 = "";
        String udf7 = "";
        String udf8 = "";
        String udf9 = "";
        String udf10 = "";

        AppEnvironment appEnvironment = ((BaseApplication) getApplication()).getAppEnvironment();
        builder.setAmount(amount)
                .setTxnId(txnId)
                .setPhone(phone)
                .setProductName(productName)
                .setFirstName(firstName)
                .setEmail(email)
                .setsUrl(appEnvironment.surl())
                .setfUrl(appEnvironment.furl())
                .setUdf1(udf1)
                .setUdf2(udf2)
                .setUdf3(udf3)
                .setUdf4(udf4)
                .setUdf5(udf5)
                .setUdf6(udf6)
                .setUdf7(udf7)
                .setUdf8(udf8)
                .setUdf9(udf9)
                .setUdf10(udf10)
                .setIsDebug(appEnvironment.debug())
                .setKey(appEnvironment.merchant_Key())
                .setMerchantId(appEnvironment.merchant_ID());

        JSONObject jsonObject = new JSONObject();

        try {

            mPaymentParams = builder.build();
            jsonObject.put("key", appEnvironment.merchant_Key());
            jsonObject.put("amount", appEnvironment.merchant_Key());
            jsonObject.put("txnid", appEnvironment.merchant_Key());
            jsonObject.put("email", appEnvironment.merchant_Key());
            jsonObject.put("key", appEnvironment.merchant_Key());
            jsonObject.put("key", appEnvironment.merchant_Key());
            jsonObject.put("key", appEnvironment.merchant_Key());


            /*
            * Hash should always be generated from your server side.
            * */
            ;
            calculateServerSideHashAndInitiatePayment1(mPaymentParams);

        } catch (Exception e) {
            // some exception occurred
            new CustomToast(MainActivity.this, e.getMessage());

            payNowButton.setEnabled(true);
        }
    }

    private PayUmoneySdkInitializer.PaymentParam calculateServerSideHashAndInitiatePayment1(final PayUmoneySdkInitializer.PaymentParam paymentParam) {

        StringBuilder stringBuilder = new StringBuilder();
        HashMap<String, String> params = paymentParam.getParams();
        stringBuilder.append(params.get(PayUmoneyConstants.KEY) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.TXNID) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.AMOUNT) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.PRODUCT_INFO) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.FIRSTNAME) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.EMAIL) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF1) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF2) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF3) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF4) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF5) + "||||||");

        AppEnvironment appEnvironment = ((BaseApplication) getApplication()).getAppEnvironment();
        stringBuilder.append(appEnvironment.salt());

        String hash = hashCal(stringBuilder.toString());
        paymentParam.setMerchantHash(hash);
        if (hash.isEmpty() || hash.equals("")) {
            new CustomToast(MainActivity.this, "Could not generate hash");
        } else {
            mPaymentParams.setMerchantHash(hash);

            if (AppPreference.selectedTheme != -1) {

                PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, MainActivity.this, AppPreference.selectedTheme, mAppPreference.isOverrideResultScreen());
            } else {
                PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, MainActivity.this, R.style.AppTheme_default, mAppPreference.isOverrideResultScreen());
            }
        }
        return paymentParam;
    }

    /**
     * This method generates hash from server.
     *
     * @param paymentParam payments params used for hash generation
     */
    public void generateHashFromServer(PayUmoneySdkInitializer.PaymentParam paymentParam) {

        //nextButton.setEnabled(false); // lets not allow the user to click the button again and again.

        HashMap<String, String> params = paymentParam.getParams();

        // lets create the post params
        StringBuffer postParamsBuffer = new StringBuffer();
        postParamsBuffer.append(concatParams(PayUmoneyConstants.KEY, params.get(PayUmoneyConstants.KEY)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.AMOUNT, params.get(PayUmoneyConstants.AMOUNT)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.TXNID, params.get(PayUmoneyConstants.TXNID)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.EMAIL, params.get(PayUmoneyConstants.EMAIL)));
        postParamsBuffer.append(concatParams("productinfo", params.get(PayUmoneyConstants.PRODUCT_INFO)));
        postParamsBuffer.append(concatParams("firstname", params.get(PayUmoneyConstants.FIRSTNAME)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF1, params.get(PayUmoneyConstants.UDF1)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF2, params.get(PayUmoneyConstants.UDF2)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF3, params.get(PayUmoneyConstants.UDF3)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF4, params.get(PayUmoneyConstants.UDF4)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF5, params.get(PayUmoneyConstants.UDF5)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.SALT, "6IxXoECdKr"));
        String hashSequence = params.get(PayUmoneyConstants.KEY) + "/" + params.get(PayUmoneyConstants.TXNID) + "/" + params.get(PayUmoneyConstants.AMOUNT) + "/" + params.get(PayUmoneyConstants.PRODUCT_INFO) + "/" + params.get(PayUmoneyConstants.FIRSTNAME) + "/" + params.get(PayUmoneyConstants.EMAIL) + "/" + params.get(PayUmoneyConstants.UDF1) + "/" + params.get(PayUmoneyConstants.UDF2) + "/" + params.get(PayUmoneyConstants.UDF3) + "/" + params.get(PayUmoneyConstants.UDF4) + "/" + params.get(PayUmoneyConstants.UDF5) + "/" + "6IxXoECdKr";

        String postParams = postParamsBuffer.charAt(postParamsBuffer.length() - 1) == '&' ? postParamsBuffer.substring(0, postParamsBuffer.length() - 1).toString() : postParamsBuffer.toString();

        // lets make an api call
        GetHashesFromServerTask getHashesFromServerTask = new GetHashesFromServerTask();
        System.out.println("sazx" + hashSequence);

        getHashesFromServerTask.execute(hashSequence);
    }


    protected String concatParams(String key, String value) {
        return key + "=" + value + "&";
    }

    /**
     * This AsyncTask generates hash from server.
     */
    private class GetHashesFromServerTask extends AsyncTask<String, String, String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
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
            payNowButton.setEnabled(true);

            if (merchantHash.isEmpty() || merchantHash.equals("")) {
                new CustomToast(MainActivity.this, "Could not generate hash");
            } else {
                mPaymentParams.setMerchantHash(merchantHash);

                if (AppPreference.selectedTheme != -1) {

                    PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, MainActivity.this, AppPreference.selectedTheme, mAppPreference.isOverrideResultScreen());
                } else {
                    PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, MainActivity.this, R.style.AppTheme_default, mAppPreference.isOverrideResultScreen());
                }
            }
        }
    }

    public static class EditTextInputWatcher implements TextWatcher {

        private TextInputLayout textInputLayout;

        EditTextInputWatcher(TextInputLayout textInputLayout) {
            this.textInputLayout = textInputLayout;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().length() > 0) {
                textInputLayout.setError(null);
                textInputLayout.setErrorEnabled(false);
            }
        }
    }

    public class DecimalDigitsInputFilter implements InputFilter {

        Pattern mPattern;

        public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
            mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            Matcher matcher = mPattern.matcher(dest);
            if (!matcher.matches())
                return "";
            return null;
        }

    }
}
