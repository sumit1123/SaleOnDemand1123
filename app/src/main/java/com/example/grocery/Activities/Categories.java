package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.CategoriesAdapter;
import com.example.grocery.Adapter.Shopbycateadapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.AllCategoriesModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.ApplicationUtility;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.ColorChangerNoItemAvailable;
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

import static com.example.grocery.Activities.Dashboard.cart_count;
import static com.example.grocery.Activities.Dashboard.notification_count;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;

public class Categories extends AppCompatActivity {

    private String category_name;
    public static int category_id;
    private TextView cartCount;
    boolean shouldExecuteOnResume;


    //private SharedPreferences labelsShared;
    private boolean hideloading;
    private SwipeRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_all_categorieslist);
        //labelsShared = getSharedPreferences("labels", MODE_PRIVATE);

        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        ColorChangerNoItemAvailable colorChangerNoItemAvailable = new ColorChangerNoItemAvailable(this);
        Bundle extras = getIntent().getExtras();
        category_name = extras.getString("category_name");
        category_id = extras.getInt("category_id");

        RelativeLayout toolNotification = (RelativeLayout) findViewById(R.id.toolnotification);
        toolNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, NotificationActivity.class);
                startActivity(intent);
            }
        });


        if (Appearance.appSettings.getIs_notification() == 0) {
            toolNotification.setVisibility(View.GONE);

        }
        RelativeLayout toolSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, SearchActivity.class);
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
        RelativeLayout toolCart = (RelativeLayout) findViewById(R.id.cart);
        toolCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, CartActivity.class);
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
        TextView toolTitle = (TextView) findViewById(R.id.titlebar);
        toolTitle.setText(category_name);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setColorSchemeColors((Color.parseColor("#" + Appearance.appSettings.getText_color())));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                // Refresh items
                hideloading = true;
                getData();


            }
        });
        getData();
    }

    protected void getData() {
        if (!hideloading) {
            findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        }
        findViewById(R.id.retryImage).setVisibility(View.GONE);


        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);

        boolean checkConnection = new ApplicationUtility().checkConnection(this);
        System.out.println("sdnj" + !checkConnection);
        System.out.println("conn" + checkConnection);
        System.out.println("connection on");
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String email = prefs.getString("email", "");
        String password = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        JSONObject jsonObject = new JSONObject();
        System.out.println("cateid" + category_id);
        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("category_id", category_id);
            jsonObject.put("id", userid);
            jsonObject.put("password", password);
            jsonObject.put("language_id", languageid);
            jsonObject.put("notification_id",NotificationActivity.noti_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("mdczklmz" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_AllCategories, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                refreshLayout.setRefreshing(false);
                if (!new ResponseHandler().validateResponse(Categories.this, response)) {
                    return;
                }


                try {
                    JSONObject jsonObject1 = response.getJSONObject("data");
                    cart_count = jsonObject1.getJSONObject("data").getInt("cart_count");
                   // Toast.makeText(Categories.this, ""+cart_count, Toast.LENGTH_SHORT).show();
                    notification_count = jsonObject1.getJSONObject("data").getInt("notification_count");
                  //  Toast.makeText(Categories.this, ""+notification_count, Toast.LENGTH_SHORT).show();
                    new CartCountUtil(Categories.this);


                    JSONArray categoriesArray = jsonObject1.getJSONObject("data").getJSONArray("categories");
                    if (categoriesArray.length() == 0) {
                        findViewById(R.id.noitemavalable).setVisibility(View.VISIBLE);
                        findViewById(R.id.noitemavalable).setVisibility(View.GONE);

                        Button resultButton = (Button) findViewById(R.id.noitemavailablebutton);
                        TextView textView = (TextView) findViewById(R.id.noitemavailabletext);
                        TextView extra = (TextView) findViewById(R.id.extra);

                        String product_title = getString(R.string.empty_category_product_title);
                        String product_des = getString(R.string.empty_category_product_description);
                        String back = getString(R.string.back);

                        textView.setText(product_title);
                        extra.setText(product_des);
                      //  textView.setText(Label.productLabel.getEmpty_category_product_title());
                       // extra.setText(Label.productLabel.getEmpty_category_product_description());

                       // resultButton.setText(Label.globalLabel.getBack());
                        resultButton.setText(back);
                        resultButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Categories.this, Dashboard.class);
                                startActivity(intent);
                            }
                        });

                    }
                    setData(categoriesArray);
                    findViewById(R.id.whiteloader).setVisibility(View.GONE);

                } catch (JSONException e) {
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
                                getData();
                            }
                        });
                    }
                }, 2000);


            }
        });


    }

    private void setData(JSONArray response) {

        Gson gson = new Gson();
        List<AllCategoriesModel> list = Arrays.asList(gson.fromJson(response.toString(), AllCategoriesModel[].class));
        System.out.println("dada" + list);

        int layouttester = Appearance.appSettings.getShow_category_type();

        RecyclerView categoryRecycler = (RecyclerView) findViewById(R.id.reccate);
        if (layouttester == 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            categoryRecycler.setLayoutManager(layoutManager);
            CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this, list);
            categoryRecycler.setAdapter(categoriesAdapter);
        } else {
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
            categoryRecycler.setLayoutManager(layoutManager);
            Shopbycateadapter shopbycateadapter = new Shopbycateadapter(this, list);
            categoryRecycler.setAdapter(shopbycateadapter);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(Categories.this);

    }
}