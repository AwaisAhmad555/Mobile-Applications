package com.example.stopwatch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class functional_activity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1000 ;
    List<switch_recycler_items> list;

    public Switch_RecyclerViewAdapter switch_recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functional_activity);

        /*
         BluetoothAdapter bluetoothAdapter = null;
         BluetoothSocket bluetoothSocket = null;

         Set<BluetoothDevice> pairedDevices;

         final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-0805F9B34FB");


          */

        TextView device_name = (TextView) findViewById(R.id.device_name_text);
        TextView device_address = (TextView) findViewById(R.id.device_address_text);

        ImageButton img_btn = (ImageButton) findViewById(R.id.img_btn);

        Intent intent = getIntent();

        final String appliance_name = intent.getExtras().getString("appliance_name").toString();
        final int img_logo = intent.getExtras().getInt("img_logo");

        TextView applianceTitle = (TextView) findViewById(R.id.appliances_title);
       // TextView applianceName = (TextView) findViewById(R.id.appliances_txt1);
        //TextView applianceName2 = (TextView) findViewById(R.id.appliances_txt2);
        ImageView applianceImg = (ImageView) findViewById(R.id.appliances_image);

        applianceTitle.setText(appliance_name);

        //applianceName.setText(appliance_name);
       // applianceName2.setText(appliance_name);
        applianceImg.setImageResource(img_logo);

        list = new ArrayList<>();

        for(int i = 1; i<=3; i++) {

            String number = Integer.toString(i) ;

            list.add(new switch_recycler_items(appliance_name, number));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.switch_items_recyclerView);

        switch_recyclerViewAdapter = new Switch_RecyclerViewAdapter(functional_activity.this,list);

        recyclerView.setLayoutManager(new LinearLayoutManager(functional_activity.this));

        recyclerView.setAdapter(switch_recyclerViewAdapter);



        try {
            device_name.setText(switch_recyclerViewAdapter.outName());
            device_address.setText(switch_recyclerViewAdapter.outAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{

            switch_recyclerViewAdapter.bluetooth_connect_device();

        }
        catch (IOException e){
            e.printStackTrace();
        }

        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                voice_event();

               // switch_recyclerViewAdapter.led_on_off("a");

            }
        });





    }

    public void voice_event() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say SomeThing.......!");

        try {


            startActivityForResult(intent, REQUEST_CODE);

        }

        catch (Exception e){


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode){

            case REQUEST_CODE:

                if (resultCode == RESULT_OK && null != data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    switch (result.get(0)){

                        case "turn on LED 1" :

                            switch_recyclerViewAdapter.led_on_off("a");

                            break;

                        case "turn on led 1" :

                            switch_recyclerViewAdapter.led_on_off("a");

                            break;

                        case "turn on led to" :

                            switch_recyclerViewAdapter.led_on_off("c");

                            break;

                        case "turn on led 2" :

                            switch_recyclerViewAdapter.led_on_off("c");

                            break;

                        case "turn off LED 1" :

                            switch_recyclerViewAdapter.led_on_off("b");

                            break;

                        case "turn off led 1" :

                            switch_recyclerViewAdapter.led_on_off("b");

                            break;

                        case "turn off LED to" :

                            switch_recyclerViewAdapter.led_on_off("d");

                            break;

                        case "turn off LED 2" :

                            switch_recyclerViewAdapter.led_on_off("d");

                            break;

                        case "turn off led 2" :

                            switch_recyclerViewAdapter.led_on_off("d");

                            break;

                        case "turn off led to" :

                            switch_recyclerViewAdapter.led_on_off("d");

                            break;

                    }



                }

                break;
        }

    }
}
