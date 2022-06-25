package com.example.grocery.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.ProductAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.CustomViews.CustomTextView;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.ProductModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.ApplicationUtility;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.ColorChangerNoItemAvailable;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.EndlessRecyclerViewScrollListener;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.example.grocery.Activities.ApplyFilter.maximumSeekBar;
import static com.example.grocery.Activities.ApplyFilter.minimumSeekBar;
import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.Activities.ProductActivity.name;
import static com.example.grocery.Activities.ProductDetails.productid;
import static com.example.grocery.Adapter.ProductAdapter.positionValue;
import static com.example.grocery.CustomViews.FontSetter.FONTNAME;
import static com.example.grocery.R.layout.sorting;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout changeLayout;
    RelativeLayout sort;
    RelativeLayout filter;
    public RecyclerView searchActivityRecyycler;
    List<ProductModel> list;
    public static int page_count = 1;
    private Dialog dialog;
    private AppCompatRadioButton one, two, three, four;
    public static String searchMax;
    public static String searchMin;
    public static String catid;
    public static int sortedida;
    private EditText editTextSearch;
    public static String formatedStringSearch;
    public static ArrayList<Integer> searchBrands;
    public static String searchKeyword;
    private ImageView imageView;
    public static String imagea;
    private RelativeLayout rel1, rel2, rel3, rel4;
    private TextView text1, text2, text3, text4;
    public ProductAdapter productAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    TextView noProductAvailable;
    public static boolean loading;
    public static int layoutType = 1;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    public static boolean shouldExecurOnResume;
    public static int page_count_number;
    private int totalCount;
    private CardView cardView;
    private SwipeRefreshLayout refreshLayout;
    //private SharedPreferences labelsShared;
    private TextView sortText;
    private TextView filterText;
    private CustomTextView notificationCount;
    private ImageView clearImageToolbar;
    private final int REQUEST_CODE = 1234;
    private boolean is_voice;

    Typeface typeface = null;
    Typeface bold = null;
    Typeface normal = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        //for colorg
        setContentView(R.layout.activity_filter);

        typeface = Typeface.createFromAsset(getAssets(), FONTNAME);
        bold = Typeface.create(typeface, Typeface.BOLD);
        normal = Typeface.create(typeface, Typeface.NORMAL);

        //labelsShared = getSharedPreferences("labels", MODE_PRIVATE);
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

        searchActivityRecyycler = (RecyclerView) findViewById(R.id.searchrec);

        cardView = (CardView) findViewById(R.id.cardall);
        cardView.setVisibility(View.GONE);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setColorSchemeColors((Color.parseColor("#" + Appearance.appSettings.getText_color())));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchActivityRecyycler = (RecyclerView) findViewById(R.id.searchrec);

                // Refresh items
                refreshItems();
            }
        });

        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        ColorChangerNoItemAvailable colorChangerNoItemAvailable = new ColorChangerNoItemAvailable(this);
        findViewById(R.id.cccc).setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        DrawableCompat.setTint(((ImageView) findViewById(R.id.toolcart)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        DrawableCompat.setTint(((ImageView) findViewById(R.id.searchsearch)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        DrawableCompat.setTint(((ImageView) findViewById(R.id.searchback)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

        //findViewById(R.id.)
        searchMin = "";
        page_count = 1;
        shouldExecurOnResume = false;
        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isViewWithCatalog = false;
                finish();
            }
        });
        RelativeLayout toolBarMic = (RelativeLayout) findViewById(R.id.toolmic);
        toolBarMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                is_voice = true;
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something");

                startActivityForResult(intent, REQUEST_CODE);
                if (findViewById(R.id.noitemavalable).getVisibility() == View.VISIBLE) {
                    findViewById(R.id.noitemavalable).setVisibility(View.GONE);
                }
            }
        });
        TextView cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
        TextView notification = (TextView) findViewById(R.id.notification_count_textview);
        if (Dashboard.cart_count !=0){
            cartCount.setVisibility(View.VISIBLE);
            cartCount.setText(String.valueOf(Dashboard.cart_count));
        }
        RelativeLayout toolCart = (RelativeLayout) findViewById(R.id.cart);
        toolCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });


        editTextSearch = (EditText) findViewById(R.id.inputSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (is_voice) {
                    page_count = 1;
                    getData();
                    View view = SearchActivity.this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            }
        });
        clearImageToolbar = (ImageView) findViewById(R.id.cancel1);
        DrawableCompat.setTint(clearImageToolbar.getDrawable(), Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        clearImageToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextSearch.setText("");
                searchKeyword = "";
            }
        });
        editTextSearch.setText(searchKeyword);
        TextView similarvisibility = (TextView) findViewById(R.id.visibilitysimilar);
        editTextSearch.setSelection(searchKeyword.length());
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchKeyword = editTextSearch.getText().toString();
                    page_count = 1;
                    getData();
                    View view = SearchActivity.this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });
        if (productid == 0) {
            similarvisibility.setVisibility(View.GONE);
        } else {
            similarvisibility.setVisibility(View.VISIBLE);
            try {
                String product = getString(R.string.similar_products_of);
                similarvisibility.setText(product + " " + name);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        if (Appearance.appSettings.getShow_product_type() == 0) {
            isViewWithCatalog = true;
        }

        initUi();
        RelativeLayout searchButton = (RelativeLayout) findViewById(R.id.searchbutton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page_count = 1;
                getData();
                View view = SearchActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }

        });
        getData();
        noProductAvailable = (TextView) findViewById(R.id.noproductavailable);
        if (Appearance.appSettings.getShow_product_type() == 0) {
            SearchActivity.imagea = "true";
        }
        if (SearchActivity.imagea.matches("true")) {
            SearchActivity.imagea = "false";
            imageView.setImageResource(R.drawable.grid);
        } else if (SearchActivity.imagea.matches("false")) {
            SearchActivity.imagea = "true";
            imageView.setImageResource(R.drawable.navigation);
        }

    }

    private void setData(JSONArray jsonArray1) {
        Gson gson = new Gson();
        list = Arrays.asList(gson.fromJson(jsonArray1.toString(), ProductModel[].class));

        List<ProductModel> productModels = new ArrayList<>();
        productModels.addAll(list);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //isViewWithCatalog=true;
        //Here we have to set dynamic layout manager

        final RecyclerView.LayoutManager layoutManager = !isViewWithCatalog ? mLayoutManager : linearLayoutManager;
        cardView.setVisibility(View.VISIBLE);


        searchActivityRecyycler.setLayoutManager(layoutManager);
       /* SlideInUpAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));
        searchActivityRecyycler.setItemAnimator(animator);*/
        productAdapter = new ProductAdapter(this, productModels);

        searchActivityRecyycler.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            // Populate the wordsList with the String values the recognition engine thought it heard
            final ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (!matches.isEmpty()) {
                String Query = matches.get(0);
                editTextSearch.setText(Query);
            }
            is_voice = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changelayout:
                final LayoutAnimationController controller =
                        AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down);

                searchActivityRecyycler.setLayoutAnimation(controller);

                searchActivityRecyycler.scheduleLayoutAnimation();

                isViewWithCatalog = !isViewWithCatalog;
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                GridLayoutManager gridlayout = new GridLayoutManager(this, 2);
                if (isViewWithCatalog) {
                    searchActivityRecyycler.setLayoutManager(layoutManager);
                    if (positionValue > 2) {
                        positionValue = positionValue - 3;
                    }

                } else {
                    searchActivityRecyycler.setLayoutManager(gridlayout);
                    if (positionValue > 2) {
                        positionValue = positionValue - 3;
                    }
                }

                (!isViewWithCatalog ? gridlayout : layoutManager).scrollToPosition(positionValue);
                searchActivityRecyycler.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();

                if (SearchActivity.imagea.matches("true")) {
                    SearchActivity.imagea = "false";
                    imageView.setImageResource(R.drawable.grid);
                } else if (SearchActivity.imagea.matches("false")) {
                    SearchActivity.imagea = "true";
                    imageView.setImageResource(R.drawable.navigation);
                }

                break;
            case R.id.sortingbutton: {

                dialog = new Dialog(this, R.style.MyDialogTheme);
                dialog.setContentView(sorting);
                dialog.setCanceledOnTouchOutside(true);
                getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                WindowManager.LayoutParams layoutparameters = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                layoutparameters.copyFrom(window.getAttributes());
                layoutparameters.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutparameters.height = WindowManager.LayoutParams.WRAP_CONTENT;

                layoutparameters.gravity = Gravity.BOTTOM;
                window.setAttributes(layoutparameters);
                TextView sortByText = (TextView) dialog.findViewById(R.id.sortByText);
                TextView newestFirstText = (TextView) dialog.findViewById(R.id.newestFirstText);
                TextView assendindorderText = (TextView) dialog.findViewById(R.id.assendindorderText);
                TextView hightolowText = (TextView) dialog.findViewById(R.id.hightolowText);
                TextView lowtoHighText = (TextView) dialog.findViewById(R.id.lowtoHighText);
                try {
                  //  sortByText.setText(Label.productLabel.getSort());
                    //newestFirstText.setText(Label.productLabel.getNewest_first());
                   // assendindorderText.setText(Label.productLabel.getAscending_order());
                    //hightolowText.setText(Label.productLabel.getHigh_to_low());
                    //lowtoHighText.setText(Label.productLabel.getLow_to_high());

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
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
                rel1 = (RelativeLayout) dialog.findViewById(R.id.rel1);
                rel2 = (RelativeLayout) dialog.findViewById(R.id.rel2);
                rel3 = (RelativeLayout) dialog.findViewById(R.id.rel3);
                rel4 = (RelativeLayout) dialog.findViewById(R.id.rel4);
              /*  text1 = (TextView) dialog.findViewById(R.id.text1);
                text2 = (TextView) dialog.findViewById(R.id.text2);
                text3 = (TextView) dialog.findViewById(R.id.text3);
                text4 = (TextView) dialog.findViewById(R.id.text4);*/


                if (sortedida == 1) {
                    one.setChecked(true);
                }
                if (sortedida == 2) {
                    two.setChecked(true);
                }
                if (sortedida == 3) {
                    three.setChecked(true);
                }
                if (sortedida == 4) {
                    four.setChecked(true);
                }

                rel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!one.isChecked()) {
                            one.setChecked(true);
                            sortedida = 1;
                            page_count = 1;
                            searchActivityRecyycler.setVisibility(View.GONE);
                            sortChnage = true;
                            getData();
                            dialog.dismiss();
                        }
                    }
                });

                rel2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (!two.isChecked()) {
                            two.setChecked(true);
                            searchActivityRecyycler.setVisibility(View.GONE);
                            sortedida = 2;
                            page_count = 1;
                            sortChnage = true;
                            getData();
                            dialog.dismiss();

                        }
                    }
                });
                rel3.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (!three.isChecked()) {
                            three.setChecked(true);
                            searchActivityRecyycler.setVisibility(View.GONE);
                            sortedida = 3;
                            page_count = 1;
                            sortChnage = true;
                            getData();
                            dialog.dismiss();
                        }
                    }
                });
                rel4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!four.isChecked()) {
                            four.setChecked(true);
                            searchActivityRecyycler.setVisibility(View.GONE);
                            sortedida = 4;
                            page_count = 1;
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
                intent.putExtra("thisisfromsearchactivity", "takeit");
                intent.putExtra("category_id", 0);
                startActivity(intent);
                break;
        }
    }

    private void makeFilerBold() {
        filterText.setTypeface(bold);
        //sortText.setTypeface(normal);
    }

    private void makeSortBold() {
        sortText.setTypeface(bold);
        //filterText.setTypeface(normal);
    }

    public void getData() {
        findViewById(R.id.noitemavalable).setVisibility(View.GONE);
        cardView.setVisibility(View.GONE);
        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        findViewById(R.id.retryImage).setVisibility(View.GONE);

        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);

        searchActivityRecyycler.setVisibility(View.GONE);
        searchBrands = ApplyFilter.brandArrays;
        if (formatedStringSearch.matches("")) {
            formatedStringSearch = "";
        } else {
            formatedStringSearch = searchBrands.toString()
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")
                    .replace(" ", "")//remove the left bracket
                    .trim();
        }
    /*   if (searchMax.matches("0")) {
            searchMax = "";
        }
        if (searchMin.matches("0")) {
            searchMin = "";
        }*/
        boolean checkConnection = new ApplicationUtility().checkConnection(this);
        System.out.println("sdnj" + !checkConnection);
        System.out.println("conn" + checkConnection);
        System.out.println("connection on");
        if (maximumSeekBar == null || minimumSeekBar == null) {
            maximumSeekBar = String.valueOf(5000);
            minimumSeekBar = String.valueOf(0);
        }
        searchKeyword = editTextSearch.getText().toString();

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
            jsonObject.put("category_id", "");
            jsonObject.put("max_price", searchMax);
            jsonObject.put("min_price", searchMin);
            jsonObject.put("order",sortedida);
            jsonObject.put("brand", "");
            jsonObject.put("keyword", searchKeyword);
            jsonObject.put("product_id", productid);
            jsonObject.put("page", page_count);
            jsonObject.put("language_id", languageid);
        //    Toast.makeText(this, ""+jsonObject, Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("adxsz" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_FIlteredPRoduct, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhsa" + response.toString());
                if (!new ResponseHandler().validateResponse(SearchActivity.this, response)) {
                    return;
                }
                try {

                    page_count_number = response.getJSONObject("data").getJSONObject("data").getInt("page_count");
                    int product_count = response.getJSONObject("data").getJSONObject("data").getInt("total_count");
                    String productCountString = "";
                    if (product_count != 0) {
                        productCountString = "" + product_count + "+ ";
                    }
                    JSONArray jsonArray1 = response.getJSONObject("data").getJSONObject("data").getJSONArray("products");
                    Dashboard.cart_count = response.getJSONObject("data").getJSONObject("data").getInt("cart_count");
                    Dashboard.notification_count = response.getJSONObject("data").getJSONObject("data").getInt("notification_count");
                    String search = getString(R.string.search);
                    String product = getString(R.string.products);
                    editTextSearch.setHint(search +
                            " " + productCountString
                            + product);
                    editTextSearch.setHintTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

                    if (response.getJSONObject("data").getJSONObject("data").getInt("total_count") == 0) {
                        cardView.setVisibility(View.GONE);
                        findViewById(R.id.noitemavalable).setVisibility(View.VISIBLE);
                        Button button = (Button) findViewById(R.id.noitemavailablebutton);

                        button.setBackground(SearchActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                        GradientDrawable bgShape = (GradientDrawable) button.getBackground();
                        bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                        button.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

                        TextView textView = (TextView) findViewById(R.id.noitemavailabletext);
                      //  textView.setText(Label.productLabel.getSearch_empty_title());
                        TextView textView1 = (TextView) findViewById(R.id.extra);
                     //   textView1.setText(Label.productLabel.getSearch_empty_description());
                        findViewById(R.id.imagenoitemAvailable).setBackground(SearchActivity.this.getResources().getDrawable(R.drawable.empty_search_default));

                      //  button.setText(Label.productLabel.getSearch());
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                findViewById(R.id.noitemavalable).setVisibility(View.GONE);
                                //     editTextSearch.requestFocus();
                                View view = SearchActivity.this.getCurrentFocus();

                                InputMethodManager imm = (InputMethodManager)
                                        getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(editTextSearch,
                                        InputMethodManager.SHOW_IMPLICIT);
                            }
                        });

                    } else {
                        noProductAvailable.setVisibility(View.GONE);
                        totalCount = response.getJSONObject("data").getJSONObject("data").getInt("total_count");
                        String products = getString(R.string.products);
                        new CustomToast(SearchActivity.this, String.valueOf(response.getJSONObject("data").getJSONObject("data").getString("total_count"))
                                + " " + products);

                        searchActivityRecyycler.setVisibility(View.VISIBLE);
                        if (jsonArray1.length() == 0) {

                        }
                        setData(jsonArray1);
                        if (sortChnage) {
                            makeSortBold();
                            sortChnage = false;
                        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isViewWithCatalog = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("shouldexecuteonResume" + shouldExecurOnResume);
        new CartCountUtil(SearchActivity.this);
        if (shouldExecurOnResume) {
            getData();

            if (filterChange) {
                makeFilerBold();
                filterChange = false;
            }

        }
        shouldExecurOnResume = false;
    }

    public static boolean sortChnage;
    public static boolean filterChange;

    void refreshItems() {
        // Load items
        // ...
        // Load complete
        page_count = 1;
        searchBrands = ApplyFilter.brandArrays;
        if (formatedStringSearch.matches("")) {
            formatedStringSearch = "";
        } else {
            formatedStringSearch = searchBrands.toString()
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")
                    .replace(" ", "")//remove the left bracket
                    .trim();
        }

   /*   if (searchMax.matches("0")) {
            searchMax = "";
        }
        if (searchMin.matches("0")) {
            searchMin = "";
        }*/


        formatedStringSearch = "";
        searchMax = "";
        searchMin = "";

        boolean checkConnection = new ApplicationUtility().checkConnection(this);
        System.out.println("sdnj" + !checkConnection);
        System.out.println("conn" + checkConnection);
        System.out.println("connection on");
        if (maximumSeekBar == null || minimumSeekBar == null) {
            maximumSeekBar = String.valueOf(5000);
            minimumSeekBar = String.valueOf(0);
        }
        searchKeyword = editTextSearch.getText().toString();
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
            jsonObject.put("category_id", "");
            jsonObject.put("max_price", searchMax);
            jsonObject.put("min_price", searchMin);
            jsonObject.put("order",sortedida);
            jsonObject.put("brand", "");
            jsonObject.put("keyword", searchKeyword);
            jsonObject.put("product_id", productid);
            jsonObject.put("page", page_count);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("SearchACtivity " + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_FIlteredPRoduct, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhsa" + response.toString());
                if (!new ResponseHandler().validateResponse(SearchActivity.this, response)) {
                    return;
                }
                try {
                    page_count_number = response.getJSONObject("data").getJSONObject("data").getInt("page_count");
                    JSONArray jsonArray1 = response.getJSONObject("data").getJSONObject("data").getJSONArray("products");
                    Dashboard.cart_count = response.getJSONObject("data").getJSONObject("data").getInt("cart_count");
                     Dashboard.notification_count = response.getJSONObject("data").getJSONObject("data").getInt("notification_count");

                    String search = getString(R.string.search);
                    String product_1 = getString(R.string.products);
                    editTextSearch.setHint(search +
                            " " + String.valueOf(response.getJSONObject("data").getJSONObject("data").getString("total_count")) + "+ "
                            + product_1);
                    editTextSearch.setHintTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

                    if (response.getJSONObject("data").getJSONObject("data").getInt("total_count") == 0) {
                        noProductAvailable.setVisibility(View.VISIBLE);


                    } else {
                        noProductAvailable.setVisibility(View.GONE);
                        totalCount = response.getJSONObject("data").getJSONObject("data").getInt("total_count");
                        String product = getString(R.string.products);
                        new CustomToast(SearchActivity.this, String.valueOf(response.getJSONObject("data").getJSONObject("data").getString("total_count"))
                                + " " +product);

                    }
                    searchActivityRecyycler.setVisibility(View.VISIBLE);
                    if (jsonArray1.length() == 0) {

                        if (jsonArray1.length() == 0) {
                            cardView.setVisibility(View.GONE);
                            findViewById(R.id.noitemavalable).setVisibility(View.VISIBLE);
                            Button button = (Button) findViewById(R.id.noitemavailablebutton);

                            button.setBackground(SearchActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                            GradientDrawable bgShape = (GradientDrawable) button.getBackground();
                            bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                            button.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));


                            TextView textView = (TextView) findViewById(R.id.noitemavailabletext);
                            String title = getString(R.string.search_empty_title);
                            textView.setText(title);
                          //  textView.setText(Label.productLabel.getSearch_empty_title());
                            TextView textView1 = (TextView) findViewById(R.id.extra);
                            String desc = getString(R.string.search_empty_title);
                            textView1.setText(title);
                          //  textView1.setText(Label.productLabel.getSearch_empty_description());
                            findViewById(R.id.imagenoitemAvailable).setBackground(SearchActivity.this.getResources().getDrawable(R.drawable.empty_search_default));

                          //  button.setText(Label.productLabel.getSearch());
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    findViewById(R.id.noitemavalable).setVisibility(View.GONE);
                                    //     editTextSearch.requestFocus();
                                    View view = SearchActivity.this.getCurrentFocus();

                                    InputMethodManager imm = (InputMethodManager)
                                            getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.showSoftInput(editTextSearch,
                                            InputMethodManager.SHOW_IMPLICIT);


                                }
                            });
                        }
                    }
                    setData1(jsonArray1);
                    onItemsLoadComplete();
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

    private void setData1(JSONArray jsonArray1) {
        Gson gson = new Gson();
        list = Arrays.asList(gson.fromJson(jsonArray1.toString(), ProductModel[].class));

        List<ProductModel> productModels = new ArrayList<>();
        productModels.addAll(list);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        final RecyclerView.LayoutManager layoutManager = !isViewWithCatalog ? mLayoutManager : linearLayoutManager;
        cardView.setVisibility(View.VISIBLE);


        searchActivityRecyycler.setLayoutManager(layoutManager);
        productAdapter = null;
        productAdapter = new ProductAdapter(this, productModels);

        searchActivityRecyycler.setAdapter(productAdapter);
    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        refreshLayout.setRefreshing(false);
    }
}