package com.raekyo.carracing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class SignIn extends AppCompatActivity {

    EditText txtUsername, txtPassword;
    Button btnLogin;

    User[] userList = {
            new User("RaeKyo", "123", 200),
            new User("User1", "123", 100),
            new User("User2", "123", 500)
    };

    User userLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        bindingSource();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                // validate account exist
                for (User user: userList) {
                    if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        userLogin = user;
                    }
                }

                // login fail
                if(userLogin == null) {
                    Toast.makeText(SignIn.this, "Username or Password is not correct", Toast.LENGTH_SHORT).show();
                    return;
                }

                // login successful
                Toast.makeText(SignIn.this, "Welcome back \"" + userLogin.getUsername() + "\"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignIn.this, Betting.class);
                intent.putExtra("userLogin", (Serializable) userLogin);
                intent.putExtra("bettingData", (Serializable) new BettingData(userLogin.getMoney(), 0,0,0));

                startActivity(intent);
            }
        });
    }

    private void bindingSource() {
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }
}