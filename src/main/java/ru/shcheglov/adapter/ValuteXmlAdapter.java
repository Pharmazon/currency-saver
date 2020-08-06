package ru.shcheglov.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Кастомный адаптер для преобразования значения с запятой в значение с точкой
 */
public class ValuteXmlAdapter extends XmlAdapter<String, Double> {

    @Override
    public Double unmarshal(String v) {
        String replacedString = v.replaceAll(",", ".");
        return Double.parseDouble(replacedString);
    }

    @Override
    public String marshal(Double v) {
        return String.valueOf(v);
    }
}
