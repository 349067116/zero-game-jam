import zero.Key;
import zero.Color;
import zero.GameApp;
import java.util.ArrayList;
import zero.ui.Label;

public class Main extends GameApp {
    private Player player1;
    private Player player2;
    private Label message;

    private ArrayList<Projectile> projectiles;

    private double shootCooldown1 = 0;
    private double shootCooldown2 = 0;


    @Override public void settings() {
        title("Greeting");
        padding(20);
        size(640, 400);
    }

    @Override public void setup() {
        message = new Label("Player 1: WASD + SPACE, Player 2: ARROWS + L");
        player1 = new Player(40, 180);
        player2 = new Player(600, 180);
        controls(message);
        projectiles = new ArrayList<>();
    }

    @Override public void update(double seconds) {
        int horizontal = 0;
        int vertical = 0;
        int horizontal2 = 0;
        int vertical2 = 0;
        if (keyDown(Key.A)) horizontal--;
        if (keyDown(Key.D)) horizontal++;
        if (keyDown(Key.W)) vertical--;
        if (keyDown(Key.S)) vertical++;
        player1.update(horizontal, vertical, seconds, width(), height());

        if (keyDown(Key.LEFT)) horizontal2--;
        if (keyDown(Key.RIGHT)) horizontal2++;
        if (keyDown(Key.UP)) vertical2--;
        if (keyDown(Key.DOWN)) vertical2++;
        player2.update(horizontal2, vertical2, seconds, width(), height());

        shootCooldown1 -= seconds;
        shootCooldown2 -= seconds;

        if (keyDown(Key.SPACE) && shootCooldown1 <= 0) {
            projectiles.add(new Projectile(player1.getX() + 20, player1.getY() + 6, 1));

            shootCooldown1 = 0.4;
        }

        if (keyDown(Key.L) && shootCooldown2 <= 0) {
            projectiles.add(new Projectile(player2.getX() - 8, player2.getY() + 6, -1));

            shootCooldown2 = 0.4;
        }

        for(int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile p = projectiles.get(i);
            p.update(seconds);

            if (p.isOffScreen(width())) {
                projectiles.remove(i);
                continue;
            }

            if (p.getX() < player2.getX() + 20 && p.getX() + 8 > player2.getX() && p.getY() < player2.getY() + 20 && p.getY() + 8 > player2.getY()) {
            message.setText("Player 2 hit!");
            projectiles.remove(i);
            continue;
            }

            if(p.getX() < player1.getX() + 20 && p.getX() + 8 > player1.getX() && p.getY() < player1.getY() + 20 && p.getY() + 8 > player1.getY()) {
                message.setText("Player 1 hit!");
                projectiles.remove(i);
            }
        }
    }
    @Override public void draw() {
        background(Color.rgb(240, 248, 255));
        fill(Color.rgb(47, 79, 79));
        player1.draw(this);
        player2.draw(this);

        for(Projectile p : projectiles) {
            p.draw(this);
        }
    }
    public static void main(String[] args) { launch(args); }
}
