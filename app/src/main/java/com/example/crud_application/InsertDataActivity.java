package com.example.crud_application;


import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class InsertDataActivity extends AppCompatActivity {

    private Button  buttonInsert;
    private EditText fullnameTextView, emailTextView, passwordTextView, idNumberTextView, phoneNumberTextView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);


        // Now you have the values and can use them as needed
        // You can display them or perform any other operations
        // Access the views in the layout and set the values
        fullnameTextView = findViewById(R.id.fullnameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
        idNumberTextView = findViewById(R.id.idNumberTextView);
        phoneNumberTextView = findViewById(R.id.phoneNumberTextView);

        buttonInsert = findViewById(R.id.insert);

        buttonInsert.setOnClickListener(v -> {
            //saveUser();
            registerUser();
           // Toast.makeText(InsertDataActivity.this, "Insert data successfully!", Toast.LENGTH_SHORT).show();
            //Intent intent1 = new Intent(InsertDataActivity.this, MainActivity.class);
            //startActivity(intent1);
            //finish();
        });

    }

       /*private void saveUser() {
        final String fullName = fullnameTextView.getText().toString().trim();
        final String email = emailTextView.getText().toString().trim();
        final String password = passwordTextView.getText().toString().trim();

        class SaveUser extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                // URL of your PHP file for saving user data
                String url = "https://deeyan-shoppe-net.000webhostapp.com/crud_android/save_user_php.php";

                // Create the request payload
                String payload = "fullName=" + fullName + "&email=" + email + "&password=" + password;

                // Send the POST request
                return HTTPHelper.sendPostRequest(url, payload);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(InsertDataActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }

        SaveUser saveUser = new SaveUser();
        saveUser.execute();
    }*/

    private void registerUser() {
        final String fullName = fullnameTextView.getText().toString().trim();
        final String email = emailTextView.getText().toString().trim();
        final String password = passwordTextView.getText().toString().trim();
        final String idNumber = idNumberTextView.getText().toString().trim();
        final String phoneNumber = phoneNumberTextView.getText().toString().trim();

          String url = "https://deeyan-shoppe-net.000webhostapp.com/PHP_FILE/register_php.php";
        //String url = "http://192.168.254.164/PHP_FILE/register_php.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(InsertDataActivity.this, response, Toast.LENGTH_SHORT).show();
                        if (response.trim().equals("Register successfully!")) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent1 = new Intent(InsertDataActivity.this, MainActivity.class);
                                    startActivity(intent1);
                                    finish();
                                }
                            }, 2000);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InsertDataActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("fullname", fullName);
                params.put("email", email);
                params.put("password", password);
                params.put("idNumber", idNumber);
                params.put("phoneNumber", phoneNumber);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




}