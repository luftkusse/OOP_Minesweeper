package com.example.minesweeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;


@RunWith(JUnit4.class)
public class GameUnitTest {

    private GameActivity gameActivity;

    @Before
    public void setUp() throws Exception {
        gameActivity = Mockito.mock(GameActivity.class);
    }

    @Test
    public void setButtons_isCorrect() throws Exception {
        gameActivity.setButtons();
        verify(gameActivity).setButtons();
    }

    @Test
    public void checkGameStart_isCorrect() throws Exception {
        gameActivity.checkGameStart(0);
        assertEquals(gameActivity.time, 0);
    }

    @Test
    public void setFields_isCorrect() throws Exception {
        gameActivity.setFields();
        assertNull(gameActivity.fields);
    }

    @Test
    public void clean_isCorrect() throws Exception {
        gameActivity.clean();
        assertFalse(gameActivity.flag_game_stop);
    }

    @Test
    public void setBombs_isCorrect() throws Exception {
        gameActivity.setBombs(0);
        verify(gameActivity).setBombs(0);
    }

    @Test
    public void setStringValue_isCorrect() throws Exception {
        gameActivity.setStringValue(0,null,null, null);
        verify(gameActivity).setStringValue(0,null,null, null);
    }

    @Test
    public void setDisplayValue_isCorrect() throws Exception {
        gameActivity.setStringValue(0,null,null, null);
        verify(gameActivity).setStringValue(0,null,null, null);
    }

    @Test
    public void setFlag_isCorrect() throws Exception {
        gameActivity.setFlag(0);
        verify(gameActivity).setFlag(0);
    }

    @Test
    public void openField_isCorrect() throws Exception {
        gameActivity.openField(0);
        verify(gameActivity).openField(0);
    }

    @Test
    public void openFieldsAround_isCorrect() throws Exception {
        gameActivity.openFieldsAround(0);
        verify(gameActivity).openFieldsAround(0);
    }

    @Test
    public void checkBombs_isCorrect() throws Exception {
        assertEquals(0, gameActivity.checkBombs(0));
    }

    @Test
    public void checkFields_isCorrect() throws Exception {
        gameActivity.checkFields();
        verify(gameActivity).checkFields();
    }

    @Test
    public void checkFlags_isCorrect() throws Exception {
        gameActivity.checkFlags(0);
        verify(gameActivity).checkFlags(0);
    }

    @Test
    public void setTimer_isCorrect() throws Exception {
        gameActivity.setTimer();
        verify(gameActivity).setTimer();
    }

    @Test
    public void stopTimer_isCorrect() throws Exception {
        gameActivity.stopTimer();
        verify(gameActivity).stopTimer();
    }

    @Test
    public void setClickTimer_isCorrect() throws Exception {
        gameActivity.setClickTimer();
        verify(gameActivity).setClickTimer();
    }

    @Test
    public void stopTimerClick_isCorrect() throws Exception {
        gameActivity.stopTimerClick();
        verify(gameActivity).stopTimerClick();
    }

    @Test
    public void restart_isCorrect() throws Exception {
        gameActivity.restart();
        assertFalse(gameActivity.flag_game_start);
    }

    @Test
    public void setDrawableButtons_isCorrect() throws Exception {
        gameActivity.setDrawableButtons();
        verify(gameActivity).setDrawableButtons();
    }

    @After
    public void tearDown() throws Exception {
        gameActivity = null;
    }
}

