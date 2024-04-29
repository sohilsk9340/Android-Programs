package com.example.multiscrrendemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText name;
    public static final String EXTRA_NAME = "com.example.multiscrrendemo.extra_NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name = findViewById(R.id.name);
    }

    public void openActivity(View view){
        Toast.makeText(this, "Openning Second Activity", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity2.class);

        name = findViewById(R.id.name);
        String newName = name.getText().toString();
        intent.putExtra(EXTRA_NAME, newName);
        startActivity(intent);
    }
}