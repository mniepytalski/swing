package pl.cbr.ucho.menu;

import lombok.Data;
import pl.cbr.ucho.menu.config.ElementConfig;

import javax.annotation.PostConstruct;
import java.util.List;

@Data
public abstract class MenuNavigation {
    private int position = 0;
    private boolean marked = false;

    private String name;
    private String text;

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

    public ElementConfig getMarkedElement() {
        return getElements().get(getPosition());
    }

    public abstract List<ElementConfig> getElements();

    private void actualizePosition() {
        getElements().forEach(e -> e.setMarked(false));
        getMarkedElement().setMarked(true);
    }
}
