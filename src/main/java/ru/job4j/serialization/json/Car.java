package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {
    private final boolean truck;
    private final int power;
    private final Number number;
    private final String name;
    private final String[] statuses;

    public Car(boolean truck, int power, Number number, String name, String[] statuses) {
        this.truck = truck;
        this.power = power;
        this.number = number;
        this.name = name;
        this.statuses = statuses;
    }

    public static void main(String[] args) {
        final Car car = new Car(true, 105, new Number("E 777 KX"), "WV polo",
                new String[]{"Slow", "Cheap"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson =
                "{"
                        + "\"truck\":false,"
                        + "\"power\":550,"
                        + "\"number\":"
                        + "{"
                        + "\"number\":\"M005MM\""
                        + "},"
                        + "\"name\":MB,"
                        + "\"statuses\":"
                        + "[\"Fast\",\"Expensive\"]"
                        + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }

    @Override
    public String toString() {
        return "Car{"
                + "truck=" + truck
                + ", power=" + power
                + ", number=" + number
                + ", name=" + name
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}