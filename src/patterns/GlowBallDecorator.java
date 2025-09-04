package patterns;

import java.awt.Graphics;
import java.awt.Color;

import objects.Shootable;

public class GlowBallDecorator extends BallDecorator {
    public GlowBallDecorator(Shootable inner) {
        super(inner);
    }

    @Override
    public void draw(Graphics g) {
        // glow halo
        int x = getX(), y = getY(), r = getRadius();
        g.setColor(new Color(255, 215, 0, 120));
        g.fillOval(x - r - 6, y - r - 6, (r * 2) + 12, (r * 2) + 12);
        super.draw(g);
    }
}
