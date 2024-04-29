package com.example.quizapp;

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

public class MainActivity extends AppCompatActivity {
    TextView text ;
    Button yes;
    Button no;

    private String[] questions = {"Is Java introduced in 1995?","Founder of Java is James Goling",
            "In an instance method or a constructor, \"this\" is a reference to the current object",
    "Constructor overloading is not possible in Java.",};
    private boolean[] answers = {true,true,true,false};
    private int score = 0;
    private int index = 0;

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

        text = findViewById(R.id.question);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        text.setText(questions[index]);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (answers[index] == true){
                    score++;
                }
                index++;
                if (index <= questions.length - 1){
                    text.setText(questions[index]);
                }else {
                    Toast.makeText(MainActivity.this, "Your Score is: "+score, Toast.LENGTH_SHORT).show();
                }

            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (answers[index] == false){
                    score++;
                }
                index++;
                if (index <= questions.length - 1){
                    text.setText(questions[index]);
                }else {
                    Toast.makeText(MainActivity.this, "Your Score is: "+score, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}