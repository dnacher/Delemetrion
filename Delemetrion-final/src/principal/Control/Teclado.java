package principal.Control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

    private final int numeroTeclas = 256;
    private final boolean[] teclas = new boolean[numeroTeclas];

    private boolean arriba;
    private boolean abajo;
    private boolean izquierda;
    private boolean derecha;
    private boolean correr;
    private boolean salir;

    public void actualizar() {
        this.arriba = this.teclas[KeyEvent.VK_W];
        this.abajo = this.teclas[KeyEvent.VK_S];
        this.izquierda = this.teclas[KeyEvent.VK_A];
        this.derecha = this.teclas[KeyEvent.VK_D];
        this.correr = this.teclas[KeyEvent.VK_SHIFT];
        this.salir = this.teclas[KeyEvent.VK_ESCAPE];
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        this.teclas[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        this.teclas[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(final KeyEvent arg0) {

    }

    public boolean getArriba() {
        return this.arriba;
    }

    public boolean getAbajo() {
        return this.abajo;
    }

    public boolean getIzquierda() {
        return this.izquierda;
    }

    public boolean getDerecha() {
        return this.derecha;
    }

    public boolean getCorrer() {
        return this.correr;
    }

    public boolean getSalir() {
        return this.salir;
    }

}
