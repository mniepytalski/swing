package pl.cbr.maze.menu.model.value;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ValueText extends MenuValue<String> {
    private List<String> values;

    @Override
    public String of(String value) {
        return null;
    }

    @Override
    public String to() {
        return getValue();
    }
}
