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

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EMAIL_KEY = "email_key";
    public static final String PASSWORD_KEY = "password_key";


    SharedPreferences sharedpreferences;

    String email_shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);


        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        email_shared = sharedpreferences.getString(EMAIL_KEY, null);


        Button buttonSignIn = findViewById(R.id.buttonSignIn);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loginUser() {
        final String email = emailEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();

        //String url = "https://192.168.254.164/PHP_FILE/login_php.php";
          String url = "https://deeyan-shoppe-net.000webhostapp.com/PHP_FILE/login_php.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();

                        if (response.trim().equals("Login successfully!")) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString(EMAIL_KEY, emailEditText.getText().toString());
                                editor.apply();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("email", email);
                                startActivity(intent);
                            }
                        }, 2000);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (email_shared != null) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
    }
}