package com.example.grocery.Activities;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.Activities.SearchActivity.formatedStringSearch;
import static com.example.grocery.Activities.SplashScreen.packagename;
import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;
import static com.example.grocery.interfaces.IConstants.URL_Home;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.grocery.Adapter.BusinessAdapter;
import com.example.grocery.Adapter.LanguageAdapter;
import com.example.grocery.Adapter.NavigationAdapter;
import com.example.grocery.Adapter.OfferBlocksAdapter;
import com.example.grocery.Adapter.ProductAdapter;
import com.example.grocery.Adapter.Shopbycateadapter;
import com.example.grocery.Adapter.Sliding_ImageAdapterDasboard;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Model.AllCategoriesModel;
import com.example.grocery.Model.BusinessModel;
import com.example.grocery.Model.FeaturedBrandsModel;
import com.example.grocery.Model.LanguagesModel;
import com.example.grocery.Model.OfferBlockModel;
import com.example.grocery.Model.ProductModel;
import com.example.grocery.Model.SliderModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.interfaces.VarintListner;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.SliderPackage.CirclePageIndicator;
import com.example.grocery.utils.Config;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.FixedSpeedScroller;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.NotificationUtils;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ViewPagerCustomDuration;
import com.example.grocery.utils.VolleyTask;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

public class Dashboard extends AppCompatActivity
        implements ViewPagerEx.OnPageChangeListener, View.OnClickListener {
    List<AllCategoriesModel> list1;
    private RecyclerView lista;
    RelativeLayout bigtoolbar;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    public DrawerLayout drawer;
    boolean shouldExecuteOnResume;
    public static boolean islatestProduct;
    private ArrayList<String> sliderString;
    public ViewPagerCustomDuration viewPagerSlider;
    public int currentPageSlider = 0;
    private int NUM_PAGES3;
    private static ViewPager offerZoneViewPager;
    private static int currentPageOfferZone = 0;
    private static int NUM_PAGES_OFFERZONE = 0;
    public static int cart_count;
    public  static int notification_count;
    private TextView cartCount;
    private TextView notificationcount;
    private RelativeLayout toolbarSearch, toolbarNotification;
    RelativeLayout toolbarCart;
    private boolean doubleBackToExitPressedOnce = false;
    private android.view.animation.Interpolator sInterpolator;
    private int listsize;
    private CardView shopbycategorycard;
    private int NUM_PAGES1;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private ProductAdapter productAdapter;
    private BusinessAdapter businessAdapter;
    private ViewPagerCustomDuration mViewPager;
    private CirclePageIndicator mCircleIndicator;
    public RecyclerView languageRecycler;
    public ImageView addButton;
    private TextView drawerTitle;
    private TextView contentTitle;
    private TextView welcomeText;
    private TextView welcomeText2;
    private CardView drawerLayout;
    private SharedPreferences sharedUserId;
    private TextView appName;
    private String name;

    private EditText editTextSearch;
    private RecyclerView offerBlocks;
    private SwipeRefreshLayout refreshLayout;
    private boolean hideloading;
    private List<ProductModel> list;
    private List<OfferBlockModel> lists;
    private ProductAdapter productAdaptermax;
    private OfferBlocksAdapter offerBlocksAdapter;
    private boolean stoploading;
    private int pagenumber = 1;
    private int totalcount;
    private NestedScrollView mScrollView;
    private int pageCount;
    private int totalCount;
    private String facebook;
    private String googleconnect;
    private String twitter;
    private String instagram;
    private String linkedin;
    private String youtube;
    private int count_product;
    private TextView maxViewedProductsText;
    TextView viewAllCategoriesText;
    TextView latestProductText;
    TextView recentfeaturedBrandText;
    TextView recentViewedProductText;
    private SharedPreferences guestPref;
    private boolean isGuestActive;
    private TextView navlogoutText;
    private TextView navloginText;
    private JSONObject labelsObject;
    public static int passedOnCreate;
    public static String token;
    private ImageView acccntimmage;
    LinearLayout more;
    LinearLayout notification;
    LinearLayout cart;
    LinearLayout contactus;
    LinearLayout order;
    LinearLayout account;
    LinearLayout support;
    LinearLayout policy;
    LinearLayout redeem;
    LinearLayout sharewithfrnds;
    LinearLayout vendors;
    LinearLayout share;
    LinearLayout logout;
    LinearLayout login;
    LinearLayout navaboutus;
    LinearLayout navwallet;
    RelativeLayout navMyLanguage;
    TextView navnotificationText;
    TextView navwishlistText;
    TextView navmycartText;
    TextView navmyorderText;
    TextView navmywalletText;
    TextView navMyaccountText;
    TextView navMylanguageText;
    TextView navshareText;
    TextView navcontactusText;
    TextView navonlinesupportText;
    TextView navaboutusText;
    TextView navpolicyText;
    TextView navredeemText;
    TextView navsharewithfrndsText;
    TextView navvendorText;
    Boolean loadSlider = true;

    public static int is_language = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_dashboard);

     //   displayFirebaseRegId();

        guestPref = getSharedPreferences("GUESTSETTING", MODE_PRIVATE);
        isGuestActive = guestPref.getBoolean("is_guest_check", false);
        Log.i("OnResume", "onResume: " + Boolean.toString(isGuestActive));
        toggleLoginLogout(isGuestActive);

        hideloading = false;
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(Dashboard.this);
        sharedUserId = getSharedPreferences("UserId", MODE_PRIVATE);
        SharedPreferences preferences = getSharedPreferences("UserId", MODE_PRIVATE);
        mViewPager = (ViewPagerCustomDuration) findViewById(R.id.pager);
        mCircleIndicator = (CirclePageIndicator) findViewById(R.id.indicator);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        bigtoolbar = (RelativeLayout) findViewById(R.id.bigtoolbar);
        bigtoolbar.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        cartCount = (TextView) toolbar.findViewById(R.id.actionbar_notifcation_textview);

        toolbarSearch = (RelativeLayout) toolbar.findViewById(R.id.toolsearch);
        toolbarNotification = (RelativeLayout) toolbar.findViewById(R.id.toolnoti);

        toolbarCart = (RelativeLayout) toolbar.findViewById(R.id.toolcart);
        setSupportActionBar(toolbar);
        shouldExecuteOnResume = false;

        initui();
        getData();

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor((Color.parseColor("#" + Appearance.appSettings.getApp_text_color())));
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                 //   displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received
                    String message = intent.getStringExtra("message");
                }
            }
        };

        mScrollView = (NestedScrollView) findViewById(R.id.scrollview);

        /*
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                                                  @Override
                                                  public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                                                      if (v.getChildAt(v.getChildCount() - 1) != null) {
                                                          if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) && scrollY > oldScrollY && pagenumber < pageCount) {

                                                              ViewGroup.MarginLayoutParams marginLayoutParams =
                                                                      (ViewGroup.MarginLayoutParams) findViewById(R.id.swipeRefreshLayout1).getLayoutParams();
                                                              int dpValue = 60; // margin in dips
                                                              float d = getResources().getDisplayMetrics().density;
                                                              int margin = (int) (dpValue * d);
                                                              marginLayoutParams.setMargins(0, 0, 0, margin);
                                                              findViewById(R.id.swipeRefreshLayout1).setLayoutParams(marginLayoutParams);
                                                              findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                                                              SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                                                              String userid = prefs.getString("user_id", "");
                                                              String email = prefs.getString("email", "");
                                                              String pwd = prefs.getString("pwd", "");
                                                              String languageid = prefs.getString("language", String.valueOf(1));
                                                              JSONObject jsonObject = new JSONObject();
//                                                              page_count = page_count + 1;
                                                              pagenumber = pagenumber + 1;

                                                              try {
                                                                  jsonObject.put("id", userid);
                                                                  jsonObject.put("password", pwd);
                                                                  jsonObject.put("page", pagenumber);

                                                                  jsonObject.put("language_id", languageid);

                                                              } catch (JSONException e) {
                                                                  e.printStackTrace();
                                                              }
                                                              System.out.println("deszx" + jsonObject);
                                                              VolleyTask volleyTask = new VolleyTask(Dashboard.this, IConstants.URl_GetProductMax, jsonObject, Request.Method.POST);
                                                              volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                                                                  @Override
                                                                  public void fnPostTaskCompleted(JSONArray response) {

                                                                  }

                                                                  @Override
                                                                  public void fnPostTaskCompletedJsonObject(JSONObject response) {


                                                                      if (!new ResponseHandler().validateResponse(Dashboard.this, response)) {
                                                                          return;
                                                                      }
                                                                      try {

                                                                          JSONArray jsonArray1 = response.getJSONObject("data").getJSONObject("data").getJSONArray("products");
                                                                          totalcount = response.getJSONObject("data").getJSONObject("data").getInt("total_count");

                                                                          Gson gson = new Gson();
                                                                          List<ProductModel> list1 = Arrays.asList(gson.fromJson(jsonArray1.toString(), ProductModel[].class));
                                                                          if (jsonArray1.length() == 0) {

                                                                              stoploading = true;

                                                                          }


                                                                          list.addAll(list1);
                                                                          RecyclerView recyclerRecentProducts = (RecyclerView) findViewById(R.id.recmaxproduct);

                                                                          productAdaptermax = new ProductAdapter(Dashboard.this, list, "max");
                                                                          recyclerRecentProducts.setAdapter(productAdaptermax);
                                                                          productAdaptermax.notifyDataSetChanged();
                                                                          final Handler handler = new Handler();
                                                                          handler.postDelayed(new Runnable() {

                                                                              @Override
                                                                              public void run() {
                                                                                  findViewById(R.id.progressBar).setVisibility(View.GONE);

                                                                                  ViewGroup.MarginLayoutParams marginLayoutParams =
                                                                                          (ViewGroup.MarginLayoutParams) findViewById(R.id.swipeRefreshLayout1).getLayoutParams();
                                                                                  marginLayoutParams.setMargins(0, 0, 0, 0);
                                                                                  findViewById(R.id.swipeRefreshLayout1).setLayoutParams(marginLayoutParams);
                                                                              }
                                                                          }, 1000);


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

                                                                          }
                                                                      }, 2000);
                                                                  }
                                                              });
                                                          }
                                                      }
                                                  }
                                              }
        );
        */

       // displayFirebaseRegId();
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout1);
        refreshLayout.setColorSchemeColors((Color.parseColor("#" + Appearance.appSettings.getText_color())));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadSlider = false;
                pagenumber = 1;
                // Refresh items
                hideloading = true;
                getData();

            }
        });

        passedOnCreate = 0;
    }

    private void initui() {

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        LinearLayout socialMediaLinks = (LinearLayout) findViewById(R.id.socialMediaLinks);
        ImageView facebookImage = (ImageView) findViewById(R.id.facebook_image);
        ImageView google_image = (ImageView) findViewById(R.id.google_image);
        ImageView twitter_image = (ImageView) findViewById(R.id.twitter_image);
        ImageView instagram_image = (ImageView) findViewById(R.id.instagram_image);
        ImageView youtube_image = (ImageView) findViewById(R.id.youtube_image);
        ImageView linkedin_image = (ImageView) findViewById(R.id.linkedin_image);
        google_image.setOnClickListener(this);
        twitter_image.setOnClickListener(this);
        facebookImage.setOnClickListener(this);
        instagram_image.setOnClickListener(this);
        youtube_image.setOnClickListener(this);
        linkedin_image.setOnClickListener(this);
        facebook = Appearance.appSettings.getFacebook();
        instagram = Appearance.appSettings.getInstagram();
        youtube = Appearance.appSettings.getYoutube();
        linkedin = Appearance.appSettings.getLinkedin();
        System.out.println("edsxsdwa" + facebook);
        googleconnect = Appearance.appSettings.getGoogle();
        twitter = Appearance.appSettings.getTwitter();

        System.out.println("fdsxsc" + googleconnect);
        if (facebook == null && twitter == null && googleconnect == null) {
            socialMediaLinks.setVisibility(View.GONE);
        }
        if (facebook == null) {
            facebookImage.setVisibility(View.GONE);
        }
        if (twitter == null) {
            twitter_image.setVisibility(View.GONE);
        }
        if (googleconnect == null) {
            google_image.setVisibility(View.GONE);
        }
        if (instagram == null) {
            instagram_image.setVisibility(View.GONE);
        }
        if (youtube == null) {
            youtube_image.setVisibility(View.GONE);
        }
        if (linkedin == null) {
            linkedin_image.setVisibility(View.GONE);
        }
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    SearchActivity.searchKeyword = editTextSearch.getText().toString();
                    SearchActivity.searchMax = "";
                    SearchActivity.searchMin = "";
                    SearchActivity.sortedida = 0;
                    isViewWithCatalog = false;
                    SearchActivity.page_count = 1;
                    ApplyFilter.brandArrays = null;
                    ApplyFilter.maximumSeekBar = "5000";
                    ProductDetails.productid = 0;
                    ApplyFilter.minimumSeekBar = "0";
                    formatedStringSearch = "";
                    SearchActivity.imagea = "true";
                    Intent intent = new Intent(Dashboard.this, SearchActivity.class);
                    startActivity(intent);
                    View view = Dashboard.this.getCurrentFocus();


                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    return true;
                }
                return false;
            }
        });

        appName = (TextView) findViewById(R.id.appname);
        drawerTitle = (TextView) drawer.findViewById(R.id.title);
        contentTitle = (TextView) drawer.findViewById(R.id.appname);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        RelativeLayout relativeLayout = (RelativeLayout) drawer.findViewById(R.id.paddingnavigation);
        drawerLayout = (CardView) drawer.findViewById(R.id.drwaerlayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            relativeLayout.setPadding(0, 45, 0, 0);
            // only for gingerbread and newer versions
        }

        lista = (RecyclerView) findViewById(R.id.nav_list);
        more = (LinearLayout) findViewById(R.id.more);
        notification = (LinearLayout) findViewById(R.id.navnotification);
        cart = (LinearLayout) findViewById(R.id.navmycart);
        contactus = (LinearLayout) findViewById(R.id.navcontactus);
        order = (LinearLayout) findViewById(R.id.navmyorder);
        account = (LinearLayout) findViewById(R.id.navMyaccount);
        support = (LinearLayout) findViewById(R.id.navonlinesupport);
        policy = (LinearLayout) findViewById(R.id.navpolicy);
        vendors = (LinearLayout) findViewById(R.id.navvendor);
        sharewithfrnds = (LinearLayout) findViewById(R.id.navsharewithfrnds);
        share = (LinearLayout) findViewById(R.id.navshare);
        logout = (LinearLayout) findViewById(R.id.navlogout);
        login = (LinearLayout) findViewById(R.id.navlogin);
        navaboutus = (LinearLayout) findViewById(R.id.navaboutus);
        navwallet = (LinearLayout) findViewById(R.id.navmywallet);
        navMyLanguage = (RelativeLayout) findViewById(R.id.navMylanguage);
        navnotificationText = (TextView) findViewById(R.id.navnotificationText);
        navwishlistText = (TextView) findViewById(R.id.navwishlistText);
        navmycartText = (TextView) findViewById(R.id.navmycartText);
        navmyorderText = (TextView) findViewById(R.id.navmyorderText);
        navmywalletText = (TextView) findViewById(R.id.navmywalletText);
        navMyaccountText = (TextView) findViewById(R.id.navMyaccountText);
        navMylanguageText = (TextView) findViewById(R.id.navMylanguageText);
        navshareText = (TextView) findViewById(R.id.navshareText);
        navcontactusText = (TextView) findViewById(R.id.navcontactusText);
        navonlinesupportText = (TextView) findViewById(R.id.navonlinesupportText);
        navaboutusText = (TextView) findViewById(R.id.navaboutusText);
        navpolicyText = (TextView) findViewById(R.id.navpolicyText);

        navsharewithfrndsText = (TextView) findViewById(R.id.navsharewithfrndsText);
        navvendorText = (TextView) findViewById(R.id.navvendorText);
        navlogoutText = (TextView) findViewById(R.id.navlogoutText);
        navloginText = (TextView) findViewById(R.id.navloginText);

        welcomeText = (TextView) drawerLayout.findViewById(R.id.welcomeText);
        welcomeText2 = (TextView) drawerLayout.findViewById(R.id.welcomeText2);
        acccntimmage = (ImageView) drawerLayout.findViewById(R.id.acccntimmage);


        if (Appearance.appSettings.getIs_multilanguage() == 0) {
            navMyLanguage.setVisibility(View.GONE);
        }
        if (Appearance.appSettings.getIs_wallet() == 0) {
            navwallet.setVisibility(View.GONE);
        }
        if(Appearance.appSettings.getIs_refferal() == 0)
        {
            sharewithfrnds.setVisibility(View.GONE);
        }
        if (Appearance.appSettings.getIs_notification() == 0) {
            notification.setVisibility(View.GONE);
        }
        if (Appearance.appSettings.getIs_vendor_calls() == 0) {
            vendors.setVisibility(View.GONE);
        }



        languageRecycler = (RecyclerView) drawer.findViewById(R.id.languageRecycler);
        addButton = (ImageView) findViewById(R.id.addButton);
        navMyLanguage.setOnClickListener(this);
        navaboutus.setOnClickListener(this);
        navwallet.setOnClickListener(this);
        LinearLayout wishlist = (LinearLayout) findViewById(R.id.navwishlist);
        wishlist.setOnClickListener(this);
        notification.setOnClickListener(this);
        cart.setOnClickListener(this);
        contactus.setOnClickListener(this);
        order.setOnClickListener(this);
        account.setOnClickListener(this);
        support.setOnClickListener(this);
        policy.setOnClickListener(this);

        sharewithfrnds.setOnClickListener(this);
        vendors.setOnClickListener(this);
        share.setOnClickListener(this);
        login.setOnClickListener(this);
        logout.setOnClickListener(this);
        navaboutus.setOnClickListener(this);

        toolbarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, CartActivity.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            }
        });
        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                SearchActivity.searchMin = "";
                SearchActivity.sortedida = 0;
                isViewWithCatalog = false;
                SearchActivity.page_count = 1;
                ApplyFilter.brandArrays = null;
                ApplyFilter.maximumSeekBar = "5000";
                ProductDetails.productid = 0;
                ApplyFilter.minimumSeekBar = "0";
                formatedStringSearch = "";
                SearchActivity.imagea = "true";
                startActivity(intent);
            }
        });
        toolbarNotification.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (isGuestActive) {
                    startActivity(new Intent(Dashboard.this, LoginActivity.class));
                } else {
                    Intent intent = new Intent(Dashboard.this, NotificationActivity.class);
                    startActivity(intent);
                }
            }
        });

        if (Appearance.appSettings.getIs_notification() == 0) {
            toolbarNotification.setVisibility(View.GONE);
        }

    }
    public void setLabels() {
        maxViewedProductsText = (TextView) findViewById(R.id.maxViewedProductsText);
        String max = getString(R.string.max_viewed);
        maxViewedProductsText.setText(max);

        viewAllCategoriesText = (TextView) findViewById(R.id.viewAllCategoriesText);
        latestProductText = (TextView) findViewById(R.id.latestProductText);
        recentfeaturedBrandText = (TextView) findViewById(R.id.recentfeaturedBrandText);
        recentViewedProductText = (TextView) findViewById(R.id.recentViewedProductText);
        String latest = getString(R.string.latest_products);
        String recent = getString(R.string.recently_viewed_products);
        String recentfeatured = getString(R.string.featured_brands);
        latestProductText.setText(latest);
        recentViewedProductText.setText(recent);
        recentfeaturedBrandText.setText(recentfeatured);

        viewAllCategoriesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Categories.class);
                intent.putExtra("category_id", 0);
                try {
                    String categories = getString(R.string.all_categories);
                    intent.putExtra("category_name",categories);

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                finishAffinity();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            new CustomToast(Dashboard.this, "Press again to exit");


            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.navaboutus:
                DrawerLayout drawer10 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer10.closeDrawer(GravityCompat.START);
                // intent = new Intent(this, AboutUsActivity.class);
                intent = new Intent(this, AboutUsActivityNew.class);
                startActivity(intent);
                break;
            case R.id.navmywallet:
                DrawerLayout drawer11 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer11.closeDrawer(GravityCompat.START);

                if (isGuestActive) {
                    startActivity(new Intent(Dashboard.this, LoginActivity.class));
                } else {
                    intent = new Intent(this, WalletActivity.class);
                    startActivity(intent);
                }
                break;

            case R.id.navnotification:
                DrawerLayout drawer2 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer2.closeDrawer(GravityCompat.START);
                drawer2.closeDrawer(GravityCompat.START);
                if (isGuestActive) {
                    startActivity(new Intent(Dashboard.this, LoginActivity.class));
                } else {
                    intent
                            = new Intent(this, NotificationActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.navmycart:
                DrawerLayout drawer3 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer3.closeDrawer(GravityCompat.START);
                drawer3.closeDrawer(GravityCompat.START);
                intent = new Intent(this, CartActivity.class);
                startActivity(intent);

                break;

            case R.id.navmyorder:
                DrawerLayout drawer4 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer4.closeDrawer(GravityCompat.START);
                drawer4.closeDrawer(GravityCompat.START);
                if (isGuestActive) {
                    startActivity(new Intent(Dashboard.this, LoginActivity.class));
                } else {
                    intent = new Intent(this, MyOrdersActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.navMyaccount:
                DrawerLayout drawer5 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer5.closeDrawer(GravityCompat.START);
                drawer5.closeDrawer(GravityCompat.START);
                if (isGuestActive) {
                    startActivity(new Intent(Dashboard.this, LoginActivity.class));
                } else {
                    intent
                            = new Intent(this, AccountActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.navonlinesupport:
                DrawerLayout drawer6 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer6.closeDrawer(GravityCompat.START);
                drawer6.closeDrawer(GravityCompat.START);
                if (isGuestActive) {
                    startActivity(new Intent(Dashboard.this, LoginActivity.class));
                } else {
                    intent = new Intent(this, OnlineSupportActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.navpolicy:
                DrawerLayout drawer7 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer7.closeDrawer(GravityCompat.START);
                drawer7.closeDrawer(GravityCompat.START);
                intent = new Intent(this, PolicyActivity.class);
                startActivity(intent);

                break;



            case R.id.navsharewithfrnds:
                DrawerLayout drawer17 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer17.closeDrawer(GravityCompat.START);
                drawer17.closeDrawer(GravityCompat.START);
                Intent intents = new Intent(Intent.ACTION_SEND);
                intents.setType("text/plain");
                String appname = Appearance.appTranslation.getApp_name();
                SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                String userid = prefs.getString("user_id", "");
                String package_name = getPackageName();
                String msg = getString(R.string.msg);
                String msg_2 = getString(R.string.msg_2);
                String msg_3 = getString(R.string.msg_3);
                String sms = (msg + " " + appname +"."+ " "+ msg_2 + " "+ userid+ "."+ " " + msg_3+" ");
                intents.putExtra(Intent.EXTRA_TEXT, sms +"https://play.google.com/store/apps/details?id=" + packagename);
                startActivityForResult(Intent.createChooser(intents, "Share via"), 5);
                break;

            case R.id.navvendor:
                DrawerLayout drawer13 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer13.closeDrawer(GravityCompat.START);
                drawer13.closeDrawer(GravityCompat.START);
                intent = new Intent(getApplicationContext(), Vendor.class);
                startActivity(intent);
                break;


            case R.id.navshare:
                DrawerLayout drawer8 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer8.closeDrawer(GravityCompat.START);
                drawer8.closeDrawer(GravityCompat.START);
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Subject test");
                i.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + packagename);
                startActivityForResult(Intent.createChooser(i, "Share via"), 5);

                break;

            case R.id.navcontactus:
                DrawerLayout drawer12 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer12.closeDrawer(GravityCompat.START);
                drawer12.closeDrawer(GravityCompat.START);
                intent = new Intent(this, Contact_Us.class);
                startActivity(intent);

                break;
            case R.id.navlogout:

                DrawerLayout drawer9 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer9.closeDrawer(GravityCompat.START);
                drawer9.closeDrawer(GravityCompat.START);

                final Dialog dialog = new Dialog(Dashboard.this, R.style.AlertDialogStyle_Default);
                dialog.setContentView(R.layout.logout_dialog);
                TextView dialog_title = dialog.findViewById(R.id.dialog_title);
                Button dialog_yes = dialog.findViewById(R.id.dialog_yes);
                Button dialog_no = dialog.findViewById(R.id.dialog_no);

                String logout = getString(R.string.do_you_want_to_logout);
                String yes = getString(R.string.yes);
                String no = getString(R.string.no);

                dialog_title.setText(logout);
                dialog_yes.setText(yes);
                dialog_no.setText(no);
                dialog_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences preferences = getSharedPreferences("cheapstuffs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        //Clearing Cheapstuff
                        editor.clear();
                        editor.apply();
                        //Clearing Guest
                        editor = guestPref.edit();
                        editor.clear();
                        editor.apply();
                        Intent intent15
                                = new Intent(Dashboard.this, LoginActivity.class);
                        startActivity(intent15);
                        intent15.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        LoginActivity.hideguest = 0;
                        finish();

                    }
                });
                dialog.show();

                break;
            case R.id.navlogin:
                DrawerLayout drawer20 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer20.closeDrawer(GravityCompat.START);
                drawer20.closeDrawer(GravityCompat.START);
                intent
                        = new Intent(this, LoginActivity.class);
                startActivity(intent);

                break;
            case R.id.facebook_image:
                drawer.closeDrawer(GravityCompat.START);
                drawer.closeDrawer(GravityCompat.START);
                try {
                    getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    System.out.println("fewdw" + facebook);
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebook)));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebook)));
                }
                break;
            case R.id.google_image:
                drawer.closeDrawer(GravityCompat.START);
                drawer.closeDrawer(GravityCompat.START);
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(googleconnect)));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(googleconnect)));
                }
                break;
            case R.id.instagram_image:
                drawer.closeDrawer(GravityCompat.START);
                drawer.closeDrawer(GravityCompat.START);
                Intent likeIng = new Intent(Intent.ACTION_VIEW, Uri.parse(instagram));
                likeIng.setPackage("com.instagram.android");
                try {
                    startActivity(likeIng);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(instagram)));
                }
                break;
            case R.id.linkedin_image:
                drawer.closeDrawer(GravityCompat.START);
                drawer.closeDrawer(GravityCompat.START);
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkedin)));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkedin)));
                }
                break;
            case R.id.youtube_image:
                drawer.closeDrawer(GravityCompat.START);
                drawer.closeDrawer(GravityCompat.START);
                Intent youtuber = new Intent(Intent.ACTION_VIEW, Uri.parse(instagram));
                youtuber.setPackage("com.google.android.youtube");
                try {
                    startActivity(youtuber);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtube)));
                }
                break;
            case R.id.twitter_image:
                drawer.closeDrawer(GravityCompat.START);
                drawer.closeDrawer(GravityCompat.START);
                Intent twit = new Intent(Intent.ACTION_VIEW, Uri.parse(instagram));
                twit.setPackage("com.twitter.android");
                try {
                    startActivity(twit);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(twitter)));
                }

                break;
            case R.id.navwishlist:
                drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                intent = new Intent(Dashboard.this, WishlistActivity.class);
                startActivity(intent);

                break;

            case R.id.navMylanguage:

                if (languageRecycler.isShown()) {
                    addButton.setImageResource(R.drawable.add);
                    final LayoutAnimationController controller =
                            AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down);

                    languageRecycler.setLayoutAnimation(controller);
                    languageRecycler.getAdapter().notifyDataSetChanged();
                    languageRecycler.scheduleLayoutAnimation();
                    languageRecycler.setVisibility(View.GONE);
                } else {
                    addButton.setImageResource(R.drawable.minusnew);
                    final LayoutAnimationController controller =
                            AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down);
                    languageRecycler.setLayoutAnimation(controller);
                    languageRecycler.getAdapter().notifyDataSetChanged();
                    languageRecycler.scheduleLayoutAnimation();
                    languageRecycler.setVisibility(View.VISIBLE);
                }

                break;

        }
    }


    private void setNavigation(JSONArray jsonArray1) {
        Gson gson = new Gson();
        List<AllCategoriesModel> listModel = new ArrayList<>();
        list1 = Arrays.asList(gson.fromJson(jsonArray1.toString(), AllCategoriesModel[].class));
        listModel.addAll(list1);
        listsize = jsonArray1.length();

        if (listModel.size() != 0) {
            AllCategoriesModel limitedModel = new AllCategoriesModel();
            //limitedModel.setCategory_image("\\uf0f5");
            try {
                String more = getString(R.string.more);

                limitedModel.setCategoryName(more);


            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            listModel.add(limitedModel);
        }


        RecyclerView navigationList = (RecyclerView) findViewById(R.id.nav_list);
        navigationList.setNestedScrollingEnabled(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        navigationList.setLayoutManager(layoutManager);
        NavigationAdapter navigationAdapter = new NavigationAdapter(this, listModel);
        navigationList.setAdapter(navigationAdapter);
    }

    private void setpopular(JSONArray jsonArray1) {
        Gson gson = new Gson();
        List<ProductModel> list = Arrays.asList(gson.fromJson(jsonArray1.toString(), ProductModel[].class));
        System.out.println("dada" + list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recpopularproduct);
        recyclerView.setNestedScrollingEnabled(false);
        islatestProduct = false;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
       /* GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);*/
        ProductAdapter productAdapter = new ProductAdapter(this, list, "latest");
        recyclerView.setAdapter(productAdapter);
    }

    private void init(final List<SliderModel> list) {

        viewPagerSlider = (ViewPagerCustomDuration) findViewById(R.id.pager);

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
        // set padding manually, the more you set the padding the more you see of prev & next page
        // sets a margin b/w individual pages to ensure that there is a gap b/w them
        viewPagerSlider.setAdapter(new Sliding_ImageAdapterDasboard(Dashboard.this, false, false, sliderString, new VarintListner() {
            @Override
            public void onModelClick(View view, int position, int size_id) {

                if (list.get(position).getNotification_class_id() == 1) {
                    Intent intent = new Intent(Dashboard.this, ProductDetails.class);
                    if (list.get(position).getProduct().getProduct_translation() == null) {
                        ProductDetails.nameofProduct = list.get(position).getProduct().getDefault_product_translation().getProduct_name();

                    } else {
                        ProductDetails.nameofProduct = list.get(position).getProduct().getProduct_translation().getProduct_name();
                    }
                    ProductDetails.productid = list.get(position).getProduct().getProduct_id();
                    ProductDetails.sku_id = 0;
                    startActivity(intent);
                } else if (list.get(position).getNotification_class_id() == 2) {

                    if (list.get(position).getCategory().getIs_parent_category() == 0) {
                        Intent intent = new Intent(Dashboard.this, ProductActivity.class);
                        formatedStringSearch = "";
                        SearchActivity.searchMax = "";
                        isViewWithCatalog = false;
                        ApplyFilter.brandArrays = null;
                        ApplyFilter.maximumSeekBar = "5000";
                        ApplyFilter.minimumSeekBar = "0";
                        ProductDetails.productid = 0;
                        ProductActivity.sortedid = 0;
                        if (list.get(position).getCategory().getCategory_translation() == null) {
                            ProductActivity.name = list.get(position).getCategory().getDefault_category_translation().getCategory_name();

                        } else {
                            ProductActivity.name = list.get(position).getCategory().getCategory_translation().getCategory_name();

                        }
                        ProductActivity.sucat_id = list.get(position).getSection_id();
                        ProductActivity.image = "true";
                        startActivity(intent);

                    } else {

                        Intent intent = new Intent(Dashboard.this, Categories.class);
                        if (list.get(position).getCategory().getCategory_translation() == null) {
                            name = list.get(position).getCategory().getDefault_category_translation().getCategory_name();

                        } else {
                            name = list.get(position).getCategory().getCategory_translation().getCategory_name();
                        }
                        intent.putExtra("category_id", list.get(position).getCategory().getCategory_id());
                        intent.putExtra("category_name", name);
                        startActivity(intent);
                    }
                }
            }
        }));
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);

        indicator.setPageColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        indicator.setFillColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        indicator.setViewPager(viewPagerSlider);
        viewPagerSlider.setScrollDurationFactor(2);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(7);

        NUM_PAGES3 = sliderString.size();

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (Appearance.appSettings.getIs_slider_effect() == 1) {
            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    // System.out.println(currentPageSlider);
                    if (currentPageSlider > NUM_PAGES3) {
                        currentPageSlider = 0;


                    }
                    //  viewPagerSlider.setCurrentItem(currentPageSlider++, true);
                    viewPagerSlider.setCurrentItem(currentPageSlider++, true);
                }
            };
            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 3000, 3000);
        }


    }

  /*  private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e("bhjjk", "Firebase reg id: " + regId);
        token = regId;
        if (!TextUtils.isEmpty(regId)) {
            System.out.println("Firebase Reg Id: " + regId);
        } else {
            System.out.println("Firebase Reg Id: " + "no");
        }
    }*/

    public void getData() {
        if (!hideloading) {
            findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        }

        findViewById(R.id.retryImage).setVisibility(View.GONE);

        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);


        Configuration configuration = getResources().getConfiguration();
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs1 = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs1.getString("user_id", "");
        String password = prefs1.getString("pwd", "");
        String languageid = prefs1.getString("language", String.valueOf(1));

        if (token == null) {
            token = "";
            Log.i("TOKEN", "TOKEN WAS NULL");
        }

        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", password);
            jsonObject.put("language_id", languageid);
            jsonObject.put("category_id", 0);
            jsonObject.put("token", token);
            jsonObject.put("is_language", is_language);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("saz" + jsonObject);
        Log.i("getData", "start_1085");

        VolleyTask volleyTask = new VolleyTask(this, URL_Home, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                findViewById(R.id.whiteloader).setVisibility(View.GONE);
                ResponseHandler responseHandler = new ResponseHandler();

                Log.i("getData", "complete1096");

                if (!responseHandler.validateResponse(Dashboard.this, response)) {
                    return;
                }


                try {
                    JSONObject jsonObject1 = response.getJSONObject("data");
//                    JSONObject application_appearance = jsonObject1.getJSONObject("data").getJSONObject("application_appearance");

                    String welcome = (String) getText(R.string.welcome);
                    welcomeText.setText(welcome);


                    if (jsonObject1.getJSONObject("data").get("user").equals(null)) {
                        String guest = getString(R.string.guest);
                        welcomeText2.setText(guest);


                    } else {

                        if (jsonObject1.getJSONObject("data").getJSONObject("user").get("name").equals(null)) {
                            welcomeText2.setText("");
                        } else {
                            welcomeText2.setText(jsonObject1.getJSONObject("data").getJSONObject("user").getString("name"));
                            String profile_image = (jsonObject1.getJSONObject("data").getJSONObject("user").getString("profile_image"));
                            Picasso.with(getApplicationContext()).load(BASE_IMAGE_URL+ profile_image).into(acccntimmage);
                        }
                    }

                    //ApplicationAppearence applicationAppearence = new ApplicationAppearence(Dashboard.this, application_appearance);
                    LoaderColorChanger loaderColorChanger = new LoaderColorChanger(Dashboard.this);
                    appName.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
                    toolbar.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                    bigtoolbar.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                    toggle.getDrawerArrowDrawable().setColor((Color.parseColor("#" + Appearance.appSettings.getApp_text_color())));
                    toggle.syncState();
                    DrawableCompat.setTint(((ImageView) findViewById(R.id.searchtoolbar)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
                    DrawableCompat.setTint(((ImageView) findViewById(R.id.notificationtoolbar)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
                    DrawableCompat.setTint(((ImageView) findViewById(R.id.carttoolbar)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

                    if (is_language == 1) {

                        // JSONObject product_labels = jsonObject1.getJSONObject("data").getJSONObject("product_labels");
                        //  JSONObject navigationLabels = jsonObject1.getJSONObject("data").getJSONObject("navigation_labels");
                        //  JSONObject wallet_labels = jsonObject1.getJSONObject("data").getJSONObject("wallet_labels");
                        // JSONObject cart_labels = jsonObject1.getJSONObject("data").getJSONObject("cart_labels");
                        // JSONObject complaint_labels = jsonObject1.getJSONObject("data").getJSONObject("complaint_labels");
                        // JSONObject gateway_labels = jsonObject1.getJSONObject("data").getJSONObject("gateway_labels");
                        // JSONObject notification_labels = jsonObject1.getJSONObject("data").getJSONObject("notification_labels");
                        //  JSONObject global_labels = jsonObject1.getJSONObject("data").getJSONObject("global_labels");
                        //JSONObject user_labels = jsonObject1.getJSONObject("data").getJSONObject("user_labels");
                        // JSONObject wishlist_labels = jsonObject1.getJSONObject("data").getJSONObject("wishlist_labels");
                        //  JSONObject order_labels = jsonObject1.getJSONObject("data").getJSONObject("order_labels");
//
                        // setlabels(navigationLabels, cart_labels, wallet_labels,
                        //   complaint_labels, gateway_labels, product_labels,
                        //  notification_labels, global_labels, user_labels,
                        //  wishlist_labels, order_labels);


                        //Additional Line for WelCome Lable
                        String welcomes = getString(R.string.welcome);
                        String guests = getString(R.string.guest);
                        welcomeText.setText(welcomes + " ");
                        if (isGuestActive) {
                            welcomeText2.setText(guests);
                        }
                        is_language = 0;
                    }

                    setLabels();

                    //  Log.i("getData", "setLable2_1148");

                    /*if (!labels.equals(null)) {
                        JSONObject labels1 = jsonObject1.getJSONObject("data").getJSONObject("labels");
                        setlabels(labels1, navigationLabels, wallet_labels, cart_labels, complaint_labels, gateway_labels);
                        initui();
                    }*/

                    appName.setText(Appearance.appTranslation.getApp_name());
                    contentTitle.setText(Appearance.appTranslation.getApp_name());
                    drawerTitle.setText(Appearance.appTranslation.getApp_name());

                    setLanguage(jsonObject1.getJSONObject("data").getJSONArray("language"));
                    cart_count = jsonObject1.getJSONObject("data").getInt("cart_count");
                    notification_count = jsonObject1.getJSONObject("data").getInt("notification_count");
                    //Toast.makeText(Dashboard.this, ""+notification_count, Toast.LENGTH_SHORT).show();
                    notificationcount = (TextView) toolbar.findViewById(R.id.notification_count_textview);
                   /* if(Dashboard.notification_count!=0)
                    {
                        notificationcount.setVisibility(View.VISIBLE);
                        notificationcount.setText(String.valueOf(Dashboard.notification_count));
                    }*/


                    new CartCountUtil(Dashboard.this);


                    Gson gson = new Gson();
                    RelativeLayout slidervisibility = (RelativeLayout) findViewById(R.id.sliderVisibility);
                    if (Appearance.appSettings.getIs_slider() == 0) {
                        slidervisibility.setVisibility(View.GONE);

                    } else if (jsonObject1.getJSONObject("data").getJSONArray("slider").length() == 0) {
                        slidervisibility.setVisibility(View.GONE);

                    } else {
                        slidervisibility.setVisibility(View.VISIBLE);

                        List<SliderModel> list = Arrays.asList(gson.fromJson(jsonObject1.getJSONObject("data").getJSONArray("slider").toString(), SliderModel[].class));
                        System.out.println("hf" + list.size());
                        sliderString = new ArrayList<String>();
                        for (int i = 0; i < jsonObject1.getJSONObject("data").getJSONArray("slider").length(); i++) {

                            if (list.get(i).getSlider_translation() == null) {
                                sliderString.add(list.get(i).getDefault_slider_translation().getSlider_image());
                            } else {
                                sliderString.add(list.get(i).getSlider_translation().getSlider_image());

                            }

                        }
                        System.out.println("fewsaz" + sliderString);
                        if (sliderString.size() > 0 && loadSlider == true) {
                            init(list);
                        }
                    }

                 /*   if (jsonObject1.getJSONObject("data").getJSONArray("offer_zone_products").length() == 0) {
                        RelativeLayout cardOffer = (RelativeLayout) findViewById(R.id.cardoffer);
                        cardOffer.setVisibility(View.GONE);

                    }*/
                    RecyclerView offerBlocks = (RecyclerView) findViewById(R.id.offerBlocks);

                    if (jsonObject1.getJSONObject("data").getJSONArray("offer_blocks").length() > 0) {
                        offerBlocks.setVisibility(View.VISIBLE);

                        setOfferBlocks(jsonObject1.getJSONObject("data").getJSONArray("offer_blocks"));
                    } else {
                        offerBlocks.setVisibility(View.GONE);
                    }
                    Log.i("getData", "setOfferBlk_1214");

                    RelativeLayout cardLatest = (RelativeLayout) findViewById(R.id.cardlatest);

                    if (jsonObject1.getJSONObject("data").getJSONArray("latest_products").length() == 0 || Appearance.appSettings.getShow_new_arrival_product() == 0) {
                        cardLatest.setVisibility(View.GONE);

                        //Toast.makeText(getApplicationContext(), "Please add products...", Toast.LENGTH_SHORT).show();
                        findViewById(R.id.noitemavalable1).setVisibility(View.VISIBLE);
                        Button button = (Button) findViewById(R.id.noitemavailablebutton);
                        GradientDrawable bgShape = (GradientDrawable) button.getBackground();
                        bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                        button.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                findViewById(R.id.noitemavalable1).setVisibility(View.GONE);
                                refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout1);
                                refreshLayout.setColorSchemeColors((Color.parseColor("#" + Appearance.appSettings.getApp_text_color())));
                                refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                    @Override
                                    public void onRefresh() {

                                        pagenumber = 1;
                                        // Refresh items
                                        hideloading = true;
                                        getData();
                                        Log.i("getData", "2222222");


                                    }
                                });
                            }
                        });

                    } else {
                        cardLatest.setVisibility(View.VISIBLE);

                    }
                    RelativeLayout cardRecent = (RelativeLayout) findViewById(R.id.cardrecentproducts);

                    if (jsonObject1.getJSONObject("data").getJSONArray("recent_view_products").length() == 0 || Appearance.appSettings.getShow_recent_view_product() == 0) {
                        cardRecent.setVisibility(View.GONE);
                    } else {
                        cardRecent.setVisibility(View.VISIBLE);

                    }
                    RelativeLayout cardShopByCategory = (RelativeLayout) findViewById(R.id.shopbycategoryvisibility);

                    if (jsonObject1.getJSONObject("data").getJSONArray("categories").length() == 0 || Appearance.appSettings.getShow_grid_category() == 0) {
                        cardShopByCategory.setVisibility(View.GONE);
                    } else {
                        cardShopByCategory.setVisibility(View.VISIBLE);

                    }
                    RelativeLayout topseller = (RelativeLayout) findViewById(R.id.topseller);

                    if (jsonObject1.getJSONObject("data").getJSONArray("top_sellers").length() == 0 || Appearance.appSettings.getShow_grid_category() == 0) {
                        topseller.setVisibility(View.GONE);
                    } else {
                        topseller.setVisibility(View.VISIBLE);
                    }
                    //setOfferBlock(jsonObject1.getJSONObject("data").getJSONArray("offer_block"));
                    pageCount = jsonObject1.getJSONObject("data").getInt("page_count");
                    totalCount = jsonObject1.getJSONObject("data").getInt("total_count");
                    setpopular(jsonObject1.getJSONObject("data").getJSONArray("latest_products"));
                    Log.i("getData", "setPop_1270");

                    setNavigation(jsonObject1.getJSONObject("data").getJSONArray("categories"));

                    Log.i("getData", "setNav_1274");

                    System.out.println("esdxz" + jsonObject1.getJSONObject("data").getJSONArray("categories"));
                    int productCount = jsonObject1.getJSONObject("data").getInt("product_count");
                 /*   String productCountString = "";
                    if (productCount != 0) {
                        productCountString = "" + productCount + "+ ";
                    }
                    String search  = getString(R.string.search);
                    String products = getString(R.string.products);
                    editTextSearch.setHint(search +
                            " " + productCountString + products);*/

                    setRecentView(jsonObject1.getJSONObject("data").getJSONArray("recent_view_products"));
                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.cardmaxproducts);


                    if(Appearance.appSettings.getIs_show_product_count() == 1)
                    {
                        String search_1  = getString(R.string.search);
                        String products_1 = getString(R.string.products);
                        editTextSearch.setHint(search_1 +
                                " " + productCount+" " + products_1);
                    }
                    else
                    {
                        String search_1  = getString(R.string.search);
                        String products_1 = getString(R.string.products);
                        editTextSearch.setHint(search_1 +" "+ products_1);
                    }


                    if (jsonObject1.getJSONObject("data").getJSONArray("max_view_products").length() == 0 || Appearance.appSettings.getShow_max_view_product() == 0) {
                        relativeLayout1.setVisibility(View.GONE);
                    } else {
                        relativeLayout1.setVisibility(View.VISIBLE);

                    }
                    setMax_view_products(jsonObject1.getJSONObject("data").getJSONArray("max_view_products"));
                    setShopBycategory(jsonObject1.getJSONObject("data").getJSONArray("categories"));
                    setTopSellers(jsonObject1.getJSONObject("data").getJSONArray("top_sellers"));
                    Log.i("getData", "setlMax&SHop_1292");

                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.featuredbrands);
                    if (jsonObject1.getJSONObject("data").getJSONArray("featured_brand").length() > 0 && Appearance.appSettings.getShow_featured_brands() == 1) {
                        relativeLayout.setVisibility(View.VISIBLE);

                        setFeaturedBrands(jsonObject1.getJSONObject("data").getJSONArray("featured_brand"));
                    } else {
                        relativeLayout.setVisibility(View.GONE);
                    }
                    offerBlocks = (RecyclerView) findViewById(R.id.offerBlocks);

                    Log.i("getData", "RefreshBefore_1312");

                    refreshLayout.setRefreshing(false);
                    Log.i("getData", "RefreshFalse_1315");


                } catch (JSONException e) {
                    e.printStackTrace();
                    refreshLayout.setRefreshing(false);

                }
            }

            @Override
            public void fnErrorOccurred(String error) {

                Log.i("getData", "Error");


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
                        progressBar.setVisibility(View.GONE);
                        findViewById(R.id.retryImage).setVisibility(View.VISIBLE);
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

                refreshLayout.setRefreshing(false);
            }
        });


    }


    private void setFeaturedBrands(final JSONArray jsonArray) {
        Gson gson = new Gson();
        final List<FeaturedBrandsModel> listfeatured = Arrays.asList(gson.fromJson(jsonArray.toString(), FeaturedBrandsModel[].class));
        final ArrayList<String> brandString = new ArrayList<>();
        for (int i = 0; i < listfeatured.size(); i++) {
            brandString.add(listfeatured.get(i).getDefault_brand_translation().getBrand_image());
        }
        final ViewPagerCustomDuration featuredbrandslider = (ViewPagerCustomDuration) findViewById(R.id.recfeaturedbrands);

        try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(featuredbrandslider.getContext(), sInterpolator, true);
            scroller.setmDuration(1000);
            mScroller.set(featuredbrandslider, scroller);
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
        featuredbrandslider.setClipToPadding(false);
        featuredbrandslider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // set padding manually, the more you set the padding the more you see of prev & next page
        // sets a margin b/w individual pages to ensure that there is a gap b/w them
        System.out.println("edsxz" + brandString);
        if (brandString.size() > 0) {
            featuredbrandslider.setAdapter(new Sliding_ImageAdapterDasboard(Dashboard.this, true, false, brandString, new VarintListner() {
                @Override
                public void onModelClick(View view, int position, int size_id) {
                    Intent intent = new Intent(Dashboard.this, ProductActivity.class);
                    ArrayList<Integer> brandArrays = new ArrayList<>();
                    brandArrays.add(listfeatured.get(position).getBrand_id());
                    System.out.println("dsxz" + listfeatured.get(position).getBrand_id() + brandArrays);
                    formatedStringSearch = brandArrays.toString()
                            .replace("[", "")  //remove the right bracket
                            .replace("]", "")
                            .replace(" ", "")//remove the left bracket
                            .trim();
                    formatedStringSearch = formatedStringSearch;
                    SearchActivity.searchMax = "";
                    isViewWithCatalog = false;
                    ApplyFilter.brandArrays = brandArrays;
                    ApplyFilter.maximumSeekBar = "5000";
                    ApplyFilter.minimumSeekBar = "0";
                    ProductDetails.productid = 0;
                    ProductActivity.sortedid = 0;
                    if (listfeatured.get(position).getBrand_translation() == null) {
                        ProductActivity.name = listfeatured.get(position).getDefault_brand_translation().getBrand_name();

                    } else {
                        ProductActivity.name = listfeatured.get(position).getBrand_translation().getBrand_name();
                    }
                    ProductActivity.sucat_id = 0;
                    ProductActivity.image = "true";
                    startActivity(intent);

                }
            }));
            featuredbrandslider.setPageMargin(20);
          /*  featuredbrandslider.setPageMargin(20);
            featuredbrandslider.setPadding(0, 0, 100, 0);

            featuredbrandslider.setPageTransformer(false, new ViewPager.PageTransformer() {
                @Override
                public void transformPage(View page, float position) {
                    if (featuredbrandslider.getCurrentItem() == 0) {
                        featuredbrandslider.setPadding(0, 0, 100, 0);

                    } else if (featuredbrandslider.getCurrentItem() == jsonArray.length() - 1) {
                        featuredbrandslider.setPadding(100, 0, 0, 0);


                    } else {
                        featuredbrandslider.setPadding(0, 0, 100, 0);

                    }
                }
            });*/
        }
    }

 /*   private void setlabels(JSONObject navigationLabels, JSONObject cart_labels, JSONObject wallet_labels,
                           JSONObject complaint_labels, JSONObject gateway_labels, JSONObject product_labels,
                           JSONObject notification_labels, JSONObject global_labels, JSONObject user_labels,
                           JSONObject wishlist_labels, JSONObject order_labels) {

        Label.cartLabel = new Gson().fromJson(cart_labels.toString(), CartLabel.class);
        Label.navigationLabels = new Gson().fromJson(navigationLabels.toString(), NavigationLabels.class);
        Label.productLabel = new Gson().fromJson(product_labels.toString(), ProductLabel.class);
        Label.wishlistLabel = new Gson().fromJson(wishlist_labels.toString(), WishlistLabel.class);
        Label.orderLabel = new Gson().fromJson(order_labels.toString(), OrderLabel.class);
        Label.globalLabel = new Gson().fromJson(global_labels.toString(), GlobalLabel.class);
        Label.userLabel = new Gson().fromJson(user_labels.toString(), UserLabel.class);
        Label.notificationLabel = new Gson().fromJson(notification_labels.toString(), NotificationLabel.class);
        Label.complaintLabel = new Gson().fromJson(complaint_labels.toString(), ComplaintLabel.class);
        Label.walletLabels = new Gson().fromJson(wallet_labels.toString(), WalletLabels.class);
        Label.gatewayLabel = new Gson().fromJson(gateway_labels.toString(), GatewayLabel.class);


        Log.i("ProductLabel", "id" + Label.productLabel.getAdd_more());
        Log.i("ProductLabel", "cont" + Label.productLabel.getAll_reviews());

    }*/

    private void setLanguage(JSONArray jsonArray) {
        System.out.println("desxaZ" + jsonArray);
        Gson gson = new Gson();
        List<LanguagesModel> list = Arrays.asList(gson.fromJson(jsonArray.toString(), LanguagesModel[].class));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        languageRecycler.setLayoutManager(layoutManager);
        LanguageAdapter languageAdapter = new LanguageAdapter(this, list);
        languageRecycler.setAdapter(languageAdapter);
    }

    private void setOfferBlocks(JSONArray jsonArray) {
        Gson gson = new Gson();
        final List<OfferBlockModel> list = Arrays.asList(gson.fromJson(jsonArray.toString(), OfferBlockModel[].class));
        //final List<ProductModel> list = Arrays.asList(gson.fromJson(jsonArray.toString(), ProductModel[].class));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.offerBlocks);
        recyclerView.setNestedScrollingEnabled(false);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);

        recyclerView.setLayoutManager(layoutManager);
        OfferBlocksAdapter offerBlocksAdapter = new OfferBlocksAdapter(this, list, new VarintListner() {
            //ProductAdapter productAdapter = new ProductAdapter(this,list,new VarintListner()


            @Override
            public void onModelClick(View view, int position, int size_id) {

                if (list.get(position).getNotification_class_id() == 1) {
                    if (list.get(position).getNotification_class_id() == 1) {
                        Intent intent = new Intent(Dashboard.this, ProductDetails.class);
                        if (list.get(position).getProduct().getProduct_translation() == null) {
                            ProductDetails.nameofProduct = list.get(position).getProduct().getDefault_product_translation().getProduct_name();
                        } else {
                            ProductDetails.nameofProduct = list.get(position).getProduct().getProduct_translation().getProduct_name();
                        }
                        ProductDetails.productid = list.get(position).getProduct().getProduct_id();
                        ProductDetails.sku_id = 0;
                        startActivity(intent);
                    } else if (list.get(position).getNotification_class_id() == 2) {

                        if (list.get(position).getCategory().getIs_parent_category() == 0) {
                            Intent intent = new Intent(Dashboard.this, ProductActivity.class);
                            formatedStringSearch = "";
                            SearchActivity.searchMax = "";
                            isViewWithCatalog = false;
                            ApplyFilter.brandArrays = null;
                            ApplyFilter.maximumSeekBar = "5000";
                            ApplyFilter.minimumSeekBar = "0";
                            ProductDetails.productid = 0;
                            ProductActivity.sortedid = 0;
                            if (list.get(position).getCategory().getCategory_translation() == null) {
                                ProductActivity.name = list.get(position).getCategory().getDefault_category_translation().getCategory_name();

                            }
                            else { ProductActivity.name = list.get(position).getCategory().getCategory_translation().getCategory_name();
                            }
                            ProductActivity.sucat_id = list.get(position).getSection_id();
                            ProductActivity.image = "true";
                            startActivity(intent);
                        }
                        else if(list.get(position).getNotification_class_id()==3)
                        {
                            Intent intent = new Intent(Dashboard.this, ProductActivity.class);
                            if (list.get(position).getCategory().getCategory_translation() == null) {
                                ProductActivity.name = list.get(position).getCategory().getDefault_category_translation().getCategory_name();

                            }
                            else { ProductActivity.name = list.get(position).getCategory().getCategory_translation().getCategory_name();
                            }
                            ProductActivity.sucat_id = list.get(position).getSection_id();
                            ProductActivity.image = "true";
                            startActivity(intent);
                        }

                        else {
                            Intent intent = new Intent(Dashboard.this, Categories.class);
                            if (list.get(position).getCategory().getCategory_translation() == null) {
                                name = list.get(position).getCategory().getDefault_category_translation().getCategory_name();
                            } else {
                                name = list.get(position).getCategory().getCategory_translation().getCategory_name();
                            }
                            intent.putExtra("category_id", list.get(position).getSection_id());
                            intent.putExtra("category_name", name);
                            startActivity(intent);

                        }

                    }
                }
            }
        });
        recyclerView.setAdapter(offerBlocksAdapter);
    }

    private void setMax_view_products(JSONArray jsonArray) {

        Gson gson = new Gson();
        List<ProductModel> list2 = Arrays.asList(gson.fromJson(jsonArray.toString(), ProductModel[].class));
        islatestProduct = false;
        list = new ArrayList<>();
        list.addAll(list2);

        System.out.println("dada" + list);
        RecyclerView recyclerRecentProducts = (RecyclerView) findViewById(R.id.recmaxproduct);
        recyclerRecentProducts.setNestedScrollingEnabled(false);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        recyclerRecentProducts.setLayoutManager(layoutManager);
        productAdaptermax = new ProductAdapter(this, list, "max");
        recyclerRecentProducts.setAdapter(productAdaptermax);
    }

    private void setRecentView(JSONArray recent_view_products) {
        Gson gson = new Gson();
        List<ProductModel> list = Arrays.asList(gson.fromJson(recent_view_products.toString(), ProductModel[].class));
        islatestProduct = false;
/*List<ProductModel> list=new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add(list1.get(i));
        }*/
        System.out.println("dada" + list);
        RecyclerView recyclerRecentProducts = (RecyclerView) findViewById(R.id.recrecentproduct);
        recyclerRecentProducts.setNestedScrollingEnabled(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerRecentProducts.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, list, "recent");
        recyclerRecentProducts.setAdapter(productAdapter);
    }

    private void setShopBycategory(JSONArray gridCategories) {
        Gson gson = new Gson();
        List<AllCategoriesModel> list = Arrays.asList(gson.fromJson(gridCategories.toString(), AllCategoriesModel[].class));

        RecyclerView recyclerViewShopBy = (RecyclerView) findViewById(R.id.recshopby);
        recyclerViewShopBy.setNestedScrollingEnabled(false);
        recyclerViewShopBy.setLayoutManager(new GridLayoutManager(this, 3));
        Shopbycateadapter shopbycateadapter = new Shopbycateadapter(Dashboard.this, list);
        recyclerViewShopBy.setAdapter(shopbycateadapter);
    }

    private void setTopSellers(JSONArray top_sellers) {
        Gson gson = new Gson();
        List<BusinessModel> list = Arrays.asList(gson.fromJson(top_sellers.toString(), BusinessModel[].class));
      //  islatestProduct = false;
/*List<ProductModel> list=new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add(list1.get(i));
        }*/
        System.out.println("dada" + list);
        RecyclerView recylertopseller = (RecyclerView) findViewById(R.id.rectopsellers);
        recylertopseller.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recylertopseller.setLayoutManager(layoutManager);
        businessAdapter = new BusinessAdapter(this, list);
        recylertopseller.setAdapter(businessAdapter);
    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        editTextSearch.setText("");

        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        isViewWithCatalog = false;
        // Your onResume Code Here
        new CartCountUtil(Dashboard.this);

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());

        if (passedOnCreate == 1) {

            SharedPreferences preferences = getSharedPreferences("cheapstuffs", Context.MODE_PRIVATE);
            if (preferences.contains("login")) {
                SharedPreferences preferences1 = getSharedPreferences("cheapstuffs", Context.MODE_PRIVATE);
                String s = prefs.getString("username", "");
                welcomeText2.setText(s);
                //Toast.makeText(this, s + "", Toast.LENGTH_SHORT).show();
                passedOnCreate = 0;
            }

            SharedPreferences guest = getSharedPreferences("GUESTSETTING", MODE_PRIVATE);
            isGuestActive = guest.getBoolean("is_guest_check", false);
            toggleLoginLogout(isGuestActive);
        }
    }

    public void toggleLoginLogout(boolean b) {
        if (b) {
            findViewById(R.id.navlogin).setVisibility(View.VISIBLE);
            findViewById(R.id.navlogout).setVisibility(View.GONE);

        } else {
            findViewById(R.id.navlogout).setVisibility(View.VISIBLE);
            findViewById(R.id.navlogin).setVisibility(View.GONE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(requestCode + "dsxc" + resultCode + data);
        if (requestCode == 5 && resultCode == RESULT_OK) {
        } else {
        }
    }
}