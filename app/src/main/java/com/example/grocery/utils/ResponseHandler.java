package com.example.grocery.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.android.volley.Request;
import com.example.grocery.Activities.LoginActivity;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;
import static com.example.grocery.interfaces.IConstants.BASE_URL;

/**
 * Created by Mohd Afzal on 11/25/2017.
 */

public class ResponseHandler {

    public static Dialog dialog;
    private AppCompatEditText pincode;
    private String userid;
    private String email;
    private String pwd;
    private String languageid;

    public boolean validateResponse(final AppCompatActivity context, JSONObject jsonObject) {
        try {
            if (jsonObject.getString("status").matches("true")) {
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (jsonObject.getJSONObject("data").has("auth")) {
                try {
                    if (jsonObject.getJSONObject("data").getInt("auth") == 0) {
                        LoginActivity.demandLogin = 1;
//                        new CustomToast(context, jsonObject.getJSONObject("data").getString("msg"));
                        SharedPreferences preferences = context.getSharedPreferences("cheapstuffs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();
                        Intent intent = new Intent(context, LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (jsonObject.getJSONObject("data").has("pincode")) {
                System.out.println("ewdsaddd");

                try {
                    if (jsonObject.getJSONObject("data").getInt("pincode") == 0) {

                        dialog = new Dialog(context,R.style.AlertDialogStyle_Default);
                        dialog.setContentView(R.layout.pincodepopup);
                        pincode = (AppCompatEditText) dialog.findViewById(R.id.etpincode);
                        new EditTextColorChanger(context,pincode);
                        Button update = (Button) dialog.findViewById(R.id.etcheck);
                        Button cancel = (Button) dialog.findViewById(R.id.etcancel);
                        update.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                        SharedPreferences prefs = context.getSharedPreferences("UserId", MODE_PRIVATE);
                        userid = prefs.getString("user_id", "");
                        email = prefs.getString("email", "");
                        pwd = prefs.getString("pwd", "");
                        languageid = prefs.getString("language", String.valueOf(1));


                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (pincode.getText().toString().matches("")) {
                                    pincode.setError("pincode cannot be empty");
                                } else {
                                    dialog.dismiss();
                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("business_id", IConstants.BUSINESS_ID);
                                        jsonObject.put("pincode", pincode.getText().toString());
                                        jsonObject.put("id", userid);
                                        jsonObject.put("password", pwd);
                                        jsonObject.put("language_id", languageid);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    String url = BASE_URL + "updatePincode";

                                    VolleyTask volleyTask = new VolleyTask(context, url.replace(" ", "%20"), jsonObject, Request.Method.POST);
                                    volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                                        @Override
                                        public void fnPostTaskCompleted(JSONArray response) {
                                            System.out.println("sed" + response);
                                        }

                                        @Override
                                        public void fnPostTaskCompletedJsonObject(JSONObject response) {
                                            if (!new ResponseHandler().validateResponse(context, response)) {
                                                return;
                                            }
                                            String jsonObject1 = null;
                                            try {
                                                jsonObject1 = response.getJSONObject("data").getString("msg");
                                                new CustomToast(context, jsonObject1);
                                                SharedPreferences.Editor editor =context.getSharedPreferences("UserId", MODE_PRIVATE).edit();

                                                editor.putString("pincode", pincode.getText().toString());
                                                editor.apply();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }

                                        @Override
                                        public void fnErrorOccurred(String error) {
                                        }
                                    });
                                }
                            }
                        });

                        dialog.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
                if (jsonObject.getString("status").matches("false")) {
                    try {
                        new CustomToast(context, jsonObject.getJSONObject("data").getString("msg"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;

    }
}