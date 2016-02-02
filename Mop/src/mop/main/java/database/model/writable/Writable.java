package mop.main.java.database.model.writable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

import mop.main.java.backend.utilities.Log;
import mop.main.java.common.exceptions.EntityMetadataException;
import mop.main.java.database.objectrelationalmapping.helpers.*;

import org.apache.logging.log4j.Logger;

public abstract class Writable {

    private static final Logger log = Log.getLog(Writable.class);

    /**
     * Gets the metadata for an entity.
     * @return an EntityMetadata object
     * @throws EntityMetadataException
     */
    public EntityMetadata getMetadata(EntityMetadata metadata) throws EntityMetadataException {

        if(metadata == null) {

            return getMetadata(this, new EntityMetadata());
        }

        return getMetadata(this, metadata);
    }

    private <T extends Writable> EntityMetadata getMetadata(T writableEntity, EntityMetadata metadata) throws EntityMetadataException {

        this.setTableName(writableEntity, metadata);
        Table entityName = Table.valueOf(writableEntity.getClass().getSimpleName().toUpperCase());
        Field[] fields = writableEntity.getClass().getDeclaredFields();

        // Split the fields into base or relational collections
        EntityFields organisedFields = this.organiseFields(fields);
        ArrayList<Field> columnFields = organisedFields.getColumnFields();
        ArrayList<Field> relationFields = organisedFields.getRelationFields();

        Entity entity = new Entity(entityName);

        for(Field field : columnFields) {

            String fieldName = field.getName();

            Object value;

            Column temp = field.getAnnotation(Column.class);

            try {

                if(temp.required()) {

                    if((value = field.get(writableEntity)) != null) {

                        Attribute attribute = new Attribute<>(fieldName, field.getType(), value);
                        entity.addAttribute(attribute);
                    }
                    else {

                        log.error("Database column " + fieldName + " is 'non-null' but field doesn't contain value.");
                        throw new EntityMetadataException("Database column " + fieldName + " is 'non-null' but field doesn't contain value.");
                    }
                }
                else if((value = field.get(this)) != null) {

                    Attribute attribute = new Attribute<>(fieldName, field.getType(), value);
                    entity.addAttribute(attribute);
                }
            }
            catch (IllegalAccessException ex) {

                log.error("Error retrieving value from field.", ex);
                throw new EntityMetadataException("Error retrieving value from field.", ex);
            }
        }

        metadata.addEntity(entity);

        ArrayList<T> relationEntities;
        try {

            relationEntities = this.extractArrayFields(relationFields, writableEntity);
        }
        catch(IllegalAccessException ex) {

            log.error("Error extracting array objects from fields.", ex);
            throw new EntityMetadataException(ex);
        }

        for(T relation : relationEntities) {

            if(relation != null) {

                getMetadata(relation, metadata);
            }
        }

        return metadata;
    }

    private <T extends Writable> void setTableName(T entity, EntityMetadata metadata) {

        // only set the base table if it hasn't already been set
        if(metadata.getBaseTable() == null) {

            metadata.setBaseTable(Table.valueOf(entity.getClass().getSimpleName().toUpperCase()));
        }
    }

    private EntityFields organiseFields(Field[] fields) {

        EntityFields result = new EntityFields();

        for(Field field : fields) {

            field.setAccessible(true);
            Annotation[] annotations = field.getAnnotations();

            if(annotations.length == 1 && annotations[0] != null) {

                if(annotations[0] instanceof Column) {

                    result.addColumnField(field);
                }
                else if(annotations[0] instanceof Relation) {

                    result.addRelationField(field);
                }
            }
        }

        return result;
    }

    private <T extends Writable> ArrayList<T> extractArrayFields(ArrayList<Field> fields, T entity) throws IllegalAccessException {

        ArrayList<T> result = new ArrayList<>();

        for(Field field : fields) {

            // if the field is an array then extract all elements and add to the collection
            if(field.getType().isArray()) {

                T[] array = (T[]) field.get(entity);
                if(array != null) {

                    Collections.addAll(result, array);
                }
            }
            // otherwise just get the object from the field and add to the collection
            else {

                T temp = (T) field.get(entity);
                result.add(temp);
            }
        }

        return result;
    }
}
