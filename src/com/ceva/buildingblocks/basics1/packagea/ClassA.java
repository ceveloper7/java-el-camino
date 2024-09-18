package com.ceva.buildingblocks.basics1.packagea;

/**
 * Compilando Clases en distintos paquetes: Como se accede a los paquetes en Windows y en Linux son distinto
 * Windows: C:\Users\home\local-projects\packagea\ClassA.java - javac packagea\ClassA.java packageb\ClassB.java
 * Linux: ~/local-projects/packageb/ClassA.java - javac packagea/ClassA.java packageb/ClassB.java
 * Nota: Tambien se puede usar javac packagea/ClassA packageb/*.java y se compilaran todas las clases dentro del packageb
 *
 * Ejecucion de Clases
 * ===================
 * java -cp ~/local-projects/intellij/java-basics/java-el-camino/src/ com.ceva.buildingblocks.one.packagea.ClassA
 * java -cp ~/local-projects/intellij/java-basics/java-el-camino/src/ com.ceva.buildingblocks.one.packageb.ClassB
 *
 * Compilando hacia otros directorios
 * ==================================
 * javac -d ~/Documents/temp packagea/ClassA.java packageb/ClassB.java
 * java -cp ~/Documents/temp/ com.ceva.buildingblocks.one.packageb.ClassB
 * o tambien ...
 * java -classpath ~/Documents/temp/ com.ceva.buildingblocks.one.packageb.ClassB
 * o tambien ...
 * java --class-path ~/Documents/temp/ com.ceva.buildingblocks.one.packageb.ClassB
 *
 * Creacion de archivos .jar
 * =========================
 * jar -cvf myProject.jar . -> el punto . significa que los archivos de la carpeta donde estoy se incluiran en el archivo .jar
 * o tambien ...
 * jar --create --verbose --file myNewFile.jar .
 *
 * En el siguiente ejemplo las clases que forman parte del .jar no se encuentra en la carpeta actual sino en otra carpeta llamada proyecto:
 * jar -cvf myProject3.jar -C proyecto .
 *
 */
public class ClassA {
    public static void main(String[] args) {
        System.out.println("Hello from Class A");
    }
}
