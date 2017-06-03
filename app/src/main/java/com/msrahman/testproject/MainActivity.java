package com.msrahman.testproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Context con;
    ViewPager pager;
    LinearLayout btnGoform;
    private Handler handler;
    private boolean isTimerRunning;
    Runnable Update;
    Timer swipeTimer;
    Activity activity;
    public static int[] imageRSC = {R.mipmap.one,R.mipmap.two,R.mipmap.three};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con = this;

        initUi();
    }

    private void initUi() {

//        handler = new Handler();
//        createSwipeTimer();
//        isTimerRunning = true;
        activity =this;
        pager = (ViewPager)findViewById(R.id.pager);
        btnGoform = (LinearLayout) findViewById(R.id.btnGoform);
        btnGoform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        pager.setAdapter(new Adapter(con, imageRSC, activity));
        pager.setCurrentItem(0);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if(position==2){
                    btnGoform.setVisibility(View.VISIBLE);
                }else {
                    btnGoform.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
//                    isTimerRunning = false;
//                    swipeTimer.cancel();
//                } else {
//                    if (!isTimerRunning) {
//                        createSwipeTimer();
//                        isTimerRunning = true;
//                    }
//                }
            }
        });


//        Update = new Runnable() {
//            @Override
//            public void run() {
//
//                if (imageRSC != null && imageRSC.length > 0) {
//                    int currentImg = pager.getCurrentItem();
//                    currentImg++;
//                    if (currentImg == imageRSC.length) {
//                        currentImg = 0;
//                    }
//                    pager.setCurrentItem(currentImg, true);
//                }
//
//				/*
//				 * if (currentPage == AllMenuImgInfo.getAllMenuImgInfo().size())
//				 * { currentPage = 0; }
//				 * MainViewPager.setCurrentItem(currentPage++, true);
//				 */
//            }
//        };
    }
    public void buttonClickFunction(View v)
    {
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        startActivity(intent);


    }



}
