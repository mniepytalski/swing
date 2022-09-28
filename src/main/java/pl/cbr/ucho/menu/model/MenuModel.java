package pl.cbr.ucho.menu.model;

import lombok.Data;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.cbr.ucho.menu.MenuMessage;
import pl.cbr.ucho.menu.config.MenuElement;
import pl.cbr.ucho.menu.config.ElementType;
import pl.cbr.ucho.menu.config.MenuConfig;

import java.util.Optional;

@Service
@Data
public class MenuModel {
    private MenuConfig menuConfig;
    private MenuElement element;
    private ApplicationEventPublisher applicationEventPublisher;

    private int depth = 0;

    public MenuModel(MenuConfig menuConfig, ApplicationEventPublisher applicationEventPublisher) {
        this.menuConfig = menuConfig;
        this.element = menuConfig;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public boolean incDepth() {
        Optional<MenuElement> e = getActualParentElement().getMarkedElement();
        if (e.isEmpty()) {
            return false;
        }
        if (e.get().getElements().size() > 0) {
            depth++;
            return true;
        } else {
            if ( e.get().getElementType()==ElementType.DIGIT ) {
                e.get().changeNavigationMode();
            }
            incDepthAction();
            return false;
        }
    }

    public void decDepth() {
        if (depth > 0) {
            depth--;
        }
    }

    public MenuElement getActualParentElement() {
        MenuElement actualElement = element;
        for (int i = 0; i < depth; i++) {
            if (actualElement.getMarkedElement().isEmpty()) {
                break;
            }
            actualElement = actualElement.getMarkedElement().get();
        }
        return actualElement;
    }

    public MenuElement getActualMarkedElement() {
        MenuElement parent = getActualParentElement();
        return parent.getMarkedElement().orElse(parent);
    }

    private void incDepthAction() {
        Optional<MenuElement> navigation = getActualParentElement().getMarkedElement();
        if (navigation.isPresent()) {
            MenuElement el = navigation.get();
            if (el.getValueType().equals(ElementType.NO_VALUE)) {
                sendMessage(el.getName(), el.getName());
            } else {
                changeState(el);
            }
        }
    }

    private void sendMessage(String origin, String message) {
        applicationEventPublisher.publishEvent(new MenuMessage(origin, message));
    }

    private void changeState(MenuElement element) {
        switch (element.getValueType()) {
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
