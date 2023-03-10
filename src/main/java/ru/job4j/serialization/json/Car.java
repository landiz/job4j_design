package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Car {
    private boolean truck;
    private int power;
    private Number number;
    private String name;
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
        JSONObject jsonNumber = new JSONObject("{\"number\":\"E 777 KX\"}");
        List<String> list = new ArrayList<>();
        list.add("Slow");
        list.add("Cheap");
        JSONArray jsonStatuses = new JSONArray(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", car.getName());
        jsonObject.put("truck", car.isTruck());
        jsonObject.put("power", car.getPower());
        jsonObject.put("statuses", jsonStatuses);
        jsonObject.put("number", jsonNumber);
        System.out.println(jsonObject);
        System.out.println(new JSONObject(car));
    }

    public boolean isTruck() {
        return truck;
    }

    public int getPower() {
        return power;
    }

    public Number isNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String[] getStatuses() {
        return statuses;
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