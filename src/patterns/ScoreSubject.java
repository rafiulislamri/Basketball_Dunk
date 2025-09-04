package patterns;

import java.util.ArrayList;
import java.util.List;

public class ScoreSubject implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private int score;
    private int ballsLeft;
    private boolean gameOver = false;

    public ScoreSubject(int score, int ballsLeft) {
        this.score = score;
        this.ballsLeft = ballsLeft;
    }

    public int getScore() {
        return score;
    }

    public int getBallsLeft() {
        return ballsLeft;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void incrementScore() {
        score++;
        notifyObservers();
    }

    public void decrementBall() {
        ballsLeft = Math.max(0, ballsLeft - 1);
        notifyObservers();
    }

    public void refundBall() {
        ballsLeft++;
        notifyObservers();
    }

    public void setGameOver() {
        gameOver = true;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
        o.update(score, ballsLeft, gameOver);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(score, ballsLeft, gameOver);
        }
    }
}
