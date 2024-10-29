package com.example.loginapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.loginapp.R;

public class WelcomeActivity extends AppCompatActivity {

    private TextView welcomeText;
    private Button misResenasButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeText = findViewById(R.id.welcomeText);
        misResenasButton = findViewById(R.id.misResenasButton);

        // Obtener el token del SharedPreferences
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String token = prefs.getString("token", "No token found");

        welcomeText.setText("Welcome! Your token is: " + token);

        misResenasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MisResenasActivity.class);
                startActivity(intent);
            }
        });
    }
}
