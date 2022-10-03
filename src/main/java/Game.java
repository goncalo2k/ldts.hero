import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    Screen screen;

    private Hero hero;
    public Game() {
            try {
                hero = new Hero(10,10);
                TerminalSize terminalSize = new TerminalSize(40, 20);
                DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
                Terminal terminal = terminalFactory.createTerminal();

                screen = new TerminalScreen(terminal);
                screen.setCursorPosition(null); // we don't need a cursor
                screen.startScreen(); // screens must be started
                screen.doResizeIfNecessary(); // resize screen if necessary
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    private void draw() {
        try {
            screen.clear();
            hero.draw(screen);
            screen.refresh();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke key) {
        KeyType pressed = key.getKeyType();
        switch (pressed)
        {
            case ArrowDown:
                hero.moveDown();
                break;
            case ArrowUp:
                hero.moveUp();
                break;
            case ArrowLeft:
                hero.moveLeft();
                break;
            case ArrowRight:
                hero.moveRight();
                break;

        }
    }

    public void run() {
        while (true) {
            try {
                draw();
                KeyStroke key = screen.readInput();
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    screen.close();
                }
                else if (key.getKeyType() == KeyType.EOF) {
                    break;
                }
                else processKey(key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
