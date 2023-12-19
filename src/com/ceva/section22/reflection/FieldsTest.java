package com.ceva.section22.reflection;

import java.lang.reflect.Field;
public class FieldsTest {
    public String str = null;

    public void showStr() {
        System.out.println(str);
    }

    public static void main(String[] args) throws Exception {
        FieldsTest test = new FieldsTest();
        Field field = test.getClass().getField("str");
        // modificamos el valor de str por medio de reflction
        field.set(test, "Hola mundo");

        test.showStr();
    }
}
