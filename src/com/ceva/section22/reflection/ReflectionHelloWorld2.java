package com.ceva.section22.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectionHelloWorld2 {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("com.ceva.section22.reflection.HelloWorld");
        Method method = cls.getMethod("hello");
        // Obtenemos el constructor de la clase
        Constructor cons = cls.getConstructor();
        // Creamos la instancia de la clase
        Object obj = cons.newInstance();
        method.invoke(obj);
    }
}
