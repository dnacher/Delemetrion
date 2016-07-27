package principal.sprites;

import java.awt.image.BufferedImage;

public class Sprite {

    private final BufferedImage imagen;
    private final int ancho;
    private final int alto;

    public Sprite(final BufferedImage imagen) {
        this.imagen = imagen;
        this.ancho = imagen.getWidth();
        this.alto = imagen.getHeight();
    }

    public BufferedImage getImagen() {
        return this.imagen;
    }

    public int getAncho() {
        return this.ancho;
    }

    public int getAlto() {
        return this.alto;
    }

}
