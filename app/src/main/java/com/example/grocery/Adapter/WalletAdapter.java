package com.example.grocery.Adapter;

import android.content.SharedPreferences;

import android.text.Html;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grocery.Activities.WalletActivity;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Model.WalletModel;
import com.example.grocery.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.util.Preconditions.checkArgument;


/**
 * Created by Mohd Afzal on 12/9/2017.
 */

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.InfoHolder> {
    private final WalletActivity context;
    private final List<WalletModel> list;

    public WalletAdapter(WalletActivity walletActivity, List<WalletModel> walletModels) {
        this.context = walletActivity;
        this.list = walletModels;
    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.walletadapter, parent, false);
        return new InfoHolder(view);
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, int position) {

        holder.title.setText(list.get(position).getTransaction_title());
        if (list.get(position).getTransaction_type() == 2) {
            holder.arrow.setRotation(180);
        }
        if (list.get(position).getTransaction_type() == 3) {
            holder.arrow.setImageResource(R.drawable.arrowanti);
            holder.arrow.setRotation(270);
        }
        if (list.get(position).getTransaction_type() == 4) {
            holder.arrow.setImageResource(R.drawable.arrow);
            holder.arrow.setRotation(270);

        }
       setdate(holder.date,list.get(position).getCreated_at());
        holder.amount.setText("" + Appearance.appTranslation.getCurrency() + " " + list.get(position).getAmount());
        holder.description.setText(list.get(position).getNarration());
        holder.currencytext.setText(Appearance.appTranslation.getCurrency());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView date;
        private final TextView amount;
        private final TextView description;
        private final TextView currencytext;
        private final ImageView arrow;


        public InfoHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            currencytext = (TextView) itemView.findViewById(R.id.currencytext);
            arrow = (ImageView) itemView.findViewById(R.id.arrow);
            date = (TextView) itemView.findViewById(R.id.date);
            amount = (TextView) itemView.findViewById(R.id.amount);

        }
    }
    private void setdate(TextView order, String string) {
        System.out.println("fdmsdx"+string);
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

        String day= (String) DateFormat.format("dd",   dates);
        String dayNumberSuffix = getDayOfMonthSuffix(Integer.parseInt(day));

        SimpleDateFormat  format2 = new SimpleDateFormat(" d'" + dayNumberSuffix + "' MMMM yyyy",Locale.ENGLISH);
        System.out.println("fdcx" + format2.format(dates));        System.out.println("fdcx" + format2.format(dates));
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
