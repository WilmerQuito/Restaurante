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
  `cod_almacen` char(10) NOT NULL,
  `ingredientes_cod_ingredientes` char(10) DEFAULT NULL,
  `bebidas_idbebidas` char(10) DEFAULT NULL,
  `cantidad` varchar(45) NOT NULL,
  `fechaexp` date NOT NULL,
  `unidades_medida_idunidades_medida` char(10) NOT NULL,
  `tamaño_medida` varchar(45) DEFAULT NULL,
  `cod_restaurante` char(10) NOT NULL,
  `usuario_cod_usuario` char(10) NOT NULL,
  PRIMARY KEY (`cod_almacen`),
  KEY `ingredientes_cod_ingredientes` (`ingredientes_cod_ingredientes`),
  KEY `bebidas_idbebidas` (`bebidas_idbebidas`),
  KEY `unidades_medida_idunidades_medida` (`unidades_medida_idunidades_medida`),
  KEY `cod_restaurante` (`cod_restaurante`),
  KEY `usuario_cod_usuario` (`usuario_cod_usuario`),
  CONSTRAINT `almacen_ibfk_1` FOREIGN KEY (`ingredientes_cod_ingredientes`) REFERENCES `ingredientes` (`cod_ingredientes`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `almacen_ibfk_2` FOREIGN KEY (`bebidas_idbebidas`) REFERENCES `bebidas` (`idbebidas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `almacen_ibfk_3` FOREIGN KEY (`unidades_medida_idunidades_medida`) REFERENCES `unidades_medida` (`idunidades_medida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `almacen_ibfk_4` FOREIGN KEY (`cod_restaurante`) REFERENCES `restaurante` (`cod_restaurante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `almacen_ibfk_5` FOREIGN KEY (`usuario_cod_usuario`) REFERENCES `usuario` (`cod_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `almacen` */

/*Table structure for table `bebidas` */

DROP TABLE IF EXISTS `bebidas`;

CREATE TABLE `bebidas` (
  `idbebidas` char(10) NOT NULL,
  `tipobebida_idtipobebida` char(10) NOT NULL,
  `marca_idmarca` char(10) DEFAULT NULL,
  `sabor_idsabor` char(10) DEFAULT NULL,
  `costo` double(8,2) NOT NULL,
  PRIMARY KEY (`idbebidas`),
  KEY `tipobebida_idtipobebida` (`tipobebida_idtipobebida`),
  KEY `marca_idmarca` (`marca_idmarca`),
  KEY `sabor_idsabor` (`sabor_idsabor`),
  CONSTRAINT `bebidas_ibfk_1` FOREIGN KEY (`tipobebida_idtipobebida`) REFERENCES `tipobebida` (`idtipobebida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `bebidas_ibfk_2` FOREIGN KEY (`marca_idmarca`) REFERENCES `marca` (`idmarca`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `bebidas_ibfk_3` FOREIGN KEY (`sabor_idsabor`) REFERENCES `sabor` (`idsabor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bebidas` */

/*Table structure for table `cliente` */

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `cod_cliente` char(10) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `dni` char(8) NOT NULL,
  `celular` char(9) DEFAULT NULL,
  PRIMARY KEY (`cod_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cliente` */

/*Table structure for table `color` */

DROP TABLE IF EXISTS `color`;

CREATE TABLE `color` (
  `idcolor` char(10) NOT NULL,
  `color` varchar(45) NOT NULL,
  PRIMARY KEY (`idcolor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `color` */

/*Table structure for table `comida` */

DROP TABLE IF EXISTS `comida`;

CREATE TABLE `comida` (
  `idcomida` char(10) NOT NULL,
  `nombre_comida` varchar(45) NOT NULL,
  `costo` double(8,2) NOT NULL,
  `origen_idorigen` char(10) NOT NULL,
  PRIMARY KEY (`idcomida`),
  KEY `origen_idorigen` (`origen_idorigen`),
  CONSTRAINT `comida_ibfk_1` FOREIGN KEY (`origen_idorigen`) REFERENCES `origen` (`idorigen`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comida` */

/*Table structure for table `comprobante` */

DROP TABLE IF EXISTS `comprobante`;

CREATE TABLE `comprobante` (
  `cod_comprobante` char(10) NOT NULL,
  `ruc` char(11) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `fecha` date NOT NULL,
  `monto` double(8,2) NOT NULL,
  `cod_tipo` char(10) NOT NULL,
  `cod_cliente` char(10) NOT NULL,
  `usuario_cod_usuario` char(10) NOT NULL,
  `consumicion_cod_consumicion` char(10) NOT NULL,
  PRIMARY KEY (`cod_comprobante`),
  KEY `cod_tipo` (`cod_tipo`),
  KEY `cod_cliente` (`cod_cliente`),
  KEY `usuario_cod_usuario` (`usuario_cod_usuario`),
  KEY `consumicion_cod_consumicion` (`consumicion_cod_consumicion`),
  CONSTRAINT `comprobante_ibfk_1` FOREIGN KEY (`cod_tipo`) REFERENCES `tipocomprobante` (`cod_tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `comprobante_ibfk_2` FOREIGN KEY (`cod_cliente`) REFERENCES `cliente` (`cod_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `comprobante_ibfk_3` FOREIGN KEY (`usuario_cod_usuario`) REFERENCES `usuario` (`cod_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `comprobante_ibfk_4` FOREIGN KEY (`consumicion_cod_consumicion`) REFERENCES `consumicion` (`cod_consumicion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comprobante` */

/*Table structure for table `consumicion` */

DROP TABLE IF EXISTS `consumicion`;

CREATE TABLE `consumicion` (
  `cod_consumicion` char(10) NOT NULL,
  `comida_idcomida` char(10) DEFAULT NULL,
  `bebidas_idbebidas` char(10) DEFAULT NULL,
  `cantidad` int(3) NOT NULL,
  `pedido_cod_pedido` char(10) NOT NULL,
  PRIMARY KEY (`cod_consumicion`),
  KEY `comida_idcomida` (`comida_idcomida`),
  KEY `bebidas_idbebidas` (`bebidas_idbebidas`),
  KEY `pedido_cod_pedido` (`pedido_cod_pedido`),
  CONSTRAINT `consumicion_ibfk_1` FOREIGN KEY (`comida_idcomida`) REFERENCES `comida` (`idcomida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `consumicion_ibfk_2` FOREIGN KEY (`bebidas_idbebidas`) REFERENCES `bebidas` (`idbebidas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `consumicion_ibfk_3` FOREIGN KEY (`pedido_cod_pedido`) REFERENCES `pedido` (`cod_pedido`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `consumicion` */

/*Table structure for table `empleado` */

DROP TABLE IF EXISTS `empleado`;

CREATE TABLE `empleado` (
  `idempleado` char(10) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `dni` int(11) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  PRIMARY KEY (`idempleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `empleado` */

insert  into `empleado`(`idempleado`,`nombre`,`dni`,`telefono`,`direccion`) values ('1035874584','ADMINISTRADOR',35874584,'943187153','AV. 27 DE NOVIEMBRE 694');

/*Table structure for table `estado` */

DROP TABLE IF EXISTS `estado`;

CREATE TABLE `estado` (
  `cod_estado` char(10) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`cod_estado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `estado` */

/*Table structure for table `estadopedido` */

DROP TABLE IF EXISTS `estadopedido`;

CREATE TABLE `estadopedido` (
  `idestadopedido` char(10) NOT NULL,
  `estado` varchar(45) NOT NULL,
  PRIMARY KEY (`idestadopedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `estadopedido` */

/*Table structure for table `ingredientes` */

DROP TABLE IF EXISTS `ingredientes`;

CREATE TABLE `ingredientes` (
  `cod_ingredientes` char(10) NOT NULL,
  `nom_ingrediente` varchar(100) NOT NULL,
  `color_idcolor` char(10) DEFAULT NULL,
  `presentacion_idpresentacion` char(10) NOT NULL,
  `tipoingre_idtipoingre` char(10) NOT NULL,
  PRIMARY KEY (`cod_ingredientes`),
  KEY `color_idcolor` (`color_idcolor`),
  KEY `presentacion_idpresentacion` (`presentacion_idpresentacion`),
  KEY `tipoingre_idtipoingre` (`tipoingre_idtipoingre`),
  CONSTRAINT `ingredientes_ibfk_1` FOREIGN KEY (`color_idcolor`) REFERENCES `color` (`idcolor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ingredientes_ibfk_2` FOREIGN KEY (`presentacion_idpresentacion`) REFERENCES `presentacion` (`idpresentacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ingredientes_ibfk_3` FOREIGN KEY (`tipoingre_idtipoingre`) REFERENCES `tipoingre` (`idtipoingre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ingredientes` */

/*Table structure for table `marca` */

DROP TABLE IF EXISTS `marca`;

CREATE TABLE `marca` (
  `idmarca` char(10) NOT NULL,
  `marcacol` varchar(45) NOT NULL,
  PRIMARY KEY (`idmarca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `marca` */

/*Table structure for table `mesa` */

DROP TABLE IF EXISTS `mesa`;

CREATE TABLE `mesa` (
  `cod_mesa` char(10) NOT NULL,
  `cant_personas` int(2) NOT NULL,
  `num_mesa` int(2) NOT NULL,
  `fumador` char(20) NOT NULL,
  `cod_estado` char(10) NOT NULL,
  `cod_ubicacion` char(10) NOT NULL,
  `cod_restaurante` char(10) NOT NULL,
  PRIMARY KEY (`cod_mesa`),
  KEY `cod_estado` (`cod_estado`),
  KEY `cod_ubicacion` (`cod_ubicacion`),
  KEY `cod_restaurante` (`cod_restaurante`),
  CONSTRAINT `mesa_ibfk_1` FOREIGN KEY (`cod_estado`) REFERENCES `estado` (`cod_estado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `mesa_ibfk_2` FOREIGN KEY (`cod_ubicacion`) REFERENCES `ubicacion` (`cod_ubicacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `mesa_ibfk_3` FOREIGN KEY (`cod_restaurante`) REFERENCES `restaurante` (`cod_restaurante`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mesa` */

/*Table structure for table `origen` */

DROP TABLE IF EXISTS `origen`;

CREATE TABLE `origen` (
  `idorigen` char(10) NOT NULL,
  `origencol` varchar(45) NOT NULL,
  PRIMARY KEY (`idorigen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `origen` */

/*Table structure for table `pedido` */

DROP TABLE IF EXISTS `pedido`;

CREATE TABLE `pedido` (
  `cod_pedido` char(10) NOT NULL,
  `hora` time NOT NULL,
  `estadopedido_idestadopedido` char(10) NOT NULL,
  `reserva_cod_reserva` char(10) NOT NULL,
  `usuario_cod_usuario` char(10) NOT NULL,
  PRIMARY KEY (`cod_pedido`),
  KEY `estadopedido_idestadopedido` (`estadopedido_idestadopedido`),
  KEY `reserva_cod_reserva` (`reserva_cod_reserva`),
  KEY `usuario_cod_usuario` (`usuario_cod_usuario`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`estadopedido_idestadopedido`) REFERENCES `estadopedido` (`idestadopedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`reserva_cod_reserva`) REFERENCES `reserva` (`cod_reserva`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pedido_ibfk_3` FOREIGN KEY (`usuario_cod_usuario`) REFERENCES `usuario` (`cod_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pedido` */

/*Table structure for table `presentacion` */

DROP TABLE IF EXISTS `presentacion`;

CREATE TABLE `presentacion` (
  `idpresentacion` char(10) NOT NULL,
  `presentacion` varchar(45) NOT NULL,
  PRIMARY KEY (`idpresentacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `presentacion` */

/*Table structure for table `reserva` */

DROP TABLE IF EXISTS `reserva`;

CREATE TABLE `reserva` (
  `cod_reserva` char(10) NOT NULL,
  `hora` time NOT NULL,
  `fecha` date NOT NULL,
  `cant_personas` int(2) NOT NULL,
  `detalle` varchar(500) DEFAULT NULL,
  `cod_cliente` char(10) NOT NULL,
  `cod_mesa` char(10) NOT NULL,
  PRIMARY KEY (`cod_reserva`),
  KEY `cod_cliente` (`cod_cliente`),
  KEY `cod_mesa` (`cod_mesa`),
  CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `cliente` (`cod_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`cod_mesa`) REFERENCES `mesa` (`cod_mesa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reserva` */

/*Table structure for table `restaurante` */

DROP TABLE IF EXISTS `restaurante`;

CREATE TABLE `restaurante` (
  `cod_restaurante` char(10) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` char(9) NOT NULL,
  PRIMARY KEY (`cod_restaurante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `restaurante` */

insert  into `restaurante`(`cod_restaurante`,`nombre`,`direccion`,`telefono`) values ('L','LA DELICIA','AV LUZURIAGA 1125','946845134');

/*Table structure for table `rol` */

DROP TABLE IF EXISTS `rol`;

CREATE TABLE `rol` (
  `cod_rol` char(10) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`cod_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rol` */

insert  into `rol`(`cod_rol`,`nombre`) values ('R','ADMINISTRADOR'),('R-1','CAJERO'),('R-2','MOZO'),('R-3','ALMACENERO');

/*Table structure for table `sabor` */

DROP TABLE IF EXISTS `sabor`;

CREATE TABLE `sabor` (
  `idsabor` char(10) NOT NULL,
  `saborcol` varchar(45) NOT NULL,
  PRIMARY KEY (`idsabor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sabor` */

/*Table structure for table `server` */

DROP TABLE IF EXISTS `server`;

CREATE TABLE `server` (
  `idserver` int(15) NOT NULL AUTO_INCREMENT,
  `usuario` char(25) DEFAULT NULL,
  `password` char(25) DEFAULT NULL,
  PRIMARY KEY (`idserver`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `server` */

/*Table structure for table `tipobebida` */

DROP TABLE IF EXISTS `tipobebida`;

CREATE TABLE `tipobebida` (
  `idtipobebida` char(10) NOT NULL,
  `nomtipo` varchar(45) NOT NULL,
  PRIMARY KEY (`idtipobebida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tipobebida` */

/*Table structure for table `tipocomprobante` */

DROP TABLE IF EXISTS `tipocomprobante`;

CREATE TABLE `tipocomprobante` (
  `cod_tipo` char(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`cod_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tipocomprobante` */

/*Table structure for table `tipoingre` */

DROP TABLE IF EXISTS `tipoingre`;

CREATE TABLE `tipoingre` (
  `idtipoingre` char(10) NOT NULL,
  `nom_tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`idtipoingre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tipoingre` */

/*Table structure for table `ubicacion` */

DROP TABLE IF EXISTS `ubicacion`;

CREATE TABLE `ubicacion` (
  `cod_ubicacion` char(10) NOT NULL,
  `ubicacion` varchar(30) NOT NULL,
  PRIMARY KEY (`cod_ubicacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ubicacion` */

/*Table structure for table `unidades_medida` */

DROP TABLE IF EXISTS `unidades_medida`;

CREATE TABLE `unidades_medida` (
  `idunidades_medida` char(10) NOT NULL,
  `unidades_medidacol` varchar(45) NOT NULL,
  PRIMARY KEY (`idunidades_medida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `unidades_medida` */

/*Table structure for table `usoingredientes` */

DROP TABLE IF EXISTS `usoingredientes`;

CREATE TABLE `usoingredientes` (
  `idusoingredientes` char(10) NOT NULL,
  `comida_idcomida` char(10) NOT NULL,
  `almacen_cod_almacen` char(10) NOT NULL,
  `unidades_medida_idunidades_medida` char(10) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`idusoingredientes`),
  KEY `comida_idcomida` (`comida_idcomida`),
  KEY `almacen_cod_almacen` (`almacen_cod_almacen`),
  KEY `unidades_medida_idunidades_medida` (`unidades_medida_idunidades_medida`),
  CONSTRAINT `usoingredientes_ibfk_1` FOREIGN KEY (`comida_idcomida`) REFERENCES `comida` (`idcomida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usoingredientes_ibfk_2` FOREIGN KEY (`almacen_cod_almacen`) REFERENCES `almacen` (`cod_almacen`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usoingredientes_ibfk_3` FOREIGN KEY (`unidades_medida_idunidades_medida`) REFERENCES `unidades_medida` (`idunidades_medida`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usoingredientes` */

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `cod_usuario` char(10) NOT NULL,
  `usuario` varchar(30) NOT NULL,
  `clave` varchar(30) NOT NULL,
  `empleado_idempleado` char(10) NOT NULL,
  `rol_cod_rol` char(10) NOT NULL,
  PRIMARY KEY (`cod_usuario`),
  KEY `empleado_idempleado` (`empleado_idempleado`),
  KEY `rol_cod_rol` (`rol_cod_rol`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`empleado_idempleado`) REFERENCES `empleado` (`idempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`rol_cod_rol`) REFERENCES `rol` (`cod_rol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usuario` */

insert  into `usuario`(`cod_usuario`,`usuario`,`clave`,`empleado_idempleado`,`rol_cod_rol`) values ('A','ADMIN','v4NrsFIKhetmo9AYkHul/g==','1035874584','R');

/*Table structure for table `vtaalmacenbebida` */

DROP TABLE IF EXISTS `vtaalmacenbebida`;

/*!50001 DROP VIEW IF EXISTS `vtaalmacenbebida` */;
/*!50001 DROP TABLE IF EXISTS `vtaalmacenbebida` */;

/*!50001 CREATE TABLE  `vtaalmacenbebida`(
 `cod_almacen` char(10) ,
 `tipobebida` varchar(45) ,
 `marcabebida` varchar(45) ,
 `saborbebida` varchar(45) ,
 `cantidad` varchar(45) ,
 `fechaexp` date ,
 `unidadmedida` varchar(45) ,
 `restaurante` varchar(100) ,
 `usuario` varchar(30) ,
 `tamaño_medida` varchar(45) 
)*/;

/*Table structure for table `vtaalmaceningredientes` */

DROP TABLE IF EXISTS `vtaalmaceningredientes`;

/*!50001 DROP VIEW IF EXISTS `vtaalmaceningredientes` */;
/*!50001 DROP TABLE IF EXISTS `vtaalmaceningredientes` */;

/*!50001 CREATE TABLE  `vtaalmaceningredientes`(
 `cod_almacen` char(10) ,
 `ingrediente` varchar(100) ,
 `cantidad` varchar(45) ,
 `fechaexp` date ,
 `unidadmedida` varchar(45) ,
 `restaurante` varchar(100) ,
 `usuario` varchar(30) ,
 `tamaño_medida` varchar(45) 
)*/;

/*Table structure for table `vtabebida` */

DROP TABLE IF EXISTS `vtabebida`;

/*!50001 DROP VIEW IF EXISTS `vtabebida` */;
/*!50001 DROP TABLE IF EXISTS `vtabebida` */;

/*!50001 CREATE TABLE  `vtabebida`(
 `idbebidas` char(10) ,
 `tipo` varchar(45) ,
 `marca` varchar(45) ,
 `sabor` varchar(45) ,
 `costo` double(8,2) 
)*/;

/*Table structure for table `vtacomida` */

DROP TABLE IF EXISTS `vtacomida`;

/*!50001 DROP VIEW IF EXISTS `vtacomida` */;
/*!50001 DROP TABLE IF EXISTS `vtacomida` */;

/*!50001 CREATE TABLE  `vtacomida`(
 `idcomida` char(10) ,
 `origencol` varchar(45) ,
 `nombre_comida` varchar(45) ,
 `costo` double(8,2) 
)*/;

/*Table structure for table `vtaingredientes` */

DROP TABLE IF EXISTS `vtaingredientes`;

/*!50001 DROP VIEW IF EXISTS `vtaingredientes` */;
/*!50001 DROP TABLE IF EXISTS `vtaingredientes` */;

/*!50001 CREATE TABLE  `vtaingredientes`(
 `cod_ingredientes` char(10) ,
 `nom_ingrediente` varchar(100) ,
 `color` varchar(45) ,
 `presentacion` varchar(45) ,
 `nom_tipo` varchar(45) 
)*/;

/*Table structure for table `vtamesa` */

DROP TABLE IF EXISTS `vtamesa`;

/*!50001 DROP VIEW IF EXISTS `vtamesa` */;
/*!50001 DROP TABLE IF EXISTS `vtamesa` */;

/*!50001 CREATE TABLE  `vtamesa`(
 `cod_mesa` char(10) ,
 `nombre` varchar(100) ,
 `num_mesa` int(2) ,
 `cant_personas` int(2) ,
 `fumador` char(20) ,
 `ubicacion` varchar(30) ,
 `estado` varchar(30) 
)*/;

/*Table structure for table `vtapedido` */

DROP TABLE IF EXISTS `vtapedido`;

/*!50001 DROP VIEW IF EXISTS `vtapedido` */;
/*!50001 DROP TABLE IF EXISTS `vtapedido` */;

/*!50001 CREATE TABLE  `vtapedido`(
 `cod_pedido` char(10) ,
 `hora` time ,
 `estado` varchar(45) ,
 `cliente` varchar(100) ,
 `num_mesa` int(2) ,
 `cant_personas` int(2) ,
 `usuario` varchar(30) 
)*/;

/*Table structure for table `vtareserva` */

DROP TABLE IF EXISTS `vtareserva`;

/*!50001 DROP VIEW IF EXISTS `vtareserva` */;
/*!50001 DROP TABLE IF EXISTS `vtareserva` */;

/*!50001 CREATE TABLE  `vtareserva`(
 `cod_reserva` char(10) ,
 `fecha` date ,
 `hora` time ,
 `cant_personas` int(2) ,
 `detalle` varchar(500) ,
 `cliente` varchar(100) ,
 `celular` char(9) ,
 `num_mesa` int(2) ,
 `personasmesa` int(2) ,
 `resta` varchar(100) 
)*/;

/*Table structure for table `vtasesion` */

DROP TABLE IF EXISTS `vtasesion`;

/*!50001 DROP VIEW IF EXISTS `vtasesion` */;
/*!50001 DROP TABLE IF EXISTS `vtasesion` */;

/*!50001 CREATE TABLE  `vtasesion`(
 `cod_usuario` char(10) ,
 `empleado` varchar(45) ,
 `rol` varchar(30) ,
 `usuario` varchar(30) ,
 `clave` varchar(30) 
)*/;

/*Table structure for table `vtausoingrediente` */

DROP TABLE IF EXISTS `vtausoingrediente`;

/*!50001 DROP VIEW IF EXISTS `vtausoingrediente` */;
/*!50001 DROP TABLE IF EXISTS `vtausoingrediente` */;

/*!50001 CREATE TABLE  `vtausoingrediente`(
 `idusoingredientes` char(10) ,
 `comida` varchar(45) ,
 `ingrediente` varchar(100) ,
 `inidadmedida` varchar(45) ,
 `cantidad` int(11) 
)*/;

/*View structure for view vtaalmacenbebida */

/*!50001 DROP TABLE IF EXISTS `vtaalmacenbebida` */;
/*!50001 DROP VIEW IF EXISTS `vtaalmacenbebida` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtaalmacenbebida` AS select `almacen`.`cod_almacen` AS `cod_almacen`,`vtabebida`.`tipo` AS `tipobebida`,`vtabebida`.`marca` AS `marcabebida`,`vtabebida`.`sabor` AS `saborbebida`,`almacen`.`cantidad` AS `cantidad`,`almacen`.`fechaexp` AS `fechaexp`,`unidades_medida`.`unidades_medidacol` AS `unidadmedida`,`restaurante`.`nombre` AS `restaurante`,`usuario`.`usuario` AS `usuario`,`almacen`.`tamaño_medida` AS `tamaño_medida` from ((((`almacen` join `vtabebida`) join `unidades_medida`) join `restaurante`) join `usuario` on(((`almacen`.`bebidas_idbebidas` = `vtabebida`.`idbebidas`) and (`almacen`.`unidades_medida_idunidades_medida` = `unidades_medida`.`idunidades_medida`) and (`almacen`.`cod_restaurante` = `restaurante`.`cod_restaurante`) and (`almacen`.`usuario_cod_usuario` = `usuario`.`cod_usuario`)))) */;

/*View structure for view vtaalmaceningredientes */

/*!50001 DROP TABLE IF EXISTS `vtaalmaceningredientes` */;
/*!50001 DROP VIEW IF EXISTS `vtaalmaceningredientes` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtaalmaceningredientes` AS select `almacen`.`cod_almacen` AS `cod_almacen`,`ingredientes`.`nom_ingrediente` AS `ingrediente`,`almacen`.`cantidad` AS `cantidad`,`almacen`.`fechaexp` AS `fechaexp`,`unidades_medida`.`unidades_medidacol` AS `unidadmedida`,`restaurante`.`nombre` AS `restaurante`,`usuario`.`usuario` AS `usuario`,`almacen`.`tamaño_medida` AS `tamaño_medida` from ((((`almacen` join `ingredientes`) join `unidades_medida`) join `restaurante`) join `usuario` on(((`almacen`.`ingredientes_cod_ingredientes` = `ingredientes`.`cod_ingredientes`) and (`almacen`.`unidades_medida_idunidades_medida` = `unidades_medida`.`idunidades_medida`) and (`almacen`.`cod_restaurante` = `restaurante`.`cod_restaurante`) and (`almacen`.`usuario_cod_usuario` = `usuario`.`cod_usuario`)))) */;

/*View structure for view vtabebida */

/*!50001 DROP TABLE IF EXISTS `vtabebida` */;
/*!50001 DROP VIEW IF EXISTS `vtabebida` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtabebida` AS select `bebidas`.`idbebidas` AS `idbebidas`,`tipobebida`.`nomtipo` AS `tipo`,`marca`.`marcacol` AS `marca`,`sabor`.`saborcol` AS `sabor`,`bebidas`.`costo` AS `costo` from (((`bebidas` left join `tipobebida` on((`bebidas`.`tipobebida_idtipobebida` = `tipobebida`.`idtipobebida`))) left join `marca` on((`bebidas`.`marca_idmarca` = `marca`.`idmarca`))) left join `sabor` on((`bebidas`.`sabor_idsabor` = `sabor`.`idsabor`))) */;

/*View structure for view vtacomida */

/*!50001 DROP TABLE IF EXISTS `vtacomida` */;
/*!50001 DROP VIEW IF EXISTS `vtacomida` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtacomida` AS select `comida`.`idcomida` AS `idcomida`,`origen`.`origencol` AS `origencol`,`comida`.`nombre_comida` AS `nombre_comida`,`comida`.`costo` AS `costo` from (`comida` join `origen` on((`comida`.`origen_idorigen` = `origen`.`idorigen`))) */;

/*View structure for view vtaingredientes */

/*!50001 DROP TABLE IF EXISTS `vtaingredientes` */;
/*!50001 DROP VIEW IF EXISTS `vtaingredientes` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtaingredientes` AS select `ingredientes`.`cod_ingredientes` AS `cod_ingredientes`,`ingredientes`.`nom_ingrediente` AS `nom_ingrediente`,`color`.`color` AS `color`,`presentacion`.`presentacion` AS `presentacion`,`tipoingre`.`nom_tipo` AS `nom_tipo` from (((`ingredientes` left join `color` on((`ingredientes`.`color_idcolor` = `color`.`idcolor`))) left join `presentacion` on((`ingredientes`.`presentacion_idpresentacion` = `presentacion`.`idpresentacion`))) left join `tipoingre` on((`ingredientes`.`tipoingre_idtipoingre` = `tipoingre`.`idtipoingre`))) */;

/*View structure for view vtamesa */

/*!50001 DROP TABLE IF EXISTS `vtamesa` */;
/*!50001 DROP VIEW IF EXISTS `vtamesa` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtamesa` AS select `mesa`.`cod_mesa` AS `cod_mesa`,`restaurante`.`nombre` AS `nombre`,`mesa`.`num_mesa` AS `num_mesa`,`mesa`.`cant_personas` AS `cant_personas`,`mesa`.`fumador` AS `fumador`,`ubicacion`.`ubicacion` AS `ubicacion`,`estado`.`estado` AS `estado` from (((`mesa` join `estado`) join `ubicacion`) join `restaurante` on(((`mesa`.`cod_estado` = `estado`.`cod_estado`) and (`mesa`.`cod_ubicacion` = `ubicacion`.`cod_ubicacion`) and (`mesa`.`cod_restaurante` = `restaurante`.`cod_restaurante`)))) */;

/*View structure for view vtapedido */

/*!50001 DROP TABLE IF EXISTS `vtapedido` */;
/*!50001 DROP VIEW IF EXISTS `vtapedido` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtapedido` AS select `pedido`.`cod_pedido` AS `cod_pedido`,`pedido`.`hora` AS `hora`,`estadopedido`.`estado` AS `estado`,`vtareserva`.`cliente` AS `cliente`,`vtareserva`.`num_mesa` AS `num_mesa`,`vtareserva`.`cant_personas` AS `cant_personas`,`usuario`.`usuario` AS `usuario` from (((`pedido` join `estadopedido`) join `vtareserva`) join `usuario` on(((`pedido`.`estadopedido_idestadopedido` = `estadopedido`.`idestadopedido`) and (`pedido`.`reserva_cod_reserva` = `vtareserva`.`cod_reserva`) and (`pedido`.`usuario_cod_usuario` = `usuario`.`cod_usuario`)))) */;

/*View structure for view vtareserva */

/*!50001 DROP TABLE IF EXISTS `vtareserva` */;
/*!50001 DROP VIEW IF EXISTS `vtareserva` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtareserva` AS select `reserva`.`cod_reserva` AS `cod_reserva`,`reserva`.`fecha` AS `fecha`,`reserva`.`hora` AS `hora`,`reserva`.`cant_personas` AS `cant_personas`,`reserva`.`detalle` AS `detalle`,`cliente`.`nombre` AS `cliente`,`cliente`.`celular` AS `celular`,`mesa`.`num_mesa` AS `num_mesa`,`mesa`.`cant_personas` AS `personasmesa`,`restaurante`.`nombre` AS `resta` from (((`reserva` left join `cliente` on((`reserva`.`cod_cliente` = `cliente`.`cod_cliente`))) left join `mesa` on((`reserva`.`cod_mesa` = `mesa`.`cod_mesa`))) left join `restaurante` on((`mesa`.`cod_restaurante` = `restaurante`.`cod_restaurante`))) */;

/*View structure for view vtasesion */

/*!50001 DROP TABLE IF EXISTS `vtasesion` */;
/*!50001 DROP VIEW IF EXISTS `vtasesion` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtasesion` AS select `usuario`.`cod_usuario` AS `cod_usuario`,`empleado`.`nombre` AS `empleado`,`rol`.`nombre` AS `rol`,`usuario`.`usuario` AS `usuario`,`usuario`.`clave` AS `clave` from ((`usuario` join `rol`) join `empleado` on(((`usuario`.`rol_cod_rol` = `rol`.`cod_rol`) and (`usuario`.`empleado_idempleado` = `empleado`.`idempleado`)))) */;

/*View structure for view vtausoingrediente */

/*!50001 DROP TABLE IF EXISTS `vtausoingrediente` */;
/*!50001 DROP VIEW IF EXISTS `vtausoingrediente` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtausoingrediente` AS select `usoingredientes`.`idusoingredientes` AS `idusoingredientes`,`comida`.`nombre_comida` AS `comida`,`vtaalmaceningredientes`.`ingrediente` AS `ingrediente`,`unidades_medida`.`unidades_medidacol` AS `inidadmedida`,`usoingredientes`.`cantidad` AS `cantidad` from (((`usoingredientes` join `comida`) join `vtaalmaceningredientes`) join `unidades_medida` on(((`usoingredientes`.`comida_idcomida` = `comida`.`idcomida`) and (`usoingredientes`.`almacen_cod_almacen` = `vtaalmaceningredientes`.`cod_almacen`) and (`usoingredientes`.`unidades_medida_idunidades_medida` = `unidades_medida`.`idunidades_medida`)))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
