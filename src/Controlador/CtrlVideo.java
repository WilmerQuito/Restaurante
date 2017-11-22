/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Controlador;
import Vista.FrmVideo;
import java.awt.BorderLayout;
import java.io.File;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlVideo {
    
    Controlador C = Controlador.getInstance();
    private static FrmVideo Frm;
    private static CtrlVideo Single;
    private final JFXPanel jfxPanel = new JFXPanel();
    
    private CtrlVideo (FrmVideo Frm){
        this.Frm = Frm;
    }
    
    public static synchronized CtrlVideo getInstance(FrmVideo Frm){
        if(Single == null){
            Single = new CtrlVideo(Frm);
        }
        return Single;
    }
    
    public void Iniciar(){
        Frm.setTitle("HOLA...!!!");
        Frm.setResizable(false);
        Frm.setLocationRelativeTo(null);
        Frm.jpPantalla.setLayout(new BorderLayout());
        Frm.jpPantalla.add(jfxPanel,BorderLayout.CENTER);
        CrearEscena();
    }
    
    private void CrearEscena(){
        Platform.runLater(new Runnable() {
             @Override
             public void run() {                 
                try{
                    String ruta = System.getProperties().getProperty("user.dir") + "/src/Complemento/Reserva.mp4";
                    File file = new File(ruta);
                                                 
                    MediaPlayer oracleVid = new MediaPlayer(                                       
                        new Media(file.toURI().toString())
                    );
                    //se añade video al jfxPanel
                    jfxPanel.setScene(new Scene(new Group(new MediaView(oracleVid))));                    
                    oracleVid.setVolume(0);//volumen
                    oracleVid.setCycleCount(MediaPlayer.INDEFINITE);//repite video
                    oracleVid.play();//play video
                }catch(Exception e){
                    C.Mensaje(String.valueOf(e));
                }
             }
        });
    }
    
    
    }