package pl.cbr.ucho.menu.config;

import lombok.Data;

@Data
public class ValueConfig {
    ValueFlagConfig flag;
    ValueDigitConfig digit;
    ValueTextConfig text;

    public ValueType getValueType() {
        if ( flag != null)
            return ValueType.FLAG;
        if ( digit != null)
            return ValueType.DIGIT;
        if ( text != null)
            return ValueType.TEXT;
        return ValueType.NO_VALUE;
    }
}
