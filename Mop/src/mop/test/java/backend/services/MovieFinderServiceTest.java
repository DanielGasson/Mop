package mop.test.java.backend.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.backend.services.MovieFinderService;

public class MovieFinderServiceTest {

    @Test
    public void testConstructRequestUrlWithoutTitleSpace()
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        // arrange
        MovieFinderService sut = new MovieFinderService();

        String expectedUrl = "http://www.omdbapi.com/?t=TestActor&y=1999&plot=short&r=json";

        // act
        Method constructRequestUrl = MovieFinderService.class.getDeclaredMethod("constructRequestUrl", String.class, String.class);
        constructRequestUrl.setAccessible(true);
        String actualResult = (String) constructRequestUrl.invoke(sut, "TestActor", "1999");

        // assert
        assertEquals(expectedUrl, actualResult);
    }

    @Test
    public void testConstructRequestUrlWithTitleSpace()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        // arrange
        MovieFinderService sut = new MovieFinderService();

        String expectedUrl = "http://www.omdbapi.com/?t=Test+Actor&y=1999&plot=short&r=json";

        // act
        Method constructRequestUrl = MovieFinderService.class.getDeclaredMethod("constructRequestUrl", String.class, String.class);
        constructRequestUrl.setAccessible(true);
        String actualResult = (String) constructRequestUrl.invoke(sut, "Test Actor", "1999");

        // assert
        assertEquals(expectedUrl, actualResult);
    }

    @Test
    public void testConstructRequestUrlWithoutYear()
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        // arrange
        MovieFinderService sut = new MovieFinderService();

        String expectedUrl = "http://www.omdbapi.com/?t=TestActor&y=&plot=short&r=json";

        // act
        Method constructRequestUrl = MovieFinderService.class.getDeclaredMethod("constructRequestUrl", String.class, String.class);
        constructRequestUrl.setAccessible(true);
        String actualResult = (String) constructRequestUrl.invoke(sut, "TestActor", null);

        // assert
        assertEquals(expectedUrl, actualResult);
    }
}
