package com.example.test1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class appointment_request_recyclerView_adapter extends RecyclerView.Adapter<appointment_request_recyclerView_adapter.MyViewHolder> {

    public Context Activity_Context;
    public List<appointment_request_items> ArrayList;
    ProgressDialog progressDialog;


    public appointment_request_recyclerView_adapter(Context activity_Context, List<appointment_request_items> arrayList) {
        Activity_Context = activity_Context;
        ArrayList = arrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view;

        LayoutInflater appointment_request_layout_inflater = LayoutInflater.from(Activity_Context);
        view = appointment_request_layout_inflater.inflate(R.layout.appointment_request_items,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        myViewHolder.user_username.setText(ArrayList.get(position).user_username);
        myViewHolder.user_email.setText(ArrayList.get(position).user_email);
        myViewHolder.appointment_status.setText(ArrayList.get(position).appointment_status);
        myViewHolder.appointment_date.setText(ArrayList.get(position).appointment_date);
        myViewHolder.appointment_time.setText(ArrayList.get(position).appointment_time);

        final String UserName = ArrayList.get(position).getUser_username();
        final String PatientName = ArrayList.get(position).getPatient_name();
        final String DoctorEmail = ArrayList.get(position).getDoctor_email();
        final String EmployeeNumber = ArrayList.get(position).getEmployee_number();
        final String Request_date = ArrayList.get(position).getAppointment_date();
        final String Hospital = ArrayList.get(position).getHospital();
        final String EmployeeCity = ArrayList.get(position).getEmployee_city();

        myViewHolder.approve_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder msg = new AlertDialog.Builder(Activity_Context);
                msg.setMessage("Please Select the appointment Date, time and Address !");
                msg.setTitle("Information");
                msg.setIcon(R.drawable.city_img);
                msg.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Activity_Context,appointment_date_time.class);
                        intent.putExtra("username",UserName);
                        intent.putExtra("patientName",PatientName);
                        intent.putExtra("doctorEmail",DoctorEmail);
                        intent.putExtra("employeeNumber",EmployeeNumber);
                        intent.putExtra("requestDate",Request_date);
                        intent.putExtra("hospital",Hospital);
                        intent.putExtra("employeeCity",EmployeeCity);
                        Activity_Context.startActivity(intent);

                    }
                });
                msg.setNegativeButton("Cancel",null);
                msg.create();
                msg.show();

            }
        });

        myViewHolder.reject_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(Activity_Context);
                progressDialog.setIcon(R.drawable.doc2);
                progressDialog.setTitle("Loading");
                progressDialog.setMessage("Please Wait While Your Request is under Process !");
                progressDialog.show();

                AlertDialog.Builder message =  new AlertDialog.Builder(Activity_Context);

                message.setTitle("Information");
                message.setMessage("Are you sure to Delete the appointment of User " + UserName);
                message.setIcon(R.drawable.ic_info_black_24dp);
                message.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        url_class urlClass = new url_class();

                        String URL = urlClass.url_function() + "delete_appointment.php";

                        StringRequest deleteAppointment_request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                AlertDialog.Builder message1 =  new AlertDialog.Builder(Activity_Context);
                                message1.setMessage(response);
                                message1.create();
                                message1.show();
                                try{

                                    JSONObject responseObject =  new JSONObject(response);

                                    String jsonResponse = responseObject.getString("success");

                                    AlertDialog.Builder message = new AlertDialog.Builder(Activity_Context);
                                    message.setTitle("Information");
                                    //message.setNegativeButton("OK",null);
                                    message.setIcon(R.drawable.ic_info_black_24dp);

                                    if(jsonResponse.equals("1")){

                                        progressDialog.dismiss();
                                        message.setMessage("Appointment Has been deleted successfully !");
                                        message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                doctor_global_class doctorGlobalClass = doctor_global_class.getInstance();
                                                Intent intent =  new Intent(Activity_Context,doctor_main.class);
                                                intent.putExtra("username",doctorGlobalClass.getDoctorUsername());
                                                intent.putExtra("doctor_email",doctorGlobalClass.getDoctorEmail());
                                                intent.putExtra("employee_number",doctorGlobalClass.getEmployeeNumber());
                                                intent.putExtra("employee_city",doctorGlobalClass.getEmployeeCity());

                                                Activity_Context.startActivity(intent);

                                            }
                                        });

                                        message.create();
                                        message.show();

                                    }else if (jsonResponse.equals("2")){

                                        progressDialog.dismiss();
                                        message.setMessage("Error in Deleting Appointment, Try Again !");
                                        message.setNegativeButton("OK",null);
                                        message.create();
                                        message.show();


                                    }



                                }catch (Exception e){
                                    progressDialog.dismiss();

                                    AlertDialog.Builder message2 =  new AlertDialog.Builder(Activity_Context);
                                    message2.setMessage(e.getMessage());
                                    message2.setNegativeButton("OK",null);
                                    message2.create();
                                    message2.show();

                                }


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                progressDialog.dismiss();
                                AlertDialog.Builder message = new AlertDialog.Builder(Activity_Context);
                                message.setTitle("Information");
                                //message.setNegativeButton("OK",null);
                                message.setIcon(R.drawable.ic_info_black_24dp);
                                message.setMessage("Check your Internet Connection");
                                message.setNegativeButton("OK",null);
                                message.create();
                                message.show();

                            }
                        }){

                            @Override
                            public Map<String,String> getParams(){

                                Map<String,String> parameters = new HashMap<>();
                                parameters.put("patient_username",UserName);
                                parameters.put("doc_email",DoctorEmail);
                                parameters.put("emp_no",EmployeeNumber);

                                return parameters;
                            }


                        };

                        RequestQueue queue = Volley.newRequestQueue(Activity_Context);
                        queue.add(deleteAppointment_request);

                    }
                });

                message.setNegativeButton("No",null);

                message.create();
                message.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView user_username;
        TextView user_email;
        TextView appointment_date;
        TextView appointment_time;
        TextView appointment_status;
        ImageButton approve_btn;
        ImageButton reject_btn;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            user_username = itemView.findViewById(R.id.appointment_userName_title);
            user_email = itemView.findViewById(R.id.pending_appointment_user_email);
            appointment_date = itemView.findViewById(R.id.appointment_request_date);
            appointment_time = itemView.findViewById(R.id.appointment_request_time);
            appointment_status = itemView.findViewById(R.id.pending_appointment_status);
            approve_btn = itemView.findViewById(R.id.approve_btn);
            reject_btn = itemView.findViewById(R.id.reject_btn);

        }
    }
}
