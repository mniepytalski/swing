package pl.cbr.retro.scene.maze;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cbr.retro.scene.ActualScenery;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Service
@AllArgsConstructor
public class MazeKeyAdapter extends KeyAdapter {

    private final ActualScenery gameState;

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gameState.setScenery("MENU");
        }
    }
}
