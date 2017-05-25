package com.example.pedrolanzagorta.realmtest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedrolanzagorta.realmtest.R;
import com.example.pedrolanzagorta.realmtest.controllers.UsersController;
import com.example.pedrolanzagorta.realmtest.models.User;

import java.sql.SQLOutput;


public class SignInActivity extends AppCompatActivity {
    EditText etUserName,etPassword,etName,etRepeatPass;
    TextView tvUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        etUserName = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etName = (EditText) findViewById(R.id.etName);
        etRepeatPass = (EditText) findViewById(R.id.etRepeatPassword);
    }

    public void registerUser(View view){
        Button button = ((Button)view);
        String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        String repetedPassword = etRepeatPass.getText().toString();
        String name = etName.getText().toString();
        boolean isValidInput = username.length()>0 && password.length()>0 && username.split(" ").length==1 && password.split(" ").length==1;
        User user = new User(name,username,password);
        if (isValidInput && (password.equals(repetedPassword))&& UsersController.countUsername(username)==0) {
            UsersController.registerUser(user);
            Toast.makeText(this, "Registration succeess",Toast.LENGTH_SHORT ).show();
            finish();
        }
        else {
            String x =(isValidInput) ? ((password.equals(repetedPassword))?"Username already exists" : "Passwords don't match"):"Invalid input";
            String msg = (isValidInput) ? "Username already exists" : "Invalid input";
            Toast.makeText(this, x, Toast.LENGTH_SHORT ).show();


        }
    }





}


