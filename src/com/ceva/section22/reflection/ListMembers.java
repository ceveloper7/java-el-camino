package com.ceva.section22.reflection;

import java.lang.reflect.Method;
public class ListMembers {
    public static void main(String[] args) {
        System.out.println("Lista de Operaciones de  la clase String:");
        Method methods[] = String.class.getMethods();
        // recorremos los metodos
        for (Method m : methods) {
            // vamos a construir la lista de argumentos que contiene cada operacion o metodo
            StringBuilder sb = new StringBuilder();
            // obtenemos los parametros de la operacion o metodo actual
            Class paramTypes[] = m.getParameterTypes();
            // recorremos los parametros
            for (Class t : paramTypes) {
                if (sb.length() > 0)
                    // agregamos la , cuando el string builder tiene info
                    sb.append(",");
                // validamos si el parametro es un arreglo
                if (t.isArray()) {
                    // imprimimos el tipo del dato
                    sb.append(t.getComponentType()).append("[]");
                } else
                    sb.append(t.getName());
            }

            System.out.printf("%s(%s)\n", m.getName(), sb.toString());
        }
    }
}
