package com.raekyo.carracing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.raekyo.carracing.listView.PlayHistory;

import java.io.Serializable;
import java.util.ArrayList;

public class Betting extends AppCompatActivity {
    TextView txtCarBetResult1, txtCarBetResult2, txtCarBetResult3;
    Button btnCar1Bet, btnCar1UnBet;
    Button btnCar2Bet, btnCar2UnBet;
    Button btnCar3Bet, btnCar3UnBet;
    Button btnGotoColosseum;

    TextView txtCurrency;
    Spinner spinnerCoinRatio;

    //============================================================================//
    private BettingData bettingData;
    private String[] coinRatioOptions = {"10", "20", "50", "All-in"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betting);

        bindingSource();

        Intent intent = getIntent();
        bettingData = (BettingData) intent.getSerializableExtra("bettingData");

        if(bettingData == null)
            bettingData = new BettingData(100, 0, 0, 0);
        else {
            // reset bet coin
            bettingData.setBetCar1(0);
            bettingData.setBetCar2(0);
            bettingData.setBetCar3(0);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, coinRatioOptions);
        spinnerCoinRatio.setAdapter(adapter);

        registerEvent();

        updateUI();
    }

    private void bindingSource() {
        txtCarBetResult1 = (TextView) findViewById(R.id.txtCarBetResult1);
        txtCarBetResult2 = (TextView) findViewById(R.id.txtCarBetResult2);
        txtCarBetResult3 = (TextView) findViewById(R.id.txtCarBetResult3);

        btnCar1Bet = (Button) findViewById(R.id.btnCar1Bet);
        btnCar1UnBet = (Button) findViewById(R.id.btnCar1UnBet);
        btnCar2Bet = (Button) findViewById(R.id.btnCar2Bet);
        btnCar2UnBet = (Button) findViewById(R.id.btnCar2UnBet);
        btnCar3Bet = (Button) findViewById(R.id.btnCar3Bet);
        btnCar3UnBet = (Button) findViewById(R.id.btnCar3UnBet);

        btnGotoColosseum = (Button) findViewById(R.id.btnGotoColosseum);
        txtCurrency = (TextView) findViewById(R.id.txtCurrency);
        spinnerCoinRatio = (Spinner) findViewById(R.id.spinnerCoinRatio);
    }

    private void registerEvent() {
        btnCar1Bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBetCar1Click();
            }
        });
        btnCar2Bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBetCar2Click();
            }
        });
        btnCar3Bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBetCar3Click();
            }
        });


        btnCar1UnBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUnBetCar1Click();
            }
        });
        btnCar2UnBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUnBetCar2Click();
            }
        });
        btnCar3UnBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUnBetCar3Click();
            }
        });

        //----------------GotoColosseum----------------//
        btnGotoColosseum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGoToColosseumClick(view);
            }
        });
    }

    private void updateUI() {
        txtCurrency.setText(bettingData.getCurrency() + "$");
        txtCarBetResult1.setText("Bet: " + bettingData.getBetCar1() + "$");
        txtCarBetResult2.setText("Bet: " + bettingData.getBetCar2() + "$");
        txtCarBetResult3.setText("Bet: " + bettingData.getBetCar3() + "$");
    }

    //----------------------------------------------------------------------------//
    private void onBetCar1Click() {
        int currentBet = bettingData.getBetCar1();
        int coinRatioValue = getSelectedCoinRatioValue();

        if(bettingData.getCurrency() < coinRatioValue) {
            // don't have enough money to bet
            Toast.makeText(this, "Don't have enough money to bet", Toast.LENGTH_SHORT).show();
            return;
        } else {
            bettingData.setBetCar1(currentBet + coinRatioValue);
            bettingData.setCurrency(bettingData.getCurrency() - coinRatioValue);
        }
        updateUI();
    }

    private void onBetCar2Click() {
        int currentBet = bettingData.getBetCar2();
        int coinRatioValue = getSelectedCoinRatioValue();

        if(bettingData.getCurrency() < coinRatioValue) {
            // don't have enough money to bet
            Toast.makeText(this, "Don't have enough money to bet", Toast.LENGTH_SHORT).show();
            return;
        } else {
            bettingData.setBetCar2(currentBet + coinRatioValue);
            bettingData.setCurrency(bettingData.getCurrency() - coinRatioValue);
        }
        updateUI();
    }

    private void onBetCar3Click() {
        int currentBet = bettingData.getBetCar3();
        int coinRatioValue = getSelectedCoinRatioValue();

        if(bettingData.getCurrency() < coinRatioValue) {
            // don't have enough money to bet
            Toast.makeText(this, "Don't have enough money to bet", Toast.LENGTH_SHORT).show();
            return;
        } else {
            bettingData.setBetCar3(currentBet + coinRatioValue);
            bettingData.setCurrency(bettingData.getCurrency() - coinRatioValue);
        }
        updateUI();
    }

    private void onUnBetCar1Click() {
        int currentBet = bettingData.getBetCar1();
        int coinRatioValue = getSelectedCoinRatioValue();

        if(currentBet < coinRatioValue) {
            Toast.makeText(this, "Don't have enough money to UnBet", Toast.LENGTH_SHORT).show();
            return;
        } else {
            bettingData.setBetCar1(currentBet - coinRatioValue);
            bettingData.setCurrency(bettingData.getCurrency() + coinRatioValue);
        }
        updateUI();
    }

    private void onUnBetCar2Click() {
        int currentBet = bettingData.getBetCar2();
        int coinRatioValue = getSelectedCoinRatioValue();

        if(currentBet < coinRatioValue) {
            Toast.makeText(this, "Don't have enough money to UnBet", Toast.LENGTH_SHORT).show();
            return;
        } else {
            bettingData.setBetCar2(currentBet - coinRatioValue);
            bettingData.setCurrency(bettingData.getCurrency() + coinRatioValue);
        }
        updateUI();
    }

    private void onUnBetCar3Click() {
        int currentBet = bettingData.getBetCar3();
        int coinRatioValue = getSelectedCoinRatioValue();

        if(currentBet < coinRatioValue) {
            Toast.makeText(this, "Don't have enough money to UnBet", Toast.LENGTH_SHORT).show();
            return;
        } else {
            bettingData.setBetCar3(currentBet - coinRatioValue);
            bettingData.setCurrency(bettingData.getCurrency() + coinRatioValue);
        }
        updateUI();
    }

    //----------------------------------------------------------------------------//

    private int getSelectedCoinRatioValue() {
        Spinner spinnerCoinRatio = findViewById(R.id.spinnerCoinRatio);
        String selectedValue = spinnerCoinRatio.getSelectedItem().toString();

        // Convert the selected value to an integer
        if (selectedValue.equals("10")) {
            return 10;
        } else if (selectedValue.equals("20")) {
            return 20;
        } else if (selectedValue.equals("50")) {
            return 50;
        } else if (selectedValue.equals("All-in")) {
            return bettingData.getCurrency();
        }

        return 0; // Default value (you can handle this case differently if needed)
    }

    private boolean isValidBet() {
        int betRes1 = bettingData.getBetCar1();
        int betRes2 = bettingData.getBetCar2();
        int betRes3 = bettingData.getBetCar3();

        if(betRes1 != 0 || betRes2 != 0 || betRes3 != 0) {
            return true;
        }

        return false;
    }

    private boolean isGameEnd() {
        int betRes1 = bettingData.getBetCar1();
        int betRes2 = bettingData.getBetCar2();
        int betRes3 = bettingData.getBetCar3();
        if(bettingData.getCurrency() == 0
            && betRes1 == 0 & betRes2 == 0 & betRes3 == 0) {
            return true;
        }

        return false;
    }

    public void onGoToColosseumClick(View view) {
        // check if valid bet
        boolean validBet = isValidBet();
        boolean gameEnd = isGameEnd();
        if(!validBet) {
            Toast.makeText(this, "You must bet before go to the race", Toast.LENGTH_SHORT).show();
            return;
        }

        if(gameEnd) {
            Intent intent = new Intent(Betting.this, Racing.class);
            intent.putExtra("bettingData", (Serializable) bettingData);
            startActivity(intent);
            return;
        }

        // Start the racing activity and pass bettingData using Intent
        Intent intent = new Intent(Betting.this, Racing.class);
        intent.putExtra("bettingData", (Serializable) bettingData);
        startActivity(intent);
    }
}