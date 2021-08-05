package com.example.grocery.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.ProductAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.ProductModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.ApplicationUtility;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.ColorChangerNoItemAvailable;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.LoaderColorChanger;
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

import static android.content.Context.MODE_PRIVATE;
import static com.example.grocery.Activities.ProductDetails.productid;
import static com.example.grocery.Activities.SearchActivity.formatedStringSearch;
import static com.example.grocery.Activities.SearchActivity.page_count;
import static com.example.grocery.Activities.SearchActivity.searchKeyword;
import static com.example.grocery.Activities.SearchActivity.searchMax;
import static com.example.grocery.Activities.SearchActivity.searchMin;
import static com.example.grocery.Activities.SearchActivity.shouldExecurOnResume;
import static com.example.grocery.Activities.SearchActivity.sortedida;
import static com.example.grocery.Activities.SellerActivity.sellerid;
import static com.example.grocery.Adapter.ProductAdapter.positionValue;
import static com.example.grocery.R.layout.sorting;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    public static int sucat_id;
    private RelativeLayout changeLayout;
    private RecyclerView productsRecycler;
    public static List<ProductModel> productList;
    public static boolean isViewWithCatalog;
    private RelativeLayout sort;
    private RelativeLayout filter;
    private Dialog dialog;
    private AppCompatRadioButton one, two, three, four;
    public static int sortedid;
    public static String name;
    public static String similar;
    public static ArrayList<Integer> productbrands;
    private ImageView imageView;
    public static String image;
    public static ArrayList<Integer> staticbrand;
    private RelativeLayout relativeLayout1, relativeLayout2, relativeLayout3, relativeLayout4;
    public TextView cartCount;
    private ProductAdapter productAdapter;
    private CardView cardView;
    private CardView cardsorting;
    private SwipeRefreshLayout refreshLayout;
    private TextView sortText;
    private TextView filterText;
    private int rProductid;
    private int rSucat_id;
    private boolean viewWithCatalogState;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_product);
        sortText = (TextView) findViewById(R.id.sortText);
        filterText = (TextView) findViewById(R.id.filterText);
        try {
               String sort = getString(R.string.sort);
            String filter = getString(R.string.filter);
            sortText.setText(sort);
            filterText.setText(filter);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        ApplyFilter.maximumSeekBar = "5000";
        ApplyFilter.minimumSeekBar = "0";
        ApplyFilter.maxSeekBar = 5000;
        ApplyFilter.minSeekBar = 0;
        

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setColorSchemeColors((Color.parseColor("#" + Appearance.appSettings.getText_color())));

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });
        cardsorting = (CardView) findViewById(R.id.sortingvisibility);
        cardsorting.setVisibility(View.GONE);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        ColorChangerNoItemAvailable colorChangerNoItemAvailable = new ColorChangerNoItemAvailable(this);
        shouldExecurOnResume = false;
        searchMin = "";
        SearchActivity.page_count = 1;
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
        RelativeLayout toolbarNotification = (RelativeLayout) findViewById(R.id.toolnotification);

        toolbarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        if (Appearance.appSettings.getIs_notification() == 0) {
            toolbarNotification.setVisibility(View.GONE);

        }
        RelativeLayout toolbarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                searchMax = "";
                ProductDetails.productid = 0;
                isViewWithCatalog = false;

                ApplyFilter.brandArrays = null;
                SearchActivity.searchMin = "";
                SearchActivity.sortedida = 0;
                formatedStringSearch = "";
                SearchActivity.imagea = "true";
                startActivity(intent);
            }
        });
        RelativeLayout toolBarCart = (RelativeLayout) findViewById(R.id.cart);
        toolBarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                isViewWithCatalog = false;
            }
        });
        TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        toolbarTitle.setText(name);
        CardView similarTextVisibility = (CardView) findViewById(R.id.visibilitysimilar);
        TextView similarProductText = (TextView) findViewById(R.id.similarProductText);


        if (Appearance.appSettings.getShow_product_type() == 0) {
            isViewWithCatalog = true;
        }

        if (productid == 0) {
            similarTextVisibility.setVisibility(View.GONE);
        } else {
            similarTextVisibility.setVisibility(View.VISIBLE);
            try {
                String similar_pro = getString(R.string.similar_products_of);
                similarProductText.setText(similar_pro + " " + name);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        initUi();
        getData();

    }

    private void refreshItems() {
        SearchActivity.page_count = 1;
        productbrands = ApplyFilter.brandArrays;

        if (formatedStringSearch.matches("")) {
            formatedStringSearch = "";
        } else {
            formatedStringSearch = productbrands.toString()
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")
                    .replace(" ", "")//remove the left bracket
                    .trim();
        }
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String languageid = prefs.getString("language", String.valueOf(1));
        String email = prefs.getString("email", "");
        String pwd = prefs.getString("pwd", "");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("language_id", languageid);
            jsonObject.put("category_id", sucat_id);
            jsonObject.put("max_price", searchMax);
            jsonObject.put("min_price", searchMin);
            jsonObject.put("order", sortedid);
            jsonObject.put("brand","");
            jsonObject.put("keyword", "");
            jsonObject.put("seller_id", sellerid);
//            jsonObject.put("product_id", productid);
            jsonObject.put("product_id", rProductid); //***  rProductid == productid
            jsonObject.put("page", SearchActivity.page_count);

          //  Toast.makeText(this, ""+jsonObject, Toast.LENGTH_SHORT).show();

            Log.i("SuCat_Product_id", "getData: " + sucat_id + " " + productid);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("sxaxsaxsa" + jsonObject);

        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_FIlteredPRoduct, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhsa" + response.toString());
                if (!new ResponseHandler().validateResponse(ProductActivity.this, response)) {
                    return;
                }


                try {
                    SearchActivity.page_count_number = response.getJSONObject("data").getJSONObject("data").getInt("page_count");

                    JSONArray jsonArray1 = response.getJSONObject("data").getJSONObject("data").getJSONArray("products");
                    Dashboard.cart_count = response.getJSONObject("data").getJSONObject("data").getInt("cart_count");
                    if (response.getJSONObject("data").getJSONObject("data").getInt("total_count") == 0) {


                    } else {

                        new CustomToast(ProductActivity.this, String.valueOf(response.getJSONObject("data").getJSONObject("data").getString("total_count")) + " Products");

                    }
                    if (jsonArray1.length() == 0) {
                        cardsorting.setVisibility(View.GONE);
                        if (jsonArray1.length() == 0) {
                            findViewById(R.id.noitemavalable).setVisibility(View.VISIBLE);
                            Button resultButton = (Button) findViewById(R.id.noitemavailablebutton);
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
                                resultButton.setBackgroundTintList(stateListDrawable);
                                resultButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
                            } else {
                                resultButton.setBackground(ProductActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                                GradientDrawable bgShape = (GradientDrawable) resultButton.getBackground();
                                bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                                resultButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

                            }
                            TextView textView = (TextView) findViewById(R.id.noitemavailabletext);
                            String pro_title = getString(R.string.empty_category_product_title);
                            textView.setText(pro_title);
                            TextView extraText = (TextView) findViewById(R.id.extra);
                            String pro_des = getString(R.string.search_empty_description);
                            extraText.setText(pro_des);
                            findViewById(R.id.imagenoitemAvailable).setBackground(ProductActivity.this.getResources().getDrawable(R.drawable.product_big));
                            String back = getString(R.string.back);
                            resultButton.setText(back);

                            resultButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    System.out.println("ewsdxzedsxcz");

                                    finish();
                                }
                            });
                        }
                    } else {
                        cardsorting.setVisibility(View.VISIBLE);
                        setData1(jsonArray1);

                    }
                    findViewById(R.id.whiteloader).setVisibility(View.GONE);
                    onItemsLoadComplete();

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
                                getData();
                            }
                        });
                    }
                }, 2000);


            }
        });
    }

    public void getData() {
           findViewById(R.id.noitemavalable).setVisibility(View.GONE);

        cardsorting.setVisibility(View.GONE);

        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);

        findViewById(R.id.retryImage).setVisibility(View.GONE);


        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);

        boolean checkConnection = new ApplicationUtility().checkConnection(this);
        System.out.println("sdnj" + !checkConnection);
        System.out.println("conn" + checkConnection);
        System.out.println("connection on");
        productbrands = ApplyFilter.brandArrays;

        if (formatedStringSearch.matches("")) {
            formatedStringSearch = "";
        } else {
            formatedStringSearch = productbrands.toString()
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")
                    .replace(" ", "")//remove the left bracket
                    .trim();
        }
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String email = prefs.getString("email", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("category_id", sucat_id);
            //sucat_id  brand=formatedStringSearch
            jsonObject.put("max_price", searchMax);
            jsonObject.put("min_price", searchMin);
            jsonObject.put("order", sortedid);
            jsonObject.put("brand", "");
            jsonObject.put("keyword", "");
            jsonObject.put("product_id", productid);
            jsonObject.put("seller_id", sellerid);
            jsonObject.put("page", SearchActivity.page_count);
            jsonObject.put("language_id", languageid);

         //   Toast.makeText(this, ""+jsonObject, Toast.LENGTH_SHORT).show();
            Log.i("SuCat_Product_id", "getData: " + sucat_id + " " + productid);
            rProductid = productid;
            rSucat_id = sucat_id;
            isViewWithCatalog = viewWithCatalogState;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("sxaxsaxsa" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_FIlteredPRoduct, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhsa" + response.toString());
                if (!new ResponseHandler().validateResponse(ProductActivity.this, response)) {
                    return;
                }

                try {
                    SearchActivity.page_count_number = response.getJSONObject("data").getJSONObject("data").getInt("page_count");

                    JSONArray jsonArray1 = response.getJSONObject("data").getJSONObject("data").getJSONArray("products");
                    Dashboard.cart_count = response.getJSONObject("data").getJSONObject("data").getInt("cart_count");
                    Dashboard.notification_count = response.getJSONObject("data").getJSONObject("data").getInt("notification_count");
                    if (response.getJSONObject("data").getJSONObject("data").getInt("total_count") == 0) {


                    } else {

                        new CustomToast(ProductActivity.this, String.valueOf(response.getJSONObject("data").getJSONObject("data").getString("total_count")) + " Products");

                    }
                    if (jsonArray1.length() == 0) {
                        cardsorting.setVisibility(View.GONE);

                        findViewById(R.id.noitemavalable).setVisibility(View.VISIBLE);
                        Button resultButton = (Button) findViewById(R.id.noitemavailablebutton);
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
                            resultButton.setBackgroundTintList(stateListDrawable);
                            resultButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
                        } else {
                            resultButton.setBackground(ProductActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                            GradientDrawable bgShape = (GradientDrawable) resultButton.getBackground();
                            bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                            resultButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

                        }
                        TextView noitemavailabletext = (TextView) findViewById(R.id.noitemavailabletext);
                        String search = getString(R.string.search_empty_title);
                        noitemavailabletext.setText(search);
                        TextView extraText = (TextView) findViewById(R.id.extra);
                        String search_title = getString(R.string.search_empty_description);
                        extraText.setText(search_title);
                        findViewById(R.id.imagenoitemAvailable).setBackground(ProductActivity.this.getResources().getDrawable(R.drawable.product_big));
                        String back = getString(R.string.back);
                        resultButton.setText(back);

                        resultButton.isEnabled();
                        resultButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ProductActivity.this, Dashboard.class);
                                startActivity(intent);
                            }
                        });

                    } else {
                        cardsorting.setVisibility(View.VISIBLE);
                        setData(jsonArray1);
                    }
                    findViewById(R.id.whiteloader).setVisibility(View.GONE);

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
                                getData();
                            }
                        });
                    }
                }, 2000);
            }
        });
    }

    private void initUi() {
        imageView = (ImageView) findViewById(R.id.changeview);
        changeLayout = (RelativeLayout) findViewById(R.id.changelayout);
        changeLayout.setOnClickListener(this);
        sort = (RelativeLayout) findViewById(R.id.sortingbutton);
        filter = (RelativeLayout) findViewById(R.id.filterButton);
        sort.setOnClickListener(this);
        filter.setOnClickListener(this);
    }

    private void setData(JSONArray response) {
        Gson gson = new Gson();
        productList = Arrays.asList(gson.fromJson(response.toString(), ProductModel[].class));
        List<ProductModel> productModels = new ArrayList<>();
        productModels.addAll(productList);
        System.out.println("dada" + productList);
        productsRecycler = (RecyclerView) findViewById(R.id.searchrec);
        productsRecycler.setVisibility(View.VISIBLE);
        productAdapter = new ProductAdapter(this, productModels, sucat_id);
        if (!isViewWithCatalog) {
            imageView.setImageResource(R.drawable.navigation);
        } else {
            imageView.setImageResource(R.drawable.grid);
        }

        productsRecycler.setLayoutManager(!isViewWithCatalog ? new GridLayoutManager(this, 2) : new LinearLayoutManager(this));
        productsRecycler.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }


    private void setData1(JSONArray response) {
        Gson gson = new Gson();
        productList = Arrays.asList(gson.fromJson(response.toString(), ProductModel[].class));
        List<ProductModel> productModels = new ArrayList<>();
        productModels.addAll(productList);
        System.out.println("dada" + productList);
        productsRecycler = (RecyclerView) findViewById(R.id.searchrec);
        productsRecycler.setVisibility(View.VISIBLE);
        productAdapter = new ProductAdapter(this, productModels, sucat_id);

        if (!isViewWithCatalog) {
            imageView.setImageResource(R.drawable.navigation);
        } else {
            imageView.setImageResource(R.drawable.grid);
        }
        productsRecycler.setLayoutManager(!isViewWithCatalog ? new GridLayoutManager(this, 2) : new LinearLayoutManager(this));
        productsRecycler.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changelayout:
                final LayoutAnimationController controller =
                        AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down);

                productsRecycler.setLayoutAnimation(controller);
                productsRecycler.scheduleLayoutAnimation();
                if (isViewWithCatalog) {
                    imageView.setImageResource(R.drawable.navigation);
                } else {
                    imageView.setImageResource(R.drawable.grid);
                }

               /* isViewWithCatalog = !isViewWithCatalog;
                supportInvalidateOptionsMenu();
                productsRecycler.setLayoutManager(!isViewWithCatalog ? new GridLayoutManager(this, 2) : new LinearLayoutManager(this));
                (!isViewWithCatalog ? new GridLayoutManager(this, 2) : new LinearLayoutManager(this)).scrollToPosition(positionValue);
                productAdapter.notifyDataSetChanged();*/

                isViewWithCatalog = !isViewWithCatalog;
                supportInvalidateOptionsMenu();
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                GridLayoutManager gridlayout = new GridLayoutManager(this, 2);
                if (!isViewWithCatalog) {
                    productsRecycler.setLayoutManager(gridlayout);
                } else {
                    productsRecycler.setLayoutManager(layoutManager);
                }

                if (isViewWithCatalog) {
                    productsRecycler.setLayoutManager(layoutManager);
                    if (positionValue > 2) {
                        positionValue = positionValue - 3;
                    }

                } else {
                    productsRecycler.setLayoutManager(gridlayout);
                    if (positionValue > 2) {
                        positionValue = positionValue - 3;
                    }
                }
                int pos = !isViewWithCatalog ? gridlayout.findFirstCompletelyVisibleItemPosition() : layoutManager.findLastVisibleItemPosition();
                (!isViewWithCatalog ? gridlayout : layoutManager).scrollToPosition(positionValue);
                productsRecycler.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();

//                if (SearchActivity.imagea.matches("true")) {
//                    SearchActivity.imagea = "false";
//                    imageView.setImageResource(R.drawable.grid);
//                } else if (SearchActivity.imagea.matches("false")) {
//                    SearchActivity.imagea = "true";
//                    imageView.setImageResource(R.drawable.navigation);
//                }

                break;
            case R.id.sortingbutton: {
                dialog = new Dialog(this, R.style.MyDialogTheme);
                dialog.setContentView(sorting);
                dialog.setCanceledOnTouchOutside(true);
                getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                lp.copyFrom(window.getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                window.setAttributes(lp);
                TextView sortByText = (TextView) dialog.findViewById(R.id.sortByText);
                TextView newestFirstText = (TextView) dialog.findViewById(R.id.newestFirstText);
                TextView assendindorderText = (TextView) dialog.findViewById(R.id.assendindorderText);
                TextView hightolowText = (TextView) dialog.findViewById(R.id.hightolowText);
                TextView lowtoHighText = (TextView) dialog.findViewById(R.id.lowtoHighText);
                try {
                    String sort = getString(R.string.sort_by);
                    sortByText.setText(sort);
                    String newest = getString(R.string.newest_first);
                    newestFirstText.setText(newest);
                    String asc = getString(R.string.ascending_order);
                    assendindorderText.setText(asc);
                    String high = getString(R.string.high_to_low);
                    hightolowText.setText(high);
                    String low = getString(R.string.low_to_high);
                    lowtoHighText.setText(low);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
             /*   dialog.getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
               // dialog.setView(sorting, 0, 0, 0, 0);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);
                WindowManager.LayoutParams wlmp = dialog.getWindow()
                        .getAttributes();
                wlmp.gravity = Gravity.BOTTOM;*/

                one = (AppCompatRadioButton) dialog.findViewById(R.id.checkbox1);
                two = (AppCompatRadioButton) dialog.findViewById(R.id.checkbox2);
                three = (AppCompatRadioButton) dialog.findViewById(R.id.checkbox3);
                four = (AppCompatRadioButton) dialog.findViewById(R.id.checkbox4);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    one.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#" + Appearance.appSettings.getText_color())));
                    two.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#" + Appearance.appSettings.getText_color())));
                    three.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#" + Appearance.appSettings.getText_color())));
                    four.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#" + Appearance.appSettings.getText_color())));

                }
                one.setHighlightColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                two.setHighlightColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                three.setHighlightColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                four.setHighlightColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                relativeLayout1 = (RelativeLayout) dialog.findViewById(R.id.rel1);
                relativeLayout2 = (RelativeLayout) dialog.findViewById(R.id.rel2);
                relativeLayout3 = (RelativeLayout) dialog.findViewById(R.id.rel3);
                relativeLayout4 = (RelativeLayout) dialog.findViewById(R.id.rel4);


                if (sortedid == 1) {
                    one.setChecked(true);
                } else if (sortedid == 2) {
                    two.setChecked(true);
                } else if (sortedid == 3) {
                    three.setChecked(true);
                } else if (sortedid == 4) {
                    four.setChecked(true);
                }

                relativeLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!one.isChecked()) {
                            one.setChecked(true);
                            sortedid = 1;
                            page_count = 1;
                            productsRecycler.setVisibility(View.GONE);
                            getData();
                            dialog.dismiss();
                        }
                    }
                });

                relativeLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!two.isChecked()) {
                            two.setChecked(true);
                            productsRecycler.setVisibility(View.GONE);
                            sortedid = 2;
                            SearchActivity.page_count = 1;
                            getData();
                            dialog.dismiss();
                        }
                    }
                });
                relativeLayout3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!three.isChecked()) {
                            three.setChecked(true);
                            productsRecycler.setVisibility(View.GONE);
                            sortedid = 3;
                            SearchActivity.page_count = 1;
                            getData();
                            dialog.dismiss();
                        }
                    }
                });
                relativeLayout4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!four.isChecked()) {
                            four.setChecked(true);
                            productsRecycler.setVisibility(View.GONE);
                            sortedid = 4;
                            SearchActivity.page_count = 1;
                            getData();
                            dialog.dismiss();


                        }
                    }
                });


                dialog.show();
            }
            break;
            case R.id.filterButton:
                Intent intent = new Intent(this, ApplyFilter.class);
                intent.putExtra("thisisfromsearchactivity", "");
                intent.putIntegerArrayListExtra("takestaticarraylist", staticbrand);
                intent.putExtra("category_id", sucat_id);
                intent.putIntegerArrayListExtra("sa", null);
                intent.putStringArrayListExtra("fsd", null);
                startActivity(intent);

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //isViewWithCatalog = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(ProductActivity.this);
      //  isViewWithCatalog = viewWithCatalogState;
        if (shouldExecurOnResume) {
            getData();

        }
        shouldExecurOnResume = false;

    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        refreshLayout.setRefreshing(false);
    }
}
