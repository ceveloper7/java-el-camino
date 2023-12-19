package com.ceva.section22.reflection;

import java.lang.reflect.Method;

public class ReflectionHelloWorld {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("com.ceva.section22.reflection.HelloWorld");
        // creamos una referencia al metodo main de la clase HelloWorld
        Method method = cls.getMethod("main", String[].class);
        // como main es static no necesita una instancia por ello el primer argumento es null
        method.invoke(null, new Object[] { args });
    }
}
