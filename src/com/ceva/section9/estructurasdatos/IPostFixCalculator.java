package com.ceva.section9.estructurasdatos;

import java.util.StringTokenizer;

public class IPostFixCalculator {
    private HStack stack;

    public IPostFixCalculator() {
        stack = new HStack(100);
    }

    private boolean isDigit(char ch) {
        return (ch >= '0') && (ch <= '9');
    }

    // Recorremos los caracteres de la cadena y confirmar que sean digitos.
    private boolean isNumber(String str) {
        int dot = -1; // almacena la posicion del punto dentro de la cadena. -1 significa que no se ha encontrado . en la cadena
        int nDigits = 0;
        for (int n=0; n<str.length(); n++) {
            char ch = str.charAt(n);
            // validamos que no contenga .
            if (ch == '.')
            {
                // si encontramos un punto y dot no es -1, significa que la cadena tiene un segundo punto lo que es un error
                // si estamos al final de la cadena y encontramos un punto, tambien es un error
                if ((dot != -1) || (n >= (str.length() - 1)))
                    return false;
                // establcemos donde encontramos el punto
                dot = n;
            }
            // validamos que no sea negativo
            else if (ch == '-') {
                // si n no esta en la primera posicion
                if (n != 0)
                    return false;
            }
            // validamos que no se haya ingresado un caracter invalido
            else if (!isDigit(ch))
                return false;
            else
                nDigits++;
        }
        return nDigits > 0;
    }

    public boolean evaluate(String expr) {
        StringTokenizer st = new StringTokenizer(expr, " ");
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            // validamos si es un numero
            if (isNumber(s)) {
                double d = Double.parseDouble(s);
                stack.push(d); // agregamos al stack
            }
            // validamos los operadores basicos
            else if ("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s)) {
                // tomamos los dos ultimos elementos del stack
                Object o2 = stack.pop();
                Object o1 = stack.pop();
                // validamos que en el stack haya dos numeros para hacer la operacion
                if (!(o2 instanceof Double) || !(o1 instanceof Double)) {
                    System.out.println("Operators are not numbers");
                    return false;
                }
                Double d1 = (Double) o1;
                Double d2 = (Double) o2;

                switch(s) {
                    // hacemos operaciones segun sea el caso y el resultado lo almacenamos en el stack
                    case "+":
                        stack.push(d1 + d2);
                        break;
                    case "-":
                        stack.push(d1 - d2);
                        break;
                    case "*":
                        stack.push(d1 * d2);
                        break;
                    case "/":
                        stack.push(d1 / d2);
                }
            }
        }
        return true;
    }

    // impresion de los valores en el stack
    public void printResult() {
        System.out.println("Result: ");
        while (stack.size() > 0) {
            Object o = stack.pop();
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        IPostFixCalculator calc = new IPostFixCalculator();
        calc.evaluate("5 6 + 9 + 2 / 5 * -1 *");
        calc.printResult();
    }
}
