package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.example.grocery.Labels.Label;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import static com.example.grocery.Appearances.Appearance.appSettings;

public class AboutUsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_about_us);
       //setContentView(R.layout.new_activity_about_us);

       setToolBar();
        ToolbarSettings toolbarSettings=new ToolbarSettings(this);

/*

        final SharedPreferences sharedPreferences = getSharedPreferences("APPSETTINGS", MODE_PRIVATE);

        DrawableCompat.setTint(((ImageView) findViewById(R.id.map)).getDrawable(), Color.parseColor("#"+sharedPreferences.getString("text_color", "#FFFFFF")));
        DrawableCompat.setTint(((ImageView) findViewById(R.id.call)).getDrawable(), Color.parseColor("#"+sharedPreferences.getString("text_color", "#FFFFFF")));

        TextView appName=(TextView)findViewById(R.id.appname);
        TextView tagline=(TextView)findViewById(R.id.tagline);

        appName.setText(sharedPreferences.getString("app_name",""));
        tagline.setText(sharedPreferences.getString("tag_line",""));
        CardView mapButton=(CardView)findViewById(R.id.mapButton);
        CardView callingButton=(CardView)findViewById(R.id.callingButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(sharedPreferences.getString("location","wdsxz")));
                startActivity(intent);
            }
        });
        callingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL); intent.setData(Uri.parse("tel:"+sharedPreferences.getString("contact_number","8149180155"))); startActivity(intent);

            }
        });
        getData();
*/
     // getData();
    }
    private void setToolBar() {

            TextView cartCount=(TextView)findViewById(R.id.actionbar_notifcation_textview) ;
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
            RelativeLayout toolBarNotification=(RelativeLayout) findViewById(R.id.toolnotification) ;
            toolBarNotification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(AboutUsActivity.this,NotificationActivity.class);
                    startActivity(intent);
                }
            });

            if (appSettings.getIs_notification() == 0) {
                toolBarNotification.setVisibility(View.GONE);
                }
            RelativeLayout toolBarSearch=(RelativeLayout) findViewById(R.id.toolsearch) ;
            toolBarSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(AboutUsActivity.this,SearchActivity.class);
                    SearchActivity.searchKeyword = "";
                    SearchActivity.searchMax = "";
                    ApplyFilter.maximumSeekBar ="5000";
                    ApplyFilter.brandArrays =null;
                    ProductDetails.productid=0;
                    ApplyFilter.minimumSeekBar ="0";
                    SearchActivity.searchMin = "";
                    SearchActivity.sortedida = 0;
                    SearchActivity.formatedStringSearch = "";
                    SearchActivity.imagea = "true";
                    startActivity(intent);            }
            });
            RelativeLayout toolBarCart=(RelativeLayout) findViewById(R.id.cart) ;
            toolBarCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(AboutUsActivity.this,CartActivity.class);
                    startActivity(intent);            }
            });
            RelativeLayout toolbarBack=(RelativeLayout) findViewById(R.id.toolimage) ;
            toolbarBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            TextView toolbarTitle=(TextView)findViewById(R.id.titlebar);
            try {

            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(AboutUsActivity.this);



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
        String languageid = prefs.getString("language", String.valueOf(1));

        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_ABOUTUS, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhsa" + response.toString());
                findViewById(R.id.whiteloader).setVisibility(View.GONE);

              /*  if (!new ResponseHandler().validateResponse(AboutUsActivity.this, response)) {
                    return;
                }*/


                try {
                    JSONObject jsonObject1 = response.getJSONObject("data").getJSONObject("data");
                   // setData(jsonObject1.getJSONArray("policies"));
                    setData(jsonObject1.getJSONObject("aboutus"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            private void setData(JSONObject response) {

                TextView textView = findViewById(R.id.aboutusTxtView);
               // TextView textView = findViewById(R.id.textview_about_us);
               // ImageView imgView = findViewById(R.id.imgview_about_us);
                try {
                    String aboutText = Html.fromHtml(response.getJSONObject("default_aboutus_translation")
                            .getString("about_us_description")).toString();
                   // String aboutImg = Html.fromHtml(response.getJSONObject("default_aboutus_translation").getString("about_image")).toString();

//            textView.setText(response.getJSONObject("default_policy_translation").getString("policy_description").toString());
                    textView.setText(aboutText);
                  // imgView.setImageURI(Uri.parse(aboutImg));

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
                      //  findViewById(R.id.retryImage).setVisibility(View.VISIBLE);


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
}
