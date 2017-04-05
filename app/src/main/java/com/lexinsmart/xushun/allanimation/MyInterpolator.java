package com.lexinsmart.xushun.allanimation;

import android.animation.TimeInterpolator;

/**
 * Created by xushun on 2017/4/5.
 */

public class MyInterpolator implements TimeInterpolator {

    private float factor;

    public MyInterpolator(){
        this.factor = 0.15f;
    }
    @Override
    public float getInterpolation(float input) {
        float output = (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);

        return output;
    }
}
