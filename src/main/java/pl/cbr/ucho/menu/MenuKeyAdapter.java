package pl.cbr.ucho.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.cbr.ucho.menu.config.ElementConfig;
import pl.cbr.ucho.menu.config.ElementType;
import pl.cbr.ucho.menu.model.MenuModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MenuKeyAdapter extends KeyAdapter {

    private final MenuModel menuModel;
    private ApplicationEventPublisher applicationEventPublisher;

    public void keyPressed(KeyEvent e) {
        ElementConfig element = menuModel.getActualElementConfig();
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                element.decMarkedPosition();
                break;
            case KeyEvent.VK_DOWN:
                element.intMarkedPosition();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_ENTER:
                if ( !menuModel.incDepth() ) {
                    Optional<ElementConfig> navigation = element.getMarkedElement();
                    if ( navigation.isPresent() ) {
                        ElementConfig el = navigation.get();
                        if (el.getValueType().equals(ElementType.NO_VALUE)) {
                            sendMessage(el.getName(), el.getName());
                        } else {
                            changeState(el, e.getKeyCode());
                        }
                    }
                }
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_DELETE:
            case KeyEvent.VK_BACK_SPACE:
                menuModel.decDepth();
                break;
        }
    }

    private void sendMessage(String origin, String message) {
        applicationEventPublisher.publishEvent(new MenuMessage(origin, message));
    }

    private void changeState(ElementConfig element, int keyEvent) {
        switch (element.getValueType() ) {
            case FLAG:
                element.getValue().getFlag().invertValue();
                break;
            case DIGIT:
            case NO_VALUE:
                break;
            case TEXT:
                element.getValue().getText().markNextValue();
                break;
        }
    }
}
