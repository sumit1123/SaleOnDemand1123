package com.example.grocery.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Model.BitmapModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.VarintListner;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mohd Afzal on 1/24/2018.
 */
public class BitmapAdapter extends RecyclerView.Adapter<BitmapAdapter.InfoHolder> {
    private final List<BitmapModel> list;
    private final ProductDetails context;
    private final VarintListner listner;
    private final boolean b;
    public BitmapAdapter(ProductDetails productDetails, List<BitmapModel> bitmapModels, boolean b, VarintListner varintListner) {
        this.list=bitmapModels;
        this.context=productDetails;
        this.listner=varintListner;
        this.b=b;
    }
    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bitmapadapter,parent,false);
        return new InfoHolder(view);
    }
    @Override
    public void onBindViewHolder(InfoHolder holder, final int position) {
        holder.imageBitmap.setImageBitmap(list.get(position).getBitmap());
        if (!b){
            holder.removeImage.setVisibility(View.GONE);
        }
        holder.removeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onModelClick(view,position,1);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class InfoHolder extends RecyclerView.ViewHolder {
        private final ImageView imageBitmap;
        private final ImageView removeImage;

        public InfoHolder(View itemView) {
            super(itemView);
            imageBitmap=(ImageView)itemView.findViewById(R.id.imageBitmap);
            removeImage=(ImageView)itemView.findViewById(R.id.removeImage);

        }
    }
}
