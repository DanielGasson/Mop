package mop.main.java.database.model.queryable;

public class Genre extends Queryable {

    private int genreId;
    private String name;

    public int getGenreId() {

        return genreId;
    }

    public String getName() {

        return name;
    }

    public void setGenreId(Integer genreId) {

        this.genreId = genreId;
    }

    public void setName(String name) {

        this.name = name;
    }
}
