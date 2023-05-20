package com.example.test1;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Doctor_Registration extends AppCompatActivity {

    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__registration);

        progressDialog = new ProgressDialog(Doctor_Registration.this);



        Button registration_btn = (Button) findViewById(R.id.doctor_register_btn);


        registration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 EditText doctor_name = (EditText) findViewById(R.id.DoctorName_text);
                 EditText doctor_username = (EditText) findViewById(R.id.DoctorUserName_text);
                 EditText doctor_email = (EditText) findViewById(R.id.doctor_email_text);
                 EditText doctor_type = (EditText) findViewById(R.id.DoctorType_text);
                 EditText Employee_number = (EditText) findViewById(R.id.Employee_Number_text);
                 EditText doctor_Hospital = (EditText) findViewById(R.id.Hospital_address_text);
                 EditText doctor_PhoneNumber = (EditText) findViewById(R.id.Doctor_phoneNumber_text);
                 EditText doctor_city = (EditText)  findViewById(R.id.Doctor_City_text);
                 EditText doctor_password = (EditText) findViewById(R.id.Doctor_Password);

                final String doctorName = doctor_name.getText().toString();
                final String doctorUsername = doctor_username.getText().toString();
                final String doctorEmail = doctor_email.getText().toString();
                final String doctorType = doctor_type.getText().toString();
                final String EmployeeNumber = Employee_number.getText().toString();
                final String doctorHospital = doctor_Hospital.getText().toString();
                final String doctorPhoneNumber = doctor_PhoneNumber.getText().toString();
                final String doctorCity = doctor_city.getText().toString();
                final String doctorPassword = doctor_password.getText().toString();


                AlertDialog.Builder message1 = new AlertDialog.Builder(Doctor_Registration.this);
                message1.setMessage(Html.fromHtml("<font color='#D81B60'>Arrange Appointment With Following Information ?</font>" +"<br><br><font>" + doctorName + "</font><br><font>" + doctorUsername + "</font><br><font>" + doctorEmail + "</font><br><font>" + doctorType + "</font><br><font>" + EmployeeNumber + "</font><br><font>" + doctorHospital + "</font><br> <br> <font color='#D81B60'>Phone Number: </font><font>" + doctorPhoneNumber + "</font><br><br><font color='#D81B60'>City: </font><font>" + doctorCity + "</font><br><br><font color='#D81B60'>Password: </font><font>" + doctorPassword + "</font><br><br><font color='#D81B60'>Email: </font><font>" + doctorEmail +"</font>")); //"Arrange Appointment With Following Information ? );
                message1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        progressDialog.setTitle("Doctor Registration!");
                        progressDialog.setMessage("Registration in Progress Please Wait !!!");
                        progressDialog.setIcon(R.drawable.doc4);
                        progressDialog.show();
                        registerDoctor(doctorName,doctorUsername,doctorEmail,doctorType,EmployeeNumber,doctorHospital,doctorPhoneNumber,doctorCity,doctorPassword);

                    }
                });
                message1.setNegativeButton("Cancel",null);
                message1.create();
                message1.show();

            }
        });



    }

    public void registerDoctor(final String doctorName, final String doctorUsername, final String doctorEmail, final String doctorType, final String employeeNumber, final String doctorHospital, final String doctorPhoneNumber, final String doctorCity, final String doctorPassword) {

        url_class url_class =  new url_class();

        String url = url_class.url_function() + "doctor_registration.php";

        StringRequest doctorRegister_Request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                AlertDialog.Builder message1 = new AlertDialog.Builder(Doctor_Registration.this);
                message1.setMessage(response);
                message1.setNegativeButton("OK",null);
                message1.create();
                message1.show();

                try {

                    JSONObject Server_Response_jsonObject = new JSONObject(response);

                    String Server_Response = Server_Response_jsonObject.getString("success");

                    if(Server_Response.equals("0")){

                        progressDialog.dismiss();

                        AlertDialog.Builder message = new AlertDialog.Builder(Doctor_Registration.this);
                        message.setMessage("Please Enter All Fields Before Submission");
                        message.setNegativeButton("OK",null);
                        message.create();
                        message.show();

                    }

                    else if(Server_Response.equals("2")){

                        progressDialog.dismiss();

                        AlertDialog.Builder message = new AlertDialog.Builder(Doctor_Registration.this);
                        message.setMessage("Another User with this Information is Already Registered !");
                        message.setNegativeButton("OK",null);
                        message.create();
                        message.show();


                    }
                    else if(Server_Response.equals("1")){

                        progressDialog.dismiss();

                        AlertDialog.Builder message = new AlertDialog.Builder(Doctor_Registration.this);
                        message.setMessage("Registration Successfull");
                        message.setNegativeButton("OK",null);
                        message.create();
                        message.show();

                    }

                }catch (Exception e){

                    progressDialog.dismiss();

                    AlertDialog.Builder message = new AlertDialog.Builder(Doctor_Registration.this);
                    message.setMessage(e.getMessage());
                    message.setNegativeButton("OK",null);
                    message.create();
                    message.show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override

            public Map<String,String> getParams(){

                Map<String,String> Post_Parameters = new HashMap<String,String>();

                Post_Parameters.put("doctor_name",doctorName);
                Post_Parameters.put("doctor_username",doctorUsername);
                Post_Parameters.put("doctor_email",doctorEmail);
                Post_Parameters.put("doctor_type",doctorType);
                Post_Parameters.put("Employee_number",employeeNumber);
                Post_Parameters.put("doctor_Hospital",doctorHospital);
                Post_Parameters.put("doctor_PhoneNumber",doctorPhoneNumber);
                Post_Parameters.put("doctor_city",doctorCity);
                Post_Parameters.put("doctor_password",doctorPassword);



                return Post_Parameters;
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(Doctor_Registration.this);
        requestQueue.add(doctorRegister_Request);

    }
}
