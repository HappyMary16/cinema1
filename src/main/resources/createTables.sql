DROP TABLE film_actor;
DROP TABLE film_country;
DROP TABLE film_director;
DROP TABLE film_genre;
DROP TABLE film_studio;
DROP TABLE person;
DROP TABLE country;
DROP TABLE studio;
DROP TABLE ticket;
DROP TABLE seance;
DROP TABLE film;
DROP TABLE language;
DROP TABLE user;
DROP TABLE role;
DROP TABLE placement;
DROP TABLE hall;

CREATE TABLE `role` (
  `id` INT(11) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id`),
  UNIQUE INDEX `role` (`role`)
) ENGINE=InnoDB;

INSERT INTO role (id, role) VALUES (1, 'admin');
INSERT INTO role (id, role) VALUES (2, 'user');
INSERT INTO role (id, role) VALUES (3, 'actor');
INSERT INTO role (id, role) VALUES (4, 'director');

CREATE TABLE `hall` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `hall_name` VARCHAR(50) NOT NULL,
  `width` INT(11) NOT NULL,
  `height` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id`),
  UNIQUE INDEX `hall_name` (`hall_name`)
) ENGINE=InnoDB;

CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `login` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50),
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id`),
  UNIQUE INDEX `login` (`login`),
  UNIQUE INDEX `phone` (`phone`),
  FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `placement` (
  `hall_id` INT (11) NOT NULL,
  `row_num` INT (11) NOT NULL,
  `column_num` INT (11) NOT NULL,
  FOREIGN KEY (`hall_id`)
  REFERENCES `hall` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `person` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id`),
  UNIQUE INDEX `first_name_last_name` (`first_name`, `last_name`),
  FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `country` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `studio` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `studio` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `language` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `language` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `film` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `description` VARCHAR(50) NOT NULL,
  `min_age` INT(11) NOT NULL,
  `duration` INT(11) NOT NULL,
  `film_language` INT(11) NOT NULL,
  `first_seance` DATE NOT NULL,
  `last_seance` DATE NOT NULL,
  `small_poster` VARCHAR(50) NOT NULL,
  `big_poster` VARCHAR(50) NOT NULL,
  `trailler_link` VARCHAR(50) NOT NULL,
  `year` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id`),
  FOREIGN KEY (`film_language`) REFERENCES `language` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `film_genre` (
  `film_id` INT(11) NOT NULL,
  `genre_id` VARCHAR(50) NOT NULL,
  FOREIGN KEY (`film_id`)
  REFERENCES `film` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `seance` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `film_id` INT(11) NOT NULL,
  `hall_id` INT(11) NOT NULL,
  `price` INT(11) NOT NULL,
  `seance_date` DATE NOT NULL,
  `seance_time` TIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id`),
  FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`hall_id`) REFERENCES `hall` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `ticket` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `seance_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `row_num` INT(11) NOT NULL,
  `place` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id`),
  FOREIGN KEY (`seance_id`) REFERENCES `seance` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `film_studio` (
  `film_id` INT(11) NOT NULL,
  `studio_id` INT(11) NOT NULL,
  FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`studio_id`) REFERENCES `studio` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `film_actor` (
  `film_id` INT(11) NOT NULL,
  `actor_id` INT(11) NOT NULL,
  FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`actor_id`) REFERENCES `person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `film_director` (
  `film_id` INT(11) NOT NULL,
  `director_id` INT(11) NOT NULL,
  FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`director_id`) REFERENCES `person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `film_country` (
  `film_id` INT(11) NOT NULL,
  `country_id` INT(11) NOT NULL,
  FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;