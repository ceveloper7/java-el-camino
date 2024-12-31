package com.ceva.core1.ch10.completableFutures;

import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

import javax.imageio.*;

/**
 * Programa que lee una pagina web
 */

public class CompletableFutureDemo {
    private static final Pattern IMG_PATTERN = Pattern.compile(
            "[<]\\s*[iI][mM][gG]\\s*[^>]*[sS][rR][cC]\\s*[=]\\s*['\"]([^'\"]*)['\"][^>]*[>]");
    private ExecutorService executor = Executors.newCachedThreadPool();
    private URI uriToProcess;

    // metodo que retorna el texto de una pagina web cuando esta disponible
    public CompletableFuture<String> readPage(URI uri)
    {
        // leemos la pagina web
        return CompletableFuture.supplyAsync(() -> {
            try
            {
                var contents = new String(uri.toURL().openStream().readAllBytes());
                System.out.println("Read page from " + uri);
                return contents;
            }
            catch (IOException e)
            {
                throw new UncheckedIOException(e);
            }
        }, executor);
    }

    // metodo que provee los links de las imagenes de la pagina web
    public List<URI> getImageLinks(String webpage) // not blocking
    {
        var result = new ArrayList<URI>();
        Matcher matcher = IMG_PATTERN.matcher(webpage);
        while (matcher.find())
        {
            URI uri = URI.create(uriToProcess + "/" + matcher.group(1));
            result.add(uri);
        }
        System.out.println("Found links: " + result);
        return result;
    }

    /*
     * Metodo asincrono
     */
    public CompletableFuture<List<BufferedImage>> getImages(List<URI> uris)
    {
        return CompletableFuture.supplyAsync(() -> {
            try
            {
                var result = new ArrayList<BufferedImage>();
                for (URI uri : uris)
                {
                    result.add(ImageIO.read(uri.toURL()));
                    System.out.println("Loaded " + uri);
                }
                return result;
            }
            catch (IOException e)
            {
                throw new UncheckedIOException(e);
            }
        }, executor);
    }

    /*
     * Procesamos el resultado: almacenamos las imagenes
     */
    public void saveImages(List<BufferedImage> images)
    {
        System.out.println("Saving " + images.size() + " images");
        try
        {
            for (int i = 0; i < images.size(); i++)
            {
                String filename = "C:\\Users\\carlo\\tmp\\image" + (i + 1) + ".png";
                ImageIO.write(images.get(i), "PNG", new File(filename));
            }
        }
        catch (IOException e)
        {
            throw new UncheckedIOException(e);
        }
        executor.shutdown();
    }

    public CompletableFutureDemo(URI uri)
    {
        uriToProcess = uri;
    }

    public void run() throws IOException, InterruptedException
    {
        CompletableFuture.completedFuture(uriToProcess)
                .thenComposeAsync(this::readPage, executor)
                // thenApply retorna otro future, cuado el primero future de readPage ha terminado
                // el resultado alimenta a getImagesLinks
                .thenApply(this::getImageLinks)
                // thenCompose trabaja exacto que thenApply
                .thenCompose(this::getImages)
                // procesamos el resultado
                .thenAccept(this::saveImages);

        // or use the HTTP client:
      /*
      HttpClient client = HttpClient.newBuilder().build();
      HttpRequest request = HttpRequest.newBuilder(uriToProcess).GET().build();
      client.sendAsync(request, BodyHandlers.ofString())
         .thenApply(HttpResponse::body)
         .thenApply(this::getImageLinks)
         .thenCompose(this::getImages)
         .thenAccept(this::saveImages);
      */
    }

    public static void main(String[] args)
            throws IOException, InterruptedException
    {
        new CompletableFutureDemo(URI.create("http://horstmann.com/index.html")).run();
    }
}
