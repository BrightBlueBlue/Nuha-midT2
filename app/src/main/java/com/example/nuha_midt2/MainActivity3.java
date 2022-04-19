package com.example.nuha_midt2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    DatabaseHelper myData;
    String id_n,name_n,surname_n,nationlID_n;
    int num_val;
    EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myData = new DatabaseHelper(this);
        id=(EditText) findViewById(R.id.id2);
        Button view = (Button) findViewById(R.id.viewdata);
        Button delete = (Button) findViewById(R.id.deletedata);


        //add button listener



        //view button listener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = myData.Viewinfo();
                StringBuffer buffer = new StringBuffer();
                while (cur.moveToNext()){
                    buffer.append("id:"+cur.getString(0)+"\n");
                    buffer.append("name:"+cur.getString(1)+"\n");
                    buffer.append("surname:"+cur.getString(2)+"\n\n");
                    buffer.append("NatioanID No.:"+cur.getString(3)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setCancelable(true);
                builder.setTitle("All User Data");
                builder.setMessage(buffer.toString());
                builder.show();
                Toast.makeText(MainActivity3.this,"Successful View",Toast.LENGTH_LONG).show();
            }
        });



        //delete button lister
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myData.delete(name_n);
                Toast.makeText(MainActivity3.this,"Successful Delete",Toast.LENGTH_LONG).show();
                //log
                Log.d("Nuha", "delete success");
            }
        });
    }
}