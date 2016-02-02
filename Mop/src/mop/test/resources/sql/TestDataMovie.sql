START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

CREATE TEMPORARY TABLE Snapshot
(
    MovieId SMALLINT NOT NULL
    ,Title VARCHAR(150) NOT NULL
    ,Year YEAR NOT NULL
    ,Director VARCHAR(50) NOT NULL
    ,Length SMALLINT NOT NULL
    ,Description VARCHAR(1000) NOT NULL
    ,ImageLocation VARCHAR(260) NOT NULL
    ,FileLocation VARCHAR(260) NOT NULL
    ,IsHighDefinition BINARY(1) NOT NULL
);

INSERT INTO Snapshot
(
    MovieId
    ,Title
    ,Year
    ,Director
    ,Length
    ,Description
    ,ImageLocation
    ,FileLocation
    ,IsHighDefinition
)
VALUES
(1, '10 Things I Hate About You', '1999', 'Gil Junger', 97, 'A new kid must find a guy to date the meanest girl in school, the older sister of the girl he has a crush on, who cannot date until her older sister does.', 'V:\\Movies\\10 Things I Hate About You.mp4', 'V:\\Images\\10 Things I Hate About You.jpg', 0)
,(2, '40 Days and 40 Nights', '2002', 'Michael Lehmann', 96, 'After a brutal break-up, a young man vows to stay celibate during the forty days of Lent, but finds the girl of his dreams and is unable to do anything about it.', 'V:\\Movies\\40 Days and 40 Nights.mp4', 'V:\\Images\\40 Days and 40 Nights.jpg', 0)
,(3, 'Alien', '1979', 'Ridley Scott', 117, 'The commercial vessel Nostromo receives a distress call from an unexplored planet. After searching for survivors, the crew heads home only to realize that a deadly bioform has joined them.', 'V:\\Movies\\Alien.mp4', 'V:\\Images\\Alien.jpg', 0)
,(4, 'Amelie', '2001', 'Jean-Pierre Jeunet', 122, 'Amelie is an innocent and naive girl in Paris with her own sense of justice. She decides to help those around her and, along the way, discovers love.', 'V:\\Movies\\Amelie.mp4', 'V:\\Images\\Amelie.jpg', 0)
,(5, 'American History X', '1998', 'Tony Kaye', 119, 'A former neo-nazi skinhead tries to prevent his younger brother from going down the same wrong path that he did.', 'V:\\Movies\\American History X.mp4', 'V:\\Images\\American History X.jpg', 0)
,(6, 'American Pie', '1999', 'Paul Weitz', 95, 'Four teenage boys enter a pact to lose their virginity by prom night.', 'V:\\Movies\\American Pie.mp4', 'V:\\Images\\American Pie.jpg', 0)
,(7, 'American Pie 2', '2001', 'J.B.Rogers', 108, 'The continuing bawdy adventures of a group of friends reuniting after their first year of college.', 'V:\\Movies\\American Pie 2.mp4', 'V:\\Images\\American Pie 2.jpg', 0)
,(8, 'American Psycho', '2000', 'Mary Harron', 102, 'A wealthy New York investment banking executive hides his alternate psychopathic ego from his co-workers and friends as he escalates deeper into his illogical, gratuitous fantasies.', 'V:\\Movies\\American Psycho.mp4', 'V:\\Images\\American Psycho.jpg', 0)
,(9, 'Apocalypse Now - Redux', '1979', 'Francis Ford Coppola', 196, 'During the Vietnam War, Captain Willard is sent on a dangerous mission into Cambodia to assassinate a renegade colonel who has set himself up as a god among a local tribe.', 'V:\\Movies\\Apocalypse Now.mp4', 'V:\\Images\\Apocalypse Now.jpg', 0)
,(10, 'Apollo 13', '1995', 'Ron Howard', 140, 'NASA must devise a strategy to return Apollo 13 to Earth safely after the spacecraft undergoes massive internal damage putting the lives of the three astronauts on board in jeopardy.', 'V:\\Movies\\Apollo 13.mp4', 'V:\\Images\\Apollo 13.jpg', 0)
,(11, 'Are We Officially Dating', '2014', 'Tom Gormican', 94, 'Three best friends find themselves where we''ve all been - at that confusing moment in every dating relationship when you have to decide "So...where is this going?"', 'V:\\Movies\\Are We Officially Dating.mp4', 'V:\\Images\\Are We Officially Dating.jpg', 0)
,(12, 'Chocolat', '2000', 'Lasse Hallström', 121, 'A woman and her daughter open a chocolate shop in a small French village that shakes up the rigid morality of the community.', 'V:\\Movies\\Chocolat.mp4', 'V:\\Images\\Chocolat.jpg', 0)
,(13, 'Cool Runnings', '1993', 'Jon Turteltaub', 98, 'When a Jamaican sprinter is disqualified to the Olympic Games, he enlists the help of a dishonored coach to start the first Jamaican Bobsled Team.', 'V:\\Movies\\Cool Runnings.mp4', 'V:\\Images\\Cool Runnings.jpg', 0)
,(14, 'Cruel Intentions', '1999', 'Roger Kumble', 97, 'Kathryn makes a bet that her step-brother, Sebastian, won''t be able to bed Annette (a virgin, who wants to wait until love). If he loses, Kathryn gets his Jaguar, if he wins, he gets Kathryn.', 'V:\\Movies\\Cruel Intentions.mp4', 'V:\\Images\\Cruel Intentions.jpg', 0)
,(15, 'Dante''s Peak', '1997', 'Roger Donaldson', 108, 'A vulcanologist arrives at a countryside town named Dante''s Peak after a long dormant volcano, which has recently been named the second most desirable place to live in America, and discovers that Dante''s Peak, may wake up at any moment.', 'V:\\Movies\\Dante''s Peak.mp4', 'V:\\Images\\Dante''s Peak.jpg', 0)
,(16, 'Donnie Darko', '2001', 'Richard Kelly', 113, 'A troubled teenager is plagued by visions of a large bunny rabbit that manipulates him to commit a series of crimes, after narrowly escaping a bizarre accident.', 'V:\\Movies\\Donnie Darko.mp4', 'V:\\Images\\Donnie Darko.jpg', 0)
,(17, 'Double Team', '1997', 'Hark Tsui', 93, 'Counter-terrorist Jack Quinn misses his target, Stavros, on the eve of his final mission. From there, he is sent to "The Colony", a rebirth for presumed-dead assassins. He breaks free from there, and seeks the aid of Yaz, a weapons dealer, for his final battle with Stavros.', 'V:\\Movies\\Double Team.mp4', 'V:\\Images\\Double Team.jpg', 0)
,(18, 'Flight', '2012', 'Robert Zemeckis', 138, 'An airline pilot saves almost all his passengers on his malfunctioning airliner which eventually crashed, but an investigation into the accident reveals something troubling.', 'V:\\Movies\\Flight.mp4', 'V:\\Images\\Flight.jpg', 0)
,(19, 'Frozen', '2013', 'Chris Buck, Jennifer Lee', 102, 'When the newly crowned Queen Elsa accidentally uses her power to turn things into ice to curse her home in infinite winter, her sister, Anna, teams up with a mountain man, his playful reindeer, and a snowman to change the weather condition.', 'V:\\Movies\\Frozen.mp4', 'V:\\Images\\Frozen.jpg', 0)
,(20, 'Full Metal Jacket', '1987', 'Stanley Kubrick', 116, 'A pragmatic U.S. Marine observes the dehumanizing effects the U.S.-Vietnam War has on his fellow recruits from their brutal boot camp training to the bloody street fighting in Hue.', 'V:\\Movies\\Full Metal Jacket.mp4', 'V:\\Images\\Full Metal Jacket.jpg', 0)
,(21, 'Gladiator', '2000', 'Ridley Scott', 155, 'When a Roman general is betrayed and his family murdered by an emperor''s corrupt son, he comes to Rome as a gladiator to seek revenge.', 'V:\\Movies\\Gladiator.mp4', 'V:\\Images\\Gladiator.jpg', 0)
,(22, 'Gravity', '2013', 'Alfonso Cuarón', 91, 'A medical engineer and an astronaut work together to survive after a catastrophe destroys their shuttle and leaves them adrift in orbit.', 'V:\\Movies\\Gravity.mp4', 'V:\\Images\\Gravity.jpg', 1)
,(23, 'James Bond - Goldeneye', '1995', 'Martin Campbell', 130, 'James Bond teams up with the lone survivor of a destroyed Russian research center to stop the hijacking of a nuclear space weapon by a fellow agent believed to be dead.', 'V:\\Movies\\James Bond - Goldeneye.mp4', 'V:\\Images\\James Bond - Goldeneye.jpg', 0)
,(24, 'James Bond - Skyfall', '2012', 'Sam Mendes', 143, 'Bond''s loyalty to M is tested when her past comes back to haunt her. Whilst MI6 comes under attack, 007 must track down and destroy the threat, no matter how personal the cost.', 'V:\\Movies\\James Bond - Skyfall.mp4', 'V:\\Images\\James Bond - Skyfall.jpg', 0)
,(25, 'Jarhead', '2005', 'Sam Mendes', 125, 'Based on former Marine Anthony Swofford''s best-selling 2003 book about his pre-Desert Storm experiences in Saudi Arabia and about his experiences fighting in Kuwait.', 'V:\\Movies\\Jarhead.mp4', 'V:\\Images\\Jarhead.jpg', 0)
,(26, 'Jerry MaGuire', '1996', 'Cameron Crowe', 139, 'When a sports agent has a moral epiphany and is fired for expressing it, he decides to put his new philosophy to the test as an independent with the only athlete who stays with him.', 'V:\\Movies\\Jerry MaGuire.mp4', 'V:\\Images\\Jerry MaGuire.jpg', 0)
,(27, 'Madagascar', '2005', 'Eric Darnell, Tom McGrath', 86, 'Spoiled by their upbringing with no idea what wild life is really like, four animals from New York Central Zoo escape, unwittingly assisted by four absconding penguins, and find themselves in Madagascar, among a bunch of merry lemurs.', 'V:\\Movies\\Madagascar.mp4', 'V:\\Images\\Madagascar.jpg', 0)
,(28, 'Magnolia', '1999', 'Paul Thomas Anderson', 188, 'An epic mosaic of interrelated characters in search of love, forgiveness, and meaning in the San Fernando Valley.', 'V:\\Movies\\Magnolia.mp4', 'V:\\Images\\Magnolia.jpg', 0)
,(29, 'Not Another Teen Movie', '2001', 'Joel Gallen', 89, 'A sendup of all the teen movies that have accumulated in the past two decades.', 'V:\\Movies\\Not Another Teen Movie.mp4', 'V:\\Images\\Not Another Teen Movie.jpg', 0)
,(30, 'Oblivion', '2013', 'Joseph Kosinski', 124, 'A veteran assigned to extract Earth''s remaining resources begins to question what he knows about his mission and himself.', 'V:\\Movies\\Oblivion.mp4', 'V:\\Images\\Oblivion.jpg', 0)
,(31, 'Prometheus', '2012', 'Ridley Scott', 124, 'Following clues to the origin of mankind a team journey across the universe and find a structure on a distant planet containing a monolithic statue of a humanoid head and stone cylinders of alien blood but they soon find they are not alone.', 'V:\\Movies\\Prometheus.mp4', 'V:\\Images\\Prometheus.jpg', 0)
,(32, 'The Beach', '2000', 'Danny Boyle', 119, 'Twenty-something Richard travels to Thailand and finds himself in possession of a strange map. Rumours state that it leads to a solitary beach paradise, a tropical bliss - excited and intrigued, he sets out to find it.', 'V:\\Movies\\The Beach.mp4', 'V:\\Images\\The Beach.jpg', 0)
,(33, 'The Bourne Identity', '2002', 'Doug Liman', 119, 'A man is picked up by a fishing boat, bullet-riddled and suffering from amnesia, before racing to elude assassins and regain his memory.', 'V:\\Movies\\The Bourne Identity.mp4', 'V:\\Images\\The Bourne Identity.jpg', 0)
,(34, 'The Bourne Supremacy', '2004', 'Paul Greengrass', 108, 'When Jason Bourne is framed for a CIA operation gone awry, he is forced to resume his former life as a trained assassin to survive.', 'V:\\Movies\\The Bourne Supremacy.mp4', 'V:\\Images\\The Bourne Supremacy.jpg', 0)
,(35, 'The Bourne Ultimatum', '2007', 'Paul Greengrass', 115, 'Jason Bourne dodges a ruthless CIA official and his agents from a new assassination program while searching for the origins of his life as a trained killer.', 'V:\\Movies\\The Bourne Ultimatum.mp4', 'V:\\Images\\The Bourne Ultimatum.jpg', 0)
,(36, 'Toy Story', '1995', 'John Lasseter', 81, 'A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy''s room.', 'V:\\Movies\\Toy Story.mp4', 'V:\\Images\\Toy Story.jpg', 0)
,(37, 'Trainspotting', '1996', 'Danny Boyle', 94, 'Renton, deeply immersed in the Edinburgh drug scene, tries to clean up and get out, despite the allure of the drugs and influence of friends.', 'V:\\Movies\\Trainspotting.mp4', 'V:\\Images\\Trainspotting.jpg', 0)
,(38, 'Tron - Legacy', '2010', 'Joseph Kosinski', 125, 'The son of a virtual world designer goes looking for his father and ends up inside the digital world that his father designed. He meets his father''s corrupted creation and a unique ally who was born inside the digital world.', 'V:\\Movies\\Tron - Legacy.mp4', 'V:\\Images\\Tron - Legacy.jpg', 0)
,(39, 'Up In the Air', '2009', 'Jason Reitman', 109, 'With a job traveling around the country firing people, Ryan Bingham enjoys his life living out of a suitcase, but finds that lifestyle threatened by the presence of a new hire and a potential love interest.', 'V:\\Movies\\Up In the Air.mp4', 'V:\\Images\\Up In the Air.jpg', 0)
,(40, 'Wreck-It Ralph', '2012', 'Rich Moore', 101, 'A video game villain wants to be a hero and sets out to fulfill his dream, but his quest brings havoc to the whole arcade where he lives.', 'V:\\Movies\\Wreck it Ralph.mp4', 'V:\\Images\\Wreck it Ralph.jpg', 0)
;

UPDATE TestMop.Movie m
    INNER JOIN Snapshot s
        ON m.MovieId = s.MovieId
SET
    m.Title = s.Title
    ,m.Year = s.Year
    ,m.Director = s.Director
    ,m.Length = s.Length
    ,m.Description = s.Description
    ,m.ImageLocation = s.ImageLocation
    ,m.FileLocation = s.FileLocation
    ,m.IsHighDefinition = s.IsHighDefinition;

INSERT INTO TestMop.Movie (MovieId, Title, Year, Director, Length, Description, ImageLocation, FileLocation, IsHighDefinition)
SELECT
    s.MovieId
    ,s.Title
    ,s.Year
    ,s.Director
    ,s.Length
    ,s.Description
    ,s.ImageLocation
    ,s.FileLocation
    ,s.IsHighDefinition
FROM
    Snapshot s
    LEFT JOIN TestMop.Movie m
        ON s.MovieId = m.MovieId
WHERE m.MovieId IS NULL;

COMMIT;

DROP TABLE Snapshot;
