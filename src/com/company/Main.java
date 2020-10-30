package com.company;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {

    verificationTests();

    MyBigIntegers result;
    MyBigIntegers A = new MyBigIntegers("93");

    result = bigFibLoop(A);
    System.out.println("BigFibLoop Fn:"+result.value());

    int X = 93;
    long s = fibLoop(X);
    System.out.println("Fib X:"+s);
    }

    public static void verificationTests(){

        /*System.out.println("MyBigIntegers Class Verification:");
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
                + "\nis\n" + abbreviatedValue(S.toString()) + "\n");*/
    }

    public static long fibLoop( int X) {

        long A = 0;
        long B = 1;
        long next;
        if ( X == 0 || X == 1 ) {
            return X;
        }
        // use the previous two results to calculate next result up until X
        for ( int i = 2; i <= X; i++) {
            next = A + B;
            A = B;
            B = next;
        }
        return B;
    }

    public static MyBigIntegers bigFibLoop( MyBigIntegers X) {


        MyBigIntegers A = new MyBigIntegers("0");
        MyBigIntegers B = new MyBigIntegers("1");

        MyBigIntegers zero = new MyBigIntegers("0");
        MyBigIntegers one = new MyBigIntegers("1");
        MyBigIntegers two = new MyBigIntegers("2");
        MyBigIntegers next;
        MyBigIntegers i;

        if ( X.equalityOP(zero) || X.equalityOP(one) ) {
            return X;
        }
        // use the previous two results to calculate next result up until X
          for ( i = two ; i.lessThanEquals(X) ; i = i.plus(one)  ){
            next = A.plus(B);
            A = B;
            B = next;
        }
        return B;
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
