package com.example.grocery.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.example.grocery.Activities.ApplyFilter;
import com.example.grocery.Activities.Categories;
import com.example.grocery.Activities.Dashboard;
import com.example.grocery.Activities.ProductActivity;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Activities.SearchActivity;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.AllCategoriesModel;
import com.example.grocery.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Mohd Afzal on 9/29/2017.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.InfoHolder> {
    private final Dashboard context;
    private final List<AllCategoriesModel> list;
    private JSONObject jsonObject;


    public NavigationAdapter(Dashboard dashboard, List<AllCategoriesModel> list) {
        this.context = dashboard;
        this.list = list;

    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigationadapter, parent, false);
        return new InfoHolder(view, list, context);
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, int position) {
        if (position == list.size() - 1) {
            holder.name.setText(list.get(position).getCategoryName());

        } else {
            Gson gson = new Gson();
            String jsonString = gson.toJson(list.get(position).getCategory_translation());
            if (jsonString.equals("null")) {
                jsonString = gson.toJson(list.get(position).getDefault_category_translation());
            }

            AllCategoriesModel.CategoryTranslationBean categoryTranslationModel = gson.fromJson(jsonString,
                    AllCategoriesModel.CategoryTranslationBean.class);
            System.out.println("WDSXz" + categoryTranslationModel.getCategory_name());
            holder.name.setText(categoryTranslationModel.getCategory_name());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final List<AllCategoriesModel> list;
        private final Dashboard context;
        TextView name;
        AppCompatTextView image;

        public InfoHolder(View itemView, List<AllCategoriesModel> list, Dashboard context) {
            super(itemView);
            this.list = list;
            this.context = context;

            name = (TextView) itemView.findViewById(R.id.atom);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(list.get(getAdapterPosition()).getCategory_translation());
            if (jsonString.equals("null")) {
                jsonString = gson.toJson(list.get(getAdapterPosition()).getDefault_category_translation());
            }

            AllCategoriesModel.CategoryTranslationBean categoryTranslationModel = gson.fromJson(jsonString,
                    AllCategoriesModel.CategoryTranslationBean.class);
            if (getAdapterPosition() == list.size() - 1) {
                DrawerLayout drawer1 = (DrawerLayout) context.findViewById(R.id.drawer_layout);
                drawer1.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(context, Categories.class);
                intent.putExtra("category_id", 0);
                try {
                   // intent.putExtra("category_name", (Label.productLabel.getAll_categories()));
                    String categories = context.getResources().getString(R.string.all_categories);
                    intent.putExtra("category_name",categories);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                context.startActivity(intent);

            } else {
                Intent intent;
                DrawerLayout drawer1 = (DrawerLayout) context.findViewById(R.id.drawer_layout);
                drawer1.closeDrawer(GravityCompat.START);
                drawer1.closeDrawer(GravityCompat.START);
                int parent = list.get(getAdapterPosition()).getIs_parent_category();
                if (parent == 1) {
                    intent = new Intent(context, Categories.class);

                    intent.putExtra("category_id", categoryTranslationModel.getCategory_id());
                    intent.putExtra("category_name", categoryTranslationModel.getCategory_name());


                } else {
                    intent = new Intent(context, ProductActivity.class);
                    SearchActivity.formatedStringSearch = "";
                    SearchActivity.searchMax = "";
                    ApplyFilter.brandArrays = null;
                    ApplyFilter.maximumSeekBar = "5000";
                    ApplyFilter.minimumSeekBar = "0";
                    ProductDetails.productid = 0;
                    ProductActivity.sortedid = 0;
                    System.out.println("edcsx" + list.get(getAdapterPosition()).getCategory_id());
                    intent.putExtra("category_name", categoryTranslationModel.getCategory_name());
                    ProductActivity.name=categoryTranslationModel.getCategory_name();
                    ProductActivity.sucat_id = categoryTranslationModel.getCategory_id();
                    ProductActivity.image = "true";
                    SearchActivity.page_count = 1;


                }
                context.startActivity(intent);
            }
        }
    }
}
