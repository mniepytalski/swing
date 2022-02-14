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
        if (navigation.getDepth()>0) {
            for ( int i=0; i<navigation.getDepth(); i++) {
                navigation = navigation.getElement();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            navigation.decPosition();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            navigation.incPosition();
        }
        if ( e.getKeyCode() == KeyEvent.VK_RIGHT ||
             e.getKeyCode() == KeyEvent.VK_ENTER ) {
            menuLogic.getMenuConfig().incDepth();
        }
        if ( e.getKeyCode() == KeyEvent.VK_LEFT ||
             e.getKeyCode() == KeyEvent.VK_ESCAPE ||
             e.getKeyCode() == KeyEvent.VK_DELETE ||
             e.getKeyCode() == KeyEvent.VK_BACK_SPACE ) {
            menuLogic.getMenuConfig().decDepth();
        }
    }
}
