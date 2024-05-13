package com.ceva.section5.poo;

public class DemoPOO {

    private static void dibujar(){
        BPoint  point = new CCircle(50,80,1);
        point.draw();
    }

    // demo typecast
    private  static void demoTypeCast(){
        BPoint p = new CCircle(50,80, 3);
        // el objeto p contiene un CCircle, invocamos el metodo area de CCircle usando casting
        ((CCircle)p).area();
    }

    // usando Interface
    private static void UsingInterface(){
        IDrawable drawable[] = new IDrawable[4];
        drawable[0] = new BPoint(5,15);
        drawable[1] = new CCircle(5,15,25);
        drawable[2] = new DRectangle(5,15, 25, 25);
        drawable[3] = new EPerson("Javier");

        for(IDrawable d : drawable){
            d.draw();
        }
    }

    // nested classes
    private static void nested(){
        FBase fNested = new FBase();
        FBase.Nested nested = fNested.nested;
    }
    public static void main(String[] args) {
        dibujar();
        demoTypeCast();
        UsingInterface();
    }
}
