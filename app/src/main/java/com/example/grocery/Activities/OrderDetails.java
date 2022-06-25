package com.example.grocery.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;

import android.text.Html;
import android.text.format.DateFormat;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.CartProductAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.CartProductModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.LoaderColorChanger;;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;
import com.willy.ratingbar.ScaleRatingBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.text.TextUtilsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import static androidx.core.util.Preconditions.checkArgument;
import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;

public class OrderDetails extends AppCompatActivity {
    private static int order_ids;
    private static int order_status;
    public TextView address, productGst, totalPrice;
    private CardView emailInvoice;
    ProgressBar VerticalProgressBar;
    int intValue = 0;
    Handler handler = new Handler();
    private String MY_COLOR;
    private CardView cardViewPincodeChange;
    public ScaleRatingBar ratingBar;
    private Dialog dialog;
    private AppCompatEditText description;
    private int ratingStars;
    private String myReview;
    private int myRating;
    private RelativeLayout bar;
    public double totalvalue;
    public TextView price;
    private Dialog dialog1;
    private Dialog dialog2;
    private Dialog dialog3;
    private TextView paymentMode;
    private String deliverylabel;
    private boolean isLeftToRight;
    private String startTime;
    public ScaleRatingBar rating;
    private SwipeRefreshLayout refreshLayout;
    private boolean hideloading;
    private TextView mobileNumber;
    private TextView emailAddress;
    Button return_item;
    Button replace_item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_cart_products);
        rating = (ScaleRatingBar) findViewById(R.id.rating);
        VerticalProgressBar = (ProgressBar) findViewById(R.id.progrebar);
        //labelsShared = getSharedPreferences("labels", MODE_PRIVATE);
        TextView writeareviewText = (TextView) findViewById(R.id.writeareviewText);
        isLeftToRight = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_LTR;
        if (isLeftToRight) {
            VerticalProgressBar.setRotation(90);
        } else {
            VerticalProgressBar.setRotation(270);
        }
        TextView orderidText = (TextView) findViewById(R.id.orderidText);
        TextView orderedText = (TextView) findViewById(R.id.orderedText);
        TextView cancelorder = (TextView) findViewById(R.id.cancelorder);
        TextView needHelp = (TextView) findViewById(R.id.needhelp);
        TextView shippingDetailsText = (TextView) findViewById(R.id.shippingDetailsText);
        TextView priceDetailsText = (TextView) findViewById(R.id.priceDetailsText);
        TextView itemCountText = (TextView) findViewById(R.id.itemCountText);
        TextView priceText = (TextView) findViewById(R.id.priceText);
        TextView gstText = (TextView) findViewById(R.id.gstText);
        TextView totalPriceText = (TextView) findViewById(R.id.totalPriceText);
        return_item = (Button) findViewById(R.id.return_item);
        replace_item = (Button) findViewById(R.id.replace_item);

        return_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2 = new Dialog(OrderDetails.this, R.style.AlertDialogStyle_Default);
                dialog2.setContentView(R.layout.postremoveorder);
                final Button dialogOkButton = (Button) dialog2.findViewById(R.id.dialogButtonOK);
                dialogOkButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                TextView cancelOrderText = (TextView) dialog2.findViewById(R.id.orderSucessText);
                TextView cabncelOrderDescription = (TextView) dialog2.findViewById(R.id.orderDescription);
                Button cancelDialog = (Button) dialog2.findViewById(R.id.canceldialogue);



                try {

                    String cancel = getString(R.string.cancel);
                    cancelDialog.setText(cancel);
                    System.out.println("asxz" + cancel);
                    String cancel_order = getString(R.string.cancel_order);
                    cancelOrderText.setText(cancel_order);
                    String cancel_msg = getString(R.string.order_cancel_msg);
                    cabncelOrderDescription.setText(cancel_msg);


                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

                cancelDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });

                dialogOkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogOkButton.setEnabled(false);
                       // cancleOrder();
                        returnorder();


                    }
                });
                dialog2.show();
            }
        });
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setColorSchemeColors((Color.parseColor("#" + Appearance.appSettings.getText_color())));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                // Refresh items
                hideloading = true;
                getData();


    returnorder();
            }
        });

        replace_item.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog3 = new Dialog(OrderDetails.this, R.style.AlertDialogStyle_Default);
                dialog3.setContentView(R.layout.postremoveorder);
                final Button dialogOkButton = (Button) dialog3.findViewById(R.id.dialogButtonOK);
                dialogOkButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                TextView cancelOrderText = (TextView) dialog3.findViewById(R.id.orderSucessText);
                TextView cabncelOrderDescription = (TextView) dialog3.findViewById(R.id.orderDescription);
                Button cancelDialog = (Button) dialog3.findViewById(R.id.canceldialogue);



                try {
                    String confirm = getString(R.string.confirm);
                    dialogOkButton.setText(confirm);
                    String cancel = getString(R.string.cancel);
                    cancelDialog.setText(cancel);
                    System.out.println("asxz" + cancel);
                    String cancel_order = getString(R.string.cancel_order);
                    cancelOrderText.setText(cancel_order);
                    String cancel_msg = getString(R.string.order_cancel_msg);
                    cabncelOrderDescription.setText(cancel_msg);


                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

                cancelDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog3.dismiss();
                    }
                });

                dialogOkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogOkButton.setEnabled(false);
                        // cancleOrder();
                        replaceorder();


                    }
                });
                dialog3.show();
            }
        });
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setColorSchemeColors((Color.parseColor("#" + Appearance.appSettings.getText_color())));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                // Refresh items
                hideloading = true;
                getData();


                replaceorder();
            }
        });
        try {
           // orderidText.setText(Label.orderLabel.getOrder_id() + ": ");
          //  writeareviewText.setText(Label.productLabel.getWrite_a_review());
           // orderedText.setText(Label.orderLabel.getOrdered());
           // cancelorder.setText(Label.orderLabel.getCancel_order());
           // needHelp.setText(Label.orderLabel.getNeed_help());
           // shippingDetailsText.setText(Label.orderLabel.getShipping_details());
            //priceDetailsText.setText(Label.cartLabel.getPrice_details());
           // itemCountText.setText(Label.cartLabel.getItems());
           // priceText.setText(Label.cartLabel.getPrice());
           // gstText.setText(Label.orderLabel.getGst());
            //totalPriceText.setText(Label.cartLabel.getTotal_amount());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        ((ProgressBar) findViewById(R.id.blurredloaderprogressbar)).getIndeterminateDrawable().setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()), PorterDuff.Mode.MULTIPLY);
        DrawableCompat.setTint(((ImageView) findViewById(R.id.giftImage)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getText_color()));
        ((TextView) findViewById(R.id
                .orderid)).setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        ((TextView) findViewById(R.id.writeareviewText)).setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        ((TextView) findViewById(R.id.cancelorder)).setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        Bundle extras = getIntent().getExtras();
        order_ids = extras.getInt("takemyOrderId");
        order_status = extras.getInt("takemyStatus");
        ScaleRatingBar rating = (ScaleRatingBar) findViewById(R.id.rating);
        rating.setEmptyDrawableRes(R.drawable.startempty);
        rating.setFilledDrawable(getResources().getDrawable(R.drawable.starfilled));
        rating.setClickable(false);
//        LayerDrawable stars = (LayerDrawable) rating.getProgressDrawable();
//        stars.getDrawable(2).setColorFilter(Color.parseColor("#" + sharedSettings.getString("text_color", "FFFFFF")), PorterDuff.Mode.SRC_ATOP);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.writereview);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeReview();
            }
        });
        needHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetails.this, OnlineSupportActivity.class);

                startActivity(intent);
            }
        });


        TextView cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
        TextView notificationCount = (TextView) findViewById(R.id.notification_count_textview);
        if (Dashboard.cart_count !=0){
            cartCount.setVisibility(View.VISIBLE);
            cartCount.setText(String.valueOf(Dashboard.cart_count));
        }
        if(Dashboard.notification_count!=0)
        {
            notificationCount.setVisibility(View.VISIBLE);
            notificationCount.setText(String.valueOf(Dashboard.notification_count));
        }
        emailInvoice = (CardView) findViewById(R.id.emailinvoice);

        TextView orderId = (TextView) findViewById(R.id.orderid);
        orderId.setText(String.valueOf(order_ids));
        emailInvoice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                String userid = prefs.getString("user_id", "");
                String pwd = prefs.getString("pwd", "");
                String languageid = prefs.getString("language", String.valueOf(1));


                try {
                    jsonObject.put("id", userid);
                    jsonObject.put("password", pwd);
                    jsonObject.put("order_id", order_ids);
                    jsonObject.put("language_id", languageid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                VolleyTask volleyTask = new VolleyTask(OrderDetails.this, IConstants.URL_EMAILINVOICE, jsonObject, Request.Method.POST);
                volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                    @Override
                    public void fnPostTaskCompleted(JSONArray response) {
                    }

                    @Override
                    public void fnPostTaskCompletedJsonObject(JSONObject response) {
                        if (!new ResponseHandler().validateResponse(OrderDetails.this, response)) {
                            return;
                        }

                        try {
                            new CustomToast(OrderDetails.this, response.getJSONObject("data").getString("msg"));
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
        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RelativeLayout toolbarNotification = (RelativeLayout) findViewById(R.id.toolnotification);

        toolbarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetails.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        if (Appearance.appSettings.getIs_notification() == 0) {
            toolbarNotification.setVisibility(View.GONE);

        }
        RelativeLayout toolbarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetails.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                SearchActivity.searchMin = "";
                ApplyFilter.maximumSeekBar = "5000";
                ProductDetails.productid = 0;
                isViewWithCatalog = false;
                ApplyFilter.minimumSeekBar = "0";
                ApplyFilter.brandArrays = null;
                SearchActivity.sortedida = 0;
                SearchActivity.formatedStringSearch = "";
                SearchActivity.imagea = "true";
                startActivity(intent);
            }
        });
        RelativeLayout toolbarCart = (RelativeLayout) findViewById(R.id.cart);
        toolbarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetails.this, CartActivity.class);
                startActivity(intent);
            }
        });
        TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        try {
            String order_detail = getString(R.string.order_detail);
            toolbarTitle.setText(order_detail);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        cardViewPincodeChange = (CardView) findViewById(R.id.cardpincodechange);

        cardViewPincodeChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1 = new Dialog(OrderDetails.this, R.style.AlertDialogStyle_Default);
                dialog1.setContentView(R.layout.postremoveorder);
                final Button dialogOkButton = (Button) dialog1.findViewById(R.id.dialogButtonOK);
                dialogOkButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                TextView cancelOrderText = (TextView) dialog1.findViewById(R.id.cancelOrderText);
                TextView cabncelOrderDescription = (TextView) dialog1.findViewById(R.id.cabncelOrderDescription);
                Button cancelDialog = (Button) dialog1.findViewById(R.id.canceldialogue);


                try {
                    String confirm = getString(R.string.confirm);
                    dialogOkButton.setText(confirm);
                    String cancel = getString(R.string.cancel);
                    cancelDialog.setText(cancel);
                    System.out.println("asxz" + cancel);
                    String cancel_order = getString(R.string.cancel_order);
                    cancelOrderText.setText(cancel_order);
                    String cancel_msg = getString(R.string.order_cancel_msg);
                    cabncelOrderDescription.setText(cancel_msg);


                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

                cancelDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });

                dialogOkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogOkButton.setEnabled(false);
                        cancleOrder();
                   //     returnorder();


                    }
                });
                dialog1.show();
            }
        });
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setColorSchemeColors((Color.parseColor("#" + Appearance.appSettings.getText_color())));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                // Refresh items
                hideloading = true;
                getData();


            }
        });
        getData();
    }


    private void writeReview() {
        {

            dialog = new Dialog(OrderDetails.this, R.style.AlertDialogStyle_Default);
            dialog.setContentView(R.layout.addreviews);
            description = (AppCompatEditText) dialog.findViewById(R.id.reviewDescription);
            new EditTextColorChanger(this, description);
            description.setText(myReview);
            description.setSelection(description.getText().length());
            final Button addReviewButton = (Button) dialog.findViewById(R.id.addreview);
            Button cancelButton = (Button) dialog.findViewById(R.id.reviewcancel);
            ratingBar = (ScaleRatingBar) dialog.findViewById(R.id.ratingBar);
            ratingBar.setEmptyDrawableRes(R.drawable.startempty);
            ratingBar.setFilledDrawable(getResources().getDrawable(R.drawable.starfilled));
            ratingBar.setClickable(false);
            ratingBar.setRating(rating.getRating());
            setStarColor(rating.getRating(), ratingBar);

            addReviewButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
            TextView ratingsandReviewsText = (TextView) dialog.findViewById(R.id.rateAndWriteAReviewText);
            TextView yourRatingText = (TextView) dialog.findViewById(R.id.yourRatingText);
            try {
              //  ratingsandReviewsText.setText(Label.productLabel.getRatings_and_reviews());
              //  yourRatingText.setText(Label.productLabel.getYour_rating());
              //  addReviewButton.setText(Label.productLabel.getAdd_review());
              //  cancelButton.setText(Label.globalLabel.getCancel());
              //  description.setHint(Label.productLabel.getEnter_your_review());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            ratingBar.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        float touchPositionX = event.getX();
                        float width = ratingBar.getWidth();
                        float starsf = (touchPositionX / width) * 5.0f;
                        ratingStars = (int) starsf + 1;
                        ratingBar.setRating(ratingStars);
                        setStarColor(ratingStars, ratingBar);
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

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            addReviewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addReviewButton.setEnabled(false);
                    if (ratingStars <= 0) {
                        addReviewButton.setEnabled(true);
                        //   try {
                        new CustomToast(OrderDetails.this, "please provide rating");
                      /*  } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                        Animation shake = AnimationUtils.loadAnimation(OrderDetails.this, R.anim.shake);

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
                        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);

                        JSONObject jsonObject = new JSONObject();
                        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

                        String userid = prefs.getString("user_id", "");
                        String emaila = prefs.getString("email", "");
                        String pwda = prefs.getString("pwd", "");
                        String languageid = prefs.getString("language", String.valueOf(1));

                        try {
                            jsonObject.put("business_id",IConstants.BUSINESS_ID);
                            jsonObject.put("id", userid);
                            jsonObject.put("password", pwda);
                            jsonObject.put("order_rating", ratingStars);
                            jsonObject.put("order_id", order_ids);
                            jsonObject.put("order_review", description.getText().toString());
                            jsonObject.put("language_id", languageid);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println("asxz" + jsonObject);
                        VolleyTask volleyTask = new VolleyTask(OrderDetails.this, IConstants.Url_OrderReview, jsonObject, Request.Method.POST);
                        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                            @Override
                            public void fnPostTaskCompleted(JSONArray response) {


                            }

                            @Override
                            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                                if (!new ResponseHandler().validateResponse(OrderDetails.this, response)) {
                                    dialog.dismiss();
                                    return;
                                }
                                try {
                                    new CustomToast(OrderDetails.this, response.getJSONObject("data").getString("msg"));
                                    rating.setRating(ratingStars);
                                    setStarColor(ratingStars, rating);
                                    myReview = description.getText().toString();

                                } catch (JSONException e) {


                                }
                                dialog.dismiss();


                            }

                            @Override
                            public void fnErrorOccurred(String error) {
                                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                                dialog1.dismiss();

                            }
                        });


                    }
                }
            });


            dialog.show();

        }
    }

    private void returnorder() {
        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        Bundle extras = getIntent().getExtras();
        order_ids = extras.getInt("takemyOrderId");

        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("order_id", order_ids);
            jsonObject.put("language_id", languageid);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("sdxcz" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_RETURN, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                if (!new ResponseHandler().validateResponse(OrderDetails.this, response)) {
                    dialog2.dismiss();

                    return;
                }

                try {
                    dialog2.dismiss();
                    new CustomToast(OrderDetails.this, response.getJSONObject("data").getString("msg"));
                    System.out.println("dcxz" + order_ids);
                    Intent intent = new Intent(OrderDetails.this, OrderDetails.class);
                    intent.putExtra("takemyOrderId", order_ids);
                    //    intent.putExtra("takemyStatus",order_status);
                    finish();
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fnErrorOccurred(String error) {
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                dialog2.dismiss();

            }
        });
    }




    private void replaceorder()
    {
    findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
    Bundle extras = getIntent().getExtras();
    order_ids = extras.getInt("takemyOrderId");

    JSONObject jsonObject = new JSONObject();
    SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
    String userid = prefs.getString("user_id", "");
    String pwd = prefs.getString("pwd", "");
    String languageid = prefs.getString("language", String.valueOf(1));

        try {
        jsonObject.put("business_id",IConstants.BUSINESS_ID);
        jsonObject.put("id", userid);
        jsonObject.put("password", pwd);
        jsonObject.put("order_id", order_ids);
        jsonObject.put("language_id", languageid);


    } catch (JSONException e) {
        e.printStackTrace();
    }
        System.out.println("sdxcz" + jsonObject);
    VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_REPLACE, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
        @Override
        public void fnPostTaskCompleted(JSONArray response) {
        }

        @Override
        public void fnPostTaskCompletedJsonObject(JSONObject response) {
            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

            if (!new ResponseHandler().validateResponse(OrderDetails.this, response)) {
                dialog3.dismiss();

                return;
            }

            try {
                dialog3.dismiss();
                new CustomToast(OrderDetails.this, response.getJSONObject("data").getString("msg"));
                System.out.println("dcxz" + order_ids);
                Intent intent = new Intent(OrderDetails.this, OrderDetails.class);
                intent.putExtra("takemyOrderId", order_ids);
                //    intent.putExtra("takemyStatus",order_status);
                finish();
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void fnErrorOccurred(String error) {
            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
            dialog3.dismiss();

        }
    });
}




    private void cancleOrder() {

        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        Bundle extras = getIntent().getExtras();
        order_ids = extras.getInt("takemyOrderId");

        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("order_id", order_ids);
            jsonObject.put("language_id", languageid);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("sdxcz" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, IConstants.Url_CancelOrder, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                if (!new ResponseHandler().validateResponse(OrderDetails.this, response)) {
                    dialog1.dismiss();
                    return;
                }

                try {
                    dialog1.dismiss();
                    new CustomToast(OrderDetails.this, response.getJSONObject("data").getString("msg"));
                    System.out.println("dcxz" + order_ids);
                    Intent intent = new Intent(OrderDetails.this, MyOrdersActivity.class);
                    intent.putExtra("takemyOrderId", order_ids);
                //    intent.putExtra("takemyStatus",order_status);
                    finish();
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fnErrorOccurred(String error) {
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                dialog1.dismiss();

            }
        });
    }

    protected void getData() {
        Bundle extras = getIntent().getExtras();
        order_ids = extras.getInt("takemyOrderId");
        if (!hideloading) {
            findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        }
        findViewById(R.id.retryImage).setVisibility(View.GONE);

        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);
        final JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("order_id", order_ids);
            jsonObject.put("language_id", languageid);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("scdxz" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_GETPURCHASEPRODUCT, jsonObject, Request.Method.POST);

        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }


            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                refreshLayout.setRefreshing(false);
                if (!new ResponseHandler().validateResponse(OrderDetails.this, response)) {
                    return;
                }

                try {

                    JSONObject jsonObject1 = response.getJSONObject("data");
                    JSONObject jsonObject3 = jsonObject1.getJSONObject("data");
                    JSONObject jsonObject2 = jsonObject3.getJSONObject("order");
//                    JSONObject jsonObject4 = jsonObject3.getJSONObject("items");
                    JSONObject jsonObject5 = jsonObject2.getJSONObject("user");


                    TextView orderDescription = (TextView) findViewById(R.id.orderDescription);
                    if (jsonObject2.get("order_description").equals(null)) {
                        orderDescription.setVisibility(View.GONE);
                    } else {
                        String  order_des = getString(R.string.order_description);
                        orderDescription.setText(order_des + " : " + jsonObject2.getString("order_description"));
                    }
                    String date = jsonObject2.getString("created_at");
                    myRating = jsonObject2.getInt("order_rating");
                    if (jsonObject2.get("order_review").equals(null)) {
                        myReview = "";
                    } else {
                        myReview = jsonObject2.getString("order_review");
                    }

                    rating.setRating(myRating);
                    setStarColor(myRating, rating);

                    TextView order = (TextView) findViewById(R.id.orderdate);
                    setdate(order, date);
                    //order.setText(date);

                    TextView deliveryDate = (TextView) findViewById(R.id.delivereddate);
                    setdate(deliveryDate, jsonObject2.getString("updated_at"));

                    JSONArray jsonArray = jsonObject3.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        JSONObject type = jsonObject.getJSONObject("product");
                        JSONObject jsonObject7 = type.getJSONObject("return_policy");

                        //    Toast.makeText(getApplicationContext(),""+jsonObject7,Toast.LENGTH_SHORT).show();


                        totalPrice = (TextView) findViewById(R.id.totalprice);
                        productGst = (TextView) findViewById(R.id.productgst);

//                    totalvalue = jsonObject2.getInt("amount");
                        totalvalue = 0;
                        TextView name = (TextView) findViewById(R.id.textname);
                        String nameJson = jsonObject2.getString("name");
                        name.setText(nameJson.matches("null") ? "" : nameJson);
                        TextView pincode = (TextView) findViewById(R.id.statepincode);
                        String pinJson = String.valueOf(jsonObject2.getString("delivery_pincode"));
                        pincode.setText(pinJson.matches("null") ? "" : pinJson);
                        address = (TextView) findViewById(R.id.address);
                        String addressJson = String.valueOf(jsonObject2.getString("delivery_address"));
                        address.setText(addressJson.matches("null") ? "" : addressJson);
                        price = (TextView) findViewById(R.id.price);
                        mobileNumber = (TextView) findViewById(R.id.mobileNumber);
                        emailAddress = (TextView) findViewById(R.id.emailAddress);
                        String mobileJson = String.valueOf(jsonObject2.getString("delivery_contact_number"));
                        mobileNumber.setText(mobileJson.matches("null") ? "" : mobileJson);
                        String emailJson = String.valueOf(jsonObject2.getString("delivery_email"));
                        emailAddress.setText(emailJson.matches("null") ? "" : emailJson);
                        //     String return_item_value = String.valueOf(jsonObject.getString("return_policy"));


                        if (emailJson.matches("null")) {
                            emailAddress.setVisibility(View.GONE);
                        }
                        if (mobileJson.matches("null")) {
                            mobileNumber.setVisibility(View.GONE);
                        }
                        if (pinJson.matches("null") || pinJson.matches("")) {
                            pincode.setVisibility(View.GONE);
                        }
                        if (emailJson.matches(mobileJson)) {
                            emailAddress.setVisibility(View.GONE);
                        }
                        TextView deliverystatus = (TextView) findViewById(R.id.deliverystatus);
                        if (jsonObject2.getJSONObject("delivery_status").get("delivery_status_translation").equals("null")) {
                            deliverylabel = jsonObject2.getJSONObject("delivery_status").getJSONObject("delivery_status_translation").getString("delivery_status_name");
                        } else {
                            deliverylabel = jsonObject2.getJSONObject("delivery_status").getJSONObject("default_delivery_status_translation").getString("delivery_status_name");
                        }
                        deliverystatus.setText(deliverylabel);
                        //   if (jsonObject2.getJSONObject("delivery_status").getInt("is_email") == 0) {

                        if (jsonObject2.getJSONObject("delivery_status").getInt("is_cancel") == 1) {
                            cardViewPincodeChange.setVisibility(View.VISIBLE);
                        } else {
                            cardViewPincodeChange.setVisibility(View.GONE);
                        }
                 /*   if (jsonObject2.getJSONObject("delivery_status").getInt("pending") == 1) {
                     //   cardViewPincodeChange.setVisibility(View.GONE);
                        cardViewPincodeChange.setVisibility(View.VISIBLE);
                    }*/
                        if (jsonObject2.get("delivery_status_id").equals(3) && (jsonObject7.get("is_available_for_return").equals(1)))
                        //    if(jsonObject2.get("is_available_for_return_replace").equals(1))
                        {
                            return_item.setVisibility(View.VISIBLE);
                        } else {
                            return_item.setVisibility(View.GONE);
                        }

                        if (jsonObject2.get("delivery_status_id").equals(3) && (jsonObject7.get("is_available_for_return_replace").equals(1))) {
                            replace_item.setVisibility(View.VISIBLE);
                        } else {
                            replace_item.setVisibility(View.GONE);
                        }


                      //  String return_days = (String) jsonObject7.get("return_days");
                       // Toast.makeText(getApplicationContext(),""+return_days,Toast.LENGTH_SHORT).show();
                      //  String updated_day = (String) jsonObject7.get("updated_at");
                      //  String  return_date = updated_day+return_days;
                    //    Toast.makeText(getApplicationContext(),""+return_date,Toast.LENGTH_SHORT).show();

                         // Start date
                      /*  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar c = Calendar.getInstance();
                        try {
                            c.setTime(sdf.parse(updated_day));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }*/
                       // c.add(Integer.parseInt(updated_day),Integer.parseInt(return_days));
                      // c.add(Calendar.DATE, Integer.parseInt(return_days));  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
                      //  SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
                      //  String current_date = sdf1.format(c.getTime());
                       // Toast.makeText(getApplicationContext(),""+current_date,Toast.LENGTH_SHORT).show();



                       /* if ( updated_day.compareTo(current_date) < 0 ) {
                            return_item.setVisibility(View.GONE);

                        }
                        if ( updated_day.compareTo(current_date) < 0 ) {
                            replace_item.setVisibility(View.GONE);
                        }*/
                    }

                 /*   if(jsonObject2.get("is_available_for_return_replace").equals(1))
                    {
                        return_item.setVisibility(View.VISIBLE);
                        replace_item.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        return_item.setVisibility(View.GONE);
                        replace_item.setVisibility(View.GONE);
                    }*/



                /*  if(jsonObject2.getJSONObject("order").getInt("delivery_status_id") == 3)
                  {
                      return_item.setVisibility(View.VISIBLE);
                      replace_item.setVisibility(View.VISIBLE);
                  }
                  else
                  {
                      return_item.setVisibility(View.GONE);
                      replace_item.setVisibility(View.GONE);
                  }*/




                      /*  emailInvoice.setVisibility(View.GONE);
                    } else {
                        emailInvoice.setVisibility(View.VISIBLE);

                    }
                    */
                    changeColor(jsonObject2.getJSONObject("delivery_status").getString("delivery_status_color"));

                    paymentMode = (TextView) findViewById(R.id.paymentMode);
                    if (jsonObject2.getInt("order_type") == 1) {
                        String payment_mode = getString(R.string.payment_mode);
                        String cash_on_del = getString(R.string.cash_on_delivery);
                        String wallet= getString(R.string.wallet);
                        paymentMode.setText(payment_mode + " : " + cash_on_del);
                    } else if (jsonObject2.getInt("order_type") == 2) {
                        String pay = getString(R.string.online_payment);
                        String payment_mode = getString(R.string.payment_mode);
                        paymentMode.setText(payment_mode + " : " + pay);
                    } else if (jsonObject2.getInt("order_type") == 3) {
                        String payment_mode = getString(R.string.payment_mode);
                        String wallet= getString(R.string.wallet);
                        paymentMode.setText(payment_mode + " : " + wallet);
                    }

                    setData(jsonArray);

                    RelativeLayout gstvisibility = (RelativeLayout) findViewById(R.id.gstvisibility);

                    int gstNumber = Appearance.appSettings.getIs_gst();
                    if (gstNumber == 0) {
                        gstvisibility.setVisibility(View.GONE);
                    }

                    findViewById(R.id.whiteloader).setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();

                    refreshLayout.setRefreshing(false);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
                            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
                            progressBar.setVisibility(View.GONE);
                            findViewById(R.id.retryImage).setVisibility(View.VISIBLE);

                            Button imageView = (Button) findViewById(R.id.retrybutton);
                            imageView.setVisibility(View.VISIBLE);
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    getData();
                                }
                            });
                        }
                    }, 2000);
                }
            }

            @Override
            public void fnErrorOccurred(String error) {
                refreshLayout.setRefreshing(false);


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
                        progressBar.setVisibility(View.GONE);

                        findViewById(R.id.retryImage).setVisibility(View.VISIBLE);

                        Button retryButton = (Button) findViewById(R.id.retrybutton);
                        retryButton.setVisibility(View.VISIBLE);
                        retryButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getData();
                            }
                        });
                    }
                }, 2000);


            }
        });
    }


    private void setData(JSONArray jsonArray1) {
        Gson gson = new Gson();
        List<CartProductModel> list = Arrays.asList(gson.fromJson(jsonArray1.toString(), CartProductModel[].class));
        System.out.println("dada" + list);

        TextView tv = (TextView) findViewById(R.id.productcount);
        tv.setText(String.valueOf(list.size()));
        SharedPreferences sharedPreferences = getSharedPreferences("usertable", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String address = sharedPreferences.getString("address", "");
        String state = sharedPreferences.getString("state", "");
        String pincode = sharedPreferences.getString("pincode", "");


        RecyclerView orderDetailsRecycler = (RecyclerView) findViewById(R.id.cartproductrec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        orderDetailsRecycler.setLayoutManager(layoutManager);
        orderDetailsRecycler.setNestedScrollingEnabled(false);
        CartProductAdapter cartProductAdapter = new CartProductAdapter(this, list);
        orderDetailsRecycler.setAdapter(cartProductAdapter);


    }

    public void changeColor(final String id) {


        VerticalProgressBar.getProgressDrawable().setColorFilter(this.getResources().getColor(R.color.black),
                PorterDuff.Mode.SRC_IN);


        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (intValue < 100) {
                    intValue++;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            if (intValue > 80) {
                                VerticalProgressBar.getProgressDrawable().setColorFilter(Color.parseColor("#" + id),
                                        PorterDuff.Mode.SRC_IN);

                            }
                            VerticalProgressBar.setProgress(intValue);


                        }
                    });
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(OrderDetails.this);

    }

    private void setdate(TextView order, String string) {
        System.out.println("fdmsdx" + string);
        StringTokenizer tk = new StringTokenizer(string);
        String date = tk.nextToken();
        String time = tk.nextToken();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


        Date dates = null;
        try {
            dates = format1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String day = (String) DateFormat.format("dd", dates);
        String dayNumberSuffix = getDayOfMonthSuffix(Integer.parseInt(day));

        SimpleDateFormat format2 = new SimpleDateFormat(" d'" + dayNumberSuffix + "' MMMM yyyy", Locale.ENGLISH);
        System.out.println("fdcx" + format2.format(dates));
        System.out.println("fdcx" + format2.format(dates));
        // holder.date.setText(format2.format(date));


        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm:ss a", Locale.ENGLISH);
        Date dt;
        try {
            dt = sdf.parse(time);
            System.out.println("Time Display: " + sdfs.format(dt));
            order.setText(Html.fromHtml(format2.format(dates)) + " " + sdfs.format(dt));// <-- I got result here
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    String getDayOfMonthSuffix(final int n) {
        checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
        if (n >= 11 && n <= 13) {
            return "<sup>th</sup>";
        }
        switch (n % 10) {
            case 1:
                return "<sup><small>st</small></sup>";
            case 2:
                return "<sup><small>nd</small></sup>";
            case 3:
                return "<sup><small>rd</small></sup>";
            default:
                return "<sup><small>th</small></sup>";
        }
    }

    public String setStarColor(float rating, ScaleRatingBar ratingbar) {

        String s = "#00bc58";
        ratingbar.setFilledDrawableRes(R.drawable.start_filled_green);

        try {
            if (rating == 1) {
                //RED
                ratingbar.setFilledDrawableRes(R.drawable.star_filled_red);
                s = "#ff4c4c";
            } else if (rating <= 3.5 && rating > 1) {
                //YELLOW
                ratingbar.setFilledDrawableRes(R.drawable.star_filled_yellow);
                s = "#ffaf4f";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


}
