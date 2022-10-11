package ru.job4j.io;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pairwithoutcomment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pairwithcomment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithoutKey() {
        String path = "./data/pairwithoutkey.properties";
        Config config = new Config(path);
        try {
            config.load();
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    void whenPairWithoutValue() {
        String path = "./data/pairwithoutvalue.properties";
        Config config = new Config(path);
        try {
            config.load();
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    void whenLineWithoutEqualSign() {
        String path = "./data/linewithoutequalsing.properties";
        Config config = new Config(path);
        try {
            config.load();
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    void whenValueWithEqualSign() {
        String path = "./data/valuewithequalsing.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password=123");
        assertThat(config.value("hibernate.connection.driver_class")).isEqualTo("org.postgresql.Driver=");
    }

    @Test
    void whenEmptyLine() {
        String path = "./data/emptyline.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password=123");
    }

    @Test
    void whenLineOnlyEqualSign() {
        String path = "./data/lineonlyequalsing.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password=123");
    }
}