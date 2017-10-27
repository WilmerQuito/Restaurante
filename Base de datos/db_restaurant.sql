/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.17-log : Database - db_restaurante
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_restaurante` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_restaurante`;

/*Table structure for table `almacen` */

DROP TABLE IF EXISTS `almacen`;

CREATE TABLE `almacen` (
  `Cod_Almacen` char(10) NOT NULL,
  `Descripcion` varchar(150) NOT NULL,
  `Cod_Restaurante` char(10) NOT NULL,
  PRIMARY KEY (`Cod_Almacen`),
  KEY `Cod_Restaurante` (`Cod_Restaurante`),
  CONSTRAINT `almacen_ibfk_1` FOREIGN KEY (`Cod_Restaurante`) REFERENCES `restaurante` (`Cod_Restaurante`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `almacen` */

insert  into `almacen`(`Cod_Almacen`,`Descripcion`,`Cod_Restaurante`) values ('A','PRIMER ALMACEN','L'),('A-1','PRIMER ALMACEN','E'),('A-2','SEGUNDO ALMACEN','E');

/*Table structure for table `bebida` */

DROP TABLE IF EXISTS `bebida`;

CREATE TABLE `bebida` (
  `Cod_Bebida` char(10) NOT NULL,
  `Descripcion` varchar(100) NOT NULL,
  `Costo` double(8,2) NOT NULL,
  PRIMARY KEY (`Cod_Bebida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bebida` */

/*Table structure for table `cliente` */

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `Cod_Cliente` char(10) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `DNI` char(8) NOT NULL,
  `Celular` char(9) DEFAULT NULL,
  PRIMARY KEY (`Cod_Cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cliente` */

insert  into `cliente`(`Cod_Cliente`,`Nombre`,`DNI`,`Celular`) values ('1059754864','CRISTIAN MORENO','59754864','994651364'),('1065684564','MARIO VEGA','65684564','959684153'),('1089684564','FAVIO SOLIS','89684564','984651354');

/*Table structure for table `comida` */

DROP TABLE IF EXISTS `comida`;

CREATE TABLE `comida` (
  `Cod_Comida` char(10) NOT NULL,
  `Descripcion` varchar(100) NOT NULL,
  `Costo` double(8,2) NOT NULL,
  PRIMARY KEY (`Cod_Comida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comida` */

/*Table structure for table `comprobante` */

DROP TABLE IF EXISTS `comprobante`;

CREATE TABLE `comprobante` (
  `Cod_Comprobante` char(10) NOT NULL,
  `RUC` char(11) DEFAULT NULL,
  `Direccion` varchar(100) DEFAULT NULL,
  `Fecha` date NOT NULL,
  `Detalle` varchar(250) DEFAULT NULL,
  `Monto` double(8,2) NOT NULL,
  `Cod_Tipo` char(10) NOT NULL,
  `Cod_Cliente` char(10) NOT NULL,
  PRIMARY KEY (`Cod_Comprobante`),
  KEY `Cod_Tipo` (`Cod_Tipo`),
  KEY `Cod_Cliente` (`Cod_Cliente`),
  CONSTRAINT `comprobante_ibfk_1` FOREIGN KEY (`Cod_Tipo`) REFERENCES `tipocomprobante` (`Cod_Tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `comprobante_ibfk_2` FOREIGN KEY (`Cod_Cliente`) REFERENCES `cliente` (`Cod_Cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comprobante` */

/*Table structure for table `consumicion` */

DROP TABLE IF EXISTS `consumicion`;

CREATE TABLE `consumicion` (
  `Cod_Consumicion` char(10) NOT NULL,
  `Cantidad` int(3) NOT NULL,
  `Cod_Bebida` char(10) DEFAULT NULL,
  `Cod_Comida` char(10) DEFAULT NULL,
  PRIMARY KEY (`Cod_Consumicion`),
  KEY `Cod_Bebida` (`Cod_Bebida`),
  KEY `Cod_Comida` (`Cod_Comida`),
  CONSTRAINT `consumicion_ibfk_1` FOREIGN KEY (`Cod_Bebida`) REFERENCES `bebida` (`Cod_Bebida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `consumicion_ibfk_2` FOREIGN KEY (`Cod_Comida`) REFERENCES `comida` (`Cod_Comida`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `consumicion` */

/*Table structure for table `estado` */

DROP TABLE IF EXISTS `estado`;

CREATE TABLE `estado` (
  `Cod_Estado` char(10) NOT NULL,
  `Estado` varchar(30) NOT NULL,
  PRIMARY KEY (`Cod_Estado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `estado` */

insert  into `estado`(`Cod_Estado`,`Estado`) values ('E','ESPERA'),('R','RESERVADO'),('S','SIRVIENDO');

/*Table structure for table `ingredientes` */

DROP TABLE IF EXISTS `ingredientes`;

CREATE TABLE `ingredientes` (
  `Cod_Ingredientes` char(10) NOT NULL,
  `Descripcion` varchar(100) NOT NULL,
  `Cant_Disponible` int(3) NOT NULL,
  `Cod_Almacen` char(10) NOT NULL,
  PRIMARY KEY (`Cod_Ingredientes`),
  KEY `Cod_Almacen` (`Cod_Almacen`),
  CONSTRAINT `ingredientes_ibfk_1` FOREIGN KEY (`Cod_Almacen`) REFERENCES `almacen` (`Cod_Almacen`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ingredientes` */

/*Table structure for table `mesa` */

DROP TABLE IF EXISTS `mesa`;

CREATE TABLE `mesa` (
  `Cod_Mesa` char(10) NOT NULL,
  `Cant_Personas` int(2) NOT NULL,
  `Num_Mesa` int(2) NOT NULL,
  `Fumador` char(20) NOT NULL,
  `Cod_Estado` char(10) NOT NULL,
  `Cod_Ubicacion` char(10) NOT NULL,
  `Cod_Restaurante` char(10) NOT NULL,
  PRIMARY KEY (`Cod_Mesa`),
  KEY `Cod_Restaurante` (`Cod_Restaurante`),
  KEY `Cod_Estado` (`Cod_Estado`),
  KEY `Cod_Ubicacion` (`Cod_Ubicacion`),
  CONSTRAINT `mesa_ibfk_1` FOREIGN KEY (`Cod_Restaurante`) REFERENCES `restaurante` (`Cod_Restaurante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `mesa_ibfk_2` FOREIGN KEY (`Cod_Estado`) REFERENCES `estado` (`Cod_Estado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `mesa_ibfk_3` FOREIGN KEY (`Cod_Ubicacion`) REFERENCES `ubicacion` (`Cod_Ubicacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mesa` */

insert  into `mesa`(`Cod_Mesa`,`Cant_Personas`,`Num_Mesa`,`Fumador`,`Cod_Estado`,`Cod_Ubicacion`,`Cod_Restaurante`) values ('M',1,1,'SI','E','D','L'),('M-1',1,1,'NO','R','C','E');

/*Table structure for table `pedido` */

DROP TABLE IF EXISTS `pedido`;

CREATE TABLE `pedido` (
  `Cod_Pedido` char(10) NOT NULL,
  `Estado` char(50) NOT NULL,
  `Hora` time NOT NULL,
  `Cod_Consumicion` char(10) NOT NULL,
  `Cod_Mesa` char(10) NOT NULL,
  PRIMARY KEY (`Cod_Pedido`),
  KEY `Cod_Consumicion` (`Cod_Consumicion`),
  KEY `Cod_Mesa` (`Cod_Mesa`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`Cod_Consumicion`) REFERENCES `consumicion` (`Cod_Consumicion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`Cod_Mesa`) REFERENCES `mesa` (`Cod_Mesa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pedido` */

/*Table structure for table `reserva` */

DROP TABLE IF EXISTS `reserva`;

CREATE TABLE `reserva` (
  `Cod_Reserva` char(10) NOT NULL,
  `Hora` time NOT NULL,
  `Fecha` date NOT NULL,
  `Cant_Personas` int(2) NOT NULL,
  `Detalle` varchar(250) DEFAULT NULL,
  `Cod_Cliente` char(10) NOT NULL,
  `Cod_Mesa` char(10) NOT NULL,
  PRIMARY KEY (`Cod_Reserva`),
  KEY `Cod_Cliente` (`Cod_Cliente`),
  KEY `Cod_Mesa` (`Cod_Mesa`),
  CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`Cod_Cliente`) REFERENCES `cliente` (`Cod_Cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`Cod_Mesa`) REFERENCES `mesa` (`Cod_Mesa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reserva` */

/*Table structure for table `restaurante` */

DROP TABLE IF EXISTS `restaurante`;

CREATE TABLE `restaurante` (
  `Cod_Restaurante` char(10) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Direccion` varchar(100) NOT NULL,
  `Telefono` char(9) NOT NULL,
  PRIMARY KEY (`Cod_Restaurante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `restaurante` */

insert  into `restaurante`(`Cod_Restaurante`,`Nombre`,`Direccion`,`Telefono`) values ('E','EL PAICHE II','AV. 27 DE NOVIEMBRE 1285','943587152'),('L','LA DELICIA','AV LUZUARIAGA 1125','943587545');

/*Table structure for table `rol` */

DROP TABLE IF EXISTS `rol`;

CREATE TABLE `rol` (
  `Cod_Rol` char(10) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`Cod_Rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rol` */

insert  into `rol`(`Cod_Rol`,`Nombre`) values ('A','ADMINISTRADOR'),('A_1','AGENTE'),('C','CAJERO'),('M','MOZO'),('S','SEGURIDAD');

/*Table structure for table `tipocomprobante` */

DROP TABLE IF EXISTS `tipocomprobante`;

CREATE TABLE `tipocomprobante` (
  `Cod_Tipo` char(10) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`Cod_Tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tipocomprobante` */

/*Table structure for table `ubicacion` */

DROP TABLE IF EXISTS `ubicacion`;

CREATE TABLE `ubicacion` (
  `Cod_Ubicacion` char(10) NOT NULL,
  `Ubicacion` varchar(30) NOT NULL,
  PRIMARY KEY (`Cod_Ubicacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ubicacion` */

insert  into `ubicacion`(`Cod_Ubicacion`,`Ubicacion`) values ('C','CENTRO'),('D','DERECHA'),('E','ENTRADA'),('F','FONDO'),('I','IZQUIERDA');

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `Cod_Usuario` char(10) NOT NULL,
  `Usuario` varchar(30) NOT NULL,
  `Clave` varchar(30) NOT NULL,
  `Cod_Rol` char(10) NOT NULL,
  PRIMARY KEY (`Cod_Usuario`),
  KEY `Cod_Rol` (`Cod_Rol`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`Cod_Rol`) REFERENCES `rol` (`Cod_Rol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usuario` */

insert  into `usuario`(`Cod_Usuario`,`Usuario`,`Clave`,`Cod_Rol`) values ('W-1','WILMER QUITO','123456789','A');

/*Table structure for table `vtaalmacen` */

DROP TABLE IF EXISTS `vtaalmacen`;

/*!50001 DROP VIEW IF EXISTS `vtaalmacen` */;
/*!50001 DROP TABLE IF EXISTS `vtaalmacen` */;

/*!50001 CREATE TABLE  `vtaalmacen`(
 `Cod_Almacen` char(10) ,
 `Nombre` varchar(100) ,
 `Descripcion` varchar(150) 
)*/;

/*Table structure for table `vtamesa` */

DROP TABLE IF EXISTS `vtamesa`;

/*!50001 DROP VIEW IF EXISTS `vtamesa` */;
/*!50001 DROP TABLE IF EXISTS `vtamesa` */;

/*!50001 CREATE TABLE  `vtamesa`(
 `Cod_Mesa` char(10) ,
 `Nombre` varchar(100) ,
 `Num_Mesa` int(2) ,
 `Cant_Personas` int(2) ,
 `Fumador` char(20) ,
 `Ubicacion` varchar(30) ,
 `Estado` varchar(30) 
)*/;

/*Table structure for table `vtasesion` */

DROP TABLE IF EXISTS `vtasesion`;

/*!50001 DROP VIEW IF EXISTS `vtasesion` */;
/*!50001 DROP TABLE IF EXISTS `vtasesion` */;

/*!50001 CREATE TABLE  `vtasesion`(
 `Cod_Usuario` char(10) ,
 `Usuario` varchar(30) ,
 `Nombre` varchar(30) ,
 `Clave` varchar(30) 
)*/;

/*View structure for view vtaalmacen */

/*!50001 DROP TABLE IF EXISTS `vtaalmacen` */;
/*!50001 DROP VIEW IF EXISTS `vtaalmacen` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtaalmacen` AS select `almacen`.`Cod_Almacen` AS `Cod_Almacen`,`restaurante`.`Nombre` AS `Nombre`,`almacen`.`Descripcion` AS `Descripcion` from (`almacen` join `restaurante` on((`almacen`.`Cod_Restaurante` = `restaurante`.`Cod_Restaurante`))) */;

/*View structure for view vtamesa */

/*!50001 DROP TABLE IF EXISTS `vtamesa` */;
/*!50001 DROP VIEW IF EXISTS `vtamesa` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtamesa` AS select `mesa`.`Cod_Mesa` AS `Cod_Mesa`,`restaurante`.`Nombre` AS `Nombre`,`mesa`.`Num_Mesa` AS `Num_Mesa`,`mesa`.`Cant_Personas` AS `Cant_Personas`,`mesa`.`Fumador` AS `Fumador`,`ubicacion`.`Ubicacion` AS `Ubicacion`,`estado`.`Estado` AS `Estado` from (((`mesa` join `estado`) join `ubicacion`) join `restaurante` on(((`mesa`.`Cod_Estado` = `estado`.`Cod_Estado`) and (`mesa`.`Cod_Ubicacion` = `ubicacion`.`Cod_Ubicacion`) and (`mesa`.`Cod_Restaurante` = `restaurante`.`Cod_Restaurante`)))) */;

/*View structure for view vtasesion */

/*!50001 DROP TABLE IF EXISTS `vtasesion` */;
/*!50001 DROP VIEW IF EXISTS `vtasesion` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtasesion` AS select `usuario`.`Cod_Usuario` AS `Cod_Usuario`,`usuario`.`Usuario` AS `Usuario`,`rol`.`Nombre` AS `Nombre`,`usuario`.`Clave` AS `Clave` from (`usuario` join `rol` on((`usuario`.`Cod_Rol` = `rol`.`Cod_Rol`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
