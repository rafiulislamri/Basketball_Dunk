package patterns.chain;

import core.GameContext;
import objects.Shootable;

import java.util.Iterator;

public class CleanupHandler extends Handler {
    @Override
    protected void process(GameContext ctx) {
        Iterator<Shootable> it = ctx.balls.iterator();
        while (it.hasNext()) {
            Shootable b = it.next();
            if (!b.isActive()) {
                it.remove();
            }
        }
    }
}
