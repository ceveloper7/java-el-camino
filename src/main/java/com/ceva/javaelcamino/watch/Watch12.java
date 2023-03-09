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
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Watch10: Reloj con carátula dia-noche giratoria.
 *
 * @author rcosio
 * @date 8/oct/2019 11:22 pm-
 */
public class Watch12 extends JPanel {

    private final Color backgroundColor = new Color(0x4040c0);
    private final Color backgroundDateColor = new Color(0X101020);
    private final Color hourColor = Color.BLACK;
    private final Color minuteColor = Color.BLACK;
    private final Color secColor = new Color(0xa00000);
    private final Color labelColor = Color.BLACK;
    private final Color faceColor = new Color(0xc0c0c0);
    private final Color shadowColor = new Color(0x0, 0x0, 0x0, 0x30);
    private final Color dayColor = new Color(0x40, 0x40, 0xff);
    private final Color nightColor = new Color(0x10, 0x10, 0x70);
    private final Color labelDateColor = new Color(0Xbdbd00);
    private final Color gradientA = new Color(224, 224, 224, 128);
    private final Color gradientB = new Color(32, 32, 32, 70);
    private final String title = "Java Watch";

    private float fsintable[] = new float[60 + 1];
    private float fcostable[] = new float[60 + 1];

    private Font fTitleSmall;
    private Font fTitleMedium;
    private Font fTitleLarge;
    private Font fSmall;
    private Font fMedium;
    private Font fLarge;
    private Font[] calendarFont;
    // indice que contralo el font actual
    private int curFontIndex;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private int ampm;

    // flag que controla si nos encontramos en modo animacion de la fecha
    boolean updatingCalendar = false;

    private int month_next;
    private int day_next;
    private int lastMinute = -1;
    private final int hourStroke = 12;
    private final int minuteStroke = 5;
    private final int secondStroke = 3;
    private javax.swing.Timer timer;

    BufferedImage backgroundImage;
    BufferedImage dayNightImage;
    Area dnClip;
    // Tamanio maximo del dia
    private Dimension calDateSize;
    // Tamanio maximo que ocupa el nombre del mes
    private Dimension calMonthSize;

    private final String[] monthName = {"ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEPT", "NOV", "DIC"};

    public Watch12() {
        super();
        initSinCosTable();

        updateTime();
        timer = new javax.swing.Timer(1000, e -> {
            updateTime();
            repaint();
        });
        timer.start();
        initFonts();
    }

    private void stopTimer() {
        timer.stop();
    }

    private void initSinCosTable() {
        for (int n = 0; n < fsintable.length; n++) {
            double angle = (270 + 360.0 / 60 * (double) n) % 360;
            double d = Math.sin(Math.toRadians(angle));
            fsintable[n] = (float) d;
            d = Math.cos(Math.toRadians(angle));
            fcostable[n] = (float) d;
        }
    }

    private void initFonts() {
        BufferedImage bi = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        calendarFont = new Font[3];
        calendarFont[0] = g2d.getFont().deriveFont(Font.BOLD, 28f);
        calendarFont[1] = g2d.getFont().deriveFont(Font.BOLD, 42f);
        calendarFont[2] = g2d.getFont().deriveFont(Font.BOLD, 84f);
        g2d.dispose();
    }

    private float sin(int angle60) {
        return fsintable[angle60];
    }

    private float cos(int angle60) {
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
        fTitleLarge = g2d.getFont().deriveFont(24f);
        return fTitleLarge;
    }

    // variables para simular el cambio de dia
    private static final boolean DEBUGFLAG = false;
    private static boolean initFlag = true;

    private void updateTime() {
        Calendar cal = Calendar.getInstance();
        //cal.set(Calendar.DATE, 31);
        if (!DEBUGFLAG || initFlag) {
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DATE);
            hour = cal.get(Calendar.HOUR);
            minute = cal.get(Calendar.MINUTE);
            second = cal.get(Calendar.SECOND);
            ampm = cal.get(Calendar.AM_PM);
        }
        
        if(DEBUGFLAG){
            if(initFlag){
                hour = 11;
                minute = 58;
                second = 50;
                ampm = 1;
                initFlag = false;
                
                day = 31;
                
            }else{
                second = (second + 1) % 60;
                if(second == 0){
                    minute = (minute + 1) % 60;
                    if(minute == 0){
                        hour = (hour + 1) % 12;
                        if(hour == 0){
                            ampm = 0;
                        }
                    }
                }
            }
        }
        
        // determinamos el inicio de la animacion de cambio de dia
        // validamos si son 11:59  de la noche
        if ((ampm == 1) && (hour == 11) && (minute == 59)) {
            // flag que controla si nos encontramos en modo animacion de la fecha
            updatingCalendar = true;
            // sumamos 1 dia a la fecha y hora actual
            cal.add(Calendar.DATE, 1);
            day_next = cal.get(Calendar.DATE);
            month_next = cal.get(Calendar.MONTH);
        } else {
            updatingCalendar = false;
        }
    }

    // determina el tamanio del calendarios
    private void updateCalendarMetrics(Graphics2D g2d) {
        calDateSize = new Dimension();
        calMonthSize = new Dimension();

        Font saveFont = g2d.getFont();
        g2d.setFont(calendarFont[curFontIndex]);
        FontMetrics fm = g2d.getFontMetrics();
        // heght de la letra
        calDateSize.height = fm.getHeight();
        calMonthSize.height = fm.getHeight();

        // calculamos la longitud del dia mas largo
        for (int n = 1; n <= 31; n++) {
            String s = (n < 10) ? "0" + n : String.valueOf(n);
            int width = fm.stringWidth(s);
            if (width > calDateSize.width) {
                calDateSize.width = width;
            }
        }
        for (int n = 0; n < monthName.length; n++) {
            String s = monthName[n];
            int width = fm.stringWidth(s);
            if (width > calMonthSize.width) {
                calMonthSize.width = width;
            }
        }

        g2d.setFont(saveFont);
    }

    private void drawCalendar(Graphics2D g2d, Rectangle frame) {
        // determinamos el tamanio de calendario
        updateCalendarMetrics(g2d);

        // calculamos el rectangulo que ocupara el calendario
        Rectangle calendarRect = new Rectangle(0, 0, calDateSize.width + calMonthSize.width + 8 * 3, calDateSize.height);
        // centramos el calendar
        calendarRect.x = frame.x + (frame.width - calendarRect.width) / 2;
        calendarRect.y = frame.y + (frame.height / 2 + (int) ((frame.height / 2) * 0.3f));

        // rectangulo con esquinas redondeadas
        RoundRectangle2D roundRect = new RoundRectangle2D.Float(
                calendarRect.x, calendarRect.y, calendarRect.width, calendarRect.height,
                calendarRect.height / 2, calendarRect.height / 2);

        g2d.setColor(backgroundDateColor);
        g2d.fill(roundRect);

        // redefinimos el rectangulo basado en el original pero mas pequeno
        roundRect.setRoundRect(
                // x
                calendarRect.x + 2,
                // y
                calendarRect.y + 2,
                // width
                calendarRect.width - 4,
                // height
                calendarRect.height - 4,
                // arcWidth
                (calendarRect.height - 4) / 2,
                // arcHeight
                (calendarRect.height - 4) / 2);

        // Area de Clip
        Shape saveClip = g2d.getClip();
        g2d.setClip(new Area(roundRect));

        // dibujamos el dia del calendario
        g2d.setColor(labelDateColor); // color
        g2d.setFont(calendarFont[curFontIndex]); // tipo de letra
        // obtenemos el fontmetrics
        FontMetrics fm = g2d.getFontMetrics();
        int x = calendarRect.x + 8; // + 8pixeles
        int y = calendarRect.y + fm.getAscent();

        // validamos si estamos actualizando el calendario
        if (updatingCalendar) {
            // subuimos el texto 1 60avo del alto del calendario * second
            // dependiendo del numero del segundo sera la posicion
            // en el segundo 59 subimos el mensaje casi hasta arriba
            y -= second * ((double) calendarRect.height / 60);
        }

        g2d.drawString(day < 10 ? "0" + day : String.valueOf(day), x, y);
        // dibujamos el siguiente dia que salga desde la parte inferior
        if (updatingCalendar) {
            g2d.drawString(day_next < 10 ? "0" + day_next : String.valueOf(day_next), x, y + calendarRect.height);
        }

        // dibujamos el mes del calendario
        x = calendarRect.x + 8 + calDateSize.width + 8;
        y = calendarRect.y + fm.getAscent();
        if (updatingCalendar && (month != month_next)) {
            y -= second * ((double) calendarRect.height / 60);
        }
        g2d.drawString(monthName[month], x, y);
        if (updatingCalendar && (month != month_next)) {
            g2d.drawString(monthName[month_next], x, y + calendarRect.height);
        }

        g2d.setClip(saveClip);
    }

    private void updateBackgroundImage(Rectangle bounds, Rectangle frame) {
        backgroundImage = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) backgroundImage.getGraphics();
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, bounds.width, bounds.height);

        int centerX = frame.x + frame.width / 2;
        int centerY = frame.y + frame.height / 2;
        float radius = frame.width / 2f - 10f;

        // Color de caratula
        g2d.setColor(faceColor);
        g2d.fillOval(frame.x, frame.y, frame.width, frame.height);

        // Sombra en caratula
        Paint savePaint = g2d.getPaint();
        RadialGradientPaint rp = new RadialGradientPaint(
                (float) (centerX - Math.cos(Math.toRadians(45)) * radius),
                (float) (centerY - Math.sin(Math.toRadians(45)) * radius), (float) radius * 3.5f,
                new float[]{0.0f, 0.5f},
                new Color[]{new Color(224, 224, 224, 255), new Color(32, 32, 32, 144)});
        g2d.setPaint(rp);
        g2d.fillOval(frame.x + 4, frame.y + 4, frame.width - 8, frame.height - 8);
        g2d.setPaint(savePaint);

        // Marco del reloj
        g2d.setColor(labelColor);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawOval(frame.x + 4, frame.y + 4, frame.width - 8, frame.height - 8);

        Font f;
        Font fTitle;
        if (frame.width < 450) {
            curFontIndex = 0;
            f = getSmallFont(g2d);
            fTitle = getSmallTitleFont(g2d);
        } else if (frame.width < 800) {
            curFontIndex = 1;
            f = getMediumFont(g2d);
            fTitle = getMediumTitleFont(g2d);
        } else {
            curFontIndex = 2;
            f = getLargeFont(g2d);
            fTitle = getLargeTitleFont(g2d);
        }
        g2d.setFont(f);
        FontMetrics fm = g2d.getFontMetrics();

        Stroke simpleStroke = new BasicStroke(1);
        Stroke boldStroke = new BasicStroke(2);

        int x0;
        int y0;
        float innerRadius;
        for (int n = 0; n < 60; n++) {
            float cos = cos(n);
            float sin = sin(n);
            x0 = (int) (cos * radius);
            y0 = (int) (sin * radius);
            if ((n % 5) == 0) {
                innerRadius = radius * 0.9f;
                int h = n / 5;
                if (h == 0) {
                    h = 12;
                }
                Rectangle2D r = fm.getStringBounds(String.valueOf(h), g2d);
                int x_ = (int) (-(r.getWidth() / 2) + radius * cos * 0.8);
                int y_ = (int) (r.getHeight() / 2 - fm.getDescent() + radius * sin * 0.8);
                g2d.drawString(String.valueOf(h), centerX + x_, centerY + y_);
                g2d.setStroke(boldStroke);
            } else {
                innerRadius = radius * 0.95f;
                g2d.setStroke(simpleStroke);
            }
            g2d.drawLine(centerX + (int) (cos * innerRadius), centerY + (int) (sin * innerRadius), centerX + x0, centerY + y0);
        }

        g2d.setFont(fTitle);
        Rectangle2D r = g2d.getFontMetrics().getStringBounds(title, g2d);
        // dibujamos el titulo: Java Watch
        g2d.drawString(title, (int) (centerX - r.getWidth() / 2), (int) (centerY + radius * 0.2));

        // dibujamos el calendario dentro de la caratula
        drawCalendar(g2d, frame);

        g2d.dispose();
    }

    private void createDayNightImage(Rectangle frame) {
        float dnRadius = frame.width / 2f * 0.65f;
        int width = (int) (dnRadius * 2);
        dayNightImage = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = dayNightImage.createGraphics();

        int centerX = width / 2;
        int centerY = width / 2;

        // Firmamento
        Paint savePaint = g2d.getPaint();
        GradientPaint grad = new GradientPaint(0, width / 2 - width / 16, dayColor, 0, width / 2 + width / 16, nightColor);
        g2d.setPaint(grad);
        g2d.fillRect(0, 0, width, width);
        g2d.setPaint(savePaint);

        // Sol
        float sunRadius = dnRadius * 0.15f;
        g2d.setColor(Color.orange);
        fillStar(g2d, (int) (centerX), (int) (centerY - dnRadius + sunRadius * 2), (int) (sunRadius * 2f), (int) (sunRadius * 1.2f), 12, 0);
        g2d.setColor(Color.yellow);
        g2d.fillOval((int) (centerX - sunRadius), (int) (centerY - dnRadius + sunRadius), (int) (sunRadius * 2), (int) (sunRadius * 2));

        // Luna
        float moonRadius = dnRadius * 0.2f;
        Arc2D arc = new Arc2D.Float(centerX - moonRadius, centerY + dnRadius - moonRadius * 3, moonRadius * 2, moonRadius * 2, 0, 360, Arc2D.CHORD);
        Area moon = new Area(arc);
        arc = new Arc2D.Float(centerX - moonRadius - moonRadius * 0.7f, centerY + dnRadius - moonRadius * 3, moonRadius * 2, moonRadius * 2, 0, 360, Arc2D.CHORD);
        moon.subtract(new Area(arc));
        g2d.setColor(Color.white);
        g2d.fill(moon);

        // Estrellas
        double angle;
        g2d.setColor(Color.yellow);
        for (int n = 0; n < 4; n++) {
            angle = (180.0 - (180.0 / 8) - n * (180.0 / 4)) % 360;
            float x = (float) (Math.cos(Math.toRadians(angle)) * dnRadius * 0.75);
            float y = (float) (Math.sin(Math.toRadians(angle)) * dnRadius * 0.75);
            fillStar(g2d, (int) (centerX + x), (int) (centerY + y), (int) (dnRadius * 0.1f), (int) (dnRadius * 0.05f), 5, (int) (270 - 180.0 / 8 - n * 180.0 / 4));
        }
        for (int n = 0; n < 2; n++) {
            angle = (180.0 - (180.0 / 4) - n * (180.0 / 2)) % 360;
            float x = (float) (Math.cos(Math.toRadians(angle)) * dnRadius * 0.35);
            float y = (float) (Math.sin(Math.toRadians(angle)) * dnRadius * 0.35);
            fillStar(g2d, (int) (centerX + x), (int) (centerY + y), (int) (dnRadius * 0.1f), (int) (dnRadius * 0.05f), 5, (int) (270 - 180.0 / 4 - n * 180.0 / 2));
        }

        g2d.dispose();
        g2d = null;

        centerX = frame.x + frame.width / 2;
        centerY = frame.y + frame.height / 2;

        // Region Clip
        arc = new Arc2D.Float(centerX - dnRadius, centerY - dnRadius, dnRadius * 2, dnRadius * 2, 10, 180, Arc2D.CHORD);
        dnClip = new Area(arc);
        arc = new Arc2D.Float(centerX - dnRadius * 1.1f, centerY - dnRadius * 1.1f, dnRadius * 2.2f, dnRadius * 2.2f, 180 - 10, 180, Arc2D.CHORD);
        dnClip.subtract(new Area(arc));
        arc = new Arc2D.Float(centerX - dnRadius * 0.2f, centerY - dnRadius * 0.2f, dnRadius * 0.4f, dnRadius * 0.4f, 0, 180, Arc2D.CHORD);
        dnClip.subtract(new Area(arc));
        /**
         * *******************************************************************
         * Codigo que redondea los angulos de la caratula de dia / noche
         */
        final float smoothLen = 0.15f;
        int testX = (int) (centerX + dnRadius * (1 - smoothLen) * Math.cos(Math.toRadians(10)));
        int testY = (int) (centerY - dnRadius * (1 - smoothLen) * Math.sin(Math.toRadians(10)));

        int testX2 = (int) (centerX + dnRadius * Math.cos(Math.toRadians(10)));
        int testY2 = (int) (centerY - dnRadius * Math.sin(Math.toRadians(10)));

        int testX3 = (int) (centerX + dnRadius * Math.cos(Math.toRadians(10) + smoothLen));
        int testY3 = (int) (centerY - dnRadius * Math.sin(Math.toRadians(10) + smoothLen));

        int testX4 = (int) (centerX + dnRadius * 1.05f * Math.cos(Math.toRadians(10) + smoothLen));
        int testY4 = (int) (centerY - dnRadius * 1.05f * Math.sin(Math.toRadians(10) + smoothLen));

        GeneralPath gp = new GeneralPath();
        gp.moveTo(testX, testY);
        gp.quadTo(testX2, testY2, testX3, testY3);
        gp.lineTo(testX4, testY4);
        testX2 = (int) (centerX + dnRadius * 1.05f * Math.cos(Math.toRadians(10 - 1)));
        testY2 = (int) (centerY - dnRadius * 1.05f * Math.sin(Math.toRadians(10 - 1)));
        gp.lineTo(testX2, testY2);
        gp.closePath();
        dnClip.subtract(new Area(gp));

        testX = (int) (centerX - dnRadius * (1 - smoothLen) * Math.cos(Math.toRadians(10)));
        testX2 = (int) (centerX - dnRadius * Math.cos(Math.toRadians(10)));
        testX3 = (int) (centerX - dnRadius * Math.cos(Math.toRadians(10) + smoothLen));
        testX4 = (int) (centerX - dnRadius * 1.05f * Math.cos(Math.toRadians(10) + smoothLen));

        gp = new GeneralPath();
        gp.moveTo(testX, testY);
        gp.quadTo(testX2, testY2, testX3, testY3);
        gp.lineTo(testX4, testY4);
        testX2 = (int) (centerX - dnRadius * 1.05f * Math.cos(Math.toRadians(10 - 1)));
        testY2 = (int) (centerY - dnRadius * 1.05f * Math.sin(Math.toRadians(10 - 1)));
        gp.lineTo(testX2, testY2);
        gp.closePath();
        dnClip.subtract(new Area(gp));
        /**
         * *******************************************************************
         */
    }

    private void fillStar(Graphics2D g2d, int x, int y, int radius, int innerRadius, int nSpikes, int rotation) {
        int xPoints[] = new int[nSpikes * 2];
        int yPoints[] = new int[xPoints.length];

        for (int n = 0; n < xPoints.length; n += 2) {
            double angle1 = (270 + rotation + (360.0 / (xPoints.length / 2)) * (n / 2)) % 360;
            double angle2 = (270 + rotation + (360.0 / (xPoints.length / 2)) * (n / 2) + (360.0 / xPoints.length)) % 360;
            xPoints[n] = x + (int) (Math.cos(Math.toRadians(angle1)) * radius);
            xPoints[n + 1] = x + (int) (Math.cos(Math.toRadians(angle2)) * innerRadius);
            yPoints[n] = y + (int) (Math.sin(Math.toRadians(angle1)) * radius);
            yPoints[n + 1] = y + (int) (Math.sin(Math.toRadians(angle2)) * innerRadius);
        }

        g2d.fillPolygon(xPoints, yPoints, xPoints.length);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Rectangle bounds = getBounds();
        int size = Math.min(bounds.width, bounds.height) - 1;
        Rectangle frame = new Rectangle(bounds.width / 2 - size / 2, bounds.height / 2 - size / 2, size, size);

        frame.x = bounds.width / 2 - frame.width / 2;
        frame.y = bounds.height / 2 - frame.height / 2;

        boolean updateDayNight = false;
        /* La primera vez que se ejecuta el programa:
              Se genera la imagen de fondo (updateBackgroundImage)
              Se genera la caractula (createDayNightImage)
              Se genera el dia/noche (updateDayNight)
         */
        // la proxima vez que se llame esta validacion no se va a cumplir por lo que solo se pinta backgroundImage
        // hasta que se produca el cambio de minuto donde se ejecuta el else if
        if ((backgroundImage == null) || (backgroundImage.getWidth() != bounds.width) || (backgroundImage.getHeight() != bounds.height)) {
            updateBackgroundImage(bounds, frame);
            createDayNightImage(frame);
            updateDayNight = true;
        } else if (lastMinute != minute) {
            updateDayNight = true;
        }
        lastMinute = minute;

        Graphics2D g2d = (Graphics2D) g;

        int centerX = frame.x + frame.width / 2;
        int centerY = frame.y + frame.height / 2;
        float radius = frame.width / 2f;

        // updateDayNight == true, realizamos la rotacion y dibujamos sobre la caratula (re-pintado)
        double angle;
        if (updateDayNight || updatingCalendar) {
            Graphics2D g2dBack = backgroundImage.createGraphics();
            // hacemos el dibujo de la caratula dia/noche
            if (updateDayNight) {
                Shape saveClip = g2dBack.getClip();
                g2dBack.setClip(dnClip);

                int hour24 = hour + 12 * ampm;
                angle = (double) (180.0 + (360.0 / 24) * hour24 + (double) ((360.0 / 24 / 60) * minute)) % 360;

                AffineTransform saveTrans = g2d.getTransform();
                AffineTransform trans = new AffineTransform();
                trans.translate(centerX, centerY);
                trans.rotate(Math.toRadians(angle));
                g2dBack.setTransform(trans);
                g2dBack.drawImage(dayNightImage, -dayNightImage.getWidth() / 2, -dayNightImage.getHeight() / 2, null);
                g2dBack.setTransform(saveTrans);

                // sombreado en dia/noche
                Paint savePaint = g2dBack.getPaint();
                RadialGradientPaint rp = new RadialGradientPaint(
                        (float) (centerX - Math.cos(Math.toRadians(45)) * radius),
                        (float) (centerY - Math.sin(Math.toRadians(45)) * radius), (float) radius * 3.5f,
                        new float[]{0.0f, 0.2f},
                        new Color[]{gradientA, gradientB});
                g2dBack.setPaint(rp);
                g2dBack.fillOval(frame.x + 4, frame.y + 4, frame.width - 8, frame.height - 8);
                g2dBack.setPaint(savePaint);
                g2dBack.setClip(saveClip);
            }
            // dibujamos el calendario
            if (updatingCalendar) {
                // cuando son las 11:59 cada segundo se redibuja el calendario
                drawCalendar(g2dBack, frame);
            }

            g2dBack.dispose();
        }
        // pintamos la caratula
        g2d.drawImage(backgroundImage, 0, 0, null);

        // Manecillas y remache
        int x0, x1, x2;
        int y0, y1, y2;

        double hourRadius = radius * 0.5f;
        float minRadius = radius * 0.7f;
        double secRadius = radius * 0.9f;

        angle = (270 + (double) hour * (360.0 / 12) + (double) minute * (360.0 / (12 * 60))) % 360;
        x0 = (int) (Math.cos(Math.toRadians(angle)) * hourRadius);
        y0 = (int) (Math.sin(Math.toRadians(angle)) * hourRadius);
        x1 = (int) (cos(minute) * minRadius);
        y1 = (int) (sin(minute) * minRadius);
        x2 = (int) (cos(second) * secRadius);
        y2 = (int) (sin(second) * secRadius);

        // Sombra de manecillas
        g2d.setStroke(new BasicStroke(hourStroke));
        g2d.setColor(shadowColor);
        g2d.fillOval(centerX - 10 + 2, centerY - 10 + 2, 20, 20);
        g2d.drawLine(centerX + 2, centerY + 2, centerX + x0 + 2, centerY + y0 + 2);
        g2d.setStroke(new BasicStroke(minuteStroke));
        g2d.drawLine((int) (centerX - x1 * 0.15f) + 2, (int) (centerY - y1 * 0.15f) + 2, centerX + x1 + 2, centerY + y1 + 2);
        g2d.setStroke(new BasicStroke(secondStroke));
        g2d.drawLine((int) (centerX - x2 * 0.2f) + 3, (int) (centerY - y2 * 0.2f) + 3, centerX + x2 + 3, centerY + y2 + 3);

        // Manecillas
        g2d.setStroke(new BasicStroke(hourStroke));
        g2d.setColor(hourColor);
        g2d.fillOval(centerX - 10, centerY - 10, 20, 20);
        g2d.drawLine(centerX, centerY, centerX + x0, centerY + y0);
        g2d.setStroke(new BasicStroke(minuteStroke));
        g2d.setColor(minuteColor);
        g2d.drawLine((int) (centerX - x1 * 0.15), (int) (centerY - y1 * 0.15), centerX + x1, centerY + y1);
        g2d.setStroke(new BasicStroke(secondStroke));
        g2d.setColor(secColor);
        g2d.drawLine((int) (centerX - x2 * 0.2), (int) (centerY - y2 * 0.2), centerX + x2, centerY + y2);

        // Remache
        g2d.setColor(backgroundColor);
        g2d.fillOval(centerX - 3, centerY - 3, 6, 6);
    }

    public static void main(String[] args) throws Exception {
        Watch12 mainPanel = new Watch12();

        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setTitle("Watch 10 - Raul Cosio");
            frame.setMinimumSize(new Dimension(400, 400));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
