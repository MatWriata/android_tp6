package com.example.tp6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        myView = new MyView(this);
        setContentView(myView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myView.pause();
    }
}