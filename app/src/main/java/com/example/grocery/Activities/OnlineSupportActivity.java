package com.example.grocery.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.example.grocery.Adapter.ComplaintAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.ComplaintModel;
import com.example.grocery.Model.ComplaintTypeModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.Appearances.Appearance.appSettings;

public class OnlineSupportActivity extends AppCompatActivity {

    private Dialog dialog;
    private AppCompatEditText colplaintDetails;
    private Spinner complaintType;
    public static int x;
    private List<ComplaintTypeModel> list;
    private ComplaintAdapter displayImageAdapter;
    private String email, pwd;
    private TextView cartCount;
    private SharedPreferences sharedPreferences;
    private FloatingActionButton addTicket;
    private SwipeRefreshLayout refreshLayout;
    private boolean hideloading;
    public static boolean executeOnResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_online_support);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        TextView userDetails = (TextView) findViewById(R.id.userDetails);
        TextView appName = (TextView) findViewById(R.id.appName);
        appName.setText(Appearance.appTranslation.getApp_name());
        userDetails.setText(Appearance.appSettings.getContact_number() + "/" + Appearance.appSettings.getBusiness_email());
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
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
        RelativeLayout retryImage = (RelativeLayout) findViewById(R.id.toolimage);
        retryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolnotification);

        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineSupportActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences appsettings = getSharedPreferences("APPSETTINGS", MODE_PRIVATE);
        RelativeLayout toolBarNotification = (RelativeLayout) findViewById(R.id.toolnotification);
        toolBarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineSupportActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        if (appSettings.getIs_notification() == 0) {
            toolBarNotification.setVisibility(View.GONE);
        }

        RelativeLayout toolbarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineSupportActivity.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.minimumSeekBar = "0";
                ProductDetails.productid = 0;
                isViewWithCatalog = false;

                ApplyFilter.brandArrays = null;
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
                Intent intent = new Intent(OnlineSupportActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        try {
            String support = getString(R.string.support);
            toolbarTitle.setText(support);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        addTicket = (FloatingActionButton) findViewById(R.id.addticket);
        DrawableCompat.setTint(((FloatingActionButton) findViewById(R.id.addticket)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        addTicket.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#" + Appearance.appSettings.getApp_back_color())));
        sharedPreferences = getSharedPreferences("APPSETTINGS", MODE_PRIVATE);

        addTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineSupportActivity.this, AddNewComplaint.class);
                startActivity(intent);
            }
        });
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setColorSchemeColors((Color.parseColor("#" + Appearance.appSettings.getText_color())));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Refresh items
                hideloading = true;
                onResume();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(OnlineSupportActivity.this);

        findViewById(R.id.noitemavalable).setVisibility(View.GONE);
        if (!hideloading) {
            findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        }
        findViewById(R.id.retryImage).setVisibility(View.GONE);


        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        email = prefs.getString("email", "");
        pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_GETALLComplaint, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhsa" + response.toString());
                findViewById(R.id.whiteloader).setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
                if (!new ResponseHandler().validateResponse(OnlineSupportActivity.this, response)) {
                    dialog.dismiss();

                    return;
                }
                try {

                    JSONArray jsonArray1 = response.getJSONObject("data").getJSONArray("data");
                    if (jsonArray1.length() == 0) {
                        findViewById(R.id.noitemavalable).setVisibility(View.VISIBLE);
                        addTicket.setVisibility(View.GONE);
                        Button button = (Button) findViewById(R.id.noitemavailablebutton);
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
                                                    Color.parseColor("#" + Appearance.appSettings.getApp_back_color()),
                                                    Color.parseColor("#" + Appearance.appSettings.getApp_back_color()),
                                                    Color.parseColor("#" + Appearance.appSettings.getApp_back_color())
                                            }
                            );
                            button.setBackgroundTintList(stateListDrawable);
                            button.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
                        } else {
                            button.setBackground(OnlineSupportActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                            GradientDrawable bgShape = (GradientDrawable) button.getBackground();
                            bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                            button.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

                        }
                        TextView textView1 = (TextView) findViewById(R.id.extra);
                        String comp1 = getString(R.string.do_you_have_any_query);
                        textView1.setText(comp1);
                        TextView textView = (TextView) findViewById(R.id.noitemavailabletext);
                        String comp2 = getString(R.string.complaint_list_empty);
                        textView.setText(comp2);

                        String complaint = getString(R.string.add_complaint);
                        button.setText(complaint);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(OnlineSupportActivity.this, AddNewComplaint.class);
                                startActivity(intent);
                            }
                        });
                    } else {
                        addTicket.setVisibility(View.VISIBLE);
                        setData(jsonArray1);

                    }

                } catch (JSONException e) {
                    refreshLayout.setRefreshing(false);
                    e.printStackTrace();
                }
            }

            @Override
            public void fnErrorOccurred(String error) {

                refreshLayout.setRefreshing(false);
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

    private void setData(JSONArray jsonArray1) {
        Gson gson = new Gson();
        List<ComplaintModel> list = Arrays.asList(gson.fromJson(jsonArray1.toString(), ComplaintModel[].class));
        System.out.println("dada" + list);
        RecyclerView complaintRecycler = (RecyclerView) findViewById(R.id.complaintrec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        complaintRecycler.setLayoutManager(layoutManager);

        displayImageAdapter = new ComplaintAdapter(this, list);
        complaintRecycler.setAdapter(displayImageAdapter);
    }

}
