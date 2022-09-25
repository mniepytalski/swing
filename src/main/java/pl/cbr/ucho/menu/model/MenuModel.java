package pl.cbr.ucho.menu.model;

import lombok.Data;
import org.springframework.stereotype.Service;
import pl.cbr.ucho.menu.config.ElementConfig;
import pl.cbr.ucho.menu.config.MenuConfig;

@Service
@Data
public class MenuModel {
    private MenuConfig menuConfig;
    private ElementConfig element;

    private int depth = 0;

    public MenuModel(MenuConfig menuConfig) {
        this.menuConfig = menuConfig;
        this.element = menuConfig;
    }

    public boolean incDepth() {
        if ( element.getMarkedElement().isEmpty()) {
            return false;
        }
        if ( element.getMarkedElement().get().getElements().size()>0 ) {
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

    public ElementConfig getActualElementConfig() {
        ElementConfig actualElement = element;
        for ( int i=0; i<depth; i++) {
            if ( element.getMarkedElement().isEmpty() ) {
                break;
            }
            actualElement = element.getMarkedElement().get();
        }
        return actualElement;
    }

}
