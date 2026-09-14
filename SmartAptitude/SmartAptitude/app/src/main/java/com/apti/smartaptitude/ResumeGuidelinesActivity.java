package com.apti.smartaptitude;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResumeGuidelinesActivity extends AppCompatActivity {
    Button btnpdf;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_guidelines);

        TextView resumeGuidelinesTextView = findViewById(R.id.resumeGuidelinesTextView);
        resumeGuidelinesTextView.setText(getResumeGuidelines());
        btnpdf = findViewById(R.id.pdfbtn);
        btnpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),pdfActivity.class);
                startActivity(i);
            }
        });
    }

    private String getResumeGuidelines() {
        StringBuilder guidelines = new StringBuilder();
        guidelines.append("1. Keep your resume concise and relevant to the job you're applying for.\n");
        guidelines.append("2. Include a header with your name, contact information, and optionally a professional summary.\n");
        guidelines.append("3. List your education, including degrees, certificates, and relevant coursework.\n");
        guidelines.append("4. Include your work experience, starting with the most recent job first.\n");
        guidelines.append("5. Provide details about your roles and responsibilities in each job, including achievements and contributions.\n");
        guidelines.append("6. Highlight relevant skills, both technical and soft skills.\n");
        guidelines.append("7. Consider including additional sections such as volunteer work, certifications, or projects.\n");
        guidelines.append("8. Use a clean and professional format, with consistent formatting and easy-to-read fonts.\n");
        guidelines.append("9. Proofread your resume carefully to avoid typos and grammatical errors.\n");
        guidelines.append("10. Tailor your resume for each job application, focusing on the qualifications and requirements of the specific job.\n");
        return guidelines.toString();
    }

}
