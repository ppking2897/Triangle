package com.example.biancaen.ondraw;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText lineA, lineB, lineC, angleAB, angleBC, angleAC;
    OnDraw onDraw;
    DegRadCount degRadCount;
    private boolean show;
    private int proportion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onDraw = (OnDraw)findViewById(R.id.ondraw);

        lineA = (EditText) findViewById(R.id.lineA);
        lineB = (EditText) findViewById(R.id.lineB);
        lineC = (EditText) findViewById(R.id.lineC);

        angleAB = (EditText) findViewById(R.id.angleAB);
        angleAC = (EditText) findViewById(R.id.angleAC);
        angleBC = (EditText) findViewById(R.id.angleBC);

        Click click = new Click();

        lineA.setOnClickListener(click);
        lineB.setOnClickListener(click);
        lineC.setOnClickListener(click);
        angleAB.setOnClickListener(click);
        angleAC.setOnClickListener(click);
        angleBC.setOnClickListener(click);

        proportion = 500;
        show = false;
        degRadCount = new DegRadCount(this, proportion , show);

    }

    public void button(View view) {

        //按下按鈕關閉鍵盤
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.button).getWindowToken(), 0);

        //放大比例
        proportion = 500;
        show = true ;
        degRadCount = new DegRadCount(this , proportion , show);



    }
    public void clear(View view){

        lineA.setText("");
        lineB.setText("");
        lineC.setText("");
        angleAB.setText("");
        angleBC.setText("");
        angleAC.setText("");
        show = false;
        degRadCount = new DegRadCount(this, 0 , show);
    }

    //點擊空白處關閉鍵盤，判斷是否有點擊到整個畫面的view
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            View view = findViewById(R.id.row);
            //view.setFocusable(true);
            //view.setFocusableInTouchMode(true);
            view.requestFocus();
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    private class Click implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            EditText editText = (EditText)v.findViewById(id);
            editText.setSelectAllOnFocus(true);
            Log.v("ppking" , " true :::" + id);
        }
    }
}
