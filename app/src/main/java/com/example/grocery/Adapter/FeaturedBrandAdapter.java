package com.example.grocery.Adapter;

import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.grocery.Activities.ApplyFilter;
import com.example.grocery.Activities.Dashboard;
import com.example.grocery.Activities.ProductActivity;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Activities.SearchActivity;
import com.example.grocery.Model.FeaturedBrandsModel;
import com.example.grocery.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;

/**
 * Created by Mohd Afzal on 1/20/2018.
 */

public class FeaturedBrandAdapter extends RecyclerView.Adapter<FeaturedBrandAdapter.InfoHolder>{
    private final Dashboard context;
    private final List<FeaturedBrandsModel> list;

    public FeaturedBrandAdapter(Dashboard dashboard, List<FeaturedBrandsModel> list) {
        this.context=dashboard;
        this.list=list;
    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.featuredbrandadapter,parent,false);
        return new InfoHolder(view,list,context);
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, int position) {
        Picasso.with(context).load(BASE_IMAGE_URL+list.get(position).getDefault_brand_translation().getBrand_image()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final List<FeaturedBrandsModel> list;
        private final Dashboard context;
        private final ImageView imageView;

        public InfoHolder(View itemView,List<FeaturedBrandsModel> list, Dashboard context) {
            super(itemView);
            this.list=list;
            this.context=context;
            itemView.setOnClickListener(this);
             imageView=(ImageView)itemView.findViewById(R.id.imageview);

        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context, ProductActivity.class);
            ArrayList<Integer> brandArrays=new ArrayList<>();
            brandArrays.add(list.get(getAdapterPosition()).getBrand_id());
            SearchActivity.formatedStringSearch = "";
            SearchActivity.searchMax = "";
            isViewWithCatalog = false;
            ApplyFilter.brandArrays = brandArrays;
            ApplyFilter.maximumSeekBar = "5000";
            ApplyFilter.minimumSeekBar = "0";
            ProductDetails.productid = 0;
            ProductActivity.sortedid = 0;
            if (list.get(getAdapterPosition()).getBrand_translation()==null){
                ProductActivity.name=list.get(getAdapterPosition()).getDefault_brand_translation().getBrand_name();

            }
            else{
                ProductActivity.name=list.get(getAdapterPosition()).getBrand_translation().getBrand_name();
            }
            ProductActivity.sucat_id =0;
            ProductActivity.image = "true";
            context.startActivity(intent);

        }
    }
}
