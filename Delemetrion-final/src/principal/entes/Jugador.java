package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.Control.GestorControles;
import principal.sprites.HojaSprite;

public class Jugador {

    private double posicionX;
    private double posicionY;
    private double x;
    private double y;
    private char direccion;
    private HojaSprite hs;
    private BufferedImage imagenActual;  
    private int animacion;
    private boolean enMovimiento=false;

    public Jugador(final double posicionX, final double posicionY) {
        this.direccion = 'e';       
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        x=posicionX;
        y=posicionY;
        this.hs = new HojaSprite("/imagenes/hojasPersonajes/characters.png",
	 Constantes.LADO_SPRITE, false);
    }

    public void actualizar() {
    	actualizarAnimacion(); 
        desplazaMapa();
        cambiarImagenActual();
    }
    
    public void actualizarAnimacion(){
    	if (this.animacion < 32767) {
            this.animacion++;
        } else {
            this.animacion = 0;
        }
    }
    
    public void desplazaMapa(){
    	y=posicionY;
    	x=posicionX;
    	if (GestorControles.teclado.getArriba()) {
            this.direccion = 'n';          
            this.posicionY -= Constantes.VELOCIDAD;
        }
        if (GestorControles.teclado.getAbajo()) {
            this.direccion = 's';
            this.posicionY += Constantes.VELOCIDAD;
        }
        if (GestorControles.teclado.getIzquierda()) {
            this.direccion = 'w';
            this.posicionX -= Constantes.VELOCIDAD;
        }
        if (GestorControles.teclado.getDerecha()) {
            this.direccion = 'e';
            this.posicionX += Constantes.VELOCIDAD;
        }
    }

     public void cambiarImagenActual() {        
    	 if(posicionX-x!=0 || posicionY-y!=0){
    		 enMovimiento=true;    		 
    		 characterMovimiento(direccion);
    	 }
    	 else{
    		 enMovimiento=false;    		 
    		 characterQuieto(direccion);
    	 }     
    }
     
     public void characterQuieto(char direccion){
    	 switch(direccion){    	 
    	 case 'n':
    		 animacion(13,20,27);
    		 break;
    	 case 's':    		
    		 animacion(10,17,24);
    		 break;
    	 case 'e':
    		 animacion(10,17,24);
    		 break;
    	 case 'w':
    		 animacion(11,18,25);    		 
    		 break;
    	 }
     }

     public void characterMovimiento(char direccion){
    	 switch(direccion){    	 
    	 case 'n':
    		 animacion(1,8,22);
    		 break;
    	 case 's':
    		 animacion(5,12,19);
    		 break;
    	 case 'e':
    		 animacion(7,14,21);
    		 break;
    	 case 'w':
    		 animacion(2,9,16);    		
    		 break;
    	 }
     }
     
     public void animacion(int uno, int dos, int tres) {
         int resto = this.animacion % 60;
         if (resto > 10 && resto <= 30) {
        	 this.imagenActual = this.hs.getSprite(uno).getImagen();
         } else if (resto > 30) {
        	 this.imagenActual = this.hs.getSprite(dos).getImagen();
         } else {
        	 this.imagenActual = this.hs.getSprite(tres).getImagen();
         }
     }
     
     
     
    public void dibujar(final Graphics g) {
        final int centroX = Constantes.ANCHO_PANTALLA / 2
	 - Constantes.LADO_SPRITE / 2;
        final int centroY = Constantes.ALTO_PANTALLA / 2
	 - Constantes.LADO_SPRITE / 2;

        g.setColor(Color.green);
        g.drawImage(this.imagenActual, centroX, centroY, null);
    }

    public void setPosicionX(final double posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(final double posicionY) {
        this.posicionY = posicionY;
    }

    public double getPosicionX() {
        return this.posicionX;
    }

    public double getPosicionY() {
        return this.posicionY;
    }
}
