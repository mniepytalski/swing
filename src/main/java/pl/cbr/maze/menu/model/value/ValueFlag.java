package pl.cbr.maze.menu.model.value;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValueFlag extends MenuValue<Boolean> {

    @Override
    public Boolean of(String value) {
        return Boolean.parseBoolean(value);
    }

    @Override
    public String to() {
        return getValue().toString();
    }
}
