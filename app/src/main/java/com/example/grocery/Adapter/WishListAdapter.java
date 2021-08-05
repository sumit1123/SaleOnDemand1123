package com.example.grocery.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Activities.WishlistActivity;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.ProductModel;
import com.example.grocery.Model.WishlistModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.VolleyTask;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;

/**
 * Created by Mohd Afzal on 11/18/2017.
 */

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.InfoHolder> {


    private final WishlistActivity context;
    private final List<WishlistModel> list;
    private String off;

    public WishListAdapter(WishlistActivity productActivity, List<WishlistModel> list) {
        this.context = productActivity;
        this.list = list;
    }


    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlistadapter, parent, false);
        return new InfoHolder(view, context, list);
    }

    @Override
    public void onBindViewHolder(final InfoHolder holder, int position) {
        //labelsShared = context.getSharedPreferences("labels", MODE_PRIVATE);

        Intent intent = new Intent(context, ProductDetails.class);
        Gson gson = new Gson();
        final WishlistModel wishlistModel = list.get(position);
        String jsonString = gson.toJson(wishlistModel.getSku().getProduct().getProduct_translation());
        if (jsonString.equals("null")) {
            jsonString = gson.toJson(wishlistModel.getSku().getProduct().getDefault_product_translation());
        }

        ProductModel.ProductTranslationBean productTranslationBean = gson.fromJson(jsonString, ProductModel.ProductTranslationBean.class);
        holder.name.setText(productTranslationBean.getProduct_name());
        holder.delivery_status.setText(productTranslationBean.getDelivery_message());
        System.out.println("sdx" + BASE_IMAGE_URL + wishlistModel.getSku().getProduct().getProduct_image());
        Picasso.with(context).load(BASE_IMAGE_URL + wishlistModel.getSku().getProduct().getProduct_image())
                .into(holder.imageView);
        holder.sellingPrice.setText(String.valueOf(" " + Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.2f", wishlistModel.getSku().getMy_price())));
        holder.actualPrice.setPaintFlags(holder.actualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.actualPrice.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.2f", wishlistModel.getSku().getMarket_price()));

        Glide.with(context)
                .load(BASE_IMAGE_URL + wishlistModel.getSku().getProduct().getProduct_image())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.progressbar.setVisibility(View.GONE);
                        holder.imageView.setBackgroundResource(R.drawable.product_big);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.progressbar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.imageView);
        double a = Double.parseDouble(String.valueOf(wishlistModel.getSku().getMarket_price()));
        double b = new Double(wishlistModel.getSku().getMy_price());
        double o = (b / a) * 100;
        Double dd = new Double(100 - o);
        float offf = dd.floatValue();
        DecimalFormat df = new DecimalFormat("#.##");
        off = df.format(dd);
        System.out.println("offfff" + off);
        if (offf > 0.00) {
            holder.actualPrice.setVisibility(View.VISIBLE);
            holder.percentOff.setVisibility(View.VISIBLE);
            if (offf % 1 == 0) {
                int i = Math.round(offf);
                try {
                    String off = context.getResources().getString(R.string.off);
                    holder.percentOff.setText(i + "% " + off);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    String off = context.getResources().getString(R.string.off);
                    holder.percentOff.setText(String.format(Locale.ENGLISH, "%.2f", offf) + "% " + off);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } else {
            holder.actualPrice.setVisibility(View.GONE);
            holder.percentOff.setVisibility(View.GONE);
        }
        if (wishlistModel.getSku().getProduct().getReview_count() != null) {
            holder.ratingCount.setText("(" + wishlistModel.getSku().getProduct().getReview_count().getRating_count() + ")");
            float rating = Float.parseFloat(String.valueOf((wishlistModel.getSku().getProduct().getReview_count().getRating())));
            DecimalFormat decimal = new DecimalFormat("0.0");
            String formattedValue = decimal.format(rating);
            if (rating != 0.0) {
                holder.noRatingLayoutVisibility.setVisibility(View.VISIBLE);
                holder.ratings.setText(String.valueOf(formattedValue));
            } else {
                holder.ratings.setText(String.format(Locale.ENGLISH, "%.1f", Double.parseDouble(Appearance.appSettings.getDefault_rating())));
                Locale locale = new Locale("en");
                holder.ratings.setTextLocale(locale);
                holder.ratingCount.setVisibility(View.GONE);
            }


            String col = "#00bc58";
            if (rating == 1) {
                //RED
                col = "#ff4c4c";
            }
            if (rating <= 3 && rating > 1) {
                //YELLOW
                col = "#ffaf4f";
            }
            if (rating >= 4) {
                //GREEN
                col = "#00bc58";
            }

            GradientDrawable bgShape = (GradientDrawable) holder.colorLayout.getBackground();
            bgShape.setColor(Color.parseColor(col));
        }
        jsonString = gson.toJson(wishlistModel.getSku().getProduct().getBrand().getBrand_translation());
        if (jsonString.equals("null")) {
            jsonString = gson.toJson(wishlistModel.getSku().getProduct().getBrand().getDefault_brand_translation());
        }
        ProductModel.BrandBean.BrandTranslationBean brandTranslationBean =
                gson.fromJson(jsonString, ProductModel.BrandBean.BrandTranslationBean.class);
        if (brandTranslationBean.getBrand_name() != null && !brandTranslationBean.getBrand_name().isEmpty()) {
            holder.productdescription.setVisibility(View.VISIBLE);
            holder.productdescription.setText(brandTranslationBean.getBrand_name());
        } else {
            holder.productdescription.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final List<WishlistModel> list;
        private final WishlistActivity context;
        private final TextView ratings;
        private final TextView ratingCount;
        private final LinearLayout noRatingLayoutVisibility;
        private LinearLayout colorLayout;
        private final ImageView deleteProduct;
        private final ProgressBar progressbar;
        private final TextView productdescription;
        private final TextView delivery_status;
        TextView name;
        ImageView imageView;
        TextView actualPrice, percentOff, sellingPrice;

        public InfoHolder(View itemView, WishlistActivity context, List<WishlistModel> list) {
            super(itemView);
            this.list = list;
            this.context = context;
            name = (TextView) itemView.findViewById(R.id.productname);
            imageView = (ImageView) itemView.findViewById(R.id.productimage);
            actualPrice = (TextView) itemView.findViewById(R.id.actualprice);
            percentOff = (TextView) itemView.findViewById(R.id.percentoff);
            delivery_status = (TextView) itemView.findViewById(R.id.delivery_status);
            sellingPrice = (TextView) itemView.findViewById(R.id.sellingprice);
            ratings = (TextView) itemView.findViewById(R.id.rating);
            ratingCount = (TextView) itemView.findViewById(R.id.ratingcount);
            colorLayout = (LinearLayout) itemView.findViewById(R.id.colorLayout);

            noRatingLayoutVisibility = (LinearLayout) itemView.findViewById(R.id.visibility);
            deleteProduct = (ImageView) itemView.findViewById(R.id.deleteproduct);
            progressbar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            productdescription = (TextView) itemView.findViewById(R.id.productdescription);
            itemView.setOnClickListener(this);
            name.setOnClickListener(this);
            imageView.setOnClickListener(this);
            deleteProduct.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Gson gson = new Gson();
            final WishlistModel wishlistModel = list.get(getAdapterPosition());
            String jsonString = gson.toJson(wishlistModel.getSku().getProduct().getProduct_translation());
            if (jsonString.equals("null")) {
                jsonString = gson.toJson(wishlistModel.getSku().getProduct().getDefault_product_translation());
            }
            final ProductModel.ProductTranslationBean productTranslationBean = gson.fromJson(jsonString, ProductModel.ProductTranslationBean.class);

            if (v.getId() == name.getId() || v.getId() == imageView.getId()) {
                Intent intent = new Intent(context, ProductDetails.class);
                ProductDetails.nameofProduct = productTranslationBean.getProduct_name();

                ProductDetails.productid = list.get(getAdapterPosition()).getSku().getProduct().getProduct_id();
                context.startActivity(intent);

            } else if (v.getId() == deleteProduct.getId()) {
                context.findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
                JSONObject jsonObject = new JSONObject();
                SharedPreferences prefs = context.getSharedPreferences("UserId", MODE_PRIVATE);

                String userid = prefs.getString("user_id", "");
                String emaila = prefs.getString("email", "");
                String password = prefs.getString("pwd", "");
                String languageid = prefs.getString("language", String.valueOf(1));

                int sku_id = list.get(getAdapterPosition()).getSku().getSku_id();
                System.out.println("takeparameters" + userid + " " + sku_id + password);

                Log.i("WishlList", "onClick: " + sku_id);
                try {
                    jsonObject.put("business_id",IConstants.BUSINESS_ID);
                    jsonObject.put(("id"), userid);
                    jsonObject.put("sku_id", sku_id);
                    jsonObject.put(("password"), password);
                    jsonObject.put(("language_id"), languageid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                VolleyTask volleyTask = new VolleyTask(context, IConstants.BASE_URL + "deleteWishlist", jsonObject, Request.Method.POST);
                volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                    @Override
                    public void fnPostTaskCompleted(JSONArray response) {


                    }

                    @Override
                    public void fnPostTaskCompletedJsonObject(JSONObject response) {
                        ResponseHandler responseHandler = new ResponseHandler();
                        context.findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                        if (!responseHandler.validateResponse(context, response)) {
                            return;
                        }
                        try {
                            new CustomToast(context, response.getJSONObject("data").getString("msg"));
                            if (response.getJSONObject("data").getJSONObject("data").getJSONArray("wishlists").length() == 0) {

                                context.findViewById(R.id.clearWishListLayout).setVisibility(View.GONE);
                                context.findViewById(R.id.clearWishListLayout2).setVisibility(View.GONE);
                            }
                            context.setWishlist(response.getJSONObject("data").getJSONObject("data").getJSONArray("wishlists"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void fnErrorOccurred(String error) {
                        context.findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                    }
                });
            }
        }
    }
}
