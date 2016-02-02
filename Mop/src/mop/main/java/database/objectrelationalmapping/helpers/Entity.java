package mop.main.java.database.objectrelationalmapping.helpers;

import java.util.ArrayList;

import mop.main.java.backend.utilities.Log;

import org.apache.logging.log4j.Logger;

public class Entity {

    private static final Logger log = Log.getLog(Entity.class);

    private final Table tableName;
    private final ArrayList<Attribute> attributes = new ArrayList<>();

    public Entity(Table tableName) {

        if(tableName == null) {

            log.error("Entity's table name cannot be null.");
        }

        this.tableName = tableName;
    }

    public Table getTableName() {

        return tableName;
    }

    public boolean addAttribute(Attribute attribute) {

        return attributes.add(attribute);
    }

    public ArrayList<Attribute> getAttributes() {

        return attributes;
    }
}
