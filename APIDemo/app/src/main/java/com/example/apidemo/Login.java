package com.example.apidemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText username;
    EditText pass;
    Button btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

    }

    public void login() throws JSONException {
        // 1. get the data from input text --> username, pass
        final String emailBody = username.getText().toString();
        final String passBody = pass.getText().toString();

        // hosted url --> POST
        String url = "https://to-do-node-js-production.up.railway.app/login";

        // 2. Request Queue
        RequestQueue reqQueue = Volley.newRequestQueue(this);

        // POST, URL, res -> Toast, err -> Toast {Body<String, String> map}
        StringRequest sreq = new StringRequest(Request.Method.POST, url,
                res -> Toast.makeText(getApplicationContext(), "Response: " + res, Toast.LENGTH_LONG).show(),
                err -> Toast.makeText(getApplicationContext(), "Error: " + err, Toast.LENGTH_LONG).show())
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("username", emailBody);
                params.put("pass", passBody);

                return params;
            }
        };

        // 3. Request Queue
        reqQueue.add(sreq);
    }



//    public void getData() {
//        String url = "https://to-do-node-js-production.up.railway.app/name/sohil";
//        // String url = "https://catfact.ninja/fact";
//        RequestQueue reqQueue = Volley.newRequestQueue(this);
//
//        StringRequest sreq = new StringRequest(Request.Method.GET, url,
//                res -> {
//                    //Toast.makeText(getApplicationContext(), "Response: " + res.toString(), Toast.LENGTH_LONG).show()
//                    // tv.setText(res.toString());
//                },
//                err -> Toast.makeText(getApplicationContext(), "Error: " + err, Toast.LENGTH_LONG).show());
//
//        reqQueue.add(sreq);
//    }

    @Override
    public void onClick(View view) {
//        final String apiurl = "https://cataas.com/cat/says/Aaryan";
//        final String apiurl2 = "https://meowfacts.herokuapp.com/";
        if(view == btn) {
            String usr = username.getText().toString();
            String psw = pass.getText().toString();

            if (usr.equals("sohil") && psw.equals("123")) {
                Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_LONG).show();

                startActivity(new Intent(Login.this, Register.class));// Navigate to a new Page
            } else {
                Toast.makeText(Login.this, "Incorrect Credentials", Toast.LENGTH_LONG).show();
            }
        }
    }

}