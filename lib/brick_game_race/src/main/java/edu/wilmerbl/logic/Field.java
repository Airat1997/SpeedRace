package edu.wilmerbl.logic;

import edu.wilmerbl.logic.Car;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Field {

    private int[][] field;
    private List<Car> enemyCars;
    private Car player;

    private int score = 0;
    private int level = 0;

    private int updateCounter = 0;

    private boolean gameOver = false;

    public Field() {
        this.field = new int[25][9];
        this.enemyCars = new ArrayList<>();
        this.player = new Car();
    }

    public void update() {
        cleanField();
        carEnemyAdd();
        moveEnemyCars();
        printAllCars();
        updateCounter++;
    }

    private void cleanField() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 9; j++) {
                field[i][j] = 0;
            }
        }
    }

    public int getScore() {
        return score;
    }

    private void printAllCars() {
        Iterator<Car> iterator = enemyCars.iterator();
        while (iterator.hasNext()) {
            Car c = iterator.next();
            if (c.getY() == 26) {
                iterator.remove();
                score++;
                if (level < 10) {
                    level++;
                }
            } else {
                printEnemyCar(c);
            }
        }
        printPlayerCar();
    }

    public int getLevel() {
        return level;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void printPlayerCar() {
        if (field[player.getY() - 1][player.getX()] == 2) {
            gameOver = true;
        }
        field[player.getY() - 1][player.getX()] = 1;
        if (field[player.getY()][player.getX()] == 2) {
            gameOver = true;
        }
        field[player.getY()][player.getX()] = 1;
        if (field[player.getY()][player.getX() + 1] == 2) {
            gameOver = true;
        }
        field[player.getY()][player.getX() + 1] = 1;
        if (field[player.getY()][player.getX() - 1] == 2) {
            gameOver = true;
        }
        field[player.getY()][player.getX() - 1] = 1;
        if (field[player.getY() - 2][player.getX()] == 2) {
            gameOver = true;
        }
        field[player.getY() - 2][player.getX()] = 1;
        if (field[player.getY() - 2][player.getX() + 1] == 2) {
            gameOver = true;
        }
        field[player.getY() - 2][player.getX() + 1] = 1;
        if (field[player.getY() - 2][player.getX() - 1] == 2) {
            gameOver = true;
        }
        field[player.getY() - 2][player.getX() - 1] = 1;
        if (field[player.getY() - 3][player.getX()] == 2) {
            gameOver = true;
        }
        field[player.getY() - 3][player.getX()] = 1;
    }


    private void printEnemyCar(Car c) {
        if (c.getSizeEnemyCar() == 0) {
            field[c.getY() - 1][c.getX()] = 2;
            c.setSizeEnemyCar(c.getSizeEnemyCar() + 1);
        } else if (c.getSizeEnemyCar() == 1) {
            field[c.getY() - 2][c.getX() - 1] = 2;
            field[c.getY() - 2][c.getX() + 1] = 2;
            field[c.getY() - 2][c.getX()] = 2;
            field[c.getY() - 1][c.getX()] = 2;
            c.setSizeEnemyCar(c.getSizeEnemyCar() + 1);
        } else if (c.getSizeEnemyCar() == 2) {
            field[c.getY() - 2][c.getX() - 1] = 2;
            field[c.getY() - 2][c.getX() + 1] = 2;
            field[c.getY() - 2][c.getX()] = 2;
            field[c.getY() - 3][c.getX()] = 2;
            field[c.getY() - 1][c.getX()] = 2;
            c.setSizeEnemyCar(c.getSizeEnemyCar() + 1);
        } else if (c.getSizeEnemyCar() == 3) {
            field[c.getY() - 2][c.getX() - 1] = 2;
            field[c.getY() - 2][c.getX() + 1] = 2;
            field[c.getY() - 2][c.getX()] = 2;
            field[c.getY() - 3][c.getX()] = 2;
            field[c.getY() - 4][c.getX() - 1] = 2;
            field[c.getY() - 4][c.getX() + 1] = 2;
            field[c.getY() - 4][c.getX()] = 2;
            field[c.getY() - 1][c.getX()] = 2;
            c.setSizeEnemyCar(c.getSizeEnemyCar() + 1);
        } else if (c.getSizeEnemyCar() == 4) {
            field[c.getY() - 2][c.getX() - 1] = 2;
            field[c.getY() - 2][c.getX() + 1] = 2;
            field[c.getY() - 2][c.getX()] = 2;
            field[c.getY() - 3][c.getX()] = 2;
            field[c.getY() - 4][c.getX() - 1] = 2;
            field[c.getY() - 4][c.getX() + 1] = 2;
            field[c.getY() - 4][c.getX()] = 2;
            field[c.getY() - 5][c.getX()] = 2;
            field[c.getY() - 1][c.getX()] = 2;
        }
    }

    private void moveEnemyCars() {
        for (Car c : enemyCars) {
            c.setY(c.getY() + 1);
        }
    }

    private void carEnemyAdd() {
        Random rand = new Random();
        int delay = 5;
        if (rand.nextBoolean() && enemyCars.size() < 3 && (updateCounter % delay == 0)) {
            int[] arrayXPosition = {1, 4, 7};
            int position = 0;
            int finalPosition = position;
            do {
                position = arrayXPosition[rand.nextInt(arrayXPosition.length)];
            } while (enemyCars.stream().anyMatch(car -> car.getX() == finalPosition));
            enemyCars.add(new Car(position));
        }
    }

    public int[][] getField() {
        return field;
    }

    public List<Car> getEnemyCars() {
        return enemyCars;
    }

    public Car getPlayer() {
        return player;
    }

}
