package com.example.test1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Patient_Temperature extends AppCompatActivity {

    public static String reading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__temperature);

        Intent intent = getIntent();

        final String heading = intent.getExtras().getString("heading");
        final String username = intent.getExtras().getString("patient_username");


        final class myclass {

            public String myfunction() {

                switch (heading) {
                    case "Temperature":
                        reading = "body_temp";
                        break;

                    case "Room Temp":
                        reading = "room_temp";

                        break;

                    case "Pulse Rate":
                        reading = "pulse";

                        break;

                    case "Humidity":
                        reading = "humidity";

                        break;

                    case "Air Quality":
                        reading = "air_quality";

                        break;
                    case "Position":
                        reading = "x_position";

                        break;
                }

                return reading;

            }



        }

        final myclass myclass = new myclass();



        final TextView header = (TextView)  findViewById(R.id.title_patientDetail);
        final TextView reading_line = (TextView) findViewById(R.id.readings_text);
        final TextView reading_line_value = (TextView) findViewById(R.id.readings_value);
        final TextView reading_date = (TextView) findViewById(R.id.readings_date);
        final TextView reading_date_value = (TextView) findViewById(R.id.readings_date_value);
        final TextView reading_time = (TextView) findViewById(R.id.readings_time);
        final TextView reading_time_value = (TextView) findViewById(R.id.readings_time_value);
        final Button refresh = (Button) findViewById(R.id.refresh);

        final Timer timer = new Timer();

        header.setText(heading);


        url_class url_class = new url_class();              //URL class definition

        final String URL;

        URL = url_class.url_function() + "get_sensor_data.php";


       final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {



                try {

                    header.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            AlertDialog.Builder msg = new AlertDialog.Builder(Patient_Temperature.this);
                            msg.setMessage(myclass.myfunction() + response);
                            msg.create();
                            msg.show();

                        }
                    });





                    final  GraphView graphView = (GraphView) findViewById(R.id.graph_temp);

                    final JSONArray jsonArray = new JSONArray(response);

                    int i;

                   final LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
                   final LineGraphSeries<DataPoint> series1 = new LineGraphSeries<DataPoint>();
                   final LineGraphSeries<DataPoint> series2 =  new LineGraphSeries<DataPoint>();

                   series.setDrawDataPoints(true);
                   series1.setDrawDataPoints(true);
                   series2.setDrawDataPoints(true);
                   series.setColor(Color.rgb(0, 133, 119));
                   series1.setColor(Color.rgb(216, 27, 96));
                   series2.setColor(Color.rgb(27, 137, 216));
                   series.setThickness(3);
                   series1.setThickness(3);
                   series2.setThickness(3);

                   series.setOnDataPointTapListener(new OnDataPointTapListener() {
                       @Override
                       public void onTap(Series series, DataPointInterface dataPoint) {

                           String point =  dataPoint.getX() + "0" ;

                           AlertDialog.Builder msg = new AlertDialog.Builder(Patient_Temperature.this);

                           double point_value = Double.parseDouble(point);

                           int int_value = (int) point_value;



                           try {


                               String new_date = jsonArray.getJSONObject(int_value).getString("date");
                               String new_time = jsonArray.getJSONObject(int_value).getString("time");
                               reading_line.setText(myclass.myfunction());
                               reading_line_value.setText(dataPoint.getY()+"");
                               reading_date.setText("Date ");
                               reading_date_value.setText(new_date);
                               reading_time.setText("Time ");
                               reading_time_value.setText(new_time);

                             //  msg.setMessage(myclass.myfunction()+" = "+dataPoint.getY() +"\n"+ "Date ="+ " "  +new_date+ "\n"+"Time ="+ " " + new_time );
                             //  msg.setNegativeButton("OK",null);
                             //  msg.create();
                              // msg.show();

                           } catch (JSONException e) {
                              e.printStackTrace();
                           }


                       }
                   });




                    graphView.getViewport().setScrollable(true);
                    graphView.getViewport().setScrollableY(true);
                    graphView.getViewport().setScalable(true);
                    graphView.getViewport().setScalableY(true);
                    graphView.getViewport().setXAxisBoundsManual(true);

                    graphView.getViewport().setMinX(0);
                    graphView.getViewport().setMaxX(jsonArray.length());
                    graphView.getViewport().setMaxXAxisSize(jsonArray.length()+ 3);

                    graphView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            graphView.getViewport().setXAxisBoundsManual(true);
                            graphView.getViewport().setMinX(0);
                            graphView.getViewport().setMaxX(jsonArray.length());
                            graphView.getViewport().setMaxXAxisSize(jsonArray.length()+ 3);

                        }
                    });

                    for(i=0;i<jsonArray.length();i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        final String parameter = jsonObject.getString(myclass.myfunction());
                        final String y = jsonObject.getString("y_position");
                        final String z = jsonObject.getString("z_position");

                        final float param_float = Float.parseFloat(parameter);
                        final float param_float1 = Float.parseFloat(y);
                        final float param_float2 = Float.parseFloat(z);


                        final String date = jsonObject.getString("date");
                        final String time = jsonObject.getString("time");

                        final int n = jsonArray.length();


                        if(jsonArray.length()-i == 1)
                        {
                        reading_line.setText(myclass.myfunction());
                        reading_line_value.setText(parameter);
                        reading_date.setText("Date ");
                        reading_date_value.setText(date);
                        reading_time.setText("Time ");
                        reading_time_value.setText(time);
                        }
                        series.appendData(new DataPoint(i,param_float),true,jsonArray.length());

                        if(heading.equals("Position")){

                            series1.appendData(new DataPoint(i,param_float1),true,jsonArray.length());
                            series2.appendData(new DataPoint(i,param_float2),true,jsonArray.length());

                        }


                        if(jsonArray.length()- i == 1) {
                            graphView.addSeries(series);
                            if(heading.equals("Position")){

                                graphView.addSeries(series1);
                                graphView.addSeries(series2);

                            }
                        }
                    }



                    } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override

            public Map<String,String> getParams(){

                Map<String,String> params = new HashMap<String,String>();

                params.put("patient_username",username);

                return params;

            }

        };



       final RequestQueue queue = Volley.newRequestQueue(Patient_Temperature.this);
        queue.add(stringRequest);



        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue.add(stringRequest);
            }
        });

        //timer.schedule(new TimerTask() {
       //     @Override
       //     public void run() {
             //   queue.add(stringRequest);
       //     }
       // }, 2000);



}

public DataPoint[] getdatapoint(){

        DataPoint[] dataPoints = new DataPoint[]{


                new DataPoint(1,96),
                new DataPoint(2,93),
                new DataPoint(3,95),
                new DataPoint(4,93),
                new DataPoint(5,98),
                new DataPoint(6,97),
                new DataPoint(7,94),
                new DataPoint(8,96),
                new DataPoint(9,95),
                new DataPoint(10,92),



        };

        return  dataPoints;


}

}
