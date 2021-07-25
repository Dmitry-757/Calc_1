package org.dng;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArabParse {
    private String inValue;
    private double inNumber;
    private double currentNumber;




    static int parse(String str){
        int arabNumber=0;
        int result=0;


        //**** делаем расчленинград исходной строки на числа и знаки операций ***
        String tmp;
        int lastEnd=0;
        char opDesignation='+';
        String REGEX = "[\\Q+-*/\\E]";
        String INPUT = str;
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT);   // получение matcher объекта

        while (m.find()){
            System.out.println("*********");
            System.out.println("at pos "+m.start()+" find "+str.charAt(m.start()));


            tmp=str.substring(lastEnd, m.start());
            lastEnd=m.end();
            System.out.println("tmp = "+tmp);

            arabNumber=Integer.valueOf(tmp);
            System.out.println("last number = "+result);
            System.out.println("operDesignation "+opDesignation);
            System.out.println("Arab equals = "+arabNumber);


            result=Calc.Calculate(result, arabNumber, opDesignation);
            System.out.println("result = "+result);

            opDesignation=str.charAt(m.start());

            System.out.println("*********");
        }

        System.out.println("*********");
        tmp=str.substring(lastEnd, str.length());
        //System.out.println("at pos "+lastEnd+" find "+str.charAt(m.start()));
        System.out.println("tmp = "+tmp);
//        arabNumber=Rome2Arab2(tmp);
        arabNumber=Integer.valueOf(tmp);


        System.out.println("last number = "+result);
        System.out.println("operDesignation "+opDesignation);
        System.out.println("Arab equals = "+arabNumber);

        result=Calc.Calculate(result, arabNumber, opDesignation);
        System.out.println("result = "+result);

        System.out.println("*********");

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