package de.bakhytzhan.kata;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your expression in one line: ");
            String input = scanner.nextLine();

            Calculator calculator = new Calculator(input);

            calculator.calculate();

            System.out.println("Your answer: " + calculator.getResult());
        }

    }

}
