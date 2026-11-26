import zero.Key;
import zero.Color;
import zero.GameApp;

public class Main extends GameApp {
    private Player player1;
    private Player player2;
    @Override public void settings() {
        padding(20);
        size(640, 400);
    }

    @Override public void setup() {
        player1 = new Player(40, 180);
        player2 = new Player(600, 180);
    }

    @Override public void update(double seconds) {
        int horizontal = 0;
        int vertical = 0;
        if (keyDown(Key.A)) horizontal--;
        if (keyDown(Key.D)) horizontal++;
        if (keyDown(Key.W)) vertical--;
        if (keyDown(Key.S)) vertical++;
        player1.update(horizontal, vertical, seconds, width(), height());

        if (keyDown(Key.LEFT)) horizontal--;
        if (keyDown(Key.RIGHT)) horizontal++;
        if (keyDown(Key.UP)) vertical--;
        if (keyDown(Key.DOWN)) vertical++;
        player2.update(horizontal, vertical, seconds, width(), height());
    }
    @Override public void draw() {
        background(Color.rgb(240, 248, 255));
        fill(Color.rgb(47, 79, 79));
        player1.draw(this);
        player2.draw(this);
    }
    public static void main(String[] args) { launch(args); }
}
