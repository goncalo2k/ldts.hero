import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero {

    private Position position;

    //Default Constructor
    public Hero(Position pos){
        this.position = pos;
    }
    //Getter and Setter
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    //Draws hero on the screen
    public void draw(Screen screen) {
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('X')[0]);
    }



    //Returns the Hero's position after moving it down by 1px.
    public Position moveUp() {
        return new Position(position.getX(), position.getY()-1);
    }

    //Returns the Hero's position after moving it up by 1px.
    public Position moveDown() {
        return new Position(position.getX(),position.getY()+1);
    }

    //Returns the Hero's position after moving it to the left by 1px.
    public Position moveLeft() {
        return new Position(position.getX()-1, position.getY());
    }

    //Returns the Hero's position after moving it to the right by 1px.
    public Position moveRight() {
        return new Position(position.getX()+1, position.getY());
    }
}

