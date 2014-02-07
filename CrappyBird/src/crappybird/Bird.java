package crappybird;

import java.awt.Color;
import java.awt.Rectangle;

/**
 * Bird in CrappyBird.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Bird extends Rectangle {

    private boolean alive = true;
    private Color color;
    private double stepY;

    public Bird(int height, int width, int y, Color color) {
        this.x = 10;
        this.color = color;
        this.height = height;
        this.width = width;
        this.y = y;
        this.stepY = 1;
    }

    public void step() {
        y += stepY;
        if (stepY < 1) {
            stepY += 0.1;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getStepY() {
        return stepY;
    }

    public void setStepY(int stepY) {
        this.stepY = stepY;
    }
}