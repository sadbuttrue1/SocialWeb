-- CREATE USER super_admin@localhost identified BY 'q0w1e2r3t4y5';
-- CREATE DATABASE IF NOT EXISTS social_web;
-- GRANT ALL privileges ON * TO super_admin@localhost;
-- USE social_web;
/*!!!!!Были удалены некоторые индексы.!!!!!*/
-- profile for security
CREATE TABLE user_profile(
  id INT (11) NOT NULL AUTO_INCREMENT,
  username VARCHAR (30) NOT NULL,
  `password` VARCHAR (30) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX username (username)
)
;

-- user information 
CREATE TABLE user_data(
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
  UNIQUE INDEX profile_link (profile_link)
)
;

-- email
CREATE TABLE email(
  email_id INT (11) NOT NULL AUTO_INCREMENT,
  user_id INT (11) NOT NULL,
  email VARCHAR (255) NOT NULL,
  PRIMARY KEY (email_id),  
  FOREIGN KEY (user_id)
  REFERENCES user_data (user_id) ON DELETE CASCADE ON UPDATE CASCADE
)
;

-- secuity role
CREATE TABLE security_role(
  id INT (11) NOT NULL AUTO_INCREMENT,
  user_role VARCHAR (30) NOT NULL,
  PRIMARY KEY (id)
)
;

-- users and roles
CREATE TABLE user_role(
  id_user INT (11) NOT NULL,
  id_role INT (11) NOT NULL,
  PRIMARY KEY (id_user, id_role),  
  FOREIGN KEY (id_role)
  REFERENCES security_role (id),
  FOREIGN KEY (id_user)
  REFERENCES user_profile (id)
);

CREATE TABLE chat(
  chat_id INT (11) NOT NULL AUTO_INCREMENT,
  first_person INT (11) NOT NULL,
  second_person INT (11) NOT NULL,
  PRIMARY KEY (chat_id),  
  FOREIGN KEY (second_person)
  REFERENCES user_data (user_id),
  FOREIGN KEY (first_person)
  REFERENCES user_data (user_id)
);


CREATE TABLE message(
  message_id INT (11) NOT NULL AUTO_INCREMENT,
  sender INT (11) NOT NULL,
  chat_id INT (11) NOT NULL,
  message_date BIGINT (20) NOT NULL,
  message_text VARCHAR (500) NOT NULL,
  PRIMARY KEY (message_id),  
  FOREIGN KEY (sender)
  REFERENCES user_data (user_id),
  FOREIGN KEY (chat_id)
  REFERENCES chat (chat_id)
);


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


INSERT INTO user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('aaaa', 'aaaaaa', '1993-07-11', 'aaaa, aaaaaa', '8927......2', 1, 'aaaaaa', 'ivanS', '1395081559408.jpg');
INSERT INTO user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('aaaaaa', 'aaaaaa', '1982-10-20', '', '', 2, 'aa', 'superStar', NULL);
INSERT INTO user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('aaaa', 'aaaaaa', '2014-02-10', '', '', 3, '', '3', NULL);
INSERT INTO user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('aa', 'aaaaaa', '2000-03-17', ' ', ' ', 4, ' ', '4', NULL);
INSERT INTO user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('aaaaaaaaa', 'aaaaaaaaaa', '1993-03-05', ' ', ' ', 5, ' ', '5', NULL);
INSERT INTO user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('aaaaaa', 'aaaaaaa', '1986-03-14', '', '', 6, '', '6', '1394174779606.jpg');
INSERT INTO user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('Mairl', 'Coco', NULL, NULL, NULL, 7, NULL, '7', NULL);
INSERT INTO user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('Bart', 'Simpson', NULL, NULL, NULL, 8, NULL, '8', NULL);
INSERT INTO user_data(user_name, user_surname, user_birth, user_hobby, user_phone, user_id, hometown, profile_link, user_photo) VALUES
('Name', 'Surname', NULL, NULL, NULL, 9, NULL, '9', NULL);


INSERT INTO friends(friend_id, user_id, user_friend_id, friend_status) VALUES
(4, 5, 1, 2);
INSERT INTO friends(friend_id, user_id, user_friend_id, friend_status) VALUES
(5, 2, 1, 2);
INSERT INTO friends(friend_id, user_id, user_friend_id, friend_status) VALUES
(6, 9, 1, 2);


INSERT INTO email(email_id, user_id, email) VALUES
(10, 5, 'zlatoverov@mail.ru');
INSERT INTO email(email_id, user_id, email) VALUES
(16, 1, 'alzlatoverov@yandex.ru');
INSERT INTO email(email_id, user_id, email) VALUES
(22, 2, 'kosti@yandex.ru');
INSERT INTO email(email_id, user_id, email) VALUES
(23, 6, 'nenaviju1C@gmail.com');
INSERT INTO email(email_id, user_id, email) VALUES
(24, 7, '1@mail.ru');
INSERT INTO email(email_id, user_id, email) VALUES
(25, 8, '2@mail.com');
INSERT INTO email(email_id, user_id, email) VALUES
(26, 9, '3@mail.ru');
INSERT INTO email(email_id, user_id, email) VALUES
(27, 1, 'zlatoverov@gmail.com');


INSERT INTO user_profile(username, password, id) VALUES
('user', '1234', 1);
INSERT INTO user_profile(username, password, id) VALUES
('alzlatoverov@mail.ru', '1111', 2);
INSERT INTO user_profile(username, password, id) VALUES
('alzlatoverov1@mail.ru', '1234', 3);
INSERT INTO user_profile(username, password, id) VALUES
('alzlatoverov@mail.rus', '1234', 4);
INSERT INTO user_profile(username, password, id) VALUES
('zlatoverov@mail.ru', 'qwerty', 5);
INSERT INTO user_profile(username, password, id) VALUES
('nenaviju1C@gmail.com', '0987', 6);
INSERT INTO user_profile(username, password, id) VALUES
('1@mail.ru', '1234', 7);
INSERT INTO user_profile(username, password, id) VALUES
('2@mail.com', '1234', 8);
INSERT INTO user_profile(username, password, id) VALUES
('3@mail.ru', '1234', 9);


INSERT INTO security_role (user_role) VALUES ('ROLE_USER');
INSERT INTO security_role (user_role) VALUES ('ROLE_ADMIN');


INSERT INTO user_role(id_user, id_role) VALUES
(1, 1);
INSERT INTO user_role(id_user, id_role) VALUES
(2, 1);
INSERT INTO user_role(id_user, id_role) VALUES
(3, 1);
INSERT INTO user_role(id_user, id_role) VALUES
(4, 1);
INSERT INTO user_role(id_user, id_role) VALUES
(5, 1);
INSERT INTO user_role(id_user, id_role) VALUES
(6, 1);
INSERT INTO user_role(id_user, id_role) VALUES
(7, 1);
INSERT INTO user_role(id_user, id_role) VALUES
(8, 1);
INSERT INTO user_role(id_user, id_role) VALUES
(9, 1);
