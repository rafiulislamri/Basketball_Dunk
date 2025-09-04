package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Basket implements GameObject {
    private int x, y, w, h;
    private int vx;
    private int screenW;

    public Basket(int x, int y, int w, int h, int vx, int screenW) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.vx = vx;
        this.screenW = screenW;
    }

    @Override
    public void draw(Graphics g) {
        // backboard
        g.setColor(new Color(220, 220, 220));
        g.fillRect(x + w / 2 - 4, y - 40, 8, 40);

        // rim (scoring area)
        g.setColor(Color.RED);
        g.fillRect(x, y, w, h);

        // net lines
        g.setColor(new Color(255, 255, 255, 180));
        for (int i = 0; i <= w; i += 10) {
            g.drawLine(x + i, y + h, x + i - 5, y + h + 15);
        }
    }

    @Override
    public void update() {
        x += vx;
        if (x <= 0 || x + w >= screenW) {
            vx = -vx;
        }
    }

    public Rectangle getRimBounds() {
        // slightly inner rectangle for fair scoring
        return new Rectangle(x + 4, y, w - 8, h + 4);
    }
}
