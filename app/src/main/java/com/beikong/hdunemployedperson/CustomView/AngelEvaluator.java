package com.beikong.hdunemployedperson.CustomView;

import android.animation.TypeEvaluator;

public class AngelEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object Value) {
        int startAngel = (int) startValue;
        int Angel = (int) Value;
        int value = (int) (startAngel + (Angel) * fraction);
        return value;
    }
} 