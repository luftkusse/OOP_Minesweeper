package com.example.minesweeper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private final int ROWS = GameSettings.getRows();
    private final int COLUMNS = GameSettings.getColumns();
    private final int COUNT_OF_FIELDS = COLUMNS * ROWS;
    private final int COUNT_OF_BOMBS = GameSettings.getCount_of_bombs();
    private final int DURATION_LONG_CLICK = GameSettings.getDuration_long_click();
    private int count_of_flags = COUNT_OF_BOMBS;
    boolean flag_game_start = false;
    boolean flag_game_restart = false;
    boolean flag_game_stop = false;
    boolean flag_short_click = false;
    String mode = "open";
    private ImageView imageViewNumberFlags1;
    private ImageView imageViewNumberFlags2;
    private ImageView imageViewNumberFlags3;
    private ImageView imageViewNumberTimer1;
    private ImageView imageViewNumberTimer2;
    private ImageView imageViewNumberTimer3;
    private ImageButton imageButtonSmile;
    private ImageButton imageButtonCancel;
    private ImageButton imageButtonShowBombs;
    private ImageButton imageButtonRestart;
    private ImageButton imageButtonChangeMode;
    ArrayList<Field> fields;
    int time, timeClick;
    Timer timer, timerClick;
    TimerTask timerTask, timerTaskClick;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        imageViewNumberFlags1 = findViewById(R.id.imageViewNumberFlags1);
        imageViewNumberFlags2 = findViewById(R.id.imageViewNumberFlags2);
        imageViewNumberFlags3 = findViewById(R.id.imageViewNumberFlags3);
        imageViewNumberTimer1 = findViewById(R.id.imageViewNumberTimer1);
        imageViewNumberTimer2 = findViewById(R.id.imageViewNumberTimer2);
        imageViewNumberTimer3 = findViewById(R.id.imageViewNumberTimer3);
        imageButtonSmile = findViewById(R.id.imageButtonSmile);
        imageButtonCancel = findViewById(R.id.imageButtonCancel);
        imageButtonShowBombs = findViewById(R.id.imageButtonShowBombs);
        imageButtonRestart = findViewById(R.id.imageButtonRestart);
        imageButtonChangeMode = findViewById(R.id.imageButtonChangeMode);

        setStringValue(count_of_flags, imageViewNumberFlags1, imageViewNumberFlags2, imageViewNumberFlags3);

        fields = new ArrayList<>();
        setButtons();
        setFields();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setButtons() {
        int counter = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.activity_field, null);
                ImageButton imageButton = (ImageButton) view.findViewById(R.id.imageButtonField);
                TextView textViewCountBombsAround = (TextView) view.findViewById(R.id.textViewCountBombsAround);
                Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                ConstraintLayout constraintLayoutInside = (ConstraintLayout) findViewById(R.id.constraintLayotInside);
                int width = display.getWidth();
                int size = (width - width / 10) / COLUMNS;
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imageButton.getLayoutParams();
                params.height = size;
                params.width = size;
                imageButton.setLayoutParams(params);
                textViewCountBombsAround.setLayoutParams(params);
                imageButton.setId(counter);
                textViewCountBombsAround.setId(COUNT_OF_FIELDS + counter);
                textViewCountBombsAround.setGravity(Gravity.CENTER);
                textViewCountBombsAround.setTextSize(size / 5);
                view.setX(width / 20 + j * size);
                view.setY(width / 20 + i * size);
                constraintLayoutInside.addView(view);

                imageButton.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN && !flag_game_stop) {
                            if(!fields.get(imageButton.getId()).getIsOpen()) {
                                imageButtonSmile.setBackgroundResource(R.drawable.smile_action);
                            }
                            setClickTimer();
                        }
                        if (event.getAction() == MotionEvent.ACTION_UP && !flag_game_stop) {
                            GameSettings.setLast_condition_fields(fields);
                            Field field = fields.get(imageButton.getId());
                            int id = field.getId();
                            checkGameStart(id);
                            stopTimerClick();
                            imageButtonSmile.setBackgroundResource(R.drawable.smile_standart);
                            if ((!flag_short_click && mode == "open") || (flag_short_click && mode == "flag")) {
                                if (!field.getIsOpen()) {
                                    setFlag(id);
                                }
                            } else {
                                if (field.getIsOpen() && checkFlags(id) && GameSettings.isRecursive_chord()) {
                                    openFieldsAround(id);
                                }
                                if (!field.getIsOpen() && !flag_game_stop) {
                                    if (!field.getIsFlag()) {
                                        openField(id);
                                    }
                                }
                            }
                            if (checkFields()) {
                                imageButtonSmile.setBackgroundResource(R.drawable.smile_win);
                                imageButtonRestart.setVisibility(View.VISIBLE);
                                stopTimer();
                            }
                        }
                        return true;
                    }
                });

                if (counter < COUNT_OF_FIELDS - 1) {
                    counter++;
                }
            }
        }
    }

    public void checkGameStart(int id) {
        if(!flag_game_start) {
            if(!flag_game_restart) {
                setBombs(id);
            }
            time = 0;
            setTimer();
            flag_game_start = true;
            flag_game_restart = false;
        }
    }

    public void setFields() {
        int counter = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                fields.add(new Field(counter, i + 1, j + 1));
                if (counter < COUNT_OF_FIELDS - 1) {
                    counter++;
                }
            }
        }
    }

    public void clean() {
        int counter = 0;
        flag_game_stop = false;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                boolean isBomb = false;
                if(flag_game_restart && fields.get(counter).getIsBomb()) {
                    isBomb = true;
                }
                fields.set(counter, new Field(counter, i + 1, j + 1));
                fields.get(counter).setIsBomb(isBomb);
                ImageButton imageButton = findViewById(counter);
                TextView textViewCountBombsAround = (TextView) findViewById(COUNT_OF_FIELDS + counter);
                imageButton.setBackgroundResource(R.drawable.field_close);
                textViewCountBombsAround.setText("");
                if (counter < COUNT_OF_FIELDS - 1) {
                    counter++;
                }
            }
        }
    }

    public void setBombs(int id) {
        int row = fields.get(id).getRow();
        int column = fields.get(id).getColumn();
        final Random random = new Random();
        int i = 0;
        while (i < COUNT_OF_BOMBS) {
            int r = random.nextInt(COUNT_OF_FIELDS);
            if (!fields.get(r).getIsBomb() && !(GameSettings.isEasy_start() && (r == id ||
                    (fields.get(r).getColumn() >= column - 1 && fields.get(r).getColumn() <= column + 1 &&
                    fields.get(r).getRow() >= row - 1 && fields.get(r).getRow() <= row + 1)))) {
                fields.get(r).setIsBomb(true);
                i++;
            }
        }
    }

    public void setStringValue(int value, ImageView imageView1, ImageView imageView2, ImageView imageView3) {
        String string;
        if (value < 10) {
            string = "00" + Integer.toString(value);
        } else if (value < 100) {
            string = "0" + Integer.toString(value);
        } else {
            string = Integer.toString(value);
        }
        setDisplayValue(imageView1, string.charAt(0));
        setDisplayValue(imageView2, string.charAt(1));
        setDisplayValue(imageView3, string.charAt(2));
    }

    public void setDisplayValue(ImageView imageView, char c) {
        switch (c) {
            case '0':
                imageView.setBackgroundResource(R.drawable.number_0);
                break;
            case '1':
                imageView.setBackgroundResource(R.drawable.number_1);
                break;
            case '2':
                imageView.setBackgroundResource(R.drawable.number_2);
                break;
            case '3':
                imageView.setBackgroundResource(R.drawable.number_3);
                break;
            case '4':
                imageView.setBackgroundResource(R.drawable.number_4);
                break;
            case '5':
                imageView.setBackgroundResource(R.drawable.number_5);
                break;
            case '6':
                imageView.setBackgroundResource(R.drawable.number_6);
                break;
            case '7':
                imageView.setBackgroundResource(R.drawable.number_7);
                break;
            case '8':
                imageView.setBackgroundResource(R.drawable.number_8);
                break;
            default:
                imageView.setBackgroundResource(R.drawable.number_9);
                break;
        }
    }

    public void setFlag(int id) {
        Field field = fields.get(id);
        ImageButton imageButton = findViewById(id);
        if (!fields.get(id).getIsFlag()) {
            if (count_of_flags > 0) {
                field.setIsFlag(!field.getIsFlag());
                imageButton.setBackgroundResource(R.drawable.field_flag);
                count_of_flags--;
            }
        } else {
            field.setIsFlag(!field.getIsFlag());
            imageButton.setBackgroundResource(R.drawable.field_close);
            count_of_flags++;
        }
        setStringValue(count_of_flags, imageViewNumberFlags1, imageViewNumberFlags2, imageViewNumberFlags3);
    }

    @SuppressLint("ResourceAsColor")
    public void openField(int id) {
        ImageButton imageButton = findViewById(id);
        TextView textViewCountBombsAround = findViewById(COUNT_OF_FIELDS + id);
        if (fields.get(id).getIsBomb()) {
            imageButtonSmile.setBackgroundResource(R.drawable.smile_lose);
            stopTimer();
            flag_game_stop = true;
            if(GameSettings.isCan_game_restart()) {
                imageButtonRestart.setVisibility(View.VISIBLE);
            }
            if(GameSettings.isCan_show_bombs()) {
                imageButtonShowBombs.setVisibility(View.VISIBLE);
            }
            if(GameSettings.isCanceling_move()) {
                imageButtonCancel.setBackgroundResource(R.drawable.image_button_cancel_on);
            }
            imageButton.setBackgroundResource(R.drawable.field_bomb_boom);
            fields.get(id).setIsBoomBomb(true);
        } else {
            if (fields.get(id).getIsFlag()) {
                imageButton.setBackgroundResource(R.drawable.field_is_not_flag);
            } else {
                fields.get(id).setIsOpen(true);
                int countOfBombsAround = checkBombs(id);
                if (countOfBombsAround != 0) {
                    textViewCountBombsAround.setText(Integer.toString(countOfBombsAround));
                    Resources resources = getResources();
                    switch (countOfBombsAround) {
                        case 1:
                            textViewCountBombsAround.setTextColor(resources.getColor(R.color.textViewCountBombsAround1));
                            break;
                        case 2:
                            textViewCountBombsAround.setTextColor(resources.getColor(R.color.textViewCountBombsAround2));
                            break;
                        case 3:
                            textViewCountBombsAround.setTextColor(resources.getColor(R.color.textViewCountBombsAround3));
                            break;
                        case 4:
                            textViewCountBombsAround.setTextColor(resources.getColor(R.color.textViewCountBombsAround4));
                            break;
                        case 5:
                            textViewCountBombsAround.setTextColor(resources.getColor(R.color.textViewCountBombsAround5));
                            break;
                        case 6:
                            textViewCountBombsAround.setTextColor(resources.getColor(R.color.textViewCountBombsAround6));
                            break;
                        case 7:
                            textViewCountBombsAround.setTextColor(resources.getColor(R.color.textViewCountBombsAround7));
                            break;
                        default:
                            textViewCountBombsAround.setTextColor(resources.getColor(R.color.textViewCountBombsAround8));
                            break;
                    }
                } else {
                    openFieldsAround(id);
                }
                imageButton.setBackgroundResource(R.drawable.field_open);
            }
        }
    }

    public void openFieldsAround(int id) {
        int row = fields.get(id).getRow();
        int column = fields.get(id).getColumn();
        for (Field field : fields) {
            if (field.getColumn() >= column - 1 && field.getColumn() <= column + 1 &&
                    field.getRow() >= row - 1 && field.getRow() <= row + 1 &&
                    field.getId() != id && !field.getIsOpen() && !(field.getIsFlag() && field.getIsBomb())) {
                openField(field.getId());
            }
        }
    }

    public int checkBombs(int id) {
        int countOfBombsAround = 0;
        int row = fields.get(id).getRow();
        int column = fields.get(id).getColumn();
        for (Field field : fields) {
            if (field.getColumn() >= column - 1 && field.getColumn() <= column + 1 &&
                    field.getRow() >= row - 1 && field.getRow() <= row + 1 &&
                    field.getId() != id && field.getIsBomb()) {
                countOfBombsAround++;
            }
        }
        return countOfBombsAround;
    }

    public boolean checkFields() {
        for (Field field : fields) {
            if (!((field.getIsBomb() && field.getIsFlag()) || field.getIsOpen())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkFlags(int id) {
        int row = fields.get(id).getRow();
        int column = fields.get(id).getColumn();
        int flags = 0;
        int closed = 0;
        for (Field field : fields) {
            if (field.getColumn() >= column - 1 && field.getColumn() <= column + 1 &&
                    field.getRow() >= row - 1 && field.getRow() <= row + 1 &&
                    field.getId() != id && !field.getIsOpen()) {
                if(field.getIsFlag()) {
                    flags++;
                } else {
                    closed++;
                }
            }
        }
        if (flags == checkBombs(id)) {
            return true;
        } else if(closed + flags == checkBombs(id) && GameSettings.isFlags_chord()) {
            for (Field field : fields) {
                if (field.getColumn() >= column - 1 && field.getColumn() <= column + 1 &&
                        field.getRow() >= row - 1 && field.getRow() <= row + 1 &&
                        field.getId() != id && !field.getIsOpen() && !field.getIsFlag()) {
                    setFlag(field.getId());
                }
            }
        }
        return false;
    }

    public void setTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        setStringValue(time, imageViewNumberTimer1, imageViewNumberTimer2, imageViewNumberTimer3);
                        if (time == 999) {
                            timerTask.cancel();
                            timer.cancel();
                        }
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void stopTimer() {
        timer.cancel();
        timerTask.cancel();
    }

    public void setClickTimer() {
        flag_short_click = true;
        timeClick = 0;
        timerClick = new Timer();
        timerTaskClick = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeClick++;
                        if (timeClick == DURATION_LONG_CLICK) {
                            flag_short_click = false;
                            timerTaskClick.cancel();
                            timerClick.cancel();
                        }
                    }
                });
            }
        };
        timerClick.scheduleAtFixedRate(timerTaskClick, 0, 1);
    }

    public void stopTimerClick() {
        timerClick.cancel();
        timerTaskClick.cancel();
    }

    public void restart() {
        imageButtonSmile.setBackgroundResource(R.drawable.smile_standart);
        count_of_flags = COUNT_OF_BOMBS;
        setStringValue(count_of_flags, imageViewNumberFlags1, imageViewNumberFlags2, imageViewNumberFlags3);
        clean();
        stopTimer();
        time = 0;
        setStringValue(time, imageViewNumberTimer1, imageViewNumberTimer2, imageViewNumberTimer3);
        flag_game_start = false;
    }

    public void setDrawableButtons() {
        imageButtonSmile.setBackgroundResource(R.drawable.smile_standart);
        imageButtonShowBombs.setVisibility(View.INVISIBLE);
        imageButtonRestart.setVisibility(View.INVISIBLE);
        imageButtonCancel.setBackgroundResource(R.drawable.image_button_cancel_off);
    }

    public void imageButtonSmileClick(View view) {
        restart();
        setDrawableButtons();
    }

    public void imageButtonBackClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void imageButtonShowBombsClick(View view) {
        for (Field field : fields) {
            if (field.getIsBomb() && !field.getIsBoomBomb()) {
                ImageButton imageButton = findViewById(field.getId());
                imageButton.setBackgroundResource(R.drawable.field_bomb);
            }
        }
        imageButtonShowBombs.setVisibility(View.INVISIBLE);
    }

    public void imageButtonRestartClick(View view) {
        flag_game_restart = true;
        restart();
        setDrawableButtons();
    }

    public void imageButtonCancelClick(View view) {
        if(GameSettings.isCanceling_move() && flag_game_stop) {
            setDrawableButtons();
            flag_game_stop = false;
            setTimer();
            for (Field field: fields) {
                Field lastField = GameSettings.getLast_condition_fields().get(field.getId());
                field.setIsOpen(lastField.getIsOpen());
                field.setIsFlag(lastField.getIsFlag());
                field.setIsBomb(lastField.getIsBomb());
                ImageButton imageButton = findViewById(field.getId());
                if(field.getIsOpen()) {
                    imageButton.setBackgroundResource(R.drawable.field_open);
                } else if(field.getIsFlag()) {
                    imageButton.setBackgroundResource(R.drawable.field_flag);
                } else {
                    imageButton.setBackgroundResource(R.drawable.field_close);
                }
            }
        }
    }

    public void imageButtonChangeModeClick(View view) {
        if(mode == "open") {
            mode = "flag";
            imageButtonChangeMode.setBackgroundResource(R.drawable.mode_flag);
        } else {
            mode = "open";
            imageButtonChangeMode.setBackgroundResource(R.drawable.mode_open);
        }
    }
}