package pl.cbr.ucho.menu.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.cbr.ucho.menu.model.ElementModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class MenuElement extends ElementModel {

    private DescriptionConfig descriptionConfig;
    private List<MenuElement> elements = new LinkedList<>();
    private ValueConfig value;
    private String name;
    private String text;

    public void init() {
        setElementType(getValueType());
    }

    public ElementType getValueType() {
        if ( value == null ) {
            return ElementType.NO_VALUE;
        } else {
            return value.getValueType();
        }
    }

    public Optional<MenuElement> getMarkedElement() {
        if ( getMarkedPosition()>=elements.size()) {
            return Optional.empty();
        } else {
            return Optional.of(elements.get(getMarkedPosition()));
        }
    }

    public void actualizePosition() {
        getElements().forEach(e -> {
            e.init();
            e.setMarked(false);
        });
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
