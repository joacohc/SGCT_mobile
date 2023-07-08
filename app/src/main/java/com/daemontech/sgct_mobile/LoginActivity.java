package com.daemontech.sgct_mobile;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daemontech.sgct_mobile.api.LoginController;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loginController = new LoginController(this, "http://192.168.1.13:8080/AEWeb3/rest/usuarios/logueo");

        loginButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Ingrese el usuario y la contraseña", Toast.LENGTH_SHORT).show();
                return;
            }

            loginController.login(username, password, new LoginController.LoginCallback() {
                @Override
                public void onSuccess(String username,String role) {
                    // Iniciar la actividad principal

                    startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                    finish(); // Optional: Close the login activity to prevent going back
            }

                @Override
                public void onError(String message) {
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña inválidos", Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
