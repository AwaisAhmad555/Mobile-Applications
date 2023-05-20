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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Doctor_Section extends AppCompatActivity {

    List<recycler_items_2> lst_items;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__section);

        progressDialog = new ProgressDialog(Doctor_Section.this);
        progressDialog.setIcon(R.drawable.doc2);
        progressDialog.setTitle("Information");
        progressDialog.setMessage("Loading Please Wait!");
        progressDialog.show();

        Intent intent = getIntent();

        final String patient_Name = intent.getExtras().getString("patientName");
        final String patient_UserName = intent.getExtras().getString("patient_username");
        final String patient_Email = intent.getExtras().getString("patient_email");
        final String patient_Phone = intent.getExtras().getString("patient_phone");

        AlertDialog.Builder message = new AlertDialog.Builder(Doctor_Section.this);
        message.setMessage(patient_Email + "\n" + patient_Phone);
        message.create();
        message.show();


        Response.Listener<String> responseListner = new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {

                final String serverMsg = response;


                try {

                    TextView title_txt = (TextView) findViewById(R.id.title_Doctor);

                    title_txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder msg = new AlertDialog.Builder(Doctor_Section.this);
                            msg.setMessage(response);
                            msg.setNegativeButton("OK",null);
                            msg.create();
                            msg.show();
                        }
                    });

                    //JSONObject jsonObject = new JSONObject(response);



                    final JSONArray jsonArray = new JSONArray(response);

                    TextView title = (TextView) findViewById(R.id.title_Doctor);
                    lst_items = new ArrayList<>();

                        int i;

                        for(i=0;i<jsonArray.length();i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            final String doc_name = jsonObject.getString("doc_name");
                            final String doc_type = jsonObject.getString("doc_type");
                            final String emp_no = jsonObject.getString("emp_no");
                            final String hospital = jsonObject.getString("hospital");
                            final String email = jsonObject.getString("email");
                            final String ph_no = jsonObject.getString("ph_no");




                            lst_items.add(new recycler_items_2(doc_name.toString(), doc_type, R.drawable.doc4, emp_no, hospital, email,patient_Name,patient_UserName, ph_no,patient_Email,patient_Phone));

                            int check = jsonArray.length()- i;

                            if(check <= 1){
                            RecyclerView doc_RV = (RecyclerView) findViewById(R.id.doctor_recyclerView);
                            RecyclerViewAdapter_for_DoctorsPanel doc_adapter = new RecyclerViewAdapter_for_DoctorsPanel(Doctor_Section.this, lst_items);

                            doc_RV.setLayoutManager(new GridLayoutManager(Doctor_Section.this, 1));
                            doc_RV.setAdapter(doc_adapter);

                            }



                        }


                        progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();

                    AlertDialog.Builder message = new AlertDialog.Builder(Doctor_Section.this);
                    message.setTitle("Information");
                    message.setMessage(e.getMessage());
                    message.create();
                    message.show();

                }

            }
        };






        doctorRequest doctorRequest = new doctorRequest(responseListner);

        RequestQueue queue = Volley.newRequestQueue(Doctor_Section.this);
        queue.add(doctorRequest);



        TextView txt = (TextView) findViewById(R.id.title_Doctor);

        //txt.setText(patient_name + " - " + patient_UserName);

    }




}
