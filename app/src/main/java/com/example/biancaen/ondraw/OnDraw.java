package com.example.biancaen.ondraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;



public class OnDraw extends View {

    private double a;
    private double b;
    private double c;

    //經過比例放大的b邊長
    private double big_B;

    private double a_xPoint;
    private double a_yPoint;
    private double bc_xPoint;
    private double bc_yPoint;
    private double ac_xPoint;
    private double ac_yPoint;


    public void setParameter(double a_xPoint , double a_yPoint ,
                             double ac_xPoint , double ac_yPoint,
                             double bc_xPoint , double bc_yPoint )
    {
        this.a_xPoint = a_xPoint;
        this.a_yPoint = a_yPoint;
        this.bc_xPoint = bc_xPoint;
        this.bc_yPoint = bc_yPoint;
        this.ac_xPoint = ac_xPoint;
        this.ac_yPoint = ac_yPoint;

        Log.v("ppking" , " ac_xPoint : " + ac_xPoint);
    }

    public void setLine(double a , double b , double c , int proportion)
    {
        this.a = a;
        this.b = b;
        this.c = c;

        big_B = proportion * b;
        Log.v("ppking" , " a : " +a );
    }


    Paint paint = new Paint();

    public OnDraw(Context context , AttributeSet attributeSet){
        super(context , attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);

        //TODO 各解析度字體大小
        float textCount = 3;
        float textSize = 50;
        float textTotalSize = textCount * textSize;
        paint.setTextSize(textSize);


        float viewCenterWidth = getWidth()/2;
        float viewCenterHeight = getHeight()/2;

        //-----B邊長為第一步，viewCenterWidth - ((float)big_B/2 是為了讓b邊長的一半都維持在view的中間
        float b_Point_Start_X = viewCenterWidth - ((float)big_B/2);
        float b_Point_Start_Y = viewCenterHeight;

        float b_Point_End_X = b_Point_Start_X+(float)big_B;
        float b_Point_End_Y = b_Point_Start_Y;

        //----依B邊長為基準點，A邊長起始點跟B邊長一樣，再利用角度求出A邊長的末端座標
        float a_Point_Start_X = b_Point_Start_X;
        float a_Point_Start_Y = b_Point_Start_Y;

        float a_Point_End_X = b_Point_Start_X+(float)a_xPoint;
        float a_Point_End_Y = b_Point_Start_Y+(float)a_yPoint;

        //-----依A邊長為基準點，C邊長起始點為A邊長末端座標
        float ac_Point_Start_X = a_Point_End_X;
        float ac_Point_Start_Y = a_Point_End_Y;

        float ac_Point_End_X = a_Point_End_X+(float)ac_xPoint;
        float ac_Point_End_Y = a_Point_End_Y+(float)ac_yPoint;

        //-----
        float bc_Point_Start_X = b_Point_End_X;
        float bc_Point_Start_Y = b_Point_End_Y;

        float bc_Point_End_X = b_Point_End_X + (float)bc_xPoint;
        float bc_Point_End_Y = b_Point_End_Y + (float)bc_yPoint;

        //-----各個文字的至中點
        float b_TextCenterX = viewCenterWidth-(textTotalSize/2);
        float b_TextCenterY = b_Point_Start_Y + textSize;

        float a_TextCenterX = b_Point_Start_X+((float)a_xPoint/2)-textTotalSize-textSize;
        float a_TextCenterY = b_Point_Start_Y+((float)a_yPoint/2);

        float c_TextCenterX = a_Point_End_X+((float)ac_xPoint/2)+(textSize/2);
        float c_TextCenterY = a_Point_End_Y+((float)ac_yPoint/2);

        //底部線 b
        canvas.drawLine(b_Point_Start_X , b_Point_Start_Y , b_Point_End_X , b_Point_End_Y  , paint);
        canvas.drawText(b+"公分", b_TextCenterX , b_TextCenterY , paint);

        //左邊線 a
        canvas.drawLine(a_Point_Start_X , a_Point_Start_Y , a_Point_End_X , a_Point_End_Y ,paint);
        canvas.drawText(a+"公分", a_TextCenterX , a_TextCenterY , paint);

        //右邊線 c
        canvas.drawLine(ac_Point_Start_X , ac_Point_Start_Y, ac_Point_End_X ,ac_Point_End_Y ,paint);
        canvas.drawText(c+"公分", c_TextCenterX , c_TextCenterY , paint);

        canvas.drawLine(bc_Point_Start_X , bc_Point_Start_Y, bc_Point_End_X ,bc_Point_End_Y ,paint);
    }

}
