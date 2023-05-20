package com.example.test1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;

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
import java.util.Queue;

public class processed_appointments extends AppCompatActivity {

    List<processed_appointment_items> ArrayList;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processed_appointments);

        progressDialog = new ProgressDialog(processed_appointments.this);
        progressDialog.setTitle("Information");
        progressDialog.setMessage("Loading Please Wait");
        progressDialog.setIcon(R.drawable.ic_info_black_24dp);
        progressDialog.show();


        ArrayList = new ArrayList<>();

        url_class urlClass = new url_class();

        String URL = urlClass.url_function() + "get_approved_appointment.php";

        doctor_global_class doctorGlobalClass = doctor_global_class.getInstance();

        final int doctor_id = doctorGlobalClass.getDoctor_id();

        final RecyclerView appointmentList_recyclerView = (RecyclerView) findViewById(R.id.processed_appointment_recyclerView);



        final StringRequest processed_appointment_request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{

                    JSONArray appointment_array = new JSONArray(response);

                    for(int i = 0; i< appointment_array.length(); i++){

                        int doc_id = doctor_id;
                        String appointmentStatus = appointment_array.getJSONObject(i).getString("appointment_status");
                        String appointmentDate = appointment_array.getJSONObject(i).getString("appointment_date");
                        String appointmentTime = appointment_array.getJSONObject(i).getString("appointment_time");
                        String appointmentHospital = appointment_array.getJSONObject(i).getString("hospital");
                        String request_date = appointment_array.getJSONObject(i).getString("request_date");
                        String patientUsername = appointment_array.getJSONObject(i).getString("patient_username");
                        String doctorUsername = appointment_array.getJSONObject(i).getString("doctor_username");

                        ArrayList.add(new processed_appointment_items(doc_id,appointmentStatus,appointmentDate,appointmentTime,appointmentHospital,request_date,patientUsername,doctorUsername));

                        int check = appointment_array.length() - i;

                        if (check<=1){

                            processed_RecyclerViewAdapter processedRecyclerViewAdapter = new processed_RecyclerViewAdapter(processed_appointments.this,ArrayList);
                            appointmentList_recyclerView.setLayoutManager(new GridLayoutManager(processed_appointments.this,1));
                            appointmentList_recyclerView.setAdapter(processedRecyclerViewAdapter);

                        }


                    }


                    progressDialog.dismiss();

                }catch (Exception e){}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                AlertDialog.Builder message = new AlertDialog.Builder(processed_appointments.this);
                message.setTitle("Information");
                message.setMessage("Check Your Internet Connection !");
                message.setNegativeButton("OK",null);
                message.create();
                message.show();

            }
        }){
            @Override

            public Map<String,String> getParams(){

                Map<String,String> parameters = new HashMap<String,String>();

                parameters.put("doctor_id",String.valueOf(doctor_id));
                return parameters;
            }


        };

        RequestQueue queue = Volley.newRequestQueue(processed_appointments.this);

        queue.add(processed_appointment_request);



    }
}
