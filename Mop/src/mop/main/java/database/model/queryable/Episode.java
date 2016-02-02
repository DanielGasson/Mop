package mop.main.java.database.model.queryable;

public class Episode extends Queryable {

    private int episodeId, episodeNumber;
    private String name, fileLocation;

    public int getEpisodeId() {

        return episodeId;
    }

    public int getEpisodeNumber() {

        return episodeNumber;
    }

    public String getName() {

        return name;
    }

    public String getFileLocation() {

        return fileLocation;
    }

    public void setEpisodeId(Integer episodeId) {

        this.episodeId = episodeId;
    }

    public void setEpisodeNumber(Integer episodeNumber) {

        this.episodeNumber = episodeNumber;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setFileLocation(String fileLocation) {

        this.fileLocation = fileLocation;
    }
}

