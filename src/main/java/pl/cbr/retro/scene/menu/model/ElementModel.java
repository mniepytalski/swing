package pl.cbr.retro.scene.menu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cbr.retro.scene.menu.config.ElementType;

@Data
@NoArgsConstructor
public class ElementModel {

    private String              id;
    private int                 markedPosition  = 0;
    private boolean             marked          = false;
    private NavigationMode      navigationMode  = NavigationMode.NAVIGATION;
    private ElementType         elementType     = ElementType.NO_VALUE;

    public void changeNavigationMode() {
        if ( getNavigationMode()==NavigationMode.NAVIGATION ) {
            navigationMode = NavigationMode.EDIT;
        } else {
            navigationMode = NavigationMode.NAVIGATION;
        }
    }
}
