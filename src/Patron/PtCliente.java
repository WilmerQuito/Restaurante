/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patron;

import Vista.*;

/**
 *
 * @author Wilmer Quito
 */
public class PtCliente {
    private static FrmCliente Single;

    public PtCliente() {
    }
    
    private static synchronized void Instancia() {
        if (Single == null) {
            Single = new FrmCliente();
        }
    }

    public static FrmCliente getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
}
