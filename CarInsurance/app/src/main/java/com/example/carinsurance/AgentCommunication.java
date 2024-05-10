package com.example.carinsurance;

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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AgentCommunication extends AppCompatActivity {
    EditText editTextMessage;
    Button button;
    final String API_URL = "https://car-insurance-api.onrender.com/contact";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.agent_communication);

        editTextMessage = findViewById(R.id.editTextMessage);
        button = findViewById(R.id.buttonSend);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String concern = editTextMessage.getText().toString();

                JSONObject agentCommunication = new JSONObject();
                try {
                    agentCommunication.put("concern", concern);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API_URL, agentCommunication,
                        new Response.Listener<JSONObject>(){

                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Toast.makeText(AgentCommunication.this, "Your Concern Recieved", Toast.LENGTH_SHORT).show();
                            }

                        },
                        new Response.ErrorListener(){

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(AgentCommunication.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                RequestQueue requestQueue = Volley.newRequestQueue(AgentCommunication.this);
                requestQueue.add(request);

            }
        });
    }
}