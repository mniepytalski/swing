package pl.cbr.maze.menu.model.value;

public abstract class MenuValue<T> {

    private T actual;

    public T getValue() {
        return actual;
    }

    public void setValue(T value) {
        actual = value;
    }

    public abstract T of(String value);

    public abstract String to();
}
