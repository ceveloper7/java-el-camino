package com.ceva.core1.ch10.process;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ProcessDemo {
    public static void main(String[] args) throws IOException, InterruptedException
    {
        // ProcessBuilder permite configurar un objeto Process
        Process p = new ProcessBuilder("/bin/ls", "-l")
                // cada process tiene su working directory se usa para resolver nombres
                // directorios relativos
                .directory(Path.of("/tmp").toFile())
                // iniciar el proceso
                .start();
        try (var in = new Scanner(p.getInputStream()))
        {
            while (in.hasNextLine())
                System.out.println(in.nextLine());
        }
        System.out.println("pid: " + p.toHandle().pid());
        int result = p.waitFor();
        System.out.println("Exit value: " + result);

        ProcessHandle.allProcesses()
                .map(ProcessHandle::info)
                .filter(info -> info.command().filter(s -> s.contains("java")).isPresent())
                .forEach(info -> info.commandLine().ifPresent(System.out::println));
    }
}
