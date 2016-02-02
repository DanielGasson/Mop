package mop.main.java.database.objectrelationalmapping.helpers;

import java.util.ArrayList;

public class EntityMetadata {

    private Table baseTable;
    private final ArrayList<Entity> entities = new ArrayList<>();

    public EntityMetadata() {

    }

    public Table getBaseTable() {

        return baseTable;
    }

    public ArrayList<Entity> getEntities() {

        return entities;
    }

    public void setBaseTable(Table baseTable) {

        this.baseTable = baseTable;
    }

    public boolean addEntity(Entity entity) {

        return entities.add(entity);
    }
}
