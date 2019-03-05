package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Pollo extends Thread {

    private int x;
    private int y;
    private boolean ejecutar;
    private boolean horizontal;
    private BufferedImage imagen;
    private boolean vCentinela;
    private boolean hCentinela;
    private ArrayList<Bala> balas;
    private Graphics2D g;

    public Pollo(int x, int y, BufferedImage imagen) throws IOException {
        this.x = x;
        this.y = y;
        this.ejecutar = true;
        this.horizontal = false;
        this.imagen = ImageIO.read(getClass().getResourceAsStream("/Assets/pato.png"));
    }

    public Pollo(int x, int y, boolean horizontal) throws IOException {
        this.x = x;
        this.y = y;
        this.ejecutar = true;
        this.vCentinela = true;
        this.hCentinela = true;
        this.horizontal = horizontal;
        this.balas = new ArrayList<>();
        cargar();
        this.imagen = ImageIO.read(getClass().getResourceAsStream("/Assets/pato.png"));
    }

    public Pollo(int x, int y, boolean horizontal, Graphics2D g) throws IOException {
        this.x = x;
        this.y = y;
        this.ejecutar = true;
        this.vCentinela = true;
        this.hCentinela = true;
        this.horizontal = horizontal;
        this.balas = new ArrayList<>();
        this.g = g;
        cargar();
        this.imagen = ImageIO.read(getClass().getResourceAsStream("/Assets/pato.png"));
    }

    public Pollo() {
        this.x = 0;
        this.y = 0;
        this.ejecutar = true;
        this.horizontal = false;
        try {
            this.imagen = ImageIO.read(getClass().getResourceAsStream(""));
        } catch (IOException ex) {
            Logger.getLogger(Pollo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (this.ejecutar) {
            try {
                comprobar();
                moverse();
            } catch (InterruptedException ex) {
                Logger.getLogger(Pollo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void comprobar() {
        if (this.x < 10) {
            this.hCentinela = false;
        } else if (this.x > 749) {
            this.hCentinela = true;
        }
        if (this.y < 10) {
            this.vCentinela = false;
        } else if (this.y > 549) {
            this.vCentinela = true;
        }

    }

    private void moverse() throws InterruptedException {
        if (this.horizontal) {
            if (this.hCentinela) {
                /*resta*/
                --this.x;
            } else {
                /*suma*/
                ++this.x;
            }
        } else {
            if (this.vCentinela) {
                /*resta*/
                --this.y;
            } else {
                /*suma*/
                ++this.y;
            }
        }
        Thread.sleep(35);
    }

    private void cargar() throws IOException {
        for (int i = 0; i < 30; i++) {
            this.balas.add(new Bala(0, 0, this.horizontal));
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void disparar() {

    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public void draw(Graphics2D gP) {
        gP.drawImage(this.imagen, this.getX(), this.getY(), 50, 50, null);
    }
}
