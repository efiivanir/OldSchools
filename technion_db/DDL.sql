DROP DATABASE IF EXISTS match_db;
CREATE DATABASE match_db;
USE match_db;

CREATE TABLE city (
name VARCHAR(20) PRIMARY KEY
);

CREATE TABLE relationType
(
name VARCHAR(20) PRIMARY KEY
);

CREATE TABLE users
(
mail VARCHAR(50) PRIMARY KEY CHECK(mail LIKE '_%@_%._%'),
firstName VARCHAR(20) NOT NULL,
surName VARCHAR(20) NOT NULL,
birthDate date CHECK(datediff(birthDate,NOW())/365 >= 18),
gender CHAR(1) CHECK(gender = 'F' OR gender = 'M' OR gender = 'N'),
description VARCHAR(255),
cityName VARCHAR(20) REFERENCES city(name)
);

CREATE TABLE userRelation
(
userEmail VARCHAR(50) REFERENCES users(mail),
relationName VARCHAR(20) NOT NULL REFERENCES relationtype(name),
gender CHAR(1) NOT NULL CHECK(gender IN ('M','F','N')),
PRIMARY KEY (userEmail,relationName)
);

CREATE TABLE Picture
(
serialNum INT AUTO_INCREMENT PRIMARY KEY,
path VARCHAR(255) NOT NULL,
userEmail VARCHAR(50) NOT NULL REFERENCES Users(mail)
)
AUTO_INCREMENT = 3;
 
CREATE TABLE matchMePic
(
-- picNum INT REFERENCES Picture(serialNum) PRIMARY KEY
   picNum INT PRIMARY KEY,
   picDate date NOT NULL,
   description VARCHAR(255)
);

CREATE TABLE Application
(
app_name VARCHAR(20) PRIMARY KEY,
description VARCHAR(255) NOT NULL,
creationDate date NOT NULL CHECK(creationDate <= NOW())
);

CREATE TABLE RegisterdTo
(
userMail VARCHAR(50) REFERENCES Users(mail),
appName VARCHAR(20) REFERENCES Application(name),
registrationDate date,
nickName VARCHAR(20),
PRIMARY KEY(usermail,appName),
UNIQUE(appName,nickName)
);

CREATE TABLE List
(
userEmail1 VARCHAR(50) REFERENCES Users(mail),
userEmail2 VARCHAR(50),
appName VARCHAR(20),
addDate date NOT NULL,
talkDate date,
FOREIGN KEY (userEmail2,appName) REFERENCES RegisterdTo(usermail,appName),
PRIMARY KEY(userEmail1,userEmail2,appName),
CHECK(userEmail1 <> userEmail2)
);

CREATE TABLE DoesntLike
(
userEmail1 VARCHAR(50) REFERENCES Users(mail),
userEmail2 VARCHAR(50) REFERENCES Users(mail),
PRIMARY KEY(userEmail1,userEmail2),
CHECK(userEmail1 != userEmail2)
);

CREATE TABLE Question
(
appName VARCHAR(20) REFERENCES Application(name) ON DELETE CASCADE,
serialQuestion int,
content VARCHAR(255) NOT NULL,
mandatory bit NOT NULL,
ans1 VARCHAR(50) NOT NULL,
ans2 VARCHAR(50) NOT NULL,
ans3 VARCHAR(50) NOT NULL,
ans4 VARCHAR(50) NOT NULL,
PRIMARY KEY(appName,serialQuestion)
);

CREATE TABLE Answers
(
userEmail VARCHAR(50) REFERENCES Users(mail),
appName VARCHAR(20),
questionSerial int,
ansDate date NOT NULL,
ans CHAR(1) NOT NULL CHECK(ans IN ('1','2','3','4')),
FOREIGN KEY(appName,questionSerial) REFERENCES Question(appName,serialQuestion),
PRIMARY KEY(userEmail,appName,questionSerial)
);

INSERT INTO city(name) VALUES
("Ashdod"),
("Ashkelon"),
("Bat Yam"),
("Beersheba"),
("Bnei Brak"),
("Haifa"),
("Holon"),
("Jerusalem"),
("Netanya"),
("Petah Tiqwa"),
("Ramat Gan"),
("Rehovot"),
("Tel Aviv");
SELECT * FROM city;

INSERT INTO relationType(name) VALUES
("Serius"),
("NOT Serius"),
("Friend");
SELECT * FROM relationType;

INSERT INTO users(mail,firstName,surName,birthDate,gender,description,cityName) VALUES
('sharon@gmail.com','Sharon','Drifus',CAST('1971-01-01' AS Date),'F','Psichology','Rehovot'),
('yuval@gmail.com','Yuval','Hanan',CAST('1966-04-22' AS Date),'M','Accounter','Ashkelon'),
('danak@gmail.com','Dana','Kursh',CAST('1973-04-22' AS Date),'F','Ambasador','Jerusalem'),
('avi@gmail.com','Avi','Cohen',CAST('1980-01-01' AS Date),'M','Good guy','Rehovot');

SELECT * FROM users;

INSERT INTO userRelation(userEmail,relationName,gender) VALUES
('sharon@gmail.com','Serius','F'),
('yuval@gmail.com','Not Serius','M'),
('danak@gmail.com','Friend','F'),
('avi@gmail.com','Serius','M');
SELECT * FROM userRelation;

INSERT INTO Picture(path,userEmail) VALUES
('/root/sharon@gmail.com','sharon@gmail.com'),
('/root/yuval@gmail.com','yuval@gmail.com'),
('/root/danak@gmail.com','danak@gmail.com'),
('/root/avi@gmail.com','avi@gmail.com');
SELECT * FROM Picture;

INSERT INTO Application(app_name,description,creationDate) VALUES
('Koopidon','koop',CAST('2010-09-19' AS Date)),
('Tree','tr',CAST('2017-10-19' AS Date)),
('Amaaaa','am',CAST('2011-10-19' AS Date));
SELECT * FROM Application;


INSERT INTO RegisterdTo(userMail,appName,registrationDate,nickName)VALUES
('sharon@gmail.com','Koopidon',CAST('2018-09-19' AS Date),'shar'),
('avi@gmail.com','Koopidon',CAST('2012-09-19' AS Date),'avi'),
('yuval@gmail.com','Tree',CAST('2012-09-19' AS Date),'yuv'),
('sharon@gmail.com','Tree',CAST('2018-09-19' AS Date),'shar'),
('danak@gmail.com','Koopidon',CAST('2012-09-19' AS Date),'dana'),
('avi@gmail.com','Amaaaa',CAST('2012-09-19' AS Date),'avi'),
('sharon@gmail.com','Amaaaa',CAST('2018-09-19' AS Date),'shar');
SELECT * FROM RegisterdTo;

INSERT INTO List(userEmail1,userEmail2,appName,addDate,talkDate)VALUES
('avi@gmail.com','sharon@gmail.com','Amaaaa', CAST('2018-03-09' AS Date), CAST('2018-03-12' AS Date)),
('sharon@gmail.com','avi@gmail.com','Koopidon', CAST('2018-01-01' AS Date), NULL),
('sharon@gmail.com','yuval@gmail.com','Tree', CAST('2018-01-01' AS Date), CAST('2018-02-02' AS Date));
SELECT * FROM List;

INSERT INTO DoesntLike(userEmail1,userEmail2)VALUES
('yuval@gmail.com', 'sharon@gmail.com'),
('sharon@gmail.com', 'avi@gmail.com');
SELECT * FROM DoesntLike;

INSERT INTO Question(appName,serialQuestion,content,mandatory,ans1,ans2,ans3,ans4)VALUES
('Koopidon', 1, 'aaaa', 1, 'asdf', 'a', 'b', 'c'),
('Koopidon', 2, 'bbb', 1, 'qwer', 'bla', 'bl', 'agh'),
('Amaaaa', 1, 'asdf', 1, 'aaaaaa', 'asdfr', 'gdgdgdg', 'hdgdt'),
('Amaaaa', 2, 'aser', 0, 'awer', 'opiu', 'gfhy', 'sder'),
('Tree', 1, 'asde', 1, '12345', 'sssssss', 'dfrt', 'hgtf');
SELECT * FROM Question;

INSERT INTO Answers(userEmail,appName,questionSerial,ansDate,ans)VALUES
('avi@gmail.com', 'Koopidon', 1, NOW(), '2'),
('avi@gmail.com', 'Amaaaa', 2, NOW(), '2'),
('sharon@gmail.com', 'Koopidon', 1, NOW(), '2');
SELECT * FROM Answers;

SELECT * FROM RegisterdTo WHERE datediff(registrationDate,NOW())/365 <= 1;

-- INSERT [dbo].[matchMePic] VALUES (3, CAST(N'2017-01-01' AS Date), NULL)
-- INSERT [dbo].[matchMePic] VALUES (53, CAST(N'2018-09-18' AS Date), NULL)


-- Get application if number of registers at 2017 is smaller that at 2018
-- SELECT A.name, A.description,datadiff(A.creationDate,NOW())
-- SELECT FROM RegisterdTo RT INNER JOIN
--        Application A on A.name = RT.appName
-- WHERE year(RT.registrationDate) = 2018
-- GROUP BY A.name,A.description,datediff(A.creationDate,NOW())/365
-- HAVING count(*) >=
--        (SELECT count(*)
--        FROM RegisterdTo RT1
--        WHERE year(RT1.registrationDate) = 2017 AND RT1.appName = A.name)
       

-- Derived TABLE
-- ---------------
-- SELECT
-- FROM Application A INNER JOIN
--      (
--         SELECT appName,count(*) as count2018
--                FROM RegisterdTo
--                WHERE YEAR(registrationDate) = 2018
--                GROUP BY appName) appsIn2018 ON A.name = appsIn2018.appName
--                WHERE count2018 >= count2017

-- create view
-- temp


-- One way   
--
-- CREATE trigger checkLove ON List AFTER INSERT AS


 
declare @mail1 varchar(50);
-- declare @mail2 varchar(50);


-- SELECT @mail1 = I.userEmail1,@mail2 = I.userEmail2
-- FROM inserted AS I

-- IF 2 <= (SELECT count(*)
--         FROM List L
--         where L.userEmail1 = @mail1 and L.userEmail2 = @mail2)
--         rollback
-- )

-- Second way
-- CREATE trigger checkLove1 ON List AFTER INSERT, UPDATE AS

-- declare @mail1 varchar(50),
--         @mail2 varchar(50)
-- SELECT @mail1 = I.userEmail1      ,@mail2 = I.userEmail2
-- FROM inserted AS I

-- IF exists (SELECT count(*)
--         FROM List L
--         where L.userEmail1 = @mail1 and L.userEmail2 = @mail2
--         group by L.userEmail1
--         having count(*) >=2)
--         rollback
-- )

-- INSERT INTO List(userEmail1,userEmail2,appName,addDate,talkDate)VALUES
-- ('avi@gmail.com','sharon@gmail.com','Amaaaa', CAST('2018-03-09' AS Date), CAST('2018-03-12' AS Date));
