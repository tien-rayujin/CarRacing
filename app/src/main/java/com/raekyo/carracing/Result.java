package com.raekyo.carracing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.raekyo.carracing.listView.RacingResult;

import java.io.Serializable;

public class Result extends AppCompatActivity {

    Button btnPlayAgain, btnLogout;
    TextView txtGameResult;

    User userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bindingSource();

        Intent intent = getIntent();
        RacingResult res = (RacingResult) intent.getSerializableExtra("racingResult");
        userLogin = (User) intent.getSerializableExtra("userLogin");
        int totalScore = res.getTotalMoney();

        txtGameResult.setText("Your score is: " + totalScore);

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // reset money to play gain
                userLogin.setMoney(100);
                Intent bettingIntent = new Intent(Result.this, Betting.class);
                bettingIntent.putExtra("userLogin", (Serializable) userLogin);
                bettingIntent.putExtra("bettingData", (Serializable) new BettingData(userLogin.getMoney(), 0, 0, 0));
                startActivity(bettingIntent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userLogin != null) {
                    // toast
                    Toast.makeText(Result.this, "See you again next time !!!", Toast.LENGTH_SHORT).show();

                    Intent loginIntent = new Intent(Result.this, SignIn.class);
                    startActivity(loginIntent);
                }
            }
        });
    }

    private void bindingSource() {
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        txtGameResult = (TextView) findViewById(R.id.txtGameResult);
    }
}