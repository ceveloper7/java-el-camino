package com.ceva.section22.reflection;

import java.lang.reflect.Field;
public class PrivateFieldsTest {
    private String str = null;

    public void showStr() {
        System.out.println(str);
    }

    public static void main(String[] args) throws Exception {
        PrivateFieldsTest test = new PrivateFieldsTest();
        Field field = test.getClass().getDeclaredField("str");
        // pedimos acceder al campo aunque sea privado
        field.setAccessible(true);
        field.set(test, "Hola mundo");

        test.showStr();
    }
}
