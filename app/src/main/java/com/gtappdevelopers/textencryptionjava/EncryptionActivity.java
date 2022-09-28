package com.gtappdevelopers.textencryptionjava;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionActivity extends AppCompatActivity {

    private TextInputEditText msgEdt;
    private TextView encryptedDataTV;
    private Button encryptBtn, copyBtn;
    private String key = "ABHGFHJKOLKIJHMJ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);
        msgEdt = findViewById(R.id.idEdtMsg);
        encryptedDataTV = findViewById(R.id.idTVEncryptedData);
        encryptBtn = findViewById(R.id.idBtnEncrypt);
        copyBtn = findViewById(R.id.idBtnCopyEncryptedData);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        copyBtn.setEnabled(false);
        encryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (msgEdt.getText().toString().isEmpty()) {
                    Toast.makeText(EncryptionActivity.this, "Please enter data to encrypt..", Toast.LENGTH_SHORT).show();
                } else {
                    copyBtn.setEnabled(true);
                    try {
                        encryptedDataTV.setText(encryptMsg(msgEdt.getText().toString(), secretKeySpec));
                    } catch (Exception e) {
                        Log.e("TAG", "Message is : " + e);
                        Toast.makeText(EncryptionActivity.this, "Fail to encrypt text..", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        });

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", encryptedDataTV.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(EncryptionActivity.this, "Copied to clipboard..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String encryptMsg(String message, SecretKey secret)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] cipherText = cipher.doFinal(message.getBytes("UTF-8"));
        return Base64.encodeToString(cipherText, Base64.NO_WRAP);
    }

}