package com.apti.smartaptitude;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class feedback extends AppCompatActivity {
    TextView tvFeedback;
    RatingBar rbStars;
    EditText etFeedback;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_activity);

        tvFeedback = findViewById(R.id.tvFeedback);
        rbStars = findViewById(R.id.rbStars);
        etFeedback = findViewById(R.id.etFeedback);
        btnSend = findViewById(R.id.btnSend);

        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == 0) {
                    tvFeedback.setText("Very Dissatisfied");
                } else if (rating == 1) {
                    tvFeedback.setText("Dissatisfied");
                } else if (rating == 2 || rating == 3) {
                    tvFeedback.setText("OK");
                } else if (rating == 4) {
                    tvFeedback.setText("Satisfied");
                } else if (rating == 5) {
                    tvFeedback.setText("Very Satisfied");
                }
            }
        });

        btnSend.setOnClickListener(view -> {
            // Get the entered feedback
            String feedbackText = etFeedback.getText().toString();

            // Assuming StudentLogin.class is your intended activity
            Intent intent = new Intent(feedback.this, StudentLogin.class);


            intent.putExtra("feedback", feedbackText);

            startActivity(intent);

            finish();


            Toast.makeText(feedback.this, "Feedback submitted successfully", Toast.LENGTH_SHORT).show();
        });
    }
}
