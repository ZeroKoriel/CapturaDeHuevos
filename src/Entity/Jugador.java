package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Jugador {

    private int puntuacion;
    private int salud;
    private int x;
    private int y;
    private BufferedImage imagen;

    public Jugador(int x, int y) throws IOException {
        this.puntuacion = 0;
        this.salud = 100;
        this.x = x;
        this.y = y;
        this.imagen = ImageIO.read(getClass().getResourceAsStream("/Assets/conejo.png"));
    }

    public Jugador() throws IOException {
        this.puntuacion = 0;
        this.salud = 100;
        this.x = 400;
        this.y = 300;
        this.imagen = ImageIO.read(getClass().getResourceAsStream("/Assets/conejo.png"));
    }

    public void danioRecibido(int danio) {
        this.salud -= danio;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion += puntuacion;
    }

    public int getPuntosVida() {
        return salud;
    }

    public void setPuntosVida(int salud) {
        this.salud = salud;
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

    public void draw(Graphics2D g) {
        g.drawImage(this.imagen, this.getX(), this.getY(), 50, 50, null);
    }
}
