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
public class PtMarca {
    private static FrmMarca Single;

    public PtMarca() {
    }
    
    private static synchronized void Instancia() {
        if (Single == null) {
            Single = new FrmMarca();
        }
    }

    public static FrmMarca getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
}
