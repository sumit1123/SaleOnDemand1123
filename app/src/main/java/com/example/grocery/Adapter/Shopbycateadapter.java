package com.example.grocery.Adapter;

import android.content.Intent;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.example.grocery.Activities.ApplyFilter;
import com.example.grocery.Activities.Categories;
import com.example.grocery.Activities.ProductActivity;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Activities.SearchActivity;
import com.example.grocery.Activities.SellerActivity;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Model.AllCategoriesModel;
import com.example.grocery.R;
import com.example.grocery.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

import static androidx.core.content.res.ResourcesCompat.getColorStateList;
import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;

/**
 * Created by Mohd Afzal on 11/10/2017.
 */

public class Shopbycateadapter extends RecyclerView.Adapter<Shopbycateadapter.Infoholder> {
    private final AppCompatActivity context;
    private final List<AllCategoriesModel> list;

    public Shopbycateadapter(AppCompatActivity allCategorieslist, List<AllCategoriesModel> allCategoriesModelList) {
        this.context = allCategorieslist;
        this.list = allCategoriesModelList;
    }

    @Override
    public Infoholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopbycategoryadapter, parent, false);

        return new Infoholder(view, list, context);
    }

    @Override
    public void onBindViewHolder(Infoholder holder, int position) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(list.get(position).getCategory_translation());
        if (jsonString.equals("null")) {
            jsonString = gson.toJson(list.get(position).getDefault_category_translation());
        }

        AllCategoriesModel.CategoryTranslationBean categoryTranslationModel = gson.fromJson(jsonString,
                AllCategoriesModel.CategoryTranslationBean.class);
        System.out.println("WDSXz" + categoryTranslationModel.getCategory_name());
        holder.categoryName.setText(categoryTranslationModel.getCategory_name());

        if (list.get(position).getCategory_image() == null) {
            Picasso.with(context).load(R.drawable.product).transform(new CircleTransform()).into(holder.imageView);
           // Picasso.with(context).load(R.drawable.product).into(holder.imageView);
        } else {
            Picasso.with(context).load(BASE_IMAGE_URL + list.get(position).getCategory_image()).
                    error(R.drawable.product_big).transform(new CircleTransform()).into(holder.imageView);

         /* Picasso.with(context).load(BASE_IMAGE_URL  + list.get(position).getCategory_image()).
                  error(R.drawable.product_big).into(holder.imageView);*/
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Infoholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final List<AllCategoriesModel> list;
        private final AppCompatActivity context;
        TextView categoryName;
        ImageView imageView;

        public Infoholder(View itemView, List<AllCategoriesModel> list, AppCompatActivity context) {
            super(itemView);
            this.list = list;
            this.context = context;
            categoryName = (TextView) itemView.findViewById(R.id.textcate);
            imageView = (ImageView) itemView.findViewById(R.id.imagecate);
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
            Intent intent;
            int parent = list.get(getAdapterPosition()).getIs_parent_category();
            if (parent == 1) {
                intent = new Intent(context, Categories.class);
                intent.putExtra("category_id", categoryTranslationModel.getCategory_id());
                intent.putExtra("category_name", categoryTranslationModel.getCategory_name());
            } else {
                      if(Appearance.appSettings.getIs_business_directory() == 1) {
                          intent = new Intent(context, SellerActivity.class);
                      }
                      else
                      {
                          intent = new Intent(context, ProductActivity.class);
                      }
                /*intent = new Intent(context, ProductActivity.class);*/
             //  intent = new Intent(context, SellerActivity.class);
                SearchActivity.formatedStringSearch = "";
                SearchActivity.searchMax = "";
                SearchActivity.searchMax = "";
                ApplyFilter.brandArrays = null;
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.minimumSeekBar = "0";
                ProductDetails.productid = 0;
                ProductActivity.sortedid = 0;
                System.out.println("edcsx" + categoryTranslationModel.getCategory_id());
                ProductActivity.name = categoryTranslationModel.getCategory_name();
                ProductActivity.sucat_id = list.get(getAdapterPosition()).getCategory_id();
                ProductActivity.image = "true";
                SearchActivity.page_count = 1;

            }
            context.startActivity(intent);
        }
    }
}
