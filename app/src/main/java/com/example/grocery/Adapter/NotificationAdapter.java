package com.example.grocery.Adapter;

import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.example.grocery.Activities.ApplyFilter;
import com.example.grocery.Activities.Categories;
import com.example.grocery.Activities.NotificationActivity;
import com.example.grocery.Activities.ProductActivity;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Activities.SearchActivity;
import com.example.grocery.Model.NotificationModel;
import com.example.grocery.R;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;

/**
 * Created by Mohd Afzal on 9/29/2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.InfoHolder> {
    private final NotificationActivity context;
    private final List<NotificationModel> list;
    private NotificationModel.NotificationTranslationBean notificationtranslationbean;
    private String name;

    public NotificationAdapter(NotificationActivity notificationActivity, List<NotificationModel> list) {
        this.context = notificationActivity;
        this.list = list;
    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notificationadapter, parent, false);

        return new InfoHolder(view,context,list);
    }

    @Override
    public void onBindViewHolder(final InfoHolder holder, int position) {
        final Gson gson = new Gson();
        final NotificationModel notificationModel = list.get(position);


        String jsonString = gson.toJson(notificationModel.getNotification_translation());

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(notificationModel.getDefault_notification_translation());

        }
        notificationtranslationbean = gson.fromJson(jsonString,
                NotificationModel.NotificationTranslationBean.class);
        holder.title.setText(notificationtranslationbean.getNotification_title());
        holder.description.setText(Html.fromHtml(notificationtranslationbean.getNotification_description()));
        // holder.date.setText(list.get(position).getCreated_at());
        Glide.with(context)
                .load(BASE_IMAGE_URL + list.get(position).getNotification_image())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.progressbar.setVisibility(View.GONE);
                        holder.imageView.setBackgroundResource(R.drawable.gallery);


                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.progressbar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.imageView);
        String dateString=list.get(position).getCreated_at();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Date convertedDate = new Date();

        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        PrettyTime p  = new PrettyTime();

        String datetime= p.format(convertedDate);
        holder.date.setText(datetime);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ProgressBar progressbar;
        private final TextView date;
        private final NotificationActivity context;
        private final List<NotificationModel> list;
        TextView title, description;
        ImageView imageView;

        public InfoHolder(View itemView, NotificationActivity context, List<NotificationModel> list) {
            super(itemView);
            this.context=context;
            this.list=list;
            title = (TextView) itemView.findViewById(R.id.notificationtitle);
            description = (TextView) itemView.findViewById(R.id.notificationdescription);
            date = (TextView) itemView.findViewById(R.id.notificationdate);
            imageView = (ImageView) itemView.findViewById(R.id.notificationimage);
            progressbar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (list.get(getAdapterPosition()).getNotification_class_id() == 1) {
                Intent intent = new Intent(context, ProductDetails.class);
                if (list.get(getAdapterPosition()).getNotification_translation() == null) {
                      ProductDetails.nameofProduct = list.get(getAdapterPosition()).getProduct().getProduct_translation().getProduct_name();

                } else {
                    ProductDetails.nameofProduct = list.get(getAdapterPosition()).getProduct().getProduct_translation().getProduct_name();
                }
                ProductDetails.productid = list.get(getAdapterPosition()).getProduct().getProduct_id();
               // Categories.category_id = list.get(getAdapterPosition()).getCategory().getCategory_id();
               // int n =  NotificationActivity.noti_id = list.get(getAdapterPosition()).getNotification_id();
                 NotificationActivity.noti_id = list.get(getAdapterPosition()).getNotification_id();
               // Toast.makeText(context, ""+n, Toast.LENGTH_SHORT).show();
                ProductDetails.sku_id = 0;

                context.startActivity(intent);
            } else if (list.get(getAdapterPosition()).getNotification_class_id() == 2) {

                if (list.get(getAdapterPosition()).getCategory().getIs_parent_category() == 0) {
                    Intent intent = new Intent(context, ProductActivity.class);
                    SearchActivity.formatedStringSearch = "";
                    SearchActivity.searchMax = "";
                    isViewWithCatalog = false;
                    ApplyFilter.brandArrays = null;
                    ApplyFilter.maximumSeekBar = "5000";
                    ApplyFilter.minimumSeekBar = "0";
                    ProductDetails.productid = 0;
                    Categories.category_id = 0;
                    NotificationActivity.noti_id = 0;
                    ProductActivity.sortedid = 0;
                    if (list.get(getAdapterPosition()).getCategory().getCategory_translation() == null) {
                        ProductActivity.name = list.get(getAdapterPosition()).getCategory().getDefault_category_translation().getCategory_name();
                    } else {
                        ProductActivity.name = list.get(getAdapterPosition()).getCategory().getCategory_translation().getCategory_name();
                    }

                    Categories.category_id = list.get(getAdapterPosition()).getCategory().getCategory_id();
                  NotificationActivity.noti_id = list.get(getAdapterPosition()).getNotification_id();
                    //Toast.makeText(context, ""+n, Toast.LENGTH_SHORT).show();
                    ProductActivity.sucat_id = list.get(getAdapterPosition()).getSection_id();
                    ProductActivity.image = "true";
                    context.startActivity(intent);

                } else {
                    Intent intent = new Intent(context, Categories.class);
                    if (list.get(getAdapterPosition()).getCategory().getCategory_translation() == null) {
                        name = list.get(getAdapterPosition()).getCategory().getDefault_category_translation().getCategory_name();

                    } else {
                        name = list.get(getAdapterPosition()).getCategory().getCategory_translation().getCategory_name();
                    }
                    Categories.category_id = list.get(getAdapterPosition()).getCategory().getCategory_id();
                    NotificationActivity.noti_id = list.get(getAdapterPosition()).getNotification_id();
                   // Toast.makeText(context, ""+n, Toast.LENGTH_SHORT).show();

                    intent.putExtra("category_id", list.get(getAdapterPosition()).getSection_id());
                    intent.putExtra("category_name", name);
                    context.startActivity(intent);

                }

            }

        }
    }

}
