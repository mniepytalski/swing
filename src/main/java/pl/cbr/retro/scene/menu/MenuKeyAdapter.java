package pl.cbr.retro.scene.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.cbr.retro.scene.menu.config.ElementType;
import pl.cbr.retro.scene.menu.config.MenuElement;
import pl.cbr.retro.scene.menu.model.MenuModel;
import pl.cbr.retro.scene.menu.model.NavigationMode;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

@Slf4j
@Service
@AllArgsConstructor
public class MenuKeyAdapter extends KeyAdapter {
    private final MenuModel menuModel;

    public void keyPressed(KeyEvent e) {
        MenuElement element = menuModel.getActualParentElement();
        MenuElement child  = menuModel.getActualMarkedElement();
        switch(e.getKeyCode()) {
            case VK_UP -> goUp(child, element);
            case VK_DOWN -> goDown(child, element);
            case VK_RIGHT, VK_ENTER -> goRight();
            case VK_LEFT, VK_ESCAPE, VK_DELETE, VK_BACK_SPACE -> goLeft(child);
        }
    }

    private void goLeft(MenuElement child) {
        if ( child.getNavigationMode() == NavigationMode.EDIT ) {
            child.changeNavigationMode();
        }
        menuModel.decDepth();
    }

    private void goRight() {
        menuModel.incDepth();
    }

    private void goDown(MenuElement child, MenuElement element) {
        if (child.getNavigationMode() == NavigationMode.EDIT) {
            if ( child.getElementType() == ElementType.DIGIT ) {
                child.getValue().getDigit().decValue();
            }
        } else {
            element.intMarkedPosition();
        }
    }

    private void goUp(MenuElement child, MenuElement element) {
        if (child.getNavigationMode() == NavigationMode.EDIT) {
            if ( child.getElementType() == ElementType.DIGIT ) {
                child.getValue().getDigit().intValue();
            }
        } else {
            element.decMarkedPosition();
        }
    }
}
