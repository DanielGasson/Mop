package mop.test.java.backend.utilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.backend.utilities.MethodGenerator;
import mop.main.java.database.model.queryable.Movie;

public class MethodGeneratorTest {

    @Test
    public void testGeneratePrimaryKeyGetter() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        Movie movie = new Movie();
        movie.setMovieId(5);

        Method method = MethodGenerator.generatePrimaryKeyAccessor(movie);
        assertEquals("getMovieId", method.getName());

        int result = (int) method.invoke(movie);
        assertEquals(5, result);
    }
}
