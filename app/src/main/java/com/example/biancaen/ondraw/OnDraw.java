package com.example.biancaen.ondraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;



public class OnDraw extends View {

    private double a;
    private double b;
    private double c;

    //經過比例放大的b邊長
    private double big_B;

    private double ab_xPoint;
    private double ab_yPoint;
    private double bc_xPoint;
    private double bc_yPoint;
    private double ac_xPoint;
    private double ac_yPoint;

    private double angleDeg_ab;
    private double angleDeg_ac;
    private double angleDeg_bc;

    private boolean show;

    public void setParameter(double ab_xPoint , double ab_yPoint ,
                             double ac_xPoint , double ac_yPoint ,
                             double bc_xPoint , double bc_yPoint ,
                             boolean show)
    {
        // x = cos角度 *r   y  = sin角度*r  ab bc ac 為參考邊長的角度
        this.ab_xPoint = ab_xPoint;
        this.ab_yPoint = ab_yPoint;
        this.bc_xPoint = bc_xPoint;
        this.bc_yPoint = bc_yPoint;
        this.ac_xPoint = ac_xPoint;
        this.ac_yPoint = ac_yPoint;

        this.show = show;
    }

    public void setLine(double a , double b , double c , int proportion)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        big_B = proportion * b;
    }

    public void setAngle(double angleDeg_ab , double angleDeg_ac , double angleDeg_bc){

        this.angleDeg_ab = angleDeg_ab;
        this.angleDeg_ac = angleDeg_ac;
        this.angleDeg_bc = angleDeg_bc;
    }


    Paint paint = new Paint();
    Paint paintText = new Paint();
    RectF rectF = new RectF();


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
        float b_Point_Start_X = viewCenterWidth - ((float) big_B / 2);
        float b_Point_Start_Y = viewCenterHeight;

        float b_Point_End_X = b_Point_Start_X + (float) big_B;
        float b_Point_End_Y = b_Point_Start_Y;

        //----依B邊長為基準點，A邊長起始點跟B邊長一樣，再利用角度求出A邊長的末端座標
        float a_Point_Start_X = b_Point_Start_X;
        float a_Point_Start_Y = b_Point_Start_Y;

        float a_Point_End_X = b_Point_Start_X + (float) ab_xPoint;
        float a_Point_End_Y = b_Point_Start_Y + (float) ab_yPoint;

        //-----依A邊長為基準點，C邊長起始點為A邊長末端座標
        float ac_Point_Start_X = a_Point_End_X;
        float ac_Point_Start_Y = a_Point_End_Y;

        float ac_Point_End_X = a_Point_End_X + (float) ac_xPoint;
        float ac_Point_End_Y = a_Point_End_Y + (float) ac_yPoint;

        //-----
        float bc_Point_Start_X = b_Point_End_X;
        float bc_Point_Start_Y = b_Point_End_Y;

        float bc_Point_End_X = b_Point_End_X + (float) bc_xPoint;
        float bc_Point_End_Y = b_Point_End_Y + (float) bc_yPoint;

        //-----各個文字的至中點
        float b_TextCenterX = viewCenterWidth - (textTotalSize / 2);
        float b_TextCenterY = b_Point_Start_Y + textSize * 2;

        float a_TextCenterX = b_Point_Start_X + ((float) ab_xPoint / 2) - textTotalSize - textSize * 2;
        float a_TextCenterY = b_Point_Start_Y + ((float) ab_yPoint / 2);

        float c_TextCenterX = a_Point_End_X + ((float) ac_xPoint / 2) + textSize;
        float c_TextCenterY = a_Point_End_Y + ((float) ac_yPoint / 2);


        //底部線 b
        canvas.drawLine(b_Point_Start_X , b_Point_Start_Y , b_Point_End_X , b_Point_End_Y  , paint);

        //左邊線 a
        canvas.drawLine(a_Point_Start_X , a_Point_Start_Y , a_Point_End_X , a_Point_End_Y ,paint);

        //右邊線 c
        canvas.drawLine(ac_Point_Start_X , ac_Point_Start_Y, ac_Point_End_X ,ac_Point_End_Y ,paint);
        canvas.drawLine(bc_Point_Start_X , bc_Point_Start_Y, bc_Point_End_X ,bc_Point_End_Y ,paint);

        //角度扇形顯示
        paintText.setStyle(Paint.Style.STROKE);
        paintText.setStrokeWidth(8);

        rectF.set(b_Point_Start_X-100 , b_Point_Start_Y -100 , b_Point_Start_X+100 ,b_Point_Start_Y+100);
        canvas.drawArc(rectF , 0 , -(float)angleDeg_ab  ,false ,paintText);

        rectF.set(a_Point_End_X-90 , a_Point_End_Y-90 , a_Point_End_X+90 ,a_Point_End_Y+90);
        canvas.drawArc(rectF , 180 - ((float)angleDeg_ac + (float)angleDeg_ab) , (float)angleDeg_ac ,false ,paintText);

        rectF.set(b_Point_End_X-90 , b_Point_End_Y-90 , b_Point_End_X+90 ,b_Point_End_Y+90);
        canvas.drawArc(rectF , 180 , (float)angleDeg_bc , false , paintText );

        //邊長 角度 字體顯示
        if (show) {
            canvas.drawText(b + "公分", b_TextCenterX, b_TextCenterY, paint);
            canvas.drawText(a + "公分", a_TextCenterX, a_TextCenterY, paint);
            canvas.drawText(c + "公分", c_TextCenterX, c_TextCenterY, paint);

            canvas.drawText(angleDeg_ab + "度", b_Point_Start_X - 3*textSize/2 , b_Point_Start_Y + 3*textSize/2 , paint);
            canvas.drawText(angleDeg_ac + "度", a_Point_End_X - textSize , a_Point_End_Y - textSize/2 , paint);
            canvas.drawText(angleDeg_bc + "度", b_Point_End_X - 3*textSize/2 , b_Point_End_Y + + 3*textSize/2 , paint);
        }else{
            canvas.drawText("邊長B", b_TextCenterX, b_TextCenterY, paint);
            canvas.drawText("邊長A", a_TextCenterX, a_TextCenterY, paint);
            canvas.drawText("邊長C", c_TextCenterX, c_TextCenterY, paint);

            canvas.drawText("角度AB", b_Point_Start_X - 3*textSize/2 , b_Point_Start_Y + 3*textSize/2 , paint);
            canvas.drawText("角度AC", a_Point_End_X - 3*textSize/2 , a_Point_End_Y - textSize/2 , paint);
            canvas.drawText("角度BC", b_Point_End_X - 3*textSize/2 , b_Point_End_Y + + 3*textSize/2 , paint);
        }


    }



}
