package principal.maquinaEstado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;

import principal.entes.Jugador;
import principal.mapas.Mapa;
import principal.maquinaEstado.EstadoJuego;

public class GestorJuego implements EstadoJuego {

    Mapa mapa = new Mapa("/Texto/prueba.dlm");
    Jugador jugador = new Jugador(1, 1);

    @Override
    public void actualizar() {
        this.jugador.actualizar();
    }

    @Override
    public void dibujar(final Graphics g) {
        this.mapa.dibujar(g, (int) this.jugador.getPosicionX(),
	 (int) this.jugador.getPosicionY());
        g.setColor(Color.red);
        g.drawString("X: " + this.jugador.getPosicionX(), 2, 10);
        g.drawString("Y: " + this.jugador.getPosicionY(), 2, 20);
     
        this.jugador.dibujar(g);
    }

}
