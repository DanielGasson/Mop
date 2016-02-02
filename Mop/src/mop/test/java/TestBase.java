package mop.test.java;

import java.lang.reflect.Field;
import org.apache.logging.log4j.Logger;

import mop.main.java.backend.caching.Cache;
import mop.main.java.backend.caching.DatabaseCache;
import mop.main.java.backend.utilities.Log;
import mop.main.java.common.Constants;

public class TestBase {

    private static final Logger log = Log.getLog(TestBase.class);

    protected TestBase() {

        Cache cache = DatabaseCache.getInstance();
        cache.set(Constants.AppConfigSections.defaultSchema, "TestMop");
    }

    /**
     * Gets the value of an object's field.
     * Helpful for testing class fields with no accessors.
     * @param object, containing the field
     * @param fieldName, the string name of the object's field
     * @return the value of the object's field
     */
    public Object getFieldValueFromObject(Object object, String fieldName) {

        Object result = null;
        try {

            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            result = field.get(object);
        }
        catch(NoSuchFieldException | IllegalAccessException ex) {

            log.info("Error getting field value for mop.test.", ex);
        }

        return result;
    }
}
