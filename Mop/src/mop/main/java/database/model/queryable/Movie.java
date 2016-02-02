package mop.main.java.database.model.queryable;

import java.time.Year;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import mop.main.java.backend.utilities.Length;
import mop.main.java.backend.utilities.LengthConverter;
import mop.main.java.backend.utilities.Log;

import org.apache.logging.log4j.Logger;

public class Movie extends Queryable {

    private static final Logger log = Log.getLog(Movie.class);

    private int movieId;
    private int playlistId;
    private String title;
    private short year;
    private String director;
    private int length;
    private String description;
    private String imageLocation;
    private String fileLocation;
    private boolean isHighDefinition;
    private final ArrayList<Genre> genres;
    private final ArrayList<Actor> actors;
    private final ArrayList<Integer> relatedMovieIds;

    public Movie() {

        genres = new ArrayList<>();
        actors = new ArrayList<>();
        relatedMovieIds = new ArrayList<>();
    }

    public int getPlaylistId() {

        return playlistId;
    }

    public int getMovieId() {

        return movieId;
    }

    public String getTitle() {

        return title;
    }

    public short getYear() {

        return year;
    }

    public String getDirector() {

        return director;
    }

    public Length getLength() {

        return LengthConverter.convertToHoursAndMinutes(length);
    }

    public String getDescription() {

        return description;
    }

    public String getImageLocation() {

        return imageLocation;
    }

    public String getFileLocation() {

        return fileLocation;
    }

    public boolean getIsHighDefinition() {

        return isHighDefinition;
    }

    public ArrayList<Genre> getGenres() {

        return genres;
    }

    public ArrayList<Actor> getActors() {

        return actors;
    }

    public ArrayList<Integer> getRelatedMovieIds() {

        return relatedMovieIds;
    }

    public void setPlaylistId(Integer playlistId) {

        this.playlistId = playlistId;
    }

    public void setMovieId(Integer movieId) {

        this.movieId = movieId;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setYear(Short year) {

        try {

            Year temp = Year.parse(String.valueOf(year));
            this.year = Short.parseShort(temp.toString());
        }
        catch(DateTimeParseException ex) {

            log.error(year  + " not a valid year.", ex);
            this.year = 0000;
        }
    }

    public void setDirector(String director) {

        this.director = director;
    }

    public void setLength(Integer length) {

        this.length = length;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public void setImageLocation(String imageLocation) {

        this.imageLocation = imageLocation;
    }

    public void setFileLocation(String fileLocation) {

        this.fileLocation = fileLocation;
    }

    public void setIsHighDefinition(Boolean isHighDefinition) {

        this.isHighDefinition = isHighDefinition;
    }

    public boolean addGenre(Genre genre) {

        return genres.add(genre);
    }

    public boolean addActor(Actor actor) {

        return actors.add(actor);
    }

    public boolean addRelatedMovieId(int relatedMovieId) {

        return relatedMovieIds.add(relatedMovieId);
    }
}
