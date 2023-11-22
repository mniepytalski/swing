package pl.cbr.retro.scene.maze;

import org.springframework.stereotype.Service;
import pl.cbr.retro.scene.Drawing;
import pl.cbr.retro.scene.maze.model.MazeGenerator;
import pl.cbr.retro.scene.maze.model.MazeModel;

import java.awt.*;

@Service
public class MazeGfx implements Drawing {

    @Override
    public void doDrawing(Graphics g) {
        MazeGenerator mazeGenerator = MazeModel.getMaze("game1");
        g.setColor(Color.BLACK);
        mazeGenerator.doDrawing(g);
    }
}
