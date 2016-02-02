package mop.main.java.backend.utilities;

import java.lang.reflect.Method;

public class MethodGenerator {

    public static <T> Method generatePrimaryKeyAccessor(T type) throws NoSuchMethodException {

        return type.getClass().getMethod("get" + type.getClass().getSimpleName() + "Id");
    }
}
