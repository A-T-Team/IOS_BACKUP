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


-- INSERT INTO `employee` VALUES 
-- 	(1,'Leslie','Andrews','leslie@luv2code.com'),
-- 	(2,'Emma','Baumgarten','emma@luv2code.com'),
-- 	(3,'Avani','Gupta','avani@luv2code.com'),
-- 	(4,'Yuri','Petrov','yuri@luv2code.com'),
-- 	(5,'Juan','Vega','juan@luv2code.com');

