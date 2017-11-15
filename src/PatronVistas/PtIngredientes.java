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
public class PtIngredientes {
    private static FrmIngredientes Single;

    public PtIngredientes() {
    }
    
    private static synchronized void Instancia() {
        if (Single == null) {
            Single = new FrmIngredientes();
        }
    }

    public static FrmIngredientes getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
}
