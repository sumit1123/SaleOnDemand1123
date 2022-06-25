package com.example.grocery.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Parcelable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.grocery.R;
import com.example.grocery.interfaces.VarintListner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.viewpager.widget.PagerAdapter;

import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;

/**
 * Created by Mohd Afzal on 10/13/2017.
 */

public class Sliding_ImageAdapterDasboard extends PagerAdapter {


    private final VarintListner listner;
    private final boolean b;
    private final boolean scale;
    private ArrayList<String> IMAGES;
    private LayoutInflater inflater;
    private Context context;
    private Resources mResources;
    private Bitmap mBitmap;

    //--------------

    public Sliding_ImageAdapterDasboard(Context context, boolean b, boolean scale, ArrayList<String> IMAGES, VarintListner varintListner) {
        this.b = b;
        this.scale = scale;
        this.context = context;
        this.IMAGES = IMAGES;
        inflater = LayoutInflater.from(context);
        this.listner = varintListner;
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


        View imageLayout = inflater.inflate(b ? R.layout.curvedborderlayout : R.layout.slidingimageslayoutdashboard, view, false);
        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);
        if (scale) {
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
        imageLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                listner.onModelClick(v, position, 1);
            }
        });

        Picasso.with(context).load(BASE_IMAGE_URL + IMAGES.get(position)).error(R.drawable.product_big).into(imageView);

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

    @Override
    public float getPageWidth(final int position) {
        if (b) {
            return 0.8f;
        } else {
            return 1f;
        }
    }


}
