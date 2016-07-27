package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.Control.GestorControles;
import principal.mapas.Mapa;
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
    private final int ANCHO_JUGADOR=16;
    private final int ALTO_JUGADOR=16;
    private final Rectangle LIMITE_ARRIBA= new Rectangle(Constantes.CENTRO_VENTANA_X - 10 ,Constantes.CENTRO_VENTANA_Y    +  6,21,1);
    private final Rectangle LIMITE_ABAJO= new Rectangle(Constantes.CENTRO_VENTANA_X - 10 ,Constantes.CENTRO_VENTANA_Y     + 13,21,1);
    private final Rectangle LIMITE_DERECHA= new Rectangle(Constantes.CENTRO_VENTANA_X + 10 ,Constantes.CENTRO_VENTANA_Y   +  8,1,6);
    private final Rectangle LIMITE_IZQUIERDA= new Rectangle(Constantes.CENTRO_VENTANA_X - 10 ,Constantes.CENTRO_VENTANA_Y +  8,1,6);
    private Mapa mapa;
    
    public Jugador(final double posicionX, final double posicionY, Mapa mapa) {
        this.direccion = 'e';       
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        x=posicionX;
        y=posicionY;
        this.hs = new HojaSprite("/imagenes/hojasPersonajes/characters.png",Constantes.LADO_SPRITE, false);
        this.mapa=mapa;
        
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
    	if(!fueraMapa((int)Constantes.VELOCIDAD, (int)Constantes.VELOCIDAD)){
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
     
     public boolean fueraMapa(final int velocidadX, final int velocidadY){
    	 int posicionFuturaX=(int)posicionX + velocidadX;
    	 int posicionFuturaY=(int)posicionY + velocidadY;
    	 final Rectangle bordesMapa=mapa.getBordes(posicionFuturaX, posicionFuturaY, ANCHO_JUGADOR, ALTO_JUGADOR);
    	 final boolean fuera;
    	 if(LIMITE_ARRIBA.intersects(bordesMapa) || LIMITE_ABAJO.intersects(bordesMapa) || LIMITE_IZQUIERDA.intersects(bordesMapa) || LIMITE_DERECHA.intersects(bordesMapa)){
    		 fuera=false;
    	 }
    	 else{
    		 fuera=true;
    	 }
    	 return fuera;    	 
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
        final int centroX = Constantes.CENTRO_VENTANA_X - Constantes.LADO_SPRITE / 2;
        final int centroY = Constantes.CENTRO_VENTANA_Y - Constantes.LADO_SPRITE / 2;
                
        g.drawImage(this.imagenActual, centroX, centroY, null);
        g.setColor(Color.red);
        g.drawRect(LIMITE_ARRIBA.x, LIMITE_ARRIBA.y, LIMITE_ARRIBA.width, LIMITE_ARRIBA.height);
        g.drawRect(LIMITE_ABAJO.x, LIMITE_ABAJO.y, LIMITE_ABAJO.width, LIMITE_ABAJO.height);
        g.drawRect(LIMITE_DERECHA.x, LIMITE_DERECHA.y, LIMITE_DERECHA.width, LIMITE_DERECHA.height);
        g.drawRect(LIMITE_IZQUIERDA.x, LIMITE_IZQUIERDA.y, LIMITE_IZQUIERDA.width, LIMITE_IZQUIERDA.height);
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
