package ru.job4j.serialization.json;

public class Number {
    private final String number;

    public Number(String number) {
        this.number = number;
    }

    public String isNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Number{"
                + "number='" + number + '\''
                + '}';
    }
}