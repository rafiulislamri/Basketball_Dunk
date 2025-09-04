package patterns;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputAdapter extends KeyAdapter {
    private final Runnable onShoot;

    public InputAdapter(Runnable onShoot) {
        this.onShoot = onShoot;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            onShoot.run();
        }
    }
}
