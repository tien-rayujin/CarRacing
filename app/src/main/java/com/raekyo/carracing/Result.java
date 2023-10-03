package com.raekyo.carracing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.raekyo.carracing.listView.PlayHistory;
import com.raekyo.carracing.listView.PlayHistoryAdapter;

import java.util.ArrayList;

public class Result extends AppCompatActivity {

    Button btnPlayAgain;
    ListView lvPlayHistory, lvHighScore;

    ArrayList<PlayHistory> playHistoryArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bindingSource();

        Intent intent = getIntent();
        playHistoryArrayList = (ArrayList<PlayHistory>) intent.getSerializableExtra("playHistoryArrayList");

        if(playHistoryArrayList == null) {
            playHistoryArrayList = new ArrayList<>();
            playHistoryArrayList.add(new PlayHistory(R.drawable.car1_1, "Round 1", "400"));
        }

        PlayHistoryAdapter playHistoryAdapter = new PlayHistoryAdapter(Result.this, playHistoryArrayList, R.layout.lv_item_playhistory);
        lvPlayHistory.setAdapter(playHistoryAdapter);
    }

    private void bindingSource() {
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        lvPlayHistory = (ListView) findViewById(R.id.lvPlayHistory);
        lvHighScore = (ListView) findViewById(R.id.lvHighScore);
    }
}