package com.example.grocery.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.grocery.Adapter.ReviewAdpater;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Model.BitmapModel;
import com.example.grocery.Model.ReviewsModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.ColorManager;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.willy.ratingbar.ScaleRatingBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.Activities.ProductDetails.productid;
import static com.example.grocery.R.drawable.selectedborder;

public class SellerDetails extends AppCompatActivity {
 ImageView img;
 String Images;
    private Button addReview;
    private ScaleRatingBar ratingbar;
    Toolbar toolbar;
    private RelativeLayout bigtoolbar;
    private TextView cartCount;
    private TextView reviewsCountText;
    private SwipeRefreshLayout refreshLayout;
    private Dialog dialog;
    private String myReview;
    private AppCompatEditText description;
    private Button choosebutton;
    private ScaleRatingBar ratingBar;
    private int ratingStars;
    private ArrayList<Object> base64String;
    private TextView ratingCount;
    private LinearLayout colorLayout;
    private TextView ratings;
    private TextView ratings2;
    public static ArrayList<String> strings;
    private ArrayList<BitmapModel> bitmapModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_details);
        base64String = new ArrayList<>();
        String add_review = getString(R.string.add_review);
        addReview = (Button) findViewById(R.id.addreview);
        ratings2 = (TextView) findViewById(R.id.rating2);
        ratings = (TextView) findViewById(R.id.rating);
        ratingCount = (TextView) findViewById(R.id.ratingcount);
        ratingbar = findViewById(R.id.ratingBar);
        img = (ImageView) findViewById(R.id.bigimage);
        colorLayout = findViewById(R.id.sd);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
        reviewsCountText = (TextView) findViewById(R.id.ratingsdd);
        addReview.setText(add_review);
        strings = new ArrayList<>();
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
        RelativeLayout toolbarNotification = (RelativeLayout) findViewById(R.id.toolnotification);

        toolbarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerDetails.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        if (Appearance.appSettings.getIs_notification() == 0) {
            toolbarNotification.setVisibility(View.GONE);
        }
        GradientDrawable drawable = (GradientDrawable) SellerDetails.this.getResources().getDrawable(selectedborder);
        drawable.setStroke(3, Color.parseColor("#" + Appearance.appSettings.getText_color()));

        RelativeLayout toolbarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerDetails.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                SearchActivity.searchMin = "";
                ApplyFilter.maximumSeekBar = "5000";
                productid = 0;
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
                Intent intent = new Intent(SellerDetails.this, CartActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView toolBarTitle = (TextView) findViewById(R.id.titlebar);

        Intent intent = getIntent();
        Images = intent.getStringExtra("image");
        Picasso.with(getApplicationContext()).load(Images).into(img);

        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast.makeText(SellerDetails.this, "Hiii", Toast.LENGTH_SHORT).show();
                dialog = new Dialog(SellerDetails.this, R.style.AlertDialogStyle_Default);
                dialog.setContentView(R.layout.addreviews);
                description = (AppCompatEditText) dialog.findViewById(R.id.reviewDescription);
                if (description == null) {
                    description.setText("");
                }
                description.setText(myReview);
                description.setSelection(description.getText().length());
                choosebutton = (Button) dialog.findViewById(R.id.choosebutton);
                choosebutton.setVisibility(View.VISIBLE);
                choosebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                    }
                });
                Button dialogOKbutton = (Button) dialog.findViewById(R.id.addreview);
                Button dialogCancelButton = (Button) dialog.findViewById(R.id.reviewcancel);
                ratingBar = (ScaleRatingBar) dialog.findViewById(R.id.ratingBar);
                ratingBar.setEmptyDrawableRes(R.drawable.startempty);
                ratingBar.setFilledDrawable(getResources().getDrawable(R.drawable.starfilled));
                ratingBar.setClickable(false);
                TextView ratingsandReviewsText = (TextView) dialog.findViewById(R.id.rateAndWriteAReviewText);
                TextView yourRatingText = (TextView) dialog.findViewById(R.id.yourRatingText);
                try {
                    //   ratingsandReviewsText.setText(Label.productLabel.getRatings_and_reviews());
                    //  yourRatingText.setText(Label.productLabel.getYour_rating());
                    // dialogOKbutton.setText(Label.productLabel.getAdd_review());
                    // dialogCancelButton.setText(Label.globalLabel.getCancel());
                    // description.setHint(Label.productLabel.getEnter_your_review());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
              //  Toast.makeText(SellerDetails.this, "Helloooo", Toast.LENGTH_SHORT).show();
                ratingBar.setRating(ratingbar.getRating());
                Log.i("MY RATING", "" + ratingBar.getRating());

                setStarColor(ratingBar.getRating(), ratingBar);
                ratingStars = (int) ratingBar
                        .getRating();
                dialogOKbutton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
                description.setHighlightColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));

                ratingBar.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            float touchPositionX = event.getX();
                            float width = ratingBar.getWidth();
                            float starsf = (touchPositionX / width) * 5.0f;
                            ratingStars = (int) starsf + 1;
                            Log.i("MYRATING", "" + ratingBar.getRating());
                            ratingBar.setRating(ratingStars);

                            v.setPressed(false);
                        }
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            v.setPressed(true);
                        }

                        if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                            v.setPressed(false);
                        }

                        setStarColor(ratingBar.getRating(), ratingBar);
                        return true;
                    }
                });

                dialogCancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialogOKbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (ratingStars <= 0) {
                            new CustomToast(SellerDetails.this, "please provide rating");

                            Animation shake = AnimationUtils.loadAnimation(SellerDetails.this, R.anim.shake);

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

                            Log.i("AAA_review", description.getText().toString());
                            Log.i("AAA_rating", ratingStars + "");
                            Log.i("AAA_password", pwda + "");
                            Log.i("AAA_id", userid + "");
                            Log.i("AAA_product_id", productid + "");
                            Log.i("AAA_language_id", languageid);
                       //     Log.i("AAA_Image", "123" + base64String.toString().replace("[", "").replace("]", ""));

                       //     System.out.println("AAA_AAA" + base64String.toString().replace("[", "").replace("]", ""));

                            try {
                                jsonObject.put("business_id",IConstants.BUSINESS_ID);
                                jsonObject.put("review", description.getText().toString());
                                jsonObject.put("rating", ratingStars);
                                jsonObject.put("password", pwda);
                                jsonObject.put("id", userid);
                                jsonObject.put("seller_detail_id", "2");
                                jsonObject.put("language_id", languageid);
                                jsonObject.put("images", base64String.toString().replace("[", "").replace("]", ""));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            System.out.println("dcxz" + jsonObject);
                            VolleyTask volleyTask = new VolleyTask(SellerDetails.this, IConstants.URL_POSTSELLERREVIEW, jsonObject, Request.Method.POST);
                            volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                                @Override
                                public void fnPostTaskCompleted(JSONArray response) {

                                }

                                @Override
                                public void fnPostTaskCompletedJsonObject(JSONObject response) {
                                    dialog.dismiss();
                                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                                    Log.i("MY RESPONSE", response.toString() + "");

                                    if (!new ResponseHandler().validateResponse(SellerDetails.this, response)) {

                                        return;
                                    }
                                    try {

                                        new CustomToast(SellerDetails.this, response.getJSONObject("data").getString("msg"));

                                    //    setReviews(response.getJSONObject("data").getJSONObject("data").getJSONArray("sellerReviews"));
                                    //   ratingCount.setText(String.valueOf(response.getJSONObject("data").getJSONObject("data").getJSONArray("sellerReviews").length()) + "reviews and ratings");

                                     //   reviewsCountText.setText(String.valueOf(response.getJSONObject("data").getJSONObject("data").getJSONArray("sellerReviews").length()) + " Reviews");

                                        int rating = response.getJSONObject("data").getJSONObject("data").getJSONObject("myReview").getInt("rating");
                                        ratingbar.setRating(ratingBar.getRating());
                                        setStarColor(ratingBar.getRating(), ratingbar);

                                        double rating1 = Double.parseDouble(String.valueOf(response.getJSONObject("data").getJSONObject("data").getDouble("rating")));
                                        DecimalFormat decimal = new DecimalFormat("0.0");

                                        String formattedValue = String.format(Locale.ENGLISH, "%.1f", rating1);
                                        rating1 = Double.parseDouble(formattedValue);

                                        String col = ColorManager.setRatingColor(rating1);

                                        Log.i("In Dialog", "fnPostTaskCompletedJsonObject: " + col);

                                    /*    GradientDrawable bgShape = (GradientDrawable) colorLayout.getBackground();
                                        bgShape.setColor(Color.parseColor(col));*/

                                        Log.i("Act", "fnPostTaskCompbvletedJsonObject: " + rating1);

                                     //   ratings.setText(String.valueOf(rating1));
                                        ratings2.setText(formattedValue);
                                        myReview = description.getText().toString();

                                        base64String.clear();
                                        findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

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
                });

                dialog.show();

            }
        });



        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setColorSchemeColors((Color.parseColor("#" + Appearance.appSettings.getText_color())));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Refresh items
                getData();

            }
        });
        getData();
    }



    private void setReviews(JSONArray jsonArray1) {
        Gson gson = new Gson();
        List<ReviewsModel> list = Arrays.asList(gson.fromJson(jsonArray1.toString(), ReviewsModel[].class));
        System.out.println("dadadada" + list);
        RecyclerView reviewsRecycler = (RecyclerView) findViewById(R.id.reviewrecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        reviewsRecycler.setLayoutManager(layoutManager);
        ReviewAdpater reviewAdpater = new ReviewAdpater(this, list);
        reviewsRecycler.setNestedScrollingEnabled(false);
        reviewsRecycler.setAdapter(reviewAdpater);
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



    private void getData() {
    }



}

