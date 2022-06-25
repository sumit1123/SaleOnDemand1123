package com.example.grocery.utils;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import android.widget.TextView;

import com.example.grocery.Appearances.Appearance;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Field;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Mohd Afzal on 12/12/2017.
 */

public class TintOfEditText {

    private final AppCompatActivity context;

    public TintOfEditText(AppCompatActivity appCompatActivity, TextInputLayout til, AppCompatEditText description) {
        this.context=appCompatActivity;

        try {
            Field fCursorDrawableRes =
                    TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(description);
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(description);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);

            Drawable[] drawables = new Drawable[2];
            Resources res = description.getContext().getResources();
            drawables[0] = res.getDrawable(mCursorDrawableRes);
            drawables[1] = res.getDrawable(mCursorDrawableRes);
            drawables[0].setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()), PorterDuff.Mode.SRC_IN);
            drawables[1].setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()), PorterDuff.Mode.SRC_IN);
            fCursorDrawable.set(editor, drawables);
        } catch (final Throwable ignored) {
        }
        ColorStateList colorStateList = ColorStateList.valueOf(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        description.setSupportBackgroundTintList(colorStateList);
        try {
            Field fDefaultTextColor = TextInputLayout.class.getDeclaredField("mDefaultTextColor");
            fDefaultTextColor.setAccessible(true);
            fDefaultTextColor.set(til, new ColorStateList(new int[][]{{0}}, new int[]{ Color.parseColor("#000000") }));

            Field fFocusedTextColor = TextInputLayout.class.getDeclaredField("mFocusedTextColor");
            fFocusedTextColor.setAccessible(true);
            fFocusedTextColor.set(til, new ColorStateList(new int[][]{{0}}, new int[]{ Color.parseColor("#" + Appearance.appSettings.getText_color()) }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
