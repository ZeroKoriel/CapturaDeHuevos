package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Huevo {

    private int x;
    private int y;
    private boolean robado;
    private BufferedImage imagen;

    public Huevo(int x, int y) {
        this.x = x;
        this.y = y;
        this.robado = false;
        try {
            this.imagen = ImageIO.read(getClass().getResourceAsStream(escogerHuevo()));
        } catch (IOException ex) {
            Logger.getLogger(Pollo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Huevo() {
        this.x = 0;
        this.y = 0;
        this.robado = false;
        try {
            this.imagen = ImageIO.read(getClass().getResourceAsStream(escogerHuevo()));
        } catch (IOException ex) {
            Logger.getLogger(Pollo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String escogerHuevo() {
        int valor = (int) (Math.random() * 390) +10;
        
        if (valor < 101) {
            return "/Assets/h1.png";
        }else if (valor < 201) {
            return "/Assets/h2.png";
        } else if (valor < 301) {
            return "/Assets/h3.png";
        } else {
            return "/Assets/h4.png";
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

    public boolean isRobado() {
        return robado;
    }

    public void setRobado(boolean robado) {
        this.robado = robado;
    }
    
    public void draw(Graphics2D g) {
        g.drawImage(this.imagen, this.getX(), this.getY(), 50, 50, null);
    }
}
