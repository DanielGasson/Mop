START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    GenreId TINYINT NOT NULL
    ,Name VARCHAR(25) NOT NULL
);

INSERT INTO Snapshot
(
    GenreId
    ,Name
)
VALUES
(1, 'Action')
,(2, 'Adventure')
,(3, 'Comedy')
,(4, 'Crime')
,(5, 'Drama')
,(6, 'Family')
,(7, 'Fantasy')
,(8, 'Horror')
,(9, 'Musical')
,(10, 'Romance')
,(11, 'Science Fiction')
,(12, 'Thriller')
,(13, 'War')
,(14, 'Western')
,(15, 'Mystery')
;

UPDATE TestMop.Genre g
  INNER JOIN Snapshot s
    ON g.GenreId = s.GenreId
SET
  g.Name = s.Name;

INSERT INTO TestMop.Genre (GenreId, Name)
SELECT
    s.GenreId
    ,s.Name
FROM
    Snapshot s
    LEFT JOIN TestMop.Genre g
        ON s.GenreId = g.GenreId
WHERE g.GenreId IS NULL;

COMMIT;

DROP TABLE Snapshot;
