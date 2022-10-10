package pl.cbr.maze.game;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.cbr.maze.GameStage;
import pl.cbr.maze.game.gfx.MazeGfx;

import java.awt.*;
import java.awt.event.KeyEvent;

@Slf4j
@Service
@AllArgsConstructor
public class MazeStage extends GameStage {

    private final GameKeyAdapter gameKeyAdapter;
    private final MazeGfx mazeGfx;

    @Override
    public void doDrawing(Graphics g) {
        mazeGfx.doDrawing(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameKeyAdapter.keyPressed(e);
    }
}
