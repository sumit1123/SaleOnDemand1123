package com.example.grocery.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.squareup.picasso.Picasso;

import androidx.cardview.widget.CardView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.Appearances.Appearance.appSettings;
import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;
import static com.example.grocery.interfaces.IConstants.URL_GETUSERPROFILE;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {
    private Dialog dialog;
    private String text;
    private TextView accountName;
    private TextView accountNumber;
    private TextView accntpin;
    private LinearLayout allOrders;
    private EditText old, newpa;
    private LinearLayout cart, wishlist, logout;
    private TextView cartCount;
    private ImageView acccntimmage;
    boolean shouldExecuteOnResume;
    private LinearLayout myaddresses;
    private TextView changePasswordText, updateProfileText, myOrderText, myCartText, myaddressesText, myWishText, logoutText,refIdText;
    //private SharedPreferences labelsShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_account);


        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        CardView changePassword = (CardView) findViewById(R.id.changepassword);
        changePassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout toolBack = (RelativeLayout) findViewById(R.id.toolimage);
        toolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RelativeLayout toolNotification = (RelativeLayout) findViewById(R.id.toolnotification);
        toolNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        if (appSettings.getIs_notification() == 0) {
            toolNotification.setVisibility(View.GONE);
        }
        TextView cartCount=(TextView)findViewById(R.id.actionbar_notifcation_textview) ;
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
        RelativeLayout toolSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isViewWithCatalog = false;

                Intent intent = new Intent(AccountActivity.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                SearchActivity.searchMin = "";
                SearchActivity.sortedida = 0;
                ApplyFilter.maximumSeekBar = "5000";
                ProductDetails.productid = 0;

                ApplyFilter.minimumSeekBar = "0";
                SearchActivity.formatedStringSearch = "";
                SearchActivity.imagea = "true";
                startActivity(intent);
            }
        });
        RelativeLayout toolCart = (RelativeLayout) findViewById(R.id.cart);
        toolCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        TextView toolTitle = (TextView) findViewById(R.id.titlebar);
        try {
           // toolTitle.setText(Label.navigationLabels.getMy_account());
           String myaccount = getString(R.string.my_account);
           toolTitle.setText(myaccount);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        accountName = (TextView) findViewById(R.id.acntname);
        accountNumber = (TextView) findViewById(R.id.accntnumber);
        changePasswordText = (TextView) findViewById(R.id.changePasswordText);
        updateProfileText = (TextView) findViewById(R.id.updateProfileText);
        myOrderText = (TextView) findViewById(R.id.myOrderText);
        myaddressesText = (TextView) findViewById(R.id.myaddressesText);
        myCartText = (TextView) findViewById(R.id.myCartText);
        myWishText = (TextView) findViewById(R.id.myWishText);
        logoutText = (TextView) findViewById(R.id.logoutText);
        refIdText = (TextView) findViewById(R.id.refid);
        acccntimmage = (ImageView) findViewById(R.id.acccntimmage);
        try {
             String change_Password = getString(R.string.change_password);
             String update_profile = getString(R.string.update_profile);
             String my_order = getString(R.string.my_orders);
             String my_address = getString(R.string.my_addresses);
             String cart = getString(R.string.my_cart);
             String logout = getString(R.string.logout);
             String wish = getString(R.string.my_wishlist);
             String ref_id = getString(R.string.ref_id);
            changePasswordText.setText(change_Password);
            updateProfileText.setText(update_profile);
            myOrderText.setText(my_order);
            myaddressesText.setText(my_address);
            myCartText.setText(cart);
            logoutText.setText(logout);
            myWishText.setText(wish);
            refIdText.setText(ref_id);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        allOrders = (LinearLayout) findViewById(R.id.cardorders);
        cart = (LinearLayout) findViewById(R.id.cardcart);
        wishlist = (LinearLayout) findViewById(R.id.cardwishlist);
        myaddresses = (LinearLayout) findViewById(R.id.myaddresses);

        logout = (LinearLayout) findViewById(R.id.cardlogout);
        cart.setOnClickListener(this);
        logout.setOnClickListener(this);
        wishlist.setOnClickListener(this);
        allOrders.setOnClickListener(this);
        myaddresses.setOnClickListener(this);


        CardView updateProfile = (CardView) findViewById(R.id.editnamenumber);
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, EditProfileActivity.class);
                startActivity(intent);

            }
        });
    }

    protected void onResume() {
        super.onResume();
        new CartCountUtil(AccountActivity.this);

        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        findViewById(R.id.retryImage).setVisibility(View.GONE);

        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar circularImage = (ProgressBar) findViewById(R.id.progressbarinner);
        circularImage.setVisibility(View.VISIBLE);

        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        final String userId = prefs.getString("user_id", "");
        String email = prefs.getString("email", "");
        String password = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));
     //   Toast.makeText(this, ""+userId, Toast.LENGTH_SHORT).show();
        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userId);
            jsonObject.put("password", password);
            jsonObject.put("language_id", languageid);
            System.out.println("sxzzzxxxxxxxxxxx" + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyTask volleyTask = new VolleyTask(this, URL_GETUSERPROFILE, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                findViewById(R.id.whiteloader).setVisibility(View.GONE);

                ResponseHandler responseHandler = new ResponseHandler();
                /*if (!responseHandler.validateResponse(AccountActivity.this, response)) {
                    return;
                }*/
                try {
                    SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                    String userid = prefs.getString("user_id", "");
                    JSONObject jsonObject1 = response.getJSONObject("data");

                    JSONObject jsonObject2 = jsonObject1.getJSONObject("data").getJSONObject("user");
                    String profile_image = jsonObject2.getString("profile_image");
                    Picasso.with(getApplicationContext()).load(BASE_IMAGE_URL+ profile_image).into(acccntimmage);
                    Dashboard.cart_count = jsonObject1.getJSONObject("data").getInt("cart_count");
                    Dashboard.notification_count = jsonObject1.getJSONObject("data").getInt("notification_count");
                    new CartCountUtil(AccountActivity.this);
                    String name = jsonObject2.getString("name");
                    String number = jsonObject2.getString("contact_number");
                    String address = jsonObject2.getString("address");
                    String email = jsonObject2.getString("email");

                    TextView textEmail = (TextView) findViewById(R.id.emailid);
                    TextView ref_id = (TextView) findViewById(R.id.refid);
                    textEmail.setText(email);
                    if (name.equals("null")) {
                        accountName.setVisibility(View.GONE);
                    } else {
                        accountName.setText(name);
                    }
                    if (number.matches(email)) {
                        accountNumber.setVisibility(View.GONE);
                    }
                    ref_id.setText(userid);
                    accountNumber.setText(number);
                    SharedPreferences.Editor editor = getSharedPreferences("usertable", MODE_PRIVATE).edit();
                    editor.putString("name", name);
                    editor.putString("address", address);
                    editor.apply();
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


                        Button retryButton = (Button) findViewById(R.id.retrybutton);
                        retryButton.setVisibility(View.VISIBLE);
                        retryButton.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardcart:
                Intent intent = new Intent(AccountActivity.this, CartActivity.class);

                startActivity(intent);
                break;
            case R.id.cardwishlist:
                Intent intent1 = new Intent(AccountActivity.this, WishlistActivity.class);

                startActivity(intent1);
                break;
            case R.id.cardorders:
                Intent intent2 = new Intent(AccountActivity.this, MyOrdersActivity.class);

                startActivity(intent2);
                break;
            case R.id.myaddresses:
                Intent intent5 = new Intent(AccountActivity.this, MyAddress.class);

                startActivity(intent5);
                break;
            case R.id.cardlogout:
                SharedPreferences preferences = getSharedPreferences("cheapstuffs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                final Dialog dialog = new Dialog(AccountActivity.this, R.style.AlertDialogStyle_Default);
                dialog.setContentView(R.layout.logout_dialog);
                TextView dialog_title = dialog.findViewById(R.id.dialog_title);
                Button dialog_yes = dialog.findViewById(R.id.dialog_yes);
                Button dialog_no = dialog.findViewById(R.id.dialog_no);


                String title = getString(R.string.do_you_want_to_logout);
                String yes = getString(R.string.yes);
                String no = getString(R.string.no);
                dialog_title.setText(title);
                dialog_yes.setText(yes);
                dialog_no.setText(no);


                dialog_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences preferences = getSharedPreferences("cheapstuffs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();

                        editor.clear();
                        editor.apply();
                        //Clearing Guest
                        editor.clear();
                        editor.apply();
                        Intent intent15
                                = new Intent(AccountActivity.this, LoginActivity.class);
                        startActivity(intent15);
                        intent15.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        finish();
                        LoginActivity.hideguest = 0;

                    }
                });
                dialog.show();

                break;

        }
    }


}
