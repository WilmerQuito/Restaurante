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
public class PtUsuario {
    private static FrmUsuario Single;

    public PtUsuario() {
    }
    
    private static synchronized void Instancia() {
        if (Single == null) {
            Single = new FrmUsuario();
        }
    }

    public static FrmUsuario getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
}
