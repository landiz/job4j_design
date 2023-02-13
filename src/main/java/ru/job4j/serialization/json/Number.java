package ru.job4j.serialization.json;

public class Number {
    private final String name;

    public Number(String number) {
        this.name = number;
    }

    @Override
    public String toString() {
        return "Number{"
                + "number='" + name + '\''
                + '}';
    }
}