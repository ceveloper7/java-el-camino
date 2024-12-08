package com.ceva.core1.ch09.map;

import com.ceva.core1.ch04.model.Employee;

import java.util.HashMap;

public class MapTest {
    public static void main(String[] args) {
        // implements a hash map
        var staff = new HashMap<String,Employee>();
        // agregamos key, y creamos un objeto Employee
        staff.put("144-25-5464", new Employee("Amy Lee", 60000, 2005,10,11));
        staff.put("567-24-2546", new Employee("Harry Hacker", 50000, 1982,7,20));
        staff.put("157-62-7935", new Employee("Gary Cooper", 70000, 1975,5,10));
        staff.put("456-62-5527", new Employee("Francesca Cruz", 48000, 2000,12,23));

        // print all
        System.out.println(staff);

        // remove an entry
        staff.remove("157-62-7935");

        // replace an entry
        staff.put("144-25-5464", new Employee("Carlos V.", 850000, 1999,8,11));
        System.out.println(staff);

        // lookup a value
        System.out.println(staff.get("144-25-5464"));

        // iteramos el hash map
        staff.forEach((k,v)-> System.out.println("key: " + k + ", value: " + v));
    }
}
