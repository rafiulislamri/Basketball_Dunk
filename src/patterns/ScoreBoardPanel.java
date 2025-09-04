package patterns;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class ScoreBoardPanel extends JPanel implements Observer {
    private int score = 0;
    private int ballsLeft = 5;
    private boolean gameOver = false;

    public ScoreBoardPanel() {
        setOpaque(false);
    }

    @Override
    public void update(int score, int ballsLeft, boolean gameOver) {
        this.score = score;
        this.ballsLeft = ballsLeft;
        this.gameOver = gameOver;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 0, 0, 200));
        g.setFont(new Font("SansSerif", Font.BOLD, 16));
        g.drawString("Score: " + score, 10, 24);
        g.drawString("Balls Left: " + ballsLeft, 110, 24);
        if (gameOver) {
            g.setColor(new Color(200, 0, 0, 200));
            g.drawString("Game Over", 240, 24);
        }
    }
}
