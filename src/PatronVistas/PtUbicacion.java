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
public class PtUbicacion {
    private static FrmUbicacion Single;

    public PtUbicacion() {
    }
    
    private static synchronized void Instancia() {
        if (Single == null) {
            Single = new FrmUbicacion();
        }
    }

    public static FrmUbicacion getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
}
