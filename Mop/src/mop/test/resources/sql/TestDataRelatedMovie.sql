START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    RelatedMovieId SMALLINT NOT NULL
    ,MovieId SMALLINT NOT NULL
    ,Relation SMALLINT NOT NULL
);

INSERT INTO Snapshot
(
    RelatedMovieId
    ,MovieId
    ,Relation
)
VALUES
(1, 6, 7)
,(2, 7, 6)
,(3, 23, 24)
,(4, 24, 23)
,(5, 33, 34)
,(6, 33, 35)
,(7, 34, 33)
,(8, 34, 35)
,(9, 35, 33)
,(10, 35, 34)
;

INSERT INTO TestMop.RelatedMovie (RelatedMovieId, MovieId, Relation)
SELECT 
    s.RelatedMovieId
    ,s.MovieId
    ,s.Relation
FROM
  Snapshot s
  LEFT JOIN TestMop.RelatedMovie rm
    ON s.RelatedMovieId = rm.RelatedMovieId
WHERE rm.RelatedMovieId IS NULL;

COMMIT;

DROP TABLE Snapshot;
