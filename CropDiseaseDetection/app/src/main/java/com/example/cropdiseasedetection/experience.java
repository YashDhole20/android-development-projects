package com.example.cropdiseasedetection;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cropdiseasedetection.ml.DiseaseDetection;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class experience extends AppCompatActivity {
    TextView result, demoText, classified, clickHere;
    ImageView imageView, demoArrow;
    Button picture, galleryButton;

    int imageSize = 224;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        result = findViewById(R.id.result);
        imageView = findViewById(R.id.imageView);
        picture = findViewById(R.id.button);
        galleryButton = findViewById(R.id.gallery_button);
        demoText = findViewById(R.id.demoText);
        clickHere = findViewById(R.id.click_here);
        demoArrow = findViewById(R.id.demoArrow);
        classified = findViewById(R.id.classified);

        demoText.setVisibility(View.VISIBLE);
        clickHere.setVisibility(View.GONE);
        demoArrow.setVisibility(View.VISIBLE);
        classified.setVisibility(View.GONE);
        result.setVisibility(View.GONE);

        picture.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override

            //double import krun bg project
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 1);
                } else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            processImage(image);
        } else if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                processImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processImage(Bitmap image) {
        imageView.setImageBitmap(image);
        demoText.setVisibility(View.GONE);
        clickHere.setVisibility(View.VISIBLE);
        demoArrow.setVisibility(View.GONE);
        classified.setVisibility(View.VISIBLE);
        result.setVisibility(View.VISIBLE);

        image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
        classifyImage(image);
    }

    private void classifyImage(Bitmap image) {
        try {
            DiseaseDetection model = DiseaseDetection.newInstance(getApplicationContext());

            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValue = new int[imageSize * imageSize];
            image.getPixels(intValue, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            int pixel = 0;
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValue[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            DiseaseDetection.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidence = outputFeature0.getFloatArray();

            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidence.length; i++) {
                if (confidence[i] > maxConfidence) {
                    maxConfidence = confidence[i];
                    maxPos = i;
                }
            }
            String[] classes = {"normal","blight", "powdery mildew"};
            result.setText(classes[maxPos]);
            result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.google.com/search?q=" + result.getText())));
                }
            });

            model.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 2);
    }
}




//package com.example.cropdiseasedetection;
//
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.media.ThumbnailUtils;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.cropdiseasedetection.ml.DiseaseDetection;
//
//import org.tensorflow.lite.DataType;
//import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
//
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.nio.ByteOrder;
//
//public class experience extends AppCompatActivity {
//    TextView result, demotext, classified, clickHere;
//    ImageView imageView, arrowimage;
//    Button picture;
//
//    int imageSize = 224;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_experience);
//
//        result = findViewById(R.id.result);
//        imageView = findViewById(R.id.imageView);
//        picture = findViewById(R.id.button);
//
//        demotext = findViewById(R.id.demoText);
//        clickHere = findViewById(R.id.click_here);
//        arrowimage = findViewById(R.id.demoArrow);
//        classified = findViewById(R.id.classified);
//
//        demotext.setVisibility(View.VISIBLE);
//        clickHere.setVisibility(View.GONE);
//        arrowimage.setVisibility(View.VISIBLE);
//        classified.setVisibility(View.GONE);
//        result.setVisibility(View.GONE);
//
//        picture.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View view) {
//                //launch camera if we have permission
//                if (checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
//                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(cameraIntent,1);
//                }else {
//                    requestPermissions(new String[]{Manifest.permission.CAMERA},100);
//                }
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == 1 && resultCode == RESULT_OK){
//            Bitmap image = (Bitmap) data.getExtras().get("data");
//            int dimension = Math.min(image.getWidth(),image.getHeight());
//            image = ThumbnailUtils.extractThumbnail(image,dimension,dimension);
//            imageView.setImageBitmap(image);
//
//            demotext.setVisibility(View.GONE);
//            clickHere.setVisibility(View.VISIBLE);
//            arrowimage.setVisibility(View.GONE);
//            classified.setVisibility(View.VISIBLE);
//            result.setVisibility(View.VISIBLE);
//
//            image = Bitmap.createScaledBitmap(image,imageSize,imageSize,false);
//            classifyIamge(image);
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    private void classifyIamge(Bitmap image) {
//        try {
//            DiseaseDetection model = DiseaseDetection.newInstance(getApplicationContext());
//
//            TensorBuffer inputFeatures0 = TensorBuffer.createFixedSize(new int[]{1,224,224,3}, DataType.FLOAT32);
//            ByteBuffer byteBuffer= ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
//            byteBuffer.order(ByteOrder.nativeOrder());
//
//            int[] intVAlue = new int[imageSize * imageSize];
//            image.getPixels(intVAlue,0,image.getWidth(),0,0,image.getWidth(),image.getHeight());
//
//            int pixel = 0;
//            for (int i = 0; i < imageSize; i++){
//                for (int j = 0; j< imageSize; j++){
//                    int val = intVAlue[pixel++];
//                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
//                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
//                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
//                }
//            }
//
//            inputFeatures0.loadBuffer(byteBuffer);
//
//            DiseaseDetection.Outputs ouputs = model.process(inputFeatures0);
//            TensorBuffer outputFeatures0 = ouputs.getOutputFeature0AsTensorBuffer();
//
//            float[] confidence = outputFeatures0.getFloatArray();
//
//            int maxPos = 0;
//            float maxConfidence =0;
//            for (int i = 0;i < confidence.length; i++){
//                if(confidence[i] > maxConfidence){
//                    maxConfidence = confidence[i];
//                    maxPos = i;
//                }
//            }
//            String[] classes = {"powdery mildew","blight"};
//            result.setText(classes[maxPos]);
//            result.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity(new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("https://www.google.com/search?q="+result.getText())));
//                }
//            });
//
//            model.close();
//
//        }catch(IOException e){
//
//        }
//    }
//}
//
//
//
//
