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

import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.AddressAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.GlobalLabel;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.AddressModel;
import com.example.grocery.Model.AreaModel;
import com.example.grocery.Model.CityModel;
import com.example.grocery.Model.CountriesModel;
import com.example.grocery.Model.PincodeModel;
import com.example.grocery.Model.StateModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.grocery.Activities.CartActivity.cartIds;
import static com.example.grocery.Activities.CartActivity.deliviry;
import static com.example.grocery.Activities.CartActivity.gst;
import static com.example.grocery.Activities.CartActivity.sum;
import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.R.drawable.selectedborder;
import static com.example.grocery.interfaces.IConstants.URL_GETDELIVERYDETAILS;
import static com.example.grocery.interfaces.IConstants.URL_placeOrderFromWallet;
import static com.example.grocery.interfaces.IConstants.Url_MinimumOrderAmount;
import static com.example.grocery.utils.GettingCurrentRTLLTR.islanguageLTR;

public class PaymentDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    public AppCompatEditText email_et;
    public AppCompatEditText address_et;
    public AppCompatEditText name_et;
    public AppCompatEditText mobile_et;
    private AppCompatEditText amount_et;
    private AppCompatEditText et_promocode;
    private Button payNowButton;
    private Button cashOnDeliveyButton;
    private int paymentType;
    public Dialog dialog;
    String totals;
    TextView delivery;
    private RelativeLayout bar;
    public static int selectedAddressId;
    private AppCompatEditText addQuantityField;
    private long walletAmountweb;
    private double cashtobepaid;
    private double cashPop;
    private double walletAmountPop;
    private double pricePop;
    private Dialog dialog2;
    private JSONArray jsonArray;
    private String orderType;
    private TextView priceDetailsText;
    private double walletRestPop;
    private TextView selectOtherAddress;
    private int pay_u_money, pay_pal, paytm, c_c_avenue, mobi_kwik;
    private Spinner city;
    private Spinner state;
    private Spinner country, pin;
    private List<CountriesModel> listCountries;
    private List<StateModel> listStates;
    private List<CityModel> listCities;
    private int valueCountry;
    private int valuestate;
    private int valueCity;
    private List<PincodeModel> listPincodes;
    private int valuePincode;
    private ArrayList<String> strings;
    private ArrayList<String> strings2, strings3;
    private int country_id, state_id, city_id, pincode_id;
    private List<AreaModel> listAreas;
    private Spinner area;
    private int valueArea;
    private int area_id;
    public static boolean isshowDialog;
    private AppCompatEditText comment;
    private double discount;
    private CardView promocodeLayout;
    private TextView price;
    private TextView total;
    private TextView gstText;
    private LinearLayout cardView;
    private boolean gstStatus;
    private String email;
    private String password;
    private boolean hideloading;
    public static int fromLogin = 0;
    private String discount_code;
    private int discount_id;
    private double sums;
    private int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());


        isshowDialog = true;
        setContentView(R.layout.activity_payment_details);

        promocodeLayout = findViewById(R.id.promocodeVisibility);
        if (Appearance.appSettings.getIs_promocode() == 1) {
            promocodeLayout.setVisibility(View.VISIBLE);
        }

        TextView currencytext = (TextView) findViewById(R.id.currencytext);
        currencytext.setText(Appearance.appTranslation.getCurrency());
        //labelsShared = getSharedPreferences("labels", MODE_PRIVATE);
        strings = new ArrayList<>();
        strings2 = new ArrayList<>();
        strings3 = new ArrayList<>();
        dialog = new Dialog(this, R.style.MyDialogTheme);
        dialog2 = new Dialog(this, R.style.AlertDialogStyle_Default);
        cardView = (LinearLayout) findViewById(R.id.cartvisibility);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear);
        linearLayout.setOnClickListener(this);
        GradientDrawable drawable = (GradientDrawable) this.getResources().getDrawable(selectedborder);
        drawable.setStroke(3, Color.parseColor("#" + Appearance.appSettings.getText_color()));
                   /* Drawable backgroundDrawable = DrawableCompat.wrap(ProductDetails.this.getResources().getDrawable(R.drawable.selectedborder)).mutate();
                    DrawableCompat.setTint(backgroundDrawable, Color.parseColor("#00FF00"));*/
        linearLayout.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.selectedborder));

        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);

        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        setToolBar();
        initUI();
        getData();
    }


    private void loadAddresses(JSONArray jsonArray) {

        Gson gson = new Gson();
        List<AddressModel> list = Arrays.asList(gson.fromJson(jsonArray.toString(), AddressModel[].class));
        dialog.setContentView(R.layout.newaddresspopup);
        dialog.setCanceledOnTouchOutside(true);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        WindowManager.LayoutParams layoutparameters = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        layoutparameters.copyFrom(window.getAttributes());
        layoutparameters.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutparameters.height = WindowManager.LayoutParams.MATCH_PARENT;

        ImageView location = findViewById(R.id.location);
        location.setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()));

        layoutparameters.gravity = Gravity.BOTTOM;
        window.setAttributes(layoutparameters);

        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recaddress);
        ImageView imageclose = (ImageView) dialog.findViewById(R.id.imageclose);
        TextView selectDeliveryText = (TextView) dialog.findViewById(R.id.selectdeliveryText);
        TextView addnewAddressText = (TextView) dialog.findViewById(R.id.addnewAddressText);
        addnewAddressText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentDetailsActivity.this, AddNewAddreses.class);
                intent.putExtra("country_id", 1);
                intent.putExtra("state_id", 1);
                intent.putExtra("city_id", 1);
                intent.putExtra("pin_id", 1);
                intent.putExtra("name", "");
                intent.putExtra("address", "");
                intent.putExtra("contact_number", "");
                intent.putExtra("address_id", 0);
                intent.putExtra("email", "");
                intent.putExtra("area_id", 1);

                startActivity(intent);
            }
        });
        try {
            String delivery = getString(R.string.select_delivery_address);
            String address = getString(R.string.select_delivery_address);

            selectDeliveryText.setText(delivery);
            addnewAddressText.setText(address);
            addnewAddressText.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        imageclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        AddressAdapter addressAdapter = new AddressAdapter(list, PaymentDetailsActivity.this);
        recyclerView.setAdapter(addressAdapter);
        dialog.show();
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
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", password);
            jsonObject.put("language_id", languageid);

            Log.d("json=====",""+jsonObject);

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
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                findViewById(R.id.whiteloader).setVisibility(View.GONE);
                showCart(response);
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


    private void initUI() {
        selectOtherAddress = (TextView) findViewById(R.id.selectOtherAddress);
        comment = (AppCompatEditText) findViewById(R.id.comment);
        email_et = (AppCompatEditText) findViewById(R.id.email_et);
        new EditTextColorChanger(this, email_et);

        address_et = (AppCompatEditText) findViewById(R.id.address_et);
        new EditTextColorChanger(this, address_et);
        city = (Spinner) findViewById(R.id.citySignUp);
        state = (Spinner) findViewById(R.id.stateSignUp);
        pin = (Spinner) findViewById(R.id.pinsignup);
        area = (Spinner) findViewById(R.id.areaSignUp);

        country = (Spinner) findViewById(R.id.countrySignUp);

        name_et = (AppCompatEditText) findViewById(R.id.name_et);
        new EditTextColorChanger(this, name_et);

        mobile_et = (AppCompatEditText) findViewById(R.id.mobile_et);
        new EditTextColorChanger(this, mobile_et);

        amount_et = (AppCompatEditText) findViewById(R.id.amount_et);

        new EditTextColorChanger(this, amount_et);

        payNowButton = (Button) findViewById(R.id.pay_now_button);

        cashOnDeliveyButton = (Button) findViewById(R.id.cashon);


        try {
            String comments = getString(R.string.any_special_comment_for_this_order);
            String address = getString(R.string.change_delivery_details);
            String pay = getString(R.string.pay_now);
            String delivery = getString(R.string.cash_on_delivery);
            String email = getString(R.string.email);
            String addr = getString(R.string.address);
            String name = getString(R.string.name);
            String mob = getString(R.string.mobile_number);
            String amt = getString(R.string.amount);
            comment.setHint(comments);
            selectOtherAddress.setText(address);
            payNowButton.setText(pay);
            cashOnDeliveyButton.setText(delivery);
            email_et.setHint(email);
            address_et.setHint(addr);
            name_et.setHint(name);
            mobile_et.setHint(mob);
            amount_et.setHint(amt);


        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        payNowButton.setOnClickListener(this);
        payNowButton.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        cashOnDeliveyButton.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        cashOnDeliveyButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        payNowButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        cashOnDeliveyButton.setOnClickListener(this);

        setPromode();
    }


    private void setPromode() {

        et_promocode = (AppCompatEditText) findViewById(R.id.et_promocode);
        new EditTextColorChanger(PaymentDetailsActivity.this, et_promocode);
        String promo = getString(R.string.do_you_have_promocode);
        et_promocode.setHint(promo);
        Button etcheck = (Button) findViewById(R.id.etcheck);
        String add = getString(R.string.add);
        etcheck.setText(add);

        etcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyPromocode(et_promocode.getText().toString());
            }
        });

    }

    private void applyPromocode(String s) {

        final TextView promoMsg = findViewById(R.id.promoMsg);
        final TextView setPromocodeName = findViewById(R.id.setPromocodeName);
        ImageView clearPromocode = findViewById(R.id.clearPromocode);
        clearPromocode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.promocodeLayout).setVisibility(View.GONE);
                sums = sum + x;
                total.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.1f", sums));
                amount_et.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.1f", sums));
                promoMsg.setVisibility(View.GONE);
                findViewById(R.id.promocodeVisibility).setVisibility(View.VISIBLE);

            }
        });

        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String email = prefs.getString("email", "");
        String password = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", password);
            jsonObject.put("language_id", languageid);
            jsonObject.put("promocode", s);
            jsonObject.put("total", Math.round(sums));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("asx" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_APPLYPROMODE, jsonObject, Request.Method.POST);
        System.out.println(IConstants.URL_GETCArt + "?id=" + userid + "&password=" + password);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {

            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                if (!new ResponseHandler().validateResponse(PaymentDetailsActivity.this, response)) {
                    return;
                }


                try {
                    if (response.getJSONObject("data").get("data") != "") {
//                        int minimum_order_amount = response.getJSONObject("data").getInt("min_amount");
                        double minimum_order_amount = 0;
                        double max_discount_amount = 150;
                        double discount = response.getJSONObject("data").getJSONObject("data").getJSONObject("promocodes")
                                .getInt("discount_value");
                        discount_code = response.getJSONObject("data").getJSONObject("data").getJSONObject("promocodes")
                                .getString("discount_code");
                        discount_id = response.getJSONObject("data").getJSONObject("data").getJSONObject("promocodes")
                                .getInt("discount_id");
                        double summ = (int) Math.round(sum);
                        if (summ >= minimum_order_amount) {
                            double discount_amount = summ * (discount / 100);
                            if (discount_amount > max_discount_amount) {
                                discount_amount = max_discount_amount;
                            }
                            summ = summ - discount_amount;
                            total.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.1f", summ));
                            promoMsg.setVisibility(View.VISIBLE);
                            promoMsg.setText(response.getJSONObject("data").getString("msg"));
                            promoMsg.setTextColor(Color.parseColor("#197319"));
                            findViewById(R.id.promocodeLayout).setVisibility(View.VISIBLE);
                            setPromocodeName.setText(discount_code);
                            et_promocode.setText("");
                            amount_et.setText(String.valueOf(sums));
                            findViewById(R.id.promocodeVisibility).setVisibility(View.GONE);


                        }
                    } else {

                        promoMsg.setVisibility(View.VISIBLE);
                        promoMsg.setText(response.getJSONObject("data").getString("msg"));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                findViewById(R.id.whiteloader).setVisibility(View.GONE);
                System.out.println("response" + response);

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


                        Button imageView = (Button) findViewById(R.id.retrybutton);
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }
                }, 2000);
            }
        });
    }

    protected void onResume() {
        super.onResume();

        if (PaymentDetailsActivity.fromLogin == 1) {
            finish();
            PaymentDetailsActivity.fromLogin = 0;
            return;
        }


        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        findViewById(R.id.retryImage).setVisibility(View.GONE);
        new CartCountUtil(PaymentDetailsActivity.this);


        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);
        final JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String email = prefs.getString("email", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("language_id", languageid);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyTask volleyTask = new VolleyTask(this, URL_GETDELIVERYDETAILS, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());


                if (!new ResponseHandler().validateResponse(PaymentDetailsActivity.this, response)) {
                    return;
                }

                try {

                    JSONObject jsonObject3 = response.getJSONObject("data");
                    JSONObject jsonObject7 = response.getJSONObject("data").getJSONObject("data").getJSONObject("user");
                    country_id = jsonObject7.getJSONObject("address").getInt("country_id");
                    state_id = jsonObject7.getJSONObject("address").getInt("state_id");
                    System.out.println("dsxz" + jsonObject7.getJSONObject("address"));
                    city_id = jsonObject7.getJSONObject("address").getInt("city_id");
                    pincode_id = jsonObject7.getJSONObject("address").getInt("pincode_id");
                    area_id = jsonObject7.getJSONObject("address").getInt("area_id");

                    selectedAddressId = response.getJSONObject("data").getJSONObject("data").getJSONObject("user").getInt("address_id");
                    pay_u_money = response.getJSONObject("data").getJSONObject("data").getJSONObject("payment_options").getInt("pay_u_money");
                    pay_pal = response.getJSONObject("data").getJSONObject("data").getJSONObject("payment_options").getInt("pay_pal");
                    paytm = response.getJSONObject("data").getJSONObject("data").getJSONObject("payment_options").getInt("paytm");
                    mobi_kwik = response.getJSONObject("data").getJSONObject("data").getJSONObject("payment_options").getInt("mobi_kwik");
                    c_c_avenue = response.getJSONObject("data").getJSONObject("data").getJSONObject("payment_options").getInt("c_c_avenue");

                    jsonArray = response.getJSONObject("data").getJSONObject("data").getJSONArray("addresses");
                    JSONArray jsonArrayCountries = response.getJSONObject("data").getJSONObject("data").getJSONArray("countries");
                    JSONArray jsonArrayStates = response.getJSONObject("data").getJSONObject("data").getJSONArray("states");
                    JSONArray jsonArrayCity = response.getJSONObject("data").getJSONObject("data").getJSONArray("cities");
                    JSONArray jsonArrayPincode = response.getJSONObject("data").getJSONObject("data").getJSONArray("pincodes");
                    JSONArray jsonArrayareas = response.getJSONObject("data").getJSONObject("data").getJSONArray("areas");

                    getPinCodes(jsonArrayPincode, jsonArrayareas);
                    getCountries(jsonArrayCountries, jsonArrayStates, jsonArrayCity);
                    JSONObject address_setting = response.getJSONObject("data").getJSONObject("data").getJSONObject("address_setting");
                    JSONObject shipping_charges = response.getJSONObject("data").getJSONObject("data").getJSONObject("shipping_charges");
                    int shipping_order_amount =  shipping_charges.getInt("shipping_order_amount");
                    int greater_amount_shipping =  shipping_charges.getInt("greater_amount_shipping");
                    int smaller_amount_shipping =  shipping_charges.getInt("smaller_amount_shipping");
                    double total_amount = sum;
                    int totals = (int)total_amount;

                    if(totals > shipping_order_amount)
                    {
                        x = greater_amount_shipping;
                      delivery.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%d", greater_amount_shipping));
                        sums = sum + x;
                        int i = (int) Math.round(sum);
                        int j = (int) Math.round(sums);
                        total.setText(Appearance.appTranslation.getCurrency() + j);
                    }
                    else
                    {
                        x = smaller_amount_shipping;
                        delivery.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%d", smaller_amount_shipping));
                        sums = sum + x;
                        int i = (int) Math.round(sum);
                        int j = (int) Math.round(sums);
                        total.setText(Appearance.appTranslation.getCurrency() + j);
                    }

                    if (address_setting.getInt("is_country") == 1) {
                        country.setVisibility(View.VISIBLE);
                    } else {
                        getStates(jsonArrayStates, jsonArrayCity);

                    }

                    if (address_setting.getInt("is_state") == 1) {
                        state.setVisibility(View.VISIBLE);
                    } else {
                        getCity(jsonArrayCity);
                    }
                    if (address_setting.getInt("is_city") == 1) {
                        city.setVisibility(View.VISIBLE);
                    }
                  /*  if (address_setting.getInt("is_area") == 1) {
                        area.setVisibility(View.VISIBLE);
                    }*/
                    if (isshowDialog) {
                        loadAddresses(jsonArray);
                    }
                    isshowDialog = false;
                    JSONObject jsonObject2 = jsonObject3.getJSONObject("data").getJSONObject("user");
                    JSONObject jsonObject1 = jsonObject3.getJSONObject("data").getJSONObject("payment_mode");
                    int cash_on_delivery = jsonObject1.getInt("cash_on_delivery");
                    int online_payment = jsonObject1.getInt("online_payment");
                    if (cash_on_delivery == 1) {
                        cashOnDeliveyButton.setVisibility(View.VISIBLE);
                    }
                    if (online_payment == 1) {
                        payNowButton.setVisibility(View.VISIBLE);
                    } else {
                        cashOnDeliveyButton.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                        cashOnDeliveyButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
                    }

                    String name = jsonObject2.getString("name");
                    String number = jsonObject2.getString("contact_number");
                    String address = jsonObject2.getString("address");
                    //String pincode = jsonObject2.getString("pincode");
                    String emailet = jsonObject2.getString("email");
                    //  address_et.setText(address);
                 /*   if (emailet.matches(number) && number.contains("@")) {
                        number = "";
                    }*/
                    name_et.setText(name.equals("null") ? "" : name);
                    mobile_et.setText(number);
                    //pin_et.setText(pincode);
                    email_et.setText(emailet);
                    if ((sum - deliviry + gst) % 1 == 0) {
                        int i = (int) Math.round(sums - deliviry + gst);
                        amount_et.setText(String.valueOf(i));

                    } else {
                        amount_et.setText(String.valueOf(sums - deliviry + gst));
                    }
                    walletAmountweb = response.getJSONObject("data").getJSONObject("data").getJSONObject("user").getLong("wallet");
                    cashtobepaid = (sums - deliviry + gst) - walletAmountweb;
                    PayuActivity.amount = cashtobepaid;
                    RazorPay.amount=cashtobepaid;
                    walletRestPop = walletAmountweb - (sum - deliviry + gst);
                    if (cashtobepaid <= 0) {
                        cashPop = 0;
                    } else {
                        cashPop = cashtobepaid;

                    }
                    pricePop = sums - deliviry + gst;
                    walletAmountPop = walletAmountweb;

                    System.out.println("sdfxweds" + islanguageLTR());
                    // productcount.setText(String.valueOf(Dashboard.cart_count));
                    SharedPreferences.Editor editor = getSharedPreferences("usertable", MODE_PRIVATE).edit();
                    editor.putString("name", name);
                    //  editor.putString("address", address);
                    // editor.putString("pincode", pincode);
                    editor.apply();
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
                                onResume();
                            }
                        });
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.linear:
                loadAddresses(jsonArray);
                break;
            case R.id.pay_now_button:
                SharedPreferences.Editor sharedPreferences1 = getSharedPreferences("paymentdetails", MODE_PRIVATE).edit();
                SharedPreferences prefs1 = getSharedPreferences("UserId", MODE_PRIVATE);
                String userid1 = prefs1.getString("user_id", "");
                String pwd1 = prefs1.getString("pwd", "");
                String languageid1 = prefs1.getString("language", String.valueOf(1));
                JSONObject jsonObject1 = new JSONObject();
                try {
                    jsonObject1.put("business_id", IConstants.BUSINESS_ID);
                    jsonObject1.put("id", userid1);
                    jsonObject1.put("password", pwd1);
                    jsonObject1.put("language_id", languageid1);
                    jsonObject1.put("order_amount", amount_et.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("sdnzfcklxn" + jsonObject1);
                VolleyTask volleyTasks = new VolleyTask(this, Url_MinimumOrderAmount, jsonObject1, Request.Method.POST);
                volleyTasks.setListener(new VolleyTask.IPostTaskListener() {
                    @Override
                    public void fnPostTaskCompleted(JSONArray response) {

                    }

                    @Override
                    public void fnPostTaskCompletedJsonObject(JSONObject response) {
                        System.out.println("hhhs" + response.toString());
                        //  findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                        if (!new ResponseHandler().validateResponse(PaymentDetailsActivity.this, response)) {
                            return;
                        }
                        try {
                            if (name_et.getText().toString().isEmpty()) {
                                name_et.requestFocus();
                                String err = getString(R.string.name_empty);
                                name_et.setError(err);
                                return;
                            } else {
                                name_et.setError(null);
                            }
                            if (email_et.getText().toString().isEmpty()) {
                                email_et.requestFocus();
                                String err = getString(R.string.email_empty);
                                email_et.setError(err);
                                return;
                            } else {
                                email_et.setError(null);
                            }
                            if (mobile_et.getText().toString().isEmpty()) {
                                mobile_et.requestFocus();
                                String err = getString(R.string.contact_number_empty);
                                mobile_et.setError(err);

                                return;
                            } else {
                                mobile_et.setError(null);
                            }
                            if (mobile_et.getText().toString().length() != 10) {
                                mobile_et.requestFocus();
                                String err = getString(R.string.please_enter_a_valid_number);
                                mobile_et.setError(err);
                                return;
                            } else {
                                mobile_et.setError(null);
                            }
                            if (address_et.getText().toString().isEmpty()) {
                                address_et.requestFocus();
                                String err = getString(R.string.address_empty);
                                address_et.setError(err);
                                return;
                            } else if (valueCountry == 0 && country.isShown()) {
                                new CustomToast(PaymentDetailsActivity.this, "Please select country from drop down");
                            } else if (valuestate == 0 && state.isShown()) {
                                new CustomToast(PaymentDetailsActivity.this, "Please select state from drop down");
                            } else if (valueCity == 0 && city.isShown()) {
                                new CustomToast(PaymentDetailsActivity.this, "Please select city from drop down");
                            } else if (pin.getSelectedItemPosition() == 0 && pin.isShown()) {
                                System.out.println("valueCity" + valueCity);
                                String err = getString(R.string.pincode_empty);
                                new CustomToast(PaymentDetailsActivity.this, err);
                            } else {
                                address_et.setError(null);


                                dialog2.setContentView(R.layout.paynowpopup);

                                TextView result = (TextView) dialog2.findViewById(R.id.result);
                                final Button paynow = (Button) dialog2.findViewById(R.id.paywithwallet);
                                TextView resultAmount = (TextView) dialog2.findViewById(R.id.resultamount);
                                TextView wallet_amountnet = (TextView) dialog2.findViewById(R.id.wallet_amountnet);
                                TextView walletAmount = (TextView) dialog2.findViewById(R.id.walletAmount);
                                Button cancel = (Button) dialog2.findViewById(R.id.cancel);
                                final Button paywithoutwallet = (Button) dialog2.findViewById(R.id.paywithoutwallet);
                                paywithoutwallet.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = null;
                                        PaymentTypeActivity.payment_for = "cart_order";
                                        if (pay_u_money == 1 && c_c_avenue == 0 && mobi_kwik == 0 && paytm == 0 && pay_pal == 0) {
                                            intent = new Intent(PaymentDetailsActivity.this, PayuActivity.class);
                                        } else {
                                            intent = new Intent(PaymentDetailsActivity.this, PaymentTypeActivity.class);
                                        }
                                        SharedPreferences.Editor sharedPreferences = getSharedPreferences("paymentdetails", MODE_PRIVATE).edit();
                                        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                                        String userid = prefs.getString("user_id", "");
                                        String pwd = prefs.getString("pwd", "");
                                        String languageid = prefs.getString("language", String.valueOf(1));
                                        JSONObject jsonObject = new JSONObject();
                                        try {
                                            PayuActivity.name = name_et.getText().toString();
                                            PayuActivity.email = email_et.getText().toString();
                                            PayuActivity.mobile = mobile_et.getText().toString();
                                            // PayuActivity.amount = cashtobepaid;
                                            PayuActivity.amount = pricePop;
                                            RazorPay.amount=pricePop;
                                            PayuActivity.address = address_et.getText().toString();
                                            PayuActivity.pin = address_et.getText().toString();


                                            jsonObject.put("name", name_et.getText().toString());
                                            jsonObject.put("email", email_et.getText().toString());
                                            jsonObject.put("mobile", mobile_et.getText().toString());
                                            jsonObject.put("amount", cashtobepaid);
                                            jsonObject.put("address", address_et.getText().toString());
                                            jsonObject.put("id", userid);
                                            jsonObject.put("password", pwd);
                                            jsonObject.put("delivery_address", address_et.getText().toString());
                                            jsonObject.put("order_type", paymentType);
                                            jsonObject.put("cart_ids", cartIds);
                                            jsonObject.put("token", "");
                                            jsonObject.put("address_id", selectedAddressId);
                                            jsonObject.put("language_id", languageid);
                                            jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
                                            jsonObject.put("state_id", valuestate == 0 ? 1 : valuestate);
                                            jsonObject.put("city_id", valueCity == 0 ? 1 : valueCity);
                                            jsonObject.put("area_id", valueArea == 0 ? 1 : valueArea);
                                            jsonObject.put("pincode_id", valuePincode == 0 ? 1 : valuePincode);
                                            jsonObject.put("order_amount", amount_et.getText().toString());
                                            jsonObject.put("order_description", comment.getText().toString());
                                            jsonObject.put("source_id", 2);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                        sharedPreferences.putString("json", jsonObject.toString());
                                        sharedPreferences.apply();


                                        SharedPreferences sharedPreferences1 = getSharedPreferences("paymentdetails", MODE_PRIVATE);
                                        String s = sharedPreferences1.getString("json", "");
                                        Log.i("Rajat", "onClick: " + s);
                                        finish();
                                        startActivity(intent);
                                    }
                                });
                                PayuActivity.name = name_et.getText().toString();
                                PayuActivity.email = email_et.getText().toString();
                                PayuActivity.mobile = mobile_et.getText().toString();
                                PayuActivity.address = address_et.getText().toString();
                                //  PayuActivity.amount = pricePop;
                                PayuActivity.amount = cashtobepaid;
                                RazorPay.amount=cashtobepaid;
                                paynow.setBackground(PaymentDetailsActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                                GradientDrawable bgShape = (GradientDrawable) paynow.getBackground();
                                bgShape.setColor(Color.parseColor("#D3D3D3"));
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
                                    paywithoutwallet.setBackgroundTintList(stateListDrawable);
                                    paywithoutwallet.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
                                } else {
                                    paywithoutwallet.setBackground(PaymentDetailsActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                                    GradientDrawable bgShape1 = (GradientDrawable) paywithoutwallet.getBackground();
                                    bgShape1.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                                    paywithoutwallet.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

                                }
                                cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog2.dismiss();
                                    }
                                });
                                String cancels = getString(R.string.cancel);

                                cancel.setText(cancels);
                                cancel.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));

                                priceDetailsText = (TextView) dialog2.findViewById(R.id.priceDetailsText);
                                String price = getString(R.string.pay_using_wallet);
                                priceDetailsText.setText(price);

                             /*   String pay = getString(R.string.continue_to_pay);
                                paynow.setText(pay + " (" + Appearance.appTranslation.getCurrency() + (pricePop - walletAmountPop) + ")");*/


                                if (cashPop == 0) {
                                    try {
                                        String amt = getString(R.string.order_amount);
                                        result.setText(amt);
                                        resultAmount.setText(Appearance.appTranslation.getCurrency() + pricePop);
                                        String wallet = getString(R.string.wallet);
                                        walletAmount.setText(wallet);
                                        wallet_amountnet.setText(Appearance.appTranslation.getCurrency() + walletAmountPop);
                                        paynow.setText("Place Order With wallet");
                                        String without_wallet = getString(R.string.pay_without_wallet);
                                        paywithoutwallet.setText(without_wallet + " (" + Appearance.appTranslation.getCurrency() + pricePop + ")");
                                    } catch (NullPointerException e) {
                                        e.printStackTrace();
                                    }
                                    orderType = "placeorder";

                                } else {
                                    String amt = getString(R.string.total_amount);
                                    result.setText(amt);
                                    resultAmount.setText(Appearance.appTranslation.getCurrency() + pricePop);
                                    String wallet = getString(R.string.wallet);
                                    walletAmount.setText(wallet);
                                    wallet_amountnet.setText(Appearance.appTranslation.getCurrency() + walletAmountPop);
                                  String proceed = getString(R.string.proceed_to_pay);
                                   orderType = "placeOrderwithWallet";
                                  paynow.setText(proceed + " (" + Appearance.appTranslation.getCurrency() + cashtobepaid + ")");
                                    String wallets = getString(R.string.pay_without_wallet);
                                    paywithoutwallet.setText(wallets + " (" + Appearance.appTranslation.getCurrency() + pricePop + ")");
                                    orderType = "paynow";
                                }
                                if (orderType.matches("placeorder")) {
                                paynow.setVisibility(View.VISIBLE);
                                }
                                paynow.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (orderType.matches("placeorder")) {
                                            dialog2.dismiss();
                                            placeOrder(pricePop, walletAmountPop);
                                        } else {
                                            dialog2.dismiss();
                                           //    placeOrderwithWallet(cashtobepaid);
                                            Intent intent = null;
                                            PaymentTypeActivity.payment_for = "cart_order";
                                            if (pay_u_money == 1 && c_c_avenue == 0 && mobi_kwik == 0 && paytm == 0 && pay_pal == 0) {
                                                intent = new Intent(PaymentDetailsActivity.this, PayuActivity.class);
                                            } else {
                                                intent = new Intent(PaymentDetailsActivity.this, PaymentTypeActivity.class);
                                            }
                                            SharedPreferences.Editor sharedPreferences = getSharedPreferences("paymentdetails", MODE_PRIVATE).edit();
                                            SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                                            String userid = prefs.getString("user_id", "");
                                            String pwd = prefs.getString("pwd", "");
                                            String languageid = prefs.getString("language", String.valueOf(1));
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                PayuActivity.name = name_et.getText().toString();
                                                PayuActivity.email = email_et.getText().toString();
                                                PayuActivity.mobile = mobile_et.getText().toString();
                                                PayuActivity.amount = cashtobepaid;
                                                // PayuActivity.amount =  pricePop;
                                                PayuActivity.address = address_et.getText().toString();
                                                PayuActivity.pin = address_et.getText().toString();

                                                jsonObject.put("name", name_et.getText().toString());
                                                jsonObject.put("email", email_et.getText().toString());
                                                jsonObject.put("mobile", mobile_et.getText().toString());
                                                jsonObject.put("amount", cashtobepaid);
                                                jsonObject.put("address", address_et.getText().toString());
                                                jsonObject.put("id", userid);
                                                jsonObject.put("password", pwd);
                                                jsonObject.put("delivery_address", address_et.getText().toString());
                                                jsonObject.put("order_type", paymentType);
                                                jsonObject.put("cart_ids", cartIds);
                                                jsonObject.put("token", "");
                                                jsonObject.put("address_id", selectedAddressId);
                                                jsonObject.put("language_id", languageid);
                                                jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
                                                jsonObject.put("state_id", valuestate == 0 ? 1 : valuestate);
                                                jsonObject.put("city_id", valueCity == 0 ? 1 : valueCity);
                                                jsonObject.put("area_id", valueArea == 0 ? 1 : valueArea);
                                                jsonObject.put("pincode_id", valuePincode == 0 ? 1 : valuePincode);
                                                jsonObject.put("order_amount", amount_et.getText().toString());
                                                jsonObject.put("order_description", comment.getText().toString());
                                                jsonObject.put("source_id", 2);

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            sharedPreferences.putString("json", jsonObject.toString());
                                            sharedPreferences.apply();
                                            finish();
                                            startActivity(intent);
                                            placeOrderwithWallet(cashtobepaid);
                                        }
                                    }

                                });

                                if (((int) walletAmountPop) == 0 || Appearance.appSettings.getIs_wallet() == 0) {
                                    dialog2.dismiss();
                                    PaymentTypeActivity.payment_for = "cart_order";
                                    Intent intent = null;
                                    if (pay_u_money == 1 && c_c_avenue == 0 && mobi_kwik == 0 && paytm == 0 && pay_pal == 0) {
                                        intent = new Intent(PaymentDetailsActivity.this, PayuActivity.class);
                                    } else {
                                        intent = new Intent(PaymentDetailsActivity.this, PaymentTypeActivity.class);
                                    }
                                    SharedPreferences.Editor sharedPreferences = getSharedPreferences("paymentdetails", MODE_PRIVATE).edit();
                                    SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                                    String userid = prefs.getString("user_id", "");
                                    String pwd = prefs.getString("pwd", "");
                                    String languageid = prefs.getString("language", String.valueOf(1));
                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("name", name_et.getText().toString());
                                        jsonObject.put("email", email_et.getText().toString());
                                        jsonObject.put("mobile", mobile_et.getText().toString());
                                        jsonObject.put("amount", cashtobepaid);
                                        jsonObject.put("address", address_et.getText().toString());
                                        jsonObject.put("id", userid);
                                        jsonObject.put("password", pwd);
                                        jsonObject.put("delivery_address", address_et.getText().toString());
                                        jsonObject.put("order_type", paymentType);
                                        jsonObject.put("cart_ids", cartIds);
                                        jsonObject.put("address_id", selectedAddressId);
                                        jsonObject.put("language_id", languageid);
                                        jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
                                        jsonObject.put("state_id", valuestate == 0 ? 1 : valuestate);
                                        jsonObject.put("city_id", valueCity == 0 ? 1 : valueCity);
                                        jsonObject.put("area_id", valueArea == 0 ? 1 : valueArea);
                                        jsonObject.put("pincode_id", valuePincode == 0 ? 1 : valuePincode);
                                        jsonObject.put("order_amount", amount_et.getText().toString());
                                        jsonObject.put("order_description", comment.getText().toString());
                                        jsonObject.put("source_id", 2);
                                        jsonObject.put("token", "");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    sharedPreferences.putString("json", jsonObject.toString());
                                    sharedPreferences.apply();

                                    SharedPreferences sharedPreferences1 = getSharedPreferences("paymentdetails", MODE_PRIVATE);
                                    String s = sharedPreferences1.getString("json", "");
                                    Log.i("Rajat", "LOG " + s);

                                    JSONObject o = new JSONObject(s);
                                    System.out.println("Rajat JSON" + o);


                                    finish();
                                    startActivity(intent);
                                } else {
                                    dialog2.show();
                                }
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void fnErrorOccurred(String error) {
                        //findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                    }
                });
                break;

              /*  try {
                    if (name_et.getText().toString().isEmpty()) {
                        name_et.requestFocus();
                        String err = getString(R.string.name_empty);
                        name_et.setError(err);
                        return;
                    } else {
                        name_et.setError(null);
                    }
                    if (email_et.getText().toString().isEmpty()) {
                        email_et.requestFocus();
                        String err = getString(R.string.email_empty);
                        email_et.setError(err);
                        return;
                    } else {
                        email_et.setError(null);
                    }
                    if (mobile_et.getText().toString().isEmpty()) {
                        mobile_et.requestFocus();
                        String err = getString(R.string.contact_number_empty);
                        mobile_et.setError(err);

                        return;
                    } else {
                        mobile_et.setError(null);
                    }
                    if (mobile_et.getText().toString().length() != 10) {
                        mobile_et.requestFocus();
                        String err = getString(R.string.please_enter_a_valid_number);
                        mobile_et.setError(err);
                        return;
                    } else {
                        mobile_et.setError(null);
                    }
                    if (address_et.getText().toString().isEmpty()) {
                        address_et.requestFocus();
                        String err = getString(R.string.address_empty);
                        address_et.setError(err);
                        return;
                    } else if (valueCountry == 0 && country.isShown()) {
                        new CustomToast(PaymentDetailsActivity.this, "Please select country from drop down");
                    } else if (valuestate == 0 && state.isShown()) {
                        new CustomToast(PaymentDetailsActivity.this, "Please select state from drop down");
                    } else if (valueCity == 0 && city.isShown()) {
                        new CustomToast(PaymentDetailsActivity.this, "Please select city from drop down");
                    } else if (pin.getSelectedItemPosition() == 0 && pin.isShown()) {
                        System.out.println("valueCity" + valueCity);
                        String err = getString(R.string.pincode_empty);
                        new CustomToast(PaymentDetailsActivity.this, err);
                    } else {
                        address_et.setError(null);


                        dialog2.setContentView(R.layout.paynowpopup);

                        TextView result = (TextView) dialog2.findViewById(R.id.result);
                        Button paynow = (Button) dialog2.findViewById(R.id.paywithwallet);
                        TextView resultAmount = (TextView) dialog2.findViewById(R.id.resultamount);
                        TextView wallet_amountnet = (TextView) dialog2.findViewById(R.id.wallet_amountnet);
                        TextView walletAmount = (TextView) dialog2.findViewById(R.id.walletAmount);
                        Button cancel = (Button) dialog2.findViewById(R.id.cancel);
                        final Button paywithoutwallet = (Button) dialog2.findViewById(R.id.paywithoutwallet);
                        paywithoutwallet.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = null;
                                PaymentTypeActivity.payment_for = "cart_order";
                                if (pay_u_money == 1 && c_c_avenue == 0 && mobi_kwik == 0 && paytm == 0 && pay_pal == 0) {
                                    intent = new Intent(PaymentDetailsActivity.this, PayuActivity.class);
                                } else {
                                    intent = new Intent(PaymentDetailsActivity.this, PaymentTypeActivity.class);
                                }
                                SharedPreferences.Editor sharedPreferences = getSharedPreferences("paymentdetails", MODE_PRIVATE).edit();
                                SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                                String userid = prefs.getString("user_id", "");
                                String pwd = prefs.getString("pwd", "");
                                String languageid = prefs.getString("language", String.valueOf(1));
                                JSONObject jsonObject = new JSONObject();
                                try {
                                    PayuActivity.name = name_et.getText().toString();
                                    PayuActivity.email = email_et.getText().toString();
                                    PayuActivity.mobile = mobile_et.getText().toString();
                                    // PayuActivity.amount = cashtobepaid;
                                    PayuActivity.amount = pricePop;
                                    PayuActivity.address = address_et.getText().toString();
                                    PayuActivity.pin = address_et.getText().toString();


                                    jsonObject.put("name", name_et.getText().toString());
                                    jsonObject.put("email", email_et.getText().toString());
                                    jsonObject.put("mobile", mobile_et.getText().toString());
                                    jsonObject.put("amount", cashtobepaid);
                                    jsonObject.put("address", address_et.getText().toString());
                                    jsonObject.put("id", userid);
                                    jsonObject.put("password", pwd);
                                    jsonObject.put("delivery_address", address_et.getText().toString());
                                    jsonObject.put("order_type", paymentType);
                                    jsonObject.put("cart_ids", cartIds);
                                    jsonObject.put("token", "");
                                    jsonObject.put("address_id", selectedAddressId);
                                    jsonObject.put("language_id", languageid);
                                    jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
                                    jsonObject.put("state_id", valuestate == 0 ? 1 : valuestate);
                                    jsonObject.put("city_id", valueCity == 0 ? 1 : valueCity);
                                    jsonObject.put("area_id", valueArea == 0 ? 1 : valueArea);
                                    jsonObject.put("pincode_id", valuePincode == 0 ? 1 : valuePincode);
                                    jsonObject.put("order_amount", amount_et.getText().toString());
                                    jsonObject.put("order_description", comment.getText().toString());
                                    jsonObject.put("source_id", 2);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                sharedPreferences.putString("json", jsonObject.toString());
                                sharedPreferences.apply();


                                SharedPreferences sharedPreferences1 = getSharedPreferences("paymentdetails", MODE_PRIVATE);
                                String s = sharedPreferences1.getString("json", "");
                                Log.i("Rajat", "onClick: " + s);
                                finish();
                                startActivity(intent);
                            }
                        });
                        PayuActivity.name = name_et.getText().toString();
                        PayuActivity.email = email_et.getText().toString();
                        PayuActivity.mobile = mobile_et.getText().toString();
                        PayuActivity.address = address_et.getText().toString();
                        //  PayuActivity.amount = pricePop;
                        PayuActivity.amount = cashtobepaid;
                        paynow.setBackground(PaymentDetailsActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                        GradientDrawable bgShape = (GradientDrawable) paynow.getBackground();
                        bgShape.setColor(Color.parseColor("#D3D3D3"));
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
                            paywithoutwallet.setBackgroundTintList(stateListDrawable);
                            paywithoutwallet.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
                        } else {
                            paywithoutwallet.setBackground(PaymentDetailsActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                            GradientDrawable bgShape1 = (GradientDrawable) paywithoutwallet.getBackground();
                            bgShape1.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                            paywithoutwallet.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

                        }
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog2.dismiss();
                            }
                        });
                        String cancels = getString(R.string.cancel);

                        cancel.setText(cancels);
                        cancel.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));

                        priceDetailsText = (TextView) dialog2.findViewById(R.id.priceDetailsText);
                        String price = getString(R.string.pay_using_wallet);
                        priceDetailsText.setText(price);

                        String pay = getString(R.string.continue_to_pay);
                        paynow.setText(pay + " (" + Appearance.appTranslation.getCurrency() + (pricePop - walletAmountPop) + ")");


                        if (cashPop == 0) {

                            try {
                                String amt = getString(R.string.order_amount);
                                result.setText(amt);
                                resultAmount.setText(Appearance.appTranslation.getCurrency() + pricePop);
                                String wallet = getString(R.string.wallet);
                                walletAmount.setText(wallet);
                                wallet_amountnet.setText(Appearance.appTranslation.getCurrency() + walletAmountPop);
                                paynow.setText("Place Order With wallet");
                                String without_wallet = getString(R.string.pay_without_wallet);
                                paywithoutwallet.setText(without_wallet + " (" + Appearance.appTranslation.getCurrency() + pricePop + ")");
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                            orderType = "placeorder";

                        } else {
                            String amt = getString(R.string.total_amount);
                            result.setText(amt);
                            resultAmount.setText(Appearance.appTranslation.getCurrency() + pricePop);
                            String wallet = getString(R.string.wallet);
                            walletAmount.setText(wallet);
                            wallet_amountnet.setText(Appearance.appTranslation.getCurrency() + walletAmountPop);
                            String proceed = getString(R.string.proceed_to_pay);
                            orderType = "placeOrderwithWallet";
                            paynow.setText(proceed + " (" + Appearance.appTranslation.getCurrency() + cashtobepaid + ")");
                            String wallets = getString(R.string.pay_without_wallet);
                            paywithoutwallet.setText(wallets + " (" + Appearance.appTranslation.getCurrency() + pricePop + ")");
                            orderType = "paynow";
                        }
                        paynow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (orderType.matches("placeorder")) {
                                    dialog2.dismiss();
                                    placeOrder(pricePop, walletAmountPop);
                                } else {
                                    dialog2.dismiss();
                                    //   placeOrderwithWallet(cashtobepaid);
                                    Intent intent = null;
                                    PaymentTypeActivity.payment_for = "cart_order";
                                    if (pay_u_money == 1 && c_c_avenue == 0 && mobi_kwik == 0 && paytm == 0 && pay_pal == 0) {
                                        intent = new Intent(PaymentDetailsActivity.this, PayuActivity.class);
                                    } else {
                                        intent = new Intent(PaymentDetailsActivity.this, PaymentTypeActivity.class);
                                    }
                                    SharedPreferences.Editor sharedPreferences = getSharedPreferences("paymentdetails", MODE_PRIVATE).edit();
                                    SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                                    String userid = prefs.getString("user_id", "");
                                    String pwd = prefs.getString("pwd", "");
                                    String languageid = prefs.getString("language", String.valueOf(1));
                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        PayuActivity.name = name_et.getText().toString();
                                        PayuActivity.email = email_et.getText().toString();
                                        PayuActivity.mobile = mobile_et.getText().toString();
                                        PayuActivity.amount = cashtobepaid;
                                        // PayuActivity.amount =  pricePop;
                                        PayuActivity.address = address_et.getText().toString();
                                        PayuActivity.pin = address_et.getText().toString();

                                        jsonObject.put("name", name_et.getText().toString());
                                        jsonObject.put("email", email_et.getText().toString());
                                        jsonObject.put("mobile", mobile_et.getText().toString());
                                        jsonObject.put("amount", cashtobepaid);
                                        jsonObject.put("address", address_et.getText().toString());
                                        jsonObject.put("id", userid);
                                        jsonObject.put("password", pwd);
                                        jsonObject.put("delivery_address", address_et.getText().toString());
                                        jsonObject.put("order_type", paymentType);
                                        jsonObject.put("cart_ids", cartIds);
                                        jsonObject.put("token", "");
                                        jsonObject.put("address_id", selectedAddressId);
                                        jsonObject.put("language_id", languageid);
                                        jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
                                        jsonObject.put("state_id", valuestate == 0 ? 1 : valuestate);
                                        jsonObject.put("city_id", valueCity == 0 ? 1 : valueCity);
                                        jsonObject.put("area_id", valueArea == 0 ? 1 : valueArea);
                                        jsonObject.put("pincode_id", valuePincode == 0 ? 1 : valuePincode);
                                        jsonObject.put("order_amount",   .getText().toString());
                                        jsonObject.put("order_description", comment.getText().toString());
                                        jsonObject.put("source_id", 2);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    sharedPreferences.putString("json", jsonObject.toString());
                                    sharedPreferences.apply();
                                    finish();
                                    startActivity(intent);
                                }
                            }
                        });

                        if (((int) walletAmountPop) == 0 || Appearance.appSettings.getIs_wallet() == 0) {
                            dialog2.dismiss();
                            PaymentTypeActivity.payment_for = "cart_order";
                            Intent intent = null;
                            if (pay_u_money == 1 && c_c_avenue == 0 && mobi_kwik == 0 && paytm == 0 && pay_pal == 0) {
                                intent = new Intent(PaymentDetailsActivity.this, PayuActivity.class);
                            } else {
                                intent = new Intent(PaymentDetailsActivity.this, PaymentTypeActivity.class);
                            }
                            SharedPreferences.Editor sharedPreferences = getSharedPreferences("paymentdetails", MODE_PRIVATE).edit();
                            SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                            String userid = prefs.getString("user_id", "");
                            String pwd = prefs.getString("pwd", "");
                            String languageid = prefs.getString("language", String.valueOf(1));
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("name", name_et.getText().toString());
                                jsonObject.put("email", email_et.getText().toString());
                                jsonObject.put("mobile", mobile_et.getText().toString());
                                jsonObject.put("amount", cashtobepaid);
                                jsonObject.put("address", address_et.getText().toString());
                                jsonObject.put("id", userid);
                                jsonObject.put("password", pwd);
                                jsonObject.put("delivery_address", address_et.getText().toString());
                                jsonObject.put("order_type", paymentType);
                                jsonObject.put("cart_ids", cartIds);
                                jsonObject.put("address_id", selectedAddressId);
                                jsonObject.put("language_id", languageid);
                                jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
                                jsonObject.put("state_id", valuestate == 0 ? 1 : valuestate);
                                jsonObject.put("city_id", valueCity == 0 ? 1 : valueCity);
                                jsonObject.put("area_id", valueArea == 0 ? 1 : valueArea);
                                jsonObject.put("pincode_id", valuePincode == 0 ? 1 : valuePincode);
                                jsonObject.put("order_amount", amount_et.getText().toString());
                                jsonObject.put("order_description", comment.getText().toString());
                                jsonObject.put("source_id", 2);
                                jsonObject.put("token", "");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            sharedPreferences.putString("json", jsonObject.toString());
                            sharedPreferences.apply();

                            SharedPreferences sharedPreferences1 = getSharedPreferences("paymentdetails", MODE_PRIVATE);
                            String s = sharedPreferences1.getString("json", "");
                            Log.i("Rajat", "LOG " + s);

                            JSONObject o = new JSONObject(s);
                            System.out.println("Rajat JSON" + o);


                            finish();
                            startActivity(intent);
                        } else {
                            dialog2.show();
                        }
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
*/

            case R.id.cashon:
                SharedPreferences.Editor sharedPreferences = getSharedPreferences("paymentdetails", MODE_PRIVATE).edit();
                SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                String userid = prefs.getString("user_id", "");
                String pwd = prefs.getString("pwd", "");
                String languageid = prefs.getString("language", String.valueOf(1));
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("business_id", IConstants.BUSINESS_ID);
                    jsonObject.put("id", userid);
                    jsonObject.put("password", pwd);
                    jsonObject.put("language_id", languageid);
                    jsonObject.put("order_amount", amount_et.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                     }
                System.out.println("sdnzfcklxn" + jsonObject);
                VolleyTask volleyTask = new VolleyTask(this, Url_MinimumOrderAmount, jsonObject, Request.Method.POST);
                volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                    @Override
                    public void fnPostTaskCompleted(JSONArray response) {

                    }

                    @Override
                    public void fnPostTaskCompletedJsonObject(JSONObject response) {
                        System.out.println("hhhs" + response.toString());
                      //  findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                        if (!new ResponseHandler().validateResponse(PaymentDetailsActivity.this, response)) {
                            return;
                        }
                        final Dialog dialog = new Dialog(PaymentDetailsActivity.this, R.style.AlertDialogStyle_Default);
                        dialog.setContentView(R.layout.cashondeliverypopup);
                        Button dialogOkButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                        TextView codmessege = (TextView) dialog.findViewById(R.id.codmessege);
                        TextView codTitle = (TextView) dialog.findViewById(R.id.codTitle);
                        Button dialogCancelButton = (Button) dialog.findViewById(R.id.canceldialogue);
                        try {
                            String no = getString(R.string.no);
                            String yes = getString(R.string.yes);
                            String confirm = getString(R.string.confirm_your_order);
                            String cash = getString(R.string.cash_on_delivery);
                            dialogCancelButton.setText(no);
                            dialogOkButton.setText(yes);
                            codmessege.setText(confirm);
                            codTitle.setText(cash);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        dialogCancelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialogOkButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                        dialogOkButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog.dismiss();
                                paymentType = 1;
                                // System.out.println(colorSettings.getInt("min_order_amount", 100) + "fcnsc" + Integer.parseInt(amount_et.getText().toString()));
                                //   if (Integer.parseInt(amount_et.getText().toString()) >= colorSettings.getInt("min_order_amount", 100)) {
                                findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
                                doPayment();


                            }
                        });

                        try {
                            if (name_et.getText().toString().isEmpty()) {
                                name_et.requestFocus();
                                String err = getString(R.string.name_empty);
                                name_et.setError(err);

                            } else if (email_et.getText().toString().isEmpty()) {
                                email_et.requestFocus();
                                String err = getString(R.string.email_empty);
                                email_et.setError(err);

                            } else if (mobile_et.getText().toString().isEmpty()) {
                                mobile_et.requestFocus();
                                String err = getString(R.string.contact_number_empty);
                                mobile_et.setError(err);


                            } else if (mobile_et.getText().toString().length() != 10) {
                                mobile_et.requestFocus();
                                String err = getString(R.string.please_enter_a_valid_number);
                                mobile_et.setError(err);


                            } else if (address_et.getText().toString().isEmpty()) {
                                address_et.requestFocus();
                                String err = getString(R.string.address_empty);
                                address_et.setError(err);


                            } else if (valueCountry == 0 && country.isShown()) {
                                new CustomToast(PaymentDetailsActivity.this, "Please select country from drop down");
                            } else if (valuestate == 0 && state.isShown()) {
                                new CustomToast(PaymentDetailsActivity.this, "Please select state from drop down");
                            } else if (valueCity == 0 && city.isShown()) {
                                new CustomToast(PaymentDetailsActivity.this, "Please select city from drop down");
                            } else if (pin.getSelectedItemPosition() == 0 && pin.isShown()) {
                                System.out.println("valueCity" + valueCity);
                                String pin = getString(R.string.pincode_empty);

                                new CustomToast(PaymentDetailsActivity.this, pin);
                            } else {
                                dialog.show();
                            }
                        }catch (NullPointerException e)
                        {
                           e.printStackTrace();
                        }
                    }



                @Override
                    public void fnErrorOccurred(String error) {
                        //findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                    }
                });
        }

               /* final Dialog dialog = new Dialog(PaymentDetailsActivity.this, R.style.AlertDialogStyle_Default);
                dialog.setContentView(R.layout.cashondeliverypopup);
                Button dialogOkButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                TextView codmessege = (TextView) dialog.findViewById(R.id.codmessege);
                TextView codTitle = (TextView) dialog.findViewById(R.id.codTitle);
                Button dialogCancelButton = (Button) dialog.findViewById(R.id.canceldialogue);
                try {
                    String no = getString(R.string.no);
                    String yes = getString(R.string.yes);
                    String confirm = getString(R.string.confirm_your_order);
                    String cash = getString(R.string.cash_on_delivery);
                    dialogCancelButton.setText(no);
                    dialogOkButton.setText(yes);
                    codmessege.setText(confirm);
                    codTitle.setText(cash);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                dialogCancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialogOkButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                dialogOkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                        paymentType = 1;
                        // System.out.println(colorSettings.getInt("min_order_amount", 100) + "fcnsc" + Integer.parseInt(amount_et.getText().toString()));
                        //   if (Integer.parseInt(amount_et.getText().toString()) >= colorSettings.getInt("min_order_amount", 100)) {
                        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);

                        doPayment();
                       *//* } else {
                            Toast.makeText(PaymentDetailsActivity.this, "Sorry you must order atleast for " + colorSettings.getInt("min_order_amount", 100), Toast.LENGTH_SHORT).show();
                        }*//*


                    }
                });
                try {
                    if (name_et.getText().toString().isEmpty()) {
                        name_et.requestFocus();
                        String err = getString(R.string.name_empty);
                        name_et.setError(err);

                    } else if (email_et.getText().toString().isEmpty()) {
                        email_et.requestFocus();
                        String err = getString(R.string.email_empty);
                        email_et.setError(err);

                    } else if (mobile_et.getText().toString().isEmpty()) {
                        mobile_et.requestFocus();
                        String err = getString(R.string.contact_number_empty);
                        mobile_et.setError(err);


                    } else if (mobile_et.getText().toString().length() != 10) {
                        mobile_et.requestFocus();
                        String err = getString(R.string.please_enter_a_valid_number);
                        mobile_et.setError(err);


                    } else if (address_et.getText().toString().isEmpty()) {
                        address_et.requestFocus();
                        String err = getString(R.string.address_empty);
                        address_et.setError(err);



                    } else if (valueCountry == 0 && country.isShown()) {
                        new CustomToast(PaymentDetailsActivity.this, "Please select country from drop down");
                    } else if (valuestate == 0 && state.isShown()) {
                        new CustomToast(PaymentDetailsActivity.this, "Please select state from drop down");
                    } else if (valueCity == 0 && city.isShown()) {
                        new CustomToast(PaymentDetailsActivity.this, "Please select city from drop down");
                    } else if (pin.getSelectedItemPosition() == 0 && pin.isShown()) {
                        System.out.println("valueCity" + valueCity);
                        String pin = getString(R.string.pincode_empty);

                        new CustomToast(PaymentDetailsActivity.this, pin);
                    } else {
                        dialog.show();
                    }


                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
        }
    */
    }

    private void getinfo() {
        int wallet = Appearance.appSettings.getIs_wallet();
        if(wallet == 1) {
            dialog2.show(); }
        else {
            dialog2.cancel(); }

    }
    public void showCart(JSONObject response) {

        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("asddd" + response);
        //  findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

        try {
            ResponseHandler responseHandler = new ResponseHandler();
            if (!responseHandler.validateResponse(PaymentDetailsActivity.this, response)) {
                return;
            }

            JSONObject jsonObject1 = response.getJSONObject("data");

            int gstNumber = Appearance.appSettings.getIs_gst();

            if (gstNumber == 1) {
                gstStatus = true;
            } else {
                gstStatus = false;
            }

            JSONArray jsonArray = jsonObject1.getJSONObject("data").getJSONArray("products");

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
                if (save % 1 == 0) {
                    int i = (int) Math.round(save);
                    String saves = getString(R.string.you_will_save);
                    String order = getString(R.string.on_this_order);
                    textView.setText(saves + " " +
                            Appearance.appTranslation.getCurrency() + "" +
                            String.format(Locale.ENGLISH, "%d", i) + " " +
                            order);

                } else {
                    String saves = getString(R.string.you_will_save);
                    String order = getString(R.string.on_this_order);
                    textView.setText(saves + " " + Appearance.appTranslation.getCurrency() + "" + String.format(Locale.ENGLISH, "%.1f", save) + " " + order);
                }
            } else {
                textView.setVisibility(View.GONE);
            }

            TextView setQuantity = (TextView) findViewById(R.id.setquamtity);

            price = (TextView) findViewById(R.id.setprice);
            total = (TextView) findViewById(R.id.settotal);
            delivery = (TextView) findViewById(R.id.setdelivery);

            if (Appearance.appSettings.getIs_promocode() == 1) {
                //promocodeLayout.setVisibility(View.VISIBLE);
            }

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
            TextView shakingtext = (TextView) findViewById(R.id.shakingtext);
            String prices = getString(R.string.price_details);
            String deliver = getString(R.string.delivery);
            shakingtext.setText(prices);
            TextView deliveryText = (TextView) findViewById(R.id.deliveryText);
            deliveryText.setText(deliver);
         //   int gst = Integer.parseInt(getString(R.string.gst));
            String gst = getString(R.string.gst);
             totals = getString(R.string.total_amount);
            TextView gstText = (TextView) findViewById(R.id.gstText);
            gstText.setText(gst);
            TextView totalAmountText = (TextView) findViewById(R.id.totalAmountText);
            totalAmountText.setText(totals);
            System.out.println("sdfxweds" + islanguageLTR());
            if (islanguageLTR()) {
                String amt = getString(R.string.price);
                String item = getString(R.string.items);
                setQuantity.setText(amt +
                        " (" + jsonArray.length() + " " +item + ")");
            } else {
                String amt = getString(R.string.price);
                String item = getString(R.string.items);
                setQuantity.setText("(" + item + " " + jsonArray.length() + ")"
                        +amt);
            }
            RelativeLayout gstLayout = (RelativeLayout) findViewById(R.id.gstvisibility);
           // if ( gst > 0 && gstStatus) {
            if (gstStatus) {
                gstLayout.setVisibility(View.VISIBLE);
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
               // gst1.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.1f", gst));
                sums = sum + x;
                total.setText(Appearance.appTranslation.getCurrency() + String.valueOf(sums + gst));
            } else {
            //    gst = 0;
                gstLayout.setVisibility(View.GONE);
                if (sum % 1 == 0) {
                    sums = sum + x;
                    int i = (int) Math.round(sum);
                    int j = (int) Math.round(sums);
                    total.setText(Appearance.appTranslation.getCurrency() + j);
                    price.setText(Appearance.appTranslation.getCurrency() + i);
                } else {
                    sums = sum + x;
                    total.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.1f", sums));
                //    price.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.1f", sum + deliviry + gst));
                }
            }

            /*if (sum % 1 == 0) {
                int i = (int) Math.round(sum);
                total.setText(Appearance.appTranslation.getCurrency() + i);
                finalTotal.setText(Appearance.appTranslation.getCurrency() + i);
                price.setText(Appearance.appTranslation.getCurrency() + i);
            } else {
                total.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.1f", sum));
                finalTotal.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.1f", sum));

                price.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.1f", sum));
            }*/
          //  String free = getString(R.string.free);
          //  delivery.setText(free);
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
//                            getData();
                        }
                    });
                }
            }, 2000);
        }
    }


    private void doPayment() {

        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));
        JSONObject jsonObject = new JSONObject();

        Log.i("id", userid);
        Log.i("password", pwd);
        Log.i("delivery_address", address_et.getText().toString());
        Log.i("order_type", "" + paymentType);
        Log.i("cart_ids", "" + cartIds);
        Log.i("address_id", "" + selectedAddressId);
        Log.i("language_id", "" + languageid);
        Log.i("country_id", "" + valueCountry);
        Log.i("state_id", "" + valuestate);
        Log.i("city_id", "" + valueCity);
        Log.i("area_id", "" + valueArea);
        Log.i("pincode_id", "" + valuePincode);
        Log.i("order_amount", amount_et.getText().toString());
        Log.i("order_description", comment.getText().toString());

        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("name", name_et.getText().toString());
            jsonObject.put("email", email_et.getText().toString());
            jsonObject.put("mobile", mobile_et.getText().toString());
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("delivery_address", address_et.getText().toString());
            jsonObject.put("order_type", paymentType);
            jsonObject.put("cart_ids", cartIds);
            jsonObject.put("address_id", selectedAddressId);
            jsonObject.put("language_id", languageid);
            jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
            jsonObject.put("state_id", valuestate == 0 ? 1 : valuestate);
            jsonObject.put("city_id", valueCity == 0 ? 1 : valueCity);
            jsonObject.put("area_id", valueArea == 0 ? 1 : valueArea);
            jsonObject.put("pincode_id", valuePincode == 0 ? 1 : valuePincode);
            jsonObject.put("order_amount", amount_et.getText().toString());
            jsonObject.put("order_description", comment.getText().toString());
            jsonObject.put("source_id", 2);
            jsonObject.put("discount_code",discount_code);
            jsonObject.put("discount_id",discount_id);
            jsonObject.put("token", "");



        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("ffffsdcexx" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(PaymentDetailsActivity.this, IConstants.URL_SUBMITCArt, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {


            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
               // Toast.makeText(PaymentDetailsActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                if (!new ResponseHandler().validateResponse(PaymentDetailsActivity.this, response)) {
                    Intent intent = new Intent(PaymentDetailsActivity.this, SuccessOrder.class);
                    startActivity(intent);
                    finish();
                    return;
                }

                try {
                  //  String ms=response.getJSONObject("data").getString("msg");
                   new CustomToast(PaymentDetailsActivity.this, response.getJSONObject("data").getString("msg"));
                    Dashboard.cart_count = 0;
                    Intent intent = new Intent(PaymentDetailsActivity.this, SuccessOrder.class);
                    startActivity(intent);
                    finish();
                } catch (JSONException e) {
                }

            }

            @Override
            public void fnErrorOccurred(String error) {
                new CustomToast(PaymentDetailsActivity.this, "No connection Available");
            }
        });


    }

    private void setToolBar() {
        TextView cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
        if (Dashboard.cart_count != 0) {
            cartCount.setVisibility(View.VISIBLE);
            cartCount.setText(String.valueOf(Dashboard.cart_count));
        }
        RelativeLayout toolbarNotification = (RelativeLayout) findViewById(R.id.toolnotification);
        toolbarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentDetailsActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        if (Appearance.appSettings.getIs_notification() == 0) {
            toolbarNotification.setVisibility(View.GONE);

        }
        RelativeLayout toolBarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolBarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentDetailsActivity.this, SearchActivity.class);
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
        RelativeLayout toolBarCart = (RelativeLayout) findViewById(R.id.cart);
        toolBarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentDetailsActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout toolBarBack = (RelativeLayout) findViewById(R.id.toolimage);

        toolBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView toolBarTitle = (TextView) findViewById(R.id.titlebar);
        try {

            String title = getString(R.string.delivery_details);
            toolBarTitle.setText(title);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    private void placeOrder(double price, double wallet) {
        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("id", userid);
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("language_id", languageid);
            jsonObject.put("password", pwd);
            jsonObject.put("email", email_et.getText().toString());
            jsonObject.put("token", "");
            jsonObject.put("source_id", 1);
            jsonObject.put("order_amount", amount_et.getText().toString());
            jsonObject.put("mobile", mobile_et.getText().toString());
            jsonObject.put("order_type", 3);
            jsonObject.put("order_description", comment.getText().toString());
            jsonObject.put("delivery_address", address_et.getText().toString());
            jsonObject.put("name", name_et.getText().toString());
            jsonObject.put("pincode_id", valuePincode == 0 ? 1 : valuePincode);
            jsonObject.put("cart_ids", cartIds);
            jsonObject.put("wallet_amount", price);
            jsonObject.put("city_id", valueCity == 0 ? 1 : valueCity);
            jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
            jsonObject.put("state_id", valuestate == 0 ? 1 : valuestate);
            jsonObject.put("area_id", valueArea == 0 ? 1 : valueArea);
            jsonObject.put("address_id", selectedAddressId);

           // jsonObject.put("cart_ids", 53);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("sdnzfcklxn",""+jsonObject);
        System.out.println("sdnzfcklxn" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, URL_placeOrderFromWallet, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                if (!new ResponseHandler().validateResponse(PaymentDetailsActivity.this, response)) {
                    Intent intent = new Intent(PaymentDetailsActivity.this, FailureOrder.class);
                    finish();
                    startActivity(intent);
                }

                try {
                   new CustomToast(PaymentDetailsActivity.this, response.getJSONObject("data").getString("msg"));
                    Intent intent = new Intent(PaymentDetailsActivity.this, SuccessOrder.class);
                    finish();
                    startActivity(intent);

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


    private void placeOrderwithWallet(double price) {
      //  findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("id", userid);
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("language_id", languageid);
            jsonObject.put("password", pwd);
            jsonObject.put("email", email_et.getText().toString());
            jsonObject.put("token", "");
            jsonObject.put("source_id", 1);
            jsonObject.put("order_amount", amount_et.getText().toString());
            jsonObject.put("mobile", mobile_et.getText().toString());
            jsonObject.put("order_type", 3);
            jsonObject.put("order_description", comment.getText().toString());
            jsonObject.put("delivery_address", address_et.getText().toString());
            jsonObject.put("name", name_et.getText().toString());
            jsonObject.put("pincode_id", valuePincode == 0 ? 1 : valuePincode);
            jsonObject.put("cart_ids", cartIds);
            jsonObject.put("wallet_amount", price);
            jsonObject.put("city_id", valueCity == 0 ? 1 : valueCity);
            jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
            jsonObject.put("state_id", valuestate == 0 ? 1 : valuestate);
            jsonObject.put("area_id", valueArea == 0 ? 1 : valueArea);
            jsonObject.put("address_id", selectedAddressId);
         //   Toast.makeText(this, ""+, Toast.LENGTH_SHORT).show();
            // jsonObject.put("cart_ids", 53);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("sdnzfcklxn" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, URL_placeOrderFromWallet, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                if (!new ResponseHandler().validateResponse(PaymentDetailsActivity.this, response)) {
                    Intent intent = new Intent(PaymentDetailsActivity.this, FailureOrder.class);
                    finish();
                    startActivity(intent);

                }
                try {
                   new CustomToast(PaymentDetailsActivity.this, response.getJSONObject("data").getString("msg"));
                    //String msg =response.getJSONObject("data").getString("msg");
                    Intent intent = new Intent(PaymentDetailsActivity.this, SuccessOrder.class);
                    finish();
                    startActivity(intent);

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


    private void getArea(JSONArray jsonArrayarea) {

        Gson gson = new Gson();
        listAreas = Arrays.asList(gson.fromJson(jsonArrayarea.toString(), AreaModel[].class));

        if (listAreas.size() == 0) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add("Select Area");
            final ArrayAdapter adapter10 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, strings);
            valueArea = 0;
            area.setAdapter(adapter10);
        } else {
            System.out.println("dsxz" + listAreas.size());
            ArrayList<String> strings = new ArrayList<>();
            strings.add("Select Area");
            for (int j = 0; j < listAreas.size(); j++) {

                if (listAreas.get(j).getArea_translation() == null) {
                    strings.add(listAreas.get(j).getDefault_area_translation().getArea());
                } else {
                    strings.add(listAreas.get(j).getArea_translation().getArea());
                }
                System.out.println("areasd" + strings);
            }

            final ArrayAdapter adapter10 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, strings);
            area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == 0) {
                        valueArea = 0;
                    } else {
                        valueArea = listAreas.get(i - 1).getArea_id();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            area.setAdapter(adapter10);
        }
    }

    private void getPinCodes(JSONArray jsonArrayPincode, JSONArray jsonArrayareas) {
        Gson gson = new Gson();
        listPincodes = Arrays.asList(gson.fromJson(jsonArrayPincode.toString(), PincodeModel[].class));
        listAreas = Arrays.asList(gson.fromJson(jsonArrayareas.toString(), AreaModel[].class));

        final ArrayList<String> pincodeArray = new ArrayList<>();
        pincodeArray.add("Select PinCode");
        for (int i = 0; i < listPincodes.size(); i++) {
            pincodeArray.add(listPincodes.get(i).getPincode());
        }

        pin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {

                    valuePincode = listPincodes.get(i - 1).getPincode_id();
                    final List<AreaModel> areaModels = new ArrayList<>();
                    for (int j = 0; j < listAreas.size(); j++) {
                        if (listAreas.get(j).getPincode_id() == valuePincode) {
                            areaModels.add(listAreas.get(j));
                        }
                    }
                    if (areaModels.size() == 0) {
                        ArrayList<String> strings = new ArrayList<>();
                        strings.add("Select Area");
                        final ArrayAdapter adapter10 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, strings);
                        valueArea = 0;
                        area.setAdapter(adapter10);
                    } else {
                        System.out.println("dsxz" + areaModels.size());
                        ArrayList<String> strings = new ArrayList<>();
                        for (int j = 0; j < areaModels.size(); j++) {

                            if (areaModels.get(j).getArea_translation() == null) {
                                strings.add(areaModels.get(j).getDefault_area_translation().getArea());
                            } else {
                                strings.add(areaModels.get(j).getArea_translation().getArea());
                            }
                            System.out.println("areasd" + strings);
                        }
                        final ArrayAdapter adapter10 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, strings);
                        area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                valueArea = areaModels.get(i).getArea_id();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        area.setAdapter(adapter10);
                        for (int j = 0; j < areaModels.size(); j++) {
                            if (areaModels.get(j).getArea_id() == area_id) {
                                area.setSelection(j);
                            }
                        }
                    }
                } else {
                    ArrayList<String> strings = new ArrayList<>();
                    strings.add("Select Area");
                    final ArrayAdapter adapter10 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, strings);
                    valueArea = 0;
                    area.setAdapter(adapter10);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pincodeArray);

        pin.setAdapter(adapter);
        for (int i = 0; i < listPincodes.size(); i++) {
            if (listPincodes.get(i).getPincode_id() == pincode_id) {
                pin.setSelection(i + 1);
            }
        }
    }

    private void getCountries(JSONArray jsonArray, JSONArray jsonArrayStates, JSONArray jsonArrayCity) {
        Gson gson = new Gson();
        listCountries = Arrays.asList(gson.fromJson(jsonArray.toString(), CountriesModel[].class));
        listStates = Arrays.asList(gson.fromJson(jsonArrayStates.toString(), StateModel[].class));
        listCities = Arrays.asList(gson.fromJson(jsonArrayCity.toString(), CityModel[].class));
        ArrayList<String> strings1 = new ArrayList<>();
        strings1.add("Select City");
        ArrayList<String> strings2 = new ArrayList<>();
        strings2.add("Select State");
        valueCity = 0;
        valueCountry = 0;
        valuestate = 0;
        final ArrayAdapter adapter5 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings1);
        final ArrayAdapter adapter6 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings2);
        city.setAdapter(adapter5);
        state.setAdapter(adapter6);


        strings.add("Select Country");
        for (int i = 0; i < listCountries.size(); i++) {

            if (listCountries.get(i).getCountry_translation() == null) {
                strings.add(listCountries.get(i).getDefault_country_translation().getCountry_name());
            } else {
                strings.add(listCountries.get(i).getCountry_translation().getCountry_name());
            }
        }
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    city.setAdapter(null);
                    state.setAdapter(null);
                    valueCountry = listCountries.get(i - 1).getCountry_id();
                    final List<StateModel> strings1 = new ArrayList<>();
                    for (int j = 0; j < listStates.size(); j++) {
                        if (listStates.get(j).getCountry_id() == valueCountry) {

                            strings1.add(listStates.get(j));
                        }
                    }
                    ArrayList<String> stateString = new ArrayList<>();
                    for (int j = 0; j < strings1.size(); j++) {
                        if (strings1.get(j).getState_translation() == null) {
                            stateString.add(strings1.get(j).getDefault_state_translation().getState_name());

                        } else {
                            stateString.add(strings1.get(j).getState_translation().getState_name());

                        }
                    }
                    if (strings1.size() == 0) {


                        ArrayList<String> strings2 = new ArrayList<>();
                        strings2.add("Select State");
                        valueCity = 0;
                        valuestate = 0;
                        final ArrayAdapter adapter6 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, strings2);
                        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                ArrayList<String> strings5 = new ArrayList<>();
                                valueCity = 0;

                                strings5.add("Select City");
                                final ArrayAdapter adapter5 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, strings5);
                                city.setAdapter(adapter5);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        state.setAdapter(adapter6);
                    } else {

                        final ArrayAdapter adapter2 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, stateString);
                        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                valuestate = strings1.get(i).getState_id();
                                final List<CityModel> cityModels = new ArrayList<>();
                                for (int j = 0; j < listCities.size(); j++) {
                                    if (listCities.get(j).getState_id() == valuestate) {
                                        cityModels.add(listCities.get(j));
                                    }
                                }
                                if (cityModels.size() == 0) {
                                    valueCity = 0;
                                }
                                ArrayList<String> strings = new ArrayList<>();
                                for (int j = 0; j < cityModels.size(); j++) {
                                    if (cityModels.get(j).getCity_translation() == null) {
                                        strings.add(cityModels.get(j).getDefault_city_translation().getCity_name());
                                    } else {
                                        strings.add(cityModels.get(j).getCity_translation().getCity_name());

                                    }
                                }
                                if (cityModels.size() == 0) {
                                    ArrayList<String> strings5 = new ArrayList<>();
                                    strings5.add("Select City");

                                    valueCity = 0;

                                    final ArrayAdapter adapter5 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, strings5);
                                    city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            valueCity = 0;
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    city.setAdapter(adapter5);
                                    return;
                                }
                                final ArrayAdapter adapter3 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, strings);
                                city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        valueCity = cityModels.get(i).getCity_id();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                                city.setAdapter(adapter3);
                                for (int k = 0; k < cityModels.size(); k++) {
                                    if (cityModels.get(k).getCity_id() == city_id) {
                                        city.setSelection(k);
                                    }
                                }


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                        state.setAdapter(adapter2);
                        for (int k = 0; k < strings1.size(); k++) {
                            if (strings1.get(k).getState_id() == state_id) {
                                state.setSelection(k);
                            }
                        }
                    }
                } else {
                    city.setAdapter(null);
                    state.setAdapter(null);

                    valueCity = 0;
                    valuestate = 0;

                    ArrayList<String> strings2 = new ArrayList<>();
                    strings2.add("Select State");
                    final ArrayAdapter adapter6 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, strings2);
                    state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            ArrayList<String> strings5 = new ArrayList<>();
                            strings5.add("Select City");
                            valueCity = 0;

                            final ArrayAdapter adapter5 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, strings5);
                            city.setAdapter(adapter5);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    state.setAdapter(adapter6);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

        country.setAdapter(adapter);
        for (int i = 0; i < listCountries.size(); i++) {
            if (listCountries.get(i).getCountry_id() == country_id) {
                System.out.println("sdxz" + listCountries.get(i).getCountry_id() + "dsa" + country_id);
                country.setSelection(i + 1);
            }
        }
    }

    public void getStates(JSONArray jsonArrayStates, JSONArray jsonArrayCity) {

        Gson gson = new Gson();
        listStates = Arrays.asList(gson.fromJson(jsonArrayStates.toString(), StateModel[].class));
        listCities = Arrays.asList(gson.fromJson(jsonArrayCity.toString(), CityModel[].class));


        ArrayList<String> stateString = new ArrayList<>();
        for (int j = 0; j < listStates.size(); j++) {
            if (listStates.get(j).getState_translation() == null) {
                stateString.add(listStates.get(j).getDefault_state_translation().getState_name());

            } else {
                stateString.add(listStates.get(j).getState_translation().getState_name());

            }


            final ArrayAdapter adapter2 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, stateString);
            state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    valuestate = listStates.get(i).getState_id();
                    final List<CityModel> cityModels = new ArrayList<>();
                    for (int j = 0; j < listCities.size(); j++) {
                        if (listCities.get(j).getState_id() == valuestate) {
                            cityModels.add(listCities.get(j));
                        }
                    }
                    if (cityModels.size() == 0) {
                        valueCity = 0;
                    }
                    ArrayList<String> strings = new ArrayList<>();
                    for (int j = 0; j < cityModels.size(); j++) {
                        if (cityModels.get(j).getCity_translation() == null) {
                            strings.add(cityModels.get(j).getDefault_city_translation().getCity_name());
                        } else {
                            strings.add(cityModels.get(j).getCity_translation().getCity_name());

                        }
                    }
                    if (cityModels.size() == 0) {
                        ArrayList<String> strings5 = new ArrayList<>();
                        strings5.add("Select City");

                        valueCity = 0;

                        final ArrayAdapter adapter5 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, strings5);
                        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                valueCity = 0;
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        city.setAdapter(adapter5);
                    } else {
                        final ArrayAdapter adapter3 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, strings);
                        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                valueCity = cityModels.get(i).getCity_id();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        city.setAdapter(adapter3);
                        for (int k = 0; k < cityModels.size(); k++) {
                            if (cityModels.get(k).getCity_id() == city_id) {
                                city.setSelection(k);
                            }
                        }


                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            state.setAdapter(adapter2);

            for (int k = 0; k < listStates.size(); k++) {
                if (listStates.get(k).getState_id() == state_id) {
                    System.out.println("sdxz" + state_id + "s" + listStates.get(k).getState_id());
                    state.setSelection(k);
                }
            }
        }
    }

    private void getCity(JSONArray jsonArrayCity) {


        Gson gson = new Gson();
        listCities = Arrays.asList(gson.fromJson(jsonArrayCity.toString(), CityModel[].class));
        final List<CityModel> strings1 = new ArrayList<>();

        ArrayList<String> stateString = new ArrayList<>();
        for (int j = 0; j < listCities.size(); j++) {
            if (listCities.get(j).getCity_translation() == null) {

                stateString.add(listCities.get(j).getDefault_city_translation().getCity_name());

            } else {
                stateString.add(listCities.get(j).getCity_translation().getCity_name());

            }
            final ArrayAdapter adapter6 = new ArrayAdapter(PaymentDetailsActivity.this, android.R.layout.simple_list_item_1, stateString);
            city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    valueCity = listCities.get(i).getCity_id();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            city.setAdapter(adapter6);
            for (int k = 0; k < listCities.size(); k++) {
                if (listCities.get(k).getCity_id() == city_id) {
                    city.setSelection(k);
                }
            }
        }
    }
}
