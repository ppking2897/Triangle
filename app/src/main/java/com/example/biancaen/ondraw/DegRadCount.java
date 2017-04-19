package com.example.biancaen.ondraw;

import android.widget.Toast;

class DegRadCount {
    private double angleDeg_ab;
    private double angleDeg_ac;
    private double angleDeg_bc;


    private double big_a;
    private double big_b;
    private double big_c;

    private int count;


    DegRadCount(MainActivity mainActivity , int proportion , boolean show){

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



        if (count>0){
            proportion = 500;
            count = 0;
            a = 1 ;
            b = 1 ;
            c = 1 ;
            big_a = proportion;
            big_c = proportion;

            angleDeg_ab = 60;
            angleDeg_ac = 60;
            angleDeg_bc = 60;

            if (show) {
                Toast.makeText(mainActivity, "輸入不得為空格!!", Toast.LENGTH_SHORT).show();
                show =false;
            }

        }else if (count == 0){
            big_a = a *proportion;
            big_c = c *proportion;
        }

        OnDraw onDraw = mainActivity.onDraw;

        onDraw.setParameter(a_xPoint(),a_yPoint(),
                ac_xPoint(),ac_yPoint(),
                bc_xPoint(),bc_yPoint(),
                show);

        onDraw.setLine(a, b, c , proportion);
        onDraw.setAngle(angleDeg_ab , angleDeg_ac , angleDeg_bc);
        onDraw.invalidate();


    }
    //依公式 x = cos(弳度)*r半徑

    private double a_xPoint() {
        double angleRad = (angleDeg_ab / 180) * Math.PI;
        return Math.cos(angleRad) * big_a;
    }
        //y = sin(弳度)*r半徑 (負的是依b邊長為基準相對往上)
    private double a_yPoint() {
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
}
