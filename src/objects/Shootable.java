package objects;

import java.awt.*;

public interface Shootable extends GameObject {
    int getX();

    int getY();

    int getRadius();

    int getDx();

    int getDy();

    void setX(int x);

    void setY(int y);

    void setDx(int dx);

    void setDy(int dy);

    boolean isActive();

    void setActive(boolean active);

    boolean isScored();

    void setScored(boolean scored);

    Rectangle getBounds();
}
