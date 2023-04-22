package dev.bakhytzhan.calculator;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Converter {

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

    public int convertToArabic(String romanNumber) {
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

}
