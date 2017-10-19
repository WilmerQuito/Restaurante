/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaura;

import Controlador.*;
import Vista.*;

/**
 *
 * @author Wilmer Quito
 */
public class Restaura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrmCliente FrmCli = new FrmCliente();
        CtrlCliente CtrlCli = new CtrlCliente(FrmCli);
        
        FrmMesa FrmMe = new FrmMesa();
        CtrlMesa CtrlMe = new CtrlMesa(FrmMe);
        
        CtrlCli.IniciarCliente();
        CtrlMe.IniciarMesas();
        FrmCli.setVisible(true);
        FrmMe.setVisible(true);
    }
}
