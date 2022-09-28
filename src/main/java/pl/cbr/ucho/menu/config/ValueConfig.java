package pl.cbr.ucho.menu.config;

import lombok.Data;

@Data
public class ValueConfig {
    private ValueFlagConfig flag;
    private ValueDigitConfig digit;
    private ValueTextConfig text;

    public ElementType getValueType() {
        if ( flag != null)
            return ElementType.FLAG;
        if ( digit != null)
            return ElementType.DIGIT;
        if ( text != null)
            return ElementType.TEXT;
        return ElementType.NO_VALUE;
    }


}
