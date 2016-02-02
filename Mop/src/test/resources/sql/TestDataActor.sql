START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    ActorId SMALLINT NOT NULL
    ,Name VARCHAR(100) NOT NULL
);

INSERT INTO Snapshot
(
    ActorId
    ,Name
)
VALUES
(1, 'George Clooney')
,(2, 'Sandra Bullock')
,(3, 'Vera Farmiga')
,(4, 'Anna Kendrick')
,(5, 'Jason Bateman')
,(6, 'Matt Damon')
,(7, 'Franka Potente')
,(8, 'Clive Owen')
,(9, 'Brian Cox')
,(10, 'Julia Stiles')
,(11, 'Chris Cooper')
,(12, 'Karl Urban')
,(13, 'Joan Allen')
,(14, 'David Strathairn')
,(15, 'Heath Ledger')
,(16, 'Joseph Gordon-Levitt')
;

UPDATE TestMop.Actor a
  INNER JOIN Snapshot s
    ON a.ActorId = s.ActorId
SET
  a.Name = s.Name;

INSERT INTO TestMop.Actor (ActorId, Name)
SELECT
    s.ActorId
    ,s.Name
FROM
    Snapshot s
    LEFT JOIN TestMop.Actor a
        ON s.ActorId = a.ActorId
WHERE a.ActorId IS NULL;

COMMIT;

DROP TABLE Snapshot;
