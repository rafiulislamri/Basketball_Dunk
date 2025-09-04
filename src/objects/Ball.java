package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball implements Shootable, Cloneable {
    private int x, y, r;
    private int dx, dy;
    private boolean active = true;
    private boolean scored = false;

    public Ball(int x, int y, int r, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(230, 110, 20));
        g.fillOval(x - r, y - r, r * 2, r * 2);
        g.setColor(Color.BLACK);
        g.drawOval(x - r, y - r, r * 2, r * 2);
        // simple seams
        g.drawArc(x - r / 2, y - r, r, r * 2, 0, 180);
        g.drawArc(x - r, y - r / 2, r * 2, r, 90, 180);
    }

    @Override
    public void update() {
        if (!active)
            return;
        x += dx;
        y += dy;

        // gravity-like effect (tiny)
        dy += 0; // keep straight shot simple as per requirement

        // out of screen at top => inactive (miss)
        if (y + r < 0) {
            active = false;
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getRadius() {
        return r;
    }

    @Override
    public int getDx() {
        return dx;
    }

    @Override
    public int getDy() {
        return dy;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setDx(int dx) {
        this.dx = dx;
    }

    @Override
    public void setDy(int dy) {
        this.dy = dy;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isScored() {
        return scored;
    }

    @Override
    public void setScored(boolean scored) {
        this.scored = scored;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x - r, y - r, r * 2, r * 2);
    }

    @Override
    public Ball clone() {
        try {
            return (Ball) super.clone();
        } catch (Exception e) {
            return new Ball(x, y, r, dx, dy);
        }
    }
}
