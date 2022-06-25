package com.example.grocery.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.example.grocery.Activities.OnlineChatActivity;
import com.example.grocery.Activities.OnlineSupportActivity;
import com.example.grocery.Model.ComplaintModel;
import com.example.grocery.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.util.Preconditions.checkArgument;

/**
 * Created by Mohd Afzal on 9/28/2017.
 */

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.InfoHolder> {
    private final OnlineSupportActivity context;
    private final List<ComplaintModel> list;

    private ComplaintModel.ComplaintStatusBean.ComplaintStatusTranslationBean statusTranslation;

    public ComplaintAdapter(OnlineSupportActivity onlineSupportActivity, List<ComplaintModel> list) {

        this.context = onlineSupportActivity;
        this.list = list;
    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaintadapter, parent, false);

        return new InfoHolder(view, context, list);
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, int position) {
        final Gson gson = new Gson();
        final ComplaintModel complaintModel = list.get(position);


        String jsonString = gson.toJson(complaintModel.getComplaint_status().getComplaint_status_translation());

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(complaintModel.getComplaint_status().getDefault_complaint_status_translation());

        }
        statusTranslation = gson.fromJson(jsonString,
                ComplaintModel.ComplaintStatusBean.ComplaintStatusTranslationBean.class);
        holder.title.setText(list.get(position).getDescription());
        holder.date.setText(list.get(position).getCreated_at());
        holder.description.setText(list.get(position).getUser().getName());
        holder.status.setText(statusTranslation.getComplaint_status_name());
        GradientDrawable bgShape = (GradientDrawable) holder.status.getBackground();
        bgShape.setColor(Color.parseColor("#" + list.get(position).getComplaint_status().getComplaint_status_color()));
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


        Date date = null;
        try {
            date = format1.parse(list.get(position).getCreated_at());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String day= (String) DateFormat.format("dd",   date);

        String dayNumberSuffix = getDayOfMonthSuffix(Integer.parseInt(day));

        SimpleDateFormat format2 = new SimpleDateFormat(" d'" + dayNumberSuffix + "' MMMM yyyy",Locale.ENGLISH);
        System.out.println("fdcx" + format2.format(date));
        holder.date.setText(Html.fromHtml(format2.format(date)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final OnlineSupportActivity context;
        private final List<ComplaintModel> list;
        TextView complaintId, title, description, postedby, date, status;

        public InfoHolder(View itemView, OnlineSupportActivity context, List<ComplaintModel> list) {
            super(itemView);
            this.context = context;
            this.list = list;
            title = (TextView) itemView.findViewById(R.id.complainttitle);
            description = (TextView) itemView.findViewById(R.id.complaintdescription);
            date = (TextView) itemView.findViewById(R.id.dateofcomplaint);
            status = (TextView) itemView.findViewById(R.id.complaintstatus);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, OnlineChatActivity.class);
            intent.putExtra("takemycomplaintid", String.valueOf(list.get(getAdapterPosition()).getComplaint_id()));
            intent.putExtra("takecomplaintname", list.get(getAdapterPosition()).getDescription());
            context.startActivity(intent);
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
