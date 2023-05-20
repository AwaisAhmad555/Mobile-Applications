package com.example.surveyapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Environment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public String environment_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);

        Button button =  (Button) findViewById(R.id.environment_next);
        Spinner spinner = (Spinner) findViewById(R.id.environment_nature);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Intent intent =  getIntent();
        final String name = intent.getExtras().getString("UserName");
        final String age = intent.getExtras().getString("Age");
        final Double latitude = intent.getExtras().getDouble("Latitude");
        final Double longitude = intent.getExtras().getDouble("Longitude");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(ActivityCompat.checkSelfPermission(Environment.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Environment.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                        AlertDialog.Builder msg = new AlertDialog.Builder(Environment.this);

                        msg.setMessage(Html.fromHtml("<font color='#D81B60'>Please Grant Permissions In Order to Proceed !</font>"));
                        msg.setNegativeButton("OK",null);
                        msg.create();
                        msg.show();

                        ActivityCompat.requestPermissions(Environment.this,new String[]{Manifest.permission.RECORD_AUDIO},100);
                        ActivityCompat.requestPermissions(Environment.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
                        ActivityCompat.requestPermissions(Environment.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                    }
                    else {

                 /*       AlertDialog.Builder msg = new AlertDialog.Builder(Environment.this);

                        msg.setMessage(Html.fromHtml("<font color='#D81B60'>"+ name+ "</font><br><font color='#D81B60'>"+  age+ "</font><br><font color='#D81B60'>"+  latitude+ "</font><br><font color='#D81B60'>"+  longitude+ "</font><br><font color='#D81B60'>"+getEnvironment_type()+" !</font>"));
                        msg.setNegativeButton("OK",null);
                        msg.create();
                        msg.show();    */

                        Intent intent =  new Intent(Environment.this, RecordActivity.class);
                        intent.putExtra("UserName", name);
                        intent.putExtra("Age", age);
                        intent.putExtra("Latitude",latitude);
                        intent.putExtra("Longitude",longitude);
                        intent.putExtra("Environment",getEnvironment_type());
                        startActivity(intent);

                    }
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        String value = parent.getItemAtPosition(position).toString();

     /*   AlertDialog.Builder msg = new AlertDialog.Builder(parent.getContext());

        msg.setMessage(value);
        msg.setPositiveButton("OK",null);
        msg.create();
        msg.show();  */

        Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();

        setEnvironment_type(value);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public String getEnvironment_type() {
        return environment_type;
    }

    public void setEnvironment_type(String environment_type) {
        this.environment_type = environment_type;
    }
}
