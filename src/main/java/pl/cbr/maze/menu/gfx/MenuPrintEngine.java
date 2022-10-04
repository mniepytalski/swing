package pl.cbr.maze.menu.gfx;

import pl.cbr.maze.menu.model.MenuModel;

import java.awt.*;

public interface MenuPrintEngine {

    void doDrawing(Graphics g, MenuModel menuModel);
}
