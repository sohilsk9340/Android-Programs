package com.example.loginpage;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button btn;

    EditText username,password;

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

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    @SuppressLint("SetText")
    @Override
    public void onClick(View view) {
        if(view == btn){
            String usr = username.getText().toString();
            String psw = password.getText().toString();

            if (usr.equals("sohil")  && psw.equals("123")){
                Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_LONG).show();

                startActivity(new Intent(MainActivity.this, HomeActivity.class));// Navigate to a new Page
            }else {
                Toast.makeText(MainActivity.this, "Incorrect Credentials", Toast.LENGTH_LONG).show();
            }
        }
    }
}