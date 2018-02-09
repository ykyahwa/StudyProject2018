package com.tistory.ykyh.test_unittest;

/**
 * Created by eokhyunlee on 2018. 2. 9..
 */

public class Calculator {

    public int evaluate(String expression) {
        int sum = 0;
        for (String summand : expression.split("\\+")) {
            sum += Integer.valueOf(summand);
        }

        return sum;
    }
}
