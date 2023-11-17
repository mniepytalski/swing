package pl.cbr.maze.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.cbr.maze.menu.config.ElementType;
import pl.cbr.maze.menu.config.MenuElement;
import pl.cbr.maze.menu.model.MenuModel;
import pl.cbr.maze.menu.model.NavigationMode;

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
            case VK_UP:
                if (child.getNavigationMode()==NavigationMode.EDIT) {
                    if ( child.getElementType()==ElementType.DIGIT ) {
                        child.getValue().getDigit().intValue();
                    }
                } else {
                    element.decMarkedPosition();
                }
                break;
            case VK_DOWN:
                if (child.getNavigationMode()== NavigationMode.EDIT) {
                    if ( child.getElementType()==ElementType.DIGIT ) {
                        child.getValue().getDigit().decValue();
                    }
                } else {
                    element.intMarkedPosition();
                }
                break;
            case VK_RIGHT, VK_ENTER:
                menuModel.incDepth();
                break;
            case VK_LEFT, VK_ESCAPE, VK_DELETE, VK_BACK_SPACE:
                if ( child.getNavigationMode()==NavigationMode.EDIT ) {
                    child.changeNavigationMode();
                }
                menuModel.decDepth();
                break;
        }
    }
}
