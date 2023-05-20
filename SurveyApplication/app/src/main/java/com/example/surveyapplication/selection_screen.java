package com.example.surveyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selection_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_screen);

        Button login_btn = (Button) findViewById(R.id.login_btn);
        Button register_btn = (Button) findViewById(R.id.registration_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(selection_screen.this,Login.class);
                startActivity(intent);

            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(selection_screen.this,registration.class);
                startActivity(intent);

            }
        });

    }
}
