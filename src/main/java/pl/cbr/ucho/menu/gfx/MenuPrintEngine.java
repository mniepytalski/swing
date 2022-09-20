package pl.cbr.ucho.menu.gfx;

import pl.cbr.ucho.menu.model.MenuModel;

import java.awt.*;

public interface MenuPrintEngine {

    void doDrawing(Graphics g, MenuModel menuModel);
}
