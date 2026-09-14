package com.example.collegeproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class StudentRegisterOTP extends AppCompatActivity {

    String phoneNumber;
    Long timeoutSeconds = 60L;
    String verificationCode;
    PhoneAuthProvider.ForceResendingToken  resendingToken;

    EditText otpInput;
    Button nextBtn;
    ProgressBar progressBar;
    TextView resendOtpTextView;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register_otp);

        otpInput = findViewById(R.id.login_otp);
        nextBtn = findViewById(R.id.login_next_btn);
        progressBar = findViewById(R.id.login_progress_bar);
        resendOtpTextView = findViewById(R.id.resend_otp_textview);

        phoneNumber = "+91" + getIntent().getStringExtra("number");
        System.out.println(phoneNumber+"  123");

        sendOtp(phoneNumber,false);

        nextBtn.setOnClickListener(v -> {
            String enteredOtp  = otpInput.getText().toString();
            PhoneAuthCredential credential =  PhoneAuthProvider.getCredential(verificationCode,enteredOtp);
            if (verificationCode.equals(enteredOtp)) {
                // Credentials match, attempt sign-inxam
                signIn(credential);
            } else {
                // Credentials do not match, display error to the user
                Toast.makeText(StudentRegisterOTP.this, "Invalid OTP. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });

        resendOtpTextView.setOnClickListener((v)->{
            sendOtp(phoneNumber,true);
        });

    }

    void sendOtp(String phoneNumber,boolean isResend){
        startResendTimer();
        setInProgress(true);
        PhoneAuthOptions.Builder builder =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(timeoutSeconds, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signIn(phoneAuthCredential);
                                setInProgress(false);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                System.out.println(e.getMessage());
                                Toast.makeText(StudentRegisterOTP.this, "OTP verification failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                setInProgress(false);
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                verificationCode = s;
                                resendingToken = forceResendingToken;
                                Toast.makeText(StudentRegisterOTP.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();
                                setInProgress(false);
                            }
                        });
        if(isResend){
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
        }else{
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }

    }

    void setInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            nextBtn.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            nextBtn.setVisibility(View.VISIBLE);
        }
    }

    void signIn(PhoneAuthCredential phoneAuthCredential){
        //login and go to next activity
        setInProgress(true);
        Intent intent = new Intent(StudentRegisterOTP.this,Dashboard.class);
        Toast.makeText(StudentRegisterOTP.this, "Registeration Successfully", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    void startResendTimer() {
        resendOtpTextView.setEnabled(false);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        timeoutSeconds--;
                        String text = "Resend OTP in " + timeoutSeconds + " seconds";
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                resendOtpTextView.setText(text);
                            }
                        });
                        if (timeoutSeconds <= 0) {
                            timeoutSeconds = 60L;
                            timer.cancel();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    resendOtpTextView.setEnabled(true);
                                }
                            });
                        }
                    }
                }, 0, 1000);
            }
        });
    }
}












