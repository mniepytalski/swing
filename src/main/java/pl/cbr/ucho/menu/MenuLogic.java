package pl.cbr.ucho.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.cbr.ucho.menu.gfx.MenuSimplePrintEngine;
import pl.cbr.ucho.menu.model.MenuModel;

import java.awt.*;
import java.awt.event.KeyAdapter;

@Service
@Slf4j
@Getter
@AllArgsConstructor
public class MenuLogic extends KeyAdapter {
    private final MenuModel menuModel;
    private final MenuSimplePrintEngine menuPrintEngine;

    public void doDrawing(Graphics g) {
        menuPrintEngine.doDrawing(g, menuModel);
    }
}
