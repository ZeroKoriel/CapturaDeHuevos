package Entity;

import java.awt.Graphics2D;
import java.io.IOException;

public class Suelo {
    
    private Cesped[][] cesped;
    
    public Suelo() throws IOException {
        this.cesped = inicializar();
    }
    
    private Cesped[][] inicializar() throws IOException {
        Cesped[][] salida = new Cesped[12][16];
        
        int x = 0;
        int y = 0;
        
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 16; j++) {
                salida[i][j] = new Cesped(x, y);
                x += 50;
            }
            y += 50;
            x = 0;
        }
        
        return salida;
    }
    
    public void draw(Graphics2D g) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 16; j++) {
                this.cesped[i][j].draw(g);
            }
        }
    }
}