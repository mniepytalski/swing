package pl.cbr.retro.scene.maze;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.cbr.retro.scene.Scenery;

import java.awt.*;
import java.awt.event.KeyEvent;

@Slf4j
@Service
@AllArgsConstructor
public class MazeStage extends Scenery {

    private final MazeKeyAdapter mazeKeyAdapter;
    private final MazeGfx mazeGfx;

    public String getName() {
        return "MAZE";
    }

    @Override
    public void doDrawing(Graphics g) {
        mazeGfx.doDrawing(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        mazeKeyAdapter.keyPressed(e);
    }
}
