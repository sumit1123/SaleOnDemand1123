package com.example.grocery.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.grocery.Activities.ViewAllReviews;
import com.example.grocery.Model.ReviewsModel;
import com.example.grocery.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mohd Afzal on 9/28/2017.
 */

public class ViewAllReviewsAdapter extends RecyclerView.Adapter<ViewAllReviewsAdapter.Infoholder> {

private final ViewAllReviews context;
private final List<ReviewsModel> list;

public ViewAllReviewsAdapter(ViewAllReviews productDisplayActivity, List<ReviewsModel> list) {
        this.context=productDisplayActivity;
        this.list=list;
        }



    @Override
public Infoholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewadapter,parent,false);

        return new Infoholder(view);
        }

@Override
public void onBindViewHolder(Infoholder holder, int position) {
    System.out.println("rdf");
        holder.description.setText(list.get(position).getReview());
        holder.date.setText(list.get(position).getDate());
        holder.title.setText(list.get(position).getUser().getName());
        holder.stars.setText(list.get(position).getRating()+".0");

        }

@Override
public int getItemCount() {
        return list.size();
        }

public class Infoholder extends RecyclerView.ViewHolder {
    TextView title,description,stars,postedby,date;
    public Infoholder(View itemView) {
        super(itemView);
        title=(TextView)itemView.findViewById(R.id.title);
        description=(TextView)itemView.findViewById(R.id.description);
        stars=(TextView)itemView.findViewById(R.id.rating);
        date=(TextView)itemView.findViewById(R.id.posteddate);

    }
}
}
