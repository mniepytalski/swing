package pl.cbr.ucho.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.cbr.ucho.menu.config.*;
import pl.cbr.ucho.menu.config.MenuCfg;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@Getter
@AllArgsConstructor
public class MenuLogic extends KeyAdapter {
    private final MenuConfig menuConfig;
    private final MenuCfg menuCfg;

    public void doDrawing(Graphics g) {
        g.setFont(new Font(menuCfg.getFontName(), Font.BOLD, menuCfg.getFontSize()));
        AtomicInteger i = new AtomicInteger();
        menuConfig.getActualElement().getElements().forEach(e -> print(g, e, i.getAndIncrement()));
    }

    void print(Graphics g, Element element, int y) {
        g.setColor(element.isMarked() ? Color.BLUE : Color.BLACK);
        g.drawString(element.getText(), menuCfg.getStartX(), menuCfg.getStartY() + y * g.getFont().getSize());
        if ( element.getValueType() == ValueType.FLAG ) {
            printFlag(g, element, y);
        }
        if ( element.getValueType() == ValueType.DIGIT ) {
            printDigit(g, element, y);
        }
        if ( element.getValueType() == ValueType.TEXT ) {
            printText(g, element, y);
        }
    }

    void printFlag(Graphics g, Element element, int y) {
        if ( element.getValue().getFlag().isActual() ) {
            g.fillRect(menuCfg.getStartX() + menuCfg.getOffsetX(), menuCfg.getStartY() + (y - 1) * g.getFont().getSize(),
                    menuCfg.getFontSize(), menuCfg.getFontSize());
        } else {
            g.drawRect(menuCfg.getStartX() + menuCfg.getOffsetX(), menuCfg.getStartY() + (y - 1) * g.getFont().getSize(),
                    menuCfg.getFontSize(), menuCfg.getFontSize());
        }
    }

    void printDigit(Graphics g, Element element, int y) {
        ValueDigit valueDigit = element.getValue().getDigit();
        g.drawString(valueDigit.printInfo(), menuCfg.getStartX()+menuCfg.getOffsetX(),
                menuCfg.getStartY() + y * g.getFont().getSize());
    }

    void printText(Graphics g, Element element, int y) {
        ValueText valueText = element.getValue().getText();
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
