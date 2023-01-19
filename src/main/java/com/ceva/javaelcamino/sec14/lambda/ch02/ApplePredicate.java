/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ceva.javaelcamino.sec14.lambda.ch02;

/**
 * Podemos usar un lambda en el contexto de una interface funcional ya que un predicate es una interface funcional
 * Una interface funcional es aquella que especifica un solo metodo abstracto
 */
public interface ApplePredicate {
    // con una lambda expression podemos proveer implementacion para el metodo abstracto test(Apple apple)
    // y tratar toda la expresion como una instancia de la interface funcional.
    boolean test(Apple apple);
}
