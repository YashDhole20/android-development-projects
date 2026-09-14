package com.example.locationbasefire;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class Registration extends AppCompatActivity {

    private EditText fullName,uname,email,mobile,pass,con;
    private Button btn_submit;
    private TextView gotoLogin;
    DatabaseReference reference;
   private FirebaseAuth  auth;
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = auth.getCurrentUser();
//        if(currentUser != null){
//            startActivity(new Intent(Registration.this, Login.class));
//            finish();
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fullName=findViewById(R.id.signupFullNm);
        uname=findViewById(R.id.signupUserNm);
        email=findViewById(R.id.signupEmail);
        mobile=findViewById(R.id.signuppMobileNo);
        pass=findViewById(R.id.signupPassword);
        con=findViewById(R.id.signupConfirmPassword);

        btn_submit=findViewById(R.id.signupBtnRegister);
        gotoLogin=findViewById(R.id.signupGotoLogin);
        reference= FirebaseDatabase.getInstance().getReference().child("User");
        auth=FirebaseAuth.getInstance();
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this,Login.class));
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HashMap<String,String> in=new HashMap<String, String>();
//                in.put("Full_Name",fullName.getText().toString().trim());
//                in.put("Username",uname.getText().toString().trim());
//                in.put("Email",email.getText().toString().trim());
//                in.put("Mobile",mobile.getText().toString().trim());
//                in.put("Password",pass.getText().toString().trim());
//
//

                String una=uname.getText().toString().trim();
                String ema=email.getText().toString().trim();
                String full=fullName.getText().toString().trim();
                String mo=mobile.getText().toString().trim();
                String pa=pass.getText().toString().trim();
                String key=ema.replace(".",",");
                User user =new User(full,una,ema,mo,pa);
                reference.child(key).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(Registration.this, "Registration Successfully ref", Toast.LENGTH_SHORT).show();
                     }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(Registration.this, "Registration Failed. ref", Toast.LENGTH_SHORT).show();
                    }
                });
                String e=email.getText().toString().trim();
                String p=pass.getText().toString().trim();
                auth.createUserWithEmailAndPassword(e, p)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                   auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           Toast.makeText(Registration.this, "Registration Successfully Auth", Toast.LENGTH_SHORT).show();
                                           startActivity(new Intent(Registration.this, Login.class));
                                           finish();
                                       }
                                   });

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Registration.this, ""+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}