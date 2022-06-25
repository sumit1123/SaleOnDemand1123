package com.example.grocery.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Mohd Afzal on 1/23/2018.
 */

public class ClassLauncher {


    private Context context;

    public void launchActivity(String className, Context context) throws Exception {
        this.context=context;
        Intent intent = new Intent(context, getActivityClass(className));
        context.startActivity(intent);
    }

    private Class<? extends Activity> getActivityClass(String target) throws Exception {
        ClassLoader classLoader = context.getClassLoader();

        @SuppressWarnings("unchecked")
        Class<? extends Activity> activityClass = (Class<? extends Activity>) classLoader.loadClass(target);

        return activityClass;
    }
}