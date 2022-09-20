package pl.cbr.ucho.menu.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pl.cbr.ucho.menu.MenuNavigation;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * TODO
 * rozdzielic wczytywane strukture i logike
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Configuration
@ConfigurationProperties(prefix = "menu")
public class MenuConfig extends MenuNavigation {
    int depth = 0;
    private List<ElementConfig> elements = new LinkedList<>();

    public boolean incDepth() {
        if ( getActualElement().getMarkedElement().getElements().size()>0 ) {
            depth++;
            return true;
        } else {
            return false;
        }
    }

    public void decDepth() {
        if ( depth>0 ) {
            depth--;
        }
    }

    public MenuNavigation getActualElement() {
        MenuNavigation element = this;
        for (int i = depth; i > 0; i--) {
            element = element.getMarkedElement();
        }
        element.init();
        return element;
    }

    public Optional<ElementConfig> getActualElementTest() {
        ElementConfig element = null;
        for (int i = depth; i > 0; i--) {
            if ( element == null ) {
                return Optional.empty();
            }
            element = element.getMarkedElement();
        }
        if ( element == null ) {
            return Optional.empty();
        }
        element.init();
        return Optional.of(element);
    }
}
