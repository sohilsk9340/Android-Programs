package com.example.loginpage;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text;
    Button button;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second);

        button = findViewById(R.id.button);
        Toast.makeText(this, "Going to About Page", Toast.LENGTH_SHORT).show();
        button.setOnClickListener(e ->
                startActivity(new Intent(HomeActivity.this, MainActivity3.class)));
        text = findViewById(R.id.textView2);

        btn = findViewById(R.id.fetch);

//        try {
//            facts();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }
    public void facts() throws IOException {
        URL url = new URL("https://meowfacts.herokuapp.com/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }


        System.out.println(content.toString());
        text.setText(content.toString());

        Toast.makeText(HomeActivity.this, "called the api", Toast.LENGTH_LONG).show();

        con.disconnect();
        in.close();
    }

//    public String getFacts() throws IOException {
//        RequestQueue volleyQueue = Volley.newRequestQueue(homeActivity.this);
//
//        String url = "https://meowfacts.herokuapp.com/";
//        JsonObjectRequest json = new JsonObjectRequest(url,(Response.Listener<JSONObject>) res -> {
//            try {
//                JSONArray ja = res.getJSONArray("data");
//
//                String fact = ja.getString(0);
//
//                tv.setText(fact);
//
//            } catch (JSONException e) {
//                throw new RuntimeException(e);
//            }
//        } , (Response.ErrorListener) error -> {
//            Toast.makeText(homeActivity.this, "Some error occurred! Cannot fetch fact", Toast.LENGTH_LONG).show();
//            // log the error message in the error stream
//        } );
//
//        volleyQueue.add(json);
//        return null;
//    }

    public void checkApi(String uri) throws IOException {
        URL url = new URL(uri);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            StringBuilder s = new StringBuilder();
            while(in.read() != -1) {
                s.append((char) in.read());
            }

            text.setText(s.toString());
        } finally {
            urlConnection.disconnect();
        }
    }
    public void httpCall(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                (Response.Listener<String>) res -> {
                    text.setText(res);
                    btn.setText(res);
                    Toast.makeText(HomeActivity.this, "fetched", Toast.LENGTH_LONG).show();
                }, (Response.ErrorListener) err -> {
            Toast.makeText(HomeActivity.this, "error", Toast.LENGTH_LONG).show();
        });

        queue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        final String apiurl = "https://cataas.com/cat/says/Aaryan";
        final String apiurl2 = "https://meowfacts.herokuapp.com/";
        if(view.getId() == btn.getId()) {
            try {
                // httpCall(apiurl2);
                checkApi(apiurl2);
                // String response = getFacts();
                // System.out.println(response);
                //tv.setText(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
