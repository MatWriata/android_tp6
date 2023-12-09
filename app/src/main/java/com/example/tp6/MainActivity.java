package com.example.tp6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {
    MyView myView;

    MaterialToolbar materialToolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = new MyView(this);
        //setContentView(myView);

        materialToolbar = findViewById(R.id.material_toolbar);
        setSupportActionBar(materialToolbar);

        LinearLayout layout = (LinearLayout) findViewById(R.id.ll);
        layout.addView(myView);
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