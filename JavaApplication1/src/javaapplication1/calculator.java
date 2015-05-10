/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Emma
 */
public class calculator {
    public void printExpress(){
        String A = "+ 2 3 (* 5 6)";
        String B = A.trim();
    
        System.out.println(B.split("(")[0]);
    }
    
    
}
