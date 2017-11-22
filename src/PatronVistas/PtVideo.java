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
public class PtVideo {
    private static FrmVideo Single;

    public PtVideo() {
    }
    
    private static synchronized void Instancia() {
        if (Single == null) {
            Single = new FrmVideo();
        }
    }

    public static FrmVideo getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
}
