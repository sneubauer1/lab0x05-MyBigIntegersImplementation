package com.company;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {

    verificationTests();
    }

    public static void verificationTests(){

        System.out.println("MyBigIntegers Class Verification:");
        MyBigIntegers A = new MyBigIntegers("-2154654654");
        MyBigIntegers B = new MyBigIntegers("-20000000000000000000000000000000000000000000000000");
        MyBigIntegers C;
        C = A.times(B);

        System.out.println("The product of\n"
                + A.abbreviatedValue() + " \nand\n" + B.abbreviatedValue() + " "
                + "\nis\n" + C.abbreviatedValue() + "\n");


        System.out.println("Comparing to BigInteger Class:");
        BigInteger Q = new BigInteger("-2154654654");
        BigInteger R = new BigInteger("-20000000000000000000000000000000000000000000000000");
        BigInteger S;
        S = Q.multiply(R);

        System.out.println("The product of\n"
                + abbreviatedValue(Q.toString()) + " \nand\n" + abbreviatedValue(R.toString()) + " "
                + "\nis\n" + abbreviatedValue(S.toString()) + "\n");
    }


    public static String abbreviatedValue(String s){
        if ( s.length() < 12){
            return s;
        } else if (s.charAt(0) == '-'){
            String s1 = s.substring(0,6);
            String s2 = s.substring(s.length()-5);
            String abVal = s1 +"..."+ s2;

            return abVal;
        } else {
            String s1 = s.substring(0,5);
            String s2 = s.substring(s.length()-5);
            String abVal = s1 +"..."+ s2;

            return abVal;
        }
    }

}
