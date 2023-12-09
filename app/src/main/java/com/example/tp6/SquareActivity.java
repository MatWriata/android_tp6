package com.example.tp6;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class SquareActivity extends AppCompatActivity {

    GLSurfaceView view;


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float xPrec = 0;
        float yPrec = 0;


        switch (event.getAction()) {
             case MotionEvent.ACTION_DOWN:
                 Log.i("ACTION","DOWN");
                 xPrec = event.getX();
                 yPrec = event.getY();
                 break;
            case MotionEvent.ACTION_UP:
                Log.i("ACTION","UP");
                break;
           case MotionEvent.ACTION_MOVE:
               Log.i("ACTION","MOVE");
               final float dx = event.getX() - xPrec;
               final float dy = event.getY() - yPrec;
               SquareRenderer.angleCubeX = dy*0.25f;
               SquareRenderer.angleCubeY = dx*0.25f;
               break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        view = new GLSurfaceView(this);
        view.setRenderer(new SquareRenderer());

        LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
        layout.addView(view);

        MaterialToolbar materialToolbar = findViewById(R.id.material_toolbar);
        setSupportActionBar(materialToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cube, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.go_to_balls) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

}