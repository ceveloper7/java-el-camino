package com.ceva.test;

import com.ceva.section29.log4j_junit.ParseIntInterface;

public class FakeMain implements ParseIntInterface {

    @Override
    public boolean validate(String str1, String str2) {
        return true;
    }

}