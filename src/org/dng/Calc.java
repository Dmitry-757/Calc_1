package org.dng;

public class Calc {
    static int Calculate(int num1, int num2, char opDesignation){
        switch (opDesignation){
            case '+':
                return(num1+num2);
            case '-':
                return(num1-num2);
            case '*':
                return(num1*num2);
            case '/':
                return(num1/num2);
            default:
                System.out.println("somthing wrong during calculating!");
        }
        return 0;
    }
}
