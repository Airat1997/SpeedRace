package edu.wilmerbl.logic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldTest {
    private Field field;

    @BeforeEach
    void setUp() {
        field = new Field();
    }

    @Test
    void testInitialState() {
        assertEquals(0, field.getScore());
        assertEquals(0, field.getLevel());
        assertFalse(field.isGameOver());
        assertEquals(0, field.getEnemyCars().size());
    }
    @Test
    void updateCarEnemyAdd() {
        for (int i = 0; i < 10000; i++) {
            field.update();
        }
        assertFalse(field.getEnemyCars().isEmpty());
    }

    @Test
    void getScore() {
        Car enemyCar = new Car(1);
        enemyCar.setY(25);
        field.getEnemyCars().add(enemyCar);
        field.update();
        assertEquals(1, field.getScore());
    }

    @Test
    void getLevel() {
        Car enemyCar = new Car(1);
        enemyCar.setY(25);
        field.getEnemyCars().add(enemyCar);
        field.update();
        assertEquals(1, field.getLevel());
    }

    @Test
    void isGameOver() {
        Car enemyCar = new Car(1);
        enemyCar.setY(22);
        field.getEnemyCars().add(enemyCar);
        field.getPlayer().setX(1);
        field.getPlayer().setY(22);
        field.update();
        assertTrue(field.isGameOver());
    }

    @Test
    void getField() {
        int[][] fieldArray = field.getField();
        assertNotNull(fieldArray);
        assertEquals(25, fieldArray.length);
        assertEquals(9, fieldArray[0].length);
    }

    @Test
    void getEnemyCars() {
        List<Car> enemyCars = field.getEnemyCars();
        assertNotNull(enemyCars);
        assertTrue(enemyCars.isEmpty());

        Car enemyCar = new Car(1);
        field.getEnemyCars().add(enemyCar);
        assertEquals(1, field.getEnemyCars().size());
        assertEquals(enemyCar, field.getEnemyCars().get(0));
    }

    @Test
    void getPlayer() {
        Car player = field.getPlayer();
        assertNotNull(player);
        assertEquals(4, player.getX());
        assertEquals(19, player.getY());
    }
}