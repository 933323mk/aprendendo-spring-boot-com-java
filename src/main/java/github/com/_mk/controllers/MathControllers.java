package github.com._mk.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathControllers {
    // http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    )throws Exception{

        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new IllegalArgumentException();
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    private boolean isNumeric(String strNumber) {
        if(strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replace(",", "."); // R$ 5,00 -> USD 5.00
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private double convertToDouble(String strNumber) {
        if(strNumber == null || strNumber.isEmpty()) throw new IllegalArgumentException();
        String number = strNumber.replace(",", "."); // R$ 5,00 -> USD 5.00
        return Double.parseDouble(number);
    }

    // http://localhost:8080/math/subtraction/3/5

    // http://localhost:8080/math/division/3/5

    // http://localhost:8080/math/sum/3/5
}
