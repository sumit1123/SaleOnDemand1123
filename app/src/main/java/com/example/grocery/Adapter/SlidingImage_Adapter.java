package com.example.grocery.Adapter;

/**
 * Created by Mohd Afzal on 10/12/2017.
 */

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;

import java.util.ArrayList;

import androidx.viewpager.widget.PagerAdapter;

import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;


public class SlidingImage_Adapter extends PagerAdapter {


    private final String type;
    private ArrayList<String> IMAGES;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImage_Adapter(Context context, ArrayList<String> IMAGES, String type) {
        this.context = context;
        this.IMAGES=IMAGES;
        this.type=type;
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
    public Object instantiateItem(ViewGroup view, int position) {



        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);

        System.out.println(type.matches("reviews")? IConstants.BASE_IMAGE_URL + "review-images/" + IMAGES.get(position):BASE_IMAGE_URL+IMAGES.get(position));
        Glide.with(context)
                .load(type.matches("reviews")? IConstants.BASE_IMAGE_URL +IMAGES.get(position):BASE_IMAGE_URL+IMAGES.get(position))
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {


                        //  holder1.imageView.setBackground(context.getResources().getDrawable(R.drawable.product));

                        return true;
                    }


                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {


                        return false;
                    }
                })
                .into(imageView);
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