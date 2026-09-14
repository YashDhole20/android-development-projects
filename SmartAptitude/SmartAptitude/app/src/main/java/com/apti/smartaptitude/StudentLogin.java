package com.apti.smartaptitude;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;

public class StudentLogin extends AppCompatActivity {
    private EditText etuname, etpass;
    private  Button btnlogin;
    TextView tv_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        etuname = findViewById(R.id.et_student_uname);
        etpass = findViewById(R.id.et_student_pass);
        btnlogin = findViewById(R.id.btn_student_login);
        tv_reg = findViewById(R.id.tv_reg);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etuname.getText().toString().isEmpty() || etpass.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty Not Allowed", Toast.LENGTH_LONG).show();
                } else {
                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("email", etuname.getText().toString());
                    param.put("password", etpass.getText().toString());

                    StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(sb);
                    String id = Network.connect("http://" + Network.IP + "/login.php", param);

                    System.out.println(id);
                    String rs=id.trim();
                    if (rs.equals("0")) {
                        Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(StudentLogin.this, Dashboard.class);
                        intent.putExtra("UserID",id);
                        startActivity(intent);
                    }
                }
            }
        });
        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), StudentRegister.class));
            }
        });
    }
}