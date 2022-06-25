package com.example.grocery.Adapter;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.example.grocery.Activities.OrderDetails;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.CartProductModel;
import com.example.grocery.Model.ProductModel;
import com.example.grocery.R;
import com.example.grocery.utils.EditTextColorChanger;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;

/**
 * Created by Mohd Afzal on 9/29/2017.
 */

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.InfoHolder> {


    private final OrderDetails context;
    private final List<CartProductModel> list;
    //private final SharedPreferences labelsShared;
    private String off;
    private Dialog dialog;
    private RatingBar ratingBar;
    private EditText description;
    private int starsa;
    private double gsttotal;
    private double total;
    private CartProductModel.ProductBean.ProductTranslationBean orderTranslation;
    private String parentVariant;
    private String childVariant;
    public static int entered;
    public static int productId;
    private EditText pincode;
    private AppCompatEditText editQuantity;

    public CartProductAdapter(OrderDetails productActivity, List<CartProductModel> list) {
        this.context = productActivity;
        this.list = list;
        //labelsShared = context.getSharedPreferences("labels", MODE_PRIVATE);

    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartproductadapter, parent, false);
        return new InfoHolder(view, context, list);
    }

    @Override
    public void onBindViewHolder(final InfoHolder holder, int position) {
        final Gson gson = new Gson();
        final CartProductModel orderDetails = list.get(position);
        String jsonString = gson.toJson(orderDetails.getProduct().getProduct_translation());
        System.out.println("zdnfjc" + jsonString);

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(orderDetails.getProduct().getDefault_product_translation());
            System.out.println("zdnfjc" + jsonString);

        }
        orderTranslation = gson.fromJson(jsonString,
                CartProductModel.ProductBean.ProductTranslationBean.class);
        holder.name.setText(orderTranslation.getProduct_name());
        holder.deliverydetails.setText(orderTranslation.getDelivery_message());


        Glide.with(context)
                .load(BASE_IMAGE_URL + list.get(position).getProduct().getProduct_image())
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


        try {
            String qty = context.getResources().getString(R.string.qty);
            holder.quantity.setText(qty + " : " + String.valueOf(list.get(position).getQuantity()));
           //  holder.quantity.setText(String.valueOf(list.get(position).getQuantity()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (list.get(position).getMy_price() * list.get(position).getQuantity() % 1 == 0) {
            int i = (int) list.get(position).getMy_price() * list.get(position).getQuantity();
            holder.sellingprice.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%d", i));

        } else {
            holder.sellingprice.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.2f", list.get(position).getMy_price() * list.get(position).getQuantity()));
        }
        System.out.println("market=" + list.get(position).getMarket_price() + "myprice=" + list.get(position).getMy_price());
        if (list.get(position).getMarket_price() <= list.get(position).getMy_price()) {

            holder.actualprice.setVisibility(View.GONE);
            holder.percentoff.setVisibility(View.GONE);

        } else {

            holder.actualprice.setVisibility(View.VISIBLE);
            holder.percentoff.setVisibility(View.VISIBLE);
            if (list.get(position).getMarket_price() * list.get(position).getQuantity() % 1 == 0) {
                int i = (int) (list.get(position).getMarket_price() * list.get(position).getQuantity());
                holder.actualprice.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%d", i));

            } else {
                holder.actualprice.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.2f", list.get(position).getMarket_price() * list.get(position).getQuantity()));
            }

//            String.format(Locale.ENGLISH, "%.2f","");
            holder.actualprice.setPaintFlags(holder.actualprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            double marketprice = Double.parseDouble(String.valueOf(list.get(position).getMarket_price()));
            double myprice = new Double(list.get(position).getMy_price());
            System.out.println("market" + list.get(position).getMarket_price() + "my" + list.get(position).getMy_price());
            double calculatedPercent = (myprice / marketprice) * 100;
            System.out.println("double" + calculatedPercent);
            Double dd = new Double(100 - calculatedPercent);
            float offf = dd.floatValue();
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            off = decimalFormat.format(dd);
            System.out.println("offfff" + off);
            String offd = context.getResources().getString(R.string.off);
            String offs = context.getResources().getString(R.string.off);

            try {
                if (offf % 1 == 0) {
                    int i = Math.round(offf);
                 //  String off = context.getResources().getString(R.string.off);
                    holder.percentoff.setText(i + "% " + offd);
                } else {
                    holder.percentoff.setText(String.format(Locale.ENGLISH, "%.2f", offf) + "% " + offs);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        }
        if (list.get(position).getGst() > 0) {
            holder.gst.setVisibility(View.VISIBLE);
            double ss = list.get(position).getGst();

            double percentgst = ss / 100;

            String s = String.format(Locale.ENGLISH, "%.2f",
                    percentgst * list.get(position).getMy_price() * list.get(position).getQuantity());


            holder.gst.setText(Html.fromHtml("<b>" + list.get(position).getGst() + "</b>" + "% GST Applied, " +
                    Appearance.appTranslation.getCurrency() + "<b>" + s + "</b>"));
            gsttotal = gsttotal + percentgst * list.get(position).getMy_price() * list.get(position).getQuantity();

            context.productGst.setText(String.valueOf(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH,"%.2f",gsttotal)));
        } else {
            holder.gst.setVisibility(View.GONE);

        }
        jsonString = gson.toJson(orderDetails.getChild_variant().getChild_variant_translation());

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(orderDetails.getChild_variant().getDefault_child_variant_translation());

        }
        CartProductModel.ChildVariantBean.ChildVariantTranslationBean childVariantTranslationBean = gson.fromJson(jsonString,
                CartProductModel.ChildVariantBean.ChildVariantTranslationBean.class);

        jsonString = gson.toJson(orderDetails.getParent_variant().getDefault_parent_variant_translation());

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(orderDetails.getParent_variant().getDefault_parent_variant_translation());

        }
        ProductModel.SkuBean.ParentVariantBean.ParentVariantTranslationBean parentVariantTranslationBean = gson.fromJson(jsonString,
                ProductModel.SkuBean.ParentVariantBean.ParentVariantTranslationBean.class);

        /*DrawableCompat.setTint(context.getDrawable(R.drawable.wishlistred), Color.parseColor("#" + sharedPreferences.getString("text_color", "#FFFFFF")));
        Drawable mDrawable = context.getResources().getDrawable(R.drawable.wishlistred);
        mDrawable.setColorFilter(new
                PorterDuffColorFilter(Color.parseColor("#" + sharedPreferences.getString("text_color", "#FFFFFF")), PorterDuff.Mode.SRC_IN));*/
        holder.parentVariantName.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
     /*   if (childVariantTranslationBean.getChild_variant_name().equals("null")) {

            parentVariant = parentVariantTranslationBean.getParent_variant_name();
            childVariant = childVariantTranslationBean.getChild_variant_name();

        }*/

       // parentVariant = parentVariantTranslationBean.getParent_variant_name();
      //  childVariant = childVariantTranslationBean.getChild_variant_name();
        if (childVariant == null) {
            childVariant = "";
        }
        if (parentVariant == null) {
            parentVariant = "";
        }
        String variant = parentVariant;
        if (variant != null && !variant.isEmpty() && childVariant != null && !childVariant.matches("")) {
            variant = variant + ", ";
        }
        variant = variant + childVariant;
        System.out.println("variant_nameq" + variant.length());

        if (variant.isEmpty()) {

            holder.variantLayout.setVisibility(View.GONE);
        }
        if (parentVariant == null && childVariant == null) {

            holder.variantLayout.setVisibility(View.GONE);

        }
        if (variant.matches("")) {
            holder.variantLayout.setVisibility(View.GONE);

        }
        holder.parentVariantName.setText(variant);
        total = total + list.get(position).getMy_price() * list.get(position).getQuantity();
        if ((total + gsttotal) % 1 == 0) {
            int i = (int) (total + gsttotal);
            context.totalPrice.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%d", i));

        } else {
            context.totalPrice.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.2f", total + gsttotal));
        }
        if (total % 1 == 0) {
            int i = (int) (total);
            context.price.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%d", i));

        } else {
            context.price.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.2f", total));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final List<CartProductModel> list;
      //  private final Spinner quantitySpinner;
        private final OrderDetails context;
        private final TextView quantity;
        private final TextView deliverydetails;
        private final LinearLayout variantLayout;
        private final TextView gst;
        private final TextView parentVariantName;
        private final ProgressBar progressbar;
      //  private final CardView cardSpinner;

        TextView name;

        ImageView imageView;
        TextView actualprice, percentoff, sellingprice;

        public InfoHolder(View itemView, final OrderDetails context, final List<CartProductModel> list) {
            super(itemView);
            this.list = list;
            this.context = context;
            name = (TextView) itemView.findViewById(R.id.productnamecart);
            //   quantitySpinner = (Spinner) itemView.findViewById(R.id.quantityspinner);
            imageView = (ImageView) itemView.findViewById(R.id.cartimage);
            sellingprice = (TextView) itemView.findViewById(R.id.sellingprice);
            quantity = (TextView) itemView.findViewById(R.id.quantityofproduct);
            actualprice = (TextView) itemView.findViewById(R.id.actualprice);
            percentoff = (TextView) itemView.findViewById(R.id.percentoff);
            deliverydetails = (TextView) itemView.findViewById(R.id.deliverydetails);
            variantLayout = (LinearLayout) itemView.findViewById(R.id.variant);
            gst = (TextView) itemView.findViewById(R.id.gstvisibility);
            parentVariantName = (TextView) itemView.findViewById(R.id.parentVariantName);
            progressbar = (ProgressBar) itemView.findViewById(R.id.progressbar);

          /*  cardSpinner = (CardView) itemView.findViewById(R.id.cardspin);
            cardSpinner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantitySpinner.performClick();

                }
            });*/

           /* quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                    System.out.println("spinner" + position);
                    if (position > 0 && position < 6) {
                        entered = position;
                        if (list.get(getAdapterPosition()).getQuantity() != entered) {

                            productId = list.get(getAdapterPosition()).getProduct().getProduct_id();

                        }
                    } else if (position == 6) {
                        view.setVisibility(View.GONE);
                        dialog = new Dialog(context, R.style.AlertDialogStyle_Default);
                        dialog.setContentView(R.layout.addquantity);
                        editQuantity = (AppCompatEditText) dialog.findViewById(R.id.quantityadd);
                        new EditTextColorChanger(context, editQuantity);
                        TextView enterQuantityText = (TextView) dialog.findViewById(R.id.enterQuantityText);
                        *//*ColorStateList colorStateList = ColorStateList.valueOf(Color.parseColor("#" + sharedPreferences.getString("text_color", "#FFFFFF")));
                        editQuantity.setSupportBackgroundTintList(colorStateList);*//*
                        final Button dialogOKButton = (Button) dialog.findViewById(R.id.cpupdate);
                        dialogOKButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));

                        Button dialogCancelButton = (Button) dialog.findViewById(R.id.cpcancel);
                        try {
                            dialogCancelButton.setText(R.string.cancel);
                            editQuantity.setHint(R.string.quantity);
                            dialogOKButton.setText(R.string.save);
                            enterQuantityText.setText(R.string.quantity);


                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }

                        dialogCancelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        dialogOKButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (editQuantity.getText().toString().isEmpty()) {
                                    try {
                                        Toast.makeText(context, "Please Enter Quantity", Toast.LENGTH_SHORT).show();
                                        //  new CustomToast(context, Label.cartLabel.getPlease_enter_quantity());
                                    } catch (NullPointerException e) {
                                        e.printStackTrace();
                                    }
                                } else if (Integer.parseInt(editQuantity.getText().toString()) == 0) {
                                    try {
                                        Toast.makeText(context, "Please Enter Quantity", Toast.LENGTH_SHORT).show();
                                        //   new CustomToast(context,R.string.please_enter_quantity);
                                    } catch (NullPointerException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    dialog.dismiss();
                                    entered = Integer.parseInt(editQuantity.getText().toString());

                                    // if (list.get(getAdapterPosition()).getQuantity() != position + 1) {
                                    if (list.get(getAdapterPosition()).getQuantity() != entered) {
                                        // }
                                    }
                                }
                            }
                        });
                        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                        dialog.show();
                    }
                }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
*/



           /* itemView.setOnClickListener(this);
            String[] plants = new String[0];
            try {
                plants = new String[]{
                        "Select Quantity",
                        "1",
                        "2",
                        "3",
                        "4",
                        "5",
                        "more"
                        // String.valueOf(R.string.more)
                };
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(

                    context, R.layout.simple_spinner_item, plants) {

                @Override
                public View getDropDownView(int position, View convertView,
                                            ViewGroup parent) {
                    View view = null;
                    TextView tv = new TextView(getContext());
                    if (position == 0) {
                        tv.setVisibility(View.GONE);
                        tv.setHeight(0);
                        view = tv;
                    } else {
                        view = super.getDropDownView(position, null, parent);
                    }
                    return view;
                }
            };
            spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_item);
            quantitySpinner.setAdapter(spinnerArrayAdapter);
        }*/
        }



        @Override
        public void onClick(View v) {
            Gson gson = new Gson();
            final CartProductModel productModel = list.get(getAdapterPosition());

            String jsonString = gson.toJson(productModel.getProduct().getProduct_translation());
            if (jsonString.equals("null")) {
                jsonString = gson.toJson(productModel.getProduct().getDefault_product_translation());
            }
            CartProductModel.ProductBean.ProductTranslationBean productTranslationBean = gson.fromJson(jsonString, CartProductModel.ProductBean.ProductTranslationBean.class);
            Intent intent = new Intent(context, ProductDetails.class);
            ProductDetails.productid = productTranslationBean.getProduct_id();
            ProductDetails.nameofProduct = "";
            ProductDetails.parentVariantId = list.get(getAdapterPosition()).getParent_variant_id();
            ProductDetails.childVariantId = list.get(getAdapterPosition()).getChild_variant_id();
            ProductDetails.sku_id = 0;
            // ProductDetails.sku_id = list.get(getAdapterPosition()).11
            System.out.println("dswq2ws" + list.get(getAdapterPosition()).getParent_variant_id() + "wdass" + list.get(getAdapterPosition()).getChild_variant_id());
            intent.putExtra("position", getAdapterPosition());
            context.startActivity(intent);
        }
    }
}
