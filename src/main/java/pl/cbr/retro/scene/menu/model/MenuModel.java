package pl.cbr.retro.scene.menu.model;

import lombok.Data;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.cbr.retro.scene.menu.MenuMessage;
import pl.cbr.retro.scene.menu.config.MenuElement;
import pl.cbr.retro.scene.menu.config.ElementType;
import pl.cbr.retro.scene.menu.config.MenuConfig;

import java.util.*;

@Service
@Data
public class MenuModel {
    private MenuConfig menuConfig;
    private MenuElement element;
    private ApplicationEventPublisher applicationEventPublisher;

    private Set<String> test = new HashSet<>();
    private int depth = 0;

    public MenuModel(MenuConfig menuConfig, ApplicationEventPublisher applicationEventPublisher) {
        this.menuConfig = menuConfig;
        this.element = menuConfig;
        this.applicationEventPublisher = applicationEventPublisher;
        initMenu(element,"menu");
    }

    public void readParametersToProperties(Properties properties) {
        setParameter(menuConfig, properties);
    }

    public Properties writeParametersToProperties() {
        Properties properties = new Properties();
        prepareParameter(menuConfig, properties);
        return properties;
    }

    private void setParameter(MenuElement element, Properties properties) {
        if (element.getElementType()!=ElementType.NO_VALUE) {
            String value = (String)properties.get(element.getId());
            element.getValue().setActual(value);
        }
        element.getElements().forEach(e -> setParameter(e, properties));
    }

    private void prepareParameter(MenuElement element, Properties properties) {
        if (element.getElementType()!=ElementType.NO_VALUE) {
            properties.put(element.getId(), element.getValue().geActual());
        }
        element.getElements().forEach(e -> prepareParameter(e, properties));
    }

    private void initMenu(MenuElement element, String parentName) {
        element.init(parentName);
        test.add(element.getId());
        element.getElements().forEach(e -> initMenu(e,element.getId()));
    }

    public boolean incDepth() {
        Optional<MenuElement> e = getActualParentElement().getMarkedElement();
        if (e.isEmpty()) {
            return false;
        }
        if (!e.get().getElements().isEmpty()) {
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
            case FLAG -> element.getValue().getFlag().invertValue();
            case TEXT -> element.getValue().getText().markNextValue();
        }
    }
}
