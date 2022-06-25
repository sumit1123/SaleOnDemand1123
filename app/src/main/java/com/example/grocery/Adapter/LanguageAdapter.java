package com.example.grocery.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.example.grocery.Activities.Dashboard;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.LanguagesModel;
import com.example.grocery.R;

import java.util.List;
import java.util.Locale;

import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Mohd Afzal on 12/26/2017.
 */
public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.Infoholder> {
    private final Dashboard context;
    private final List<LanguagesModel> list;
    private int row_index;
    public static int language_id;
    private LanguagesModel.LanguageTranslationBean productTranslationBean;

    public LanguageAdapter(Dashboard dashboard, List<LanguagesModel> list) {
        this.context = dashboard;
        this.list = list;
    }

    @Override
    public Infoholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.languageadapter, parent, false);
        return new Infoholder(view, context, list);
    }

    @Override
    public void onBindViewHolder(Infoholder holder, final int position) {
        final Gson gson = new Gson();
        final LanguagesModel productModel = list.get(position);
        String jsonString = gson.toJson(productModel.getLanguage_translation());
        System.out.println("zdnfjc" + jsonString);

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(productModel.getDefault_language_translation());
            System.out.println("zdnfjc" + jsonString);

        }
        productTranslationBean = gson.fromJson(jsonString,
                LanguagesModel.LanguageTranslationBean.class);
        holder.languageName.setText(productTranslationBean.getLanguage_name());
        holder.highlighter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                context.drawer.closeDrawer(GravityCompat.START);
                context.languageRecycler.setVisibility(View.GONE);
                context.addButton.setImageResource(R.drawable.add);
                language_id = list.get(position).getLanguage_id();
                SharedPreferences.Editor editor = context.getSharedPreferences("UserId", MODE_PRIVATE).edit();
                editor.putString("language", String.valueOf(list.get(position).getLanguage_id()));
                editor.putString("language_code", list.get(position).getLanguage_code());
                Configuration configuration = context.getResources().getConfiguration();
                configuration.setLocale(new Locale(list.get(position).getLanguage_code()));
                configuration.setLayoutDirection(new Locale(list.get(position).getLanguage_code()));
                context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
                editor.apply();

                Dashboard.is_language = 1;

                context.startActivity(new Intent(context, Dashboard.class));
                notifyDataSetChanged();
                context.finish();

            }
        });
        if (list.get(position).getLanguage_id() == language_id) {
            holder.languageName.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));


        } else {

            holder.languageName.setTextColor(context.getResources().getColor(R.color.black));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Infoholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView languageName;
        private final Dashboard context;
        private final List<LanguagesModel> list;
        private final LinearLayout highlighter;

        public Infoholder(View itemView, Dashboard context, List<LanguagesModel> list) {
            super(itemView);
            this.context = context;
            this.list = list;

            highlighter = (LinearLayout) itemView.findViewById(R.id.highlighter);
            languageName = (TextView) itemView.findViewById(R.id.textcate);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {


        }
    }
}
