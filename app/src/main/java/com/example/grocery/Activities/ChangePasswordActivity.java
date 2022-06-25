package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CartCountUtil;
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

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;

public class ChangePasswordActivity extends AppCompatActivity {

    private AppCompatEditText oldPassword;
    private AppCompatEditText newPassword;
    private TextView cartCount;
    private boolean type, type1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        type = false;
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.changepassword);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#" + Appearance.appSettings.getApp_status_bar_color()));
        }
        final ImageView imageView = (ImageView) findViewById(R.id.eyedrawableold);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    password.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD);*/
                if (!type) {
                    type = true;
                    imageView.setBackground(null);
                    imageView.setImageResource(R.drawable.removedeye);
                    oldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    oldPassword.setSelection(oldPassword.getText().toString().length());

                } else {
                    imageView.setBackground(null);

                    type = false;
                    imageView.setImageResource(R.drawable.eye);

                    oldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    oldPassword.setSelection(oldPassword.getText().toString().length());

                }
            }
        });
        final ImageView imageView1 = (ImageView) findViewById(R.id.eyedrawablenew);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    password.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD);*/
                if (!type1) {
                    type1 = true;

                    imageView1.setImageResource(R.drawable.removedeye);

                    newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    newPassword.setSelection(newPassword.getText().toString().length());

                } else {
                    imageView1.setImageResource(R.drawable.eye);

                    type1 = false;
                    newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    newPassword.setSelection(newPassword.getText().toString().length());

                }
            }
        });
        //labelsShared = getSharedPreferences("labels", MODE_PRIVATE);

        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        oldPassword = (AppCompatEditText) findViewById(R.id.cpoldpassword);
        newPassword = (AppCompatEditText) findViewById(R.id.cpnewPassword);
        new EditTextColorChanger(this, oldPassword);
        new EditTextColorChanger(this, newPassword);

        Button submitText = (Button) findViewById(R.id.submitText);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
            submitText.setBackgroundTintList(stateListDrawable);
            submitText.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        } else {
            submitText.setBackground(ChangePasswordActivity.this.getResources().getDrawable(R.drawable.buttonshape));
            GradientDrawable bgShape = (GradientDrawable) submitText.getBackground();
            bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
            submitText.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        }

        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);

        settoolbar();
        try {
            String new_pass = getString(R.string.new_password);
            String confirm_pass = getString(R.string.confirm_password);
            String submit = getString(R.string.submit);

            oldPassword.setHint(new_pass);
            newPassword.setHint(confirm_pass);
            submitText.setText(submit);

            String new_password = getString(R.string.new_password);
            String confirm = getString(R.string.confirm_password);
             String save = getString(R.string.submit);
           oldPassword.setHint(new_password);
            newPassword.setHint(confirm);
           submitText.setText(submit);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        submitText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (oldPassword.getText().toString().matches("")) {
                    try {
                        String err = getString(R.string.password_empty);
                        oldPassword.setError(err);

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                if (newPassword.getText().toString().matches("")) {
                    try {
                        String err = getString(R.string.password_empty);
                        oldPassword.setError(err);
                        newPassword.setError(err);
                   //     newPassword.setError(Label.userLabel.getPassword_empty());
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                } else {
                    findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
                    JSONObject jsonObject = new JSONObject();

                    SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                    String userid = prefs.getString("user_id", "");
                    String password = prefs.getString("pwd", "");
                    String languageid = prefs.getString("language", String.valueOf(1));
                    try {
                        jsonObject.put("business_id", IConstants.BUSINESS_ID);
                        jsonObject.put("id", userid);
                        jsonObject.put("new_password", oldPassword.getText().toString());
                        jsonObject.put("confirm_password", newPassword.getText().toString());
                        jsonObject.put("password", password);
                        jsonObject.put("language_id",languageid);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    System.out.println("sxz" + jsonObject);

                    VolleyTask volleyTask = new VolleyTask(ChangePasswordActivity.this, IConstants.URL_CHANGEPASSWORD, jsonObject, Request.Method.POST);
                    volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                        @Override
                        public void fnPostTaskCompleted(JSONArray response) {
                        }

                        @Override
                        public void fnPostTaskCompletedJsonObject(JSONObject response) {
                            ResponseHandler responseHandler = new ResponseHandler();
                            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                            if (!responseHandler.validateResponse(ChangePasswordActivity.this, response)) {
                                return;
                            }
                            try {
                                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                                new CustomToast(ChangePasswordActivity.this, response.getJSONObject("data").getString("msg"));

                                Intent intent = new Intent(ChangePasswordActivity.this, AccountActivity.class);
                                finish();
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            SharedPreferences.Editor editor1 = getSharedPreferences("UserId", MODE_PRIVATE).edit();

                            editor1.putString("pwd", newPassword.getText().toString());
                            editor1.apply();

                        }

                        @Override
                        public void fnErrorOccurred(String error) {
                        }
                    });
                }
            }
        });
    }

    private void settoolbar() {
        new CartCountUtil(ChangePasswordActivity.this);
        RelativeLayout toolbarNotification = (RelativeLayout) findViewById(R.id.toolnotification);
        toolbarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePasswordActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences appsettings = getSharedPreferences("APPSETTINGS", MODE_PRIVATE);

        if (appsettings.getInt("is_notification", 0) == 0) {
            toolbarNotification.setVisibility(View.GONE);

        }
        RelativeLayout toolbarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePasswordActivity.this, SearchActivity.class);
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
        RelativeLayout toolbarCart = (RelativeLayout) findViewById(R.id.cart);
        toolbarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePasswordActivity.this, CartActivity.class);
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
            String forget_pass = getString(R.string.change_password);
            toolbarTitle.setText(forget_pass);
          //  toolbarTitle.setText(Label.userLabel.getChange_password());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(ChangePasswordActivity.this);
       }
}
