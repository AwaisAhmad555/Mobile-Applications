package com.example.test1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class doctor_login_panel extends AppCompatActivity {

    ProgressDialog progressDialog;

    public String doctorEmail = "";
    public String EmployeeNumber = "";
    public String EmployeeCity = "";
    public int doctor_id;


    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }




    public String getEmployeeCity() {
        return EmployeeCity;
    }

    public void setEmployeeCity(String employeeCity) {
        EmployeeCity = employeeCity;
    }



    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getEmployeeNumber() {
        return EmployeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        EmployeeNumber = employeeNumber;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login_panel);

        progressDialog =  new ProgressDialog(doctor_login_panel.this);

        final EditText doctor_password = (EditText) findViewById(R.id.doc_password);
        final EditText doctor_username = (EditText) findViewById(R.id.doctor_username);


        Button login_btn = (Button) findViewById(R.id.doc_Login_btn);

        ImageButton registration_button = (ImageButton) findViewById(R.id.doc_registration_Button);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setTitle("Doctor Login");
                progressDialog.setMessage("Login in Process Please Wait !");
                progressDialog.setIcon(R.drawable.doc3);
                progressDialog.show();

                String DoctorUsername = doctor_username.getText().toString();
                String DoctorPassword = doctor_password.getText().toString();

                DoctorLogin(DoctorUsername,DoctorPassword);



            }
        });

        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(doctor_login_panel.this,Doctor_Registration.class);
                startActivity(intent);

            }
        });

    }






    public void DoctorLogin(final String doctorUsername, final String doctorPassword) {

        url_class url_class = new url_class();

        //String Request Function to get Doctor Email address and employee_number

        String URL0 = url_class.url_function().toString() + "get_doctor_detail.php";


        StringRequest doctor_detail_request = new StringRequest(Request.Method.POST, URL0, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONArray doctor_detail_array = new JSONArray(response);

                    String doctor_email = doctor_detail_array.getJSONObject(doctor_detail_array.length()-1).getString("email");
                    String doctor_employee_no = doctor_detail_array.getJSONObject(doctor_detail_array.length()-1).getString("emp_no");
                    String doctor_city = doctor_detail_array.getJSONObject(doctor_detail_array.length()-1).getString("city");
                    String doctor_id = doctor_detail_array.getJSONObject(doctor_detail_array.length()-1).getString("doc_id");

                    /*
                    AlertDialog.Builder message =  new AlertDialog.Builder(doctor_login_panel.this);
                    message.setMessage(doctor_email + "\n" + doctor_employee_no);
                    message.setNegativeButton("OK",null);
                    message.create();
                    message.show();

                     */

                    setDoctorEmail(doctor_email);
                    setEmployeeNumber(doctor_employee_no);
                    setEmployeeCity(doctor_city);
                    setDoctor_id(Integer.parseInt(doctor_id));

                }catch (Exception e){

                    /*
                    AlertDialog.Builder message =  new AlertDialog.Builder(doctor_login_panel.this);
                    message.setMessage(e.getMessage());
                    message.setNegativeButton("OK",null);
                    message.create();
                    message.show();


                     */

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

              /*  AlertDialog.Builder message = new AlertDialog.Builder(doctor_login_panel.this);
                message.setTitle("Information !");
                message.setMessage("Check Your Internet Connection and Try Again !");
                message.setNegativeButton("OK",null);
                message.create();
                message.show();

               */

            }
        }){
            @Override
            public Map<String,String> getParams(){

                Map<String,String> doctor_detail_parameters = new HashMap<String,String>();

                doctor_detail_parameters.put("doctor_username",doctorUsername);

                return doctor_detail_parameters;
            }


        };


        String URL = url_class.url_function().toString() + "doctorLogin.php";

        StringRequest DoctorLogin_Request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

/*
                AlertDialog.Builder AlertDialog_Message1 = new AlertDialog.Builder(doctor_login_panel.this);

                AlertDialog_Message1.setTitle("Information");
                AlertDialog_Message1.setMessage(response);
                AlertDialog_Message1.create();
                AlertDialog_Message1.show();

 */


                try {

                    JSONObject server_response = new JSONObject(response);

                    String server_response_message = server_response.getString("success");

                    AlertDialog.Builder AlertDialog_Message = new AlertDialog.Builder(doctor_login_panel.this);


                    switch (server_response_message) {

                        case "0":

                            progressDialog.dismiss();

                            AlertDialog_Message.setTitle("Information");
                            AlertDialog_Message.setMessage("Please Enter All Credentials !");
                            AlertDialog_Message.setNegativeButton("OK", null);
                            AlertDialog_Message.create();
                            AlertDialog_Message.show();


                            break;

                        case "1":

                            progressDialog.dismiss();

                            AlertDialog.Builder message1 = new AlertDialog.Builder(doctor_login_panel.this);
                            message1.setTitle("Credentials");
                            message1.setMessage(getDoctorEmail() + "\n" + getEmployeeNumber() + "\n" + getEmployeeCity());
                            message1.setNegativeButton("Cancel", null);
                            message1.create();
                            message1.show();


                            AlertDialog_Message.setTitle("Login Status");
                            AlertDialog_Message.setMessage("Login Successful !");
                            AlertDialog_Message.setNegativeButton("Cancel", null);
                            AlertDialog_Message.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    doctor_global_class doctorGlobalClass = doctor_global_class.getInstance();

                                    Intent intent = new Intent(doctor_login_panel.this, doctor_main.class);
                                    intent.putExtra("username", doctorUsername);
                                    intent.putExtra("doctor_email", getDoctorEmail());
                                    intent.putExtra("employee_number", getEmployeeNumber());
                                    intent.putExtra("employee_city", getEmployeeCity());

                                    doctorGlobalClass.setDoctorUsername(doctorUsername);
                                    doctorGlobalClass.setDoctorEmail(getDoctorEmail());
                                    doctorGlobalClass.setEmployeeNumber(getEmployeeNumber());
                                    doctorGlobalClass.setEmployeeCity(getEmployeeCity());
                                    doctorGlobalClass.setDoctor_id(getDoctor_id());

                                    if (getDoctorEmail().equals("") && getEmployeeNumber().equals("")) {

                                        AlertDialog.Builder message = new AlertDialog.Builder(doctor_login_panel.this);
                                        message.setTitle("Information !");
                                        message.setMessage("Check Your Internet Connection and Try Again !");
                                        message.setNegativeButton("OK", null);
                                        message.create();
                                        message.show();

                                    } else {
                                        startActivity(intent);
                                    }

                                }
                            });
                            AlertDialog_Message.create();
                            AlertDialog_Message.show();


                            break;


                        case "2":

                            progressDialog.dismiss();

                            AlertDialog_Message.setTitle("Login Status");
                            AlertDialog_Message.setMessage("Login Failed Enter Valid Credentials !");
                            AlertDialog_Message.setNegativeButton("Ok", null);
                            AlertDialog_Message.create();
                            AlertDialog_Message.show();


                            break;

                    }


                } catch (Exception e) {


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                AlertDialog.Builder message = new AlertDialog.Builder(doctor_login_panel.this);
                message.setTitle("Information !");
                message.setMessage("Check Your Internet Connection and Try Again !");
                message.setNegativeButton("OK", null);
                message.create();
                message.show();

            }
        })

        {

            @Override
            public Map<String,String> getParams(){

                Map<String,String> LoginParameters = new HashMap<String,String>();

                LoginParameters.put("doctorUsername",doctorUsername);
                LoginParameters.put("doctorPassword",doctorPassword);

                return LoginParameters;

            }

        };


        RequestQueue requestQueue = Volley.newRequestQueue(doctor_login_panel.this);

        requestQueue.add(doctor_detail_request);


        requestQueue.add(DoctorLogin_Request);



    }

}
