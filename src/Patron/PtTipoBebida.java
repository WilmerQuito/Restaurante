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
public class PtTipoBebida {
    private static FrmTipoBebida Single;

    public PtTipoBebida() {
    }
    
    private static synchronized void Instancia() {
        if (Single == null) {
            Single = new FrmTipoBebida();
        }
    }

    public static FrmTipoBebida getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
}
