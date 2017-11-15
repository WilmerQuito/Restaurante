/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatronVistas;

import Vista.FrmLoginPersonal;

/**
 *
 * @author Wilmer Quito
 */
public class PtLoginPersonal {
    private static FrmLoginPersonal Single;

    private PtLoginPersonal() {
    }
    
    private static synchronized void Instancia() {
        if (Single == null) {
            Single = new FrmLoginPersonal();
        }
    }

    public static FrmLoginPersonal getInstance() {
        if(Single == null){
            Instancia();
        }
        return Single;
    }
}
