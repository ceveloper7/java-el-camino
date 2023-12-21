package com.ceva.section24.enums;

public class TestResolution {
    // var de tipo emun
    private Resolution res = Resolution.MID;

    private int getProcessorSpeed(){
        return 1500;
    }
    private void Test(){
        int processorSpeed = getProcessorSpeed();
        if(processorSpeed > 1000){
            res = Resolution.HIGH;
        }else{
            res = Resolution.LOW;
        }

        System.out.println(res.getMaxPolygons());

    }
    public static void main(String[] args) {
        TestResolution test = new TestResolution();
        test.Test();
    }
}
