START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    DocumentaryEpisodeId SMALLINT NOT NULL
    ,DocumentaryId SMALLINT NOT NULL
    ,Title VARCHAR(150) NOT NULL
    ,EpisodeNumber TINYINT NOT NULL
    ,FileLocation VARCHAR(260) NOT NULL
);

INSERT INTO Snapshot
(
    DocumentaryEpisodeId
    ,DocumentaryId
    ,Title
    ,EpisodeNumber
    ,FileLocation
)
VALUES
(1, 2, 'Born Again Christians', 1, 'V:\\Documentaries\\Weird Weekends - Born Again Christians.mp4')
,(2, 2, 'UFOs', 2, 'V:\\Documentaries\\Weird Weekends - UFOs.mp4')
,(3, 2, 'Porn', 3, 'V:\\Documentaries\\Weird Weekends - Porn.mp4')
,(4, 2, 'Survivalists', 4, 'V:\\Documentaries\\Weird Weekends - Survivalists.mp4')
,(5, 2, 'Weird Christmas', 5, 'V:\\Documentaries\\Weird Weekends - Weird Christmas.mp4')
,(6, 2, 'Infomercials', 6, 'V:\\Documentaries\\Weird Weekends - Infomercials.mp4')
,(7, 2, 'Swingers', 7, 'V:\\Documentaries\\Weird Weekends - Swingers.mp4')
,(8, 2, 'Black Nationalism', 8, 'V:\\Documentaries\\Weird Weekends - Black Nationalism.mp4')
,(9, 2, 'Demolition Derby', 9, 'V:\\Documentaries\\Weird Weekends - Demolition Derby.mp4')
,(10, 2, 'Off-Off Broadway', 10, 'V:\\Documentaries\\Weird Weekends - Off-Off Broadway.mp4')
,(11, 2, 'Wrestling', 11, 'V:\\Documentaries\\Weird Weekends - Wrestling.mp4')
,(12, 2, 'Self-Fulfilment', 12, 'V:\\Documentaries\\Weird Weekends - Self-Fulfilment.mp4')
,(13, 2, 'Indian Gurus', 13, 'V:\\Documentaries\\Weird Weekends - Indian Gurus.mp4')
,(14, 2, 'South Africa', 14, 'V:\\Documentaries\\Weird Weekends - South Africa.mp4')
,(15, 2, 'Body Building', 15, 'V:\\Documentaries\\Weird Weekends - Body Building.mp4')
,(16, 2, 'Looking for Love', 16, 'V:\\Documentaries\\Weird Weekends - Looking for Love.mp4')
,(17, 2, 'Gangsta Rap', 17, 'V:\\Documentaries\\Weird Weekends - Gangsta Rap.mp4')
;

UPDATE TestMop.DocumentaryEpisode de
  INNER JOIN Snapshot s
    ON de.DocumentaryEpisodeId = s.DocumentaryEpisodeId
SET
  de.DocumentaryEpisodeId = s.DocumentaryEpisodeId
  ,de.DocumentaryId = s.DocumentaryId
  ,de.Title = s.Title
  ,de.EpisodeNumber = s.EpisodeNumber
  ,de.FileLocation = s.FileLocation;

INSERT INTO TestMop.DocumentaryEpisode (DocumentaryEpisodeId, DocumentaryId, Title, EpisodeNumber, FileLocation)
SELECT
    s.DocumentaryEpisodeId
    ,s.DocumentaryId
    ,s.Title
    ,s.EpisodeNumber
    ,s.FileLocation
FROM
    Snapshot s
    LEFT JOIN TestMop.DocumentaryEpisode de
        ON s.DocumentaryEpisodeId = de.DocumentaryEpisodeId
WHERE de.DocumentaryEpisodeId IS NULL;

COMMIT;

DROP TABLE Snapshot;
