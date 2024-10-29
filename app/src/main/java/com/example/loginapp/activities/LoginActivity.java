package com.example.loginapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginapp.R;
import com.example.loginapp.api.ApiService;
import com.example.loginapp.models.LoginRequest;
import com.example.loginapp.models.User;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);

        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://backend-mercado-libro-mobile.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        LoginRequest loginRequest = new LoginRequest(email, password);
        Call<User> call = apiService.loginUser(loginRequest);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String accessToken = response.body().getAccess();

                    // Guardar el token de acceso y el email en SharedPreferences
                    getSharedPreferences("MyPrefs", MODE_PRIVATE)
                            .edit()
                            .putString("token", accessToken)
                            .putString("usuario_email", email) // Guardar el email correctamente
                            .apply();

                    Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
                }

                // Para depurar: verificar el email guardado
                String emailUsuario = getSharedPreferences("MyPrefs", MODE_PRIVATE).getString("usuario_email", null);
                Log.d("LoginActivity", "Email del usuario logueado: " + emailUsuario);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("LoginActivity", "Login failed", t);
                Toast.makeText(LoginActivity.this, "Login failed. Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
