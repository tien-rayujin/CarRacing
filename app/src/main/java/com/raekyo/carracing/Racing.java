package com.raekyo.carracing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.raekyo.carracing.listView.PlayHistory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Racing extends AppCompatActivity {
    SeekBar seekBar1, seekBar2, seekBar3;
    TextView txtResultBet1, txtResultBet2, txtResultBet3;
    Button btnStart, btnBackToBet;
    AnimationDrawable seekThumbAnim1, seekThumbAnim2, seekThumbAnim3;
    Random rand;
    Handler handler;
    private int seekBarBoundaries = 1000;
    private boolean isAnimationRunning = false;
    private BettingData bettingData;
    private boolean keepCurrentBet = false;

    //--------------------------------------------------------------------
    TextView txtCurrency;
    boolean hasWinner = false;

    int carWinner_img = 0;
    int roundCount = 0;
    ArrayList<PlayHistory> playHistoryArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_racing);

        bindingSource();

        Intent intent = getIntent();
        bettingData = (BettingData) intent.getSerializableExtra("bettingData");
        txtCurrency.setText(String.valueOf(bettingData.getCurrency()) + "$");
        txtResultBet1.setText(String.valueOf(bettingData.getBetCar1()) + "$");
        txtResultBet2.setText(String.valueOf(bettingData.getBetCar2()) + "$");
        txtResultBet3.setText(String.valueOf(bettingData.getBetCar3()) + "$");

        // prevent default
        seekBar1.setEnabled(false);
        seekBar2.setEnabled(false);
        seekBar3.setEnabled(false);

        seekBar1.setThumb(seekThumbAnim1);
        seekBar2.setThumb(seekThumbAnim2);
        seekBar3.setThumb(seekThumbAnim3);

        seekBar1.setMax(seekBarBoundaries);
        seekBar2.setMax(seekBarBoundaries);
        seekBar3.setMax(seekBarBoundaries);

        handler = new Handler(Looper.getMainLooper());

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isAnimationRunning) {
                    seekBar1.setProgress(0);
                    seekBar2.setProgress(0);
                    seekBar3.setProgress(0);
                    hasWinner = false;
                    roundCount++;
                    txtCurrency.setText(String.valueOf(bettingData.getCurrency()) + "$");

                    // check if currency enough to playAgain
                    int betCar1 = bettingData.getBetCar1();
                    int betCar2 = bettingData.getBetCar2();
                    int betCar3 = bettingData.getBetCar3();
                    if(keepCurrentBet) {
                        if(bettingData.getCurrency() < ( betCar1 + betCar2 + betCar3 )){
                            Toast.makeText(Racing.this, "Not enough Money to play again", Toast.LENGTH_SHORT).show();
                            btnBackToBet.setBackgroundColor(Color.MAGENTA);
                            return;
                        }
                        bettingData.setCurrency(bettingData.getCurrency() - betCar1 - betCar2 - betCar3);
                        txtCurrency.setText(String.valueOf(bettingData.getCurrency()) + "$");
                    }
                    startSeekBarAnimations();
                }
            }
        });

        btnBackToBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isGameEnd = bettingData.getCurrency() <= 0;
                if(isGameEnd) {
                    // go to Result page
                    Intent resultIntent = new Intent(Racing.this, Result.class);
                    resultIntent.putExtra("playHistoryArrayList", (Serializable) playHistoryArrayList);
                    startActivity(resultIntent);
                    return;
                }
                Intent betIntent = new Intent(Racing.this, Betting.class);
                betIntent.putExtra("bettingData", (Serializable) bettingData);
                startActivity(betIntent);
            }
        });
    }

    private void bindingSource() {
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnBackToBet = (Button) findViewById(R.id.btnBackToBet);
        txtResultBet1 = (TextView) findViewById(R.id.txtResultBet1);
        txtResultBet2 = (TextView) findViewById(R.id.txtResultBet2);
        txtResultBet3 = (TextView) findViewById(R.id.txtResultBet3);
        seekThumbAnim1 = (AnimationDrawable) getResources().getDrawable(R.drawable.seek_thumb_animation1);
        seekThumbAnim2 = (AnimationDrawable) getResources().getDrawable(R.drawable.seek_thumb_animation2);
        seekThumbAnim3 = (AnimationDrawable) getResources().getDrawable(R.drawable.seek_thumb_animation3);

        txtCurrency = (TextView) findViewById(R.id.txtCurrency);
        rand = new Random();
    }

    private void startSeekBarAnimations() {
        isAnimationRunning = true;
        // Start a thread for each SeekBar animation
        new Thread(new Runnable() {
            @Override
            public void run() {
                animateSeekBar(seekBar1, txtResultBet1, seekThumbAnim1, bettingData.getBetCar1(), R.drawable.car1_1);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                animateSeekBar(seekBar2, txtResultBet2, seekThumbAnim2, bettingData.getBetCar2(), R.drawable.car2_1);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                animateSeekBar(seekBar3, txtResultBet3, seekThumbAnim3, bettingData.getBetCar3(), R.drawable.car3_1);
            }
        }).start();

        keepCurrentBet = true;
        playHistoryArrayList.add(new PlayHistory(carWinner_img, "Round " + roundCount, String.valueOf(bettingData.getCurrency())));
    }

    private void animateSeekBar(SeekBar seekBar, TextView resultTextView, AnimationDrawable seekThumbAnim, int bettingCarValue, int car_img) {
        int currentProgress = 0;
        // reset result state
        handler.post(new Runnable() {
            @Override
            public void run() {
                resultTextView.setTextColor(Color.BLACK);
                resultTextView.setBackgroundColor(Color.WHITE);
            }
        });

        // from [start, end)
        while(currentProgress < seekBarBoundaries) {
            int speed = rand.nextInt(40) + 5;   // range 5 -> 45
            currentProgress = seekBar.getProgress();
            int step = currentProgress + speed;

            handler.post(new Runnable() {
                @Override
                public void run() {
                    seekThumbAnim.start();
//                    resultTextView.setText(String.valueOf(step));
                    seekBar.setProgress(step);
                }
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // game end
            if(currentProgress >= seekBarBoundaries) {
                isAnimationRunning = false;

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(!hasWinner) {
                            hasWinner = true;
                            carWinner_img = car_img;
                            resultTextView.setTextColor(Color.YELLOW);
                            resultTextView.setBackgroundColor(Color.GREEN);
                            // add money if win
                            bettingData.setCurrency(bettingData.getCurrency() + bettingCarValue*2);

                        }else {
                            resultTextView.setTextColor(Color.WHITE);
                            resultTextView.setBackgroundColor(Color.RED);
                        }

                        txtCurrency.setText(String.valueOf(bettingData.getCurrency()) + "$");

                        // check on Win on bettingValue
//                        if(bettingCarValue > 0) {
//                            // user have bet on this car
//                            resultTextView.setTextColor(Color.YELLOW);
//                            resultTextView.setBackgroundColor(Color.GREEN);
//
//                            bettingData.setCurrency(bettingData.getCurrency() + bettingCarValue*2);
//                        } else {
//                            resultTextView.setTextColor(Color.WHITE);
//                            resultTextView.setBackgroundColor(Color.RED);
//                        }
                    }
                });
                break;
            }
        }

        isAnimationRunning = false;
    }
}