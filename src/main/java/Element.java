import com.googlecode.lanterna.graphics.TextGraphics;

public class Element {
    private Position position;

    public Element (Position pos) {
        this.position = pos;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void draw(TextGraphics graphics) {
    }
}
