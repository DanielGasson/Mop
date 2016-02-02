START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    MovieId SMALLINT NOT NULL
    ,GenreId TINYINT NOT NULL
);

INSERT INTO Snapshot
(
    MovieId
    ,GenreId
)
VALUES
(1, 3), (1, 5), (1, 10)
,(2, 3), (2, 10)
,(3, 8), (3, 11)
,(4, 3), (4, 10)
,(5, 4), (5, 5)
,(6, 3), (6, 10)
,(7, 3), (7, 10)
,(8, 4), (8, 5)
,(9, 5), (9, 13)
,(10, 2), (10, 5)
,(11, 3), (11, 10)
,(12, 5), (12, 10)
,(13, 2), (13, 3), (13, 6)
,(14, 5), (14, 10)
,(15, 1), (12, 2), (15, 12)
,(16, 5), (16, 11), (16, 15)
,(17, 1)
,(18, 5)
,(19, 2), (19, 3), (19, 6)
,(20, 5), (20, 13)
,(21, 1), (21, 5)
,(22, 11), (22, 12)
,(23, 1)
,(24, 1)
,(25, 5), (25, 13)
,(26, 3), (26, 5), (26, 10)
,(27, 2), (27, 3), (27, 6)
,(28, 5)
,(29, 3)
,(30, 1), (30, 2), (30, 15)
,(31, 8), (31, 11)
,(32, 2), (32, 5)
,(33, 1), (33, 12), (33, 15)
,(34, 1), (34, 12), (34, 15)
,(35, 1), (35, 12), (35, 15)
,(36, 2), (36, 3), (36, 6)
,(37, 5)
,(38, 1), (38, 2), (38, 11)
,(39, 5), (39, 10)
,(40, 2), (40, 3), (40, 6)
;

INSERT INTO TestMop.MovieGenre (MovieId, GenreId)
SELECT
    s.MovieId
    ,s.GenreId
FROM
    Snapshot s
    LEFT JOIN TestMop.MovieGenre mg
        ON s.MovieId = mg.MovieId AND s.GenreId = mg.GenreId
WHERE mg.MovieId IS NULL;

COMMIT;

DROP TABLE Snapshot;
