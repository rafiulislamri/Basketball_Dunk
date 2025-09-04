package main;

import core.GameFrame;
import core.GamePanel;

public class GameMain {
    public static void main(String[] args) {
        GameFrame frame = GameFrame.getInstance(); // Singleton
        frame.add(new GamePanel());
        frame.setVisible(true);
    }
}
