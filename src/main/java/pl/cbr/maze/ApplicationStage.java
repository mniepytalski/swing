package pl.cbr.maze;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class ApplicationStage extends KeyAdapter implements Drawing {

    public abstract String getName();

    @Override
    public abstract void doDrawing(Graphics g);

    public abstract void keyPressed(KeyEvent e);

}
