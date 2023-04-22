package dev.bakhytzhan.calculator.config;

import dev.bakhytzhan.calculator.Calculator;
import dev.bakhytzhan.calculator.Converter;
import dev.bakhytzhan.calculator.RomanCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "dev.bakhytzhan.calculator")
public class Config {

    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }

    @Bean
    public Converter getConverter() {
        return new Converter();
    }

    @Bean(name = "calculator")
    public Calculator getCalculator() {
        return new Calculator();
    }

    @Bean(name = "romanCalculator")
    public RomanCalculator getRomanCalculator(Converter converter) {
        return new RomanCalculator(converter);
    }

}
