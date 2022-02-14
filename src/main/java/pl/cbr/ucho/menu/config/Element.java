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

    private String name;
    private String text;
//    private State state;
    private Description description;
    private List<Element> elements = new LinkedList<>();

    @Override
    public List<Element> getElements() {
        return elements;
    }
//    private Optional<Value> value;

    public Element getElement() {
        return elements.get(getPosition());
    }
}
