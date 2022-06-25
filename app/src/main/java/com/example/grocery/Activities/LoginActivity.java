package com.example.grocery.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.UnderlineSpan;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.grocery.Appearances.Appearance;
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
import com.facebook.login.Login;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private AppCompatEditText number;
    private AppCompatEditText password;
    private FrameLayout buttonlayout;
    private TextView facebooktext;
    private TextView googletext;
    private TextView forgotButton;
    private TextView or;
    private GoogleApiClient mGoogleApiClient;
    private String TAG = "LOGIN";
    private static final int RC_SIGN_IN = 007;
    private CallbackManager callbackManager;
    private LinearLayout fb;
    private LoginButton loginButton;
    private String facebookName;
    private String facebookEmail;
    private String registertype;
    private String gmailpersonName;
    private String gmailemail;
    private int num;
    Toolbar toolbar;
    private int is_social = 0;
    public static int hideguest = 0;


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private Drawable eye;
    private boolean type;
    RelativeLayout bigtoolbar;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private String token;
    public static int demandLogin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        setContentView(R.layout.activity_login);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
      //  bigtoolbar = (RelativeLayout) findViewById(R.id.bigtoolbar);
      //  bigtoolbar.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));

        //  ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        //   TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        // String logins =  getString(R.string.login);
        //   toolbarTitle.setText(logins);

        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);
        //  toolbarTitle.setText("  " + Label.userLabel.getLogin());
        //  findViewById(R.id.icons).setVisibility(View.GONE);
        // findViewById(R.id.toolimage).setVisibility(View.GONE);
        buttonlayout=(FrameLayout) findViewById(R.id.buttonlayout);

      /*  if (Appearance.appSettings.getIs_social_authentication() == 1) {
            buttonlayout.setVisibility(View.VISIBLE);
        }

*/

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {

                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");
                    new CustomToast(LoginActivity.this, "Push notification: " + message);


                }
            }
        };



        //labelsShared = getSharedPreferences("labels", MODE_PRIVATE);
        ((ProgressBar) findViewById(R.id.blurredloaderprogressbar)).getIndeterminateDrawable().setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()), PorterDuff.Mode.MULTIPLY);

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
                //Log.d("AppLog", "key:" + FacebookSdk.getApplicationSignature(getApplicationContext())+"=");
                AppEventsLogger.activateApp(LoginActivity.this);
                loginButton.setReadPermissions("email");

            }
        });

        displayFirebaseRegId();

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
       /* boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));*/
        LinearLayout googleSignIn = (LinearLayout) findViewById(R.id.googleSignIn);
        googleSignIn.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#" + Appearance.appSettings.getApp_status_bar_color()));
        }

        facebooktext = (TextView) findViewById(R.id.facebooktext);
        googletext = (TextView) findViewById(R.id.googletext);
        or = (TextView) findViewById(R.id.or);
        TextView appname = (TextView) findViewById(R.id.appName);

        TextView tagline = (TextView) findViewById(R.id.tagLine);
        appname.setText(Appearance.appTranslation.getApp_name());
        tagline.setText(Appearance.appTranslation.getTag_line());
        Log.v("textcolor==",""+Appearance.appSettings.getText_color());
        appname.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        tagline.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        ImageView logo = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load(BASE_IMAGE_URL + Appearance.appSettings.getApp_logo()).into(logo);

        Button login = (Button) findViewById(R.id.continuue);
        TextView signup = (TextView) findViewById(R.id.btnsignup);
        TextView guestButton = (TextView) findViewById(R.id.guest);

        String continue_as_guest = getString(R.string.continue_as_guest);
        guestButton.setText(continue_as_guest);

        if (Appearance.appSettings.getIs_allow_guest() == 0 || LoginActivity.hideguest == 1) {
            guestButton.setVisibility(View.GONE);
        }

        login.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        signup.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        login.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

        guestButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));


        login.setBackground(LoginActivity.this.getResources().getDrawable(R.drawable.buttonshape));
        GradientDrawable bgShape = (GradientDrawable) login.getBackground();
        bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        login.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));


        forgotButton = (TextView) findViewById(R.id.forgotpassword);
        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
            }
        });
        forgotButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        SpannableString content = new SpannableString("I have forgotten my password?");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        forgotButton.setText(content);
        number = (AppCompatEditText) findViewById(R.id.number);
        password = (AppCompatEditText) findViewById(R.id.password);

        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.passwordlogin);
        //eyeWithStrike = VectorDrawableCompat.create(getResources(), R.drawable.ic_eye_strike, null).mutate();
//password.setCompoundDrawablesWithIntrinsicBounds(icon,0,0,0);
        new EditTextColorChanger(this, number);
        new EditTextColorChanger(this, password);
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
       /* DrawableContainer.DrawableContainerState dcs2 = (DrawableContainer.DrawableContainerState)bgShape2.getConstantState();
        Drawable[] drawableItems2 = dcs2.getChildren();
        GradientDrawable gradientDrawableChecked2 = (GradientDrawable)drawableItems2[0]; // item 1
        GradientDrawable gradientDrawableUnChecked2 = (GradientDrawable)drawableItems2[1];
        gradientDrawableChecked2.setStroke(3,Color.parseColor("#" + colorSettings.getString("text_color", "FFFFFF")));
        gradientDrawableUnChecked2.setStroke(1, Color.BLACK);*/
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number.getText().toString().matches("")) {
                    try {
                        number.requestFocus();
                        String err = getString(R.string.contact_number_empty);
                        number.setError(err);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                } else if (password.getText().toString().length() < 6 && password.isShown()) {
                    password.requestFocus();
                    String err = getString(R.string.password_length_should_be_8_characters);
                    number.setError(err);
                    // password.setError(Label.userLabel.getPassword_length_should_be_8_characters());
                } else if (password.getText().toString().matches("")) {
                    try {
                        password.requestFocus();
                        String err = getString(R.string.password_empty);
                        number.setError(err);
                        // password.setError(Label.userLabel.getPassword_empty());
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                } else {
                    sendReq();
                    /*Intent intent=new Intent(LoginActivity.this,Dashboard.class);
                    startActivity(intent);*/
                }

            }
        });
        try {
            String option = getString(R.string.or);

            or.setText(option);
            String  fb = getString(R.string.sign_up_with);
            String google = getString(R.string.sign_up);
            String signin = getString(R.string.sign_in);
            String forget_pass = getString(R.string.forgot_password);
            String mob = getString(R.string.mobile_number);
            String pass = getString(R.string.password);
            String email = getString(R.string.email);
            facebooktext.setText(fb);
            googletext.setText(fb);
            signup.setText(google);
            login.setText(signin);
            forgotButton.setText(forget_pass);
            number.setHint(email + "/" + mob);
            password.setHint(pass);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor1 = getSharedPreferences("cheapstuffs", MODE_PRIVATE).edit();
                editor1.putBoolean("login", false);
                editor1.apply();


                SharedPreferences.Editor editor = getSharedPreferences("GUESTSETTING", MODE_PRIVATE).edit();
                editor.putBoolean("is_guest_check", true);
                editor.apply();


                SharedPreferences.Editor user = getSharedPreferences("UserId", MODE_PRIVATE).edit();
                user.putString("user_id", token);
                user.apply();

                startActivity(new Intent(LoginActivity.this, Dashboard.class));
                finish();
                LoginActivity.hideguest = 1;
            }
        });

    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e("TOKEN", "Firebase reg id: " + regId);
        token = regId;
        if (!TextUtils.isEmpty(regId)) {
            System.out.println("Firebase Reg Id: " + regId);
        } else {
            System.out.println("Firebase Reg Id: " + "no");
        }
    }

    protected void sendReq() {
        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        if (isNetworkAvailable()) {
            JSONObject jsonObject = new JSONObject();
            SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
            String languageid = prefs.getString("language", String.valueOf(1));
            if (token == null) {
                token = "";
            }
            try {
                jsonObject.put("business_id", IConstants.BUSINESS_ID);
                jsonObject.put("username", number.getText().toString());
                jsonObject.put("password", password.getText().toString());
                jsonObject.put("language_id", languageid);
                jsonObject.put("token", token);
                jsonObject.put("source_id", 2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(jsonObject.toString());
            VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_LOGIN, jsonObject, Request.Method.POST);
            volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                @Override
                public void fnPostTaskCompleted(JSONArray response) {
                }

                @Override
                public void fnPostTaskCompletedJsonObject(JSONObject response) {
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                    if (!new ResponseHandler().validateResponse(LoginActivity.this, response)) {
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
                            editor.putString("username", jsonObject1.getString("name"));
                            editor.putString("email", user_name);
                            editor.putString("pwd", password.getText().toString());
                            editor.putInt("role_id", jsonObject1.getInt("role_id"));
                            //  editor.putString("pincode", pincode);
                            System.out.println("wesdx" + user_name + password.getText().toString());
                            editor.apply();

                            SharedPreferences.Editor editor1 = getSharedPreferences("cheapstuffs", MODE_PRIVATE).edit();
                            editor1.putBoolean("login", true);
                            editor1.apply();

                            guestSetting();

                        } else {
                            //  new CustomToast(LoginActivity.this, "Invailid Credintials");

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void fnErrorOccurred(String error) {

                    new CustomToast(LoginActivity.this, error);
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                }
            });
        } else {
            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
            new CustomToast(LoginActivity.this, "Connection Not Available");


        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
        System.out.println("sdxz" + requestCode + "dsx" + resultCode + data);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //finishAffinity();
        PaymentDetailsActivity.fromLogin = 1;
    }

    protected void sendreq() {

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
            System.out.println("djeajsixasdx" + token);
            if (registertype.matches("google")) {
                try {
                    jsonObject.put("business_id",IConstants.BUSINESS_ID);
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

                    Log.i(TAG, "sendreq: " + is_social);
                    Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (registertype.matches("facebook")) {
                try {
                    jsonObject.put("business_id",IConstants.BUSINESS_ID);
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


                    Log.i(TAG, "sendreq: " + is_social);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("Asxz" + jsonObject);
            VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_Register, jsonObject, Request.Method.POST);
            volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                @Override
                public void fnPostTaskCompleted(JSONArray response) {
                }

                @Override
                public void fnPostTaskCompletedJsonObject(JSONObject response) {
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                    if (!new ResponseHandler().validateResponse(LoginActivity.this, response)) {
                        return;
                    }

                    System.out.println("hhhs" + response.toString());
                    try {
                        String status = response.getString("status");
                        if (status.matches("true")) {
                            JSONObject jsonObject1 = response.getJSONObject("data").getJSONObject("data");
                            String s = jsonObject1.getString("id");
                            String user_name = jsonObject1.getString("email");
                            int role_id = jsonObject1.getInt("role_id");
                            //String pincode = jsonObject1.getString("pincode");
                            //String address=jsonObject1.getString("address");
                            SharedPreferences.Editor editor = getSharedPreferences("UserId", MODE_PRIVATE).edit();
                            editor.putString("user_id", s);
                            editor.putString("email", user_name);
                            editor.putInt("role_id", role_id);
                            if (registertype.matches("google") || registertype.matches("facebook")) {
                                editor.putString("username", registertype.matches("facebook") ? facebookName : gmailpersonName);

                                editor.putString("pwd", String.valueOf(num));
                            }

                            // editor.putString("pincode", pincode);


                            System.out.println("wesdx" + user_name + password.getText().toString());
                            editor.apply();


                            guestSetting();
                            /*SharedPreferences.Editor editor=getSharedPreferences("userpin",MODE_PRIVATE).edit();
                            editor.putString("pin",)*/
                        } else {
                            new CustomToast(LoginActivity.this, response.getString("data"));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void fnErrorOccurred(String error) {
                    new CustomToast(LoginActivity.this, error);
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                }
            });
        } else {
            new CustomToast(LoginActivity.this, "Connection Not Available");
            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
        }
    }

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
            Intent intent = new Intent(LoginActivity.this, Dashboard.class);
            intent.putExtra("token", token);
            startActivity(intent);
            finish();
        }
        LoginActivity.demandLogin = 0;
    }
}
