START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    SeasonId SMALLINT NOT NULL
    ,SeriesId SMALLINT NOT NULL
    ,SeasonNumber TINYINT NOT NULL
    ,Year YEAR NOT NULL
    ,Episodes TINYINT NOT NULL
);

INSERT INTO Snapshot
(
    SeasonId
    ,SeriesId
    ,SeasonNumber
    ,Year
    ,Episodes
)
VALUES
(1, 1, 1, '2005', 6)
,(2, 1, 2, '2006', 6)
,(3, 2, 1, '2012', 12)
,(4, 2, 2, '2013', 12)
;

UPDATE TestMop.Season tms
  INNER JOIN Snapshot s
    ON tms.SeasonId = s.SeasonId
SET
  tms.SeriesId = s.SeriesId
  ,tms.SeasonNumber = s.SeasonNumber
  ,tms.Year = s.Year
  ,tms.Episodes = s.Episodes;

INSERT INTO TestMop.Season (SeasonId, SeriesId, SeasonNumber, Year, Episodes)
SELECT
    s.SeasonId
    ,s.SeriesId
    ,s.SeasonNumber
    ,s.Year
    ,s.Episodes
FROM
    Snapshot s
    LEFT JOIN TestMop.Season tms
        ON s.SeasonId = tms.SeasonId
WHERE tms.SeasonId IS NULL;

COMMIT;

DROP TABLE Snapshot;
