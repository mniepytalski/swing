package pl.cbr.ucho.menu.config;

import lombok.Data;

@Data
public class ValueFlagConfig {
    boolean actual;

    public String printInfo() {
        return "actual:"+actual;
    }

    public void invertValue() {
        actual = !actual;
    }
}
