package com.example.grocery.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.example.grocery.Activities.ProductDetails;
import com.example.grocery.R;
import com.example.grocery.utils.Config;
import com.example.grocery.utils.NotificationUtils;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

//    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
private static final String TAG = "MessagingService";


    private NotificationUtils notificationUtils;
    private SharedPreferences labelsShared;
    private String class_name;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            return;

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            JSONObject json = null;
            try {
                json = new JSONObject(remoteMessage.getData().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            handleDataMessage(json);
        }

         //Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());

            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                handleDataMessage(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }

//        try {
//            String title = remoteMessage.getData().get( "title" );
//            String message = remoteMessage.getData().get( "body" );
//
//            Log.d( LOG_TAG,"onMessageReceived: Message Received: \n" +
//                    "Title: " +title+ "\n" +
//                    "Message: "+ message );
//
//            // Code to create notification
//        } catch ( Exception e ) {
//            Utils.fnPrintLog( LOG_TAG, "Exception onMessageReceived: " + Log.getStackTraceString( e ));
//        }

    }

    private void handleNotification(String message) {
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            // play notification sound
            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();
        } else {
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            // play notification sound
            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();        }
    }

    private void handleDataMessage(JSONObject json) {
        Log.e(TAG, "push json: " + json.toString());

        try {
            JSONObject data = json.getJSONObject("data");

            String title = data.getString("title");
            int mNotificationId = data.getInt("id");

            String message = data.getString("message");
            boolean isBackground = data.getBoolean("is_background");
            String imageUrl = data.getString("image");
            String timestamp = data.getString("timestamp");
            if (data.getJSONObject("notification").get("product").equals(null)) {
                class_name = "Categories";
            } else {
                class_name = "ProductDetails";
            }
            JSONObject payload = data.getJSONObject("payload");

            Log.e(TAG, "title: " + title);
            Log.e(TAG, "message: " + message);
            Log.e(TAG, "isBackground: " + isBackground);
            Log.e(TAG, "payload: " + payload.toString());
            Log.e(TAG, "imageUrl: " + imageUrl);
            Log.e(TAG, "timestamp: " + timestamp);

            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
                System.out.println("dxoszds" + class_name);

                Intent resultIntent = new Intent(getApplicationContext(), Class.forName(getApplicationContext().getPackageName()+".Activities." + class_name));
                if (class_name.matches("ProductDetails")) {
                    int product_id = data.getJSONObject("notification").getJSONObject("product").
                            getJSONObject("default_product_translation").getInt("product_id");
                    ProductDetails.productid = product_id;
                    ProductDetails.nameofProduct = data.getJSONObject("notification").getJSONObject("product").
                            getJSONObject("default_product_translation").getString("product_id");
                } else if (class_name.matches("Categories")) {
                    resultIntent.putExtra("category_id", 0);
                    try {
                        labelsShared = getSharedPreferences("labels", MODE_PRIVATE);

                        resultIntent.putExtra("category_name", (new JSONObject(labelsShared.getString("labels", null)).getString("all_categories")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                int requestCode = 0;
                PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, resultIntent, PendingIntent.FLAG_ONE_SHOT);
                Uri sound =
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.logo)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                R.drawable.logo))

                        .setContentTitle(getResources().getString(R.string.app_name))
                        .setContentText(title)
                        .setAutoCancel(true)
                        .setSound(sound)
                        .setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(mNotificationId, noBuilder.build());
               /* if (TextUtils.isEmpty(imageUrl)) {
                    showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
                } else {
                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
                }*/
            } else {
                System.out.println("dxoszds" + class_name);
                Intent resultIntent = new Intent(getApplicationContext(), Class.forName(getApplicationContext().getPackageName()+".Activities." + class_name));
                if (class_name.matches("ProductDetails")) {
                    int product_id = data.getJSONObject("notification").getJSONObject("product").
                            getJSONObject("default_product_translation").getInt("product_id");
                    ProductDetails.productid = product_id;
                    ProductDetails.nameofProduct = data.getJSONObject("notification").getJSONObject("product").
                            getJSONObject("default_product_translation").getString("product_id");
                } else if (class_name.matches("Categories")) {
                    resultIntent.putExtra("category_id", 0);
                    try {
                        labelsShared = getSharedPreferences("labels", MODE_PRIVATE);

                        resultIntent.putExtra("category_name", (new JSONObject(labelsShared.getString("labels", null)).getString("all_categories")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                int requestCode = 0;
                PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, resultIntent, PendingIntent.FLAG_ONE_SHOT);
                Uri sound =
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.logo)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                R.drawable.logo))

                        .setContentTitle(getResources().getString(R.string.app_name))
                        .setContentText(title)
                        .setAutoCancel(true)
                        .setSound(sound)
                        .setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(mNotificationId, noBuilder.build());
               /* if (TextUtils.isEmpty(imageUrl)) {
                    showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
                } else {
                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
                }*/
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }


}