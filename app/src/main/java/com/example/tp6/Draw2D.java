package com.example.tp6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;

public class Draw2D extends View {
    Paint myPaint;

    public Draw2D(Context context) {
        super(context);
        myPaint = new Paint();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        myPaint.setStyle(Paint.Style.FILL);
        myPaint.setAntiAlias(true);

        myPaint.setColor(Color.BLUE);
        canvas.drawPaint(myPaint);

        myPaint.setColor(Color.RED);
        canvas.drawCircle(canvas.getWidth()/2f, canvas.getHeight()/2f, 200f, myPaint);

        myPaint.setColor(Color.BLACK);
        canvas.drawCircle(canvas.getWidth()/2f, canvas.getHeight()/2f, 20f, myPaint);
    }
}
