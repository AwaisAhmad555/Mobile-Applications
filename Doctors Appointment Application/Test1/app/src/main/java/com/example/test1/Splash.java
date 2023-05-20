package com.example.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread= new Thread()
        {


            public void run () {


            try {

              sleep(5000);


            } catch (Exception e) {

                e.printStackTrace();
            } finally {
                Intent intent = new Intent(Splash.this, SelectionScreen.class);
                startActivity(intent);

            }


        }
        };thread.start();

    }



}
