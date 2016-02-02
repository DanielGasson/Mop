START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    PlaylistId TINYINT NOT NULL
    ,DocumentaryId SMALLINT NOT NULL
);

INSERT INTO Snapshot
(
    PlaylistId
    ,DocumentaryId
)
VALUES
(1, 1)
,(1, 2)
;

INSERT INTO TestMop.PlaylistDocumentary (PlaylistId, DocumentaryId)
SELECT
    s.PlaylistId
    ,s.DocumentaryId
FROM
  Snapshot s
  LEFT JOIN TestMop.PlaylistDocumentary pd
    ON s.DocumentaryId = pd.DocumentaryId AND s.PlaylistId = pd.PlaylistId
WHERE pd.DocumentaryId IS NULL;

COMMIT;

DROP TABLE Snapshot;
