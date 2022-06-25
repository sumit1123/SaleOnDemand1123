package com.example.grocery.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.BrandAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.BrandModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.RangeSeekBar;
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

import androidx.cardview.widget.CardView;
import me.bendik.simplerangeview.SimpleRangeView;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;

public class ApplyFilter extends AppCompatActivity {
    private CardView cardbrand;
    private Button applyFilter;
    public Dialog dialog;
    public TextView selectedBrand;
    public TextView hide;
    private TextView minimumSeekText;
    private TextView maximumSeekText;
    private TextView textMin;
    private TextView textMax;
    private String searrchActtivity;
    private int categoryId;
    private String formattedString;
    public static ArrayList<Integer> brandArrays;
    private EditText searchBrands;
    private BrandAdapter brandAdapter;
    private ArrayList<Object> stringss;
    private List<BrandModel> lista;
    public static String minimumSeekBar, maximumSeekBar;
    public static int minSeekBar = 0, maxSeekBar = 5000;

    int brandID;
    boolean shouldExecuteOnResume;
    private TextView cartCount;
    //private SharedPreferences labelsShared;
    private TextView cardbrandText;
    private TextView selectBrandText;
    private BrandModel.BrandTranslationBean productTranslationBean;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras1 = getIntent().getExtras();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        searrchActtivity = extras1.getString("thisisfromsearchactivity");
        SearchActivity.shouldExecurOnResume = false;
        setContentView(R.layout.activity_apply_filter);

        searchBrands = (EditText) findViewById(R.id.searchBrands);
        applyFilter = (Button) findViewById(R.id.apply);
        selectBrandText = (TextView) findViewById(R.id.selectBrandText);
        //labelsShared = getSharedPreferences("labels", MODE_PRIVATE);
        cardbrandText = (TextView) findViewById(R.id.cardbrand);


        try {
            TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
            String getfilter = getString(R.string.filter);
            toolbarTitle.setText(getfilter);

          String brand = getString(R.string.search_by_brand_name);
          String card = getString(R.string.select_brands);
          String apply = getString(R.string.apply);
            searchBrands.setHint(brand);
            cardbrandText.setText(card);
            applyFilter.setText(apply);
            selectBrandText.setText(card);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
        TextView notificationCount = (TextView) findViewById(R.id.notification_count_textview);
        if (Dashboard.cart_count != 0) {
            cartCount.setVisibility(View.VISIBLE);
            cartCount.setText(String.valueOf(Dashboard.cart_count));
        }
        initi();
        RelativeLayout imageNotification = (RelativeLayout) findViewById(R.id.toolnotification);

        imageNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplyFilter.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        if (Appearance.appSettings.getIs_notification() == 0) {
            imageNotification.setVisibility(View.GONE);
            notificationCount.setText(String.valueOf(Dashboard.notification_count));
        }
        RelativeLayout toolSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplyFilter.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                SearchActivity.searchMin = "";
                ApplyFilter.brandArrays = null;
                ProductDetails.productid = 0;
                isViewWithCatalog = false;

                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.minimumSeekBar = "0";
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
                Intent intent = new Intent(ApplyFilter.this, CartActivity.class);
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

        selectedBrand = (TextView) findViewById(R.id.selectedbrand);

        Bundle extras = getIntent().getExtras();
        categoryId = extras.getInt("category_id");

        try {
            String none = getString(R.string.none_selected);
            selectedBrand.setText(none);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        textMin = (TextView) findViewById(R.id.textMin);
        textMax = (TextView) findViewById(R.id.textMax);
        textMin.setText(getText(R.string.min));
        textMax.setText(getText(R.string.max));
        String min = getString(R.string.min);
        String max = getString(R.string.max);
        textMin.setText(min);
        textMax.setText(max);

        minimumSeekText = (TextView) findViewById(R.id.minimumText);
        maximumSeekText = (TextView) findViewById(R.id.maximumText);


        minimumSeekText.setText(minimumSeekBar);
        maximumSeekText.setText(maximumSeekBar);


        if (maximumSeekBar.matches("5000")) {
            maximumSeekText.setText("5000+");
        }


        SimpleRangeView rangeSeekbar = findViewById(R.id.rangeSeekbar);

        rangeSeekbar.setStart(minSeekBar);
        rangeSeekbar.setEnd(maxSeekBar);

        rangeSeekbar.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
            @Override
            public void onStartRangeChanged(SimpleRangeView simpleRangeView, int minValue) {

                minSeekBar = minValue;

                minimumSeekBar = String.valueOf(minValue);
                minimumSeekText.setText(minimumSeekBar);
            }

            @Override
            public void onEndRangeChanged(SimpleRangeView simpleRangeView, int maxValue) {
                maxSeekBar = maxValue;

                maximumSeekBar = String.valueOf(maxValue);
                maximumSeekText.setText(maximumSeekBar);
                if (maxValue >= 5000) {
                    maximumSeekText.setText("5000+");
                    maximumSeekBar = String.valueOf(5000);
                    maxSeekBar = maxValue;
                }


            }
        });

//        RangeSeekBar rangeSeekbar = (RangeSeekBar) findViewById(R.id.rangeSeekbar);
//        rangeSeekbar.setColorFilter(ApplyFilter.this.getResources().getColor(R.color.black));
//        ColorStateList colorStateList = new ColorStateList(
//                new int[][]{
//                        new int[]{-android.R.attr.state_checked}, // unchecked
//                        new int[]{android.R.attr.state_checked}  // checked
//                },
//                new int[]{
//                        R.color.errorColor,
//                        R.color.active_yellow
//                }
//        );
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            rangeSeekbar.setBackgroundTintList(colorStateList);
//        }
//        rangeSeekbar.setRangeValues(0, 5000);
//        rangeSeekbar.setColorFilter(Color.parseColor("#000000"));
//        rangeSeekbar.setSelectedMaxValue(Integer.parseInt(maximumSeekBar));
//        rangeSeekbar.setSelectedMinValue(Integer.parseInt(minimumSeekBar));
//        rangeSeekbar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
//            @Override
//            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
//
//
//                minimumSeekBar = String.valueOf(minValue);
//                maximumSeekBar = String.valueOf(maxValue);
//
//                minimumSeekText.setText(minimumSeekBar);
//
//                maximumSeekText.setText(maximumSeekBar);
//                if (maxValue.equals("5000")) {
//                    maximumSeekText.setText("5000+");
//                }
//
//            }
//        });
//        rangeSeekbar.setNotifyWhileDragging(true);
        applyFilter.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        applyFilter.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        applyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.filterChange = true;
                initBrands();
                if (searrchActtivity.matches("")) {

                    SearchActivity.searchMax = maximumSeekBar;
                    SearchActivity.searchMin = minimumSeekBar;
                    if (brandArrays == null) {
                        ProductActivity.productbrands = null;
                    } else {
                        SearchActivity.formatedStringSearch = "asd";
                        ProductActivity.productbrands = brandArrays;
                    }
                    SearchActivity.shouldExecurOnResume = true;
                    SearchActivity.page_count = 1;

                    finish();
                } else {
                    SearchActivity.searchMax = maximumSeekBar;
                    SearchActivity.searchMin = minimumSeekBar;
                    if (brandArrays == null) {
                        ProductActivity.productbrands = null;
                    } else {
                        SearchActivity.formatedStringSearch = "asd";
                        SearchActivity.searchBrands = brandArrays;
                    }
                    SearchActivity.shouldExecurOnResume = true;
                    SearchActivity.page_count = 1;

                    finish();
                }
            }
        });
        getData();
    }

    private void initBrands() {
        brandArrays = new ArrayList<Integer>();
        if (searchBrands.isShown()) {
            for (int i = 0; i < BrandAdapter.list.size(); i++) {
                if (BrandAdapter.list.get(i).getSelected()) {
                    brandArrays.add(BrandAdapter.list.get(i).getBrand_id());
                }
            }
        }
    }

    protected void getData() {

        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);

        findViewById(R.id.retryImage).setVisibility(View.GONE);

        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String password = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("category_id", categoryId);
            jsonObject.put("id", userid);
            jsonObject.put("password", password);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("wxsdaz" + categoryId);
        VolleyTask volleyTask = new VolleyTask(ApplyFilter.this, IConstants.URL_AllBRANDS, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());
                findViewById(R.id.whiteloader).setVisibility(View.GONE);
                if (!new ResponseHandler().validateResponse(ApplyFilter.this, response)) {
                    return;
                }
                try {
                    JSONArray jsonArray1 = response.getJSONObject("data").getJSONArray("data");

                    if (jsonArray1.length() != 0) {
                        CardView cardViewBrand1 = (CardView) findViewById(R.id.dczx);
                        cardViewBrand1.setVisibility(View.VISIBLE);

                        searchBrands.setVisibility(View.VISIBLE);
                        CardView cardViewBrand = (CardView) findViewById(R.id.brandvisibility);
                        cardViewBrand.setVisibility(View.VISIBLE);
                        setBrands(jsonArray1);
                    }

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

    private void setBrands(JSONArray jsonArray1) {
        Gson gson = new Gson();
        lista = Arrays.asList(gson.fromJson(jsonArray1.toString(), BrandModel[].class));

        System.out.println("dada" + lista);
        RecyclerView brandsRecycler = (RecyclerView) findViewById(R.id.recbrands);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ApplyFilter.this);
        brandAdapter = new BrandAdapter(lista, ApplyFilter.this);
        brandsRecycler.setLayoutManager(layoutManager);
        brandsRecycler.setAdapter(brandAdapter);
    }

    public void initi() {
        searchBrands.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {
                query = query.toString().toLowerCase();

                final List<BrandModel> filteredList = new ArrayList<>();
                for (int i = 0; i < lista.size(); i++) {
                    if (lista.get(i).getBrand_translation().equals(null)) {
                        text = lista.get(i).getBrand_translation().getBrand_name().toLowerCase();
                    } else {
                        text = lista.get(i).getDefault_brand_translation().getBrand_name().toLowerCase();

                    }
                    if (text.contains(query)) {

                        filteredList.add(lista.get(i));
                    }
                }

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recbrands);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ApplyFilter.this);
                brandAdapter = new BrandAdapter(filteredList, ApplyFilter.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(brandAdapter);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (shouldExecuteOnResume) {
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
        } else {
            shouldExecuteOnResume = true;
        }

    }
}