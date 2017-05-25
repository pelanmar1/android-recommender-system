package com.example.pedrolanzagorta.realmtest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedrolanzagorta.realmtest.R;
import com.example.pedrolanzagorta.realmtest.controllers.UsersController;
import com.example.pedrolanzagorta.realmtest.models.User;
import com.example.pedrolanzagorta.realmtest.sessionInstances.ActiveUser;

/**
 * Created by Pedro Lanzagorta on 07/07/2016.
 */
public class LogInActivity extends AppCompatActivity{
    EditText etUsername,etPassword;
    TextView tvUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

    }
    public void logInUser(View view) {
        Button button = ((Button)view);
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        boolean isValidInput = username.length()>0 && password.length()>0 && username.split(" ").length==1 && password.split(" ").length==1;
        if (isValidInput && UsersController.verifyUser(username,password)==1) {
            ActiveUser.setActiveUser(username);
            Toast.makeText(this,"Welcome "+ etUsername.getText(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ProductViewerActivity.class));
            etUsername.setText("");
            etPassword.setText("");
        }
        else{
            String msg = (isValidInput) ? "Invalid username or password" : "Invalid input";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT ).show();
        }

    }

}
