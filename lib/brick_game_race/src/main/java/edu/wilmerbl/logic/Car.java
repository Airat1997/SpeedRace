package edu.wilmerbl.logic;

public class Car {

    private int x;
    private int y;
    private int sizeEnemyCar = 0;

    public Car() { //player
        this.x = 4;
        this.y = 19;
    }

    public Car(int x) { //enemy
        this.x = x;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getSizeEnemyCar() {
        return sizeEnemyCar;
    }

    public void setSizeEnemyCar(int sizeEnemyCar) {
        this.sizeEnemyCar = sizeEnemyCar;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void moveLeft() {
        if (this.x != 1) {
            this.x = x - 3;
        }

    }

    public void moveRight() {
        if (this.x != 7) {
            this.x = x + 3;
        }
    }

    public void moveDown() {
        this.y = y - 1;
    }
}
