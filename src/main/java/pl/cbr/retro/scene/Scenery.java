package pl.cbr.retro.scene;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class Scenery extends KeyAdapter implements Drawing {

    public abstract String getName();

    @Override
    public abstract void doDrawing(Graphics g);

    public abstract void keyPressed(KeyEvent e);

}
