package com.example.grocery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Model.ProductModel;
import com.example.grocery.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import androidx.viewpager.widget.PagerAdapter;

import static android.content.Context.MODE_PRIVATE;
import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;

/**
 * Created by Mohd Afzal on 10/13/2017.
 */

public class OfferZoneSliderAdapter extends PagerAdapter {


    private List<ProductModel> IMAGES;
    private LayoutInflater inflater;
    private Context context;
    private String off1;


    public OfferZoneSliderAdapter(Context context, List<ProductModel> IMAGES) {
        this.context = context;
        this.IMAGES = IMAGES;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout = inflater.inflate(R.layout.dashofferadapter, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.productimage);


        Picasso.with(context).load(BASE_IMAGE_URL + IMAGES.get(position).getProduct_image()).into(imageView);
        TextView off = (TextView) imageLayout.findViewById(R.id.off);
        LinearLayout butto = (LinearLayout) imageLayout.findViewById(R.id.backgrounColor);

        butto.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        off.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        double calculatedMarketPrice = Double.parseDouble(String.valueOf(IMAGES.get(position).getSku().getMarket_price()));
        double calculatedMyPrice = new Double(IMAGES.get(position).getSku().getMy_price());
        double calculatedPercentage = (calculatedMyPrice / calculatedMarketPrice) * 100;
        Double doublePercentage = new Double(100 - calculatedPercentage);
        float offf = doublePercentage.floatValue();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        off1 = decimalFormat.format(doublePercentage);
        System.out.println("offfff" + off1);
        if (offf > 0) {
            off.setVisibility(View.VISIBLE);
            if (offf % 1 == 0) {
                int i = Math.round(offf);
                String offs = context.getResources().getString(R.string.off);
                off.setText(i + "%" + offs);

            } else {
                String offs = context.getResources().getString(R.string.off);
                off.setText(String.format(Locale.ENGLISH, "%.2f", offf) + "%" + offs);
            }
        } else {
            off.setVisibility(View.GONE);
        }
        imageLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //this will log the page number that was click
                Intent intent = new Intent(context, ProductDetails.class);
                Gson gson = new Gson();
                final ProductModel productModel = IMAGES.get(position);
                String jsonString = gson.toJson(productModel.getProduct_translation());
                if (jsonString.equals("null")) {
                    jsonString = gson.toJson(productModel.getDefault_product_translation());
                }
                final ProductModel.ProductTranslationBean productTranslationBean = gson.fromJson(jsonString, ProductModel.ProductTranslationBean.class);
                ProductDetails.productid = IMAGES.get(position).getProduct_id();
                ProductDetails.nameofProduct = productTranslationBean.getProduct_name();


                intent.putExtra("position", position);
                ProductDetails.nameofProduct = productTranslationBean.getProduct_name();

                context.startActivity(intent);
            }
        });


        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}
