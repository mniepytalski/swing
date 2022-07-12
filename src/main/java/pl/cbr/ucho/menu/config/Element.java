package pl.cbr.ucho.menu.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.cbr.ucho.menu.MenuNavigation;

import java.util.LinkedList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Element extends MenuNavigation {

    private Description description;
    private List<Element> elements = new LinkedList<>();

    private ValueConfig value;

    @Override
    public List<Element> getElements() {
        return elements;
    }

    public Element getMarkedElement() {
        return elements.get(getPosition());
    }

    public ValueType getValueType() {
        if ( value == null ) {
            return ValueType.NO_VALUE;
        } else {
            return value.getValueType();
        }
    }
}
