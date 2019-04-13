--                        triggers 

-- Check that dealer age is more than 24

DELIMITER $$
CREATE PROCEDURE check_dealer_age (IN birth_date DATE, IN work_start_date DATE)
BEGIN
    IF TIMESTAMPDIFF(YEAR, birth_date,work_start_date) <= 24 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Dealer age is less than 24';
    END IF;

END$$
DELIMITER ;

-- before insert
DELIMITER $$
CREATE TRIGGER dealer_age_before_insert BEFORE INSERT ON Dealer
FOR EACH ROW
BEGIN
    CALL check_dealer_age(NEW.birth_date,NEW.work_start_date);
END$$   
DELIMITER ;

-- before update
DELIMITER $$
CREATE TRIGGER dealer_age_before_update BEFORE UPDATE ON Dealer
FOR EACH ROW
BEGIN
    CALL check_dealer_age(NEW.birth_date,NEW.work_start_date);
END$$  
DELIMITER ;


-- trigger chk_game_ply ---------------------------------------------
DELIMITER $$
CREATE PROCEDURE fn_chk_game_ply(IN min_ply TINYINT,IN max_ply TINYINT)
       BEGIN
       IF min_ply <= 0 THEN
          SIGNAL SQLSTATE '45001'
          SET MESSAGE_TEXT = 'Num. of min players at GameType <= 0';
       END IF;
       
       IF max_ply <= 0 THEN
          SIGNAL SQLSTATE '45002'
          SET MESSAGE_TEXT = 'Num. of max players at GameType <= 0';
       END IF;

       IF min_ply > max_ply  THEN
          SIGNAL SQLSTATE '45003'
          SET MESSAGE_TEXT = 'Num. of min players larger tham max players at GameType';
       END IF;
       

END$$
DELIMITER ;

-- before insert
DELIMITER $$
CREATE TRIGGER tg_chk_game_ply_before_insert BEFORE INSERT ON GameType
FOR EACH ROW
    BEGIN
        CALL fn_chk_game_ply(NEW.minimumPlayers,NEW.maximumPlayers);
     END$$   
DELIMITER ;

-- before update
DELIMITER $$
CREATE TRIGGER tg_chk_game_ply_before_update BEFORE UPDATE ON GameType
FOR EACH ROW
    BEGIN
        CALL fn_chk_game_ply(NEW.minimumPlayers,NEW.maximumPlayers);
    END$$  
DELIMITER ;

select 'Check That dealer age >= 24 trigger' AS '';
-- trigger chk_player_age ---------------------------------------------
DELIMITER $$
CREATE PROCEDURE fn_chk_player_age(IN birth_date DATE)
       BEGIN
          IF TIMESTAMPDIFF(YEAR, birth_date,NOW()) <= 18 THEN
              SIGNAL SQLSTATE '45004'
              SET MESSAGE_TEXT = 'Player age is less than 18';
          END IF;
       END$$
DELIMITER ;

-- before insert
DELIMITER $$
CREATE TRIGGER tg_chk_player_age_before_insert BEFORE INSERT ON Player
FOR EACH ROW
    BEGIN
        CALL fn_chk_player_age(NEW.birth_date);
    END$$   
DELIMITER ;

-- before update
DELIMITER $$
CREATE TRIGGER tg_chk_player_age_before_update BEFORE UPDATE ON Player
FOR EACH ROW
    BEGIN
        CALL fn_chk_player_age(NEW.birth_date);
    END$$  
DELIMITER ;

select 'Check That player age >= 18 trigger' AS '';

-- trigger check_max_players_q09 ---------------------------------------------
DELIMITER $$
CREATE PROCEDURE fn_check_max_players_q09()
BEGIN

END$$
DELIMITER ;

-- before insert
DELIMITER $$
CREATE TRIGGER tg_check_max_players_q09_before_insert BEFORE INSERT ON 
FOR EACH ROW
BEGIN
CALL fn_check_max_players_q09();
END$$   
DELIMITER ;

-- before update
DELIMITER $$
CREATE TRIGGER tg_check_max_players_q09_before_update BEFORE UPDATE ON 
FOR EACH ROW
BEGIN
CALL fn_check_max_players_q09();
END$$  
DELIMITER ;

