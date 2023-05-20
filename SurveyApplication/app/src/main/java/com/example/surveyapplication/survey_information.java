package com.example.surveyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class survey_information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_information);

        EditText name = (EditText) findViewById(R.id.survey_user_name);
        EditText email = (EditText) findViewById(R.id.survey_user_email);
        EditText phone = (EditText) findViewById(R.id.survey_user_phone);
        EditText age = (EditText) findViewById(R.id.survey_user_age);
        Spinner nature_of_environment = (Spinner) findViewById(R.id.nature_of_environment);
        Button next_btn = (Button) findViewById(R.id.submit_btn);


        ArrayList<String> spinner_information_list = new ArrayList<>();

        spinner_information_list.add("Buildings");
        spinner_information_list.add("Plane Land");
        spinner_information_list.add("Marshes");
        spinner_information_list.add("Garbage or Trash Covered Area");
        spinner_information_list.add("Coastal Area");
        spinner_information_list.add("Green Space");

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(survey_information.this,android.R.layout.simple_spinner_item,spinner_information_list);

        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        nature_of_environment.setAdapter(stringArrayAdapter);


        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name_string = name.getText().toString();
                String email_string = email.getText().toString();
                String phone_string = phone.getText().toString();
                String age_string = age.getText().toString();
                String nature_of_environment_string = nature_of_environment.getSelectedItem().toString();

                if(name_string.equals("") || age_string.equals("") || nature_of_environment_string.equals("")){

                    new AlertDialog.Builder(survey_information.this)
                            .setTitle("Information")
                            .setMessage("Please Enter Required Information !")
                            .setNegativeButton("OK",null)
                            .create().show();

                }else {

                    Intent intent = new Intent(survey_information.this,location.class);
                    intent.putExtra("UserName",name_string);
                    intent.putExtra("Age",age_string);
                    intent.putExtra("nature_of_environment",nature_of_environment_string);

                    startActivity(intent);

                }



            }
        });


    }
}
