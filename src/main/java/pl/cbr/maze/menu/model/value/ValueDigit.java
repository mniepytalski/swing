package pl.cbr.maze.menu.model.value;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValueDigit extends MenuValue<Integer> {
    private Integer minimum;
    private Integer maximum;

    @Override
    public Integer of(String value) {
        return Integer.getInteger(value);
    }

    @Override
    public String to() {
        return getValue().toString();
    }

    public void incrementValue() {
        if ( getValue()<maximum ) {
            setValue(getValue()+1);
        }
    }

    public void decrementValue() {
        if ( getValue()>minimum ) {
            setValue(getValue()-1);
        }
    }
}
