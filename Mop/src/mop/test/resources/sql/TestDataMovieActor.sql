START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    MovieId SMALLINT NOT NULL
    ,ActorId SMALLINT NOT NULL
);

INSERT INTO Snapshot
(
    MovieId
    ,ActorId
)
VALUES
(1, 10), (1, 15), (1, 16)
,(22, 1), (22, 2)
,(33, 6), (33, 7), (33, 8), (33, 9), (33, 10), (33, 11)
,(34, 6), (34, 7), (34, 9), (34, 10), (34, 12), (34, 13)
,(35, 6), (35, 10), (35, 13), (35, 14)
,(39, 1), (39, 3), (39, 4), (39, 5)
;

INSERT INTO TestMop.MovieActor (MovieId, ActorId)
SELECT
    s.MovieId
    ,s.ActorId
FROM
  Snapshot s
  LEFT JOIN TestMop.MovieActor ma
    ON s.MovieId = ma.MovieId AND s.ActorId = ma.ActorId
WHERE ma.MovieId IS NULL;

COMMIT;

DROP TABLE Snapshot;
