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
        /*
        FrmLogin Frm = new FrmLogin();
        CtrlLogin Ctl= new CtrlLogin(Frm);
        */
        
        FrmMesa Frm = new FrmMesa();
        CtrlMesa Ctl= new CtrlMesa(Frm);
        
        Ctl.Iniciar();
        Frm.setVisible(true);

    }
}
