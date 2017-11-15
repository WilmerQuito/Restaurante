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
public class PtBuscarCliente {
    private static FrmBuscarCliente Single;

    public PtBuscarCliente() {
    }
    
    private static synchronized void Instancia() {
        if (Single == null) {
            Single = new FrmBuscarCliente();
        }
    }

    public static FrmBuscarCliente getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
}
