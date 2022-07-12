package pl.cbr.ucho.menu;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
public class MenuMessage extends ApplicationEvent {

    String message;

    public MenuMessage(Object source, String message) {
        super(source);
        this.message = message;
    }

    public MenuMessage(Object source, Clock clock) {
        super(source, clock);
    }
}
