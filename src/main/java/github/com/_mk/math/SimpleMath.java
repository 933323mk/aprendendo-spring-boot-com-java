package github.com._mk.math;

import github.com._mk.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;

import static github.com._mk.request.converters.NumberConverter.convertToDouble;
import static github.com._mk.request.converters.NumberConverter.isNumeric;

public class SimpleMath {

    public double sum(Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }

    public double subtraction(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }

    public double division(Double numberOne, Double numberTwo) {
        return numberOne / numberTwo;
    }

    public double multiplication(Double numberOne, Double numberTwo) {
        return numberOne * numberTwo;
    }

    public double mean(Double numberOne, Double numberTwo) {
        return (numberOne + numberTwo) / 2;
    }

    public double squareRoot(Double number) {
        return Math.sqrt(number);
    }
}
