package com.example.test1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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

public class appointment extends AppCompatActivity {

    List<appointment_items> appointment_list;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        progressDialog = new ProgressDialog(appointment.this);
        progressDialog.setTitle("Information");
        progressDialog.setMessage("Loading Please Wait !");
        progressDialog.setIcon(R.drawable.ic_info_black_24dp);
        progressDialog.show();


        Intent intent = getIntent();

        final String patient_username = intent.getExtras().getString("patient_username");
        final String patient_Name = intent.getExtras().getString("patientName");


        url_class url_class = new url_class();
        final String URL;

        URL = url_class.url_function() + "appointment_get.php";
        //String URL = "http://smart-health-care-system.000webhostapp.com/appointment_get.php";

        StringRequest request = new StringRequest(Request.Method.POST, URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {


                        try {

                            TextView title_txt = (TextView) findViewById(R.id.title_Appointment);

                            title_txt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    AlertDialog.Builder msg = new AlertDialog.Builder(appointment.this);
                                    msg.setMessage(response);
                                    msg.setNegativeButton("OK",null);
                                    msg.create();
                                    msg.show();


                                }
                            });

                            JSONArray jsonArray = new JSONArray(response);

                            appointment_list = new ArrayList<>();

                            final String jsonString = jsonArray.toString();



                            int i;

                            for(i=0;i<jsonArray.length();i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                final String patient = jsonObject.getString("patient");
                                final String patient_username = jsonObject.getString("patient_username");
                                final String patient_email = jsonObject.getString("patient_email");
                                final String doctor_name = jsonObject.getString("doctor_name");
                                final String doc_type = jsonObject.getString("doc_type");
                                final String emp_no = jsonObject.getString("emp_no");
                                final String doc_email = jsonObject.getString("doc_email");
                                final String ph_no = jsonObject.getString("ph_no");
                                final String hospital = jsonObject.getString("hospital");
                                final String appointment_date = jsonObject.getString("appointment_date");
                                final String from_time = jsonObject.getString("from_time");
                                final String to_time = jsonObject.getString("to_time");
                                final String request_date = jsonObject.getString("request_date");


                                if (i % 2 == 0) {


                                appointment_list.add(new appointment_items(doctor_name, R.drawable.doc2, doc_type, "Pending", from_time + " " + to_time, appointment_date));

                            }

                                else{

                                    appointment_list.add(new appointment_items(doctor_name, R.drawable.doc4, doc_type, "Pending", from_time + " " + to_time, appointment_date));

                                }


                                int check = jsonArray.length() - i;
                                if(check <=1) {
                                    RecyclerView ap_rv = (RecyclerView) findViewById(R.id.appointment_recycler);
                                    appointment_RecyclerViewAdapter adapter = new appointment_RecyclerViewAdapter(appointment.this, appointment_list);
                                    ap_rv.setLayoutManager(new GridLayoutManager(appointment.this, 1));
                                    ap_rv.setAdapter(adapter);
                                }


                            }





                            progressDialog.dismiss();


                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }




                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressDialog.dismiss();

                        AlertDialog.Builder message =  new AlertDialog.Builder(appointment.this);
                        message.setIcon(R.drawable.ic_info_black_24dp);
                        message.setTitle("Information");
                        message.setMessage("Check Your Internet Connection And Try Again");
                        message.create();
                        message.show();


                    }
                }){     @Override
        public Map <String, String> getParams(){
            Map<String,String> params = new HashMap<String, String>();

            params.put("patientName",patient_Name);
            params.put("patient_username",patient_username);

            return params;
        }
        };




        RequestQueue queue = Volley.newRequestQueue(appointment.this);
        queue.add(request);







    }
}






