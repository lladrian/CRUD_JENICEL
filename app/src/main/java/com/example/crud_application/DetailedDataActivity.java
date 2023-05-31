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


public class DetailedDataActivity extends AppCompatActivity {

    private Button  buttonUpdate, buttonDelete;
    private EditText fullnameTextView, emailTextView, passwordTextView, idTextView, idNumberTextView, phoneNumberTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_data);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String fullname = intent.getStringExtra("fullname");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");
        String studentID = intent.getStringExtra("studentID");
        String phoneNumber = intent.getStringExtra("phoneNumber");

        // Now you have the values and can use them as needed
        // You can display them or perform any other operations
        // Access the views in the layout and set the values
        fullnameTextView = findViewById(R.id.fullnameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
        idTextView = findViewById(R.id.idTextView);
        idNumberTextView = findViewById(R.id.idNumberTextView);
        phoneNumberTextView = findViewById(R.id.phoneNumberTextView);

        buttonUpdate = findViewById(R.id.update);
        buttonDelete = findViewById(R.id.delete);

        idTextView.setText(id);
        fullnameTextView.setText(fullname);
        emailTextView.setText(email);
        passwordTextView.setText(password);
        idNumberTextView.setText(studentID);
        phoneNumberTextView.setText(phoneNumber);

        // To make the EditText invisible but still take up space
        //idTextView.setVisibility(View.INVISIBLE);

        // To completely hide the EditText and free up the space it occupies
        idTextView.setVisibility(View.GONE);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });
    }

   /* private void updateUser() {
        final String fullName = fullnameTextView.getText().toString().trim();
        final String email = emailTextView.getText().toString().trim();
        final String password = passwordTextView.getText().toString().trim();
        final String id = idTextView.getText().toString().trim();


        class UpdateUser extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                // URL of your PHP file for updating user data
                String url = "https://deeyan-shoppe-net.000webhostapp.com/crud_android/update_user_php.php";

                // Create the request payload
                String payload = "fullName=" + fullName + "&email=" + email + "&password=" + password + "&id=" + id;

                // Send the POST request
                return HTTPHelper.sendPostRequest(url, payload);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(DetailedDataActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }

        UpdateUser updateUser = new UpdateUser();
        updateUser.execute();
    }

    private void deleteUser() {
        final String id = idTextView.getText().toString().trim();

        class DeleteUser extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                // URL of your PHP file for deleting a user
                String url = "https://deeyan-shoppe-net.000webhostapp.com/crud_android/delete_user_php.php";

                // Create the request payload
                String payload = "id=" + id;

                // Send the POST request
                return HTTPHelper.sendPostRequest(url, payload);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(DetailedDataActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }

        DeleteUser deleteUser = new DeleteUser();
        deleteUser.execute();
    }*/

    private void updateUser() {
        final String id = idTextView.getText().toString().trim();
        final String fullname = fullnameTextView.getText().toString().trim();
        final String email = emailTextView.getText().toString().trim();
        final String password = passwordTextView.getText().toString().trim();
        final String idNumber = idNumberTextView.getText().toString().trim();
        final String phoneNumber= phoneNumberTextView.getText().toString().trim();

        //String url = "https://192.168.254.164/PHP_FILE/update_user_php.php";
        String url = "https://kittle-pistols.000webhostapp.com/PHP_FILE/update_user_php.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailedDataActivity.this, response, Toast.LENGTH_SHORT).show();
                        if (response.trim().equals("Student data updated successfully!")) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent1 = new Intent(DetailedDataActivity.this, MainActivity.class);
                                    startActivity(intent1);
                                    finish();
                                }
                            }, 2000);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailedDataActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("fullname", fullname);
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

    private void deleteUser()  {
        final String id = idTextView.getText().toString().trim();

        //String url = "https://192.168.254.164/PHP_FILE/delete_user_php.php";
          String url = "https://kittle-pistols.000webhostapp.com/PHP_FILE/delete_user_php.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailedDataActivity.this, response, Toast.LENGTH_SHORT).show();
                        if (response.trim().equals("Student data deleted successfully!")) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent1 = new Intent(DetailedDataActivity.this, MainActivity.class);
                                    startActivity(intent1);
                                    finish();
                                }
                            }, 2000);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailedDataActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}