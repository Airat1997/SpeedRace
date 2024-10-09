package edu.wilmerbl.logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarTest {
    private Car playerCar;
    private Car enemyCar;

    @BeforeEach
    void setUp() {
        playerCar = new Car();
        enemyCar = new Car(4);
    }
    @Test
    void testPlayerCarInitialPosition() {
        assertEquals(4, playerCar.getX());
        assertEquals(19, playerCar.getY());
        assertEquals(0, playerCar.getSizeEnemyCar());
    }

    @Test
    void testEnemyCarInitialPosition() {
        assertEquals(4, enemyCar.getX());
        assertEquals(0, enemyCar.getY());
        assertEquals(0, enemyCar.getSizeEnemyCar());
    }
    @Test
    void testSetX() {
        playerCar.setX(5);
        assertEquals(5, playerCar.getX());
    }

    @Test
    void testSetY() {
        playerCar.setY(20);
        assertEquals(20, playerCar.getY());
    }
    @Test
    void moveLeft() {
        playerCar.moveLeft();
        assertEquals(1, playerCar.getX());

        // Попытка двигаться влево, когда x уже равен 1
        playerCar.moveLeft();
        assertEquals(1, playerCar.getX());
    }

    @Test
    void moveRight() {
        playerCar.moveRight();
        assertEquals(7, playerCar.getX());

        // Попытка двигаться вправо, когда x уже равен 7
        playerCar.moveRight();
        assertEquals(7, playerCar.getX());
    }

    @Test
    void moveDown() {
        playerCar.moveDown();
        assertEquals(18, playerCar.getY());
    }
}