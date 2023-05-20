package com.example.test1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class user_registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);



        final EditText name = (EditText) findViewById(R.id.name_text);
        final EditText user_name = (EditText) findViewById(R.id.username_text) ;
        final EditText user_age = (EditText) findViewById(R.id.age_edit);
        final EditText user_password = (EditText) findViewById(R.id.password_edit);
        final Button submit_button = (Button) findViewById(R.id.Submit_btn);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Name = name.getText().toString();
                final String User_Name = user_name.getText().toString();
                final String User_Password = user_password.getText().toString();
                int User_Age;

                if(user_age.getText().toString().equals("")){

                    User_Age = 0;

                } else {

                    User_Age = Integer.parseInt(user_age.getText().toString());

                }


                Response.Listener<String> responseListner = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            AlertDialog.Builder try_msg = new AlertDialog.Builder(user_registration.this);

                            //try_msg.setMessage("Try Portion listner response =  " + response + " ");
                            //try_msg.setNegativeButton("OK", null);
                            //try_msg.create();
                            //try_msg.show();

                            JSONObject jsonResponse = new JSONObject(response);
                            String success = jsonResponse.getString("success");


                            if(success.equals("1")){


                                AlertDialog.Builder builder = new AlertDialog.Builder(user_registration.this);

                                builder.setMessage("Registration Successful")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                Intent intent = new Intent(user_registration.this, Login.class);
                                                user_registration.this.startActivity(intent);

                                            }
                                        })
                                        .create()
                                        .show();


                            }

                            if(success.equals("0")){

                                AlertDialog.Builder builder = new AlertDialog.Builder(user_registration.this);

                                builder.setMessage("User Existed Already !!!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();



                            }

                            if(success.equals("2")){

                                AlertDialog.Builder builder = new AlertDialog.Builder(user_registration.this);

                                builder.setMessage("Please Enter All Information !!!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();


                            }

                    //        else {

                      //          AlertDialog.Builder builder = new AlertDialog.Builder(user_registration.this);

                       //         builder.setMessage("Registration Failed")
                       //                 .setNegativeButton("Retry", null)
                           //             .create()
                        //                .show();


                         //   }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };


                //AlertDialog.Builder builder = new AlertDialog.Builder(user_registration.this);

                //builder.setMessage("Registration Successful")

                      //  .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                          //  @Override
                         //   public void onClick(DialogInterface dialog, int id) {

                            //    Intent intent = new Intent(user_registration.this, Login.class);
                             //   user_registration.this.startActivity(intent);

                           // }
                     //   })
                      //  .create()
                       // .show();



                RegisterRequest registerRequest = new RegisterRequest(Name,User_Name,User_Age,User_Password,responseListner);
                RequestQueue queue = Volley.newRequestQueue(user_registration.this);
                queue.add(registerRequest);

            }
        });



    }




}

