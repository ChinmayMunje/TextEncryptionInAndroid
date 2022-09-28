package com.gtappdevelopers.textencryptionjava;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DecryptionActivity extends AppCompatActivity {

    private TextInputEditText encryptedEdt;
    private Button decryptBtn;
    private TextView decryptedTV;
    private String key = "ABHGFHJKOLKIJHMJ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);
        encryptedEdt = findViewById(R.id.idEdtEncryptedMsg);
        decryptBtn = findViewById(R.id.idBtnDecrypt);
        decryptedTV = findViewById(R.id.idTVDecryptedData);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        decryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (encryptedEdt.getText().toString().isEmpty()) {
                    Toast.makeText(DecryptionActivity.this, "Please specify encrypted data..", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        decryptedTV.setText(decryptMsg(encryptedEdt.getText().toString(), secretKeySpec));
                    } catch (Exception e) {
                        Toast.makeText(DecryptionActivity.this, "Please enter valid encrypted data..", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public String decryptMsg(String cipherText, SecretKey secret)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidParameterSpecException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] decode = Base64.decode(cipherText, Base64.NO_WRAP);
        String decryptString = new String(cipher.doFinal(decode), "UTF-8");
        return decryptString;
    }

}