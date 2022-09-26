package pl.cbr.ucho.menu.model;

import lombok.Data;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.cbr.ucho.menu.MenuMessage;
import pl.cbr.ucho.menu.config.ElementConfig;
import pl.cbr.ucho.menu.config.ElementType;
import pl.cbr.ucho.menu.config.MenuConfig;

import java.util.Optional;

@Service
@Data
public class MenuModel {
    private MenuConfig menuConfig;
    private ElementConfig element;
    private ApplicationEventPublisher applicationEventPublisher;

    private int depth = 0;

    public MenuModel(MenuConfig menuConfig, ApplicationEventPublisher applicationEventPublisher) {
        this.menuConfig = menuConfig;
        this.element = menuConfig;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public boolean incDepth() {
        if (element.getMarkedElement().isEmpty()) {
            return false;
        }
        if (element.getMarkedElement().get().getElements().size() > 0) {
            depth++;
            return true;
        } else {
            incDepthAction();
            return false;
        }
    }

    public void decDepth() {
        if (depth > 0) {
            depth--;
        }
    }

    public ElementConfig getActualElementConfig() {
        ElementConfig actualElement = element;
        for (int i = 0; i < depth; i++) {
            if (element.getMarkedElement().isEmpty()) {
                break;
            }
            actualElement = element.getMarkedElement().get();
        }
        return actualElement;
    }

    private void incDepthAction() {
        Optional<ElementConfig> navigation = element.getMarkedElement();
        if (navigation.isPresent()) {
            ElementConfig el = navigation.get();
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

    private void changeState(ElementConfig element) {
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
