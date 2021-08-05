package com.example.grocery.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.grocery.interfaces.IConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;


public class VolleyTask implements IConstants {
    private IPostTaskListener postTaskListener;
    private Context mContext;

    private JSONArray mJsonArrayData;
    private JSONObject mJsonObjectData;
    private String URL;
    private final String LOG_TAG = "VolleyTask";
    private int method;
    private int MY_SOCKET_TIMEOUT_MS=30000;

    public interface IPostTaskListener {
        void fnPostTaskCompleted(JSONArray response);
        void fnPostTaskCompletedJsonObject(JSONObject response);
        void fnErrorOccurred(String error);
    }

    public IPostTaskListener getListener() {
        return postTaskListener;
    }

    public void setListener( IPostTaskListener postTaskListener ) {
        this.postTaskListener = postTaskListener;
    }


    public VolleyTask(Context mContext, String URL, JSONObject jsonObjectData, int method ) {
        this.mContext = mContext;
        this.URL = URL;
        this.method = method;
        mJsonObjectData = jsonObjectData;
        fnExecuteTaskJsonObject();
    }

    /*public void executeAsync() {
        if( Utils.fnCheckIsNetworkAvailable( mContext ))
            fnExecuteTask();
    }*/

    private void fnExecuteTask() {
        JsonArrayRequest request = new JsonArrayRequest( method, URL, mJsonArrayData,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Utils.fnPrintLog( LOG_TAG, "Response fetched in fnExecuteTask: " +response.toString() );
                        fnNotifyTaskCompleted( response );
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error ) {
                Utils.fnPrintLog( LOG_TAG, "Error: " +error.getMessage() );
                Utils.fnPrintLog( LOG_TAG, "Request: " +mJsonArrayData.toString() );
                Utils.fnPrintLog( LOG_TAG, "Error fetched in fnExecuteTask: " +error.toString() );
                if( error.networkResponse != null ) {
                    Utils.fnPrintLog( LOG_TAG, "Error code: " +error.networkResponse.statusCode );
                }

                try {
                    fnErrorOccurred( error.toString() );
//                    fnNotifyTaskCompleted( new JSONArray( error.toString() ));
                } catch ( Exception e ) {
                    Utils.fnPrintLog( LOG_TAG, "Exception: " + Log.getStackTraceString( e ));
                }
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue( mContext );
        request.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add( request );
    }

    private void fnExecuteTaskJsonObject() {
        JsonObjectRequest request = new JsonObjectRequest( method, URL, mJsonObjectData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse( JSONObject response) {
                        Utils.fnPrintLog( LOG_TAG, "Response fetched in fnExecuteTask: " +response.toString() );
                        fnNotifyTaskCompletedJSONObject( response );
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error ) {
                Utils.fnPrintLog( LOG_TAG, "Error: " +error.getMessage() );
                Utils.fnPrintLog( LOG_TAG, "Error fetched in fnExecuteTask: " +error.toString() );
                Utils.fnPrintLog( LOG_TAG, "Error fetched in fnExecuteTask: " + Arrays.toString( error.getStackTrace() ));

                String message = "";
                if ( error instanceof NetworkError) {
                    message = NO_INTERNET_CONNECTION;
                } else if ( error instanceof ServerError) {
                    message = ERROR_SERVER_NOT_FOUND;
                } else if ( error instanceof AuthFailureError) {
                    message = NO_INTERNET_CONNECTION;
                } else if ( error instanceof ParseError) {
                    message = ERROR_DATA_PARSE;
                } /*else if ( error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                }*/ else if ( error instanceof TimeoutError) {
                    message = ERROR_CONNECTION_TIME_OUT;
                }

                try {
                    fnErrorOccurred( message );
                } catch ( Exception e ) {
                    Utils.fnPrintLog( LOG_TAG, "Exception: " + Log.getStackTraceString( e ));
                }
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue( mContext );
        request.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add( request );
    }

    private void fnNotifyTaskCompletedJSONObject( JSONObject responseFetched ) {
        postTaskListener.fnPostTaskCompletedJsonObject( responseFetched );
    }

    private void fnNotifyTaskCompleted( JSONArray responseFetched ) {
        postTaskListener.fnPostTaskCompleted( responseFetched );
    }

    private void fnErrorOccurred( String error ) {
        postTaskListener.fnErrorOccurred( error );
    }
}