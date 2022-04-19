package com.example.nuha_midt2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    DatabaseHelper myData;
    String id_n,name_n,surname_n,nationlID_n;

    EditText id,name,surname,nationlID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myData = new DatabaseHelper(this);
        id=(EditText) findViewById(R.id.id);
        name=(EditText) findViewById(R.id.name);
        surname=(EditText) findViewById(R.id.surname);
        nationlID=(EditText) findViewById(R.id.nationalid);
        Button insert = (Button) findViewById(R.id.insert);
        Button abttn1 = (Button) findViewById(R.id.goa1);
        Button abttn3 = (Button) findViewById(R.id.goa3);


        //add button listener
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_n=id.getText().toString();
                name_n=name.getText().toString();
                surname_n=surname.getText().toString();
                nationlID_n=nationlID.getText().toString();

                //log
                Log.d("Nuha", "insert success");

                myData.addData(id_n,name_n,surname_n,nationlID_n);
                Toast.makeText(MainActivity2.this, "insertion successfull", Toast.LENGTH_SHORT).show();
            }
        });




        //view button listener
        abttn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MainActivity.class));

            }
        });



        //delete button lister
        abttn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MainActivity3.class));
            }
        });
    }
}