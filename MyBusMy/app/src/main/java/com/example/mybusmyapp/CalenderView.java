//package com.example.mybusmyapp;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
//import android.widget.CalendarView;
//import android.widget.Toast;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//
//public class CalenderView extends AppCompatActivity {
//
//    private List<String> studentDataList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calender_view);
//
//
//        CalendarView calendarView = findViewById(R.id.calendarView);
//
//        studentDataList = fetchPresentDates();
////        Calendar calendar = Calendar.getInstance();
////        calendar.set(year, month, dayOfMonth);
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @SuppressLint("ResourceType")
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(year, month, dayOfMonth);
//                String selectedDateString = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime());
//
//                Drawable backgroundDrawable;
//                if (studentDataList.contains(selectedDateString)) {
//                    // Present student: Set green circle background
//                    backgroundDrawable = getResources().getDrawable(R.drawable.present_date_color);
//                } else {
//                    // Absent student: Set red circle background
//                    backgroundDrawable = getResources().getDrawable(R.drawable.absent_date_color);
//                }
//                view.setBackgroundDrawable(backgroundDrawable);
//
//            }
//        });
//
//    }
//    private void updateDateBackground(CalendarView view, String dateString) {
//        Drawable backgroundDrawable;
//        if (studentDataList.contains(dateString)) {
//            // Present student: Set green circle background
//            backgroundDrawable = getResources().getDrawable(R.drawable.present_date_circle);
//        } else {
//            // Absent student: Set red circle background
//            backgroundDrawable = getResources().getDrawable(R.drawable.absent_date_circle);
//        }
//        view.setBackgroundDrawableForDate(parseDateString(dateString), backgroundDrawable);
//    }
//
//    private List<String> fetchPresentDates() {
//        HashMap<String, String> param = new HashMap<String, String>();
//        String id = getIntent().getStringExtra("studentid");
//        param.put("studentid", id);
//        String response = Network.connect("http://" + Network.IP + "/showattenndanceforstudent.php", param);
//
//        if (response.equals("0")) {
////            textView.setText("No Record Available");
//            Toast.makeText(this, "No Record Available", Toast.LENGTH_SHORT).show();
//        } else if (!response.equals("0")) {
//            List<String> students = Arrays.asList(response.trim().split("#"));
//
//             studentDataList = new ArrayList<>();
//            System.out.println(students+"student");
//
//            System.out.println(students);
//            for (String busString : students) {
//                String[] busData = busString.trim().split("<br>"); // Assuming each bus data string is comma-separated
//                studentDataList.add(busData[0]);
//            }
//            System.out.println(studentDataList+"in");
//        }
//        System.out.println(studentDataList+"out");
//
//        return studentDataList;
//    }
//
//}