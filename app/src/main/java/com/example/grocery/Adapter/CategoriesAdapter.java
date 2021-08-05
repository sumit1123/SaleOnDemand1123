package com.example.grocery.Adapter;

import android.content.Intent;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.example.grocery.Activities.ApplyFilter;
import com.example.grocery.Activities.Categories;
import com.example.grocery.Activities.ProductActivity;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Activities.SearchActivity;
import com.example.grocery.Model.AllCategoriesModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.List;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;

/**
 * Created by Administrator on 9/15/2017.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.Infoholder> {
    private final Categories context;
    private final List<AllCategoriesModel> list;
    private JSONObject jsonObject;

    public CategoriesAdapter(Categories categories, List<AllCategoriesModel> allCategoriesModelList) {
        this.context = categories;
        this.list = allCategoriesModelList;

    }

    @Override
    public Infoholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allcategoriesadapter, parent, false);
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

        if (list.get(position).getIs_parent_category() == 1) {
            holder.plusIcon.setVisibility(View.VISIBLE);
        } else {
            holder.plusIcon.setVisibility(View.GONE);
        }
        Picasso.with(context).load(IConstants.BASE_IMAGE_URL+list.get(position).getCategory_image())
                .error(R.drawable.product_big).into(holder.imagecate);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Infoholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final List<AllCategoriesModel> list;
        private final Categories context;
        private final ImageView plusIcon,imagecate;
        TextView categoryName;

        public Infoholder(View itemView, List<AllCategoriesModel> list, Categories context) {
            super(itemView);
            this.list = list;
            this.context = context;
            categoryName = (TextView) itemView.findViewById(R.id.textcate);
            plusIcon = (ImageView) itemView.findViewById(R.id.addCategoryImage);
            imagecate = (ImageView) itemView.findViewById(R.id.imagecate);

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
            System.out.println();
            if (parent == 1) {
                intent = new Intent(context, Categories.class);

                intent.putExtra("category_id", categoryTranslationModel.getCategory_id());
                  intent.putExtra("category_name",categoryTranslationModel.getCategory_name());
            } else {
                intent = new Intent(context, ProductActivity.class);
                SearchActivity.formatedStringSearch = "";
                SearchActivity.searchMax = "";
                isViewWithCatalog = false;
                ApplyFilter.brandArrays = null;
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.minimumSeekBar = "0";
                ProductDetails.productid = 0;
                ProductActivity.sortedid = 0;
                 ProductActivity.name=categoryTranslationModel.getCategory_name();
                ProductActivity.sucat_id = categoryTranslationModel.getCategory_id();
                ProductActivity.image = "true";


            }
            context.startActivity(intent);


        }
    }
}
