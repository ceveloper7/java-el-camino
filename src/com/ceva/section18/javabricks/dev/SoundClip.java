package com.ceva.section18.javabricks.dev;

import java.io.*;
import javax.sound.sampled.*;

public class SoundClip implements LineListener {
    private Clip clip = null;
    private boolean loopFlag = false;
    private LineListener listener = null;

    public SoundClip(String fileName, String defaultClassPath) {
        loadClip(fileName, defaultClassPath);
    }

    private static InputStream openStream(String filename, String defaultPath) {
        // intentamos abrir el archivo de sonido por el nombre
        File file = new File(filename);
        if (!file.exists())
            // si no encuentra el archivo, lo buscamos en el home
            file = new File(System.getProperty("user.dir"), file.getName());
        try {
            if (file.exists())
                return new FileInputStream(file);
            else {
                // si no encotramos el archivo, lo buscamos como recurso del classpath
                InputStream in = SoundClip.class.getResourceAsStream((defaultPath == null ? "/" : defaultPath) + filename);
                if (in == null) {
                    System.out.println("Error: resource not found: " + ((defaultPath == null ? "/" : defaultPath) + filename));
                }
                return in;
            }
        } catch (IOException e) {
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private void loadClip(String fileName, String defaultClassPath) {
        try {
            InputStream in = openStream(fileName, defaultClassPath);
            BufferedInputStream bin = new BufferedInputStream(in);
            AudioInputStream stream = AudioSystem.getAudioInputStream(bin);
            AudioFormat format = stream.getFormat();

            // Convert ULAW/ALAW to PCM
            if ((format.getEncoding() == AudioFormat.Encoding.ULAW) ||
                    (format.getEncoding() == AudioFormat.Encoding.ALAW)) {
                AudioFormat newFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                        format.getSampleRate(),
                        format.getSampleSizeInBits() * 2,
                        format.getChannels(),
                        format.getFrameSize() * 2,
                        format.getFrameRate(), true);
                stream = AudioSystem.getAudioInputStream(newFormat, stream);
                format = newFormat;
            }

            DataLine.Info info = new DataLine.Info(Clip.class, format);
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Unsupported sound file: " + fileName);
                return;
            }
            clip = (Clip) AudioSystem.getLine(info);
            clip.addLineListener(this);
            clip.open(stream);
            stream.close();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported audio file: " + fileName);
        } catch (IOException e) {
            System.out.println("Could not read: " + fileName);
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.out.println("No audio line available for : " + fileName);
        }
    }

    @Override
    public void update(LineEvent lineEvent) {
        if (lineEvent.getType() == LineEvent.Type.STOP) {
            clip.stop();
            clip.setFramePosition(0);
            if (loopFlag)
                clip.start();
        }
        if (listener != null)
            listener.update(lineEvent);
    }

    public void close() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    public void setVolume(int volumeLevel) {
        if (volumeLevel > 200)
            volumeLevel = 200;
        else if (volumeLevel <= 0)
            volumeLevel = 1;
        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float f = (float)(Math.log((double)volumeLevel/100) / Math.log(10) * 20);
        if (f > volume.getMaximum())
            f = volume.getMaximum();
        volume.setValue(f);
    }

    public void play(int volumeLevel, boolean loop) {
        if (clip != null) {
            loopFlag = loop;
            setVolume(volumeLevel);
            clip.start();
        }
    }

    public void play(boolean loop) {
        if (clip != null) {
            loopFlag = loop;
            clip.start(); // start playing
        }
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

    public boolean isLooping() {
        return loopFlag;
    }

    public void stop() {
        if (clip != null) {
            loopFlag = false;
            clip.stop();
            clip.setFramePosition(0);
        }
    }

    public void pause() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void resume() {
        if (clip != null) {
            clip.start();
        }
    }

    public void setListener(LineListener listener) {
        this.listener = listener;
    }
}
