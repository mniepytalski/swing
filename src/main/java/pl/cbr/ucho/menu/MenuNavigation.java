package pl.cbr.ucho.menu;

import lombok.Data;
import pl.cbr.ucho.menu.config.Element;

import java.util.List;

@Data
public abstract class MenuNavigation {
    int position = 0;
    int depth = 0;

    public void decPosition() {
        if ( position>0 ) {
            position--;
        }
    }

    public void decDepth() {
        if ( depth>0 ) {
            depth--;
        }
    }

    public void incPosition() {
        if (position<getElements().size()) {
            position++;
        }
    }

    public void incDepth() {
        if ( getElement().getElements().size()>0 ) {
            depth++;
        }
    }

    public Element getElement() {
        return getElements().get(getPosition());
    }

    public abstract List<Element> getElements();
}
