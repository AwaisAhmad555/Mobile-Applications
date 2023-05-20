package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.util.ArrayList;
import java.util.List;

public class Main_Page extends AppCompatActivity {

    List<recycler_items> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__page);

        list = new ArrayList<>();



        list.add(new recycler_items(R.drawable.bulb, "Lights"));
        list.add(new recycler_items(R.drawable.fan3, "Fans"));
        list.add(new recycler_items(R.drawable.led, "LEDs"));
        list.add(new recycler_items(R.drawable.fridge2, "Refrigerator"));
        list.add(new recycler_items(R.drawable.ac, "Air Conditioner"));
        list.add(new recycler_items(R.drawable.motor, "Water Motor"));
        list.add(new recycler_items(R.drawable.user_icon, "User Detail"));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(Main_Page.this,list);

        recyclerView.setLayoutManager(new GridLayoutManager(Main_Page.this,2));
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
