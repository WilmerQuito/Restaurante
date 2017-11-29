/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Wilmer Quito
 */
public class BaseDatos {
    Controlador C = Controlador.getInstance();
    private static BaseDatos Single = null;
    //IMPLEMENTACION DE SINGLETON
    private BaseDatos() {
    }

    private synchronized static void Instancia() {
        if (Single == null) {
            Single = new BaseDatos();
        }
    }

    public static BaseDatos getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
    
    //ELIMINACION DE LA BASE DE DATOS
    public void EliminarBD() {
        C.sql = "DROP DATABASE IF EXISTS restaurante;";
        C.InsertaRegistro(C.sql);
    }

    //CREACION DE LA BASE DE DATOS
    public void CrearBD() {
        C.sql = "CREATE DATABASE restaurante;";
        C.InsertaRegistro(C.sql);
    }

    //USO DE LA BASE DE DATOS
    public void UsoDB() {
        C.sql = "USE restaurante;";
        C.InsertaRegistro(C.sql);
    }

    //CREACION DE LAS TABLAS DE LA BASE DE DATOS
    public void CrearTablas() {
        String T1 = "CREATE TABLE almacen ("
                + "cod_almacen CHAR(10) NOT NULL PRIMARY KEY,"
                + "ingredientes_cod_ingredientes CHAR(10) DEFAULT NULL,"
                + "bebidas_idbebidas CHAR(10) DEFAULT NULL,"
                + "cantidad VARCHAR(45) NOT NULL,"
                + "fechaexp DATE NOT NULL,"
                + "unidades_medida_idunidades_medida CHAR(10) NOT NULL,"
                + "tamaño_medida VARCHAR(45) DEFAULT NULL,"
                + "cod_restaurante CHAR(10) NOT NULL,"
                + "usuario_cod_usuario CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T2 = "CREATE TABLE bebidas ("
                + "idbebidas CHAR(10) NOT NULL PRIMARY KEY,"
                + "tipobebida_idtipobebida CHAR(10) NOT NULL,"
                + "marca_idmarca CHAR(10) DEFAULT NULL,"
                + "sabor_idsabor CHAR(10) DEFAULT NULL,"
                + "costo DOUBLE(8,2) NOT NULL"
                + ") ENGINE=INNODB;";

        String T3 = "CREATE TABLE cliente ("
                + "cod_cliente CHAR(10) NOT NULL PRIMARY KEY,"
                + "nombre VARCHAR(100) NOT NULL,"
                + "dni CHAR(8) NOT NULL,"
                + "celular CHAR(9) DEFAULT NULL"
                + ") ENGINE=INNODB;";

        String T4 = "CREATE TABLE color ("
                + "idcolor CHAR(10) NOT NULL PRIMARY KEY,"
                + "color VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T5 = "CREATE TABLE comida ("
                + "idcomida CHAR(10) NOT NULL PRIMARY KEY,"
                + "nombre_comida VARCHAR(45) NOT NULL,"
                + "costo DOUBLE(8,2) NOT NULL,"
                + "origen_idorigen CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T6 = "CREATE TABLE comprobante ("
                + "cod_comprobante CHAR(10) NOT NULL PRIMARY KEY,"
                + "ruc CHAR(11) DEFAULT NULL,"
                + "direccion VARCHAR(100) DEFAULT NULL,"
                + "fecha DATE NOT NULL,"
                + "monto DOUBLE(8,2) NOT NULL,"
                + "cod_tipo CHAR(10) NOT NULL,"
                + "cod_cliente CHAR(10) NOT NULL,"
                + "usuario_cod_usuario CHAR(10) NOT NULL,"
                + "consumicion_cod_consumicion CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T7 = "CREATE TABLE consumicion ("
                + "cod_consumicion CHAR(10) NOT NULL PRIMARY KEY,"
                + "comida_idcomida CHAR(10) DEFAULT NULL,"
                + "bebidas_idbebidas CHAR(10) DEFAULT NULL,"
                + "cantidad INT(3) NOT NULL,"
                + "pedido_cod_pedido CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T8 = "CREATE TABLE empleado ("
                + "idempleado CHAR(10) NOT NULL PRIMARY KEY,"
                + "nombre VARCHAR(45) NOT NULL,"
                + "dni INT(11) NOT NULL,"
                + "telefono VARCHAR(45) NOT NULL,"
                + "direccion VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T9 = "CREATE TABLE estado ("
                + "cod_estado CHAR(10) NOT NULL PRIMARY KEY,"
                + "estado VARCHAR(30) NOT NULL"
                + ") ENGINE=INNODB;";

        String T10 = "CREATE TABLE estadopedido ("
                + "idestadopedido CHAR(10) NOT NULL PRIMARY KEY,"
                + "estado VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T11 = "CREATE TABLE ingredientes ("
                + "cod_ingredientes CHAR(10) NOT NULL PRIMARY KEY,"
                + "nom_ingrediente VARCHAR(100) NOT NULL,"
                + "color_idcolor CHAR(10) DEFAULT NULL,"
                + "presentacion_idpresentacion CHAR(10) NOT NULL,"
                + "tipoingre_idtipoingre CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T12 = "CREATE TABLE marca ("
                + "idmarca CHAR(10) NOT NULL PRIMARY KEY,"
                + "marcacol VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T13 = "CREATE TABLE mesa ("
                + "cod_mesa CHAR(10) NOT NULL PRIMARY KEY,"
                + "cant_personas INT(2) NOT NULL,"
                + "num_mesa INT(2) NOT NULL,"
                + "fumador CHAR(20) NOT NULL,"
                + "cod_estado CHAR(10) NOT NULL,"
                + "cod_ubicacion CHAR(10) NOT NULL,"
                + "cod_restaurante CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T14 = "CREATE TABLE origen ("
                + "idorigen CHAR(10) NOT NULL PRIMARY KEY,"
                + "origencol VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T15 = "CREATE TABLE pedido ("
                + "cod_pedido CHAR(10) NOT NULL PRIMARY KEY,"
                + "hora TIME NOT NULL,"
                + "estadopedido_idestadopedido CHAR(10) NOT NULL,"
                + "reserva_cod_reserva CHAR(10) NOT NULL,"
                + "usuario_cod_usuario CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T16 = "CREATE TABLE presentacion ("
                + "idpresentacion CHAR(10) NOT NULL PRIMARY KEY,"
                + "presentacion VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T17 = "CREATE TABLE reserva ("
                + "cod_reserva CHAR(10) NOT NULL PRIMARY KEY,"
                + "hora TIME NOT NULL,"
                + "fecha DATE NOT NULL,"
                + "cant_personas INT(2) NOT NULL,"
                + "detalle VARCHAR(500) DEFAULT NULL,"
                + "cod_cliente CHAR(10) NOT NULL,"
                + "cod_mesa CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T18 = "CREATE TABLE restaurante ("
                + "cod_restaurante CHAR(10) NOT NULL PRIMARY KEY,"
                + "nombre VARCHAR(100) NOT NULL,"
                + "direccion VARCHAR(100) NOT NULL,"
                + "telefono CHAR(9) NOT NULL"
                + ") ENGINE=INNODB;";

        String T19 = "CREATE TABLE rol ("
                + "cod_rol CHAR(10) NOT NULL PRIMARY KEY,"
                + "nombre VARCHAR(30) NOT NULL"
                + ") ENGINE=INNODB;";

        String T20 = "CREATE TABLE sabor ("
                + "idsabor CHAR(10) NOT NULL PRIMARY KEY,"
                + "saborcol VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T21 = "CREATE TABLE tipobebida ("
                + "idtipobebida CHAR(10) NOT NULL PRIMARY KEY,"
                + "nomtipo VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T22 = "CREATE TABLE tipocomprobante ("
                + "cod_tipo CHAR(10) NOT NULL PRIMARY KEY,"
                + "nombre VARCHAR(50) NOT NULL"
                + ") ENGINE=INNODB;";

        String T23 = "CREATE TABLE tipoingre ("
                + "idtipoingre CHAR(10) NOT NULL PRIMARY KEY,"
                + "nom_tipo VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T24 = "CREATE TABLE ubicacion ("
                + "cod_ubicacion CHAR(10) NOT NULL PRIMARY KEY,"
                + "ubicacion VARCHAR(30) NOT NULL"
                + ") ENGINE=INNODB;";

        String T25 = "CREATE TABLE unidades_medida ("
                + "idunidades_medida CHAR(10) NOT NULL PRIMARY KEY,"
                + "unidades_medidacol VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T26 = "CREATE TABLE usoingredientes ("
                + "idusoingredientes CHAR(10) NOT NULL PRIMARY KEY,"
                + "comida_idcomida CHAR(10) NOT NULL,"
                + "almacen_cod_almacen CHAR(10) NOT NULL,"
                + "unidades_medida_idunidades_medida CHAR(10) NOT NULL,"
                + "cantidad INT(11) NOT NULL"
                + ") ENGINE=INNODB;";

        String T27 = "CREATE TABLE usuario ("
                + "cod_usuario CHAR(10) NOT NULL PRIMARY KEY,"
                + "usuario VARCHAR(30) NOT NULL,"
                + "clave VARCHAR(30) NOT NULL,"
                + "empleado_idempleado CHAR(10) NOT NULL,"
                + "rol_cod_rol CHAR(10) NOT NULL"
                + ")ENGINE=INNODB;";

        String T28 = "CREATE TABLE server ("
                + "idserver INT(15) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "usuario CHAR(25) DEFAULT NULL,"
                + "password CHAR(25) DEFAULT NULL"
                + ")ENGINE=INNODB;";

        C.InsertaRegistro(T1);
        C.InsertaRegistro(T2);
        C.InsertaRegistro(T3);
        C.InsertaRegistro(T4);
        C.InsertaRegistro(T5);
        C.InsertaRegistro(T6);
        C.InsertaRegistro(T7);
        C.InsertaRegistro(T8);
        C.InsertaRegistro(T9);
        C.InsertaRegistro(T10);
        C.InsertaRegistro(T11);
        C.InsertaRegistro(T12);
        C.InsertaRegistro(T13);
        C.InsertaRegistro(T14);
        C.InsertaRegistro(T15);
        C.InsertaRegistro(T16);
        C.InsertaRegistro(T17);
        C.InsertaRegistro(T18);
        C.InsertaRegistro(T19);
        C.InsertaRegistro(T20);
        C.InsertaRegistro(T21);
        C.InsertaRegistro(T22);
        C.InsertaRegistro(T23);
        C.InsertaRegistro(T24);
        C.InsertaRegistro(T25);
        C.InsertaRegistro(T26);
        C.InsertaRegistro(T27);
        C.InsertaRegistro(T28);
    }

    //CREACION DE RELACIONES ENTRE TABLAS
    public void CrearRelaciones() {
        String R1 = "ALTER TABLE comprobante "
                + "ADD FOREIGN KEY (cod_tipo) "
                + "REFERENCES tipocomprobante(cod_tipo) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R2 = "ALTER TABLE comprobante "
                + "ADD FOREIGN KEY (cod_cliente) "
                + "REFERENCES cliente(cod_cliente) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R3 = "ALTER TABLE comprobante "
                + "ADD FOREIGN KEY (usuario_cod_usuario) "
                + "REFERENCES usuario(cod_usuario) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R4 = "ALTER TABLE comprobante "
                + "ADD FOREIGN KEY (consumicion_cod_consumicion) "
                + "REFERENCES consumicion(cod_consumicion) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R5 = "ALTER TABLE consumicion "
                + "ADD FOREIGN KEY (comida_idcomida) "
                + "REFERENCES comida(idcomida) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R6 = "ALTER TABLE consumicion "
                + "ADD FOREIGN KEY (bebidas_idbebidas) "
                + "REFERENCES bebidas(idbebidas) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R7 = "ALTER TABLE consumicion "
                + "ADD FOREIGN KEY (pedido_cod_pedido) "
                + "REFERENCES pedido(cod_pedido) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R8 = "ALTER TABLE usuario "
                + "ADD FOREIGN KEY (empleado_idempleado) "
                + "REFERENCES empleado(idempleado) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R9 = "ALTER TABLE usuario "
                + "ADD FOREIGN KEY (rol_cod_rol) "
                + "REFERENCES rol(cod_rol) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R10 = "ALTER TABLE comida "
                + "ADD FOREIGN KEY (origen_idorigen) "
                + "REFERENCES origen(idorigen) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R11 = "ALTER TABLE bebidas "
                + "ADD FOREIGN KEY (tipobebida_idtipobebida) "
                + "REFERENCES tipobebida(idtipobebida) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R12 = "ALTER TABLE bebidas "
                + "ADD FOREIGN KEY (marca_idmarca) "
                + "REFERENCES marca(idmarca) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R13 = "ALTER TABLE bebidas "
                + "ADD FOREIGN KEY (sabor_idsabor) "
                + "REFERENCES sabor(idsabor) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R14 = "ALTER TABLE usoingredientes "
                + "ADD FOREIGN KEY (comida_idcomida) "
                + "REFERENCES comida(idcomida) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R15 = "ALTER TABLE usoingredientes "
                + "ADD FOREIGN KEY (almacen_cod_almacen) "
                + "REFERENCES almacen(cod_almacen) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R16 = "ALTER TABLE usoingredientes "
                + "ADD FOREIGN KEY (unidades_medida_idunidades_medida) "
                + "REFERENCES unidades_medida(idunidades_medida) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R17 = "ALTER TABLE almacen "
                + "ADD FOREIGN KEY (ingredientes_cod_ingredientes) "
                + "REFERENCES ingredientes(cod_ingredientes) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R18 = "ALTER TABLE almacen "
                + "ADD FOREIGN KEY (bebidas_idbebidas) "
                + "REFERENCES bebidas(idbebidas) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R19 = "ALTER TABLE almacen "
                + "ADD FOREIGN KEY (unidades_medida_idunidades_medida) "
                + "REFERENCES unidades_medida(idunidades_medida) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R20 = "ALTER TABLE almacen "
                + "ADD FOREIGN KEY (cod_restaurante) "
                + "REFERENCES restaurante(cod_restaurante) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R21 = "ALTER TABLE almacen "
                + "ADD FOREIGN KEY (usuario_cod_usuario) "
                + "REFERENCES usuario(cod_usuario) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R22 = "ALTER TABLE ingredientes "
                + "ADD FOREIGN KEY (color_idcolor) "
                + "REFERENCES color(idcolor) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R23 = "ALTER TABLE ingredientes "
                + "ADD FOREIGN KEY (presentacion_idpresentacion) "
                + "REFERENCES presentacion(idpresentacion) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R24 = "ALTER TABLE ingredientes "
                + "ADD FOREIGN KEY (tipoingre_idtipoingre) "
                + "REFERENCES tipoingre(idtipoingre) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R25 = "ALTER TABLE pedido "
                + "ADD FOREIGN KEY (estadopedido_idestadopedido) "
                + "REFERENCES estadopedido(idestadopedido) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R26 = "ALTER TABLE pedido "
                + "ADD FOREIGN KEY (reserva_cod_reserva) "
                + "REFERENCES reserva(cod_reserva) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R27 = "ALTER TABLE pedido "
                + "ADD FOREIGN KEY (usuario_cod_usuario) "
                + "REFERENCES usuario(cod_usuario) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R28 = "ALTER TABLE reserva "
                + "ADD FOREIGN KEY (cod_cliente) "
                + "REFERENCES cliente(cod_cliente) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R29 = "ALTER TABLE reserva "
                + "ADD FOREIGN KEY (cod_mesa) "
                + "REFERENCES mesa(cod_mesa) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R30 = "ALTER TABLE mesa "
                + "ADD FOREIGN KEY (cod_estado) "
                + "REFERENCES estado(cod_estado) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R31 = "ALTER TABLE mesa "
                + "ADD FOREIGN KEY (cod_ubicacion) "
                + "REFERENCES ubicacion(cod_ubicacion) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R32 = "ALTER TABLE mesa "
                + "ADD FOREIGN KEY (cod_restaurante) "
                + "REFERENCES restaurante(cod_restaurante) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        C.InsertaRegistro(R1);
        C.InsertaRegistro(R2);
        C.InsertaRegistro(R3);
        C.InsertaRegistro(R4);
        C.InsertaRegistro(R5);
        C.InsertaRegistro(R6);
        C.InsertaRegistro(R7);
        C.InsertaRegistro(R8);
        C.InsertaRegistro(R9);
        C.InsertaRegistro(R10);
        C.InsertaRegistro(R11);
        C.InsertaRegistro(R12);
        C.InsertaRegistro(R13);
        C.InsertaRegistro(R14);
        C.InsertaRegistro(R15);
        C.InsertaRegistro(R16);
        C.InsertaRegistro(R17);
        C.InsertaRegistro(R18);
        C.InsertaRegistro(R19);
        C.InsertaRegistro(R20);
        C.InsertaRegistro(R21);
        C.InsertaRegistro(R22);
        C.InsertaRegistro(R23);
        C.InsertaRegistro(R24);
        C.InsertaRegistro(R25);
        C.InsertaRegistro(R26);
        C.InsertaRegistro(R27);
        C.InsertaRegistro(R28);
        C.InsertaRegistro(R29);
        C.InsertaRegistro(R30);
        C.InsertaRegistro(R31);
        C.InsertaRegistro(R32);
    }

    //CREACION DE VISTAS EN LA BASE DE DATOS
    public void CrearVistas() {
        String V1 = "CREATE OR REPLACE VIEW vtasesion AS "
                + "SELECT usuario.cod_usuario, empleado.nombre AS empleado, rol.nombre AS rol, usuario.usuario, usuario.clave "
                + "FROM usuario "
                + "INNER JOIN rol "
                + "INNER JOIN empleado "
                + "ON usuario.rol_cod_rol=rol.cod_rol "
                + "AND usuario.empleado_idempleado=empleado.idempleado;";

        String V2 = "CREATE OR REPLACE VIEW vtamesa AS "
                + "SELECT mesa.cod_mesa, restaurante.nombre, mesa.num_mesa, mesa.cant_personas, mesa.fumador, ubicacion.ubicacion, estado.estado "
                + "FROM mesa "
                + "INNER JOIN estado "
                + "INNER JOIN ubicacion "
                + "INNER JOIN restaurante "
                + "ON mesa.cod_estado=estado.cod_estado "
                + "AND mesa.cod_ubicacion=ubicacion.cod_ubicacion "
                + "AND mesa.cod_restaurante=restaurante.cod_restaurante;";

        String V3 = "CREATE OR REPLACE VIEW vtabebida AS "
                + "SELECT bebidas.idbebidas, tipobebida.nomtipo AS tipo, marca.marcacol AS marca, sabor.saborcol AS sabor, bebidas.costo "
                + "FROM bebidas "
                + "LEFT JOIN tipobebida "
                + "ON bebidas.tipobebida_idtipobebida = tipobebida.idtipobebida "
                + "LEFT JOIN marca "
                + "ON bebidas.marca_idmarca = marca.idmarca "
                + "LEFT JOIN sabor "
                + "ON bebidas.sabor_idsabor = sabor.idsabor;";

        String V4 = "CREATE OR REPLACE VIEW vtaingredientes AS "
                + "SELECT ingredientes.cod_ingredientes, ingredientes.nom_ingrediente, color.color, presentacion.presentacion, tipoingre.nom_tipo "
                + "FROM ingredientes "
                + "LEFT JOIN color "
                + "ON ingredientes.color_idcolor = color.idcolor "
                + "LEFT JOIN presentacion "
                + "ON ingredientes.presentacion_idpresentacion = presentacion.idpresentacion "
                + "LEFT JOIN tipoingre "
                + "ON ingredientes.tipoingre_idtipoingre = tipoingre.idtipoingre;";

        String V5 = "CREATE OR REPLACE VIEW vtaalmaceningredientes AS "
                + "SELECT almacen.cod_almacen, ingredientes.nom_ingrediente AS ingrediente, almacen.cantidad, almacen.fechaexp, "
                + "unidades_medida.unidades_medidacol AS unidadmedida, restaurante.nombre AS restaurante, usuario.usuario AS usuario, almacen.tamaño_medida "
                + "FROM almacen "
                + "INNER JOIN ingredientes "
                + "INNER JOIN unidades_medida "
                + "INNER JOIN restaurante "
                + "INNER JOIN usuario "
                + "ON almacen.ingredientes_cod_ingredientes = ingredientes.cod_ingredientes "
                + "AND almacen.unidades_medida_idunidades_medida = unidades_medida.idunidades_medida "
                + "AND almacen.cod_restaurante = restaurante.cod_restaurante "
                + "AND almacen.usuario_cod_usuario = usuario.cod_usuario;";

        String V6 = "CREATE OR REPLACE VIEW vtaalmacenbebida AS "
                + "SELECT almacen.cod_almacen, vtabebida.tipo AS tipobebida, vtabebida.marca AS marcabebida, vtabebida.sabor AS saborbebida, "
                + "almacen.cantidad, almacen.fechaexp, unidades_medida.unidades_medidacol AS unidadmedida, restaurante.nombre AS restaurante, "
                + "usuario.usuario AS usuario, almacen.tamaño_medida "
                + "FROM almacen "
                + "INNER JOIN vtabebida "
                + "INNER JOIN unidades_medida "
                + "INNER JOIN restaurante "
                + "INNER JOIN usuario "
                + "ON almacen.bebidas_idbebidas = vtabebida.idbebidas "
                + "AND almacen.unidades_medida_idunidades_medida = unidades_medida.idunidades_medida "
                + "AND almacen.cod_restaurante = restaurante.cod_restaurante "
                + "AND almacen.usuario_cod_usuario = usuario.cod_usuario;";

        String V7 = "CREATE OR REPLACE VIEW vtausoingrediente AS "
                + "SELECT usoingredientes.idusoingredientes, comida.nombre_comida AS comida, vtaalmaceningredientes.ingrediente, "
                + "unidades_medida.unidades_medidacol AS inidadmedida, usoingredientes.cantidad "
                + "FROM usoingredientes "
                + "INNER JOIN comida "
                + "INNER JOIN vtaalmaceningredientes "
                + "INNER JOIN unidades_medida "
                + "ON usoingredientes.comida_idcomida = comida.idcomida "
                + "AND usoingredientes.almacen_cod_almacen = vtaalmaceningredientes.cod_almacen "
                + "AND usoingredientes.unidades_medida_idunidades_medida = unidades_medida.idunidades_medida;";

        String V8 = "CREATE OR REPLACE VIEW vtareserva AS "
                + "SELECT reserva.cod_reserva, reserva.fecha, reserva.hora, reserva.cant_personas, reserva.detalle, "
                + "cliente.nombre AS cliente, cliente.celular, mesa.num_mesa, mesa.cant_personas AS personasmesa, restaurante.nombre AS resta "
                + "FROM reserva "
                + "LEFT JOIN cliente "
                + "ON reserva.cod_cliente = cliente.cod_cliente "
                + "LEFT JOIN mesa "
                + "ON reserva.cod_mesa = mesa.cod_mesa "
                + "LEFT JOIN restaurante "
                + "ON mesa.cod_restaurante = restaurante.cod_restaurante;";

        String V9 = "CREATE OR REPLACE VIEW vtapedido AS "
                + "SELECT pedido.cod_pedido, pedido.hora, estadopedido.estado, vtareserva.cliente, vtareserva.num_mesa, vtareserva.cant_personas, usuario.usuario "
                + "FROM pedido "
                + "INNER JOIN estadopedido "
                + "INNER JOIN vtareserva "
                + "INNER JOIN usuario "
                + "ON pedido.estadopedido_idestadopedido = estadopedido.idestadopedido "
                + "AND pedido.reserva_cod_reserva = vtareserva.cod_reserva "
                + "AND pedido.usuario_cod_usuario = usuario.cod_usuario;";

        String V10 = "CREATE OR REPLACE VIEW vtacomida AS "
                + "SELECT comida.idcomida, origen.origencol, comida.nombre_comida, comida.costo "
                + "FROM comida "
                + "INNER JOIN origen "
                + "ON comida.origen_idorigen = origen.idorigen;";

        C.InsertaRegistro(V1);
        C.InsertaRegistro(V2);
        C.InsertaRegistro(V3);
        C.InsertaRegistro(V4);
        C.InsertaRegistro(V5);
        C.InsertaRegistro(V6);
        C.InsertaRegistro(V7);
        C.InsertaRegistro(V8);
        C.InsertaRegistro(V9);
        C.InsertaRegistro(V10);
    }
    
    //INSERCION DE DATOS EN LAS TABLAS
    public void InsertarDatos(){
        String I1="INSERT INTO empleado VALUES "
                + "('1035874584','ADMINISTRADOR',35874584,'943187153','AV. 27 DE NOVIEMBRE 694');";
        
        String I2="INSERT INTO rol VALUES "
                + "('R','ADMINISTRADOR'),"
                + "('R-1','CAJERO'),"
                + "('R-2','MOZO'),"
                + "('R-3','ALMACENERO');";
        
        String I3="INSERT INTO usuario VALUES "
                + "('A','ADMIN','v4NrsFIKhetmo9AYkHul/g==','1035874584','R');";
        
        String I4="INSERT INTO restaurante VALUES "
                + "('L','LA DELICIA','AV LUZURIAGA 1125','946845134');";
        
        C.InsertaRegistro(I1);
        C.InsertaRegistro(I2);
        C.InsertaRegistro(I3);
        C.InsertaRegistro(I4);
    }
}
