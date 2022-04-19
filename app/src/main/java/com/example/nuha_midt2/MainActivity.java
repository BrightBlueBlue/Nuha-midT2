package com.example.nuha_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // we"ll make HTTP request to this URL to retrieve weather conditions
    String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=london&appid=ec00986f68ad6ce7f6c39e980fffaa46&units=metric";

    // Textview to show temperature and description
    TextView Temperature, Description, Humidity;

    Button button2,a2bttn;
    Spinner spinner;
    JSONObject jsonObj;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Temperature = (TextView) findViewById(R.id.temprature);
        Humidity = (TextView) findViewById(R.id.humidity);
        a2bttn = (Button)findViewById(R.id.gota2); //change the city
        button2 = (Button)findViewById(R.id.button2);

        //calendar to pick a date then view the humidity and temprature of the city



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        String place = spinner.getSelectedItem().toString();


        DatePicker simpleDatePicker = (DatePicker) findViewById(R.id.simpleDatePicker);
        weather(weatherURL);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=";
                weatherURL = weatherURL + place + "&appid=ec00986f68ad6ce7f6c39e980fffaa46&units=metric";
                weather(weatherURL);

            }
        });

        a2bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));

            }
        });
    }




    public void weather(String url) {



        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Nuha", "Responce success");
                        Log.d("Nuha", response.toString());



                        try {

                            JSONObject jsonMain = response.getJSONObject("main");


                            // there is another way to do it


                            String town = response.getString("name");
                            Description.setText(town);


                            double temp = jsonMain.getDouble("temp");
                            Log.d("Nuha-temp", String.valueOf(temp));
                            Temperature.setText(String.valueOf(temp));

                            double humi = jsonMain.getDouble("humidity");
                            Log.d("Nuha-humi", String.valueOf(humi));
                            Humidity.setText(String.valueOf(humi));




                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Reciever Error", e.toString());

                        }


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Nuha","Responce Error");

            }
        }
        );



        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);

    }





}
