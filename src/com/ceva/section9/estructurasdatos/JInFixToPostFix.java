package com.ceva.section9.estructurasdatos;

import java.util.StringTokenizer;

/**
 * En el programa asumimos que cada caracter esta separado por un espacio.
 * 1 + 2 + 3 = 1 2 3 + +
 * 1 * 2 + 3 = 1 2 * 3 +
 * 1 + 2 * 3 = 1 2 3 * +
 * ( 1 + 2 * ( 5 + 6 ) ) * ( 3 + 4 ) = 1 2 5 6 + * + 3 4 + *
 */
public class JInFixToPostFix {
    private boolean isDigit(char ch) {
        return (ch >= '0') && (ch <= '9');
    }

    private boolean isNumber(String str) {
        int dot = -1;
        int nDigits = 0;
        for (int n=0; n<str.length(); n++) {
            char ch = str.charAt(n);
            if (ch == '.') {
                if ((dot != -1) || (n >= (str.length() - 1)))
                    return false;
                dot = n;
            } else if (ch == '-') {
                if (n != 0)
                    return false;
            } else if (!isDigit(ch))
                return false;
            else
                nDigits++;
        }
        // si nDigits es mayor a cero quiere decir que si hay numeros.
        return nDigits > 0;
    }

    private boolean isOperator(String s) {
        return "+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s);
    }

    private int operatorPrecedence(String s) {
        if ("+".equals(s) || "-".equals(s))
            return 1;
        else if ("*".equals(s) || "/".equals(s))
            return 2;
        return 0;
    }

    private void spaceAppend(StringBuilder sb, String s) {
        // si el resulatado ya tiene informacion, se agrega un espacio.
        if (sb.length() > 0)
            sb.append(" ");
        sb.append(s);
    }

    public String toPostFix(String expr) {
        StringBuilder res = new StringBuilder();
        HStack<String> stack = new HStack<>(100);

        // el espacio en blanco sera el separador de palabras
        // 0. Obtener los elementos d la expresion
        StringTokenizer st = new StringTokenizer(expr, " ");
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            // 1. Validamos si se trata de un parentesis abrierto
            if ("(".equals(s)) {
                // 1. Lo guardamos en el stack
                stack.push(s);
            }
            // 2. Validamos que el elemento sea un numero
            else if (isNumber(s)) {
                // 2. Agregamos el element a la respuesta
                spaceAppend(res, s);
            }
            // 3. validamos is se trata de un operador aritmetico
            else if (isOperator(s)) {
                // validamos que haya elementos en el stack
                if (stack.size() > 0) {
                    // 3.1 obtenemos el ultimo elemento que hicimos push en el stack
                    String peek = stack.peek();
                    // mientas haya elementos en el stack
                    // mientras sea un operador el ultimo elemento en el stack
                    // mientras la precedencia del ultimo elemento sea mayor o igual a la precedencia del operador encontrado
                    // mientras el ultimo elemento del stack no sea un parentesis abierto "("
                    while ((stack.size() > 0) && isOperator(peek) &&
                            (operatorPrecedence(peek) >= operatorPrecedence(s)) && !"(".equals(peek)
                    ) {
                        // agregamos el elemento a la respuesta
                        spaceAppend(res, peek);
                        // pop al stack
                        peek = stack.pop();
                    }
                }
                // 3.2 guardamos el elemento encontrado en el stack
                stack.push(s);
            }
            // 4.
            else if (")".equals(s)) {
                // mientras haya elementos en el stack
                // y mientras lo que hay en stack,peek no sea un parentesis abierto "("
                while ((stack.size() > 0) && !"(".equals(stack.peek())) {
                    // lo agregamos a la respuesta
                    spaceAppend(res, stack.pop());
                }
                stack.pop(); // sacamos (pop) '('
            }
        }
        // 5. vaciamos lo que haya quedado en el stack, agregandolo a la respuesta
        while (stack.size() > 0) {
            String s = stack.pop();
            spaceAppend(res, s);
        }

        return res.toString();
    }

    public static void main(String[] args) {
        JInFixToPostFix cvt = new JInFixToPostFix();
        String result = cvt.toPostFix("( 1 + 2 * ( 5 + 6 ) ) * ( 3 + 4 )");
        System.out.println(result);

        IPostFixCalculator calc = new IPostFixCalculator();
        if (calc.evaluate(result)) {
            calc.printResult();
        }
    }
}
