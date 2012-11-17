-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 17, 2012 at 09:47 PM
-- Server version: 5.5.16
-- PHP Version: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `exceed_vote`
--

-- --------------------------------------------------------

--
-- Table structure for table `election_committee`
--

CREATE TABLE IF NOT EXISTS `election_committee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `election_committee`
--

INSERT INTO `election_committee` (`ID`, `username`, `password`) VALUES
(1, 'Tun', 'athiwatc');

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE IF NOT EXISTS `project` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `teamname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`ID`, `name`, `teamname`) VALUES
(1, 'HookHook', 'Handsome'),
(2, 'aMaze', 'Beauty'),
(3, 'Liverpool', 'Ugly');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `questions` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`ID`, `questions`) VALUES
(1, 'Which project do you like most?'),
(2, 'Which project''s design is the best?');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(25) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`ID`, `role`, `score`) VALUES
(1, 'Student', 1),
(2, 'Teacher', 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `ID` (`ID`,`username`,`password`,`role_id`),
  KEY `password` (`password`),
  KEY `username` (`username`),
  KEY `role_id` (`role_id`),
  KEY `role_id_2` (`role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `username`, `password`, `role_id`) VALUES
(1, 'Oor', '1234', 1),
(2, 'Metas', '5678', 1),
(3, 'Guysit', 'i4cu', 1),
(4, 'peepo', 'peepo', 1),
(5, 'James', 'OOP', 2);

-- --------------------------------------------------------

--
-- Table structure for table `vote`
--

CREATE TABLE IF NOT EXISTS `vote` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `datetime` timestamp NULL DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `vote_fk1_idx` (`user_id`),
  KEY `vote_fk2_idx` (`project_id`),
  KEY `vote_fk3_idx` (`question_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `vote`
--

INSERT INTO `vote` (`ID`, `user_id`, `project_id`, `question_id`, `score`, `datetime`, `comment`) VALUES
(1, 4, 1, 1, 1, '2012-10-08 14:28:33', NULL),
(2, 3, 1, 1, 1, '2012-10-08 14:58:52', NULL),
(3, 1, 1, 1, 1, '2012-10-08 15:01:54', NULL),
(4, 4, 1, 2, 1, '2012-10-08 15:09:42', NULL),
(5, 1, 3, 2, 1, '2012-10-10 04:52:00', NULL),
(6, 3, 2, 2, 1, '2012-10-11 04:30:38', NULL),
(7, 4, 2, 2, 1, '2012-10-23 20:21:51', NULL),
(8, 1, 2, 1, 1, '2012-10-23 20:25:20', NULL),
(9, 3, 3, 2, 1, '2012-11-16 08:17:24', NULL),
(10, 5, 1, 1, 1, '2012-11-16 12:04:58', NULL);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`ID`) ON UPDATE NO ACTION;

--
-- Constraints for table `vote`
--
ALTER TABLE `vote`
  ADD CONSTRAINT `vote_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `vote_fk2` FOREIGN KEY (`project_id`) REFERENCES `project` (`ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `vote_fk3` FOREIGN KEY (`question_id`) REFERENCES `question` (`ID`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
