package com.ceva.core1.ch05.sealed;

sealed interface JSONPrimitive extends JSONValue permits JSONNumber, JSONString, JSONBoolean, JSONNull {
}
