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

-- export database structure of travel-agency-service_db
CREATE DATABASE IF NOT EXISTS `travel-agency-service_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `travel-agency-service_db`;

-- export structure of table travel-agency-service_db.address
CREATE TABLE IF NOT EXISTS `address` (
    `address_id` INT(11) NOT NULL COMMENT 'address''s unique identification number',
    `street` VARCHAR(60) COMMENT 'street name',
    `number` VARCHAR(10) COMMENT 'house number',
    `zip` VARCHAR(10) COMMENT 'zip code if existent',
    `town` VARCHAR(50) COMMENT 'town name',
    `country` VARCHAR(50) COMMENT 'country',
PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered addresses';

-- export structure of table travel-agency-service_db.personal_data
CREATE TABLE IF NOT EXISTS `personal_data` (
    `personal_data_id` INT(11) NOT NULL COMMENT 'personal_data''s unique identification number',
    `last_name` VARCHAR(40) COMMENT 'last name',
    `first_name` VARCHAR(40) COMMENT 'first name',
    `middle_name` VARCHAR(200) COMMENT 'middle name',
    `date_of_birth` DATE COMMENT 'date of birth',
    `address_id` INT(11) COMMENT 'country',
PRIMARY KEY (`personal_data_id`),
CONSTRAINT `fk_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered personal data';

-- export structure of table travel-agency-service_db.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` INT(11) NOT NULL COMMENT 'customer''s unique identification number',
  `IBAN` CHAR(34) COMMENT 'IBAN',
  `personal_data_id` INT(11) COMMENT 'unique identification number of the personal data',
  `billing_address_id` INT(11) COMMENT 'unique identification number of the billing address',
  PRIMARY KEY (`customer_id`),
  CONSTRAINT `fk_customer_data` FOREIGN KEY (`personal_data_id`) REFERENCES `personal_data` (`personal_data_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_billing_address` FOREIGN KEY (`billing_address_id`) REFERENCES `address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered customer';

-- export structure of table travel-agency-service_db.hotel
CREATE TABLE IF NOT EXISTS `hotel` (
    `hotel_id` INT(11) NOT NULL COMMENT 'hotel''s unique identification number',
    `name` VARCHAR(60) COMMENT 'name of the hotel',
    `price_per_person` DECIMAL (8,2) COMMENT 'price per person per night',
    `currency_key` CHAR(3) COMMENT 'currency to the amount above',
    `address_id` INT(11) COMMENT 'unique identification number of the hotel\'s address',
    PRIMARY KEY (`hotel_id`),
    CONSTRAINT `fk_hotel_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered hotels';

-- export structure of table travel-agency-service_db.flight_connection
CREATE TABLE IF NOT EXISTS `flight_connection` (
    `flight_connection_id` INT(11) NOT NULL COMMENT 'flight_connections''s unique identification number',
    `carrier_id` CHAR(2) COMMENT 'airline code',
    `connection_id` CHAR(4) COMMENT 'connection code',
    `departure_airport_code` CHAR(3) COMMENT 'departing airport code',
    `arrival_airport_code` CHAR(3) COMMENT 'arriving airport code',
    PRIMARY KEY (`flight_connection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered flight connections';

-- export structure of table travel-agency-service_db.flight
CREATE TABLE IF NOT EXISTS `flight` (
    `flight_id` INT(11) NOT NULL COMMENT 'flight''s unique identification number',
    `flight_connection_id` INT(11) NOT NULL COMMENT 'flight''s corresponding flight connection',
    `departure_date` DATE COMMENT 'date of departure',
    `departure_time` TIME COMMENT 'time of departure',
    `departure_time_zone` CHAR(9) COMMENT 'time zone of departing airport in format UTC+HH:MM',
    `arrival_date` DATE COMMENT 'date of arrival',
    `arrival_time` TIME COMMENT 'time of arrival',
    `arrival_time_zone` CHAR(9) COMMENT 'time zone of arriving airport in format UTC+HH:MM',
    `price_per_person` DECIMAL (8,2)COMMENT 'price per person per flight',
    `currency_key` CHAR(3) COMMENT 'currency to the amount above',
    PRIMARY KEY (`flight_id`),
    CONSTRAINT `fk_flight_connection` FOREIGN KEY (`flight_connection_id`) REFERENCES `flight_connection` (`flight_connection_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered flights';

-- grant select to demo_user
GRANT SELECT ON `flight` TO `DEMO_USER`;

-- export structure of table travel-agency-service_db.booking
CREATE TABLE IF NOT EXISTS `booking` (
    `booking_id` INT(11) NOT NULL COMMENT 'trip''s unique identification number',
    `customer_id` INT(11) NOT NULL COMMENT 'booking associated with this trip',
    `date` DATE NOT NULL COMMENT 'date of the booking',
    PRIMARY KEY (`booking_id`),
    CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered bookings';

-- export structure of table travel-agency-service_db.trip
CREATE TABLE IF NOT EXISTS `trip` (
    `trip_id` INT(11) NOT NULL COMMENT 'trip''s unique identification number',
    `booking_id` INT(11) COMMENT 'booking associated with this trip',
    PRIMARY KEY (`trip_id`),
    CONSTRAINT `fk_booking_id` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered trips';

-- export structure of table travel-agency-service_db.hotel_booking
CREATE TABLE IF NOT EXISTS `hotel_booking` (
    `hotel_booking_id` INT(11) NOT NULL COMMENT 'hotel_booking''s unique identification number',
    `trip_id` INT(11) NOT NULL COMMENT 'trip associated with the hotel booking',
    `hotel_id` INT(11) NOT NULL COMMENT 'hotel associated with the booking',
    `number_of_guests` SMALLINT NOT NULL COMMENT 'number of hotel guests',
    `number_of_nights` SMALLINT NOT NULL COMMENT 'number of nights spent at the hotel',
    PRIMARY KEY (`hotel_booking_id`),
    CONSTRAINT `fk_hotel_trip_id` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`trip_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_hotel_id` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered hotel bookings';

-- export structure of table travel-agency-service_db.flight_booking
CREATE TABLE IF NOT EXISTS `flight_booking` (
    `flight_booking_id` INT(11) NOT NULL COMMENT 'flight_booking''s unique identification number',
    `trip_id` INT(11) NOT NULL COMMENT 'trip associated with the booking',
    `flight_id` INT(11) NOT NULL COMMENT 'flight associated with the booking',
    `number_of_passengers` SMALLINT NOT NULL COMMENT 'number of flight passengers',
    PRIMARY KEY (`flight_booking_id`),
    CONSTRAINT `fk_flight_trip_id` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`trip_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_flight_id` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`flight_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered flight bookings';

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
