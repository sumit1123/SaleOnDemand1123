package com.example.grocery.Activities;

 import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Appearances.ApplicationAppearances;
import com.example.grocery.BuildConfig;
import com.example.grocery.CustomViews.FontSetter;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.Config;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.VolleyTask;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import static com.example.grocery.CustomViews.FontSetter.FONTNAME;
import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2500;
    ProgressBar circularImage;
    //    private SharedPreferences sharedSettings;
    private SharedPreferences sharedUserId;
    private int currentVersion;
    private int versionCode;
    private int is_allow_guest;
    public static String packagename;
    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
       // displayFirebaseRegId();

        Button retryButton = findViewById(R.id.retrybutton);
        GradientDrawable bgShape = (GradientDrawable) retryButton.getBackground();
        bgShape.setColor(Color.parseColor("#C5C5C5"));
        getData();
        //getAppKeyHash();

    }

   /* private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e("bhjjk", "Firebase reg id: " + regId);
        token = regId;
        if (!TextUtils.isEmpty(regId)) {
            System.out.println("Firebase Reg Id: " + regId);
        } else {
            System.out.println("Firebase Reg Id: " + "no");
        }
    }
*/
    protected void getData() {
        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        findViewById(R.id.retryImage).setVisibility(View.GONE);
        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);
        sharedUserId = getSharedPreferences("UserId", MODE_PRIVATE);
        JSONObject jsonObject = new JSONObject();

        try {
//            jsonObject.put("language_id", sharedUserId.getString("language", "1"));
            jsonObject.put("language_id", 1);
            jsonObject.put("business_id", IConstants.BUSINESS_ID);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_SETTINGS, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());
                try {
                    JSONObject jsonObject = response.getJSONObject("data").getJSONObject("data").getJSONObject("application_appearance");
                    SharedPreferences.Editor editor = getSharedPreferences("APPSETTINGS", MODE_PRIVATE).edit();
                    editor.putString("app_text_color", jsonObject.getString("app_text_color"));
                    editor.putString("app_back_color", jsonObject.getString("app_back_color"));
                    editor.putString("text_color", jsonObject.getString("text_color"));
                    editor.putString("app_status_bar_color", jsonObject.getString("app_status_bar_color"));
                    editor.putString("splash_text_color", jsonObject.getString("splash_text_color"));
                    //editor.putString("is_social_authentication", jsonObject.getString("is_social_authentication"));
//                    editor.putBoolean("is_color_saved",true );
                    editor.apply();

                    /*editor.putInt("show_cart_button", jsonObject.getInt("show_cart_button"));
                    editor.putInt("show_category_type", jsonObject.getInt("show_category_type"));
                    editor.putInt("show_recent_view_product", jsonObject.getInt("show_recent_view_product"));
                    editor.putInt("is_multivendor", jsonObject.getInt("is_multivendor"));
                    editor.putInt("is_wallet", jsonObject.getInt("is_wallet"));
                    editor.putInt("is_otp", jsonObject.getInt("is_otp"));
                    editor.putInt("is_gst", jsonObject.getInt("is_gst"));
                    editor.putInt("show_cart_button", jsonObject.getInt("show_cart_button"));
                    editor.putInt("is_otp", jsonObject.getInt("is_otp"));
                    editor.putString("app_logo", jsonObject.getString("app_logo"));
                    editor.putString("msg_sender_id", jsonObject.getString("msg_sender_id"));
                    editor.putInt("is_pincode", jsonObject.getInt("is_pincode"));
                    editor.putInt("is_slider_effect", jsonObject.getInt("is_slider_effect"));
                    editor.putString("location", jsonObject.getString("business_location"));
                    editor.putString("contact_number", jsonObject.getString("contact_number"));
                    editor.putString("business_email", jsonObject.getString("business_email"));
                    editor.putString("default_rating", jsonObject.getString("default_rating"));
                    editor.putString("toast_back_color", jsonObject.getString("toast_back_color"));
                    editor.putString("toast_text_color", jsonObject.getString("toast_text_color"));
                    editor.putString("is_multilanguage", jsonObject.getString("is_multilanguage"));
                    editor.putInt("is_slider", jsonObject.getInt("is_slider"));
                    editor.putInt("show_recent_view_product", jsonObject.getInt("show_recent_view_product"));
                    editor.putInt("show_grid_category", jsonObject.getInt("show_grid_category"));
                    editor.putInt("is_notification", jsonObject.getInt("is_notification"));
                    editor.putInt("show_new_arrival_product", jsonObject.getInt("show_new_arrival_product"));
                    editor.putInt("show_max_view_product", jsonObject.getInt("show_max_view_product"));
                    editor.putInt("show_featured_brands", jsonObject.getInt("show_featured_brands"));
                    editor.putInt("is_promocode", jsonObject.getInt("is_promocode"));
                    editor.putInt("min_order_amount", jsonObject.getInt("min_order_amount"));*/

                    Appearance.appSettings = new Gson().fromJson(jsonObject.toString(), ApplicationAppearances.class);


                    if (!jsonObject.get("appearance_translation").equals(null)) {
                        Appearance.appTranslation = new Gson().fromJson(jsonObject.getJSONObject("appearance_translation").toString(),
                                ApplicationAppearances.AppearanceTranslationBean.class);
                    } else {
                        ApplicationAppearances.DefaultAppearanceTranslationBean oo = new Gson()
                                .fromJson(jsonObject.getJSONObject("default_appearance_translation").toString(),
                                        ApplicationAppearances.DefaultAppearanceTranslationBean.class);
                        Appearance.appTranslation = new ApplicationAppearances.AppearanceTranslationBean(oo);
                        String s = Appearance.appTranslation.getApp_name();

                    }

                    try {
                        FontSetter.setFont((jsonObject.getInt("font_id")));
                    } catch (Exception e) {
                        FontSetter.setFont(2);
                    }

                    /*
                    if (jsonObject.get("facebook").equals(null)) {
                        editor.putString("facebook", "null");
                    } else {
                        editor.putString("facebook", jsonObject.getString("facebook"));
                    }
                    if (jsonObject.get("twitter").equals(null)) {
                        editor.putString("twitter", "null");
                    } else {
                        editor.putString("twitter", jsonObject.getString("twitter"));
                    }
                    if (jsonObject.get("google").equals(null)) {
                        editor.putString("google", "null");
                    } else {
                        editor.putString("google", jsonObject.getString("google"));

                    }
                    editor.putInt("is_flag", jsonObject.getInt("show_product_type"));

                    //**** Rajat
                    editor.putInt("is_allow_guest", jsonObject.getInt("is_allow_guest"));
                    */
                    is_allow_guest = (jsonObject.getInt("is_allow_guest"));

                    Log.i("is_allow_guest", "fnPostTaskCompletedJsonObject: " + is_allow_guest);

                    // relativeLayout.setBackgroundColor(Color.parseColor("#" + jsonObject.getString("app_back_color")));
                    TextView textView = (TextView) findViewById(R.id.appName);
                    TextView tagLine = (TextView) findViewById(R.id.tagLine);
                    textView.setTextColor(Color.parseColor("#" + jsonObject.getString("splash_text_color")));
                    tagLine.setTextColor(Color.parseColor("#" + jsonObject.getString("splash_text_color")));


                 /*   JSONObject product_labels = response.getJSONObject("data").getJSONObject("data").getJSONObject("product_labels");
                    JSONObject navigationLabels = response.getJSONObject("data").getJSONObject("data").getJSONObject("navigation_labels");
                    JSONObject wallet_labels = response.getJSONObject("data").getJSONObject("data").getJSONObject("wallet_labels");
                    JSONObject cart_labels = response.getJSONObject("data").getJSONObject("data").getJSONObject("cart_labels");
                    JSONObject complaint_labels = response.getJSONObject("data").getJSONObject("data").getJSONObject("complaint_labels");
                    JSONObject gateway_labels = response.getJSONObject("data").getJSONObject("data").getJSONObject("gateway_labels");
                    JSONObject notification_labels = response.getJSONObject("data").getJSONObject("data").getJSONObject("notification_labels");
                    JSONObject global_labels = response.getJSONObject("data").getJSONObject("data").getJSONObject("global_labels");
                    JSONObject user_labels = response.getJSONObject("data").getJSONObject("data").getJSONObject("user_labels");
                    JSONObject wishlist_labels = response.getJSONObject("data").getJSONObject("data").getJSONObject("wishlist_labels");
                    JSONObject order_labels = response.getJSONObject("data").getJSONObject("data").getJSONObject("order_labels");
                    JSONObject promocode_labels = response.getJSONObject("data").getJSONObject("data").getJSONObject("promocode_labels");


                    setlabels(navigationLabels, cart_labels, wallet_labels,
                            complaint_labels, gateway_labels, product_labels,
                            notification_labels, global_labels, user_labels,
                            wishlist_labels, order_labels, promocode_labels);
                    Log.i("getData", "setlable1_1137");*/


                    findViewById(R.id.whiteloader).setVisibility(View.GONE);
                    RelativeLayout relativeLayout = findViewById(R.id.rel_layout);
                    Picasso.with(SplashScreen.this).load(BASE_IMAGE_URL + jsonObject.getString("app_logo")).into((ImageView) findViewById(R.id.splash));



                    if (jsonObject.get("appearance_translation").equals(null)) {
                        jsonObject = jsonObject.getJSONObject("default_appearance_translation");
                    } else {
                        jsonObject = jsonObject.getJSONObject("appearance_translation");
                    }

                    /*
                    editor.putString("app_name", jsonObject.getString("app_name"));
                    editor.putString("tag_line", jsonObject.getString("tag_line"));
                    editor.putString("currency", jsonObject.getString("currency"));
                    editor.apply();
                    */

                    Typeface typeface = null;
                    try {
                        typeface = Typeface.createFromAsset(getAssets(), FONTNAME);
                    } catch (Exception e) {
                        Log.e("Splash", "Unable to load typeface: " + e.getMessage());
                    }

                    textView.setTypeface(typeface);
                    tagLine.setTypeface(typeface);

                    textView.setText(jsonObject.getString("app_name"));
                    tagLine.setText(jsonObject.getString("tag_line"));
                    versionCode = BuildConfig.VERSION_CODE;
                    currentVersion = response.getJSONObject("data").getJSONObject("data").getJSONObject("business").getInt("version");
                   packagename = response.getJSONObject("data").getJSONObject("data").getJSONObject("business").getString("package_name");
                  //  packagename ="com.salesondemand.ecom";
                    Log.d("current version=",""+currentVersion);
                    Log.d("packagename",""+packagename);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            if (currentVersion > versionCode) {
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + packagename));
                                startActivity(intent);
                            } else {
                                SharedPreferences prefs = getSharedPreferences("cheapstuffs", MODE_PRIVATE);
                                SharedPreferences guest = getSharedPreferences("GUESTSETTING", MODE_PRIVATE);
//                                if (prefs.contains("login")) {
                                if (prefs.contains("login") || (is_allow_guest == 1 && guest.contains("is_guest_check"))) {
                                    Intent intent = new Intent(SplashScreen.this, Dashboard.class);
                                    startActivity(intent);
                                } else {
                                    SharedPreferences.Editor editor1 = getSharedPreferences("cheapstuffs", MODE_PRIVATE).edit();
                                    editor1.putBoolean("login", false);
                                    editor1.apply();


                                    SharedPreferences.Editor editor = getSharedPreferences("GUESTSETTING", MODE_PRIVATE).edit();
                                    editor.putBoolean("is_guest_check", true);
                                    editor.apply();


                                    SharedPreferences.Editor user = getSharedPreferences("UserId", MODE_PRIVATE).edit();
                                    user.putString("user_id", token);
                                    user.apply();

                                    startActivity(new Intent(SplashScreen.this, Dashboard.class));
                                    finish();
                                    LoginActivity.hideguest = 1;
                                }
                            }
                        }
                    }, SPLASH_TIME_OUT);
                } catch (JSONException e) {

                    e.printStackTrace();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            findViewById(R.id.retryImage).setVisibility(View.VISIBLE);

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

   /* private void setlabels(JSONObject navigationLabels, JSONObject cart_labels, JSONObject wallet_labels,
                           JSONObject complaint_labels, JSONObject gateway_labels, JSONObject product_labels,
                           JSONObject notification_labels, JSONObject global_labels, JSONObject user_labels,
                           JSONObject wishlist_labels, JSONObject order_labels, JSONObject promocode_labels) {

        Label.cartLabel = new Gson().fromJson(cart_labels.toString(), CartLabel.class);
        Label.navigationLabels = new Gson().fromJson(navigationLabels.toString(), NavigationLabels.class);
        Label.productLabel = new Gson().fromJson(product_labels.toString(), ProductLabel.class);
        Label.wishlistLabel = new Gson().fromJson(wishlist_labels.toString(), WishlistLabel.class);
        Label.orderLabel = new Gson().fromJson(order_labels.toString(), OrderLabel.class);
        Label.globalLabel = new Gson().fromJson(global_labels.toString(), GlobalLabel.class);
        Label.userLabel = new Gson().fromJson(user_labels.toString(), UserLabel.class);
        Label.notificationLabel = new Gson().fromJson(notification_labels.toString(), NotificationLabel.class);
        Label.complaintLabel = new Gson().fromJson(complaint_labels.toString(), ComplaintLabel.class);
        Label.walletLabels = new Gson().fromJson(wallet_labels.toString(), WalletLabels.class);
        Label.gatewayLabel = new Gson().fromJson(gateway_labels.toString(), GatewayLabel.class);
        Label.promocodeLabel = new Gson().fromJson(promocode_labels.toString(), PromocodeLabel.class);
    }
}*/
}