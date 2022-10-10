package pl.cbr.maze.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cbr.maze.GameState;
import pl.cbr.maze.SystemState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Service
@AllArgsConstructor
public class GameKeyAdapter extends KeyAdapter {

    private final GameState gameState;

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gameState.setSystemState(SystemState.MENU);
        }
    }
}
