package com.example.test1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.*;


//RecyclerView Adapter Class

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    //Variables
    private Context mContext;
    private List<recycler_items> mData;


    //Constructor
    public RecyclerViewAdapter(Context mContext, List<recycler_items> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    //Constructor (empty)
    public RecyclerViewAdapter() {
    }

    //ViewHolder function to generate views in recyclerView
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater mInflater = LayoutInflater.from(mContext);

        view = mInflater.inflate(R.layout.activity_items,parent,false);

        return new MyViewHolder(view);
    }

    //Data Binding class to bind data with objects in recyclerView
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int position) {

        myViewHolder.Item_name.setText(mData.get(position).getItm_name());
        myViewHolder.img_itm.setImageResource(mData.get(position).getLogo());

        final String Heading_name = mData.get(position).getItm_name();
        final String patient_name = mData.get(position).getPatient_Name();
        final String patient_UserName = mData.get(position).getPatient_UserName();
        final String patient_Email = mData.get(position).getPatient_email();
        final String patient_Phone = mData.get(position).getPatient_phone();

        myViewHolder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itm_Name;
                itm_Name= myViewHolder.Item_name.getText().toString();

                if(itm_Name.equals("Doctors")){

                    AlertDialog.Builder msg = new AlertDialog.Builder(mContext);
                    msg.setMessage("You Have Clicked " + itm_Name + " Section");
                    msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {



                        }
                    });
                    msg.create();
                    msg.show();

                    Intent intent = new Intent(mContext,Doctor_Section.class);

                    intent.putExtra("patientName",patient_name);
                    intent.putExtra("patient_username",patient_UserName);
                    intent.putExtra("patient_email",patient_Email);
                    intent.putExtra("patient_phone",patient_Phone);

                    mContext.startActivity(intent);


                } else if(itm_Name.equals("Appointments")){


                    Intent intent = new Intent(mContext,appointment.class);
                    intent.putExtra("patientName",patient_name);
                    intent.putExtra("patient_username",patient_UserName);
                    intent.putExtra("patient_email",patient_Email);
                    intent.putExtra("patient_phone",patient_Phone);
                    mContext.startActivity(intent);



                }else if(itm_Name.equals("Detail Info")){


                    Intent intent = new Intent(mContext,patient_personal_detail.class);
                    intent.putExtra("patientName",patient_name);
                    intent.putExtra("patient_username",patient_UserName);
                    intent.putExtra("patient_email",patient_Email);
                    intent.putExtra("patient_phone",patient_Phone);
                    mContext.startActivity(intent);



                } else if(itm_Name.equals("Approved Appointments")){


                    Intent intent = new Intent(mContext,patient_processed_appointments.class);
                    intent.putExtra("patientName",patient_name);
                    intent.putExtra("patient_username",patient_UserName);
                    intent.putExtra("patient_email",patient_Email);
                    intent.putExtra("patient_phone",patient_Phone);
                    mContext.startActivity(intent);

                }else{


                Intent intent = new Intent(mContext,Patient_Temperature.class);
                intent.putExtra("patientName",patient_name);
                intent.putExtra("patient_username",patient_UserName);
                intent.putExtra("heading",Heading_name);
                mContext.startActivity(intent);



            }

            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    //ViewHolder Class

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Item_name;
        ImageView img_itm;
        CardView card_view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Item_name = (TextView) itemView.findViewById(R.id.itm_name);
            img_itm = (ImageView) itemView.findViewById(R.id.img_logo);
            card_view = (CardView) itemView.findViewById(R.id.card_item);
        }
    }
}
