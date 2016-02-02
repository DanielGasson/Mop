START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    EpisodeId SMALLINT NOT NULL
    ,SeasonId SMALLINT NOT NULL
    ,Title VARCHAR(150) NOT NULL
    ,EpisodeNumber TINYINT NOT NULL
    ,FileLocation VARCHAR(260) NOT NULL
);

INSERT INTO Snapshot
(
    EpisodeId
    ,SeasonId
    ,Title
    ,EpisodeNumber
    ,FileLocation
)
VALUES
(1, 1, 'Ben Stiller', 1, 'V:\\TV\\Extras\\Season 1\\Ben Stiller.mp4')
,(2, 1, 'Ross Kemp and Vinnie Jones', 2, 'V:\\TV\\Extras\\Season 1\\Ross Kemp and Vinnie Jones.mp4')
,(3, 1, 'Kate Winslet', 3, 'V:\\TV\\Extras\\Season 1\\Kate Winslet.mp4')
,(4, 1, 'Les Dennis', 4, 'V:\\TV\\Extras\\Season 1\\Les Dennis.mp4')
,(5, 1, 'Samuel L. Jackson', 5, 'V:\\TV\\Extras\\Season 1\\Samuel L. Jackson.mp4')
,(6, 1, 'Patrick Stewart', 6, 'V:\\TV\\Extras\\Season 1\\Patrick Stewart.mp4')
,(7, 2, 'Orlando Bloom', 1, 'V:\\TV\\Extras\\Season 2\\Orlando Bloom.mp4')
,(8, 2, 'David Bowie', 2, 'V:\\TV\\Extras\\Season 2\\David Bowie.mp4')
,(9, 2, 'Daniel Radcliffe', 3, 'V:\\TV\\Extras\\Season 2\\Daniel Radcliffe.mp4')
,(10, 2, 'Chris Martin', 4, 'V:\\TV\\Extras\\Season 2\\Chris Martin.mp4')
,(11, 2, 'Sir Ian McKellen', 5, 'V:\\TV\\Extras\\Season 2\\Sir Ian McKellen.mp4')
,(12, 2, 'Jonathan Ross', 6, 'V:\\TV\\Extras\\Season 2\\Jonathan Ross.mp4')
,(13, 3, 'Gods of Dangerous Financial Instruments', 1, 'V:\\TV\\House of Lies\\Season 1\\Gods of Dangerous Financial Instruments.mp4')
,(14, 3, 'Amsterdam', 2, 'V:\\TV\\House of Lies\\Season 1\\Amsterdam.mp4')
,(15, 3, 'Microphallus', 3, 'V:\\TV\\House of Lies\\Season 1\\Microphallus.mp4')
,(16, 3, 'Mini-Mogul', 4, 'V:\\TV\\House of Lies\\Season 1\\Mini-Mogul.mp4')
,(17, 3, 'Utah', 5, 'V:\\TV\\House of Lies\\Season 1\\Utah.mp4')
,(18, 3, 'Our Descent Into Los Angeles', 6, 'V:\\TV\\House of Lies\\Season 1\\Our Descent Into Los Angeles.mp4')
,(19, 3, 'Bareback Town', 7, 'V:\\TV\\House of Lies\\Season 1\\Bareback Town.mp4')
,(20, 3, 'Veritas', 8, 'V:\\TV\\House of Lies\\Season 1\\Veritas.mp4')
,(21, 3, 'Ouroboros', 9, 'V:\\TV\\House of Lies\\Season 1\\Ouroboros.mp4')
,(22, 3, 'Prologue and Aftermath', 10, 'V:\\TV\\House of Lies\\Season 1\\Prologue and Aftermath.mp4')
,(23, 3, 'Business', 11, 'V:\\TV\\House of Lies\\Season 1\\Business.mp4')
,(24, 3, 'The Mayan Apocalypse', 12, 'V:\\TV\\House of Lies\\Season 1\\The Mayan Apocalypse.mp4')
,(25, 4, 'Stochasticity', 1, 'V:\\TV\\House of Lies\\Season 2\\Stochasticity.mp4')
,(26, 4, 'When Dinosaurs Ruled the Planet', 2, 'V:\\TV\\House of Lies\\Season 2\\When Dinosaurs Ruled the Planet.mp4')
,(27, 4, 'Man–date', 3, 'V:\\TV\\House of Lies\\Season 2\\Man–date.mp4')
,(28, 4, 'Damonschildren.org', 4, 'V:\\TV\\House of Lies\\Season 2\\Damonschildren.org.mp4')
,(29, 4, 'Sincerity is an Easy Disguise in This Business', 5, 'V:\\TV\\House of Lies\\Season 2\\Sincerity is an Easy Disguise in This Business.mp4')
,(30, 4, 'Family Values', 6, 'V:\\TV\\House of Lies\\Season 2\\Family Values.mp4')
,(31, 4, 'The Runner Stumbles', 7, 'V:\\TV\\House of Lies\\Season 2\\The Runner Stumbles.mp4')
,(32, 4, 'Wonders of the World', 8, 'V:\\TV\\House of Lies\\Season 2\\Wonders of the World.mp4')
,(33, 4, 'Liability', 9, 'V:\\TV\\House of Lies\\Season 2\\Liability.mp4')
,(34, 4, 'Exit Strategy', 10, 'V:\\TV\\House of Lies\\Season 2\\Exit Strategy.mp4')
,(35, 4, 'Hostile Takeover', 11, 'V:\\TV\\House of Lies\\Season 2\\Hostile Takeover.mp4')
,(36, 4, 'Til Death Do Us Part', 12, 'V:\\TV\\House of Lies\\Season 2\\Til Death Do Us Part.mp4')
;

UPDATE TestMop.SeasonEpisode se
  INNER JOIN Snapshot s
    ON se.EpisodeId = s.EpisodeId
SET
  se.SeasonId = s.SeasonId
  ,se.Title = s.Title
  ,se.EpisodeNumber = s.EpisodeNumber
  ,se.FileLocation = s.FileLocation;

INSERT INTO TestMop.SeasonEpisode (EpisodeId, SeasonId, Title, EpisodeNumber, FileLocation)
SELECT
    s.EpisodeId
    ,s.SeasonId
    ,s.Title
    ,s.EpisodeNumber
    ,s.FileLocation
FROM
    Snapshot s
    LEFT JOIN TestMop.SeasonEpisode se
        ON s.EpisodeId = se.EpisodeId
WHERE se.EpisodeId IS NULL;

COMMIT;

DROP TABLE Snapshot;
