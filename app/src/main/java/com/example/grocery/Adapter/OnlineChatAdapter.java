package com.example.grocery.Adapter;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.grocery.Activities.OnlineChatActivity;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Model.OnlineChatModel;
import com.example.grocery.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.util.Preconditions.checkArgument;


/**
 * Created by Mohd Afzal on 9/30/2017.
 */

public class OnlineChatAdapter extends RecyclerView.Adapter<OnlineChatAdapter.InfoHolder> {
    private final OnlineChatActivity context;
    private final List<OnlineChatModel> list;


    public OnlineChatAdapter(OnlineChatActivity onlineChatActivity, List<OnlineChatModel> list) {
        this.context = onlineChatActivity;
        this.list = list;
    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatadapter, parent, false);
        return new InfoHolder(view);
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, int position) {
        final InfoHolder viewHolder = (InfoHolder) holder;

        final OnlineChatModel mItems = this.list.get(position);
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserId", MODE_PRIVATE);
        int user_id = Integer.parseInt(sharedPreferences.getString("user_id", "1"));
        System.out.println();
        if (list.get(position).getUser_id() == user_id) {

            viewHolder.leftImage.setVisibility(View.GONE);
            setdate(holder.date, list.get(position).getUpdated_at());
            viewHolder.name.setText(list.get(position).getUser().getName());
            viewHolder.textComment.setText(mItems.getMessage());
            viewHolder.cardComment.setBackground(context.getResources().getDrawable(R.drawable.chatadaptercurved));
            GradientDrawable bgShape = (GradientDrawable) viewHolder.cardComment.getBackground();
            bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
            viewHolder.date.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
            viewHolder.name.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
            viewHolder.textComment.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        } else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1.0f;
            params.gravity = Gravity.START;

            viewHolder.motion.setLayoutParams(params);
            viewHolder.rightImage.setVisibility(View.GONE);

            viewHolder.textComment.setText(mItems.getMessage());
            setdate(viewHolder.date, list.get(position).getCreated_at());

            viewHolder.name.setText(list.get(position).getUser().getName());
            viewHolder.date.setTextColor(Color.parseColor("#000000"));
            viewHolder.textComment.setTextColor(Color.parseColor("#000000"));
            viewHolder.name.setTextColor(Color.parseColor("#000000"));

            viewHolder.cardComment.setBackground(context.getResources().getDrawable(R.drawable.chatadaptercurved));
            GradientDrawable bgShape = (GradientDrawable) viewHolder.cardComment.getBackground();
            bgShape.setColor(Color.parseColor("#FFFFFF"));

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder {
        private final CardView cardComment;
        private final ImageView rightImage;
        private final LinearLayout motion;
        private final ImageView leftImage;
        TextView textComment, name, date;

        public InfoHolder(View itemView) {
            super(itemView);
            textComment = (TextView) itemView.findViewById(R.id.textComment);

            cardComment = (CardView) itemView.findViewById(R.id.cardComment);
            name = (TextView) itemView.findViewById(R.id.nameofchat);
            date = (TextView) itemView.findViewById(R.id.timeofchat);
            rightImage = (ImageView) itemView.findViewById(R.id.acccntimmage);
            leftImage = (ImageView) itemView.findViewById(R.id.acccntimmage1);

            motion = (LinearLayout) itemView.findViewById(R.id.motion);


        }
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
}
