package com.example.grocery.Adapter;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocery.Activities.SellerActivity;
import com.example.grocery.Activities.SellerDetails;
import com.example.grocery.Appearances.Appearance;
import com.google.gson.Gson;
import com.example.grocery.Activities.ApplyFilter;
import com.example.grocery.Activities.ProductActivity;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Activities.SearchActivity;
import com.example.grocery.Model.BusinessModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.squareup.picasso.Picasso;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.List;
import java.util.Locale;

import static com.example.grocery.Activities.ProductActivity.image;

/**
 * Created by JMTIT-Server on 02/07/2019.
 */

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.InfoHolder>{
    private Context context;
    private List<BusinessModel> list;
    String sellerimage;

    public BusinessAdapter(Context context, List<BusinessModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_item, parent, false);
        return new InfoHolder(view,context,list);
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, final int position) {
        final Gson gson = new Gson();
        final BusinessModel buisnessnameModel = list.get(position);

        String jsonString = gson.toJson(buisnessnameModel.getSeller_detail_translation());
        if (jsonString.equals("null")) {
            jsonString = gson.toJson(buisnessnameModel.getDefault_seller_detail_translation());
        }
        final BusinessModel.DefaultSellerDetailTranslationBean buisnessTranslation = gson.fromJson(jsonString, BusinessModel.DefaultSellerDetailTranslationBean.class);
        holder.sellerimage.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        holder.buiscontactperson.setText(list.get(position).getUser().getName());
        holder.buisnessname.setText(buisnessTranslation.getSeller_name());
        holder.area.setText(buisnessTranslation.getAddress());
        holder.ratingcount.setVisibility(View.VISIBLE);
        holder.ratingcount.setText("   ");
        holder.raingbar.setRating(4.5f);
        holder.ratings.setText(String.format(Locale.ENGLISH, "%.1f", 4.5f));
        Picasso.with(context)
                .load(IConstants.BASE_IMAGE_URL + list.get(position).getSeller_image())
                .into(holder.sellerimage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //holder.image.setImageResource(R.drawable.gallery_default);
                    }
                });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                SearchActivity.formatedStringSearch = "";
                SearchActivity.searchMax = "";
                SearchActivity.searchMax = "";
                ApplyFilter.brandArrays = null;
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.minimumSeekBar = "0";
                ProductDetails.productid = 0;
                ProductActivity.sortedid = 0;
                SellerActivity.sellerid=list.get(position).getUser_id();
                ProductActivity.name = buisnessTranslation.getSeller_name();
                ProductActivity.sucat_id = 0;
                image = "true";
                SearchActivity.page_count = 1;
                context.startActivity(intent);
               //Toast.makeText(context, ""+list.get(position).getUser_id(), Toast.LENGTH_SHORT).show();

            }
        });
       holder.details.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        holder.details.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

        holder.details.setBackground(context.getResources().getDrawable(R.drawable.buttonshape));
        GradientDrawable bgShape = (GradientDrawable) holder.details.getBackground();
        bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        holder.details.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));


        holder.details.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Intent i=new Intent(context, SellerDetails.class);
                      i.putExtra("image",IConstants.BASE_IMAGE_URL + list.get(position).getSeller_image());
                   //   Toast.makeText(context, ""+IConstants.BASE_IMAGE_URL + list.get(position).getSeller_image(), Toast.LENGTH_SHORT).show();
                     context.startActivity(i);
                  }
              });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder{
        private final TextView buisnessname;
        //  private final TextView contact1;
        //  private final TextView contact2;
        private final TextView address;
        private final ImageView sellerimage;
        private final ImageView imageView;
        private final TextView tvfirstcontact;
        private final ScaleRatingBar raingbar;
        private final TextView ratings;
        private final TextView ratingcount;
        private final Context context;
        private List<BusinessModel> list;
        private TextView buiscontactperson;
        RelativeLayout relativeLayout;
        private final TextView area;
        private Button details;
        public InfoHolder(View itemView,Context context, List<BusinessModel> list) {
            super(itemView);
            this.context = context;
            this.list = list;
//            itemView.setOnClickListener(this);

            buisnessname = (TextView) itemView.findViewById(R.id.buisnessname);
            buiscontactperson = (TextView) itemView.findViewById(R.id.contactperson);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.listnerbuisness);
            raingbar = (ScaleRatingBar) itemView.findViewById(R.id.raingbar);
            ratings = (TextView) itemView.findViewById(R.id.rating);
            ratingcount = (TextView) itemView.findViewById(R.id.ratingcount);
            address = (TextView) itemView.findViewById(R.id.address);
            tvfirstcontact = (TextView) itemView.findViewById(R.id.tvfirstcontact);
            sellerimage = (ImageView) itemView.findViewById(R.id.contactimage);
            imageView = (ImageView) itemView.findViewById(R.id.wishlist);
            area=(TextView) itemView.findViewById(R.id.areaname);
            details=(Button) itemView.findViewById(R.id.details);
        }

    }
}
