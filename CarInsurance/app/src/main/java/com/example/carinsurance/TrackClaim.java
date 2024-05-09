package com.example.carinsurance;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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

public class TrackClaim extends AppCompatActivity {

    // API Endpoint URL
    private static final String API_URL = "https://car-insurance-api.onrender.com/tackClaim";

    private TextView textViewClaimId;
    private TextView textViewDate;
    private TextView textViewDescription;
    private TextView textViewClaimStatus;
    private TextView textViewProgressUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_claim);

        textViewClaimId = findViewById(R.id.textViewClaimID);
        textViewDate = findViewById(R.id.textViewDateSubmitted);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewClaimStatus = findViewById(R.id.textViewClaimStatus);
        textViewProgressUpdate = findViewById(R.id.textViewProgressUpdates);

        // Fetch claim details from API
        fetchClaimDetails();
    }

    private void fetchClaimDetails() {
        // Make API request to fetch claim details
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parse JSON response and update UI
                            String claimId = response.getString("claimId");
                            String date = response.getString("date");
                            String description = response.getString("description");
                            String claimStatus = response.getString("claimStatus");
                            String progressUpdate = response.getString("progressUpdate");

                            // Update UI with fetched data
                            textViewClaimId.setText("Claim ID: " + claimId);
                            textViewDate.setText("Date: " + date);
                            textViewDescription.setText("Description: " + description);
                            textViewClaimStatus.setText("Claim Status: " + claimStatus);
                            textViewProgressUpdate.setText("Progress Update: " + progressUpdate);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TrackClaim.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TrackClaim.this, "Error fetching claim details: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("API Error", error.toString());
                    }
                });

        // Add the request to the request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
