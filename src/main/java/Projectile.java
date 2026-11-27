import zero.Color;
import zero.GameApp;
public class Projectile {
    private double x;
    private double y;
    private final double size = 8;
    private final double speed = 300;
    private final int direction;

    public Projectile(double startX, double startY, int direction) {
        x = startX;
        y = startY;
        this.direction = direction;
    }
    public void update(double seconds) {
        x += direction * speed * seconds;
    }

    public void draw(GameApp app) {
        app.fill(Color.rgb(0, 100, 255));
        app.rect(x, y, size, size);
    }

    public boolean isOffScreen(double width) {
        return x < 0 || x > width;
    }

    public double getX() {
        return x;
    }

    public double getY() { 
        return y;
    }
}
