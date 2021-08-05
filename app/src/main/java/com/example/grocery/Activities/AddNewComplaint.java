package com.example.grocery.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.ComplaintTypeModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.NotificationUtils;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.NotificationUtils;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.example.grocery.Appearances.Appearance.appSettings;

public class AddNewComplaint extends AppCompatActivity {
    private AppCompatEditText colplaintDetails;
    private Spinner complaintType;
    public static int x;
    private List<ComplaintTypeModel> list;
    private Button dilogOkButton;
    private TextView addComplaintText;
    private LinearLayout spinnerline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_add_new_complaint);

        //labelsShared = getSharedPreferences("labels", MODE_PRIVATE);

        colplaintDetails = (AppCompatEditText) findViewById(R.id.complaintdetails);
        dilogOkButton = (Button) findViewById(R.id.cpupdate);
        addComplaintText = (TextView) findViewById(R.id.addComplaintText);
        complaintType = (Spinner) findViewById(R.id.complaintType);
        spinnerline = (LinearLayout) findViewById(R.id.spinnerline);
        GradientDrawable drawable = (GradientDrawable) AddNewComplaint.this.getResources().getDrawable(R.drawable.selectedborder);
        drawable.setStroke(2, AddNewComplaint.this.getResources().getColor(R.color.bordercolor));
        spinnerline.setBackgroundDrawable(AddNewComplaint.this.getResources().getDrawable(R.drawable.selectedborder));
        complaintType.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                colplaintDetails.clearFocus();
                GradientDrawable drawable = (GradientDrawable) AddNewComplaint.this.getResources().getDrawable(R.drawable.selectedborder);
                drawable.setStroke(3, Color.parseColor("#" + Appearance.appSettings.getText_color()));
                spinnerline.setBackgroundDrawable(AddNewComplaint.this.getResources().getDrawable(R.drawable.selectedborder));
                return false;
            }
        });
        colplaintDetails.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                complaintType.clearFocus();
                spinnerline.setBackgroundDrawable(null);
                GradientDrawable drawable = (GradientDrawable) AddNewComplaint.this.getResources().getDrawable(R.drawable.selectedborder);
                drawable.setStroke(2, AddNewComplaint.this.getResources().getColor(R.color.bordercolor));
                spinnerline.setBackgroundDrawable(AddNewComplaint.this.getResources().getDrawable(R.drawable.selectedborder));

            }
        });
        complaintType.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Toast.makeText(AddNewComplaint.this, "true", Toast.LENGTH_SHORT).show();

                if (b) {
                    Toast.makeText(AddNewComplaint.this, "true", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(AddNewComplaint.this, "false", Toast.LENGTH_SHORT).show();

                }
            }
        });
        new EditTextColorChanger(this, colplaintDetails);

        try {
            String complaint_des = getString(R.string.complaint_description);
            String add = getString(R.string.add);
            String add_comp = getString(R.string.add_complaint);
            colplaintDetails.setHint(complaint_des);
            dilogOkButton.setText(add);
            addComplaintText.setText(add_comp);


        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        setToolBar();
        getList();
        addticket();
    }

    private void addticket() {


        complaintType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                x = position - 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
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
                                    Color.parseColor("#" +Appearance.appSettings.getApp_back_color()),
                                    Color.parseColor("#" + Appearance.appSettings.getApp_back_color()),
                                    Color.parseColor("#" + Appearance.appSettings.getApp_back_color())
                            }
            );
            dilogOkButton.setBackgroundTintList(stateListDrawable);
            dilogOkButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        } else {
            dilogOkButton.setBackground(AddNewComplaint.this.getResources().getDrawable(R.drawable.buttonshape));
            GradientDrawable bgShape = (GradientDrawable) dilogOkButton.getBackground();
            bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
            dilogOkButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

        }
        dilogOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (colplaintDetails.getText().toString().matches("")) {
                    try {
                        colplaintDetails.requestFocus();
                        String err = getString(R.string.description_empty);
                        colplaintDetails.setError(err);
                      //  colplaintDetails.setError(Label.userLabel.getDescription_empty());
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                } else if (x + 1 == 0) {
                    try {
                        new CustomToast(AddNewComplaint.this,getString(R.string.select_complaint_type));
                     //   new CustomToast(AddNewComplaint.this, Label.complaintLabel.getSelect_complaint_type());
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                } else {
                    findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
                    findViewById(R.id.retryImage).setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject();
                    SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

                    String userid = prefs.getString("user_id", "");
                    String emai = prefs.getString("email", "");
                    String pw = prefs.getString("pwd", "");
                    String languageid = prefs.getString("language", String.valueOf(1));

                    System.out.println("dasx" + list.get(x).getComplaint_type_id());
                    try {
                        jsonObject.put("business_id",IConstants.BUSINESS_ID);
                        jsonObject.put("complaint_type", list.get(x).getComplaint_type_id());
                        jsonObject.put("id", userid);
                        jsonObject.put("password", pw);
                        jsonObject.put("description", colplaintDetails.getText().toString());
                        jsonObject.put("language_id", languageid);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    VolleyTask volleyTask = new VolleyTask(AddNewComplaint.this, IConstants.URL_POSTNEwComplaint, jsonObject, Request.Method.POST);
                    volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                        @Override
                        public void fnPostTaskCompleted(JSONArray response) {
                        }

                        @Override
                        public void fnPostTaskCompletedJsonObject(JSONObject response) {
                            findViewById(R.id.whiteloader).setVisibility(View.GONE);

                            if (!new ResponseHandler().validateResponse(AddNewComplaint.this, response)) {
                                return;
                            }
                            try {
                                new CustomToast(AddNewComplaint.this, response.getJSONObject("data").getString("msg"));
                                finish();
                              //  OnlineSupportActivity.executeOnResume = true;
                                Intent intent = new Intent(AddNewComplaint.this, OnlineSupportActivity.class);
                                finish();
                                startActivity(intent);
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
    }

    private void getList() {
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String emaila = prefs.getString("email", "");
        String pwda = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwda);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {


        }

        VolleyTask volleyTask = new VolleyTask(AddNewComplaint.this, IConstants.URLGETCOMPLAINTTYPE, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhsa" + response.toString());
                if (!new ResponseHandler().validateResponse(AddNewComplaint.this, response)) {
                    return;
                }
                try {
                    JSONArray jsonArray1 = response.getJSONObject("data").getJSONArray("data");
                    Gson gson = new Gson();
                    list = Arrays.asList(gson.fromJson(jsonArray1.toString(), ComplaintTypeModel[].class));

                    List<String> strings = new ArrayList<String>();
                 //   strings.add(Label.complaintLabel.getSelect_complaint_type());
                    String complaint = getString(R.string.select_complaint_type);
                    strings.add(complaint);


                    for (int i = 0; i < jsonArray1.length(); i++) {
                        if (!jsonArray1.getJSONObject(i).get("complaint_type_translation").equals(null)) {
                            strings.add(jsonArray1.getJSONObject(i).getJSONObject("complaint_type_translation").getString("complaint_type_name"));

                        } else {
                            strings.add(jsonArray1.getJSONObject(i).getJSONObject("default_complaint_type_translation").getString("complaint_type_name"));
                        }
                    }
                    System.out.println("wed" + strings);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddNewComplaint.this, android.R.layout.simple_spinner_item, strings);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    complaintType.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fnErrorOccurred(String error) {
            }
        });
    }

    private void setToolBar() {
        {
            TextView cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
            RelativeLayout toolBarNotification = (RelativeLayout) findViewById(R.id.toolnotification);
            TextView notificationCount = (TextView) findViewById(R.id.notification_count_textview);

            if (Appearance.appSettings.getIs_notification() == 0) {
                toolBarNotification.setVisibility(View.GONE);
            }
            if (Dashboard.cart_count !=0){
                cartCount.setVisibility(View.VISIBLE);
                cartCount.setText(String.valueOf(Dashboard.cart_count));
            }
            if(Dashboard.notification_count!=0)
            {
                notificationCount.setVisibility(View.VISIBLE);
                notificationCount.setText(String.valueOf(Dashboard.notification_count));
            }
            toolBarNotification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(AddNewComplaint.this,NotificationActivity.class);
                    startActivity(intent);
                }
            });


            SharedPreferences sharedPreferences = getSharedPreferences("APPSETTINGS", MODE_PRIVATE);

            RelativeLayout toolBarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
            toolBarSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AddNewComplaint.this, SearchActivity.class);
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
                    Intent intent = new Intent(AddNewComplaint.this, CartActivity.class);
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
                String addcomplaint = getString(R.string.add_complaint);
                toolbarTitle.setText(addcomplaint);
                //toolbarTitle.setText(Label.complaintLabel.getAdd_complaint());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
       new CartCountUtil(AddNewComplaint.this);

    }
}
