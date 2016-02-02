package mop.main.java.database.model.queryable;

import java.util.ArrayList;
import java.time.format.DateTimeParseException;
import java.time.Year;

import mop.main.java.backend.utilities.Log;

import org.apache.logging.log4j.Logger;

public class Season extends Queryable {

    private static final Logger log = Log.getLog(Season.class);

    private int seasonId, seriesId, seasonNumber;
    private Year year;
    private final ArrayList<Episode> episodes;

    public Season() {

        episodes = new ArrayList<>();
    }

    public int getSeasonId() {

        return seasonId;
    }

    public int getSeriesId() {

        return seriesId;
    }

    public int getSeasonNumber() {

        return seasonNumber;
    }

    public Year getYear() {

        return year;
    }

    public void setSeasonId(int seasonId) {

        this.seasonId = seasonId;
    }

    public void setSeriesId(int seriesId) {

        this.seriesId = seriesId;
    }

    public void setSeasonNumber(int seasonNumber) {

        this.seasonNumber = seasonNumber;
    }

    public void setYear(Short year) {

        try {

            this.year = Year.parse(year.toString());
        }
        catch(DateTimeParseException ex) {

            log.error("Unable to create Year from '" + year.toString() + "'", ex);
        }
    }

    public boolean addEpisode(Episode episode) {

        return episodes.add(episode);
    }

    public boolean removeEpisode(Episode episode) {

        return episodes.remove(episode);
    }

    public Episode getEpisode(int episodeNumber) {

        for(Episode episode : episodes) {

            if(episode.getEpisodeNumber() == episodeNumber) {

                return episode;
            }
        }

        return null;
    }
}
