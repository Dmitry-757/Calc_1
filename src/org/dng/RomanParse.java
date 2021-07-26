package org.dng;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//*****************************************
//не мое, но красиво )))
enum RomanNumeralE {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100),
    CD(400), D(500), CM(900), M(1000);

    private int value;

    RomanNumeralE(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public static List getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumeralE e) -> e.value).reversed())
                .collect(Collectors.toList());
    }
}
//*************************************

public class RomanParse {
    private String inValue;
    private double inNumber;
    private double currentNumber;


    static int Rome2Arab(String rome) {
        int result=0;
        int lastNum=RomanNumeral(rome.charAt(0));
        int tekNum=0;
        char ch;
        for (int i = 0; i < rome.length(); i++){
            ch = rome.charAt(i);
            tekNum = RomanNumeral(ch);
            if (tekNum == -1){
//                throw new NumberFormatException("Invalid input numeral format ?");
                throw new IllegalArgumentException("something wrong during conversion from roman 2 arabic");
            }
            if (lastNum == tekNum) result+=lastNum;
            else if (lastNum>tekNum) result+=tekNum;
            else if (lastNum<tekNum) result=tekNum-result;
            lastNum=tekNum;

        }
        return result;
    }

    private static int RomanNumeral(char letter) {
        switch (letter) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return -1;
        }
    }

    //не мое, но красиво )))
    static int Rome2Arab2(String roman) {
        String romanNum = roman.toUpperCase();
        int result = 0;

        List romanNumerals = RomanNumeralE.getReverseSortedValues();

        int i = 0;

        while ((romanNum.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeralE symbol = (RomanNumeralE) romanNumerals.get(i);
            if (romanNum.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNum = romanNum.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNum.length() > 0) {
            throw new IllegalArgumentException(roman + " something wrong during conversion from roman 2 arabic");
        }

        return result;

    }



    static String Arab2Rome(int number){
        int numbers[]  = {1, 4, 5, 9, 10, 50, 100, 500, 1000 };
        String letters[]  = { "I", "IV", "V", "IX", "X", "L", "C", "D", "M"};
        String romanValue = "";
        int N = number;
        while ( N > 0 ){
            for (int i = 0; i < numbers.length; i++){
                if ( N < numbers[i] ){
                    N -= numbers[i-1];
                    romanValue += letters[i-1];
                    break;
                }
            }
        }
        return romanValue;
    }

    static int parse(String str){
        int arabNumber=0;
        int result=0;

//        if(str.matches("[MDCLXVI\\Q+-*/\\E]*")  ){ //все, что между \\Q и \\E экранируется
            //System.out.println("str.matches passed");

            //**** делаем расчленинград исходной строки на числа и знаки операций ***
            //**** правильный порядок выполнения арифметических операций "не задавали" -
            //**** поэтому случаи из ряда а+б*с и (а+б)*с будут считаться не правильно (((
            String tmp;
            int lastEnd=0;
            char opDesignation='+';
            String REGEX = "[\\Q+-*/\\E]";
            String INPUT = str;
            Pattern p = Pattern.compile(REGEX);
            Matcher m = p.matcher(INPUT);   // получение matcher объекта

            while (m.find()){

                if (Main.debugMode) {
                    System.out.println("*********");
                    System.out.println("at pos " + m.start() + " find " + str.charAt(m.start()));
                }


                tmp=str.substring(lastEnd, m.start());
                lastEnd=m.end();
                if (Main.debugMode) System.out.println("tmp = "+tmp);

//                arabNumber=Rome2Arab2(tmp);
                arabNumber=Rome2Arab(tmp);
                if (Main.debugMode) {
                    System.out.println("last number = " + result);
                    System.out.println("operDesignation " + opDesignation);
                    System.out.println("Arab equals = " + arabNumber);
                }


                result=Calc.Calculate(result, arabNumber, opDesignation);
                if (Main.debugMode) System.out.println("result = "+result);

                opDesignation=str.charAt(m.start());

                if (Main.debugMode) System.out.println("*********");
            }

            if (Main.debugMode) System.out.println("*********");
//            tmp=str.substring(lastEnd, str.length()-1);
            tmp=str.substring(lastEnd);
            if (Main.debugMode) System.out.println("tmp = "+tmp);
            if (tmp.length()>0) arabNumber=Rome2Arab2(tmp);
            else arabNumber=0;
            if (arabNumber == 0) throw new IllegalArgumentException("WTF?! input must contain numbers from I to X !");


            if (Main.debugMode) {
                System.out.println("last number = " + result);
                System.out.println("operDesignation " + opDesignation);
                System.out.println("Arab equals = " + arabNumber);
            }
            result=Calc.Calculate(result, arabNumber, opDesignation);
            if (Main.debugMode) {
                System.out.println("result = " + result);
                System.out.println("*********");
            }
//        }
/*
        else{
            System.out.println("WTF?! Wrong roman digits!");
            throw new IllegalArgumentException("WTF?! Inputed digits isn't roman !");
        }
*/

        return result;
    }


}
