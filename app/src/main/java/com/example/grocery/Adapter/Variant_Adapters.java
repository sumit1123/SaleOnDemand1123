package com.example.grocery.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Model.BusinessModel;
import com.example.grocery.Model.ParentVariantModel;
import com.example.grocery.R;
import com.google.gson.Gson;

import java.util.List;

import static com.example.grocery.Activities.ProductDetails.parentVariantId;
import static com.example.grocery.R.drawable.selectedborder;

public class Variant_Adapters extends RecyclerView.Adapter<Variant_Adapters.InfoHolder> {
    private Context context;
    private List<ParentVariantModel> list;
    String sellerimage;

    public Variant_Adapters(Context context, List<ParentVariantModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv, parent, false);
        return new InfoHolder(view,context,list);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoHolder holder, int position) {
        final Gson gson = new Gson();
        final ParentVariantModel parentVariantModel = list.get(position);

        String jsonString = gson.toJson(parentVariantModel.getParent_variant_translation());
        if (jsonString.equals("null")) {
            jsonString = gson.toJson(parentVariantModel.getDefault_parent_variant_translation());
        }
        //final BusinessModel.DefaultSellerDetailTranslationBean buisnessTranslation = gson.fromJson(jsonString, BusinessModel.DefaultSellerDetailTranslationBean.class);
         final ParentVariantModel.ParentVariantTranslationBean tanslationBean = (ParentVariantModel.ParentVariantTranslationBean) gson.fromJson(jsonString, ParentVariantModel.ParentVariantTranslationBean.class);
         Log.v("ttttttttttttt=======", String.valueOf(tanslationBean));
        if (parentVariantModel.getParent_variant_id() == parentVariantId) {
            GradientDrawable drawable = (GradientDrawable) this.context.getResources().getDrawable(selectedborder);
            drawable.setStroke(3, Color.parseColor("#" + Appearance.appSettings.getText_color()));
            holder.tv.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.selectedborder));
        } else {
            holder.tv.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.custom_border));
        }
       holder.tv.setText(tanslationBean.getParent_variant_name());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder {
        private final TextView tv;
        public InfoHolder(@NonNull View itemView, Context context, List<ParentVariantModel> list) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
