package pl.cbr.ucho.menu.value;

public abstract class MenuValue<T> {

    private T actual;

    public T getValue() {
        return actual;
    }
}
