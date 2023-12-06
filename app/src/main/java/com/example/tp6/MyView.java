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

import java.util.ArrayList;
import java.util.Random;

public class MyView extends SurfaceView implements Runnable, View.OnTouchListener, SensorEventListener {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Thread thread = null;
    SurfaceHolder sh;

    SensorManager sensorManager;
    Sensor sensor;
    boolean paused = true;

    ArrayList<Balls> ballList = new ArrayList<Balls>();

    Random weightGenerator = new Random();

    float ax;
    float ay;
    float az;


/*    public MyView(Context context, AttributeSet attrSet) {
        super(context, attrSet);
        sh = getHolder();
        setOnTouchListener(this);
    }*/


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
        float i = 1f;


        while(!paused) {
            if (!sh.getSurface().isValid()) continue;
            Canvas c = sh.lockCanvas();
            paint.setAntiAlias(true);
            paint.setColor(Color.WHITE);
            c.drawPaint(paint);
            paint.setColor(Color.BLUE);

            if(ax > 0 && ax <1 && ay > 0 && ay < 1){
//                 cy = cy*i;
  //               cx = cx*i;

                //Log.e("i", String.valueOf(i));
            }



            for (Balls elem:ballList){
                c.drawCircle(elem.cx, elem.cy, elem.radius, paint);
            }

            if (!ballList.isEmpty()){
                c.drawCircle(ballList.get(0).cx, ballList.get(0).cy, ballList.get(0).radius, paint);
            }
            //c.drawCircle(ballList.get(1).cx, ballList.get(1).cy, ballList.get(1).radius, paint);
            sh.unlockCanvasAndPost(c);

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        Canvas c = sh.lockCanvas();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ballList.add(new Balls(1f +weightGenerator.nextFloat()* (1f - 2f),20f+ weightGenerator.nextFloat()* (40f - 100f),x,y));

                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }

        sh.unlockCanvasAndPost(c);
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ax = event.values[0];
        ay = event.values[1];
        az = event.values[2];

        Log.i("Info acceleration", "x:" + ax + ", y:" + ay + ", z:" + az);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do nothing
    }
}
