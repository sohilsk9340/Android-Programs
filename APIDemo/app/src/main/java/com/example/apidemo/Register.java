package com.example.apidemo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import com.example.apidemo.models.DataModels;
import com.example.apidemo.network.ApiService;
import com.example.apidemo.network.RetrofitClient;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity implements View.OnClickListener {
    TextView title;
    TextView desc;
    TextView owner;
    Button button;
    private ProgressDialog progressDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        title = findViewById(R.id.name);
        desc = findViewById(R.id.emial);
        owner= findViewById(R.id.owner);
        button = findViewById(R.id.btnr);

        progressDialog = new ProgressDialog(this);

        button.setOnClickListener(this);

    }

    private void sendData() {

        progressDialog.setMessage("Post Data in Progress......");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        final DataModels dataModels = new DataModels(title.getText().toString(),
                desc.getText().toString(),owner.getText().toString());

        Call<DataModels> call = apiService.postData(dataModels);

        call.enqueue(new Callback<DataModels>() {
            @Override
            public void onResponse(Call<DataModels> call, Response<DataModels> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){
                    Toast.makeText(Register.this, "Sucess", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(Register.this, "Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DataModels> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        sendData();
    }
}