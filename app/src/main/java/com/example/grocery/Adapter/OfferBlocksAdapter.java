package com.example.grocery.Adapter;

import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.grocery.Activities.ApplyFilter;
import com.example.grocery.Activities.Categories;
import com.example.grocery.Activities.Dashboard;
import com.example.grocery.Activities.ProductActivity;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Activities.SearchActivity;
import com.example.grocery.Model.OfferBlockModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.interfaces.VarintListner;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;

/**
 * Created by Mohd Afzal on 1/25/2018.
 */

public class OfferBlocksAdapter extends RecyclerView.Adapter<OfferBlocksAdapter.InfoHolder> {
    private final Dashboard context;
    private final List<OfferBlockModel> list;
    private final VarintListner listner;
    private String name;


    public OfferBlocksAdapter(Dashboard dashboard, List<OfferBlockModel> list,VarintListner varintListner) {
        this.context=dashboard;
        this.list=list;
        this.listner = varintListner;


    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.offerblockadapter,parent,false);

        return new InfoHolder(view,context,list);
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, final int position) {
        Picasso.with(context).load(IConstants.BASE_IMAGE_URL+list.get(position).
                getDefault_offer_block_translation().getOffer_block_image()).into(holder.offerBlockImage);
        holder.offerBlockImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onModelClick(view, position, 1);
               // listner.onModelClick(view,position,2);
               // Toast.makeText(context, "Hii", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView offerBlockImage;
        private final List<OfferBlockModel> list;
        private final Dashboard context;

        public InfoHolder(View itemView, Dashboard context, List<OfferBlockModel> list) {
            super(itemView);
            this.list=list;
            this.context=context;
            offerBlockImage=(ImageView)itemView.findViewById(R.id.offerBlockImage);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            if (list.get(getAdapterPosition()).getCategory().getIs_parent_category() == 0) {
                Intent intent = new Intent(context, ProductActivity.class);
                SearchActivity.formatedStringSearch = "";
                SearchActivity.searchMax = "";
                isViewWithCatalog = false;
                ApplyFilter.brandArrays = null;
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.minimumSeekBar = "0";
                ProductDetails.productid = 0;
                ProductActivity.sortedid = 0;
                if (list.get(getAdapterPosition()).getCategory().getCategory_translation() == null) {
                    ProductActivity.name = list.get(getAdapterPosition()).getCategory().getDefault_category_translation().getCategory_name();

                } else {
                    ProductActivity.name = list.get(getAdapterPosition()).getCategory().getCategory_translation().getCategory_name();

                }
                ProductActivity.sucat_id = list.get(getAdapterPosition()).getSection_id();
                ProductActivity.image = "true";
                context.startActivity(intent);
            }
            else{
                Intent intent = new Intent(context, Categories.class);
                if (list.get(getAdapterPosition()).getCategory().getCategory_translation() == null) {
                    name = list.get(getAdapterPosition()).getCategory().getDefault_category_translation().getCategory_name();

                } else {
                    name = list.get(getAdapterPosition()).getCategory().getCategory_translation().getCategory_name();

                }
                intent.putExtra("category_id", list.get(getAdapterPosition()).getSection_id());
                intent.putExtra("category_name", name);
            }
        }
    }
}
