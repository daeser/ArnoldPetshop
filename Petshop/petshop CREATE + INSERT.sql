-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 13, 2023 at 12:13 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `petshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `action`
--

CREATE TABLE `action` (
  `actionID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `petID` int(11) NOT NULL,
  `animal` varchar(255) NOT NULL,
  `PetName` varchar(255) NOT NULL,
  `PetPrice` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `action`
--

INSERT INTO `action` (`actionID`, `userID`, `petID`, `animal`, `PetName`, `PetPrice`, `description`, `Date`) VALUES
(5, 1, 10, 'Penguin', 'Kowalski', 1300000, 'Inserted Pet', '2023-01-10'),
(8, 1, 2, 'Penguin', 'Skipper', 1233333, 'Inserted Pet', '2023-01-13'),
(9, 1, 3, 'Monkey', 'Boots', 200000, 'Inserted Pet', '2023-01-13'),
(10, 1, 3, 'Monkey', 'Boots', 300000, 'Updated Pet', '2023-01-13'),
(11, 1, 4, 'Rooster', 'Sample', 666666, 'Inserted Pet', '2023-01-13');

-- --------------------------------------------------------

--
-- Table structure for table `animal`
--

CREATE TABLE `animal` (
  `AnimalID` int(11) NOT NULL,
  `Animal` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `animal`
--

INSERT INTO `animal` (`AnimalID`, `Animal`) VALUES
(1, 'Dog'),
(2, 'Cat'),
(3, 'Rooster'),
(4, 'Monkey'),
(5, 'Seal'),
(6, 'Snake'),
(7, 'Platypus'),
(8, 'Beaver'),
(9, 'Penguin'),
(10, 'Bird'),
(11, 'Eagle'),
(12, 'Chipmunk');

-- --------------------------------------------------------

--
-- Table structure for table `animalaction`
--

CREATE TABLE `animalaction` (
  `AnimalActionID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `AnimalID` int(11) NOT NULL,
  `Animal` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `animalaction`
--

INSERT INTO `animalaction` (`AnimalActionID`, `userID`, `AnimalID`, `Animal`, `description`, `Date`) VALUES
(1, 1, 11, 'Eagle', 'Inserted Animal', '2023-01-13'),
(2, 1, 12, 'sample', 'Inserted Animal', '2023-01-13'),
(3, 1, 12, 'Chipmunk', 'Updated Animal', '2023-01-13'),
(4, 1, 13, 'sample', 'Inserted Animal', '2023-01-13'),
(5, 1, 13, 'sample', 'Deleted Pet', '2023-01-13');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `userID` int(11) NOT NULL,
  `petID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pet`
--

CREATE TABLE `pet` (
  `petID` int(11) NOT NULL,
  `AnimalID` int(11) NOT NULL,
  `PetName` varchar(255) NOT NULL,
  `PetPrice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pet`
--

INSERT INTO `pet` (`petID`, `AnimalID`, `PetName`, `PetPrice`) VALUES
(2, 9, 'Skipper', 1233333),
(3, 4, 'Boots', 300000);

-- --------------------------------------------------------

--
-- Table structure for table `transactiondetail`
--

CREATE TABLE `transactiondetail` (
  `TransactionID` int(11) NOT NULL,
  `petID` int(11) NOT NULL,
  `PetName` varchar(255) NOT NULL,
  `Animal` varchar(255) NOT NULL,
  `Price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactiondetail`
--

INSERT INTO `transactiondetail` (`TransactionID`, `petID`, `PetName`, `Animal`, `Price`) VALUES
(1, 1, 'Perry', 'Platypus', 1500000),
(2, 4, 'Sample', 'Rooster', 666666);

-- --------------------------------------------------------

--
-- Table structure for table `transactionheader`
--

CREATE TABLE `transactionheader` (
  `TransactionID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `TransactionDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactionheader`
--

INSERT INTO `transactionheader` (`TransactionID`, `userID`, `TransactionDate`) VALUES
(1, 3, '2023-01-13'),
(2, 3, '2023-01-13');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `userPassword` varchar(255) NOT NULL,
  `userRole` varchar(255) NOT NULL,
  `userEmail` varchar(255) NOT NULL,
  `userGender` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `username`, `userPassword`, `userRole`, `userEmail`, `userGender`) VALUES
(1, 'Staff0', 'Staff123', 'Staff', 'arnoldlewis@gmail.com', 'Female'),
(2, 'Owner', 'Owner123', 'Owner', 'Rachman@gmail.com', 'Male'),
(3, 'Raihan', 'Cs123', 'Customer', 'Bagas31@gmail.com', 'Male'),
(4, 'Kemal', 'Password1', 'Customer', 'Malikkilam@gmail.com', 'Female');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `action`
--
ALTER TABLE `action`
  ADD PRIMARY KEY (`actionID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `animal`
--
ALTER TABLE `animal`
  ADD PRIMARY KEY (`AnimalID`);

--
-- Indexes for table `animalaction`
--
ALTER TABLE `animalaction`
  ADD PRIMARY KEY (`AnimalActionID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`petID`,`userID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `pet`
--
ALTER TABLE `pet`
  ADD PRIMARY KEY (`petID`),
  ADD KEY `AnimalID` (`AnimalID`);

--
-- Indexes for table `transactiondetail`
--
ALTER TABLE `transactiondetail`
  ADD PRIMARY KEY (`TransactionID`,`petID`),
  ADD KEY `TransactionID` (`TransactionID`);

--
-- Indexes for table `transactionheader`
--
ALTER TABLE `transactionheader`
  ADD PRIMARY KEY (`TransactionID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `action`
--
ALTER TABLE `action`
  MODIFY `actionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `animal`
--
ALTER TABLE `animal`
  MODIFY `AnimalID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `animalaction`
--
ALTER TABLE `animalaction`
  MODIFY `AnimalActionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `pet`
--
ALTER TABLE `pet`
  MODIFY `petID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `transactionheader`
--
ALTER TABLE `transactionheader`
  MODIFY `TransactionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `action`
--
ALTER TABLE `action`
  ADD CONSTRAINT `action_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `animalaction`
--
ALTER TABLE `animalaction`
  ADD CONSTRAINT `animalaction_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`petID`) REFERENCES `pet` (`petID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cart_ibfk_3` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cart_ibfk_4` FOREIGN KEY (`petID`) REFERENCES `pet` (`petID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pet`
--
ALTER TABLE `pet`
  ADD CONSTRAINT `pet_ibfk_1` FOREIGN KEY (`AnimalID`) REFERENCES `animal` (`AnimalID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transactionheader`
--
ALTER TABLE `transactionheader`
  ADD CONSTRAINT `transactionheader_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
