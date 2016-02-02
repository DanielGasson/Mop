START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    PlaylistId TINYINT NOT NULL
    ,SeriesId SMALLINT NOT NULL
);

INSERT INTO Snapshot
(
    PlaylistId
    ,SeriesId
)
VALUES
(1, 1)
,(1, 2)
;

INSERT INTO TestMop.PlaylistSeries (PlaylistId, SeriesId)
SELECT
    s.PlaylistId
    ,s.SeriesId
FROM
  Snapshot s
  LEFT JOIN TestMop.PlaylistSeries ps
    ON s.SeriesId = ps.SeriesId AND s.PlaylistId = ps.PlaylistId
WHERE ps.SeriesId IS NULL;

COMMIT;

DROP TABLE Snapshot;
