package com.example.test1;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

public class patient_personal_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_personal_detail);


        Intent intent = getIntent();

        final String Patient_name = intent.getExtras().getString("patientName");
        final String Patient_username = intent.getExtras().getString("patient_username");

        EditText patient_Name = (EditText) findViewById(R.id.patient_name_text);
        EditText patient_UserName = (EditText) findViewById(R.id.patient_username_text);
        final EditText patient_email = (EditText)  findViewById(R.id.patient_email_edit);
        final EditText patient_ph_no = (EditText)  findViewById(R.id.patient_ph_no_edit);
        final EditText patient_bloodGroup = (EditText)  findViewById(R.id.bloodgroup_edit);

        final String Email = patient_email.getText().toString();
        final String Ph_No =  patient_ph_no.getText().toString();
        final String BloodGroup = patient_bloodGroup.getText().toString();

        Button update_btn = (Button) findViewById(R.id.Update_btn);



        patient_Name.setText(Patient_name);
        patient_UserName.setText(Patient_username);

        patient_Name.setTextColor(Color.rgb(216,27,96));
        patient_UserName.setTextColor(Color.rgb(216,27,96));



        patient_Name.setEnabled(false);
        patient_UserName.setEnabled(false);

        final url_class url_class = new url_class();
        final String URL_2 = url_class.url_function() + "patient_detail_get.php";

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    final String PatientEmail = jsonObject.getString("email");
                    final String PatientPh_No = jsonObject.getString("ph_no");
                    final String PatientBloodGroup = jsonObject.getString("blood_group");

                    patient_email.setText(PatientEmail);
                    patient_ph_no.setText(PatientPh_No);
                    patient_bloodGroup.setText(PatientBloodGroup);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            public Map<String,String> getParams(){

                Map<String,String> params = new HashMap<>();

                params.put("username",Patient_username);

                return params;
            }

        };

        RequestQueue queue = Volley.newRequestQueue(patient_personal_detail.this);
        queue.add(stringRequest);


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Email_new = patient_email.getText().toString();
                final String Ph_No_new = patient_ph_no.getText().toString();
                final String Blood_Group_new = patient_bloodGroup.getText().toString();


                final url_class url_class = new url_class();

                final String URL = url_class.url_function() + "update_patient_detail.php";

                StringRequest update_request =  new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            final String success =  jsonObject.getString("success");

                            if(success.equals("1")){

                                AlertDialog.Builder msg = new AlertDialog.Builder(patient_personal_detail.this);

                                msg.setMessage("Detail Updated Successfully !");
                                msg.setNegativeButton("OK",null);
                                msg.create();
                                msg.show();


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })

                {

                    @Override
                    public Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<>();

                        params.put("username",Patient_username);
                        params.put("email",Email_new);
                        params.put("ph_no",Ph_No_new);
                        params.put("blood_group",Blood_Group_new);

                        return params;
                    }



                };

                RequestQueue queue = Volley.newRequestQueue(patient_personal_detail.this);
                queue.add(update_request);
                queue.add(stringRequest);

            }
        });


    }



}
