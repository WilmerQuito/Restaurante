/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatronVistas;

import Vista.*;

/**
 *
 * @author Wilmer Quito
 */
public class PtClienteNuevo {
    private static FrmClienteNuevo Single;

    public PtClienteNuevo() {
    }
    
    private static synchronized void Instancia() {
        if (Single == null) {
            Single = new FrmClienteNuevo();
        }
    }

    public static FrmClienteNuevo getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
}
