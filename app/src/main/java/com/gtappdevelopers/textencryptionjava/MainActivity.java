package com.gtappdevelopers.textencryptionjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout encryptButton, decryptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encryptButton = findViewById(R.id.idLLEncrypt);
        decryptButton = findViewById(R.id.idLLDecrypt);
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, EncryptionActivity.class);
                startActivity(i);
            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DecryptionActivity.class);
                startActivity(i);

            }
        });
    }
}