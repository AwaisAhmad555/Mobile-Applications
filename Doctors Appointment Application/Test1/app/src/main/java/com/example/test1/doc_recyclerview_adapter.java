package com.example.test1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class doc_recyclerview_adapter extends RecyclerView.Adapter<doc_recyclerview_adapter.MyViewHolder> {

    //Variables
    private Context mContext;
    private List<doc_recycler_items> mList;


    //Constructor
    public doc_recyclerview_adapter(Context mContext, List<doc_recycler_items> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        LayoutInflater mInflater = LayoutInflater.from(mContext);

        view = mInflater.inflate(R.layout.activity_items,parent,false);

        return new doc_recyclerview_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int position) {

        myViewHolder.Item_name.setText(mList.get(position).getItm_name());
        myViewHolder.img_itm.setImageResource(mList.get(position).getLogo());

        final String ItemName = myViewHolder.Item_name.getText().toString();
        final String doctor_email = mList.get(position).getDoctor_email();
        final String employee_number = mList.get(position).getEmployee_number();
        final String employee_city = mList.get(position).getEmployee_city();

        myViewHolder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ItemName.equals("Appointment Requests")){

                    Intent intent = new Intent(mContext, pending_appointment_list.class);
                    intent.putExtra("doctorEmail",doctor_email);
                    intent.putExtra("employeeNumber",employee_number);
                    intent.putExtra("employeeCity",employee_city);

                    mContext.startActivity(intent);

                }else if(ItemName.equals("Approved Appointment")){

                    Intent intent = new Intent(mContext, processed_appointments.class);
                    //intent.putExtra("doctorEmail",doctor_email);
                    //intent.putExtra("employeeNumber",employee_number);
                    //intent.putExtra("employeeCity",employee_city);

                    mContext.startActivity(intent);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

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
