package com.example.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class patient_processed_appointments extends AppCompatActivity {

    List<processed_appointment_items> ArrayList;

    patient_global_class patientGlobalClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_processed_appointments);

        patientGlobalClass = patient_global_class.getInstance();

        final int patientID = patientGlobalClass.getPatientID();

        ArrayList = new ArrayList<>();

        final RecyclerView appointment_recyclerView = (RecyclerView) findViewById(R.id.patient_processed_appointment_recyclerView);

        url_class urlClass =  new url_class();

        String URL = urlClass.url_function() + "patient_approved_appointments.php";

        StringRequest appointment_request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{

                    JSONArray appointmentArray = new JSONArray(response);

                    for(int i = 0; i<appointmentArray.length();i++){

                        String appointmentStatus = appointmentArray.getJSONObject(i).getString("appointment_status");
                        String appointmentDate = appointmentArray.getJSONObject(i).getString("appointment_date");
                        String appointmentTime = appointmentArray.getJSONObject(i).getString("appointment_time");
                        String appointmentHospital = appointmentArray.getJSONObject(i).getString("hospital");
                        String request_date = appointmentArray.getJSONObject(i).getString("request_date");
                        String patientUsername = appointmentArray.getJSONObject(i).getString("patient_username");
                        String doctorUsername = appointmentArray.getJSONObject(i).getString("doctor_username");

                        ArrayList.add(new processed_appointment_items(patientID,appointmentStatus,appointmentDate,appointmentTime,appointmentHospital,request_date,patientUsername,doctorUsername));

                        int check = appointmentArray.length() - i;

                        if(check<=1){

                            processed_RecyclerViewAdapter processedRecyclerViewAdapter = new processed_RecyclerViewAdapter(patient_processed_appointments.this,ArrayList);

                            appointment_recyclerView.setLayoutManager(new GridLayoutManager(patient_processed_appointments.this,1));

                            appointment_recyclerView.setAdapter(processedRecyclerViewAdapter);

                        }


                    }


                }catch (Exception e){}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override

            public Map<String,String> getParams(){

                Map<String,String> parameters = new HashMap<String,String>();

                parameters.put("patient_id",String.valueOf(patientID));

                return parameters;
            }

        };


        RequestQueue queue = Volley.newRequestQueue(patient_processed_appointments.this);
        queue.add(appointment_request);



    }
}
