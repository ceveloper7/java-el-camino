package com.ceva.core1.ch07;

public class JavaCoreException extends RuntimeException{
    public JavaCoreException(){
    }

    public JavaCoreException(String message){
        super(message);
    }

    public JavaCoreException(Throwable cause){
        super(cause);
    }

    public JavaCoreException(String message, Throwable cause){
        super(message, cause);
    }

    @Override
    public String getLocalizedMessage(){
        return super.getLocalizedMessage();
    }
}
