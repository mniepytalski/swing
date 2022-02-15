package pl.cbr.ucho.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Slf4j
@AllArgsConstructor
public class MenuKeyAdapter extends KeyAdapter {

    private final MenuLogic menuLogic;

    public void keyPressed(KeyEvent e) {
//        log.info("keyPressed: {}",e.toString());
        MenuNavigation navigation = menuLogic.getMenuConfig();
        if (menuLogic.getMenuConfig().getDepth()>0) {
            for ( int i=0; i<menuLogic.getMenuConfig().getDepth(); i++) {
                navigation = navigation.getMarkedElement();
            }
        }
        MenuNavigation el = menuLogic.getMenuConfig().getActualElement();
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                navigation.decPosition();
                break;
            case KeyEvent.VK_DOWN:
                navigation.incPosition();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_ENTER:
                menuLogic.getMenuConfig().incDepth();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_DELETE:
            case KeyEvent.VK_BACK_SPACE:
                menuLogic.getMenuConfig().decDepth();
                break;
        }
    }
}
