-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Máj 22. 14:30
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

DROP TABLE IF EXISTS `alaplap`;
CREATE TABLE `alaplap` (
  `id` int(11) NOT NULL,
  `gyarto` varchar(150) NOT NULL,
  `foglalat` varchar(10) NOT NULL,
  `ram_slotok_szama` int(2) NOT NULL,
  `ram_tipus` varchar(4) NOT NULL,
  `integralt_gpu` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `asztali_pc`
--

DROP TABLE IF EXISTS `asztali_pc`;
CREATE TABLE `asztali_pc` (
  `id` int(11) NOT NULL,
  `alaplap_id` int(11) NOT NULL,
  `cpu_id` int(11) NOT NULL,
  `ram_id` int(11) NOT NULL,
  `gpu_id` int(11) NOT NULL,
  `hattertar_id` int(11) NOT NULL,
  `tap_id` int(11) NOT NULL,
  `gephaz_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `cpu`
--

DROP TABLE IF EXISTS `cpu`;
CREATE TABLE `cpu` (
  `id` int(11) NOT NULL,
  `gyarto` varchar(5) NOT NULL,
  `tipus` varchar(100) NOT NULL,
  `foglalat` varchar(10) NOT NULL,
  `orajel` double NOT NULL,
  `tuning_e` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `gephaz`
--

DROP TABLE IF EXISTS `gephaz`;
CREATE TABLE `gephaz` (
  `id` int(11) NOT NULL,
  `gyarto` varchar(30) NOT NULL,
  `nev` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `gpu`
--

DROP TABLE IF EXISTS `gpu`;
CREATE TABLE `gpu` (
  `id` int(11) NOT NULL,
  `gyarto` varchar(10) NOT NULL,
  `orajel` int(11) NOT NULL,
  `vram` int(3) NOT NULL,
  `hdmi_szama` int(2) NOT NULL,
  `dp_szama` int(2) NOT NULL,
  `dvi_szama` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `hattertar`
--

DROP TABLE IF EXISTS `hattertar`;
CREATE TABLE `hattertar` (
  `id` int(11) NOT NULL,
  `gyarto` varchar(30) NOT NULL,
  `meret` int(6) NOT NULL,
  `ssd_e` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `laptop`
--

DROP TABLE IF EXISTS `laptop`;
CREATE TABLE `laptop` (
  `id` int(11) NOT NULL,
  `alaplap_id` int(11) NOT NULL,
  `cpu_id` int(11) NOT NULL,
  `ram_id` int(11) NOT NULL,
  `gpu_id` int(11) NOT NULL,
  `hattertar_id` int(11) NOT NULL,
  `monitor_id` int(11) NOT NULL,
  `billentyuzet` int(11) NOT NULL,
  `touchpad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `monitor`
--

DROP TABLE IF EXISTS `monitor`;
CREATE TABLE `monitor` (
  `id` int(11) NOT NULL,
  `gyarto` varchar(150) NOT NULL,
  `atmero` double NOT NULL,
  `felbontas` varchar(12) NOT NULL,
  `hdmi_szama` int(2) NOT NULL,
  `dp_szama` int(2) NOT NULL,
  `dvi_szama` int(2) NOT NULL,
  `integralt_e` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `ram`
--

DROP TABLE IF EXISTS `ram`;
CREATE TABLE `ram` (
  `id` int(11) NOT NULL,
  `gyarto` varchar(10) NOT NULL,
  `ram_tipus` varchar(4) NOT NULL,
  `orajel` int(11) NOT NULL,
  `meret` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `tap`
--

DROP TABLE IF EXISTS `tap`;
CREATE TABLE `tap` (
  `id` int(11) NOT NULL,
  `gyarto` varchar(30) NOT NULL,
  `teljesitmeny` int(4) NOT NULL,
  `modularis_e` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `alaplap`
--
ALTER TABLE `alaplap`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `asztali_pc`
--
ALTER TABLE `asztali_pc`
  ADD PRIMARY KEY (`id`),
  ADD KEY `alaplap_id` (`alaplap_id`),
  ADD KEY `cpu_id` (`cpu_id`),
  ADD KEY `ram_id` (`ram_id`),
  ADD KEY `gpu_id` (`gpu_id`),
  ADD KEY `hattertar_id` (`hattertar_id`),
  ADD KEY `tap_id` (`tap_id`),
  ADD KEY `gephaz_id` (`gephaz_id`);

--
-- A tábla indexei `cpu`
--
ALTER TABLE `cpu`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `gephaz`
--
ALTER TABLE `gephaz`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `gpu`
--
ALTER TABLE `gpu`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `hattertar`
--
ALTER TABLE `hattertar`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `laptop`
--
ALTER TABLE `laptop`
  ADD PRIMARY KEY (`id`),
  ADD KEY `alaplap_id` (`alaplap_id`),
  ADD KEY `cpu_id` (`cpu_id`),
  ADD KEY `ram_id` (`ram_id`),
  ADD KEY `gpu_id` (`gpu_id`),
  ADD KEY `hattertar_id` (`hattertar_id`),
  ADD KEY `monitor_id` (`monitor_id`);

--
-- A tábla indexei `monitor`
--
ALTER TABLE `monitor`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `ram`
--
ALTER TABLE `ram`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `tap`
--
ALTER TABLE `tap`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `alaplap`
--
ALTER TABLE `alaplap`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `asztali_pc`
--
ALTER TABLE `asztali_pc`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `cpu`
--
ALTER TABLE `cpu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `gephaz`
--
ALTER TABLE `gephaz`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `gpu`
--
ALTER TABLE `gpu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `hattertar`
--
ALTER TABLE `hattertar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `laptop`
--
ALTER TABLE `laptop`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `monitor`
--
ALTER TABLE `monitor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `ram`
--
ALTER TABLE `ram`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `tap`
--
ALTER TABLE `tap`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `asztali_pc`
--
ALTER TABLE `asztali_pc`
  ADD CONSTRAINT `asztali_pc_ibfk_1` FOREIGN KEY (`alaplap_id`) REFERENCES `alaplap` (`id`),
  ADD CONSTRAINT `asztali_pc_ibfk_2` FOREIGN KEY (`cpu_id`) REFERENCES `cpu` (`id`),
  ADD CONSTRAINT `asztali_pc_ibfk_3` FOREIGN KEY (`ram_id`) REFERENCES `ram` (`id`),
  ADD CONSTRAINT `asztali_pc_ibfk_4` FOREIGN KEY (`gpu_id`) REFERENCES `gpu` (`id`),
  ADD CONSTRAINT `asztali_pc_ibfk_5` FOREIGN KEY (`hattertar_id`) REFERENCES `hattertar` (`id`),
  ADD CONSTRAINT `asztali_pc_ibfk_6` FOREIGN KEY (`tap_id`) REFERENCES `tap` (`id`),
  ADD CONSTRAINT `asztali_pc_ibfk_7` FOREIGN KEY (`gephaz_id`) REFERENCES `gephaz` (`id`);

--
-- Megkötések a táblához `laptop`
--
ALTER TABLE `laptop`
  ADD CONSTRAINT `laptop_ibfk_1` FOREIGN KEY (`alaplap_id`) REFERENCES `alaplap` (`id`),
  ADD CONSTRAINT `laptop_ibfk_2` FOREIGN KEY (`cpu_id`) REFERENCES `cpu` (`id`),
  ADD CONSTRAINT `laptop_ibfk_3` FOREIGN KEY (`ram_id`) REFERENCES `ram` (`id`),
  ADD CONSTRAINT `laptop_ibfk_4` FOREIGN KEY (`gpu_id`) REFERENCES `gpu` (`id`),
  ADD CONSTRAINT `laptop_ibfk_5` FOREIGN KEY (`hattertar_id`) REFERENCES `hattertar` (`id`),
  ADD CONSTRAINT `laptop_ibfk_6` FOREIGN KEY (`monitor_id`) REFERENCES `monitor` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
