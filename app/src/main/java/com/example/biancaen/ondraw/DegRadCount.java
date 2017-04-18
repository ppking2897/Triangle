package com.example.biancaen.ondraw;

import android.util.Log;

class DegRadCount {
    private double angleDeg_ab;
    private double angleDeg_ac;
    private double angleDeg_bc;

    private double a;
    private double c;

    DegRadCount(double angleDeg_ab, double angleDeg_ac ,double angleDeg_bc ,double a, double c , int proportion){
        this.angleDeg_ab = angleDeg_ab;
        this.angleDeg_ac = angleDeg_ac;
        this.angleDeg_bc = angleDeg_bc;

        this.a = a *proportion;
        this.c = c *proportion;

    }
    //依公式 x = cos(弳度)*r半徑

    double a_xPoint() {
        double angleRad = (angleDeg_ab / 180) * Math.PI;
        return Math.cos(angleRad) * a;
    }
        //y = sin(弳度)*r半徑 (負的是依b邊長為基準相對往上)
    double a_yPoint() {
        double angleRad = (angleDeg_ab / 180) * Math.PI;
        return -Math.sin(angleRad) * a;
    }
    double ac_xPoint() {
        double angleRad = ((180-(angleDeg_ab+angleDeg_ac)) / 180) * Math.PI;
        Log.v("ppking" , " ac_xPoint :" + Math.cos(angleRad) * c);
        return Math.cos(angleRad) * c;
    }
    double ac_yPoint() {
        double angleRad = ((180-(angleDeg_ab+angleDeg_ac)) / 180) * Math.PI;
        return Math.sin(angleRad) * c;
    }
    double bc_xPoint() {
        double angleRad = (angleDeg_bc / 180) * Math.PI;
        return -Math.cos(angleRad) * c;
    }
    double bc_yPoint() {
        double angleRad = (angleDeg_bc / 180) * Math.PI;
        return -Math.sin(angleRad) * c;
    }
}
