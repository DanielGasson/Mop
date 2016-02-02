package mop.main.java.database.objectrelationalmapping.helpers;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class EntityFields {

    private final ArrayList<Field> relationFields = new ArrayList<>();
    private final ArrayList<Field> columnFields = new ArrayList<>();

    public boolean addRelationField(Field field) {

        return relationFields.add(field);
    }

    public boolean addColumnField(Field field) {

        return columnFields.add(field);
    }

    public ArrayList<Field> getRelationFields() {

        return relationFields;
    }

    public ArrayList<Field> getColumnFields() {

        return columnFields;
    }
}
