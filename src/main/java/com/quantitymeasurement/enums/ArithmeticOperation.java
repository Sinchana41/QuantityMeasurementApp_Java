package com.quantitymeasurement.enums;

public enum ArithmeticOperation {

    ADD {
        @Override
        public double compute(double firstValue, double secondValue) {
            return firstValue + secondValue;
        }
    },

    SUBTRACT {
        @Override
        public double compute(double firstValue, double secondValue) {
            return firstValue - secondValue;
        }
    },

    DIVIDE {
        @Override
        public double compute(double firstValue, double secondValue) {

            if (secondValue == 0) {
                throw new ArithmeticException("Division by zero");
            }

            return firstValue / secondValue;
        }
    };

    public abstract double compute(double firstValue, double secondValue);

}
