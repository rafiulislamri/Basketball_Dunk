package core;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private static GameFrame instance;

    private GameFrame() {
        setTitle("Basketball Dunk");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static GameFrame getInstance() {
        if (instance == null) {
            instance = new GameFrame();
        }
        return instance;
    }
}
