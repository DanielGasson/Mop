package mop.main.java.database.model.queryable;

import java.util.ArrayList;

public class Series extends Queryable {

    private int seriesId, numSeasons;
    private final ArrayList<Season> seasons;
    private String seriesName, imageLocation;

    public Series() {

        seasons = new ArrayList<>();
    }

    public int getSeriesId() {

        return this.seriesId;
    }

    public int getNumSeasons() {

        return numSeasons;
    }

    public String getSeriesName() {

        return this.seriesName;
    }

    public String getImageLocation() {

        return this.imageLocation;
    }

    public void setSeriesId(int seriesId) {

        this.seriesId = seriesId;
    }

    public void setNumSeasons(int numSeasons) {

        this.numSeasons = numSeasons;
    }

    public void setSeriesName(String seriesName) {

        this.seriesName = seriesName;
    }

    public void setImageLocation(String imageLocation) {

        this.imageLocation = imageLocation;
    }

    public boolean addSeason(Season season) {

        return seasons.add(season);
    }

    public boolean removeSeason(Season season) {

        return seasons.remove(season);
    }

    public ArrayList<Season> getSeasons() {

        return seasons;
    }
}
