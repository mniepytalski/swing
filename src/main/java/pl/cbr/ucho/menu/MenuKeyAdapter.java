package pl.cbr.ucho.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.cbr.ucho.menu.config.ElementConfig;
import pl.cbr.ucho.menu.model.MenuModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Slf4j
@Service
@AllArgsConstructor
public class MenuKeyAdapter extends KeyAdapter {

    private final MenuModel menuModel;
    private ApplicationEventPublisher applicationEventPublisher;

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                menuModel.getActualElementConfig().decMarkedPosition();
                break;
            case KeyEvent.VK_DOWN:
                menuModel.getActualElementConfig().intMarkedPosition();
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

/*

        MenuNavigation navigation = menuModel.getMenuConfig();
        if (menuModel.getMenuConfig().getDepth()>0) {
            for ( int i=0; i<menuModel.getMenuConfig().getDepth(); i++) {
             //   navigation = navigation.getMarkedElement();
            }
        }
        MenuNavigation el = menuModel.getMenuConfig().getActualElement();
        var element = menuModel.getMenuConfig().getActualElementTest();
        if ( element.isPresent() ) {
            ElementConfig ec = element.get();
            ec.getElementActualState();
        }
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                navigation.decPosition();
                break;
            case KeyEvent.VK_DOWN:
                navigation.incPosition();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_ENTER:
                if ( !menuModel.getMenuConfig().incDepth() ) {
                    if ( el.getMarkedElement().getValueType().equals(ElementType.NO_VALUE)) {
                 //       sendMessage(el.getName(), el.getMarkedElement().getName());
                    } else {
                        changeState(el.getMarkedElement(), e.getKeyCode());
                    }
                }
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_DELETE:
            case KeyEvent.VK_BACK_SPACE:
                menuModel.getMenuConfig().decDepth();
                break;
        }
*/
    }

    private void sendMessage(String origin, String message) {
        applicationEventPublisher.publishEvent(new MenuMessage(origin, message));
    }

    private void changeState(ElementConfig element, int keyEvent) {
//        switch (element.getValueType() ) {
//            case FLAG:
//                element.getValue().getFlag().invertValue();
//                break;
//            case DIGIT:
//                break;
//            case TEXT:
//                element.getValue().getText().markNextValue();
//                break;
//            case NO_VALUE:
//                break;
//        }
    }
}
