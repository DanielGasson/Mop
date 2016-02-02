START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    PlaylistId TINYINT NOT NULL
    ,MovieId SMALLINT NOT NULL
);

INSERT INTO Snapshot
(
    PlaylistId
    ,MovieId
)
VALUES
(1, 1)
,(1, 2)
,(1, 3)
,(1, 4)
,(1, 5)
,(1, 6)
,(1, 7)
,(1, 8)
,(1, 9)
,(1, 10)
,(1, 11)
,(1, 12)
,(1, 13)
,(1, 14)
,(1, 15)
,(1, 16)
,(1, 17)
,(1, 18)
,(1, 19)
,(1, 20)
,(1, 21)
,(1, 22)
,(1, 23)
,(1, 24)
,(1, 25)
,(1, 26)
,(1, 27)
,(1, 28)
,(1, 29)
,(1, 30)
,(1, 31)
,(1, 32)
,(1, 33)
,(1, 34)
,(1, 35)
,(1, 36)
,(2, 37)
,(2, 38)
,(2, 39)
,(2, 40)
;

INSERT INTO TestMop.PlaylistMovie (PlaylistId, MovieId)
SELECT 
    s.PlaylistId
    ,s.MovieId
FROM
  Snapshot s
  LEFT JOIN TestMop.PlaylistMovie pm
    ON s.MovieId = pm.MovieId AND s.PlaylistId = pm.PlaylistId
WHERE pm.MovieId IS NULL;

COMMIT;

DROP TABLE Snapshot;
