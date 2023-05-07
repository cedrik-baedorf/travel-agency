-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               10.10.2-MariaDB - mariadb.org binary distribution
-- Server Betriebssystem:        Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- create demo user for database
CREATE USER IF NOT EXISTS `DEMO_USER` IDENTIFIED BY 'PASSWORD';

-- grant select to demo_user
GRANT SELECT ON `address` TO `DEMO_USER`;

-- grant select to demo_user
GRANT SELECT ON `personal_data` TO `DEMO_USER`;

-- grant select to demo_user
GRANT SELECT ON `customer` TO `DEMO_USER`;

-- grant select to demo_user
GRANT SELECT ON `hotel` TO `DEMO_USER`;

-- grant select to demo_user
GRANT SELECT ON `flight_connection` TO `DEMO_USER`;

-- grant select to demo_user
GRANT SELECT ON `flight` TO `DEMO_USER`;

-- grant select to demo_user
GRANT SELECT ON `booking` TO `DEMO_USER`;

-- grant select to demo_user
GRANT SELECT ON `trip` TO `DEMO_USER`;

-- grant select to demo_user
GRANT SELECT ON `hotel_booking` TO `DEMO_USER`;

-- grant select to demo_user
GRANT SELECT ON `flight_booking` TO `DEMO_USER`;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
