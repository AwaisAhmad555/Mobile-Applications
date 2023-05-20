package com.example.surveyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.Instant;

public class StartActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        textView = findViewById(R.id.main_title);
        imageView = findViewById(R.id.main_img);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.mytransition);

        Animation end_animation = AnimationUtils.loadAnimation(this,R.anim.endtransition);

        textView.startAnimation(animation);
        imageView.startAnimation(animation);

        final Intent intent = new Intent(this,HomeScreen.class);

        Thread timer = new Thread(){

            public void run(){

                try{

                    sleep(5000);

                }
                catch (InterruptedException e){

                    e.printStackTrace();

                }

                finally {

                    startActivity(intent);
                    finish();
                }



            }

        };

        timer.start();

       // textView.startAnimation(end_animation);
        //imageView.startAnimation(end_animation);
    }
}
