package com.msrahman.testproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.msrahman.testproject.utils.AlertMessage;
import com.msrahman.testproject.utils.AllURL;
import com.msrahman.testproject.utils.Appconstant;
import com.msrahman.testproject.utils.BusyDialog;
import com.msrahman.testproject.utils.NetInfo;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Shahin on 6/2/2017.
 */

public class FormActivity extends AppCompatActivity {

    Context con;
    private EditText etName,etAge,etLat,etLon;
    private Button btnSave,btnMap,btnHtml;
    private String name,age,lat,lon;
    private DatabaseHandler db;
    private List<Contact> contacts  = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        con = this;

        initUi();
    }

    private void initUi() {
        db = new DatabaseHandler(this);
        etName = (EditText)findViewById(R.id.etName);
        etAge = (EditText)findViewById(R.id.etAge);
        etLat = (EditText)findViewById(R.id.etLat);
        etLon = (EditText)findViewById(R.id.etLon);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnMap = (Button)findViewById(R.id.btnMap);
        btnHtml = (Button)findViewById(R.id.btnHtml);

        btnHtml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(con,WebActivity.class);
                startActivity(i);
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(con,MapsActivity.class);
                startActivity(i);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etName.getText().toString())){
                    Toast.makeText(con,"Enter Name",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(etAge.getText().toString())){
                    Toast.makeText(con,"Enter Age",Toast.LENGTH_SHORT).show();

                }else if(TextUtils.isEmpty(etLat.getText().toString())){
                    Toast.makeText(con,"Enter Latitude",Toast.LENGTH_SHORT).show();

                }else if(TextUtils.isEmpty(etLon.getText().toString())){
                    Toast.makeText(con,"Enter Longititude",Toast.LENGTH_SHORT).show();
                }else {

                    name= etName.getText().toString();
                    age = etAge.getText().toString();
                    lat = etLat.getText().toString();
                    lon = etLon.getText().toString();

                    insertContact();
                    sendDataServer(AllURL.sentDta());
                }
            }
        });

    }


    private void insertContact() {

        db.addContact(new Contact(name,age,lat,lon));
        contacts = db.getAllContacts();
        Appconstant.contacts = contacts;
        Intent i = new Intent(con,InfoListActivity.class);
        startActivity(i);
        for (Contact cn : contacts) {
            String log = "Age: "+cn.getAge()+" ,Name: " + cn.getName() + " ,lat: " + cn.getLat() + " ,lon: " + cn.getLon();
            Log.e("logdata: ", log);

        }

    }

    //Send data web server
    protected void sendDataServer(final String url) {

        if (!NetInfo.isOnline(con)) {
            AlertMessage.showMessage(con, "Alert",
                    "No Internet");
            return;
        }

        final BusyDialog busyNow = new BusyDialog(con, true, false);
        busyNow.show();

        final AsyncHttpClient client = new AsyncHttpClient();

        final RequestParams param = new RequestParams();

        try {

            param.put("name", name);
            param.put("age", age);
            param.put("lat", lat);
            param.put("lon", lon);

        } catch (final Exception e1) {
            e1.printStackTrace();
        }

        client.post(url, param, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (busyNow != null) {
                    busyNow.dismis();
                }
                Gson g = new Gson();
               Contact dataResponse = g.fromJson(new String(responseBody), Contact.class);

                if(dataResponse!=null) {


                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (busyNow != null) {
                    busyNow.dismis();
                }
            }
        } );

    }

}
