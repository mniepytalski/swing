package pl.cbr.ucho.menu.config;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class ValueText {
    String actual;
    List<ValueTextElement> list = new ArrayList<>();

    public String printInfo() {
        Map<String, String> map = list.stream().collect(Collectors.toMap(ValueTextElement::getName, ValueTextElement::getText));
        return "actual:"+map.get(actual)+", all:"+list.stream().map(ValueTextElement::getText).collect(Collectors.joining(","));
    }

    public List<String> getAllTexts() {
        return list.stream().map(ValueTextElement::getText).collect(Collectors.toList());
    }

    public String getActualText() {
        Map<String, String> map = list.stream().collect(Collectors.toMap(ValueTextElement::getName, ValueTextElement::getText));
        return map.get(actual);
    }

    public void markNextValue() {
        int position = getPosition() + 1;
        if ( position >= list.size() ) {
            position = 0;
        }
        setPosition(position);
    }

    private void setPosition(int position) {
        actual = list.get(position).getName();
    }

    private int getPosition() {
        int i=0;
        for ( ValueTextElement valueTextElement : list) {
            if ( valueTextElement.getName().equals(actual)) {
                return i;
            }
            i++;
        }
        return i;
    }
}
