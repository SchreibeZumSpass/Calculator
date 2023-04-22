package dev.bakhytzhan.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class App {

    private final Scanner scanner;
    private final InputFormatValidator inputFormatValidator;
    private final Calculator calculator;
    private final RomanCalculator romanCalculator;

    @Autowired
    public App(Scanner scanner, InputFormatValidator inputFormatValidator,
                Calculator calculator, RomanCalculator romanCalculator) {
        this.scanner = scanner;
        this.inputFormatValidator = inputFormatValidator;
        this.calculator = calculator;
        this.romanCalculator = romanCalculator;
    }

    public void start() {
        while (true) {
            System.out.print("Enter your expression in one line: ");
            String input = scanner.nextLine();

            List<String> validatedArgs = inputFormatValidator.validateInput(input);

            if (inputFormatValidator.areBothArgsArabic()) {
                calculator.setArguments(validatedArgs);
                calculator.calculate();
            } else {
                romanCalculator.setArguments(validatedArgs);
                romanCalculator.calculate();
            }
        }
    }

}
