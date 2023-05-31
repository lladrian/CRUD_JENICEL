package com.example.crud_application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.os.AsyncTask;
import android.graphics.Color;
import android.os.Bundle;
//import android.view.View;
import android.view.View;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.widget.Toast;

//import androidx.appcompat.app.AppCompatActivity;

//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import android.os.AsyncTask;
//import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;

//import androidx.appcompat.app.AppCompatActivity;

//import org.json.JSONArray;
//import org.json.JSONObject;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;







public class MainActivity extends AppCompatActivity {
   // private EditText editTextFullName, editTextEmail, editTextPassword;
    //private Button  buttonUpdate, buttonDelete;

    public static final String SHARED_PREFS = "shared_prefs";
    //key for storing email.
    public static final String EMAIL_KEY = "email_key";
    //key for storing password.

    private TextView welcomeTextView;

    SharedPreferences sharedpreferences;
    String email_shared;

    //private Button buttonSave;

    //private static final String BASE_URL = "http://192.168.254.164/PHP_FILE/fetch_all_php.php";
    private static final String BASE_URL = "https://deeyan-shoppe-net.000webhostapp.com/PHP_FILE/fetch_all_php.php";

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dataList;

    private Button logoutButton;
    String welcomeMessage;
    private FloatingActionButton float_plus_btn;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //editTextFullName = findViewById(R.id.editTextFullName);
        // editTextEmail = findViewById(R.id.editTextEmail);
        //editTextPassword = findViewById(R.id.editTextPassword);
      //  buttonSave = findViewById(R.id.buttonSave);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        float_plus_btn = findViewById(R.id.fab);


       /* buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);*/

        //buttonRetrieve = findViewById(R.id.buttonRetrieve);
        logoutButton = findViewById(R.id.logoutButton);

        //Intent intent = getIntent();
        //String email = intent.getStringExtra("email");

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        //getting data from shared prefs and storing it in our string variable.
        email_shared = sharedpreferences.getString(EMAIL_KEY, null);

        welcomeMessage = getString(R.string.welcome_message, email_shared);

        listView = findViewById(R.id.listView);
        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);



        // Choose the desired background color for each item
        //int itemBackgroundColor = Color.parseColor("#2596be"); // Example: red color
       // adapter = new CustomAdapter(this, android.R.layout.simple_list_item_1, dataList, itemBackgroundColor);
       // listView.setAdapter(adapter);

       fetchDataFromMySQL();


        // Set item click listener
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Retrieve the clicked item ID
            long clickedItemId = id;

            // Create an HTTP request to the PHP script
            OkHttpClient client = new OkHttpClient();
           String url = "https://deeyan-shoppe-net.000webhostapp.com/PHP_FILE/display_data.php";
            //String url = "https://192.168.254.164/PHP_FILE/display_data.php";

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        String responseData = response.body().string();
                        try {
                            JSONArray jsonArray = new JSONArray(responseData);
                            JSONObject clickedItem = jsonArray.getJSONObject((int) clickedItemId);
                            String idText = clickedItem.getString("id");
                            String emailText = clickedItem.getString("email");
                            String studentIDText = clickedItem.getString("studentID");
                            String fullnameText = clickedItem.getString("fullname");
                            String phoneNumberText = clickedItem.getString("phoneNumber");
                            String passwordText = clickedItem.getString("password");

                            // Perform actions with the retrieved data
                            // For example, you can update the UI on the main thread
                            runOnUiThread(() -> {
                                // Do something with the data
                                // String result = id + " - " + fullname + " - " + password;
                                Intent intent = new Intent(MainActivity.this, DetailedDataActivity.class);
                                intent.putExtra("id", idText);
                                intent.putExtra("fullname", fullnameText);
                                intent.putExtra("email", emailText);
                                intent.putExtra("studentID", studentIDText);
                                intent.putExtra("phoneNumber", phoneNumberText);
                                intent.putExtra("password", passwordText);
                                startActivity(intent);
                                //Toast.makeText(MainActivity.this, "Selected item: " + id, Toast.LENGTH_SHORT).show();

                                //Log.d("Result", result);
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.d("Response", "Unsuccessful");
                    }
                }
            });
        });


        // fetchDataFromMySQL();

       /* buttonSave.setOnClickListener(v -> {
            Intent intent1 = new Intent(MainActivity.this, InsertDataActivity.class);
            startActivity(intent1);
            finish();
        });*/

        float_plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click event
                Intent intent1 = new Intent(MainActivity.this, InsertDataActivity.class);
                startActivity(intent1);
                finish();
            }
        });

//        buttonSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle button click event
//                Intent intent1 = new Intent(MainActivity.this, InsertDataActivity.class);
//                startActivity(intent1);
//                finish();
//            }
//        });

        /*buttonUpdate.setOnClickListener(new View.OnClickListener() {
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
        });*/

    /*    buttonRetrieve.setOnClickListener(v -> {
            fetchDataFromMySQL();
        });*/

        logoutButton.setOnClickListener(v -> {
            // Display logout message
            String logoutMessage = "You logged out";
            Toast.makeText(MainActivity.this, logoutMessage, Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedpreferences.edit();
            //below line will clear the data in shared prefs.
            editor.clear();
            //below line will apply empty data to shared prefs.
            editor.apply();
            // Go back to login activity
            Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent1);
            finish();
        });
    }

    /*private void saveUser() {
        final String fullName = editTextFullName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

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
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }

        SaveUser saveUser = new SaveUser();
        saveUser.execute();
    }*/

    /*private void updateUser() {
        final String fullName = editTextFullName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        class UpdateUser extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                // URL of your PHP file for updating user data
                String url = "https://deeyan-shoppe-net.000webhostapp.com/crud_android/update_user_php.php";

                // Create the request payload
                String payload = "fullName=" + fullName + "&email=" + email + "&password=" + password;

                // Send the POST request
                return HTTPHelper.sendPostRequest(url, payload);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }

        UpdateUser updateUser = new UpdateUser();
        updateUser.execute();
    }

    private void deleteUser() {
        final String email = editTextEmail.getText().toString().trim();

        class DeleteUser extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                // URL of your PHP file for deleting a user
                String url = "https://deeyan-shoppe-net.000webhostapp.com/crud_android/delete_user_php.php";

                // Create the request payload
                String payload = "email=" + email;

                // Send the POST request
                return HTTPHelper.sendPostRequest(url, payload);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }

        DeleteUser deleteUser = new DeleteUser();
        deleteUser.execute();
    }
*/
/*

    private void retrieveUsers() {
        OkHttpClient client = new OkHttpClient();

        String url = "https://deeyan-shoppe-net.000webhostapp.com/crud_android/retrieve_users_php.php";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                final String message = "Error: " + e.getMessage();

                runOnUiThread(() -> Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected response code: " + response.code());
                    }

                    final String responseData = response.body().string();

                    runOnUiThread(() -> {
                        // Handle the response (display users, parse JSON, etc.)
                        Toast.makeText(MainActivity.this, responseData, Toast.LENGTH_SHORT).show();
                    });
                } catch (final IOException e) {
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "IO Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                } catch (final RuntimeException e) {
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Runtime Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                } finally {
                    response.close();
                }
            }
        });
    }
*/

    private void fetchDataFromMySQL() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();

                try {
                    JSONArray jsonArray = new JSONArray(jsonData);

                    // Clear the existing data
                    dataList.clear();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String data = jsonObject.getString("fullname"); // Replace "column_name" with the appropriate column name in your MySQL table
                        dataList.add(data);
                    }

                    runOnUiThread(() -> adapter.notifyDataSetChanged());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Error fetching data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (email_shared == null) {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        } else {
           // welcomeTextView.setText(welcomeMessage);
            welcomeTextView.setText("HELLO WORLD");
            welcomeTextView.requestLayout();
        }
    }

}