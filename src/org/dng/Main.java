package org.dng;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in );
        System.out.println("input expression!");
        String inputStr=in.nextLine();
        in.close();
        System.out.println("you taped: "+inputStr);


        inputStr=inputStr.replace(" ","").toUpperCase();//убираем пробелы
        System.out.println("you taped: "+inputStr);

        try {
            int result = 0;
            //**** try to define Rome or Arab
            if (inputStr.matches("[MDCLXVI\\Q+-*/\\E]*")) { //все, что между \\Q и \\E экранируется
                result = RomanParse.parse(inputStr);
            } else if (inputStr.matches("[1234567890\\Q+-*/\\E]*")) {
                result = ArabParse.parse(inputStr);
            } else {
                System.out.println("WTF?! Wrong input!");
                throw new IllegalArgumentException("WTF?! Inputed digits isn't roman or arabian !");
            }
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

}
