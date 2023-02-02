/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.watch;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class Watch8 extends JPanel {

    private final Color backgroundColor = new Color(0x404c0); // color de fondo
    private final Color hourColor = Color.BLACK; // color manecilla de la hora
    private final Color minuteColor = Color.BLACK; // color manecilla minuto
    private final Color secondColor = new Color(0xa00000); // color manecilla segundo
    private final Color faceColor = new Color(0xc0c0c0); // Color de la caratula
    private final Color labelColor = Color.BLACK; // color del marco del reloj
    private final Color dayColor = new Color(0x40, 0x40, 0xff);
    private final Color nightColor = new Color(0x10, 0x10, 0x70);
    private final Color gradientA = new Color(224, 224, 224, 255);
    private final Color gradientB = new Color(32, 32, 32, 144);
    private final Color shadowColor = new Color(0x0, 0x0, 0x0, 0x30);
    private final String title = "Java Watch";
    
    // pre calculamos los datos de seno, coseno y lo guardamos en una tabla valores de 0 - 59
    private float fsintable[] = new float[60 + 1];
    private float fcostable[] = new float[60 + 1];
    

    private Font fSmall;
    private Font fMedium;
    private Font fLarge;
    private Font fTitleSmall;
    private Font fTitleMedium;
    private Font fTitleLarge;

    private int hour;
    private int minute;
    private int second;

    private int hourStroke = 12;
    private int minuteStroke = 5;
    private int secondStroke = 3;
    private javax.swing.Timer timer;
    
    BufferedImage backgroundImage;
    BufferedImage dayNightImage;

    // implementando el timer
    public Watch8() {
        super();
        initSinCosTable();
        // actualizamos la hora
        updateTime();
        // set up el timer cada segundo
        timer = new javax.swing.Timer(1000, (e) -> {
            // actulizamos la hora y redibujamos el reloj
            updateTime();
            repaint();
        });
        timer.start();
    }

    private void stopTimer() {
        timer.stop();
    }
    
    // precalculamos senos y cosenos
    private void initSinCosTable(){
        for(int n = 0; n < fsintable.length; n++){
            double angle = (270 + 360.0/60 * (double)n) % 360;
            // calculams 60 senos
            double d = Math.sin(Math.toRadians(angle));
            fsintable[n] = (float)d;
            
            // calculamos 60 cosenos
            d = Math.cos(Math.toRadians(angle));
            fcostable[n] = (float)d;
        }
    }
    
    // retornar un valor en un rango de 0-59
    private float sin(int angle60){
        return fsintable[angle60];
    }
    
    private float cos(int angle60){
        return fcostable[angle60];
    }

    private Font getSmallFont(Graphics2D g2d) {
        if (fSmall != null) {
            return fSmall;
        }
        fSmall = g2d.getFont().deriveFont(24f);
        return fSmall;
    }
    
    private Font getSmallTitleFont(Graphics2D g2d) {
        if (fTitleSmall != null) {
            return fTitleSmall;
        }
        fTitleSmall = g2d.getFont().deriveFont(12f);
        return fTitleSmall;
    }

    private Font getMediumFont(Graphics2D g2d) {
        if (fMedium != null) {
            return fMedium;
        }
        fMedium = g2d.getFont().deriveFont(36f);
        return fMedium;
    }
    
    private Font getMediumTitleFont(Graphics2D g2d) {
        if (fTitleMedium != null) {
            return fTitleMedium;
        }
        fTitleMedium = g2d.getFont().deriveFont(18f);
        return fTitleMedium;
    }

    private Font getLargeFont(Graphics2D g2d) {
        if (fLarge != null) {
            return fLarge;
        }
        fLarge = g2d.getFont().deriveFont(72f);
        return fLarge;
    }
    
    private Font getLargeTitleFont(Graphics2D g2d) {
        if (fTitleLarge != null) {
            return fTitleLarge;
        }
        fTitleLarge = g2d.getFont().deriveFont(36f);
        return fTitleLarge;
    }

    // metodo para saber/obtener la hora
    private void updateTime() {
        // obtenemos instancia de Calendar
        Calendar cal = Calendar.getInstance();
        // retornamos hora minto segundo
        hour = cal.get(Calendar.HOUR);
        minute = cal.get(Calendar.MINUTE);
        second = cal.get(Calendar.SECOND);
    }
    
    /**
     * dibujamos la caratula del reloj
     * @param bounds -> Rectangulo del panel
     * @param frame -> Rectangulo que ocupa el reloj
     */
    private void updateBackgroundImage(Rectangle bounds, Rectangle frame){
        backgroundImage = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = backgroundImage.createGraphics();
        // dibujo caratula
        g2d.setColor(backgroundColor); // color de fondo
        g2d.fillRect(0, 0, bounds.width, bounds.height); // pintamos el fondo
        
        g2d.setColor(faceColor); // color de linea
        g2d.fillOval(frame.x, frame.y, frame.width, frame.height); // dibujamos el circulo en le medio
        
        Stroke simpleStroke = g2d.getStroke();
        Stroke boldStroke = new BasicStroke(2);
        
        // obtenemos el centro del circulo (centramos el frame)
        int centerX = frame.x + frame.width / 2;
        int centerY = frame.y + frame.height / 2;
        float radius = frame.width / 2 - 10; // radio del circulo, con un marge de 5
        double innerRadius = radius * 0.9; // definimos un radio interno que es igual al 90% del radio

        // gradiente
        Paint savePaint = g2d.getPaint();
        RadialGradientPaint rp = new RadialGradientPaint(
                // definimos un punto coordenada x que se encuentra en e centro del relos
                (float) (centerX - Math.cos(Math.toRadians(45)) * radius),
                // definimos un punto coordenada y que se encuentr en el centro del reloj
                (float) (centerY - Math.sin(Math.toRadians(45)) * radius),
                // 
                radius * 3.5f,
                new float[]{0.0f, 0.5f},
                new Color[]{gradientA, gradientB});
        // end gradiente

        g2d.setPaint(rp);
        // pintamos utilizando el gradiente definido
        g2d.fillOval(frame.x + 4, frame.y + 4, frame.width - 8, frame.height - 8);
        g2d.setPaint(savePaint);
        // color para el marco del reloj
        g2d.setColor(labelColor);
        // dibujamos un marco grueso para el reloj
        g2d.setStroke(new BasicStroke(10));
        g2d.drawOval(frame.x + 4, frame.y + 4, frame.width - 8, frame.height - 8);
        g2d.setStroke(simpleStroke);
        
        // establecemos el tamanio del font, de acuerdo al tamanio en pixeles del frame
        Font f;
        Font fTitle;
        if (frame.width < 400) {
            f = getSmallFont(g2d);
            fTitle = getSmallTitleFont(g2d);
        } else if (frame.width < 800) {
            f = getMediumFont(g2d);
            fTitle = getMediumTitleFont(g2d);
        } else {
            f = getLargeFont(g2d);
            fTitle = getLargeTitleFont(g2d);
        }
        g2d.setFont(f);
        FontMetrics fm = g2d.getFontMetrics();
        
        // coordenadas para hora, minuto, segundo
        int coordXHora, coordXMin, coordXSeg;
        int coordYHora, coordYMin, coordYSec;
        // dibujamos las manecillas - angulos de 0-59
        for (int n = 0; n < 60; n++) {
            // angulo que tiene cada segundo 360/60
            //angle = (270 + n * (360 / 60)) % 360.0; // mod 360 nos permite un rango entre 0 y 360
            float coseno = cos(n);// calculamos el coseno de angulo pero primero lo convertimos a radianes
            float seno = sin(n);// calculamos el seno del a angulo
            coordXHora = (int) (cos(n) * radius);
            coordYHora = (int) (sin(n) * radius);

            // si n es divisible por 5 significa que estoy en la marca de una division de hora
            if ((n % 5) == 0) {
                // dibujamos la lineas de hora
                // cuando es 1 hora, el radio es 0.9 del radius
                innerRadius = radius * 0.9;

                // dibujamos la hora
                int h = n / 5;
                // validamos si estamos en as 12
                if (h == 0) {
                    h = 12;
                }
                // calculamos los limites del numero a dibujar
                Rectangle2D r = fm.getStringBounds(String.valueOf(h), g2d);
                // trasladamos hacia el angulo que queremos dibujar
                int x = (int) (-r.getWidth() / 2 + radius * coseno * 0.8);
                int y = (int) (r.getHeight() / 2 - fm.getDescent() + radius * seno * 0.8);
//                    g2d.drawRect(centerX + x, centerY - y - fm.getDescent(), (int) r.getWidth(), (int) r.getHeight());
                g2d.drawString(String.valueOf(h), centerX + x, centerY + y);
                // un punto en el centro del reloj
//                g2d.fillOval(centerX - 2, centerY - 2, 4, 4);
                // fin dibujamos la hora

                g2d.setStroke(boldStroke);
            } else {
                // dibujamos la lineas de minutos
                // cuando es 1 minuto, el radio es 0.95 del radius
                innerRadius = radius * 0.95;
                g2d.setStroke(simpleStroke);
            }

            // dibujamos la linea
            g2d.drawLine((int) (centerX + coseno * innerRadius), (int) (centerY + seno * innerRadius),
                    (int) (centerX + coordXHora), (int) (centerY + coordYHora));
        }
        // dibujamos la marca del reloj
        g2d.setFont(fTitle);
        Rectangle2D r = g2d.getFontMetrics().getStringBounds(title, g2d);
        // centrado a lo horizontal pero en lo vertical a una distancia de 30% del radio
        g2d.drawString(title, (int)(centerX - r.getWidth()/2), (int)(centerY + radius * 0.3));
        
        g2d.dispose();
        // fin dibujo caratula
    }

    private void createDayNightImage(int width){
        dayNightImage = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = dayNightImage.createGraphics();
        // dibujamos el firmamento con un gradiente en lineas horizontales
        Paint savePaint = g2d.getPaint();
        /**
         * TOP_ALIGNMENT X no importa la coordenada por ello 0
         * TOP_ALIGMENT Y la ubicamos a la mitad de la pantalla y le restamos una 16 avo del tamano
         */
        GradientPaint gp = new GradientPaint(0, width/2 - width/16, dayColor, 0, width/2 + width/16, nightColor);
        g2d.setPaint(gp); // colocamos el nuevo gradiente
        g2d.fillRect(0, 0, width, width);
        
        g2d.setPaint(savePaint);
        g2d.dispose();
    }
    
    /**
     * 
     * @param g2d nos permite dibujar
     * @param x
     * @param y
     * @param radius del circulo exterior
     * @param innerRadius del circulo interno
     * @param nSpikes nro de puntas de la estrella
     * @param rotation angulo de rotacion
     */
    private void fillStars(Graphics2D g2d, int x, int y, int radius, int innerRadius, int nSpikes, int rotation){
        // arraeglo de puntos en x, y. Si la estrella tiene 5 puntos en el circulo interno y externo
        // necesitamos un total de 10 puntos, que es igual a nSpikes * 2
        int xPoints[] = new int[nSpikes *2];
        int yPoints[] = new int[xPoints.length];
        
        // recorremos los puntos de 2 en 2
        for(int n = 0; n < xPoints.length; n+=2){
            // calculamos el angulo del primero punto que puede estar en la posicion de la hora 12
            double angle1 = (270 + rotation + (360.0/nSpikes)*(n/2)) % 360;
            double angle2 = (270 + rotation + (360.0/nSpikes)* (n/2) + (360.0 / xPoints.length)) % 360;
            
            // angulo 1 circulo externo
            xPoints[n] = x + (int)(Math.cos(Math.toRadians(angle1)) * radius);
            // angulo 2 circulo interno
            xPoints[n+1] = x + (int)(Math.cos(Math.toRadians(angle2)) * innerRadius);
            
            yPoints[n] = y + (int)(Math.sin(Math.toRadians(angle1)) * radius);
            yPoints[n+1] = y + (int)(Math.sin(Math.toRadians(angle2)) * innerRadius);
        }
        
        g2d.fillPolygon(xPoints, yPoints, xPoints.length);
    }
    
    // Tamanio de letra (chico, mediano , grande)
    @Override
    protected void paintComponent(Graphics g) {
        // forma del reloj: es un circulo responsivo
        Rectangle bounds = getBounds(); // obtenemos Jpanel size
        Graphics2D g2d = (Graphics2D) g;
        
        /**
         * Conforme se redimensiona la pantalla se forma un ovalo pero el reloj
         * siempre debe ser un circulo para determinar el tamanio el ancho debe
         * ser igual al alto
         */
        int size = Math.min(bounds.width, bounds.height) - 1;// obtenemos el minimo valor entre bounds.width y bounds.height
        
        /**
         * bounds.width/2 - size/2 -> obtenemos la coordenada x centrada
         * bounds.height/2 - size/2 -> obtenemos la coordenada y centrada size
         * -> with size -> height frame -> frame que describe al circulo
         */
        Rectangle frame = new Rectangle(bounds.width / 2 - size / 2, bounds.height / 2 - size / 2, size, size);
        
        // probamos dibujar la estrella
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, bounds.width, bounds.height);
/*        g2d.setColor(Color.WHITE);
        fillStars(g2d, bounds.width/2, bounds.height/2, frame.width/2, frame.height/4, 5, 30);
 */       // fin de la prueba de dibujo de estrella
        
        // probamos dibujar el sol
        // esfera del sol
        float sunRadius = getWidth()/6;
        g2d.setColor(Color.ORANGE);
        fillStars(g2d, getWidth()/2, getHeight()/2, (int)sunRadius*2, (int)(sunRadius*1.2f), 12, 0);
        g2d.setColor(Color.YELLOW);
        g2d.fillOval((int)(getWidth()/2 - sunRadius), (int)(getHeight()/2 - sunRadius), (int)sunRadius*2, (int)sunRadius*2);
        if(true){
            return;
        } 
        
        g2d.setColor(Color.BLACK);
        /**
         * Dibujo de lineas para horas, minutos y segundos
         */
        
        // Letra de tamanio 36
//        g2d.setFont(g2d.getFont().deriveFont(36f));

        // centramos el frame
        frame.x = bounds.width/2 - frame.width/2;
        frame.y = bounds.height/2 - frame.height/2;
        
        // validamos que backgroundImage sea nulo o cambio el tamano del panel (ventana)
        if((backgroundImage == null) || (backgroundImage.getWidth() != bounds.width) || (backgroundImage.getHeight() != bounds.height)){
            updateBackgroundImage(bounds, frame);
        }
        // dibujamos la imagen del backgroundImage
        g2d.drawImage(backgroundImage, 0, 0, null);
        
        // obtenemos el centro del circulo (centramos el frame)
        int centerX = frame.x + frame.width / 2;
        int centerY = frame.y + frame.height / 2;
        float radius = frame.width / 2 - 10; // radio del circulo, con un marge de 5
        
        // lineas para las manecillas
        double hourRadius = radius * 0.5;
        double minRadius = radius * 0.7;
        double secRadius = radius * 0.9;
        
        // radius -> radio del reloj
        float dayNightRadius = radius * 0.65f;
        createDayNightImage((int)dayNightRadius * 2);
        g2d.drawImage(dayNightImage, centerX-dayNightImage.getWidth()/2, centerY-dayNightImage.getHeight()/2, null);
        
        // coordenadas para hora, minuto, segundo
        int coordXHora, coordXMin, coordXSeg;
        int coordYHora, coordYMin, coordYSec;
        
        coordXHora = (int)(Math.cos(Math.toRadians((270+(double)hour*(360/12)+(double)minute*360/(12*60)))%360) * hourRadius);
        coordYHora = (int)(Math.sin(Math.toRadians((270+(double)hour*(360/12)+(double)minute*360/(12*60)))%360) * hourRadius);
        // aceleramos los calculos
        coordXMin = (int)(cos(minute) * minRadius);
        coordYMin = (int)(sin(minute) * minRadius);
        
        coordXSeg = (int) (cos(second) * secRadius);
        coordYSec = (int) (sin(second) * secRadius);
        
        // sobras para efecto 3D de las manecillas segundos
        g2d.setStroke(new BasicStroke(secondStroke));
        g2d.setColor(shadowColor);
        // dubujamos la sombra para el segundero y le agregamos 3 pixels
        g2d.drawLine((int)(centerX - coordXSeg*0.2)+3, (int)(centerY - coordYSec*0.2)+3, centerX + coordXSeg+3, centerY + coordYSec+3);
        
        // sombras para efecto 3D de las manecillas minutos
        g2d.setStroke(new BasicStroke(minuteStroke));
        g2d.drawLine((int)(centerX - coordXMin*0.15)+2, (int)(centerY - coordYMin*0.2)+2, centerX + coordXMin+2, centerY + coordYMin+2);
        
        // sombras para efecto 3D de las manecillas horas
        g2d.setStroke(new BasicStroke(hourStroke));
        g2d.drawLine(centerX+2, centerY+2, centerX + coordXHora+2, centerY + coordYHora+2);
        
        // manecilla de horas
        g2d.setStroke(new BasicStroke(hourStroke));
        g2d.setColor(hourColor);
//        angle = (270 + hour * (360.0 / 12) + minute * (360.0 / 12 / 60)) % 360.0;
        // circulo en el centro de las manecillas
        g2d.fillOval(centerX-10, centerY-10, 20, 20);
        g2d.drawLine(centerX, centerY, centerX + coordXHora, centerY + coordYHora);
        
        // manecilla de minutos
//        angle = (270 + minute * (360 / 60)) % 360.0;
        g2d.setColor(minuteColor);
        g2d.setStroke(new BasicStroke(minuteStroke));
        g2d.drawLine((int)(centerX - coordXMin*0.15), (int)(centerY - coordYMin*0.2), centerX + coordXMin, centerY + coordYMin);
        
        // manecilla de segundos
//        angle = (270 + second * (360 / 60)) % 360.0;
        g2d.setColor(secondColor);
        g2d.setStroke(new BasicStroke(secondStroke));
        // dibujamos el segundero antes del centro
        g2d.drawLine((int)(centerX - coordXSeg*0.2), (int)(centerY - coordYSec*0.2), centerX + coordXSeg, centerY + coordYSec);
        
        // pequenio circulo (remache) en el medio de las manecillas
        g2d.setColor(backgroundColor);
        g2d.fillOval(centerX-3, centerY-3, 6, 6);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Watch8 mainPanel = new Watch8();
            JFrame frame = new JFrame();
            frame.setTitle("Watch 8");
            frame.setMinimumSize(new Dimension(400, 400));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // cuando se cierra la ventana, terminamos el timer
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    mainPanel.stopTimer();
                }
            });

            frame.setContentPane(mainPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
