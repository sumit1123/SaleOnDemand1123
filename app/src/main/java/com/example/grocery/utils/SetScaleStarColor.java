package com.example.grocery.utils;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;

import com.example.grocery.R;
import com.willy.ratingbar.ScaleRatingBar;

/**
 * Created by Mohd Afzal on 7/12/2018.
 */

public class SetScaleStarColor {

    float rating;
    ScaleRatingBar ratingBar;

    public SetScaleStarColor(float rating, ScaleRatingBar ratingBar) {
        this.rating = rating;
        this.ratingBar = ratingBar;
        drawStar(rating, ratingBar);
    }

    private String drawStar(float rating, ScaleRatingBar ratingBar) {

        String s = "#00bc58";
        ratingBar.setFilledDrawableRes(R.drawable.start_filled_green);

        try {
            if (rating == 1) {
                //RED
                ratingBar.setFilledDrawableRes(R.drawable.star_filled_red);
                s = "#ff4c4c";
            } else if (rating <= 3.5 && rating > 1) {
                //YELLOW
                ratingBar.setFilledDrawableRes(R.drawable.star_filled_yellow);
                s = "#ffaf4f";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
