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
        g.setColor(Color.ORANGE);
        g.drawRect(350, 350, 100, 100);
        MazeGenerator mazeGenerator = MazeModel.getMaze();
        g.setColor(Color.BLACK);
        mazeGenerator.doDrawing(g);
    }
}
