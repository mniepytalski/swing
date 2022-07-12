package pl.cbr.ucho.menu.config;

import lombok.Data;

@Data
public class ValueConfig {
    ValueFlag   flag;
    ValueDigit  digit;
    ValueText   text;

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
