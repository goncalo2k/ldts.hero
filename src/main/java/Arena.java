import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    public boolean gameOver = false;

    public Arena(int x, int y, Hero h) {
        this.width = x;
        this.height = y;
        this.hero = h;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#A59C9C"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins) {
            coin.draw(graphics);
            if (retrieveCoin(coin)) {
                coins.remove(coin);
                break;
            }
        }
        for (Monster monster : monsters) {
            monster.draw(graphics);
        }
        hero.draw(graphics);
    }
    public void processKey(KeyStroke key) {
        KeyType pressed = key.getKeyType();
        switch (pressed)
        {
            case ArrowDown:
                moveHero(hero.moveDown());
                moveMonsters();
                break;
            case ArrowUp:
                moveHero(hero.moveUp());
                moveMonsters();
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                moveMonsters();
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                moveMonsters();
                break;
        }
        if (verifyMonsterCollision(hero)) {
            System.out.print("Game over! Your hero has been slain! :c");
            gameOver = true;
        }

    }

    //Sets the hero position after the action has been taken
    private void moveHero(Position position) {
        if (canMove(position))
            hero.setPosition(position);
    }

    private void moveMonsters() {
        for (Monster monster : monsters)
        {
            Position spos = monster.getPosition();
            Random rand = new Random();
            int n = rand.nextInt(4);
            switch (n) {
                case 0:
                    monster.setPosition(new Position(monster.getPosition().getX()+1,monster.getPosition().getY()));
                    break;
                case 1:
                    monster.setPosition(new Position(monster.getPosition().getX()-1,monster.getPosition().getY()));
                    break;
                case 2:
                    monster.setPosition(new Position(monster.getPosition().getX(),monster.getPosition().getY()+1));
                    break;
                case 3:
                    monster.setPosition(new Position(monster.getPosition().getX(),monster.getPosition().getY()-1));
                    break;
                case 4:
                    break;
            }
            if (!canMove(monster.getPosition()))
                monster.setPosition(spos);
        }
    }

    private boolean canMove(Position position) {
        for (Wall wall: walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyMonsterCollision(Hero hero) {
        for (Monster monster : monsters) {
            if (hero.getPosition().equals(monster.getPosition()))
                return true;
        }
        return false;
    }

    private boolean retrieveCoin(Coin coin) {
        return hero.getPosition().equals(coin.getPosition());
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(new Position(c, 0)));
            walls.add(new Wall(new Position(c, height - 1)));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(new Position(0, r)));
            walls.add(new Wall(new Position(width - 1, r)));
        }
        return walls;
    }
    private List<Coin> createCoins() {
        Random random = new Random();
        List<Coin> coins = new ArrayList<>();
        /*for (int i = 0; i < 5; i++)
            coins.add(new Coin(new Position((width - 2) + 1, random.nextInt(height - 2) + 1)));*/
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(new Position(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1)));
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(new Position(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1)));
        return monsters;
    }
}
