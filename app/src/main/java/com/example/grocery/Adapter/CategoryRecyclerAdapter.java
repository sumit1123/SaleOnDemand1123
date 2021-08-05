package com.example.grocery.Adapter;

import android.content.Intent;
import android.graphics.Typeface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.grocery.Activities.Categories;
import com.example.grocery.Activities.Dashboard;
import com.example.grocery.Model.LimitedModel;
import com.example.grocery.R;

import org.apache.commons.lang.StringEscapeUtils;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mohd Afzal on 11/13/2017.
 */

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.InfoHolder> {
    private final Dashboard context;
    private final List<LimitedModel> list;


    public CategoryRecyclerAdapter(Dashboard dashboard, List<LimitedModel> list) {
        this.context = dashboard;
        this.list = list;

    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoryrecycleradapter, parent, false);
        return new InfoHolder(view, context, list);
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, int position) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/fontawesome_webfont.ttf");

        holder.icon.setTypeface(font);

        String temp = StringEscapeUtils.unescapeJava(list.get(position).getCategory_icon());
        holder.icon.setText(temp);
        holder.name.setText(list.get(position).getCategory_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView name;
        private final TextView icon;
        private final Dashboard context;
        private final List<LimitedModel> list;

        public InfoHolder(View itemView, Dashboard context, List<LimitedModel> list) {
            super(itemView);
            this.context = context;
            this.list = list;
            name = (TextView) itemView.findViewById(R.id.catetitle);
            icon = (TextView) itemView.findViewById(R.id.cateimage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            String category_name = list.get(getAdapterPosition()).getCategory_name();
            int category_id=list.get(getAdapterPosition()).getCategory_id();
            if(category_id==0){
                category_name="All Categories";
            }

            Intent intent = new Intent(context, Categories.class);
            intent.putExtra("category_id", category_id);
            intent.putExtra("category_name", category_name);
            context.startActivity(intent);


        }
    }
}
