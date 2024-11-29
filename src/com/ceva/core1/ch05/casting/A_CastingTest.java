package com.ceva.core1.ch05.casting;

import com.ceva.core1.ch04.model.Employee;
import com.ceva.core1.ch05.Manager;

public class A_CastingTest {
    private static Employee[] staff = new Employee[3];

    public static void main(String[] args) {
        convertingObjects();
        useInstanceof();
    }

    private static void useInstanceof(){
        /*
         * la unica razon para hacer un cast es usar un metodo que es unico Managers como el metodo setBonus()
         * si queremos llamar al metodo setBonus() en objetos Employee, pero si ese es el caso, quizas sea
         * momento de preguntarse si se trata de un mal diseno en su clase, quizas sea motivo de llevar
         * el metodo setBonus() a la clase Employee.
         * En general es mejor minimizar el uso de cast y del operador instanceof
         */

        // use instanceof old way
        if(staff[1] instanceof Manager){
            Manager m = (Manager) staff[1];
            m.setBonus(500);
        }else{
            System.out.println("staff[1] no es una instancia de Manager");
        }

        /*
         * using pattern matching for instanceof.
         * Si staff[1] es una instancia de la clase Manager entonces a staff[1] se le asigna
         * la variable boss y podemos usarlo como Manager y saltamos el cast.
         *
         * Si staff[1] no hace referencia a Manager, boss no se aplica, y el operador instanceof
         * retorna false. el cuerpo de if se salta.
         */
        if(staff[1] instanceof Manager boss){
            boss.setBonus(500);
        }
    }

    private static void convertingObjects(){

        staff[0] = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);
    }

    private static void simpleCasting(){
        // 1.
        double x = 3.55;
        int nx = (int)x; // se descarta la parte fraccional
        System.out.println(nx);

        // 2.
        nx = (int)Math.round(x);
        System.out.println(nx);
    }
}
