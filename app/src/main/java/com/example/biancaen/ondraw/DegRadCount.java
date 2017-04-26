package com.example.biancaen.ondraw;

import android.util.Log;
import android.widget.Toast;

class DegRadCount {
    private float b_Point_Start_X;
    private float b_Point_Start_Y;
    private float b_Point_End_X;
    private float b_Point_End_Y;

    private float a_Point_Start_X;
    private float a_Point_Start_Y;
    private float a_Point_End_X;
    private float a_Point_End_Y;

    private float bc_Point_Start_X;
    private float bc_Point_Start_Y;
    private float bc_Point_End_X;
    private float bc_Point_End_Y;

    private float ac_Point_Start_X;
    private float ac_Point_Start_Y;
    private float ac_Point_End_X;
    private float ac_Point_End_Y;

    private float a_TextCenterX;
    private float a_TextCenterY;
    private float b_TextCenterX;
    private float b_TextCenterY;
    private float c_TextCenterX;
    private float c_TextCenterY;

    private int textSize;
    private float viewCenterWidth;
    private float viewCenterHeight;

    private double angleDeg_ab;
    private double angleDeg_ac;
    private double angleDeg_bc;

    private double big_a;
    private double big_b;
    private double big_c;

    private int count;


    DegRadCount(MainActivity mainActivity , int proportion , boolean show){

        //各個EditText get出來轉成字串，再去判斷是否為空格，若不是再轉成double
        String lineA_Data = mainActivity.lineA.getText().toString();
        double a = prove(lineA_Data);

        String lineB_Data = mainActivity.lineB.getText().toString();
        double b = prove(lineB_Data);

        String lineC_Data = mainActivity.lineC.getText().toString();
        double c = prove(lineC_Data);

        String angleAB_Data = mainActivity.angleAB.getText().toString();
        angleDeg_ab = prove(angleAB_Data);

        String angleAC_Data = mainActivity.angleAC.getText().toString();
        angleDeg_ac = prove(angleAC_Data);

        String angleBC_Data = mainActivity.angleBC.getText().toString();
        angleDeg_bc = prove(angleBC_Data);


        if (mainActivity.checkTest.isChecked()){
            if ((angleDeg_ab + angleDeg_ac + angleDeg_bc) !=180){
                Toast.makeText(mainActivity , "角度總合不等於180度" , Toast.LENGTH_SHORT).show();
                count = 1;
                show = false;
            }
        }

        if (count>0){
            count = 0;
            a = 1 ;
            b = 1 ;
            c = 1 ;
            big_a = proportion;
            big_b = proportion;
            big_c = proportion;

            angleDeg_ab = 60;
            angleDeg_ac = 60;
            angleDeg_bc = 60;

            //判斷是否為第一次進入App，並且只在按下產生按鍵時才會出現警告
            if (show) {
                Toast.makeText(mainActivity, "輸入不得為空格!!", Toast.LENGTH_SHORT).show();
                show =false;
            }

            //設定各頂點座標XY以及設定出現字體的座標XY
            initial(mainActivity);

        }else if (count == 0){
            big_a = a *proportion;
            big_b = b *proportion;
            big_c = c *proportion;

            //設定各頂點座標XY以及設定出現字體的座標XY
            initial(mainActivity);
        }

        //檢查顯示的座標點是否超出設定的視窗範圍，若是的話減少比例大小
        for (int i = proportion ; ac_Point_End_X > 2*viewCenterWidth/14*12 ; i-- ){
            proportion = i;
            big_a = a *proportion;
            big_b = b *proportion;
            big_c = c *proportion;
            initial(mainActivity);
        }

        for (int i = proportion ; a_Point_End_X < 0 ; i-- ){
            proportion = i;
            big_a = a *proportion;
            big_b = b *proportion;
            big_c = c *proportion;
            initial(mainActivity);
        }

        Log.v("ppking" , "  a_Point_End_Y  : " + a_Point_End_X );

        for (int i = proportion ; a_Point_End_Y < viewCenterHeight/4 ; i-- ){

            proportion = i;
            big_a = a *proportion;
            big_b = b *proportion;
            big_c = c *proportion;
            initial(mainActivity);
        }

        //檢查顯示的座標點是否超出設定的視窗範圍，若是的話減少比例大小
        for (int i = proportion ; b_Point_End_X > 2*viewCenterWidth/14*12 ; i-- ){
            proportion = i;
            big_a = a *proportion;
            big_b = b *proportion;
            big_c = c *proportion;
            initial(mainActivity);
        }

        //檢查顯示的座標點是否超出設定的視窗範圍，若是的話減少比例大小
        for (int i = proportion ; bc_Point_End_X > 2*viewCenterWidth/14*12 ; i-- ){
            proportion = i;
            big_a = a *proportion;
            big_b = b *proportion;
            big_c = c *proportion;
            initial(mainActivity);
        }

        OnDraw onDraw = mainActivity.onDraw;

        onDraw.setParameter(b_Point_Start_X , b_Point_Start_Y,
                            b_Point_End_X ,b_Point_End_Y,
                            a_Point_Start_X,a_Point_Start_Y,
                            a_Point_End_X,a_Point_End_Y,
                            ac_Point_Start_X,ac_Point_Start_Y,
                            ac_Point_End_X,ac_Point_End_Y,
                            bc_Point_Start_X,bc_Point_Start_Y,
                            bc_Point_End_X,bc_Point_End_Y);

        onDraw.setTextCenter(b_TextCenterX,b_TextCenterY,
                            a_TextCenterX,a_TextCenterY,
                            c_TextCenterX,c_TextCenterY,
                            textSize , show);
        onDraw.setLine(a, b, c);
        onDraw.setAngle(angleDeg_ab , angleDeg_ac , angleDeg_bc);
        onDraw.invalidate();


    }

    //依公式 x = cos(弳度)*r半徑
    private double ab_xPoint() {
        double angleRad = (angleDeg_ab / 180) * Math.PI;
        return Math.cos(angleRad) * big_a;
    }
    //y = sin(弳度)*r半徑 (負的是依b邊長為基準相對往上)
    private double ab_yPoint() {
        double angleRad = (angleDeg_ab / 180) * Math.PI;
        return -Math.sin(angleRad) * big_a;
    }

    private double ac_xPoint() {
        double angleRad = ((180-(angleDeg_ab+angleDeg_ac)) / 180) * Math.PI;
        return Math.cos(angleRad) * big_c;
    }

    private double ac_yPoint() {
        double angleRad = ((180-(angleDeg_ab+angleDeg_ac)) / 180) * Math.PI;
        return Math.sin(angleRad) * big_c;
    }

    private double bc_xPoint() {
        double angleRad = (angleDeg_bc / 180) * Math.PI;
        return -Math.cos(angleRad) * big_c;
    }

    private double bc_yPoint() {
        double angleRad = (angleDeg_bc / 180) * Math.PI;
        return -Math.sin(angleRad) * big_c;
    }

    private double prove(String data) {
        double intData = 0;
        if (data.matches("")) {
            count++;
        } else {
            intData = Double.parseDouble(data);
        }
        return intData;
    }

    //設定各頂點座標XY以及設定出現字體的座標XY
    private void initial(MainActivity mainActivity){
        //解析度字體大小
        float textCount = 3;
        textSize = 45;
        float textTotalSize = textCount * textSize;

        viewCenterWidth = mainActivity.onDraw.getWidth()/2;
        viewCenterHeight = mainActivity.onDraw.getHeight()/3*2;

        //-----B邊長為第一步，viewCenterWidth - ((float)big_B/2 是為了讓b邊長的一半都維持在view的中間
        b_Point_Start_X = viewCenterWidth - ((float) big_b / 2);
        b_Point_Start_Y = viewCenterHeight;

        b_Point_End_X = b_Point_Start_X + (float) big_b;
        b_Point_End_Y = b_Point_Start_Y;

        //----依B邊長為基準點，A邊長起始點跟B邊長一樣，再利用角度求出A邊長的末端座標
        a_Point_Start_X = b_Point_Start_X;
        a_Point_Start_Y = b_Point_Start_Y;

        a_Point_End_X = b_Point_Start_X + (float) ab_xPoint();
        a_Point_End_Y = b_Point_Start_Y + (float) ab_yPoint();

        //-----依A邊長為基準點，C邊長起始點為A邊長末端座標
        ac_Point_Start_X = a_Point_End_X;
        ac_Point_Start_Y = a_Point_End_Y;

        ac_Point_End_X = a_Point_End_X + (float) ac_xPoint();
        ac_Point_End_Y = a_Point_End_Y + (float) ac_yPoint();

        //-----
        bc_Point_Start_X = b_Point_End_X;
        bc_Point_Start_Y = b_Point_End_Y;

        bc_Point_End_X = b_Point_End_X + (float) bc_xPoint();
        bc_Point_End_Y = b_Point_End_Y + (float) bc_yPoint();

        //-----各個文字的至中點
        b_TextCenterX = viewCenterWidth - (textTotalSize / 2);
        b_TextCenterY = b_Point_Start_Y + textSize * 2;

        a_TextCenterX = b_Point_Start_X + ((float) ab_xPoint() / 2) - textTotalSize - 3*textSize/2 ;
        a_TextCenterY = b_Point_Start_Y + ((float) ab_yPoint() / 2);

        c_TextCenterX = a_Point_End_X + ((float) ac_xPoint() / 2) + textSize;
        c_TextCenterY = a_Point_End_Y + ((float) ac_yPoint() / 2);
    }
}
