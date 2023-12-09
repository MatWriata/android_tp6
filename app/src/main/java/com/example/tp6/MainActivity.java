package com.example.tp6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

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

        LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
        layout.addView(myView);
        Toast.makeText(this, "Touch the toolbar to switch on/off collisions", Toast.LENGTH_LONG).show();
        materialToolbar.setOnClickListener(v -> {
            if (myView.collideSwitch()){
                Toast.makeText(this, "On", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Off", Toast.LENGTH_SHORT).show();
            }
        });
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.go_to_cube) {
            Intent intent = new Intent(getApplicationContext(), SquareActivity.class);
            startActivity(intent);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}