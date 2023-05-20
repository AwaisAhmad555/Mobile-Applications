package com.example.test1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class processed_RecyclerViewAdapter extends RecyclerView.Adapter<processed_RecyclerViewAdapter.myViewHolder> {


    public Context activity_context;
    public List<processed_appointment_items> ArrayList;



    public processed_RecyclerViewAdapter(Context activity_context, List<processed_appointment_items> arrayList) {
        this.activity_context = activity_context;
        this.ArrayList = arrayList;
    }



    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view;

        LayoutInflater mInflater = LayoutInflater.from(activity_context);

        view = mInflater.inflate(R.layout.processed_appointment_item,parent,false);


        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int position) {

        int appointment_index = position+1;
        myViewHolder.appointment_Main.setText(myViewHolder.appointment_Main.getText() + " " + appointment_index);
        myViewHolder.hospital_Name.setText(ArrayList.get(position).AppointmentHospital);
        myViewHolder.appointment_status.setText(ArrayList.get(position).AppointmentStatus);
        myViewHolder.date_text.setText(ArrayList.get(position).AppointmentDate);
        myViewHolder.time_text.setText(ArrayList.get(position).AppointmentTime);


        myViewHolder.view_detail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        Button view_detail_button;
        TextView appointment_Main;
        TextView hospital_Name;
        TextView appointment_status;
        TextView date_text;
        TextView time_text;


        public myViewHolder(@NonNull View itemView) {


            super(itemView);

            view_detail_button = itemView.findViewById(R.id.view_detail_btn);
            appointment_Main = itemView.findViewById(R.id.processed_appointment_userName_title);
            hospital_Name = itemView.findViewById(R.id.processed_appointment_hospital_address);
            appointment_status = itemView.findViewById(R.id.processed_appointment_status);
            date_text = itemView.findViewById(R.id.appointment_processed_date);
            time_text = itemView.findViewById(R.id.appointment_processed_time);


        }
    }
}
