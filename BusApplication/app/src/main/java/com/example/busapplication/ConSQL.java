package com.example.busapplication;//package com.example.mybusmyapp;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//
//public class ConSQL {
//        // Instantiate the RequestQueue.
//// Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://your_domain/api.php";
//
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                                // Handle the JSON response
//                                try {
//                                        JSONArray jsonArray = new JSONArray(response);
//                                        // Process JSON data here
//                                } catch (JSONException e) {
//                                        e.printStackTrace();
//                                }
//                        }
//                }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                        // Handle errors
//                }
//        });
//
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//}