package com.example.test1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter_for_DoctorsPanel extends RecyclerView.Adapter<RecyclerViewAdapter_for_DoctorsPanel.MyViewHolder> {


    public Context doc_context;
    public List<recycler_items_2> doc_data;



    public RecyclerViewAdapter_for_DoctorsPanel(Context doc_context, List<recycler_items_2> doc_data) {
        this.doc_context = doc_context;
        this.doc_data = doc_data;
    }

    ProgressDialog progressDialog;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        LayoutInflater doc_inflater = LayoutInflater.from(doc_context);
        view = doc_inflater.inflate(R.layout.doctor_items,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int position) {

        myViewHolder.docter_name.setText(doc_data.get(position).getDoc_Name());
        myViewHolder.docter_type.setText(doc_data.get(position).getDoc_type());
        myViewHolder.employee_no.setText(doc_data.get(position).getEmployee_no());
        myViewHolder.doc_address.setText(doc_data.get(position).getHospital_address());
        myViewHolder.docter_email.setText(doc_data.get(position).getEmail());
        myViewHolder.doc_img.setImageResource(doc_data.get(position).getProfile_img());

        final String patient_name = doc_data.get(position).getPatient_name();
        final String patient_username = doc_data.get(position).getPatient_username();
        final String patient_email = doc_data.get(position).getPatient_email();
        final String patient_phone = doc_data.get(position).getPatient_phone();
        final String ph_no = doc_data.get(position).getPh_no();


        myViewHolder.doc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               final String Doc_Name = myViewHolder.docter_name.getText().toString();
               final String Doc_Type = myViewHolder.docter_type.getText().toString();
               final String Emp_No = myViewHolder.employee_no.getText().toString();
               final String Doc_Address = myViewHolder.doc_address.getText().toString();
               final String Doc_email = myViewHolder.docter_email.getText().toString();

                AlertDialog.Builder  msg = new AlertDialog.Builder(doc_context);

                progressDialog = new ProgressDialog(doc_context);

                msg.setMessage(Html.fromHtml("<font color='#D81B60'>Arrange Appointment With Following Information ?</font>" +"<br><br><font>" + Doc_Name + "</font><br><font>" + Doc_Type + "</font><br><font>" + Emp_No + "</font><br><font>" + Doc_Address + "</font><br><font>" + ph_no + "</font><br><font>" + Doc_email + "</font><br> <br> <font color='#D81B60'>Name: </font><font>" + patient_name + "</font><br><font color='#D81B60'>UserName: </font><font>" + patient_username + "</font><br><br><font color='#D81B60'>User_email: </font><font>" + patient_email + "</font><br><br><font color='#D81B60'>User Ph_No: </font><font>" + patient_phone +"</font>")); //"Arrange Appointment With Following Information ? );


                msg.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        progressDialog.setIcon(R.drawable.doc3);
                        progressDialog.setTitle("Information");
                        progressDialog.setMessage("Loading Please Wait !");
                        progressDialog.show();

                        final url_class url_class = new url_class();


                        String URL = url_class.url_function() + "appointment.php";
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    final String result = jsonObject.getString("result");

                                    if(result.equals("1")){
                                        progressDialog.dismiss();
                                    AlertDialog.Builder msg = new AlertDialog.Builder(doc_context);
                                    msg.setMessage("Your Appointment has been submitted Successfully\n\nWait until Appointment get Approved !");
                                    msg.setNegativeButton("OK",null);
                                    msg.create();
                                    msg.show();
                                    }

                                    if(result.equals("2")){
                                        progressDialog.dismiss();
                                        AlertDialog.Builder msg = new AlertDialog.Builder(doc_context);
                                        msg.setMessage("Your Appointment has been submitted already!\n\nWait Until your appointment get approved!");
                                        msg.setNegativeButton("OK",null);
                                        msg.create();
                                        msg.show();
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }





                            }
                        }, null){

                            @Override

                            public Map<String, String> getParams(){

                                Map<String,String> params = new HashMap<String, String>();

                                params.put("patient_name",patient_name);
                                params.put("patient_username",patient_username);
                                params.put("patient_email",patient_email);
                                params.put("doc_name",Doc_Name);
                                params.put("doc_type",Doc_Type);
                                params.put("emp_no",Emp_No);
                                params.put("doc_address",Doc_Address);
                                params.put("ph_no",ph_no);
                                params.put("doc_email",Doc_email);

                                return params;

                            }




                        };


                        //queue

                        RequestQueue queue = Volley.newRequestQueue(doc_context);
                        queue.add(stringRequest);






                    }
                });
                msg.setNegativeButton("Cancel",null);
                msg.create();
                msg.show();




            }
        });


    }

    @Override
    public int getItemCount() {

        return doc_data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView docter_name;
        TextView docter_type;
        TextView employee_no;
        TextView doc_address;
        ImageView doc_img;
        CardView doc_card;
        TextView docter_email;
        Button doc_btn;


        public MyViewHolder(View itemView){

            super(itemView);

            docter_name = (TextView) itemView.findViewById(R.id.DocName);
            docter_type = (TextView) itemView.findViewById(R.id.Doctor_type);
            employee_no = (TextView) itemView.findViewById(R.id.Emp_No);
            doc_address = (TextView) itemView.findViewById(R.id.Hospital_address);
            doc_img = (ImageView) itemView.findViewById(R.id.doc_img_logo);
            doc_card = (CardView) itemView.findViewById(R.id.doc_cardview);
            docter_email = (TextView) itemView.findViewById(R.id.doc_Email);
            doc_btn = (Button) itemView.findViewById(R.id.doc_btn);

        }


    }






}
