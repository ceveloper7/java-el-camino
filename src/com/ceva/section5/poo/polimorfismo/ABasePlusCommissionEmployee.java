package com.ceva.section5.poo.polimorfismo;

// Fig. 9.11: BasePlusCommissionEmployee.java
// BasePlusCommissionEmployee class inherits from CommissionEmployee
// and accesses the superclasss private data via inherited
// public methods.
public class ABasePlusCommissionEmployee extends ACommissionEmployee{
    private double baseSalary; // base salary per week

    // six-argument constructor
    public ABasePlusCommissionEmployee(String firstName, String lastName,
                                      String socialSecurityNumber, double grossSales,
                                      double commissionRate, double baseSalary) {
        super(firstName, lastName, socialSecurityNumber,
                grossSales, commissionRate);

        // if baseSalary is invalid throw exception
        if (baseSalary < 0.0) {
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        }

        this.baseSalary = baseSalary;
    }

    // set base salary
    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0.0) {
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        }

        this.baseSalary = baseSalary;
    }

    // return base salary
    public double getBaseSalary() {return baseSalary;}

    // calculate earnings
    @Override
    public double earnings() {return getBaseSalary() + super.earnings();}

    // return String representation of BasePlusCommissionEmployee
    @Override
    public String toString() {
        return String.format("%s %s%n%s: %.2f", "base-salaried",
                super.toString(), "base salary", getBaseSalary());
    }
}
