package pl.cbr.ucho.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.cbr.ucho.menu.config.MenuElement;
import pl.cbr.ucho.menu.model.MenuModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Slf4j
@Service
@AllArgsConstructor
public class MenuKeyAdapter extends KeyAdapter {
    private final MenuModel menuModel;

    public void keyPressed(KeyEvent e) {
        MenuElement element = menuModel.getActualParentElement();
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                element.decMarkedPosition();
                break;
            case KeyEvent.VK_DOWN:
                element.intMarkedPosition();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_ENTER:
                menuModel.incDepth();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_DELETE:
            case KeyEvent.VK_BACK_SPACE:
                menuModel.decDepth();
                break;
        }
    }
}
