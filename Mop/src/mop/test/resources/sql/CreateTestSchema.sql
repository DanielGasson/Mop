-- -----------------------------------------------------
-- Sets up the TestTestMop Schema, and tables, keys, and indexes
-- -----------------------------------------------------

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

START TRANSACTION;

-- TestMop Schema
DROP SCHEMA IF EXISTS TestMop;

CREATE SCHEMA IF NOT EXISTS TestMop DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE TestMop ;

-- TestMop.Movie
DROP TABLE IF EXISTS TestMop.Movie;

CREATE TABLE IF NOT EXISTS TestMop.Movie
(
    MovieId SMALLINT NOT NULL,
    Title VARCHAR(150) NOT NULL,
    Year YEAR(4) NULL,
    Director VARCHAR(50) NULL,
    Length SMALLINT NULL,
    Description VARCHAR(1000) NULL,
    ImageLocation VARCHAR(260) NULL,
    FileLocation VARCHAR(260) NOT NULL,
    IsHighDefinition BOOLEAN NULL,

    PRIMARY KEY (MovieId),
    INDEX IDX_Movie_Title (Title ASC),
    INDEX IDX_Movie_Year (Year ASC),
    INDEX IDX_Movie_Director (Director ASC)
)
ENGINE = InnoDB;

-- TestMop.Actor
DROP TABLE IF EXISTS TestMop.Actor;

CREATE TABLE IF NOT EXISTS TestMop.Actor
(
    ActorId SMALLINT NOT NULL AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,

    PRIMARY KEY (ActorId),
    INDEX IDX_Actor_Name (Name ASC)
)
ENGINE = InnoDB;

-- TestMop.MovieActor
DROP TABLE IF EXISTS TestMop.MovieActor;

CREATE TABLE IF NOT EXISTS TestMop.MovieActor
(
    MovieId SMALLINT NOT NULL,
    ActorId SMALLINT NOT NULL,

    INDEX FK_IDX_MovieActor_Movie (MovieId ASC),
    INDEX FK_IDX_MovieActor_Actor (ActorId ASC),
    PRIMARY KEY (MovieId, ActorId),
    CONSTRAINT FK_MovieActor_Movie
        FOREIGN KEY (MovieId)
        REFERENCES TestMop.Movie (MovieId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT FK_MovieActor_Actor
        FOREIGN KEY (ActorId)
        REFERENCES TestMop.Actor (ActorId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- TestMop.RelatedMovie
DROP TABLE IF EXISTS TestMop.RelatedMovie;

CREATE TABLE IF NOT EXISTS TestMop.RelatedMovie
(
    RelatedMovieId SMALLINT NOT NULL AUTO_INCREMENT,
    MovieId SMALLINT NOT NULL,
    Relation SMALLINT NOT NULL,

    PRIMARY KEY (RelatedMovieId),
    INDEX FK_IDX_RelatedMovie_Movie_Movie (MovieId ASC),
    INDEX FK_IDX_RelatedMovie_Relation_Movie (Relation ASC),
    CONSTRAINT FK_RelatedMovie_Movie_Movie
        FOREIGN KEY (MovieId)
        REFERENCES TestMop.Movie (MovieId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT FK_RelatedMovie_Relation_Movie
        FOREIGN KEY (Relation)
        REFERENCES TestMop.Movie (MovieId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- TestMop.MovieRelatedItem
DROP TABLE IF EXISTS TestMop.MovieRelatedItem;

CREATE TABLE IF NOT EXISTS TestMop.MovieRelatedItem
(
    MovieRelatedItemId INT NOT NULL,
    MovieID VARCHAR(45) NULL,

    PRIMARY KEY (MovieRelatedItemId)
)
ENGINE = InnoDB;

-- TestMop.AcademyAwardWinner
DROP TABLE IF EXISTS TestMop.AcademyAwardWinner;

CREATE TABLE IF NOT EXISTS TestMop.AcademyAwardWinner
(
    AcademyAwardWinnerId TINYINT NOT NULL AUTO_INCREMENT,
    MovieId SMALLINT NULL,

    PRIMARY KEY (AcademyAwardWinnerId),
    UNIQUE INDEX MovieId_UNIQUE (MovieId ASC),
    CONSTRAINT FK_AcademyAwardWinner_Movie
        FOREIGN KEY (MovieId)
        REFERENCES TestMop.Movie (MovieId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- TestMop.Playlist
DROP TABLE IF EXISTS TestMop.Playlist;

CREATE TABLE IF NOT EXISTS TestMop.Playlist
(
    PlaylistId TINYINT NOT NULL AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,

    PRIMARY KEY (PlaylistId)
)
ENGINE = InnoDB;

-- TestMop.Series
DROP TABLE IF EXISTS TestMop.Series;

CREATE TABLE IF NOT EXISTS TestMop.Series
(
    SeriesId SMALLINT NOT NULL AUTO_INCREMENT,
    Title VARCHAR(150) NOT NULL,
    Seasons TINYINT NULL,
    ImageLocation VARCHAR(260) NULL,

    PRIMARY KEY (SeriesId),
    INDEX IDX_Series_Title (Title ASC)
)
ENGINE = InnoDB;

-- TestMop.Season
DROP TABLE IF EXISTS TestMop.Season;

CREATE TABLE IF NOT EXISTS TestMop.Season
(
    SeasonId SMALLINT NOT NULL,
    SeriesId SMALLINT NOT NULL,
    SeasonNumber TINYINT NOT NULL,
    Year YEAR NULL,
    Episodes TINYINT NULL,

    PRIMARY KEY (SeasonId),
    INDEX FK_IDX_Season_Series (SeriesId ASC),
    INDEX IDX_Season_SeasonNumber (SeasonNumber ASC),
    CONSTRAINT FK_Season_Series
        FOREIGN KEY (SeriesId)
        REFERENCES TestMop.Series (SeriesId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- TestMop.SeasonEpisode
DROP TABLE IF EXISTS TestMop.SeasonEpisode;

CREATE TABLE IF NOT EXISTS TestMop.SeasonEpisode
(
    EpisodeId SMALLINT NOT NULL AUTO_INCREMENT,
    SeasonId SMALLINT NOT NULL,
    Title VARCHAR(150) NULL,
    EpisodeNumber TINYINT NOT NULL,
    FileLocation VARCHAR(260) NOT NULL,

    PRIMARY KEY (EpisodeId),
    INDEX FK_IDX_Episode_Season (SeasonId ASC),
    INDEX IDX_Episode_Title (Title ASC),
    CONSTRAINT FK_Episode_Season
        FOREIGN KEY (SeasonId)
        REFERENCES TestMop.Season (SeasonId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- TestMop.Documentary
DROP TABLE IF EXISTS TestMop.Documentary;

CREATE TABLE IF NOT EXISTS TestMop.Documentary
(
    DocumentaryId SMALLINT NOT NULL AUTO_INCREMENT,
    IsSeries BINARY(1) NOT NULL,
    Title VARCHAR(150) NULL,
    ImageLocation VARCHAR(260) NULL,
    FileLocation VARCHAR(260) NULL,
    Episodes TINYINT NULL DEFAULT 1,

    PRIMARY KEY (DocumentaryId),
    INDEX IDX_Documentary_Title (Title ASC)
)
ENGINE = InnoDB;

-- TestMop.DocumentaryEpisode
DROP TABLE IF EXISTS TestMop.DocumentaryEpisode;

CREATE TABLE IF NOT EXISTS TestMop.DocumentaryEpisode
(
    DocumentaryEpisodeId SMALLINT NOT NULL,
    DocumentaryId SMALLINT NOT NULL,
    Title VARCHAR(150) NULL,
    EpisodeNumber TINYINT NULL,
    FileLocation VARCHAR(260) NULL,

    PRIMARY KEY (DocumentaryEpisodeId),
    INDEX FK_IDX_DocumentaryEpisode_Documentary (DocumentaryId ASC),
    INDEX IDX_Episode_Title (Title ASC),
    CONSTRAINT FK_DocumentaryEpisode_Documentary
        FOREIGN KEY (DocumentaryId)
        REFERENCES TestMop.Documentary (DocumentaryId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- TestMop.PlaylistMovie
DROP TABLE IF EXISTS TestMop.PlaylistMovie;

CREATE TABLE IF NOT EXISTS TestMop.PlaylistMovie
(
    PlaylistId TINYINT NOT NULL,
    MovieId SMALLINT NOT NULL,

    INDEX FK_IDX_PlaylistMovie_Playlist (PlaylistId ASC),
    INDEX FK_IDX_PlaylistMovie_Movie (MovieId ASC),
    PRIMARY KEY (PlaylistId, MovieId),
    CONSTRAINT FK_IDX_PlaylistMovie_Playlist
        FOREIGN KEY (PlaylistId)
        REFERENCES TestMop.Playlist (PlaylistId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT FK_IDX_PlaylistMovie_Movie
        FOREIGN KEY (MovieId)
        REFERENCES TestMop.Movie (MovieId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- TestMop.PlaylistSeries
DROP TABLE IF EXISTS TestMop.PlaylistSeries;

CREATE TABLE IF NOT EXISTS TestMop.PlaylistSeries
(
    PlaylistId TINYINT NOT NULL,
    SeriesId SMALLINT NOT NULL,

    INDEX FK_IDX_PlaylistSeries_Playlist (PlaylistId ASC),
    INDEX FK_IDX_PlaylistSeries_Series (SeriesId ASC),
    CONSTRAINT FK_IDX_PlaylistSeries_Playlist
        FOREIGN KEY (PlaylistId)
        REFERENCES TestMop.Playlist (PlaylistId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT FK_IDX_PlaylistSeries_Series
        FOREIGN KEY (SeriesId)
        REFERENCES TestMop.Series (SeriesId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- TestMop.PlaylistDocumentary
DROP TABLE IF EXISTS TestMop.PlaylistDocumentary;

CREATE TABLE IF NOT EXISTS TestMop.PlaylistDocumentary
(
    PlaylistId TINYINT NOT NULL,
    DocumentaryId SMALLINT NOT NULL,

    INDEX FK_IDX_PlaylistDocumentary_Playlist (PlaylistId ASC),
    INDEX FK_IDX_PlaylistDocumentary_Documentary (DocumentaryId ASC),
    PRIMARY KEY (PlaylistId, DocumentaryId),
    CONSTRAINT FK_IDX_PlaylistDocumentary_Playlist
        FOREIGN KEY (PlaylistId)
        REFERENCES TestMop.Playlist (PlaylistId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT FK_IDX_PlaylistDocumentary_Documentary
        FOREIGN KEY (DocumentaryId)
        REFERENCES TestMop.Documentary (DocumentaryId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- TestMop.Genre
DROP TABLE IF EXISTS TestMop.Genre;

CREATE TABLE IF NOT EXISTS TestMop.Genre
(
    GenreId TINYINT NOT NULL,
    Name VARCHAR(25) NOT NULL,

    PRIMARY KEY (GenreId)
)
ENGINE = InnoDB;

-- TestMop.MovieGenre
DROP TABLE IF EXISTS TestMop.MovieGenre;

CREATE TABLE IF NOT EXISTS TestMop.MovieGenre
(
    MovieId SMALLINT NOT NULL,
    GenreId TINYINT NOT NULL,

    PRIMARY KEY (MovieId, GenreId),
    INDEX FK_IDX_MovieGenre_Genre (GenreId ASC),
    INDEX FK_IDX_MovieGenre_Movie (MovieId ASC),
    CONSTRAINT FK_IDX_MovieGenre_Movie
        FOREIGN KEY (MovieId)
        REFERENCES TestMop.Movie (MovieId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT FK_IDX_MovieGenre_Genre
        FOREIGN KEY (GenreId)
        REFERENCES TestMop.Genre (GenreId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

COMMIT;
