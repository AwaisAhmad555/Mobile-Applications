package com.example.stopwatch;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    public Context mContext;
    public List<recycler_items> itm_list;

    public RecyclerViewAdapter(Context mContext, List<recycler_items> itm_list) {
        this.mContext = mContext;
        this.itm_list = itm_list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater inflater = LayoutInflater.from(mContext);

        view = inflater.inflate(R.layout.activity_items,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        myViewHolder.item_name.setText(itm_list.get(position).getItm_name());
        myViewHolder.item_img.setImageResource(itm_list.get(position).getImg());

        final String appliance_name = itm_list.get(position).getItm_name();
        final int img_logo = itm_list.get(position).getImg();

        myViewHolder.itm_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,functional_activity.class);

                intent.putExtra("appliance_name",appliance_name);
                intent.putExtra("img_logo",img_logo);


                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return itm_list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_name;
        ImageView item_img;
        CardView itm_card_view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_name = (TextView) itemView.findViewById(R.id.itm_name_txt);
            item_img = (ImageView) itemView.findViewById(R.id.img_logo);
            itm_card_view = (CardView) itemView.findViewById(R.id.card_item);

        }
    }
}
