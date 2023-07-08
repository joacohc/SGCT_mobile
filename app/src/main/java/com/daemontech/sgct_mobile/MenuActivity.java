package com.daemontech.sgct_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    private Button btnAltaTernera;
    private Button btnListarTerneras;
    private Button btnListarEnfPadecidas;

    private ImageButton btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnListarTerneras = findViewById(R.id.btnListarTerneras);
        btnListarEnfPadecidas = findViewById(R.id.btnListarEnfPadecidas);
        btnLogOut = findViewById(R.id.logoffButton);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the TernerasActivity
                startActivity(new Intent(MenuActivity.this, LoginActivity.class));
            }
        });

        btnListarTerneras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the TernerasActivity
                startActivity(new Intent(MenuActivity.this, ListarTernerasActivity.class));
            }
        });

        btnListarEnfPadecidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the EnfTerVarActivityActivity
                startActivity(new Intent(MenuActivity.this, EnfTerVarActivity.class));
            }
        });
    }
}