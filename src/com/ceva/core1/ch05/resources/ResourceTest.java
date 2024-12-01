package com.ceva.core1.ch05.resources;

/*
 * Trabajando con Resources.
 * Para compilar: Nos ubicamos en la ru
 * javac resources/ResourceTest.java
 * jar cvfe ResourceTest.jar resources/ResourceTest resources/*.class resources/*.gif resources/data/*.txt corejava/*.txt
 *
 */
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ResourceTest {
    public static void main(String[] args) throws IOException {
        // obtenemos el objeto Class de la clase ResourceTest.java
        Class<?> cl = ResourceTest.class;
        // URL decribe la ubicacion del recurso
        URL url = cl.getResource("about.gif");
        // ImageIcon acepta una ubicacion url
        var icon = new ImageIcon(url);

        InputStream stream = cl.getResourceAsStream("data/about.txt");
        var about = new String(stream.readAllBytes());

        InputStream stream2 = cl.getResourceAsStream("../corejava/title.txt");
        var title = new String(stream2.readAllBytes()).strip();

        JOptionPane.showMessageDialog(null, about, title, JOptionPane.INFORMATION_MESSAGE, icon);
    }


}
