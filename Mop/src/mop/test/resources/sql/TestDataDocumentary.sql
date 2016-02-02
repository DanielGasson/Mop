START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    DocumentaryId SMALLINT NOT NULL
    ,IsSeries BOOLEAN NOT NULL
    ,Title VARCHAR(150) NOT NULL
    ,ImageLocation VARCHAR(260) NOT NULL
    ,FileLocation VARCHAR(260) NULL
    ,Episodes TINYINT NOT NULL
);

INSERT INTO Snapshot
(
    DocumentaryId
    ,IsSeries
    ,Title
    ,ImageLocation
    ,FileLocation
    ,Episodes
)
VALUES
(1, 0, 'BlackFish', 'V:\\Images\\BlackFish.jpg', 'V:\\Documentaries\\BlackFish.mp4', 0)
,(2, 1, 'Louis Theroux: Weird Weekends', 'V:\\Images\\Weird Weekends.jpg', NULL, 17)
;

UPDATE TestMop.Documentary d
  INNER JOIN Snapshot s
    ON d.DocumentaryId = s.DocumentaryId
SET
  d.IsSeries = s.IsSeries
  ,d.Title = s.Title
  ,d.ImageLocation = s.ImageLocation
  ,d.FileLocation = s.FileLocation
  ,d.Episodes = s.Episodes;

INSERT INTO TestMop.Documentary (DocumentaryId, IsSeries, Title, ImageLocation, FileLocation, Episodes)
SELECT
    s.DocumentaryId
    ,s.IsSeries
    ,s.Title
    ,s.ImageLocation
    ,s.FileLocation
    ,s.Episodes
FROM
    Snapshot s
    LEFT JOIN TestMop.Documentary d
        ON s.DocumentaryId = d.DocumentaryId
WHERE d.DocumentaryId IS NULL;

COMMIT;

DROP TABLE Snapshot;
