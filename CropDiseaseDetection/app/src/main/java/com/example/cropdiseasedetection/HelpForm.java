package com.example.cropdiseasedetection;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HelpForm extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int SELECT = 1;
    private EditText editCropName, editCropDescription;
    private ImageView imageView;
    private String selectedPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_form);

        imageView = findViewById(R.id.imageView);
        editCropName = findViewById(R.id.editCropName);
        editCropDescription = findViewById(R.id.editCropDescription);
        Button selectImageButton = findViewById(R.id.btnSelectImage);
        Button uploadButton = findViewById(R.id.btnSubmit);

        selectImageButton.setOnClickListener(v -> openFilePicker());

        uploadButton.setOnClickListener(v -> uploadimg());
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT) {
                Uri selectedUri = data.getData();
                selectedPath = getPath(selectedUri);
                System.out.println("Path : " + selectedPath);
            }
        }
    }

    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    private byte[] getImageBytesFromUri(Uri imageUri) {
        try {
            return getBytesFromBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }

    public void uploadimg() {
        String cropName = editCropName.getText().toString().trim();
        String cropDescription = editCropDescription.getText().toString().trim();

        // Check if crop name and description are not empty
        if (cropName.isEmpty() || cropDescription.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter crop name and description", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://" + Network.IP + "/UploadFile.php";

        System.out.println(url);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        File photoFile = new File(selectedPath);

        try {
            int size;
            size = (int) photoFile.length();

            byte[] bytes = new byte[size];

             BufferedInputStream buf = new BufferedInputStream(new FileInputStream(photoFile));
            buf.read(bytes, 0, bytes.length);
            buf.close();

            String encoded = Base64.encodeToString(bytes, Base64.DEFAULT);
            System.err.println("encoded -> " + encoded);

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<BasicNameV aluePair> nameValuePairs = new ArrayList<>();

            nameValuePairs.add(new BasicNameValuePair("file_name", photoFile.getName()));
            nameValuePairs.add(new BasicNameValuePair("file", encoded));
            nameValuePairs.add(new BasicNameValuePair("CropName", cropName));
            nameValuePairs.add(new BasicNameValuePair("CropDescription", cropDescription));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httpPost);
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line;
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line).append(NL);
            }

            in.close();
            String resp = sb.toString().trim();

            if (resp.equals("0")) {
                Toast.makeText(getApplicationContext(), "No Food Found...", Toast.LENGTH_LONG).show();
                imageView.setVisibility(View.INVISIBLE);
            } else {
                Toast.makeText(getApplicationContext(), "Submission Successful", Toast.LENGTH_SHORT).show();
                openNewActivity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openNewActivity() {
        Intent intent = new Intent(this, farmersection.class);
        startActivity(intent);
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        } else {
            return null;
        }
    }
}


//package com.example.cropdiseasedetection;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.provider.MediaStore;
//import android.util.Base64;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//public class HelpForm extends AppCompatActivity {
//    private static final int PICK_IMAGE_REQUEST = 1;
//    private static final int SELECT = 1;
//    private EditText editCropName, editCropDescription;
//    private ImageView imageView;
//    private String selectedPath = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_help_form);
//
//        imageView = findViewById(R.id.imageView);
//        editCropName = findViewById(R.id.editCropName);
//        editCropDescription = findViewById(R.id.editCropDescription);
//        Button selectImageButton = findViewById(R.id.btnSelectImage);
//        Button uploadButton = findViewById(R.id.btnSubmit);
//
//        selectImageButton.setOnClickListener(v -> openFilePicker());
//
//        uploadButton.setOnClickListener(v -> uploadimg());
//    }
//
//    private void openFilePicker() {
//        Intent intent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, SELECT);
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == SELECT) {
//                Uri selectedUri = data.getData();
//                selectedPath = getPath(selectedUri);
//                System.out.println("Path : " + selectedPath);
//            }
//        }
//    }
//
//    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        return stream.toByteArray();
//    }
//
//    private byte[] getImageBytesFromUri(Uri imageUri) {
//        try {
//            return getBytesFromBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private byte[] getBytesFromBitmap(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
//        return stream.toByteArray();
//    }
//
//    public void uploadimg() {
//        String url = "http://" + Network.IP + "/UploadFile.php";
//
//        System.out.println(url);
//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//                .permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        File photoFile = new File(selectedPath);
//
//        try {
//            int size;
//            size = (int) photoFile.length();
//
//            byte[] bytes = new byte[size];
//
//            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(photoFile));
//            buf.read(bytes, 0, bytes.length);
//            buf.close();
//
//            String encoded = Base64.encodeToString(bytes, Base64.DEFAULT);
//            System.err.println("encoded -> " + encoded);
//
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost(url);
//            List<BasicNameValuePair> nameValuePairs = new ArrayList<>();
//
//            nameValuePairs.add(new BasicNameValuePair("file_name", photoFile.getName()));
//            nameValuePairs.add(new BasicNameValuePair("file", encoded));
//            nameValuePairs.add(new BasicNameValuePair("CropName", editCropName.getText().toString()));
//            nameValuePairs.add(new BasicNameValuePair("CropDescription", editCropDescription.getText().toString()));
//
//            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//            HttpResponse response = httpclient.execute(httpPost);
//            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            StringBuffer sb = new StringBuffer("");
//            String line;
//            String NL = System.getProperty("line.separator");
//            while ((line = in.readLine()) != null) {
//                sb.append(line).append(NL);
//            }
//
//            in.close();
//            String resp = sb.toString().trim();
//
//            if (resp.equals("0")) {
//                Toast.makeText(getApplicationContext(), "No Food Found...", Toast.LENGTH_LONG).show();
//                imageView.setVisibility(View.INVISIBLE);
//            } else {
//                Toast.makeText(getApplicationContext(), "Submission Successful", Toast.LENGTH_SHORT).show();
//                openNewActivity();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void openNewActivity() {
//        Intent intent = new Intent(this, farmersection.class);
//        startActivity(intent);
//    }
//
//    public String getPath(Uri uri) {
//        String[] projection = {MediaStore.Video.Media.DATA};
//        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
//        if (cursor != null) {
//            cursor.moveToFirst();
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
//            String path = cursor.getString(column_index);
//            cursor.close();
//            return path;
//        } else {
//            return null;
//        }
//    }
//}







//package com.example.cropdiseasedetection;
//
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.provider.MediaStore;
//import android.util.Base64;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//public class HelpForm extends AppCompatActivity {
//    private static final int PICK_IMAGE_REQUEST = 1;
//    private static final int SELECT=1;
//    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
//    private EditText editCropName, editCropDescription;
//
//
//    private ImageView imageView;
//
//    private byte[] imageBytes;
//
//    private String selectedPath=null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_help_form);
//
//        imageView = findViewById(R.id.imageView);
//        editCropName = findViewById(R.id.editCropName);
//        editCropDescription = findViewById(R.id.editCropDescription);
//        Button selectImageButton = findViewById(R.id.btnSelectImage);
//        Button uploadButton = findViewById(R.id.btnSubmit);
//
//        selectImageButton.setOnClickListener(v -> {
//            openFilePicker();
//        });
//
//        uploadButton.setOnClickListener(v -> {
//            uploadimg();
//        });
//    }
//
//    private void openFilePicker() {
//        Intent intent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent,SELECT);
//    }
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == SELECT) {
//                Uri selectedUri = data.getData();
//                selectedPath = getPath(selectedUri);
//                System.out.println("Path : " + selectedPath);
//
//            }
//        }
//
//    }
//
//
//    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        return stream.toByteArray();
//    }
//
//    private byte[] getImageBytesFromUri(Uri imageUri) {
//        try {
//            return getBytesFromBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private byte[] getBytesFromBitmap(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
//        return stream.toByteArray();
//    }
//    public void uploadimg(){
////
////        HashMap<String, String> param = new HashMap<String, String>();
////                param.put();
////                param.put();
////
////
////                String rs = Network.connect("http://" + Network.IP + "/helpform.php", param);
////                // Convert Bitmap to Base64
////
////
//        String url = "http://"+Network.IP+"/UploadFile.php?";
//
//        System.out.println(url);
//        //		Toast.makeText(Get, text, duration)
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//                .permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        File photoFile=null;
//        String resp="0";
//        photoFile = new File(selectedPath);
//
//        try {
//            int size,size1,size2,size3,size4;
//            size= (int) photoFile.length();
//
//            byte[] bytes,bytes1,bytes2,bytes3,bytes4;
//            bytes= new byte[size];
//
//            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(photoFile));
//            buf.read(bytes, 0, bytes.length);
//            buf.close();
//
//
//            String encoded = Base64.encodeToString(bytes, Base64.DEFAULT);
//            System.err.println("encoded -> "+encoded);
//
//            Toast.makeText(getApplicationContext(), "encoded"+encoded, Toast.LENGTH_LONG).show();
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost(url);
//            List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
//
//
//
//            nameValuePairs.add(new BasicNameValuePair("file_name", photoFile.getName()));
//
//            nameValuePairs.add(new BasicNameValuePair("id", "data"));
//
//            nameValuePairs.add(new BasicNameValuePair("file", encoded));
//            nameValuePairs.add(new BasicNameValuePair("CropName", editCropName.getText().toString()));
//            nameValuePairs.add(new BasicNameValuePair("CropDescription", editCropDescription.getText().toString()));
//
//
//
//
//
//            System.out.println("URL"+nameValuePairs);
//            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//            HttpResponse response = httpclient.execute(httpPost);
//            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            StringBuffer sb = new StringBuffer("");
//            String line = "";
//            String NL = System.getProperty("line.separator");
//            while ((line = in.readLine()) != null) {
//                sb.append(line + NL);
//            }
//
//            in.close();
//            resp = sb.toString();
//
//            resp = resp.trim();
//            if(resp.equals("0")){
//                Toast.makeText(getApplicationContext(),"No Food Found...", Toast.LENGTH_LONG).show();
//                imageView.setVisibility(View.INVISIBLE);
//
//            }else{
//                String sep[]=resp.split(",");
//                System.out.println(resp);
//
//                imageView.setVisibility(View.VISIBLE);
//                Bitmap bmp= BitmapFactory.decodeFile(selectedPath);
//
//                imageView.setImageBitmap(bmp);
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//    public String getPath(Uri uri) {
//        String[] projection = { MediaStore.Video.Media.DATA };
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(column_index);
//    }
//}





//package com.example.cropdiseasedetection;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import java.util.HashMap;
//public class HelpForm extends AppCompatActivity {
////    private Bitmap bitmap;
//
//    private static final int REQUEST_WRITE_PERMISSION = 786;
//    private String selectedPath = null;
//    private static final int SELECT = 1;
//
//    //    public static final String UPLOAD_KEY = "image";
////    private static final int PICK_IMAGE_REQUEST = 1;
////    private Uri filePath;
//    private EditText editCropName, editCropDescription;
//    private ImageView imageCrop;
//
//    //    private Uri imageUri;
////    String UPLOAD_URL = "http://"+Network.IP+"/helpform.php";
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_help_form);
//
//        editCropName = findViewById(R.id.editCropName);
//        editCropDescription = findViewById(R.id.editCropDescription);
//        imageCrop = findViewById(R.id.imageCrop);
//        Button btnSelectImage = findViewById(R.id.btnSelectImage);
//        Button btnSubmit = findViewById(R.id.btnSubmit);
//
//        btnSelectImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openFilePicker();
//                Toast.makeText(getApplicationContext(), "Selecting Image", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    public void run() {
//                        if (selectedPath != null) {
//                            UploadFiles.upload(selectedPath);
//                            selectedPath = null;
//                        }
//
//                    }
//                }).start();
//                HashMap<String, String> param = new HashMap<String, String>();
//                param.put("CropName", editCropName.getText().toString());
//                param.put("CropImages", selectedPath);
//                param.put("CropDescription", editCropDescription.getText().toString());
//                StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                StrictMode.setThreadPolicy(sb);
//
//                String id = Network.connect("http://" + Network.IP + "/helpform.php", param);
//                // Convert Bitmap to Base64
//
//
//
//                if (id.equals("0")) {
//                    Toast.makeText(getApplicationContext(), " unsuccessfull", Toast.LENGTH_LONG).show();
//                } else if (!id.equals("1")) {
//                    Toast.makeText(getApplicationContext(), "successfull", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//
//        });
//    }
//
//
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            openFilePicker();
//        }
//    }
//
//    private void openFilePicker() {
//        Intent intent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, SELECT);
//    }
//
//    private void requestPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
//        } else {
//            openFilePicker();
//        }
//    }
//
//    private boolean checkpermission() {
//        int cuapi = Build.VERSION.SDK_INT;
//        if (cuapi >= Build.VERSION_CODES.M) {
//            if (ActivityCompat.checkSelfPermission(getApplicationContext(),
//                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
//                    ActivityCompat.checkSelfPermission(getApplicationContext(),
//                            android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                if (ActivityCompat.shouldShowRequestPermissionRationale(HelpForm.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
//
//                    AlertDialog.Builder albud = new AlertDialog.Builder(HelpForm.this);
//                    albud.setCancelable(true);
//                    albud.setTitle("Permission Necessary");
//                    albud.setMessage("Write Storage Permission");
//                    albud.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            ActivityCompat.requestPermissions(HelpForm.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
//                        }
//                    });
//                    AlertDialog alrt = new AlertDialog.Builder(HelpForm.this).create();
//                    alrt.show();
//
//                } else {
//                    ActivityCompat.requestPermissions(HelpForm.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
//
//                }
//                return false;
//            } else {
//
//                return true;
//            }
//
//        } else {
//
//            return true;
//        }
//
//    }
//
//    public void onActivityResult(int requestCode, int resultCode,@NonNull Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == SELECT) {
//                Uri selectedUri = data.getData();
////                Toast.makeText(getActivity(),"uri"+selectedUri,Toast.LENGTH_SHORT).show();
//                selectedPath = getPath(selectedUri);
//                System.out.println("Path : " + selectedPath);
////                tv1.setText(selectedPath);
//            }
//
//        }
//    }
//
//
//    public String getPath(Uri uri) {
//        String[] projection = {MediaStore.Video.Media.DATA};
//        Cursor cursor = HelpForm.this.managedQuery(uri, projection, null, null, null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(column_index);
//    }
//}
