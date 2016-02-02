START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    SeriesId SMALLINT NOT NULL
    ,Title VARCHAR(150) NOT NULL
    ,Seasons TINYINT NOT NULL
    ,ImageLocation VARCHAR(260) NOT NULL
);

INSERT INTO Snapshot
(
    SeriesId
    ,Title
    ,Seasons
    ,ImageLocation
)
VALUES
(1, 'Extras', 2, 'V:\\Images\\Extras.jpg')
,(2, 'House of Lies', 5, 'V:\\Images\\House of Lies.jpg')
;

UPDATE TestMop.Series tms
  INNER JOIN Snapshot s
    ON tms.SeriesId = s.SeriesId
SET
  tms.Title = s.Title
  ,tms.Seasons = s.Seasons
  ,tms.ImageLocation = s.ImageLocation;

INSERT INTO TestMop.Series (SeriesId, Title, Seasons, ImageLocation)
SELECT
    s.SeriesId
    ,s.Title
    ,s.Seasons
    ,s.ImageLocation
FROM
    Snapshot s
    LEFT JOIN TestMop.Series tms
        ON s.SeriesId = tms.SeriesId
WHERE tms.SeriesId IS NULL;

COMMIT;

DROP TABLE Snapshot;
