package principal;

import principal.Control.GestorControles;
import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.maquinaEstado.GestorEstados;

public class GestorPrincipal {

    private boolean enFuncionamiento = false;
    private String titulo;
    private int ancho;
    private int alto;

    private SuperficieDibujo sd;
    private Ventana ventana;
    private GestorEstados ge;

    private GestorPrincipal(final String titulo, final int ancho, final int alto) {
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
    }

    public static void main(final String[] args) {
        GestorPrincipal gp = new GestorPrincipal("Delemetrion", 640, 320);
        Constantes.ANCHO_PANTALLA=640;
        Constantes.ALTO_PANTALLA=320;
        gp.iniciarJuego();
        gp.iniciarBuclePrincipal();

    }

    private void iniciarJuego() {
        this.enFuncionamiento = true;
        incializar();

    }

    private void incializar() {
        this.sd = new SuperficieDibujo(this.ancho, this.alto);
        this.ventana = new Ventana(this.titulo, this.sd);
        this.ge = new GestorEstados();
    }

    private void iniciarBuclePrincipal() {
        int aps = 0;
        int fps = 0;
        final int NS_X_SEGUNDO = 1000000000;
        final byte APS_OBJETIVO = 60;
        final double NS_X_ACTUALIZACION = NS_X_SEGUNDO / APS_OBJETIVO;
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        double tiempoTranscurrido;
        double delta = 0;

        while (this.enFuncionamiento) {
            final long inicioBucle = System.nanoTime();
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            delta += tiempoTranscurrido / NS_X_ACTUALIZACION;
            while (delta >= 1) {
	 actualizar();
	 aps++;
	 delta--;
            }

            dibujar();
            fps++;
            if (System.nanoTime() - referenciaContador > NS_X_SEGUNDO) {
	 this.ventana.setTitle("Delemetrion " + "|| FPS: " + fps
	         + " || " + "APS: " + aps);
	 aps = 0;
	 fps = 0;
	 referenciaContador = System.nanoTime();

            }

        }
    }

    private void actualizar() {
        GestorControles.teclado.actualizar();
        this.ge.actualizar();
    }

    private void dibujar() {
        this.sd.dibujar(this.ge);
    }

}
