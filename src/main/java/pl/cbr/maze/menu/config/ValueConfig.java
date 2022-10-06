package pl.cbr.maze.menu.config;

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

    public String geActual() {
        if ( flag != null)
            return ""+flag.isActual();
        if ( digit != null)
            return ""+digit.getActual();
        if ( text != null)
            return text.getActual();
        return "";
    }

    public void setActual(String storedState) {
        if ( storedState==null ) {
            return;
        }
        if ( flag != null)
            flag.setActual(Boolean.parseBoolean(storedState));
        if ( digit != null)
            digit.setActual(Integer.parseInt(storedState));
        if ( text != null)
            text.setActual(storedState);

    }


}
