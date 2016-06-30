CREATE USER super_admin@localhost identified BY 'q0w1e2r3t4y5';
CREATE DATABASE IF NOT EXISTS social_web;
GRANT ALL privileges ON social_web.* TO super_admin@localhost;
USE social_web;

-- profile for security
CREATE TABLE social_web.user_profile(
  id INT (11) NOT NULL AUTO_INCREMENT,
  username VARCHAR (30) NOT NULL,
  `password` VARCHAR (30) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX username USING BTREE (username)
)
;

-- user information 
CREATE TABLE social_web.user_data(
  user_id INT (11) NOT NULL,
  user_name VARCHAR (30) NOT NULL,
  user_surname VARCHAR (30) NOT NULL,
  user_birth DATE DEFAULT NULL,
  user_hobby VARCHAR (30) DEFAULT NULL,
  user_phone VARCHAR (30) DEFAULT NULL,
  hometown VARCHAR (30) DEFAULT NULL,
  profile_link VARCHAR (30) DEFAULT NULL,
  user_photo VARCHAR (255) DEFAULT NULL,
  PRIMARY KEY (user_id),
  UNIQUE INDEX profile_link USING BTREE (profile_link)
)
;

-- email
CREATE TABLE social_web.email(
  email_id INT (11) NOT NULL AUTO_INCREMENT,
  user_id INT (11) NOT NULL,
  email VARCHAR (255) NOT NULL,
  PRIMARY KEY (email_id),
  INDEX emailUser_dataFK USING BTREE (user_id),
  CONSTRAINT emailUser_dataFK FOREIGN KEY (user_id)
  REFERENCES social_web.user_data (user_id) ON DELETE CASCADE ON UPDATE CASCADE
)
;

-- secuity role
CREATE TABLE social_web.security_role(
  id INT (11) NOT NULL AUTO_INCREMENT,
  user_role VARCHAR (30) NOT NULL,
  PRIMARY KEY (id)
)
;

-- users and roles
CREATE TABLE social_web.user_role(
  id_user INT (11) NOT NULL,
  id_role INT (11) NOT NULL,
  PRIMARY KEY (id_user, id_role),
  INDEX user_role_FK1 USING BTREE (id_role),
  CONSTRAINT user_role_FK1 FOREIGN KEY (id_role)
  REFERENCES social_web.security_role (id),
  CONSTRAINT user_role_FK2 FOREIGN KEY (id_user)
  REFERENCES social_web.user_profile (id)
);

CREATE TABLE social_web.chat(
  chat_id INT (11) NOT NULL AUTO_INCREMENT,
  first_person INT (11) NOT NULL,
  second_person INT (11) NOT NULL,
  PRIMARY KEY (chat_id),
  INDEX chat_FK1 USING BTREE (first_person),
  INDEX chat_FK2 USING BTREE (second_person),
  CONSTRAINT chat_FK2 FOREIGN KEY (second_person)
  REFERENCES social_web.user_data (user_id),
  CONSTRAINT chat_FK1 FOREIGN KEY (first_person)
  REFERENCES social_web.user_data (user_id)
);

ALTER TABLE chat DROP last_update;
ALTER TABLE message ADD message_text text NOT NULL;
ALTER TABLE message CHARACTER SET utf8;
ALTER TABLE message DROP message_text;

CREATE TABLE social_web.message(
  message_id INT (11) NOT NULL AUTO_INCREMENT,
  sender INT (11) NOT NULL,
  chat_id INT (11) NOT NULL,
  message_date BIGINT (20) NOT NULL,
  message_text VARCHAR (500) NOT NULL,
  PRIMARY KEY (message_id),
  INDEX message_FK1 USING BTREE (chat_id),
  INDEX message_FK2 USING BTREE (sender),
  CONSTRAINT message_FK2 FOREIGN KEY (sender)
  REFERENCES social_web.user_data (user_id),
  CONSTRAINT message_FK1 FOREIGN KEY (chat_id)
  REFERENCES social_web.chat (chat_id)
);

#DROP TABLE friends;
CREATE TABLE friends(
  friend_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id int NOT NULL,
  user_friend_id int NOT NULL,  
  friend_status int NOT NULL
  );

ALTER TABLE friends ADD CONSTRAINT friendsUser_DataFK
  FOREIGN KEY( user_id ) REFERENCES user_data (user_id)
  ON UPDATE CASCADE ON DELETE CASCADE; 

ALTER TABLE friends ADD CONSTRAINT friendsUser_DataFK2
  FOREIGN KEY( user_friend_id ) REFERENCES user_data (user_id)
  ON UPDATE CASCADE ON DELETE CASCADE;


#Insert user_data
INSERT INTO social_web.user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('иван', 'иванов', '1993-07-11', 'рыба, блесна', '8927......2', 1, 'Самара', 'ivanS', '1395081559408.jpg');
INSERT INTO social_web.user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('Михаил', 'Костик', '1982-10-20', '', '', 2, 'Эх', 'superStar', NULL);
INSERT INTO social_web.user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('иван', 'иванов', '2014-02-10', '', '', 3, '', '3', NULL);
INSERT INTO social_web.user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('Ми', 'иванов', '2000-03-17', ' ', ' ', 4, ' ', '4', NULL);
INSERT INTO social_web.user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('Александр', 'Златоверов', '1993-03-05', ' ', ' ', 5, ' ', '5', NULL);
INSERT INTO social_web.user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('никита', 'никитин', '1986-03-14', '', '', 6, '', '6', '1394174779606.jpg');
INSERT INTO social_web.user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('Mairl', 'Coco', NULL, NULL, NULL, 7, NULL, '7', NULL);
INSERT INTO social_web.user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('Bart', 'Simpson', NULL, NULL, NULL, 8, NULL, '8', NULL);
INSERT INTO social_web.user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('Name', 'Surname', NULL, NULL, NULL, 9, NULL, '9', NULL);

#insert friends
INSERT INTO social_web.friends(friend_id, user_id, user_friend_id, friend_status) VALUES
(4, 5, 1, 2);
INSERT INTO social_web.friends(friend_id, user_id, user_friend_id, friend_status) VALUES
(5, 2, 1, 2);
INSERT INTO social_web.friends(friend_id, user_id, user_friend_id, friend_status) VALUES
(6, 9, 1, 2);

#email
INSERT INTO social_web.email(email_id, user_id, email) VALUES
(10, 5, 'zlatoverov@mail.ru');
INSERT INTO social_web.email(email_id, user_id, email) VALUES
(16, 1, 'alzlatoverov@yandex.ru');
INSERT INTO social_web.email(email_id, user_id, email) VALUES
(22, 2, 'kosti@yandex.ru');
INSERT INTO social_web.email(email_id, user_id, email) VALUES
(23, 6, 'nenaviju1C@gmail.com');
INSERT INTO social_web.email(email_id, user_id, email) VALUES
(24, 7, '1@mail.ru');
INSERT INTO social_web.email(email_id, user_id, email) VALUES
(25, 8, '2@mail.com');
INSERT INTO social_web.email(email_id, user_id, email) VALUES
(26, 9, '3@mail.ru');
INSERT INTO social_web.email(email_id, user_id, email) VALUES
(27, 1, 'zlatoverov@gmail.com');

#user_profile
INSERT INTO social_web.user_profile(username, password, id) VALUES
('user', '1234', 1);
INSERT INTO social_web.user_profile(username, password, id) VALUES
('alzlatoverov@mail.ru', '1111', 2);
INSERT INTO social_web.user_profile(username, password, id) VALUES
('alzlatoverov1@mail.ru', '1234', 3);
INSERT INTO social_web.user_profile(username, password, id) VALUES
('alzlatoverov@mail.rus', '1234', 4);
INSERT INTO social_web.user_profile(username, password, id) VALUES
('zlatoverov@mail.ru', 'qwerty', 5);
INSERT INTO social_web.user_profile(username, password, id) VALUES
('nenaviju1C@gmail.com', '0987', 6);
INSERT INTO social_web.user_profile(username, password, id) VALUES
('1@mail.ru', '1234', 7);
INSERT INTO social_web.user_profile(username, password, id) VALUES
('2@mail.com', '1234', 8);
INSERT INTO social_web.user_profile(username, password, id) VALUES
('3@mail.ru', '1234', 9);

#insert security_role
INSERT INTO security_role (user_role) VALUES ('ROLE_USER');
INSERT INTO security_role (user_role) VALUES ('ROLE_ADMIN');

#insert user_role
INSERT INTO social_web.user_role(id_user, id_role) VALUES
(1, 1);
INSERT INTO social_web.user_role(id_user, id_role) VALUES
(2, 1);
INSERT INTO social_web.user_role(id_user, id_role) VALUES
(3, 1);
INSERT INTO social_web.user_role(id_user, id_role) VALUES
(4, 1);
INSERT INTO social_web.user_role(id_user, id_role) VALUES
(5, 1);
INSERT INTO social_web.user_role(id_user, id_role) VALUES
(6, 1);
INSERT INTO social_web.user_role(id_user, id_role) VALUES
(7, 1);
INSERT INTO social_web.user_role(id_user, id_role) VALUES
(8, 1);
INSERT INTO social_web.user_role(id_user, id_role) VALUES
(9, 1);
