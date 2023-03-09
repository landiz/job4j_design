package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "number")
public class Number {

    @XmlAttribute
    private String name;

    public Number(String number) {
        this.name = number;
    }

    public Number() {
    }

    @Override
    public String toString() {
        return "Number{"
                + "number='" + name + '\''
                + '}';
    }
}