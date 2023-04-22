package dev.bakhytzhan.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RomanCalculator extends Calculator {

    private final Converter converter;

    @Autowired
    public RomanCalculator(Converter converter) {
        super();
        this.converter = converter;
    }

    @Override
    public void setArguments(List<String> args) {
        this.a = converter.convertToArabic(args.get(0));
        this.sign = args.get(1).charAt(0);
        this.b = converter.convertToArabic(args.get(2));
    }

    @Override
    void subtract() {
        if (a - b > 0) {
            result = a - b;
        } else {
            throw new CalculatorArgumentException("Roman numbers subtraction is not positive");
        }
    }

    @Override
    void divide() {
        if (a / b > 0) {
            result = a / b;
        } else {
            throw new CalculatorArgumentException("oman numbers division is not positive");
        }
    }

    @Override
    void printAnswer() {
        System.out.println("Your answer: " + converter.convertToRoman(result));
    }
}
