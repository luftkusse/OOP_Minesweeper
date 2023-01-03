package com.example.minesweeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class FieldTest {

    private Field field;

    @Before
    public void setUp() throws Exception {
        field = Mockito.mock(Field.class);
    }

    @Test
    public void setId_isCorrect() throws Exception {
        field.setId(5);
        assertEquals(5, field.getId());
    }

    @Test
    public void setRow_isCorrect() throws Exception {
        field.setRow(5);
        assertEquals(5, field.getRow());
    }
    @Test
    public void setColumn_isCorrect() throws Exception {
        field.setColumn(5);
        assertEquals(5, field.getColumn());
    }

    @Test
    public void setCountBombsAround_isCorrect() throws Exception {
        field.setCountBombsAround(5);
        assertEquals(5, field.getCountBombsAround());
    }

    @Test
    public void setIsOpen_isCorrect() throws Exception {
        field.setIsOpen(true);
        assertTrue(field.getIsOpen());
    }

    @Test
    public void setIsFlag_isCorrect() throws Exception {
        field.setIsFlag(true);
        assertTrue(field.getIsFlag());
    }

    @Test
    public void setIsBomb_isCorrect() throws Exception {
        field.setIsBomb(true);
        assertTrue(field.getIsBomb());
    }

    @Test
    public void setIsBoomBomb_isCorrect() throws Exception {
        field.setIsBoomBomb(true);
        assertTrue(field.getIsBoomBomb());
    }

    @After
    public void tearDown() throws Exception {
        field = null;
    }
}
