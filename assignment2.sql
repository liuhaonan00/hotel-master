-- phpMyAdmin SQL Dump
-- version 4.4.9
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: May 05, 2016 at 04:25 PM
-- Server version: 5.5.42
-- PHP Version: 5.6.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `comp9321_ass2`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking_record`
--

CREATE TABLE `booking_record` (
  `booking_id` varchar(20) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `hotel_id` varchar(20) NOT NULL,
  `total_price` float NOT NULL,
  `checkin` date NOT NULL,
  `checkout` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer_user`
--

CREATE TABLE `customer_user` (
  `user_id` varchar(20) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `nickname` varchar(30) NOT NULL,
  `credit_card_type` varchar(30) NOT NULL,
  `credit_card_number` varchar(16) NOT NULL,
  `credit_card_name` varchar(30) NOT NULL,
  `credit_card_exp_month` int(11) NOT NULL,
  `credit_card_exp_year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE `hotel` (
  `hotel_id` varchar(20) NOT NULL DEFAULT '',
  `hotel_name` varchar(50) NOT NULL,
  `city` varchar(30) NOT NULL,
  `hotel_address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_booking_record`
--

CREATE TABLE `room_booking_record` (
  `booking_id` varchar(20) NOT NULL,
  `room_id` varchar(20) NOT NULL,
  `add_extra_bed` int(11) NOT NULL,
  `room_no` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_type`
--

CREATE TABLE `room_type` (
  `room_id` varchar(20) NOT NULL DEFAULT '',
  `hotel_id` varchar(20) NOT NULL,
  `offpeak_price` float NOT NULL,
  `num_of_room` int(11) NOT NULL,
  `available_num` int(11) NOT NULL,
  `room_description` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `special_offer`
--

CREATE TABLE `special_offer` (
  `offer_id` varchar(20) NOT NULL,
  `hotel_id` varchar(20) NOT NULL,
  `room_id` varchar(20) NOT NULL,
  `special_price` float NOT NULL,
  `promotion_start` date NOT NULL,
  `promotion_stop` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking_record`
--
ALTER TABLE `booking_record`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `hotel_id` (`hotel_id`);

--
-- Indexes for table `customer_user`
--
ALTER TABLE `customer_user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`hotel_id`);

--
-- Indexes for table `room_booking_record`
--
ALTER TABLE `room_booking_record`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `room_id` (`room_id`);

--
-- Indexes for table `room_type`
--
ALTER TABLE `room_type`
  ADD PRIMARY KEY (`room_id`),
  ADD KEY `hotel_id` (`hotel_id`);

--
-- Indexes for table `special_offer`
--
ALTER TABLE `special_offer`
  ADD PRIMARY KEY (`offer_id`),
  ADD KEY `hotel_id` (`hotel_id`,`room_id`),
  ADD KEY `room_id` (`room_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking_record`
--
ALTER TABLE `booking_record`
  ADD CONSTRAINT `booking_record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `customer_user` (`user_id`),
  ADD CONSTRAINT `booking_record_ibfk_2` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`);

--
-- Constraints for table `room_booking_record`
--
ALTER TABLE `room_booking_record`
  ADD CONSTRAINT `room_booking_record_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `booking_record` (`booking_id`),
  ADD CONSTRAINT `room_booking_record_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `room_type` (`room_id`);

--
-- Constraints for table `room_type`
--
ALTER TABLE `room_type`
  ADD CONSTRAINT `room_type_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`);

--
-- Constraints for table `special_offer`
--
ALTER TABLE `special_offer`
  ADD CONSTRAINT `special_offer_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `room_type` (`room_id`),
  ADD CONSTRAINT `special_offer_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`);
