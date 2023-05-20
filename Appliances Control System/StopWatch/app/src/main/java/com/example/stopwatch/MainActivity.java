package com.example.stopwatch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public EditText username;
    public EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);

        Button login_btn = (Button) findViewById(R.id.Login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UserName = username.getText().toString() + "";
                String Password = password.getText().toString() + "";

                if (("12345".equals(Password)) && ("Admin".equals(UserName))) {

                    AlertDialog.Builder message = new AlertDialog.Builder(MainActivity.this);

                    message.setMessage("Login Successful ! With following Credentials \n\n" + UserName + " " + Password + "\n\nClick Next to proceed");
                    message.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MainActivity.this, Main_Page.class);

                            startActivity(intent);

                        }
                    });
                    message.setNegativeButton("Cancel", null);
                    message.create();
                    message.show();


                } else {



                    AlertDialog.Builder message = new AlertDialog.Builder(MainActivity.this);

                    message.setMessage("Login Failed !");
                    message.setNegativeButton("OK", null);
                    message.create();
                    message.show();




                }

            }
        });
    }
}
