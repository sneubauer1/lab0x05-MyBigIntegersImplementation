package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

    //verificationTests();

    //bigPlusArithmeticTimeTests();
    //bigTimesArithmeticTimeTests();
    //bigKaratsubaTimesArithmeticTimeTests();
    //fibLoopBigTimeTests();
    //fibMatrixBigTimeTests();
    fibConstantBigTimeTests();

    }

    /** Get CPU time in nanoseconds since the program(thread) started. */
    /**
     * from: http://nadeausoftware.com/articles/2008/03/java_tip_how_get_cpu_and_user_time_benchmarking#TimingasinglethreadedtaskusingCPUsystemandusertime
     **/
    public static long getCpuTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadCpuTime() : 0L;
    }

    public static void verificationTests(){

        /*System.out.println("MyBigIntegers Class Verification:");
        MyBigIntegers A = new MyBigIntegers("2154654654");
        MyBigIntegers B = new MyBigIntegers("200000000300000000000000000000000000000000000000000");
        MyBigIntegers C;
        C = A.karatsubaTimes(B);

        System.out.println("The karatsuba product of\n"
                + A.abbreviatedValue() + " \nand\n" + B.abbreviatedValue() + " "
                + "\nis\n" + C.abbreviatedValue() + "\n");


        System.out.println("Comparing to BigInteger Class:");
        BigInteger Q = new BigInteger("2154654654");
        BigInteger R = new BigInteger("200000000300000000000000000000000000000000000000000");
        BigInteger S;
        S = Q.multiply(R);

        System.out.println("The product of\n"
                + abbreviatedValue(Q.toString()) + " \nand\n" + abbreviatedValue(R.toString()) + " "
                + "\nis\n" + abbreviatedValue(S.toString()) + "\n");*/

        System.out.println("fibConstantBig Verification:");
        BigInteger X = new BigInteger("200");
        BigInteger result;
        result = fibConstantBig(X);
        System.out.println("The "+X+"th term of fibConstantBig is \n"
                + abbreviatedValue(result.toString()) + "\n" );
    }

    public static void bigPlusArithmeticTimeTests(){

        int N_MAX = Integer.MAX_VALUE;
        String X1;
        String X2;
        long trialCount = 0;
        long maxTrials = 1000000;
        long maxTime = 150000000;
        long totalTime = 0;

        double previousTimeMeasured = 1;
        double expectedDoubleRatio = 1;
        System.out.printf("\n%15s %15s %15s %18s %25s %35s\n", "N", "X1", "X2", "Time", "Doubling Ratio", "Expected Doubling Ratio");
        System.out.printf("%130s", "---------------------------------------------------------------------------------------------------------------------\n");
        for (int N = 1; N <= N_MAX; N = N * 2){
            X1 = generateRandomNDigitNum( N );
            X2 = generateRandomNDigitNum( N );

            String zStr = generateZeroString( N + 1 );
            MyBigIntegers result = new MyBigIntegers(zStr);
            MyBigIntegers A = new MyBigIntegers(X1);
            MyBigIntegers B = new MyBigIntegers(X2);

            totalTime = 0;
            trialCount = 0;

            while ( totalTime < maxTime && trialCount < maxTrials ) {
                long timeStampBefore = getCpuTime();
                result = A.plus(B);
                long timeStampAfter = getCpuTime();
                long timeMeasured = timeStampAfter - timeStampBefore;
                totalTime = totalTime + timeMeasured;
                trialCount++;
            }
            long averageTimeMeasured = totalTime / trialCount;
            double doubleRatio = (averageTimeMeasured / previousTimeMeasured);
            if ( N == 1){
                expectedDoubleRatio = 4;
            } else {
                expectedDoubleRatio =(double) (N / (N / 2)) + (double)((N) / (N / 2));
            }
            previousTimeMeasured = averageTimeMeasured;
            System.out.printf("%15s %15s %15s %18s %25.2f %35.2f\n", N, abbreviatedValue(X1), abbreviatedValue(X2), averageTimeMeasured, doubleRatio, expectedDoubleRatio);
        }

    }

    public static void bigTimesArithmeticTimeTests(){

        int N_MAX = Integer.MAX_VALUE;
        String X1;
        String X2;
        long trialCount = 0;
        long maxTrials = 10000000;
        long maxTime = 1500000000;
        long totalTime = 0;

        double previousTimeMeasured = 1;
        double expectedDoubleRatio = 1;
        System.out.printf("\n%15s %15s %15s %18s %25s %35s\n", "N", "X1", "X2", "Time", "Doubling Ratio", "Expected Doubling Ratio");
        System.out.printf("%130s", "---------------------------------------------------------------------------------------------------------------------\n");
        for (int N = 1; N <= N_MAX; N = N * 2){
            X1 = generateRandomNDigitNum( N );
            X2 = generateRandomNDigitNum( N );

            String zStr = generateZeroString( N * 2 );
            MyBigIntegers result = new MyBigIntegers(zStr);
            MyBigIntegers A = new MyBigIntegers(X1);
            MyBigIntegers B = new MyBigIntegers(X2);

            totalTime = 0;
            trialCount = 0;

            while ( totalTime < maxTime && trialCount < maxTrials ) {
                long timeStampBefore = getCpuTime();
                result = A.times(B);
                long timeStampAfter = getCpuTime();
                long timeMeasured = timeStampAfter - timeStampBefore;
                totalTime = totalTime + timeMeasured;
                trialCount++;
            }
            long averageTimeMeasured = totalTime / trialCount;
            double doubleRatio = (averageTimeMeasured / previousTimeMeasured);
            if ( N == 1){
                expectedDoubleRatio = 4;
            } else {
                expectedDoubleRatio =(double) (N / (N / 2)) * (double)((N) / (N / 2));
            }
            previousTimeMeasured = averageTimeMeasured;
            System.out.printf("%15s %15s %15s %18s %25.2f %35.2f\n", N, abbreviatedValue(X1), abbreviatedValue(X2), averageTimeMeasured, doubleRatio, expectedDoubleRatio);
        }

    }

    public static void bigKaratsubaTimesArithmeticTimeTests(){

        int N_MAX = Integer.MAX_VALUE;
        String X1;
        String X2;
        long trialCount = 0;
        long maxTrials = 1000000;
        long maxTime = 150000000;
        long totalTime = 0;

        double previousTimeMeasured = 1;
        double expectedDoubleRatio = 1;
        System.out.printf("\n%15s %15s %15s %18s %25s %35s\n", "N", "X1", "X2", "Time", "Doubling Ratio", "Expected Doubling Ratio");
        System.out.printf("%130s", "---------------------------------------------------------------------------------------------------------------------\n");
        for (int N = 1; N <= N_MAX; N = N * 2){
            X1 = generateRandomNDigitNum( N );
            X2 = generateRandomNDigitNum( N );

            String zStr = generateZeroString( N * 2 );
            MyBigIntegers result = new MyBigIntegers(zStr);
            MyBigIntegers A = new MyBigIntegers(X1);
            MyBigIntegers B = new MyBigIntegers(X2);

            totalTime = 0;
            trialCount = 0;

            while ( totalTime < maxTime && trialCount < maxTrials ) {
                long timeStampBefore = getCpuTime();
                result = A.karatsubaTimes(B);
                long timeStampAfter = getCpuTime();
                long timeMeasured = timeStampAfter - timeStampBefore;
                totalTime = totalTime + timeMeasured;
                trialCount++;
            }
            long averageTimeMeasured = totalTime / trialCount;
            double doubleRatio = (averageTimeMeasured / previousTimeMeasured);
            if ( N == 1){
                expectedDoubleRatio = 2;
            } else {
                expectedDoubleRatio =  (Math.pow(N,log2(3))) / (Math.pow((N/2),log2(3))) ;
            }
            previousTimeMeasured = averageTimeMeasured;
            System.out.printf("%15s %15s %15s %18s %25.2f %35.2f\n", N, abbreviatedValue(X1), abbreviatedValue(X2), averageTimeMeasured, doubleRatio, expectedDoubleRatio);
        }

    }

    public static void fibLoopBigTimeTests() {
        //int N_MAX = Integer.MAX_VALUE;

        long trialCount = 0;
        long maxTrials = 1000;
        long maxTime = 150000;
        long totalTime = 0;

        double previousTimeMeasured = 1;
        double expectedDoubleRatio = 1;
        double previousTenXRatio = 1;
        double tenXRatio = 1;
        int N_MAX = 4;
        //int base = 1;
        //int X = 1;
        int base_MAX = 10000000;
        long timeResults[] = new long[base_MAX*10];
        long averageTimeMeasured = 1;
        System.out.printf("\n%15s %10s %20s %25s %16s %25s %20s\n", "N", "X", "fib(X)", "Run Time", "10x Ratio", "Expected 10x Ratio", "Expected +1 Ratio");
        System.out.printf("%135s", "-------------------------------------------------------------------------------------------------------------------------\n");

        for ( int base = 1; base <= base_MAX; base = base * 10) {

            for ( int X = base; X < base * 10; X = X + base) {

                String xStr = String.valueOf(X);

                MyBigIntegers xInput = new MyBigIntegers(xStr);
                MyBigIntegers xResult = new MyBigIntegers("0");

                totalTime = 0;
                trialCount = 0;

                while ( totalTime < maxTime && trialCount < maxTrials ) {

                    long timeStampBefore = getCpuTime();
                    xResult = fibLoopBig(xInput);
                    long timeStampAfter = getCpuTime();
                    long timeMeasured = timeStampAfter - timeStampBefore;
                    totalTime = totalTime + timeMeasured;
                    trialCount++;
                }
                
                averageTimeMeasured = totalTime / trialCount;
                //double doubleRatio = (averageTimeMeasured / previousTimeMeasured);
                timeResults[X] = averageTimeMeasured;
                double expected10XRatio = 1;
                if( X ==1 ){
                    tenXRatio = 1;
                    expected10XRatio = 10;
                } else if ( X == base){
                    tenXRatio = (double) timeResults[X] / timeResults[X/10];
                    long N = (long) (Math.log10(X) +1);

                }

                long N = (long) (Math.log10(X) +1);
                double expectedPlusOneNRatio = (double) N;
                if ( X < 10 ){

                    expected10XRatio = 10;
                } else {

                    expected10XRatio = (double) (X / (X / 10)) + log2(X);
                }
                previousTimeMeasured = averageTimeMeasured;
                System.out.printf("%15s %10s %20s %25s %16.2f %25.2f %20s\n", N, abbreviatedValue(xStr), xResult.abbreviatedValue(), averageTimeMeasured, tenXRatio, expected10XRatio, expectedPlusOneNRatio);
            }
        }
    }

    public static void fibMatrixBigTimeTests() {
        //int N_MAX = Integer.MAX_VALUE;

        long trialCount = 0;
        long maxTrials = 1000;
        long maxTime = 150000;
        long totalTime = 0;

        double previousTimeMeasured = 1;
        double expectedDoubleRatio = 1;
        double previousTenXRatio = 1;
        double tenXRatio = 1;
        int N_MAX = 4;
        //int base = 1;
        //int X = 1;
        int base_MAX = 10000000;
        long timeResults[] = new long[base_MAX*10];
        long averageTimeMeasured = 1;
        System.out.printf("\n%15s %10s %20s %25s %16s %25s %20s\n", "N", "X", "fib(X)", "Run Time", "10x Ratio", "Expected 10x Ratio", "Expected +1 Ratio");
        System.out.printf("%135s", "-------------------------------------------------------------------------------------------------------------------------\n");

        for ( int base = 1; base <= base_MAX; base = base * 10) {

            for ( int X = base; X < base * 10; X = X + base) {

                String xStr = String.valueOf(X);

                MyBigIntegers xInput = new MyBigIntegers(xStr);
                MyBigIntegers xResult = new MyBigIntegers("0");

                totalTime = 0;
                trialCount = 0;

                while ( totalTime < maxTime && trialCount < maxTrials ) {

                    long timeStampBefore = getCpuTime();
                    xResult = fibMatrixBig(xInput);
                    long timeStampAfter = getCpuTime();
                    long timeMeasured = timeStampAfter - timeStampBefore;
                    totalTime = totalTime + timeMeasured;
                    trialCount++;
                }

                averageTimeMeasured = totalTime / trialCount;
                //double doubleRatio = (averageTimeMeasured / previousTimeMeasured);
                timeResults[X] = averageTimeMeasured;
                double expected10XRatio = 1;
                if( X ==1 ){
                    tenXRatio = 1;
                    expected10XRatio = 10;
                } else if ( X == base){
                    tenXRatio = (double) timeResults[X] / timeResults[X/10];
                    long N = (long) (Math.log10(X) +1);

                }

                long N = (long) (Math.log10(X) +1);
                double expectedPlusOneNRatio = (double) N;
                if ( X < 10 ){

                    expected10XRatio = 10;
                } else {

                    expected10XRatio = (double) (X / (X / 10)) + log2(X); ;
                }
                previousTimeMeasured = averageTimeMeasured;
                System.out.printf("%15s %10s %20s %25s %16.2f %25.2f %20s\n", N, abbreviatedValue(xStr), xResult.abbreviatedValue(), averageTimeMeasured, tenXRatio, expected10XRatio, expectedPlusOneNRatio);
            }
        }
    }

    public static void fibConstantBigTimeTests() {
        //int N_MAX = Integer.MAX_VALUE;

        long trialCount = 0;
        long maxTrials = 100;
        long maxTime = 1500;
        long totalTime = 0;

        double previousTimeMeasured = 1;
        double expectedDoubleRatio = 1;
        double previousTenXRatio = 1;
        double tenXRatio = 1;
        int N_MAX = 4;
        //int base = 1;
        //int X = 1;
        int base_MAX = 10000000;
        long timeResults[] = new long[base_MAX*10];
        long averageTimeMeasured = 1;
        System.out.printf("\n%15s %10s %20s %25s %16s %25s %20s\n", "N", "X", "fib(X)", "Run Time", "10x Ratio", "Expected 10x Ratio", "Expected +1 Ratio");
        System.out.printf("%135s", "-------------------------------------------------------------------------------------------------------------------------\n");

        for ( int base = 1; base <= base_MAX; base = base * 10) {

            for ( int X = base; X < base * 10; X = X + base) {

                String xStr = String.valueOf(X);

                BigInteger xInput = new BigInteger(xStr);
                BigInteger xResult = new BigInteger("0");

                totalTime = 0;
                trialCount = 0;

                while ( totalTime < maxTime && trialCount < maxTrials ) {

                    long timeStampBefore = getCpuTime();
                    xResult = fibConstantBig(xInput);
                    long timeStampAfter = getCpuTime();
                    long timeMeasured = timeStampAfter - timeStampBefore;
                    totalTime = totalTime + timeMeasured;
                    trialCount++;
                }

                averageTimeMeasured = totalTime / trialCount;
                //double doubleRatio = (averageTimeMeasured / previousTimeMeasured);
                timeResults[X] = averageTimeMeasured;
                double expected10XRatio = 1;
                if( X ==1 ){
                    tenXRatio = 1;
                    expected10XRatio = 1;
                } else if ( X == base){
                    tenXRatio = (double) timeResults[X] / timeResults[X/10];
                    long N = (long) (Math.log10(X) +1);

                }

                long N = (long) (Math.log10(X) +1);
                double expectedPlusOneNRatio = N;
                if ( X < 10 ){

                    expected10XRatio = 1;
                } else {

                    expected10XRatio = (double) 1 * log2(X) ;
                }
                previousTimeMeasured = averageTimeMeasured;
                System.out.printf("%15s %10s %20s %25s %16.2f %25.2f %20.2f\n", N, abbreviatedValue(xStr), abbreviatedValue(xResult.toString()), averageTimeMeasured, tenXRatio, expected10XRatio, expectedPlusOneNRatio);
            }
        }
    }

    public static int[] multiplesofX(int base){
        int [] temp = new int[9];
        for(int i = 0; i < temp.length; i++){
            temp[i] = (i+1)*base;
        }
        return temp;
    }

    public static int log2(int x){
        return (int) (Math.log(x) / Math.log(2));
    }

    public static String generateRandomNDigitNum(int N){
        //code adapted from https://www.baeldung.com/java-random-string
        int leftLimit = 48; // 0
        int rightLimit = 57; // 9
        int targetStringLength = N;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public static String generateZeroString ( int N){
        StringBuilder zeroString = new StringBuilder( N );
        for ( int i = 0; i < N; i++){
            zeroString.append(0);
        }
        return zeroString.toString();
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

    public static MyBigIntegers fibLoopBig( MyBigIntegers X) {


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

    public static MyBigIntegers fibMatrixBig ( MyBigIntegers X){

        MyBigIntegers zero = new MyBigIntegers("0");
        MyBigIntegers one = new MyBigIntegers("1");
        MyBigIntegers fib[][] = new MyBigIntegers[][]{{one, one}, {one,zero}};


        if ( X.equalityOP(zero) ){
            return zero;
        }
        matrixPOWBig( fib, X.minus(one) );
        // returns upper left position of 2x2 matrix

        fib[0][0].setSign(false);

        return fib[0][0];
    }

    public static void matrixMultiplyBig(MyBigIntegers A[][], MyBigIntegers B[][]){
        //perform matrix multiplication on a 2x2 matrix
        MyBigIntegers a = A[0][0].times(B[0][0]).plus(A[0][1].times(B[1][0]) ) ;
        MyBigIntegers b = A[0][0].times(B[0][1]).plus(A[0][1].times(B[1][1]) ) ;
        MyBigIntegers c = A[1][0].times(B[0][0]).plus(A[1][1].times(B[1][0]) ) ;
        MyBigIntegers d = A[1][0].times(B[0][1]).plus(A[1][1].times(B[1][1]) ) ;

        //store results into each specific position of the matrix
        A[0][0] = a;
        A[0][1] = b;
        A[1][0] = c;
        A[1][1] = d;
    }

    public static void matrixPOWBig( MyBigIntegers fib[][], MyBigIntegers X){
        MyBigIntegers zero = new MyBigIntegers("0");
        MyBigIntegers one = new MyBigIntegers("1");
        MyBigIntegers two = new MyBigIntegers("2");
        MyBigIntegers mat[][] = new MyBigIntegers[][]{{one, one}, {one,zero}};
        MyBigIntegers i;

        for ( i = two; i.lessThanEquals(X); i = i.plus(one)){
            matrixMultiplyBig(fib, mat);
        }
    }

    public static int fibConstant( int X){
        double phi = (1 + Math.sqrt(5)) / 2;
        return (int) ((int) Math.pow(phi, X)
                        / Math.sqrt(5));
    }

    public static int pow(int X, int N){

        int result = X;
        if ( N == 0 ){
            result = 1;
            return result;
        }
        int i;
        for( i = 1; i * 2 < N; i = i * 2){
            result *= result;
        }
        while ( i < N){
            result *= X;
            i++;
        }
        return result;
    }

    public static BigInteger fibConstantBig(BigInteger X){
        int x = X.intValue();

        MathContext mc = new MathContext(x);
        BigDecimal FIVE = new BigDecimal("5");
        BigDecimal TWO = new BigDecimal("2");

        BigInteger nthTerm;
        BigDecimal squareRootof5 = FIVE.sqrt(mc);
        BigDecimal phi = (BigDecimal.ONE.add(squareRootof5)).divide(TWO);
        if( x == 2){
            nthTerm = BigInteger.ONE;
        } else{
            BigDecimal bround = phi.pow(x).divide(squareRootof5,mc);

            nthTerm =  bround.round(mc).toBigInteger();
            if ( x % 2 == 1 ){
                nthTerm = nthTerm.add(BigInteger.ONE);
            }
        }
        return nthTerm;
    }


}
