package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        byte a = 33;
        short primerShort = 255;
        long c = -9223372036854L;
        float floatNumber = 27.5f;
        double sample = 59.36;
        boolean isAlive = true;
        char symbol = 'M';
        LOG.debug("User info name : {}, age : {},"
                + " byte a: {},"
                + " short primerShort: {},"
                + " long c: {},"
                + " float floatNumber: {},"
                + " double sample: {},"
                + " boolean isAlive: {},"
                + " char symbol: {}", name, age, a, primerShort, c, floatNumber, sample, isAlive, symbol);
    }
}