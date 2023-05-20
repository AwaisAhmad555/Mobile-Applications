package com.example.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class doctor_main extends AppCompatActivity {

    List<doc_recycler_items> lst_items;

    public long time_duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        Intent intent = getIntent();

        String doctor_username_text = intent.getExtras().getString("username").toString();
        String doctor_email = intent.getExtras().getString("doctor_email");
        String employee_number = intent.getExtras().getString("employee_number");
        String employee_city = intent.getExtras().getString("employee_city");

        TextView Doctor_title = (TextView) findViewById(R.id.doctor_username_title);

        Doctor_title.setText(doctor_username_text);


        lst_items = new ArrayList<>();

        lst_items.add(new doc_recycler_items("Approved Appointment",R.drawable.appointment0,doctor_email,employee_number,employee_city));
        lst_items.add(new doc_recycler_items("Appointment Requests",R.drawable.appointment,doctor_email,employee_number,employee_city));
        lst_items.add(new doc_recycler_items("Patients",R.drawable.user_icon,doctor_email,employee_number,employee_city));
        lst_items.add(new doc_recycler_items("Account Information",R.drawable.list0,doctor_email,employee_number,employee_city));
        lst_items.add(new doc_recycler_items("Update Information",R.drawable.list_icon,doctor_email,employee_number,employee_city));

        lst_items.add(new doc_recycler_items("Item1",R.drawable.ic_air_quality,doctor_email,employee_number,employee_city));
        lst_items.add(new doc_recycler_items("Item2",R.drawable.ic_email_black_24dp,doctor_email,employee_number,employee_city));
        lst_items.add(new doc_recycler_items("Item3",R.drawable.ic_location_on_black_24dp,doctor_email,employee_number,employee_city));
        lst_items.add(new doc_recycler_items("Item4",R.drawable.pulse1,doctor_email,employee_number,employee_city));
        lst_items.add(new doc_recycler_items("Item5",R.drawable.aq,doctor_email,employee_number,employee_city));



        RecyclerView rv = (RecyclerView) findViewById(R.id.doc_recycler_view);
        doc_recyclerview_adapter docadapter = new doc_recyclerview_adapter(doctor_main.this,lst_items);

        rv.setLayoutManager(new GridLayoutManager(doctor_main.this,2));
        rv.setAdapter(docadapter);
    }

    @Override
    public void onBackPressed() {

        if(time_duration + 2000 > System.currentTimeMillis()){

            //super.onBackPressed();
            Intent intent =  new Intent(doctor_main.this,SelectionScreen.class);
            startActivity(intent);
            return;

        }else {

            Toast.makeText(getBaseContext(),"Press Again to Log OFF !",Toast.LENGTH_LONG).show();
        }



        time_duration = System.currentTimeMillis();
    }
}
