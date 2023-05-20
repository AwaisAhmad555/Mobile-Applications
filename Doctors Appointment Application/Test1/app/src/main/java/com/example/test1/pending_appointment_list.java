package com.example.test1;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pending_appointment_list extends AppCompatActivity {

    public List<appointment_request_items> ArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_appointment_list);

        Intent intent = getIntent();

        final String doctor_email = intent.getExtras().getString("doctorEmail");
        final String employee_number = intent.getExtras().getString("employeeNumber");
        final String employee_city = intent.getExtras().getString("employeeCity");


        AlertDialog.Builder message =  new AlertDialog.Builder(pending_appointment_list.this);
        message.setMessage(doctor_email + "\n" +employee_number);
        message.setNegativeButton("OK",null);
        message.create();
        message.show();

        url_class url_class = new url_class();

        String URL = url_class.url_function().toString() + "pending_appointment_get.php";

        final StringRequest pending_appointment_request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

/*
                AlertDialog.Builder message1 = new AlertDialog.Builder(pending_appointment_list.this);
                message1.setMessage(response);
                message1.create();
                message1.show();

 */

                ArrayList = new ArrayList<>();

                try{

                    int i;
                    JSONArray pending_appointment_array = new JSONArray(response);

                    for(i = 0;i <= pending_appointment_array.length()-1;i++){

                        JSONObject pending_appointment_object = pending_appointment_array.getJSONObject(i);

                        String patient_name = pending_appointment_object.getString("patient");
                        String patient_username  = pending_appointment_object.getString("patient_username");
                        String patient_email  = pending_appointment_object.getString("patient_email");
                        String doctor_name  = pending_appointment_object.getString("doctor_name");
                        String doc_type = pending_appointment_object.getString("doc_type");
                        String emp_no = pending_appointment_object.getString("emp_no");
                        String doc_email = pending_appointment_object.getString("doc_email");
                        String ph_no = pending_appointment_object.getString("ph_no");
                        String hospital = pending_appointment_object.getString("hospital");
                        String appointment_date = pending_appointment_object.getString("appointment_date");
                        String from_time = pending_appointment_object.getString("from_time");
                        String to_time = pending_appointment_object.getString("to_time");
                        String request_date = pending_appointment_object.getString("request_date");


                        ArrayList.add(new appointment_request_items(doc_email,emp_no,patient_name,patient_username,patient_email,request_date,from_time + "" + to_time,"Pending",hospital,employee_city));

                        int check = pending_appointment_array.length() - i;
                        if(check <=1) {

                            RecyclerView pending_appointment_recyclerView = (RecyclerView) findViewById(R.id.pending_appointment_recyclerView);


                            appointment_request_recyclerView_adapter appointment_request_recyclerView_adapter = new appointment_request_recyclerView_adapter(pending_appointment_list.this,ArrayList);

                            pending_appointment_recyclerView.setLayoutManager(new GridLayoutManager(pending_appointment_list.this,1));

                            pending_appointment_recyclerView.setAdapter(appointment_request_recyclerView_adapter);



                        }

                    }


                }catch (Exception e){

                    AlertDialog.Builder message = new AlertDialog.Builder(pending_appointment_list.this);

                    message.setMessage(e.getMessage());
                    message.create();
                    message.show();

                }


               // ArrayList.add(new appointment_request_items("UserName 1","UserEmail 1","Date 1","Time 1","Pending"));





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            public Map<String,String> getParams()
            {
                Map<String, String> doctor_parameters = new HashMap<String, String>();

                doctor_parameters.put("doctor_email",doctor_email);
                doctor_parameters.put("employee_no",employee_number);

                return doctor_parameters;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(pending_appointment_list.this);
        queue.add(pending_appointment_request);


    }
}
