package org.dng;

import java.util.Scanner;

public class Main {
//    static final boolean debugMode=true;
    static final boolean debugMode=false;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in );
        System.out.println("input expression:");
        String inputStr=in.nextLine();
        in.close();
        System.out.println("you taped: "+inputStr);


        inputStr=inputStr.replace(" ","").toUpperCase();//убираем пробелы
//        System.out.println("you taped: "+inputStr);

        try {
            int result = 0;
            //**** try to define Rome or Arab
//            if (inputStr.matches("[MDCLXVI\\Q+-*/\\E]*")) { //все, что между \\Q и \\E экранируется
            if (inputStr.matches("[XVI\\Q+-*/\\E]*")) { //все, что между \\Q и \\E экранируется
                result = RomanParse.parse(inputStr);
                System.out.println("Result of calculation is: "+RomanParse.Arab2Rome(result));
            } else if (inputStr.matches("[1234567890\\Q+-*/\\E]*")) {
                result = ArabParse.parse(inputStr);
                System.out.println("Result of calculation is: "+result);
            } else {
                System.out.println("WTF?! Wrong input!");
                throw new IllegalArgumentException("WTF?! please input roman number from I to X or integer number from 1 to 10  !");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }

    }

}
