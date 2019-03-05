package GUI;

import com.panamahitek.ArduinoException;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;
import jssc.SerialPortException;

public class VentanaPrincipal extends JFrame{

    private PanelJuego panelJuego;
    
    public VentanaPrincipal() throws ArduinoException, SerialPortException, IOException {
        super("Ventana Principal");
        setSize(new Dimension(800, 600));
        setLayout(null);
        
        init();
    }
    
    private void init() throws ArduinoException, SerialPortException, IOException {
        this.panelJuego = new PanelJuego();
        this.panelJuego.setVisible(true);
        this.add(this.panelJuego);
        this.pack();
    }
}