package core;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import objects.*;
import patterns.*;
import patterns.chain.*;

public class GamePanel extends JPanel {

    // Model + Subject/Observer
    private final ScoreSubject scoreSubject = new ScoreSubject(0, 5);
    private final ScoreBoardPanel hud = new ScoreBoardPanel();

    // Entities
    private final Basket basket = new Basket(260, 140, 80, 20, 3, 600);
    private final List<Shootable> balls = new ArrayList<>();

    // Prototype template for balls (Prototype)
    private final BallPrototype template;

    // Chain of Responsibility
    private final Handler chain;

    // Loop
    private Timer timer;

    // Screen
    private final int W = 600, H = 600;

    public GamePanel() {
        setPreferredSize(new Dimension(W, H));
        setBackground(Color.WHITE);
        setLayout(null);

        // Observer wiring
        scoreSubject.registerObserver(hud);
        hud.setBounds(0, 0, W, 40);
        add(hud);

        // Prototype built from Builder (Builder + Prototype)
        Ball base = new BallBuilder()
                .setX(W / 2)
                .setY(H - 40)
                .setRadius(14)
                .setDx(0)
                .setDy(0)
                .build();
        template = new BallPrototype(base);

        // Input through Adapter (Adapter)
        addKeyListener(new InputAdapter(this::shoot));
        setFocusable(true);

        // CoR chain
        Handler collision = new CollisionHandler();
        Handler cleanup = new CleanupHandler();
        Handler gameOver = new GameOverHandler();
        collision.setNext(cleanup);
        cleanup.setNext(gameOver);
        chain = collision;

        // Main loop
        timer = new Timer(16, e -> tick());
        timer.start();
    }

    private void shoot() {
        if (scoreSubject.isGameOver())
            return;

        // ballsLeft is decremented on SHOT; refunded on SCORE
        if (scoreSubject.getBallsLeft() > 0) {
            scoreSubject.decrementBall();

            // clone from prototype (Prototype)
            Ball spawned = template.cloneBall();
            if (spawned == null)
                return;

            // reset spawn at current launcher position (bottom center)
            spawned.setX(getWidth() / 2);
            spawned.setY(getHeight() - 40);
            // simple straight-up shot
            spawned.setDy(-6);
            spawned.setDx(0);

            Shootable ballEntity = spawned;

            // Decorate when last ball (Decorator)
            if (scoreSubject.getBallsLeft() == 0) {
                ballEntity = new GlowBallDecorator(ballEntity);
            }

            balls.add(ballEntity);
        }
    }

    private void tick() {
        // move basket
        basket.update();

        // move balls
        for (Shootable b : balls) {
            b.update();
        }

        // Run Chain of Responsibility
        GameContext ctx = new GameContext(balls, basket, scoreSubject);
        chain.handle(ctx);

        if (ctx.gameOver) {
            timer.stop();
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw court baseline
        g.setColor(new Color(240, 240, 240));
        g.fillRect(0, getHeight() - 30, getWidth(), 30);

        // draw basket
        basket.draw(g);

        // draw balls
        for (Shootable b : balls) {
            b.draw(g);
        }

        // HUD text (also updated via Observer on panel)
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.drawString("Score: " + scoreSubject.getScore(), 10, 56);
        g.drawString("Balls Left: " + scoreSubject.getBallsLeft(), 120, 56);

        if (scoreSubject.isGameOver()) {
            g.setFont(new Font("SansSerif", Font.BOLD, 28));
            g.drawString("Game Over!", getWidth() / 2 - 90, getHeight() / 2);
        }
    }
}
