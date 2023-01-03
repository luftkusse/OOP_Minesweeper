package com.example.minesweeper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);

        RadioButton radioButtonEasy = findViewById(R.id.radioButtonEasy);
        RadioButton radioButtonNormal = findViewById(R.id.radioButtonNormal);
        RadioButton radioButtonHard = findViewById(R.id.radioButtonHard);
        CheckBox checkBoxEasyStart = findViewById(R.id.checkBoxEasyStart);
        CheckBox checkBoxCancelingMove = findViewById(R.id.checkBoxCancelingMove);
        CheckBox checkBoxRestart = findViewById(R.id.checkBoxRestart);
        CheckBox checkBoxShowBombs = findViewById(R.id.checkBoxShowBombs);
        CheckBox checkBoxRecursiveChord = findViewById(R.id.checkBoxRecursiveChord);
        CheckBox checkBoxFlagsChord = findViewById(R.id.checkBoxFlagsChord);
        checkBoxEasyStart.setChecked(GameSettings.isEasy_start());
        checkBoxCancelingMove.setChecked(GameSettings.isCanceling_move());
        checkBoxRestart.setChecked(GameSettings.isCan_game_restart());
        checkBoxShowBombs.setChecked(GameSettings.isCan_show_bombs());
        checkBoxRecursiveChord.setChecked(GameSettings.isRecursive_chord());
        checkBoxFlagsChord.setChecked(GameSettings.isFlags_chord());

        radioButtonEasy.setChecked(false);
        radioButtonNormal.setChecked(false);
        radioButtonHard.setChecked(false);
        switch (GameSettings.getCount_of_bombs()) {
            case 10:
                radioButtonEasy.setChecked(true);
                break;
            case 30:
                radioButtonNormal.setChecked(true);
                break;
            case 60:
                radioButtonHard.setChecked(true);
                break;
        }

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                if (radioButton != null && checkedId > -1) {
                    String s = (String) radioButton.getText();
                    switch (s) {
                        case "easy":
                            GameSettings.setRows(8);
                            GameSettings.setColumns(8);
                            GameSettings.setCount_of_bombs(10);
                            break;
                        case "normal":
                            GameSettings.setRows(16);
                            GameSettings.setColumns(12);
                            GameSettings.setCount_of_bombs(30);
                            break;
                        case "hard":
                            GameSettings.setRows(20);
                            GameSettings.setColumns(16);
                            GameSettings.setCount_of_bombs(60);
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        SeekBar seekBarDurationLongClick = findViewById(R.id.seekBarDurationLongClick);
        TextView textViewDurationLongClick = findViewById(R.id.textViewDurationLongClick);
        seekBarDurationLongClick.setProgress((GameSettings.getDuration_long_click()-100)/5);
        textViewDurationLongClick.setText(Integer.toString(GameSettings.getDuration_long_click()));

        seekBarDurationLongClick.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value = (100 + progress * 5);
                textViewDurationLongClick.setText(String.valueOf(value));
                GameSettings.setDuration_long_click(value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    public void onCheckBoxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkBoxEasyStart:
                GameSettings.setEasy_start(checked);
                break;
            case R.id.checkBoxCancelingMove:
                GameSettings.setCanceling_move(checked);
                break;
            case R.id.checkBoxRestart:
                GameSettings.setCan_game_restart(checked);
                break;
            case R.id.checkBoxShowBombs:
                GameSettings.setCan_show_bombs(checked);
                break;
            case R.id.checkBoxRecursiveChord:
                GameSettings.setRecursive_chord(checked);
                break;
            case R.id.checkBoxFlagsChord:
                GameSettings.setFlags_chord(checked);
                break;
        }
    }

    public void imageButtonBackClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}