package com.example.grocery.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.grocery.Adapter.PhotosAdapter;
import com.example.grocery.Adapter.SlidingImage_Adapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Model.OptionalImagesModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CirclePageIndicator;
import com.example.grocery.utils.LoaderColorChanger;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.grocery.Activities.ProductDetails.nameofProduct;
import static com.example.grocery.Activities.ProductDetails.strings;
import static com.example.grocery.interfaces.IConstants.BASE_IMAGE_URL;

public class ImageShowActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<String> ImagesArray = new ArrayList<String>();
    private List<OptionalImagesModel> lista;
    private ArrayList<String> stringsa;
    private ArrayList<String> catname;
    private Bitmap image;
    ArrayList<Uri> sss;
    private Uri uri;
    private ProgressDialog progressDialog;
    private String type;
    private int pager_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_image_show);
        Bundle extras1 = getIntent().getExtras();
        type = extras1.getString("type");
        pager_position = extras1.getInt("pager_position");
        if (type.matches("reviews")) {
            //labelsShared = getSharedPreferences("labels", MODE_PRIVATE);
        }
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        Bundle extras = getIntent().getExtras();
        catname = extras.getStringArrayList("position");
        ((RelativeLayout) findViewById(R.id.sharelayout)).setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        LinearLayout toolbarNotification = (LinearLayout) findViewById(R.id.toolnotification);
        DrawableCompat.setTint(((ImageView) findViewById(R.id.shareback)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        DrawableCompat.setTint(((ImageView) findViewById(R.id.shareshare)).getDrawable(), Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

        toolbarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.matches("reviews")) {
                    shareItem(PhotosAdapter.photosString.get(mPager.getCurrentItem()));
                } else {
                    shareItem(strings.get(mPager.getCurrentItem()));
                }
            }
        });

        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        toolbarTitle.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        toolbarTitle.setText(nameofProduct);

        init();
    }

    public void shareItem(final String url) {
        Picasso.with(getApplicationContext()).load(type.matches("reviews") ? IConstants.BASE_IMAGE_URL + url : BASE_IMAGE_URL + url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                new AsyncTaskRunner().execute(bitmap);

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });

    }

    public Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }


    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(ImageShowActivity.this, catname, type));


        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = catname.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        mPager.setCurrentItem(pager_position);
     /*   Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 100000, 10000);*/

        // Pager listener over indicator

        indicator.setPageColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        indicator.setFillColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //  new AsyncTaskRunner().execute();
    }

    private class AsyncTaskRunner extends AsyncTask<Bitmap, String, Uri> {

        private String resp;

        @Override
        protected Uri doInBackground(Bitmap... params) {
            Uri bmpUri = null;
            Bitmap bmp = params[0];
            try {
                File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
                FileOutputStream out = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.close();
                bmpUri = Uri.fromFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmpUri;
        }


        @Override
        protected void onPostExecute(Uri result) {
            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("image/*");
            i.putExtra(Intent.EXTRA_STREAM, result);
            startActivity(Intent.createChooser(i, "Share Image"));
            System.out.println("ffffddd" + sss);
        }


        @Override
        protected void onPreExecute() {
            findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }
}