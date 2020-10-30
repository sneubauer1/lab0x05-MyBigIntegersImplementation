package com.company;

import java.math.BigInteger;

public class MyBigIntegers{

    private String value;
    private boolean sign;


    public MyBigIntegers() {
        value = "0";
        sign = false;
    }

    public MyBigIntegers(String s) {

        if( Character.isDigit(s.charAt(0)) )
        {
            setValue(s);
            sign = false;
        }
        else
        {
            setValue( s.substring(1) );
            sign = (s.charAt(0) == '-');
        }
    }


    public void setValue(String s) {
        value = s;
    }
    public String getValue() {
        return value;
    }

    public String value() {

        if ( sign == true){
            String neg = "-";
            String v = value;
            String s = neg + v;
            return s;
        } else{
            return value;
        }
    }

    public String abbreviatedValue() {
        if (value.length() < 12)
        {
            return value();
        }
        else
        {
            String s1 = value.substring(0,5);
            String s2 = value.substring(value.length()-5);
            String abVal = s1 +"..."+ s2;
            if (sign == true){
                String neg = "-";
                return neg + abVal;
            } else{
                return abVal;
            }
        }
    }

    public void setSign(boolean s) {
        sign = s;
    }

    public boolean getSign() {
        return sign;
    }

    public MyBigIntegers absolute() {
        return new MyBigIntegers( getValue() );
    }

    public boolean equalityOP (MyBigIntegers b){
        return equals(this, b);
    }

    public void equalOP(MyBigIntegers b){
        setValue( b.getValue() );
        setSign( b.getSign() );
    }

    public boolean equals(MyBigIntegers a, MyBigIntegers b){
        return a.getValue().equals(b.getValue())
                && a.getSign() == b.getSign();
    }

    public boolean lessThanEquals(MyBigIntegers b){
        return equals(this , b)
		|| less(this, b);
    }

    public MyBigIntegers incrementPostFix(){
        MyBigIntegers before = this;
        MyBigIntegers one = new MyBigIntegers("1");
        this.equalOP(this.plus(one));

        return before;
    }

    public boolean greater( MyBigIntegers a, MyBigIntegers b){
        return ! equals( a , b) && ! less ( a, b);
    }

    public boolean lessThan(MyBigIntegers b) {
        return less(this, b);
    }

    public boolean less(MyBigIntegers a, MyBigIntegers b){

        boolean sign1 = a.getSign();
        boolean sign2 = b.getSign();

        if(sign1 && ! sign2) // if a is - and b is +
            return true;

        else if(! sign1 && sign2)
            return false;

        else if(! sign1) // both +
        {
            if(a.getValue().length() < b.getValue().length() )
                return true;
            if(a.getValue().length() > b.getValue().length() )
                return false;
            return a.getValue().compareTo( b.getValue() ) < 0;
            //return n1.getValue().lessThan(n2.getValue()) ;//lessThan
        }
        else // both -
        {
            if(a.getValue().length() > b.getValue().length())
                return true;
            if(a.getValue().length() < b.getValue().length())
                return false;
            return a.getValue().compareTo( b.getValue() ) > 0; // greater with - sign is less
        }
    }

    public boolean greaterThan (MyBigIntegers b){
        return greater (this , b);
    }

    public MyBigIntegers plus(MyBigIntegers b){

        MyBigIntegers addition = new MyBigIntegers();
        if( getSign() == b.getSign() ) // both +ve or -ve
        {
            addition.setValue( add(getValue(), b.getValue() ) );
            addition.setSign( getSign() );
        }
        else // sign different
        {
            if( absolute().greaterThan(b.absolute())  )
            {
                addition.setValue( subtract(getValue(), b.getValue() ) );
                addition.setSign( getSign() );
            }
            else
            {
                addition.setValue( subtract(b.getValue(), getValue() ) );
                addition.setSign( b.getSign() );
            }
        }
        if(addition.getValue().equals("0")) // avoid (-0) problem
            addition.setSign(false);

        return addition;
    }

    public MyBigIntegers minus(MyBigIntegers b){

        b.setSign( ! b.getSign() ); // x - y = x + (-y)
        return this.plus(b);
    }

    public MyBigIntegers times(MyBigIntegers b){

        MyBigIntegers multiply = new MyBigIntegers();

        multiply.setValue( multiply(getValue(), b.getValue() ) );
        multiply.setSign( getSign() != b.getSign() );

        if(multiply.getValue().equals("0")) // avoid (-0) problem
            multiply.setSign(false);

        return multiply;
    }

    public String add(String num1, String num2) {

        if( num1.charAt(0) == '-'){
            num1 = num1.substring(1);
        }
        if( num2.charAt(0) == '-'){
            num2 = num2.substring(1);
        }

        // Before proceeding further, make sure length
        // of str2 is larger.
        if (num1.length() > num2.length()){
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        // Take an empty String for storing result
        String result = "";

        // Calculate difference in lengths
        int diff = num2.length() - num1.length();

        // Initially take carry zero
        int carry = 0;

        // Traverse from end of both Strings
        for (int i = num1.length() - 1; i>=0; i--)
        {
            // Do school mathematics, compute sum of
            // current digits and carry
            int sum = ((num1.charAt(i)-'0') +
                    (num2.charAt(i+diff)-'0') + carry);
            result += (char)(sum % 10 + '0');
            carry = sum / 10;
        }

        // Add remaining digits of str2[]
        for (int i = num2.length() - num1.length() - 1; i >= 0; i--)
        {
            int sum = ((num2.charAt(i) - '0') + carry);
            result += (char)(sum % 10 + '0');
            carry = sum / 10;
        }

        // Add remaining carry
        if (carry > 0)
            result += (char)(carry + '0');

        // reverse resultant String
        return new StringBuilder(result).reverse().toString();
    }

    public String subtract(String num1, String num2) {

        if( num1.charAt(0) == '-'){
            num1 = num1.substring(1);
        }
        if( num2.charAt(0) == '-'){
            num2 = num2.substring(1);
        }
        // Before proceeding further, make sure str1
        // is not smaller
        if (isSmaller(num1, num2)) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        // Take an empty string for storing result
        String result = "";

        // Reverse both of strings
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();

        int carry = 0;

        // Run loop till small string length
        // and subtract digit of str1 to str2
        for (int i = 0; i < num2.length(); i++) {
            // Do school mathematics, compute difference of
            // current digits
            int sub
                    = ((num1.charAt(i) - '0')
                    - (num2.charAt(i) - '0') - carry);

            // If subtraction is less then zero
            // we add then we add 10 into sub and
            // take carry as 1 for calculating next step
            if (sub < 0) {
                sub = sub + 10;
                carry = 1;
            }
            else
                carry = 0;

            result += (char)(sub + '0');
        }

        // subtract remaining digits of larger number
        for (int i = num2.length(); i < num1.length(); i++) {
            int sub = (num1.charAt(i) - '0' - carry);

            // if the sub value is -ve, then make it
            // positive
            if (sub < 0) {
                sub = sub + 10;
                carry = 1;
            }
            else
                carry = 0;

            result += (char)(sub + '0');
        }

        // reverse resultant string
        return new StringBuilder(result).reverse().toString();
    }

    public String multiply(String num1, String num2){

        if (num1.length() == 0 || num2.length() == 0)
            return "0";

        // will keep the result number in vector
        // in reverse order
        int result[] = new int[num1.length() + num2.length()];

        // Below two indexes are used to
        // find positions in result.
        int i_n1 = 0;
        int i_n2 = 0;

        // Go from right to left in num1
        for (int i = num1.length() - 1; i >= 0; i--)
        {
            int carry = 0;
            int n1 = num1.charAt(i) - '0';

            // To shift position to left after every
            // multipliccharAtion of a digit in num2
            i_n2 = 0;

            // Go from right to left in num2
            for (int j = num2.length() - 1; j >= 0; j--)
            {
                // Take current digit of second number
                int n2 = num2.charAt(j) - '0';

                // Multiply with current digit of first number
                // and add result to previously stored result
                // charAt current position.
                int sum = n1 * n2 + result[i_n1 + i_n2] + carry;

                // Carry for next itercharAtion
                carry = sum / 10;

                // Store result
                result[i_n1 + i_n2] = sum % 10;

                i_n2++;
            }

            // store carry in next cell
            if (carry > 0)
                result[i_n1 + i_n2] += carry;

            // To shift position to left after every
            // multipliccharAtion of a digit in num1.
            i_n1++;
        }

        // ignore '0's from the right
        int i = result.length - 1;
        while (i >= 0 && result[i] == 0)
            i--;

        // If all were '0's - means either both
        // or one of num1 or num2 were '0'
        if (i == -1)
            return "0";

        // genercharAte the result String
        String strResult = "";

        while (i >= 0)
            strResult += (result[i--]);

        return strResult;
    }

    public boolean isSmaller(String num1, String num2){

        if (num1.length() < num2.length()) {
            return true;
        }
        if (num2.length() < num1.length()) {
            return false;
        }
        for (int i = 0; i < num1.length(); i++) {
            if (num1.charAt(i) < num2.charAt(i)) {
                return true;
            } else if (num1.charAt(i) > num2.charAt(i)) {
                return false;
            }
        }
        return false;

    }

}
