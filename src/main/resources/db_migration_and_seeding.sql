/* MIGRATION */
DROP SCHEMA IF EXISTS `mygamelist`;
CREATE SCHEMA `mygamelist`;
USE `mygamelist`;

CREATE TABLE `mygamelist`.`media_type` (
	`id` VARCHAR(50) NOT NULL,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`created_at` DATETIME NULL DEFAULT NULL,
	`updated_at` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `mygamelist`.`media` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`media_type_id` VARCHAR(50) NULL DEFAULT NULL,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`created_at` DATETIME NULL DEFAULT NULL,
	`updated_at` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_MEDIA_MEDIA_TYPE`
		FOREIGN KEY (`media_type_id`)
		REFERENCES `mygamelist`.`media_type` (`id`)
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `mygamelist`.`user` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(50) NULL DEFAULT NULL,
	`password` VARCHAR(255) NULL DEFAULT NULL,
	`created_at` DATETIME NULL DEFAULT NULL,
	`updated_at` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `mygamelist`.`user_aggregate` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`user_id` INT NOT NULL,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`media_id` INT NULL DEFAULT NULL,
	`created_at` DATETIME NULL DEFAULT NULL,
	`updated_at` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_USER_AGGREGATE_USER`
		FOREIGN KEY (`user_id`) REFERENCES `mygamelist`.`user` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `FK_USER_AGGREGATE_MEDIA`
		FOREIGN KEY (`media_id`) REFERENCES `mygamelist`.`media` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `mygamelist`.`game` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`giantbomb_id` VARCHAR(50) NULL DEFAULT NULL,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`release_date` DATETIME NULL DEFAULT NULL,
	`created_at` DATETIME NULL DEFAULT NULL,
	`updated_at` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `mygamelist`.`developer` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`giantbomb_id` VARCHAR(50) NULL DEFAULT NULL,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`media_id` INT NULL DEFAULT NULL,
	`created_at` DATETIME NULL DEFAULT NULL,
	`updated_at` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_DEVELOPER_MEDIA`
		FOREIGN KEY (`media_id`) REFERENCES `mygamelist`.`media` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `mygamelist`.`game_developer` (
	`game_id` INT NOT NULL,
	`developer_id` INT NOT NULL,
	PRIMARY KEY (`game_id`, `developer_id`),
	CONSTRAINT `FK_GAME_DEVELOPER_GAME`
		FOREIGN KEY (`game_id`) REFERENCES `mygamelist`.`game` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `FK_GAME_DEVELOPER_DEVELOPER`
		FOREIGN KEY (`developer_id`) REFERENCES `mygamelist`.`developer` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `mygamelist`.`manufacturer` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`giantbomb_id` VARCHAR(50) NULL DEFAULT NULL,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`media_id` INT NULL DEFAULT NULL,
	`created_at` DATETIME NULL DEFAULT NULL,
	`updated_at` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_MANUFACTURER_MEDIA`
		FOREIGN KEY (`media_id`) REFERENCES `mygamelist`.`media` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `mygamelist`.`platform` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`giantbomb_id` VARCHAR(50) NULL DEFAULT NULL,
	`manufacturer_id` INT NULL DEFAULT NULL,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`abbreviation` VARCHAR(50) NULL DEFAULT NULL,
	`media_id` INT NULL DEFAULT NULL,
	`created_at` DATETIME NULL DEFAULT NULL,
	`updated_at` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_PLATFORM_MANUFACTURER`
		FOREIGN KEY (`manufacturer_id`) REFERENCES `mygamelist`.`manufacturer` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `FK_PLATFORM_MEDIA`
		FOREIGN KEY (`media_id`) REFERENCES `mygamelist`.`media` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `mygamelist`.`game_release` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`game_id` INT NOT NULL,
	`platform_id` INT NOT NULL,
	`media_id` INT NULL DEFAULT NULL,
	`created_at` DATETIME NULL DEFAULT NULL,
	`updated_at` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_GAME_RELEASE_GAME`
		FOREIGN KEY (`game_id`) REFERENCES `mygamelist`.`game` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `FK_GAME_RELEASE_PLATFORM`
		FOREIGN KEY (`platform_id`) REFERENCES `mygamelist`.`platform` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `FK_GAME_RELEASE_MEDIA`
		FOREIGN KEY (`media_id`) REFERENCES `mygamelist`.`media` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `mygamelist`.`user_release` (
	`user_id` INT NOT NULL,
	`release_id` INT NOT NULL,
	PRIMARY KEY (`user_id`, `release_id`),
	CONSTRAINT `FK_USER_RELEASE_USER`
		FOREIGN KEY (`user_id`) REFERENCES `mygamelist`.`user` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `FK_USER_RELEASE_GAME_RELEASE`
		FOREIGN KEY (`release_id`) REFERENCES `mygamelist`.`game_release` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

/* SEEDING */
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("Windows", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("Sony", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("Microsoft", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("Nintendo", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("SEGA", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("Atari", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("Amiga", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("Amstrad", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("CBM", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("NEC", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("SNK", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("Philips", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("Coleco", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("Panasonic", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("Goldstar", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("Sanyo", NOW(), NOW());
INSERT INTO `mygamelist`.`manufacturer` (`name`,`created_at`,`updated_at`) VALUES ("Magnavox", NOW(), NOW());

INSERT INTO `mygamelist`.`media_type` (`id`,`name`,`created_at`,`updated_at`) VALUES ("user", "User avatar", NOW(), NOW());
INSERT INTO `mygamelist`.`media_type` (`id`,`name`,`created_at`,`updated_at`) VALUES ("developer", "Developer image", NOW(), NOW());
INSERT INTO `mygamelist`.`media_type` (`id`,`name`,`created_at`,`updated_at`) VALUES ("manufacturer", "Manufacturer image", NOW(), NOW());
INSERT INTO `mygamelist`.`media_type` (`id`,`name`,`created_at`,`updated_at`) VALUES ("platform", "Platform image", NOW(), NOW());
INSERT INTO `mygamelist`.`media_type` (`id`,`name`,`created_at`,`updated_at`) VALUES ("game_release", "Release image", NOW(), NOW());
