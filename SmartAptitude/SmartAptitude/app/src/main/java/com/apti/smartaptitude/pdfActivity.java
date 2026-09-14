package com.apti.smartaptitude;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class pdfActivity extends AppCompatActivity {
    PDFView pdfView;

    @SuppressLint({"MissingSuperCall", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf); // Assuming your layout XML file is named "activity_pdf.xml"

        pdfView = new PDFView(this, null);
        setContentView(pdfView); // Set the content view to the PDFView

        pdfView.fromAsset("resumeformate.pdf")
                .defaultPage(0)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10)
                .load();
    }
}