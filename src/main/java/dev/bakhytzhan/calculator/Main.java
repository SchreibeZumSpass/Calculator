package dev.bakhytzhan.calculator;

import dev.bakhytzhan.calculator.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your expression in one line: ");
            String input = scanner.nextLine();

            Calculator calculator = applicationContext.getBean(Calculator.class, input);

            calculator.calculate();

            System.out.println("Your answer: " + calculator.getResult());
        }

    }

}
