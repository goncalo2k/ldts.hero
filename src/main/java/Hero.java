import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero extends Element{
    public Hero(Position pos) {
        super(pos);
    }

    //Draws hero on the screen
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#A41623"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(this.getPosition().getX(),this.getPosition().getY()), "X");
    }

    //Returns the Hero's position after moving it down by 1px.
    public Position moveUp() {
        return new Position(this.getPosition().getX(), this.getPosition().getY()-1);
    }

    //Returns the Hero's position after moving it up by 1px.
    public Position moveDown() {
        return new Position(this.getPosition().getX(),this.getPosition().getY()+1);
    }

    //Returns the Hero's position after moving it to the left by 1px.
    public Position moveLeft() {
        return new Position(this.getPosition().getX()-1, this.getPosition().getY());
    }

    //Returns the Hero's position after moving it to the right by 1px.
    public Position moveRight() {
        return new Position(this.getPosition().getX()+1, this.getPosition().getY());
    }
}

