/* MIGRATION */
DROP SCHEMA IF EXISTS `mygamelist`;
CREATE SCHEMA `mygamelist`;
USE `mygamelist`;

CREATE TABLE `mygamelist`.`user_aggregate` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `mygamelist`.`user` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(50) NULL DEFAULT NULL,
	`user_aggregate_id` INT NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_USER_AGGREGATE`
		FOREIGN KEY (`user_aggregate_id`)
		REFERENCES `user_aggregate` (`id`)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `mygamelist`.`developer` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `mygamelist`.`game` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`developer_id` INT NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_DEVELOPER`
		FOREIGN KEY (`developer_id`)
		REFERENCES `developer` (`id`)
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `mygamelist`.`user_game` (
	`user_id` INT NOT NULL,
	`game_id` INT NOT NULL,
	PRIMARY KEY (`user_id`, `game_id`),
	CONSTRAINT `FK_USER`
		FOREIGN KEY (`user_id`)
		REFERENCES `user` (`id`)
		ON UPDATE NO ACTION
		ON DELETE NO ACTION,
	CONSTRAINT `FK_GAME`
		FOREIGN KEY (`game_id`)
		REFERENCES `game` (`id`)
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

/* SEEDING */
INSERT INTO `mygamelist`.`user_aggregate` (`name`) VALUES ("Nouvel Utilisateur 1");
INSERT INTO `mygamelist`.`user_aggregate` (`name`) VALUES ("Nouvel Utilisateur 2");
INSERT INTO `mygamelist`.`user_aggregate` (`name`) VALUES ("Nouvel Utilisateur 3");

INSERT INTO `mygamelist`.`user` (`email`, `user_aggregate_id`) VALUES ("1nouvel@utilisateur.com", 1);
INSERT INTO `mygamelist`.`user` (`email`, `user_aggregate_id`) VALUES ("2nouvel@utilisateur.com", 2);
INSERT INTO `mygamelist`.`user` (`email`, `user_aggregate_id`) VALUES ("3nouvel@utilisateur.com", 3);

INSERT INTO `mygamelist`.`developer` (`name`) VALUES ("Epic Games");
INSERT INTO `mygamelist`.`developer` (`name`) VALUES ("Valve");
INSERT INTO `mygamelist`.`developer` (`name`) VALUES ("Gearbox Software");

INSERT INTO `mygamelist`.`game` (`name`, `developer_id`) VALUES ("Gears of War", 1);
INSERT INTO `mygamelist`.`game` (`name`, `developer_id`) VALUES ("Half Life", 2);
INSERT INTO `mygamelist`.`game` (`name`, `developer_id`) VALUES ("Borderlands", 3);

INSERT INTO `mygamelist`.`user_game` (`user_id`, `game_id`) VALUES (1, 1);
INSERT INTO `mygamelist`.`user_game` (`user_id`, `game_id`) VALUES (1, 2);
INSERT INTO `mygamelist`.`user_game` (`user_id`, `game_id`) VALUES (1, 3);
