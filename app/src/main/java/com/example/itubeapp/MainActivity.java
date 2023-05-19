package com.example.itubeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.itubeapp.data.User;
import com.example.itubeapp.data.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button login, signup;
    EditText username, password;
    UserViewModel userViewModel;
    LiveData<List<User>> users;
    int pos;
    boolean loginCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.button);
        signup = findViewById(R.id.button2);
        username = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        users = userViewModel.getUsers();
        userViewModel.getUsers().observe(this, users -> {
            Log.println(Log.DEBUG, "tag", "items inline count = " + users.stream().count());
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int num = 0; num < users.getValue().size(); num++) {
                    if (username.getText().toString().equals(users.getValue().get(num).getUsername())) {
                        if (password.getText().toString().equals(users.getValue().get(num).getPassword())) {
                            loginCheck = true;
                            pos = num;
                        }
                    }
                }
                if (loginCheck) {
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    i.putExtra("user", users.getValue().get(pos));
                    startActivity(i);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(MainActivity.this, SignUpActivity.class);
                startActivityForResult(j, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                User user = new User(0, data.getStringExtra("name"), data.getStringExtra("username"), data.getStringExtra("password"));
                userViewModel.insert(user);
            }
        }
    }
}