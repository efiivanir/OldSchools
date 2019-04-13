set global sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
set session sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

DROP DATABASE IF EXISTS cityGardens;
CREATE DATABASE IF NOT EXISTS cityGardens;
USE cityGardens;

CREATE TABLE Areas (
       id TINYINT NOT NULL,
       name VARCHAR(20) NOT NULL,
       manager_id CHAR(9) REFERENCES Stuff(id),
       PRIMARY KEY ( id )
);

CREATE TABLE Garden (
       id TINYINT NOT NULL,
       name VARCHAR(20) NOT NULL,
       adress VARCHAR(20) NOT NULL,
       area_id TINYINT NOT NULL REFERENCES Areas(id),
       manager_id CHAR(9) REFERENCES Stuff(id),
       assist_1_id CHAR(9) REFERENCES Stuff(id),
       assist_2_id CHAR(9) REFERENCES Stuff(id),
       assist_3_id CHAR(9) REFERENCES Stuff(id),
       
       PRIMARY KEY ( id )
);



CREATE TABLE Stuff (
   id VARCHAR(9) NOT NULL,
   first_name VARCHAR(20) NOT NULL,
   last_name  VARCHAR(20) NOT NULL,
   adress VARCHAR(20) NOT NULL,
   city VARCHAR(20) NOT NULL,
   phone VARCHAR(10) NOT NULL,
   birth_year VARCHAR(4) NOT NULL,
   start_year VARCHAR(4) NOT NULL,
   added_hours VARCHAR(4) NOT NULL,
   PRIMARY KEY ( id )
);

CREATE TABLE Child (
   id VARCHAR(9) NOT NULL,
   first_name VARCHAR(20) NOT NULL,
   last_name  VARCHAR(20) NOT NULL,
   adress VARCHAR(20) NOT NULL,
   area_id TINYINT NOT NULL REFERENCES Areas(id),
   phone VARCHAR(10) NOT NULL,
   birth_year VARCHAR(4) NOT NULL,
   start_year VARCHAR(4) NOT NULL,

   
 
   garden_id TINYINT REFERENCES Garden(id),
   PRIMARY KEY ( id )
);

-- Stuff 
INSERT INTO Stuff VALUES ("022397103","Efi","Ivanir","Moshe Dayan 5/8","Bat Yam","0547884102",1966,2017,10);
INSERT INTO Stuff VALUES ("022397102","Dana","Ivanir","Moshe Dayan 5/8","Bat Yam","0547884102",1966,2017,20);

-- Areas 
INSERT INTO Areas VALUES (1,"K.Amal","022397103");
INSERT INTO Areas VALUES (2,"K.Haroshet","022397103");
INSERT INTO Areas VALUES (3,"Alroi","022397103");
INSERT INTO Areas VALUES (4,"Hagefen","022397102");

-- Gardens
INSERT  INTO Garden VALUES (1,"Shikma","aaaa",1,"NA","NA","NA","NA");

-- Child
INSERT INTO Child VALUES ("922397102","Peleg","Ivanir","Moshe Dayan 5/8",1,"0547884102","2000","2016",1);
INSERT INTO Child VALUES ("922117102","Moshe","Ivanir","aaaaa 5/8",3,"0547811102","2002",year(curdate()),1);



