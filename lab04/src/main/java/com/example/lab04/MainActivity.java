package com.example.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v4.app.*;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener  {
    TextView bellTextView;
    TextView labelTextView;
    CheckBox repeatCheckView;
    CheckBox vibrateCheckView;
    Switch switchView;

    float initX;
    long  initTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bellTextView = (TextView) findViewById(R.id.bell_name);
        labelTextView = (TextView) findViewById(R.id.label);
        repeatCheckView = (CheckBox) findViewById(R.id.repeatCheck);
        vibrateCheckView =(CheckBox) findViewById(R.id.repeatCheck);
        vibrateCheckView = (CheckBox) findViewById(R.id.vibrate);
        switchView =(Switch) findViewById(R.id.onOff);

        bellTextView.setOnClickListener(this);
        labelTextView.setOnClickListener(this);

        repeatCheckView.setOnCheckedChangeListener(this);
        vibrateCheckView.setOnCheckedChangeListener(this);
        switchView.setOnCheckedChangeListener(this);
    }



    private void showToast(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            initX = event.getRawX();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float diffX = initX - event.getRawX();
            if (diffX > 30) {
                showToast("왼쪽으로 화면을 밀었습니다.");
            } else if(diffX < -30){
                showToast("오른쪽으로 화면을 밀었습니다.");
            }
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if(System.currentTimeMillis() - initTime > 3000) {
                showToast("종료할려면 한번 더 누르세요");
                initTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v){
        if(v == bellTextView ){
            showToast("벨 텍스트 클릭");
        } else if(v==labelTextView){
            showToast("라벨 텍스트 클릭");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isCheck) {
        if(buttonView==repeatCheckView){
            showToast("repeat checkbox is"+isCheck);
        } else if(buttonView==vibrateCheckView){
            showToast("vibrate checkbox is" + isCheck);
        } else if(buttonView == switchView){
            showToast("switch is "+isCheck);
        }
    }


}