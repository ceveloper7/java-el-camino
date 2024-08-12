package com.ceva.section5.poo.polimorfismo;

// Fig. 10.1: PolymorphismTest.java
// Assigning superclass and subclass references to superclass and
// subclass variables.
public class APolymorphismTest {
    public static void main(String[] args) {
        // assign superclass reference to superclass variable
        ACommissionEmployee commissionEmployee = new ACommissionEmployee(
                "Sue", "Jones", "222-22-2222", 10000, .06);

        // assign subclass reference to subclass variable
        ABasePlusCommissionEmployee basePlusCommissionEmployee =
                new ABasePlusCommissionEmployee(
                        "Bob", "Lewis", "333-33-3333", 5000, .04, 300);

        // invoke toString on superclass object using superclass variable
        System.out.printf("%s %s:%n%n%s%n%n",
                "Call CommissionEmployee's toString with superclass reference ",
                "to superclass object", commissionEmployee.toString());

        // invoke toString on subclass object using subclass variable
        System.out.printf("%s %s:%n%n%s%n%n",
                "Call BasePlusCommissionEmployee's toString with subclass",
                "reference to subclass object",
                basePlusCommissionEmployee.toString());

        // invoke toString on subclass object using superclass variable
        ACommissionEmployee commissionEmployee2 =
                basePlusCommissionEmployee;
        System.out.printf("%s %s:%n%n%s%n",
                "Call BasePlusCommissionEmployee's toString with superclass",
                "reference to subclass object", commissionEmployee2.toString());
    }
}
