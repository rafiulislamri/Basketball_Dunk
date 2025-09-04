package patterns;

import java.awt.Graphics;
import java.awt.Rectangle;

import objects.Shootable;

public abstract class BallDecorator implements Shootable {
    protected final Shootable inner;

    protected BallDecorator(Shootable inner) {
        this.inner = inner;
    }

    @Override
    public void draw(Graphics g) {
        inner.draw(g);
    }

    @Override
    public void update() {
        inner.update();
    }

    @Override
    public int getX() {
        return inner.getX();
    }

    @Override
    public int getY() {
        return inner.getY();
    }

    @Override
    public int getRadius() {
        return inner.getRadius();
    }

    @Override
    public int getDx() {
        return inner.getDx();
    }

    @Override
    public int getDy() {
        return inner.getDy();
    }

    @Override
    public void setX(int x) {
        inner.setX(x);
    }

    @Override
    public void setY(int y) {
        inner.setY(y);
    }

    @Override
    public void setDx(int dx) {
        inner.setDx(dx);
    }

    @Override
    public void setDy(int dy) {
        inner.setDy(dy);
    }

    @Override
    public boolean isActive() {
        return inner.isActive();
    }

    @Override
    public void setActive(boolean active) {
        inner.setActive(active);
    }

    @Override
    public boolean isScored() {
        return inner.isScored();
    }

    @Override
    public void setScored(boolean scored) {
        inner.setScored(scored);
    }

    @Override
    public Rectangle getBounds() {
        return inner.getBounds();
    }
}
