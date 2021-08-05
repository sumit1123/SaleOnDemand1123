package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.interfaces.IConstants.URL_ADDNEWADDRESS;
import static com.example.grocery.interfaces.IConstants.Url_UpdateAddress;

public class AddNewAddreses extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout bar;
    public AppCompatEditText address_et;
    private Button addNewAddress;
    private AppCompatEditText usernamesignup, numbersignup;
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
    private int country_id, state_id, city_id, pincode_id, address_id, area_id;
    private String name, address, contact_number, email;
    private AppCompatEditText email_et;
    private List<AreaModel> listAreas;
    private int valueArea;
    private Spinner area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_my_addreses);
        Bundle extras = getIntent().getExtras();
        country_id = extras.getInt("country_id");
        state_id = extras.getInt("state_id");
        city_id = extras.getInt("city_id");
        pincode_id = extras.getInt("pin_id");
        address_id = extras.getInt("address_id");
        area_id = extras.getInt("area_id");

        name = extras.getString("name");
        address = extras.getString("address");
        contact_number = extras.getString("contact_number");
        email = extras.getString("email");
        if (email.matches(contact_number) && contact_number.contains("@")) {
            contact_number = "";
        }

        strings = new ArrayList<>();
        strings2 = new ArrayList<>();
        strings3 = new ArrayList<>();

        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        setToolBar();
        initUI();
        try {
           /* addNewAddress.setBackground(this.getResources().getDrawable(R.drawable.buttonshape));
            GradientDrawable bgShape = (GradientDrawable) addNewAddress.getBackground();
            bgShape.setColor(Color.parseColor("#" + colorSettings.getString("app_color", "FFFFFF")));*/
            String add = getString(R.string.add);
            addNewAddress.setText(add);
            address_et.setHighlightColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
            String newaddress = getString(R.string.new_address);
            String name = getString(R.string.name);
            String mob = getString(R.string.mobile_number);
            String email = getString(R.string.email);
            address_et.setHint(newaddress);
            usernamesignup.setHint(name);
            numbersignup.setHint(mob);
            address_et.setHint(newaddress);
            usernamesignup.setHint(name);
            numbersignup.setHint(mob);

            email_et.setHint(email);
            System.out.println(address + name + contact_number);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (address_id != 0) {

            try {
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        getData();
    }


    private void initUI() {
        city = (Spinner) findViewById(R.id.citySignUp);
        state = (Spinner) findViewById(R.id.stateSignUp);
        pin = (Spinner) findViewById(R.id.pinsignup);
        area = (Spinner) findViewById(R.id.areaSignUp);

        country = (Spinner) findViewById(R.id.countrySignUp);
        address_et = (AppCompatEditText) findViewById(R.id.address_et);
        usernamesignup = (AppCompatEditText) findViewById(R.id.usernamesignup);
        numbersignup = (AppCompatEditText) findViewById(R.id.numbersignup);
        email_et = (AppCompatEditText) findViewById(R.id.email_et);

        new EditTextColorChanger(this, address_et);
        new EditTextColorChanger(this, usernamesignup);
        new EditTextColorChanger(this, numbersignup);
        addNewAddress = (Button) findViewById(R.id.addnewAddress);

        addNewAddress.setOnClickListener(this);
    /*    addNewAddress.setBackgroundColor(Color.parseColor("#" + colorSettings.getString("app_color", "#FFFFFF")));*/
        addNewAddress.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));


    }

    private void setToolBar() {
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
                Intent intent = new Intent(AddNewAddreses.this, NotificationActivity.class);
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
                Intent intent = new Intent(AddNewAddreses.this, SearchActivity.class);
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
                Intent intent = new Intent(AddNewAddreses.this, CartActivity.class);
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
          //  toolBarTitle.setText(Label.orderLabel.getAddress());
            String address = getString(R.string.add_new_address);
            toolBarTitle.setText(address);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void addnewAddress() {
        try {
            if (usernamesignup.getText().toString().isEmpty()) {
                usernamesignup.requestFocus();
                String err = getString(R.string.name_empty);
                usernamesignup.setError(err);
                return;
            } else {
                usernamesignup.setError(null);
            }
            if (email_et.getText().toString().isEmpty()) {
                email_et.requestFocus();
                String err = getString(R.string.email_empty);
                email_et.setError(err);
                return;
            } else {
                email_et.setError(null);
            }
            if (!emailValidator(email_et.getText().toString())) {
                email_et.requestFocus();
                email_et.setError("Valid Email");
                return;
            } else {
                email_et.setError(null);
            }
            if (numbersignup.getText().toString().isEmpty()) {
                numbersignup.requestFocus();
                String err = getString(R.string.contact_number_empty);
                numbersignup.setError(err);
                return;
            } else {
                numbersignup.setError(null);
            }

            if (numbersignup.getText().toString().length() > 13) {
                numbersignup.requestFocus();
                String err = getString(R.string.please_enter_a_valid_number);
                numbersignup.setError(err);
                return;
            } else {
                numbersignup.setError(null);
            }

            if (address_et.getText().toString().isEmpty()) {
                address_et.requestFocus();
                String err = getString(R.string.address_empty);
                address_et.setError(err);
                return;
            } else if (valueCountry == 0 && country.isShown()) {
                new CustomToast(AddNewAddreses.this, "Please select country from drop down");
            } else if (valuestate == 0 && state.isShown()) {
                new CustomToast(AddNewAddreses.this, "Please select state from drop down");
            } else if (valueCity == 0 && city.isShown()) {
                new CustomToast(AddNewAddreses.this, "Please select city from drop down");
            } else if (pin.getSelectedItemPosition() == 0 && pin.isShown()) {
                System.out.println("valueCity" + valueCity);
                new CustomToast(AddNewAddreses.this,getString(R.string.pincode_empty));

            } else if (valueArea == 0 && area.isShown()) {
                new CustomToast(AddNewAddreses.this, "please select area drom dropdown");
            } else {
                address_et.setError(null);

                if (address_id != 0) {
                    updateAddress();
                } else {
                    findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
                    JSONObject jsonObject = new JSONObject();
                    SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

                    String userid = prefs.getString("user_id", "");
                    String email = prefs.getString("email", "");
                    String pwd = prefs.getString("pwd", "");
                    String languageid = prefs.getString("language", String.valueOf(1));

                    try {
                        jsonObject.put("business_id",IConstants.BUSINESS_ID);
                        jsonObject.put("id", userid);
                        jsonObject.put("password", pwd);
                        jsonObject.put("address", address_et.getText().toString());
                        jsonObject.put("language_id", languageid);
                        jsonObject.put("name", usernamesignup.getText().toString());
                        jsonObject.put("contact_number", numbersignup.getText().toString());
                        jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
                        jsonObject.put("state_id", valuestate == 0 ? 1 : valuestate);
                        jsonObject.put("city_id", valueCity == 0 ? 1 : valueCity);
                        jsonObject.put("area_id", valueArea == 0 ? 1 : valueArea);
                        jsonObject.put("pincode_id", valuePincode == 0 ? 1 : valuePincode);
                        jsonObject.put("email", email_et.getText().toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    System.out.println("dcxz" + jsonObject);
                    VolleyTask volleyTask = new VolleyTask(this, URL_ADDNEWADDRESS, jsonObject, Request.Method.POST);
                    volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                        @Override
                        public void fnPostTaskCompleted(JSONArray response) {

                        }

                        @Override
                        public void fnPostTaskCompletedJsonObject(JSONObject response) {
                            System.out.println("hhhs" + response.toString());
                            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                        /*    if (!new ResponseHandler().validateResponse(AddNewAddreses.this, response)) {
                                return;
                            }*/

                            try {
                                PaymentDetailsActivity.isshowDialog = true;

                                new CustomToast(AddNewAddreses.this, response.getJSONObject("data").getString("msg"));
                                finish();


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

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void updateAddress() {
        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String email = prefs.getString("email", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("address", address_et.getText().toString());
            jsonObject.put("language_id", languageid);
            jsonObject.put("name", usernamesignup.getText().toString());
            jsonObject.put("contact_number", numbersignup.getText().toString());
            jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
            jsonObject.put("state_id", valuestate == 0 ? 1 : valuestate);
            jsonObject.put("city_id", valueCity == 0 ? 1 : valueCity);
            jsonObject.put("area_id", valueArea == 0 ? 1 : valueArea);
            jsonObject.put("pincode_id", valuePincode == 0 ? 1 : valuePincode);
            jsonObject.put("address_id", address_id);
            jsonObject.put("email", email_et.getText().toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("dcxz" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, Url_UpdateAddress, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

               /* if (!new ResponseHandler().validateResponse(AddNewAddreses.this, response)) {
                    return;
                }*/

                try {
                    PaymentDetailsActivity.isshowDialog = true;

                    new CustomToast(AddNewAddreses.this, response.getJSONObject("data").getString("msg"));
                    finish();


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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addnewAddress:
                addnewAddress();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(AddNewAddreses.this);


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
                        final ArrayAdapter adapter10 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, strings);
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
                        final ArrayAdapter adapter10 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, strings);
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
                    final ArrayAdapter adapter10 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, strings);
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
                        final ArrayAdapter adapter6 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, strings2);
                        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                ArrayList<String> strings5 = new ArrayList<>();
                                valueCity = 0;

                                strings5.add("Select City");
                                final ArrayAdapter adapter5 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, strings5);
                                city.setAdapter(adapter5);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        state.setAdapter(adapter6);
                    } else {

                        final ArrayAdapter adapter2 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, stateString);
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

                                    final ArrayAdapter adapter5 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, strings5);
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
                                final ArrayAdapter adapter3 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, strings);
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
                    final ArrayAdapter adapter6 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, strings2);
                    state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            ArrayList<String> strings5 = new ArrayList<>();
                            strings5.add("Select City");
                            valueCity = 0;

                            final ArrayAdapter adapter5 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, strings5);
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
        System.out.println("sdxz" + strings2);
        for (int i = 0; i < listCountries.size(); i++) {
            if (listCountries.get(i).getCountry_id() == country_id) {
                System.out.println("sdxz" + listCountries.get(i).getCountry_id() + "dsa" + country_id);
                country.setSelection(i + 1);
            }
        }

    }

    public void getData() {
        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);

        findViewById(R.id.retryImage).setVisibility(View.GONE);
        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String languageid = prefs.getString("language", String.valueOf(1));
        String userid = prefs.getString("user_id", "");
        String email = prefs.getString("email", "");
        String pwd = prefs.getString("pwd", "");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("language_id", languageid);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_GetAddressSetting, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                try {

                    JSONArray jsonArrayCountries = response.getJSONObject("data").getJSONObject("data").getJSONArray("countries");
                    JSONArray jsonArrayStates = response.getJSONObject("data").getJSONObject("data").getJSONArray("states");
                    JSONArray jsonArrayCity = response.getJSONObject("data").getJSONObject("data").getJSONArray("cities");
                    JSONArray jsonArrayPincode = response.getJSONObject("data").getJSONObject("data").getJSONArray("pincodes");
                    JSONArray jsonArrayArea = response.getJSONObject("data").getJSONObject("data").getJSONArray("areas");
                    getPinCodes(jsonArrayPincode, jsonArrayArea);
                    getCountries(jsonArrayCountries, jsonArrayStates, jsonArrayCity);
                    JSONObject jsonObject3 = response.getJSONObject("data").getJSONObject("data").getJSONObject("address_setting");
                    if (jsonObject3.getInt("is_country") == 1) {
                        country.setVisibility(View.VISIBLE);
                    } else {
                        getStates(jsonArrayStates, jsonArrayCity);

                    }
                    if (jsonObject3.getInt("is_pincode") == 1) {
                        pin.setVisibility(View.VISIBLE);
                    } else {
                        getArea(jsonArrayArea);
                    }
                    if (jsonObject3.getInt("is_state") == 1) {
                        state.setVisibility(View.VISIBLE);
                    } else {
                        getCity(jsonArrayCity);
                    }
                    if (jsonObject3.getInt("is_city") == 1) {
                        city.setVisibility(View.VISIBLE);
                    }
                    if (jsonObject3.getInt("is_area") == 1) {
                        area.setVisibility(View.VISIBLE);
                    }


                    findViewById(R.id.whiteloader).setVisibility(View.GONE);


                } catch (JSONException e) {

                    e.printStackTrace();
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

    private void getArea(JSONArray jsonArrayarea) {

        Gson gson = new Gson();
        listAreas = Arrays.asList(gson.fromJson(jsonArrayarea.toString(), AreaModel[].class));

        if (listAreas.size() == 0) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add("Select Area");
            final ArrayAdapter adapter10 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, strings);
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

            final ArrayAdapter adapter10 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, strings);
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

    public void getStates(JSONArray jsonArrayStates, JSONArray jsonArrayCity) {

        Gson gson = new Gson();
        listStates = Arrays.asList(gson.fromJson(jsonArrayStates.toString(), StateModel[].class));
        listCities = Arrays.asList(gson.fromJson(jsonArrayCity.toString(), CityModel[].class));

        final List<StateModel> strings1 = new ArrayList<>();

        ArrayList<String> stateString = new ArrayList<>();
        stateString.add("Select State");
        for (int j = 0; j < listStates.size(); j++) {
            if (listStates.get(j).getState_translation() == null) {
                stateString.add(listStates.get(j).getDefault_state_translation().getState_name());

            } else {
                stateString.add(listStates.get(j).getState_translation().getState_name());

            }


            final ArrayAdapter adapter2 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, stateString);
            state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == 0) {
                        valuestate = 0;
                    } else {
                        valuestate = listStates.get(i - 1).getState_id();
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

                            final ArrayAdapter adapter5 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, strings5);
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
                            final ArrayAdapter adapter3 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, strings);
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
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            state.setAdapter(adapter2);
            for (int k = 0; k < listStates.size(); k++) {
                if (listStates.get(k).getState_id() == state_id) {
                    System.out.println("sdxz" + state_id + "s" + listStates.get(k).getState_id());
                    state.setSelection(k + 1);
                }
            }

        }
    }

    private void getCity(JSONArray jsonArrayCity) {


        Gson gson = new Gson();
        listCities = Arrays.asList(gson.fromJson(jsonArrayCity.toString(), CityModel[].class));
        final List<CityModel> strings1 = new ArrayList<>();

        ArrayList<String> cityString = new ArrayList<>();
        cityString.add("Select City");
        for (int j = 0; j < listCities.size(); j++) {
            if (listCities.get(j).getCity_translation() == null) {

                cityString.add(listCities.get(j).getDefault_city_translation().getCity_name());

            } else {
                cityString.add(listCities.get(j).getCity_translation().getCity_name());

            }
            final ArrayAdapter adapter6 = new ArrayAdapter(AddNewAddreses.this, android.R.layout.simple_list_item_1, cityString);
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
        }
    }

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
