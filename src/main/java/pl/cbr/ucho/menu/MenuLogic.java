package pl.cbr.ucho.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.cbr.ucho.menu.config.Element;
import pl.cbr.ucho.menu.config.MenuConfig;

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
        var navigation = (MenuNavigation)menuConfig.getActualElement();
        var elements = navigation.getElements();
        g.drawString("depth:"+menuConfig.getDepth()+
                ",position:"+navigation.getPosition()+
                ",elements:"+elements.size(), 0, 500);
        elements.forEach(e -> print(g, e, i.getAndIncrement()));
    }

    void print(Graphics g, Element element, int y) {
        g.setColor(element.isMarked() ? Color.BLUE : Color.BLACK);
        g.drawString(element.getText(), menuCfg.getStartX(), menuCfg.getStartY() + y * g.getFont().getSize());
    }
}
