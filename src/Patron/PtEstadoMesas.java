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
public class PtEstadoMesas {
    private static FrmEstadoMesas Single;

    public PtEstadoMesas() {
    }
    
    private static synchronized void Instancia() {
        if (Single == null) {
            Single = new FrmEstadoMesas();
        }
    }

    public static FrmEstadoMesas getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
}
