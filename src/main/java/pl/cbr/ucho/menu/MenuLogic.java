package pl.cbr.ucho.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.cbr.ucho.menu.config.Element;
import pl.cbr.ucho.menu.config.MenuConfig;

import java.awt.*;
import java.awt.event.KeyAdapter;

@Service
@Slf4j
@Getter
@AllArgsConstructor
public class MenuLogic extends KeyAdapter {
    private final MenuConfig menuConfig;
    private final MenuCfg menuCfg;

    public void doDrawing(Graphics g) {
        g.setFont(new Font(menuCfg.getFontName(), Font.BOLD, menuCfg.getFontSize()));
        int i = 0;

        var navigation = menuConfig;
        var position = menuConfig.getPosition();
        var elements = menuConfig.getElements();
        if (menuConfig.getDepth()>0 ) {
            var element = navigation.getElement();
            elements = element.getElements();
            position = element.getPosition();
        }

//        elements.forEach(e -> print(g, e, position, i++));
        for (Element elementIt : elements ) {
            print(g, elementIt, position, i++);
        }
    }

    void print(Graphics g, Element element, int position, int y) {
        if ( y==position )
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.BLACK);
        g.drawString(element.getText(), menuCfg.getStartX(), menuCfg.getStartY() + y * g.getFont().getSize());
    }
}
