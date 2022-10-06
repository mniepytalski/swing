package pl.cbr.maze.game.gfx;

import org.springframework.stereotype.Service;
import pl.cbr.maze.Drawing;
import pl.cbr.maze.game.model.MazeGenerator;
import pl.cbr.maze.game.model.MazeModel;

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
