package com.example.test1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class appointment_date_time extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    public String appointmentDate = "";
    public String appointmentTime = "";
    public String hospital_address = "";

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_date_time);


        progressDialog = new ProgressDialog(appointment_date_time.this);

        final Intent intent =  getIntent();

        final String PatientName = intent.getExtras().getString("patientName");
        final String PatientUserName = intent.getExtras().getString("username");
        final String DoctorEmail = intent.getExtras().getString("doctorEmail");
        final String EmploymentNumber = intent.getExtras().getString("employeeNumber");
        final String RequestDate = intent.getExtras().getString("requestDate");
        final String hospitalName = intent.getExtras().getString("hospital");

        final String employeeCity = intent.getExtras().getString("employeeCity");

        final EditText userName_editText = (EditText) findViewById(R.id.appointment_user_text);
        final EditText hospital_name_editText = (EditText) findViewById(R.id.Hospital_name_text);
        final EditText hospital_address_editText = (EditText) findViewById(R.id.Hospital_Address_text);
        final EditText hospital_city_editText = (EditText) findViewById(R.id.Hospital_City_text);
        final EditText date_editText = (EditText) findViewById(R.id.date_text);
        final EditText time_editText = (EditText) findViewById(R.id.time_text);

        hospital_city_editText.setText(employeeCity);
        hospital_name_editText.setText(hospitalName);
        hospital_address_editText.setText(hospitalName);

        userName_editText.setText(intent.getExtras().getString("patientName").toString());


        Button date_button = (Button) findViewById(R.id.date_btn);
        Button time_button = (Button) findViewById(R.id.time_btn);
        final EditText time_field = (EditText) findViewById(R.id.time_text);

        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //using DatePickerFragment class we have created in our project

                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Appointment Date");

            }
        });


        time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();

                int HOUR = calendar.get(Calendar.HOUR);
                int MINUTE = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(appointment_date_time.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        String timeString = hourOfDay + " : " + minute;

                        Calendar calendar1 = Calendar.getInstance();

                        calendar1.set(Calendar.HOUR, hourOfDay);
                        calendar1.set(Calendar.MINUTE,minute);

                        String timeString1 = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar1.getTime());
                        String timeString2 = android.text.format.DateFormat.format("HH:mm:ss",calendar1.getTime()).toString();

                        setAppointmentTime(timeString2);

                        time_field.setText("   " + timeString1);
                        time_field.setTextColor(Color.rgb(216,27,96));

                    }
                },HOUR,MINUTE,false);

                timePickerDialog.show();

            }
        });

        Button submit_btn = (Button) findViewById(R.id.Submit_dateTime_btn);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                AlertDialog.Builder msg = new AlertDialog.Builder(appointment_date_time.this);
                msg.setMessage(getAppointmentDate()+"\n"+getAppointmentTime());
                msg.create();
                msg.show();

 */

                final doctor_global_class doctorGlobalClass = doctor_global_class.getInstance();

                AlertDialog.Builder message = new AlertDialog.Builder(appointment_date_time.this);
                message.setMessage(doctorGlobalClass.getDoctorUsername()+"\n"+doctorGlobalClass.getDoctorEmail()+"\n"+doctorGlobalClass.getEmployeeNumber()+"\n"+doctorGlobalClass.getEmployeeCity());
                message.create();
                message.show();

             progressDialog.setTitle("Information");
             progressDialog.setMessage("Processing Request Please Wait");
             progressDialog.setIcon(R.drawable.doc4);
             progressDialog.show();


             hospital_address = hospital_address_editText.getText().toString();
             final String hospital_city = hospital_city_editText.getText().toString();



            if(getAppointmentDate().equals("") || getAppointmentTime().equals("") || hospital_address.equals("")){

                AlertDialog.Builder msg = new AlertDialog.Builder(appointment_date_time.this);
                msg.setMessage("Please Enter All Values");
                msg.create();
                msg.show();
                progressDialog.dismiss();



            }else{

                AlertDialog.Builder confirm_message = new AlertDialog.Builder(appointment_date_time.this);
                confirm_message.setTitle("Confirmation Message!");
                confirm_message.setMessage(Html.fromHtml("<font color='#D81B60'>Arrange Appointment With Following Information ?</font>" +"<br><br><font>" + PatientName + "</font><br><font>" + PatientUserName + "</font><br><font>" + DoctorEmail + "</font><br><font>" + EmploymentNumber + "</font><br><font>" + RequestDate + "</font><br><font>" + getAppointmentDate() + "</font><br><font>" + getAppointmentTime() + "</font><br><font>" + hospitalName + "</font><br><font>" + hospital_address + "</font><br> <br> <font color='#D81B60'>Patient Name: </font><font>" + PatientName + "</font><br><font color='#D81B60'>Patient UserName: </font><font>" + PatientUserName + "</font><br><br><font color='#D81B60'>Your Email Address: </font><font>" + DoctorEmail + "</font><br><br><font color='#D81B60'>Your Employee_No: </font><font>" + EmploymentNumber +"</font>"));

                 //"Arrange Appointment With Following Information ? );

                confirm_message.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//Arranging appointment Using Volley String Request

                        url_class url_class = new url_class();

                        String URL = url_class.url_function() + "appointment_processed.php";

                        final StringRequest appointment_process_request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                        /*
                        AlertDialog.Builder msg = new AlertDialog.Builder(appointment_date_time.this);
                        msg.setMessage(response);
                        msg.create();
                        msg.show();

                         */



                                try{

                                    JSONObject check_result_status = new JSONObject(response);

                                    String check_result_message = check_result_status.getString("success");

                                    AlertDialog.Builder message = new AlertDialog.Builder(appointment_date_time.this);
                                    message.setTitle("Information");

                                    switch (check_result_message) {

                                        case "0":

                                            progressDialog.dismiss();
                                            message.setMessage("Appointment Approval Failed! Please Try Again");
                                            message.setNegativeButton("Ok",null);

                                            break;


                                        case "1":

                                            progressDialog.dismiss();
                                            message.setMessage("Appointment has been arranged successfully !");
                                            //message.setNegativeButton("Cancel",null);
                                            message.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    Intent intent1 = new Intent(appointment_date_time.this,doctor_main.class);
                                                    intent1.putExtra("username", doctorGlobalClass.getDoctorUsername());
                                                    intent1.putExtra("doctor_email", doctorGlobalClass.getDoctorEmail());
                                                    intent1.putExtra("employee_number", doctorGlobalClass.getEmployeeNumber());
                                                    intent1.putExtra("employee_city", doctorGlobalClass.getEmployeeCity());

                                                    startActivity(intent1);

                                                }
                                            });

                                            break;


                                        case "2":

                                            progressDialog.dismiss();
                                            message.setMessage("Please Select Coming Date instead of Past date !");
                                            message.setNegativeButton("Ok",null);


                                            break;


                                        case "3":

                                            progressDialog.dismiss();
                                            message.setMessage("Please Insert All Values");
                                            message.setNegativeButton("Ok",null);


                                            break;

                                        case "4":

                                            progressDialog.dismiss();
                                            message.setMessage("Appointment is already existed in appointment schedule");
                                            message.setNegativeButton("Ok",null);

                                            break;



                                    }

                                    message.create();
                                    message.show();



                                }catch(Exception e){

                                    progressDialog.dismiss();

                                    AlertDialog.Builder message = new AlertDialog.Builder(appointment_date_time.this);
                                    message.setMessage(e.getMessage());
                                    message.setNegativeButton("OK",null);
                                    message.create();
                                    message.show();

                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                AlertDialog.Builder message = new AlertDialog.Builder(appointment_date_time.this);
                                message.setTitle("Information");
                                message.setMessage("Check Your Internet Connection and try Again");
                                message.setNegativeButton("OK",null);
                                message.create();
                                message.show();
                                progressDialog.dismiss();

                            }
                        }){
                            @Override
                            public Map<String,String> getParams(){

                                Map<String,String> appointment_process_parameters = new HashMap<String,String>();

                                appointment_process_parameters.put("appointment_status","Approved");
                                appointment_process_parameters.put("PatientName",PatientName);
                                appointment_process_parameters.put("PatientUserName",PatientUserName);
                                appointment_process_parameters.put("DoctorEmail",DoctorEmail);
                                appointment_process_parameters.put("EmploymentNumber",EmploymentNumber);
                                appointment_process_parameters.put("RequestDate",RequestDate);
                                appointment_process_parameters.put("appointmentDate",getAppointmentDate());
                                appointment_process_parameters.put("appointmentTime",getAppointmentTime());

                                appointment_process_parameters.put("HospitalName",hospitalName);
                                appointment_process_parameters.put("HospitalAddress",hospital_address);
                                appointment_process_parameters.put("HospitalCity",employeeCity);

                                return appointment_process_parameters;
                            };


                        };

                        RequestQueue requestQueue = Volley.newRequestQueue(appointment_date_time.this);
                        requestQueue.add(appointment_process_request);




                    }
                });

                confirm_message.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        progressDialog.dismiss();

                    }
                });
                confirm_message.create();
                confirm_message.show();



            }

            }
        });



    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        setAppointmentDate(String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year));

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        EditText dateText = (EditText) findViewById(R.id.date_text);

        dateText.setText("   " + currentDateString);

        dateText.setTextColor(Color.rgb(216,27,96));

    }
}
