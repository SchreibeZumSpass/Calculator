package dev.bakhytzhan.calculator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

@Component
public class InputFormatValidator {

    private final List<String> args;

    public InputFormatValidator() {
        this.args = new ArrayList<>(3);
    }

    public List<String> validateInput(String input) {
        areThreeArgumentsPresent(input);
        isSignValid(args.get(1));

        return args;
    }

    private void areThreeArgumentsPresent(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input);

        if (tokenizer.countTokens() != 3) {
            throw new CalculatorArgumentException("Illegal arguments! Send two numbers separated by a sign");
        }

        tokenize(tokenizer);
    }

    private void tokenize(StringTokenizer tokenizer) {
        args.clear();
        while (tokenizer.hasMoreTokens()) {
            args.add(tokenizer.nextToken());
        }
    }

    private void isSignValid(String sign) {
        String[] signs = {"+", "-", "*", "/"};
        if (sign.length() != 1 || Arrays.stream(signs).noneMatch(sign::contains)) {
            throw new CalculatorArgumentException("Illegal argument! Invalid arithmetic sign");
        }
    }

    public boolean areBothArgsArabic() {
        String arabicRegex = "[0-9]+";
        boolean isArabicA = args.get(0).matches(arabicRegex);
        boolean isArabicB = args.get(2).matches(arabicRegex);

        if (isArabicA && isArabicB) {
            return true;
        }

        String romanRegex = "M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})";
        boolean isRomanA = args.get(0).matches(romanRegex);
        boolean isRomanB = args.get(2).matches(romanRegex);

        if (isRomanA && isRomanB) {
            return false;
        }

        throw new CalculatorArgumentException("Both numbers must be valid and of the same system");
    }

}
