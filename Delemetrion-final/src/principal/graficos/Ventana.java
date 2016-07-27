package principal.graficos;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import principal.herramientas.CargadorRecursos;

public class Ventana extends JFrame {
    private static final long serialVersionUID = 1L;
    private String titulo;
    private final ImageIcon icono;

    public Ventana(final String titulo, final SuperficieDibujo sd) {
        this.titulo = titulo;
        BufferedImage imagen = CargadorRecursos
	 .cargarImagenCompatibleTranslucida("/Imagenes/icono/icono.jpg");
        this.icono = new ImageIcon(imagen);
        configurarVentana(sd);
    }

    private void configurarVentana(final SuperficieDibujo sd) {
        setTitle(this.titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(this.icono.getImage());
        setResizable(false);
        setLayout(new BorderLayout());
        add(sd, BorderLayout.CENTER);
        // setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
