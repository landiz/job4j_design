package ru.job4j.io.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean truck;
    @XmlAttribute
    private int power;
    private Number number;
    private String name;
    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public Car(boolean truck, int power, Number number, String name, String[] statuses) {
        this.truck = truck;
        this.power = power;
        this.number = number;
        this.name = name;
        this.statuses = statuses;
    }

    public Car() {
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