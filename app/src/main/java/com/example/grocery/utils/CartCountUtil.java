package com.example.grocery.utils;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.grocery.Activities.Dashboard;
import com.example.grocery.R;

/**
 * Created by Mohd Afzal on 12/2/2017.
 */

public class CartCountUtil {


    private final TextView cartCount;
    private final AppCompatActivity context;

    public CartCountUtil(AppCompatActivity context) {
        this.context=context;
        cartCount = (TextView) context.findViewById(R.id.actionbar_notifcation_textview);
        if (Dashboard.cart_count !=0){
            cartCount.setVisibility(View.VISIBLE);
            cartCount.setText(String.valueOf(Dashboard.cart_count));
        }
        else{
            cartCount.setVisibility(View.GONE);

        }

    }
}
