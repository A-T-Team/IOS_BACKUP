CREATE DATABASE  IF NOT EXISTS `device_directory`;
USE `device_directory`;

DROP TABLE IF EXISTS `device`;

CREATE TABLE `device` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(45),
    `vendor` varchar(45) DEFAULT NULL,
	`ip` varchar(45) DEFAULT NULL,
	`port` INT UNSIGNED DEFAULT 22,
	`user` varchar(45) DEFAULT NULL,
	`password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;


INSERT INTO `device` VALUES 
	(1,'Switch1','Cisco','1.1.1.1',22,'admin','P@ssw0rd'),
	(2,'Switch2','HP','2.2.2.2',22,'admin','P@ssw0rd'),
	(3,'Router1','Cisco','3.3.3.3',22,'admin','P@ssw0rd'),
	(4,'Switch3','Dell','4.4.4.4',22,'admin','P@ssw0rd'),
	(5,'Switch4','Arista','5.5.5.5',22,'admin','P@ssw0rd')

;

DROP TABLE IF EXISTS `backup`;

CREATE TABLE `backup` (
	`backup_id` BINARY (16) PRIMARY KEY ,
	`time_stamp` varchar(45),
	`device_id` INT,
	`payload` TEXT DEFAULT NULL

);

INSERT INTO `backup` VALUES 
	(UUID_TO_BIN(UUID()),'121212',1,'sdfsfsfsfdsfsddffdsfsdfsdfsfd11111'),
    (UUID_TO_BIN(UUID()),'121212',1,'sdfsfsfsfdsfsddffdsfsdfsdfsfd11111')
;

