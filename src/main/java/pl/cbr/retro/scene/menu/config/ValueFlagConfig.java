package pl.cbr.retro.scene.menu.config;

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
