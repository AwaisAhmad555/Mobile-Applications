package com.example.test1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class appointment_RecyclerViewAdapter extends RecyclerView.Adapter<appointment_RecyclerViewAdapter.MyViewHolder> {

    private Context Ap_Context;
    private List<appointment_items> ap_data;

    public appointment_RecyclerViewAdapter(Context ap_context, List<appointment_items> ap_data) {
        this.Ap_Context = ap_context;
        this.ap_data = ap_data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater inflater = LayoutInflater.from(Ap_Context);

        view = inflater.inflate(R.layout.appointment_items,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        myViewHolder.doc_name.setText(ap_data.get(position).getDoc_name());
        myViewHolder.doc_type.setText(ap_data.get(position).getDoc_type());
        myViewHolder.appointment_status.setText(ap_data.get(position).getStatus());
        myViewHolder.logo.setImageResource(ap_data.get(position).getLogo());
        myViewHolder.appointment_time.setText(ap_data.get(position).getTime());
        myViewHolder.appointment_date.setText(ap_data.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return ap_data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{



        TextView doc_name;
        TextView doc_type;
        TextView appointment_status;
        ImageView logo;
        TextView appointment_date;
        TextView appointment_time;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            doc_name = (TextView) itemView.findViewById(R.id.ap_DocName);
            doc_type = (TextView) itemView.findViewById(R.id.ap_Doctor_type);
            appointment_status = (TextView) itemView.findViewById(R.id.appointment_status);
            logo = (ImageView) itemView.findViewById(R.id.ap_doc_img_logo);
            appointment_date = (TextView) itemView.findViewById(R.id.appointment_date);
            appointment_time = (TextView) itemView.findViewById(R.id.appointment_time);

        }
    }


}
