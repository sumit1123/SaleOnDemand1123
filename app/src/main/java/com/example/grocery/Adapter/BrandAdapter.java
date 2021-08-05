package com.example.grocery.Adapter;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.example.grocery.Activities.ApplyFilter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.BrandModel;
import com.example.grocery.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.grocery.R.drawable.cb_selector;

/**
 * Created by Mohd Afzal on 9/29/2017.
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.Infoholder> {
    public static List<BrandModel> list;
    private final ApplyFilter basecontext;
    private ArrayList<String> names;
    List<Integer> integers = new ArrayList<>();
    private BrandModel.BrandTranslationBean productTranslationBean;

    public BrandAdapter(List<BrandModel> cityModels, ApplyFilter dashBoard) {

        this.list = cityModels;
        this.basecontext = dashBoard;
    }

    @Override
    public Infoholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cityadapter, parent, false);

        return new Infoholder(view, this, list);
    }

    public static void setAppCompatCheckBoxColors(final AppCompatCheckBox _checkbox, final int _uncheckedColor, final int _checkedColor) {
        int[][] states = new int[][]{new int[]{-android.R.attr.state_checked}, new int[]{android.R.attr.state_checked}};
        int[] colors = new int[]{_uncheckedColor, _checkedColor};
        _checkbox.setSupportButtonTintList(new ColorStateList(states, colors));
    }

    @Override
    public void onBindViewHolder(final Infoholder holder, final int position) {

        final Gson gson = new Gson();
        final BrandModel productModel = list.get(position);
        String jsonString = gson.toJson(productModel.getBrand_translation());
        System.out.println("zdnfjc" + jsonString);

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(productModel.getDefault_brand_translation());
            System.out.println("zdnfjc" + jsonString);

        }
        productTranslationBean = gson.fromJson(jsonString,
                BrandModel.BrandTranslationBean.class);
        holder.brandName.setText(productModel.getBrand_translation()== null ?
                productModel.getDefault_brand_translation().getBrand_name() :
                productModel.getBrand_translation().getBrand_name());
        try {
            String none = basecontext.getResources().getString(R.string.none_selected);
            basecontext.selectedBrand.setText(none);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        setAppCompatCheckBoxColors(holder.brandCheckBox, Color.parseColor("#" + Appearance.appSettings.getText_color()), Color.parseColor("#" + Appearance.appSettings.getText_color()));
        int brand_id = list.get(position).getBrand_id();
        if (ApplyFilter.brandArrays != null) {

            if (ApplyFilter.brandArrays.contains(brand_id)) {
                list.get(position).setSelected(true);

                holder.brandCheckBox.setChecked(list.get(position).getSelected());
                try {
                    String none = basecontext.getResources().getString(R.string.none_selected);
                    basecontext.selectedBrand.setText(String.valueOf(ApplyFilter.brandArrays.size()) + " " +none);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }


                DrawableCompat.setTint(basecontext.getResources().getDrawable(cb_selector), Color.parseColor("#" + Appearance.appSettings.getText_color()));


            }
        }

        //  holder.brandCheckBox.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#"+ colorSettings.getString("text_color", "#FFFFFF"))));
        holder.brandCheckBox.setTextColor(Color.parseColor("#cbff75"));

        //holder.brandCheckBox.setBackgroundColor(Color.parseColor("#"+ colorSettings.getString("text_color", "#FFFFFF")));


        holder.brandCheckBox.setTag(position);
        holder.brandCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.brandCheckBox.getTag();

                if (list.get(pos).getSelected()) {

                    list.get(pos).setSelected(false);

                } else {
                    list.get(pos).setSelected(true);

                }
                if (ApplyFilter.brandArrays == null) {
                    integers.clear();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getSelected()) {
                            integers.add(i);
                        }
                    }
                    System.out.println(integers);
                    try {
                        if (integers.size() == 0) {
                            String none = basecontext.getResources().getString(R.string.none_selected);
                            basecontext.selectedBrand.setText(none);

                        } else {
                            String none = basecontext.getResources().getString(R.string.none_selected);
                            basecontext.selectedBrand.setText(String.valueOf(integers.size()) + " " + none);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                } else {
                    integers.clear();

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getSelected()) {
                            integers.add(i);
                        }
                    }
                    System.out.println(integers);
                    basecontext.selectedBrand.setText(String.valueOf(integers.size()) + " Selected");

                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Infoholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final BrandAdapter context;
        private final List<BrandModel> list;
        TextView brandName;
        AppCompatCheckBox brandCheckBox;

        public Infoholder(View itemView, BrandAdapter cityAdapter, List<BrandModel> list) {
            super(itemView);
            this.context = cityAdapter;
            this.list = list;
            brandName = (TextView) itemView.findViewById(R.id.textcity);
            brandCheckBox = (AppCompatCheckBox) itemView.findViewById(R.id.cb);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            String s = String.valueOf(list.get(getAdapterPosition()).getBrand_id());


       /* basecontext.selectedBrand.setText(list.get(getAdapterPosition()).getBrand_name());
        basecontext.hide.setText(s);
        basecontext.setBrandID(list.get(getAdapterPosition()).getBrand_id());
        basecontext.dialog.dismiss();*/


        }
    }


}