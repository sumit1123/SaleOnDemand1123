package com.example.grocery.Adapter;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Activities.MyOrdersActivity;
import com.example.grocery.Activities.OrderDetails;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.MyOrderModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.VolleyTask;
import com.willy.ratingbar.ScaleRatingBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Mohd Afzal on 9/29/2017.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.InfoHolder> {

    private final MyOrdersActivity context;
    private String review;
    private float rating;
    private final List<MyOrderModel> list;
    //private final SharedPreferences labelsShared;
    private ScaleRatingBar ratingBar;
    private Dialog dialog;
    private AppCompatEditText description;
    private int starsa;
    private MyOrderModel.DeliveryStatusBean.DefaultDeliveryStatusTranslationBean orderTranslation;

    public OrderAdapter(MyOrdersActivity myOrdersActivity, List<MyOrderModel> list) {
        this.context = myOrdersActivity;
        this.list = list;
    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderadapter, parent, false);

        return new InfoHolder(view, context, list);
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, int position) {
        final Gson gson = new Gson();
        final MyOrderModel orderModel = list.get(position);
        String jsonString = gson.toJson(orderModel.getDelivery_status().getDelivery_status_translation());
        System.out.println("zdnfjc" + jsonString);

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(orderModel.getDelivery_status().getDefault_delivery_status_translation());
            System.out.println("zdnfjc" + jsonString);

        }
        orderTranslation = gson.fromJson(jsonString,
                MyOrderModel.DeliveryStatusBean.DefaultDeliveryStatusTranslationBean.class);



        holder.writeAReviewText.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        try {
            String review = context.getResources().getString(R.string.write_a_review);
            holder.writeAReviewText.setText(review);
           // int number=5;
            // mytextview.setText(getResources().getString(R.String.mycar) + " " +number + " " + getResources().getString(R.String.mysecondcar));

               String order = context.getResources().getString(R.string.order_id);
               holder.orderid.setText(order + ": " + String.valueOf(list.get(position).getOrder_id()));
             // Toast.makeText(context, ""+order, Toast.LENGTH_SHORT).show();


        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        holder.status.setText(orderTranslation.getDelivery_status_name());
        try {
            String s = list.get(position).getDelivery_status().getDelivery_status_color();

            GradientDrawable drawable = (GradientDrawable) holder.imageView.getBackground();
            drawable.setColor(Color.parseColor("#" + s));

        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            double finalamout = list.get(position).getOrder_amount().getTotal_amount();

            holder.totalAmount.setText( Appearance.appTranslation.getCurrency()+
                    String.format(Locale.ENGLISH, "%.2f", finalamout));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


//        if (list.get(position).getOrder_product_amount().getTotal_my_price() % 1 == 0) {
//            int i = (int) list.get(position).getOrder_product_amount().getTotal_my_price();
//            holder.totalAmount.setText(colorSettings.getString("currency", "₹") +
//                    String.valueOf(i)
//                    + list.get(position).getOrder_product_amount().getTotal_gst());
//        } else {
//            holder.totalAmount.setText(colorSettings.getString("currency", "₹") +
//                    String.valueOf(list.get(position).getOrder_product_amount().getTotal_my_price()
//                            + list.get(position).getOrder_product_amount().getTotal_gst()));
//        }

//        String.format(Locale.ENGLISH, "%d", i)

        rating = (float) orderModel.getOrder_rating();
        if (orderModel.getOrder_review() == null) {
            review = "";
        } else {
            review = orderModel.getOrder_review();
            Log.i("MyTAg", "onBindViewHolder: " + review);
        }
        holder.ratinng_Bar.setEmptyDrawableRes(R.drawable.startempty);
        holder.ratinng_Bar.setFilledDrawable(context.getResources().getDrawable(R.drawable.starfilled));
        holder.ratinng_Bar.setClickable(false);
        holder.ratinng_Bar.setRating(rating);
        setStarColor(rating, holder.ratinng_Bar);
        Log.i("NewRating", rating + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final MyOrdersActivity context;
        private final List<MyOrderModel> list;
        private final ScaleRatingBar ratinng_Bar;
        private final TextView writeAReviewText;
        TextView orderid, totalAmount, date, status;
        LinearLayout provideRating;
        TextView imageView;

        public InfoHolder(View itemView, MyOrdersActivity context, List<MyOrderModel> list) {
            super(itemView);
            this.context = context;
            this.list = list;
            orderid = (TextView) itemView.findViewById(R.id.orderid);
            totalAmount = (TextView) itemView.findViewById(R.id.totalorderprice);
            imageView = (TextView) itemView.findViewById(R.id.deliveryindicator);
            status = (TextView) itemView.findViewById(R.id.status);
            provideRating = (LinearLayout) itemView.findViewById(R.id.giverating);
            ratinng_Bar = (ScaleRatingBar) itemView.findViewById(R.id.rating);
            writeAReviewText = (TextView) itemView.findViewById(R.id.writeareviewText);
            provideRating.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (v.getId() == provideRating.getId()) {

                dialog = new Dialog(context, R.style.AlertDialogStyle_Default);
                dialog.setContentView(R.layout.addreviews);
                description = (AppCompatEditText) dialog.findViewById(R.id.reviewDescription);
                new EditTextColorChanger(context, description);
                if (list.get(getAdapterPosition()).getOrder_review() != null) {
                    description.setText(list.get(getAdapterPosition()).getOrder_review());
                    description.setSelection(description.getText().length());
                }


                Button dialogButtonOk = (Button) dialog.findViewById(R.id.addreview);
                dialogButtonOk.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                Button dialogButtonCancel = (Button) dialog.findViewById(R.id.reviewcancel);
                ratingBar = (ScaleRatingBar) dialog.findViewById(R.id.ratingBar);
                ratingBar.setEmptyDrawableRes(R.drawable.startempty);
                ratingBar.setFilledDrawable(context.getResources().getDrawable(R.drawable.starfilled));
                ratingBar.setClickable(false);
                ratingBar.setRating(ratinng_Bar.getRating());
                setStarColor(ratinng_Bar.getRating(), ratingBar);

                TextView ratingsandReviewsText = (TextView) dialog.findViewById(R.id.rateAndWriteAReviewText);
                TextView yourRatingText = (TextView) dialog.findViewById(R.id.yourRatingText);
                description.setHighlightColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));

                try {
                    ratingsandReviewsText.setText(R.string.ratings_and_reviews);
                    yourRatingText.setText(R.string.your_rating);
                    dialogButtonOk.setText(R.string.add_review);
                    dialogButtonCancel.setText(R.string.cancel);
                    String descr = context.getResources().getString(R.string.enter_your_review);
                    description.setHint(descr);

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                ratingBar.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            float touchPositionX = event.getX();
                            float width = ratingBar.getWidth();
                            float stars = (touchPositionX / width) * 5.0f;
                            starsa = (int) stars + 1;
                            ratingBar.setRating(starsa);
                            setStarColor(starsa, ratingBar);

                            v.setPressed(false);
                        }
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            v.setPressed(true);
                        }

                        if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                            v.setPressed(false);
                        }


                        return true;
                    }
                });

                dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (starsa <= 0) {
                            new CustomToast(context, "please provide rating");

                            Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);

                            shake.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            ratingBar.startAnimation(shake);

                        } else {
                            dialog.dismiss();
                            context.findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);

                            JSONObject jsonObject = new JSONObject();
                            SharedPreferences prefs = context.getSharedPreferences("UserId", MODE_PRIVATE);

                            String userid = prefs.getString("user_id", "");
                            String emaila = prefs.getString("email", "");
                            String pwda = prefs.getString("pwd", "");
                            String languageid = prefs.getString("language", String.valueOf(1));

                            try {
                                jsonObject.put("business_id",IConstants.BUSINESS_ID);
                                jsonObject.put("id", userid);
                                jsonObject.put("password", pwda);
                                jsonObject.put("order_rating", starsa);
                                jsonObject.put("order_id", list.get(getAdapterPosition()).getOrder_id());
                                jsonObject.put("order_review", description.getText().toString());
                                jsonObject.put("language_id", languageid);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            System.out.println("dfxc" + jsonObject);
                            VolleyTask volleyTask = new VolleyTask(context, IConstants.Url_OrderReview, jsonObject, Request.Method.POST);
                            volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                                @Override
                                public void fnPostTaskCompleted(JSONArray response) {

                                }

                                @Override
                                public void fnPostTaskCompletedJsonObject(JSONObject response) {
                                    if (!new ResponseHandler().validateResponse(context, response)) {
                                        dialog.dismiss();
                                        return;
                                    }
                                    try {
                                        context.setData(response.getJSONObject("data").getJSONObject("data").getJSONArray("order"));
                                        new CustomToast(context, response.getJSONObject("data").getString("msg"));

                                    } catch (JSONException e) {
                                        context.findViewById(R.id.whiteloader).setVisibility(View.GONE);
                                    }
                                    dialog.dismiss();


                                }

                                @Override
                                public void fnErrorOccurred(String error) {
                                    context.findViewById(R.id.whiteloader).setVisibility(View.GONE);

                                }
                            });


                        }
                    }
                });


                dialog.show();

            } else {
                Intent intent = new Intent(context, OrderDetails.class);
                intent.putExtra("takemyOrderId", list.get(getAdapterPosition()).getOrder_id());
                context.startActivity(intent);



            }
        }
    }

    public String setStarColor(float rating, ScaleRatingBar ratingbar) {

        String s = "#00bc58";

        try {
            if (rating == 1) {
                //RED
                ratingbar.setFilledDrawableRes(R.drawable.star_filled_red);
/*
                LayerDrawable stars = (LayerDrawable) ratingbar.getProgressDrawable();
                stars.getDrawable(2).
                        setColorFilter(Color.parseColor("#ff4c4c"), PorterDuff.Mode.SRC_ATOP);
                s = "#ff4c4c";
*/
            }
            if (rating <= 3.5 && rating > 1) {
                Log.i("Rating", rating + "");
                ratingbar.setFilledDrawableRes(R.drawable.star_filled_yellow);
                //YELLOW
/*
                LayerDrawable stars = (LayerDrawable) ratingbar.getProgressDrawable();
                stars.getDrawable(2).
                        setColorFilter(Color.parseColor("#ffaf4f"), PorterDuff.Mode.SRC_ATOP);
                s = "#ffaf4f";
*/
            }
            if (rating > 3.5) {
                ratingbar.setFilledDrawableRes(R.drawable.start_filled_green);
                //GREEN
/*
                LayerDrawable stars = (LayerDrawable) ratingbar.getProgressDrawable();
                stars.getDrawable(2).
                        setColorFilter(Color.parseColor("#00bc58"), PorterDuff.Mode.SRC_ATOP);
                s = "#00bc58";
*/
            }
        } catch (Exception e) {
        }
        return s;
    }
}

