package com.raekyo.carracing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

public class Racing extends AppCompatActivity {
    SeekBar seek1, seek2, seek3;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_racing);

        bindingSource();


    }

    private void bindingSource() {
        seek1 = (SeekBar) findViewById(R.id.seek1);
        seek2 = (SeekBar) findViewById(R.id.seek2);
        seek3 = (SeekBar) findViewById(R.id.seek3);
        btnStart = (Button) findViewById(R.id.btnStart);
    }
}