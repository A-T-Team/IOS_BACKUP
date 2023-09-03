CREATE DATABASE  IF NOT EXISTS `device_directory`;
USE `device_directory`;

DROP TABLE IF EXISTS `device`;
DROP TABLE IF EXISTS `pool`;
DROP TABLE IF EXISTS `backup`;

CREATE TABLE `pool` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL, 
   PRIMARY KEY (`id`),  
  UNIQUE KEY `NAME_UNIQUE` (`name`)

) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

device
INSERT INTO `pool` VALUES
	(1, 'Pool1', 'Pool 1 Description');


CREATE TABLE `device` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(45),
    `vendor` varchar(45) DEFAULT NULL,
	`ip` varchar(45) DEFAULT NULL,
	`port` INT UNSIGNED DEFAULT 22,
	`user` varchar(45) DEFAULT NULL,
	`password` varchar(45) DEFAULT NULL,
    `pool_id` int DEFAULT NULL,          
  KEY `FK_POOL_idx` (`pool_id`),  
  CONSTRAINT `FK_POOL` 
  FOREIGN KEY (`pool_id`) 
  REFERENCES `pool` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION ,     
  PRIMARY KEY (`id`)  
) AUTO_INCREMENT=1;


INSERT INTO `device` VALUES 
	(1,'Switch1','Cisco','1.1.1.1',22,'admin','P@ssw0rd',1),
	(2,'Switch2','HP','2.2.2.2',22,'admin','P@ssw0rd',1),
	(3,'Router1','Cisco','3.3.3.3',22,'admin','P@ssw0rd',1),
	(4,'Switch3','Dell','4.4.4.4',22,'admin','P@ssw0rd',1),
	(5,'Switch4','Arista','5.5.5.5',22,'admin','P@ssw0rd',1)

;




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



SET FOREIGN_KEY_CHECKS = 1;
