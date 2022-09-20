package pl.cbr.ucho.menu.model.value;

public abstract class MenuValue<T> {

    private T actual;

    public T getValue() {
        return actual;
    }
}
