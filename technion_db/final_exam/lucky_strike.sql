set global sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
set session sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

DROP DATABASE IF EXISTS lucky_strike;
CREATE DATABASE IF NOT EXISTS lucky_strike;
USE lucky_strike;

CREATE TABLE Dealer (
       worker_id INT AUTO_INCREMENT PRIMARY KEY,
       full_name VARCHAR(50) NOT NULL,
       birth_date DATE NOT NULL,
       work_start_date DATE NOT NULL,    
       id CHAR(9) NOT NULL
)
AUTO_INCREMENT = 1000;

CREATE TABLE GameType (
       name VARCHAR(20) PRIMARY KEY,
       description VARCHAR(20) NOT NULL,
       minimumPlayers TINYINT NOT NULL,
       maximumPlayers TINYINT NOT NULL
);

CREATE TABLE Game (
       game_type_name VARCHAR(20) NOT NULL REFERENCES GameType(name),
       game_start_time DATETIME NOT NULL,
       game_end_time DATETIME NOT NULL,
       dealer_in_game INT NOT NULL REFERENCES Dealer(worker_id),
       PRIMARY KEY(game_type_name,game_start_time)
);

CREATE TABLE Player (
       id CHAR(9) UNIQUE PRIMARY KEY,
       first_name VARCHAR(20) NOT NULL,
       sur_name VARCHAR(20) NOT NULL,
       city VARCHAR(20) NOT NULL,
       birth_date DATE NOT NULL,
       preferred VARCHAR(20) REFERENCES GameType(name)
 );

CREATE TABLE PlayerInAGame (
       player_id CHAR(9) REFERENCES Player(id),
       game_type_name VARCHAR(20) REFERENCES GameType(name),
       game_date_time DATETIME NOT NULL,
       bet_amount INT NOT NULL CHECK(bet_amount > 0),
       win_los_amount INT NOT NULL,
       CHECK(win_los_amount >= bet_amount),
       FOREIGN KEY(game_type_name,game_date_time) REFERENCES Game(game_type_name,game_start_time),
       PRIMARY KEY(player_id,game_type_name,game_date_time)
);

CREATE TABLE PlayerDealerRelation (
       player_id CHAR(9) NOT NULL,
       dealer_id INT NOT NULL,
       relation VARCHAR(9) CHECK(relation IN ('parent','grand','brother','child','nephew','uncle'))
);

SOURCE triggers.sql;

-- Insert data
LOAD DATA LOCAL INFILE 'Dealer.csv' INTO TABLE Dealer FIELDS TERMINATED BY ',';
SELECT * FROM Dealer;

LOAD DATA LOCAL INFILE 'GameType.csv' INTO TABLE GameType FIELDS TERMINATED BY ',';
SELECT * FROM GameType;

LOAD DATA LOCAL INFILE 'Game.csv' INTO TABLE Game FIELDS TERMINATED BY ',';
SELECT * FROM Game;

LOAD DATA LOCAL INFILE 'Player.csv' INTO TABLE Player FIELDS TERMINATED BY ',';
SELECT * FROM Player;

LOAD DATA LOCAL INFILE 'PlayerInGame.csv' INTO TABLE PlayerInAGame FIELDS TERMINATED BY ',';
SELECT * FROM PlayerInAGame ORDER BY game_date_time,game_type_name;

LOAD DATA LOCAL INFILE 'PlayerDealerRelation.csv' INTO TABLE PlayerDealerRelation FIELDS TERMINATED BY ',';
SELECT * FROM PlayerDealerRelation;


SELECT 'Qouries' AS '';
-- -------------------------------------------------------------------------------------------------------------------------------------------------------
SELECT 'Q01: Gmaes with more then 10 players and at least 5 players win' AS '';

DROP TABLE IF EXISTS tmp1;
CREATE TEMPORARY TABLE tmp1
SELECT  game_type_name,game_date_time FROM
(SELECT *
  FROM PlayerInAGame
  HAVING win_los_amount > 0) AS win
  GROUP BY game_type_name,game_date_time
  HAVING COUNT(*) > 5;

DROP TABLE IF EXISTS tmp2;
CREATE TEMPORARY TABLE tmp2
SELECT  game_type_name,game_date_time FROM
(SELECT *
  FROM PlayerInAGame) AS all_player
GROUP BY game_type_name,game_date_time
HAVING COUNT(*) > 10;

SELECT DISTINCT  game_type_name,game_date_time FROM tmp1
       INNER JOIN tmp2 USING(game_type_name,game_date_time);

DROP TABLE tmp1;
DROP TABLE tmp2;
-- ------------------------------------------------------------------------------------------------------------------------------------------------
SELECT 'Q02: Gmaes that total bet > 10000' AS '';
SELECT game_type_name,game_date_time,SUM(bet_amount)
       FROM PlayerInAGame GROUP BY game_type_name,game_date_time
       HAVING SUM(bet_amount) > 10000   
       ORDER BY game_date_time;

-- -------------------------------------------------------------------------------------------------------------------------------------------------------
SELECT 'Q03: For each player, How many games types he play' AS '';
DROP TABLE IF EXISTS tmp1;
CREATE TEMPORARY TABLE tmp1
SELECT player_id,game_type_name,COUNT(*) 
       FROM PlayerInAGame
       GROUP BY player_id,game_type_name;

SELECT player_id,CONCAT(Player.sur_name,' ',Player.first_name) AS 'Full Name',COUNT(*) AS 'Num of Game types'
       FROM tmp1 INNER JOIN Player ON tmp1.player_id = Player.id
       GROUP BY player_id
       ORDER BY COUNT(*) DESC ;    
DROP TABLE tmp1; 

-- -------------------------------------------------------------------------------------------------------------------------------------------------------
SELECT 'Q04: Quant of players that are not between min & max players num' AS '';
DROP TABLE IF EXISTS tmp1;
CREATE TEMPORARY TABLE tmp1
SELECT  game_type_name,game_date_time ,COUNT(*) AS 'Count'
        FROM PlayerInAGame 
        GROUP BY  game_type_name,game_date_time;

SELECT game_type_name,game_date_time,Count
       FROM tmp1 INNER JOIN GameType ON tmp1.game_type_name = GameType.name
       WHERE  Count < GameType.minimumPlayers OR Count > GameType.maximumPlayers;
DROP TABLE tmp1;

-- -------------------------------------------------------------------------------------------------------------------------------------------------------
SELECT 'Q05: Games popularity' AS '';
DROP TABLE IF EXISTS tmp1;
CREATE TEMPORARY TABLE tmp1
SELECT  game_type_name,game_date_time ,COUNT(*) AS 'Count',DATEDIFF(NOW(),game_date_time) AS 'Period'
FROM PlayerInAGame 
     GROUP BY game_type_name,game_date_time
     HAVING DATEDIFF(NOW(),game_date_time) BETWEEN 0 AND 150
     ORDER BY game_date_time;


DROP TABLE IF EXISTS tmp2;
CREATE TEMPORARY TABLE tmp2
SELECT game_type_name,SUM(Count) AS SM
       FROM tmp1
       GROUP BY game_type_name;

SELECT * FROM tmp2;

ALTER TABLE tmp2
ADD COLUMN POP VARCHAR(15) AFTER SM ;

UPDATE tmp2
SET POP = 'Not Popular'
WHERE SM <= 500;

UPDATE tmp2
SET POP = 'Half Popular'
WHERE SM BETWEEN 501 AND 1000;


UPDATE tmp2
SET POP = 'Popular'
WHERE SM >= 1001;

ALTER TABLE tmp2
DROP column SM;

SELECT * FROM tmp2;
DROP TABLE tmp1;
DROP TABLE tmp2;

-- -------------------------------------------------------------------------------------------------------------------------------------------------------
SELECT 'Q06: Dealers Ids which manage the Casino first games' AS '';
-- SELECT * FROM Game;
SELECT  game_type_name,game_start_time,dealer_in_game
        FROM Game
        GROUP BY game_type_name
        ORDER BY game_start_time;

-- -------------------------------------------------------------------------------------------------------------------------------------------------------
SELECT 'Q07: Gamers which get the most gain' AS '';
DROP TABLE IF EXISTS tmp1;
CREATE TEMPORARY TABLE tmp1
SELECT  player_id,game_date_time,win_los_amount FROM PlayerInAGame
        WHERE DATEDIFF(NOW(),game_date_time) BETWEEN 0 AND 90
        ORDER BY player_id;
SELECT * FROM tmp1;

SELECT  player_id,SUM(win_los_amount) AS 'Total' FROM tmp1
        GROUP BY player_id
        ORDER BY Total;
DROP TABLE tmp1;

-- -------------------------------------------------------------------------------------------------------------------------------------------------------
SELECT 'Q08: Cheating players which have family dealers' AS '';
DROP TABLE IF EXISTS tmp1;
CREATE TEMPORARY TABLE tmp1
SELECT game_type_name,game_start_time AS 'game_date_time',dealer_in_game FROM Game;
SELECT * FROM tmp1;

DROP TABLE IF EXISTS tmp2;
CREATE TEMPORARY TABLE tmp2
SELECT player_id,dealer_in_game,win_los_amount,relation FROM PlayerInAGame       
       INNER JOIN tmp1 USING(game_type_name,game_date_time)
       INNER JOIN PlayerDealerRelation USING(player_id)
       ORDER BY player_id,dealer_in_game;
SELECT * FROM tmp2;

DROP TABLE tmp1;
DROP TABLE tmp2;

-- -------------------------------------------------------------------------------------------------------------------------------------------------------
SELECT 'Q10: Proc that return favorite games per id' AS '';

-- favorite_games_q10 ---------------------------------------------
DELIMITER $$
CREATE PROCEDURE fn_favorite_games_q10()
BEGIN

END$$
DELIMITER ;


