package com.example.jaison.phpserver_test;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private EditText name;
    private Button send;
    private TextView textView;
    private RequestQueue mRequest;
    private StringRequest stringRequest;
    private String url="http://192.168.1.105:8000/AndroidServer/Server1.php";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.editText);
        send = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestAndPrintResponse();
                


            }
        });

    }

    private void sendRequestAndPrintResponse() {
        mRequest= Volley.newRequestQueue(this);

        stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.i(TAG,"Response: "+response.toString());
                Toast.makeText(MainActivity.this,"Response: "+response.toString(),Toast.LENGTH_SHORT).show();
                

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.i(TAG,"Error : "+error.toString());
                Toast.makeText(MainActivity.this,"Error: "+error.toString(),Toast.LENGTH_SHORT).show();
                
            }
        });
        mRequest.add(stringRequest);

    }
}