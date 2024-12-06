package com.ceva.core1.ch08.generics;

import com.ceva.core1.ch04.model.Employee;
import com.ceva.core1.ch05.Manager;

import java.util.ArrayList;

/*
 * Reglas de herencia para los tipos genericos
 */
public class C_PairTest {

    // Comodin ?
    // imprime un tipo Pair cuyo parameter type sea una subclase de Employee, por ejemplo Pair<Manager>
    // Pair<Manager> es un subtipo de Pair<? extends Employee>
    private static void printBuddies(A_Pair<? extends Employee> p){
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
    }

    // ? super Manager puede ser un Manager
    // ? super Manager puede ser un Employee
    private static void minmaxBonus(Manager[] a, A_Pair<? super Manager> result){
        if (a.length == 0) return;
        Manager min = a[0];
        Manager max = a[0];
        for (int i = 1; i < a.length; i++)
        {
            if (min.getBonus() > a[i].getBonus()) min = a[i];
            if (max.getBonus() < a[i].getBonus()) max = a[i];
        }
        result.setFirst(min);
        result.setSecond(max);
    }

    private static void maxminBonus(Manager[] a, A_Pair<? super Manager> result){
        minmaxBonus(a, result);
        D_PairAlg.swapHelper(result);
    }

    public static void main(String[] args) {
        var ceo = new Manager("Gus Greedy", 800000, 2003,12,15);
        var cfo = new Manager("Sid Sneaky", 600000, 2003, 12, 15);
        var budies = new A_Pair<Manager>(ceo, cfo);
        printBuddies(budies);

        ceo.setBonus(1000000);
        cfo.setBonus(500000);

        Manager[] managers = new Manager[]{ceo, cfo};

        var result = new A_Pair<Employee>();
        minmaxBonus(managers, result);
        System.out.println("first: " + result.getFirst().getName()
                + ", second: " + result.getSecond().getName());

        maxminBonus(managers, result);
        System.out.println("first: " + result.getFirst().getName()
                + ", second: " + result.getSecond().getName());
    }
}
