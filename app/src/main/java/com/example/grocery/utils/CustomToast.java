package com.example.grocery.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocery.Appearances.Appearance;
import com.example.grocery.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Mohd Afzal on 11/28/2017.
 */

public class CustomToast {



    public CustomToast(Context context, String messege) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view = inflater.inflate( R.layout.custom_toast, null );

        view.setBackgroundColor(Color.parseColor("#"+ Appearance.appSettings.getToast_back_color()));

        TextView text = (TextView) view.findViewById(R.id.text);
        text.setTextColor(Color.parseColor("#"+Appearance.appSettings.getToast_text_color()));
        text.setText(messege);

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
}
