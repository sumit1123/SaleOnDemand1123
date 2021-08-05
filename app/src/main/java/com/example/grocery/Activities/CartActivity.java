package com.example.grocery.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.CartAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.CartModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.NestedScrollView;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.interfaces.IConstants.BASE_URL;
import static com.example.grocery.utils.GettingCurrentRTLLTR.islanguageLTR;

public class CartActivity extends AppCompatActivity {
    public static boolean isRemoveItemPressed;
    public static CartAdapter cartAdapter;
    public static double sum;
    public static int deliviry;
    public static ArrayList<Integer> cartIds;
    public static double gst;
    public static boolean gstStatus;
    public static boolean shouldExecuteOnResume;
    public TextView pin;
    public double quantity;
    private int whiteLoader;
    private String email, password;
    private List<CartModel> lista;
    private Dialog dialog;
    private AppCompatEditText pincode;
    private String userid;
    private LinearLayout cardView2;
    private LinearLayout cardView;
    private LinearLayout cardView1;
    private String user_pincode;
    private TextView toolTitleText;
    private TextView viewPriceDetails, shakingPriceText;
    private NestedScrollView scrollview;
    private CardView pincodeCardVisibility;
    //private SharedPreferences labelsShared;
    private TextView deliveryText;
    private TextView gstText;
    private Button submitCart;
    private TextView policyText;
    private TextView pincodeText;
    private TextView changetext;
    private SwipeRefreshLayout refreshLayout;
    private boolean hideloading;
    private TextView price;
    private TextView total;
    private TextView finalTotal;



       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_cart);

        ((ProgressBar) findViewById(R.id.blurredloaderprogressbar)).getIndeterminateDrawable().setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()), PorterDuff.Mode.MULTIPLY);

        //labelsShared = getSharedPreferences("labels", MODE_PRIVATE);
        submitCart = (Button) findViewById(R.id.submitcart);

        pincodeCardVisibility = (CardView) findViewById(R.id.pincodeCardVisibility);
        if (Appearance.appSettings.getIs_pincode() == 0) {
            pincodeCardVisibility.setVisibility(View.GONE);
        }

        whiteLoader = 1;
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        // ColorChangerNoItemAvailable colorChangerNoItemAvailable = new ColorChangerNoItemAvailable(this);
        pin = (TextView) findViewById(R.id.cartpin);
        pin.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        DrawableCompat.setTint(((ImageView) findViewById(R.id.pincodeImage)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getText_color()));
        ((TextView) findViewById(R.id.changetext)).setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        findViewById(R.id.cart).setVisibility(View.GONE);
        cardView = (LinearLayout) findViewById(R.id.cartvisibility);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        cardView1 = (LinearLayout) findViewById(R.id.buttoncard);
        viewPriceDetails = (TextView) findViewById(R.id.shake);
        pincodeText = (TextView) findViewById(R.id.pincodeText);
        changetext = (TextView) findViewById(R.id.changetext);

        shakingPriceText = (TextView) findViewById(R.id.shakingtext);
        shakingPriceText.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));

        deliveryText = (TextView) findViewById(R.id.deliveryText);
        gstText = (TextView) findViewById(R.id.gstText);
        policyText = (TextView) findViewById(R.id.policyText);

        try {
//            pincodeText.setText(new JSONObject(labelsShared.getString("labels", null)).getString("pincode"));
//            // changetext.setText(new JSONObject(labelsShared.getString("cart_labels", null)).getString("view_price_details"));
//            viewPriceDetails.setText(new JSONObject(labelsShared.getString("cart_labels", null)).getString("view_price_details"));
//            shakingPriceText.setText(new JSONObject(labelsShared.getString("cart_labels", null)).getString("price_details"));
//            deliveryText.setText(new JSONObject(labelsShared.getString("cart_labels", null)).getString("delivery"));
//            // gstText.setText(new JSONObject(labelsShared.getString("cart_labels", null)).getString("gst"));
//            submitCart.setText(new JSONObject(labelsShared.getString("cart_labels", null)).getString("continue"));
//            policyText.setText(new JSONObject(labelsShared.getString("cart_labels", null)).getString("cart_secure_msg"));

            String pincode = getString(R.string.pincode);
            String viewprice = getString(R.string.view_price_details);
            String price = getString(R.string.price_details);
            String del = getString(R.string.delivery);
            String gst = getString(R.string.gst);
            String cart = getString(R.string.cart_secure_msg);

           pincodeText.setText(pincode);
            // changetext.setText(new JSONObject(labelsShared.getString("cart_labels", null)).getString("view_price_details"));
            viewPriceDetails.setText(viewprice);
            shakingPriceText.setText(price);
           deliveryText.setText(del);
            gstText.setText(gst);
           policyText.setText(cart);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        scrollview = (NestedScrollView) findViewById(R.id.scrollview);
        viewPriceDetails.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        viewPriceDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollview.fullScroll(View.FOCUS_DOWN);
                Animation shake = AnimationUtils.loadAnimation(CartActivity.this, R.anim.shake);
                shake.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        //shakingPriceText.setTextColor(Color.parseColor("#" + colorSettings.getString("text_color", "#FFFFFF")));
                        shakingPriceText.setTextColor(Color.parseColor("#00A0FF"));
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //shakingPriceText.getTextColors().getDefaultColor();
                        shakingPriceText.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                shakingPriceText.startAnimation(shake);

            }
        });

        SharedPreferences prefs1 = getSharedPreferences("UserId", MODE_PRIVATE);
        user_pincode = prefs1.getString("pincode", "");
        pin.setText(user_pincode);
        RelativeLayout cardViewpinchange = (RelativeLayout) findViewById(R.id.cardpincodechange);
        cardViewpinchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(CartActivity.this, R.style.AlertDialogStyle_Default);
                dialog.setContentView(R.layout.pincodepopup);
                pincode = (AppCompatEditText) dialog.findViewById(R.id.etpincode);

                new EditTextColorChanger(CartActivity.this, pincode);

                final Button checkPincode = (Button) dialog.findViewById(R.id.etcheck);
                Button cancelPincode = (Button) dialog.findViewById(R.id.etcancel);
                checkPincode.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));

                SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                userid = prefs.getString("user_id", "");
                email = prefs.getString("email", "");
                password = prefs.getString("pwd", "");
                final String languageid = prefs.getString("language", String.valueOf(1));
                cancelPincode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                checkPincode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkPincode.setEnabled(false);
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("business_id",IConstants.BUSINESS_ID);
                            jsonObject.put("pincode", pincode.getText().toString());
                            jsonObject.put("id", userid);
                            jsonObject.put("password", password);
                            jsonObject.put("language_id", languageid);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String url = BASE_URL + "updatePincode";
                        System.out.println("sfcx" + jsonObject);
                        System.out.println("wedxs" + url.trim());
                        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);

                        VolleyTask volleyTask = new VolleyTask(CartActivity.this, url.replace(" ", "%20"), jsonObject, Request.Method.POST);

                        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                            @Override
                            public void fnPostTaskCompleted(JSONArray response) {
                                System.out.println("sed" + response);
                            }

                            @Override
                            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                                try {
                                    if (!new ResponseHandler().validateResponse(CartActivity.this, response)) {
                                        dialog.dismiss();
                                        return;
                                    }
                                    new CustomToast(CartActivity.this, response.getJSONObject("data").getString("msg"));

                                    SharedPreferences.Editor editor = getSharedPreferences("UserId", MODE_PRIVATE).edit();

                                    editor.putString("pincode", pincode.getText().toString());
                                    editor.apply();
                                    pin.setText(pincode.getText().toString());
                                    dialog.dismiss();

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


                });
                dialog.show();
            }
        });

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
           RelativeLayout toolbarNotification = (RelativeLayout) findViewById(R.id.toolnotification);

           toolbarNotification.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(CartActivity.this, NotificationActivity.class);
                   startActivity(intent);
               }
           });

           if (Appearance.appSettings.getIs_notification() == 0) {
               toolbarNotification.setVisibility(View.GONE);
           }
           RelativeLayout toolBarCart = (RelativeLayout) findViewById(R.id.cart);
           toolBarCart.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(CartActivity.this, CartActivity.class);
                   startActivity(intent);
               }
           });


       /* RelativeLayout toolNotification = (RelativeLayout) findViewById(R.id.toolnotification);

        toolNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, NotificationActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });

        if (Appearance.appSettings.getIs_notification() == 0) {
            toolNotification.setVisibility(View.GONE);
        }*/
        RelativeLayout toolSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.minimumSeekBar = "0";
                ApplyFilter.brandArrays = null;
                SearchActivity.searchMax = "";
                SearchActivity.searchMin = "";
                ProductDetails.productid = 0;
                isViewWithCatalog = false;
                SearchActivity.sortedida = 0;
                SearchActivity.formatedStringSearch = "";
                SearchActivity.imagea = "true";
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
        toolTitleText = (TextView) findViewById(R.id.titlebar);
        submitCart.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        submitCart.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        submitCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lista.size() != 0) {
                    cartIds = new ArrayList<Integer>();

                    for (int i = 0; i < lista.size(); i++) {
                        cartIds.add(lista.get(i).getCart_id());
                     //   Toast.makeText(CartActivity.this, ""+cartIds, Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(CartActivity.this, PaymentDetailsActivity.class);
                    startActivity(intent);
                } else {
                }

            }

        });
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setColorSchemeColors((Color.parseColor("#" + Appearance.appSettings.getText_color())));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                hideloading = true;

                getData();
            }
        });
        getData();

    }

    public void getData() {

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
        email = prefs.getString("email", "");
        password = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", password);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("asx" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_GETCArt, jsonObject, Request.Method.POST);
        System.out.println(IConstants.URL_GETCArt + "?id=" + userid + "&password=" + password);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {

            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                refreshLayout.setRefreshing(false);
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                findViewById(R.id.whiteloader).setVisibility(View.GONE);
                showCart(response);

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

    private void setReviews(JSONArray data) {
        Gson gson = new Gson();
        lista = Arrays.asList(gson.fromJson(data.toString(), CartModel[].class));
        System.out.println("dada" + lista);
        RecyclerView cartRecycler = (RecyclerView) findViewById(R.id.reccart);
        //  bar.setVisibility(View.GONE);

        cartRecycler.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        cartRecycler.setLayoutManager(layoutManager);

        cartAdapter = new CartAdapter(this, lista);
        cartRecycler.setAdapter(cartAdapter);
    }

    public void showCart(JSONObject response) {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("asddd" + response);
        //  findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

        try {
            ResponseHandler responseHandler = new ResponseHandler();
            if (!responseHandler.validateResponse(CartActivity.this, response)) {
                return;
            }

            JSONObject jsonObject1 = response.getJSONObject("data");
            Dashboard.cart_count = jsonObject1.getJSONObject("data").getInt("cart_count");
            Dashboard.notification_count = jsonObject1.getJSONObject("data").getInt("notification_count");

            int gstNumber = Appearance.appSettings.getIs_gst();

            if (gstNumber == 1) {
                gstStatus = true;
            } else {
                gstStatus = false;
            }

            JSONArray jsonArray = jsonObject1.getJSONObject("data").getJSONArray("products");

            if (jsonArray.length() > 0) {
                       String cart = getString(R.string.my_cart);
                toolTitleText.setText(cart + "(" + jsonArray.length() + ")");

                cardView.setVisibility(View.VISIBLE);
                cardView1.setVisibility(View.VISIBLE);

            } else {
                String cart = getString(R.string.my_cart);
                toolTitleText.setText(cart);

                findViewById(R.id.noitemavalable).setVisibility(View.VISIBLE);
                Button button = (Button) findViewById(R.id.noitemavailablebutton);
        /*     button.setBackgroundColor(Color.parseColor("#" + colorSettings.getString("app_color", "#FFFFFF")));
                button.setTextColor(Color.parseColor("#" + colorSettings.getString("app_text_color", "#FFFFFF")));*/
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
                    button.setBackgroundTintList(stateListDrawable);
                    button.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
                } else {
                    button.setBackground(CartActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                    GradientDrawable bgShape = (GradientDrawable) button.getBackground();
                    bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                    button.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
                }
                findViewById(R.id.imagenoitemAvailable).setBackground(CartActivity.this.getResources().getDrawable(R.drawable.empty_cart_default));
                TextView textView = (TextView) findViewById(R.id.noitemavailabletext);

               textView.setText(getText(R.string.empty_cart_title));
                TextView extra = (TextView) findViewById(R.id.extra);
                extra.setText(getText(R.string.empty_cart_description));

               button.setText(getText(R.string.continue_shopping));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CartActivity.this, Dashboard.class);
                        startActivity(intent);
                    }
                });
                cardView.setVisibility(View.GONE);
                cardView1.setVisibility(View.GONE);
            }
            setReviews(jsonArray);
            sum = 0;
            for (int i = 0; i < jsonArray.length(); i++) {
                double quantity = (jsonArray.getJSONObject(i).getDouble("quantity"));
                double myprice = jsonArray.getJSONObject(i).getJSONObject("sku").getDouble("my_price") * quantity;
                sum = sum + myprice;
            }
            deliviry = 0;
            double save = 0;
            for (int i = 0; i < jsonArray.length(); i++) {
                double quantity = (jsonArray.getJSONObject(i).getDouble("quantity"));
                double actual = jsonArray.getJSONObject(i).getJSONObject("sku").getDouble("market_price") * quantity;
                double selling = jsonArray.getJSONObject(i).getJSONObject("sku").getDouble("my_price") * quantity;
                if (actual > selling) {
                    double saveq = actual - selling;
                    save = save + saveq;
                }
            }
            TextView textView = (TextView) findViewById(R.id.savings);
            System.out.println("dsxz" + save);
            if (save > 0) {
//                if (save % 1 == 0) {
//                    int i = (int) Math.round(save);
//                    textView.setText(new JSONObject(labelsShared.getString("cart_labels", null))
//                            .getString("you_will_save") + " " +
//                            colorSettings.getString("currency", "₹") + "" +
//                            String.format(Locale.ENGLISH, "%d", i) + " " +
//                            new JSONObject(labelsShared.getString("cart_labels", null))
//                                    .getString("on_this_order"));
//
//                } else {
//                    textView.setText(new JSONObject(labelsShared.getString("cart_labels", null)).getString("you_will_save") + " " + colorSettings.getString("currency", "₹") + "" + String.format(Locale.ENGLISH, "%.1f", save) + " " + new JSONObject(labelsShared.getString("cart_labels", null)).getString("on_this_order"));
//                }
                if (save % 1 == 0) {
                    int i = (int) Math.round(save);
                    String save_price = getString(R.string.you_will_save);
                    String order = getString(R.string.on_this_order);
                    textView.setText(save_price + " " +
                            Appearance.appTranslation.getCurrency() + "" +
                            String.format(Locale.ENGLISH, "%d", i) + " " +
                           //  R.string.on_this_order);
                          order);

                } else {
                    String save_price = getString(R.string.you_will_save);
                    String order = getString(R.string.on_this_order);
                   textView.setText(save_price + " " + Appearance.appTranslation.getCurrency() + "" + String.format(Locale.ENGLISH, "%.1f", save) + " " + order);
                   // textView.setText(sa+ " " + Appearance.appTranslation.getCurrency() + "" + String.format(Locale.ENGLISH, "%.1f", save) + " " + getString(R.string.on_this_order));
                }
            } else {
                textView.setVisibility(View.GONE);
            }
            TextView setQuantity = (TextView) findViewById(R.id.setquamtity);
            finalTotal = (TextView) findViewById(R.id.finaltotal);

            price = (TextView) findViewById(R.id.setprice);
            total = (TextView) findViewById(R.id.settotal);
            TextView delivery = (TextView) findViewById(R.id.setdelivery);
            gst = 0;
            for (int i = 0; i < jsonArray.length(); i++) {
                double quantity = (jsonArray.getJSONObject(i).getDouble("quantity"));
                System.out.println("asxzsxz" + jsonArray);
                double percentgst = jsonArray.getJSONObject(i).getJSONObject("sku").getJSONObject("product").getJSONObject("gst").getInt("gst");
                double myprice = jsonArray.getJSONObject(i).getJSONObject("sku").getDouble("my_price") * quantity;
                double value = percentgst / 100;

                gst = gst + value * myprice;
            }
            TextView gst1 = (TextView) findViewById(R.id.setgst);
            TextView totalAmountText = (TextView) findViewById(R.id.totalAmountText);
            String total_amt = getString(R.string.total_amount);
            totalAmountText.setText(total_amt);


            System.out.println("sdfxweds" + islanguageLTR());
            if (islanguageLTR()) {

                String price = getString(R.string.price);
                String items = getString(R.string.items);

                setQuantity.setText(price + " (" + jsonArray.length() + " " + items + ")");
            } else {
                String price = getString(R.string.price);
                String items = getString(R.string.items);
                setQuantity.setText(items + " (" + jsonArray.length() + " " + price + ")");

            }
            RelativeLayout gstLayout = (RelativeLayout) findViewById(R.id.gstvisibility);
            if (gst > 0 && gstStatus) {
                gstLayout.setVisibility(View.VISIBLE);
                DecimalFormat decimalFormat = new DecimalFormat("#.#");

                gst1.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.1f", gst));

                total.setText(Appearance.appTranslation.getCurrency() + String.valueOf(sum + gst));
                finalTotal.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.1f", sum + deliviry + gst));
            } else {
                gst = 0;
                gstLayout.setVisibility(View.GONE);
                if (sum % 1 == 0) {
                    int i = (int) Math.round(sum);
                    total.setText(Appearance.appTranslation.getCurrency() + i);
                    finalTotal.setText(Appearance.appTranslation.getCurrency() + i);
                    price.setText(Appearance.appTranslation.getCurrency() + i);
                } else {
                    total.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.1f", sum));
                    finalTotal.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.1f", sum));
                    price.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.1f", sum + deliviry + gst));

                }
            }
            /*if (sum % 1 == 0) {
                int i = (int) Math.round(sum);
                total.setText(colorSettings.getString("currency", "₹") + i);
                finalTotal.setText(colorSettings.getString("currency", "₹") + i);
                price.setText(colorSettings.getString("currency", "₹") + i);
            } else {
                total.setText(colorSettings.getString("currency", "₹") + String.format(Locale.ENGLISH, "%.1f", sum));
                finalTotal.setText(colorSettings.getString("currency", "₹") + String.format(Locale.ENGLISH, "%.1f", sum));

                price.setText(colorSettings.getString("currency", "₹") + String.format(Locale.ENGLISH, "%.1f", sum));
            }*/
            String free = getString(R.string.free);
            delivery.setText(free);


            System.out.println("wesa" + sum + deliviry);
        } catch (JSONException e) {
            e.printStackTrace();
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
    protected void onResume() {
        super.onResume();

        if (shouldExecuteOnResume) {
            getData();
            shouldExecuteOnResume = false;
        }
    }
}