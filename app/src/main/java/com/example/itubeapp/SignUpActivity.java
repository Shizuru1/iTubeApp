package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    EditText name, username, password, confirm;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.editTextTextPersonName2);
        username = findViewById(R.id.editTextTextPersonName3);
        password = findViewById(R.id.editTextTextPassword2);
        confirm = findViewById(R.id.editTextTextPassword3);
        button = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().equals(confirm.getText().toString())) {
                    Intent i = new Intent();
                    i.putExtra("name", name.getText().toString());
                    i.putExtra("username", username.getText().toString());
                    i.putExtra("password", password.getText().toString());
                    i.putExtra("confirm", confirm.getText().toString());
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });
    }
}