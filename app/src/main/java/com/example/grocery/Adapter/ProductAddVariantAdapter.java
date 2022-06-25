package com.example.grocery.Adapter;

import android.content.SharedPreferences;
import android.graphics.Paint;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Model.ProductAddVariantModel;
import com.example.grocery.Model.ProductModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.VarintListner;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Mohd Afzal on 12/3/2017.
 */

public class ProductAddVariantAdapter extends RecyclerView.Adapter<ProductAddVariantAdapter.InfoHolder> {

    private final List<ProductAddVariantModel> list;
    private final AppCompatActivity context;
    private final int sku_ids;
    private int row_index = 0;
    public static int skuIdVariantRecycler;
    private String jsonString;
    public VarintListner onClickListener;



    public ProductAddVariantAdapter(AppCompatActivity context, List<ProductAddVariantModel> list, int sku_id, VarintListner varintListner) {
        this.list = list;
        this.context = context;
        this.onClickListener = varintListner;
        this.sku_ids = sku_id;
    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productaddvariantadapter, parent, false);

        return new InfoHolder(view);
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, final int position) {
        if (list.get(position).getQuantity() <1&&list.get(position).getProduct().getTrack_inventory() == 1) {
            holder.outofstock.setVisibility(View.VISIBLE);
        }
        ProductAddVariantModel productModel = list.get(position);
        Gson gson = new Gson();
        jsonString = gson.toJson(productModel.getChild_variant().getChild_variant_translation());

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(productModel.getChild_variant().getDefault_child_variant_translation());

        }
        ProductModel.SkuBean.ChildVariantBean.ChildVariantTranslationBean childVariantTranslationBean = gson.fromJson(jsonString,
                ProductModel.SkuBean.ChildVariantBean.ChildVariantTranslationBean.class);

        jsonString = gson.toJson(productModel.getParent_variant().getDefault_parent_variant_translation());

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(productModel.getParent_variant().getDefault_parent_variant_translation());

        }
        ProductModel.SkuBean.ParentVariantBean.ParentVariantTranslationBean parentVariantTranslationBean = gson.fromJson(jsonString,
                ProductModel.SkuBean.ParentVariantBean.ParentVariantTranslationBean.class);
        if (childVariantTranslationBean.getChild_variant_name() == null) {
            holder.varianName.setText(parentVariantTranslationBean.getParent_variant_name());
        } else {
            holder.varianName.setText(parentVariantTranslationBean.getParent_variant_name() + " - " + childVariantTranslationBean.getChild_variant_name());

        }
        holder.varianActual.setText(Appearance.appTranslation.getCurrency() + productModel.getMarket_price());
        holder.varianActual.setPaintFlags(holder.varianActual.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.varianSelling.setText(Appearance.appTranslation.getCurrency() + productModel.getMy_price());
        holder.variantLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                onClickListener.onModelClick(view, position, list.get(position).getSku_id());

                notifyDataSetChanged();
            }
        });
        if (list.get(position).getSku_id() == sku_ids) {
            skuIdVariantRecycler = position;
            holder.variantLayout.setBackground(context.getResources().getDrawable(R.drawable.selectedvariantbackground));
            holder.varianName.setTextColor(context.getResources().getColor(R.color.white));
            holder.varianActual.setTextColor(context.getResources().getColor(R.color.white));
            holder.varianSelling.setTextColor(context.getResources().getColor(R.color.white));


        } else {
            holder.varianName.setTextColor(context.getResources().getColor(R.color.black));
            holder.varianActual.setTextColor(context.getResources().getColor(R.color.iconColor));
            holder.varianSelling.setTextColor(context.getResources().getColor(R.color.black));

            holder.variantLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        if (list.get(position).getMarket_price()==0||list.get(position).getMarket_price()>=list.get(position).getMy_price()){
            holder.varianActual.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder {
        private final TextView varianName, outofstock;
        private final TextView varianSelling;
        private final TextView varianActual;
        private LinearLayout variantLayout;

        public InfoHolder(View itemView) {
            super(itemView);
            varianName = (TextView) itemView.findViewById(R.id.textvariant);
            variantLayout = (LinearLayout) itemView.findViewById(R.id.variantlayout);
            varianActual = (TextView) itemView.findViewById(R.id.textvariantpriceactual);
            varianSelling = (TextView) itemView.findViewById(R.id.textvariantpriceselling);
            outofstock = (TextView) itemView.findViewById(R.id.outofstock);

        }
    }
}
