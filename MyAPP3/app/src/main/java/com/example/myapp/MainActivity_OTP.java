package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_OTP extends AppCompatActivity {

    private EditText otp1,otp2,otp3,otp4;
    private TextView resetOtp;
    private Button verifyBtn;

    private  int resetTime=60;
    private  boolean resentEnabled=false;
    private int selectETPosition=0;

    public interface OnNextPageListener{
        void onNextPage();
    }
    private OnNextPageListener mListener;
      public void setOnNextPageListener(OnNextPageListener listener){
         mListener=listener;
     }
     private void gotoNextPage(){
         if (mListener!=null){
             mListener.onNextPage();
         }
     }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_otp);

        otp1=findViewById(R.id.editTxtOne);
        otp2=findViewById(R.id.editTxtTwo);
        otp3=findViewById(R.id.editTxtThree);
        otp4=findViewById(R.id.editTxtFour);
        verifyBtn=findViewById(R.id.btnVerify);
        resetOtp=findViewById(R.id.txtTResetOtp);


        otp1.addTextChangedListener(textWatcher);
        otp2.addTextChangedListener(textWatcher);
        otp3.addTextChangedListener(textWatcher);
        otp4.addTextChangedListener(textWatcher);

       showKeyboard(otp1);
        startCountDownTimer();

        resetOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resentEnabled){
                    startCountDownTimer();
                }
            }
        });
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getOtp=otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString();
                if(getOtp.length()==4 && getOtp.equals("1234")){
                     Intent intent=new Intent(MainActivity_OTP.this, MainActivity_resetPassword.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity_OTP.this, "Invalid", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
    private  final TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length()>0){
                if (selectETPosition==0){
                    selectETPosition=1;
                    showKeyboard(otp2);
                }
                else if(selectETPosition==1) {
                    selectETPosition=2;
                    showKeyboard(otp3);
                }
                else if(selectETPosition==2) {
                    selectETPosition=3;
                    showKeyboard(otp4);
                }
                else{
                    verifyBtn.setBackgroundColor(R.drawable.round_back_red);
                }
            }

        }
    };

    private void showKeyboard(EditText otp){
         otp.requestFocus();
        InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otp,InputMethodManager.SHOW_IMPLICIT);
    }

    private void startCountDownTimer(){

        resentEnabled=false;
        resetOtp.setTextColor(Color.parseColor("#85BFDA"));

        new CountDownTimer(resetTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                resetOtp.setText("Resend Code ("+(millisUntilFinished/1000)+")");
            }

            @Override
            public void onFinish() {
                resentEnabled=true;
                resetOtp.setText("Resend OTP");
                resetOtp.setTextColor(getResources().getColor(android.R.color.black));
            }
        }.start();

    }

    @Override
    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DEL ){
            if(selectETPosition==3){
                selectETPosition=2;
                showKeyboard(otp3);
            }
            else if(selectETPosition==2) {
                selectETPosition=1;
                showKeyboard(otp2);
            }
            else if(selectETPosition==1) {
                selectETPosition=0;
                showKeyboard(otp1);
            }
            return true;
        }
        else{
            return super.onKeyUp(keyCode, event);
        }
    }

}