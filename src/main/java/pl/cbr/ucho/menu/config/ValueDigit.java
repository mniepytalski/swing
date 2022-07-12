package pl.cbr.ucho.menu.config;

import lombok.Data;

@Data
public class ValueDigit {
    int actual;
    int min;
    int max;

    public String printInfo() {
        return "min:"+min+",actual:"+actual+",max:"+max;
    }
}
