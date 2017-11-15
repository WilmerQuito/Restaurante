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
public class PtRol {
    private static FrmRol Single;

    private PtRol() {
    }
    
    private static synchronized void Instancia() {
        if (Single == null) {
            Single = new FrmRol();
        }
    }

    public static FrmRol getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
}
