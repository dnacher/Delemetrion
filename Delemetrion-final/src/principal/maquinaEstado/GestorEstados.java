package principal.maquinaEstado;

import java.awt.Graphics;

import principal.maquinaEstado.estados.juego.GestorJuego;

public class GestorEstados {
    private EstadoJuego[] estados;
    private EstadoJuego estadoActual;

    public GestorEstados() {
        iniciarEstados();
        iniciarEstadoActual();

    }

    private void iniciarEstados() {
        this.estados = new EstadoJuego[1];
        this.estados[0] = new GestorJuego();
        // aÃ±adir e iniciar los demas estados a medida que los creemos
    }

    private void iniciarEstadoActual() {
        this.estadoActual = this.estados[0];

    }

    public void actualizar() {
        this.estadoActual.actualizar();
    }

    public void dibujar(final Graphics g) {
        this.estadoActual.dibujar(g);
    }

    public void cambiarEstadoActual(final int nuevoEstado) {
        this.estadoActual = this.estados[nuevoEstado];
    }

    public EstadoJuego getEstadoActual() {
        return this.estadoActual;
    }
}
