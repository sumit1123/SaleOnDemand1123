package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.WishListAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.ProductModel;
import com.example.grocery.Model.WishlistModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
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

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.Appearances.Appearance.appSettings;
import static com.example.grocery.interfaces.IConstants.BASE_URL;

public class WishlistActivity extends AppCompatActivity {

    private boolean shouldExecuteOnResume;
    private LinearLayout clearWishListLayout;
    public TextView clearWishlist;
    private SwipeRefreshLayout refreshLayout;
    private boolean hideloading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_wishlist);
        //labelsShared = getSharedPreferences("labels", MODE_PRIVATE);

        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        ColorChangerNoItemAvailable colorChangerNoItemAvailable = new ColorChangerNoItemAvailable(this);
        setToolbar();
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
       clearWishlist = (TextView) findViewById(R.id.clearWishList);
       String clear_wishlist = getString(R.string.clear_wishlist);
       clearWishlist.setText(clear_wishlist);
       clearWishListLayout = findViewById(R.id.clearWishListLayout);
       clearWishListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearWishlst();
            }
        });

    }


    private void setToolbar() {
        TextView cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
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
        RelativeLayout toolBarNotification = (RelativeLayout) findViewById(R.id.toolnotification);
        toolBarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WishlistActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences appsettings = getSharedPreferences("APPSETTINGS", MODE_PRIVATE);

        if (appSettings.getIs_notification() == 0) {
            toolBarNotification.setVisibility(View.GONE);
        }
        RelativeLayout toolbarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WishlistActivity.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                SearchActivity.searchMin = "";
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.minimumSeekBar = "0";
                ProductDetails.productid = 0;
                isViewWithCatalog = false;
                ApplyFilter.brandArrays = null;
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
                Intent intent = new Intent(WishlistActivity.this, CartActivity.class);
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

            String title = getString(R.string.my_wishlist);
            toolbarTitle.setText(title);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void getData() {
        super.onResume();
        if (!hideloading) {
            findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        }
        findViewById(R.id.retryImage).setVisibility(View.GONE);


        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();

        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String emaila = prefs.getString("email", "");
        String pwda = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwda);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = BASE_URL + "getWishlist";

        System.out.println("wedxs" + url.trim());
        VolleyTask volleyTask = new VolleyTask(this, url.replace(" ", "%20"), jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
                System.out.println("sed" + response);
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                refreshLayout.setRefreshing(false);
                System.out.println("hhhs" + response.toString());
                findViewById(R.id.whiteloader).setVisibility(View.GONE);
                if (!new ResponseHandler().validateResponse(WishlistActivity.this, response)) {
                    return;
                }


                try {
                    String status = response.getString("status");
                    findViewById(R.id.clearWishListLayout2).setVisibility(View.GONE);
                    clearWishListLayout.setVisibility(View.GONE);


                    JSONObject jsonObject1 = response.getJSONObject("data").getJSONObject("data");
                    Dashboard.cart_count = jsonObject1.getInt("cart_count");
                 //   Dashboard.notification_count = jsonObject1.getInt("notification_count");
                    new CartCountUtil(WishlistActivity.this);


                    if (jsonObject1.getJSONArray("wishlists").length() == 0) {
                        findViewById(R.id.clearWishListLayout2).setVisibility(View.GONE);
                        clearWishListLayout.setVisibility(View.GONE);
                        findViewById(R.id.noitemavalable).setVisibility(View.VISIBLE);
                        TextView textView = (TextView) findViewById(R.id.noitemavailabletext);
                        TextView extra = (TextView) findViewById(R.id.extra);
                        String wishlist_des = getString(R.string.empty_wishlist_description);
                        String wishlist_title = getString(R.string.empty_wishlist_title);
                        extra.setText(wishlist_des);

                        textView.setText(wishlist_title);
                        Button button = (Button) findViewById(R.id.noitemavailablebutton);

                        button.setBackground(WishlistActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                        GradientDrawable bgShape = (GradientDrawable) button.getBackground();
                        bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                        button.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));


                        findViewById(R.id.whiteloader).setVisibility(View.GONE);
                        String shopping = getString(R.string.continue_shopping);
                        button.setText(shopping);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(WishlistActivity.this, Dashboard.class);
                                startActivity(intent);

                            }
                        });
                    } else {
                        findViewById(R.id.clearWishListLayout2).setVisibility(View.VISIBLE);
                        clearWishListLayout.setVisibility(View.VISIBLE);


                        findViewById(R.id.noitemavalable).setVisibility(View.GONE);

                        setWishlist(jsonObject1.getJSONArray("wishlists"));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    refreshLayout.setRefreshing(false);
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
                        findViewById(R.id.retryImage).setVisibility(View.GONE);


                        Button toolbarButton = (Button) findViewById(R.id.retrybutton);
                        toolbarButton.setVisibility(View.VISIBLE);
                        toolbarButton.setOnClickListener(new View.OnClickListener() {
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

    public void setWishlist(JSONArray wishlists) {
        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        Gson gson = new Gson();
        //Toast.makeText(this, ""+wishlists.toString(), Toast.LENGTH_SHORT).show();
        List<WishlistModel> list = Arrays.asList(gson.fromJson(wishlists.toString(), WishlistModel[].class));
        try {
            if (list.size() == 0) {
                findViewById(R.id.noitemavalable).setVisibility(View.VISIBLE);
                TextView textView = (TextView) findViewById(R.id.noitemavailabletext);
                TextView extra = (TextView) findViewById(R.id.extra);
                String wishlist_des = getString(R.string.empty_wishlist_description);
                String wishlist_title = getString(R.string.empty_wishlist_title);
                extra.setText(wishlist_des);

                textView.setText(wishlist_title);
                Button button = (Button) findViewById(R.id.noitemavailablebutton);

                button.setBackground(WishlistActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                GradientDrawable bgShape = (GradientDrawable) button.getBackground();
                bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                button.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
                String con = getString(R.string.continue_shopping);
                button.setText(con);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        finish();

                    }
                });
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        RecyclerView wishListRecycler = (RecyclerView) findViewById(R.id.wishlistrecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        wishListRecycler.setLayoutManager(layoutManager);
        WishListAdapter wishListAdapter = new WishListAdapter(this, list);
        wishListRecycler.setAdapter(wishListAdapter);
        findViewById(R.id.whiteloader).setVisibility(View.GONE);

    }

    private void clearWishlst() {
        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);

        JSONObject jsonObject = new JSONObject();

        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String emaila = prefs.getString("email", "");
        String pwda = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwda);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = BASE_URL + "clearWishlist";

        System.out.println("wedxs" + url.trim());
        VolleyTask volleyTask = new VolleyTask(this, url.replace(" ", "%20"), jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
                System.out.println("sed" + response);
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                if (!new ResponseHandler().validateResponse(WishlistActivity.this, response)) {
                    return;
                }


                try {
                    String status = response.getString("status");

                    findViewById(R.id.clearWishListLayout2).setVisibility(View.VISIBLE);
                    clearWishListLayout.setVisibility(View.VISIBLE);

                    JSONObject jsonObject1 = response.getJSONObject("data").getJSONObject("data");

                    new CartCountUtil(WishlistActivity.this);
                    if (jsonObject1.getJSONArray("wishlists").length() == 0) {

                        findViewById(R.id.clearWishListLayout2).setVisibility(View.GONE);
                        clearWishListLayout.setVisibility(View.GONE);


                        findViewById(R.id.noitemavalable).setVisibility(View.VISIBLE);
                        TextView textView = (TextView) findViewById(R.id.noitemavailabletext);
                        TextView extra = (TextView) findViewById(R.id.extra);
                        String wishlist_des = getString(R.string.empty_wishlist_description);
                        String wishlist_title = getString(R.string.empty_wishlist_title);
                        extra.setText(wishlist_des);

                        textView.setText(wishlist_title);
                        Button button = (Button) findViewById(R.id.noitemavailablebutton);
                        findViewById(R.id.whiteloader).setVisibility(View.GONE);

                        button.setBackground(WishlistActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                        GradientDrawable bgShape = (GradientDrawable) button.getBackground();
                        bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                        button.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
                        String global = getString(R.string.continue_shopping);
                        button.setText(global);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                finish();

                            }
                        });
                    } else {
                        findViewById(R.id.clearWishListLayout2).setVisibility(View.GONE);
                        clearWishListLayout.setVisibility(View.GONE);


                        setWishlist(jsonObject1.getJSONArray("wishlists"));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                }
            }

            @Override
            public void fnErrorOccurred(String error) {

                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        new CartCountUtil(WishlistActivity.this);

    }
}
