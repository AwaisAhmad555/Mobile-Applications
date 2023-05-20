package com.example.surveyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QuistionOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quistion__one);




        Button btn = findViewById(R.id.name_next);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText username = (EditText) findViewById(R.id.user_name_text);

                final String user_name = username.getText().toString();
                Intent intent = new Intent(QuistionOne.this,UserAge.class);
                intent.putExtra("UserName",user_name);
                startActivity(intent);
            }
        });
    }
}
