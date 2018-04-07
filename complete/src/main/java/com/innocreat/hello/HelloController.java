package com.innocreat.hello;

import com.innocreat.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.innocreat.service.CalculatorService;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {

    @Autowired
    CalculatorService calculator;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return new Greeting(counter.incrementAndGet(),String.format(template,name));
    }

    //This API does calculation between two numbers
    @RequestMapping("/do-math")
    public Integer doCal(@RequestParam(value = "firstVal") Integer firstVal, @RequestParam(value = "secondVal") Integer secondVal, @RequestParam(value = "operator", defaultValue = "plus") String operator) {
        if (operator.equals("plus")) {
            System.out.println("Inside plus");
            return calculator.doAdd(firstVal, secondVal);
        } else if (operator.equals("mul")) {
            return calculator.doMul(firstVal, secondVal);
        }
        return 0;
    }
    
}
