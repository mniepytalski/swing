package pl.cbr.retro.scene.menu;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MenuMessage extends ApplicationEvent {

    String message;

    public MenuMessage(Object source, String message) {
        super(source);
        this.message = message;
    }
}
