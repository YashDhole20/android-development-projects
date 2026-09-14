package com.example.bustrackingrfid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    View startView;
    View endView;
    int sx,sy,ex,ey;

    public DrawView(Context context,View startView,View endView,int sx,int sy,int ex,int ey) {
        super(context);
        paint.setColor(Color.BLACK);
        this.startView = startView;
        this.endView = endView;
        this.sx=sx;
        this.sy=sy;
        this.ex=ex;
        this.ey=ey;
    }

    @SuppressLint("NewApi")
    public void onDraw(Canvas canvas) {
        canvas.drawLine(startView.getX()+sx, startView.getY()+sy, endView.getX()+ex, endView.getY()+ey, paint);
    }

}
