package patterns.chain;

import core.GameContext;

public abstract class Handler {
    private Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public final void handle(GameContext ctx) {
        process(ctx);
        if (next != null)
            next.handle(ctx);
    }

    protected abstract void process(GameContext ctx);
}
