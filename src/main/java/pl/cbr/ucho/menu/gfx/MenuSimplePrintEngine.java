package pl.cbr.ucho.menu.gfx;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cbr.ucho.menu.model.NavigationMode;
import pl.cbr.ucho.menu.config.*;
import pl.cbr.ucho.menu.model.MenuModel;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class MenuSimplePrintEngine implements MenuPrintEngine {

    private final MenuSimplePrintCfg menuCfg;

    public void doDrawing(Graphics g, MenuModel menuModel) {
        g.setFont(new Font(menuCfg.getFontName(), Font.BOLD, menuCfg.getFontSize()));
        AtomicInteger i = new AtomicInteger();
        g.setColor(Color.DARK_GRAY);
        g.drawString("depth:"+menuModel.getDepth(),menuCfg.getStartX(), 300);
        menuModel.getActualElementConfig().actualizePosition();
        menuModel.getActualElementConfig().getElements().forEach(e -> print(g, e, i.getAndIncrement()));
    }

    private void print(Graphics g, ElementConfig element, int y) {
        g.setColor(element.isMarked() ? Color.BLUE : Color.BLACK);
        g.drawString(element.getText(), menuCfg.getStartX(), menuCfg.getStartY() + y * g.getFont().getSize());
        if ( element.getValueType() == ElementType.FLAG ) {
            printFlag(g, element, y);
        }
        if ( element.getValueType() == ElementType.DIGIT ) {
            printDigit(g, element, y);
        }
        if ( element.getValueType() == ElementType.TEXT ) {
            printText(g, element, y);
        }
    }

    private void printFlag(Graphics g, ElementConfig element, int y) {
        if ( element.getValue().getFlag().isActual() ) {
            g.fillRect(menuCfg.getStartX() + menuCfg.getOffsetX(), menuCfg.getStartY() + (y - 1) * g.getFont().getSize(),
                    menuCfg.getFontSize(), menuCfg.getFontSize());
        } else {
            g.drawRect(menuCfg.getStartX() + menuCfg.getOffsetX(), menuCfg.getStartY() + (y - 1) * g.getFont().getSize(),
                    menuCfg.getFontSize(), menuCfg.getFontSize());
        }
    }

    private void printDigit(Graphics g, ElementConfig element, int y) {
        ValueDigitConfig valueDigit = element.getValue().getDigit();
        if ( element.getNavigationMode() != null &&
                element.getNavigationMode() == NavigationMode.EDIT) {
            g.setColor(Color.YELLOW);
        }
        g.drawString(valueDigit.printInfo(), menuCfg.getStartX() + menuCfg.getOffsetX(),
                menuCfg.getStartY() + y * g.getFont().getSize());
    }

    private void printText(Graphics g, ElementConfig element, int y) {
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
