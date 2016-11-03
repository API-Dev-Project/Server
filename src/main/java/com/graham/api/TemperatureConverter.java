package com.graham.api;

public class TemperatureConverter {

    public enum Type {
        CELSIUS, FAHRENHEIT
    }

    private double value;
    private Type type;

    public TemperatureConverter(double value, Type type) {
        this.value = Double.valueOf(value);
        this.type = type;

        calculate();
    }

    private void calculate() {
        if(type.equals(Type.CELSIUS)) {
            celsiusToFahrenheit();
        } else if (type.equals(Type.FAHRENHEIT)) {
            fahrenheitToCelsius();
        } else {
            value = 0.0;
        }
    }

    private void celsiusToFahrenheit() {
       value = (value * 9/5) + 32;
    }

    private void fahrenheitToCelsius() {
        value = (value - 32) * 9/5;
    }
}
