package patterns.chain;

import core.GameContext;

public class GameOverHandler extends Handler {
    @Override
    protected void process(GameContext ctx) {
        if (ctx.scoreSubject.getBallsLeft() == 0 && ctx.balls.isEmpty()) {
            ctx.scoreSubject.setGameOver();
            ctx.gameOver = true;
        }
    }
}
