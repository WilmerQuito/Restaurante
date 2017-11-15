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
public class PtColor {
    private static FrmColor Single;

    public PtColor() {
    }
    
    private static synchronized void Instancia() {
        if (Single == null) {
            Single = new FrmColor();
        }
    }

    public static FrmColor getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
}
