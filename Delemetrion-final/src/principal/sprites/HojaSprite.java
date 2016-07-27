package principal.sprites;

import java.awt.image.BufferedImage;

import principal.herramientas.CargadorRecursos;

public class HojaSprite {

    final private int anchoHojaEnPixeles;
    final private int altoHojaEnPixeles;
    final private int anchoHojaEnSprites;
    final private int altoHojaEnSprites;

    private int anchoSprites;
    private int altoSprites;
    final private Sprite[] sprites;

    public HojaSprite(final String ruta, final int SpriteSize, final boolean HojaOpaca) {
        BufferedImage imagen;
        if (HojaOpaca) {
            imagen = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);
        } else {
            imagen = CargadorRecursos.cargarImagenCompatibleTranslucida(ruta);
        }
        this.anchoHojaEnPixeles = imagen.getWidth();
        this.altoHojaEnPixeles = imagen.getHeight();
        this.anchoHojaEnSprites = this.anchoHojaEnPixeles / SpriteSize;
        this.altoHojaEnSprites = this.altoHojaEnPixeles / SpriteSize;
        this.anchoSprites = SpriteSize;
        this.altoSprites = SpriteSize;
        this.sprites = new Sprite[this.anchoHojaEnSprites * this.altoHojaEnSprites];
        rellenarSpritesDesdeImagen(imagen);
    }

    public HojaSprite(final String ruta, final int anchoSprites, final int altoSprites, final boolean HojaOpaca) {
        BufferedImage imagen;
        if (HojaOpaca) {
            imagen = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);
        } else {
            imagen = CargadorRecursos.cargarImagenCompatibleTranslucida(ruta);
        }
        this.anchoHojaEnPixeles = imagen.getWidth();
        this.altoHojaEnPixeles = imagen.getHeight();
        this.anchoHojaEnSprites = this.anchoHojaEnPixeles / anchoSprites;
        this.altoHojaEnSprites = this.altoHojaEnPixeles / altoSprites;
        this.anchoSprites = anchoSprites;
        this.altoSprites = altoSprites;
        this.sprites = new Sprite[this.anchoHojaEnSprites * this.altoHojaEnSprites];
        rellenarSpritesDesdeImagen(imagen);
    }

    private void rellenarSpritesDesdeImagen(final BufferedImage imagen) {
        for (int y = 0; y < this.altoHojaEnSprites; y++) {
            for (int x = 0; x < this.anchoHojaEnSprites; x++) {
                final int posicionX = x * this.anchoSprites;
                final int posicionY = y * this.altoSprites;
                this.sprites[x + y * this.anchoHojaEnSprites] = new Sprite(imagen.getSubimage(posicionX, posicionY, this.anchoSprites,
                        this.altoSprites));
            }
        }
    }

    public Sprite getSprite(final int indice) {
        return this.sprites[indice];
    }

    public Sprite getSprite(final int x, final int y) {
        return this.sprites[x + y * this.anchoHojaEnSprites];
    }

}