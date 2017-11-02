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
  `ingredientes_Cod_Ingredientes` char(10) DEFAULT NULL,
  `bebidas_idbebidas` int(11) DEFAULT NULL,
  `cantidad` varchar(45) NOT NULL,
  `FechaExp` date NOT NULL,
  `Unidades_medida_idUnidades_medida` int(11) NOT NULL,
  `tamaño_medida` varchar(45) DEFAULT NULL,
  `Cod_Restaurante` char(10) NOT NULL,
  `usuario_Cod_Usuario` char(10) NOT NULL,
  PRIMARY KEY (`Cod_Almacen`),
  KEY `Cod_Restaurante` (`Cod_Restaurante`),
  KEY `fk_almacen_ingredientes1_idx` (`ingredientes_Cod_Ingredientes`),
  KEY `fk_almacen_Unidades_medida1_idx` (`Unidades_medida_idUnidades_medida`),
  KEY `fk_almacen_bebidas1_idx` (`bebidas_idbebidas`),
  KEY `fk_almacen_usuario1_idx` (`usuario_Cod_Usuario`),
  CONSTRAINT `almacen_ibfk_1` FOREIGN KEY (`Cod_Restaurante`) REFERENCES `restaurante` (`Cod_Restaurante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_almacen_Unidades_medida1` FOREIGN KEY (`Unidades_medida_idUnidades_medida`) REFERENCES `unidades_medida` (`idUnidades_medida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_almacen_bebidas1` FOREIGN KEY (`bebidas_idbebidas`) REFERENCES `bebidas` (`idbebidas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_almacen_ingredientes1` FOREIGN KEY (`ingredientes_Cod_Ingredientes`) REFERENCES `ingredientes` (`Cod_Ingredientes`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_almacen_usuario1` FOREIGN KEY (`usuario_Cod_Usuario`) REFERENCES `usuario` (`Cod_Usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `almacen` */

/*Table structure for table `bebidas` */

DROP TABLE IF EXISTS `bebidas`;

CREATE TABLE `bebidas` (
  `idbebidas` int(11) NOT NULL,
  `tipoBebida_idtipoBebida` int(11) NOT NULL,
  `marca_idmarca` int(11) DEFAULT NULL,
  `sabor_idsabor` int(11) DEFAULT NULL,
  `Costo` double(8,2) NOT NULL,
  PRIMARY KEY (`idbebidas`),
  KEY `fk_bebidas_marca1_idx` (`marca_idmarca`),
  KEY `fk_bebidas_sabor1_idx` (`sabor_idsabor`),
  KEY `fk_bebidas_tipoBebida1_idx` (`tipoBebida_idtipoBebida`),
  CONSTRAINT `fk_bebidas_marca1` FOREIGN KEY (`marca_idmarca`) REFERENCES `marca` (`idmarca`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bebidas_sabor1` FOREIGN KEY (`sabor_idsabor`) REFERENCES `sabor` (`idsabor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bebidas_tipoBebida1` FOREIGN KEY (`tipoBebida_idtipoBebida`) REFERENCES `tipobebida` (`idtipoBebida`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bebidas` */

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

/*Table structure for table `color` */

DROP TABLE IF EXISTS `color`;

CREATE TABLE `color` (
  `idcolor` int(11) NOT NULL,
  `color` varchar(45) NOT NULL,
  PRIMARY KEY (`idcolor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `color` */

/*Table structure for table `comida` */

DROP TABLE IF EXISTS `comida`;

CREATE TABLE `comida` (
  `idcomida` int(11) NOT NULL,
  `nombre_comida` varchar(45) NOT NULL,
  `Costo` double(8,2) NOT NULL,
  `origen_idorigen` int(11) NOT NULL,
  PRIMARY KEY (`idcomida`),
  KEY `fk_comida_origen1_idx` (`origen_idorigen`),
  CONSTRAINT `fk_comida_origen1` FOREIGN KEY (`origen_idorigen`) REFERENCES `origen` (`idorigen`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comida` */

/*Table structure for table `comprobante` */

DROP TABLE IF EXISTS `comprobante`;

CREATE TABLE `comprobante` (
  `Cod_Comprobante` char(10) NOT NULL,
  `RUC` char(11) DEFAULT NULL,
  `Direccion` varchar(100) DEFAULT NULL,
  `Fecha` date NOT NULL,
  `Monto` double(8,2) NOT NULL,
  `Cod_Tipo` char(10) NOT NULL,
  `Cod_Cliente` char(10) NOT NULL,
  `usuario_Cod_Usuario` char(10) NOT NULL,
  `consumicion_Cod_Consumicion` char(10) NOT NULL,
  PRIMARY KEY (`Cod_Comprobante`),
  KEY `Cod_Tipo` (`Cod_Tipo`),
  KEY `Cod_Cliente` (`Cod_Cliente`),
  KEY `fk_comprobante_usuario1_idx` (`usuario_Cod_Usuario`),
  KEY `fk_comprobante_consumicion1_idx` (`consumicion_Cod_Consumicion`),
  CONSTRAINT `comprobante_ibfk_1` FOREIGN KEY (`Cod_Tipo`) REFERENCES `tipocomprobante` (`Cod_Tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `comprobante_ibfk_2` FOREIGN KEY (`Cod_Cliente`) REFERENCES `cliente` (`Cod_Cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comprobante_consumicion1` FOREIGN KEY (`consumicion_Cod_Consumicion`) REFERENCES `consumicion` (`Cod_Consumicion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comprobante_usuario1` FOREIGN KEY (`usuario_Cod_Usuario`) REFERENCES `usuario` (`Cod_Usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comprobante` */

/*Table structure for table `consumicion` */

DROP TABLE IF EXISTS `consumicion`;

CREATE TABLE `consumicion` (
  `Cod_Consumicion` char(10) NOT NULL,
  `comida_idcomida` int(11) DEFAULT NULL,
  `bebidas_idbebidas` int(11) DEFAULT NULL,
  `Cantidad` int(3) NOT NULL,
  `pedido_Cod_Pedido` char(10) NOT NULL,
  PRIMARY KEY (`Cod_Consumicion`),
  KEY `fk_consumicion_comida1_idx` (`comida_idcomida`),
  KEY `fk_consumicion_bebidas1_idx` (`bebidas_idbebidas`),
  KEY `fk_consumicion_pedido1_idx` (`pedido_Cod_Pedido`),
  CONSTRAINT `fk_consumicion_bebidas1` FOREIGN KEY (`bebidas_idbebidas`) REFERENCES `bebidas` (`idbebidas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_consumicion_comida1` FOREIGN KEY (`comida_idcomida`) REFERENCES `comida` (`idcomida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_consumicion_pedido1` FOREIGN KEY (`pedido_Cod_Pedido`) REFERENCES `pedido` (`Cod_Pedido`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `consumicion` */

/*Table structure for table `empleado` */

DROP TABLE IF EXISTS `empleado`;

CREATE TABLE `empleado` (
  `idempleado` int(11) NOT NULL,
  `nombrel` varchar(45) DEFAULT NULL,
  `dni` int(11) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idempleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `empleado` */

/*Table structure for table `estado` */

DROP TABLE IF EXISTS `estado`;

CREATE TABLE `estado` (
  `Cod_Estado` char(10) NOT NULL,
  `Estado` varchar(30) NOT NULL,
  PRIMARY KEY (`Cod_Estado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `estado` */

/*Table structure for table `estadopedido` */

DROP TABLE IF EXISTS `estadopedido`;

CREATE TABLE `estadopedido` (
  `idEstadoPedido` int(11) NOT NULL,
  `Estado` varchar(45) NOT NULL,
  PRIMARY KEY (`idEstadoPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `estadopedido` */

/*Table structure for table `ingredientes` */

DROP TABLE IF EXISTS `ingredientes`;

CREATE TABLE `ingredientes` (
  `Cod_Ingredientes` char(10) NOT NULL,
  `Nom_ingrediente` varchar(100) NOT NULL,
  `color_idcolor` int(11) DEFAULT NULL,
  `presentacion_idpresentacion` int(11) NOT NULL,
  `tipoIngre_idtipo` int(11) NOT NULL,
  PRIMARY KEY (`Cod_Ingredientes`),
  KEY `fk_ingredientes_color1_idx` (`color_idcolor`),
  KEY `fk_ingredientes_presentacion1_idx` (`presentacion_idpresentacion`),
  KEY `fk_ingredientes_tipoIngre1_idx` (`tipoIngre_idtipo`),
  CONSTRAINT `fk_ingredientes_color1` FOREIGN KEY (`color_idcolor`) REFERENCES `color` (`idcolor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ingredientes_presentacion1` FOREIGN KEY (`presentacion_idpresentacion`) REFERENCES `presentacion` (`idpresentacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ingredientes_tipoIngre1` FOREIGN KEY (`tipoIngre_idtipo`) REFERENCES `tipoingre` (`idtipoIngre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ingredientes` */

/*Table structure for table `marca` */

DROP TABLE IF EXISTS `marca`;

CREATE TABLE `marca` (
  `idmarca` int(11) NOT NULL,
  `marcacol` varchar(45) NOT NULL,
  PRIMARY KEY (`idmarca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `marca` */

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

/*Table structure for table `origen` */

DROP TABLE IF EXISTS `origen`;

CREATE TABLE `origen` (
  `idorigen` int(11) NOT NULL,
  `origencol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idorigen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `origen` */

/*Table structure for table `pedido` */

DROP TABLE IF EXISTS `pedido`;

CREATE TABLE `pedido` (
  `Cod_Pedido` char(10) NOT NULL,
  `Hora` time NOT NULL,
  `EstadoPedido_idEstadoPedido` int(11) NOT NULL,
  `reserva_Cod_Reserva` char(10) NOT NULL,
  `usuario_Cod_Usuario` char(10) NOT NULL,
  PRIMARY KEY (`Cod_Pedido`),
  KEY `fk_pedido_usuario1_idx` (`usuario_Cod_Usuario`),
  KEY `fk_pedido_EstadoPedido1_idx` (`EstadoPedido_idEstadoPedido`),
  KEY `fk_pedido_reserva1_idx` (`reserva_Cod_Reserva`),
  CONSTRAINT `fk_pedido_EstadoPedido1` FOREIGN KEY (`EstadoPedido_idEstadoPedido`) REFERENCES `estadopedido` (`idEstadoPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_reserva1` FOREIGN KEY (`reserva_Cod_Reserva`) REFERENCES `reserva` (`Cod_Reserva`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_usuario1` FOREIGN KEY (`usuario_Cod_Usuario`) REFERENCES `usuario` (`Cod_Usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pedido` */

/*Table structure for table `presentacion` */

DROP TABLE IF EXISTS `presentacion`;

CREATE TABLE `presentacion` (
  `idpresentacion` int(11) NOT NULL,
  `presentacion` varchar(45) NOT NULL,
  PRIMARY KEY (`idpresentacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `presentacion` */

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

/*Table structure for table `rol` */

DROP TABLE IF EXISTS `rol`;

CREATE TABLE `rol` (
  `Cod_Rol` char(10) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`Cod_Rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rol` */

/*Table structure for table `sabor` */

DROP TABLE IF EXISTS `sabor`;

CREATE TABLE `sabor` (
  `idsabor` int(11) NOT NULL,
  `saborcol` varchar(45) NOT NULL,
  PRIMARY KEY (`idsabor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sabor` */

/*Table structure for table `tipobebida` */

DROP TABLE IF EXISTS `tipobebida`;

CREATE TABLE `tipobebida` (
  `idtipoBebida` int(11) NOT NULL,
  `nomTipo` varchar(45) NOT NULL,
  PRIMARY KEY (`idtipoBebida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tipobebida` */

/*Table structure for table `tipocomprobante` */

DROP TABLE IF EXISTS `tipocomprobante`;

CREATE TABLE `tipocomprobante` (
  `Cod_Tipo` char(10) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`Cod_Tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tipocomprobante` */

/*Table structure for table `tipoingre` */

DROP TABLE IF EXISTS `tipoingre`;

CREATE TABLE `tipoingre` (
  `idtipoIngre` int(11) NOT NULL,
  `nom_tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`idtipoIngre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tipoingre` */

/*Table structure for table `ubicacion` */

DROP TABLE IF EXISTS `ubicacion`;

CREATE TABLE `ubicacion` (
  `Cod_Ubicacion` char(10) NOT NULL,
  `Ubicacion` varchar(30) NOT NULL,
  PRIMARY KEY (`Cod_Ubicacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ubicacion` */

/*Table structure for table `unidades_medida` */

DROP TABLE IF EXISTS `unidades_medida`;

CREATE TABLE `unidades_medida` (
  `idUnidades_medida` int(11) NOT NULL,
  `Unidades_medidacol` varchar(45) NOT NULL,
  PRIMARY KEY (`idUnidades_medida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `unidades_medida` */

/*Table structure for table `usoingredientes` */

DROP TABLE IF EXISTS `usoingredientes`;

CREATE TABLE `usoingredientes` (
  `idusoIngredientes` int(11) NOT NULL,
  `comida_idcomida` int(11) NOT NULL,
  `almacen_Cod_Almacen` char(10) NOT NULL,
  `Unidades_medida_idUnidades_medida` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`idusoIngredientes`),
  KEY `fk_usoIngredientes_comida1_idx` (`comida_idcomida`),
  KEY `fk_usoIngredientes_almacen1_idx` (`almacen_Cod_Almacen`),
  KEY `fk_usoIngredientes_Unidades_medida1_idx` (`Unidades_medida_idUnidades_medida`),
  CONSTRAINT `fk_usoIngredientes_Unidades_medida1` FOREIGN KEY (`Unidades_medida_idUnidades_medida`) REFERENCES `unidades_medida` (`idUnidades_medida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usoIngredientes_almacen1` FOREIGN KEY (`almacen_Cod_Almacen`) REFERENCES `almacen` (`Cod_Almacen`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usoIngredientes_comida1` FOREIGN KEY (`comida_idcomida`) REFERENCES `comida` (`idcomida`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usoingredientes` */

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `Cod_Usuario` char(10) NOT NULL,
  `Usuario` varchar(30) NOT NULL,
  `Clave` varchar(30) NOT NULL,
  `empleado_idempleado` int(11) NOT NULL,
  `rol_Cod_Rol` char(10) NOT NULL,
  PRIMARY KEY (`Cod_Usuario`),
  KEY `fk_usuario_empleado1_idx` (`empleado_idempleado`),
  KEY `fk_usuario_rol1_idx` (`rol_Cod_Rol`),
  CONSTRAINT `fk_usuario_empleado1` FOREIGN KEY (`empleado_idempleado`) REFERENCES `empleado` (`idempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_rol1` FOREIGN KEY (`rol_Cod_Rol`) REFERENCES `rol` (`Cod_Rol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usuario` */

/*Table structure for table `vtaalmacenbebida` */

DROP TABLE IF EXISTS `vtaalmacenbebida`;

/*!50001 DROP VIEW IF EXISTS `vtaalmacenbebida` */;
/*!50001 DROP TABLE IF EXISTS `vtaalmacenbebida` */;

/*!50001 CREATE TABLE  `vtaalmacenbebida`(
 `Cod_Almacen` char(10) ,
 `tipobebida` varchar(45) ,
 `Marcabebida` varchar(45) ,
 `Saborbebida` varchar(45) ,
 `cantidad` varchar(45) ,
 `Fechaexp` date ,
 `unidadmedida` varchar(45) ,
 `Restaurante` varchar(100) ,
 `Usuario` varchar(30) ,
 `tamaño_medida` varchar(45) 
)*/;

/*Table structure for table `vtaalmaceningredientes` */

DROP TABLE IF EXISTS `vtaalmaceningredientes`;

/*!50001 DROP VIEW IF EXISTS `vtaalmaceningredientes` */;
/*!50001 DROP TABLE IF EXISTS `vtaalmaceningredientes` */;

/*!50001 CREATE TABLE  `vtaalmaceningredientes`(
 `Cod_Almacen` char(10) ,
 `Ingrediente` varchar(100) ,
 `cantidad` varchar(45) ,
 `Fechaexp` date ,
 `unidadmedida` varchar(45) ,
 `Restaurante` varchar(100) ,
 `Usuario` varchar(30) ,
 `tamaño_medida` varchar(45) 
)*/;

/*Table structure for table `vtabebida` */

DROP TABLE IF EXISTS `vtabebida`;

/*!50001 DROP VIEW IF EXISTS `vtabebida` */;
/*!50001 DROP TABLE IF EXISTS `vtabebida` */;

/*!50001 CREATE TABLE  `vtabebida`(
 `idbebidas` int(11) ,
 `Tipo` varchar(45) ,
 `Marca` varchar(45) ,
 `Sabor` varchar(45) ,
 `costo` double(8,2) 
)*/;

/*Table structure for table `vtaingredientes` */

DROP TABLE IF EXISTS `vtaingredientes`;

/*!50001 DROP VIEW IF EXISTS `vtaingredientes` */;
/*!50001 DROP TABLE IF EXISTS `vtaingredientes` */;

/*!50001 CREATE TABLE  `vtaingredientes`(
 `Cod_Ingredientes` char(10) ,
 `Nom_Ingrediente` varchar(100) ,
 `color` varchar(45) ,
 `presentacion` varchar(45) ,
 `nom_tipo` varchar(45) 
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

/*Table structure for table `vtapedido` */

DROP TABLE IF EXISTS `vtapedido`;

/*!50001 DROP VIEW IF EXISTS `vtapedido` */;
/*!50001 DROP TABLE IF EXISTS `vtapedido` */;

/*!50001 CREATE TABLE  `vtapedido`(
 `Cod_Pedido` char(10) ,
 `hora` time ,
 `estado` varchar(45) ,
 `cliente` varchar(100) ,
 `Num_Mesa` int(2) ,
 `Cant_Personas` int(2) ,
 `usuario` varchar(30) 
)*/;

/*Table structure for table `vtareserva` */

DROP TABLE IF EXISTS `vtareserva`;

/*!50001 DROP VIEW IF EXISTS `vtareserva` */;
/*!50001 DROP TABLE IF EXISTS `vtareserva` */;

/*!50001 CREATE TABLE  `vtareserva`(
 `Cod_Reserva` char(10) ,
 `Fecha` date ,
 `Hora` time ,
 `Cant_Personas` int(2) ,
 `Detalle` varchar(250) ,
 `cliente` varchar(100) ,
 `Celular` char(9) ,
 `Num_Mesa` int(2) ,
 `personasmesa` int(2) 
)*/;

/*Table structure for table `vtasesion` */

DROP TABLE IF EXISTS `vtasesion`;

/*!50001 DROP VIEW IF EXISTS `vtasesion` */;
/*!50001 DROP TABLE IF EXISTS `vtasesion` */;

/*!50001 CREATE TABLE  `vtasesion`(
 `Cod_Usuario` char(10) ,
 `Empleado` varchar(45) ,
 `Rol` varchar(30) ,
 `Usuario` varchar(30) ,
 `Clave` varchar(30) 
)*/;

/*Table structure for table `vtausoingrediente` */

DROP TABLE IF EXISTS `vtausoingrediente`;

/*!50001 DROP VIEW IF EXISTS `vtausoingrediente` */;
/*!50001 DROP TABLE IF EXISTS `vtausoingrediente` */;

/*!50001 CREATE TABLE  `vtausoingrediente`(
 `idusoingredientes` int(11) ,
 `comida` varchar(45) ,
 `Ingrediente` varchar(100) ,
 `inidadmedida` varchar(45) ,
 `cantidad` int(11) 
)*/;

/*View structure for view vtaalmacenbebida */

/*!50001 DROP TABLE IF EXISTS `vtaalmacenbebida` */;
/*!50001 DROP VIEW IF EXISTS `vtaalmacenbebida` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtaalmacenbebida` AS select `almacen`.`Cod_Almacen` AS `Cod_Almacen`,`vtabebida`.`Tipo` AS `tipobebida`,`vtabebida`.`Marca` AS `Marcabebida`,`vtabebida`.`Sabor` AS `Saborbebida`,`almacen`.`cantidad` AS `cantidad`,`almacen`.`FechaExp` AS `Fechaexp`,`unidades_medida`.`Unidades_medidacol` AS `unidadmedida`,`restaurante`.`Nombre` AS `Restaurante`,`usuario`.`Usuario` AS `Usuario`,`almacen`.`tamaño_medida` AS `tamaño_medida` from ((((`almacen` join `vtabebida`) join `unidades_medida`) join `restaurante`) join `usuario` on(((`almacen`.`bebidas_idbebidas` = `vtabebida`.`idbebidas`) and (`almacen`.`Unidades_medida_idUnidades_medida` = `unidades_medida`.`idUnidades_medida`) and (`almacen`.`Cod_Restaurante` = `restaurante`.`Cod_Restaurante`) and (`almacen`.`usuario_Cod_Usuario` = `usuario`.`Cod_Usuario`)))) */;

/*View structure for view vtaalmaceningredientes */

/*!50001 DROP TABLE IF EXISTS `vtaalmaceningredientes` */;
/*!50001 DROP VIEW IF EXISTS `vtaalmaceningredientes` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtaalmaceningredientes` AS select `almacen`.`Cod_Almacen` AS `Cod_Almacen`,`ingredientes`.`Nom_ingrediente` AS `Ingrediente`,`almacen`.`cantidad` AS `cantidad`,`almacen`.`FechaExp` AS `Fechaexp`,`unidades_medida`.`Unidades_medidacol` AS `unidadmedida`,`restaurante`.`Nombre` AS `Restaurante`,`usuario`.`Usuario` AS `Usuario`,`almacen`.`tamaño_medida` AS `tamaño_medida` from ((((`almacen` join `ingredientes`) join `unidades_medida`) join `restaurante`) join `usuario` on(((`almacen`.`ingredientes_Cod_Ingredientes` = `ingredientes`.`Cod_Ingredientes`) and (`almacen`.`Unidades_medida_idUnidades_medida` = `unidades_medida`.`idUnidades_medida`) and (`almacen`.`Cod_Restaurante` = `restaurante`.`Cod_Restaurante`) and (`almacen`.`usuario_Cod_Usuario` = `usuario`.`Cod_Usuario`)))) */;

/*View structure for view vtabebida */

/*!50001 DROP TABLE IF EXISTS `vtabebida` */;
/*!50001 DROP VIEW IF EXISTS `vtabebida` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtabebida` AS select `bebidas`.`idbebidas` AS `idbebidas`,`tipobebida`.`nomTipo` AS `Tipo`,`marca`.`marcacol` AS `Marca`,`sabor`.`saborcol` AS `Sabor`,`bebidas`.`Costo` AS `costo` from (((`bebidas` left join `tipobebida` on((`bebidas`.`tipoBebida_idtipoBebida` = `tipobebida`.`idtipoBebida`))) left join `marca` on((`bebidas`.`marca_idmarca` = `marca`.`idmarca`))) left join `sabor` on((`bebidas`.`sabor_idsabor` = `sabor`.`idsabor`))) */;

/*View structure for view vtaingredientes */

/*!50001 DROP TABLE IF EXISTS `vtaingredientes` */;
/*!50001 DROP VIEW IF EXISTS `vtaingredientes` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtaingredientes` AS select `ingredientes`.`Cod_Ingredientes` AS `Cod_Ingredientes`,`ingredientes`.`Nom_ingrediente` AS `Nom_Ingrediente`,`color`.`color` AS `color`,`presentacion`.`presentacion` AS `presentacion`,`tipoingre`.`nom_tipo` AS `nom_tipo` from (((`ingredientes` left join `color` on((`ingredientes`.`color_idcolor` = `color`.`idcolor`))) left join `presentacion` on((`ingredientes`.`presentacion_idpresentacion` = `presentacion`.`idpresentacion`))) left join `tipoingre` on((`ingredientes`.`tipoIngre_idtipo` = `tipoingre`.`idtipoIngre`))) */;

/*View structure for view vtamesa */

/*!50001 DROP TABLE IF EXISTS `vtamesa` */;
/*!50001 DROP VIEW IF EXISTS `vtamesa` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtamesa` AS select `mesa`.`Cod_Mesa` AS `Cod_Mesa`,`restaurante`.`Nombre` AS `Nombre`,`mesa`.`Num_Mesa` AS `Num_Mesa`,`mesa`.`Cant_Personas` AS `Cant_Personas`,`mesa`.`Fumador` AS `Fumador`,`ubicacion`.`Ubicacion` AS `Ubicacion`,`estado`.`Estado` AS `Estado` from (((`mesa` join `estado`) join `ubicacion`) join `restaurante` on(((`mesa`.`Cod_Estado` = `estado`.`Cod_Estado`) and (`mesa`.`Cod_Ubicacion` = `ubicacion`.`Cod_Ubicacion`) and (`mesa`.`Cod_Restaurante` = `restaurante`.`Cod_Restaurante`)))) */;

/*View structure for view vtapedido */

/*!50001 DROP TABLE IF EXISTS `vtapedido` */;
/*!50001 DROP VIEW IF EXISTS `vtapedido` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtapedido` AS select `pedido`.`Cod_Pedido` AS `Cod_Pedido`,`pedido`.`Hora` AS `hora`,`estadopedido`.`Estado` AS `estado`,`vtareserva`.`cliente` AS `cliente`,`vtareserva`.`Num_Mesa` AS `Num_Mesa`,`vtareserva`.`Cant_Personas` AS `Cant_Personas`,`usuario`.`Usuario` AS `usuario` from (((`pedido` join `estadopedido`) join `vtareserva`) join `usuario` on(((`pedido`.`EstadoPedido_idEstadoPedido` = `estadopedido`.`idEstadoPedido`) and (`pedido`.`reserva_Cod_Reserva` = `vtareserva`.`Cod_Reserva`) and (`pedido`.`usuario_Cod_Usuario` = `usuario`.`Cod_Usuario`)))) */;

/*View structure for view vtareserva */

/*!50001 DROP TABLE IF EXISTS `vtareserva` */;
/*!50001 DROP VIEW IF EXISTS `vtareserva` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtareserva` AS select `reserva`.`Cod_Reserva` AS `Cod_Reserva`,`reserva`.`Fecha` AS `Fecha`,`reserva`.`Hora` AS `Hora`,`reserva`.`Cant_Personas` AS `Cant_Personas`,`reserva`.`Detalle` AS `Detalle`,`cliente`.`Nombre` AS `cliente`,`cliente`.`Celular` AS `Celular`,`mesa`.`Num_Mesa` AS `Num_Mesa`,`mesa`.`Cant_Personas` AS `personasmesa` from ((`reserva` left join `cliente` on((`reserva`.`Cod_Cliente` = `cliente`.`Cod_Cliente`))) left join `mesa` on((`reserva`.`Cod_Mesa` = `mesa`.`Cod_Mesa`))) */;

/*View structure for view vtasesion */

/*!50001 DROP TABLE IF EXISTS `vtasesion` */;
/*!50001 DROP VIEW IF EXISTS `vtasesion` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtasesion` AS select `usuario`.`Cod_Usuario` AS `Cod_Usuario`,`empleado`.`nombrel` AS `Empleado`,`rol`.`Nombre` AS `Rol`,`usuario`.`Usuario` AS `Usuario`,`usuario`.`Clave` AS `Clave` from ((`usuario` join `rol`) join `empleado` on(((`usuario`.`rol_Cod_Rol` = `rol`.`Cod_Rol`) and (`usuario`.`empleado_idempleado` = `empleado`.`idempleado`)))) */;

/*View structure for view vtausoingrediente */

/*!50001 DROP TABLE IF EXISTS `vtausoingrediente` */;
/*!50001 DROP VIEW IF EXISTS `vtausoingrediente` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtausoingrediente` AS select `usoingredientes`.`idusoIngredientes` AS `idusoingredientes`,`comida`.`nombre_comida` AS `comida`,`vtaalmaceningredientes`.`Ingrediente` AS `Ingrediente`,`unidades_medida`.`Unidades_medidacol` AS `inidadmedida`,`usoingredientes`.`cantidad` AS `cantidad` from (((`usoingredientes` join `comida`) join `vtaalmaceningredientes`) join `unidades_medida` on(((`usoingredientes`.`comida_idcomida` = `comida`.`idcomida`) and (`usoingredientes`.`almacen_Cod_Almacen` = `vtaalmaceningredientes`.`Cod_Almacen`) and (`usoingredientes`.`Unidades_medida_idUnidades_medida` = `unidades_medida`.`idUnidades_medida`)))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
