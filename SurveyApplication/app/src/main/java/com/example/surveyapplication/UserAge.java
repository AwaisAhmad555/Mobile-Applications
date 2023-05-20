package com.example.surveyapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserAge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_age);

        final Button nxt_btn;

        Intent intent = getIntent();
        final String user_name = intent.getExtras().getString("UserName");

        final TextView username = (TextView) findViewById(R.id.age_name);

        final EditText age_text = (EditText) findViewById(R.id.user_age);

        final String string_one = username.getText().toString();

        username.setText(user_name+"! "+string_one);


        //username.setText("Dear "+user_name+", ");

        nxt_btn =  findViewById(R.id.age_next);


        nxt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Age = age_text.getText().toString();
                Intent intent1 =  new Intent(UserAge.this,location.class);
                intent1.putExtra("UserName",user_name);
                intent1.putExtra("Age",Age);
                startActivity(intent1);

               /* AlertDialog.Builder msg = new AlertDialog.Builder(UserAge.this);
                msg.setMessage(Age);
                msg.create();
                msg.show(); */
            }
        });
    }
}
