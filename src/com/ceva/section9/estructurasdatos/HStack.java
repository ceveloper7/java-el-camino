package com.ceva.section9.estructurasdatos;

public class HStack<T> {
    private Object data[];
    // sp -> stack pointer
    private int sp = -1; // Fuera del rango del arreglo

    public HStack(int size) {
        data = new Object[size];
    }

    public void push(T element) {
        if (sp >= (data.length -1))
            throw new StackOverflowError();
        sp++;
        data[sp] = element;
    }

    public T pop() {
        if (sp < 0)
            throw new IllegalStateException("Empty stack.");
        T result = (T) data[sp];
        // una vez recuperado el objeto a retirar del stack, asignamos null en esa posicion para evitar las fugas de memoria.
        data[sp] = null;
        sp--;
        return result;
    }

    // devuelve el elemento donde se encuentra el stack pointer
    public T peek() {
        if(sp < 0)
            throw new IllegalStateException("Empty stack.");
        return (T) data[sp];
    }

    public int size() {
        return sp + 1;
    }

    @Override
    public String toString() {
        if (sp == -1)
            return "<< empty >>";
        StringBuilder sb = new StringBuilder();
        for (int n=0; n<=sp; n++) {
            if (sb.length() > 0)
                sb.append(" ");
            sb.append(data[n].toString());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        HStack<Integer> stack = new HStack<>(5);
        for (int i=0; i<5; i++) {
            // llenamos el stack
            stack.push(i);
            System.out.print(i + " ");
        }
        System.out.println();
        while (stack.size() > 0) {
            // vaciamos el stack
            int i = stack.pop();
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
