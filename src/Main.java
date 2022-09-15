import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //while (true) {
            System.out.print("Enter your expression in one line: ");
            String input = scanner.nextLine();
            String result = calc(input);
            System.out.println("Your answer: " + result);
        //}

    }

    public static String calc(String input) {
        String[] elements = input.split(" ");

        String firstNumber = elements[0];
        String sign = elements[1];
        String secondNumber = elements[2];

        int a, b;
        boolean isArabic = checkIfBothNumbersArabic(firstNumber, secondNumber);

        if (!isArabic) {
            a = romanToArabic(firstNumber);
            b = romanToArabic(secondNumber);
        } else {
            a = Integer.parseInt(firstNumber);
            b = Integer.parseInt(secondNumber);
        }

        //Check if numbers are in range of [1; 10].
        if (a > 10 || b > 10 || a < 1 || b < 1) {
            printError("Number(s) out of range [1; 10] or [I; X]");
        }

        //Check if arithmetic sign is valid
        String validSigns = "+-*/";
        if (!validSigns.contains(sign)) {
            printError("Invalid arithmetic sign");
        }

        int res = 0;
        switch (sign) {
            case "+" -> res = a + b;
            case "-" -> res = subtract(a, b, isArabic);
            case "*" -> res = a * b;
            case "/" -> res = divide(a, b, isArabic);
        }

        return (isArabic ? String.valueOf(res) : arabicToRoman(res));
    }

    public static boolean checkIfBothNumbersArabic(String a, String b) {
        String arabicRegex = "[0-9]+";
        boolean isArabicA = a.matches(arabicRegex);
        boolean isArabicB = b.matches(arabicRegex);

        if (isArabicA & isArabicB) { return true; } // Arabic notation

        String romanRegex = "M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})";
        boolean isRomanA = a.matches(romanRegex);
        boolean isRomanB = b.matches(romanRegex);

        if (isRomanA & isRomanB) { return false; } // Roman notation

        printError("Both numbers must be valid integer arabic or roman"); // Exception

        return false; // Unreachable
    }

    public static int romanToArabic(String romanNumber) {

        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        for (int i = 0; i < romanNumber.length()-1; i++) {
            char symbol = romanNumber.charAt(i);
            char nextSymbol = romanNumber.charAt(i+1);
            if (map.get(symbol) < map.get(nextSymbol)) {
                res -= map.get(symbol);
            } else {
                res += map.get(symbol);
            }
        }
        char lastLetter = romanNumber.charAt(romanNumber.length()-1);
        res += map.get(lastLetter);

        return res;
    }

    public static void printError(String message) {
        try {
            throw new Exception("ERROR! " + message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public static int subtract(int a, int b, boolean isArabic) {
        if (!isArabic && (a - b <= 0)) {
            printError("Roman numbers subtraction is not positive");
        }
        return a - b;
    }

    public static int divide(int a, int b, boolean isArabic) {
        if (!isArabic && (a / b == 0)) {
            printError("Roman numbers division is not positive");
        }
        return a / b;
    }

    public static String arabicToRoman(int arabNumber) {
        int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] letters = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder romanNumber = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (arabNumber >= values[i]) {
                arabNumber -= values[i];
                romanNumber.append(letters[i]);
            }
        }
        return romanNumber.toString();
    }

}
