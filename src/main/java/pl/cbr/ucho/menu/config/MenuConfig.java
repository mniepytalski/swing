package pl.cbr.ucho.menu.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pl.cbr.ucho.menu.MenuNavigation;

import java.util.LinkedList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Configuration
@ConfigurationProperties(prefix = "menu")
public class MenuConfig extends MenuNavigation {
    int depth = 0;
    private List<Element> elements = new LinkedList<>();

    public void incDepth() {
        if ( getActualElement().getMarkedElement().getElements().size()>0 ) {
            depth++;
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
}
