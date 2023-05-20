package com.example.surveyapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class information_upload extends AppCompatActivity {

    public static final int REQUEST_CODE = 101;
    public Button info_btn;
    public ProgressDialog progressDialog;

    public EditText User_Name_edit_text;
    public EditText User_Age_edit_text;
    public EditText edit_latitude;
    public EditText edit_longitude;
    public EditText environment_edit;
    public EditText file_name_edit;

    public String NameValue;
    public String AgeValue;
    public String LatitudeValue;
    public String LongitudeValue;
    public String Environment_typeValue;


    public String PathValue;

    //public TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_upload);


        Intent intent = getIntent();

        final String name = intent.getExtras().getString("UserName");
        final String age = intent.getExtras().getString("Age");
        final Double latitude = intent.getExtras().getDouble("Latitude");
        final Double longitude = intent.getExtras().getDouble("Longitude");
        final String environment_type = intent.getExtras().getString("Environment");
        final String Path = intent.getExtras().getString("Path");

        setNameValue(name);
        setAgeValue(age);
        setLatitudeValue(latitude.toString());
        setLongitudeValue(longitude.toString());
        setEnvironment_typeValue(environment_type);



        User_Name_edit_text = (EditText) findViewById(R.id.edit_name_text);
        User_Age_edit_text = (EditText) findViewById(R.id.username_age);
        edit_latitude = (EditText) findViewById(R.id.edit_latitude);
        edit_longitude = (EditText) findViewById(R.id.edit_longitude);
        environment_edit = (EditText) findViewById(R.id.environment_edit);
        file_name_edit = (EditText) findViewById(R.id.file_name_edit);

        info_btn =  (Button) findViewById(R.id.upload_btn);
        //textView = (TextView) findViewById(R.id.text_check);

        User_Name_edit_text.setText(name);
        User_Age_edit_text.setText(age);
        edit_latitude.setText(latitude.toString());
        edit_longitude.setText(longitude.toString());
        environment_edit.setText(environment_type);
        file_name_edit.setText(Path);

        User_Name_edit_text.setTextColor(Color.rgb(216,27,96));
        User_Age_edit_text.setTextColor(Color.rgb(216,27,96));
        edit_latitude.setTextColor(Color.rgb(216,27,96));
        edit_longitude.setTextColor(Color.rgb(216,27,96));
        environment_edit.setTextColor(Color.rgb(216,27,96));
        file_name_edit.setTextColor(Color.rgb(216,27,96));

        User_Name_edit_text.setEnabled(false);
        User_Age_edit_text.setEnabled(false);
        edit_latitude.setEnabled(false);
        edit_longitude.setEnabled(false);
        environment_edit.setEnabled(false);
        file_name_edit.setEnabled(false);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){


                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE);
            }

        }


        upload_function();
    }

    private void upload_function() {

        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new MaterialFilePicker()
                        .withActivity(information_upload.this)
                        .withRequestCode(10)
                        .start();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 10 && resultCode == RESULT_OK){

            progressDialog = new ProgressDialog(information_upload.this);
            progressDialog.setTitle("Uploading !");
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();


            Thread thread =  new Thread(new Runnable() {
                @Override
                public void run() {

                    File file = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));

                    String content_type = getMimeType(file.getPath());

                    String file_path = file.getAbsolutePath();

                    OkHttpClient client = new OkHttpClient();

                    RequestBody file_body = RequestBody.create(MediaType.parse(content_type),file);

                    RequestBody request_body = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("type",content_type)
                            .addFormDataPart("uploaded_file",file_path.substring(file_path.lastIndexOf("/")+1), file_body)
                            .build();

                    Request request = new Request.Builder()
                            .url("https://smart-health-care-system.000webhostapp.com/sample.php")
                            .post(request_body)
                            .build();




                    try {
                        Response response = client.newCall(request).execute();

                        if(!response.isSuccessful()){

                            throw new IOException("Error : " + response);

                        }

                        progressDialog.dismiss();

                      /*  AlertDialog.Builder msg = new AlertDialog.Builder(information_upload.this);
                        msg.setNegativeButton("OK",null);
                        msg.setMessage(response.toString());
                        msg.create();
                        msg.show(); */


                    } catch (IOException e) {
                        e.printStackTrace();

                        // progressDialog.dismiss();

                        //Toast.makeText(getApplicationContext(),"Catch portion",Toast.LENGTH_SHORT).show();


                    }

                }
            });

            thread.start();

            File file = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));

            String content_type = getMimeType(file.getPath());

            String file_path = file.getAbsolutePath();

            final String fileName = file_path.substring(file_path.lastIndexOf("/")+1);


            StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, "https://smart-health-care-system.000webhostapp.com/sample.php",
                    new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    AlertDialog.Builder msg1 = new AlertDialog.Builder(information_upload.this);
                    msg1.setMessage("You Have Successfully Completed Survey !");
                    msg1.setNegativeButton("EXIT APPLICATION", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            finish();
                            moveTaskToBack(true);
                        }
                    });

                    msg1.setCancelable(false);
                    msg1.create();
                    msg1.show();



                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                public Map<String, String> getParams() {

                    Map<String,String> params = new HashMap<>();

                    params.put("name",getNameValue());
                    params.put("age",getAgeValue());
                    params.put("latitude",getLatitudeValue());
                    params.put("longitude",getLongitudeValue());
                    params.put("environment",getEnvironment_typeValue());
                    params.put("file_address",fileName);

                    return params;
                }
            };

            RequestQueue queue = Volley.newRequestQueue(information_upload.this);
            queue.add(request);
        }
    }

    private String getMimeType(String path) {

        String extension = MimeTypeMap.getFileExtensionFromUrl(path);

        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){


            upload_function();


        }else {

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE);


            }


        }

    }


    public void setNameValue(String nameValue) {
        NameValue = nameValue;
    }

    public void setAgeValue(String ageValue) {
        AgeValue = ageValue;
    }

    public void setLatitudeValue(String latitudeValue) {
        LatitudeValue = latitudeValue;
    }

    public void setLongitudeValue(String longitudeValue) {
        LongitudeValue = longitudeValue;
    }

    public void setEnvironment_typeValue(String environment_typeValue) {
        Environment_typeValue = environment_typeValue;
    }

    public String getNameValue() {
        return NameValue;
    }

    public String getAgeValue() {
        return AgeValue;
    }

    public String getLatitudeValue() {
        return LatitudeValue;
    }

    public String getLongitudeValue() {
        return LongitudeValue;
    }

    public String getEnvironment_typeValue() {
        return Environment_typeValue;
    }

}
