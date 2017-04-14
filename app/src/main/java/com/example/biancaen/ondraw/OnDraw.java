package com.example.biancaen.ondraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class OnDraw extends View {

    //initial data
    double a = 1 *250;
    double b = 1.414 *250;
    double c = 1 *250;

    double angleDeg_ab = 45;
    double angleDeg_bc = 0;
    double angleDeg_ca = 180- (90 +angleDeg_ab);


    Paint paint = new Paint();
    DegRadCount degRadCount =new DegRadCount(angleDeg_ab , angleDeg_ca , a , c);


    public OnDraw(Context context , AttributeSet attributeSet){
        super(context , attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);

        float viewCenterWidth = getWidth()/2;
        float viewCenterHeight = getHeight()/2;

        float drawCenterX = viewCenterWidth/2;
        float drawCenterY = viewCenterHeight;

        double a_xPoint = degRadCount.a_xPoint();
        double a_yPoint = degRadCount.a_yPoint();

        double c_xPoint = degRadCount.c_xPoint();
        double c_yPoint = degRadCount.c_yPoint();


        //底部線 b
        canvas.drawLine(drawCenterX , drawCenterY , drawCenterX+(float)b , drawCenterY  , paint);
        //左邊線 a
        canvas.drawLine(drawCenterX , drawCenterY , drawCenterX+(float)a_xPoint , drawCenterY+(float)a_yPoint ,paint);
        //右邊線 c
        canvas.drawLine(drawCenterX+(float)a_xPoint , drawCenterY+(float)a_yPoint, drawCenterX+(float)a_xPoint+(float)c_xPoint ,drawCenterY+(float)a_yPoint+(float)c_yPoint ,paint);

    }



   private class DegRadCount {
       private double angleDeg_ab;
       private double angleDeg_ca;
       private double a;
       private double c;
       private DegRadCount(double angleDeg_ab ,double angleDeg_ca , double a , double c){
           this.angleDeg_ab = angleDeg_ab;
           this.angleDeg_ca = angleDeg_ca;
           this.a = a;
           this.c = c;
       }

       //依公式 x = cos(弳度)*r半徑  y = sin(弳度)*r半徑

       private double a_xPoint(){
           double angleRad = (angleDeg_ab/180)*Math.PI;
           return Math.cos(angleRad)*a;
       }

       private double a_yPoint(){
           double angleRad = (angleDeg_ab/180)*Math.PI;
           return -Math.sin(angleRad)*a;
       }

       private double c_xPoint(){
           Log.v("ppking " , " angleDeg_ca : " +angleDeg_ca);
           double angleRad = (angleDeg_ca/180)*Math.PI;
           return Math.cos(angleRad)*c;
       }

       private double c_yPoint(){
           double angleRad = (angleDeg_ca/180)*Math.PI;
           return Math.sin(angleRad)*c;
       }

   }
}
