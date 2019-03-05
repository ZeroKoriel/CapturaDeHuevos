package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Bala {
    
    private int danio;
    private BufferedImage imagen;
    private int x;
    private int y;
    private boolean horizontal;
    private boolean pintar;
    private boolean marcado;
    
    public Bala () throws IOException {
        this.x = 0;
        this.y = 0;
        this.danio = (int) (Math.random() * 9) + 1;
         this.imagen = ImageIO.read(getClass().getResourceAsStream("/Assets/bala32.png"));
    }
    
    public Bala (int x, int y, boolean horizontal) throws IOException {
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
        this.pintar = true;
        this.marcado = false;
        this.danio = (int) (Math.random() * 9) + 1;
         this.imagen = ImageIO.read(getClass().getResourceAsStream("/Assets/bala32.png"));
    }
    
    public void draw(Graphics2D g) {
        moverse();
        if (this.pintar)
            g.drawImage(this.imagen, this.getX(), this.getY(), 25, 25, null);
    }

    private void moverse() {
        if (this.horizontal) {
           this.y +=3;
        } else {
          this.x+=3;
        }
        if (this.y > 565 || this.x > 765) {
            try {
                this.pintar = false;
                finalize();
            } catch (Throwable ex) {
                Logger.getLogger(Bala.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void limpiar() {
        try {
            this.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(Bala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }
    
    public int getDanio() {
        return danio;
    }

    public void setDanio(int danio) {
        this.danio = danio;
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

    public boolean isPintar() {
        return pintar;
    }

    public void setPintar(boolean pintar) {
        this.pintar = pintar;
    }
    
}