DROP DATABASE IF EXISTS cardealershipDB;
CREATE DATABASE cardealershipDB;

USE cardealershipDB;

CREATE TABLE `condition`(
id INT PRIMARY KEY AUTO_INCREMENT,
`condition` VARCHAR(10) NOT NULL
);

INSERT INTO `condition`(`condition`) VALUES ("new");
INSERT INTO `condition`(`condition`) VALUES ("used");

CREATE TABLE bodyStyle(
id INT PRIMARY KEY AUTO_INCREMENT,
bodyStyle VARCHAR(25) NOT NULL
);

INSERT INTO bodyStyle(bodyStyle) VALUES ("car");
INSERT INTO bodyStyle(bodyStyle) VALUES ("SUV");
INSERT INTO bodyStyle(bodyStyle) VALUES ("truck");
INSERT INTO bodyStyle(bodyStyle) VALUES ("van");

CREATE TABLE color(
id INT PRIMARY KEY AUTO_INCREMENT,
color VARCHAR(25) NOT NULL
);

INSERT INTO color(color) VALUES ("red");
INSERT INTO color(color) VALUES ("orange");
INSERT INTO color(color) VALUES ("yellow");
INSERT INTO color(color) VALUES ("green");
INSERT INTO color(color) VALUES ("blue");

CREATE TABLE interior(
id INT PRIMARY KEY AUTO_INCREMENT,
interior VARCHAR(25) NOT NULL
);

INSERT INTO interior(interior) VALUES ("red");
INSERT INTO interior(interior) VALUES ("orange");
INSERT INTO interior(interior) VALUES ("yellow");
INSERT INTO interior(interior) VALUES ("green");
INSERT INTO interior(interior) VALUES ("blue");

CREATE TABLE trans(
id INT PRIMARY KEY AUTO_INCREMENT,
trans VARCHAR(25) NOT NULL
);

INSERT INTO trans(trans) VALUES ("automatic");
INSERT INTO trans(trans) VALUES ("manual");

CREATE TABLE contact(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(25) NOT NULL,
message MEDIUMTEXT NOT NULL,
phone VARCHAR(15),
email VARCHAR(50)
);

INSERT INTO contact(`name`, message, phone) VALUES ("Caleb", "Hi", "5555360878");

CREATE TABLE `role`(
id INT PRIMARY KEY AUTO_INCREMENT,
`role` VARCHAR(25) NOT NULL
);

INSERT INTO `role`(`role`) VALUES ("disabled");
INSERT INTO `role`(`role`) VALUES ("sales");
INSERT INTO `role`(`role`) VALUES ("admin");

CREATE TABLE purchaseType(
id INT PRIMARY KEY AUTO_INCREMENT,
purchaseType VARCHAR(25) NOT NULL
);

INSERT INTO purchaseType(purchaseType) VALUES ("bank finance");
INSERT INTO purchaseType(purchaseType) VALUES ("cash");
INSERT INTO purchaseType(purchaseType) VALUES ("dealer finance");

CREATE TABLE state(
id INT PRIMARY KEY AUTO_INCREMENT,
state VARCHAR(25) NOT NULL
);

INSERT INTO state(state) VALUES ("florida");

CREATE TABLE special (
id INT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(50) NOT NULL,
`description` MEDIUMTEXT NOT NULL
);

INSERT INTO special(title, `description`) VALUES ("Big Sale!", "We have great cars on sale.");

CREATE TABLE `user` (
id INT PRIMARY KEY AUTO_INCREMENT,
firstName VARCHAR(25) NOT NULL,
lastName VARCHAR(25) NOT NULL,
email VARCHAR(50) NOT NULL,
`password` VARCHAR(25) NOT NULL,
roleId INT NOT NULL,
FOREIGN KEY (roleId) REFERENCES `role`(id)
);

INSERT INTO `user`(firstName, lastName, email, `password`, roleId) VALUES ("Caleb", "Diaz", "calebdiaz@wustl.edu", "password", 3);

CREATE TABLE make (
id INT PRIMARY KEY AUTO_INCREMENT,
make VARCHAR(25) NOT NULL,
dateAdded DATETIME NOT NULL,
userId INT NOT NULL,
FOREIGN KEY (userId) REFERENCES `user`(id)
);

INSERT INTO make(make, dateAdded, userId) VALUES ("Toyota", '2012-12-31 11:30:45', 1);

CREATE TABLE model (
id INT PRIMARY KEY AUTO_INCREMENT,
model VARCHAR(25) NOT NULL,
dateAdded DATETIME NOT NULL,
userId INT NOT NULL,
makeId INT NOT NULL,
FOREIGN KEY (makeId) REFERENCES make(id),
FOREIGN KEY (userId) REFERENCES `user`(id)
);

INSERT INTO model(model, dateAdded, userId, makeId) VALUES ("Corolla", '2012-12-31 11:30:45', 1, 1);

CREATE TABLE vehicle(
id INT PRIMARY KEY AUTO_INCREMENT,
`year` DATETIME NOT NULL,
salePrice DECIMAL NOT NULL,
msrp DECIMAL NOT NULL,
mileage MEDIUMINT NOT NULL,
vin CHAR(17) NOT NULL,
`description` MEDIUMTEXT NOT NULL,
picture VARCHAR(50),
purchased BOOLEAN NOT NULL,
featured BOOLEAN NOT NULL, 
makeId INT NOT NULL,
modelId INT NOT NULL,
conditionId INT NOT NULL,
bodyStyleId INT NOT NULL,
interiorId INT NOT NULL,
transId INT NOT NULL,
colorId INT NOT NULL,
FOREIGN KEY (makeId) REFERENCES make(id),
FOREIGN KEY (modelId) REFERENCES model(id),
FOREIGN KEY (conditionId) REFERENCES `condition`(id),
FOREIGN KEY (bodyStyleId) REFERENCES bodyStyle(id),
FOREIGN KEY (interiorId) REFERENCES interior(id),
FOREIGN KEY (transId) REFERENCES trans(id),
FOREIGN KEY (colorId) REFERENCES color(id));

INSERT INTO vehicle(`year`, salePrice, msrp, mileage, vin, `description`, purchased, featured, makeId, modelId, conditionId, bodyStyleId, interiorId, transId, colorId)
VALUES('2017-1-1 00:00:00', 17000, 21000, 55000, "1HGBH41JXMN109186", "Great car", false, true, 1, 1, 2, 1, 1, 1, 1);

CREATE TABLE sale(
id INT PRIMARY KEY AUTO_INCREMENT,
buyerName VARCHAR(50) NOT NULL,
purchasePrice DECIMAL NOT NULL,
purchaseDate DATETIME NOT NULL,
email VARCHAR(50),
phone VARCHAR(15),
street1 VARCHAR(50) NOT NULL,
street2 VARCHAR(50),
city VARCHAR(25) NOT NULL,
zipcode CHAR(5) NOT NULL,
stateId INT NOT NULL,
purchaseTypeId INT NOT NULL,
vehicleId INT NOT NULL,
userId INT NOT NULL,
FOREIGN KEY (stateId) REFERENCES state(id),
FOREIGN KEY (purchaseTypeId) REFERENCES purchaseType(id),
FOREIGN KEY (vehicleId) REFERENCES vehicle(id),
FOREIGN KEY (userId) REFERENCES `user`(id)
);



