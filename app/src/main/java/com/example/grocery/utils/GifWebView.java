package com.example.grocery.utils;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by Administrator on 9/14/2017.
 */

public class GifWebView extends WebView {

    public GifWebView(Context context, String path) {
        super(context);

        loadUrl(path);
    }
}
