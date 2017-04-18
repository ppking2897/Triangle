package com.example.biancaen.ondraw;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText lineA, lineB, lineC, angleAB, angleBC, angleAC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineA = (EditText) findViewById(R.id.lineA);
        lineB = (EditText) findViewById(R.id.lineB);
        lineC = (EditText) findViewById(R.id.lineC);

        angleAB = (EditText) findViewById(R.id.angleAB);
        angleAC = (EditText) findViewById(R.id.angleAC);
        angleBC = (EditText) findViewById(R.id.angleBC);
    }

    public void button(View view) {

        //按下按鈕關閉鍵盤
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.button).getWindowToken(), 0);

        String lineA_Data = lineA.getText().toString();
        double a = prove(lineA_Data);

        String lineB_Data = lineB.getText().toString();
        double b = prove(lineB_Data);

        String lineC_Data = lineC.getText().toString();
        double c = prove(lineC_Data);

        String angleAB_Data = angleAB.getText().toString();
        double angleDeg_ab = prove(angleAB_Data);

        String angleAC_Data = angleAC.getText().toString();
        double angleDeg_ac = prove(angleAC_Data);

        String angleBC_Data = angleBC.getText().toString();
        double angleDeg_bc = prove(angleBC_Data);

        //放大比例
        int proportion = 250;

        DegRadCount degRadCount = new DegRadCount(angleDeg_ab, angleDeg_ac, angleDeg_bc , a, c , proportion);

        OnDraw ondraw = (OnDraw)findViewById(R.id.ondraw);
        ondraw.setParameter(degRadCount.a_xPoint(),degRadCount.a_yPoint(),
                            degRadCount.ac_xPoint(),degRadCount.ac_yPoint(),
                            degRadCount.bc_xPoint(),degRadCount.bc_yPoint());
        ondraw.setLine(a , b , c , proportion);
        ondraw.invalidate();

    }

    public double prove(String data) {
        double intData = 0;
        if (data.matches("")) {
            Toast.makeText(this, "Error !!", Toast.LENGTH_SHORT).show();
        } else {
            intData = Double.parseDouble(data);
        }
        return intData;
    }

    //點擊空白處關閉鍵盤，判斷是否有點擊到整個畫面的view
    public boolean onTouchEvent(MotionEvent event) {

        if(null != this.getCurrentFocus()){
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }

        return super .onTouchEvent(event);
    }

}
