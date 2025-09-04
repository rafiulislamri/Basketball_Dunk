package core;

import java.util.List;
import objects.Shootable;
import objects.Basket;
import patterns.ScoreSubject;

public class GameContext {
    public final List<Shootable> balls;
    public final Basket basket;
    public final ScoreSubject scoreSubject;
    public boolean gameOver;

    public GameContext(List<Shootable> balls, Basket basket, ScoreSubject scoreSubject) {
        this.balls = balls;
        this.basket = basket;
        this.scoreSubject = scoreSubject;
        this.gameOver = false;
    }
}
