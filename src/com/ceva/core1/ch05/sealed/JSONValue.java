package com.ceva.core1.ch05.sealed;

sealed interface JSONValue permits JSONArray, JSONObject, JSONPrimitive {
    default String type(){
        // agregando un case null evitamos el caso que el switch expression lance un NullPointerException
        return switch (this) {
            case JSONArray jsonValues -> "Array";
            case JSONObject jsonObject -> "Object";
            case JSONNumber jsonNumber -> "Number";
            case JSONString jsonString -> "String";
            case JSONBoolean jsonBoolean -> "Boolean";
            case null-> "null";
            default -> "Is not a data type";
        };
//        if(this instanceof JSONArray) return "Array";
//
//        else if(this instanceof JSONObject) return "Object";
//        else if(this instanceof JSONNumber) return "Number";
//        else if(this instanceof JSONString) return "String";
//        else if(this instanceof JSONBoolean) return "Boolean";
//
//        else return "null";
    }
}
