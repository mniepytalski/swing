package pl.cbr.maze.menu.gfx;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cbr.maze.Drawing;
import pl.cbr.maze.menu.model.NavigationMode;
import pl.cbr.maze.menu.config.*;
import pl.cbr.maze.menu.model.MenuModel;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class MenuSimplePrintEngine implements Drawing {

    private final MenuSimplePrintCfg menuCfg;
    private final MenuModel menuModel;

    public void doDrawing(Graphics g) {
        g.setFont(new Font(menuCfg.getFontName(), Font.BOLD, menuCfg.getFontSize()));
        AtomicInteger i = new AtomicInteger();
        menuModel.getActualParentElement().actualizePosition();
        menuModel.getActualParentElement().getElements().forEach(e -> print(g, e, i.getAndIncrement()));
    }

    private void print(Graphics g, MenuElement element, int y) {
        if ( element.getNavigationMode()==NavigationMode.EDIT ) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(element.isMarked() ? Color.BLUE : Color.BLACK);
        }
        g.drawString(element.getText(), menuCfg.getStartX(), menuCfg.getStartY() + y * g.getFont().getSize());
        switch(element.getElementType()) {
            case FLAG:
                printFlag(g, element, y);
                break;
            case DIGIT:
                printDigit(g, element, y);
                break;
            case TEXT:
                printText(g, element, y);
                break;
        }
    }

    private void printFlag(Graphics g, MenuElement element, int y) {
        if ( element.getValue().getFlag().isActual() ) {
            g.fillRect(menuCfg.getStartX() + menuCfg.getOffsetX(), menuCfg.getStartY() + (y - 1) * g.getFont().getSize(),
                    menuCfg.getFontSize(), menuCfg.getFontSize());
        } else {
            g.drawRect(menuCfg.getStartX() + menuCfg.getOffsetX(), menuCfg.getStartY() + (y - 1) * g.getFont().getSize(),
                    menuCfg.getFontSize(), menuCfg.getFontSize());
        }
    }

    private void printDigit(Graphics g, MenuElement element, int y) {
        ValueDigitConfig valueDigit = element.getValue().getDigit();
        if ( element.getNavigationMode() != null &&
                element.getNavigationMode() == NavigationMode.EDIT) {
            g.setColor(Color.BLUE);
        }
        g.drawString(valueDigit.printInfo(), menuCfg.getStartX() + menuCfg.getOffsetX(),
                menuCfg.getStartY() + y * g.getFont().getSize());
    }

    private void printText(Graphics g, MenuElement element, int y) {
        ValueTextConfig valueText = element.getValue().getText();
        StringBuilder info = new StringBuilder();
        String markedText = valueText.getActualText();
        int i = 0;
        for (String text : valueText.getAllTexts() ) {
            if ( i++ > 0 ) {
                info.append(" ");
            }
            if ( text.equals(markedText) ) {
                info.append("[").append(text).append("]");
            } else {
                info.append(text);
            }
        }
        g.drawString(info.toString(), menuCfg.getStartX()+menuCfg.getOffsetX(),
                menuCfg.getStartY() + y * g.getFont().getSize());
    }
}
