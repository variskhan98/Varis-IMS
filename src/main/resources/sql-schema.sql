drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `ims`.`item` (
    id int NOT NULL AUTO_INCREMENT,
    itemname varchar(255) NOT NULL,
    itemvalue int,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    id int NOT NULL AUTO_INCREMENT,
    orderid int NOT NULL,
    customerid int,
    itemid int,
    PRIMARY KEY (id),
    FOREIGN KEY (customerid) REFERENCES customers(id),
    FOREIGN KEY (itemid) REFERENCES item(id)
);