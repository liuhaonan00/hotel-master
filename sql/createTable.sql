DROP TABLE IF EXISTS `offer`;
DROP TABLE IF EXISTS `room_status`;
DROP TABLE IF EXISTS `booking`;
DROP TABLE IF EXISTS `room`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `hotel`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `firstname` varchar(30) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `credit_card_type` varchar(45) DEFAULT NULL,
  `credit_card_number` varchar(45) DEFAULT NULL,
  `credit_card_exp_month` int(11) DEFAULT NULL,
  `credit_card_exp_year` int(11) DEFAULT NULL,
  `credit_card_cvv` int(11) DEFAULT NULL,
  `email_verification` int(11) DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `iduser_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO user (username, password, email,email_verification) VALUES ('super', 'root', 'expmple@sss.com', 1);
INSERT INTO user (username, password, email) VALUES ('chang ma', 'hiroot', 'liuhaonan1@qq.com');
INSERT INTO user (username, password, email) VALUES ('rua', 'hiroot', 'liuhaonan2@qq.com');



CREATE TABLE `hotel` (
  `hotel_id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_name` varchar(50) DEFAULT NULL,
  `hotel_address` varchar(100) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`hotel_id`),
  UNIQUE KEY `hotel_id_UNIQUE` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO hotel (hotel_name, hotel_address, city) VALUES ('Rampage-SYD-1', 'Sydney Road, NSW', 'Sydney');
INSERT INTO hotel (hotel_name, hotel_address, city) VALUES ('Rampage-SYD-2', 'Sydney Road, NSW', 'Sydney');
INSERT INTO hotel (hotel_name, hotel_address, city) VALUES ('Rampage-MEL-1', 'Sydney Road, VIC', 'Melbourne');
INSERT INTO hotel (hotel_name, hotel_address, city) VALUES ('Rampage-BRI-1', 'Sydney Road, QLD', 'Brisbane');
INSERT INTO hotel (hotel_name, hotel_address, city) VALUES ('Rampage-ADE-1', 'Sydney Road, SA', 'Adelaide');

CREATE TABLE `room` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_id` int(11) NOT NULL,
  `room_type` varchar(50) DEFAULT NULL,
  `room_no` varchar(45) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `room_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `room_id_UNIQUE` (`room_id`),
  KEY `hotel_id_idx` (`hotel_id`),
  KEY `room_type_idx` (`room_type`),
  CONSTRAINT `hotel_id` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
UNLOCK TABLES;

INSERT INTO room (hotel_id, room_type,room_no,price,room_description) VALUES (1, 'Double bedroom', '101', 100, 'A double bedroom.');
INSERT INTO room (hotel_id, room_type,room_no,price,room_description) VALUES (1, 'Double bedroom', '102', 100, 'A double bedroom.');
INSERT INTO room (hotel_id, room_type,room_no,price,room_description) VALUES (1, 'Double bedroom', '103', 100, 'A double bedroom.');
INSERT INTO room (hotel_id, room_type,room_no,price,room_description) VALUES (1, 'Double bedroom', '104', 100, 'A double bedroom.');
INSERT INTO room (hotel_id, room_type,room_no,price,room_description) VALUES (1, 'Double bedroom', '105', 100, 'A double bedroom.');

CREATE TABLE `booking` (
  `booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `checkin` date DEFAULT NULL,
  `checkout` date DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  UNIQUE KEY `booking_id_UNIQUE` (`booking_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `hotel_id_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Dumping data for table `booking`
--
LOCK TABLES `booking` WRITE;
UNLOCK TABLES;

INSERT INTO booking (user_id,hotel_id,checkin,checkout,total_price) 
VALUES (1,1,'2000-07-28','2100-07-30', 0);
INSERT INTO booking (user_id,hotel_id,checkin,checkout,total_price) 
VALUES (2,1,'2016-07-28','2016-07-30', 200);

CREATE TABLE `room_status` (
  `status_id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_id` int(11) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  `booking_id` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`status_id`),
  UNIQUE KEY `status_id_UNIQUE` (`status_id`),
  KEY `hotel_id2_idx` (`hotel_id`),
  KEY `room_id2_idx` (`room_id`),
  KEY `booking_id2_idx` (`booking_id`),
  CONSTRAINT `hotel_id2` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `room_id2` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `booking_id2` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`) ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Dumping data for table `room_status`
--
LOCK TABLES `room_status` WRITE;
UNLOCK TABLES;

INSERT INTO room_status (hotel_id, room_id,booking_id, status, start_date, end_date) VALUES
(1,1,2,'Booked', '2016-07-28','2016-07-30');

CREATE TABLE `offer` (
  `offer_id` int(11) NOT NULL AUTO_INCREMENT,
  `offer_price` float DEFAULT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `room_type` varchar(50) DEFAULT NULL,
  `start` date DEFAULT NULL,
  `end` date DEFAULT NULL,
  PRIMARY KEY (`offer_id`),
  KEY `hotel_id3_idx` (`hotel_id`),
  KEY `room_type3_idx` (`room_type`),
  CONSTRAINT `hotel_id3` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `room_type3` FOREIGN KEY (`room_type`) REFERENCES `room` (`room_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Dumping data for table `offer`
--
LOCK TABLES `offer` WRITE;
UNLOCK TABLES;
