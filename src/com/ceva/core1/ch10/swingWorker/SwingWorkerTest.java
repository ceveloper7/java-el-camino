package com.ceva.core1.ch10.swingWorker;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

import javax.swing.*;


/**
 * Tecnica que nos permite ejecutar una tarea que consume tiempo, mientras mantenemos
 * la UI responsiva.
 */
public class SwingWorkerTest
{
    public static void main(String[] args) throws Exception
    {
        EventQueue.invokeLater(() ->
        {
            var frame = new SwingWorkerFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * This frame has a text area to show the contents of a text file, a menu to open a file and
 * cancel the opening process, and a status line to show the file loading progress.
 */
class SwingWorkerFrame extends JFrame
{
    private JFileChooser chooser;
    private JTextArea textArea;
    private JLabel statusLine;
    private JMenuItem openItem;
    private JMenuItem cancelItem;
    private SwingWorker<StringBuilder, ProgressData> textReader;
    public static final int TEXT_ROWS = 20;
    public static final int TEXT_COLUMNS = 60;

    public SwingWorkerFrame()
    {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(textArea));

        statusLine = new JLabel(" ");
        add(statusLine, BorderLayout.SOUTH);

        var menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        var menu = new JMenu("File");
        menuBar.add(menu);

        openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(event ->
        {
            // show file chooser dialog
            int result = chooser.showOpenDialog(null);

            // if file selected, set it as icon of the label
            if (result == JFileChooser.APPROVE_OPTION)
            {
                textArea.setText("");
                openItem.setEnabled(false);
                textReader = new TextReader(chooser.getSelectedFile());
                textReader.execute();
                cancelItem.setEnabled(true);
            }
        });

        cancelItem = new JMenuItem("Cancel");
        menu.add(cancelItem);
        cancelItem.setEnabled(false);
        cancelItem.addActionListener(event -> textReader.cancel(true));
        pack();
    }

    // El nro de linea y el texto de la linea lo manejamos en un record
    private record ProgressData(int number, String line) {}

    private class TextReader extends SwingWorker<StringBuilder, ProgressData>
    {
        private File file;
        private StringBuilder text = new StringBuilder();

        public TextReader(File file)
        {
            this.file = file;
        }

        // the following method executes in the worker thread; it doesn't touch Swing components
        // llama a publish para comunicar el progreso del trabajo
        @Override
        public StringBuilder doInBackground() throws IOException, InterruptedException
        {
            int lineNumber = 0;
            try (var in = new Scanner(new FileInputStream(file)))
            {
                // leemos el archivo una linea a la vez
                while (in.hasNextLine())
                {
                    String line = in.nextLine();
                    lineNumber++;
                    text.append(line).append("\n");
                    // construimos una linea de texto
                    var data = new ProgressData(lineNumber, line);
                    // pasamos la linea al publish
                    publish(data);
                    // permitimos demorar el procesamiento para hacer la prueba con el menu cancel
                    //Thread.sleep(1); // to test cancellation; no need to do this in your programs
                }
            }
            return text;
        }

        // the following methods execute in the event dispatch thread (gestionador de la UI)
        // cuando el trabajo se completa, llamamos al metodo done() en el EDT
        @Override
        public void process(List<ProgressData> data)
        {
            if (isCancelled()) return;
            // total las lineas del archivo en un StringBuilder
            var builder = new StringBuilder();
            // pasamos el ultimo numero de linea
            statusLine.setText("" + data.getLast().number());
            // cargamos al StringBuilder con las lineas del archivo
            for (ProgressData d : data) builder.append(d.line()).append("\n");
            textArea.append(builder.toString());
        }

        // Metodo que culmina la actualizacion de la UI
        @Override
        public void done()
        {
            try
            {
                // actualizamos el TextArea
                StringBuilder result = get();
                textArea.setText(result.toString());
                statusLine.setText("Done");
            }
            catch (InterruptedException ex)
            {
            }
            catch (CancellationException ex)
            {
                textArea.setText("");
                statusLine.setText("Cancelled");
            }
            catch (ExecutionException ex)
            {
                statusLine.setText("" + ex.getCause());
            }

            cancelItem.setEnabled(false);
            openItem.setEnabled(true);
        }
    }
}
