package com.tistory.ykyh.test_unittest;

import java.io.Serializable;

/**
 * Created by eokhyunlee on 2018. 2. 9..
 */

class BmiValue implements Serializable{

    public float bmiValue;
    public BmiValue(float bmiValue) {
        this.bmiValue = bmiValue;
    }
}
