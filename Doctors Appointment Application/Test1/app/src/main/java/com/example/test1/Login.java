package com.example.test1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.content.DialogInterface;
import android.widget.TextView;
import android.support.v7.app.AlertDialog.Builder;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import javax.xml.namespace.QName;

public class Login extends AppCompatActivity {

    private Button Btn_login;
    private EditText Name;
    private EditText Password;
    private int counter = 5;
    private TextView info;
    private Toolbar toolbar;
    private TextView log_title;
    private TextView registration;
    public ProgressDialog progressDialog;

    public String PatientId;


    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        log_title =(TextView) findViewById(R.id.title_login);
        Btn_login = (Button)findViewById(R.id.Login_btn);
        Name = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.password);
        info = (TextView) findViewById(R.id.Info);
        info.setText("No. Of Attempts Left : " + String.valueOf(counter));

        progressDialog = new ProgressDialog(Login.this);

        registration = (TextView) findViewById(R.id.registration_link);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, user_registration.class);
                startActivity(intent);

            }
        });

        Btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //validate(Name.getText().toString(),Password.getText().toString());
                final String UserName = Name.getText().toString();
                final String UserPassword = Password.getText().toString();

                //user_detail class to store all information of user
                final user_detail detail_object =  new user_detail();

                AlertDialog.Builder test_msg = new AlertDialog.Builder(Login.this);

                //test_msg.setMessage("Click Listner is working");
               // test_msg.setNegativeButton("OK",null);
               // test_msg.create();
                //test_msg.show();

                progressDialog.setTitle("Login in Progress !");
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();


                url_class url_class = new url_class();

                final String URL = url_class.url_function() + "patient_detail_get.php";

                StringRequest detail_request =  new StringRequest(Request.Method.POST, URL,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(response);

                                    final String PatientEmail = jsonObject.getString("email");
                                    final String PatientPh_No = jsonObject.getString("ph_no");
                                    final String PatientBloodGroup = jsonObject.getString("blood_group");
                                    final String patient_id = jsonObject.getString("patient_id");


                                    //patient ID is being stored for further use in application

                                    setPatientId(patient_id);

                                    // detail_object.setUserName(UserName);
                                    detail_object.setEmail(PatientEmail);
                                    detail_object.setBloodGroup(PatientBloodGroup);
                                    detail_object.setPhone(PatientPh_No);

                                    AlertDialog.Builder msg = new AlertDialog.Builder(Login.this);

                                    msg.setMessage(UserName + "\n" + PatientEmail + "\n" + PatientBloodGroup + "\n" + PatientPh_No);
                                    msg.setNegativeButton("Cancel", null);
                                    msg.create();
                                    msg.show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder msg =  new AlertDialog.Builder(Login.this);
                        msg.setTitle("Information");
                        msg.setMessage("Check Your Internet Connection !");
                        msg.setNegativeButton("OK",null);
                        msg.create();
                        msg.show();
                        progressDialog.dismiss();
                    }
                }){

                    @Override
                    public Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<>();

                        params.put("username",UserName);

                        return params;


                    }};



                final patient_global_class patientGlobalClass = patient_global_class.getInstance();

                Response.Listener <String> responseListner = new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {

                        AlertDialog.Builder listner_msg = new AlertDialog.Builder(Login.this);

                        //listner_msg.setMessage("This is response listner" + " and response is" + response);
                        //listner_msg.setNegativeButton("ok",null);
                        //listner_msg.create();
                        //listner_msg.show();

                        try {

                            AlertDialog.Builder try_msg = new AlertDialog.Builder(Login.this);

                            //try_msg.setMessage("Try Portion is not working");
                            //try_msg.setNegativeButton("ok",null);
                            //try_msg.create();
                            //try_msg.show();


                            JSONObject jsonResponse = new JSONObject(response);

                            //JSONArray jsonArray = new JSONArray(response);

                            //JSONArray jsonArray = jsonResponse.getJSONArray("");


                            String success = jsonResponse.getString("success");

                            //final String username = jsonResponse.getString("username");


                            AlertDialog.Builder try_msg1 = new AlertDialog.Builder(Login.this);

                            //try_msg1.setMessage("Message after JSON object");
                            //try_msg1.setNegativeButton("ok",null);
                            //try_msg1.create();
                            //try_msg1.show();

                            if(success.equals("1")){

                                progressDialog.dismiss();

                                final String name = jsonResponse.getString("name");
                                final String age = jsonResponse.getString("age");
                                final String USERNAME = jsonResponse.getString("username");

                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);

                                builder.setMessage("Login Successful !")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                            final String Name = name;
                                            final String Age = age;

                                            final String User_Name = USERNAME;
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                if(detail_object.getEmail().equals(null) && detail_object.getPhone().equals(null) && detail_object.getBloodGroup().equals(null)) {

                                                    AlertDialog.Builder msg =  new AlertDialog.Builder(Login.this);
                                                    msg.setTitle("Information");
                                                    msg.setMessage("Check Your Internet Connection and Try Again!");
                                                    msg.setNegativeButton("OK",null);
                                                    msg.create();
                                                    msg.show();

                                                }else {

                                                    patientGlobalClass.setPatientName(Name);
                                                    patientGlobalClass.setUserName(User_Name);
                                                    patientGlobalClass.setUserEmail(detail_object.getEmail());
                                                    patientGlobalClass.setUserPhone(detail_object.getPhone());
                                                    patientGlobalClass.setUserBloodGroup(detail_object.getBloodGroup());
                                                    patientGlobalClass.setPatientID(Integer.parseInt(getPatientId()));

                                                    Intent intent = new Intent(Login.this, Home.class);

                                                    intent.putExtra("name", Name);
                                                    intent.putExtra("userName", User_Name);
                                                    intent.putExtra("age", Age);
                                                    intent.putExtra("email", detail_object.getEmail());
                                                    intent.putExtra("phone", detail_object.getPhone());
                                                    intent.putExtra("bloodGroup", detail_object.getBloodGroup());

                                                    startActivity(intent);
                                                }


                                            }
                                        })
                                        .create()
                                        .show();


                            } if(success.equals("0"))
                            {

                                progressDialog.dismiss();

                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);

                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();


                                counter--;

                                info.setText("No. Of Attempts Left : " + String.valueOf(counter));

                                if(counter == 0){

                                    Btn_login.setEnabled(false);
                                    registration.setEnabled(false);
                                    Name.setEnabled(false);
                                    Password.setEnabled(false);
                                    Name.setTextColor(Color.GRAY);
                                    Name.setText("--------Access Denied!-----------");
                                    Password.setText("");
                                    toolbar.setBackgroundColor(Color.RED);
                                    log_title.setText("User Validation is Expired!!!!!");
                                    Password.setHint("--------Access Denied!-----------");


                                    info.setTextColor(Color.RED);
                                    info.setText("Access Denied !!!!!!!!!!!!!!");

                                    Btn_login.setBackgroundColor(Color.RED);
                                    Btn_login.setText("Error");

                                }

                            } if (success.equals("2")){

                                progressDialog.dismiss();

                                AlertDialog.Builder msg  = new AlertDialog.Builder(Login.this);
                                msg.setMessage("Please Enter Credentials Fields !!!!");
                                msg.setNegativeButton("OK",null);
                                msg.create();
                                msg.show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);

                            builder.setMessage("Login Failed //Catch Portion" + response)
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();

                        }


                    }
                };

                final String User_Name = Name.getText().toString();

                final String URL_2 = url_class.url_function() + "login_user.php";

                StringRequest checkRequest = new StringRequest(Request.Method.POST, URL_2,
                        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // AlertDialog.Builder msg = new AlertDialog.Builder(Login.this);
                       // msg.setMessage("This is Check Request Response : " + response);
                        //msg.create();
                       // msg.show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    public Map <String, String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("user", User_Name);

                        return params;

                    }

                };




// LOGIN Volley String Request , Response Listener has been created above already

                LoginRequest loginRequest = new LoginRequest(UserName,UserPassword,responseListner);

                RequestQueue queue = Volley.newRequestQueue(Login.this);

                queue.add(detail_request);
                queue.add(loginRequest);
                queue.add(checkRequest);


            }
        });


    }

    private void validate(final String userName, String userPassword) {




        //if((userName.equals("Admin")) && (userPassword.equals("1234"))){

            //Intent intent = new Intent(Login.this, Home.class);
            //startActivity(intent);

       // } else
       // {


        //}


    }
}
