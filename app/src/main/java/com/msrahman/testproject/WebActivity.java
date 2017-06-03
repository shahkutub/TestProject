package com.msrahman.testproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shahin on 6/3/2017.
 */

public class WebActivity extends AppCompatActivity{

    DatabaseHandler db;
    private List<Contact> contacts  = new ArrayList<>();
    private String data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);

        db = new DatabaseHandler(this);
        contacts = db.getAllContacts();
        WebView web = (WebView)findViewById(R.id.web);

        for (int i = 0; i < contacts.size(); i++) {

            data += "\n"+contacts.get(i).getName()+","+contacts.get(i).getAge()+","+contacts.get(i).getLat()+","+contacts.get(i).getLon();
            web.loadData(data, "text/html", "UTF-8");
        }




    }
}
