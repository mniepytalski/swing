package pl.cbr.ucho.menu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cbr.ucho.menu.config.ElementType;

@Data
@NoArgsConstructor
public class ElementModel {
    private int                 markedPosition  = 0;
    private boolean             marked          = false;
    private NavigationMode      navigationMode  = NavigationMode.NAVIGATION;
    private ElementType         elementType     = ElementType.NO_VALUE;

}
