package com.example.crud_application;

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

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private EditText fullNameEditText, emailEditText, passwordEditText, idNumberTextView, phoneNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fullNameEditText = findViewById(R.id.fullnameTextView);
        emailEditText = findViewById(R.id.emailTextView);
        passwordEditText = findViewById(R.id.passwordTextView);
        idNumberTextView = findViewById(R.id.idNumberTextView);
        phoneNumberTextView = findViewById(R.id.phoneNumberTextView);

        Button signUpButton = findViewById(R.id.sign_up);
        Button signInButton = findViewById(R.id.sign_in);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void registerUser() {
        final String fullName = fullNameEditText.getText().toString().trim();
        final String email = emailEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();
        final String idNumber = idNumberTextView.getText().toString().trim();
        final String phoneNumber = phoneNumberTextView.getText().toString().trim();

       // String url = "https://192.168.254.164/PHP_FILE/register_php.php";
          String url = "https://kittle-pistols.000webhostapp.com/PHP_FILE/register_php.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RegistrationActivity.this, response, Toast.LENGTH_SHORT).show();
                        if (response.trim().equals("Register successfully!")) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }, 2000);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistrationActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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