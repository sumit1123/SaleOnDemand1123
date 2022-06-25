package com.example.grocery.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.example.grocery.Adapter.ProductAdapter;
import com.example.grocery.Adapter.VendorAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.ProductModel;
import com.example.grocery.Model.VendorModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.Activities.ProductDetails.productid;

public class Vendor extends AppCompatActivity {
    public RecyclerView vendorrecycler;
    public VendorAdapter vendorAdapter;
    List<VendorModel> list;

    private SwipeRefreshLayout refreshLayout;
    private int vendor_call_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        // toolbarTitle.setText("  " + Label.userLabel.getVendor());
        toolbarTitle.setText("Vendor");
        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        vendorrecycler = (RecyclerView) findViewById(R.id.vendorrecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        vendorrecycler.setLayoutManager(linearLayoutManager);
        vendorAdapter = new VendorAdapter(this, list);
        getdata();
    }


    private void getdata() {
        Bundle extras = getIntent().getExtras();
        final JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs.getString("user_id", "");
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
        System.out.println("scdxz" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_Vendor, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                if (!new ResponseHandler().validateResponse(Vendor.this, response)) {
                    //return;
                }

                try {
                    JSONObject jsonObject1 = response.getJSONObject("data");
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
                    JSONArray jsonArray1 = jsonObject2.getJSONArray("vendorCalls");
                    setData1(jsonArray1);
                    onItemsLoadComplete();
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

    private void setData1(JSONArray jsonArray) {
        Gson gson = new Gson();
        list = Arrays.asList(gson.fromJson(jsonArray.toString(), VendorModel[].class));
        List<VendorModel> vendorModels = new ArrayList<>();
        vendorModels.addAll(list);
        vendorrecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        final RecyclerView.LayoutManager linearLayoutManager = layoutManager;
        vendorrecycler.setLayoutManager(layoutManager);
        vendorAdapter = new VendorAdapter(this, vendorModels);
        vendorrecycler.setAdapter(vendorAdapter);
    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
//      refreshLayout.setRefreshing(false);
    }
}


