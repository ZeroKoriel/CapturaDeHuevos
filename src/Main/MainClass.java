package Main;

import GUI.PanelJuego;
import com.panamahitek.ArduinoException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import jssc.SerialPortException;

public class MainClass {

    public static void main(String[] args) {
        try {
            PanelJuego panelJuego = new PanelJuego();
            JFrame jFrame = new  JFrame();
            jFrame.setContentPane(panelJuego);
            panelJuego.setVisible(true);
            jFrame.pack();
            jFrame.setResizable(false);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setVisible(true);
        } catch (ArduinoException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}