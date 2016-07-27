package principal.mapas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import principal.Constantes;
import principal.herramientas.CargadorRecursos;
import principal.sprites.HojaSprite;
import principal.sprites.Sprite;

public class Mapa {

    private String[] partes;
    private final int ancho;
    private final int alto;
    private final Sprite[] paleta;
    private final boolean[] colisiones;
    private final int[] sprites;
    private final int MARGEN_X=Constantes.CENTRO_VENTANA_X - Constantes.MEDIO_LADO;
    private final int MARGEN_Y=Constantes.CENTRO_VENTANA_Y - Constantes.MEDIO_LADO;

    public Mapa(final String ruta) {

        String contenido = CargadorRecursos.leerArchivoTexto(ruta);

        this.partes = contenido.split("\\*");
        this.ancho = Integer.parseInt(this.partes[0]);
        this.alto = Integer.parseInt(this.partes[1]);
        String hojasUtilizadas = this.partes[2];
        String[] hojasSeparadas = hojasUtilizadas.split(",");
        String paletaEntera = this.partes[3];
        String[] partesPaleta = paletaEntera.split("#");
        this.paleta = new Sprite[partesPaleta.length];
        String colisionesEnteras = this.partes[4];
        this.colisiones = getColisiones(colisionesEnteras);
        String spritesEnteros = this.partes[5];
        String[] cadenasSprites = spritesEnteros.split(" ");
        this.sprites = getSprites(cadenasSprites);
        asignarSprites(partesPaleta, hojasSeparadas);
    }

    private Sprite[] asignarSprites(final String[] partesPaleta,
            final String[] hojasSeparadas) {
        HojaSprite hoja = new HojaSprite("/imagenes/hojasTextura/"
	 + hojasSeparadas[0] + ".png", 32, false);
        for (int i = 0; i < partesPaleta.length; i++) {
            String spriteTemporal = partesPaleta[i];
            String[] partesSprite = spriteTemporal.split("-");
            int indicePaleta = Integer.parseInt(partesSprite[0]);
            int indiceSpriteHoja = Integer.parseInt(partesSprite[2]);
            this.paleta[indicePaleta] = hoja.getSprite(indiceSpriteHoja);
        }
        return this.paleta;
    }

    private int[] getSprites(final String[] cadenaSprites) {
        ArrayList<Integer> sprites = new ArrayList<Integer>();
        for (int i = 0; i < cadenaSprites.length; i++) {
            if (cadenaSprites[i].length() == 2) {
	 sprites.add(Integer.parseInt(cadenaSprites[i]));
            } else {
	 String uno = "";
	 String dos = "";
	 String error = cadenaSprites[i];
	 uno += error.charAt(0);
	 uno += error.charAt(1);
	 dos += error.charAt(2);
	 dos += error.charAt(3);
	 sprites.add(Integer.parseInt(uno));
	 sprites.add(Integer.parseInt(dos));
            }
        }
        int[] vectorSprites = new int[sprites.size()];
        for (int i = 0; i < sprites.size(); i++) {
            vectorSprites[i] = sprites.get(i);
        }
        return vectorSprites;
    }

    private boolean[] getColisiones(final String cadenaColisiones) {
        boolean[] colisiones = new boolean[cadenaColisiones.length()];
        for (int i = 0; i < cadenaColisiones.length(); i++) {
            if (cadenaColisiones.charAt(i) == '0') {
	 colisiones[i] = false;
            } else {
	 colisiones[i] = true;
            }
        }
        return colisiones;
    }

    public void escribirArray(final String[] hojasSeparadas) {
        for (int i = 0; i < this.partes.length; i++) {
            System.out.println(this.partes[i]);
        }
    }

    public static BufferedImage cargaDesierto(final HojaSprite hs) {
        BufferedImage Desierto = hs.getSprite(0, 0).getImagen();
        return Desierto;
    }

    public static BufferedImage cargaTierra(final HojaSprite hs) {
        BufferedImage Desierto = hs.getSprite(1, 0).getImagen();
        return Desierto;
    }

    public static BufferedImage cargaBosque(final HojaSprite hs) {
        BufferedImage Desierto = hs.getSprite(2, 0).getImagen();
        return Desierto;
    }

    public static BufferedImage cargaAguaProfunda(final HojaSprite hs) {
        BufferedImage Desierto = hs.getSprite(3, 0).getImagen();
        return Desierto;
    }

    public static BufferedImage cargaAgua(final HojaSprite hs) {
        BufferedImage Desierto = hs.getSprite(4, 0).getImagen();
        return Desierto;
    }

    public static void CargarMapa(final Graphics g, final HojaSprite hs) {
        int anchoSprite = Constantes.LADO_SPRITE;
        int altoSprite = anchoSprite;
    }

    public void dibujar(final Graphics g, final int posicionX,
            final int posicionY) {
        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {	 
            	BufferedImage imagen = this.paleta[this.sprites[x + y * this.ancho]].getImagen();
            	int puntoX= x * Constantes.LADO_SPRITE - posicionX + MARGEN_X;            	
            	int puntoY= y * Constantes.LADO_SPRITE - posicionY + MARGEN_Y;
            	g.drawImage(imagen, puntoX, puntoY, null);
            }
        }
    }
}