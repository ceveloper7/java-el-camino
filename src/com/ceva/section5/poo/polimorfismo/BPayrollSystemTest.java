package com.ceva.section5.poo.polimorfismo;

// Fig. 10.9: PayrollSystemTest.java
// Employee hierarchy test program.
public class BPayrollSystemTest {
    public static void main(String[] args) {
        // create subclass objects
        BSalariedEmployee salariedEmployee =
                new BSalariedEmployee("John", "Smith", "111-11-1111", 800.00);
        BHourlyEmployee hourlyEmployee =
                new BHourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40);
        BCommissionEmployee commissionEmployee =
                new BCommissionEmployee(
                        "Sue", "Jones", "333-33-3333", 10000, .06);
        BBasePlusCommissionEmployee basePlusCommissionEmployee =
                new BBasePlusCommissionEmployee(
                        "Bob", "Lewis", "444-44-4444", 5000, .04, 300);

        System.out.println("Employees processed individually:");

        System.out.printf("%n%s%n%s: $%,.2f%n%n",
                salariedEmployee, "earned", salariedEmployee.earnings());
        System.out.printf("%s%n%s: $%,.2f%n%n",
                hourlyEmployee, "earned", hourlyEmployee.earnings());
        System.out.printf("%s%n%s: $%,.2f%n%n",
                commissionEmployee, "earned", commissionEmployee.earnings());
        System.out.printf("%s%n%s: $%,.2f%n%n",
                basePlusCommissionEmployee,
                "earned", basePlusCommissionEmployee.earnings());

        // create four-element Employee array
        BEmployee[] employees = new BEmployee[4];

        // initialize array with Employees
        employees[0] = salariedEmployee;
        employees[1] = hourlyEmployee;
        employees[2] = commissionEmployee;
        employees[3] = basePlusCommissionEmployee;

        System.out.printf("Employees processed polymorphically:%n%n");

        // generically process each element in array employees
        for (BEmployee currentEmployee : employees) {
            System.out.println(currentEmployee); // invokes toString

            // determine whether element is a BasePlusCommissionEmployee
            if (currentEmployee instanceof BBasePlusCommissionEmployee) {
                // downcast Employee reference to
                // BasePlusCommissionEmployee reference
                BBasePlusCommissionEmployee employee =
                        (BBasePlusCommissionEmployee) currentEmployee;

                employee.setBaseSalary(1.10 * employee.getBaseSalary());

                System.out.printf(
                        "new base salary with 10%% increase is: $%,.2f%n",
                        employee.getBaseSalary());
            }

            System.out.printf(
                    "earned $%,.2f%n%n", currentEmployee.earnings());
        }

        // get type name of each object in employees array
        for (int j = 0; j < employees.length; j++) {
            System.out.printf("Employee %d is a %s%n", j,
                    employees[j].getClass().getName());
        }
    }
}
