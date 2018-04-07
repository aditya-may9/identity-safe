package com.innocreat.service;

import org.springframework.stereotype.Service;

@Service("CalculatorService")
public class CalculatorService {

    public Integer doAdd(Integer value1, Integer value2)
    {
        return value1+value2;
    }

    public Integer doMul(Integer firstVal, Integer secondVal) {
        return firstVal-secondVal;
    }
}

