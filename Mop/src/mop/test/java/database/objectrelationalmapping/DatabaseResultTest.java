package mop.test.java.database.objectrelationalmapping;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import mop.main.java.database.objectrelationalmapping.helpers.DatabaseResult;

public class DatabaseResultTest {

    private ResultSetMetaData metadataMock;

    @Before
    public void setup() throws SQLException {

        metadataMock = Mockito.mock(ResultSetMetaData.class);

        Mockito.when(metadataMock.getColumnCount()).thenReturn(9);

        Mockito.when(metadataMock.getColumnName(1)).thenReturn("MovieId");
        Mockito.when(metadataMock.getColumnName(2)).thenReturn("Title");
        Mockito.when(metadataMock.getColumnName(3)).thenReturn("Year");
        Mockito.when(metadataMock.getColumnName(4)).thenReturn("Director");
        Mockito.when(metadataMock.getColumnName(5)).thenReturn("Length");
        Mockito.when(metadataMock.getColumnName(6)).thenReturn("Description");
        Mockito.when(metadataMock.getColumnName(7)).thenReturn("ImageLocation");
        Mockito.when(metadataMock.getColumnName(8)).thenReturn("FileLocation");
        Mockito.when(metadataMock.getColumnName(9)).thenReturn("IsHighDefinition");

        Mockito.when(metadataMock.getColumnClassName(1)).thenReturn("java.lang.Integer");
        Mockito.when(metadataMock.getColumnClassName(2)).thenReturn("java.lang.String");
        Mockito.when(metadataMock.getColumnClassName(3)).thenReturn("java.lang.String");
        Mockito.when(metadataMock.getColumnClassName(4)).thenReturn("java.lang.String");
        Mockito.when(metadataMock.getColumnClassName(5)).thenReturn("java.lang.Integer");
        Mockito.when(metadataMock.getColumnClassName(6)).thenReturn("java.lang.String");
        Mockito.when(metadataMock.getColumnClassName(7)).thenReturn("java.lang.String");
        Mockito.when(metadataMock.getColumnClassName(8)).thenReturn("java.lang.String");
        Mockito.when(metadataMock.getColumnClassName(9)).thenReturn("java.lang.Boolean");
    }

    @After
    public void teardown() {

        metadataMock = null;
    }

    @Test
    public void testParseResultSet() throws SQLException {

        fail();
    }

    @Test
    public void testGetColumnDetails() throws SQLException {

        DatabaseResult sut = new DatabaseResult();

        Map<String,Class<Object>> result = sut.getColumnDetails(metadataMock);

        assertEquals(9, result.size());

        assertEquals(java.lang.Integer.class, result.get("MovieId"));
        assertEquals(java.lang.String.class, result.get("Title"));
        assertEquals(java.lang.String.class, result.get("Year"));
        assertEquals(java.lang.String.class, result.get("Director"));
        assertEquals(java.lang.Integer.class, result.get("Length"));
        assertEquals(java.lang.String.class, result.get("Description"));
        assertEquals(java.lang.String.class, result.get("ImageLocation"));
        assertEquals(java.lang.String.class, result.get("FileLocation"));
        assertEquals(java.lang.Boolean.class, result.get("IsHighDefinition"));
    }
}
