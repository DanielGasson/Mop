START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    PlaylistId TINYINT NOT NULL
    ,Name VARCHAR(50) NOT NULL
);

INSERT INTO Snapshot
(
    PlaylistId
    ,Name
)
VALUES
(1, 'Main')
,(2, 'Secondary')
;

UPDATE TestMop.Playlist p
  INNER JOIN Snapshot s
    ON p.PlaylistId = s.PlaylistId
SET
  p.Name = s.Name;

INSERT INTO TestMop.Playlist (PlaylistId, Name)
SELECT 
    s.PlaylistId
    ,s.Name
FROM
  Snapshot s
  LEFT JOIN TestMop.Playlist p
    ON s.PlaylistId = p.PlaylistId
WHERE p.PlaylistId IS NULL;

COMMIT;

DROP TABLE Snapshot;
