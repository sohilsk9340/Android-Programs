package com.example.carinsurance;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FileClaim extends AppCompatActivity {

    private EditText editTextDate;
    private EditText editTextLocation;
    private EditText editTextDamageDesc;
    private EditText editTextAdditionalInfo;
    private Button buttonSubmitClaim;

    // API Endpoint URL
    private static final String API_URL = "https://car-insurance-api.onrender.com/fileClaim";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_claim);

        editTextDate = findViewById(R.id.editTextDate);
        editTextLocation = findViewById(R.id.editTextLocation);
        editTextDamageDesc = findViewById(R.id.editTextDescription);
        editTextAdditionalInfo = findViewById(R.id.editTextAdditionalInfo);
        buttonSubmitClaim = findViewById(R.id.buttonSubmitClaim);

        buttonSubmitClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String date = editTextDate.getText().toString();
                String location = editTextLocation.getText().toString();
                String damageDesc = editTextDamageDesc.getText().toString();
                String additionalInfo = editTextAdditionalInfo.getText().toString();

                // Create JSON object for the claim data
                JSONObject claimData = new JSONObject();
                try {
                    claimData.put("date", date);
                    claimData.put("location", location);
                    claimData.put("damageDesc", damageDesc);
                    claimData.put("additionalInfo", additionalInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Make API request to file the claim
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API_URL, claimData,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle successful response
                                Toast.makeText(FileClaim.this, "File Claim Succesfull", Toast.LENGTH_SHORT).show();
                                Log.d("API Response", response.toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error response
                                Toast.makeText(FileClaim.this, "Error filing claim: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("API Error", error.toString());
                            }
                        });

                // Add the request to the request queue
                RequestQueue requestQueue = Volley.newRequestQueue(FileClaim.this);
                requestQueue.add(request);
            }
        });
    }
}
