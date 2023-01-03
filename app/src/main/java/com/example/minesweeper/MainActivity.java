package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textViewDesignation);
        String s = "";
        switch (GameSettings.getCount_of_bombs()) {
            case 10:
                s = "EASY";
                break;
            case 30:
                s = "NORMAL";
                break;
            case 60:
                s = "HARD";
                break;
        }
        textView.setText(s + ": " + GameSettings.getRows() + "x" + GameSettings.getColumns());
    }

    public void imageButtonPlayClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void imageButtonGameSettingsClick(View view) {
        Intent intent = new Intent(this, GameSettingsActivity.class);
        startActivity(intent);
    }
}