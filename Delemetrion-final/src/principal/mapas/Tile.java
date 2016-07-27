package principal.mapas;

import java.awt.Rectangle;

import principal.sprites.Sprite;

public class Tile {

    private final Sprite sprite;
    private final int id;
    private boolean solido;

    public Tile(final Sprite sprite, final int id) {
        this.sprite = sprite;
        this.id = id;
        this.solido = false;
    }

    public Tile(final Sprite sprite, final int id, final boolean solido) {
        this.sprite = sprite;
        this.id = id;
        this.solido = solido;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public int getId() {
        return this.id;
    }

    public void setSolido(final boolean solido) {
        this.solido = solido;
    }

    public Rectangle getLimites(final int x, final int y) {
        return new Rectangle(x, y, this.sprite.getAncho(), this.sprite.getAlto());
    }

}
