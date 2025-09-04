package patterns.chain;

import core.GameContext;
import objects.Shootable;

import java.awt.Rectangle;

public class CollisionHandler extends Handler {
    @Override
    protected void process(GameContext ctx) {
        Rectangle rim = ctx.basket.getRimBounds();

        for (Shootable b : ctx.balls) {
            if (!b.isActive())
                continue;
            // score condition: ball intersects rim rectangle while moving upward (dy<0)
            if (b.getBounds().intersects(rim) && b.getDy() < 0) {
                b.setActive(false);
                b.setScored(true);
                ctx.scoreSubject.incrementScore();
                // refund a ball because it scored (spec: deduct only on miss)
                ctx.scoreSubject.refundBall();
            }
        }
    }
}
