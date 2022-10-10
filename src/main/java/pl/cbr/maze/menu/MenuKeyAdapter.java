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

@Slf4j
@Service
@AllArgsConstructor
public class MenuKeyAdapter extends KeyAdapter {
    private final MenuModel menuModel;

    public void keyPressed(KeyEvent e) {
        MenuElement element = menuModel.getActualParentElement();
        MenuElement child  = menuModel.getActualMarkedElement();
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (child.getNavigationMode()==NavigationMode.EDIT) {
                    if ( child.getElementType()==ElementType.DIGIT ) {
                        child.getValue().getDigit().intValue();
                    }
                } else {
                    element.decMarkedPosition();
                }
                break;
            case KeyEvent.VK_DOWN:
                if (child.getNavigationMode()== NavigationMode.EDIT) {
                    if ( child.getElementType()==ElementType.DIGIT ) {
                        child.getValue().getDigit().decValue();
                    }
                } else {
                    element.intMarkedPosition();
                }
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_ENTER:
                menuModel.incDepth();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_DELETE:
            case KeyEvent.VK_BACK_SPACE:
                if ( child.getNavigationMode()==NavigationMode.EDIT ) {
                    child.changeNavigationMode();
                }
                menuModel.decDepth();
                break;
        }
    }
}
