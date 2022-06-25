package com.example.grocery.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import id.zelory.compressor.Compressor;
import id.zelory.compressor.FileUtil;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.interfaces.IConstants.URL_GETUSERPROFILE;
import static com.example.grocery.interfaces.IConstants.URL_updatePRofile;

public class EditProfileActivity extends AppCompatActivity {
    private AppCompatEditText editTextName, editTextMobile, editTextAddress;
    private Button submit;
    private TextView cartCount;
    private boolean shouldExecuteOnResume;
    private Spinner city;
    private static final int CAMERA_REQUEST = 1888;
    private File actualImage;
    private Spinner state;
    private Spinner country, pin;
    private List<CountriesModel> listCountries;
    private List<StateModel> listStates;
    private List<CityModel> listCities;
    private int valueCountry;
    private int valuestate;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    private int valueCity;
    private ImageView profile;
    private List<PincodeModel> listPincodes;
    private int valuePincode;
    private ArrayList<String> strings;
    private ArrayList<String> strings2, strings3;
    private int country_id, state_id, city_id, pincode_id, area_id;
    private Spinner area;
    private List<AreaModel> listAreas;
    private int valueArea;
    private ImageView outside_imageview;
    private ImageView profile_pic;
    private static int RESULT_LOAD_IMAGE = 1;
    Random random = new Random();
    private Bitmap bitmap;
    int num = random.nextInt(100000000);
    String profileName = "" + num + ".jpg";
    private ArrayList<Object> base64String;
    String imagePath = null;
    private String encodedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_edit_profile);
        //labelsShared = getSharedPreferences("labels", MODE_PRIVATE);
        strings = new ArrayList<>();
        strings2 = new ArrayList<>();
        strings3 = new ArrayList<>();

        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        setToolbar();
        initui();
        getData();

    }

    private void initui() {
        submit = (Button) findViewById(R.id.submitText);
        editTextName = (AppCompatEditText) findViewById(R.id.updateprofileusername);
        editTextMobile = (AppCompatEditText) findViewById(R.id.updatemobile);
        editTextAddress = (AppCompatEditText) findViewById(R.id.updateaddress);
        city = (Spinner) findViewById(R.id.citySignUp);
        state = (Spinner) findViewById(R.id.stateSignUp);
        pin = (Spinner) findViewById(R.id.pinsignup);
        country = (Spinner) findViewById(R.id.countrySignUp);
        area = (Spinner) findViewById(R.id.areaSignUp);
        profile_pic = (ImageView) findViewById(R.id.profile_pic);
        outside_imageview = (ImageView) findViewById(R.id.outside_imageview);
        outside_imageview.setVisibility(View.VISIBLE);

       /* GradientDrawable bgShape = (GradientDrawable) submit.getBackground();
        bgShape.setColor(Color.parseColor("#" + sharedPreferences.getString("app_color", "FFFFFF")));*/
        /*StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(
                new int[]{android.R.attr.state_pressed},
                new ColorDrawable(Color.parseColor("#FF0000"))
        );
        stateListDrawable.addState(
                new int[]{android.R.attr.state_focused},
                new ColorDrawable(Color.parseColor("#0000FF"))
        );
        stateListDrawable.addState(
                new int[]{},
                new ColorDrawable(Color.parseColor("#FF0000"))
        );*/
    /*    float[] outerRadii = new float[]{75,75,75,75,75,75,75,75};
        float[] innerRadii = new float[]{75,75,75,75,75,75,75,75};

        // Set the shape border
        ShapeDrawable borderDrawable = new ShapeDrawable(new RoundRectShape(
                outerRadii,
                null, // Inset
                innerRadii
        ));


        // Set the shape background

        Drawable[] drawables = new Drawable[]{
                Drawablels.getSelectableDrawableFor(Color.parseColor("#" + sharedPreferences.getString("app_color", "FFFFFF"))),
                borderDrawable

        };*/
  /*      StateListDrawable bgShape1 = (StateListDrawable)(submit.getBackground());

        DrawableContainer.DrawableContainerState dcs = (DrawableContainer.DrawableContainerState)bgShape1.getConstantState();
        Drawable[] drawableItems = dcs.getChildren();
        GradientDrawable gradientDrawableChecked = (GradientDrawable)drawableItems[0]; // item 1
        GradientDrawable gradientDrawableUnChecked = (GradientDrawable)drawableItems[1];*/

        outside_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // selectImage();
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

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
            submit.setBackgroundTintList(stateListDrawable);
        } else {
            submit.setBackground(EditProfileActivity.this.getResources().getDrawable(R.drawable.buttonshape));
            GradientDrawable bgShape = (GradientDrawable) submit.getBackground();
            bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        }
        new EditTextColorChanger(this, editTextName);
        new EditTextColorChanger(this, editTextMobile);
        new EditTextColorChanger(this, editTextAddress);

        TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        try {
            String name = getString(R.string.name);
            String mob = getString(R.string.mobile_number);
            String address = getString(R.string.address);
            String save = getString(R.string.submit);
            editTextName.setHint(name);
            editTextMobile.setHint(mob);
            editTextAddress.setHint(address);
            submit.setText(save);
            String profile = getString(R.string.update_profile);
            toolbarTitle.setText(profile);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (editTextName.getText().toString().isEmpty()) {
                        editTextName.requestFocus();
                        String err = getString(R.string.name_empty);
                        editTextName.setError(err);
                        return;
                    } else {
                        editTextName.setError(null);
                    }

                    if (editTextMobile.getText().toString().isEmpty()) {
                        editTextMobile.requestFocus();
                        String err = getString(R.string.contact_number_empty);
                        editTextMobile.setError(err);
                        return;
                    } else {
                        editTextMobile.setError(null);
                    }
                    if (editTextMobile.getText().toString().length() != 10) {
                        editTextMobile.requestFocus();
                        String err = getString(R.string.please_enter_a_valid_number);
                        editTextMobile.setError(err);
                        return;
                    } else {
                        editTextMobile.setError(null);
                    }
                    if (editTextAddress.getText().toString().isEmpty()) {
                        editTextAddress.requestFocus();
                        String err = getString(R.string.address_empty);
                        editTextMobile.setError(err);
                        return;
                    } else if (profile_pic.getDrawable() == null) {
                        profile_pic.requestFocus();
                        String err = "profile pic can not be empty";
                        //Toast.makeText(getApplicationContext(), "Please upload profile picture of the visitor", Toast.LENGTH_SHORT).show();
                    } else if (valueCountry == 0 && country.isShown()) {
                        new CustomToast(EditProfileActivity.this, "Please select country from drop down");
                    } else if (valuestate == 0 && state.isShown()) {
                        new CustomToast(EditProfileActivity.this, "Please select state from drop down");
                    } else if (valueCity == 0 && city.isShown()) {
                        new CustomToast(EditProfileActivity.this, "Please select city from drop down");
                    } else if (pin.getSelectedItemPosition() == 0 && pin.isShown()) {
                        System.out.println("valueCity" + valueCity);

                        String pincode = getString(R.string.pincode_empty);
                        new CustomToast(EditProfileActivity.this, pincode);
                    } else if (valueArea == 0 && area.isShown()) {
                        new CustomToast(EditProfileActivity.this, "please select area drom dropdown");

                    } else {
                        editTextAddress.setError(null);
                        submitdetails();

                    }


                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void setToolbar() {

        RelativeLayout toolbarNotification = (RelativeLayout) findViewById(R.id.toolnotification);
        toolbarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences appsettings = getSharedPreferences("APPSETTINGS", MODE_PRIVATE);

        if (appsettings.getInt("is_notification", 0) == 0) {
            toolbarNotification.setVisibility(View.GONE);

        }
        RelativeLayout toolSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, SearchActivity.class);
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
        RelativeLayout toolbarCart = (RelativeLayout) findViewById(R.id.cart);
        toolbarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, CartActivity.class);
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

    }

    protected void getData() {
        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        findViewById(R.id.retryImage).setVisibility(View.GONE);


        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);


        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        final String email = prefs.getString("email", "");
        String password = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", password);
            jsonObject.put("language_id", languageid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("fsdxz" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, URL_GETUSERPROFILE, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());
                findViewById(R.id.whiteloader).setVisibility(View.GONE);
                if (!new ResponseHandler().validateResponse(EditProfileActivity.this, response)) {
                    return;
                }
                try {
                    JSONObject jsonObject1 = response.getJSONObject("data");
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("data").getJSONObject("user");
                    country_id = jsonObject2.getJSONObject("address").getInt("country_id");
                    state_id = jsonObject2.getJSONObject("address").getInt("state_id");
                    System.out.println("dsxz" + jsonObject2.getJSONObject("address"));
                    city_id = jsonObject2.getJSONObject("address").getInt("city_id");
                    pincode_id = jsonObject2.getJSONObject("address").getInt("pincode_id");
                    area_id = jsonObject2.getJSONObject("address").getInt("area_id");
                    System.out.println("state_id" + state_id + "dsxz" + city_id + "saxz" + country_id);
                    JSONArray jsonArrayCountries = response.getJSONObject("data").getJSONObject("data").getJSONArray("countries");
                    JSONArray jsonArrayStates = response.getJSONObject("data").getJSONObject("data").getJSONArray("states");
                    JSONArray jsonArrayCity = response.getJSONObject("data").getJSONObject("data").getJSONArray("cities");
                    JSONArray jsonArrayPincode = response.getJSONObject("data").getJSONObject("data").getJSONArray("pincodes");
                    JSONArray jsonArrayareas = response.getJSONObject("data").getJSONObject("data").getJSONArray("areas");

                    getPinCodes(jsonArrayPincode, jsonArrayareas);
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
                        getArea(jsonArrayareas);
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

                    Dashboard.cart_count = jsonObject1.getJSONObject("data").getInt("cart_count");
                    Dashboard.notification_count = jsonObject1.getJSONObject("data").getInt("notification_count");
                    new CartCountUtil(EditProfileActivity.this);

                    String name = jsonObject2.getString("name");
                    String number = jsonObject2.getString("contact_number");
                    String email = jsonObject2.getString("email");

                    String address = "";
                    if (!jsonObject2.get("address").equals(null)) {
                        address = jsonObject2.getJSONObject("address").getString("address");
                    }
                    if (email.matches(number) && number.contains("@")) {
                        number = "";
                    }
                    editTextAddress.setText(address.equals("null") ? "" : address);
                    editTextName.setText(name.equals("null") ? "" : name);
                    editTextMobile.setText(number.equals("null") ? "" : number);
                    SharedPreferences.Editor editor = getSharedPreferences("usertable", MODE_PRIVATE).edit();
                    editor.putString("name", name);
                    editor.putString("address", address);
                    editor.apply();


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

    private void submitdetails() {
        {
            findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
            JSONObject jsonObject = new JSONObject();
            SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

            /*ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] imgBytes = byteArrayOutputStream.toByteArray();
            encodedImage = Base64.encodeToString(imgBytes, Base64.DEFAULT);
            base64String.add(encodedImage);*/

            ByteArrayOutputStream byteArrayOutputStreamObject ;

            byteArrayOutputStreamObject = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

            byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

            final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);



            String userid = prefs.getString("user_id", "");
            String email = prefs.getString("email", "");
            String pwd = prefs.getString("pwd", "");
            String languageid = prefs.getString("language", String.valueOf(1));

            try {
                jsonObject.put("business_id", IConstants.BUSINESS_ID);
                jsonObject.put("id", userid);
                jsonObject.put("password", pwd);
                jsonObject.put("name", editTextName.getText().toString());
                jsonObject.put("contact_number", editTextMobile.getText().toString());
                jsonObject.put("email", email);
                jsonObject.put("address", editTextAddress.getText().toString());
                jsonObject.put("language_id", languageid);
                jsonObject.put("country_id", valueCountry == 0 ? 1 : valueCountry);
                jsonObject.put("state_id", valuestate == 0 ? 1 : valuestate);
                jsonObject.put("city_id", valueCity == 0 ? 1 : valueCity);
                jsonObject.put("area_id", valueArea == 0 ? 1 : valueArea);
                jsonObject.put("pincode_id", valuePincode == 0 ? 1 : valuePincode);
                jsonObject.put("image", ConvertImage);



            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println("sxzzzggg" + jsonObject);
            VolleyTask volleyTask = new VolleyTask(EditProfileActivity.this, URL_updatePRofile, jsonObject, Request.Method.POST);
            volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                @Override
                public void fnPostTaskCompleted(JSONArray response) {

                }

                @Override
                public void fnPostTaskCompletedJsonObject(JSONObject response) {
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                    if (!new ResponseHandler().validateResponse(EditProfileActivity.this, response)) {
                        findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                        return;
                    }

                    try {
                        new CustomToast(EditProfileActivity.this, response.getJSONObject("data").getString("msg"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    SharedPreferences.Editor editor1 = getSharedPreferences("usertable", MODE_PRIVATE).edit();
                    editor1.putString("name", editTextName.getText().toString());
                    editor1.putString("address", editTextAddress.getText().toString());
                    editor1.apply();
                    SharedPreferences.Editor editor = getSharedPreferences("userdetails", MODE_PRIVATE).edit();
                    editor.putString("user_address", editTextAddress.getText().toString());

                    editor.apply();
                    SharedPreferences.Editor editor2 = getSharedPreferences("UserId", MODE_PRIVATE).edit();
                    editor2.apply();
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                    finish();


                }

                @Override
                public void fnErrorOccurred(String error) {
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(EditProfileActivity.this);


    }

    private void getArea(JSONArray jsonArrayarea) {
        Gson gson = new Gson();
        listAreas = Arrays.asList(gson.fromJson(jsonArrayarea.toString(), AreaModel[].class));

        if (listAreas.size() == 0) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add("Select Area");
            final ArrayAdapter adapter10 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, strings);
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

            final ArrayAdapter adapter10 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, strings);
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
                        final ArrayAdapter adapter10 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, strings);
                        valueArea = 0;
                        area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                valueArea = 0;
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
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
                        final ArrayAdapter adapter10 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, strings);
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
                    final ArrayAdapter adapter10 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, strings);
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
                        Toast.makeText(EditProfileActivity.this, "I enetred", Toast.LENGTH_SHORT).show();


                        ArrayList<String> strings2 = new ArrayList<>();
                        strings2.add("Select State");
                        valueCity = 0;
                        valuestate = 0;
                        final ArrayAdapter adapter6 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, strings2);
                        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                ArrayList<String> strings5 = new ArrayList<>();
                                valueCity = 0;

                                strings5.add("Select City");
                                final ArrayAdapter adapter5 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, strings5);
                                city.setAdapter(adapter5);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        state.setAdapter(adapter6);
                    } else {

                        final ArrayAdapter adapter2 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, stateString);
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

                                    final ArrayAdapter adapter5 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, strings5);
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
                                final ArrayAdapter adapter3 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, strings);
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
                    final ArrayAdapter adapter6 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, strings2);
                    state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            ArrayList<String> strings5 = new ArrayList<>();
                            strings5.add("Select City");
                            valueCity = 0;

                            final ArrayAdapter adapter5 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, strings5);
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


            final ArrayAdapter adapter2 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, stateString);
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

                            final ArrayAdapter adapter5 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, strings5);
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
                            final ArrayAdapter adapter3 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, strings);
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "CAMERA GRANTED", Toast.LENGTH_SHORT).show();
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
            final ArrayAdapter adapter6 = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_list_item_1, cityString);
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

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        encodedImage = Base64.encodeToString(imgBytes, Base64.DEFAULT);
        base64String.add(encodedImage);
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
            outside_imageview.setVisibility(View.GONE);
            bitmap = (Bitmap) data.getExtras().get("data");
            profile_pic.setImageBitmap(bitmap);

        } else {
            Uri selectedImage = data.getData();
            try {
                actualImage = FileUtil.from(EditProfileActivity.this, data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = EditProfileActivity.this.getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(EditProfileActivity.this.getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            outside_imageview.setVisibility(View.GONE);
            bitmap = Compressor.getDefault(EditProfileActivity.this).compressToBitmap(actualImage);
            profile_pic.setImageBitmap(bitmap);


        }

    }
}


