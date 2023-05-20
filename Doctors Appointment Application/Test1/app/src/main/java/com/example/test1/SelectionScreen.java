package com.example.test1;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionScreen extends AppCompatActivity {

    private Button doctorBtn;
    private Button patientBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_screen);



        doctorBtn = (Button) findViewById(R.id.doctor_btn);
        patientBtn = (Button) findViewById(R.id.patient_btn);



        patientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SelectionScreen.this,Login.class);
                startActivity(intent);

            }
        });

        doctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* AlertDialog.Builder msg = new AlertDialog.Builder(SelectionScreen.this);

                msg.setMessage("DOCTOR Sign Up SCREEN is not yet Ready !!!!!");
                msg.setNegativeButton("Cancel",null);
                msg.create();
                msg.show(); */

               Intent intent =  new Intent(SelectionScreen.this,doctor_login_panel.class);
               startActivity(intent);


            }
        });


    }







}
