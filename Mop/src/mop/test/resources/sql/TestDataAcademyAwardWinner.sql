START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    AcademyAwardWinnerId TINYINT NOT NULL
    ,MovieId SMALLINT NOT NULL
);

INSERT INTO Snapshot
(
    AcademyAwardWinnerId
    ,MovieId
)
VALUES
(1, 21)
;

INSERT INTO TestMop.AcademyAwardWinner (AcademyAwardWinnerId, MovieId)
SELECT
    s.AcademyAwardWinnerId
    ,s.MovieId
FROM
    Snapshot s
    LEFT JOIN TestMop.AcademyAwardWinner aaw
        ON s.AcademyAwardWinnerId = aaw.AcademyAwardWinnerId
WHERE aaw.AcademyAwardWinnerId IS NULL;

COMMIT;

DROP TABLE Snapshot;
