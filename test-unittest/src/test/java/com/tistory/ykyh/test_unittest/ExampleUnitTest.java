package com.tistory.ykyh.test_unittest;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void evaluateExpressionFail1() throws Exception {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2-3");
        assertEquals(6,sum);
    }
    @Test
    public void evaluateExpressionFail2() throws Exception {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        assertEquals(3,sum);
    }
    @Test
    public void evaluateExpression() throws Exception {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        assertEquals(6,sum);
    }
}