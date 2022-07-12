package pl.cbr.ucho.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import pl.cbr.ucho.menu.config.Element;
import pl.cbr.ucho.menu.config.MenuConfig;
import pl.cbr.ucho.menu.config.ValueType;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Slf4j
@AllArgsConstructor
public class MenuKeyAdapter extends KeyAdapter {

    private final MenuLogic menuLogic;
    private ApplicationEventPublisher applicationEventPublisher;

    public void keyPressed(KeyEvent e) {
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
                if ( !menuLogic.getMenuConfig().incDepth() ) {
                    if ( el.getMarkedElement().getValueType().equals(ValueType.NO_VALUE)) {
                        sendMessage(el.getName(), el.getMarkedElement().getName());
                    } else {
                        changeState(el.getMarkedElement(), e.getKeyCode());
                    }
                }
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_DELETE:
            case KeyEvent.VK_BACK_SPACE:
                menuLogic.getMenuConfig().decDepth();
                break;
        }
    }

    private void sendMessage(String origin, String message) {
        applicationEventPublisher.publishEvent(new MenuMessage(origin, message));
    }

    private void changeState(Element element, int keyEvent) {
        switch (element.getValueType() ) {
            case FLAG:
                element.getValue().getFlag().invertValue();
                break;
            case DIGIT:
                break;
            case TEXT:
                element.getValue().getText().markNextValue();
                break;
            case NO_VALUE:
                break;
        }
    }
}
