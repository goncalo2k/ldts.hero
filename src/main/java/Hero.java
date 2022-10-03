import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero {
    private int x;
    private int y;

    //Default Constructor
    public Hero(int x, int y){
        this.x = x;
        this.y = y;
    }

    //Getters and Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //Draws hero on the screen
    public void draw(Screen screen) {
        screen.setCharacter(this.getX(), this.getY(), TextCharacter.fromCharacter('X')[0]);
    }

    //Moves Hero up by 1px.
    public void moveUp() {
        this.setY(this.getY()-1);
    }

    //Moves Hero down by 1px.
    public void moveDown() {
        this.setY(this.getY()+1);
    }

    //Moves Hero left by 1px.
    public void moveLeft() {
        this.setX(this.getX()-1);
    }

    //Moves Hero right by 1px.
    public void moveRight() {
        this.setX(this.getX()+1);
    }
}