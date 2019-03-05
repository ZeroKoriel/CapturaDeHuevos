package GUI;

import Entity.Fondo;
import Entity.Huevo;
import Entity.Jugador;
import Entity.Pollo;
import Entity.Suelo;
import Entity.Bala;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class PanelJuego extends JPanel implements Runnable {

    private Thread hilo;
    private Fondo fondo;
    private Suelo suelo;
    private BufferedImage image;
    private Graphics2D graphics2D;

    private PanamaHitek_Arduino arduino;

    private boolean ejecutar;

    private final String PORTNAME = "/dev/ttyACM0";
    private final int DATARATE = 9600;

    private Jugador jugador;
    private ArrayList<Pollo> pollos;
    private ArrayList<Huevo> huevos;
    private ArrayList<Bala> balas;

    SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {
            try {
                if (arduino.isMessageAvailable()) {
                    String mensaje = arduino.printMessage();
                    switch (mensaje) {
                        case "1":
                            jugador.setY(jugador.getY() - 10);
                            break;
                        case "2":
                            jugador.setX(jugador.getX() + 10);
                            break;
                        case "3":
                            jugador.setY(jugador.getY() + 10);
                            break;
                        case "4":
                            jugador.setX(jugador.getX() - 10);
                            break;
                    }
                }
            } catch (SerialPortException ex) {
                Logger.getLogger(PanelJuego.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ArduinoException ex) {
                Logger.getLogger(PanelJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public PanelJuego() throws ArduinoException, SerialPortException, IOException {
        this.ejecutar = true;
        this.arduino = new PanamaHitek_Arduino();
        this.jugador = new Jugador();
        this.pollos = new ArrayList<>();
        this.fondo = new Fondo();
        this.suelo = new Suelo();
        this.huevos = new ArrayList<>();
        this.balas = new ArrayList<>();
        setPreferredSize(new Dimension(800, 600));
        this.arduino.arduinoRXTX(PORTNAME, DATARATE, listener);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (this.hilo == null) {
            this.hilo = new Thread(this);
            this.hilo.start();
        }
    }

    @Override
    public void run() {
        iniciar();
        activarPollos();
        activarHuevos();
        long inicio = System.nanoTime();
        long duracion = 0;
        while (this.ejecutar) {
            try {
                Thread.sleep(25);

                draw();
                comprobarDanio();
                duracion = System.nanoTime() - inicio;
                duracion /= 1000000000;
                if (duracion >= 3) {
                    disparar();
                    inicio = System.nanoTime();
                }
                drawToscreen();

                Thread.sleep(25);
            } catch (InterruptedException ex) {
                Logger.getLogger(PanelJuego.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PanelJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void draw() throws InterruptedException {
        this.fondo.draw(graphics2D);
        this.suelo.draw(graphics2D);

        for (Pollo pollo : pollos) {
            pollo.draw(graphics2D);
        }

        for (Bala bala : balas) {
            bala.draw(graphics2D);
        }

        for (Huevo huevo : huevos) {
            huevo.draw(graphics2D);
        }
        this.jugador.draw(graphics2D);
    }

    private void disparar() throws IOException {
        for (Pollo pollo : pollos) {
            this.balas.add(new Bala(pollo.getX(), pollo.getY(), pollo.isHorizontal()));
        }
    }

    private void drawToscreen() {
        Graphics g = this.getGraphics();
        g.drawImage(this.image, 0, 0, 800, 600, null);
        g.dispose();
    }

    private void iniciar() {
        this.image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        this.graphics2D = (Graphics2D) this.image.getGraphics();
    }

    private void activarPollos() {
        int x = 0;
        int y = 0;
        boolean horizontal = false;
        for (int i = 0; i < 4; i++) {
            try {
                if (i % 2 == 0) {
                    x = 10;
                    y = (int) (Math.random() * 550 + 10);
                    horizontal = false;
                } else {
                    x = (int) (Math.random() * 750 + 10);
                    y = 10;
                    horizontal = true;
                }
                Pollo pollo = new Pollo(x, y, horizontal);
                pollo.start();
                this.pollos.add(pollo);
            } catch (IOException ex) {
                Logger.getLogger(PanelJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void comprobarDanio() {
        for (Bala bala : this.balas) {
            if (!bala.isMarcado()) {
                int pX = bala.getX() + 25;
                int pY = bala.getY() + 25;
                if (bala.getX() >= this.jugador.getX() && bala.getY() >= this.jugador.getY()) {
                    if (bala.getX() <= (this.jugador.getX() + 50) && bala.getY() <= (this.jugador.getY() + 50 )) {
                        bala.setPintar(false);
                        bala.setMarcado(ejecutar);
                        this.jugador.danioRecibido(bala.getDanio());
                        System.err.println("Vida: " + this.jugador.getPuntosVida());
                        break;
                    }
                } else if (pX >= this.jugador.getX() && pY >= this.jugador.getY()) {
                    if (pX <= (this.jugador.getX() + 50) && pY <= (this.jugador.getY() + 50 )) {
                        bala.setPintar(false);
                        bala.setMarcado(ejecutar);
                        this.jugador.danioRecibido(bala.getDanio());
                        System.err.println("Vida: " + this.jugador.getPuntosVida());
                        break;
                    }
                }
            }
        }
    }

    private void activarHuevos() {
        int x = 0;
        int y = 0;
        for (int i = 0; i < 10; i++) {
            x = (int) (Math.random() * 700) + 50;
            y = (int) (Math.random() * 500) + 50;
            this.huevos.add(new Huevo(x, y));
        }
    }
}
