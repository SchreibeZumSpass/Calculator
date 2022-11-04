package de.bakhytzhan.kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public interface Validatable {

    default List<String> tokenizeInput(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input);
        if (tokenizer.countTokens() != 3) {
            printError("Arguments must be: number 1, sign, number 2.");
        }
        List<String> arguments = new ArrayList<>(3);
        while (tokenizer.hasMoreTokens()) {
            arguments.add(tokenizer.nextToken());
        }
        return arguments;
    }

    default void isSignValid(char sign) {
        List<Character> signs = Arrays.asList('+', '-', '*', '/');
        if (!signs.contains(sign)) {
            printError("Invalid arithmetic sign");
        }
    }

    default boolean areBothNumbersArabic(String a, String b) {
        String arabicRegex = "[0-9]+";
        boolean isArabicA = a.matches(arabicRegex);
        boolean isArabicB = b.matches(arabicRegex);

        if (isArabicA && isArabicB) { return true; } // Arabic notation

        String romanRegex = "M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})";
        boolean isRomanA = a.matches(romanRegex);
        boolean isRomanB = b.matches(romanRegex);

        if (isRomanA && isRomanB) { return false; } // Roman notation

        printError("Both numbers must be valid integer arabic or roman");

        return false; // Unreachable
    }

    default void areNumbersInRange(int a, int b) {
        if (a > 10 || b > 10 || a < 1 || b < 1) {
            printError("Both numbers must be in range [1; 10] or [I; X]");
        }
    }

    default void printError(String message) {
        System.out.println(new RuntimeException("ERROR! " + message).getMessage());
        System.exit(-1);
    }

}
