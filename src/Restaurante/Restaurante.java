/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante;

import Controlador.*;
import Vista.*;

/**
 *
 * @author Wilmer Quito
 */
public class Restaurante {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrmBuscarCliente Frm = new FrmBuscarCliente();
        CtrlBuscarCliente Ctl = new CtrlBuscarCliente(Frm);

        /*FrmReservaCliente Frm = new FrmReservaCliente();
        CtrlReservaCliente Ctl = new CtrlReservaCliente(Frm);*/
        
        Ctl.Iniciar();
        Frm.setVisible(true);
    }
}
