package pl.cbr.ucho.menu;

import lombok.Data;
import pl.cbr.ucho.menu.config.Element;

import javax.annotation.PostConstruct;
import java.util.List;

@Data
public abstract class MenuNavigation {
    int position = 0;
    boolean marked = false;

    @PostConstruct
    public synchronized void init() {
        actualizePosition();
    }

    public synchronized void decPosition() {
        if ( position > 0 ) {
            position--;
            actualizePosition();
        }
    }

    public synchronized void incPosition() {
        if ( position+1 < getElements().size() ) {
            position++;
            actualizePosition();
        }
    }

    public Element getMarkedElement() {
        return getElements().get(getPosition());
    }

    public abstract List<Element> getElements();

    private void actualizePosition() {
        getElements().stream().forEach(e -> e.setMarked(false));
        getMarkedElement().setMarked(true);
    }

}
