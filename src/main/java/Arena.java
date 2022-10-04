import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    private int width;
    private int height;
    private Hero hero;

    public Arena(int x, int y, Hero h) {
        this.width = x;
        this.height = y;
        this.hero = h;
    }

    public void draw(Screen screen) {
        hero.draw(screen);
    }
    public void processKey(KeyStroke key) {
        KeyType pressed = key.getKeyType();
        switch (pressed)
        {
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
        }
    }

    //Sets the hero position after the action has been taken
    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    public boolean canHeroMove(Position position) {
        if ((position.getX() <= width || position.getX() >= width) &&
            (position.getY() <= height || position.getY() >= height)) {
            return true;
        }
        else return false;
    }
}
