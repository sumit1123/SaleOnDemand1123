package com.example.grocery.Adapter;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.bumptech.glide.Glide;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.example.grocery.Activities.CartActivity;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.CartModel;
import com.example.grocery.Model.ProductModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static com.example.grocery.Activities.CartActivity.gstStatus;
import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;
import static com.example.grocery.interfaces.IConstants.BASE_URL;

/**
 * Created by Mohd Afzal on 9/28/2017.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.InfoHolder> {
    private final CartActivity context;
    private final List<CartModel> list;
    //private final SharedPreferences labelsShared;
    private String off;
    private String userid;
    private String email;
    private String pwd;
    public static int entered;
    public static int productId;
    private Dialog dialog;
    private EditText pincode;
    private AppCompatEditText editQuantity;
    private static int size_id;
    private int color_ida;
    public static int sku_id;
    private DecimalFormat decimalFormat;
    private String parentVariant;
    private String childVariant;



    public CartAdapter(CartActivity cartActivity, List<CartModel> list) {
        this.context = cartActivity;
        this.list = list;
        //labelsShared = context.getSharedPreferences("labels", MODE_PRIVATE);
    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartadapter_test, parent, false);
        return new InfoHolder(view);
    }

    @Override
    public void onBindViewHolder(final InfoHolder holder, int position) {

        holder.variantName.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        holder.textoutofstock.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));

        Gson gson = new Gson();
        final CartModel cartModel = list.get(position);
        String jsonString = gson.toJson(cartModel.getSku().getProduct().getProduct_translation());
        System.out.println("zdnfjc" + jsonString);
        Configuration config = context.getResources().getConfiguration();
        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {

        }
        if (jsonString.equals("null")) {

            jsonString = gson.toJson(cartModel.getSku().getProduct().getDefault_product_translation());
            System.out.println("zdnfjc" + jsonString);

        }
        CartModel.SkuBean.ProductBean.ProductTranslationBean productTranslationBean = gson.fromJson(jsonString,
                CartModel.SkuBean.ProductBean.ProductTranslationBean.class);
        holder.name.setText(productTranslationBean.getProduct_name());
        Glide.with(context)
                .load(BASE_IMAGE_URL + cartModel.getSku().getProduct().getProduct_image())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.progressbar.setVisibility(View.GONE);
                        holder.productImage.setBackgroundResource(R.drawable.product_big);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.progressbar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.productImage);

        try {
            String movetowishlist = context.getResources().getString(R.string.move_to_wishlist);
            String remove = context.getResources().getString(R.string.remove_upper);
            holder.movetowishlistText.setText(movetowishlist);
            holder.removeText.setText(remove);

           // holder.quantity.setText(R.string.quantity + " : " + String.valueOf(list.get(position).getQuantity()));
             holder.quantity.setText( String.valueOf(list.get(position).getQuantity()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        if (cartModel.getSku().getQuantity() <= 0 && cartModel.getSku().getProduct().getTrack_inventory() == 1) {
            holder.cardSpinner.setVisibility(View.GONE);
            holder.variantLayout.setVisibility(View.GONE);
            holder.outofstock.setVisibility(View.GONE);
            holder.textoutofstock.setVisibility(View.VISIBLE);

            try {
                String outofstock = context.getResources().getString(R.string.out_of_stock);
                holder.textoutofstock.setText(outofstock);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        } else if (cartModel.getSku().getQuantity() < cartModel.getQuantity() && cartModel.getSku().getProduct().getTrack_inventory() == 1 && cartModel.getSku().getProduct().getAllow_customer_stock_out() == 0) {
            holder.cardSpinner.setVisibility(View.VISIBLE);
            holder.variantLayout.setVisibility(View.GONE);
            holder.outofstock.setVisibility(View.GONE);

            holder.textoutofstock.setVisibility(View.VISIBLE);
            holder.textoutofstock.setText("Only " + cartModel.getSku().getQuantity() + " is available");
        } else {
            holder.cardSpinner.setVisibility(View.VISIBLE);
            holder.variantLayout.setVisibility(View.VISIBLE);
            holder.outofstock.setVisibility(View.VISIBLE);

            holder.textoutofstock.setVisibility(View.GONE);
        }

        if (cartModel.getSku().getWishlist() != null) {
            holder.wishlistIcon.setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        }else {
            holder.wishlistIcon.clearColorFilter();
        }

        if (productTranslationBean.getDelivery_message() == null || productTranslationBean.getDelivery_message().matches("")) {
            holder.deliveryDeatails.setVisibility(View.GONE);

        } else {
            holder.deliveryDeatails.setText(productTranslationBean.getDelivery_message());
        }

        if (cartModel.getSku().getMarket_price() <= cartModel.getSku().getMy_price()) {
            if (cartModel.getSku().getMy_price() * cartModel.getQuantity() > 9999.99 || cartModel.getSku().getMy_price() * cartModel.getQuantity() % 1 == 0) {
                holder.selling.setText(String.valueOf(Appearance.appTranslation.getCurrency() + Math.round(cartModel.getSku().getMy_price() * cartModel.getQuantity())));

            } else {
                holder.selling.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.2f", cartModel.getSku().getMy_price() * cartModel.getQuantity()));
            }
            holder.actualprice.setVisibility(View.GONE);
            holder.percentoff.setVisibility(View.GONE);

        } else {
            if (cartModel.getSku().getMy_price() * cartModel.getQuantity() > 9999.99 || cartModel.getSku().getMy_price() * cartModel.getQuantity() % 1 == 0) {
                holder.selling.setText(String.valueOf(Appearance.appTranslation.getCurrency() + Math.round(cartModel.getSku().getMy_price() * cartModel.getQuantity())));
                System.out.println("dsxz" + Math.round(cartModel.getSku().getMy_price() * cartModel.getQuantity()));

            } else {

                holder.selling.setText(Appearance.appTranslation.getCurrency() + String.format(Locale.ENGLISH, "%.2f", cartModel.getSku().getMy_price() * cartModel.getQuantity()));

            }
            if (cartModel.getSku().getMarket_price() * cartModel.getQuantity() > 9999.99 || cartModel.getSku().getMarket_price() * cartModel.getQuantity() % 1 == 0) {
                holder.actualprice.setText(Appearance.appTranslation.getCurrency() + String.valueOf((long) cartModel.getSku().getMarket_price() * cartModel.getQuantity()));

            } else {
                holder.actualprice.setText(Appearance.appTranslation.getCurrency() + String.valueOf(cartModel.getSku().getMarket_price() * cartModel.getQuantity()));

            }
            holder.actualprice.setVisibility(View.VISIBLE);
            holder.percentoff.setVisibility(View.VISIBLE);
            holder.actualprice.setPaintFlags(holder.actualprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            double marketprice = Double.parseDouble(String.valueOf(cartModel.getSku().getMarket_price()));
            double myprice = new Double(cartModel.getSku().getMy_price());
            System.out.println("market" + cartModel.getSku().getMarket_price() + "my" + cartModel.getSku().getMy_price());
            double percentCalculated = (myprice / marketprice) * 100;
            System.out.println("double" + percentCalculated);
            Double offCalculated = new Double(100 - percentCalculated);
            decimalFormat = new DecimalFormat("#.##");
            off = decimalFormat.format(offCalculated);
            String offs = context.getResources().getString(R.string.off);

            try {
                if (offCalculated % 1 == 0) {
                    int i = (int) Math.round(offCalculated);
                    holder.percentoff.setText(i + "% " +offs);

                } else {
                    holder.percentoff.setText(String.format(Locale.ENGLISH, "%.2f", offCalculated) + "% " + offs);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        }

        jsonString = gson.toJson(cartModel.getSku().getChild_variant().getChild_variant_translation());

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(cartModel.getSku().getChild_variant().getDefault_child_variant_translation());

        }
        ProductModel.SkuBean.ChildVariantBean.ChildVariantTranslationBean childVariantTranslationBean = gson.fromJson(jsonString,
                ProductModel.SkuBean.ChildVariantBean.ChildVariantTranslationBean.class);

        jsonString = gson.toJson(cartModel.getSku().getParent_variant().getDefault_parent_variant_translation());

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(cartModel.getSku().getParent_variant().getDefault_parent_variant_translation());

        }
        ProductModel.SkuBean.ParentVariantBean.ParentVariantTranslationBean parentVariantTranslationBean = gson.fromJson(jsonString,
                ProductModel.SkuBean.ParentVariantBean.ParentVariantTranslationBean.class);

        /*DrawableCompat.setTint(context.getDrawable(R.drawable.wishlistred), Color.parseColor("#" + sharedPreferences.getString("text_color", "#FFFFFF")));
        Drawable mDrawable = context.getResources().getDrawable(R.drawable.wishlistred);
        mDrawable.setColorFilter(new
                PorterDuffColorFilter(Color.parseColor("#" + sharedPreferences.getString("text_color", "#FFFFFF")), PorterDuff.Mode.SRC_IN));*/
        // holder1.variantName.setTextColor(Color.parseColor("#" + sharedPreferences.getString("text_color", "#FFFFFF")));
     /*   if (childVariantTranslationBean.getChild_variant_name().equals("null")) {

            parentVariant = parentVariantTranslationBean.getParent_variant_name();
            childVariant = childVariantTranslationBean.getChild_variant_name();

        }*/

   //     parentVariant = parentVariantTranslationBean.getParent_variant_name();
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
        if (parentVariant == null && childVariant == null) {

            holder.variantLayout.setVisibility(View.GONE);
        }
        if (variant.matches("")) {
            holder.variantLayout.setVisibility(View.GONE);

        }
        holder.variantName.setText(variant);
        holder.quantitySpinner.setSelection(-1);
        if (cartModel.getSku().getProduct().getGst().getGst() > 0 && gstStatus) {
            holder.gst.setVisibility(View.VISIBLE);
            double ss = cartModel.getSku().getProduct().getGst().getGst();

            double percentgst = ss / 100;
            decimalFormat = new DecimalFormat("#.##");


            holder.gst.setText(Html.fromHtml("<b>" + cartModel.getSku().getProduct().getGst().getGst() + "</b>" + "% GST Applied, " +
                    Appearance.appTranslation.getCurrency() + "<b>" + decimalFormat.format(percentgst * list.get(position).getSku().getMy_price() * list.get(position).getQuantity()) + "</b>"));
        } else {
            holder.gst.setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Spinner quantitySpinner;
        private final TextView selling;
        private final LinearLayout removeProduct;
        private final TextView quantity;
        private final CardView cardSpinner;
        private final TextView actualprice;
        private final TextView deliveryDeatails;
        private final TextView percentoff;
        private final LinearLayout outofstock;
        private final TextView textoutofstock;
        private final LinearLayout movetoWishlist;
        private final TextView gst;
        private final TextView variantName;
        private final ProgressBar progressbar;
        private final LinearLayout variantLayout;
        private final TextView movetowishlistText, removeText;
        private ImageView wishlistIcon;


        TextView name;
        ImageView productImage;

        public InfoHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.productnamecart);
            variantName = (TextView) itemView.findViewById(R.id.parentVariantName);
            wishlistIcon = itemView.findViewById(R.id.wishlisticon);
            progressbar = (ProgressBar) itemView.findViewById(R.id.progressbar);

            movetowishlistText = (TextView) itemView.findViewById(R.id.movetowishlistText);
            removeText = (TextView) itemView.findViewById(R.id.removeText);


            actualprice = (TextView) itemView.findViewById(R.id.actualprice);
            deliveryDeatails = (TextView) itemView.findViewById(R.id.deliverydetails);
            productImage = (ImageView) itemView.findViewById(R.id.cartimage);
            quantitySpinner = (Spinner) itemView.findViewById(R.id.quantityspinner);
            quantity = (TextView) itemView.findViewById(R.id.quantityofproduct);
            variantLayout = (LinearLayout) itemView.findViewById(R.id.variant);
            outofstock = (LinearLayout) itemView.findViewById(R.id.outofstock);
            textoutofstock = (TextView) itemView.findViewById(R.id.textoutofstock);
            movetoWishlist = (LinearLayout) itemView.findViewById(R.id.movetowishlist);
            gst = (TextView) itemView.findViewById(R.id.gstvisibility);


            movetoWishlist.setOnClickListener(this);
            cardSpinner = (CardView) itemView.findViewById(R.id.cardspin);
            cardSpinner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantitySpinner.performClick();

                }
            });
            quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                    System.out.println("spinner" + position);
                    if (position > 0 && position < 6) {
                        entered = position;
                        if (list.get(getAdapterPosition()).getQuantity() != entered) {
                            sku_id = list.get(getAdapterPosition()).getSku().getSku_id();
                            productId = list.get(getAdapterPosition()).getSku().getProduct().getProduct_id();
                            getdata();
                        }
                    } else if (position == 6) {
                        view.setVisibility(View.GONE);
                        dialog = new Dialog(context, R.style.AlertDialogStyle_Default);
                        dialog.setContentView(R.layout.addquantity);
                        editQuantity = (AppCompatEditText) dialog.findViewById(R.id.quantityadd);
                        new EditTextColorChanger(context, editQuantity);
                        TextView enterQuantityText = (TextView) dialog.findViewById(R.id.enterQuantityText);
                        /*ColorStateList colorStateList = ColorStateList.valueOf(Color.parseColor("#" + sharedPreferences.getString("text_color", "#FFFFFF")));
                        editQuantity.setSupportBackgroundTintList(colorStateList);*/
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

                                        productId = list.get(getAdapterPosition()).getSku().getProduct().getProduct_id();

                                        sku_id = list.get(getAdapterPosition()).getSku().getSku_id();
                                        getdata();

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
            selling = (TextView) itemView.findViewById(R.id.sellingprice);
            percentoff = (TextView) itemView.findViewById(R.id.percentoff);
            removeProduct = (LinearLayout) itemView.findViewById(R.id.linearremove);
            removeProduct.setOnClickListener(this);
            productImage.setOnClickListener(this);
            name.setOnClickListener(this);
            itemView.setOnClickListener(this);
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
        }

        @Override
        public void onClick(View v) {
            Gson gson = new Gson();
            final CartModel cartModel = list.get(getAdapterPosition());
            String jsonString = gson.toJson(cartModel.getSku().getProduct().getProduct_translation());
            System.out.println("zdnfjc" + jsonString);

            if (jsonString.equals("null")) {

                jsonString = gson.toJson(cartModel.getSku().getProduct().getDefault_product_translation());
                System.out.println("zdnfjc" + jsonString);

            }
            CartModel.SkuBean.ProductBean.ProductTranslationBean productTranslationBean = gson.fromJson(jsonString,
                    CartModel.SkuBean.ProductBean.ProductTranslationBean.class);
            if (v.getId() == removeProduct.getId()) {
                final Dialog dialog = new Dialog(context, R.style.AlertDialogStyle_Default);

                dialog.setContentView(R.layout.postremove);
                Button cancelDialog = (Button) dialog.findViewById(R.id.canceldialogue);
                TextView removeItemConfirmation = (TextView) dialog.findViewById(R.id.removeItemConfirmation);
                TextView removeItemText = (TextView) dialog.findViewById(R.id.removeItemText);

                cancelDialog.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                Button dialogOk = (Button) dialog.findViewById(R.id.dialogButtonOK);
                try {
                    cancelDialog.setText(R.string.cancel);
                    removeItemText.setText(R.string.remove_item);
                    dialogOk.setText(R.string.remove_upper);
                    removeItemConfirmation.setText(R.string.do_you_really_want_remove_this_product);

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                dialogOk.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                dialogOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        JSONObject jsonObject = new JSONObject();
                        SharedPreferences prefs = context.getSharedPreferences("UserId", MODE_PRIVATE);

                        String userid = prefs.getString("user_id", "");
                        String emaila = prefs.getString("email", "");
                        String pwda = prefs.getString("pwd", "");
                        String languageid = prefs.getString("language", String.valueOf(1));

                        int cartid = list.get(getAdapterPosition()).getCart_id();
                        System.out.println("takeparameters" + userid + " " + cartid + pwda);
                        try {
                            jsonObject.put("business_id",IConstants.BUSINESS_ID);
                            jsonObject.put(("id"), userid);
                            jsonObject.put("cart_id", cartid);
                            jsonObject.put("password", pwda);
                            jsonObject.put("language_id", languageid);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println("sdxz" + jsonObject);
                        context.findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);

                        VolleyTask volleyTask = new VolleyTask(context, IConstants.BASE_URL + "deleteFromCart", jsonObject, Request.Method.POST);
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
                                    new CustomToast(context, response.getJSONObject("data").getString("msg"));
                                    context.findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                                    context.showCart(response);
                                    //To refresh ProductDetail Activity on OnResume
                                    CartActivity.isRemoveItemPressed = true;

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
                dialog.show();

            } else if (v.getId() == name.getId() || v.getId() == productImage.getId()) {
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("takemyproductid", list.get(getAdapterPosition()).getSku().getProduct().getProduct_id());
                ProductDetails.nameofProduct = productTranslationBean.getProduct_name();
                ProductDetails.productid = list.get(getAdapterPosition()).getSku().getProduct().getProduct_id();
                intent.putExtra("position", getAdapterPosition());
                context.startActivity(intent);
            } else if (v.getId() == movetoWishlist.getId()) {
                sendtowishlist();
            }
        }

        private void sendtowishlist() {
            JSONObject jsonObject = new JSONObject();
            SharedPreferences prefs = context.getSharedPreferences("UserId", MODE_PRIVATE);

            String userid = prefs.getString("user_id", "");
            String emaila = prefs.getString("email", "");
            String pwda = prefs.getString("pwd", "");
            String languageid = prefs.getString("language", String.valueOf(1));

            int productid = list.get(getAdapterPosition()).getSku().getProduct_id();
            int cartid = list.get(getAdapterPosition()).getCart_id();

            System.out.println("takeparameters" + userid + " " + productid + pwda + cartid);
            try {
                jsonObject.put("business_id",IConstants.BUSINESS_ID);
                jsonObject.put(("id"), userid);
                jsonObject.put("product_id", productid);
                jsonObject.put("cart_id", cartid);
                jsonObject.put("language_id", languageid);
                jsonObject.put("password", pwda);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println("saxz" + jsonObject);
            context.findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);

            VolleyTask volleyTask = new VolleyTask(context, IConstants.BASE_URL + "moveToWishlist", jsonObject, Request.Method.POST);
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

                        context.showCart(response);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void fnErrorOccurred(String error) {
                }
            });
        }
    }

    private void getdata() {

        System.out.println("sdcxz" + entered);
        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs1 = context.getSharedPreferences("UserId", MODE_PRIVATE);
        userid = prefs1.getString("user_id", "");
        email = prefs1.getString("email", "");
        pwd = prefs1.getString("pwd", "");
        String languageid = prefs1.getString("language", String.valueOf(1));
        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("quantity", entered);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("sku_id", sku_id);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("sdcx" + jsonObject);
        String url = BASE_URL + "addToCart";
        context.findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        System.out.println("wedxs" + url.trim());
        VolleyTask volleyTask = new VolleyTask(context, url, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
                System.out.println("sed" + response);
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());
                ResponseHandler responseHandler = new ResponseHandler();
                if (!responseHandler.validateResponse(context, response)) {
                    context.findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                    return;
                }

                try {
                    context.findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                    new CustomToast(context, response.getJSONObject("data").getString("msg"));

                    context.showCart(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void fnErrorOccurred(String error) {
            }
        });

    }
}

