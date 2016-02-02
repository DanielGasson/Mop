package mop.main.java.database.objectrelationalmapping.helpers;

import java.util.HashMap;
import java.util.Map;

public class Row {

    private final Map<Integer, Attribute> rowAttributes;

    public Row() {

        rowAttributes = new HashMap<>();
    }

    public void addAttribute(Integer columnIndex, Attribute attributeDetails) {

        rowAttributes.put(columnIndex, attributeDetails);
    }

    public Map<Integer, Attribute> getRowDetails() {

        return rowAttributes;
    }
}
