package pl.cbr.ucho.menu.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.cbr.ucho.menu.model.Element;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ElementConfig extends Element {

    private DescriptionConfig descriptionConfig;
    private List<ElementConfig> elements = new LinkedList<>();
    private ValueConfig value;
    private String name;
    private String text;

    private Element elementState = new Element();

    public ElementType getValueType() {
        if ( value == null ) {
            return ElementType.NO_VALUE;
        } else {
            return value.getValueType();
        }
    }

    public Element getElementActualState() {
        return elementState;
    }

    public Optional<ElementConfig> getMarkedElement() {
        if ( getMarkedPosition()>=elements.size()) {
            return Optional.empty();
        } else {
            return Optional.of(elements.get(getMarkedPosition()));
        }
    }

    public void actualizePosition() {
        getElements().forEach(e -> e.setMarked(false));
        getMarkedElement().ifPresent(e -> e.setMarked(true));
    }

    public void intMarkedPosition() {
        if ( getMarkedPosition()<elements.size()-1 ) {
            setMarkedPosition(getMarkedPosition()+1);
            actualizePosition();
        }
    }

    public void decMarkedPosition() {
        if ( getMarkedPosition()>0 ) {
            setMarkedPosition(getMarkedPosition()-1);
            actualizePosition();
        }
    }
}
