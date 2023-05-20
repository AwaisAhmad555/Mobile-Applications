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

import com.android.volley.AuthFailureError;
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

public class Login extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = (EditText) findViewById(R.id.login_username);
        EditText password = (EditText) findViewById(R.id.login_password);

        Button login_button = (Button) findViewById(R.id.Login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(Login.this);

                progressDialog.setTitle("Loading");
                progressDialog.setMessage("Please Wait Login in process !");
                progressDialog.show();
                progressDialog.setCancelable(false);

                String url = "https://smart-health-care-system.000webhostapp.com/survey_files/login.php";

                String UserName = username.getText().toString();
                String Password = password.getText().toString();



                StringRequest login_request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject server_json = new JSONObject(response);

                            String server_response = server_json.getString("response");

                            if(server_response.equals("1")){

                                progressDialog.dismiss();

                                new AlertDialog.Builder(Login.this)
                                        .setTitle("Information")
                                        .setMessage("Login successful")
                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                Intent intent = new Intent(Login.this,dashboard.class);
                                                startActivity(intent);

                                            }
                                        })
                                        .create()
                                        .show();

                            }else {

                                progressDialog.dismiss();

                                new AlertDialog.Builder(Login.this)
                                        .setTitle("Information")
                                        .setMessage("Login Failed")
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
                            progressDialog.dismiss();
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        new AlertDialog.Builder(Login.this)
                                .setTitle("Information")
                                .setMessage("Check Your Internet Connection !")
                                /*.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        //Intent intent = new Intent(Login.this,QuistionOne.class);
                                        //startActivity(intent);

                                    }
                                })

                                 */
                                .setNegativeButton("OK",null)
                                .create()
                                .show();

                    }
                }){

                    @Override
                    public Map<String, String> getParams(){

                        Map<String,String> login_credentials = new HashMap<String, String>();

                        login_credentials.put("username",UserName);
                        login_credentials.put("password",Password);

                        return login_credentials;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(Login.this);

                requestQueue.add(login_request);

            }
        });
    }
}
