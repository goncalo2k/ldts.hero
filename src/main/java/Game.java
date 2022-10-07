import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
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
    private Arena arena;
    private TextGraphics graphics;

    public Game() {
            try {
                hero = new Hero(new Position(10,10));
                arena = new Arena(80,24, hero);
                TerminalSize terminalSize = new TerminalSize(80, 24);
                DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
                Terminal terminal = terminalFactory.createTerminal();

                screen = new TerminalScreen(terminal);
                graphics = screen.newTextGraphics();
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
            arena.draw(graphics);
            screen.refresh();
        } catch(IOException e) {
            e.printStackTrace();
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
                else arena.processKey(key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
