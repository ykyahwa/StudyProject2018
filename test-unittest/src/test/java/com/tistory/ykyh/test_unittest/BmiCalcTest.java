package com.tistory.ykyh.test_unittest;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by eokhyunlee on 2018. 2. 9..
 */

public class BmiCalcTest {

    //Spy 일반객체 , 일부 메서드만 교체 또는 호출
    @Spy
    private BmiCalculator calculator = new BmiCalculator();

    //test 호출 되기 이전에 항상 호출
    @Before
    public void setup() {
        //모든 테스트 이전에 spy 객체 준비
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void BMI계산() {
        BmiValue result = calculator.calculate(1, 1);
        assertNotNull(result);

        //verify 목 객체와 스파이 객체에서 메서드가 예상대로 호출되는지 확인하는 메서드
        verify(calculator, times(1)).createValueObj(1f);
    }

    @Test
    public void 키몸무게BMI계산() throws Exception {
        BmiValue result = calculator.calculate(1.7f, 60f);
        assertNotNull(result);

        verify(calculator, times(1)).createValueObj(20.761246f);
    }


}

