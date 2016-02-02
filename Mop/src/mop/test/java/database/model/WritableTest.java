package mop.test.java.database.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import mop.main.java.database.model.writable.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.common.exceptions.EntityMetadataException;
import mop.main.java.database.objectrelationalmapping.helpers.EntityMetadata;
import mop.main.java.database.objectrelationalmapping.helpers.Table;
import mop.main.java.database.objectrelationalmapping.helpers.EntityFields;

public class WritableTest {

    @Test
    public void testSetTableNameWhenTableNameNull()
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        // arrange
        Movie sut = new Movie();

        EntityMetadata metadata = new EntityMetadata();

        // act (get private method via reflection)
        Method method = Writable.class.getDeclaredMethod("setTableName", Writable.class, EntityMetadata.class);
        method.setAccessible(true);
        method.invoke(sut, sut, metadata);

        // assert
        assertEquals(Table.MOVIE, metadata.getBaseTable());
    }

    @Test
    public void testSetTableNameDoesNothingWhenTableNameNotNull()
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        // arrange
        Movie sut = new Movie();

        EntityMetadata metadata = new EntityMetadata();
        metadata.setBaseTable(Table.DOCUMENTARY);

        // act (get private method via reflection)
        Method setTableName = Writable.class.getDeclaredMethod("setTableName", Writable.class, EntityMetadata.class);
        setTableName.setAccessible(true);
        setTableName.invoke(sut, sut, metadata);

        // assert
        assertEquals(Table.DOCUMENTARY, metadata.getBaseTable());
    }

    @Test
    public void testOrganiseEntityFields()
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        // arrange
        Movie sut = new Movie();

        // act (get private method via reflection)
        Object fields = sut.getClass().getDeclaredFields();

        Method organiseFields = Writable.class.getDeclaredMethod("organiseFields", Field[].class);
        organiseFields.setAccessible(true);
        EntityFields organisedFields = (EntityFields) organiseFields.invoke(sut, fields);

        // assert
        assertEquals(9, organisedFields.getColumnFields().size());
        assertEquals(5, organisedFields.getRelationFields().size());
    }

    @Test
    public void testExtractArrayFields()
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        // arrange
        int movieId = 9;
        int actorId = 1;

        Playlist playlist = new Playlist.Builder()
            .playlistId(1)
            .name("Test Playlist")
            .build();

        PlaylistMovie entity1 = new PlaylistMovie.Builder()
            .playlistId(1)
            .movieId(movieId)
            .playlist(playlist)
            .build();

        AcademyAwardWinner entity2 = new AcademyAwardWinner.Builder()
            .academyAwardWinnerId(5)
            .movieId(movieId)
            .build();

        Actor actor = new Actor.Builder()
            .actorId(actorId)
            .name("Test Actor 1")
            .build();

        MovieActor entity3 = new MovieActor.Builder()
            .movieId(movieId)
            .actorId(actorId)
            .actor(actor)
            .build();

        MovieActor entity4 = new MovieActor.Builder()
            .movieId(movieId)
            .actorId(2)
            .build();

        MovieActor[] movieActors = { entity3, entity4 };

        Genre genre = new Genre.Builder()
            .genreId(1)
            .name("Test Genre")
            .build();

        MovieGenre[] entity5 = { new MovieGenre.Builder()
            .movieId(movieId)
            .genreId(1)
            .genre(genre)
            .build() };

        Movie sut = new Movie.Builder()
            .movieId(movieId)
            .title("Test Title")
            .year("2015")
            .director("Test Director")
            .description("Test Description")
            .imageLocation("C:/Image.jpg")
            .fileLocation("C:/File.jpg")
            .length((short) 120)
            .isHighDefinition(false)
            .playlistMovie(entity1)
            .academyAwardWinner(entity2)
            .movieActors(movieActors)
            .movieGenres(entity5)
            .build();

        // act (get private method via reflection)
        Object fields = sut.getClass().getDeclaredFields();

        Method organiseFields = Writable.class.getDeclaredMethod("organiseFields", Field[].class);
        organiseFields.setAccessible(true);
        EntityFields organisedFields = (EntityFields) organiseFields.invoke(sut, fields);

        Method extractArrayFields = Writable.class.getDeclaredMethod("extractArrayFields", ArrayList.class, Writable.class);
        extractArrayFields.setAccessible(true);
        ArrayList<Writable> result = (ArrayList<Writable>) extractArrayFields.invoke(sut, organisedFields.getRelationFields(), sut);

        // assert
        assertEquals(5, result.size());
    }

    @Test
    public void testGetEntityMetadataWithNoRelations() throws EntityMetadataException {

        // arrange
        Movie sut = new Movie.Builder()
            .movieId(9)
            .title("Test Title")
            .year("2015")
            .director("Test Director")
            .description("Test Description")
            .imageLocation("C:/Image.jpg")
            .fileLocation("C:/File.jpg")
            .length((short) 120)
            .isHighDefinition(false)
            .build();

        // act
        EntityMetadata metadata = sut.getMetadata(null);

        // assert
        assertEquals("Movie", metadata.getBaseTable().toString());

        assertEquals(1, metadata.getEntities().size());

        assertEquals(9, metadata.getEntities().get(0).getAttributes().size());

        assertEquals(Table.MOVIE, metadata.getEntities().get(0).getTableName());
    }

    @Test
    public void testGetEntityMetadataWithOneRelation() throws EntityMetadataException {

        // arrange
        int movieId = 9;

        AcademyAwardWinner academyAwardWinner = new AcademyAwardWinner.Builder()
            .academyAwardWinnerId(5)
            .movieId(movieId)
            .build();

        Movie sut = new Movie.Builder()
            .movieId(movieId)
            .title("Test Title")
            .year("2015")
            .director("Test Director")
            .description("Test Description")
            .imageLocation("C:/Image.jpg")
            .fileLocation("C:/File.jpg")
            .length((short) 120)
            .isHighDefinition(false)
            .academyAwardWinner(academyAwardWinner)
            .build();

        // act
        EntityMetadata metadata = sut.getMetadata(null);

        // assert
        assertEquals("Movie", metadata.getBaseTable().toString());

        assertEquals(2, metadata.getEntities().size());

        assertEquals(9, metadata.getEntities().get(0).getAttributes().size());
        assertEquals(2, metadata.getEntities().get(1).getAttributes().size());

        assertEquals(Table.MOVIE, metadata.getEntities().get(0).getTableName());
        assertEquals(Table.ACADEMYAWARDWINNER, metadata.getEntities().get(1).getTableName());
    }

    @Test
    public void testGetEntityMetadataWithMultipleRelations() throws EntityMetadataException {

        // arrange
        int movieId = 9;

        PlaylistMovie playlistMovie = new PlaylistMovie.Builder()
            .playlistId(1)
            .movieId(movieId)
            .build();

        AcademyAwardWinner academyAwardWinner = new AcademyAwardWinner.Builder()
            .academyAwardWinnerId(5)
            .movieId(movieId)
            .build();

        Movie sut = new Movie.Builder()
            .movieId(movieId)
            .title("Test Title")
            .year("2015")
            .director("Test Director")
            .description("Test Description")
            .imageLocation("C:/Image.jpg")
            .fileLocation("C:/File.jpg")
            .length((short) 120)
            .isHighDefinition(false)
            .playlistMovie(playlistMovie)
            .academyAwardWinner(academyAwardWinner)
            .build();

        // act
        EntityMetadata metadata = sut.getMetadata(null);

        // assert
        assertEquals("Movie", metadata.getBaseTable().toString());

        assertEquals(3, metadata.getEntities().size());

        assertEquals(9, metadata.getEntities().get(0).getAttributes().size());
        assertEquals(2, metadata.getEntities().get(1).getAttributes().size());
        assertEquals(2, metadata.getEntities().get(2).getAttributes().size());

        assertEquals(Table.MOVIE, metadata.getEntities().get(0).getTableName());
        assertEquals(Table.ACADEMYAWARDWINNER, metadata.getEntities().get(1).getTableName());
        assertEquals(Table.PLAYLISTMOVIE, metadata.getEntities().get(2).getTableName());
    }

    @Test
    public void testGetEntityMetadataWithRecursiveRelation() throws EntityMetadataException {

        // arrange
        int movieId = 9;

        Playlist playlist = new Playlist.Builder()
            .playlistId(1)
            .name("Test Playlist")
            .build();

        PlaylistMovie playlistMovie = new PlaylistMovie.Builder()
            .playlistId(1)
            .movieId(movieId)
            .playlist(playlist)
            .build();

        Movie sut = new Movie.Builder()
            .movieId(movieId)
            .title("Test Title")
            .year("2015")
            .director("Test Director")
            .description("Test Description")
            .imageLocation("C:/Image.jpg")
            .fileLocation("C:/File.jpg")
            .length((short) 120)
            .isHighDefinition(false)
            .playlistMovie(playlistMovie)
            .build();

        // act
        EntityMetadata metadata = sut.getMetadata(null);

        // assert
        assertEquals("Movie", metadata.getBaseTable().toString());

        assertEquals(3, metadata.getEntities().size());

        assertEquals(9, metadata.getEntities().get(0).getAttributes().size());
        assertEquals(2, metadata.getEntities().get(1).getAttributes().size());
        assertEquals(2, metadata.getEntities().get(2).getAttributes().size());

        assertEquals(Table.MOVIE, metadata.getEntities().get(0).getTableName());
        assertEquals(Table.PLAYLISTMOVIE, metadata.getEntities().get(1).getTableName());
        assertEquals(Table.PLAYLIST, metadata.getEntities().get(2).getTableName());
    }

    @Test
    public void testGetEntityMetadataWithCollectionOfRelation() throws EntityMetadataException {

        // arrange
        int movieId = 9;
        int actorId = 2;

        Actor actor = new Actor.Builder()
            .actorId(actorId)
            .name("Test Actor")
            .build();

        MovieActor movieActor1 = new MovieActor.Builder()
            .movieId(movieId)
            .actorId(1)
                .build();

        MovieActor movieActor2 = new MovieActor.Builder()
            .movieId(movieId)
            .actorId(actorId)
            .actor(actor)
                .build();

        MovieActor[] movieActors = { movieActor1, movieActor2 };

        Movie sut = new Movie.Builder()
            .movieId(movieId)
            .title("Test Title")
            .year("2015")
            .director("Test Director")
            .description("Test Description")
            .imageLocation("C:/Image.jpg")
            .fileLocation("C:/File.jpg")
            .length((short) 120)
            .isHighDefinition(false)
            .movieActors(movieActors)
            .build();

        // act
        EntityMetadata metadata = sut.getMetadata(null);

        // assert
        assertEquals("Movie", metadata.getBaseTable().toString());

        assertEquals(4, metadata.getEntities().size());

        assertEquals(9, metadata.getEntities().get(0).getAttributes().size());
        assertEquals(2, metadata.getEntities().get(1).getAttributes().size());
        assertEquals(2, metadata.getEntities().get(2).getAttributes().size());
        assertEquals(2, metadata.getEntities().get(3).getAttributes().size());

        assertEquals(Table.MOVIE, metadata.getEntities().get(0).getTableName());
        assertEquals(Table.MOVIEACTOR, metadata.getEntities().get(1).getTableName());
        assertEquals(Table.MOVIEACTOR, metadata.getEntities().get(2).getTableName());
        assertEquals(Table.ACTOR, metadata.getEntities().get(3).getTableName());
    }

    @Test
    public void testGetEntityMetadataWithFullEntity() throws EntityMetadataException {

        // arrange
        int movieId = 9;
        int actorId = 1;

        Playlist playlist = new Playlist.Builder()
            .playlistId(1)
            .name("Test Playlist")
            .build();

        PlaylistMovie playlistMovie = new PlaylistMovie.Builder()
            .playlistId(1)
            .movieId(movieId)
            .playlist(playlist)
            .build();

        AcademyAwardWinner academyAwardWinner = new AcademyAwardWinner.Builder()
            .academyAwardWinnerId(5)
            .movieId(movieId)
            .build();

        Actor actor = new Actor.Builder()
            .actorId(actorId)
            .name("Test Actor 1")
            .build();

        MovieActor movieActor1 = new MovieActor.Builder()
            .movieId(movieId)
            .actorId(actorId)
            .actor(actor)
                .build();

        MovieActor movieActor2 = new MovieActor.Builder()
            .movieId(movieId)
            .actorId(2)
                .build();

        MovieActor[] movieActors = { movieActor1, movieActor2 };

        Genre genre = new Genre.Builder()
            .genreId(1)
            .name("Test Genre")
            .build();

        MovieGenre[] movieGenre = { new MovieGenre.Builder()
            .movieId(movieId)
            .genreId(1)
            .genre(genre)
            .build() };

        RelatedMovie[] relatedMovies = { new RelatedMovie.Builder()
            .movieId(movieId)
            .relatedMovieId(1)
            .relationId(8)
            .build() };

        Movie sut = new Movie.Builder()
            .movieId(9)
            .title("Test Title")
            .year("2015")
            .director("Test Director")
            .description("Test Description")
            .imageLocation("C:/Image.jpg")
            .fileLocation("C:/File.jpg")
            .length((short) 120)
            .isHighDefinition(false)
            .playlistMovie(playlistMovie)
            .academyAwardWinner(academyAwardWinner)
            .movieActors(movieActors)
            .movieGenres(movieGenre)
            .relatedMovies(relatedMovies)
            .build();

        // act
        EntityMetadata metadata = sut.getMetadata(null);

        // assert
        assertEquals(10, metadata.getEntities().size());
    }
}
