package com.example.minesweeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class GameSettingsUnitTest {

    private int rows;
    private int columns;
    private int count_of_bombs;
    private int duration_long_click;
    private boolean easy_start;
    private boolean canceling_move;
    private boolean can_show_bombs;
    private boolean can_game_restart;
    private boolean recursive_chord;
    private boolean flags_chord;

    @Before
    public void setUp() throws Exception {
        //MockitoAnnotations.initMocks(this);
        //MockitoAnnotations.openMocks(this);
        rows = GameSettings.getRows();
        columns = GameSettings.getColumns();
        count_of_bombs = GameSettings.getCount_of_bombs();
        duration_long_click = GameSettings.getDuration_long_click();
        easy_start = GameSettings.isEasy_start();
        canceling_move = GameSettings.isCanceling_move();
        can_show_bombs = GameSettings.isCan_show_bombs();
        can_game_restart = GameSettings.isCan_game_restart();
        recursive_chord = GameSettings.isRecursive_chord();
        flags_chord = GameSettings.isFlags_chord();
    }

    @Test
    public void setRows_isCorrect() throws Exception {
        GameSettings.setRows(5);
        assertEquals(5, GameSettings.getRows());
    }
    @Test
    public void setColumns_isCorrect() throws Exception {
        GameSettings.setColumns(5);
        assertEquals(5, GameSettings.getColumns());
    }
    @Test
    public void setCount_of_bombs_isCorrect() throws Exception {
        GameSettings.setCount_of_bombs(5);
        assertEquals(5, GameSettings.getCount_of_bombs());
    }
    @Test
    public void setDuration_long_click_isCorrect() throws Exception {
        GameSettings.setDuration_long_click(5);
        assertEquals(5, GameSettings.getDuration_long_click());
    }
    @Test
    public void setEasy_start_isCorrect() throws Exception {
        GameSettings.setEasy_start(true);
        assertTrue(GameSettings.isEasy_start());
    }
    @Test
    public void setCanceling_move_isCorrect() throws Exception {
        GameSettings.setCanceling_move(true);
        assertTrue(GameSettings.isCanceling_move());
    }
    @Test
    public void setCan_show_bombs_isCorrect() throws Exception {
        GameSettings.setCan_show_bombs(true);
        assertTrue(GameSettings.isCan_show_bombs());
    }
    @Test
    public void setCan_game_restart_isCorrect() throws Exception {
        GameSettings.setCan_game_restart(true);
        assertTrue(GameSettings.isCan_game_restart());
    }
    @Test
    public void setRecursive_chord_isCorrect() throws Exception {
        GameSettings.setRecursive_chord(true);
        assertTrue(GameSettings.isRecursive_chord());
    }
    @Test
    public void setFlags_chord_isCorrect() throws Exception {
        GameSettings.setFlags_chord(true);
        assertTrue(GameSettings.isFlags_chord());
    }
    @After
    public void tearDown() throws Exception {
        GameSettings.setRows(rows);
        GameSettings.setColumns(columns);
        GameSettings.setCount_of_bombs(count_of_bombs);
        GameSettings.setDuration_long_click(duration_long_click);
        GameSettings.setEasy_start(easy_start);
        GameSettings.setCanceling_move(canceling_move);
        GameSettings.setCan_show_bombs(can_show_bombs);
        GameSettings.setCan_game_restart(can_game_restart);
        GameSettings.setRecursive_chord(recursive_chord);
        GameSettings.setFlags_chord(flags_chord);
    }
}
