package com.example.test1;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    public String patient_email = null;
    public String patient_phone = null;


    public String getPatient_email() {
        return patient_email;
    }

    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public void setPatient_phone(String patient_phone) {
        this.patient_phone = patient_phone;
    }


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;

    List<recycler_items> lstItems;

    public ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        progressDialog = new ProgressDialog(Home.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Loading Please Wait !");
        progressDialog.show();

        LinearLayout home_screen = (LinearLayout) findViewById(R.id.home_screen);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton navigation_drawer_button = (ImageButton) findViewById(R.id.navigation_menu_button);


        navigation_drawer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        Intent intent = getIntent();


        final String userdata = intent.getExtras().getString("name");

        final String patient_username = intent.getExtras().getString("userName");

        final String patient_email1 = intent.getExtras().getString("email");

        final String patient_phone1 = intent.getExtras().getString("phone");

        final String patient_bloodGroup = intent.getExtras().getString("bloodGroup");

        if(patient_email1.equals("NILL")){

            AlertDialog.Builder msg = new AlertDialog.Builder(Home.this);
            msg.setMessage(Html.fromHtml("<font>Your Email Address has been Set </font>"+"<font color='#D81B60'> 'NILL' </font>"+"<font> Initially </font><br><font>Navigate to 'Detail info' Section to Update Email !</font>"));
            msg.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent1 = new Intent(Home.this,patient_personal_detail.class);
                    //  intent1.putExtra();
                    intent1.putExtra("patientName",userdata);
                    intent1.putExtra("patient_username",patient_username);
                    intent1.putExtra("patient_email",patient_email1);
                    intent1.putExtra("patient_phone",patient_phone1);
                    startActivity(intent1);

                }
            });

            msg.create();
            msg.show();

        }



        TextView text_admin = (TextView) findViewById(R.id.txtAdmin);



        NavigationView navigationView = (NavigationView)  findViewById(R.id.navigation_drawer);


        text_admin.setText(userdata);

        url_class url_class = new url_class();
        final String URL;

        URL = url_class.url_function() + "patient_detail_get.php";

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    final String PatientEmail = jsonObject.getString("email");
                    final String PatientPh_No = jsonObject.getString("ph_no");
                    final String PatientBloodGroup = jsonObject.getString("blood_group");

                    //patient_email = PatientEmail;
                    //patient_phone = PatientPh_No;

                    //setPatient_email(PatientEmail);
                    //setPatient_phone(PatientPh_No);


                    progressDialog.dismiss();

                    lstItems = new ArrayList<>();
                    lstItems.add(new recycler_items("Temperature" ,R.drawable.head,userdata,patient_username,PatientEmail,PatientPh_No));
                    lstItems.add(new recycler_items("Room Temp" ,R.drawable.room,userdata,patient_username,PatientEmail,PatientPh_No));
                    lstItems.add(new recycler_items("Pulse Rate" ,R.drawable.pulse1,userdata,patient_username,PatientEmail,PatientPh_No));
                    lstItems.add(new recycler_items("Humidity" ,R.drawable.dew,userdata,patient_username,PatientEmail,PatientPh_No));
                    lstItems.add(new recycler_items("Air Quality" ,R.drawable.aq,userdata,patient_username,PatientEmail,PatientPh_No));
                    lstItems.add(new recycler_items("Position" ,R.drawable.lay_down,userdata,patient_username,PatientEmail,PatientPh_No));
                    lstItems.add(new recycler_items("Doctors" ,R.drawable.doc3,userdata,patient_username,PatientEmail,PatientPh_No));
                    lstItems.add(new recycler_items("Appointments" ,R.drawable.appointment,userdata,patient_username,PatientEmail,PatientPh_No));

                    lstItems.add(new recycler_items("Approved Appointments" ,R.drawable.appointment0,userdata,patient_username,PatientEmail,PatientPh_No));
                    
                    lstItems.add(new recycler_items("Detail Info" ,R.drawable.logo,userdata,patient_username,getPatient_email(),PatientPh_No));


                    RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
                    RecyclerViewAdapter myadapter = new RecyclerViewAdapter(Home.this, lstItems);

                    rv.setLayoutManager(new GridLayoutManager(Home.this,2));
                    rv.setAdapter(myadapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                AlertDialog.Builder message =  new AlertDialog.Builder(Home.this);

                message.setTitle("Information");
                message.setMessage("Check Your Internet Connection and try again !");
                message.setNegativeButton("OK",null);
                message.create();
                message.show();

            }
        }) {

            @Override
            public Map<String,String> getParams(){

                Map<String,String> params = new HashMap<>();

                params.put("username",patient_username);

                return params;
            }

        };

        final RequestQueue queue = Volley.newRequestQueue(Home.this);
        queue.add(stringRequest);


        try {

            queue.add(stringRequest);

        } catch (Exception e) {

            AlertDialog.Builder message =  new AlertDialog.Builder(Home.this);
            message.setMessage("Exception Error : " + e.getMessage() + "\nReload Again");
            message.setTitle("Information");
            message.setPositiveButton("Reload", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    queue.add(stringRequest);
                }
            });
            message.create();
            message.show();

        };



        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(this);

        View header_view = navigationView.getHeaderView(0);

        TextView textView = (TextView) header_view.findViewById(R.id.header_name);

        TextView textView1 = (TextView) header_view.findViewById(R.id.header_email);

        textView.setText(patient_username);
        textView1.setText(patient_email1);

        final  user_detail user_detail = new user_detail();

        user_detail.setEmail(patient_email1);
        user_detail.setPhone(patient_phone1);

        patient_email = user_detail.getEmail();
        patient_phone = user_detail.getPhone();

        text_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* AlertDialog.Builder msg = new AlertDialog.Builder(Home.this);
                msg.setMessage(patient_email+"\n"+patient_phone);
                msg.setNegativeButton("OK",null);
                msg.create();
                msg.show();


                */

                Intent intent1 = new Intent(Home.this,patient_personal_detail.class);
                intent1.putExtra("patientName",userdata);
                intent1.putExtra("patient_username",patient_username);
                intent1.putExtra("patient_email",patient_email);
                intent1.putExtra("patient_phone",patient_phone);
                startActivity(intent1);

            }
        });



        home_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Loading Screen Please Wait !", Toast.LENGTH_LONG).show();
                queue.add(stringRequest);
              //  rv.setLayoutManager(new GridLayoutManager(Home.this,2));
               // rv.setAdapter(myadapter);

                progressDialog.setTitle("Loading");
                progressDialog.setMessage("Loading Please Wait !");
                progressDialog.show();


            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        final  user_detail user_detail = new user_detail();

        Intent intent = getIntent();

        final String user_Name = intent.getExtras().getString("name");

        final String patient_username = intent.getExtras().getString("userName");

        final String patient_email1 = intent.getExtras().getString("email");

        final String patient_phone1 = intent.getExtras().getString("phone");

        final String patient_bloodGroup = intent.getExtras().getString("bloodGroup");

        final String patient_name = user_Name;
        //final String patient_username = user_detail.getUserName();
        patient_email = patient_email1;
        patient_phone = patient_phone1;

        int id = menuItem.getItemId();

        if(id == R.id.item_doctor){

            Toast.makeText(this,"Doctor Section",Toast.LENGTH_SHORT).show();

            Intent intent1 = new Intent(this,Doctor_Section.class);
            intent1.putExtra("patientName",patient_name);
            intent1.putExtra("patient_username",patient_username);
            intent1.putExtra("patient_email",patient_email);
            intent1.putExtra("patient_phone",patient_phone);
            startActivity(intent1);

       AlertDialog.Builder msg = new AlertDialog.Builder(this);
       msg.setMessage(patient_name+ "\n"+patient_username+ "\n"+patient_email+ "\n"+patient_phone+ "\n"+menuItem.toString());
       msg.setNegativeButton("OK",null);
       msg.create();
       msg.show();

        }

        if(id == R.id.item_appointment){

            Toast.makeText(this,"Appointment Section",Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(this,appointment.class);
            intent1.putExtra("patientName",patient_name);
            intent1.putExtra("patient_username",patient_username);
            intent1.putExtra("patient_email",patient_email);
            intent1.putExtra("patient_phone",patient_phone);
            startActivity(intent1);

            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage(patient_name+ "\n"+patient_username+ "\n"+patient_email+ "\n"+patient_phone+ "\n"+menuItem.toString());
            msg.setNegativeButton("OK",null);
            msg.create();
            msg.show();

        }

        DrawerLayout drawerLayout =  (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }


}
