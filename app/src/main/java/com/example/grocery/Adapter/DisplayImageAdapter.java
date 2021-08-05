package com.example.grocery.Adapter;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.R;
import com.example.grocery.interfaces.VarintListner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.grocery.R.drawable.selectedborder;
import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;

/**
 * Created by Administrator on 9/16/2017.
 */

public class DisplayImageAdapter extends RecyclerView.Adapter<DisplayImageAdapter.InfoHolder> {
    private final ProductDetails context;
    private final ArrayList<String> list;
    private final VarintListner listner;
    public static int indeximage =0;


    public DisplayImageAdapter(ProductDetails productDetails, ArrayList<String> list, VarintListner varintListner) {
        this.context= productDetails;
        this.list=list;
        this.listner=varintListner;

    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.displayimageadapter,parent,false);
        return new InfoHolder(view,context,list);
    }

    @Override
    public void onBindViewHolder(final InfoHolder holder, final int position) {
        Picasso.with(context).load(BASE_IMAGE_URL+list.get(position)).error(R.drawable.product).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onModelClick(view,position,1);
                indeximage =position;
                notifyDataSetChanged();
            }
        });
        if(indeximage ==position){
            GradientDrawable drawable = (GradientDrawable) context.getResources().getDrawable(selectedborder);
            drawable.setStroke(3, Color.parseColor("#" + Appearance.appSettings.getText_color()));
            holder.row_linearlayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.selectedborder));
        }
        else
        {
            holder.row_linearlayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.custom_border));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ArrayList<String> list;
        private final ProductDetails context;
        private final LinearLayout row_linearlayout;
        ImageView imageView;
        public InfoHolder(View itemView, ProductDetails context, ArrayList<String> list) {
            super(itemView);
            this.list=list;
            this.context=context;
            itemView.setOnClickListener(this);
            row_linearlayout=(LinearLayout)itemView.findViewById(R.id.linea);

            imageView=(ImageView)itemView.findViewById(R.id.diplayimageicon);
        }

        @Override
        public void onClick(View v) {
            //Picasso.with(context).load(BASE_IMAGE_URL+list.get(getAdapterPosition())).into(context.imageVieww);
        }
    }
}
