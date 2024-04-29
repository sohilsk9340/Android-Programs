package com.example.loginpage;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second);

        button = findViewById(R.id.button);
        Toast.makeText(this, "Going to About Page", Toast.LENGTH_SHORT).show();
        button.setOnClickListener(e ->
                startActivity(new Intent(HomeActivity.this, MainActivity3.class)));
    }
}
