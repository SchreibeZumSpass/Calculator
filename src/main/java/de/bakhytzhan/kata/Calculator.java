package de.bakhytzhan.kata;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class Calculator implements Validatable {
    private final int a;
    private final int b;
    private final char sign;
    private final boolean isArabic;
    private int result;

    public Calculator(String input) {
        List<String> args = tokenizeInput(input); // Tokenize input to arguments and check if there are three arguments

        isArabic = areBothNumbersArabic(args.get(0), args.get(2)); // True if both numbers are arabic, false if roman, error otherwise

        if (isArabic) {
            a = Integer.parseInt(args.get(0));
            b = Integer.parseInt(args.get(2));
        } else {
            a = convertToArabic(args.get(0));
            b = convertToArabic(args.get(2));
        }
        sign = args.get(1).charAt(0);

        isSignValid(sign);

        areNumbersInRange(a, b); // Check if numbers are in range [1; 10]
    }

    public String getResult() {
        if (isArabic) {
            return String.valueOf(result);
        } else {
            return convertToRoman(result);
        }
    }

    public void calculate() {
        switch (sign) {
            case '+' -> add();
            case '-' -> subtract();
            case '*' -> multiply();
            case '/' -> divide();
        }
    }

    public void add() {
        result = a + b;
    }

    public void subtract() {
        if (!isArabic && (a <= b)) {
            printError("Roman numbers subtraction is not positive");
        }
        result = a - b;
    }

    public void multiply() {
        result = a * b;
    }

    public void divide() {
        if (!isArabic && (a / b == 0)) {
            printError("Roman numbers division is not positive");
        }
        result = a / b;
    }

    private int convertToArabic(String romanNumber) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        for (int i = 0; i < romanNumber.length() - 1; i++) {
            char symbol = romanNumber.charAt(i);
            char nextSymbol = romanNumber.charAt(i + 1);
            if (map.get(symbol) < map.get(nextSymbol)) {
                res -= map.get(symbol);
            } else {
                res += map.get(symbol);
            }
        }
        char lastLetter = romanNumber.charAt(romanNumber.length() - 1);
        res += map.get(lastLetter);

        return res;
    }

    public String convertToRoman(int arabicNumber) {
        int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] letters = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder romanNumber = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (arabicNumber >= values[i]) {
                arabicNumber -= values[i];
                romanNumber.append(letters[i]);
            }
        }
        return romanNumber.toString();
    }

}
