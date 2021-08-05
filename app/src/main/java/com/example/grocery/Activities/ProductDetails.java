package com.example.grocery.Activities;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.BitmapAdapter;
import com.example.grocery.Adapter.DisplayImageAdapter;
import com.example.grocery.Adapter.ReviewAdpater;
import com.example.grocery.Adapter.Sliding_ImageAdapterDasboard;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.BitmapModel;
import com.example.grocery.Model.ChildVariantModel;
import com.example.grocery.Model.OptionalImagesModel;
import com.example.grocery.Model.ParentVariantModel;
import com.example.grocery.Model.ReviewsModel;
import com.example.grocery.R;
import com.example.grocery.SliderPackage.CirclePageIndicator;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.interfaces.VarintListner;
import com.example.grocery.utils.ApplicationUtility;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.ColorManager;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.FixedSpeedScroller;
import com.example.grocery.utils.LoaderColorChanger;

import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.ViewPagerCustomDuration;
import com.example.grocery.utils.VolleyTask;
import com.willy.ratingbar.ScaleRatingBar;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.text.TextUtilsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import static com.example.grocery.Activities.Dashboard.cart_count;
import static com.example.grocery.Activities.Dashboard.notification_count;
import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.Activities.SplashScreen.packagename;
import static com.example.grocery.R.drawable.selectedborder;
import static com.example.grocery.interfaces.IConstants.URL_GETPRODUCTADDTOCARTT;
import static com.example.grocery.interfaces.IConstants.URL_PRODUCTDETAILS;

public class ProductDetails extends AppCompatActivity implements View.OnClickListener {
    public static List<Integer> selectedOptionId;
    public static int productid;
    public static String nameofProduct;
    public static String product_image;
    public static ArrayList<String> strings;
    public static int positionid;
    public static int sku_id;
    public static int parentVariantId, childVariantId;
    public TextView textviewcolor;
    public int whiteLoader;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    List<String> imagesEncodedList;
    int size_position;
    List<OptionalImagesModel> list;
    boolean shouldExecuteOnResume;
    private android.view.animation.Interpolator sInterpolator;
    private int minimumInteger = 1;
    private TextView displayInteger;
    private RelativeLayout add;
    private RelativeLayout minus;
    private String s;
    private Button addtoCartButton;
    private Button addReview;
    private Dialog dialog;
    private AppCompatEditText description;
    private ScaleRatingBar ratingBar;
    private int ratingStars;
    private Button viewAllReviews;
    private String userid;
    private URI uri;
    private String email, pwd;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private String off;
    private TextView productName;
    private TextView productSellingPrice;
    private TextView productActualPrice;
    private TextView productoff;
    private TextView textViewq;
    private String Pname;
    private Button viewCartButton;
    private TextView ratingCount;
    private ScaleRatingBar ratingbar;
    private TextView ratings;
    private TextView ratings2;
    private RecyclerView recyclerView;
    private RelativeLayout frameLayout;
    private LinearLayout scrollView;
    private LinearLayout wishListIcon;
    private RelativeLayout ratingLayout;
    private LinearLayout colorLayout;
    private TextView reviewsCountText;
    private TextView addMore;
    private AppCompatEditText addQuantityField;
    private NestedScrollView scrollview;
    private LinearLayout linearLayout3;
    private int quantity;
    private TextView tv;
    private double myPrice, marketPrice;
    private int myRating;
    private String myReview;
    private int isinWishlist;
    private boolean isincart;
    //private SharedPreferences labelsShared;
    private boolean isLeftToRight;
    private ViewPagerCustomDuration viewPagerSlider;
    private int NUM_PAGES3;
    private DisplayImageAdapter displayImageAdapter;
    private TextView cartCountTextView;
    private TextView notificationCountTextview;
    private boolean dontGoImagehowSlider;
    private Button choosebutton;
    private Bitmap FixBitmap;
    private String encodedImage ;
    private ArrayList<Object> base64String;
    private SwipeRefreshLayout refreshLayout;
    private boolean hideloading;
    private BitmapAdapter bitmapAdapter;
    private ArrayList<BitmapModel> bitmapModels;
    private TextView toolBarTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        setContentView(R.layout.activity_product_display);

        whiteLoader = 1;
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        base64String = new ArrayList<>();

        addtoCartButton = (Button) findViewById(R.id.addtocart);
        viewAllReviews = (Button) findViewById(R.id.viewAllreviews);
        TextView shareText = (TextView) findViewById(R.id.shareText);
        TextView addmore = (TextView) findViewById(R.id.addmore);
        TextView similarText = (TextView) findViewById(R.id.similarText);
        TextView wishlistText = (TextView) findViewById(R.id.wishlistText);
        TextView highlightText = (TextView) findViewById(R.id.highlightText);
        TextView ratingsandReviewsText = (TextView) findViewById(R.id.ratingsandReviewsText);
        TextView yourRatingText = (TextView) findViewById(R.id.yourRatingText);
        TextView viewAllreviews = (TextView) findViewById(R.id.viewAllreviews);
        addReview = (Button) findViewById(R.id.addreview);
        viewCartButton = (Button) findViewById(R.id.viewCart);

        try {
            String cart = getString(R.string.add_to_cart);
            String review = getString(R.string.view_all_reviews);
            String share = getString(R.string.share);
            String add_more = getString(R.string.add_more);
            String sim = getString(R.string.similar);
            String wishlist = getString(R.string.wishlist);
            String highlight = getString(R.string.highlights);
            String rae_rat = getString(R.string.reviews_and_ratings);
            String rating = getString(R.string.your_rating);
            String add_review = getString(R.string.add_review);
            String carts = getString(R.string.view_cart);

            addtoCartButton.setText(cart);
            viewAllReviews.setText(review);
            shareText.setText(share);
            addmore.setText(add_more);
            similarText.setText(sim);
            wishlistText.setText(wishlist);
            highlightText.setText(highlight);
            ratingsandReviewsText.setText(rae_rat);
            yourRatingText.setText(rating);
            viewAllreviews.setText(review);
            addReview.setText(add_review);
            viewCartButton.setText(carts);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        isincart = false;
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#" + Appearance.appSettings.getApp_status_bar_color()));
        }
        scrollview = (NestedScrollView) findViewById(R.id.scrollview);


       // circularImage.getProgressDrawable().setColorFilter(Color.parseColor("#"+sharedSettings.getString("text_color","#FFFFFF")), PorterDuff.Mode.SRC_IN);

        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
      //  ProgressBar circularImage = (ProgressBar) findViewById(R.id.circularImage);
        //((ProgressBar) findViewById(R.id.progressbarinner)).getIndeterminateDrawable().setColorFilter(Color.parseColor("#" + colorSettings.getString("text_color", "#FFFFFF")), android.graphics.PorterDuff.Mode.MULTIPLY);

     //   circularImage.getIndeterminateDrawable().setColorFilter(Color.parseColor("#" + colorSettings.getString("text_color", "#FFFFFF")), android.graphics.PorterDuff.Mode.MULTIPLY);*/
        reviewsCountText = (TextView) findViewById(R.id.ratingsdd);

        selectedOptionId = new ArrayList<>();

        textviewcolor = (TextView) findViewById(R.id.colortitle);
        wishListIcon = (LinearLayout) findViewById(R.id.wishlist);

        wishListIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isinWishlist == 0) {
                    addtoWishList();
                } else {
                    deleteFromWishlist();
                }

            }
        });

        scrollView = (LinearLayout) findViewById(R.id.ScrollView1);

        RelativeLayout toolBarNotiFication = (RelativeLayout) findViewById(R.id.toolnotification);

        toolBarNotiFication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetails.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        if (Appearance.appSettings.getIs_notification() == 0) {
            toolBarNotiFication.setVisibility(View.GONE);
        }
        TextView notificationCount = (TextView) findViewById(R.id.notification_count_textview);
        if(Dashboard.notification_count !=0)
        {
            notificationCount.setVisibility(View.VISIBLE);
            notificationCount.setText(String.valueOf(Dashboard.notification_count));
        }

        RelativeLayout toolbarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetails.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                SearchActivity.searchMin = "";
                ApplyFilter.maximumSeekBar = "5000";
                ProductDetails.productid = 0;
                isViewWithCatalog = false;

                ApplyFilter.brandArrays = null;
                ApplyFilter.minimumSeekBar = "0";
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
                Intent intent = new Intent(ProductDetails.this, CartActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout toolBarBack = (RelativeLayout) findViewById(R.id.toolimage);
        toolBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productid = 0;

                finish();
            }
        });
        toolBarTitle = (TextView) findViewById(R.id.titlebar);

        toolBarTitle.setText(nameofProduct);

        textViewq = (TextView) findViewById(R.id.des);
        initui();
        initlistner();
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

    private void deleteFromWishlist() {
        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);

        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String emaila = prefs.getString("email", "");
        String password = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));


        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put(("id"), userid);
            jsonObject.put("sku_id", sku_id);
            jsonObject.put(("password"), password);
            jsonObject.put(("language_id"), languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("fcsdxz" + jsonObject);

        VolleyTask volleyTask = new VolleyTask(ProductDetails.this, IConstants.BASE_URL + "deleteWishlist", jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {


            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                ResponseHandler responseHandler = new ResponseHandler();
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                if (!responseHandler.validateResponse(ProductDetails.this, response)) {
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                    return;
                }
                try {
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                    isinWishlist = 0;
                    ImageView imageView = (ImageView) findViewById(R.id.wishlisticon);
                    imageView.clearColorFilter();
                    new CustomToast(ProductDetails.this, response.getJSONObject("data").getString("msg"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fnErrorOccurred(String error) {
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
            }
        });
    }

    private void addtoWishList() {

        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs.getString("user_id", "");
        String emaila = prefs.getString("email", "");
        String pwda = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        System.out.println("takeparameters" + userid + " " + productid + pwda);
        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put(("id"), userid);
            jsonObject.put("sku_id", sku_id);
            jsonObject.put(("password"), pwda);
            jsonObject.put("language_id", languageid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("fcsdxz" + jsonObject);
        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);

        VolleyTask volleyTask = new VolleyTask(ProductDetails.this, IConstants.BASE_URL + "addToWishlist", jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {

                ResponseHandler responseHandler = new ResponseHandler();

                if (!responseHandler.validateResponse(ProductDetails.this, response)) {
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                    return;
                }
                ;
                try {
                    ImageView imageView = (ImageView) findViewById(R.id.wishlisticon);
                    imageView.setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                    isinWishlist = 1;
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                    new CustomToast(ProductDetails.this, response.getJSONObject("data").getString("msg"));

                    //context.showCart(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fnErrorOccurred(String error) {
            }
        });
    }

    private void loadParentVariant(final JSONArray jsonArray2) {
        Gson gson = new Gson();
        final List<ParentVariantModel> list = Arrays.asList(gson.fromJson(jsonArray2.toString(), ParentVariantModel[].class));
        final TagFlowLayout recyclerView1 = (TagFlowLayout) findViewById(R.id.recsize);
        isLeftToRight = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_LTR;

        if (!isLeftToRight) {
            recyclerView1.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        } else {
            recyclerView1.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        }
        final LayoutInflater mInflater = LayoutInflater.from(this);
        recyclerView1.setAdapter(new TagAdapter<ParentVariantModel>(list) {

            public ParentVariantModel.ParentVariantTranslationBean tanslationBean;

            @Override
            public View getView(FlowLayout parent, final int position, ParentVariantModel parentVariantModel) {
                tv = (TextView) mInflater.inflate(R.layout.tv,
                        recyclerView1, false);
                System.out.println("ewwwwwww" + parentVariantId);
                final Gson gson = new Gson();
                final ParentVariantModel productModel = list.get(position);
                String jsonString = gson.toJson(productModel.getParent_variant_translation());
                System.out.println("zdnfjc" + jsonString);

                if (jsonString.equals("null")) {

                    jsonString = gson.toJson(productModel.getDefault_parent_variant_translation());
                    System.out.println("zdnfjc" + jsonString);
                }
                tanslationBean = gson.fromJson(jsonString,
                        ParentVariantModel.ParentVariantTranslationBean.class);
                if (parentVariantModel.getParent_variant_id() == parentVariantId) {
                    GradientDrawable drawable = (GradientDrawable) ProductDetails.this.getResources().getDrawable(selectedborder);
                    drawable.setStroke(3, Color.parseColor("#" + Appearance.appSettings.getText_color()));
                    tv.setBackgroundDrawable(ProductDetails.this.getResources().getDrawable(R.drawable.selectedborder));
                } else {
                    tv.setBackgroundDrawable(ProductDetails.this.getResources().getDrawable(R.drawable.custom_border));
                }
               // tv.setText(tanslationBean.getParent_variant_name());

                recyclerView1.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        if (parentVariantId != list.get(position).getParent_variant_id()) {
                            parentVariantId = list.get(position).getParent_variant_id();

                            recyclerView1.getAdapter().notifyDataChanged();
                            sku_id = 0;
                            whiteLoader = 0;
                            getData();
                        }
                        return false;
                    }
                });
                return tv;
            }
        });
    }


    public void increaseInteger(View view) {
        minimumInteger = minimumInteger + 1;
        display(minimumInteger);
    }

    public void decreaseInteger(View view) {
        if (minimumInteger == 1) {
        } else {
            minimumInteger = minimumInteger - 1;
            display(minimumInteger);
        }
    }

    private void display(int number) {
        sendreq(number);
    }

    private void sendreq(final int number) {
        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);

        JSONObject jsonObject = new JSONObject();

        SharedPreferences prefs1 = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs1.getString("user_id", "");
        String pwd = prefs1.getString("pwd", "");
        String languageid = prefs1.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("sku_id", sku_id);
            jsonObject.put("quantity", number);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("language_id", languageid);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("dsdxszx" + jsonObject);
        String url = URL_GETPRODUCTADDTOCARTT;

        System.out.println("wedxs" + sku_id + "ewsd" + userid + "sadxz");
        VolleyTask volleyTask = new VolleyTask(this, url, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
                System.out.println("sed" + response);
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());


                if (!new ResponseHandler().validateResponse(ProductDetails.this, response)) {
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                    return;
                }
                try {
                    //   displayInteger.setText("" + minimumInteger);
                    String jsonObject1 = response.getJSONObject("data").getString("msg");
                    if (response.getJSONObject("data").getJSONObject("data").getJSONObject("cart").getInt("quantity") != number) {
                        new CustomToast(ProductDetails.this, jsonObject1);
                    }
                    cart_count = response.getJSONObject("data").getJSONObject("data").getInt("cart_count");
                    notification_count = response.getJSONObject("data").getJSONObject("data").getInt("notification_count");
                    new CartCountUtil(ProductDetails.this);
                    displayInteger.setText("" + response.getJSONObject("data").getJSONObject("data").getJSONObject("cart").getString("quantity"));
                    minimumInteger = response.getJSONObject("data").getJSONObject("data").getJSONObject("cart").getInt("quantity");
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void fnErrorOccurred(String error) {
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
               new CustomToast(ProductDetails.this, "No connection Available");


            }
        });

    }

    private void initlistner() {
        addMore.setOnClickListener(this);
        linearLayout1.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        add.setOnClickListener(this);
        minus.setOnClickListener(this);
        addtoCartButton.setOnClickListener(this);
        addReview.setOnClickListener(this);
        viewAllReviews.setOnClickListener(this);


    }

    private void initui() {
        ratings2 = (TextView) findViewById(R.id.rating2);
        ratingLayout = (RelativeLayout) findViewById(R.id.visibility);
        ratings = (TextView) findViewById(R.id.rating);
        ratingCount = (TextView) findViewById(R.id.ratingcount);
        ratingbar = findViewById(R.id.ratingBar);
        ratingbar.setEmptyDrawableRes(R.drawable.startempty);
        ratingbar.setFilledDrawable(getResources().getDrawable(R.drawable.starfilled));
        ratingbar.setClickable(false);
        addMore = (TextView) findViewById(R.id.addmore);
        addMore.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));

        viewCartButton.setOnClickListener(this);
        colorLayout = findViewById(R.id.sd);

        linearLayout = (LinearLayout) findViewById(R.id.shareproduct);
        linearLayout1 = (LinearLayout) findViewById(R.id.viewsimilar);
        displayInteger = (TextView) findViewById(
                R.id.quantity);
        add = (RelativeLayout) findViewById(R.id.plus);
        minus = (RelativeLayout) findViewById(R.id.minus);
        productName = (TextView) findViewById(R.id.productname);
        productSellingPrice = (TextView) findViewById(R.id.sellingprice);
        productActualPrice = (TextView) findViewById(R.id.actualprice);
        productoff = (TextView) findViewById(R.id.percentoff);


        addtoCartButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        addtoCartButton.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));

    }


    public void getData() {


        if (whiteLoader == 1) {
            if (!hideloading) {
                findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
            }
            findViewById(R.id.retryImage).setVisibility(View.GONE);
            Button retryButton = (Button) findViewById(R.id.retrybutton);
            retryButton.setVisibility(View.GONE);
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);

        }
        initui();
        initlistner();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs.getString("user_id", "");
        String pwda = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        boolean checkConnection = new ApplicationUtility().checkConnection(this);
        System.out.println("sdnj" + !checkConnection);
        System.out.println("conn" + checkConnection);
        System.out.println("connection on");
        System.out.println("sfedcw" + userid);
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwda);
            jsonObject.put("product_id", productid);
            jsonObject.put("sku_id", sku_id);
            jsonObject.put("parent_variant_id", 1);
            jsonObject.put("child_variant_id", 1);
            jsonObject.put("language_id", languageid);
            jsonObject.put("notification_id",NotificationActivity.noti_id);
            Log.i("AAAAid", ""+jsonObject.toString());
            Log.i("AAAAidpassword", pwda);
            Log.i("AAAAidproduct_id", productid + "");
            Log.i("AAAAidsku_id", sku_id + "");
            Log.i("AAAAidparent_variant_id", "" + parentVariantId);
            Log.i("AAAAidchild_variant_id", "" + childVariantId);
            Log.i("AAAAidlanguage_id", languageid);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("dswq2ws" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, URL_PRODUCTDETAILS, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
                if (!new ResponseHandler().validateResponse(ProductDetails.this, response)) {
                    finish();
                    return;
                }
                try {
                    JSONObject parentVariantObject = null;
                    JSONObject childVariantObject = null;
                    TextView parentVariantName = (TextView) findViewById(R.id.parentVariantName);
                    TextView childVariantName = (TextView) findViewById(R.id.childVariantName);
                    JSONObject jsonObject1 = response.getJSONObject("data").getJSONObject("data");
                    JSONObject jsonObject4 = jsonObject1.getJSONObject("sku");
                    if (!jsonObject1.getJSONObject("sku").getJSONObject("product").getJSONObject("parent_variant").get("variant_translation").equals(null)) {
                        parentVariantObject = jsonObject1.getJSONObject("sku").getJSONObject("product").getJSONObject("parent_variant").getJSONObject("variant_translation");

                    } else {
                        parentVariantObject = jsonObject1.getJSONObject("sku").getJSONObject("product").getJSONObject("parent_variant").getJSONObject("default_variant_translation");
                    }
                    if (!jsonObject1.getJSONObject("sku").getJSONObject("product").getJSONObject("child_variant").get("variant_translation").equals(null)) {
                        childVariantObject = jsonObject1.getJSONObject("sku").getJSONObject("product").getJSONObject("child_variant").getJSONObject("variant_translation");

                    } else {
                        childVariantObject = jsonObject1.getJSONObject("sku").getJSONObject("product").getJSONObject("child_variant").getJSONObject("default_variant_translation");

                    }
                    parentVariantName.setText(parentVariantObject.getString("variant_name"));
                    childVariantName.setText(childVariantObject.getString("variant_name"));

                    quantity = jsonObject4.getInt("quantity");
                    if (quantity <= 0 && jsonObject1.getJSONObject("sku").getJSONObject("product").getInt("track_inventory") == 1) {
                        String out_of_stock = getString(R.string.out_of_stock);
                        addtoCartButton.setText(out_of_stock);

                        addtoCartButton.setEnabled(false);
                    } else {
                        String addtocart = getString(R.string.add_to_cart);
                        addtoCartButton.setText(addtocart);
                        findViewById(R.id.card).setVisibility(View.GONE);
                        findViewById(R.id.addmore).setVisibility(View.GONE);
                        addtoCartButton.setEnabled(true);
                        isincart = false;
                    }
                    if (!jsonObject1.get("cart").equals(null)) {
                        isincart = true;
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("cart");
                        displayInteger.setText("" + jsonObject2.getString("quantity"));
                        minimumInteger = jsonObject2.getInt("quantity");
                        String buy = getString(R.string.buy_now);
                        addtoCartButton.setText(buy);
                        findViewById(R.id.card).setVisibility(View.VISIBLE);
                        findViewById(R.id.addmore).setVisibility(View.VISIBLE);
                    } else {
                        displayInteger.setText("" + 1);
                        minimumInteger = 1;
                    }
                    System.out.println("sdxz" + jsonObject1.getJSONObject("sku").getJSONObject("parent_variant"));
                    JSONObject parentJson = null;
                    if (jsonObject1.getJSONObject("sku").getJSONObject("parent_variant").get("variant_option_translation").equals(null)) {
                        parentJson = jsonObject1.getJSONObject("sku").getJSONObject("parent_variant").getJSONObject("default_variant_option_translation");

                    } else {
                        parentJson = jsonObject1.getJSONObject("sku").getJSONObject("parent_variant").getJSONObject("variant_option_translation");
                    }
                    JSONObject childJson = null;
                    if (jsonObject1.getJSONObject("sku").getJSONObject("child_variant").get("variant_option_translation").equals(null)) {
                        childJson = jsonObject1.getJSONObject("sku").getJSONObject("child_variant").getJSONObject("default_variant_option_translation");
                    } else {
                        childJson = jsonObject1.getJSONObject("sku").getJSONObject("child_variant").getJSONObject("variant_option_translation");
                    }

                    String child = childJson.getString("variant_option_name");
                    String parent = parentJson.getString("variant_option_name");

                    JSONArray parentVariant = jsonObject1.getJSONArray("parent_variants");
                    JSONArray childVariant = jsonObject1.getJSONArray("child_variants");

                    parentVariantId = jsonObject1.getInt("parent_variant_id");
                    childVariantId = jsonObject1.getInt("child_variant_id");
                    Log.d("parentvarid==",""+parentVariantId);
                    Log.d("childVariantId==",""+childVariantId);
                    //JSONArray selectedOptionsId=jsonObject1.getJSONArray("selected_options");
                    CardView parentVariantCard = (CardView) findViewById(R.id.parentVariantVisibility);
                    CardView childVariantCard = (CardView) findViewById(R.id.childVariantVisibility);

                    System.out.println("sdxz" + childVariant.length() + "sdx" + child);
                    if (childVariant.length() == 0) {
                        childVariantCard.setVisibility(View.GONE);
                    } else {
                        loadChildVariant(childVariant);
                    }
                    if (parentVariant.length() == 0) {
                        parentVariantCard.setVisibility(View.GONE);
                    } else {
                        loadParentVariant(parentVariant);
                    }
                    ImageView imageView = (ImageView) findViewById(R.id.wishlisticon);

                    if (!jsonObject1.getJSONObject("sku").get("wishlist").equals(null)) {
                        imageView.setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                        isinWishlist = 1;
                    } else {
                        isinWishlist = 0;
                        imageView.clearColorFilter();
                    }


                    sku_id = jsonObject4.getInt("sku_id");
                    myPrice = jsonObject4.getDouble("my_price");
                    marketPrice = jsonObject4.getDouble("market_price");


                    JSONArray jsonArray = jsonObject1.getJSONArray("images");


                    JSONArray jsonArray1 = jsonObject1.getJSONArray("reviews");
                    System.out.println("dcxz" + jsonArray1.length());
                    if (jsonArray1.length() == 0) {
                        viewAllReviews.setVisibility(View.GONE);
                    }
                    JSONObject productTranslationObject = null;
                    if (jsonObject1.getJSONObject("sku").getJSONObject("product").get("product_translation_full").equals(null)) {
                        productTranslationObject = jsonObject1.getJSONObject("sku").getJSONObject("product").getJSONObject("default_product_translation_full");
                    } else {
                        productTranslationObject = jsonObject1.getJSONObject("sku").getJSONObject("product").getJSONObject("product_translation_full");
                    }
                    JSONObject brandTranslationObject = null;
                    if (jsonObject1.getJSONObject("sku").getJSONObject("product").getJSONObject("brand").get("brand_translation").equals(null)) {
                        brandTranslationObject = jsonObject1.getJSONObject("sku").getJSONObject("product").getJSONObject("brand").getJSONObject("default_brand_translation");
                    } else {
                        brandTranslationObject = jsonObject1.getJSONObject("sku").getJSONObject("product").getJSONObject("brand").getJSONObject("brand_translation");
                    }
                    TextView brandName = (TextView) findViewById(R.id.subcatename);
                    if (brandTranslationObject.get("brand_name").equals(null)) {
                        brandName.setVisibility(View.GONE);
                    } else {
                        brandName.setText(brandTranslationObject.getString("brand_name"));
                    }
                    TextView deliveryMessege = (TextView) findViewById(R.id.deliveryMessege);
                    deliveryMessege.setText(productTranslationObject.getString("delivery_message").equals("null") ? "" : productTranslationObject.getString("delivery_message"));
                    Pname = productTranslationObject.getString("product_name");
                    toolBarTitle.setText(Pname);

                    product_image = jsonObject1.getJSONObject("sku").getJSONObject("product").getString("product_image");
                    String product_description = productTranslationObject.getString("description");
                    System.out.println("jecdklxs" + product_description);


                    if (jsonObject1.getString("my_review").isEmpty()) {
                        myRating = 0;
                        myReview = "";
                    } else if (jsonObject1.getJSONObject("my_review").get("review").equals(null)) {
                        myRating = jsonObject1.getJSONObject("my_review").getInt("rating");
                        myReview = "";
                    } else {
                        myRating = jsonObject1.getJSONObject("my_review").getInt("rating");
                        myReview = jsonObject1.getJSONObject("my_review").getString("review");
                    }

                    cartCountTextView = (TextView) findViewById(R.id.actionbar_notifcation_textview);
                    notificationCountTextview = (TextView) findViewById(R.id.notification_count_textview);

                    Dashboard.cart_count = jsonObject1.getInt("cart_count");
                    notification_count = jsonObject1.getInt("notification_count");
                   // Toast.makeText(ProductDetails.this, ""+notification_count, Toast.LENGTH_SHORT).show();
                    new CartCountUtil(ProductDetails.this);

                    if (jsonObject1.getJSONObject("sku").getJSONObject("product").get("review_count").equals(null)) {

                    } else {
                        double ratingAverage = jsonObject1.getJSONObject("sku").getJSONObject("product").getJSONObject("review_count").getDouble("rating");

                        double rating = Double.parseDouble(String.valueOf(ratingAverage));
                        DecimalFormat decimal = new DecimalFormat("0.0");

                        String col = ColorManager.setRatingColor(rating);


                        GradientDrawable bgShape = (GradientDrawable) colorLayout.getBackground();
                        bgShape.setColor(Color.parseColor(col));

                        String formattedValue = decimal.format(rating);
                        ratingLayout.setVisibility(View.VISIBLE);
                        ratings.setText(String.format(Locale.ENGLISH, "%.1f", rating));
                        ratings2.setText(String.format(Locale.ENGLISH, "%.1f", rating));
                        if (!jsonObject1.getJSONObject("sku").getJSONObject("product").getJSONObject("review_count").get("rating_count").equals(null)) {
                            int ratingCount = jsonObject1.getJSONObject("sku").getJSONObject("product").getJSONObject("review_count").getInt("rating_count");

                            String rating_review = getString(R.string.reviews_and_ratings);
                            ProductDetails.this.ratingCount.setText(String.valueOf(ratingCount) + " " + rating_review);
                            reviewsCountText.setText(String.valueOf(ratingCount) + " " + rating_review);

                        } else {
                            String rating_review = getString(R.string.reviews_and_ratings);
                            ProductDetails.this.ratingCount.setText(0 + " " + rating_review);
                            reviewsCountText.setText(0 + " " + rating_review);
                        }
                    }

                    ratingbar.setRating(myRating);
                    setStarColor(myRating, ratingbar);

//                    LayerDrawable stars = (LayerDrawable) ratingbar.getProgressDrawable();
//                    stars.getDrawable(2).setColorFilter(Color.parseColor("#" + colorSettings.getString("text_color", "#FFFFFF")), PorterDuff.Mode.SRC_ATOP);

                    DecimalFormat df = new DecimalFormat("#.##");

                    if (myPrice == 0) {
                        //findViewById(R.id.card).setVisibility(View.GONE);
                        productSellingPrice.setVisibility(View.GONE);
                        //findViewById(R.id.addmore).setVisibility(View.GONE);
                        addtoCartButton.setVisibility(View.GONE);

                    } else {
                        //findViewById(R.id.card).setVisibility(View.VISIBLE);
                        productSellingPrice.setVisibility(View.VISIBLE);
                        //findViewById(R.id.addmore).setVisibility(View.VISIBLE);
                        addtoCartButton.setVisibility(View.VISIBLE);

                    }

                    if (marketPrice == 0 || marketPrice <= myPrice) {
                        productoff.setVisibility(View.GONE);
                        productActualPrice.setVisibility(View.GONE);

                    } else {
                        productActualPrice.setVisibility(View.VISIBLE);
                        if (marketPrice % 1 == 0) {
                            int i = (int) Math.round(marketPrice);
                            productActualPrice.setText(Appearance.appTranslation.getCurrency() + i);
                        } else {
                            productActualPrice.setText(Appearance.appTranslation.getCurrency() + marketPrice);
                        }
                        productActualPrice.setPaintFlags(productActualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        productoff.setVisibility(View.VISIBLE);
                        double marketpriceCalculated = Double.parseDouble(String.valueOf(marketPrice));
                        double myPriceCalculated = new Double(myPrice);
                        double percentCalculated = (myPriceCalculated / marketpriceCalculated) * 100;

                        Double offCalculated = new Double(100 - percentCalculated);
                        float offf = offCalculated.floatValue();
                        System.out.println("zsxz" + offf);
                        off = df.format(offCalculated);
                        System.out.println("offfff" + off);
                        String offs = getString(R.string.off);
                        productoff.setText(String.valueOf(offf) + "%"+ offs);
                        if (offf != 0.00) {
                            productActualPrice.setVisibility(View.VISIBLE);
                            productoff.setVisibility(View.VISIBLE);
                            if (offf % 1 == 0) {
                                int i = (int) Math.round(offf);
                                String offd = getString(R.string.off);
                                productoff.setText(String.format(Locale.ENGLISH, "%.2f", offf) + "% " + offd);
                            } else {
                                String offd = getString(R.string.off);
                                productoff.setText(String.format(Locale.ENGLISH, "%.2f", offf) + "% " + offd);
                            }
                        } else {
                            productActualPrice.setVisibility(View.GONE);
                            productoff.setVisibility(View.GONE);
                        }
                    }
                    productName.setText(Pname);

                    if (myPrice % 1 == 0) {
                        int i = (int) Math.round(myPrice);
                        productSellingPrice.setText(Appearance.appTranslation.getCurrency() + i);
                     //   PayuActivity.amount = Double.parseDouble(String.valueOf(myPrice));
                     //   RazorPay.amount =  Double.parseDouble(String.valueOf(myPrice));

                    } else {
                        productSellingPrice.setText(Appearance.appTranslation.getCurrency() + myPrice);
                      //  PayuActivity.amount = Double.parseDouble(String.valueOf(myPrice));
                      //  RazorPay.amount =   Double.parseDouble(String.valueOf(myPrice));

                    }
                    if (!product_description.matches("") && !productTranslationObject.get("description").equals(null)) {
                      textViewq.setText(Html.fromHtml(product_description));

                    } else {
                        CardView descriptionCardView = (CardView) findViewById(R.id.highlightvisibility);
                        descriptionCardView.setVisibility(View.GONE);
                    }

                    //   holder.percentoff.setText(off+"% off");


                    setReviews(jsonArray1);
                    setData(jsonArray);
                    findViewById(R.id.whiteloader).setVisibility(View.GONE);


                } catch (JSONException e) {
                    e.printStackTrace();
                    refreshLayout.setRefreshing(false);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
                            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
                            progressBar.setVisibility(View.GONE);
                            findViewById(R.id.retryImage).setVisibility(View.VISIBLE);
                            Button imageView = (Button) findViewById(R.id.retrybutton);
                            imageView.setVisibility(View.VISIBLE);
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    getData();
                                }
                            });
                        }
                    }, 2000);

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


                        Button imageView = (Button) findViewById(R.id.retrybutton);
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setOnClickListener(new View.OnClickListener() {
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

    private void loadChildVariant(final JSONArray childVariant) {
        Gson gson = new Gson();
        final List<ChildVariantModel> list = Arrays.asList(gson.fromJson(childVariant.toString(), ChildVariantModel[].class));
        final TagFlowLayout recyclerView1 = (TagFlowLayout) findViewById(R.id.recChildVariant);
        isLeftToRight = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_LTR;

        if (!isLeftToRight) {
            recyclerView1.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        } else {
            recyclerView1.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        }
        final LayoutInflater mInflater = LayoutInflater.from(this);
        recyclerView1.setAdapter(new TagAdapter<ChildVariantModel>(list) {

            @Override
            public View getView(FlowLayout parent, final int position, ChildVariantModel childVariantModel) {
                tv = (TextView) mInflater.inflate(R.layout.tv,
                        recyclerView1, false);
                System.out.println("ewwwwwww" + childVariantId);
                final Gson gson = new Gson();
                final ChildVariantModel productModel = list.get(position);
                String jsonString = gson.toJson(productModel.getChild_variant_translation());
                System.out.println("zdnfjc" + jsonString);

                if (jsonString.equals("null")) {

                    jsonString = gson.toJson(productModel.getDefault_child_variant_translation());
                    System.out.println("zdnfjc" + jsonString);

                }
                ChildVariantModel.DefaultChildVariantTranslationBean tanslationBean = gson.fromJson(jsonString,
                        ChildVariantModel.DefaultChildVariantTranslationBean.class);

                if (childVariantModel.getChild_variant_id() == childVariantId) {
                    GradientDrawable drawable = (GradientDrawable) ProductDetails.this.getResources().getDrawable(selectedborder);
                    drawable.setStroke(3, Color.parseColor("#" + Appearance.appSettings.getText_color()));
                   /* Drawable backgroundDrawable = DrawableCompat.wrap(ProductDetails.this.getResources().getDrawable(R.drawable.selectedborder)).mutate();
                    DrawableCompat.setTint(backgroundDrawable, Color.parseColor("#00FF00"));*/
                    tv.setBackgroundDrawable(ProductDetails.this.getResources().getDrawable(R.drawable.selectedborder));
                } else {
                    tv.setBackgroundDrawable(ProductDetails.this.getResources().getDrawable(R.drawable.custom_border));
                }
                tv.setText(tanslationBean.getChild_variant_name());
                recyclerView1.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {

                        if (childVariantId != list.get(position).getChild_variant_id()) {
                            sku_id = 0;
                            childVariantId = list.get(position).getChild_variant_id();
                            recyclerView1.getAdapter().notifyDataChanged();
                            getData();
                            whiteLoader = 0;
                        }
                        return false;
                    }
                });
                return tv;

            }
/* @Override
            public void onSelected(int position, View view) {
                super.onSelected(position, view);
                size_id = list.get(position).getChild_variant_id();
                TextView textView = (TextView) findViewById(R.id.sizetitle);
                textView.setText(list.get(position).getChild_variant_name());
                if (size_position != position) {
                    onResume();
                }


            }

            @Override
            public void unSelected(int position, View view) {
                super.unSelected(position, view);
                recyclerView1.getAdapter().setSelectedList(position);
            }*/

        });
        System.out.println("sdcscs");
        recyclerView1.getAdapter().setSelectedList(size_position);

        /*for (int i = 0; i < jsonArray2.length(); i++) {
            try {
                if (jsonArray2.getJSONObject(i).getInt("size_id") == size_id) ;
                {
                    recyclerView1.getAdapter().setSelectedList(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/

    }


    private void setReviews(JSONArray jsonArray1) {
        Gson gson = new Gson();
        List<ReviewsModel> list = Arrays.asList(gson.fromJson(jsonArray1.toString(), ReviewsModel[].class));
        System.out.println("dada" + list);

        RecyclerView reviewsRecycler = (RecyclerView) findViewById(R.id.reviewrecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);


        reviewsRecycler.setLayoutManager(layoutManager);
        ReviewAdpater reviewAdpater = new ReviewAdpater(this, list);
        reviewsRecycler.setNestedScrollingEnabled(false);

        reviewsRecycler.setAdapter(reviewAdpater);
    }

    private void setData(JSONArray response) {

        Gson gson = new Gson();
        List<OptionalImagesModel> list = new LinkedList<OptionalImagesModel>(Arrays.asList(gson.fromJson(response.toString(), OptionalImagesModel[].class)));
        // list = Arrays.asList(gson.fromJson(response.toString(), OptionalImagesModel[].class));

        strings = new ArrayList<>();
        if (response.length() == 0) {
            strings.add(product_image);
            System.out.println("productimage" + product_image);


        }
        if (product_image.matches("null")) {
            dontGoImagehowSlider = true;

        }
        for (int i = 0; i < list.size(); i++) {
            strings.add(list.get(i).getProduct_image());
        }
        viewPagerSlider = (ViewPagerCustomDuration) findViewById(R.id.bigimage);


        try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPagerSlider.getContext(), sInterpolator, true);
            // scroller.setFixedDuration(5000);
            mScroller.set(viewPagerSlider, scroller);
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
        viewPagerSlider.setClipToPadding(false);
        viewPagerSlider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                DisplayImageAdapter.indeximage = position;
                displayImageAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // set padding manually, the more you set the padding the more you see of prev & next page
        // sets a margin b/w individual pages to ensure that there is a gap b/w them


        viewPagerSlider.setAdapter(new Sliding_ImageAdapterDasboard(ProductDetails.this, false, true, strings, new VarintListner() {
            @Override
            public void onModelClick(View view, int position, int size_id) {
                if (!dontGoImagehowSlider) {
                    Intent intent10 = new Intent(ProductDetails.this, ImageShowActivity.class);
                    intent10.putStringArrayListExtra("position", strings);
                    intent10.putExtra("type", "");
                    intent10.putExtra("pager_position", position);
                    intent10.putExtra("name", nameofProduct);

                    startActivity(intent10);
                }

            }
        }));


        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setPageColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        indicator.setFillColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));

        indicator.setViewPager(viewPagerSlider);
        viewPagerSlider.setScrollDurationFactor(2);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(7);

        NUM_PAGES3 = strings.size();


        recyclerView = (RecyclerView) findViewById(R.id.recdisplayimage);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ggg);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);


        recyclerView.setLayoutManager(layoutManager);
        displayImageAdapter = new DisplayImageAdapter(this, strings, new VarintListner() {
            @Override
            public void onModelClick(View view, int position, int size_id) {
                viewPagerSlider.setCurrentItem(position);
            }
        });
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setAdapter(displayImageAdapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plus:
                increaseInteger(displayInteger);
                break;
            case R.id.bigimage:
                if (strings.size() > 0) {
                    Intent intent10 = new Intent(this, ImageShowActivity.class);
                    intent10.putStringArrayListExtra("position", strings);
                    intent10.putExtra("name", nameofProduct);
                    startActivity(intent10);
                }
                break;
            case R.id.minus:
                decreaseInteger(displayInteger);
                break;

            case R.id.addtocart:
                if (isincart) {
                    Intent intent = new Intent(ProductDetails.this, PaymentDetailsActivity.class);
                    startActivity(intent);

                } else {
                    findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
                    JSONObject jsonObject = new JSONObject();

                    SharedPreferences prefs = getSharedPreferences("userdetails", MODE_PRIVATE);

                    String user_address = prefs.getString("user_address", "");
                    String user_pincode = prefs.getString("user_pincode", "");
                    String user_state = prefs.getString("user_state", "");

                    SharedPreferences prefs1 = getSharedPreferences("UserId", MODE_PRIVATE);
                    String languageid = prefs1.getString("language", String.valueOf(1));

                    userid = prefs1.getString("user_id", "");
                    email = prefs1.getString("email", "");
                    pwd = prefs1.getString("pwd", "");
                    System.out.println("xdc" + sku_id + userid);
                    try {
                        jsonObject.put("business_id",IConstants.BUSINESS_ID);
                        jsonObject.put("sku_id", sku_id);
                        jsonObject.put("quantity", displayInteger.getText().toString());
                        jsonObject.put("id", userid);
                        jsonObject.put("password", pwd);
                        jsonObject.put("language_id", languageid);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    System.out.println("kcosdcsd" + jsonObject);
                    String url = URL_GETPRODUCTADDTOCARTT;
                    try {
                        uri = new URI(url.replace(" ", "%20"));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                    System.out.println("wedxs" + url.trim());
                    VolleyTask volleyTask = new VolleyTask(ProductDetails.this, url, jsonObject, Request.Method.POST);
                    volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                        @Override
                        public void fnPostTaskCompleted(JSONArray response) {
                            System.out.println("sed" + response);
                        }

                        @Override
                        public void fnPostTaskCompletedJsonObject(JSONObject response) {
                            System.out.println("hhhs" + response.toString());
                            findViewById(R.id.whiteloader).setVisibility(View.GONE);
                            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                            if (!new ResponseHandler().validateResponse(ProductDetails.this, response)) {
                                return;
                            }
                            try {
                                String jsonObject1 = response.getJSONObject("data").getString("msg");
                                JSONObject jsonObject2 = response.getJSONObject("data").getJSONObject("data").getJSONObject("cart");
                                cart_count = response.getJSONObject("data").getJSONObject("data").getInt("cart_count");

                                new CustomToast(ProductDetails.this, jsonObject1);
                                displayInteger.setText("" + jsonObject2.getString("quantity"));
                                minimumInteger = jsonObject2.getInt("quantity");
                                String buynow = getString(R.string.buy_now);
                                addtoCartButton.setText(buynow);
                                isincart = true;
                                findViewById(R.id.card).setVisibility(View.VISIBLE);
                                findViewById(R.id.addmore).setVisibility(View.VISIBLE);

                                TextView cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);

                                if (Dashboard.cart_count !=0){
                                    cartCount.setVisibility(View.VISIBLE);
                                    cartCount.setText(String.valueOf(Dashboard.cart_count));
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void fnErrorOccurred(String error) {
                            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                            new CustomToast(ProductDetails.this, "You are need to login");
                            Intent intent10 = new Intent(ProductDetails.this, LoginActivity.class);
                            startActivity(intent10);

                        }
                    });
                }
                break;

            case R.id.addreview:
//
                dialog = new Dialog(ProductDetails.this, R.style.AlertDialogStyle_Default);
                dialog.setContentView(R.layout.addreviews);
                description = (AppCompatEditText) dialog.findViewById(R.id.reviewDescription);
                if (description == null) {
                    description.setText("");
                }
                description.setText(myReview);
                description.setSelection(description.getText().length());
                choosebutton = (Button) dialog.findViewById(R.id.choosebutton);
                choosebutton.setVisibility(View.VISIBLE);
                choosebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                    }
                });
                new EditTextColorChanger(this, description);

                /*ColorStateList colorStateList = ColorStateList.valueOf(Color.parseColor("#" + colorSettings.getString("text_color", "#FFFFFF")));
                description.setSupportBackgroundTintList(colorStateList);*/
                Button dialogOKbutton = (Button) dialog.findViewById(R.id.addreview);
                Button dialogCancelButton = (Button) dialog.findViewById(R.id.reviewcancel);
                ratingBar = (ScaleRatingBar) dialog.findViewById(R.id.ratingBar);
                ratingBar.setEmptyDrawableRes(R.drawable.startempty);
                ratingBar.setFilledDrawable(getResources().getDrawable(R.drawable.starfilled));
                ratingBar.setClickable(false);
                TextView ratingsandReviewsText = (TextView) dialog.findViewById(R.id.rateAndWriteAReviewText);
                TextView yourRatingText = (TextView) dialog.findViewById(R.id.yourRatingText);
                try {
                 //   ratingsandReviewsText.setText(Label.productLabel.getRatings_and_reviews());
                  //  yourRatingText.setText(Label.productLabel.getYour_rating());
                   // dialogOKbutton.setText(Label.productLabel.getAdd_review());
                   // dialogCancelButton.setText(Label.globalLabel.getCancel());
                   // description.setHint(Label.productLabel.getEnter_your_review());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

//                LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
//                stars.getDrawable(2).setColorFilter(Color.parseColor("#" + colorSettings.getString("text_color", "#FFFFFF")), PorterDuff.Mode.SRC_ATOP);

                ratingBar.setRating(ratingbar.getRating());
                Log.i("MY RATING", "" + ratingBar.getRating());

                setStarColor(ratingBar.getRating(), ratingBar);
                ratingStars = (int) ratingBar
                        .getRating();
                dialogOKbutton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                description.setHighlightColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));

                ratingBar.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            float touchPositionX = event.getX();
                            float width = ratingBar.getWidth();
                            float starsf = (touchPositionX / width) * 5.0f;
                            ratingStars = (int) starsf + 1;
                            Log.i("MYRATING", "" + ratingBar.getRating());
                            ratingBar.setRating(ratingStars);

                            v.setPressed(false);
                        }
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            v.setPressed(true);
                        }

                        if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                            v.setPressed(false);
                        }

                        setStarColor(ratingBar.getRating(), ratingBar);
                        return true;
                    }
                });

                dialogCancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialogOKbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (ratingStars <= 0) {
                            new CustomToast(ProductDetails.this, "please provide rating");

                            Animation shake = AnimationUtils.loadAnimation(ProductDetails.this, R.anim.shake);

                            shake.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            ratingBar.startAnimation(shake);

                        } else {
                            dialog.dismiss();
                            findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
                            JSONObject jsonObject = new JSONObject();
                            SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

                            String userid = prefs.getString("user_id", "");
                            String emaila = prefs.getString("email", "");
                            String pwda = prefs.getString("pwd", "");
                            String languageid = prefs.getString("language", String.valueOf(1));

                            Log.i("AAA_review", description.getText().toString());
                            Log.i("AAA_rating", ratingStars + "");
                            Log.i("AAA_password", pwda + "");
                            Log.i("AAA_id", userid + "");
                            Log.i("AAA_product_id", productid + "");
                            Log.i("AAA_language_id", languageid);
                            Log.i("AAA_Image", "123" + base64String.toString().replace("[", "").replace("]", ""));

                            System.out.println("AAA_AAA" + base64String.toString().replace("[", "").replace("]", ""));

                            try {
                                jsonObject.put("business_id",IConstants.BUSINESS_ID);
                                jsonObject.put("review", description.getText().toString());
                                jsonObject.put("rating", ratingStars);
                                jsonObject.put("password", pwda);
                                jsonObject.put("id", userid);
                                jsonObject.put("product_id", productid);
                                jsonObject.put("language_id", languageid);
                                jsonObject.put("images", base64String.toString().replace("[", "").replace("]", ""));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            System.out.println("dcxz" + jsonObject);
                            VolleyTask volleyTask = new VolleyTask(ProductDetails.this, IConstants.URL_GETPADDREVIEW, jsonObject, Request.Method.POST);
                            volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                                @Override
                                public void fnPostTaskCompleted(JSONArray response) {

                                }

                                @Override
                                public void fnPostTaskCompletedJsonObject(JSONObject response) {
                                    dialog.dismiss();
                                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                                    Log.i("MY RESPONSE", response.toString() + "");

                                    if (!new ResponseHandler().validateResponse(ProductDetails.this, response)) {

                                        return;
                                    }
                                    try {
                                        viewAllReviews.setVisibility(View.VISIBLE);
                                        new CustomToast(ProductDetails.this, response.getJSONObject("data").getString("msg"));

                                        setReviews(response.getJSONObject("data").getJSONObject("data").getJSONArray("productReviews"));
                                        ratingCount.setText(String.valueOf(response.getJSONObject("data").getJSONObject("data").getJSONArray("productReviews").length()) + " reviews and ratings");

                                        reviewsCountText.setText(String.valueOf(response.getJSONObject("data").getJSONObject("data").getJSONArray("productReviews").length()) + " Reviews");

                                        int rating = response.getJSONObject("data").getJSONObject("data").getJSONObject("myReview").getInt("rating");
                                        ratingbar.setRating(ratingBar.getRating());
                                        setStarColor(ratingBar.getRating(), ratingbar);

                                        double rating1 = Double.parseDouble(String.valueOf(response.getJSONObject("data").getJSONObject("data").getDouble("rating")));
                                        DecimalFormat decimal = new DecimalFormat("0.0");

                                        String formattedValue = String.format(Locale.ENGLISH, "%.1f", rating1);
                                        rating1 = Double.parseDouble(formattedValue);

                                        String col = ColorManager.setRatingColor(rating1);

                                        Log.i("In Dialog", "fnPostTaskCompletedJsonObject: " + col);

                                        GradientDrawable bgShape = (GradientDrawable) colorLayout.getBackground();
                                        bgShape.setColor(Color.parseColor(col));

                                        Log.i("Act", "fnPostTaskCompletedJsonObject: " + rating1);

                                        ratings.setText(String.valueOf(rating1));
                                        ratings2.setText(formattedValue);
                                        myReview = description.getText().toString();

                                        base64String.clear();
                                        findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

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
                break;
            case R.id.viewAllreviews:
                //scrollview.smoothScrollTo(0,0);
                Intent intent = new Intent(this, ViewAllReviews.class);
                intent.putExtra("takeproductid", productid);
                startActivity(intent);
                break;
            case R.id.shareproduct:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Subject test");
                i.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + packagename);
                startActivity(Intent.createChooser(i, "Share via"));
                break;
            case R.id.viewsimilar:
                ProductActivity.similar = nameofProduct;
                Intent intent1 = new Intent(this, ProductActivity.class);
                SearchActivity.formatedStringSearch = "";
                SearchActivity.searchMax = "";
                ProductActivity.sortedid = 0;
                ProductActivity.name = nameofProduct;
                ProductActivity.sucat_id = -1;
                startActivity(intent1);
                break;
            case R.id.viewCart:

                Intent intent2 = new Intent(this, CartActivity.class);
                startActivity(intent2);
                break;

            case R.id.addmore: {
                dialog = new Dialog(this, R.style.AlertDialogStyle_Default);
                dialog.setContentView(R.layout.addquantity);
                addQuantityField = (AppCompatEditText) dialog.findViewById(R.id.quantityadd);
                Button dialogButtonCancel = (Button) dialog.findViewById(R.id.cpcancel);
                new EditTextColorChanger(this, addQuantityField);

                TextView enterQuantityText = (TextView) dialog.findViewById(R.id.enterQuantityText);

                final Button dialogButtonOk = (Button) dialog.findViewById(R.id.cpupdate);
                try {
                  //  enterQuantityText.setText(Label.productLabel.getEnter_quantity());
                  //  dialogButtonOk.setText(Label.globalLabel.getSave());
                  //  dialogButtonCancel.setText(Label.globalLabel.getCancel());
                   // addQuantityField.setHint(Label.productLabel.getQuantity());

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                dialogButtonOk.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (addQuantityField.getText().toString().isEmpty()) {
                            try {
                                String qty = getString(R.string.please_enter_quantity);
                                new CustomToast(ProductDetails.this, qty);
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                        } else if (Integer.parseInt(addQuantityField.getText().toString()) == 0) {
                            try {
                                String qty = getString(R.string.please_enter_quantity);
                                new CustomToast(ProductDetails.this,qty);
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                        } else {
                            minimumInteger = Integer.parseInt(addQuantityField.getText().toString());
                            dialog.dismiss();
                            display(minimumInteger);

                        }
                    }
                });
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                dialog.show();
            }


            break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        productid = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(ProductDetails.this);

//        guestPref = getSharedPreferences("GUESTSETTING",MODE_PRIVATE);
//        isGuestActive = guestPref.getBoolean("is_guest_check",false);
//        Log.i("IsGuest", "onResume: "+Boolean.toString(isGuestActive));

        if(CartActivity.isRemoveItemPressed){
            getData();
            CartActivity.isRemoveItemPressed = false;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            System.out.println("sdxxz" + requestCode + "dxswaz" + requestCode + "cdxs0" + data);
            try {
                // When an Image is picked
                if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                        && null != data) {
                    // Get the Image from data
                    base64String.clear();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    imagesEncodedList = new ArrayList<String>();
                    if (data.getData() != null) {
                        final List<BitmapModel> bitmapModels = new ArrayList<>();
                        BitmapModel bitmapModel = new BitmapModel();

                        Uri mImageUri = data.getData();
                        FixBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mImageUri);

                        bitmapModel.setBitmap(FixBitmap);
                        bitmapModels.add(bitmapModel);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        FixBitmap.compress(Bitmap.CompressFormat.JPEG, 60, bos);
                        byte[] data1 = bos.toByteArray();
                        encodedImage = Base64.encodeToString(data1, Base64.DEFAULT);
                        base64String.add(encodedImage);
                        RecyclerView imagesRecycler = (RecyclerView) dialog.findViewById(R.id.imagesRecycler);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                        imagesRecycler.setLayoutManager(layoutManager);
                        bitmapAdapter = new BitmapAdapter(ProductDetails.this, bitmapModels, true, new VarintListner() {
                            @Override
                            public void onModelClick(View view, int position, int size_id) {
                                bitmapModels.remove(position);
                                bitmapAdapter.notifyItemRemoved(position);
                                bitmapAdapter.notifyDataSetChanged();

                                base64String.remove(position);

                            }
                        });
                        imagesRecycler.setAdapter(bitmapAdapter);

                        // Get the cursor
                        Cursor cursor = getContentResolver().query(mImageUri,
                                filePathColumn, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imageEncoded = cursor.getString(columnIndex);
                        cursor.close();

                    } else {

                        if (data.getClipData() != null) {

                            bitmapModels = new ArrayList<>();
                            bitmapModels.clear();
                            ClipData mClipData = data.getClipData();
                            ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                            for (int i = 0; i < mClipData.getItemCount(); i++) {
                                BitmapModel bitmapModel = new BitmapModel();
                                ClipData.Item item = mClipData.getItemAt(i);
                                Uri uri = item.getUri();
                                mArrayUri.add(uri);
                                FixBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                                bitmapModel.setBitmap(FixBitmap);
                                bitmapModels.add(bitmapModel);

                                //System.out.println("encodedimage"+encodedImage);
                                // Get the cursor
                                Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                                // Move to first row
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                imageEncoded = cursor.getString(columnIndex);
                                imagesEncodedList.add(imageEncoded);
                                cursor.close();

                            }
                            for (int i = 0; i < bitmapModels.size(); i++) {
                                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                                bitmapModels.get(i).getBitmap().compress(Bitmap.CompressFormat.JPEG, 60, bos);
                                byte[] data1 = bos.toByteArray();
                                encodedImage = Base64.encodeToString(data1, Base64.DEFAULT);
                                base64String.add(encodedImage);
                            }
                            System.out.println("dsxz" + base64String.size());
                            RecyclerView imagesRecycler = (RecyclerView) dialog.findViewById(R.id.imagesRecycler);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                            imagesRecycler.setLayoutManager(layoutManager);
                            bitmapAdapter = new BitmapAdapter(ProductDetails.this, bitmapModels, true, new VarintListner() {
                                @Override
                                public void onModelClick(View view, int position, int size_id) {

                                    bitmapModels.remove(position);
                                    bitmapAdapter.notifyItemRemoved(position);
                                    bitmapAdapter.notifyDataSetChanged();
                                    base64String.remove(position);
                                    System.out.println("listSize" + bitmapModels.size());
                                    System.out.println("base64" + base64String.toString());
                                }
                            });
                            imagesRecycler.setAdapter(bitmapAdapter);
                            Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                        }
                    }
                } else {
               /* Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();*/
                }
            } catch (Exception e) {
         /*   Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();*/
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String setStarColor(float rating, ScaleRatingBar ratingbar) {

        String s = "#00bc58";
        ratingbar.setFilledDrawableRes(R.drawable.start_filled_green);

        try {
            if (rating == 1) {
                //RED
                ratingbar.setFilledDrawableRes(R.drawable.star_filled_red);
                s = "#ff4c4c";
            } else if (rating <= 3.5 && rating > 1) {
                //YELLOW
                ratingbar.setFilledDrawableRes(R.drawable.star_filled_yellow);
                s = "#ffaf4f";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}