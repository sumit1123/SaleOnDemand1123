package com.example.grocery.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Activities.ImageShowActivity;
import com.example.grocery.Model.ReviewsModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.VolleyTask;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Mohd Afzal on 2/6/2018.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.InfoHolder> {
    private final AppCompatActivity context;
    private List<ReviewsModel.ImagesBean> list;
    public static ArrayList<String> photosString;
    private final boolean myReview;

    public PhotosAdapter(List<ReviewsModel.ImagesBean> images, AppCompatActivity context, boolean myReview) {
        this.context = context;
        this.list = images;
        this.myReview = myReview;

    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bitmapadapter, parent, false);
        return new InfoHolder(view, context, list);
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, final int position) {
        System.out.println("newImageUrl" + IConstants.BASE_IMAGE_URL +list.get(position).getProduct_review_image());
        Picasso.with(context).load(IConstants.BASE_IMAGE_URL + list.get(position).getProduct_review_image()).into(holder.imageView);
        if (myReview) {
            holder.removeImage.setVisibility(View.VISIBLE);
        } else {
            holder.removeImage.setVisibility(View.GONE);

        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photosString = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    photosString.add(list.get(i).getProduct_review_image());

                }
                Intent intent = new Intent(context, ImageShowActivity.class);
                intent.putExtra("type", "reviews");
                intent.putExtra("position", photosString);
                intent.putExtra("name", "Photos");
                intent.putExtra("pager_position", position);

                context.startActivity(intent);
            }
        });
        holder.removeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
                SharedPreferences prefs = context.getSharedPreferences("UserId", MODE_PRIVATE);

                String userid = prefs.getString("user_id", "");
                String password = prefs.getString("pwd", "");
                String languageid = prefs.getString("language", String.valueOf(1));

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("review_image_id",list.get(position).getReview_image_id());
                    jsonObject.put("review_id", list.get(position).getReview_id());
                    jsonObject.put("id", userid);
                    jsonObject.put("business_id",IConstants.BUSINESS_ID);
                    jsonObject.put("password", password);
                    jsonObject.put("language_id", languageid);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("skdxz"+jsonObject);
                VolleyTask volleyTask = new VolleyTask(context, IConstants.URL_DeleteREVIEWIMAE, jsonObject, Request.Method.POST);
                volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                    @Override
                    public void fnPostTaskCompleted(JSONArray response) {
                    }

                    @Override
                    public void fnPostTaskCompletedJsonObject(JSONObject response) {
                        System.out.println("hhhs" + response.toString());
                        context.findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                        if (!new ResponseHandler().validateResponse(context, response)) {
                            return;
                        }
                        try {
                            new CustomToast(context,response.getJSONObject("data").getString("msg"));
                            Gson gson=new Gson();
                            JSONArray jsonArray1 = response.getJSONObject("data").getJSONObject("data").getJSONArray("review_images");
                            list = Arrays.asList(gson.fromJson(jsonArray1.toString(), ReviewsModel.ImagesBean[].class));
                            notifyDataSetChanged();



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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView imageView;
        private final AppCompatActivity context;
        private final List<ReviewsModel.ImagesBean> list;
        private final ImageView removeImage;

        public InfoHolder(View itemView, AppCompatActivity context, List<ReviewsModel.ImagesBean> list) {
            super(itemView);
            this.context = context;
            this.list = list;
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.imageBitmap);
            removeImage = (ImageView) itemView.findViewById(R.id.removeImage);


        }

        @Override
        public void onClick(View view) {


        }
    }
}
