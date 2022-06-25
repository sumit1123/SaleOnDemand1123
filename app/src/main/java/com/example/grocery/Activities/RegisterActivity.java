package com.example.grocery.Activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.grocery.Activities.Dashboard;
import com.example.grocery.Activities.LoginActivity;
import com.example.grocery.Activities.OtpVeriFicationActivity;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Model.AreaModel;
import com.example.grocery.Model.CityModel;
import com.example.grocery.Model.CountriesModel;
import com.example.grocery.Model.PincodeModel;
import com.example.grocery.Model.StateModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.Config;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.NotificationUtils;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.VolleyTask;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;


public class RegisterActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {
    private AppCompatEditText username;
    private AppCompatEditText email;
    private AppCompatEditText contactnumber;
    private AppCompatEditText password;
    private AppCompatEditText address;
    private AppCompatEditText referrel;
    private FrameLayout buttonlayout;
    private Spinner pin;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private AppCompatEditText dateView;
    private AppCompatEditText gstnumber;
    private GoogleApiClient mGoogleApiClient;
    private String TAG = "fdcx";
    private static final int RC_SIGN_IN = 007;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private LinearLayout fb;
    private SharedPreferences mPrefs;
    private String registertype;
    private String gmailpersonName;
    private String gmailemail;
    private String facebookName;
    private String facebookEmail;
    private TextView ref;
    private int num;
    //private SharedPreferences labelsShared;
    private TextView facebooktext;
    private TextView googletext;
    private TextView signInButton;
    private Button signUpButton;
    TextView guestButton;
    private TextView or;
    private Spinner city;
    private Spinner state;
    private Spinner country;
    private String my_var;
    private ArrayList<String> languages;
    private List<CountriesModel> listCountries;
    private List<StateModel> listStates;
    private List<CityModel> listCities;
    private int valueCountry;
    private int valuestate;
    private int valueCity;
    private ArrayList<String> strings2;
    private ArrayList<String> strings;
    private ArrayList<String> strings3;
    private boolean myCountries;
    private boolean myStates;
    private List<PincodeModel> listPincodes;
    private int valuePincode;
    private Spinner area;
    private List<AreaModel> listAreas;
    private int valueArea;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    public static String token;
    Toolbar toolbar;
    RelativeLayout bigtoolbar;
    private boolean type;
    private RelativeLayout citySignUprel;
    private RelativeLayout stateSignUprel;
    private RelativeLayout areaSignUprel;
    private RelativeLayout countrySignUprel;
    private RelativeLayout pinsignuprel;
    private RelativeLayout passwordsignuprel;
    private int is_social = 0;
    private int is_referral = 0;
    private SharedPreferences sharedUserId;
    private String app_text_color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_register);
        String reg = getString(R.string.register);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
       // bigtoolbar = (RelativeLayout) findViewById(R.id.bigtoolbar);
      //  bigtoolbar.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));


        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    //  displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");
                    new CustomToast(RegisterActivity.this, "Push notification: " + message);
                }
            }
        };
        strings = new ArrayList<>();
        strings2 = new ArrayList<>();
        strings3 = new ArrayList<>();
        languages = new ArrayList<>();
        languages.add("android");
        languages.add("jave");
        languages.add("ios");
        languages.add("dotnet");

        ((ProgressBar) findViewById(R.id.blurredloaderprogressbar)).getIndeterminateDrawable().setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()), PorterDuff.Mode.MULTIPLY);
        facebooktext = (TextView) findViewById(R.id.facebooktext);
        googletext = (TextView) findViewById(R.id.googletext);
        or = (TextView) findViewById(R.id.orText);
        disconnectFromFacebook();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        callbackManager = CallbackManager.Factory.create();
        fb = (LinearLayout) findViewById(R.id.facebookLogin);
        fb.setOnClickListener(this);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FacebookSdk.sdkInitialize(getApplicationContext());
                AppEventsLogger.activateApp(RegisterActivity.this);
                loginButton.setReadPermissions("email");
            }
        });

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {
                System.out.println("loginresult" + "sxcz");
            }

            @Override
            public void onError(FacebookException exception) {
                System.out.println("loginresult" + "sdxz");
            }
        });
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        GraphRequest data_request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject json_object,
                                            GraphResponse response) {

                                        try {
                                            facebookName = json_object.getString("name");
                                            facebookEmail = json_object.getString("email");
                                            registertype = "facebook";
                                            is_social = 1;
                                            sendreq();

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }

                                });
                        Bundle permission_param = new Bundle();
                        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
                        data_request.setParameters(permission_param);
                        data_request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onError(FacebookException exception) {
                    }
                });

        LinearLayout googleSignIn = (LinearLayout) findViewById(R.id.googleSignIn);
        googleSignIn.setOnClickListener(this);

        getData();

        dateView = (AppCompatEditText) findViewById(R.id.dateofBirth);
        new EditTextColorChanger(this, dateView);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        // showDate(year, month+1, day);
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(dateView);
            }
        });
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#" + Appearance.appSettings.getApp_status_bar_color()));
        }
        TextView appname = (TextView) findViewById(R.id.appName);
        TextView tagline = (TextView) findViewById(R.id.tagLine);
        appname.setText(Appearance.appTranslation.getApp_name());
        tagline.setText(Appearance.appTranslation.getTag_line());
        appname.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        tagline.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        ImageView logo = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load(BASE_IMAGE_URL + Appearance.appSettings.getApp_logo()).into(logo);

        ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.CALL_PHONE},
                1);
        username = (AppCompatEditText) findViewById(R.id.usernamesignup);
        email = (AppCompatEditText) findViewById(R.id.emailsignup);
        contactnumber = (AppCompatEditText) findViewById(R.id.numbersignup);
        password = (AppCompatEditText) findViewById(R.id.passwordsignup);
        address = (AppCompatEditText) findViewById(R.id.addresssignup);
        referrel = (AppCompatEditText) findViewById(R.id.referrel);
        buttonlayout = (FrameLayout) findViewById(R.id.buttonlayout);

        final ImageView imageView = (ImageView) findViewById(R.id.eyedrawableold);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    password.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD);*/
                if (!type) {
                    type = true;
                    imageView.setImageResource(R.drawable.removedeye);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    password.setSelection(password.getText().toString().length());

                } else {
                    imageView.setImageResource(R.drawable.eye);
                    type = false;
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password.setSelection(password.getText().toString().length());
                }
            }
        });
        gstnumber = (AppCompatEditText) findViewById(R.id.gstnumber);
        citySignUprel = (RelativeLayout) findViewById(R.id.citySignUprel);
        areaSignUprel = (RelativeLayout) findViewById(R.id.areaSignUprel);
        stateSignUprel = (RelativeLayout) findViewById(R.id.stateSignUprel);
        countrySignUprel = (RelativeLayout) findViewById(R.id.countrySignUprel);
        pinsignuprel = (RelativeLayout) findViewById(R.id.pinsignuprel);
        passwordsignuprel = (RelativeLayout) findViewById(R.id.passwordsignuprel);
        ref = (TextView) findViewById(R.id.ref);
        city = (Spinner) findViewById(R.id.citySignUp);
        area = (Spinner) findViewById(R.id.areaSignUp);
        state = (Spinner) findViewById(R.id.stateSignUp);
        guestButton = (TextView) findViewById(R.id.guest);
        pin = (Spinner) findViewById(R.id.pinsignup);
        country = (Spinner) findViewById(R.id.countrySignUp);
        new EditTextColorChanger(this, username);
        new EditTextColorChanger(this, email);
        new EditTextColorChanger(this, contactnumber);
        new EditTextColorChanger(this, password);
        new EditTextColorChanger(this, address);
        new EditTextColorChanger(this, gstnumber);
        signInButton = (TextView) findViewById(R.id.registersignin);
        signUpButton = (Button) findViewById(R.id.registersignup);
        guestButton = (TextView) findViewById(R.id.guest);
        Log.v("textcolor=", "" + "#" + Appearance.appSettings.getText_color());
        guestButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        String guest = getString(R.string.guest);
        guestButton.setText(guest);
        if (Appearance.appSettings.getIs_allow_guest() == 0 || LoginActivity.hideguest == 1) {
            guestButton.setVisibility(View.GONE);
        }
        signUpButton.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        signUpButton.setBackground(RegisterActivity.this.getResources().getDrawable(R.drawable.buttonshape));
        GradientDrawable bgShape = (GradientDrawable) signUpButton.getBackground();
        bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        signUpButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));


        signInButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        signUpButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (username.getText().toString().matches("") && username.isShown()) {
                        username.requestFocus();
                        String err = getString(R.string.name_empty);
                        username.setError(err);

                    } else if (contactnumber.getText().toString().matches("") && contactnumber.isShown()) {
                        contactnumber.requestFocus();
                        String err = getString(R.string.contact_number_empty);
                        contactnumber.setError(err);

                        // contactnumber.setError(Label.userLabel.getContact_number_empty());
                    } else if (contactnumber.getText().toString().length() > 10 && contactnumber.isShown()) {
                        contactnumber.requestFocus();
                        String err = getString(R.string.please_enter_a_valid_number);
                        contactnumber.setError(err);

                        //contactnumber.setError(Label.userLabel.getPlease_enter_a_valid_number());
                    } else if (email.getText().toString().matches("") && email.isShown()) {
                        email.requestFocus();
                        String err = getString(R.string.email_empty);
                        email.setError(err);

                        //   email.setError(Label.userLabel.getEmail_empty());
                    } else if (!emailValidator(email.getText().toString()) && email.isShown()) {
                        email.requestFocus();
                        email.setError("Valid Email");
                    } else if (password.getText().toString().matches("") && password.isShown()) {
                        password.requestFocus();
                        String err = getString(R.string.password_empty);
                        password.setError(err);

                        // password.setError(Label.userLabel.getPassword_empty());
                    } else if (dateView.getText().toString().matches("") && dateView.isShown()) {
                        String err = getString(R.string.dob_empty);
                        dateView.setError(err);
                        // dateView.setError(Label.userLabel.getDob_empty());
                    } else if (password.getText().toString().length() < 6 && password.isShown()) {
                        password.requestFocus();
                        String err = getString(R.string.password_length_should_be_8_characters);
                        password.setError(err);

                        //password.setError(Label.userLabel.getPassword_length_should_be_8_characters());
                    } else if (address.getText().toString().matches("") && address.isShown()) {
                        address.requestFocus();
                        String err = getString(R.string.address_empty);
                        address.setError(err);

                        //  address.setError(Label.userLabel.getAddress_empty());
                    } else if (valueCountry == 0 && country.isShown()) {
                        new CustomToast(RegisterActivity.this, "Please select country from drop down");
                    } else if (valuestate == 0 && state.isShown()) {
                        new CustomToast(RegisterActivity.this, "Please select state from drop down");
                    } else if (valueCity == 0 && city.isShown()) {
                        new CustomToast(RegisterActivity.this, "Please select city from drop down");
                    } else if (pin.getSelectedItemPosition() == 0 && pin.isShown()) {
                        System.out.println("valueCity" + valueCity);
                        String pin = getString(R.string.pincode_empty);
                        new CustomToast(RegisterActivity.this, pin);
                    } else if (valueArea == 0 && area.isShown()) {
                        new CustomToast(RegisterActivity.this, "please Select Area");

                    } else if (gstnumber.getText().toString().matches("") && gstnumber.isShown()) {
                        gstnumber.requestFocus();
                        String gst = getString(R.string.please_enter_GST_number);

                        gstnumber.setError(gst);
                    } else {

                        registertype = "normal";

                        sendreq();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("GUESTSETTING", MODE_PRIVATE).edit();
                editor.putBoolean("is_guest_check", true);

                SharedPreferences.Editor user = getSharedPreferences("UserId", MODE_PRIVATE).edit();
                user.putString("user_id", token);
                editor.apply();
                user.apply();

                Log.i("1234", "onClick: " + token);
                String aa = getSharedPreferences("UserId", MODE_PRIVATE).getString("user_id", "");
                Log.i("12345", "onClick: " + aa);

                SharedPreferences.Editor editor1 = getSharedPreferences("cheapstuffs", MODE_PRIVATE).edit();
                editor1.putBoolean("login", false);
                editor1.apply();

                startActivity(new Intent(RegisterActivity.this, Dashboard.class));
                finish();
                LoginActivity.hideguest = 1;
            }
        });

        try {

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    protected void sendreq() {

        if (token == null) {
            token = "";
        }

        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        if (isNetworkAvailable()) {
            final JSONObject jsonObject = new JSONObject();
            SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
            String languageid = prefs.getString("language", String.valueOf(1));
            if (registertype.matches("google") || registertype.matches("facebook")) {
                Random rand = new Random();
                num = rand.nextInt(89999999) + 10000000;
            } else {

            }
            if (registertype.matches("google")) {
                try {
                    jsonObject.put("business_id", IConstants.BUSINESS_ID);
                    jsonObject.put("email", gmailemail);
                    jsonObject.put("contact_number", gmailemail);
                    jsonObject.put("name", gmailpersonName);
                    jsonObject.put("password", num);
                    jsonObject.put("address", "");
                    jsonObject.put("pincode_id", 1);
                    jsonObject.put("dob", "");
                    jsonObject.put("gst_number", "");
                    jsonObject.put("country_id", 1);
                    jsonObject.put("state_id", 1);
                    jsonObject.put("city_id", 1);
                    jsonObject.put("area_id", 1);
                    jsonObject.put("token", token);
                    jsonObject.put("language_id", languageid);
                    jsonObject.put("is_social", is_social);
                    jsonObject.put("source_id", 2);
//                    Log.i("111email", gmailemail);
//                    Log.i("111contact_number", gmailemail);
//                    Log.i("111name", gmailpersonName);
//                    Log.i("111password", "" + num);
//                    Log.i("111address", "");
//                    Log.i("111pincode_id", "" + 1);
//                    Log.i("111dob", "");
//                    Log.i("111gst_number", "");
//                    Log.i("111country_id", "" + 1);
//                    Log.i("111state_id", "" + 1);
//                    Log.i("111city_id", "" + 1);
//                    Log.i("111area_id", "" + 1);
//                    Log.i("111token", token);
//                    Log.i("111language_id", languageid);
//                    Log.i("111is_social", "" + is_social);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (registertype.matches("facebook")) {
                try {
                    jsonObject.put("business_id", IConstants.BUSINESS_ID);
                    jsonObject.put("email", facebookEmail);
                    jsonObject.put("contact_number", facebookEmail);
                    jsonObject.put("name", facebookName);
                    jsonObject.put("password", num);
                    jsonObject.put("address", "");
                    jsonObject.put("pincode_id", 1);
                    jsonObject.put("dob", "");
                    jsonObject.put("gst_number", "");
                    jsonObject.put("country_id", 1);
                    jsonObject.put("state_id", 1);
                    jsonObject.put("city_id", 1);
                    jsonObject.put("area_id", 1);
                    jsonObject.put("token", token);
                    jsonObject.put("language_id", languageid);
                    jsonObject.put("is_social", is_social);
                    jsonObject.put("source_id", 2);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    jsonObject.put("business_id", IConstants.BUSINESS_ID);
                    jsonObject.put("email", email.isShown() ? email.getText().toString() : contactnumber.getText().toString());
                    jsonObject.put("contact_number", contactnumber.getText().toString());
                    jsonObject.put("name", username.getText().toString());
                    jsonObject.put("password", password.getText().toString());
                    jsonObject.put("address", address.getText().toString());
                    jsonObject.put("reffered_by", referrel.getText().toString());
                    jsonObject.put("pincode_id", valuePincode == 0 ? 1 : valuePincode);
                    jsonObject.put("dob", dateView.getText().toString());
                    jsonObject.put("gst_number", gstnumber.getText().toString());
                    jsonObject.put("msg_sender_id", Appearance.appSettings.getMsg_sender_id());
                    jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
                    jsonObject.put("state_id", valuestate == 0 ? 1 : valuestate);
                    jsonObject.put("city_id", valueCity == 0 ? 1 : valueCity);
                    jsonObject.put("area_id", valueArea == 0 ? 1 : valueArea);
                    jsonObject.put("token", token);
                    jsonObject.put("language_id", languageid);
                    jsonObject.put("is_social", is_social);
                    jsonObject.put("source_id", 2);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.i("Asxz", "" + jsonObject.toString());
            VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_Register, jsonObject, Request.Method.POST);
            // Toast.makeText(RegisterActivity.this, "Invalid Refferal code", Toast.LENGTH_SHORT).show();
            volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                @Override
                public void fnPostTaskCompleted(JSONArray response) {
                }

                @Override
                public void fnPostTaskCompletedJsonObject(JSONObject response) {
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                    if (!new ResponseHandler().validateResponse(RegisterActivity.this, response)) {
                        return;

                    }

                    System.out.println("hhhs" + response.toString());
                    try {
                        String status = response.getString("status");
                        if (status.matches("true")) {
                            JSONObject jsonObject1 = response.getJSONObject("data").getJSONObject("data");
                            String s = jsonObject1.getString("id");
                            String user_name = jsonObject1.getString("email");
                            // String pincode = jsonObject1.getString("pincode");
                            //String address=jsonObject1.getString("address");
                            SharedPreferences.Editor editor = getSharedPreferences("UserId", MODE_PRIVATE).edit();
                            editor.putString("user_id", s);
                            editor.putString("email", user_name);
                            if (registertype.matches("google") || registertype.matches("facebook")) {
                                editor.putString("username", registertype.matches("facebook") ? facebookName : gmailpersonName);

                                editor.putString("pwd", String.valueOf(num));
                            } else {
                                editor.putString("pwd", password.getText().toString());
                                editor.putString("username", username.getText().toString());

                            }

                            System.out.println("wesdx" + user_name + password.getText().toString());
                            editor.apply();


                            if (Appearance.appSettings.getIs_otp() == 1 && !registertype.matches("google") && !registertype.matches("facebook")) {
                                Intent intent = new Intent(RegisterActivity.this, OtpVeriFicationActivity.class);
                                startActivity(intent);
                                finish();


                            } else {

                                guestSetting();
//                                SharedPreferences.Editor editor1 = getSharedPreferences("cheapstuffs", MODE_PRIVATE).edit();
//                                editor1.putBoolean("login", true);
//                                editor1.apply();
//                                Intent intent = new Intent(RegisterActivity.this, Dashboard.class);
//                                startActivity(intent);
//                                finish();

                            }
                            /*SharedPreferences.Editor editor=getSharedPreferences("userpin",MODE_PRIVATE).edit();
                            editor.putString("pin",)*/
                        } else {

                            new CustomToast(RegisterActivity.this, response.getString("data"));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void fnErrorOccurred(String error) {
                    new CustomToast(RegisterActivity.this, error);
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                }
            });
        } else {
            new CustomToast(RegisterActivity.this, "Connection Not Available");
            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(RegisterActivity.this,
//                            new String[]{Manifest.permission.READ_SMS},
//                            2);
                    //  Toast.makeText(LoginActivity.this, "Permission for Read external storage has been granted ", Toast.LENGTH_SHORT).show();

                } else {
                    // Toast.makeText(this, "You need to give this permission before entering in this app", Toast.LENGTH_SHORT).show();
                    // finishAffinity();

                }
                return;
            }
            case 2: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {


                    Toast.makeText(RegisterActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }

            }


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    public void getData() {
        //   Toast.makeText(this, "dcxkzlk", Toast.LENGTH_SHORT).show();
        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);

        findViewById(R.id.retryImage).setVisibility(View.GONE);
        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String languageid = prefs.getString("language", String.valueOf(1));
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("language_id", languageid);
            jsonObject.put("token", token);
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_REGISTERFORM_SETTINGS, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                try {

                    JSONObject jsonObject = response.getJSONObject("data").getJSONObject("data").getJSONObject("registration_setting");
                    JSONArray jsonArrayCountries = response.getJSONObject("data").getJSONObject("data").getJSONArray("countries");
                    JSONArray jsonArrayareas = response.getJSONObject("data").getJSONObject("data").getJSONArray("areas");

                    JSONArray jsonArrayStates = response.getJSONObject("data").getJSONObject("data").getJSONArray("states");
                    JSONArray jsonArrayCity = response.getJSONObject("data").getJSONObject("data").getJSONArray("cities");
                    JSONArray jsonArrayPincode = response.getJSONObject("data").getJSONObject("data").getJSONArray("pincodes");
                    getPinCodes(jsonArrayPincode, jsonArrayareas);
                    getCountries(jsonArrayCountries, jsonArrayStates, jsonArrayCity);
                    if (jsonObject.getInt("is_name") == 1) {
                        username.setVisibility(View.VISIBLE);
                    }
                    if (jsonObject.getInt("is_email") == 1) {
                        email.setVisibility(View.VISIBLE);
                    }
                    if (jsonObject.getInt("is_DOB") == 1) {
                        dateView.setVisibility(View.VISIBLE);
                    }
                    if (jsonObject.getInt("is_password") == 1) {
                        passwordsignuprel.setVisibility(View.VISIBLE);
                    }
                    if (jsonObject.getInt("is_refferal") == 1) {
                        ref.setVisibility(View.VISIBLE);
                        referrel.setVisibility(View.VISIBLE);
                    }
                    if (jsonObject.getInt("is_address") == 1) {
                        address.setVisibility(View.VISIBLE);
                    }
                    if (jsonObject.getInt("is_pincode") == 1) {
                        pinsignuprel.setVisibility(View.VISIBLE);
                    } else {
                        getArea(jsonArrayareas);
                    }
                    if (jsonObject.getInt("is_gst_number") == 1) {
                        gstnumber.setVisibility(View.VISIBLE);
                    }
                    if (jsonObject.getInt("is_contact_number") == 1) {
                        contactnumber.setVisibility(View.VISIBLE);
                    }

                    if (jsonObject.getInt("is_country") == 1) {
                        country.setVisibility(View.VISIBLE);
                    } else {
                        getStates(jsonArrayStates, jsonArrayCity);

                    }

                    if (jsonObject.getInt("is_state") == 1) {
                        stateSignUprel.setVisibility(View.VISIBLE);
                    } else {
                        getCity(jsonArrayCity);
                    }
                    if (jsonObject.getInt("is_city") == 1) {
                        citySignUprel.setVisibility(View.VISIBLE);
                    }
                    if (jsonObject.getInt("is_area") == 1) {
                        areaSignUprel.setVisibility(View.VISIBLE);
                    }

                    if (Appearance.appSettings.getIs_social_authentication() == 1) {
                        buttonlayout.setVisibility(View.VISIBLE);
                    }


                    username.requestFocus();

                    findViewById(R.id.whiteloader).setVisibility(View.GONE);


                } catch (JSONException e) {

                    e.printStackTrace();
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
                                    getData();
                                }
                            });
                        }
                    }, 2000);
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
                                getData();
                            }
                        });
                    }
                }, 2000);
            }
        });
    }

    private void getArea(JSONArray jsonArrayarea) {
        Gson gson = new Gson();
        listAreas = Arrays.asList(gson.fromJson(jsonArrayarea.toString(), AreaModel[].class));

        if (listAreas.size() == 0) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add("Select Area");
            final ArrayAdapter adapter10 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, strings);
            valueArea = 0;
            area.setAdapter(adapter10);
        } else {
            System.out.println("dsxz" + listAreas.size());
            ArrayList<String> strings = new ArrayList<>();
            strings.add("Select Area");
            for (int j = 0; j < listAreas.size(); j++) {

                if (listAreas.get(j).getArea_translation() == null) {
                    strings.add(listAreas.get(j).getDefault_area_translation().getArea());
                } else {
                    strings.add(listAreas.get(j).getArea_translation().getArea());
                }
                System.out.println("areasd" + strings);
            }

            final ArrayAdapter adapter10 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, strings);
            area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == 0) {
                        valueArea = 0;
                    } else {
                        valueArea = listAreas.get(i - 1).getArea_id();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            area.setAdapter(adapter10);
        }
    }


    private void getPinCodes(JSONArray jsonArrayPincode, JSONArray jsonArrayareas) {
        Gson gson = new Gson();
        listPincodes = Arrays.asList(gson.fromJson(jsonArrayPincode.toString(), PincodeModel[].class));
        listAreas = Arrays.asList(gson.fromJson(jsonArrayareas.toString(), AreaModel[].class));

        final ArrayList<String> pincodeArray = new ArrayList<>();
        pincodeArray.add("Select PinCode");
        for (int i = 0; i < listPincodes.size(); i++) {
            pincodeArray.add(listPincodes.get(i).getPincode());
        }

        pin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {

                    valuePincode = listPincodes.get(i - 1).getPincode_id();
                    final List<AreaModel> areaModels = new ArrayList<>();
                    for (int j = 0; j < listAreas.size(); j++) {
                        if (listAreas.get(j).getPincode_id() == valuePincode) {
                            areaModels.add(listAreas.get(j));
                        }
                    }
                    if (areaModels.size() == 0) {
                        ArrayList<String> strings = new ArrayList<>();
                        strings.add("Select Area");
                        final ArrayAdapter adapter10 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, strings);
                        valueArea = 0;
                        area.setAdapter(adapter10);
                    } else {
                        System.out.println("dsxz" + areaModels.size());
                        ArrayList<String> strings = new ArrayList<>();
                        for (int j = 0; j < areaModels.size(); j++) {

                            if (areaModels.get(j).getArea_translation() == null) {
                                strings.add(areaModels.get(j).getDefault_area_translation().getArea());
                            } else {
                                strings.add(areaModels.get(j).getArea_translation().getArea());
                            }
                            System.out.println("areasd" + strings);
                        }
                        final ArrayAdapter adapter10 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, strings);
                        area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                valueCity = areaModels.get(i).getArea_id();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        area.setAdapter(adapter10);
                    }
                } else {
                    ArrayList<String> strings = new ArrayList<>();
                    strings.add("Select Area");
                    final ArrayAdapter adapter10 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, strings);
                    valueArea = 0;
                    area.setAdapter(adapter10);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pincodeArray);

        pin.setAdapter(adapter);
    }

    private void getCountries(JSONArray jsonArray, JSONArray jsonArrayStates, JSONArray jsonArrayCity) {

        ArrayList<String> strings1 = new ArrayList<>();
        strings1.add("Select City");
        ArrayList<String> strings2 = new ArrayList<>();
        strings2.add("Select State");
        valueCity = 0;
        valueCountry = 0;
        valuestate = 0;
        final ArrayAdapter adapter5 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings1);
        final ArrayAdapter adapter6 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings2);
        city.setAdapter(adapter5);
        state.setAdapter(adapter6);

        Gson gson = new Gson();
        listCountries = Arrays.asList(gson.fromJson(jsonArray.toString(), CountriesModel[].class));
        listStates = Arrays.asList(gson.fromJson(jsonArrayStates.toString(), StateModel[].class));
        listCities = Arrays.asList(gson.fromJson(jsonArrayCity.toString(), CityModel[].class));

        strings.add("Select Country");
        for (int i = 0; i < listCountries.size(); i++) {

            if (listCountries.get(i).getCountry_translation() == null) {
                strings.add(listCountries.get(i).getDefault_country_translation().getCountry_name());
            } else {
                strings.add(listCountries.get(i).getCountry_translation().getCountry_name());
            }
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    city.setAdapter(null);
                    state.setAdapter(null);
                    valueCountry = listCountries.get(i - 1).getCountry_id();
                    final List<StateModel> strings1 = new ArrayList<>();
                    for (int j = 0; j < listStates.size(); j++) {
                        if (listStates.get(j).getCountry_id() == valueCountry) {

                            strings1.add(listStates.get(j));
                        }
                    }
                    ArrayList<String> stateString = new ArrayList<>();
                    for (int j = 0; j < strings1.size(); j++) {
                        if (strings1.get(j).getState_translation() == null) {
                            stateString.add(strings1.get(j).getDefault_state_translation().getState_name());

                        } else {
                            stateString.add(strings1.get(j).getState_translation().getState_name());

                        }
                    }
                    if (strings1.size() == 0) {


                        ArrayList<String> strings2 = new ArrayList<>();
                        strings2.add("Select State");
                        valueCity = 0;
                        valuestate = 0;
                        final ArrayAdapter adapter6 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, strings2);
                        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                ArrayList<String> strings5 = new ArrayList<>();
                                valueCity = 0;

                                strings5.add("Select City");
                                final ArrayAdapter adapter5 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, strings5);
                                city.setAdapter(adapter5);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        state.setAdapter(adapter6);
                    } else {

                        final ArrayAdapter adapter2 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, stateString);
                        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                valuestate = strings1.get(i).getState_id();
                                final List<CityModel> cityModels = new ArrayList<>();
                                for (int j = 0; j < listCities.size(); j++) {
                                    if (listCities.get(j).getState_id() == valuestate) {
                                        cityModels.add(listCities.get(j));
                                    }
                                }
                                if (cityModels.size() == 0) {
                                    valueCity = 0;
                                }
                                ArrayList<String> strings = new ArrayList<>();
                                for (int j = 0; j < cityModels.size(); j++) {
                                    if (cityModels.get(j).getCity_translation() == null) {
                                        strings.add(cityModels.get(j).getDefault_city_translation().getCity_name());
                                    } else {
                                        strings.add(cityModels.get(j).getCity_translation().getCity_name());

                                    }
                                }
                                if (cityModels.size() == 0) {
                                    ArrayList<String> strings5 = new ArrayList<>();
                                    strings5.add("Select City");

                                    valueCity = 0;

                                    final ArrayAdapter adapter5 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, strings5);
                                    city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            valueCity = 0;
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    city.setAdapter(adapter5);
                                } else {
                                    final ArrayAdapter adapter3 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, strings);
                                    city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            valueCity = cityModels.get(i).getCity_id();
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    city.setAdapter(adapter3);


                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                        state.setAdapter(adapter2);
                    }
                } else {

                    city.setAdapter(null);
                    state.setAdapter(null);

                    valueCity = 0;
                    valuestate = 0;

                    ArrayList<String> strings2 = new ArrayList<>();
                    strings2.add("Select State");
                    final ArrayAdapter adapter6 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, strings2);
                    state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            ArrayList<String> strings5 = new ArrayList<>();
                            strings5.add("Select City");
                            valueCity = 0;

                            final ArrayAdapter adapter5 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, strings5);
                            city.setAdapter(adapter5);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    state.setAdapter(adapter6);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

        country.setAdapter(adapter);
        System.out.println("sdxz" + strings2);

        if (state.getAdapter() == null) {
            city.setAdapter(null);
        }
    }


    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(year).append("-")
                .append(month).append("-").append(day));
    }

    private void signIn() {
        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            gmailpersonName = acct.getDisplayName();
            // String personPhotoUrl = acct.getPhotoUrl().toString();
            gmailemail = acct.getEmail();
            registertype = "google";
            is_social = 1;
            sendreq();
     /*       Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);*/

            // txtName.setText(personName);
            //txtEmail.setText(email);
           /* Glide.with(getApplicationContext()).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProfilePic);
*/
            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.googleSignIn:

                signIn();
                break;
            case R.id.facebookLogin:
                loginButton.performClick();
                break;
           /* case R.id.btn_sign_out:
                signOut();
                break;

            case R.id.btn_revoke_access:
                revokeAccess();
                break;*/
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }
    }
/*
    @Override
    public void onStart() {
        super.onStart();
*//*
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
           // showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                   // hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }*//*
    }*/

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

   /* private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("loading");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }*/

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
           /* btnSignIn.setVisibility(View.GONE);
            btnSignOut.setVisibility(View.VISIBLE);
            btnRevokeAccess.setVisibility(View.VISIBLE);
            llProfileLayout.setVisibility(View.VISIBLE);*/
        } else {
            /*btnSignIn.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.GONE);
            btnRevokeAccess.setVisibility(View.GONE);
            llProfileLayout.setVisibility(View.GONE);*/
        }
    }

    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }

    public void getStates(JSONArray jsonArrayStates, JSONArray jsonArrayCity) {

        Gson gson = new Gson();
        listStates = Arrays.asList(gson.fromJson(jsonArrayStates.toString(), StateModel[].class));
        listCities = Arrays.asList(gson.fromJson(jsonArrayCity.toString(), CityModel[].class));

        final List<StateModel> strings1 = new ArrayList<>();

        ArrayList<String> stateString = new ArrayList<>();
        stateString.add("Select State");
        for (int j = 0; j < listStates.size(); j++) {
            if (listStates.get(j).getState_translation() == null) {
                stateString.add(listStates.get(j).getDefault_state_translation().getState_name());

            } else {
                stateString.add(listStates.get(j).getState_translation().getState_name());

            }


            final ArrayAdapter adapter2 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, stateString);
            state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == 0) {
                        valuestate = 0;
                    } else {
                        valuestate = listStates.get(i - 1).getState_id();
                        final List<CityModel> cityModels = new ArrayList<>();
                        for (int j = 0; j < listCities.size(); j++) {
                            if (listCities.get(j).getState_id() == valuestate) {
                                cityModels.add(listCities.get(j));
                            }
                        }
                        if (cityModels.size() == 0) {
                            valueCity = 0;
                        }
                        ArrayList<String> strings = new ArrayList<>();
                        for (int j = 0; j < cityModels.size(); j++) {
                            if (cityModels.get(j).getCity_translation() == null) {
                                strings.add(cityModels.get(j).getDefault_city_translation().getCity_name());
                            } else {
                                strings.add(cityModels.get(j).getCity_translation().getCity_name());

                            }
                        }
                        if (cityModels.size() == 0) {
                            ArrayList<String> strings5 = new ArrayList<>();
                            strings5.add("Select City");

                            valueCity = 0;

                            final ArrayAdapter adapter5 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, strings5);
                            city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    valueCity = 0;
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });
                            city.setAdapter(adapter5);
                        } else {
                            final ArrayAdapter adapter3 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, strings);
                            city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    valueCity = cityModels.get(i).getCity_id();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });
                            city.setAdapter(adapter3);


                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            state.setAdapter(adapter2);

        }
    }

    private void getCity(JSONArray jsonArrayCity) {


        Gson gson = new Gson();
        listCities = Arrays.asList(gson.fromJson(jsonArrayCity.toString(), CityModel[].class));
        final List<CityModel> strings1 = new ArrayList<>();

        ArrayList<String> cityString = new ArrayList<>();
        cityString.add("Select City");
        for (int j = 0; j < listCities.size(); j++) {
            if (listCities.get(j).getCity_translation() == null) {

                cityString.add(listCities.get(j).getDefault_city_translation().getCity_name());

            } else {
                cityString.add(listCities.get(j).getCity_translation().getCity_name());

            }
            final ArrayAdapter adapter6 = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, cityString);
            city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    valueCity = listCities.get(i).getCity_id();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            city.setAdapter(adapter6);
        }
    }

 /*   private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e("bhjjk", "Firebase reg id: " + regId);
        token = regId;
        if (!TextUtils.isEmpty(regId)) {
            System.out.println("Firebase Reg Id: " + regId);
        } else {
            System.out.println("Firebase Reg Id: " + "no");
        }
    }*/

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    public void guestSetting() {

        SharedPreferences.Editor editor1 = getSharedPreferences("cheapstuffs", MODE_PRIVATE).edit();
        editor1.putBoolean("login", true);
        editor1.apply();

        SharedPreferences.Editor editor2 = getSharedPreferences("GUESTSETTING", MODE_PRIVATE).edit();
        editor2.putBoolean("is_guest_check", false);
        editor2.apply();

        Dashboard.passedOnCreate = 1;

        if (LoginActivity.demandLogin == 1) {
            finish();
        } else {
            Intent intent = new Intent(RegisterActivity.this, Dashboard.class);
            intent.putExtra("token", token);
            startActivity(intent);
            finish();
        }
        LoginActivity.demandLogin = 0;
    }

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}


