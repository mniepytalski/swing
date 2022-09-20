package pl.cbr.ucho.menu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cbr.ucho.menu.model.value.MenuValue;

import java.util.Optional;

@Data
@NoArgsConstructor
public class ElementState {
    private int                 position        = 0;
    private boolean             marked          = false;
    private NavigationMode      navigationMode  = NavigationMode.NAVIGATION;
    private Optional<MenuValue> value           = Optional.empty();
}
