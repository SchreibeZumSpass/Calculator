package dev.bakhytzhan.calculator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Calculator {
    int a;
    int b;
    char sign;
    int result;

    public Calculator() {
    }

    public void setArguments(List<String> args) {
        this.a = Integer.parseInt(args.get(0));
        this.sign = args.get(1).charAt(0);
        this.b = Integer.parseInt(args.get(2));
    }

    public void calculate() {
        areNumbersInRange(a, b);

        switch (sign) {
            case '+' -> add();
            case '-' -> subtract();
            case '*' -> multiply();
            case '/' -> divide();
        }

        printAnswer();
    }

    void add() {
        result = a + b;
    }

    void subtract() {
        result = a - b;
    }

    void multiply() {
        result = a * b;
    }

    void divide() {
        result = a / b;
    }

    void areNumbersInRange(int a, int b) {
        if (a > 10 || b > 10 || a < 1 || b < 1) {
            throw new CalculatorArgumentException("Illegal value range! Both numbers must be in range [1; 10] or [I; X]");
        }
    }

    void printAnswer() {
        System.out.println("Your answer: " + result);
    }

}
