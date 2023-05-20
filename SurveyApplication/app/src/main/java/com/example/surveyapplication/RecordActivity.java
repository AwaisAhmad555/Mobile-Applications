package com.example.surveyapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class RecordActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 101;

    public Chronometer chronometer;
    public Boolean booleanValue = false;
    public long pauseOffset = 0;

    public MediaRecorder mediaRecorder;
    public String filePath;
    public String fileName;

    public String path_parameter;

    public int counter = 0;
    public int fileCounter = 0;
    public ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        final ImageButton record_btn =  (ImageButton) findViewById(R.id.record_btn);
        final Button stop_btn = (Button) findViewById(R.id.btn_stop);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        final TextView record_txt =  (TextView) findViewById(R.id.record_txt);
        chronometer.setBase(SystemClock.elapsedRealtime());

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        final Button nxt_btn =  (Button) findViewById(R.id.next_round_btn1);

        Intent intent = getIntent();

        final String name = intent.getExtras().getString("UserName");
        final String age = intent.getExtras().getString("Age");
        final Double latitude = intent.getExtras().getDouble("Latitude");
        final Double longitude = intent.getExtras().getDouble("Longitude");
        final String environment_type = intent.getExtras().getString("Environment");

        nxt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordActivity.this, information_upload.class);
                intent.putExtra("UserName", name);
                intent.putExtra("Age", age);
                intent.putExtra("Latitude",latitude);
                intent.putExtra("Longitude",longitude);
                intent.putExtra("Environment",environment_type);
                intent.putExtra("Path",getPath_parameter());
                startActivity(intent);
            }
        });

        mediaRecorder = new MediaRecorder();

        check();

        stop_btn.setEnabled(false);
        stop_btn.setBackgroundResource(R.drawable.gradient_black);


        record_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stop_btn.setBackgroundResource(R.color.color2);
                stop_btn.setEnabled(true);
                record_btn.setEnabled(false);

                Toast.makeText(getApplicationContext(),"Recording Started !",Toast.LENGTH_SHORT).show();
                recordAudio();
                startChronometer(booleanValue);

                chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {



                        if(counter == 0) {

                            record_txt.setText("Recording.");

                        }else if(counter == 1) {

                            record_txt.setText("Recording..");

                        }else if(counter == 2) {

                            record_txt.setText("Recording...");

                            counter = -1;
                        }
                        counter++;
                    }
                });
            }
        });

        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stop_btn.setEnabled(false);
                stop_btn.setBackgroundResource(R.drawable.gradient_black);
                record_btn.setEnabled(true);
                stopChronometer(booleanValue);
                stopRecordAudio();
                Toast.makeText(getApplicationContext(),"Media Recorded Successfully ! \n File Name : "+filePath,Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(RecordActivity.this)
                        .setTitle("Information")
                        .setMessage("Please Use this path to access file in next activity : " + "\n"+filePath)
                        .create()
                        .show();
                record_txt.setText("Tap button to start recording!");

            }
        });

    }

    public String getPath_parameter() {
        return path_parameter;
    }

    public void setPath_parameter(String path_parameter) {
        this.path_parameter = path_parameter;
    }

    public void RecordingMain() {

        /*
        if (fileCounter == 1) {

            filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/record_sample.3gp";

        }else {

            filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/record_sample("+fileCounter+").3gp";

        }

         */

        if (fileCounter != 1) {

            filePath = getExternalFilesDir("/").getAbsolutePath() + "/record_sample.3gp";

        }else {

            filePath = getExternalFilesDir("/").getAbsolutePath() + "/record_sample.3gp";
            //filePath = getExternalFilesDir("/").getAbsolutePath() + "/record_sample("+fileCounter+").3gp";

        }

        fileName = filePath.substring(filePath.lastIndexOf("/")+ 1);

        setPath_parameter(fileName);

        fileCounter++;

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

        mediaRecorder.setOutputFile(filePath);


    }
    private void stopRecordAudio() {

        mediaRecorder.stop();
        //mediaRecorder.reset();
        //mediaRecorder.release();
        //mediaRecorder = null;

        RecordingMain();

    }

    private void startChronometer(Boolean aBoolean) {

        if(!aBoolean){

            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            booleanValue = true;

        }
    }

    private void pauseChronometer(Boolean aBoolean) {

        if(aBoolean){

            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            booleanValue = false;

        }
    }

    private void stopChronometer(Boolean aBoolean) {

        if(aBoolean){

            chronometer.stop();
            chronometer.setBase(SystemClock.elapsedRealtime());
            pauseOffset = 0;
            booleanValue = false;

        }
    }

    public void check()
    {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            // Check Permissions Now

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);

            // permission has been granted, continue as usual
        }

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){

            // Check Permissions Now

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},REQUEST_CODE);

            // permission has been granted, continue as usual
        }

        RecordingMain();
    }

    private void recordAudio() {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            // Check Permissions Now

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);

            // permission has been granted, continue as usual
        }

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){

            // Check Permissions Now

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},REQUEST_CODE);

            // permission has been granted, continue as usual
        }

        try {

            mediaRecorder.prepare();
            mediaRecorder.start();

        }catch (IllegalStateException ise)
        {

            ise.printStackTrace();

        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {

            case REQUEST_CODE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                }

                break;

        }

    }
}
