CREATE TABLE `role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id`),
  UNIQUE INDEX `role` (`role`)
)

  ENGINE=InnoDB;


CREATE TABLE `hall` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `width` INT(11) NOT NULL,
  `height` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id`),
  UNIQUE INDEX `name` (`name`)
)
  ENGINE=InnoDB;

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
)
  ENGINE=InnoDB;

CREATE TABLE `placement` (
  `hall_id` INT (11) NOT NULL,
  `row` INT (11) NOT NULL,
  `column` INT (11) NOT NULL,
  FOREIGN KEY (`hall_id`)
  REFERENCES `hall` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE=InnoDB;

CREATE TABLE `genre` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `genre` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id`),
  UNIQUE INDEX `genre` (`genre`)
)
  ENGINE=InnoDB;

CREATE TABLE `person` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id`),
  UNIQUE INDEX `first_name_last_name` (`first_name`, `last_name`)
)
  ENGINE=InnoDB;