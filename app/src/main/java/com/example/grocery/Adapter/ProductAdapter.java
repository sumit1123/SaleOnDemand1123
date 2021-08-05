package com.example.grocery.Adapter;

import android.app.Dialog;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.example.grocery.Activities.Dashboard;
import com.example.grocery.Activities.ProductActivity;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Activities.SearchActivity;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.ProductAddVariantModel;
import com.example.grocery.Model.ProductModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.interfaces.VarintListner;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.VolleyTask;
import com.squareup.picasso.Picasso;
import com.willy.ratingbar.ScaleRatingBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static com.example.grocery.Activities.ApplyFilter.maximumSeekBar;
import static com.example.grocery.Activities.ApplyFilter.minimumSeekBar;
import static com.example.grocery.Activities.Dashboard.cart_count;
import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.Activities.ProductDetails.productid;
import static com.example.grocery.Activities.ProductDetails.sku_id;
import static com.example.grocery.Activities.SearchActivity.formatedStringSearch;
import static com.example.grocery.Activities.SearchActivity.layoutType;
import static com.example.grocery.Activities.SearchActivity.page_count;
import static com.example.grocery.Activities.SearchActivity.page_count_number;
import static com.example.grocery.Activities.SearchActivity.searchKeyword;
import static com.example.grocery.Activities.SearchActivity.searchMax;
import static com.example.grocery.Activities.SearchActivity.searchMin;
import static com.example.grocery.Activities.SearchActivity.sortedida;
import static com.example.grocery.Adapter.ProductAddVariantAdapter.skuIdVariantRecycler;
import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;
import static com.example.grocery.interfaces.IConstants.BASE_URL;
import static com.example.grocery.interfaces.IConstants.URL_GETPRODUCTADDTOCARTT;
import static com.example.grocery.interfaces.IConstants.Url_GETSKU;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String layouttype;
    private int subcate_id;
    private int product_id;
    private AppCompatActivity context;
    private List<ProductModel> list;
    private boolean stoploading;
    private int minimumInteger;
    private boolean set;
    private boolean count;
    private Dialog dialog;
    private boolean s;
    private List<ProductAddVariantModel> list1;
    public static int positionValue;
    private ProductModel.ProductTranslationBean productTranslationBean;
    public String parentVariant;
    public String childVariant;
    private String add;



    public ProductAdapter(ProductActivity productActivity, List<ProductModel> list, int sucat_id) {
        this.context = productActivity;
        this.list = list;
        layouttype = "";
        this.subcate_id = sucat_id;
    }

    public ProductAdapter(SearchActivity searchActivity, List<ProductModel> list) {
        set = true;
        layouttype = "";

        this.context = searchActivity;
        this.list = list;
    }

    public ProductAdapter(Dashboard dashboard, List<ProductModel> list, String s) {
        this.layouttype = s;
        stoploading = true;
        this.context = dashboard;
        this.list = list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (layouttype.matches("latest")) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.latestproductadapter, parent, false);
            return new InfoHolder(view, context, list);

        } else if (layouttype.matches("recent")) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recentviewadapter, parent, false);

            return new InfoHolder(view, context, list);
        } else if (layouttype.matches("max")) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.maxviewedproductsadapter, parent, false);

            return new InfoHolder(view, context, list);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(isViewWithCatalog ? R.layout.productadapterhorizontal : R.layout.productadapter, parent, false);
            return new InfoHolder(view, context, list);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (layouttype.matches("max")) {
            System.out.println("position max=" + position);
        }
        final ProductModel productModel = list.get(position);

        if (productModel.getSku().getWishlist() != null) {
           // productModel.setIsinWishlist(true);
        }
        final Gson gson = new Gson();

        String jsonString = gson.toJson(productModel.getProduct_translation());

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(productModel.getDefault_product_translation());

        }
        productTranslationBean = gson.fromJson(jsonString,
                ProductModel.ProductTranslationBean.class);
        positionValue = position;
        final InfoHolder holder1 = (InfoHolder) holder;

        try {
            String add = context.getResources().getString(R.string.add);
               holder1.addQuantity.setText(add);
           // holder1.addQuantity.setText(Label.productLabel.getAdd());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        jsonString = gson.toJson(productModel.getSku().getChild_variant().getChild_variant_translation());

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(productModel.getSku().getChild_variant().getDefault_child_variant_translation());

        }
        ProductModel.SkuBean.ChildVariantBean.ChildVariantTranslationBean childVariantTranslationBean = gson.fromJson(jsonString,
                ProductModel.SkuBean.ChildVariantBean.ChildVariantTranslationBean.class);

        jsonString = gson.toJson(productModel.getSku().getParent_variant().getDefault_parent_variant_translation());

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(productModel.getSku().getParent_variant().getDefault_parent_variant_translation());

        }
        ProductModel.SkuBean.ParentVariantBean.ParentVariantTranslationBean parentVariantTranslationBean = gson.fromJson(jsonString,
                ProductModel.SkuBean.ParentVariantBean.ParentVariantTranslationBean.class);
        //holder1.variantName.setTextColor(Color.parseColor("#" + sharedPreferences.getString("text_color", "#FFFFFF")));
   /*     if (childVariantTranslationBean.getChild_variant_name().matches("null")) {

            parentVariant = parentVariantTranslationBean.getParent_variant_name();
            childVariant = childVariantTranslationBean.getChild_variant_name();

        }*/

   //    parentVariant = parentVariantTranslationBean.getParent_variant_name();
   //    childVariant = childVariantTranslationBean.getChild_variant_name();
        if (childVariant == null) {
            childVariant = "";
        }
        if (parentVariant == null) {
            parentVariant = "";
        }

        String variant = parentVariant;
        if (variant != null && !variant.isEmpty() && childVariant != null && !childVariant.isEmpty()) {
            variant = variant + ", ";
        }
        variant = variant + childVariant;

        System.out.println("wdsxaz" + parentVariant + childVariant);
        if (parentVariant == null && childVariant == null) {


        } else {
            productModel.setWholeLayoutVisibility(true);

        }
        if (list.get(position).isWholeLayoutVisibility()) {

            holder1.wholeVariantLayout.setVisibility(View.VISIBLE);

        } else {
            holder1.wholeVariantLayout.setVisibility(View.GONE);

        }
        if (variant.matches("")) {
            productModel.setWholeLayoutVisibility(false);
            holder1.wholeVariantLayout.setVisibility(View.GONE);
        }
        if (list.get(position).isinWebservice) {
            holder1.add.setClickable(false);
            holder1.minus.setClickable(false);
            holder1.add.setEnabled(false);
            holder1.minus.setEnabled(false);
        } else {
            holder1.add.setClickable(true);
            holder1.minus.setClickable(true);
            holder1.add.setEnabled(true);
            holder1.minus.setEnabled(true);
        }
        holder1.variantName.setText(variant);
        holder1.wishlistLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (productModel.isinWishlist()) {
                    deleteFromWishlist();

                    notifyDataSetChanged();
                } else {
                    addToWishlist();


                    notifyDataSetChanged();
                }
            }

            private void deleteFromWishlist() {
                context.findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);

                JSONObject jsonObject = new JSONObject();
                SharedPreferences prefs = context.getSharedPreferences("UserId", MODE_PRIVATE);

                String userid = prefs.getString("user_id", "");
                String password = prefs.getString("pwd", "");
                String languageid = prefs.getString("language", String.valueOf(1));

                int sku_id = productModel.getSku().getSku_id();
                try {
                    jsonObject.put("business_id",IConstants.BUSINESS_ID);
                    jsonObject.put(("id"), userid);
                    jsonObject.put("sku_id", sku_id);
                    jsonObject.put(("password"), password);
                    jsonObject.put("language_id", languageid);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("dfcx" + jsonObject);

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
                            productModel.setIsinWishlist(false);
                            list.get(position).getSku().setWishlist(null);

                            notifyDataSetChanged();
                            System.out.println("fwdxz" + list.get(position).isinWishlist());
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

            private void addToWishlist() {
                JSONObject jsonObject = new JSONObject();
                SharedPreferences prefs = context.getSharedPreferences("UserId", MODE_PRIVATE);

                String userid = prefs.getString("user_id", "");
                String pwda = prefs.getString("pwd", "");
                String languageid = prefs.getString("language", String.valueOf(1));

                int sku_id=list.get(position).getSku().getSku_id();
                try {
                    jsonObject.put("business_id",IConstants.BUSINESS_ID);
                    jsonObject.put(("id"), userid);
                    jsonObject.put("sku_id", sku_id);
                    jsonObject.put("language_id", languageid);
                    jsonObject.put(("password"), pwda);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                context.findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
                VolleyTask volleyTask = new VolleyTask(context, IConstants.BASE_URL + "addToWishlist", jsonObject, Request.Method.POST);
                volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                    @Override
                    public void fnPostTaskCompleted(JSONArray response) {
                    }

                    @Override
                    public void fnPostTaskCompletedJsonObject(JSONObject response) {
                        ResponseHandler responseHandler = new ResponseHandler();
                        if (!responseHandler.validateResponse(context, response)) {
                            context.findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                            return;
                        }

                        try {
                            context.findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                            new CustomToast(context, response.getJSONObject("data").getString("msg"));
                            productModel.setIsinWishlist(true);
                            notifyDataSetChanged();
                            System.out.println("fwdxz" + list.get(position).isinWishlist());

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
        });
        if (productModel.isinWishlist()) {
            holder1.wishlistImage.setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()), PorterDuff.Mode.MULTIPLY);
        } else {
            holder1.wishlistImage.clearColorFilter();
        }
        if (productModel.getSku().getCart() != null && Appearance.appSettings.getShow_cart_button() == 1) {
            if (productModel.getSku().getCart().getQuantity() > 0) {
                productModel.isaddbuttonvisible = true;


                holder1.displayInteger.setText(String.valueOf(productModel.getSku().getCart().getQuantity()));
                productModel.setQuantityvariant(productModel.getSku().getCart().getQuantity());
            } else {
                productModel.isaddbuttonvisible = false;
            }
        }
        if (productModel.isaddbuttonvisible()) {
            holder1.addQuantity.setVisibility(View.GONE);
            holder1.cardaddquantity.setVisibility(View.VISIBLE);

        } else {
            holder1.addQuantity.setVisibility(View.VISIBLE);
            holder1.cardaddquantity.setVisibility(View.GONE);
        }
        holder1.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).setIsinWebservice(true);
                notifyDataSetChanged();
                sendReq(list.get(position).getQuantityvariant() + 1, position);
                notifyDataSetChanged();
            }
        });
        holder1.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).setIsinWebservice(true);

//                notifyDataSetChanged();
                if (productModel.getQuantityvariant() == 1) {
                    sendReq(productModel.getQuantityvariant() - 1, position);

                    productModel.setIsaddbuttonvisible(false);
                    notifyDataSetChanged();
                } else {

                    sendReq(productModel.getQuantityvariant() - 1, position);
                }
                notifyDataSetChanged();
            }
        });
        holder1.displayInteger.setText(String.valueOf(productModel.getQuantityvariant()));
        holder1.name.setText(productTranslationBean.getProduct_name());
        holder1.addQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productModel.setIsaddbuttonvisible(true);

                sendReq(productModel.getQuantityvariant() + 1, position);

            }
        });
        if (productModel.getSku_count().getSku_count() == 1) {
            productModel.isvariantone = true;
            holder1.variantName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            holder1.variantName.setBackgroundResource(0);

        } else {
            productModel.isvariantone = false;
            holder1.variantName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.dropdownnew, 0);
        }
        if (productModel.isvariantone) {
            holder1.variantName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            holder1.variantName.setBackgroundResource(0);

        } else {
            holder1.variantName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.dropdownend, 0);

        }
        holder1.variantName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!productModel.isvariantone) {
                    minimumInteger = 1;
                    product_id = list.get(position).getProduct_id();
                    dialog = new Dialog(context, R.style.AlertDialogStyle_Default);

                    dialog.setContentView(R.layout.productaddquantity);
                    TextView availableVariationforText = (TextView) dialog.findViewById(R.id.availableVariationforText);

                    TextView textView = (TextView) dialog.findViewById(R.id.productName);
                    textView.setText(productTranslationBean.getProduct_name());
                    getList(position);
                    try {
                        String variation = context.getResources().getString(R.string.available_variations_for);
                        availableVariationforText.setText(variation);

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        holder1.progressbar.setVisibility(View.VISIBLE);
        //holder1.imageView.layout(0,0,0,0);
        if (productModel.isImageNotAvailable()) {
            //requirement came thats y set image source else else set PIcasso
            //  Picasso.with(context).load(R.drawable.product).into(holder1.imageView);
            holder1.imageView.setImageResource(R.drawable.product);
            holder1.progressbar.setVisibility(View.GONE);

        } else {

            Picasso.with(context)
                    .load(BASE_IMAGE_URL + productModel.getProduct_image())
                    .into(holder1.imageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            holder1.progressbar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            holder1.imageView.setImageResource(R.drawable.product);
                            holder1.progressbar.setVisibility(View.GONE);
                        }
                    });
        }

        if (productModel.getSku().getMy_price() != 0) {

            holder1.sellingPrice.setVisibility(View.VISIBLE);

            if (productModel.getSku().getMy_price() > 9999.99 || productModel.getSku().getMy_price() % 1 == 0) {
                holder1.sellingPrice.setText(String.valueOf(Appearance.appTranslation.getCurrency() + Math.round(productModel.getSku().getMy_price())));

            } else {
                holder1.sellingPrice.setText(String.valueOf(Appearance.appTranslation.getCurrency() + productModel.getSku().getMy_price()));
            }

            if (productModel.getSku().getMarket_price() > 9999.99 || productModel.getSku().getMarket_price() % 1 == 0) {
                holder1.actualPrice.setText(Appearance.appTranslation.getCurrency() + String.valueOf((long) productModel.getSku().getMarket_price()));

            } else {
                holder1.actualPrice.setText(Appearance.appTranslation.getCurrency() + String.valueOf(productModel.getSku().getMarket_price()));

            }
            holder1.actualPrice.setPaintFlags(holder1.actualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        } else {
            holder1.sellingPrice.setVisibility(View.GONE);
        }

        jsonString = gson.toJson(productModel.getBrand().getBrand_translation());
        if (jsonString.equals("null")) {
            jsonString = gson.toJson(productModel.getBrand().getDefault_brand_translation());
        }
        ProductModel.BrandBean.BrandTranslationBean brandTranslationBean =
                gson.fromJson(jsonString, ProductModel.BrandBean.BrandTranslationBean.class);


        if (brandTranslationBean.getBrand_name() != null && !brandTranslationBean.getBrand_name().isEmpty()) {
            holder1.productDescription.setVisibility(View.VISIBLE);
            holder1.productDescription.setText(brandTranslationBean.getBrand_name());
        } else {
            holder1.productDescription.setVisibility(View.GONE);
        }
        double calculatedMarketPrice = Double.parseDouble(String.valueOf(productModel.getSku().getMarket_price()));
        double calculatedMyPrice = new Double(productModel.getSku().getMy_price());
        double calculatedPercentoff = (calculatedMyPrice / calculatedMarketPrice) * 100;
        Double doublePercentOff = new Double(100 - calculatedPercentoff);
        float offf = doublePercentOff.floatValue();
        DecimalFormat df = new DecimalFormat("#.##");
        if (offf > 0.00) {
            holder1.actualPrice.setVisibility(View.VISIBLE);
            holder1.percentoff.setVisibility(View.VISIBLE);
            try {
                if (offf % 1 == 0) {
                    int i = Math.round(offf);
                    String offs = context.getResources().getString(R.string.off);
                    holder1.percentoff.setText(i + "% " + offs);
                } else {
                    String offs = context.getResources().getString(R.string.off);
                  holder1.percentoff.setText(String.format(Locale.ENGLISH, "%.2f", offf) + "% "+ offs);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            holder1.actualPrice.setVisibility(View.GONE);
            holder1.percentoff.setVisibility(View.GONE);
        }
        int color = Color.parseColor("#" + Appearance.appSettings.getText_color());
        DrawableCompat.setTint(context.getResources().getDrawable(R.drawable.starfilled),
                color);
        holder1.raingbar.setEmptyDrawableRes(R.drawable.startempty);
        holder1.raingbar.setFilledDrawable(context.getResources().getDrawable(R.drawable.starfilled));
        holder1.raingbar.setClickable(false);

        if (productModel.getReview_count() != null) {

            holder1.noRatingVisibilityLayout.setVisibility(View.VISIBLE);
            holder1.ratingcount.setVisibility(View.VISIBLE);
            holder1.ratingcount.setText("(" + productModel.getReview_count().getRating_count() + ")");
            float rating = Float.parseFloat(String.valueOf((productModel.getReview_count().getRating())));
            DecimalFormat decimal = new DecimalFormat("0.0");
            if (rating != 0.0) {
                holder1.noRatingVisibilityLayout.setVisibility(View.VISIBLE);
                holder1.raingbar.setRating(rating);
                holder1.rating1.setText(String.format(Locale.ENGLISH, "%.1f", rating));
                holder1.ratings.setText(String.format(Locale.ENGLISH, "%.1f", rating));

                setStarColor(rating, holder1.raingbar);
            }
        } else {
            /*LayerDrawable stars = (LayerDrawable) holder1.raingbar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.parseColor("#" + sharedPreferences.getString("text_color", "#FFFFFF")), PorterDuff.Mode.SRC_ATOP);
*/
            holder1.raingbar.setRating(Float.parseFloat(Appearance.appSettings.getDefault_rating()));
            holder1.rating1.setText(String.format(Locale.ENGLISH, "%.1f", Double.parseDouble(Appearance.appSettings.getDefault_rating())));

            holder1.ratings.setText(String.format(Locale.ENGLISH, "%.1f", Double.parseDouble(Appearance.appSettings.getDefault_rating())));
            Locale locale = new Locale("en");
            holder1.ratings.setTextLocale(locale);
            setStarColor(holder1.raingbar.getRating(), holder1.raingbar);
            holder1.ratingcount.setVisibility(View.GONE);
        }
        if (stoploading) {
            if (set && !count) {
                try {
                    Toast.makeText(context, "No More Products available", Toast.LENGTH_SHORT).show();
                 //   new CustomToast(context, Label.productLabel.getNo_more_products_available());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                count = true;
            }
        }

        Log.i("MyPostion", "onBindViewHolder: " + position);
        if (position == this.getItemCount() - 2 && !stoploading && page_count < page_count_number) {
            if (maximumSeekBar == null || minimumSeekBar == null) {
                maximumSeekBar = String.valueOf(5000);
                minimumSeekBar = String.valueOf(0);
            }

            ViewGroup.MarginLayoutParams marginLayoutParams =
                    (ViewGroup.MarginLayoutParams) context.findViewById(R.id.swipeRefreshLayout).getLayoutParams();
            int dpValue = 60; // margin in dips
            float d = context.getResources().getDisplayMetrics().density;
            int margin = (int) (dpValue * d);
            marginLayoutParams.setMargins(0, 0, 0, margin);
            context.findViewById(R.id.swipeRefreshLayout).setLayoutParams(marginLayoutParams);
            context.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
            SharedPreferences prefs = context.getSharedPreferences("UserId", MODE_PRIVATE);
            String userid = prefs.getString("user_id", "");
            String email = prefs.getString("email", "");
            String pwd = prefs.getString("pwd", "");
            String languageid = prefs.getString("language", String.valueOf(1));
            JSONObject jsonObject = new JSONObject();
            page_count = page_count + 1;
            try {
                jsonObject.put("business_id",IConstants.BUSINESS_ID);
                jsonObject.put("id", userid);
                jsonObject.put("password", pwd);
                jsonObject.put("category_id", subcate_id == 0 ? "" : subcate_id);
                jsonObject.put("max_price", searchMax);
                jsonObject.put("min_price", searchMin);
                jsonObject.put("order", sortedida);
                jsonObject.put("brand", formatedStringSearch);
                jsonObject.put("keyword", searchKeyword == null ? "" : searchKeyword);
                jsonObject.put("product_id", productid);
                jsonObject.put("page", page_count);
                jsonObject.put("language_id", languageid);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(IConstants.URL_FIlteredPRoduct);
            System.out.println(jsonObject);
            VolleyTask volleyTask = new VolleyTask(context, IConstants.URL_FIlteredPRoduct, jsonObject, Request.Method.POST);
            volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                @Override
                public void fnPostTaskCompleted(JSONArray response) {

                }

                @Override
                public void fnPostTaskCompletedJsonObject(JSONObject response) {
                    context.findViewById(R.id.progressBar).setVisibility(View.GONE);
                    ViewGroup.MarginLayoutParams marginLayoutParams =
                            (ViewGroup.MarginLayoutParams) context.findViewById(R.id.swipeRefreshLayout).getLayoutParams();
                    marginLayoutParams.setMargins(0, 0, 0, 0);
                    context.findViewById(R.id.swipeRefreshLayout).setLayoutParams(marginLayoutParams);

                    if (!new ResponseHandler().validateResponse(context, response)) {
                        return;
                    }
                    try {

                        JSONArray jsonArray1 = response.getJSONObject("data").getJSONObject("data").getJSONArray("products");

                        Gson gson = new Gson();
                        List<ProductModel> list1 = Arrays.asList(gson.fromJson(jsonArray1.toString(), ProductModel[].class));
                        if (jsonArray1.length() == 0) {

                            stoploading = true;

                        }
                        list.addAll(list1);

                        layoutType = 1;
                        notifyDataSetChanged();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void fnErrorOccurred(String error) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {

                        }
                    }, 2000);
                }
            });


        }
        if (Appearance.appSettings.getShow_cart_button() == 0) {
            holder1.addQuantity.setVisibility(View.GONE);
            productModel.setWholeLayoutVisibility(false);
            holder1.wholeVariantLayout.setVisibility(View.GONE);
        }
        if (layouttype.matches("latest") || layouttype.matches("recent") || layouttype.matches("max")) {
            holder1.variantName.setVisibility(View.GONE);
            holder1.noRatingVisibilityLayout.setVisibility(View.GONE);
            holder1.productDescription.setVisibility(View.GONE);
            holder1.addQuantity.setVisibility(View.GONE);
            holder1.cardaddquantity.setVisibility(View.GONE);
            holder1.wishlistImage.setVisibility(View.GONE);
            holder1.raingbar.setVisibility(View.GONE);
        }

        if (isViewWithCatalog && !layouttype.matches("latest") && !layouttype.matches("recent") && !layouttype.matches("max")) {
            if (Appearance.appSettings.getShow_cart_button() == 0) {
                holder1.noRatingVisibilityLayout1.setVisibility(View.GONE);
            } else {
                holder1.noRatingVisibilityLayout.setVisibility(View.GONE);
            }
        }
        if (isViewWithCatalog) {
            holder1.variantName.setVisibility(View.VISIBLE);
            productModel.setWholeLayoutVisibility(true);

        } else {
            holder1.variantName.setVisibility(View.GONE);
            holder1.cardaddquantity.setVisibility(View.GONE);
            holder1.addQuantity.setVisibility(View.GONE);
            productModel.setWholeLayoutVisibility(false);
            holder1.wholeVariantLayout.setVisibility(View.GONE);
        }
    }

    public void getList(final int position) {
        context.findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = context.getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs.getString("user_id", "");
        String pwda = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));
        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwda);
            jsonObject.put("product_id", product_id);
            jsonObject.put("language_id", languageid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("gghg" + jsonObject);
        String url = BASE_URL + "getProductSkus";
        VolleyTask volleyTask = new VolleyTask(context, url.replace(" ", "%20"), jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                context.findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                if (!new ResponseHandler().validateResponse(context, response)) {
                    return;
                }
                try {
                    skuIdVariantRecycler = -1;
                    String status = response.getString("status");
                    if (response.getJSONObject("data").getJSONObject("data").get("skus").equals("")) {
                        list.get(position).setIsaddbuttonvisible(true);
                        if (list.get(position).getSku().getCart() != null) {
                            list.get(position).getSku().getCart().setQuantity(minimumInteger);
                        }
                        list.get(position).setQuantityvariant(minimumInteger);
                        notifyDataSetChanged();
                        new CustomToast(context, response.getJSONObject("data").getString("msg"));

                        skuIdVariantRecycler = list.get(position).getSku().getSku_id();
                        Dashboard.cart_count = response.getJSONObject("data").getJSONObject("data").getInt("cart_count");
                        new CartCountUtil(context);
                    } else {
                        dialog.show();
                        JSONObject jsonObject1 = response.getJSONObject("data").getJSONObject("data");
                        JSONArray jsonArray = jsonObject1.getJSONArray("skus");
                        Gson gson = new Gson();
                        list1 = Arrays.asList(gson.fromJson(jsonArray.toString(), ProductAddVariantModel[].class));
                        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.productAddRecycler);

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                        recyclerView.setLayoutManager(layoutManager);
                        ProductAddVariantAdapter productAddVariantAdapter = new ProductAddVariantAdapter(context, list1, list.get(position).getSku().getSku_id(), new VarintListner() {
                            @Override
                            public void onModelClick(View view, int position1, int sku_id) {
                                dialog.dismiss();
                                context.findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
                                JSONObject jsonObject = new JSONObject();
                                SharedPreferences prefs = context.getSharedPreferences("UserId", MODE_PRIVATE);
                                String userid = prefs.getString("user_id", "");
                                String pwda = prefs.getString("pwd", "");
                                String languageid = prefs.getString("language", String.valueOf(1));
                                try {
                                    jsonObject.put("business_id", IConstants.BUSINESS_ID);
                                    jsonObject.put("id", userid);
                                    jsonObject.put("password", pwda);
                                    jsonObject.put("sku_id", sku_id);
                                    jsonObject.put("language_id", languageid);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("gghg" + jsonObject);

                                VolleyTask volleyTask = new VolleyTask(context, Url_GETSKU, jsonObject, Request.Method.POST);
                                volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                                    @Override
                                    public void fnPostTaskCompleted(JSONArray response) {
                                    }

                                    @Override
                                    public void fnPostTaskCompletedJsonObject(JSONObject response) {
                                        context.findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                                        if (!new ResponseHandler().validateResponse(context, response)) {
                                            return;
                                        }
                                        Gson gson = new Gson();

                                        try {
                                            ProductModel productModel = gson.fromJson(response.getJSONObject("data").getJSONObject("data").toString(), ProductModel.class);
                                            list.set(position, productModel);
                                            notifyDataSetChanged();
                                            context.findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                                            notifyDataSetChanged();

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void fnErrorOccurred(String error) {
                                    }
                                });
                            }
                        });
                        recyclerView.setAdapter(productAddVariantAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fnErrorOccurred(String error) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class InfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final List<ProductModel> list;
        private final AppCompatActivity context;
        private final TextView ratings;
        private final TextView ratingcount;
        private final TextView productDescription;
        private final LinearLayout noRatingVisibilityLayout;
        public final ProgressBar progressbar;
        private final Button addQuantity;
        private final LinearLayout cardaddquantity;
        private final RelativeLayout add;
        private final RelativeLayout minus;
        private final ImageView wishlistImage;
        private final FrameLayout wishlistLayout;
        private final TextView variantName;
        private final TextView displayInteger;
        private final LinearLayout wholeVariantLayout;
        private final LinearLayout clickEventLayout;
        private final ScaleRatingBar raingbar;
        private final TextView rating1;
        private final LinearLayout noRatingVisibilityLayout1;
        TextView name;
        public ImageView imageView;
        TextView actualPrice, percentoff, sellingPrice;

        public InfoHolder(View itemView, AppCompatActivity context, List<ProductModel> list) {
            super(itemView);
            this.list = list;
            this.context = context;
            name = (TextView) itemView.findViewById(R.id.productname);
            rating1 = (TextView) itemView.findViewById(R.id.rating1);

            imageView = (ImageView) itemView.findViewById(R.id.productimage);
            actualPrice = (TextView) itemView.findViewById(R.id.actualprice);
            percentoff = (TextView) itemView.findViewById(R.id.percentoff);
            sellingPrice = (TextView) itemView.findViewById(R.id.sellingprice);
            ratings = (TextView) itemView.findViewById(R.id.rating);
            ratingcount = (TextView) itemView.findViewById(R.id.ratingcount);
            productDescription = (TextView) itemView.findViewById(R.id.productdescription);
            wishlistImage = (ImageView) itemView.findViewById(R.id.wishlistImage);
            wishlistLayout = (FrameLayout) itemView.findViewById(R.id.wishlistLayout);
            noRatingVisibilityLayout = (LinearLayout) itemView.findViewById(R.id.visibility);
            noRatingVisibilityLayout1 = (LinearLayout) itemView.findViewById(R.id.visibility1);

            progressbar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            addQuantity = (Button) itemView.findViewById(R.id.addQuantity);
            cardaddquantity = (LinearLayout) itemView.findViewById(R.id.cardaddquantity);
            add = (RelativeLayout) itemView.findViewById(R.id.plus);
            minus = (RelativeLayout) itemView.findViewById(R.id.minus);
            wholeVariantLayout = (LinearLayout) itemView.findViewById(R.id.wholeVariantlayout);
            clickEventLayout = (LinearLayout) itemView.findViewById(R.id.sdxaz);
            variantName = (TextView) itemView.findViewById(R.id.parentName);
            raingbar = (ScaleRatingBar) itemView.findViewById(R.id.raingbar);
            displayInteger = (TextView) itemView.findViewById(
                    R.id.quantity);
            itemView.setOnClickListener(this);
            addQuantity.setOnClickListener(this);
            add.setOnClickListener(this);
            minus.setOnClickListener(this);
            imageView.setOnClickListener(this);
            productDescription.setOnClickListener(this);
            name.setOnClickListener(this);
            wishlistLayout.setOnClickListener(this);
            clickEventLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Gson gson = new Gson();
            final ProductModel productModel = list.get(getAdapterPosition());
            String jsonString = gson.toJson(productModel.getProduct_translation());
            if (jsonString.equals("null")) {
                jsonString = gson.toJson(productModel.getDefault_product_translation());
            }
            ProductModel.ProductTranslationBean productTranslationBean = gson.fromJson(jsonString, ProductModel.ProductTranslationBean.class);

            if (v.getId() == imageView.getId() || v.getId() == clickEventLayout.getId() || v.getId() == productDescription.getId()) {
                Intent intent = new Intent(context, ProductDetails.class);
                ProductDetails.nameofProduct = productTranslationBean.getProduct_name();
                ProductDetails.productid = productTranslationBean.getProduct_id();
                ProductDetails.sku_id = productModel.getSku().getSku_id();
                context.startActivity(intent);
            }
        }
    }

    public boolean sendReq(final int minimumInteger, final int position) {
        context.findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs1 = context.getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs1.getString("user_id", "");
        String pwd = prefs1.getString("pwd", "");
        String languageid = prefs1.getString("language", String.valueOf(1));
        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("sku_id", list.get(position).getSku().getSku_id());
            jsonObject.put("quantity", minimumInteger);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("language_id", languageid);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = URL_GETPRODUCTADDTOCARTT;

        System.out.println("wedxs" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(context, url, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                context.findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                try {
                    s = Boolean.parseBoolean(response.getString("status"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (!new ResponseHandler().validateResponse(context, response)) {

                    list.get(position).setIsinWebservice(false);
                    notifyDataSetChanged();
                    return;
                }
                try {

                    if (list.get(position).getSku().getCart() != null) {
                        list.get(position).getSku().getCart().setQuantity(minimumInteger);
                    }
                    list.get(position).setQuantityvariant(minimumInteger);
                    list.get(position).setIsinWebservice(false);

                    notifyDataSetChanged();
                    cart_count = response.getJSONObject("data").getJSONObject("data").getInt("cart_count");
                    new CartCountUtil(context);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fnErrorOccurred(String error) {
                context.findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                list.get(position).setIsinWebservice(false);
            }
        });
        return s;
    }

    public String setStarColor(float rating, ScaleRatingBar ratingbar) {

        String s = "#00bc58";
        try {
            if (rating == 1) {
                //RED
                ratingbar.setFilledDrawableRes(R.drawable.star_filled_red);
            }
            if (rating <= 3.5 && rating > 1) {

                ratingbar.setFilledDrawableRes(R.drawable.star_filled_yellow);
                //YELLOW
            }
            if (rating > 3.5) {

                ratingbar.setFilledDrawableRes(R.drawable.start_filled_green);
                //GREEN
            }
        } catch (Exception e) {
        }
        return s;
    }

}