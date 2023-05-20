package com.example.surveyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registration extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        progressDialog = new ProgressDialog(registration.this);

        EditText name = (EditText) findViewById(R.id.registration_name);
        EditText username = (EditText) findViewById(R.id.registration_username);
        EditText email = (EditText) findViewById(R.id.registration_email);
        EditText age = (EditText) findViewById(R.id.registration_age);
        EditText phone = (EditText) findViewById(R.id.registration_phone);
        EditText password = (EditText) findViewById(R.id.registration_password);

        Button registration_button = (Button) findViewById(R.id.Registration_btn);

        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setTitle("Loading");
                progressDialog.setMessage("Registration in progress !");
                progressDialog.show();

                String url = "https://smart-health-care-system.000webhostapp.com/survey_files/survey_register.php";

                String Name = name.getText().toString();
                String UserName = username.getText().toString();
                String Email = email.getText().toString();
                String Age = age.getText().toString();
                String Phone = phone.getText().toString();
                String Password = password.getText().toString();



                StringRequest registration_request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject server_json = new JSONObject(response);

                            String server_response = server_json.getString("response");

                            if(server_response.equals("1")){

                                progressDialog.dismiss();

                                new AlertDialog.Builder(registration.this)
                                        .setTitle("Information")
                                        .setMessage("Registration successful")
                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                Intent intent = new Intent(registration.this,selection_screen.class);
                                                finish();
                                                startActivity(intent);

                                            }
                                        })
                                        .create()
                                        .show();

                            }else if(server_response.equals("2")) {

                                progressDialog.dismiss();

                                new AlertDialog.Builder(registration.this)
                                        .setTitle("Information")
                                        .setMessage("User with these credentials already exists !")
                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                //Intent intent = new Intent(Login.this,QuistionOne.class);
                                                //startActivity(intent);

                                            }
                                        })
                                        .create()
                                        .show();




                            } else if(server_response.equals("0")) {

                                progressDialog.dismiss();

                                new AlertDialog.Builder(registration.this)
                                        .setTitle("Information")
                                        .setMessage("Please Enter All Information !")
                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                //Intent intent = new Intent(Login.this,QuistionOne.class);
                                                //startActivity(intent);

                                            }
                                        })
                                        .create()
                                        .show();




                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressDialog.dismiss();
                        new AlertDialog.Builder(registration.this)
                                .setTitle("Information")
                                .setMessage("Check Your internet Connection")
                                .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        //Intent intent = new Intent(Login.this,QuistionOne.class);
                                        //startActivity(intent);

                                    }
                                })
                                .create()
                                .show();



                    }
                }){

                    @Override
                    public Map<String, String> getParams(){

                        Map<String,String> registration_credentials = new HashMap<String, String>();


                        registration_credentials.put("name",Name);
                        registration_credentials.put("username",UserName);
                        registration_credentials.put("email",Email);
                        registration_credentials.put("age",Age);
                        registration_credentials.put("phone",Phone);
                        registration_credentials.put("password",Password);

                        return registration_credentials;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(registration.this);

                requestQueue.add(registration_request);

            }
        });
    }
}
