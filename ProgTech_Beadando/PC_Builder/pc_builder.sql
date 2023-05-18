-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Máj 18. 19:22
-- Kiszolgáló verziója: 10.4.27-MariaDB
-- PHP verzió: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `pc_builder`
--
CREATE DATABASE IF NOT EXISTS `pc_builder` DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;
USE `pc_builder`;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `alaplap`
--

CREATE TABLE IF NOT EXISTS `alaplap` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alaplap_nev` varchar(150) NOT NULL,
  `foglalat` varchar(10) NOT NULL,
  `ram_slotok_szama` int(2) NOT NULL,
  `ram_tipus` varchar(4) NOT NULL,
  `tuning_e` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `cpu`
--

CREATE TABLE IF NOT EXISTS `cpu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cpu_nev` varchar(150) NOT NULL,
  `foglalat` varchar(10) NOT NULL,
  `orajel` double NOT NULL,
  `tuning_e` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `gpu`
--

CREATE TABLE IF NOT EXISTS `gpu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gpu_nev` varchar(150) NOT NULL,
  `orajel` int(11) NOT NULL,
  `van_e_hdmi` tinyint(1) NOT NULL,
  `van_e_dp` tinyint(1) NOT NULL,
  `van_e_dvi` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `monitor`
--

CREATE TABLE IF NOT EXISTS `monitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `monitor_nev` varchar(150) NOT NULL,
  `atmero` double NOT NULL,
  `van_e_hdmi` tinyint(1) NOT NULL,
  `van_e_dp` tinyint(1) NOT NULL,
  `van_e_dvi` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `ram`
--

CREATE TABLE IF NOT EXISTS `ram` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ram_nev` varchar(150) NOT NULL,
  `ram_tipus` varchar(4) NOT NULL,
  `orajel` int(11) NOT NULL,
  `tuning_e` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
