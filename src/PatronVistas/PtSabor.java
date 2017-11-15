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
public class PtSabor {
    private static FrmSabor Single;

    public PtSabor() {
    }
    
    private static synchronized void Instancia() {
        if (Single == null) {
            Single = new FrmSabor();
        }
    }

    public static FrmSabor getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
}
