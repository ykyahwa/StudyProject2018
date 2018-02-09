package com.tistory.ykyh.test_unittest;

import android.support.annotation.VisibleForTesting;

/**
 * Created by eokhyunlee on 2018. 2. 9..
 */

public class BmiCalculator {

    public BmiValue calculate(float height, float weight) {
        if (height < 0 || weight < 0 ) {
            throw new RuntimeException("키, 몸무게 이상");
        }

        float bmiValue = weight / ( height * height);
        return createValueObj(bmiValue);

    }

    @VisibleForTesting
    public BmiValue createValueObj(float bmiValue) {
        return new BmiValue(bmiValue);
    }
}
