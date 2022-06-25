package com.example.grocery.Adapter;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.grocery.Model.ReviewsModel;
import com.example.grocery.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.util.Preconditions.checkArgument;


public class ReviewAdpater extends RecyclerView.Adapter<ReviewAdpater.Infoholder> {

    private final AppCompatActivity context;
    private final List<ReviewsModel> list;
    private boolean myReview;

    public ReviewAdpater(AppCompatActivity appCompatActivity, List<ReviewsModel> list) {
        this.context = appCompatActivity;
        this.list = list;
    }

    @Override
    public Infoholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewadapter, parent, false);

        return new Infoholder(view);
    }

    @Override
    public void onBindViewHolder(Infoholder holder, int position) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.photosRecycler.setLayoutManager(layoutManager);
        holder.photosRecycler.setNestedScrollingEnabled(false);
        SharedPreferences prefs = context.getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        try {
            if (list.get(position).getUser_id() == Integer.parseInt(userid)) {
                myReview = true;
            } else {
                myReview = false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            //When Id == TOKEN THIS WILL Exectute
        }
        PhotosAdapter photosAdapter = new PhotosAdapter(list.get(position).getImages(), context, myReview);
        holder.photosRecycler.setAdapter(photosAdapter);
        if (list.get(position).getReview() != null) {
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(list.get(position).getReview());

        }
        if (list.get(position).getUser().getName() != null) {

            holder.title.setText(list.get(position).getUser().getEmail());


        } else {
            holder.title.setText(list.get(position).getUser().getName());
        }
        System.out.println("fdcx" + list.get(position).getDate());

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


        Date date = null;
        try {
            date = format1.parse(list.get(position).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String day = (String) DateFormat.format("dd", date);
        SimpleDateFormat format2 = null;
        String dayNumberSuffix = getDayOfMonthSuffix(Integer.parseInt(day));
        format2 = new SimpleDateFormat(" d'" + dayNumberSuffix + "' MMMM yyyy", Locale.ENGLISH);


        System.out.println("fdcx" + format2.format(date));
        holder.date.setText(Html.fromHtml(format2.format(date)));
/*
        System.out.println(Integer.valueOf(list.get(position).getUser_rating().toString());
*/
        double rating = list.get(position).getRating();
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

        GradientDrawable bgShape = (GradientDrawable) holder.ratingLayout.getBackground();
        bgShape.setColor(Color.parseColor(col));

        holder.stars.setText(String.format(Locale.ENGLISH, "%.1f", list.get(position).getRating()));
        if (position == list.size() - 1) {
            holder.line.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Infoholder extends RecyclerView.ViewHolder {
        private final LinearLayout line, ratingLayout;
        private final RecyclerView photosRecycler;
        TextView title, description, stars, postedby, date;

        public Infoholder(View itemView) {
            super(itemView);
            line = (LinearLayout) itemView.findViewById(R.id.line);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            stars = (TextView) itemView.findViewById(R.id.rating);
            date = (TextView) itemView.findViewById(R.id.posteddate);
            photosRecycler = (RecyclerView) itemView.findViewById(R.id.photosRecycler);
            ratingLayout = (LinearLayout) itemView.findViewById(R.id.ratingLayout);

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
}
