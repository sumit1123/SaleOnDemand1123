package com.example.grocery.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocery.Adapter.VendorAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.R;
import com.example.grocery.utils.SmsListener;
import com.example.grocery.utils.SmsReceiver;
import com.example.grocery.utils.ToolbarSettings;

public class ReferFriendActivity extends AppCompatActivity {
    TextView name,number;
    public  static final int RequestPermissionCode  = 1 ;
    EditText contact_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_friend);
        final Button contacts = findViewById(R.id.contact_list);
              contact_number = findViewById(R.id.contact_number);
              name = findViewById(R.id.contact_name);
             // number = findViewById(R.id.contact_num);
              EnableRuntimePermission();

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 1);

                SmsReceiver.bindListener(new SmsListener() {
                    @Override
                    public void messageReceived(String sender, String messageText) {

                    }
                });

            }
        });





        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        // toolbarTitle.setText("  " + Label.userLabel.getVendor());
        String Refer = getString(R.string.referyourfrnd);
        toolbarTitle.setText(Refer);
        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        getdata();
    }

    private void EnableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(ReferFriendActivity.this, Manifest.permission.READ_CONTACTS)) {

            Toast.makeText(ReferFriendActivity.this, "CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(ReferFriendActivity.this, new String[]{
                    Manifest.permission.READ_CONTACTS},RequestPermissionCode);
        }
    }

  /*  public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:
                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(ReferFriendActivity.this, "Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(ReferFriendActivity.this, "Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }*/




    @Override
    protected void onActivityResult(int RequestCode, int ResultCode, Intent ResultIntent) {
        super.onActivityResult(RequestCode, ResultCode, ResultIntent);

        switch (RequestCode) {

            case (1):
                if (ResultCode == Activity.RESULT_OK) {

                    Uri uri;
                    Cursor cursor1, cursor2;
                    String TempNameHolder, TempNumberHolder, TempContactID, IDresult = "" ;
                    int IDresultHolder ;

                    uri = ResultIntent.getData();

                    cursor1 = getContentResolver().query(uri, null, null, null, null);

                    if (cursor1.moveToFirst()) {

                        TempNameHolder = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                        TempContactID = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts._ID));

                        IDresult = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        IDresultHolder = Integer.valueOf(IDresult) ;

                        if (IDresultHolder == 1) {

                            cursor2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + TempContactID, null, null);

                            while (cursor2.moveToNext()) {

                                TempNumberHolder = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                                name.setText(TempNameHolder);
                              //  number.setText(TempNumberHolder);
                                contact_number.setText(TempNumberHolder);

                            }
                        }

                    }
                }
                break;
        }
    }


    private void getdata() {
        String appname = Appearance.appTranslation.getApp_name();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs.getString("user_id", "");
        String package_name = getPackageName();

          String msg = getString(R.string.msg);
        String msg_2 = getString(R.string.msg_2);
        String msg_3 = getString(R.string.msg_3);
    }
}

