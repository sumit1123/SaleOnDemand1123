package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;

import android.os.Bundle;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class PasswordResetActivity extends AppCompatActivity {
    private AppCompatEditText oldPassword;
    private AppCompatEditText newPassword;
    private TextView cartCount;
    private boolean type, type1;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        type = false;
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_password_reset);

        ((ProgressBar) findViewById(R.id.blurredloaderprogressbar)).getIndeterminateDrawable().setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()), PorterDuff.Mode.MULTIPLY);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#" + Appearance.appSettings.getApp_back_color())));
        getSupportActionBar().setTitle(Html.fromHtml("<font color=" + Color.parseColor("#" + Appearance.appSettings.getApp_text_color()) + ">Password Reset</font>"));

        Bundle extras = getIntent().getExtras();
        user_id = extras.getInt("user_id");
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

        //ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        oldPassword = (AppCompatEditText) findViewById(R.id.cpoldpassword);
        newPassword = (AppCompatEditText) findViewById(R.id.cpnewPassword);
        new EditTextColorChanger(this, oldPassword);
        new EditTextColorChanger(this, newPassword);

        Button submitText = (Button) findViewById(R.id.submitText);
        submitText.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        submitText.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
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
            submitText.setBackground(PasswordResetActivity.this.getResources().getDrawable(R.drawable.buttonshape));
            GradientDrawable bgShape = (GradientDrawable) submitText.getBackground();
            bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
            submitText.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

        }


        try {
            String old = getString(R.string.new_password);
            oldPassword.setHint(old);
            String new_pass = getString(R.string.confirm_password);
            newPassword.setHint(new_pass);
            String submit = getString(R.string.submit);
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
                } else if (newPassword.getText().toString().matches("")) {
                    try {
                        String err = getString(R.string.password_empty);
                            newPassword.setError(err);

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                } else if (!oldPassword.getText().toString().matches(newPassword.getText().toString())) {
                    Toast.makeText(PasswordResetActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();

                } else {
                    findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
                    JSONObject jsonObject = new JSONObject();
                    SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                    String languageid = prefs.getString("language", String.valueOf(1));


                    try {
                        jsonObject.put("business_id", IConstants.BUSINESS_ID);
                        jsonObject.put("user_id", user_id);
                        jsonObject.put("password", newPassword.getText().toString());
                        jsonObject.put("language_id", languageid);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    System.out.println("sxz" + jsonObject);

                    VolleyTask volleyTask = new VolleyTask(PasswordResetActivity.this, IConstants.URL_FORGOTCHANGEPASSWORD, jsonObject, Request.Method.POST);
                    volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                        @Override
                        public void fnPostTaskCompleted(JSONArray response) {
                        }

                        @Override
                        public void fnPostTaskCompletedJsonObject(JSONObject response) {
                            ResponseHandler responseHandler = new ResponseHandler();
                            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                            if (!responseHandler.validateResponse(PasswordResetActivity.this, response)) {
                                return;
                            }
                            try {
                                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                                JSONObject jsonObject1 = response.getJSONObject("data").getJSONObject("data");
                                String s = jsonObject1.getString("id");
                                String user_name = jsonObject1.getString("email");
                                // String pincode = jsonObject1.getString("pincode");
                                //String address=jsonObject1.getString("address");
                                SharedPreferences.Editor editor = getSharedPreferences("UserId", MODE_PRIVATE).edit();
                                editor.putString("user_id", s);
                                editor.putString("username", jsonObject1.getString("name"));

                                editor.putString("email", user_name);
                                editor.putString("pwd", newPassword.getText().toString());
                                //  editor.putString("pincode", pincode);


                                editor.apply();

                                SharedPreferences.Editor editor1 = getSharedPreferences("cheapstuffs", MODE_PRIVATE).edit();
                                editor1.putBoolean("login", true);
                                editor1.apply();
                                Intent intent = new Intent(PasswordResetActivity.this, Dashboard.class);
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

}
