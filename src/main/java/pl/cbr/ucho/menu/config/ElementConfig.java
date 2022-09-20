package pl.cbr.ucho.menu.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.cbr.ucho.menu.MenuNavigation;
import pl.cbr.ucho.menu.model.ElementState;

import java.util.LinkedList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ElementConfig extends MenuNavigation {

    private DescriptionConfig descriptionConfig;
    private List<ElementConfig> elements = new LinkedList<>();

    private ValueConfig value;

    private ElementState elementState = new ElementState();

    @Override
    public List<ElementConfig> getElements() {
        return elements;
    }

    public ElementConfig getMarkedElement() {
        return elements.get(getPosition());
    }

    public ValueType getValueType() {
        if ( value == null ) {
            return ValueType.NO_VALUE;
        } else {
            return value.getValueType();
        }
    }

    public ElementState getElementActualState() {
        return elementState;
    }
}
