package com.example.tp6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Random;

public class MyView extends SurfaceView implements Runnable, View.OnTouchListener, SensorEventListener {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Thread thread = null;
    SurfaceHolder sh;
    SensorManager sensorManager;
    Sensor sensor;
    boolean paused = true;

    ArrayList<Balls> ballList = new ArrayList<>();

    Random randomGenerator = new Random();

    float ax;
    float ay;
    float az;
    boolean collide = true;

    public boolean collideSwitch(){
        this.collide = !collide;
        return collide;
    }

    public MyView(Context context) {
        super(context, null);
        sh = getHolder();
        setOnTouchListener(this);
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void resume() { // to call in Activity.onResume
        paused = false;
        thread = new Thread(this);
        thread.start();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void pause() { // to call in Activity.onPause
        paused = true;
        while(true) {
            try { thread.join(); break; }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
        thread = null;
        sensorManager.unregisterListener(this);

    }



    public void run() {

        while (!paused) {

            if (!sh.getSurface().isValid()) continue;
            Canvas c = sh.lockCanvas();
            paint.setAntiAlias(true);
            paint.setColor(Color.WHITE);
           c.drawPaint(paint);

            for (Balls elem:ballList) {
                paint.setColor(Color.rgb(elem.getColor()&0xFFFFFF00,(elem.getColor()&0xFFFF00FF)>>8,(elem.getColor()&0xFF00FFFF)>>16));
                c.drawCircle(elem.getCx(), elem.getCy(), elem.getRadius(), paint);


                elem.setCx(elem.getCx() - (ax * (0.025f * elem.getRadius())));
                elem.setCy(elem.getCy() + (ay * (0.025f * elem.getRadius())));

                if (collide){
                    for (int j = 0; j < ballList.size(); j++) {
                        Balls otherBall = ballList.get(j);

                        if (elem.collidesWith(otherBall)) {
                            float overlap = (elem.getRadius() + otherBall.getRadius()) - (float) Math.sqrt(Math.pow(elem.getCx() - otherBall.getCx(), 2) + Math.pow(elem.getCy() - otherBall.getCy(), 2));
                            float angle = (float) Math.atan2(otherBall.getCy() - elem.getCy(), otherBall.getCx() - elem.getCx());

                            float offsetX = overlap * (float) Math.cos(angle);
                            float offsetY = overlap * (float) Math.sin(angle);

                            elem.setCx(elem.getCx() - offsetX / 2);
                            elem.setCy(elem.getCy() - offsetY / 2);

                            otherBall.setCx(otherBall.getCx() + offsetX / 2);
                            otherBall.setCy(otherBall.getCy() + offsetY / 2);
                        }
                    }
                }

                float x = elem.getCx();
                float y = elem.getCy();
                if (x <= 0 + elem.getRadius() || x >= c.getWidth() - elem.getRadius()) {
                    elem.setCx(x <= 0 + elem.getRadius() ? 0 + elem.getRadius() : c.getWidth() - elem.getRadius());
                }

                if (y <= 0 + elem.getRadius() || y >= c.getHeight() - elem.getRadius()) {
                    elem.setCy(y <= 0 + elem.getRadius() ? 0 + elem.getRadius() : c.getHeight() - elem.getRadius());
                }

            }
            sh.unlockCanvasAndPost(c);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float weight = 20f+ randomGenerator.nextFloat()* (100f - 20f);
        int color = randomGenerator.nextInt();

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                ballList.add(new Balls(weight/10f,weight,x,y,color));
        }
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ax = event.values[0];
        ay = event.values[1];
        az = event.values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do nothing
    }
}
