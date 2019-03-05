package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Fondo {

    private BufferedImage imagen;
    private int y;
    private int x;

    public Fondo() throws IOException {
        this.imagen = ImageIO.read(getClass().getResourceAsStream("/Assets/v.jpg"));
        this.x = 0;
        this.y = 0;
    }//constructor

    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(this.imagen, this.x, this.y, 800, 600, null);
    }
}
