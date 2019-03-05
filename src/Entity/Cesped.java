package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cesped {
    
    private int x;
    private int y;
    private BufferedImage imagen;

    public Cesped(int x, int y) throws IOException {
        this.x = x;
        this.y = y;
         this.imagen = ImageIO.read(getClass().getResourceAsStream("/Assets/cesped.png"));
    }
  
    public void draw(Graphics2D g) {
        g.drawImage(this.imagen, this.getX(), this.getY(), 50, 50, null);
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
    
}