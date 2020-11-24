-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 23-11-2020 a las 20:01:25
-- Versión del servidor: 10.4.10-MariaDB
-- Versión de PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `music`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artista`
--

DROP TABLE IF EXISTS `artista`;
CREATE TABLE IF NOT EXISTS `artista` (
  `idArtista` int(11) NOT NULL AUTO_INCREMENT,
  `nombreArtista` varchar(1000) DEFAULT NULL,
  `nacimiento` varchar(10) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `nombreArtistaPublic` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idArtista`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=345325 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `artista`
--

INSERT INTO `artista` (`idArtista`, `nombreArtista`, `nacimiento`, `descripcion`, `id`, `nombreArtistaPublic`) VALUES
(1, 'https://kgo.googleusercontent.com/profile_vrt_raw_bytes_1587514348_2154.jpg', '08/01/1968', 'Dua Lipa es una cantante, compositora, modelo y diseñadora de moda británica de origen albano-kosovar. Inició su carrera musical a los 14 años, cuando comenzó a hacer covers de canciones de otros artistas en YouTube.', 1, 'Dua Lipa'),
(2, 'https://www.interviewmagazine.com/wp-content/uploads/2019/05/Interview_2019_Web_Summer_BadBunny-05.jpg', '08/01/1968', 'Benito Antonio Martínez Ocasio, más conocido por su nombre artístico Bad Bunny, es un cantante, rapero y trapero puertorriqueño de género reguetón y trap latino.', 1, 'Bad Bunny'),
(345321, 'https://cdn.pixabay.com/photo/2015/08/11/21/28/chayanne-885227_960_720.jpg', '08/01/1968', 'Elmer Figueroa Arce, conocido como Chayanne, es un cantante, compositor, bailarín y actor puertorriqueño. Ha vendido más de 50 millones de álbumes en todo el mundo, lo que lo convierte en uno de los artistas latinos con mayores ventas.', 2, 'Chayanne'),
(345322, 'artistas/maluma.jpg', '2020-11-12', 'Juan Luis Londoño Arias, conocido artísticamente como Maluma, es un cantante colombiano de pop, reguetón y trap latino. Saltó a la fama en su país natal en 2011, gracias a los sencillos «Farandulera» y «Obsesión», y gracias a «La temperatura» y «Carnaval», en el resto de América Latina.', 7, 'Maluma');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE IF NOT EXISTS `empleados` (
  `codigoEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `nombreEmpleado` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `usuarioEmpleado` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `contrasena` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `codigoTipoEmpleado` int(11) NOT NULL,
  `correo` varchar(70) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `imagen` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`codigoEmpleado`),
  KEY `codigoTipoEmpleado` (`codigoTipoEmpleado`)
) ENGINE=InnoDB AUTO_INCREMENT=123478 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`codigoEmpleado`, `nombreEmpleado`, `usuarioEmpleado`, `contrasena`, `codigoTipoEmpleado`, `correo`, `imagen`) VALUES
(123456, 'Carlos Eduardo', 'Carlos', '123', 1, 'carlossalazar2228@gmail.com', 'img/descarga.jpg'),
(123457, 'Bryan Arnold', 'Bryan', '123', 2, 'bryan.herrera@oportunidades.org.sv', 'img/jesse-joy-corre.jpg'),
(123459, 'Jairo José', 'Jairo', '123456', 3, 'carlossalazar2228@gmail.com', 'img/man.png'),
(123475, 'Carlos Eduardo Peñate Salazar', 'Test', '123', 3, 'carlossalazar2228@gmail.com', 'img/22347.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `generos`
--

DROP TABLE IF EXISTS `generos`;
CREATE TABLE IF NOT EXISTS `generos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombreGenero` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 CHECKSUM=1;

--
-- Volcado de datos para la tabla `generos`
--

INSERT INTO `generos` (`id`, `nombreGenero`) VALUES
(1, 'POP'),
(2, 'Bachata'),
(3, 'Clásica'),
(5, 'Pop4'),
(7, 'Electronica'),
(8, 'Banda'),
(10, 'Prueba');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `generos_paquetes`
--

DROP TABLE IF EXISTS `generos_paquetes`;
CREATE TABLE IF NOT EXISTS `generos_paquetes` (
  `GenerosEntity_ID` int(11) NOT NULL,
  `paquete_IDPAQUETE` int(11) NOT NULL,
  PRIMARY KEY (`GenerosEntity_ID`,`paquete_IDPAQUETE`),
  KEY `FK_generos_paquetes_paquete_IDPAQUETE` (`paquete_IDPAQUETE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `music`
--

DROP TABLE IF EXISTS `music`;
CREATE TABLE IF NOT EXISTS `music` (
  `idMusic` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCancion` varchar(100) DEFAULT NULL,
  `id` int(11) DEFAULT NULL,
  `audio` longblob DEFAULT NULL,
  `imagen` varchar(1000) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  `lyrics` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`idMusic`),
  KEY `music_ibfk_1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `music`
--

INSERT INTO `music` (`idMusic`, `nombreCancion`, `id`, `audio`, `imagen`, `precio`, `likes`, `lyrics`) VALUES
(28, '¡Corre!', 1, NULL, 'img/jesse-joy-corre.jpg', 12, 0, 'mp3/y2mate.com - #Jesse&Joy - ¡Corre!.mp3'),
(29, 'Levels', 1, NULL, 'img/descarga.jpg', 12, 2, 'mp3/y2mate.com - Avicii - Levels.mp3'),
(31, 'Summer', 7, NULL, 'img/hqdefault.jpg', 18, 0, 'mp3/y2mate.com - Calvin Harris - Summer (Official Video).mp3'),
(79, 'De que me sirve la vida', 1, NULL, 'img/camila.jpg', 5, 1, 'mp3/y2mate.com - Camila - De Que Me Sirve la Vida.mp3'),
(82, 'Devuélveme a mi chica', 3, NULL, 'img/22347.jpg', 3, 2, 'mp3/y2mate.com - Devuélveme a mi chica.mp3'),
(83, 'El Garrobero', 1, NULL, 'img/maxresdefault.jpg', 5, 0, 'mp3/y2mate.com - Aniceto Molina - El Garrobero.mp3'),
(84, 'Súbete a mi moto', 1, NULL, 'img/maxresdefault (1).jpg', 5, 0, 'mp3/y2mate.com - _SÚBETE A MI MOTO_ - MENUDO - 1981 (REMASTERIZADO).mp3'),
(85, 'El Machito', 10, NULL, 'img/600x600cc.jpg', 5, 0, 'mp3/y2mate.com - Aniceto Molina El Machito (Video Oficial) MASTER.mp3'),
(86, 'Don\'t start now', 7, NULL, 'img/Dua-Lipa.jpg', 5, 0, 'mp3/y2mate.com - Dua Lipa - Don\'t Start Now (Official Music Video).mp3');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nombreplaylist`
--

DROP TABLE IF EXISTS `nombreplaylist`;
CREATE TABLE IF NOT EXISTS `nombreplaylist` (
  `idNombrePlayList` int(11) NOT NULL AUTO_INCREMENT,
  `nombrePlayList` varchar(100) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`idNombrePlayList`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `nombreplaylist`
--

INSERT INTO `nombreplaylist` (`idNombrePlayList`, `nombrePlayList`, `idUser`) VALUES
(1, 'prueba', NULL),
(3, 'MixElectronica2021', NULL),
(79, 'Mix 2020', NULL),
(80, 'Sad', NULL),
(82, 'Sad 2020', NULL),
(83, 'Test', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paquetes`
--

DROP TABLE IF EXISTS `paquetes`;
CREATE TABLE IF NOT EXISTS `paquetes` (
  `idPaquete` int(11) NOT NULL AUTO_INCREMENT,
  `nombrePaquete` varchar(150) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `id` int(11) DEFAULT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  `fechaPublicacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idPaquete`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `paquetes`
--

INSERT INTO `paquetes` (`idPaquete`, `nombrePaquete`, `precio`, `id`, `descripcion`, `fechaPublicacion`) VALUES
(7, 'Paquete de Clásica', 1, 3, 'Con este paquete podrás comprar toda las canciones del género clásico', NULL),
(8, 'Paquete de Pop', 4, 3, 'Con este paquete podrás comprar toda las canciones del género pop', 'Wed Nov 11 12:00:00 CST 2020'),
(9, 'Paquete de Bachata', 4, 9, '4', 'Thu Nov 19 12:00:00 CST 2020'),
(10, 'Cristiana', 6, 9, 'Con este paquete podrás comprar toda las canciones del género Cristiana', 'Tue Nov 10 12:00:00 CST 2020');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paquetes_generos`
--

DROP TABLE IF EXISTS `paquetes_generos`;
CREATE TABLE IF NOT EXISTS `paquetes_generos` (
  `PaquetesEntity_IDPAQUETE` int(11) NOT NULL,
  `genero_ID` int(11) NOT NULL,
  PRIMARY KEY (`PaquetesEntity_IDPAQUETE`,`genero_ID`),
  KEY `FK_paquetes_generos_genero_ID` (`genero_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `playlist`
--

DROP TABLE IF EXISTS `playlist`;
CREATE TABLE IF NOT EXISTS `playlist` (
  `idPlayList` int(11) NOT NULL AUTO_INCREMENT,
  `idMusic` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  `idNombrePlayList` int(11) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idPlayList`),
  KEY `idMusic` (`idMusic`),
  KEY `idUser` (`idUser`),
  KEY `idNombrePlayList` (`idNombrePlayList`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `playlist`
--

INSERT INTO `playlist` (`idPlayList`, `idMusic`, `idUser`, `idNombrePlayList`, `estado`) VALUES
(1, 28, 123459, 1, 'activo'),
(2, 28, 123456, 1, 'activo'),
(3, 31, 123459, 82, 'Activo'),
(4, 79, 123459, 83, 'Activo'),
(5, 82, 123459, 80, 'Activo'),
(6, 85, 123459, 79, 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol_usuario`
--

DROP TABLE IF EXISTS `rol_usuario`;
CREATE TABLE IF NOT EXISTS `rol_usuario` (
  `IdU` int(11) NOT NULL,
  `Tip_usuario` varchar(50) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `rol_usuario`
--

INSERT INTO `rol_usuario` (`IdU`, `Tip_usuario`) VALUES
(1, 'Administrador'),
(2, 'Usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoempleados`
--

DROP TABLE IF EXISTS `tipoempleados`;
CREATE TABLE IF NOT EXISTS `tipoempleados` (
  `codigoTipoEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `nombreTipoEmpleado` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`codigoTipoEmpleado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tipoempleados`
--

INSERT INTO `tipoempleados` (`codigoTipoEmpleado`, `nombreTipoEmpleado`) VALUES
(1, 'Administrador'),
(2, 'Administrador de canciones'),
(3, 'Cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(100) COLLATE utf8_bin NOT NULL,
  `Apellido` varchar(100) COLLATE utf8_bin NOT NULL,
  `Tipo_usuario` int(11) NOT NULL,
  `Correo` varchar(50) COLLATE utf8_bin NOT NULL,
  `Contrasena` varchar(50) COLLATE utf8_bin NOT NULL,
  KEY `fk_tipos_usuarios` (`Tipo_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`Id`, `Nombre`, `Apellido`, `Tipo_usuario`, `Correo`, `Contrasena`) VALUES
(1, 'Javier Alberto', 'Gomez NAVARRETE', 1, 'jairo', 'hola123'),
(2, 'rOBERTO', 'Gonzalez', 2, 'carlos', 'hola321');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE IF NOT EXISTS `ventas` (
  `idVenta` int(11) NOT NULL AUTO_INCREMENT,
  `idMusic` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  `idPaquete` int(11) DEFAULT NULL,
  `fechaVenta` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idVenta`),
  KEY `idMusic` (`idMusic`),
  KEY `idUser` (`idUser`),
  KEY `idPaquete` (`idPaquete`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`idVenta`, `idMusic`, `idUser`, `idPaquete`, `fechaVenta`) VALUES
(1, 28, 123459, NULL, '11/20/2020'),
(2, 29, 123459, NULL, '11/20/2020'),
(3, 29, 123459, NULL, '11/20/2020'),
(4, 31, 123459, NULL, '11/20/2020'),
(5, 82, 123459, NULL, '11/21/2020'),
(6, 79, 123459, NULL, '11/21/2020'),
(7, NULL, 123459, 7, '11/21/2020'),
(8, 83, 123459, NULL, '11/23/2020'),
(9, 84, 123459, NULL, '11/23/2020');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `nombreplaylist`
--
ALTER TABLE `nombreplaylist`
  ADD CONSTRAINT `nombreplaylist_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `empleados` (`codigoEmpleado`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
