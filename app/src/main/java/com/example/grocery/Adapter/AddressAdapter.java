package com.example.grocery.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grocery.Activities.AddNewAddreses;
import com.example.grocery.Activities.PaymentDetailsActivity;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.AddressModel;
import com.example.grocery.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


import static android.content.Context.MODE_PRIVATE;
import static com.example.grocery.R.drawable.selectedborder;

/**
 * Created by Mohd Afzal on 12/15/2017.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.InfoHolder> {
    private final List<AddressModel> list;
    private AppCompatActivity cc;
    private PaymentDetailsActivity context;
    private String number;

    public AddressAdapter(List<AddressModel> addressModels, PaymentDetailsActivity appCompatActivity) {
        this.list = addressModels;
        this.context = appCompatActivity;
    }

    public AddressAdapter(List<AddressModel> list, AppCompatActivity myAddress) {
        this.cc = myAddress;
        this.list = list;

    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newaddressadapter, parent, false);
        return new InfoHolder(view, list, context);
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, final int position) {
        if (context == null) {
            GradientDrawable drawable = (GradientDrawable) cc.getResources().getDrawable(selectedborder);
            drawable.setStroke(3, Color.parseColor("#" + Appearance.appSettings.getText_color()));
            holder.select.setBackgroundDrawable(cc.getResources().getDrawable(R.drawable.selectedborder));
        } else {
            GradientDrawable drawable = (GradientDrawable) context.getResources().getDrawable(selectedborder);
            drawable.setStroke(3, Color.parseColor("#" + Appearance.appSettings.getText_color()));
            holder.select.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.selectedborder));
        }
        try {
            String select = context.getResources().getString(R.string.select);
            holder.select.setText(select);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        holder.select.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        holder.name.setText(list.get(position).getName());
        if (list.get(position).getAddress() == null) {
            holder.address.setVisibility(View.GONE);
        } else {
            holder.address.setText(list.get(position).getAddress());

        }
        if (list.get(position).getPincode().getPincode() != null && !list.get(position).getPincode().getPincode().isEmpty()) {
            holder.pincode.setText(list.get(position).getPincode().getPincode());
        } else {
            holder.pincode.setVisibility(View.GONE);
        }
        if (context == null) {
            holder.select.setVisibility(View.GONE);
        }
        holder.number.setText(list.get(position).getContact_number());
        if (context != null) {
            holder.select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.dialog.dismiss();
                    if (list.get(position).getEmail().matches(list.get(position).getContact_number()) && list.get(position).getContact_number().contains("@")) {
                        number = "";
                    } else {
                        number = list.get(position).getContact_number();
                    }
                    PaymentDetailsActivity.selectedAddressId = list.get(position).getAddress_id();

                    context.address_et.setText(list.get(position).getAddress());
                    context.address_et.setError(null);
                    context.name_et.setText(list.get(position).getName());
                    context.name_et.setError(null);
                    context.mobile_et.setText(number);
                    context.email_et.setText(list.get(position).getEmail());


                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView address, name, number, pincode;

        private final List<AddressModel> list;
        private final Button select;
        private final ImageView editOption;
        private final PaymentDetailsActivity context;

        public InfoHolder(View itemView, final List<AddressModel> list, final PaymentDetailsActivity context) {

            super(itemView);
            this.list = list;
            this.context = context;
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            number = (TextView) itemView.findViewById(R.id.number);
            pincode = (TextView) itemView.findViewById(R.id.pincode);
            select = (Button) itemView.findViewById(R.id.select);
            editOption = (ImageView) itemView.findViewById(R.id.editOption);
            itemView.setOnClickListener(this);
            editOption.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (view.getId() == editOption.getId()) {
                Intent intent = new Intent(context == null ? cc : context, AddNewAddreses.class);
                if (context != null) {
                    context.dialog.dismiss();
                }
                intent.putExtra("country_id", list.get(getAdapterPosition()).getCountry().getCountry_id());
                intent.putExtra("state_id", list.get(getAdapterPosition()).getState().getState_id());
                intent.putExtra("city_id", list.get(getAdapterPosition()).getCity().getCity_id());
                intent.putExtra("pin_id", list.get(getAdapterPosition()).getPincode_id());
                intent.putExtra("name", list.get(getAdapterPosition()).getName());
                intent.putExtra("address", list.get(getAdapterPosition()).getAddress());
                intent.putExtra("contact_number", list.get(getAdapterPosition()).getContact_number());
                intent.putExtra("email", list.get(getAdapterPosition()).getEmail());
                intent.putExtra("area_id", list.get(getAdapterPosition()).getAddress_id());
                System.out.println("desxz" + list.get(getAdapterPosition()).getAddress_id());
                intent.putExtra("address_id", list.get(getAdapterPosition()).getAddress_id());
                (context == null ? cc : context).startActivity(intent);

            }

        }
    }
}
