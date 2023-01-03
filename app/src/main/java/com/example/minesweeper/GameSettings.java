package com.example.minesweeper;

import java.util.ArrayList;

public class GameSettings {
    private static int rows = 8;
    private static int columns = 8;
    private static int count_of_bombs = 10;
    private static int duration_long_click = 250;
    private static boolean easy_start = true;
    private static boolean canceling_move = true;
    private static boolean can_show_bombs = true;
    private static boolean can_game_restart = true;
    private static boolean recursive_chord = true;
    private static boolean flags_chord = true;
    private static ArrayList<Field> last_condition_fields;

    public static int getRows() {
        return rows;
    }

    public static void setRows(int rows) {
        GameSettings.rows = rows;
    }

    public static int getColumns() {
        return columns;
    }

    public static void setColumns(int columns) {
        GameSettings.columns = columns;
    }

    public static int getCount_of_bombs() {
        return count_of_bombs;
    }

    public static void setCount_of_bombs(int count_of_bombs) {
        GameSettings.count_of_bombs = count_of_bombs;
    }

    public static int getDuration_long_click() {
        return duration_long_click;
    }

    public static void setDuration_long_click(int duration_long_click) {
        GameSettings.duration_long_click = duration_long_click;
    }

    public static boolean isEasy_start() {
        return easy_start;
    }

    public static void setEasy_start(boolean easy_start) {
        GameSettings.easy_start = easy_start;
    }

    public static boolean isCanceling_move() {
        return canceling_move;
    }

    public static void setCanceling_move(boolean canceling_move) {
        GameSettings.canceling_move = canceling_move;
    }

    public static ArrayList<Field> getLast_condition_fields() {
        return last_condition_fields;
    }

    public static void setLast_condition_fields(ArrayList<Field> last_condition_fields) {
        GameSettings.last_condition_fields = last_condition_fields;
    }

    public static boolean isCan_show_bombs() {
        return can_show_bombs;
    }

    public static void setCan_show_bombs(boolean can_show_bombs) {
        GameSettings.can_show_bombs = can_show_bombs;
    }

    public static boolean isCan_game_restart() {
        return can_game_restart;
    }

    public static void setCan_game_restart(boolean can_game_restart) {
        GameSettings.can_game_restart = can_game_restart;
    }

    public static boolean isRecursive_chord() {
        return recursive_chord;
    }

    public static void setRecursive_chord(boolean recursive_chord) {
        GameSettings.recursive_chord = recursive_chord;
    }

    public static boolean isFlags_chord() {
        return flags_chord;
    }

    public static void setFlags_chord(boolean flags_chord) {
        GameSettings.flags_chord = flags_chord;
    }
}
