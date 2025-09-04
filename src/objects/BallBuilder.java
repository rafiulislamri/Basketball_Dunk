package objects;

public class BallBuilder {
    private int x = 0, y = 0, r = 12, dx = 0, dy = 0;

    public BallBuilder setX(int x) {
        this.x = x;
        return this;
    }

    public BallBuilder setY(int y) {
        this.y = y;
        return this;
    }

    public BallBuilder setRadius(int r) {
        this.r = r;
        return this;
    }

    public BallBuilder setDx(int dx) {
        this.dx = dx;
        return this;
    }

    public BallBuilder setDy(int dy) {
        this.dy = dy;
        return this;
    }

    public Ball build() {
        return new Ball(x, y, r, dx, dy);
    }
}
