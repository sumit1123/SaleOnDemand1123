package com.example.grocery.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.grocery.Activities.Vendor;
import com.example.grocery.Model.VendorModel;

import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

public class VendorAdapter extends RecyclerView.Adapter<VendorAdapter.VendorInfo> {
   String call = String.valueOf(1);

    private final Vendor context;
   private final List<VendorModel> list;

   public VendorAdapter(Vendor Vendor, List<VendorModel> list) {
        this.context = Vendor;
        this.list = list;
    }

    @Override
    public VendorInfo onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendor_list_layout, parent, false);
        return new VendorInfo(view,list,context);
    }

    @Override
    public void onBindViewHolder(VendorInfo holder, final int position) {
        holder.name.setText(list.get(position).getName());
       holder.phone.setText(list.get(position).getContact_number());


       holder.call.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int vendor_call_id = list.get(position).getVendor_call_id();
               getdata(vendor_call_id);

           }
       });
    }

    private void getdata(int vendor_call_id) {

        final JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = context.getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));


        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("language_id", languageid);
            jsonObject.put("calls", call);
           jsonObject.put("vendor_call_id", vendor_call_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("sdxcz" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(context, IConstants.URL_AddVendor, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {

                try {
                    String status = response.getString("status");
                    if (status.matches("true")) {
                        //     new CustomToast(Contact_Us.this, response.getJSONObject("data").getString("msg"));
                        JSONObject jsonObject1 = response.getJSONObject("data");
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
                        JSONObject jsonObject3 = jsonObject2.getJSONObject("vendorCalls");
                        Toast.makeText(context, "Vendor added Successfully", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fnErrorOccurred(String error) {
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VendorInfo extends RecyclerView.ViewHolder {
       TextView name,phone;
       ImageView call;

        public VendorInfo(View itemView, List<VendorModel> list, Vendor context) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView)itemView.findViewById(R.id.phone);
            call = (ImageView)itemView.findViewById(R.id.call);
        }


    }
}
