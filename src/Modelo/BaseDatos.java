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

    public void EliminarBD() {
        C.sql = "DROP DATABASE IF EXISTS db_Restaurante2;";
        C.InsertaRegistro(C.sql);
    }

    public void CrearBD() {
        C.sql = "CREATE DATABASE db_Restaurante2;";
        C.InsertaRegistro(C.sql);
    }

    public void UsoDB() {
        C.sql = "USE db_restaurante2;";
        C.InsertaRegistro(C.sql);
    }

    public void CrearTablas() {
        String T1 = "CREATE TABLE almacen ("
                + "Cod_Almacen CHAR(10) NOT NULL PRIMARY KEY,"
                + "ingredientes_Cod_Ingredientes CHAR(10) DEFAULT NULL,"
                + "bebidas_idbebidas CHAR(10) DEFAULT NULL,"
                + "cantidad VARCHAR(45) NOT NULL,"
                + "FechaExp DATE NOT NULL,"
                + "Unidades_medida_idUnidades_medida CHAR(10) NOT NULL,"
                + "tamaño_medida VARCHAR(45) DEFAULT NULL,"
                + "Cod_Restaurante CHAR(10) NOT NULL,"
                + "usuario_Cod_Usuario CHAR(10) NOT NULL"
                + ")ENGINE=INNODB;";

        String T2 = "CREATE TABLE bebidas ("
                + "idbebidas CHAR(10) NOT NULL PRIMARY KEY,"
                + "tipoBebida_idtipoBebida CHAR(10) NOT NULL,"
                + "marca_idmarca CHAR(10) DEFAULT NULL,"
                + "sabor_idsabor CHAR(10) DEFAULT NULL,"
                + "Costo DOUBLE(8,2) NOT NULL"
                + ") ENGINE=INNODB;";

        String T3 = "CREATE TABLE cliente ("
                + "Cod_Cliente CHAR(10) NOT NULL PRIMARY KEY,"
                + "Nombre VARCHAR(100) NOT NULL,"
                + "DNI CHAR(8) NOT NULL,"
                + "Celular CHAR(9) DEFAULT NULL"
                + ") ENGINE=INNODB;";

        String T4 = "CREATE TABLE color ("
                + "idcolor CHAR(10) NOT NULL PRIMARY KEY,"
                + "color VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T5 = "CREATE TABLE comida ("
                + "idcomida CHAR(10) NOT NULL PRIMARY KEY,"
                + "nombre_comida VARCHAR(45) NOT NULL,"
                + "Costo DOUBLE(8,2) NOT NULL,"
                + "origen_idorigen CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T6 = "CREATE TABLE comprobante ("
                + "Cod_Comprobante CHAR(10) NOT NULL PRIMARY KEY,"
                + "RUC CHAR(11) DEFAULT NULL,"
                + "Direccion VARCHAR(100) DEFAULT NULL,"
                + "Fecha DATE NOT NULL,"
                + "Monto DOUBLE(8,2) NOT NULL,"
                + "Cod_Tipo CHAR(10) NOT NULL,"
                + "Cod_Cliente CHAR(10) NOT NULL,"
                + "usuario_Cod_Usuario CHAR(10) NOT NULL,"
                + "consumicion_Cod_Consumicion CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T7 = "CREATE TABLE consumicion ("
                + "Cod_Consumicion CHAR(10) NOT NULL PRIMARY KEY,"
                + "comida_idcomida CHAR(10) DEFAULT NULL,"
                + "bebidas_idbebidas CHAR(10) DEFAULT NULL,"
                + "Cantidad INT(3) NOT NULL,"
                + "pedido_Cod_Pedido CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T8 = "CREATE TABLE empleado ("
                + "idempleado CHAR(10) NOT NULL PRIMARY KEY,"
                + "nombre VARCHAR(45) NOT NULL,"
                + "dni INT(11) NOT NULL,"
                + "telefono VARCHAR(45) NOT NULL,"
                + "Direccion VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T9 = "CREATE TABLE estado ("
                + "Cod_Estado CHAR(10) NOT NULL PRIMARY KEY,"
                + "Estado VARCHAR(30) NOT NULL"
                + ") ENGINE=INNODB;";

        String T10 = "CREATE TABLE estadopedido ("
                + "idEstadoPedido CHAR(10) NOT NULL PRIMARY KEY,"
                + "Estado VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T11 = "CREATE TABLE ingredientes ("
                + "Cod_Ingredientes CHAR(10) NOT NULL PRIMARY KEY,"
                + "Nom_ingrediente VARCHAR(100) NOT NULL,"
                + "color_idcolor CHAR(10) DEFAULT NULL,"
                + "presentacion_idpresentacion CHAR(10) NOT NULL,"
                + "tipoIngre_idtipoIngre CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T12 = "CREATE TABLE marca ("
                + "idmarca CHAR(10) NOT NULL PRIMARY KEY,"
                + "marcacol VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T13 = "CREATE TABLE mesa ("
                + "Cod_Mesa CHAR(10) NOT NULL PRIMARY KEY,"
                + "Cant_Personas INT(2) NOT NULL,"
                + "Num_Mesa INT(2) NOT NULL,"
                + "Fumador CHAR(20) NOT NULL,"
                + "Cod_Estado CHAR(10) NOT NULL,"
                + "Cod_Ubicacion CHAR(10) NOT NULL,"
                + "Cod_Restaurante CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T14 = "CREATE TABLE origen ("
                + "idorigen CHAR(10) NOT NULL PRIMARY KEY,"
                + "origencol VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T15 = "CREATE TABLE pedido ("
                + "Cod_Pedido CHAR(10) NOT NULL PRIMARY KEY,"
                + "Hora TIME NOT NULL,"
                + "EstadoPedido_idEstadoPedido CHAR(10) NOT NULL,"
                + "reserva_Cod_Reserva CHAR(10) NOT NULL,"
                + "usuario_Cod_Usuario CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T16 = "CREATE TABLE presentacion ("
                + "idpresentacion CHAR(10) NOT NULL PRIMARY KEY,"
                + "presentacion VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T17 = "CREATE TABLE reserva ("
                + "Cod_Reserva CHAR(10) NOT NULL PRIMARY KEY,"
                + "Hora TIME NOT NULL,"
                + "Fecha DATE NOT NULL,"
                + "Cant_Personas INT(2) NOT NULL,"
                + "Detalle VARCHAR(500) DEFAULT NULL,"
                + "Cod_Cliente CHAR(10) NOT NULL,"
                + "Cod_Mesa CHAR(10) NOT NULL"
                + ") ENGINE=INNODB;";

        String T18 = "CREATE TABLE restaurante ("
                + "Cod_Restaurante CHAR(10) NOT NULL PRIMARY KEY,"
                + "Nombre VARCHAR(100) NOT NULL,"
                + "Direccion VARCHAR(100) NOT NULL,"
                + "Telefono CHAR(9) NOT NULL"
                + ") ENGINE=INNODB;";

        String T19 = "CREATE TABLE rol ("
                + "Cod_Rol CHAR(10) NOT NULL PRIMARY KEY,"
                + "Nombre VARCHAR(30) NOT NULL"
                + ") ENGINE=INNODB;";

        String T20 = "CREATE TABLE sabor ("
                + "idsabor CHAR(10) NOT NULL PRIMARY KEY,"
                + "saborcol VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T21 = "CREATE TABLE tipobebida ("
                + "idtipoBebida CHAR(10) NOT NULL PRIMARY KEY,"
                + "nomTipo VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T22 = "CREATE TABLE tipocomprobante ("
                + "Cod_Tipo CHAR(10) NOT NULL PRIMARY KEY,"
                + "Nombre VARCHAR(50) NOT NULL"
                + ") ENGINE=INNODB;";

        String T23 = "CREATE TABLE tipoingre ("
                + "idtipoIngre CHAR(10) NOT NULL PRIMARY KEY,"
                + "nom_tipo VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T24 = "CREATE TABLE ubicacion ("
                + "Cod_Ubicacion CHAR(10) NOT NULL PRIMARY KEY,"
                + "Ubicacion VARCHAR(30) NOT NULL"
                + ") ENGINE=INNODB;";

        String T25 = "CREATE TABLE unidades_medida ("
                + "idUnidades_medida CHAR(10) NOT NULL PRIMARY KEY,"
                + "Unidades_medidacol VARCHAR(45) NOT NULL"
                + ") ENGINE=INNODB;";

        String T26 = "CREATE TABLE usoingredientes ("
                + "idusoIngredientes CHAR(10) NOT NULL PRIMARY KEY,"
                + "comida_idcomida CHAR(10) NOT NULL,"
                + "almacen_Cod_Almacen CHAR(10) NOT NULL,"
                + "Unidades_medida_idUnidades_medida CHAR(10) NOT NULL,"
                + "cantidad INT(11) NOT NULL"
                + ") ENGINE=INNODB;";

        String T27 = "CREATE TABLE usuario ("
                + "Cod_Usuario CHAR(10) NOT NULL PRIMARY KEY,"
                + "Usuario VARCHAR(30) NOT NULL,"
                + "Clave VARCHAR(30) NOT NULL,"
                + "empleado_idempleado CHAR(10) NOT NULL,"
                + "rol_Cod_Rol CHAR(10) NOT NULL"
                + ")ENGINE=INNODB;";

        String T28 = "CREATE TABLE server ("
                + "idServer INT(15) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "Usuario CHAR(25) DEFAULT NULL,"
                + "Password CHAR(25) DEFAULT NULL"
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

    public void CrearRelaciones() {
        String R1 = "ALTER TABLE comprobante "
                + "ADD FOREIGN KEY (Cod_Tipo) "
                + "REFERENCES tipocomprobante(Cod_Tipo) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R2 = "ALTER TABLE comprobante "
                + "ADD FOREIGN KEY (Cod_Cliente) "
                + "REFERENCES cliente(Cod_Cliente) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R3 = "ALTER TABLE comprobante "
                + "ADD FOREIGN KEY (usuario_Cod_Usuario) "
                + "REFERENCES usuario(Cod_Usuario) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R4 = "ALTER TABLE comprobante "
                + "ADD FOREIGN KEY (consumicion_Cod_Consumicion) "
                + "REFERENCES consumicion(Cod_Consumicion) "
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
                + "ADD FOREIGN KEY (pedido_Cod_Pedido) "
                + "REFERENCES pedido(Cod_Pedido) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R8 = "ALTER TABLE usuario "
                + "ADD FOREIGN KEY (empleado_idempleado) "
                + "REFERENCES empleado(idempleado) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R9 = "ALTER TABLE usuario "
                + "ADD FOREIGN KEY (rol_Cod_Rol) "
                + "REFERENCES rol(Cod_Rol) "
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
                + "ADD FOREIGN KEY (almacen_Cod_Almacen) "
                + "REFERENCES almacen(Cod_Almacen) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R16 = "ALTER TABLE usoingredientes "
                + "ADD FOREIGN KEY (unidades_medida_idunidades_medida) "
                + "REFERENCES unidades_medida(idunidades_medida) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R17 = "ALTER TABLE almacen "
                + "ADD FOREIGN KEY (ingredientes_Cod_Ingredientes) "
                + "REFERENCES ingredientes(Cod_Ingredientes) "
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
                + "ADD FOREIGN KEY (Cod_Restaurante) "
                + "REFERENCES restaurante(Cod_Restaurante) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R21 = "ALTER TABLE almacen "
                + "ADD FOREIGN KEY (usuario_Cod_Usuario) "
                + "REFERENCES usuario(Cod_Usuario) "
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
                + "ADD FOREIGN KEY (reserva_Cod_Reserva) "
                + "REFERENCES reserva(Cod_Reserva) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R27 = "ALTER TABLE pedido "
                + "ADD FOREIGN KEY (usuario_Cod_Usuario) "
                + "REFERENCES usuario(Cod_Usuario) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R28 = "ALTER TABLE reserva "
                + "ADD FOREIGN KEY (Cod_Cliente) "
                + "REFERENCES cliente(Cod_Cliente) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R29 = "ALTER TABLE reserva "
                + "ADD FOREIGN KEY (Cod_Mesa) "
                + "REFERENCES mesa(Cod_Mesa) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R30 = "ALTER TABLE mesa "
                + "ADD FOREIGN KEY (Cod_Estado) "
                + "REFERENCES estado(Cod_Estado) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R31 = "ALTER TABLE mesa "
                + "ADD FOREIGN KEY (Cod_Ubicacion) "
                + "REFERENCES ubicacion(Cod_Ubicacion) "
                + "ON DELETE NO ACTION "
                + "ON UPDATE NO ACTION;";

        String R32 = "ALTER TABLE mesa "
                + "ADD FOREIGN KEY (Cod_Restaurante) "
                + "REFERENCES restaurante(Cod_Restaurante) "
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

    public void CrearVistas() {
        String V1 = "CREATE OR REPLACE VIEW VtaSesion AS "
                + "SELECT Usuario.Cod_Usuario, Empleado.nombre AS Empleado, Rol.Nombre AS Rol, Usuario.Usuario, Usuario.Clave "
                + "FROM Usuario "
                + "INNER JOIN Rol "
                + "INNER JOIN Empleado "
                + "ON Usuario.rol_Cod_Rol=Rol.Cod_Rol "
                + "AND Usuario.empleado_idempleado=Empleado.idempleado;";

        String V2 = "CREATE OR REPLACE VIEW VtaMesa AS "
                + "SELECT Mesa.Cod_Mesa, Restaurante.Nombre, Mesa.Num_Mesa, Mesa.Cant_Personas, Mesa.Fumador, ubicacion.Ubicacion, Estado.Estado "
                + "FROM Mesa "
                + "INNER JOIN estado "
                + "INNER JOIN ubicacion "
                + "INNER JOIN restaurante "
                + "ON Mesa.Cod_Estado=Estado.Cod_Estado "
                + "AND Mesa.Cod_Ubicacion=Ubicacion.Cod_Ubicacion "
                + "AND Mesa.Cod_Restaurante=Restaurante.Cod_Restaurante;";

        String V3 = "CREATE OR REPLACE VIEW VtaBebida AS "
                + "SELECT bebidas.idbebidas, tipobebida.nomTipo AS Tipo, marca.marcacol AS Marca, sabor.saborcol AS Sabor, bebidas.costo "
                + "FROM bebidas "
                + "LEFT JOIN tipobebida "
                + "ON bebidas.tipoBebida_idtipoBebida = tipobebida.idtipoBebida "
                + "LEFT JOIN marca "
                + "ON bebidas.marca_idmarca = marca.idmarca "
                + "LEFT JOIN sabor "
                + "ON bebidas.sabor_idsabor = sabor.idsabor;";

        String V4 = "CREATE OR REPLACE VIEW VtaIngredientes AS "
                + "SELECT Ingredientes.Cod_Ingredientes, Ingredientes.Nom_Ingrediente, Color.color, presentacion.presentacion, tipoingre.nom_tipo "
                + "FROM Ingredientes "
                + "LEFT JOIN color "
                + "ON Ingredientes.color_idcolor = Color.idcolor "
                + "LEFT JOIN presentacion "
                + "ON Ingredientes.presentacion_idpresentacion = Presentacion.idpresentacion "
                + "LEFT JOIN tipoingre "
                + "ON Ingredientes.tipoingre_idtipoIngre = tipoingre.idtipoIngre;";

        String V5 = "CREATE OR REPLACE VIEW VtaAlmacenIngredientes AS "
                + "SELECT almacen.Cod_Almacen, ingredientes.Nom_ingrediente AS Ingrediente, almacen.cantidad, almacen.Fechaexp, "
                + "unidades_medida.unidades_medidacol AS unidadmedida, restaurante.Nombre AS Restaurante, usuario.usuario AS Usuario, almacen.tamaño_medida "
                + "FROM almacen "
                + "INNER JOIN ingredientes "
                + "INNER JOIN unidades_medida "
                + "INNER JOIN restaurante "
                + "INNER JOIN usuario "
                + "ON almacen.ingredientes_Cod_Ingredientes = ingredientes.Cod_Ingredientes "
                + "AND almacen.unidades_medida_idunidades_medida = unidades_medida.idunidades_medida "
                + "AND almacen.Cod_restaurante = restaurante.cod_Restaurante "
                + "AND almacen.usuario_cod_usuario = usuario.cod_usuario;";

        String V6 = "CREATE OR REPLACE VIEW VtaAlmacenBebida AS "
                + "SELECT almacen.Cod_Almacen, vtabebida.Tipo AS tipobebida, vtabebida.Marca AS Marcabebida, vtabebida.Sabor AS Saborbebida, "
                + "almacen.cantidad, almacen.Fechaexp, unidades_medida.unidades_medidacol AS unidadmedida, restaurante.Nombre AS Restaurante, "
                + "usuario.usuario AS Usuario, almacen.tamaño_medida "
                + "FROM almacen "
                + "INNER JOIN vtabebida "
                + "INNER JOIN unidades_medida "
                + "INNER JOIN restaurante "
                + "INNER JOIN usuario "
                + "ON almacen.bebidas_idbebidas = vtabebida.idbebidas "
                + "AND almacen.unidades_medida_idunidades_medida = unidades_medida.idunidades_medida "
                + "AND almacen.Cod_restaurante = restaurante.cod_Restaurante "
                + "AND almacen.usuario_cod_usuario = usuario.cod_usuario;";

        String V7 = "CREATE OR REPLACE VIEW VtaUsoIngrediente AS "
                + "SELECT usoingredientes.idusoingredientes, comida.nombre_comida AS comida, VtaAlmacenIngredientes.Ingrediente, "
                + "unidades_medida.unidades_medidacol AS inidadmedida, usoingredientes.cantidad "
                + "FROM usoingredientes "
                + "INNER JOIN comida "
                + "INNER JOIN vtaalmaceningredientes "
                + "INNER JOIN unidades_medida "
                + "ON usoingredientes.comida_idcomida = comida.idcomida "
                + "AND usoingredientes.almacen_Cod_almacen = vtaalmaceningredientes.Cod_Almacen "
                + "AND usoingredientes.unidades_medida_idunidades_medida = unidades_medida.idunidades_medida;";

        String V8 = "CREATE OR REPLACE VIEW VtaReserva AS "
                + "SELECT Reserva.Cod_Reserva, Reserva.Fecha, Reserva.Hora, Reserva.Cant_Personas, Reserva.Detalle, "
                + "Cliente.Nombre AS cliente, Cliente.Celular, Mesa.Num_Mesa, Mesa.Cant_Personas AS personasmesa, restaurante.Nombre AS Resta "
                + "FROM reserva "
                + "LEFT JOIN cliente "
                + "ON reserva.Cod_Cliente = cliente.Cod_cliente "
                + "LEFT JOIN mesa "
                + "ON reserva.Cod_Mesa = mesa.Cod_Mesa "
                + "LEFT JOIN restaurante "
                + "ON mesa.Cod_Restaurante = restaurante.Cod_Restaurante;";

        String V9 = "CREATE OR REPLACE VIEW VtaPedido AS "
                + "SELECT pedido.Cod_Pedido, pedido.hora, estadopedido.estado, vtareserva.cliente, vtareserva.Num_Mesa, vtareserva.Cant_Personas, usuario.usuario "
                + "FROM pedido "
                + "INNER JOIN estadopedido "
                + "INNER JOIN vtareserva "
                + "INNER JOIN usuario "
                + "ON pedido.EstadoPedido_idestadopedido = EstadoPedido.idestadopedido "
                + "AND pedido.reserva_cod_reserva = vtareserva.cod_reserva "
                + "AND pedido.usuario_Cod_usuario = usuario.Cod_usuario;";

        String V10 = "CREATE OR REPLACE VIEW VtaComida AS "
                + "SELECT Comida.idcomida, origen.origencol, comida.nombre_comida, comida.costo "
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
        
        C.InsertaRegistro(I1);
        C.InsertaRegistro(I2);
        C.InsertaRegistro(I3);
    }
}


//BaseDatos BD = BaseDatos.getInstance();
//        
//        BD.EliminarBD();
//        BD.CrearBD();
//        BD.UsoDB();
//        BD.CrearTablas();
//        BD.CrearRelaciones();
//        BD.CrearVistas();
//        BD.InsertarDatos();
