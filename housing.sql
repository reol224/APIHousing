-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 14, 2023 at 07:18 PM
-- Server version: 5.7.24
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `housing`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `username` varchar(25) NOT NULL,
  `password` varchar(100) NOT NULL,
  `id` int(11) NOT NULL,
  `login_attempts` int(11) DEFAULT '0',
  `is_locked` tinyint(1) DEFAULT '0',
  `lockout_expiration` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`username`, `password`, `id`, `login_attempts`, `is_locked`, `lockout_expiration`) VALUES
('iulia', '123', 1, 0, 0, NULL),
('smit', '12348', 2, 0, 0, NULL),
('aayush', '$2b$12$zhxhWUdtuY94Z1WY6DJgLexAHBrzCbtlQCtrCsrqp1cwR4N7JEFf2', 3, 0, 0, NULL),
('dascaleasca', '$2b$12$wdRRo2fMvtOzvuJzEOM0jenxh1giI4kJPkf1Aq3pdkrv3s9S9IzYy', 5, 6, 1, '2023-06-11 19:06:13'),
('dascaleasca]]]]]]]]]', '$2b$12$yOdVRiSOX5EWDFLrYEHY0OL0.us422kVq8fhqen4.Tvtce.Sg3kj6', 6, 0, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `leases`
--

CREATE TABLE `leases` (
  `lease_id` bigint(20) NOT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `lease_start_date` date DEFAULT NULL,
  `lease_end_date` date DEFAULT NULL,
  `lease_length` int(11) DEFAULT NULL,
  `lease_status` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `leases`
--

INSERT INTO `leases` (`lease_id`, `unit_id`, `user_id`, `lease_start_date`, `lease_end_date`, `lease_length`, `lease_status`, `id`) VALUES
(1, 1, 1, '2023-06-18', '2025-04-12', 50, 'Active', 52);

-- --------------------------------------------------------

--
-- Table structure for table `leases_seq`
--

CREATE TABLE `leases_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `leases_seq`
--

INSERT INTO `leases_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Table structure for table `maintenance_requests`
--

CREATE TABLE `maintenance_requests` (
  `request_id` bigint(20) NOT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `request_date` date DEFAULT NULL,
  `request_description` varchar(255) DEFAULT NULL,
  `request_status` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `maintenance_requests_seq`
--

CREATE TABLE `maintenance_requests_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `maintenance_requests_seq`
--

INSERT INTO `maintenance_requests_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `residences`
--

CREATE TABLE `residences` (
  `residence_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `manager_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `residences`
--

INSERT INTO `residences` (`residence_id`, `name`, `address`, `description`, `manager_id`, `id`) VALUES
(1, 'bloc prapadit', 'waterloo', 'descriere', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `residences_seq`
--

CREATE TABLE `residences_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `residences_seq`
--

INSERT INTO `residences_seq` (`next_val`) VALUES
(51);

-- --------------------------------------------------------

--
-- Table structure for table `units`
--

CREATE TABLE `units` (
  `unit_id` bigint(20) NOT NULL,
  `residence_id` bigint(20) DEFAULT NULL,
  `unit_number` varchar(255) DEFAULT NULL,
  `unit_type` varchar(255) DEFAULT NULL,
  `unit_description` varchar(255) DEFAULT NULL,
  `monthly_rent` decimal(38,2) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `units`
--

INSERT INTO `units` (`unit_id`, `residence_id`, `unit_number`, `unit_type`, `unit_description`, `monthly_rent`, `id`) VALUES
(1, 1, '73', 'sexy flat', '24 rooms', '234212.00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `units_seq`
--

CREATE TABLE `units_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `units_seq`
--

INSERT INTO `units_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `college_name` varchar(255) DEFAULT NULL,
  `fcm` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `first_name`, `last_name`, `phone_number`, `address`, `date_of_birth`, `user_id`, `college_name`, `fcm`, `postal_code`, `student_id`) VALUES
(1, 'asdasd@gmail.com', 'password', 'firstName', 'lastName', 'phoneNumber', 'address', '2023-05-21', NULL, NULL, NULL, NULL, NULL),
(25, 'john.doe@examplex.com', '$2a$10$znzSV/ngzFEbq8NUUG769ePFz0yOT6hQnToSAg9uRAqaYMTX1H.EO', 'John', 'Doe', '1234567890', '123 Main St', '1990-01-01', NULL, NULL, 'fS4DBGgXRs-fL4xcp9iiQ7:APA91bErBcswP_zMPrxcfRhSKPs8ahnGLLIMdcmOmASJjETpVBEZA64E301wxJPn4SBurhcoO54RcdMPmKlWpYTJq6OISnH1uetzLAHU9tFZsIXD52wCaHA3HHN0FVwq5gid8e_-CRSU', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users_seq`
--

CREATE TABLE `users_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users_seq`
--

INSERT INTO `users_seq` (`next_val`) VALUES
(26);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `leases`
--
ALTER TABLE `leases`
  ADD PRIMARY KEY (`lease_id`),
  ADD KEY `unit_id` (`unit_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `maintenance_requests`
--
ALTER TABLE `maintenance_requests`
  ADD PRIMARY KEY (`request_id`),
  ADD KEY `unit_id` (`unit_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `residences`
--
ALTER TABLE `residences`
  ADD PRIMARY KEY (`residence_id`),
  ADD KEY `manager_id` (`manager_id`);

--
-- Indexes for table `units`
--
ALTER TABLE `units`
  ADD PRIMARY KEY (`unit_id`),
  ADD KEY `residence_id` (`residence_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `leases`
--
ALTER TABLE `leases`
  MODIFY `lease_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `maintenance_requests`
--
ALTER TABLE `maintenance_requests`
  MODIFY `request_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `residences`
--
ALTER TABLE `residences`
  MODIFY `residence_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `units`
--
ALTER TABLE `units`
  MODIFY `unit_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `leases`
--
ALTER TABLE `leases`
  ADD CONSTRAINT `leases_ibfk_1` FOREIGN KEY (`unit_id`) REFERENCES `units` (`unit_id`),
  ADD CONSTRAINT `leases_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `maintenance_requests`
--
ALTER TABLE `maintenance_requests`
  ADD CONSTRAINT `maintenance_requests_ibfk_1` FOREIGN KEY (`unit_id`) REFERENCES `units` (`unit_id`),
  ADD CONSTRAINT `maintenance_requests_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `residences`
--
ALTER TABLE `residences`
  ADD CONSTRAINT `residences_ibfk_1` FOREIGN KEY (`manager_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `units`
--
ALTER TABLE `units`
  ADD CONSTRAINT `units_ibfk_1` FOREIGN KEY (`residence_id`) REFERENCES `residences` (`residence_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
