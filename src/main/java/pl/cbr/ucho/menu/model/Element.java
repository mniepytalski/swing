package pl.cbr.ucho.menu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cbr.ucho.menu.config.ElementType;
import java.util.Optional;

@Data
@NoArgsConstructor
public class Element {
    private int                 markedPosition  = 0;
    private boolean             marked          = false;
    private NavigationMode      navigationMode  = NavigationMode.NAVIGATION;
//    private Optional<MenuValue> value           = Optional.empty();
    private ElementType         elementType     = ElementType.NO_VALUE;

}
